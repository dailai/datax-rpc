package com.alibaba.datax.common.element;

/**
 * @author ChengJie
 * @desciption
 * @date 2019/7/10 10:01
 **/
public class DataXJob {

    private Long jobId;

    private String jobName;

    private int jobState;

    public DataXJob(Long jobId){
        this.jobId=jobId;
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
}
