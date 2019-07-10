package org.xiaoyao.bigdata.job.entity;

import com.alibaba.datax.common.statistics.VMInfo;
import com.alibaba.datax.common.util.Configuration;
import lombok.Getter;
import lombok.Setter;

/**
 * @author ChengJie
 * @desciption
 * @date 2019/7/10 10:01
 **/
@Getter
@Setter
public class DataXJob {

    Long jobId;

    String jobName;

    Integer state;

    Double progress;

    Integer failCount;

    Configuration configuration;

    VMInfo vmInfo;

    Integer taskCount;



}
