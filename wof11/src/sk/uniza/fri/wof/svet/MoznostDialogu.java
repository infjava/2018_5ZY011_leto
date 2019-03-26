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
class MoznostDialogu {

    private final StavDialogu cielovyStav;
    private final String text;

    MoznostDialogu(StavDialogu cielovyStav, String text) {
        this.cielovyStav = cielovyStav;
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    public StavDialogu getCielovyStav() {
        return this.cielovyStav;
    }
    
    
}
