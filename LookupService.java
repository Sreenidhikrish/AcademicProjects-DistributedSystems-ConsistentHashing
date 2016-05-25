package edu.scu.dc.project.kvstore.master.service;

/******************************************
**** COEN317-CONSISTENT HASHING************
**** AUTHORS: NISHA RAMACHANDRAN***********
**** SREENIDHI KRISHNA*********************
**** DATED: 3/01/2016,VERSION:V1.0*********
*******************************************/

import edu.scu.dc.project.kvstore.master.base.HResponse;
import edu.scu.dc.project.kvstore.master.base.HashFunction;
import edu.scu.dc.project.kvstore.master.base.NodeProperty;

import java.util.ArrayList;
import java.util.List;

/*
*LookUpService.java: 
*1.The method process sevice takes a key and
*determines in which node the key should be stored based on hash value
*2. Handle cases:
	a) Only one node in the list
	b) No target node found with suitable value
*/
public class LookupService extends Service{
    public LookupService() {
        super();
    }

    public NodeProperty getMaxNodeInList( List<NodeProperty> nodes ){
        NodeProperty maxNode = null;
        for(NodeProperty n: nodes){
            if(maxNode == null)
               maxNode = n;
            else {
                if(n.getHashKey() > maxNode.getHashKey()){
                    maxNode = n;
                }
            }
        }
        return maxNode;
    }    
    
    public static NodeProperty getMinNodeInList( List<NodeProperty> nodes ){
        NodeProperty minNode = null;
        for(NodeProperty n: nodes){
            if(minNode == null)
               minNode = n;
            else {
                if(n.getHashKey() < minNode.getHashKey()){
                    minNode = n;
                }
            }
        }
        return minNode;
    }
    
    public  NodeProperty getMinNode(){
        List<NodeProperty> nodes = getNodes();
        return getMinNodeInList(nodes);
    }
 
    
    public NodeProperty getMaxNode(){
        List<NodeProperty> nodes = getNodes();
        return getMaxNodeInList(nodes);
    }   
    public HResponse processService(String key) {
        int hashCode = HashFunction.getHashKey(key);
        System.out.println("key = " + key + " hashCode = " + hashCode);
        
        List<NodeProperty> nodes = getNodes();
       
        List<NodeProperty> greaterNodes = new ArrayList<NodeProperty>();
        
        for(NodeProperty n: nodes){
            System.out.println("node = " + n.getNodeName() + " hashCode = " + n.getHashKey());
            
            if(n.getHashKey() >= hashCode) {
                greaterNodes.add(n);
                System.out.println("Add greater node : " + n.getNodeName());
            }
        }
        
        NodeProperty destinationHashCode = null;
        
        if(greaterNodes.size() >= 2) {
            destinationHashCode = getMinNodeInList(greaterNodes);
            System.out.println("More greater nodes so get min ");
        } else if (greaterNodes.size() == 1) {
            destinationHashCode = greaterNodes.get(0);
            System.out.println("Just one greater ");
        } else {
            destinationHashCode = getMaxNode();
            System.out.println("Get the max ");
        }
        
        if(destinationHashCode != null){
            System.out.println("Found node as : " + destinationHashCode.getNodeName());
            HResponse hr = new HResponse();
            hr.setResponseBody(destinationHashCode.getJSONString());
            return hr;
        }
        return null;
    }
}
