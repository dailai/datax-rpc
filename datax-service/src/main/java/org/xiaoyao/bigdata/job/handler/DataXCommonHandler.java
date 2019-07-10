package org.xiaoyao.bigdata.job.handler;

import com.alibaba.datax.common.element.DataXJob;
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
import org.xiaoyao.bigdata.job.dto.DataXJobDTO;
import org.xiaoyao.bigdata.report.entity.DataXReport;

/**
 * @author ChengJie
 * @desciption
 * @date 2019/7/10 17:31
 **/
@Slf4j
public class DataXCommonHandler implements AbstractJobHandler {
    @Override
    public void beforeStartJob(Long jobId) {

    }

    @Override
    public Pair<DataXJob, DataXReport> startJob(DataXJobDTO dataXJobDTO) {
        DataXJob dataXJob=new DataXJob();
        try{
            Configuration configuration=ConfigParser.parseWithJobConf(dataXJobDTO.getJobConf());

            configuration.set(CoreConstant.DATAX_CORE_CONTAINER_JOB_ID, dataXJobDTO.getJobId());

            //打印vmInfo
            VMInfo vmInfo = VMInfo.getVmInfo();
            if (vmInfo != null) {
                log.info(vmInfo.toString());
            }

            log.info("\n" + Engine.filterJobConfiguration(configuration) + "\n");

            log.debug(configuration.toJSON());

            //校验任务格式以及是否已经在执行了
            ConfigurationValidate.doValidate(configuration);

            //校验完毕后在缓存中注册任务信息
            dataXJob.setJobId(dataXJobDTO.getJobId());
            dataXJob.setState(State.SUBMITTING.value());
            dataXJob.setConfiguration(configuration);
            dataXJob.setFailCount(0);
            dataXJob.setProgress(0d);
            DataXJobManager.INSTANCE.registJob(dataXJob);
            Engine engine = new Engine();
            engine.start(configuration);
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public void beforeCompleteJob(Pair<DataXJob, DataXReport> jobInfo) {

    }

    @Override
    public void afterCompleteJob(Pair<DataXJob, DataXReport> jobInfo) {

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
