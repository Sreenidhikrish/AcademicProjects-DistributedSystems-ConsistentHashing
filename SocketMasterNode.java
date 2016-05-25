package edu.scu.dc.project.kvstore.master.socket;

/******************************************
**** COEN317-CONSISTENT HASHING************
**** AUTHORS: NISHA RAMACHANDRAN***********
**** SREENIDHI KRISHNA*********************
**** DATED: 3/01/2016,VERSION:V1.0*********
*******************************************/

import edu.scu.dc.project.kvstore.master.base.MasterNode;
import edu.scu.dc.project.kvstore.master.base.NodeProperty;
import edu.scu.dc.project.kvstore.node.util.ArgumentParser;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/*
*SocketMasterNode.java
*The class contains the main method which starts the Master node
*1.It also initialtes the node health monitoring services in a seperate thread 
*
*/
public class SocketMasterNode implements MasterNode {
   
    private static List<NodeProperty> m_nodeLookup ;
    
    private static final String g_keyReadWriteNodeArg = "-keyport";
    private static final String g_childIpArg = "-childip";
    private int m_keyPort;
    public static int m_healthCheckFreq = 10000;
    
    public SocketMasterNode() {
        super();
    }

    public static List<NodeProperty> getNodes(){
        if(m_nodeLookup == null)
           m_nodeLookup = Collections.synchronizedList(new ArrayList<NodeProperty>());
        return m_nodeLookup;
    }
    


    @Override
    public void startUp(String[] args) {
        Map<String,String> argMap = ArgumentParser.parseArgs(args);  
        System.out.println("[SocketMasterNode] args = " + argMap);
        List<NodeProperty> nodes = getNodes();
        if( argMap.containsKey(g_childIpArg)) {
            String childIp = argMap.get(g_childIpArg);  
            nodes.add(new NodeProperty("Node-31",childIp,9031,9032));
            nodes.add(new NodeProperty("Node-51",childIp,9051,9052));
        }
          
       
        
        if(argMap.containsKey(g_keyReadWriteNodeArg))
           m_keyPort = Integer.parseInt(argMap.get(g_keyReadWriteNodeArg));
        else
          throw new RuntimeException("-keyport is required"); // TODO
        
          ServerSocket s;
          System.out.println("[INFO] Master  keyport starting up on port: " + m_keyPort);
          try {
              s = new ServerSocket(m_keyPort);
              System.out.println("[INFO] Starting master keyport **** ");
              MasterKeyPort kp = new MasterKeyPort(s);
              (new Thread(kp)).start();
              System.out.println("[INFO] Started master keyport ");
          } catch (Exception e) {
              e.printStackTrace();
              return;
          }
          
        HealthMonitorProcessor hmp = new HealthMonitorProcessor();
        (new Thread(hmp)).start();
        

    }
    
    public static void main(String[] args){
        SocketMasterNode s = new SocketMasterNode();
        s.startUp(args);
    }
}
