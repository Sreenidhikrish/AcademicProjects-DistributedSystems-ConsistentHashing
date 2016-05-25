package edu.scu.dc.project.kvstore.master.task;

/******************************************
**** COEN317-CONSISTENT HASHING************
**** AUTHORS: NISHA RAMACHANDRAN***********
**** SREENIDHI KRISHNA*********************
**** DATED: 3/01/2016,VERSION:V1.0*********
*******************************************/

import edu.scu.dc.project.kvstore.master.base.HRequest;
import edu.scu.dc.project.kvstore.master.base.HResponse;
import edu.scu.dc.project.kvstore.master.service.AddNodeService;
import edu.scu.dc.project.kvstore.master.service.LookupService;
import edu.scu.dc.project.kvstore.master.service.NodeHealthService;
import edu.scu.dc.project.kvstore.master.service.RemoveNodeService;
import edu.scu.dc.project.kvstore.node.service.GetService;
import edu.scu.dc.project.kvstore.node.service.PutService;

/*
*QueryDispatcher.java: The processTask method extracts the request type and calls 
*the corresponding process service
*/

public class QueryDispatcher {
    private HRequest m_hRequest;

    public QueryDispatcher(HRequest hr) {
        super();
        m_hRequest = hr;
    }

    public HResponse processService(String service, String key, String value, String body) {
        if ("lookuphost".equals(service)) {
            LookupService ls = new LookupService();
            return ls.processService(key);
        } else if ("get".equals(service)) {
            GetService gs = new GetService();
            return gs.processService(key);
        } else if ("put".equals(service)) {
            PutService ps = new PutService();
            return ps.processService(key, value);
        } else if ("nodestate".equals(service)) {
            NodeHealthService nhs = new NodeHealthService();
            return nhs.processService();
        } else if ("addnode".equals(service)) {
            AddNodeService ans = new AddNodeService();
            return ans.processService(key, value, body);
        }else if("removenode".equals(service)){
            RemoveNodeService rns = new RemoveNodeService();
            return rns.processService(key,value,body);
        }

        return null;
    }

    public HResponse processTask() {
        String key = null;
        String value = null;
        String body = null;
        String service;
        if ("GET".equals(m_hRequest.getRequestType())) {
            if (m_hRequest.getPath() != null) {
                System.out.println("m_hRequest.getPath() = " + m_hRequest.getPath());
                String tokens[] = m_hRequest.getPath().split("/");
                System.out.println("tokens.length = " + tokens.length);
                if (tokens != null && tokens.length >= 3) {
                    service = tokens[2];
                    key = null;
                    if (tokens.length >= 4)
                        key = tokens[3];
                    value = null;
                    if (tokens.length >= 5)
                        value = tokens[4];
                    System.out.println("service = " + service + " key= " + key + "" + " value= " + value);
                    return processService(service, key, value, body);
                }
            }
        }

        else if ("POST".equals(m_hRequest.getRequestType())) {
            if (m_hRequest.getPath() != null) {
                System.out.println("m_hRequest.getPath() = " + m_hRequest.getPath());
                String tokens[] = m_hRequest.getPath().split("/");
                System.out.println("tokens.length = " + tokens.length);
                if (tokens != null && tokens.length >= 3) {
                    service = tokens[2];
                    body = m_hRequest.getBody();
                    return processService(service, key, value, body);
                }
                
            }

        }
        return null;
    }

}
