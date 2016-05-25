package edu.scu.dc.project.kvstore.node.socket;

/******************************************
**** COEN317-CONSISTENT HASHING************
**** AUTHORS: NISHA RAMACHANDRAN***********
**** SREENIDHI KRISHNA*********************
**** DATED: 3/01/2016,VERSION:V1.0*********
*******************************************/

import edu.scu.dc.project.kvstore.master.base.HRequest;
import edu.scu.dc.project.kvstore.master.base.HResponse;
import edu.scu.dc.project.kvstore.master.base.HttpParser;
import edu.scu.dc.project.kvstore.master.task.QueryDispatcher;
import java.io.PrintStream;
import java.net.Socket;
import java.util.List;

public class KeyRWProcessor  extends TaskProcessor implements Runnable{
    
    public KeyRWProcessor(Socket remote) {
        super();
        setRemote(remote);
        
    }
    
    protected void processRequest(List<String> inputList,PrintStream outP)  {
        
        HttpParser hp = new HttpParser();
        HRequest hr = hp.parse(inputList);
        if(hr != null){
            QueryDispatcher qd = new QueryDispatcher(hr);
            HResponse hResponse = qd.processTask();
            outP.println("HTTP/1.0 200 OK");
            outP.println("Content-Type:application/json");
            outP.println("Access-Control-Allow-Origin:http://127.0.0.1:7101");
            outP.println("");
            if(hResponse != null) {
               System.out.println(" Sending response " + hResponse.getResponseBody()) ;
               outP.print(hResponse.getResponseBody());
            }
            outP.println("");
            outP.println("");
            
        }
        
    }
    

    
}
