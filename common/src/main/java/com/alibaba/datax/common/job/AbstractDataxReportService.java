package com.alibaba.datax.common.job;

import com.alibaba.datax.common.element.DataXJob;
import com.alibaba.datax.common.element.DataXReport;

/**
 * @author ChengJie
 * @desciption
 * @date 2019/7/11 20:00
 **/
public interface AbstractDataxReportService {
    void reportJob(DataXJob dataXJob);
}
