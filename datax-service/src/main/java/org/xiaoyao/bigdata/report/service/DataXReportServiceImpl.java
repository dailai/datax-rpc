package org.xiaoyao.bigdata.report.service;

import com.alibaba.datax.common.element.DataXJob;
import com.alibaba.datax.common.job.AbstractDataxReportService;
import com.alibaba.datax.common.util.RetryUtil;
import org.springframework.stereotype.Service;
import org.xiaoyao.bigdata.rpc.OdsAdminService;

import javax.annotation.Resource;

@Service
public class DataXReportServiceImpl implements AbstractDataxReportService {

    @Resource
    private OdsAdminService odsAdminService;

    @Override
    public void reportJob(DataXJob dataXJob) {
        try {
            RetryUtil.executeWithRetry(() -> odsAdminService.report(dataXJob), 3, 60000, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
