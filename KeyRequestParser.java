package edu.scu.dc.project.kvstore.node.socket;

/******************************************
**** COEN317-CONSISTENT HASHING************
**** AUTHORS: NISHA RAMACHANDRAN***********
**** SREENIDHI KRISHNA*********************
**** DATED: 3/01/2016,VERSION:V1.0*********
*******************************************/

import edu.scu.dc.project.kvstore.node.base.KeyRequest;
import java.util.List;

/*
*KeyRequestParser.java: Parses the input list and extracts the request type(GET/PUT)
*
*/

public class KeyRequestParser {
    public KeyRequestParser() {
        super();
    }
    
    public KeyRequest parse(List<String> inputList){
        if(inputList != null && inputList.size() > 0) {
            String[] tokens = inputList.get(0).split(" ");    
            System.out.println("input request: " + inputList.get(0));
            if(tokens != null && tokens.length >= 2){
                KeyRequest kr = new KeyRequest();
                if("GET".equals(tokens[0])){
                    kr.setRequestType(kr.TYPE_GET);
                    kr.setKey(tokens[1]);
                    System.out.println("input KeyRequest: " + kr);
                } else if ("PUT".equals(tokens[0]) && tokens.length >= 3) {
                    kr.setRequestType(kr.TYPE_PUT);
                    kr.setKey(tokens[1]);
                    kr.setValue(tokens[2]);
                    System.out.println("input KeyRequest: " + kr);
                }
                return kr;
            }
        }
        return null;
        
    }
}
