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
public class Dialog {

    private final StavDialogu zaciatocnyStav;

    Dialog(StavDialogu zaciatocnyStav) {
        this.zaciatocnyStav = zaciatocnyStav;
    }

    public void zacni() {
        System.out.println("NPC sa s tebou rozprava");
    }
}
