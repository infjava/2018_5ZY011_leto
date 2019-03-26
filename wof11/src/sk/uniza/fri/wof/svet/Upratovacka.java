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
public class Upratovacka implements INpc {
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
    @Override
    public String getMeno() {
        return "upratovacka";
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
        
        StavDialogu a = new StavDialogu(null,
            new MoznostDialogu(b, "Dobry den."),
            new MoznostDialogu(c, "Kde najdem Janecha.")
        );
        
        Dialog dialog = new Dialog(a);
        
        return dialog;
    }

}
