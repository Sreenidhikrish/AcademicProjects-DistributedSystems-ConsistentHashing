package edu.scu.dc.project.kvstore.node.service;

/******************************************
**** COEN317-CONSISTENT HASHING************
**** AUTHORS: NISHA RAMACHANDRAN***********
**** SREENIDHI KRISHNA*********************
**** DATED: 3/01/2016,VERSION:V1.0*********
*******************************************/

import edu.scu.dc.project.kvstore.master.base.HResponse;
import edu.scu.dc.project.kvstore.master.service.Service;
import edu.scu.dc.project.kvstore.node.base.KeyRequest;
import edu.scu.dc.project.kvstore.node.base.KeyStore;

/*
*GetService.java: Gets the key from user and retrieves 
*the key value pair from corresponding node
*/

public class GetService extends Service{
    public GetService() {
        super();
    }
    
    public HResponse processService(String key) {
        KeyStore ks = getKeyStore();
        String value = ks.getValue(key);
        KeyRequest kr = new KeyRequest();
        kr.setKey(key);
        kr.setValue(value);
        kr.setNode(getNodeDetail());
        HResponse hr = new HResponse();
        hr.setResponseBody(kr.getJSONString());
        return hr;
    }
}
