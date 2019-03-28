/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.uniza.fri.wof.svet;

import sk.uniza.fri.wof.hra.Hrac;


public class StavDialoguSPredmetom extends StavDialogu {

    private final String nazovPredmetu;

    public StavDialoguSPredmetom(String replika, String nazovPredmetu, MoznostDialogu... moznosti) {
        super(replika, moznosti);
        this.nazovPredmetu = nazovPredmetu;
    }

    @Override
    void vykonajAkciu(Hrac hrac, INpc npc) {
        hrac.pridajPredmet(npc.vyberPredmetZInventara(this.nazovPredmetu));
    }
}
