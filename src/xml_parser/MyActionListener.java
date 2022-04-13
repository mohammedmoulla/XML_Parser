/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xml_parser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author M#
 */
public class MyActionListener  implements ActionListener{
    DefaultMutableTreeNode obj ;
    JTree tree;
    Document doc;
    MyActionListener (DefaultMutableTreeNode obj ,JTree tree,Document doc) {
        this.obj = obj;
        this.tree = tree;
        this.doc = doc;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        DefaultTreeModel model = (DefaultTreeModel)tree.getModel();
        DefaultMutableTreeNode root = (DefaultMutableTreeNode)model.getRoot();
       
        switch (command) {
            case "add" :
                System.out.println("add new");
                break;
            case "update" : 
                DefaultMutableTreeNode parent = (DefaultMutableTreeNode) obj.getParent();
                NODE N = (NODE) parent.getUserObject();
                ELEMENT E1 = (ELEMENT) obj.getUserObject();
                
                String key = E1.key , value = E1.value;
                String NEW = JOptionPane.showInputDialog(null,"set value for "+key,value);
                E1.value = NEW;
                N.set(key, NEW,true);
                model.reload(parent);
                break;
            case "add attribute" :
                NODE N1 = (NODE) obj.getUserObject();
                
                break;
            case "delete attribute" :
            
                DefaultMutableTreeNode parent_1 = (DefaultMutableTreeNode) obj.getParent();
                NODE N_1 = (NODE) parent_1.getUserObject();
                ELEMENT E1_1 = (ELEMENT) obj.getUserObject();
                N_1.map.remove(E1_1.key);
                N_1.N.removeChild(E1_1.N);
                parent_1.remove(obj);
                model.reload(parent_1);
                
                System.out.println("delete attribute");
            
                break;
            case "duplicate":
                NODE N2 = (NODE) obj.getUserObject();
                Node cloned = N2.N.cloneNode(true);
                
                doc.getFirstChild().appendChild(cloned);
                
                DefaultMutableTreeNode NewNode = (DefaultMutableTreeNode)obj.clone();
                int n = root.getChildCount();
                String name = N2.name.substring(0,N2.name.length()-1) + (n+1);
                NODE N22 = new NODE(name,cloned);
                
                NodeList LL = cloned.getChildNodes();

                for (int i=0; i<LL.getLength(); i++){
                    if (LL.item(i).getNodeType() ==3)
                        continue;
                    String k = LL.item(i).getNodeName();
                    N22.set(k, N2.get(k), false);
                    NewNode.add(new DefaultMutableTreeNode(new ELEMENT(k,N2.get(k),LL.item(i))));
                }
                NewNode.setUserObject(N22);
                
                root.add(NewNode);
                model.reload();
                break;
                
            case "delete" :
                NODE N3 = (NODE) obj.getUserObject();
                doc.getFirstChild().removeChild(N3.N);
                root.remove(obj);
                model.reload();
                break;
        }
            
    }
    
}

//        //update
//        DefaultTreeModel model = (DefaultTreeModel)Tree.getModel();
//        
//        
//        root.add(new DefaultMutableTreeNode("another_child"));

//        //delete

//        DefaultTreeModel model = (DefaultTreeModel)Tree.getModel();
//        DefaultMutableTreeNode root = (DefaultMutableTreeNode)model.getRoot();
//        
//        root.remove(1);
