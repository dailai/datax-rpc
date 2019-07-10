package com.alibaba.datax.common.element;

import com.alibaba.datax.common.statistics.VMInfo;
import com.alibaba.datax.common.util.Configuration;

import java.util.Date;

/**
 * @author ChengJie
 * @desciption
 * @date 2019/7/10 10:01
 **/
public class DataXJob {

    private Long jobId;

    private String jobName;

    private int state;

    private Double progress;

    private Integer failCount;

    private Configuration configuration;

    private VMInfo vmInfo;

    private Integer taskCount;

    private long startTimeStamp;

    private long endTimeStamp;

    private long startTransferTimeStamp;

    private long endTransferTimeStamp;

//    List<DataXTaskReport>

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

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Double getProgress() {
        return progress;
    }

    public void setProgress(Double progress) {
        this.progress = progress;
    }

    public Integer getFailCount() {
        return failCount;
    }

    public void setFailCount(Integer failCount) {
        this.failCount = failCount;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    public VMInfo getVmInfo() {
        return vmInfo;
    }

    public void setVmInfo(VMInfo vmInfo) {
        this.vmInfo = vmInfo;
    }

    public Integer getTaskCount() {
        return taskCount;
    }

    public void setTaskCount(Integer taskCount) {
        this.taskCount = taskCount;
    }

    public long getStartTimeStamp() {
        return startTimeStamp;
    }

    public void setStartTimeStamp(long startTimeStamp) {
        this.startTimeStamp = startTimeStamp;
    }

    public long getEndTimeStamp() {
        return endTimeStamp;
    }

    public void setEndTimeStamp(long endTimeStamp) {
        this.endTimeStamp = endTimeStamp;
    }

    public long getStartTransferTimeStamp() {
        return startTransferTimeStamp;
    }

    public void setStartTransferTimeStamp(long startTransferTimeStamp) {
        this.startTransferTimeStamp = startTransferTimeStamp;
    }

    public long getEndTransferTimeStamp() {
        return endTransferTimeStamp;
    }

    public void setEndTransferTimeStamp(long endTransferTimeStamp) {
        this.endTransferTimeStamp = endTransferTimeStamp;
    }
}
