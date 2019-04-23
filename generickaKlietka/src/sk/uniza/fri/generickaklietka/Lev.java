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
public class Lev extends Zviera<Lev> {

    public Lev() {
    }

    @Override
    public String toString() {
        return "Lev{" + '}';
    }

    @Override
    void zjedz(IPotrava<Lev> potrava) {
        System.out.println("Lev zjedol " + potrava);
    }
}
