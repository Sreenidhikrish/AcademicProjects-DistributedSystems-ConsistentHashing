package edu.scu.dc.project.kvstore.master.service;

/******************************************
**** COEN317-CONSISTENT HASHING************
**** AUTHORS: NISHA RAMACHANDRAN***********
**** SREENIDHI KRISHNA*********************
**** DATED: 3/01/2016,VERSION:V1.0*********
*******************************************/

import edu.scu.dc.project.kvstore.master.base.HResponse;
import edu.scu.dc.project.kvstore.master.base.NodeProperty;
import java.util.List;

/*
*NodeHealthService.java: The processService method 
*returns a response containing the node status and 
*other details of all the nodes associated with Master
*/
public class NodeHealthService extends Service{
    public NodeHealthService() {
        super();
    }
    
    public HResponse processService() {
        List<NodeProperty> nodes = getNodes();
        StringBuffer sb = new StringBuffer();
        sb.append("{\"nodes\":[");
        int cnt = 0;
        for(NodeProperty n: nodes){
            if(cnt > 0 )
                sb.append(",");
            sb.append(n.getJSONString());
            cnt++;
        }
        sb.append("]}");
        HResponse hr = new HResponse();
        hr.setResponseBody(sb.toString());
        return hr;
    }
}
