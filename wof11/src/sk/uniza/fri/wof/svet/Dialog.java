/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.uniza.fri.wof.svet;

import java.util.Scanner;

/**
 *
 * @author janik
 */
public class Dialog {

    private final StavDialogu zaciatocnyStav;

    Dialog(StavDialogu zaciatocnyStav) {
        this.zaciatocnyStav = zaciatocnyStav;
    }

    public void zacni() {
        Scanner vstup = new Scanner(System.in);
        
        this.zaciatocnyStav.vypisInfoOStave();
        
        int poradie;
        final int pocetMoznosti = this.zaciatocnyStav.getPocetMoznosti();
        do {
            System.out.format("Odpoved(1-%d)> ", pocetMoznosti);
            poradie = vstup.nextInt();
        } while (poradie < 1 || poradie > pocetMoznosti);
        
        StavDialogu ciel = this.zaciatocnyStav.getCielovyStav(poradie);
        ciel.vypisInfoOStave();
    }
}
