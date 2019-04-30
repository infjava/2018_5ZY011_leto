/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.uniza.fri.vtipnaaplikacia;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;

/**
 *
 * @author janik
 */
public class PremiestnenieTlacidielMouseListener extends MouseAdapter {

    private final JButton tlacidloA;
    private final JButton tlacidloFx;

    PremiestnenieTlacidielMouseListener(JButton tlacidloA, JButton tlacidloFx) {
        this.tlacidloA = tlacidloA;
        this.tlacidloFx = tlacidloFx;
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        this.tlacidloA.setText("FX");
        this.tlacidloFx.setText("A");
        this.tlacidloFx.setFocusable(true);
        this.tlacidloA.setFocusable(false);
    }
}
