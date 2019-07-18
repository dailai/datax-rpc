package org.xiaoyao.bigdata.job.runnable;

import com.alibaba.datax.common.element.DataXJob;
import com.alibaba.datax.common.element.DataXReport;
import com.alibaba.datax.common.util.Configuration;
import javafx.util.Pair;
import org.xiaoyao.bigdata.job.handler.AbstractJobHandler;
import java.util.concurrent.Callable;

/**
 * @author ChengJie
 * @desciption
 * @date 2019/7/18 20:44
 **/
public class DataXJobWorker implements Callable {

    private AbstractJobHandler handler;

    private Configuration configuration;

    public DataXJobWorker(AbstractJobHandler handler,Configuration configuration){
        this.handler=handler;
        this.configuration=configuration;
    }

    @Override
    public Pair<DataXJob,DataXReport> call() throws Exception {
        return  this.handler.startJob(this.configuration);
    }
}
