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
*PutService.java: Puts the key value from user 
into the corresponding node 
*/

public class PutService extends Service{
    public PutService() {
        super();
    }
    
    public HResponse processService(String key, String value) {
        KeyStore ks = getKeyStore();
        ks.putValue(key,value);
        KeyRequest kr = new KeyRequest();
        kr.setKey(key);
        kr.setValue(value);
        kr.setNode(getNodeDetail());
        HResponse hr = new HResponse();
        hr.setResponseBody(kr.getJSONString());
        return hr;
    }
}

