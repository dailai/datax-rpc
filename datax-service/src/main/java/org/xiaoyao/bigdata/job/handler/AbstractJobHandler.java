package org.xiaoyao.bigdata.job.handler;

import com.alibaba.datax.common.element.DataXJob;
import com.alibaba.datax.common.element.DataXReport;
import com.alibaba.datax.common.util.Configuration;
import com.alibaba.nacos.api.exception.NacosException;
import javafx.util.Pair;
import org.xiaoyao.bigdata.job.dto.DataXJobDTO;

/**
 * @author ChengJie
 * @desciption
 * @date 2019/7/10 16:38
 **/
public interface AbstractJobHandler {

    /**
     * 任务开始之前需校验任务是否在执行，查看其他节点是否存在此任务，查看当前节点是否需要断点续传
     * @param dataXJobDTO
     */
    Configuration beforeStartJob(DataXJobDTO dataXJobDTO) throws Exception;

    /**
     * 启动任务
     * @param configuration
     */
    Pair<DataXJob,DataXReport> startJob(Configuration configuration);

    /**
     * 可以在这里考虑做一些数据的校验，如脏数据处理，如果任务失败可以持久化任务快照，然后做重试机制
     */
    void beforeCompleteJob(Pair<DataXJob,DataXReport> jobInfo);

    /**
     * 任务执行完毕之后上报推数任务日志
     */
    void afterCompleteJob(Pair<DataXJob,DataXReport> jobInfo) throws Exception;

    /**
     * 结束之前需保存当前任务快照，方便后续断点续传
     */
    void beforeStopJob(Long jobId);

    Pair<DataXJob,DataXReport> stopJob(Long jobId);

    /**
     * 上报任务状态
     */
    void afterStopJob(Pair<DataXJob,DataXReport> jobInfo);

    /**
     * 实时上报当前任务状态
     * @return
     */
    Pair<DataXJob,DataXReport> reportResult(Long jobId);

    /**
     * 暂停任务
     * @param id
     */
    void  suspendJob(Long id);

    /**
     * 重启任务
     * @param id
     */
    void continueJob(Long id);

}
