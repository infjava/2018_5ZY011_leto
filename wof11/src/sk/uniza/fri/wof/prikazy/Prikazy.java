package sk.uniza.fri.wof.prikazy;

import sk.uniza.fri.wof.hra.Hrac;
import sk.uniza.fri.wof.hra.exceptions.NedaSaVstupitException;
import sk.uniza.fri.wof.hra.exceptions.NemanipulovatelnyPredmetException;
import sk.uniza.fri.wof.hra.exceptions.NenasielSaPredmetException;
import sk.uniza.fri.wof.hra.exceptions.NenasielSaVychodException;
import sk.uniza.fri.wof.svet.Miestnost;

/**
 * Trieda NazvyPrikazov udrzuje zoznam nazvov platnych prikazov hry. Za ulohu ma
 * rozpoznavat platne prikazy.
 *
 * @author Michael Kolling and David J. Barnes
 * @version 2006.03.30
 * @author lokalizacia: Lubomir Sadlon, Jan Janech
 * @version 2012.02.21
 */
public class Prikazy {

    // konstantne pole nazvov prikazov
    private static final String[] PLATNE_PRIKAZY = {
        "chod", "ukonci", "pomoc", "preskumaj", "zdvihni",
        "profil", "poloz", "pouzi", "mapa", "oslov"
    };

    /**
     * Kontroluje, ci nazov v parametri je platny prikaz.
     *
     * @return true ak je parameter platny prikaz, false inak.
     */
    public boolean jePrikaz(String nazov) {
        for (int i = 0; i < Prikazy.PLATNE_PRIKAZY.length; i++) {
            if (Prikazy.PLATNE_PRIKAZY[i].equals(nazov)) {
                return true;
            }
        }
        // ak algoritmus dosiahne tento bod, parameter nie je platny prikaz
        return false;
    }

    /**
     * Prevezne prikaz a vykona ho.
     *
     * @param prikaz prikaz, ktory ma byt vykonany.
     * @return true ak prikaz ukonci hru, inak vrati false.
     */
    public boolean vykonajPrikaz(Hrac hrac, Prikaz prikaz) {
        boolean jeKoniec = false;

        if (prikaz.jeNeznamy()) {
            System.out.println("Nerozumiem, co mas na mysli...");
            return false;
        }

        String nazovPrikazu = prikaz.getNazov();

        switch (nazovPrikazu) {
            case "pomoc":
                this.vypisNapovedu();
                return false;
            case "chod":
                this.chodDoMiestnosti(hrac, prikaz);
                return false;
            case "ukonci":
                return this.ukonciHru(prikaz);
            case "preskumaj":
                this.preskumajMiestnost(hrac);
                return false;
            case "zdvihni":
                this.zdvihniPredmet(hrac, prikaz);
                return false;
            case "profil":
                this.zobrazProfilHraca(hrac);
                return false;
            case "poloz":
                this.polozPredmet(hrac, prikaz);
                return false;
            case "pouzi":
                this.pouziPredmet(hrac, prikaz);
                return false;
            case "mapa":
                this.vykresliMapu();
                return false;
            case "oslov":
                this.oslovNpc(hrac, prikaz);
                return false;
            default:
                return false;
        }
    }

    // implementacie prikazov:
    /**
     * Vypise text pomocnika do terminaloveho okna. Text obsahuje zoznam moznych
     * prikazov.
     */
    private void vypisNapovedu() {
        System.out.println("Zabludil si. Si sam. Tulas sa po fakulte.");
        System.out.println();
        System.out.println("Mozes pouzit tieto prikazy:");
        System.out.print("  ");
        for (String prikaz : Prikazy.PLATNE_PRIKAZY) {
            System.out.print(" " + prikaz);
        }
        System.out.println();
    }

