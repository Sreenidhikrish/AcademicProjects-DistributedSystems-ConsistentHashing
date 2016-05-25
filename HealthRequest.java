package edu.scu.dc.project.kvstore.node.base;

/******************************************
**** COEN317-CONSISTENT HASHING************
**** AUTHORS: NISHA RAMACHANDRAN***********
**** SREENIDHI KRISHNA*********************
**** DATED: 3/01/2016,VERSION:V1.0*********
*******************************************/

public class HealthRequest {
    private int  keyCount;
    private NodeDetail m_nodeDetail;
    
    public String getJSONString(){
        StringBuffer sb = new StringBuffer();
        sb.append("{\"nodeDetail\": ").append(m_nodeDetail.getJSONString()).append(",");
        sb.append("\"keyCount\": \"").append(keyCount).append("\"}");
        return sb.toString();
    }
    
    public HealthRequest() {
        super();
    }

    public void setKeyCount(int keyCount) {
        this.keyCount = keyCount;
    }

    public int getKeyCount() {
        return keyCount;
    }

    public void setNodeDetail(NodeDetail m_nodeDetail) {
        this.m_nodeDetail = m_nodeDetail;
    }

    public NodeDetail getNodeDetail() {
        return m_nodeDetail;
    }


}
