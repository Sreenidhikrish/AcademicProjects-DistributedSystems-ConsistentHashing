package edu.scu.dc.project.kvstore.node.socket;

/******************************************
**** COEN317-CONSISTENT HASHING************
**** AUTHORS: NISHA RAMACHANDRAN***********
**** SREENIDHI KRISHNA*********************
**** DATED: 3/01/2016,VERSION:V1.0*********
*******************************************/

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public abstract class TaskProcessor {
    private Socket m_remote = null;

    protected List<String> waitForInput(BufferedReader in) throws IOException {
        String str = ".";
        List<String> request = new ArrayList<String>();
        boolean isPost = false;
        final String CONTENT_LENGTH_STR = "Content-Length:";
        int contentLength = 0;
        while (!"".equals(str)) {
            str = in.readLine();
            request.add(str);
            if (str == null)
                str = "";

            if (str.contains("POST"))
                isPost = true;

            if (str.contains(CONTENT_LENGTH_STR)) {
                contentLength = Integer.parseInt(str.substring(CONTENT_LENGTH_STR.length() + 1));
            }
        }

        System.out.println("XX Content length = " + contentLength);
        StringBuffer body = new StringBuffer();
        if (isPost && contentLength > 0) {
            int read;
            while ((read = in.read()) != -1) {
                body.append((char) read);
                if (body.length() == contentLength)
                    break;
            }

        }
        request.add(body.toString());
        return request;
    }

    public void setRemote(Socket m_remote) {
        this.m_remote = m_remote;
    }

    public Socket getRemote() {
        return m_remote;
    }

    protected void processRequest(List<String> inputList, PrintStream outP) {

    }

    public void run() {
        PrintStream outP = null;
        BufferedReader in = null;
        try {

            System.out.println("[INFO] Connection accepted from " + getRemote().getRemoteSocketAddress());
            in = new BufferedReader(new InputStreamReader(getRemote().getInputStream()));
            outP = new PrintStream(new BufferedOutputStream(getRemote().getOutputStream()));
            List<String> request = waitForInput(in);
            processRequest(request, outP);
        } catch (IOException e) {
            if (outP != null) {
                outP.println("ERROR " + e.getMessage());
                outP.println("");
            }
        } finally {
            try {
                if (outP != null) {
                    outP.flush();
                    outP.close();
                }
                if (in != null) {
                    in.close();
                }
                if (getRemote() != null) {
                    getRemote().close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
