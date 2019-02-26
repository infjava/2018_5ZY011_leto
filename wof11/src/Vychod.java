/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author janik
 */
public class Vychod {

    private final String smer;
    private final Miestnost miestnost;

    public Vychod(String smer, Miestnost miestnost) {
        this.smer = smer;
        this.miestnost = miestnost;
    }

    public String getSmer() {
        return smer;
    }

    public Miestnost getMiestnost() {
        return miestnost;
    }
    
}
