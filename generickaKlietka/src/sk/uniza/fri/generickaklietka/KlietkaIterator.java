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
public class KlietkaIterator<T> implements Iterator<T> {

    private T zviera;

    public KlietkaIterator(T zviera) {
        this.zviera = zviera;
    }

    @Override
    public boolean hasNext() {
        return this.zviera != null;
    }

    @Override
    public T next() {
        T odlozene = this.zviera;
        this.zviera = null;
        return odlozene;
    }
}
