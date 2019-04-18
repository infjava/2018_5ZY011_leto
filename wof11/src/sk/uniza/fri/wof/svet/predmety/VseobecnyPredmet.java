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
public class VseobecnyPredmet implements IPredmet {
    private String nazov;

    public VseobecnyPredmet(String nazov) {
        this.nazov = nazov;
    }

    public String getNazov() {
        return this.nazov;
    }

    @Override
    public void pouziSa(Hrac hrac) {
        System.out.println("Pouzivas, pouzivam, pouzivame....");
        System.out.println("Ale nist to nerobi.");
    }

    @Override
    public boolean jeManipulovatelny() {
        return true;
    }

    @Override
    public String getTyp() {
        return "VseobecnyPredmet";
    }

    @Override
    public void ulozSave(DataOutputStream zapisovac) throws IOException {
        zapisovac.writeUTF(this.nazov);
    }

    @Override
    public void nacitajSave(DataInputStream citac, int verzia) throws IOException {
        this.nazov = citac.readUTF();
    }
}
