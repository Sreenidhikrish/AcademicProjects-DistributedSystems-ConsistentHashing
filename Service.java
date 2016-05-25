package edu.scu.dc.project.kvstore.master.service;

/******************************************
**** COEN317-CONSISTENT HASHING************
**** AUTHORS: NISHA RAMACHANDRAN***********
**** SREENIDHI KRISHNA*********************
**** DATED: 3/01/2016,VERSION:V1.0*********
*******************************************/

import edu.scu.dc.project.kvstore.master.base.NodeProperty;
import edu.scu.dc.project.kvstore.master.socket.SocketMasterNode;
import edu.scu.dc.project.kvstore.node.base.KeyStore;
import edu.scu.dc.project.kvstore.node.base.NodeDetail;
import edu.scu.dc.project.kvstore.node.socket.SocketNode;
import java.util.List;

public class Service {
    public Service() {
        super();
    }
    
    protected List<NodeProperty> getNodes(){
        return SocketMasterNode.getNodes();
    }
    
    protected KeyStore getKeyStore(){
        return SocketNode.ks;
    }
    
    protected NodeDetail getNodeDetail(){
        return SocketNode.nodeDetails;
    }
}
