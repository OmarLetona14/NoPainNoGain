/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parcial2.node;

/**
 *
 * @author Omar
 */
public class ABBNode {
    
    private Comparable data;
    private ABBNode left;
    private ABBNode right;

    public ABBNode(Comparable data) {
        this.data = data;
    }

    public Comparable getData() {
        return data;
    }

    public void setData(Comparable data) {
        this.data = data;
    }

    public ABBNode getLeft() {
        return left;
    }

    public void setLeft(ABBNode left) {
        this.left = left;
    }

    public ABBNode getRight() {
        return right;
    }

    public void setRight(ABBNode right) {
        this.right = right;
    }
    
    
    
}
