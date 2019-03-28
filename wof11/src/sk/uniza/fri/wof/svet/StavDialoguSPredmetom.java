/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.uniza.fri.wof.svet;

import sk.uniza.fri.wof.hra.Hrac;


public class StavDialoguSPredmetom extends StavDialogu {

    private final IPredmet predmet;

    public StavDialoguSPredmetom(String replika, IPredmet predmet, MoznostDialogu... moznosti) {
        super(replika, moznosti);
        this.predmet = predmet;
    }

    @Override
    void vykonajAkciu(Hrac hrac) {
        hrac.pridajPredmet(this.predmet);
    }
}
