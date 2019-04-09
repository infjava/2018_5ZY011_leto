/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.uniza.fri.wof.prikazy;

import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author janik
 */
class RiadokZaznamu {

    private final String prikaz;
    private final String parameter;
    private final ArrayList<Integer> precitaneCisla;

    RiadokZaznamu(String prikaz, String parameter) {
        this.prikaz = prikaz;
        this.parameter = parameter;
        this.precitaneCisla = new ArrayList<Integer>();
    }

    void write(PrintWriter zapisovac) {
        zapisovac.format("> %s %s%n", this.prikaz, this.parameter);
        for (Integer cislo : this.precitaneCisla) {
            zapisovac.format("# %d%n", cislo);
        }
    }
    
    void pridajPrecitaneCislo(int cislo) {
        this.precitaneCisla.add(cislo);
    }
}
