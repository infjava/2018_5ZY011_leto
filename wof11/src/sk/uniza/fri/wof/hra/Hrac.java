package sk.uniza.fri.wof.hra;

import sk.uniza.fri.wof.svet.Dialog;
import java.util.HashMap;
import sk.uniza.fri.wof.svet.INpc;
import sk.uniza.fri.wof.svet.Miestnost;
import sk.uniza.fri.wof.svet.IPredmet;

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
    private final HashMap<String, IPredmet> inventar;

    public Hrac(Miestnost startovaciaMiestnost) {
        this.aktualnaMiestnost = startovaciaMiestnost;
        this.inventar = new HashMap<String, IPredmet>();
    }

    public Miestnost getAktualnaMiestnost() {
        return this.aktualnaMiestnost;
    }

    public void chodSmerom(String smer) {
        // Pokus o opustenie aktualnej miestnosti danym vychodom.
        Miestnost novaMiestnost = this.aktualnaMiestnost.getVychod(smer);

        if (novaMiestnost == null) {
            System.out.println("Tam nie je vychod!");
        } else if (!novaMiestnost.mozeVstupit(this)) {
            System.out.println("Neda sa vojst.");
        } else {
            this.aktualnaMiestnost = novaMiestnost;
            novaMiestnost.infoOMiestnosti();
        }
    }

    public void zdvihniPredmet(String nazovPredmetu) {
        IPredmet zdvihnuty = this.aktualnaMiestnost.getPredmet(nazovPredmetu);
        
        if (zdvihnuty == null) {
            System.out.println("Nenasiel si predmet " + nazovPredmetu);
            return;
        }
        
        if (!zdvihnuty.jeManipulovatelny()) {
            System.out.println("Nemozes zdvihnut " + nazovPredmetu);
            return;
        }
        
        this.aktualnaMiestnost.odstranPredmet(nazovPredmetu);
        this.inventar.put(zdvihnuty.getNazov(), zdvihnuty);
    }

    public void zobrazProfil() {
        if (this.inventar.isEmpty()) {
            System.out.println("Inventar je prazdny");
        } else {
            System.out.println("Inventar:");
            for (IPredmet predmet : this.inventar.values()) {
                System.out.println("- " + predmet.getNazov());
            }
        }
    }

    public void polozPredmet(String nazovPredmetu) {
        IPredmet pokladany = this.inventar.get(nazovPredmetu);
        
        if (pokladany == null) {
            System.out.println("Nemas predmet " + nazovPredmetu + " v inventari");
            return;
        }
        
        if (!pokladany.jeManipulovatelny()) {
            System.out.println("Nemozes polozit " + nazovPredmetu);
            return;
        }
        
        this.inventar.remove(nazovPredmetu);
        this.aktualnaMiestnost.pridajPredmet(pokladany);
    }

    public void pouziPredmet(String nazovPredmetu) {
        IPredmet pouzivany = this.inventar.get(nazovPredmetu);
        
        if (pouzivany == null) {
            System.out.println("Nemas predmet " + nazovPredmetu + " v inventari");
            return;
        }
        
        pouzivany.pouziSa(this);
    }

    public void odstranPredmetZInventara(String nazov) {
        this.inventar.remove(nazov);
    }

    public IPredmet getPredmet(String nazovPredmetu) {
        return this.inventar.get(nazovPredmetu);
    }

    public void oslovNpc(String menoNpc) {
        INpc npc = this.aktualnaMiestnost.getNpc(menoNpc);
        
        if (npc == null) {
            System.out.println("Nevidim nikoho takeho");
            return;
        }
        
        Dialog dialog = npc.getDialog();
        
        if (dialog == null) {
            System.out.println("Joj, " + menoNpc + " nie je moc zhovorcive NPC");
            return;
        }
        
        dialog.zacni(this);
    }

    public void pridajPredmet(IPredmet predmet) {
        this.inventar.put(predmet.getNazov(), predmet);
    }
}
