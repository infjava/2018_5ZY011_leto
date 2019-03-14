/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.uniza.fri.wof.svet;

import sk.uniza.fri.wof.hra.Hrac;

/**
 *
 * @author janik
 */
public class Navleky implements IPredmet {

    private boolean suObute;
    private int pocetObuti;

    public Navleky() {
        this.suObute = false;
        this.pocetObuti = 0;
    }
    
    @Override
    public String getNazov() {
        return "navleky";
    }

    @Override
    public void pouziSa(Hrac hrac) {
        if (this.suObute) {
            System.out.println("Vyzul si si navleky");
            this.suObute = false;
        } else {
            this.pocetObuti++;
            if (this.pocetObuti > 5) {
                System.out.println("Navleky su znicene");
                hrac.odstranPredmetZInventara(this.getNazov());
            } else {
                System.out.println("Obul si si navleky");
                this.suObute = true;
            }
        }
    }

    @Override
    public boolean jeManipulovatelny() {
        return !this.suObute;
    }

    boolean getObute() {
        return this.suObute;
    }
}
