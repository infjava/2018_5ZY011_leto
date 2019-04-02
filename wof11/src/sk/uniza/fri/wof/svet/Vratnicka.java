/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.uniza.fri.wof.svet;

import sk.uniza.fri.wof.hra.Hrac;

/**
 *
 * @author janik
 */
public class Vratnicka implements INpc {

    public Vratnicka() {
    }

    @Override
    public String getMeno() {
        return "vratnicka";
    }

    @Override
    public boolean mozeVstupitDoMiestnosti(Hrac hrac) {
        return true;
    }

    @Override
    public Dialog getDialog() {
        Miestnost[] miestnosti = Mapa.getInstancia().getMiestnosti();
        
        MoznostDialogu[] moznosti = new MoznostDialogu[miestnosti.length];
        
        for (int i = 0; i < miestnosti.length; i++) {
            StavDialogu cielovyStav = new StavDialoguCesta(miestnosti[i]);
            moznosti[i] = new MoznostDialogu(cielovyStav, miestnosti[i].getNazov());
        }
        
        StavDialogu zaciatocnyStav = new StavDialogu("Kam chces ist?", moznosti);
        return new Dialog(zaciatocnyStav, this);
    }

    @Override
    public IPredmet vyberPredmetZInventara(String nazovPredmetu) {
        return null;
    }
    
}
