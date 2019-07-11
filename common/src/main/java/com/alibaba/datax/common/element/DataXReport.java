package com.alibaba.datax.common.element;

import com.alibaba.datax.common.statistics.VMInfo;

public class DataXReport {
    private Long jobId;
    private String jobName;
    private int state;
    /**
     * 完成进度
     */
    private Double progress;
    /**
     * 失败次数
     */
    private Integer failCount;
    /**
     * jvm快照
     */
    private VMInfo vmInfo;

    private Integer taskCount;

    private long startTimeStamp;

    private long endTimeStamp;

    private long startTransferTimeStamp;

    private long endTransferTimeStamp;

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

    private String totalRecordCount;

    private String totalFailRecordCount;


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

    public String getRunTimes() {
        return runTimes;
    }

    public void setRunTimes(String runTimes) {
        this.runTimes = runTimes;
    }

    public String getAvgFlow() {
        return avgFlow;
    }

    public void setAvgFlow(String avgFlow) {
        this.avgFlow = avgFlow;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getTotalRecordCount() {
        return totalRecordCount;
    }

    public void setTotalRecordCount(String totalRecordCount) {
        this.totalRecordCount = totalRecordCount;
    }

    public String getTotalFailRecordCount() {
        return totalFailRecordCount;
    }

    public void setTotalFailRecordCount(String totalFailRecordCount) {
        this.totalFailRecordCount = totalFailRecordCount;
    }


}
