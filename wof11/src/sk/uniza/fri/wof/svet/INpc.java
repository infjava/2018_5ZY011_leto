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
public interface INpc {

    String getMeno();
    
    boolean mozeVstupitDoMiestnosti(Hrac hrac);
    
}
