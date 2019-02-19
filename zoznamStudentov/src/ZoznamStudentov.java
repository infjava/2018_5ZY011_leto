/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author janik
 */
public class ZoznamStudentov {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        System.out.println("Hello world");
        
        Student ferko = new Student("123", "Ferko", "Mrkvicka");
        
        //System.out.println(ferko.toString());
        
        Skupina sk5ZY011 = new Skupina("5ZY011");
        sk5ZY011.pridajStudenta(ferko);
        sk5ZY011.vypisStudentov();
    }
    
}
