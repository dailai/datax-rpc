package org.xiaoyao.bigdata.cluster.service;

import org.xiaoyao.bigdata.cluster.entity.DataXCluster;
import org.xiaoyao.bigdata.cluster.entity.DataXNode;
import java.util.List;

public interface ClusterService {

    DataXCluster getClusterInfo();

    List<DataXNode> getNodes();

}
