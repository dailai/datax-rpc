package com.alibaba.datax.common.element;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author ChengJie
 * @desciption
 * @date 2019/7/10 10:01
 **/
public class DataXJob implements Serializable {

    /**
     * jobId
     */
    private Long jobId;

    /**
     * 任务名称
     */
    private String jobName;

    /**
     * 任务状态
     */
    private int jobState;

    /**
     * 开始时间
     */
    private Long startTime;

    /**
     * 任务生命周期，用于标记任务是否有效
     */
    private Long jobLifeCycle;

    /**
     * 备注，目前展示用于标记在哪台机器上运行
     */
    private String remark;

    public DataXJob(){

    }

    public DataXJob(Long jobId){
        this.jobId=jobId;
        StringBuffer buffer=new StringBuffer();
        try {
            InetAddress address = InetAddress.getLocalHost();
            buffer.append(address.getCanonicalHostName()).append("【").append(address.getHostAddress()).append("】");
            this.remark=buffer.toString();
        } catch (UnknownHostException e) {
        }
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public int getJobState() {
        return jobState;
    }

    public void setJobState(int jobState) {
        this.jobState = jobState;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getJobLifeCycle() {
        return jobLifeCycle;
    }

    public void setJobLifeCycle(Long jobLifeCycle) {
        this.jobLifeCycle = jobLifeCycle;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
