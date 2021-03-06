package sk.uniza.fri.wof.svet;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import sk.uniza.fri.wof.svet.predmety.IPredmet;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeMap;
import sk.uniza.fri.wof.hra.Hrac;
import sk.uniza.fri.wof.svet.npc.Npc;

/**
 * Trieda Miestnost realizuje jednu miestnost/priestor v celom priestore hry.
 * Kazda "miestnost" je z inymi miestnostami spojena vychodmi. 
 * Vychody z miestnosti su oznacovane svetovymi stranami sever, vychod, juh
 * a zapad. Pre kazdy vychod si miestnost pamata odkaz na susednu miestnost
 * alebo null, ak tym smerom vychod nema.
 *
 * @author  Michael Kolling, David J. Barnes
 * @version 2006.03.30
 * @author  lokalizacia: Lubomir Sadlon, Jan Janech
 * @version 2012.02.21
 */
public class Miestnost {
    private final String popisMiestnosti;
    private final TreeMap<String, Miestnost> vychody;
    private final HashMap<String, IPredmet> predmety;
    private final HashMap<String, Npc> npccka;
    private final String nazov;

    /**
     * Vytvori miestnost popis ktorej je v parametrom.
     * Po vytvoreni miestnost nema ziadne vychody. Popis miesnost strucne 
     * charakterizuje.
     * 
     * @param popis text popisu miestnosti.
     */
    public Miestnost(String nazov, String popis) {
        this.nazov = nazov;
        this.popisMiestnosti = popis;
        this.vychody = new TreeMap<String, Miestnost>();
        this.predmety = new HashMap<String, IPredmet>();
        this.npccka = new HashMap<String, Npc>();
    }

    /**
     * Nastavi vychody z miestnosti. Kazdy vychod je urceny bud odkazom 
     * na miestnost alebo hodnotou null, ak vychod tym smerom neexistuje.
     * 
     * @param smer smer vychodu.
     * @param miestnost cielova miestnost vychodu.
     */
    public void nastavVychod(String smer, Miestnost miestnost) {
        this.vychody.put(smer, miestnost);
    }

    public String getNazov() {
        return this.nazov;
    }

    /**
     * @return textovy popis miestnosti.
     */
    public String getPopis() {
        return this.popisMiestnosti;
    }

    public void infoOMiestnosti() {
        System.out.println("Teraz si v miestnosti " + this.getNazov());
        System.out.println(this.getPopis());
        System.out.print("Vychody: ");
        for (String smer : this.vychody.keySet()) {
            System.out.print(smer + " ");
        }
        System.out.println();
    }
    
    public Miestnost getVychod(String smer) {
        return this.vychody.get(smer);
    }

    public void pridajPredmet(IPredmet predmet) {
        this.predmety.put(predmet.getNazov(), predmet);
    }

    public void vypisZoznamObjektov() {
        if (this.predmety.isEmpty() && this.npccka.isEmpty()) {
            System.out.println("Nic si nenasiel.");
            return;
        }
        
        this.vypisObjekty(this.predmety.keySet(), "Nasiel si tieto predmety");
        this.vypisObjekty(this.npccka.keySet(), "Vidis tieto postavy");
    }

    private void vypisObjekty(Set<String> objekty, String popis) {
        if (!objekty.isEmpty()) {
            System.out.println(popis + ":");
            for (String predmet : objekty) {
                System.out.println("- " + predmet);
            }
        }
    }

    public IPredmet odstranPredmet(String nazovPredmetu) {
        return this.predmety.remove(nazovPredmetu);
    }

    public IPredmet getPredmet(String nazovPredmetu) {
        return this.predmety.get(nazovPredmetu);
    }

    void pridajNpc(Npc npc) {
        this.npccka.put(npc.getMeno(), npc);
    }
    
    public boolean mozeVstupit(Hrac hrac) {
        for (Npc npc : this.npccka.values()) {
            if (!npc.mozeVstupitDoMiestnosti(hrac)) {
                return false;
            }
        }
        
        return true;
    }

    public Npc getNpc(String menoNpc) {
        return this.npccka.get(menoNpc);
    }

    Miestnost[] getCieloveMiestnosti() {
        Miestnost[] ret = new Miestnost[this.vychody.size()];
        this.vychody.values().toArray(ret);
        return ret;
    }

    void ulozSave(DataOutputStream zapisovac) throws IOException {
        zapisovac.writeInt(this.predmety.size());
        for (IPredmet predmet : this.predmety.values()) {
            zapisovac.writeUTF(predmet.getTyp());
            predmet.ulozSave(zapisovac);
        }
    }

    void nacitajSave(DataInputStream citac, int verzia) throws IOException {
        this.predmety.clear();

        final int pocetPredmetov = citac.readInt();

        for (int i = 0; i < pocetPredmetov; i++) {
            String typPredmetu = citac.readUTF();
            IPredmet predmet = Mapa.getInstancia().vytvorPredmet(typPredmetu);

            predmet.nacitajSave(citac, verzia);

            this.predmety.put(predmet.getNazov(), predmet);
        }
    }

    boolean getTrebaUlozit() {
        if (this.predmety.size() > 0) {
            return true;
        }
        
        return false;
    }
}
