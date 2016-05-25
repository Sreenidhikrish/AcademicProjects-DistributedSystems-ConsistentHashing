package edu.scu.dc.project.kvstore.node.base;

/******************************************
**** COEN317-CONSISTENT HASHING************
**** AUTHORS: NISHA RAMACHANDRAN***********
**** SREENIDHI KRISHNA*********************
**** DATED: 3/01/2016,VERSION:V1.0*********
*******************************************/

import edu.scu.dc.project.kvstore.master.base.HashFunction;

public class KeyRequest {
    public static final int TYPE_GET = 1;
    public static final int TYPE_PUT = 2;
    
    private int m_requestType;
    private String m_key;
    private String m_value;

    public void setNode(NodeDetail m_node) {
        this.m_node = m_node;
    }

    public NodeDetail getNode() {
        return m_node;
    }
    private NodeDetail m_node;
    

    public String toString(){
        return m_requestType + ":" + m_key + ":" + m_value;
    }
    
    public String getJSONString(){
        StringBuffer sb = new StringBuffer();
        sb.append("{ \"key\":\"").append(m_key).append("\"").append(",");
        sb.append("\"value\":\"").append(m_value).append("\"").append(",");
        sb.append("\"keyHash\":\"").append(HashFunction.getHashKey(m_key)).append("\"").append(",");
        sb.append("\"nodeDetail\": ").append(m_node.getJSONString()).append("}");
        return sb.toString();
    }
    
    public void setRequestType(int m_requestType) {
        this.m_requestType = m_requestType;
    }

    public int getRequestType() {
        return m_requestType;
    }

    public void setKey(String m_key) {
        this.m_key = m_key;
    }

    public String getKey() {
        return m_key;
    }

    public void setValue(String m_value) {
        this.m_value = m_value;
    }

    public String getValue() {
        return m_value;
    }

    public KeyRequest() {
        super();
    }
}
