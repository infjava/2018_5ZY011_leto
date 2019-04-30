/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.uniza.fri.vtipnaaplikacia;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author janik
 */
public class PremiestnenieTlacidielMouseListener extends MouseAdapter {

    public PremiestnenieTlacidielMouseListener() {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        System.out.println("Presiel si ponad komponent");
    }
}
