package edu.scu.dc.project.kvstore.master.base;

/******************************************
**** COEN317-CONSISTENT HASHING************
**** AUTHORS: NISHA RAMACHANDRAN***********
**** SREENIDHI KRISHNA*********************
**** DATED: 3/01/2016,VERSION:V1.0*********
*******************************************/

import java.util.List;
import org.json.JSONObject;

/*
*NodeProperty.java: The class contains the attributes 
*for the node like nodename, hostname, keyport, healthport, 
*state and keycount and corresponding getters and setters to 
*set the various attributes
*/

public class NodeProperty {
    private String m_nodeName;
    private String m_hostName;
    private int hashKey;
    private int m_keyPort;
    private int m_healthPort;
    private String m_state = "INITIALIZED";
    private int m_keyCount;
    
    public static NodeProperty parseNodeProperty(String body){
        JSONObject jobj = new JSONObject(body);
        String nodeName =jobj.getString("nodeName");
        String hostName = jobj.getString("hostName");
        Integer keyPort = jobj.getInt("keyPort");
        Integer healthPort = jobj.getInt("healthPort");
        
        NodeProperty np = new NodeProperty();
        np.setNodeName(nodeName);
        np.setHostName(hostName);
        np.setKeyPort(keyPort);
        np.setHealthPort(healthPort);    
        np.setHashKey(HashFunction.getHashKey(nodeName));
        return np;
    }
    
    @Override
    public boolean equals(Object n){
       if(n != null && ((NodeProperty)n).getNodeName().equals(m_nodeName))
         return true;
       else
        return false;
    }
    
    public String getJSONString(){
        StringBuffer sb = new StringBuffer();
        sb.append("{ \"nodeName\":\"").append(m_nodeName).append("\"").append(",");
        sb.append("\"hostName\":\"").append(m_hostName).append("\"").append(",");
        sb.append("\"keyPort\":\"").append(m_keyPort).append("\"").append(",");
        sb.append("\"healthPort\":\"").append(m_healthPort).append("\"").append(",");
        sb.append("\"state\":\"").append(m_state).append("\"").append(",");
        sb.append("\"keyCount\":\"").append(m_keyCount).append("\"").append(",");
        sb.append("\"hashKey\":\"").append(hashKey).append("\"").append("} ");
        return sb.toString();
    }
    
    public void setStateDead(){
        if(!"INITIALIZED".equals(m_state))
            m_state = "DEAD";
    }
    
    public void setStateAlive(String inputString){
        JSONObject jobj = new JSONObject(inputString);
        String state = jobj.getString("state");
        m_keyCount = Integer.parseInt(jobj.getString("keyCount"));
        m_state = state;
        
    }

    public void setState(String m_state) {
        this.m_state = m_state;
    }

    public String getState() {
        return m_state;
    }

    public NodeProperty(String nodeName, String hostname, int keyport, int healthPort) {
        super();
        m_nodeName = nodeName;
        m_hostName = hostname;
        m_keyPort = keyport;
        m_healthPort = healthPort;
        hashKey = m_nodeName.hashCode();
    }

    public void setHashKey(int hashKey) {
        this.hashKey = hashKey;
    }

    public int getHashKey() {
        return hashKey;
    }

    
    

    public void setNodeName(String m_nodeName) {
        this.m_nodeName = m_nodeName;
    }

    public String getNodeName() {
        return m_nodeName;
    }


    public void setHostName(String m_hostName) {
        this.m_hostName = m_hostName;
    }

    public String getHostName() {
        return m_hostName;
    }

    public void setKeyPort(int m_keyPort) {
        this.m_keyPort = m_keyPort;
    }

    public int getKeyPort() {
        return m_keyPort;
    }

    public void setHealthPort(int m_healthPort) {
        this.m_healthPort = m_healthPort;
    }

    public int getHealthPort() {
        return m_healthPort;
    }


    public NodeProperty() {
        super();
    }
    

}
