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
class Klietka {

    private Object zviera;

    Klietka() {
        this.zviera = null;
    }

    void vloz(Object zviera) {
        this.zviera = zviera;
    }

    @Override
    public String toString() {
        return "Klietka{" + "zviera=" + this.zviera + '}';
    }
}
