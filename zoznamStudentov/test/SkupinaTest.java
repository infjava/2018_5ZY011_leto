/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author janik
 */
public class SkupinaTest {

    private Skupina skupina;
    private Student fero;
    
    @Before
    public void setUp() {
        this.skupina = new Skupina("5ZY011");
        this.fero = new Student("123", "Fero", "Mrkva");
    }
    
    @Test
    public void pridanieStudenta() {
        this.skupina.pridajStudenta(this.fero);
        Student najdeny = this.skupina.getStudent(this.fero.getOsobneCislo());
        //toto je zle: Assert.assertEquals(this.fero.getOsobneCislo(), najdeny.getOsobneCislo());
        Assert.assertSame(this.fero, najdeny);
    }
}
