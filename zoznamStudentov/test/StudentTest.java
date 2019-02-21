/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author janik
 */
public class StudentTest {
    @Test
    public void osobneCislo() {
        Student fero = new Student("123", "Fero", "Mrkva");
        String osobneCislo = fero.getOsobneCislo();
        Assert.assertEquals("123", osobneCislo);
    }

    @Test
    public void osobneCisloJeNull() {
        Student fero = new Student(null, "Fero", "Mrkva");
        String osobneCislo = fero.getOsobneCislo();
        Assert.assertNull(osobneCislo);
    }
}
