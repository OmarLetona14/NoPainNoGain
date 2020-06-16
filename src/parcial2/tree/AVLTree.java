/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parcial2.tree;

import java.util.ArrayList;
import java.util.List;
import parcial2.node.AVLNode;

/**
 *
 * @author Omar
 */
public class AVLTree {
    private AVLNode root;

    public int getIteracion() {
        return iteracion;
    }

    public void setIteracion(int iteracion) {
        this.iteracion = iteracion;
    }
    
    private int iteracion;

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
    private String contenido;
    private int size;
    private boolean validation;
    
    public AVLTree(){
        this.size = 0;
        this.iteracion = 0;
        this.contenido = "";
    }
    
     private int height (AVLNode N) {
        if (N == null)
            return 0;
        return N.getHeight();
    }
     
     public void syncRoot(AVLNode syncRoot){
         root = syncRoot;
     }
     
     public AVLNode getRoot(){
         return this.root;
     }

    public AVLNode insert(AVLNode node, Comparable value) {
        /* 1.  Perform the normal BST rotation */
        if (node == null) {
            return(new AVLNode(value));
        }
        if(!searchByName(root, value)){
            /*si el valor a introducir es menor al nodo padre, se va a la izquierda, de caso contrario se va a la derecha.
            Es decir, los valores menores numericamente al padre se iran a la izquierda y los valores mayores se iran
            a la derecha, como en un arbol binario normal.
            a.compareTo(b) funciona asi:
            si a = b devuelve 0
            si a > b devuelve un numero positivo 
            si a < b devuelve un numero negativo
            */
            if (value.compareTo(node.getValue()) < 0)
                
                node.left  = insert(node.left, value);
            else
                node.right = insert(node.right, value);

            /* 2. Update height of this ancestor node */
            node.height = Math.max(height(node.left), height(node.right)) + 1;

            /* 3. Get the balance factor of this ancestor node to check whether
               this node became unbalanced */
            int balance = getBalance(node);

            // If this node becomes unbalanced, then there are 4 cases

            // Left Left Case
            if (balance > 1 && value.compareTo(node.left.value) < 0)
                return rightRotate(node);

            // Right Right Case
            if (balance < -1 && value.compareTo(node.right.value) > 0)
                return leftRotate(node);

            // Left Right Case
            if (balance > 1 && value.compareTo(node.left.value) > 0)
            {
                node.left =  leftRotate(node.left);
                return rightRotate(node);
            }

            // Right Left Case
            if (balance < -1 && value.compareTo(node.right.value) < 0)
            {
                node.right = rightRotate(node.right);
                return leftRotate(node);
            }

            /* return the (unchanged) node pointer */
            return node;
        }   
        return null;
    }
    

    private AVLNode rightRotate(AVLNode y) {
        AVLNode x = y.left;
        AVLNode T2 = x.right;

        // Perform rotation
        x.right = y;
        y.left = T2;

        // Update heights
        y.height = Math.max(height(y.left), height(y.right))+1;
        x.height = Math.max(height(x.left), height(x.right))+1;

        // Return new root
        return x;
    }

    private AVLNode leftRotate(AVLNode x) {
        AVLNode y = x.right;
        AVLNode T2 = y.left;

        // Perform rotation
        y.left = x;
        x.right = T2;

        //  Update heights
        x.height = Math.max(height(x.left), height(x.right))+1;
        y.height = Math.max(height(y.left), height(y.right))+1;

        // Return new root
        return y;
    }

    // Get Balance factor of node N
    private int getBalance(AVLNode N) {
        if (N == null)
            return 0;
        return height(N.left) - height(N.right);
    }
    

    private AVLNode minValueNode(AVLNode node) {
        AVLNode current = node;
        /* loop down to find the leftmost leaf */
        while (current.left != null)
            current = current.left;
        return current;
    }

    public AVLNode deleteNode(AVLNode root, Comparable value) {
        // STEP 1: PERFORM STANDARD BST DELETE

        if (root == null)
            return root;

        // If the value to be deleted is smaller than the root's value,
        // then it lies in left subtree
        if ( value.compareTo(root.value) < 0)
            root.left = deleteNode(root.left, value);

        // If the value to be deleted is greater than the root's value,
        // then it lies in right subtree
        else if( value.compareTo(root.value) > 0)
            root.right = deleteNode(root.right, value);

        // if value is same as root's value, then This is the node
        // to be deleted
        else {
            // node with only one child or no child
            if( (root.left == null) || (root.right == null) ) {

                AVLNode temp;
                if (root.left != null)
                        temp = root.left;
                else
                    temp = root.right;

                // No child case
                if(temp == null) {
                    temp = root;
                    root = null;
                }
                else // One child case
                    root = temp; // Copy the contents of the non-empty child

                temp = null;
            }
            else {
                // node with two children: Get the inorder successor (smallest
                // in the right subtree)
                AVLNode temp = minValueNode(root.right);

                // Copy the inorder successor's data to this node
                root.value = temp.value;

                // Delete the inorder successor
                root.right = deleteNode(root.right, temp.value);
            }
        }

        // If the tree had only one node then return
        if (root == null)
            return root;

        // STEP 2: UPDATE HEIGHT OF THE CURRENT NODE
        root.height = Math.max(height(root.left), height(root.right)) + 1;

        // STEP 3: GET THE BALANCE FACTOR OF THIS NODE (to check whether
        //  this node became unbalanced)
        int balance = getBalance(root);

        // If this node becomes unbalanced, then there are 4 cases

        // Left Left Case
        if (balance > 1 && getBalance(root.left) >= 0)
            return rightRotate(root);

        // Left Right Case
        if (balance > 1 && getBalance(root.left) < 0) {
            root.left =  leftRotate(root.left);
            return rightRotate(root);
        }

        // Right Right Case
        if (balance < -1 && getBalance(root.right) <= 0)
            return leftRotate(root);

        // Right Left Case
        if (balance < -1 && getBalance(root.right) > 0) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        return root;
    }

