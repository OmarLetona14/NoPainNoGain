/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parcial2.tree;

import parcial2.node.ABBNode;

/**
 *
 * @author Omar
 */
public class ABBTree {
    private int size;
    private String content;
    
    public ABBTree(){
        this.size = 0;
        
    }
    public ABBNode insert(ABBNode root, Comparable value){
        if(root ==null){
            root = new ABBNode(value);
            size++;
        }else{
            if(value.compareTo(root.getData()) < 0){
                root.setLeft(insert(root.getLeft(), value));
            }else if(value.compareTo(root.getData())>0){
                root.setRight(insert(root.getRight(), value));
            }else if(value.compareTo(root.getData())==0){
                root.setLeft(insert(root.getLeft(), value));
            }else{
                System.out.println("Ocurrio un error al ingresar el valor");
            }
        }
        return root;
    }
    
    public ABBNode searchNode(ABBNode root, ABBNode temp, Comparable value){
        if(root ==null && temp==null){
            return null;
        }else{
            if(temp==null){
                if(root!=null){
                    if(root.getData() == value){
                    return root;
                    }
                    temp = searchNode(root.getLeft(), temp, value);
                    temp = searchNode(root.getRight(), temp, value);
                }
            }
        }
        return temp;
    }
    
    public void postOrder(ABBNode root){
        if(root==null){
        }else{
            postOrder(root.getLeft());
            postOrder(root.getRight());
            content += root.getData() + ", ";
        }
    }
    
    public void preOrder(ABBNode root){
       if(root==null){
        }else{
            content += root.getData() + ", ";
            preOrder(root.getLeft());
            preOrder(root.getRight());
        } 
    }
    
    public void inOrder(ABBNode root){
        if(root==null){
        }else{
            inOrder(root.getLeft());
            content += root.getData() + ", ";
            inOrder(root.getRight());
        } 
    }
    
    private String Branch(ABBNode root, String chain){
        if(root == null){
            return chain;
        }
        chain += "nodo" + root.hashCode() + " [ label = \" " + root.getData().toString() + "\"];\n";
        chain = Branch(root.getLeft(), chain);
        chain = Branch(root.getRight(), chain);
        return chain;
    }

    private String Children(ABBNode root, String chain){
        if(root == null){
            return chain;
        }
        if(root.getLeft() != null){
            chain += "nodo" + root.hashCode() + ": c0->nodo" + root.getLeft().hashCode() + ";\n";
        }
        if(root.getRight() != null){
            chain += "nodo" + root.hashCode() + ": c1->nodo" + root.getRight().hashCode() + ";\n";
        }
        chain = Children(root.getLeft(), chain);
        chain = Children(root.getRight(), chain);
        return chain;
    }

    
    public String graph(ABBNode root){
           content +=  Branch(root, content);
           content += Children(root,content);
           return content;
    }
    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
    
}
