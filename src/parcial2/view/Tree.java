/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parcial2.view;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import parcial2.helper.GenerateReport;
import parcial2.node.ABBNode;
import parcial2.node.AVLNode;
import parcial2.tree.ABBTree;
import parcial2.tree.AVLTree;

/**
 *
 * @author Omar
 */
public class Tree extends javax.swing.JFrame {
    
    private AVLTree avlTree;
    private ABBTree abbTree;
    private boolean avl;
    private boolean abb;
    private AVLNode root;
    private ABBNode abb_root;
    private GenerateReport report;
    /**
     * Creates new form ABBTree
     */
    public Tree() {
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        valuesTxt = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        postOrderLbl = new javax.swing.JLabel();
        preOrderLbl = new javax.swing.JLabel();
        inOrderLbl = new javax.swing.JLabel();
        generateBtn = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        typeCb = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Introduzca los valores");

        jLabel2.setText("Recorrido PostOrder");

        jLabel3.setText("Recorrido PreOrder");

        jLabel4.setText("Recorrido InOrder");

        postOrderLbl.setText("jLabel5");

        preOrderLbl.setText("jLabel6");

        inOrderLbl.setText("jLabel7");

        generateBtn.setText("Generar");
        generateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generateBtnActionPerformed(evt);
            }
        });

        jLabel8.setText("Tipo de arbol");

        typeCb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Arbol Binario", "Arbol AVL" }));
        typeCb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                typeCbActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(generateBtn))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2)
                            .addComponent(jLabel8))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(postOrderLbl)
                            .addComponent(inOrderLbl)
                            .addComponent(preOrderLbl)
                            .addComponent(valuesTxt)
                            .addComponent(typeCb, 0, 255, Short.MAX_VALUE))
                        .addGap(0, 28, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(valuesTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(typeCb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(preOrderLbl))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(inOrderLbl))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(postOrderLbl))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(generateBtn)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void generateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateBtnActionPerformed
        cleanFields();
        checkTree();
        report = new GenerateReport();
        if(!valuesTxt.getText().equals("")){
            try {
                String[] values  = valuesTxt.getText().split(",");
                for(String element:values){
                    Comparable x;
                    try{
                        try{x = Integer.valueOf(element.trim());}catch(Exception e){x = element.trim();}
                        if(avl){
                            root = avlTree.insert(root, x);
                        }else if(abb){
                            abb_root = abbTree.insert(abb_root, x);
                        }
                        System.out.println("Valor ingresado correctamente");
                    }catch(Exception e){
                        System.out.println("Ocurrio un error al ingresar los datos");
                    }
                }
                
                generarRecorridos();
            } catch (IOException ex) { 
                Logger.getLogger(Tree.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_generateBtnActionPerformed

    private void typeCbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_typeCbActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_typeCbActionPerformed
    
    private void generarRecorridos() throws IOException{
        File archivo;
        Desktop ejecutar = Desktop.getDesktop();
        if(avl){
            avlTree.postOrder(root);
            postOrderLbl.setText(avlTree.getContenido());
            avlTree.setContenido("");
            avlTree.inOrder(root);
            inOrderLbl.setText(avlTree.getContenido());
            avlTree.setContenido("");
            avlTree.preOrder(root);
            preOrderLbl.setText(avlTree.getContenido());
            avlTree.setContenido("");
            report.generate("ArbolAVL", avlTree.printTree(root));
            archivo = new File("ArbolAVL.png");
        }else{
            abbTree.postOrder(abb_root);
            postOrderLbl.setText(abbTree.getContent());
            abbTree.setContent("");
            abbTree.inOrder(abb_root);
            inOrderLbl.setText(abbTree.getContent());
            abbTree.setContent("");
            abbTree.preOrder(abb_root);
            preOrderLbl.setText(abbTree.getContent());
            abbTree.setContent("");
            report.generate("ArbolABB", abbTree.graph(abb_root));
            archivo = new File("ArbolABB.png");       
        }
        ejecutar.open(archivo);
    }
    
    private void cleanFields(){
        abb = false;
        avl = false;
        inOrderLbl.setText("");
        postOrderLbl.setText("");
        preOrderLbl.setText("");
        avlTree = new AVLTree();
        abbTree = new ABBTree();
    }
    
    private void checkTree(){
        switch(typeCb.getSelectedItem().toString()){
            case "Arbol Binario":
                abb = true;
                break;
            case "Arbol AVL":
                avl = true;
                break;
        } 
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Tree.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Tree.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Tree.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Tree.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Tree().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton generateBtn;
    private javax.swing.JLabel inOrderLbl;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel postOrderLbl;
    private javax.swing.JLabel preOrderLbl;
    private javax.swing.JComboBox typeCb;
    private javax.swing.JTextField valuesTxt;
    // End of variables declaration//GEN-END:variables
}
