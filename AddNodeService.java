package edu.scu.dc.project.kvstore.master.service;

/******************************************
**** COEN317-CONSISTENT HASHING************
**** AUTHORS: NISHA RAMACHANDRAN***********
**** SREENIDHI KRISHNA*********************
**** DATED: 3/01/2016,VERSION:V1.0*********
*******************************************/

import edu.scu.dc.project.kvstore.master.base.HRequest;
import edu.scu.dc.project.kvstore.master.base.HResponse;
import edu.scu.dc.project.kvstore.master.base.HashFunction;
import edu.scu.dc.project.kvstore.master.base.HttpParser;
import edu.scu.dc.project.kvstore.master.base.NodeProperty;
import edu.scu.dc.project.kvstore.node.base.KeyRequest;
import edu.scu.dc.project.kvstore.node.base.KeyStore;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.*;

/*
*AddNodeService.java: 
*1. The method processService accepts
*various node properties which are extracted from the 
*body of the POST request and assigns it to a newly 
*created node
*2. Calls the function to find the target node (source node)
*to perform redistribution of keys to the newly created node.
*/

public class AddNodeService extends Service {
       
       public AddNodeService() {
       super();
   }
 public HResponse processService(String key, String value, String body) {
                       
               HResponse  hr = new HResponse();
               System.out.println("BODY = " + body);
               JSONObject jobj = new JSONObject(body);
               String nodeName =jobj.getString("nodeName");
               String hostName = jobj.getString("hostName");
               Integer keyPort = jobj.getInt("keyPort");
               Integer healthPort = jobj.getInt("healthPort");
        
               NodeProperty np = NodeProperty.parseNodeProperty(body);
               RedistAddNdServc ras = new RedistAddNdServc();
               NodeProperty targetNode = ras.AddnRedist(np);
               
               if(targetNode != null){
                   hr.setResponseBody(targetNode.getJSONString());
               }

               getNodes().add(np); 
               return hr;
              
           }
}