/**
 * Hlavna trieda hry WoF s metodou main - spustanie v NB
 * 
 * @author Lubomir Sadlon
 * @version 21.2.2012
 */
public class Wof00 {
/*
    Mozne rozsirenia hry:
    - Teleport
    - Smrt
        - Pad zo schodov s urcitou pravdepodobnostou
    - Questy
        - Ciel pre celu hru
        - Odmena
        - Vycistit podlahu v kazdej miestnosti
    - Inventar
        - Predmety
        - Pivo (obnovi zivoty)
        - Kluce od miestnosti
        - Navleky
        - Handra
        - Cistiace prostriedky
    - Levely/Charakteristiky
        - Exp
        - Zivoty - HP
    - Npc
        - Nepriatelia
        - Bosovia
        - Vratnicka
        - Bufetarka
    - Obchodovanie
        - Peniaze
*/
    /**
     * @param args parametre programu
     */
    public static void main(String[] args) {
        Hra hra = new Hra();
        hra.hraj();
    }
}