    /**
     * Vykona pokus o prechod do miestnosti urcenej danym smerom. Ak je tym
     * smerom vychod, hrac prejde do novej miestnosti. Inak sa vypise chybova
     * sprava do terminaloveho okna.
     */
    private void chodDoMiestnosti(Hrac hrac, Prikaz prikaz) {
        if (!prikaz.maParameter()) {
            // ak prikaz nema parameter - druhe slovo - nevedno kam ist
            System.out.println("Chod kam?");
            return;
        }

        String smer = prikaz.getParameter();

        try {
            hrac.chodSmerom(smer);
        } catch (NenasielSaVychodException ex) {
            System.out.println("Nenasiel sa vychod");
        } catch (NedaSaVstupitException ex) {
            System.out.println("Do miestnosti sa neda vstupit");
        }
    }

    /**
     * Ukonci hru. Skotroluje cely prikaz a zisti, ci je naozaj koniec hry.
     * Prikaz ukoncenia nema parameter.
     *
     * @return true, if this command quits the game, false otherwise.
     * @return true, ak prikaz konci hru, inak false.
     */
    private boolean ukonciHru(Prikaz prikaz) {
        if (prikaz.maParameter()) {
            System.out.println("Ukonci, co?");
            return false;
        } else {
            return true;
        }
    }

    private void preskumajMiestnost(Hrac hrac) {
        Miestnost aktualna = hrac.getAktualnaMiestnost();
        aktualna.vypisZoznamObjektov();
    }

    private void zdvihniPredmet(Hrac hrac, Prikaz prikaz) {
        try {
            hrac.zdvihniPredmet(prikaz.getParameter());
        } catch (NenasielSaPredmetException ex) {
            System.out.println("Taky predmet nikde nevidis");
        } catch (NemanipulovatelnyPredmetException ex) {
            System.out.println("Nejde ani len pohnut");
        }
    }

    private void zobrazProfilHraca(Hrac hrac) {
        hrac.zobrazProfil();
    }

    private void polozPredmet(Hrac hrac, Prikaz prikaz) {
        try {
            hrac.polozPredmet(prikaz.getParameter());
        } catch (NemanipulovatelnyPredmetException ex) {
            System.out.println("To nemas");
        } catch (NenasielSaPredmetException ex) {
            System.out.println("Je v tvojom inventari ako prilepeny");
        }
    }

    private void pouziPredmet(Hrac hrac, Prikaz prikaz) {
        try {
            hrac.pouziPredmet(prikaz.getParameter());
        } catch (NenasielSaPredmetException ex) {
            System.out.println("To nemas");
        }
    }

    private void vykresliMapu() {
        System.out.println(
                  "                                                          +--------------+\n"
                + "                                                          |              |\n"
                + "                                                          |              |\n"
                + "                                                          |              |\n"
                + "                                                          |              |\n"
                + "                                                          |  Chodba A    | +-------------+\n"
                + "                                              +---------+ |              | |             |\n"
                + "                                              |         | |              | |    WC       |\n"
                + "                       +---------------+      |   RA6   +<+              | |             |\n"
                + "                       |               |      |         | |              | +-------------+\n"
                + "                       |    MHD        |      +---------+ +X-----^-------+\n"
                + "                       |               |                 XXX     |\n"
                + "                       +---------------+               XXX       |\n"
                + "                               ^             +--------XX+        |\n"
                + "                               |             |  Jedalen |        |\n"
                + "   +-------------+      +------------------->-----------+        |\n"
                + "   |             |      |               |                        |\n"
                + "   |  Domov      +<-----+   Parkovisko  |                +---------------+  +-------------+\n"
                + "   |             |      |               +---------------->               |  |             |\n"
                + "   +-------------+      +---------------+                |    Vratnica   +->+    IC       |\n"
                + "                                                         |               |  |             |\n"
                + "                                                         +---------------+  +-------------+\n"
                + "                                                                 |\n"
                + "                                                           +-----v-----+\n"
                + "                                                           |           |\n"
                + "                                                           |           |\n"
                + "                                                           |           |\n"
                + "                                                           |  Chodba B |\n"
                + "                                                           |           |\n"
                + "                                                           |           |\n"
                + "                                                           +-----------+\n"
        );
    }

    private void oslovNpc(Hrac hrac, Prikaz prikaz) {
        hrac.oslovNpc(prikaz.getParameter());
    }
}
