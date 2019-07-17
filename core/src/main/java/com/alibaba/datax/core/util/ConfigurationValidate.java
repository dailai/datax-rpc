package com.alibaba.datax.core.util;

import com.alibaba.datax.common.element.DataXJob;
import com.alibaba.datax.common.element.DataXReport;
import com.alibaba.datax.common.exception.DataXException;
import com.alibaba.datax.common.job.DataXJobManager;
import com.alibaba.datax.common.util.Configuration;
import com.alibaba.datax.core.job.meta.State;
import com.alibaba.datax.core.util.container.CoreConstant;
import javafx.util.Pair;
import org.apache.commons.lang.Validate;

/**
 * Created by jingxing on 14-9-16.
 *
 * 对配置文件做整体检查
 */
public class ConfigurationValidate {
    public static void doValidate(Configuration allConfig) {
        Validate.isTrue(allConfig!=null, "");

        coreValidate(allConfig);

        pluginValidate(allConfig);

        jobValidate(allConfig);
    }

    private static void coreValidate(Configuration allconfig) {
        return;
    }

    private static void pluginValidate(Configuration allConfig) {
        return;
    }

    private static void jobValidate(Configuration allConfig) {
        return;
    }
}
