package edu.scu.dc.project.kvstore.master.service;

/******************************************
**** COEN317-CONSISTENT HASHING************
**** AUTHORS: NISHA RAMACHANDRAN***********
**** SREENIDHI KRISHNA*********************
**** DATED: 3/01/2016,VERSION:V1.0*********
*******************************************/

import edu.scu.dc.project.kvstore.master.base.NodeProperty;
import java.util.ArrayList;
import java.util.List;

/*
*RedistAddNdServc.java: The AddnRedist method takes the 
*current node(to be added node) and gets the target node
*(source node) for redistribution of keys
*/
public class RedistAddNdServc extends Service {
    public RedistAddNdServc() {
        super();
    }

    public NodeProperty AddnRedist(NodeProperty currNode) {
        List<NodeProperty> nodes = getNodes();
        List<NodeProperty> greaterNodes = new ArrayList<NodeProperty>();
        int currNodeHash = currNode.getHashKey();
        for (NodeProperty n : nodes) {
            if (n.getHashKey() >= currNodeHash) {
                greaterNodes.add(n);
                System.out.println("Add greater node : " + n.getNodeName());
            }
        }

        NodeProperty targetNode = LookupService.getMinNodeInList(greaterNodes);
        return targetNode;
    }

}
