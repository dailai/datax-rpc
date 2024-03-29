package org.xiaoyao.bigdata.job.service;

import com.alibaba.datax.common.element.DataXJob;
import com.alibaba.nacos.api.exception.NacosException;
import org.xiaoyao.bigdata.job.dto.DataXJobDTO;

import java.util.List;
import java.util.Map;

/**
 * @author ChengJie
 * @desciption
 * @date 2019/7/10 10:14
 **/
public interface  JobService   {

    /**
     * 启动任务
     * @param dataXJobDTO
     */
    void startJob(DataXJobDTO dataXJobDTO) throws Exception;

    /**
     * 结束任务
     * @param jobId
     */
    void stopJob(Long jobId) throws Exception;

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

    /**
     * 根据条件查询对应任务
     * @param params
     * @return
     */
    List<DataXJob> list(Map params);

    /**
     * 获取符合条件的任务数量
     * @param params
     * @return
     */
    Long count(Map params);

    /**
     * 获取任务
     * @param jobId
     * @return
     */
    DataXJob get(Long jobId);

    /**
     * 保存任务快照
     * @param dataXJob
     */
    void saveJobSnapshot(DataXJob dataXJob);

}
