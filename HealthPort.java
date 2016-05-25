package edu.scu.dc.project.kvstore.node.socket;

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
*HealthPort.java: Accepts the connection at the health port and starts the thread
*/

public class HealthPort  implements Runnable {
    private ServerSocket m_serverSocket;
    public HealthPort(ServerSocket s) {
        super();
        m_serverSocket = s;
    }
    
    @Override
    public void run() {
        System.out.println("[HealthPort] Waiting for connection ...");
        Socket remote = null;
        while (true) {
            try {
                remote = m_serverSocket.accept();
                System.out.println("[HealthPort] Connection accepted from client" + remote.getInetAddress());
                (new Thread(new HealthProcessor(remote))).run();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
