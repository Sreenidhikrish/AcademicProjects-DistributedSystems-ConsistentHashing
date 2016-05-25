package edu.scu.dc.project.kvstore.master.base;

/******************************************
**** COEN317-CONSISTENT HASHING************
**** AUTHORS: NISHA RAMACHANDRAN***********
**** SREENIDHI KRISHNA*********************
**** DATED: 3/01/2016,VERSION:V1.0*********
*******************************************/

/*
* HRequest.java : Class that handles various request types
* such as GET, PUT, POST 
*/

public class HRequest {
    private String m_requestType;
    private String m_path;
    private String m_body;
    
    public HRequest() {
        super();
    }

    public void setRequestType(String m_requestType) {
        this.m_requestType = m_requestType;
    }

    public String getRequestType() {
        return m_requestType;
    }

    public void setPath(String m_path) {
        this.m_path = m_path;
    }

    public String getPath() {
        return m_path;
    }

    public void setBody(String m_body) {
        this.m_body = m_body;
    }

    public String getBody() {
        return m_body;
    }
}
