package sk.uniza.fri.wof.hra;

import java.util.ArrayList;
import sk.uniza.fri.wof.svet.Miestnost;
import sk.uniza.fri.wof.svet.Predmet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author janik
 */
public class Hrac {

    private Miestnost aktualnaMiestnost;
    private final ArrayList<Predmet> inventar;

    public Hrac(Miestnost startovaciaMiestnost) {
        this.aktualnaMiestnost = startovaciaMiestnost;
        this.inventar = new ArrayList<Predmet>();
    }

    public Miestnost getAktualnaMiestnost() {
        return this.aktualnaMiestnost;
    }

    public void chodSmerom(String smer) {
        // Pokus o opustenie aktualnej miestnosti danym vychodom.
        Miestnost novaMiestnost = this.aktualnaMiestnost.getVychod(smer);

        if (novaMiestnost == null) {
            System.out.println("Tam nie je vychod!");
        } else {
            this.aktualnaMiestnost = novaMiestnost;
            novaMiestnost.infoOMiestnosti();
        }
    }

    public void zdvihniPredmet(String nazovPredmetu) {
        Predmet zdvihuty = this.aktualnaMiestnost.odstranPredmet(nazovPredmetu);
        
        if (zdvihuty == null) {
            System.out.println("Nenasiel si predmet " + nazovPredmetu);
            return;
        }
        
        this.inventar.add(zdvihuty);
    }

    public void zobrazProfil() {
        if (this.inventar.isEmpty()) {
            System.out.println("Inventar je prazdny");
        } else {
            System.out.println("Inventar:");
            for (Predmet predmet : this.inventar) {
                System.out.println("- " + predmet.getNazov());
            }
        }
    }
}
