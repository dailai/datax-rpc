package org.xiaoyao.bigdata.report.entity;

import java.util.Date;

/**
 * @author ChengJie
 * @desciption
 * @date 2019/7/10 10:01
 **/
public class DataXReport {

    /**
     * 主键id
     */
    private Long id;

    /**
     * 任务id
     */
    private Long jobId;

    /**
     * 任务名称
     */
    private Long jobName;

    /**
     * 启动时间
     */
    private Date runStartDate;

    /**
     * 任务结束时间
     */
    private Date runEndDate;
    /**
     * 任务耗时
     */
    private String runTimes;
    /**
     * 任务平均流量
     */
    private String avgFlow;
    /**
     * 任务写入速度
     */
    private String speed;
    /**
     * 读出记录总数
     */
    private Long readCount;
    /**
     * 失败次数
     */
    private Long failCount;
    /**
     * 备注
     */
    private String remark;

    /**
     * 任务执行状态
     */
    private Integer jobState;



}
