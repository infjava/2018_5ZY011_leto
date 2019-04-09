package sk.uniza.fri.wof.prikazy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Trieda Parser cita vstup zadany hracom do terminaloveho okna a pokusi sa
 * interpretovat ho ako prikaz hry. Kazda sprava dajPrikaz sposobi, ze parser
 * precita jeden riadok z terminaloveho okna a vyberie z neho prve dve slova.
 * Tie dve slova pouzije ako parametre v sprave new triede Prikaz.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 * @author  lokalizacia: Lubomir Sadlon, Jan Janech
 * @version 2012.02.21
 */
public class Parser {
    private Prikazy prikazy;  // odkaz na pripustne nazvy prikazov
    private Scanner citac;         // zdroj vstupov od hraca
    private final ArrayList<RiadokZaznamu> zaznam;
    private final ArrayList<NacitanyRiadokZaznamu> nacitanyZaznam;
    private final ArrayList<Integer> nacitaneCisla;

    /**
     * Vytvori citac na citanie vstupov z terminaloveho okna.
     */
    public Parser(Prikazy prikazy) {
        this.prikazy = prikazy;
        this.citac = new Scanner(System.in);
        this.zaznam = new ArrayList<RiadokZaznamu>();
        this.nacitanyZaznam = new ArrayList<NacitanyRiadokZaznamu>();
        this.nacitaneCisla = new ArrayList<Integer>();
    }

    /**
     * @return prikaz zadany hracom
     */
    public Prikaz nacitajPrikaz() {
        this.nacitaneCisla.clear();
        
        if (!this.nacitanyZaznam.isEmpty()) {
            NacitanyRiadokZaznamu nacitanyRiadokZaznamu = this.nacitanyZaznam.remove(0);
            this.nacitaneCisla.addAll(nacitanyRiadokZaznamu.getCisla());
            nacitanyRiadokZaznamu.vypis();
            return nacitanyRiadokZaznamu.getPrikaz(this.prikazy);
        }
        
        System.out.print("> ");     // vyzva pre hraca na zadanie prikazu

        String vstupnyRiadok = this.citac.nextLine();

        String prikaz = null;
        String parameter = null;

        // najde prve dve slova v riadku 
        Scanner tokenizer = new Scanner(vstupnyRiadok);
        if (tokenizer.hasNext()) {
            prikaz = tokenizer.next();      // prve slovo
            if (tokenizer.hasNext()) {
                parameter = tokenizer.next();      // druhe slovo
                // vsimnite si, ze zbytok textu sa ignoruje
            }
        }
        
        RiadokZaznamu riadokZaznamu = new RiadokZaznamu(prikaz, parameter);
        this.zaznam.add(riadokZaznamu);

        // kontrola platnosti prikazu
        if (this.prikazy.jePrikaz(prikaz)) {
            // vytvori platny prikaz
            return new Prikaz(prikaz, parameter);
        } else {
            // vytvori neplatny - "neznamy" prikaz
            return new Prikaz(null, parameter); 
        }
    }
    
    public void zapisDoSuboru(String nazov, int pocet) {
        int idxDo = this.zaznam.size() - 1; // ignoruj posledny prikaz
        int idxOd = idxDo - pocet;
        if (idxOd < 0) {
            idxOd = 0;
        }
        
        try (PrintWriter zapisovac = new PrintWriter(new File(nazov + ".cmd"))) {
            for (int i = idxOd; i < idxDo; i++) {
                this.zaznam.get(i).write(zapisovac);
            }
        } catch (FileNotFoundException ex) {
            throw new RuntimeException("Nepodarilo sa ulozit makro", ex);
        }
    }
    
    public int citajInt() {
        if (!this.nacitaneCisla.isEmpty()) {
            int ret = this.nacitaneCisla.remove(0);
            
            System.out.println(ret);
            
            return ret;
        }
        
        int ret = Integer.parseInt(this.citac.nextLine());
        
        // pridame precitane cislo do posledneho zaznamu
        this.zaznam.get(this.zaznam.size() - 1).pridajPrecitaneCislo(ret);
        
        return ret;
    }

    void nacitajZoSuboru(String nazov) throws FileNotFoundException {
        this.nacitanyZaznam.clear();
        try (Scanner citacMakra = new Scanner(new File(nazov + ".cmd"))) {
            while (citacMakra.hasNextLine()) {
                this.nacitanyZaznam.add(NacitanyRiadokZaznamu.read(citacMakra));
            }
        }
    }
}
