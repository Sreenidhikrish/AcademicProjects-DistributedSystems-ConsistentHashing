package edu.scu.dc.project.kvstore.master.base;

/******************************************
**** COEN317-CONSISTENT HASHING************
**** AUTHORS: NISHA RAMACHANDRAN***********
**** SREENIDHI KRISHNA*********************
**** DATED: 3/01/2016,VERSION:V1.0*********
*******************************************/

/*
* HResponse.java : Class that generates various responses
* for the corresponding requests
*/

public class HResponse {
    public void setResponseBody(String m_responseBody) {
        this.m_responseBody = m_responseBody;
    }

    public String getResponseBody() {
        return m_responseBody;
    }
    private String m_responseBody;
    public HResponse() {
        super();
    }
}
