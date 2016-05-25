package edu.scu.dc.project.kvstore.master.base;

/******************************************
**** COEN317-CONSISTENT HASHING************
**** AUTHORS: NISHA RAMACHANDRAN***********
**** SREENIDHI KRISHNA*********************
**** DATED: 3/01/2016,VERSION:V1.0*********
*******************************************/

/*
* HttpParser.java : Parses the request (identitfies
* the request type as GET/POST) and sets the HRequest 
*/

import java.util.List;

public class HttpParser {
    public HttpParser() {
        super();
    }

   
/*
*Checks if the input list is valid and extracts the request type
*Generates a request. For POST Request, extract the body from the
* request
*/	
    public HRequest parse(List<String> inputList) {
        if (inputList != null && inputList.size() > 0 && inputList.get(0)!= null) {
            String tokens[] = inputList.get(0).split(" ");
            if (tokens[0].equals("GET") && (tokens != null && tokens.length > 1)) {
			    HRequest hr = new HRequest();
                hr.setRequestType(tokens[0]);
                hr.setPath(tokens[1]);
                return hr;
            } else if (tokens[0].equals("POST") && (tokens != null && tokens.length > 1)) {
                HRequest hr = new HRequest();
                hr.setRequestType(tokens[0]);
                StringBuffer bodyBuffer = new StringBuffer();
                Boolean startBody = false;
                for (int i = 0; i < inputList.size(); i++) {
                    String checkStr = inputList.get(i);
                    System.out.println("LINE-" + i + ":" + inputList.get(i));
                    
                    if (startBody.booleanValue()) {
                        bodyBuffer.append(inputList.get(i)).append(" ");
                    }
                    if ("".equals(checkStr) || checkStr == null) {
                        startBody = true;
                    }
                    
                }
                System.out.println("BODY :" + bodyBuffer.toString());
                hr.setBody(bodyBuffer.toString());
                hr.setPath(tokens[1]);
                return hr;
            }

        }
        return null;
    }
}
