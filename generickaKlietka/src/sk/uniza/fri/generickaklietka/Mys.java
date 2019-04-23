/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.uniza.fri.generickaklietka;

/**
 *
 * @author janik
 */
public class Mys extends Zviera {

    public Mys() {
    }

    @Override
    public String toString() {
        return "Mys{" + '}';
    }

    @Override
    void zjedz(Potrava potrava) {
        System.out.println("Mys zjedla " + potrava);
    }
}
