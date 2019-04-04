/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.uniza.fri.wof.svet.npc;

import sk.uniza.fri.wof.hra.Hrac;

/**
 *
 * @author janik
 */
class StavDialogu {

    private final String replika;
    private final MoznostDialogu[] moznosti;

    StavDialogu(String replika, MoznostDialogu... moznosti) {
        this.replika = replika;
        this.moznosti = moznosti;
    }

    void vypisInfoOStave() {
        if (this.replika != null) {
            System.out.println(this.replika);
        }
        
        int poradoveCislo = 1;
        for (MoznostDialogu moznostDialogu : this.moznosti) {
            System.out.format("%d) %s%n", poradoveCislo, moznostDialogu.getText());
            poradoveCislo++;
        }
    }

    StavDialogu getCielovyStav(int poradie) {
        return this.moznosti[poradie - 1].getCielovyStav();
    }

    int getPocetMoznosti() {
        return this.moznosti.length;
    }
    
    boolean getJeKoniec() {
        return this.moznosti.length == 0;
    }

    void vykonajAkciu(Hrac hrac, INpc npc) {
        
    }
}
