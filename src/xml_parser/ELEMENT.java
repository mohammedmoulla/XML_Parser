
package xml_parser;

import org.w3c.dom.Node;

public class ELEMENT {
    String key,value;
    Node N;
    ELEMENT (String key,String value,Node N) {
        this.key = key;
        this.value = value;
        this.N = N;
    }
    @Override
    public String toString () {
        return key +" : " + value;
    }
}
