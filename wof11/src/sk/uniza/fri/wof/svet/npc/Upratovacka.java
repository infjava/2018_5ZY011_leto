/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.uniza.fri.wof.svet.npc;

import sk.uniza.fri.wof.hra.Hrac;
import sk.uniza.fri.wof.svet.predmety.Navleky;
import sk.uniza.fri.wof.svet.predmety.VseobecnyPredmet;

/**
 *
 * @author janik
 */
public class Upratovacka extends Npc {
    /* Priklad rozhovoru:
    *** A ***
    1) Dobry den.
    2) Kde najdem Janecha.
    > 1
    *** B ***
    Padaj
    ------------------------
    *** A ***
    1) Dobry den.
    2) Kde najdem Janecha?
    > 2
    *** C ***
    Kto je Janech
    1) Najlepsi ucitel.
    2) Tiez neviem.
    3) Ten profak.
    > 1
    *** D ***
    Toho nepoznam.
    ------------------------
    *** A ***
    1) Dobry den.
    2) Kde najdem Janecha?
    > 2
    *** C ***
    Kto je Janech
    1) Najlepsi ucitel.
    2) Tiez neviem.
    3) Ten profak.
    > 2
    *** E ***
    Tak neotravuj.
    ------------------------
    *** A ***
    1) Dobry den.
    2) Kde najdem Janecha?
    > 2
    *** C ***
    Kto je Janech
    1) Najlepsi ucitel.
    2) Tiez neviem.
    3) Ten profak.
    > 3
    *** F ***
    Je na wecku.
    */
    
    public Upratovacka() {
        super("upratovacka");
        this.pridajDoInventara(new VseobecnyPredmet("handra"));
    }

    @Override
    public boolean mozeVstupitDoMiestnosti(Hrac hrac) {
        Navleky navleky = (Navleky)hrac.getPredmet("navleky");
        
        if (navleky == null || !navleky.getObute()) {
            System.out.println("Upratovacka: „Ale ides! Kde mas navleky?!?“");
            return false;
        }
        
        return true;
    }

    @Override
    public Dialog getDialog() {
        StavDialogu d = new StavDialogu("Toho nepoznam.");
        StavDialogu e = new StavDialogu("Tak neotravuj.");
        StavDialogu f = new StavDialogu("Je na wecku.");
        
        StavDialogu b = new StavDialogu("Padaj");
        StavDialogu c = new StavDialogu("Kto je Janech",
            new MoznostDialogu(d, "Najlepsi ucitel."),
            new MoznostDialogu(e, "Tiez neviem."),
            new MoznostDialogu(f, "Ten profak.")
        );
        
        StavDialogu g;
        if (this.maPredmet("handra")) {
            g = new StavDialoguSPredmetom("Tu mas", "handra");
        } else {
            g = new StavDialogu("Aku handru? Ved si uz jednu dostal. Tak padaj!");
        }
        
        StavDialogu a = new StavDialogu(null,
            new MoznostDialogu(b, "Dobry den."),
            new MoznostDialogu(c, "Kde najdem Janecha."),
            new MoznostDialogu(g, "Daj handru")
        );
        
        Dialog dialog = new Dialog(a, this);
        
        return dialog;
    }
}
