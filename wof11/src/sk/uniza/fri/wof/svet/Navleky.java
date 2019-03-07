/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.uniza.fri.wof.svet;

/**
 *
 * @author janik
 */
public class Navleky implements IPredmet {

    private boolean suObute;

    public Navleky() {
        this.suObute = false;
    }
    
    @Override
    public String getNazov() {
        return "navleky";
    }

    @Override
    public void pouziSa() {
        if (this.suObute) {
            System.out.println("Vyzul si si navleky");
            this.suObute = false;
        } else {
            System.out.println("Obul si si navleky");
            this.suObute = true;
        }
    }
    
}