    public void print(AVLNode root) {

        if(root == null) {
            System.out.println("(XXXXXX)");
            return;
        }

        int height = root.height,
            width = (int)Math.pow(2, height-1);

        // Preparing variables for loop.
        List<AVLNode> current = new ArrayList<AVLNode>(1),
            next = new ArrayList<AVLNode>(2);
        current.add(root);

        final int maxHalfLength = 4;
        int elements = 1;

        StringBuilder sb = new StringBuilder(maxHalfLength*width);
        for(int i = 0; i < maxHalfLength*width; i++)
            sb.append(' ');
        String textBuffer;

        // Iterating through height levels.
        for(int i = 0; i < height; i++) {

            sb.setLength(maxHalfLength * ((int)Math.pow(2, height-1-i) - 1));

            // Creating spacer space indicator.
            textBuffer = sb.toString();

            // Print tree node elements
            for(AVLNode n : current) {

                System.out.print(textBuffer);

                if(n == null) {

                    System.out.print("        ");
                    next.add(null);
                    next.add(null);

                } else {

                    System.out.printf(n.value.toString());
                    next.add(n.left);
                    next.add(n.right);

                }

                System.out.print(textBuffer);

            }

            System.out.println();
            // Print tree node extensions for next level.
            if(i < height - 1) {

                for(AVLNode n : current) {

                    System.out.print(textBuffer);

                    if(n == null)
                        System.out.print("        ");
                    else
                        System.out.printf("%s      %s",
                                n.left == null ? " " : "/", n.right == null ? " " : "\\");

                    System.out.print(textBuffer);

                }

                System.out.println();

            }

            // Renewing indicators for next run.
            elements *= 2;
            current = next;
            next = new ArrayList<AVLNode>(elements);

        }
    }
    
    public AVLNode searchNode(String category_name, AVLNode root, AVLNode tmp){
        if(root ==null && tmp == null){
            return null;
        }else{
            if(tmp == null){
                if(root.value.equals(category_name)){
                    return root;
                }
                tmp = searchNode(category_name, root.left, tmp);
                tmp = searchNode(category_name, root.right, tmp);
            }
        }
        return tmp;
    }
    
    public void ReportIn(AVLNode root){
        if(root==null){
            return;
        }else{
            ReportIn(root.left);
            if(iteracion!=0){
                contenido +=  " -> "+ root.value.toString();
            }else{
                contenido += root.value.toString();
                
            }
        }
        iteracion++;
        ReportIn(root.right);
        
    }
    
    public void postOrder(AVLNode root){
        if(root==null){
        }else{
            postOrder(root.left);
            postOrder(root.right);
            contenido += root.value + ", ";
        }
    }
    
    public void preOrder(AVLNode root){
       if(root==null){
        }else{
            contenido += root.value + ", ";
            preOrder(root.left);
            preOrder(root.right);
        } 
    }
    
    public void inOrder(AVLNode root){
        if(root==null){
        }else{
            inOrder(root.left);
            contenido += root.value + ", ";
            inOrder(root.right);
        } 
    }
    
    public void ReportPost(AVLNode root){
        if(root==null){
            return;
        }else{
            ReportPost(root.left);
            ReportPost(root.right);
            if(iteracion!=0){
                contenido += " -> " +root.value.toString();
            }else{
                contenido += root.value.toString();
            }
        }
        iteracion++;
    }
    
    public void ReportPre(AVLNode root){
        if(root==null){
            return;
        }else{
            if(iteracion!=0){
                contenido +=  " -> "+ root.value.toString();
            }else{
                contenido += root.value.toString();
            }
        }
        iteracion++;
        ReportPre(root.left);
        ReportPre(root.right);
    }
    
    private String Branch(AVLNode root, String chain){
        if(root == null){
            return chain;
        }
        chain += "nodo" + root.value.toString() + " [ label = \" " + root.value.toString() + "\"];\n";
        chain = Branch(root.left, chain);
        chain = Branch(root.right, chain);
        return chain;
    }

    public String Children(AVLNode root, String chain){
        if(root == null){
            return chain;
        }
        if(root.left != null){
            chain += "nodo" + root.value.toString() + ": c0->nodo" + root.left.value.toString() + ";\n";
        }
        if(root.right != null){
            chain += "nodo" + root.value.toString()  + ": c1->nodo" + root.right.value.toString() + ";\n";
        }
        chain = Children(root.left, chain);
        chain = Children(root.right, chain);
        return chain;
    }
    
    private String GraphAVL(AVLNode root){
        String txt = "";
        txt = Branch(root, txt);
        txt = Children(root, txt);
        return txt;
    }
    
    public String printTree(AVLNode root){
        return GraphAVL(root);
    }
    
    public boolean searchByName(AVLNode root, Comparable v){
        if(root==null){
            return validation;
        }else{
            if(root.value.equals(v)){
                validation = true;
            }
            searchByName(root.left, v);
            searchByName(root.right, v);
        }
        return validation;
    }
    
    
}
