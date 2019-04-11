package sk.uniza.fri.wof.svet;

import sk.uniza.fri.wof.svet.npc.Vratnicka;
import sk.uniza.fri.wof.svet.predmety.Navleky;
import sk.uniza.fri.wof.svet.predmety.VseobecnyPredmet;
import sk.uniza.fri.wof.svet.npc.Upratovacka;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author janik
 */
public class Mapa {
    private static Mapa instancia = new Mapa();
    
    public static Mapa getInstancia() {
        return Mapa.instancia;
    }

    private final Miestnost startovaciaMiestnost;
    private final ArrayList<Miestnost> zoznamMiestnosti;

    private Mapa() {
        this.zoznamMiestnosti = new ArrayList<Miestnost>();
        
        // vytvorenie miestnosti
        Miestnost mhd = this.newMiestnost("mhd", "Stale stoji. Ujo sofer niekam odbehol");
        Miestnost domov = this.newMiestnost("domov", "Vsade dobre, na FRI najlepsie");
        Miestnost parkovisko = this.newMiestnost("parkovisko", "Pokojne tu stoja auta. Treba byt ticho a nevyplasit ich");
        Miestnost jedalen = this.newMiestnost("jedalen", "Mnam mnam, tu to vonia");
        Miestnost vratnica = this.newMiestnost("vratnica", "Tu sedi pani vratnicka");
        Miestnost ra = this.newMiestnost("chodba ra", "Dlha chodba");
        Miestnost rb = this.newMiestnost("chodba rb", "Tmava chodba");
        Miestnost ra6 = this.newMiestnost("ra006", "Labacik plny laboratornych (teda ehm vlastne pocitacovych) mysi");
        Miestnost wc = this.newMiestnost("wc", "Smradocek, ale teplucko");
        Miestnost ic = this.newMiestnost("ic", "Informacne centrum, prave prebiehaju volby");
        
        parkovisko.pridajPredmet(new VseobecnyPredmet("kluc"));
        
        mhd.nastavVychod("juh", parkovisko);
        
        domov.nastavVychod("vychod", parkovisko);
        
        parkovisko.nastavVychod("sever", mhd);
        parkovisko.nastavVychod("zapad", domov);
        parkovisko.nastavVychod("vychod1", jedalen);
        parkovisko.nastavVychod("vychod2", vratnica);
        
        jedalen.nastavVychod("zapad", parkovisko);
        jedalen.nastavVychod("hore", ra);
        
        jedalen.pridajPredmet(new Navleky());
        
        vratnica.nastavVychod("zapad", parkovisko);
        vratnica.nastavVychod("sever", ra);
        vratnica.nastavVychod("juh", rb);
        vratnica.nastavVychod("vychod", ic);
        
        vratnica.pridajNpc(new Vratnicka());
        
        ra.nastavVychod("dole", jedalen);
        ra.nastavVychod("zapad", ra6);
        ra.nastavVychod("vychod", wc);
        ra.nastavVychod("juh", vratnica);
        
        rb.nastavVychod("sever", vratnica);
        
        ra6.nastavVychod("vychod", ra);
        ra6.pridajNpc(new Upratovacka());
        
        wc.nastavVychod("zapad", ra);
        
        ic.nastavVychod("zapad", vratnica);

        this.startovaciaMiestnost = parkovisko;  // startovacia miestnost hry
    }
    
    public Miestnost[] getMiestnosti() {
        Miestnost[] ret = new Miestnost[this.zoznamMiestnosti.size()];
        this.zoznamMiestnosti.toArray(ret);
        return ret;
    }
    
    private Miestnost newMiestnost(String nazov, String popis) {
        Miestnost ret = new Miestnost(nazov, popis);
        this.zoznamMiestnosti.add(ret);
        return ret;
    }

    public Miestnost getStartovaciaMiestnost() {
        return this.startovaciaMiestnost;
    }
    
    public ArrayList<Miestnost> getCesta(Miestnost vychod, Miestnost ciel) {
        int cisloVychod = this.getCisloMiestnosti(vychod);
        int cisloCiel = this.getCisloMiestnosti(ciel);
        
        int[][] maticaVzdialenosti = new int[this.getPocetMiestnosti()][this.getPocetMiestnosti()];
        int[][] maticaPrechodov = new int[this.getPocetMiestnosti()][this.getPocetMiestnosti()];
        
        this.inicializujFloyda(maticaVzdialenosti, maticaPrechodov);
        this.vypocitajFloyda(maticaVzdialenosti, maticaPrechodov);
        return this.najdiFloyda(maticaPrechodov, cisloVychod, cisloCiel);
    }
    
    private int getPocetMiestnosti() {
        return this.zoznamMiestnosti.size();
    }

    private int getCisloMiestnosti(Miestnost miestnost) {
        return this.zoznamMiestnosti.indexOf(miestnost);
    }

    private void inicializujFloyda(int[][] maticaVzdialenosti, int[][] maticaPrechodov) {
        for (int y = 0; y < this.getPocetMiestnosti(); y++) {
            for (int x = 0; x < this.getPocetMiestnosti(); x++) {
                maticaPrechodov[y][x] = -1;
                // 1000000 je nekonecno.
                // Nemoze byt Integer.MAX_VALUE, lebo sa budu scitavat
                maticaVzdialenosti[y][x] = 1000000;
            }
        }
        
        
        for (Miestnost vychodzia : this.zoznamMiestnosti) {
            int vychodziaCislo = this.getCisloMiestnosti(vychodzia);
            for (Miestnost cielova : vychodzia.getCieloveMiestnosti()) {
                int cielovaCislo = this.getCisloMiestnosti(cielova);
                
                maticaVzdialenosti[vychodziaCislo][cielovaCislo] = 1;
                maticaPrechodov[vychodziaCislo][cielovaCislo] = vychodziaCislo;
            }
        }
    }

    private void vypocitajFloyda(int[][] maticaVzdialenosti, int[][] maticaPrechodov) {
        for (int vychodzia = 0; vychodzia < this.getPocetMiestnosti(); vychodzia++) {
            for (int cielova = 0; cielova < this.getPocetMiestnosti(); cielova++) {
                for (int prechodova = 0; prechodova < this.getPocetMiestnosti(); prechodova++) {
                    int staraVzdialenost = maticaVzdialenosti[vychodzia][cielova];
                    int novaVzdialenost = maticaVzdialenosti[vychodzia][prechodova]
                            + maticaVzdialenosti[prechodova][cielova];
                    
                    if (novaVzdialenost < staraVzdialenost) {
                        maticaVzdialenosti[vychodzia][cielova] = novaVzdialenost;
                        maticaPrechodov[vychodzia][cielova] = prechodova;
                    }
                }
            }
        }
    }

    private ArrayList<Miestnost> najdiFloyda(int[][] maticaPrechodov, int cisloVychod, int cisloCiel) {
        ArrayList<Miestnost> ret = new ArrayList<Miestnost>();
        
        int cisloAktualna = cisloCiel;
        
        while (cisloAktualna != cisloVychod) {
            ret.add(0, this.zoznamMiestnosti.get(cisloAktualna));
            cisloAktualna = maticaPrechodov[cisloVychod][cisloAktualna];
            
            if (cisloAktualna == -1) {
                return null;
            }
        }
        
        ret.add(0, this.zoznamMiestnosti.get(cisloVychod));
        
        return ret;
    }
    
}
