/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.uniza.fri.generickaklietka;

import java.util.Iterator;

/**
 *
 * @author janik
 */
class Klietka<T extends Zviera> implements Iterable<T> {

    private T zviera;

    Klietka() {
        this.zviera = null;
    }

    void vloz(T zviera) {
        if (this.zviera != null) {
            throw new KlietkaNieJePrazdnaException("Uz tam mas " + this.zviera);
        }
        
        this.zviera = zviera;
    }
    
    void nakrm(IPotrava<T> potrava) {
        if (this.zviera == null) {
            throw new KlietkaJePrazdnaException("Nemozem nakrmit null");
        }
        
        this.zviera.zjedz(potrava);
    }

    @Override
    public String toString() {
        return "Klietka{" + "zviera=" + this.zviera + '}';
    }

    @Override
    public Iterator<T> iterator() {
        return new KlietkaIterator<T>(this.zviera);
    }
}
