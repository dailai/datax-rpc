package org.xiaoyao.bigdata.rpc;

import com.alibaba.datax.common.element.DataXJob;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.xiaoyao.bigdata.common.entity.ResponeResult;

@FeignClient(name = "odsAdminService", fallback = OdsAdminService.class)
public interface OdsAdminService {

    @PostMapping("/rpc/admin")
    ResponeResult report(DataXJob dataXJob);

}
