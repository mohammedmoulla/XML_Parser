package xml_parser;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.parsers.*;
import java.io.*;
import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.xml.XMLConstants;

public class Reader {
    File f;
    Document doc;
            
    public Reader (File f) {
        this.f = f;
    }
    
    public Document parse (DefaultMutableTreeNode top) {    
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

      try {
          dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
          DocumentBuilder db = dbf.newDocumentBuilder();
          doc = db.parse(f);

          Element root = doc.getDocumentElement();
          
          root.normalize();
          NodeList L = root.getChildNodes();
          int index = 1;
          for (int i=0; i<L.getLength(); i++){
              Node N = L.item(i);
              
              if (N.getNodeType() ==3)
                      continue;
              NODE obj = new NODE(N.getNodeName()+index++,N);
              DefaultMutableTreeNode category = new DefaultMutableTreeNode(obj);
              
              NodeList elements = N.getChildNodes();
              for (int j=0; j<elements.getLength(); j++){
                  Node e = elements.item(j);
                  if (e.getNodeType() ==3)
                      continue;
                  String key = e.getNodeName();
                  String value = e.getTextContent();
                  obj.set(key,value ,false);
                  category.add(new DefaultMutableTreeNode(new ELEMENT(key,value,e)));
              }//end of for (j)
              
              top.add(category);
          }//end of for (i)
          

      } catch (Exception e) {
          e.printStackTrace();
          JOptionPane.showMessageDialog(null, e.getMessage(),"ERROR", JOptionPane.ERROR_MESSAGE);
      }
      

      return doc;
    }
    
    
   
}