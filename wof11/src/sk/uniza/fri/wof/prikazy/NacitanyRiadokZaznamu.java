/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.uniza.fri.wof.prikazy;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author janik
 */
class NacitanyRiadokZaznamu {

    static NacitanyRiadokZaznamu read(Scanner citacMakra) {
        String vstupnyRiadok = citacMakra.nextLine();
        
        String prikaz = null;
        String parameter = null;

        // najde prve dve slova v riadku 
        Scanner tokenizer = new Scanner(vstupnyRiadok);
        tokenizer.next();                        // ignoruj znak >
        if (tokenizer.hasNext()) {
            prikaz = tokenizer.next();      // prve slovo
            if (tokenizer.hasNext()) {
                parameter = tokenizer.next();      // druhe slovo
                // vsimnite si, ze zbytok textu sa ignoruje
            }
        }
        
        ArrayList<Integer> cisla = new ArrayList<Integer>();
        while (citacMakra.hasNext("#")) {
            String vstupnyRiadok2 = citacMakra.nextLine();
            
            Scanner tokenizer2 = new Scanner(vstupnyRiadok2);
            tokenizer2.next();                        // ignoruj znak #
            cisla.add(tokenizer2.nextInt());
        }
        
        return new NacitanyRiadokZaznamu(prikaz, parameter, cisla);
    }
    
    private final String prikaz;
    private final String parameter;
    private final ArrayList<Integer> cisla;

    private NacitanyRiadokZaznamu(String prikaz, String parameter, ArrayList<Integer> cisla) {
        this.prikaz = prikaz;
        this.parameter = parameter;
        this.cisla = cisla;
        
    }
    
    Prikaz getPrikaz(Prikazy prikazy) {
        // kontrola platnosti prikazu
        if (prikazy.jePrikaz(this.prikaz)) {
            // vytvori platny prikaz
            return new Prikaz(this.prikaz, this.parameter);
        } else {
            // vytvori neplatny - "neznamy" prikaz
            return new Prikaz(null, this.parameter); 
        }
    }

    ArrayList<Integer> getCisla() {
        return this.cisla;
    }

    void vypis() {
        System.out.format("> %s %s (macro)%n", this.prikaz, this.parameter);
    }
}
