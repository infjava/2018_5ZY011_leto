/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.uniza.fri.vtipnaaplikacia;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author janik
 */
public class VtipneOkno {

    private final JFrame okno;

    public VtipneOkno() {
        this.okno = new JFrame("Otazka");
        this.okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.okno.setLayout(new BorderLayout());
        this.okno.add(new JLabel("Aku znamku budes mat z Informatiky"), BorderLayout.NORTH);
        
        JPanel tlacidla = new JPanel();
        tlacidla.setLayout(new GridLayout(1, 2));
        final JButton tlacidloA = new JButton("A");
        final JButton tlacidloFx = new JButton("FX");
        
        tlacidloA.addMouseListener(new PremiestnenieTlacidielMouseListener(tlacidloFx, tlacidloA));
        tlacidloFx.addMouseListener(new PremiestnenieTlacidielMouseListener(tlacidloA, tlacidloFx));
        
        tlacidloA.addActionListener(new ZobrazenieHlaskyActionListener());
        tlacidloFx.addActionListener(new ZobrazenieHlaskyActionListener());
        
        tlacidla.add(tlacidloA);
        tlacidla.add(tlacidloFx);
        
        this.okno.add(tlacidla, BorderLayout.CENTER);
        
        this.okno.pack();
    }
    
    public void zobraz() {
        this.okno.setVisible(true);
    }
}
