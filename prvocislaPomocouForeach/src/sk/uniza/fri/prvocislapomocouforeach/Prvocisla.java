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
public class Prvocisla implements Iterable<Integer> {

    private final int min;
    private final int max;

    public Prvocisla(int min, int max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new PrvocislaIterator(this.min, this.max);
    }
    
}
