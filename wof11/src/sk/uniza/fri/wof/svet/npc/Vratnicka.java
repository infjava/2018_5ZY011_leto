/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.uniza.fri.wof.svet.npc;

import sk.uniza.fri.wof.hra.Hrac;
import sk.uniza.fri.wof.svet.Mapa;
import sk.uniza.fri.wof.svet.Miestnost;

/**
 *
 * @author janik
 */
public class Vratnicka extends Npc {

    public Vratnicka() {
        super("vratnicka");
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
    
}
