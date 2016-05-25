package edu.scu.dc.project.kvstore.node.service;

import edu.scu.dc.project.kvstore.master.base.HashFunction;
import edu.scu.dc.project.kvstore.master.service.Service;
import edu.scu.dc.project.kvstore.node.base.KeyStore;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AddNodeRedistBlkKeyTx extends Service{
    public AddNodeRedistBlkKeyTx() {
        super();
    }
    public void requestKeyInfo(Integer newNodeHash)
        {
        //This function will run on every nearest neighbour 
        //from which the value has to pass on to the new node.
        //It takes as argument the value of the newnode's hash key.
        // This function will now iterate through its keys and find 
        // the minimum of its keys. Now it has the range of values ready 
        // (i.e, from its minimum to the new nodes hashkey) to be 
        // stored in new node.
       KeyStore ks = getKeyStore();
       Set<String> keysTarget = ks.getKeys();
       Map<String,String> retMapKeys = new HashMap<String,String> ();
       int pTargetkeyHash;
               
       for(String s: keysTarget){
           pTargetkeyHash =HashFunction.getHashKey(s);
           if (pTargetkeyHash <= newNodeHash) {
               retMapKeys.put(s,ks.getValue(s));
           }
           
       }
    
             
        
       }
    }
    

