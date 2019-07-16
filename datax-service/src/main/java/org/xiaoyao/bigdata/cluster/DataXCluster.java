package org.xiaoyao.bigdata.cluster;

import lombok.Data;

import java.util.List;

/**
 * @author ChengJie
 * @desciption
 * @date 2019/7/16 9:58
 **/
@Data
public class DataXCluster {

    String reportUrl;

    List<DataXNode> nodes;

}
