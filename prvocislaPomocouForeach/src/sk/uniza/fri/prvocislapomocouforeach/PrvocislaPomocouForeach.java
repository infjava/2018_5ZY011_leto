/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.uniza.fri.prvocislapomocouforeach;

/**
 *
 * @author janik
 */
public class PrvocislaPomocouForeach {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        for (Integer cislo : new Prvocisla(100, 200)) {
            System.out.println("Toto je tiez prvocislo: " + cislo);
        }
    }
    
}
