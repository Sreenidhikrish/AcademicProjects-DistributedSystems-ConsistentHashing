package edu.scu.dc.project.kvstore.node.socket;

/******************************************
**** COEN317-CONSISTENT HASHING************
**** AUTHORS: NISHA RAMACHANDRAN***********
**** SREENIDHI KRISHNA*********************
**** DATED: 3/01/2016,VERSION:V1.0*********
*******************************************/

import edu.scu.dc.project.kvstore.node.base.KeyStore;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/*
*KeyPort.java: Accepts connection at the keyport and starts the thread
*/

public class KeyPort  implements Runnable {
    private ServerSocket m_serverSocket;
    
    public KeyPort(ServerSocket s) {
        super();
        m_serverSocket = s;
      
    }


    @Override
    public void run() {
        System.out.println("[INFO] Waiting for connection ...");
        Socket remote = null;
        while (true) {
            try {
                remote = m_serverSocket.accept();
                (new Thread(new KeyRWProcessor(remote))).run();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
