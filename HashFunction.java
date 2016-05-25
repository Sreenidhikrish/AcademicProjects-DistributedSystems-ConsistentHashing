package edu.scu.dc.project.kvstore.master.base;

/******************************************
**** COEN317-CONSISTENT HASHING************
**** AUTHORS: NISHA RAMACHANDRAN***********
**** SREENIDHI KRISHNA*********************
**** DATED: 3/01/2016,VERSION:V1.0*********
*******************************************/

public class HashFunction {
    public HashFunction() {
        super();
    }
    
    public static int getHashKey(String key){
        return key.hashCode();
    }
}
