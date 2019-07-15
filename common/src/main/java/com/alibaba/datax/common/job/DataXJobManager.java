package com.alibaba.datax.common.job;

import com.alibaba.datax.common.element.DataXJob;
import com.alibaba.datax.common.element.DataXReport;
import com.alibaba.fastjson.JSONObject;
import javafx.util.Pair;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum  DataXJobManager {

    INSTANCE;

    private static  Map<Long ,Pair<DataXJob,DataXReport>> jobMap=new ConcurrentHashMap<>();

    /**
     * 保存任务
     * @param dataXJobInfo
     */
    public void registJob(Pair<DataXJob,DataXReport> dataXJobInfo){
        jobMap.put(dataXJobInfo.getKey().getJobId(),dataXJobInfo);
    }

    /**
     * 其实和registJob一样,怕引起歧义，所以分成两个方法
     * @param dataXJobInfo
     */
    public void refreshJob(Pair<DataXJob,DataXReport> dataXJobInfo){
        jobMap.put(dataXJobInfo.getKey().getJobId(),dataXJobInfo);
    }

    /**
     * 获取一个任务
     * @param id
     * @return
     */
    public Pair<DataXJob,DataXReport> getJob(Long id){
        return jobMap.get(id);
    }

    /**
     * 清空所有的任务信息
     */
    public void clear(){
        jobMap.clear();
    }

    /**
     * 删除某一个任务信息
     * @param jobId
     */
    public void removeJob(Long jobId){
        jobMap.remove(jobId);
    }

    /**
     * 上报任务
     * @param dataXJobInfo
     */
    public void reportJob(Pair<DataXJob,DataXReport> dataXJobInfo){
        refreshJob(dataXJobInfo);
        System.out.println("上报任务结果："+JSONObject.toJSONString(dataXJobInfo.getValue()));
    }

}
