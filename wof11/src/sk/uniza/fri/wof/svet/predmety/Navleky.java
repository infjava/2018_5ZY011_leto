/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.uniza.fri.wof.svet.predmety;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
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

    public boolean getObute() {
        return this.suObute;
    }

    @Override
    public String getTyp() {
        return "Navleky";
    }

    @Override
    public void ulozSave(DataOutputStream zapisovac) throws IOException {
        zapisovac.writeBoolean(this.suObute);
        zapisovac.writeInt(this.pocetObuti);
    }

    @Override
    public void nacitajSave(DataInputStream citac, int verzia) throws IOException {
        this.suObute = citac.readBoolean();
        this.pocetObuti = citac.readInt();
    }
}
