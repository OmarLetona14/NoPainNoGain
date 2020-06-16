/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parcial2.matrix;

/**
 *
 * @author Omar
 */
public class Linearization {
    
    
    
    public int rowMajor(int x, int y, int row){
        int pos = (x*row)+y;
        return pos;
    }
    
    public int columnMajor(int x, int y, int column){
        int pos = x + y*column;
        return pos;
    }
    
        
}
