/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.uniza.fri.wof.svet.npc;

import java.util.Scanner;
import sk.uniza.fri.wof.hra.Hrac;
import sk.uniza.fri.wof.prikazy.Parser;

/**
 *
 * @author janik
 */
public class Dialog {

    private final StavDialogu zaciatocnyStav;
    private final Npc npc;

    Dialog(StavDialogu zaciatocnyStav, Npc npc) {
        this.zaciatocnyStav = zaciatocnyStav;
        this.npc = npc;
    }

    public void zacni(Parser parser, Hrac hrac) {
        StavDialogu aktualny = this.zaciatocnyStav;
        
        do {
            aktualny.vykonajAkciu(hrac, this.npc);
            aktualny.vypisInfoOStave();
            
            int poradie;
            final int pocetMoznosti = aktualny.getPocetMoznosti();
            do {
                System.out.format("Odpoved(1-%d)> ", pocetMoznosti);
                poradie = parser.citajInt();
            } while (poradie < 1 || poradie > pocetMoznosti);
            
            aktualny = aktualny.getCielovyStav(poradie);
        } while (!aktualny.getJeKoniec());
        
        aktualny.vykonajAkciu(hrac, this.npc);
        aktualny.vypisInfoOStave();
    }
}
