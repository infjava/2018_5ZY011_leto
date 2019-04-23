/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.uniza.fri.generickaklietka;

/**
 *
 * @author janik
 */
public class GenerickaKlietka {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Klietka klietkaNaLeva = new Klietka();
        klietkaNaLeva.vloz(new Lev());
        // tu by nastala chyba
        // klietkaNaLeva.vloz(new Lev());
        System.out.println(klietkaNaLeva);
        
        Klietka klietkaNaMys = new Klietka();
        klietkaNaMys.vloz(new Mys());
        System.out.println(klietkaNaMys);
    }
}
