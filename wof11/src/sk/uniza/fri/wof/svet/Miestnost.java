package sk.uniza.fri.wof.svet;


import java.util.HashMap;
import java.util.TreeMap;

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
    private final HashMap<String, INpc> npccka;

    /**
     * Vytvori miestnost popis ktorej je v parametrom.
     * Po vytvoreni miestnost nema ziadne vychody. Popis miesnost strucne 
     * charakterizuje.
     * 
     * @param popis text popisu miestnosti.
     */
    public Miestnost(String popis) {
        this.popisMiestnosti = popis;
        this.vychody = new TreeMap<String, Miestnost>();
        this.predmety = new HashMap<String, IPredmet>();
        this.npccka = new HashMap<String, INpc>();
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

    /**
     * @return textovy popis miestnosti.
     */
    public String getPopis() {
        return this.popisMiestnosti;
    }

    public void infoOMiestnosti() {
        System.out.println("Teraz si v miestnosti " + this.getPopis());
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

    public void vypisZoznamPredmetov() {
        if (this.predmety.isEmpty()) {
            System.out.println("Nic si nenasiel.");
            return;
        }
        
        System.out.println("Nasiel si tieto predmety:");
        for (IPredmet predmet : this.predmety.values()) {
            System.out.println("- " + predmet.getNazov());
        }
    }

    public IPredmet odstranPredmet(String nazovPredmetu) {
        return this.predmety.remove(nazovPredmetu);
    }

    public IPredmet getPredmet(String nazovPredmetu) {
        return this.predmety.get(nazovPredmetu);
    }

    void pridajNpc(INpc npc) {
        this.npccka.put(npc.getMeno(), npc);
    }
}
