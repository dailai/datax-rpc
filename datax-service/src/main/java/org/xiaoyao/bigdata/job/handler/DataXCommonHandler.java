package org.xiaoyao.bigdata.job.handler;

import com.alibaba.datax.common.element.DataXJob;
import com.alibaba.datax.common.element.DataXReport;
import com.alibaba.datax.common.job.DataXJobManager;
import com.alibaba.datax.common.statistics.VMInfo;
import com.alibaba.datax.common.util.Configuration;
import com.alibaba.datax.core.Engine;
import com.alibaba.datax.core.job.meta.State;
import com.alibaba.datax.core.util.ConfigParser;
import com.alibaba.datax.core.util.ConfigurationValidate;
import com.alibaba.datax.core.util.container.CoreConstant;
import javafx.util.Pair;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;
import org.xiaoyao.bigdata.common.enums.ReportStatus;
import org.xiaoyao.bigdata.job.dto.DataXJobDTO;
import org.xiaoyao.bigdata.report.dao.DataXReportDao;
import org.xiaoyao.bigdata.report.service.DataXReportService;
/**
 * @author ChengJie
 * @desciption
 * @date 2019/7/10 17:31
 **/
@Slf4j
@Component
@RefreshScope
public class DataXCommonHandler implements AbstractJobHandler {

    @Autowired
    private DataXReportService dataXReportService;

    @Autowired
    private DataXReportDao dataXReportDao;

    /**
     * datax目录默认为/user/local/services/datax，如果是windows系统自己在配置文件里面手动设置即可
     */
    @Value("${datax.home}")
    String dataXHome="/user/local/services/datax";


    /**
     * 在任务开始之前，先注册，并且校验任务是否在执行，如果在执行则直接返回异常
     * @param dataXJobDTO
     */
    @Override
    public Configuration beforeStartJob(DataXJobDTO dataXJobDTO) {

        //这里先设置好环境变量，方便搜索我们的datax的目录
        System.setProperty("datax.home","dataXHome");

        Configuration configuration=ConfigParser.parseWithJobConf(dataXJobDTO.getJobConf());

        configuration.set(CoreConstant.DATAX_CORE_CONTAINER_JOB_ID, dataXJobDTO.getJobId());

        //打印vmInfo
        VMInfo vmInfo = VMInfo.getVmInfo();
        if (vmInfo != null) {
            log.info(vmInfo.toString());
        }

        log.info("\n" + Engine.filterJobConfiguration(configuration) + "\n");

        log.debug(configuration.toJSON());
        //记录当前jvm参数以及cpu，内存信息
        DataXJobManager.INSTANCE.getJob(dataXJobDTO.getJobId()).getValue().setVmInfo(vmInfo.totalString());
        DataXJobManager.INSTANCE.getJob(dataXJobDTO.getJobId()).getValue().setConfigurationInfo(Engine.filterJobConfiguration(configuration));

        //校验任务格式以及是否已经在执行了
        ConfigurationValidate.doValidate(configuration);

        Pair<DataXJob,DataXReport> dataXJob=DataXJobManager.INSTANCE.getJob(dataXJobDTO.getJobId());
        if(dataXJob==null){
            dataXJob=new Pair<>(new DataXJob(dataXJobDTO.getJobId()),new DataXReport(dataXJobDTO.getJobId()));
            //校验完毕后在缓存中注册任务信息
            dataXJob.getKey().setJobState(State.SUBMITTING.value());
        }

        DataXJobManager.INSTANCE.registJob(dataXJob);

        return configuration;
    }

    /**
     * 启动任务，开始推数
     * @param configuration
     * @return
     */
    @Override
    public Pair<DataXJob, DataXReport> startJob(Configuration configuration) {

        try{
            Engine engine = new Engine();
            engine.start(configuration);
      }catch (Exception e){
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return DataXJobManager.INSTANCE.getJob(configuration.getLong(CoreConstant.DATAX_CORE_CONTAINER_JOB_ID));
    }

    /**
     * 保证当前任务的快照
     * @param jobInfo
     */
    @Override
    public void beforeCompleteJob(Pair<DataXJob, DataXReport> jobInfo) {
        jobInfo.getValue().setStatus(ReportStatus.SUCCESSED.getValue());
        dataXReportDao.save(jobInfo.getValue());
    }

    /**
     * 上报日志，如果上报失败则将状态设置为失败，并持久化，后续交由定时任务去处理
     * @param jobInfo
     */
    @Override
    public void afterCompleteJob(Pair<DataXJob, DataXReport> jobInfo) {
        try {
            //RetryUtil.executeWithRetry(() -> dataXReportService.report(jobInfo.getValue()), 3, 60000, true);
            //上报日志
            BeanUtils.copyProperties(jobInfo.getKey(),jobInfo.getValue());
            dataXReportService.report(jobInfo.getValue());
        } catch (Exception e) {
            dataXReportDao.changeStatus(jobInfo.getValue().getJobId(), ReportStatus.FAILED.getValue());
            log.error(e.getMessage());
        }
    }

    @Override
    public void beforeStopJob(Long jobId) {

    }

    @Override
    public Pair<DataXJob, DataXReport> stopJob(Long jobId) {
        return null;
    }

    @Override
    public void afterStopJob(Pair<DataXJob, DataXReport> jobInfo) {

    }

    @Override
    public Pair<DataXJob, DataXReport> reportResult(Long jobId) {
        return null;
    }

    @Override
    public void suspendJob(Long id) {

    }

    @Override
    public void continueJob(Long id) {

    }
}
