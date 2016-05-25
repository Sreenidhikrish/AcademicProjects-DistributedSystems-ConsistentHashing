package edu.scu.dc.project.kvstore.master.service;

/******************************************
**** COEN317-CONSISTENT HASHING************
**** AUTHORS: NISHA RAMACHANDRAN***********
**** SREENIDHI KRISHNA*********************
**** DATED: 3/01/2016,VERSION:V1.0*********
*******************************************/

import edu.scu.dc.project.kvstore.master.base.HResponse;
import edu.scu.dc.project.kvstore.master.base.NodeProperty;
import java.util.Iterator;
import org.json.JSONObject;

/*
*RemoveNodeService.java: 
*1.The method processService takes in the 
*parameters of the node to be removed and finds it
*2.Find the target node to which the keys of the to be removed node
*has to be transfered.
*/

public class RemoveNodeService extends Service {
    public RemoveNodeService() {
        super();
    }


    public HResponse processService(String key, String value, String body) {

        HResponse hr = new HResponse();
        System.out.println("BODY = " + body);
        JSONObject jobj = new JSONObject(body);
        String nodeName = jobj.getString("nodeName");

        Iterator nodes = getNodes().iterator();

        while (nodes.hasNext()) {
            NodeProperty n = (NodeProperty)nodes.next();
            if (n.getNodeName().equals(nodeName)) {
                nodes.remove();
                RedistAddNdServc ras = new RedistAddNdServc();
                NodeProperty targetNode = ras.AddnRedist(n);              
                hr.setResponseBody(targetNode.getJSONString());
                
                break;
            }

        }
        return hr;
    }

}
