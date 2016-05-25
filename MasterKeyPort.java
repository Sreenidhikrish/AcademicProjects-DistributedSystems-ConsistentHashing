package edu.scu.dc.project.kvstore.master.socket;

/******************************************
**** COEN317-CONSISTENT HASHING************
**** AUTHORS: NISHA RAMACHANDRAN***********
**** SREENIDHI KRISHNA*********************
**** DATED: 3/01/2016,VERSION:V1.0*********
*******************************************/

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/*
* MasterKeyPort.java: It accepts the incoming connection at the key port
*/

public class MasterKeyPort implements Runnable {
    private ServerSocket m_serverSocket;
    
    public MasterKeyPort(ServerSocket s) {
        super();
        m_serverSocket = s;
      
    }

    @Override
    public void run() {
        System.out.println("[MasterKeyPort] Waiting for connection ...");
        Socket remote = null;
        while (true) {
            try {
                remote = m_serverSocket.accept();
                System.out.println("[MasterKeyPort] accepted connection from " + remote.getInetAddress());
                (new Thread(new MasterKeyPortProcessor(remote))).run();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
