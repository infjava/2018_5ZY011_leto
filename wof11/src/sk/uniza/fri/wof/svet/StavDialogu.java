/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.uniza.fri.wof.svet;

/**
 *
 * @author janik
 */
class StavDialogu {

    private final String replika;
    private final MoznostDialogu[] moznosti;

    StavDialogu(String replika, MoznostDialogu[] moznosti) {
        this.replika = replika;
        this.moznosti = moznosti;
    }
    
}
