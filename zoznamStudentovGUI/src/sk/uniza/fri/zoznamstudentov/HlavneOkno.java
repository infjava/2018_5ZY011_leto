/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.uniza.fri.zoznamstudentov;

import javax.swing.JOptionPane;

/**
 *
 * @author janik
 */
public class HlavneOkno extends javax.swing.JFrame {

    /**
     * Creates new form HlavneOkno
     */
    public HlavneOkno() {
        this.initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.JPanel jPanel2 = new javax.swing.JPanel();
        javax.swing.JLabel jLabel2 = new javax.swing.JLabel();
        javax.swing.JScrollPane jScrollPane2 = new javax.swing.JScrollPane();
        meno = new javax.swing.JTextArea();
        javax.swing.JLabel jLabel3 = new javax.swing.JLabel();
        javax.swing.JScrollPane jScrollPane3 = new javax.swing.JScrollPane();
        priezvisko = new javax.swing.JTextArea();
        javax.swing.JPanel jPanel3 = new javax.swing.JPanel();
        tlacidloPridajStudenta = new javax.swing.JButton();
        tlacidloOdstranStudenta = new javax.swing.JButton();
        javax.swing.JPanel jPanel4 = new javax.swing.JPanel();
        javax.swing.JLabel jLabel1 = new javax.swing.JLabel();
        javax.swing.JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        zoznamStudentov = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Zoznam študentov");

        jPanel2.setLayout(new java.awt.GridLayout(4, 0));

        jLabel2.setText("Meno");
        jPanel2.add(jLabel2);

        meno.setColumns(20);
        meno.setRows(5);
        jScrollPane2.setViewportView(meno);

        jPanel2.add(jScrollPane2);

        jLabel3.setText("Priezvisko");
        jPanel2.add(jLabel3);

        priezvisko.setColumns(20);
        priezvisko.setRows(5);
        jScrollPane3.setViewportView(priezvisko);

        jPanel2.add(jScrollPane3);

        getContentPane().add(jPanel2, java.awt.BorderLayout.LINE_END);

        jPanel3.setLayout(new java.awt.GridLayout());

        tlacidloPridajStudenta.setText("pridať študenta");
        tlacidloPridajStudenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pridatStudenta(evt);
            }
        });
        jPanel3.add(tlacidloPridajStudenta);

        tlacidloOdstranStudenta.setText("odstrániť študenta");
        jPanel3.add(tlacidloOdstranStudenta);

        getContentPane().add(jPanel3, java.awt.BorderLayout.PAGE_END);

        jPanel4.setLayout(new java.awt.BorderLayout());

        jLabel1.setText("Zoznam študentov");
        jPanel4.add(jLabel1, java.awt.BorderLayout.PAGE_START);

        jScrollPane1.setViewportView(zoznamStudentov);

        jPanel4.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel4, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void pridatStudenta(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pridatStudenta
        JOptionPane.showMessageDialog(null, "Som tu");
    }//GEN-LAST:event_pridatStudenta

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea meno;
    private javax.swing.JTextArea priezvisko;
    private javax.swing.JButton tlacidloOdstranStudenta;
    private javax.swing.JButton tlacidloPridajStudenta;
    private javax.swing.JList<String> zoznamStudentov;
    // End of variables declaration//GEN-END:variables
}
