package edu.scu.dc.project.kvstore.node.service;

/******************************************
**** COEN317-CONSISTENT HASHING************
**** AUTHORS: NISHA RAMACHANDRAN***********
**** SREENIDHI KRISHNA*********************
**** DATED: 3/01/2016,VERSION:V1.0*********
*******************************************/

import edu.scu.dc.project.kvstore.master.base.HResponse;
import edu.scu.dc.project.kvstore.master.service.Service;
import edu.scu.dc.project.kvstore.node.base.HealthRequest;
import edu.scu.dc.project.kvstore.node.base.KeyStore;

public class NodeStateService extends Service{
    public NodeStateService() {
        super();
    }
    
    public HResponse processService() {
        HResponse hr = new HResponse();
        hr.setResponseBody(getNodeDetail().getJSONString());
        return hr;
    }
}
