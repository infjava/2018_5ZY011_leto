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
public interface IPredmet {

    String getNazov();

    void pouziSa(Hrac hrac);
    
    boolean jeManipulovatelny();

    String getTyp();

    void ulozSave(DataOutputStream zapisovac) throws IOException;

    void nacitajSave(DataInputStream citac, int verzia) throws IOException;
    
}
