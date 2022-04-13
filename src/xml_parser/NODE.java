package xml_parser;

import java.util.HashMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

//class with variable number of attributes
public class NODE {
    String name;
    HashMap <String,String> map;
    Node N;
    NODE (String name,Node N) {
        this.name = name;
        this.N = N;
        map = new HashMap<>();
    }
    
    void set (String key,String value,boolean update){
        map.put(key, value);
        if (update){
            NodeList L = N.getChildNodes();
            for (int i=0; i<L.getLength(); i++){
                
                if (L.item(i).getNodeType() != 3 && L.item(i).getNodeName().equals(key)){
                    L.item(i).setTextContent(value);
                    break;
                }
                    
            }
            
        }
    }
    String get (String key){
       return map.get(key);
    }
    String getName (){
       return name;
    }
    String [] getKeys(){ 
        
        return map.keySet().toArray(new String[ map.keySet().size()]);
    }
    String [] getValues(){ 
        return  map.values().toArray(new String[ map.values().size()]);
    }
    @Override
    public String toString(){
        return name;
    }
    
}
