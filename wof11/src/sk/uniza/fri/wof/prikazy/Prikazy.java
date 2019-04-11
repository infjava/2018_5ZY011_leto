package sk.uniza.fri.wof.prikazy;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
    private static final int SAVE_MAGIC_NUMBER = 468763546;
    private static final int SAVE_VERSION = 0;

    // konstantne pole nazvov prikazov
    private static final String[] PLATNE_PRIKAZY = {
        "chod", "ukonci", "pomoc", "preskumaj", "zdvihni",
        "profil", "poloz", "pouzi", "mapa", "oslov", "zaznamenaj",
        "zopakuj", "save", "load"
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
    public boolean vykonajPrikaz(Hrac hrac, Parser parser, Prikaz prikaz) {
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
                this.oslovNpc(hrac, parser, prikaz);
                return false;
            case "zaznamenaj":
                this.zaznamenajMakro(parser, prikaz);
                return false;
            case "zopakuj":
                this.zopakujMakro(parser, prikaz);
                return false;
            case "save":
                this.ulozSave(hrac, prikaz);
                return false;
            case "load":
                this.nacitajSave(hrac, prikaz);
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

    private void oslovNpc(Hrac hrac, Parser parser, Prikaz prikaz) {
        hrac.oslovNpc(parser, prikaz.getParameter());
    }

    private void zaznamenajMakro(Parser parser, Prikaz prikaz) {
        System.out.print("Zadaj pocet:");
        int pocet = parser.citajInt();
        
        parser.zapisDoSuboru(prikaz.getParameter(), pocet);
    }

    private void zopakujMakro(Parser parser, Prikaz prikaz) {
        try {
            parser.nacitajZoSuboru(prikaz.getParameter());
        } catch (FileNotFoundException ex) {
            System.out.println("Nenaslo sa makro");
        }
    }

    private void ulozSave(Hrac hrac, Prikaz prikaz) {
        try (DataOutputStream zapisovac = new DataOutputStream(new FileOutputStream(new File(prikaz.getParameter() + ".sav")))) {
            zapisovac.writeInt(Prikazy.SAVE_MAGIC_NUMBER);
            zapisovac.writeInt(Prikazy.SAVE_VERSION);
            hrac.ulozSave(zapisovac);
        } catch (IOException ex) {
            System.out.println("Nastala divna chyba, kontaktuj programatora.");
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void nacitajSave(Hrac hrac, Prikaz prikaz) {
        try (DataInputStream citac = new DataInputStream(new FileInputStream(new File(prikaz.getParameter() + ".sav")))) {
            int magicNumber = citac.readInt();
            if (magicNumber != Prikazy.SAVE_MAGIC_NUMBER) {
                System.out.println("Toto nie je save subor.");
                return;
            }
            
            int verzia = citac.readInt();
            if (verzia > Prikazy.SAVE_VERSION) {
                System.out.println("Save bol vytvoreny v prilis novej verzii hry");
                System.out.println("Nedokazem nacitat!");
                return;
            }
            
            hrac.nacitajSave(citac, verzia);
            
            hrac.getAktualnaMiestnost().infoOMiestnosti();
        } catch (FileNotFoundException ex) {
            System.out.println("Nenasiel sa prislusny save subor.");
        } catch (IOException ex) {
            System.out.println("Nastala divna chyba, kontaktuj programatora.");
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
}
