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
public class AVLNode {
    public AVLNode left, right, parent;
    public int height = 1;
    public Comparable value;

    public AVLNode(Comparable value) {
        this.value = value;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Comparable getValue() {
        return value;
    }

    public void setValue(Comparable value) {
        this.value = value;
    }
    
}
