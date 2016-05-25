package edu.scu.dc.project.kvstore.master.socket;

import edu.scu.dc.project.kvstore.master.base.NodeProperty;
import edu.scu.dc.project.kvstore.master.task.MontiorHealthTask;

/******************************************
**** COEN317-CONSISTENT HASHING************
**** AUTHORS: NISHA RAMACHANDRAN***********
**** SREENIDHI KRISHNA*********************
**** DATED: 3/01/2016,VERSION:V1.0*********
*******************************************/

public class HealthMonitorProcessor  implements Runnable{
    public HealthMonitorProcessor() {
        super();
    }


    private void monitorHealth(){
        MontiorHealthTask mht = new MontiorHealthTask();
        System.out.println("[HealthMonitorProcessor] Sending heartbeat");
        for(int i=0; i < 500; i++){
            for(NodeProperty n: SocketMasterNode.getNodes()){
                mht.monitor(n);
            }
            try {
                Thread.sleep(SocketMasterNode.m_healthCheckFreq);
                System.out.println("[HealthMonitorProcessor] Sleeping ....");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void run() {
        monitorHealth();
    }
}
