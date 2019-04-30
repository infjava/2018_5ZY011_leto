/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.uniza.fri.vtipnaaplikacia;

import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author janik
 */
public class VtipneOkno {

    private final JFrame okno;

    public VtipneOkno() {
        this.okno = new JFrame("Otazka");
        this.okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.okno.add(new JLabel("Aku znamku budes mat z Informatiky"));
        this.okno.pack();
    }
    
    public void zobraz() {
        this.okno.setVisible(true);
    }
}
