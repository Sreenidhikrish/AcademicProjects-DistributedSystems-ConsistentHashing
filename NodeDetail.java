package edu.scu.dc.project.kvstore.node.base;

/******************************************
**** COEN317-CONSISTENT HASHING************
**** AUTHORS: NISHA RAMACHANDRAN***********
**** SREENIDHI KRISHNA*********************
**** DATED: 3/01/2016,VERSION:V1.0*********
*******************************************/


import edu.scu.dc.project.kvstore.master.base.HashFunction;
import edu.scu.dc.project.kvstore.node.socket.SocketNode;

/*
*NodeDetail.java: This contains the details of the node including the state
*/

public class NodeDetail {
    private  String m_nodeName;
    private int m_hashCode;
    private int m_keyPort;
    private int m_healthPort;
    private String m_state = "ACTIVE";
    
    public NodeDetail(String nodeName) {
        m_nodeName = nodeName;
        m_hashCode = HashFunction.getHashKey(nodeName);
    }
    
    public String getJSONString(){
        long count = SocketNode.ks.getKeyCount();
        return "{\"nodeName\":\"" + m_nodeName + "\"," +
                 "\"state\":\"" + m_state + "\"," +
                 "\"keyPort\":\"" + m_keyPort + "\"," +
                 "\"keyCount\":\"" + count + "\"," +
                 "\"hashCode\":\"" + m_hashCode + "\"}";
    }

    public void setNodeName(String m_nodeName) {
        this.m_nodeName = m_nodeName;
    }

    public String getNodeName() {
        return m_nodeName;
    }

    public void setHashCode(int m_hashCode) {
        this.m_hashCode = m_hashCode;
    }

    public int getHashCode() {
        return m_hashCode;
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

    public void setState(String m_state) {
        this.m_state = m_state;
    }

    public String getState() {
        return m_state;
    }


}
