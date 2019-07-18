package org.xiaoyao.bigdata.report.dao;

import com.alibaba.datax.common.element.DataXReport;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ChengJie
 * @desciption
 * @date 2019/7/15 19:18
 **/
@Component
public class DataXReportDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 保存快照
     * @param dataXReport
     */
    @Transactional
    public void save(DataXReport dataXReport) throws IllegalAccessException {
        Pair<String,List> param=createInsertParam(dataXReport);
        jdbcTemplate.update(param.getKey(),param.getValue());
    }

    @Transactional
    public void changeStatus(Long jobId,Integer status){
        String sql= "update datax_report set status=?";
        jdbcTemplate.update(sql,status);
    }

    /**
     * 动态构造 insert sql
     * @param dataXReport
     * @return
     * @throws IllegalAccessException
     */
    public Pair<String,List> createInsertParam(DataXReport dataXReport) throws IllegalAccessException {
        StringBuffer sql=new StringBuffer();
        sql.append("insert into report(");
        List params=new ArrayList();
        Field[] declaredFields = dataXReport.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            params.add(field.get(dataXReport));
            sql.append(field.getName()).append(",");
        }
        //去掉最后一个多余的，
        sql.deleteCharAt(sql.length()-1).append(")values(");
        for(int i=0;i<declaredFields.length;i++){
            sql.append("?").append(",");
        }
        sql.deleteCharAt(sql.length()-1).append(")");
        return new Pair<>(sql.toString(),params);
    }

}
