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
        Klietka<Lev> klietkaNaLeva = new Klietka<Lev>();
        klietkaNaLeva.vloz(new Lev());
        klietkaNaLeva.nakrm(new Maso());
        klietkaNaLeva.nakrm(new Mys());
        // leva nemozem krmit syrom
        // klietkaNaLeva.nakrm(new Syr());
        // tu by nastala chyba
        // klietkaNaLeva.vloz(new Lev());
        System.out.println(klietkaNaLeva);
        
        Klietka<Mys> klietkaNaMys = new Klietka<Mys>();
        klietkaNaMys.vloz(new Mys());
        klietkaNaMys.nakrm(new Syr());
        // do klietky na mys sa neda vlozit lev
        // klietkaNaMys.vloz(new Lev());
        System.out.println(klietkaNaMys);
        
        Klietka<Zviera> klietkaNaZviera = new Klietka<Zviera>();
        klietkaNaZviera.vloz(new Mys());
        //klietkaNaZviera.vloz(new Zviera());
        System.out.println(klietkaNaZviera);
        
        // klietka nemoze obsahovat insie ako zvierata
        // Klietka<Integer> klietkaNaCeleCisla = new Klietka<Integer>();
        
        Lev lev = new Lev();
        lev.zjedz(new Mys());
        // lev isto nezje syr
        // lev.zjedz(new Syr());
    }
}
