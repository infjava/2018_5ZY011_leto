/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.uniza.fri.wof.svet.npc;

import java.util.HashMap;
import sk.uniza.fri.wof.hra.Hrac;
import sk.uniza.fri.wof.svet.predmety.IPredmet;

/**
 *
 * @author janik
 */
public abstract class Npc {

    private final HashMap<String, IPredmet> inventar;
    private final String meno;

    public Npc(String meno) {
        this.inventar = new HashMap<String, IPredmet>();
        this.meno = meno;
    }

    public String getMeno() {
        return this.meno;
    }
    
    public boolean mozeVstupitDoMiestnosti(Hrac hrac) {
        return true;
    }

    public abstract Dialog getDialog();

    IPredmet vyberPredmetZInventara(String nazovPredmetu) {
        return this.inventar.remove(nazovPredmetu);
    }

    protected void pridajDoInventara(IPredmet predmet) {
        this.inventar.put(predmet.getNazov(), predmet);
    }

    protected boolean maPredmet(String nazovPredmetu) {
        return this.inventar.containsKey(nazovPredmetu);
    }
}
