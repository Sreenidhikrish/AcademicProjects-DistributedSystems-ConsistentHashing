package edu.scu.dc.project.kvstore.node.util;

/******************************************
**** COEN317-CONSISTENT HASHING************
**** AUTHORS: NISHA RAMACHANDRAN***********
**** SREENIDHI KRISHNA*********************
**** DATED: 3/01/2016,VERSION:V1.0*********
*******************************************/

import java.util.HashMap;
import java.util.Map;

/*
*ArgumentParser.java: Parses the argument and returns the tokens
*/

public class ArgumentParser {
    public ArgumentParser() {
        super();
    }
    
    public static Map<String,String> parseArgs(String[]  args){
        Map<String,String> retMap = new HashMap<String,String>();
        if(args != null && args.length >= 2) {
            for(int i=1; i < args.length; i = i + 2){
                retMap.put(args[i-1],args[i]);
            }
        }
        return retMap;
            
    }
}
