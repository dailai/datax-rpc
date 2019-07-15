package org.xiaoyao.bigdata.report.service;

import com.alibaba.datax.common.element.DataXReport;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.xiaoyao.bigdata.common.entity.ResponeResult;
import org.xiaoyao.bigdata.report.service.hystrix.DataXReportServiceHystrix;

/**
 * @author ChengJie
 * @desciption
 * @date 2019/7/15 17:51
 **/
@FeignClient(name = "ods-admin-service", fallback = DataXReportServiceHystrix.class)
public interface DataXReportService {

    @PostMapping("/ods/rpc/admin/report/pushLog")
    ResponeResult report(DataXReport dataXReport);


}
