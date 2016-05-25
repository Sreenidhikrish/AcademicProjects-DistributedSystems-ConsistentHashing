package edu.scu.dc.project.kvstore.master.task;

/******************************************
**** COEN317-CONSISTENT HASHING************
**** AUTHORS: NISHA RAMACHANDRAN***********
**** SREENIDHI KRISHNA*********************
**** DATED: 3/01/2016,VERSION:V1.0*********
*******************************************/

import edu.scu.dc.project.kvstore.master.base.NodeProperty;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

import java.net.Socket;
import java.net.UnknownHostException;

/*
* MontiorHealthTask.java: 
* 1. The method monitor polls every node in the node list by sending heart beat messages 
* 2. Extracts the node detail in its(Master's)Node list  
*
*/

public class MontiorHealthTask {
    public MontiorHealthTask() {
        super();
    }
    
    public void monitor(NodeProperty n){
        PrintStream outP = null;
        BufferedReader in = null;
        Socket s = null;
        try {
           // System.out.println("[MontiorHealthTask] Sending heartbeat to node: " + n.getNodeName() + " " + n.getHostName() + ":" +  n.getHealthPort());
            s = new Socket(n.getHostName(), n.getHealthPort());
            outP = new PrintStream(new BufferedOutputStream(s.getOutputStream()));
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            outP.println("GET /context/nodestate HTTP/1.0");
            outP.println("");
            outP.println("");
            outP.flush();
            //outP.close();
            String readLine = "-1";
            boolean headerComplete = false;
            String nodeDetail = null;
            while(!(readLine == null)) {
              readLine = in.readLine();
                if(headerComplete && nodeDetail == null){
                    nodeDetail = readLine;
                }
                
                if("".equals(readLine)){
                    headerComplete = true;
                }
            }
            System.out.println("[MontiorHealthTask] Heartbeat from " + nodeDetail);
            n.setStateAlive(nodeDetail);

                                                                          
        } catch (UnknownHostException e) {
             //System.out.println("NODE " + n.getNodeName() + " DEAD");
             n.setStateDead();
        } catch (IOException e) {
            //System.out.println("NODE " + n.getNodeName() + " DEAD");
            n.setStateDead();
        } finally {
            try {
                if (outP != null) {
                    outP.flush();
                    //outP.close();
                }
                if(in != null){
                    in.close();
                }
                if (s != null) {
                        s.close();
                    }
                    
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
