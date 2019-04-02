/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.uniza.fri.wof.svet;

import java.util.ArrayList;
import sk.uniza.fri.wof.hra.Hrac;


public class StavDialoguCesta extends StavDialogu {

    private final Miestnost cielovaMiestnost;

    public StavDialoguCesta(Miestnost miestnost) {
        super(null, new MoznostDialogu[0]);
        this.cielovaMiestnost = miestnost;
    }

    @Override
    void vykonajAkciu(Hrac hrac, INpc npc) {
        ArrayList<Miestnost> cesta = Mapa.getInstancia().getCesta(hrac.getAktualnaMiestnost(), this.cielovaMiestnost);
        
        if (cesta == null) {
            System.out.println("Sorry! Cesta neexistuje");
            return;
        }
        
        System.out.println("Tak chod takto:");
        for (Miestnost miestnost : cesta) {
            System.out.println("- " + miestnost.getNazov());
        }
    }
    
    
}
