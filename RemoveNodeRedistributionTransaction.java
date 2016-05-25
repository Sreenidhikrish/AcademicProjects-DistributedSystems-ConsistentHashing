package edu.scu.dc.project.kvstore.node.service;

/******************************************
**** COEN317-CONSISTENT HASHING************
**** AUTHORS: NISHA RAMACHANDRAN***********
**** SREENIDHI KRISHNA*********************
**** DATED: 3/01/2016,VERSION:V1.0*********
*******************************************/

import edu.scu.dc.project.kvstore.master.base.HResponse;
import edu.scu.dc.project.kvstore.master.base.HashFunction;
import edu.scu.dc.project.kvstore.master.service.Service;
import edu.scu.dc.project.kvstore.node.base.KeyStore;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/*
*RemoveNodeRedistributionTransaction.java: The  requestKeyInfo takes in 
* newNodeHash (hash value of the node to be removed)gets the keys associated with
*the node.The process Service method removes the keys from the node
*
*/

public class RemoveNodeRedistributionTransaction extends Service {
    public RemoveNodeRedistributionTransaction() {
        super();
    }

    public Map<String, String> requestKeyInfo(Integer newNodeHash) {

        KeyStore ks = getKeyStore();
        Set<String> keysTarget = ks.getKeys();
        Map<String, String> retMapKeys = new HashMap<String, String>();

        System.out.println("[RemoveNodeRedistributionTransaction] get keys less than " + newNodeHash);
        for (String s : keysTarget) {
            System.out.println("[RemoveNodeRedistributionTransaction] adding key " + s);
            retMapKeys.put(s, ks.getValue(s));

        }

        return retMapKeys;
    }

    public HResponse processService(String nodeName) {
        int nodeHashKey = HashFunction.getHashKey(nodeName);
        Map<String, String> retMapKeys = requestKeyInfo(nodeHashKey);
        StringBuffer sb = new StringBuffer();
        sb.append("{\"items\":[");
        int i = 0;
        for (String key : retMapKeys.keySet()) {
            String value = retMapKeys.get(key);
            if (i > 0)
                sb.append(",");
            sb.append("{\"key\":\"").append(key).append("\",");
            sb.append("\"value\":\"").append(value).append("\"}");
            i++;
            getKeyStore().removeKey(key);
        }
        sb.append("]}");
        HResponse hr = new HResponse();
        hr.setResponseBody(sb.toString());
        return hr;
    }
}
