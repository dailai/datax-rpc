package org.xiaoyao.bigdata.report.service.hystrix;

import com.alibaba.datax.common.element.DataXReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.xiaoyao.bigdata.common.entity.ResponeResult;
import org.xiaoyao.bigdata.report.dao.DataXReportDao;
import org.xiaoyao.bigdata.report.service.DataXReportService;

/**
 * @author ChengJie
 * @desciption
 * @date 2019/7/15 17:53
 **/
@Component
public class DataXReportServiceHystrix  implements DataXReportService {

    @Autowired
    private DataXReportDao reportDao;

    @Override
    public ResponeResult report(DataXReport dataXReport) {
        //触发熔断之后保存报告快照
        try {
            reportDao.save(dataXReport);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return ResponeResult.ok();
    }
}
