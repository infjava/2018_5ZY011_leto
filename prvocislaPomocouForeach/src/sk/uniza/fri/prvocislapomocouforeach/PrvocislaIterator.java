/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.uniza.fri.prvocislapomocouforeach;

import java.util.Iterator;

/**
 *
 * @author janik
 */
public class PrvocislaIterator implements Iterator<Integer> {

    private int aktualne;
    private final int maximalne;

    public PrvocislaIterator(int min, int max) {
        this.aktualne = min;
        this.maximalne = max;
        
        if (!this.jePrvocislo()) {
            this.najdiDalsiePrvocislo();
        }
    }

    private void najdiDalsiePrvocislo() {
        while (this.aktualne <= this.maximalne) {
            this.aktualne++;
            
            if (this.jePrvocislo()) {
                return;
            }
        }
    }

    private boolean jePrvocislo() {
        if (this.aktualne < 2) {
            return false;
        }
        
        for (int i = 2; i < this.aktualne; i++) {
            if (this.aktualne % i == 0) {
                return false;
            }
        }
        
        return true;
    }

    @Override
    public boolean hasNext() {
        return this.aktualne <= this.maximalne;
    }

    @Override
    public Integer next() {
        int odlozene = this.aktualne;
        this.najdiDalsiePrvocislo();
        return odlozene;
    }
}
