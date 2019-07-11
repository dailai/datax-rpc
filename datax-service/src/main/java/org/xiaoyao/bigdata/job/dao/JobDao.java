package org.xiaoyao.bigdata.job.dao;

import com.alibaba.datax.common.element.DataXJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class JobDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void save(DataXJob dataXJob){

        String sql="insert into job(id,namae,a) values(?,?,?)";
        jdbcTemplate.update(sql,dataXJob.getJobId(),dataXJob.getJobName(),dataXJob.getAvgFlow());
    }




}
