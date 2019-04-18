package sk.uniza.fri.wof.hra;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import sk.uniza.fri.wof.hra.exceptions.NemanipulovatelnyPredmetException;
import sk.uniza.fri.wof.hra.exceptions.NedaSaVstupitException;
import sk.uniza.fri.wof.hra.exceptions.NenasielSaVychodException;
import sk.uniza.fri.wof.hra.exceptions.NenasielSaPredmetException;
import sk.uniza.fri.wof.svet.npc.Dialog;
import java.util.HashMap;
import sk.uniza.fri.wof.prikazy.Parser;
import sk.uniza.fri.wof.svet.Mapa;
import sk.uniza.fri.wof.svet.Miestnost;
import sk.uniza.fri.wof.svet.predmety.IPredmet;
import sk.uniza.fri.wof.svet.npc.Npc;

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

    public void chodSmerom(String smer)
            throws NenasielSaVychodException, NedaSaVstupitException {
        // Pokus o opustenie aktualnej miestnosti danym vychodom.
        Miestnost novaMiestnost = this.aktualnaMiestnost.getVychod(smer);

        if (novaMiestnost == null) {
            throw new NenasielSaVychodException();
        } else if (!novaMiestnost.mozeVstupit(this)) {
            throw new NedaSaVstupitException();
        } else {
            this.aktualnaMiestnost = novaMiestnost;
            novaMiestnost.infoOMiestnosti();
        }
    }

    public void zdvihniPredmet(String nazovPredmetu)
            throws NenasielSaPredmetException, NemanipulovatelnyPredmetException {
        IPredmet zdvihnuty = this.aktualnaMiestnost.getPredmet(nazovPredmetu);
        
        if (zdvihnuty == null) {
            throw new NenasielSaPredmetException();
        }
        
        if (!zdvihnuty.jeManipulovatelny()) {
            throw new NemanipulovatelnyPredmetException();
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

    public void polozPredmet(String nazovPredmetu)
            throws NemanipulovatelnyPredmetException, NenasielSaPredmetException {
        IPredmet pokladany = this.inventar.get(nazovPredmetu);
        
        if (pokladany == null) {
            throw new NenasielSaPredmetException();
        }
        
        if (!pokladany.jeManipulovatelny()) {
            throw new NemanipulovatelnyPredmetException();
        }
        
        this.inventar.remove(nazovPredmetu);
        this.aktualnaMiestnost.pridajPredmet(pokladany);
    }

    public void pouziPredmet(String nazovPredmetu)
            throws NenasielSaPredmetException {
        IPredmet pouzivany = this.inventar.get(nazovPredmetu);
        
        if (pouzivany == null) {
            throw new NenasielSaPredmetException();
        }
        
        pouzivany.pouziSa(this);
    }

    public void odstranPredmetZInventara(String nazov) {
        this.inventar.remove(nazov);
    }

    public IPredmet getPredmet(String nazovPredmetu) {
        return this.inventar.get(nazovPredmetu);
    }

    public void oslovNpc(Parser parser, String menoNpc) {
        Npc npc = this.aktualnaMiestnost.getNpc(menoNpc);
        
        if (npc == null) {
            System.out.println("Nevidim nikoho takeho");
            return;
        }
        
        Dialog dialog = npc.getDialog();
        
        if (dialog == null) {
            System.out.println("Joj, " + menoNpc + " nie je moc zhovorcive NPC");
            return;
        }
        
        dialog.zacni(parser, this);
    }

    public void pridajPredmet(IPredmet predmet) {
        this.inventar.put(predmet.getNazov(), predmet);
    }

    public void ulozSave(DataOutputStream zapisovac) throws IOException {
        zapisovac.writeUTF(this.aktualnaMiestnost.getNazov());
        
        zapisovac.writeInt(this.inventar.size());
        for (IPredmet predmet : this.inventar.values()) {
            zapisovac.writeUTF(predmet.getTyp());
        }
    }

    public void nacitajSave(DataInputStream citac, int verzia) throws IOException {
        String nazovMiestnosti = citac.readUTF();
        this.aktualnaMiestnost = Mapa.getInstancia().getMiestnost(nazovMiestnosti);
        
        if (verzia >= 1) {
            final int pocetPredmetov = citac.readInt();

            for (int i = 0; i < pocetPredmetov; i++) {
                String typPredmetu = citac.readUTF();
                IPredmet predmet = Mapa.getInstancia().vytvorPredmet(typPredmetu);
                
                this.inventar.put(predmet.getNazov(), predmet);
            }
        }
    }
}
