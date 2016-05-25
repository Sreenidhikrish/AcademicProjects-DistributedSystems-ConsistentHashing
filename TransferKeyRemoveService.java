package edu.scu.dc.project.kvstore.node.service;

/******************************************
**** COEN317-CONSISTENT HASHING************
**** AUTHORS: NISHA RAMACHANDRAN***********
**** SREENIDHI KRISHNA*********************
**** DATED: 3/01/2016,VERSION:V1.0*********
*******************************************/

import edu.scu.dc.project.kvstore.master.base.HResponse;
import edu.scu.dc.project.kvstore.master.base.NodeProperty;
import edu.scu.dc.project.kvstore.master.service.Service;
import edu.scu.dc.project.kvstore.node.base.KeyStore;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class TransferKeyRemoveService extends Service{
    public TransferKeyRemoveService() {
        super();
    }
    
    private void addKeys(String kvArray){
        JSONObject jobj = new JSONObject(kvArray);

        JSONArray kvJsonArray =jobj.getJSONArray("items");
        KeyStore ks = getKeyStore();
        for(int i=0; i < kvJsonArray.length() ; i++){
            JSONObject kvPairObj = kvJsonArray.getJSONObject(i);
            String key = kvPairObj.getString("key");
            String value = kvPairObj.getString("value"); 
            ks.putValue(key, value);
            System.out.println("[TransferKeyRemoveService] adding key " + key + " value " + value);
        }
    }

    public void fetchData(NodeProperty targetNode) {
        PrintStream outP = null;
        BufferedReader in = null;
        Socket s = null;
        try {
            // System.out.println("[MontiorHealthTask] Sending heartbeat to node: " + n.getNodeName() + " " + n.getHostName() + ":" +  n.getHealthPort());
            s = new Socket(targetNode.getHostName(), targetNode.getHealthPort());
            outP = new PrintStream(new BufferedOutputStream(s.getOutputStream()));
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            outP.println("GET /context/bulkremovetransfer/" + getNodeDetail().getNodeName() + " HTTP/1.0");
            outP.println("");
            outP.println("");
            outP.flush();
            //outP.close();
            String readLine = "-1";
            List<String> nodeDetailData = new ArrayList<String>();
            System.out.println("[TransferKeyRemoveService] reading data from target node ..");
            boolean headerComplete = false;
            String kvArray = null;
            while (!(readLine == null)) {
                readLine = in.readLine();
                nodeDetailData.add(readLine);
                
                if(kvArray == null && headerComplete && readLine != null){
                    kvArray = readLine;
                }
                
                if("".equals(readLine)) 
                    headerComplete = true;
                
                System.out.println("[TransferKeyRemoveService]" + readLine);
            }
            
            System.out.println("[TransferKeyRemoveService] got response " + kvArray);
            if(kvArray != null){
                addKeys(kvArray);
            }

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (outP != null) {
                    outP.flush();
                    //outP.close();
                }
                if (in != null) {
                    in.close();
                }
                if (s != null) {
                    s.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    
    public HResponse processService(String body){
        NodeProperty targetNode = NodeProperty.parseNodeProperty(body);
        fetchData(targetNode);
        HResponse hr = new HResponse();
        hr.setResponseBody("{\"keyCount\": " + getKeyStore().getKeyCount() + " }");
        return hr;
    }

}
