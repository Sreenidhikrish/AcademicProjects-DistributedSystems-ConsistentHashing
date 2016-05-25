package edu.scu.dc.project.kvstore.node.socket;

/******************************************
**** COEN317-CONSISTENT HASHING************
**** AUTHORS: NISHA RAMACHANDRAN***********
**** SREENIDHI KRISHNA*********************
**** DATED: 3/01/2016,VERSION:V1.0*********
*******************************************/

import edu.scu.dc.project.kvstore.node.base.KeyStore;
import edu.scu.dc.project.kvstore.node.base.NodeDetail;
import edu.scu.dc.project.kvstore.node.util.ArgumentParser;
import java.net.ServerSocket;
import java.util.Map;

public class SocketNode  {
    private static final String g_keyReadWriteNodeArg = "-keyport";
    private static final String g_healthNodeArg = "-healthport";
    private static final String g_nameNodeArg = "-name";
    public static final  KeyStore ks = new KeyStore();
    public static NodeDetail nodeDetails;
    
    private int m_keyReadWriteNode;
    private int m_healthNode;
    
    public SocketNode() {
        super();
    }
    


    public void startUp(String[] args) {
       Map<String,String> argMap = ArgumentParser.parseArgs(args);  
       System.out.println("[SocketNode]args = " + argMap);
        
       if(argMap.containsKey(g_keyReadWriteNodeArg))
          m_keyReadWriteNode = Integer.parseInt(argMap.get(g_keyReadWriteNodeArg));
       else
         throw new RuntimeException("-keyport is required"); // TODO
       
         ServerSocket s;
         System.out.println("[INFO] Node  keyport starting up on port: " + m_keyReadWriteNode);
         try {
             s = new ServerSocket(m_keyReadWriteNode);
             System.out.println("[INFO] Starting keyport **** ");
            
             KeyPort kp = new KeyPort(s);
             (new Thread(kp)).start();
             System.out.println("[INFO] Started keyport ");
         } catch (Exception e) {
             e.printStackTrace();
             return;
         }

        if(argMap.containsKey(g_healthNodeArg))
           m_healthNode = Integer.parseInt(argMap.get(g_healthNodeArg));
        else
          throw new RuntimeException("-healthport is required"); // TODO
        
          ServerSocket s1;
          System.out.println("[INFO] Node  healthport starting up on port: " + m_healthNode);
          try {
              s1 = new ServerSocket(m_healthNode);
              System.out.println("[INFO] Starting healthport - ");
              HealthPort hp = new HealthPort(s1);
              (new Thread(hp)).start();
          } catch (Exception e) {
              e.printStackTrace();
              return;
          }
          
          String name = null;
          if(argMap.containsKey(g_nameNodeArg)) {
              name = argMap.get(g_nameNodeArg);
          } else {
              throw new RuntimeException("-name is required"); // TODO
          }
          nodeDetails = new NodeDetail(name);
          nodeDetails.setKeyPort(m_keyReadWriteNode);
          nodeDetails.setHealthPort(m_healthNode);
         
    }
    
    public static void main(String[] args){
        SocketNode s = new SocketNode();
        s.startUp(args);
    }


}
