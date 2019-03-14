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
public class Upratovacka implements INpc {

    @Override
    public String getMeno() {
        return "upratovacka";
    }

    @Override
    public boolean mozeVstupitDoMiestnosti(Hrac hrac) {
        Navleky navleky = (Navleky)hrac.getPredmet("navleky");
        
        if (navleky == null || !navleky.getObute()) {
            System.out.println("Upratovacka: „Ale ides! Kde mas navleky?!?“");
            return false;
        }
        
        return true;
    }

}
