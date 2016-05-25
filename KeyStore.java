package edu.scu.dc.project.kvstore.node.base;

/******************************************
**** COEN317-CONSISTENT HASHING************
**** AUTHORS: NISHA RAMACHANDRAN***********
**** SREENIDHI KRISHNA*********************
**** DATED: 3/01/2016,VERSION:V1.0*********
*******************************************/
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/*
*KeyStore.java: This contains the key and value pair for each Node
*/
public class KeyStore {
    private ConcurrentHashMap<String,String> m_keyStore = new ConcurrentHashMap<String,String>();
    
    public KeyStore() {
        super();
    }
    
    public Set<String> getKeys(){
        return m_keyStore.keySet();
    }
    
    public void removeKey(String key){
        m_keyStore.remove(key);
    }
    
    public String getValue(String key){
        return m_keyStore.get(key);
    }
    
    public void putValue(String key, String value){
         m_keyStore.putIfAbsent(key,value);
    }
    
    public int getKeyCount(){
        return m_keyStore.size();
    }
}
