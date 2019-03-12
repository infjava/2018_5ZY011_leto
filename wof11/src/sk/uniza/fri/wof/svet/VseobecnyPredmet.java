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
public class VseobecnyPredmet implements IPredmet {
    private final String nazov;

    public VseobecnyPredmet(String nazov) {
        this.nazov = nazov;
    }

    public String getNazov() {
        return this.nazov;
    }

    @Override
    public void pouziSa() {
        System.out.println("Pouzivas, pouzivam, pouzivame....");
        System.out.println("Ale nist to nerobi.");
    }

    @Override
    public boolean jeManipulovatelny() {
        return true;
    }
}
