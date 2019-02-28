package sk.uniza.fri.wof.svet;

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

    private final Miestnost startovaciaMiestnost;

    public Mapa() {
        // vytvorenie miestnosti
        Miestnost mhd = new Miestnost("mhd - Stale stoji. Ujo sofer niekam odbehol");
        Miestnost domov = new Miestnost("domov - Vsade dobre, na FRI najlepsie");
        Miestnost parkovisko = new Miestnost("parkovisko - Pokojne tu stoja auta. Treba byt ticho a nevyplasit ich");
        Miestnost jedalen = new Miestnost("jedalen - Mnam mnam, tu to vonia");
        Miestnost vratnica = new Miestnost("vratnica - Tu sedi pani vratnicka");
        Miestnost ra = new Miestnost("chodba ra - Dlha chodba");
        Miestnost rb = new Miestnost("chodba rb - Tmava chodba");
        Miestnost ra6 = new Miestnost("ra006 - Labacik plny laboratornych (teda ehm vlastne pocitacovych) mysi");
        Miestnost wc = new Miestnost("wc - Smradocek, ale teplucko");
        Miestnost ic = new Miestnost("ic - Informacne centrum, prave prebiehaju volby");
        
        mhd.nastavVychod("juh", parkovisko);
        
        domov.nastavVychod("vychod", parkovisko);
        
        parkovisko.nastavVychod("sever", mhd);
        parkovisko.nastavVychod("zapad", domov);
        parkovisko.nastavVychod("vychod1", jedalen);
        parkovisko.nastavVychod("vychod2", vratnica);
        
        jedalen.nastavVychod("zapad", parkovisko);
        jedalen.nastavVychod("hore", ra);
        
        vratnica.nastavVychod("zapad", parkovisko);
        vratnica.nastavVychod("sever", ra);
        vratnica.nastavVychod("juh", rb);
        vratnica.nastavVychod("vychod", ic);
        
        ra.nastavVychod("dole", jedalen);
        ra.nastavVychod("zapad", ra6);
        ra.nastavVychod("vychod", wc);
        ra.nastavVychod("juh", vratnica);
        
        rb.nastavVychod("sever", vratnica);
        
        ra6.nastavVychod("vychod", ra);
        
        wc.nastavVychod("zapad", ra);
        
        ic.nastavVychod("zapad", vratnica);

        this.startovaciaMiestnost = parkovisko;  // startovacia miestnost hry
    }

    public Miestnost getStartovaciaMiestnost() {
        return this.startovaciaMiestnost;
    }
    
}
