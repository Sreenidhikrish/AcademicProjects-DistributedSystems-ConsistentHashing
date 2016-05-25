package edu.scu.dc.project.kvstore.master.task;

/******************************************
**** COEN317-CONSISTENT HASHING************
**** AUTHORS: NISHA RAMACHANDRAN***********
**** SREENIDHI KRISHNA*********************
**** DATED: 3/01/2016,VERSION:V1.0*********
*******************************************/

import edu.scu.dc.project.kvstore.master.base.HRequest;
import edu.scu.dc.project.kvstore.master.base.HResponse;
import edu.scu.dc.project.kvstore.node.service.BulkTransferService;
import edu.scu.dc.project.kvstore.node.service.NodeStateService;
import edu.scu.dc.project.kvstore.node.service.RemoveNodeRedistributionTransaction;
import edu.scu.dc.project.kvstore.node.service.TransferKeyRemoveService;
import edu.scu.dc.project.kvstore.node.service.TransferKeyService;

/*
*HealthDispatcher.java: Monitors the activities(node state, bulk transfer) in node
*The processTask method extracts the request type and calls the corresponding process service
*/
public class HealthDispatcher {
    private HRequest m_hRequest;

    public HealthDispatcher(HRequest hr) {
        super();
        m_hRequest = hr;
    }

    public HResponse processService(String service, String nodeName, String body) {
        if ("nodestate".equals(service)) {
            NodeStateService ns = new NodeStateService();
            return ns.processService();
        } else if ("bulktransfer".equals(service)) {
            BulkTransferService bs = new BulkTransferService();
            return bs.processService(nodeName);
        } else if ("startbulktransfer".equals(service)) {
            TransferKeyService tks = new TransferKeyService();
            return tks.processService(body);
        }  else if ("bulkremovetransfer".equals(service)) {
            RemoveNodeRedistributionTransaction rrt = new RemoveNodeRedistributionTransaction();
            return rrt.processService(nodeName);
        }   else if ("startbulkremovetransfer".equals(service)) {
            TransferKeyRemoveService tkr = new TransferKeyRemoveService();
            return tkr.processService(body);
        } 
        return null;
    }

    public HResponse processTask() {
        if ("GET".equals(m_hRequest.getRequestType())) {
            if (m_hRequest.getPath() != null) {
                System.out.println("[HealthDispatcher] GET m_hRequest.getPath() = " + m_hRequest.getPath());
                String tokens[] = m_hRequest.getPath().split("/");
                System.out.println("tokens.length = " + tokens.length);
                if (tokens != null && tokens.length >= 3) {
                    String service = tokens[2];
                    String nodeName = null;
                    String body = m_hRequest.getBody();
                    if (tokens != null && tokens.length >= 4) {
                        nodeName = tokens[3];
                    }
                    System.out.println("[HealthDispatcher] service = " + service + " node name  " + nodeName);
                    System.out.println("[HealthDispatcher] body = " + body);
                    return processService(service, nodeName, body);
                }
            }

        } else if ("POST".equals(m_hRequest.getRequestType())) {
            if (m_hRequest.getPath() != null) {
                System.out.println("[HealthDispatcher] POST m_hRequest.getPath() = " + m_hRequest.getPath());
                String tokens[] = m_hRequest.getPath().split("/");
                System.out.println("tokens.length = " + tokens.length);
                if (tokens != null && tokens.length >= 3) {
                    String service = tokens[2];
                    String nodeName = null;
                    String body = m_hRequest.getBody();
                    System.out.println("[HealthDispatcher] service = " + service + " node name  " + nodeName);

                    return processService(service, nodeName, body);
                }
            }

        }
        return null;
    }
}
