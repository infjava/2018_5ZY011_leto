/**
 * Trieda Hra je hlavna trieda aplikacie "World of FRI".
 * "World of FRI" je velmi jednoducha textova hra - adventura. 
 * Hrac sa moze prechadzat po niektorych priestoroch - miestnostiach fakulty. 
 * To je v tejto verzii vsetko. Hru treba skutocne zancne rozsirit,
 * aby bola zaujimava.
 * 
 * Ak chcete hrat "World of FRI", vytvorte instanciu triedy Hra (hra) 
 * a poslite jej spravu hraj.
 * 
 * Hra vytvori a inicializuje vsetky potebne objekty:
 * vytvori vsetky miestnosti, vytvori parser a zacne hru. Hra tiez vyhodnocuje
 * a vykonava prikazy, ktore vrati parser.
 * 
 * @author  Michael Kolling, David J. Barnes
 * @version 2006.03.30
 * @author  lokalizacia: Lubomir Sadlon, Jan Janech
 * @version 2012.02.21
*/
 
public class Hra  {
    private Parser parser;
    private Miestnost aktualnaMiestnost;
    
    /**
     * Vytvori a inicializuje hru.
     */
    public Hra() {
        this.vytvorMiestnosti();
        this.parser = new Parser();
    }

    /**
     * Vytvori mapu hry - miestnosti.
     */
    private void vytvorMiestnosti() {
        // vytvorenie miestnosti
        Miestnost mhd = new Miestnost("mhd - Stale stoji. Ujo sofer niekam odbehol");
        Miestnost domov = new Miestnost("domov - Vsade dobre, na FRI najlepsie");
        Miestnost parkovisko = new Miestnost("parkovisko - Pokojne tu stoja auta. Treba byt ticho a nevyplasit ich");
        Miestnost jedalen = new Miestnost("jedalen - Mnam mnam, tu to vonia");
        Miestnost vratnica = new Miestnost("vratnica - Tu sedi pani vratnicka");
        Miestnost ra = new Miestnost("chodba ra - Dlha chodba");
        Miestnost rb = new Miestnost("chodba rb - Tmava chodba");
        Miestnost ra6 = new Miestnost("ra006 - Labacik plny laboratornych (teda ehm vlastne pocitacovych) mysi");
        Miestnost wc = new Miestnost("wc - Smradocek, ale teplucko");
        Miestnost ic = new Miestnost("ic - Informacne centrum, prave prebiehaju volby");
        
        mhd.nastavVychod("juh", parkovisko);
        
        domov.nastavVychod("vychod", parkovisko);
        
        parkovisko.nastavVychod("sever", mhd);
        parkovisko.nastavVychod("zapad", domov);
        parkovisko.nastavVychod("vychod1", jedalen);
        parkovisko.nastavVychod("vychod2", vratnica);
        
        jedalen.nastavVychod("zapad", parkovisko);
        jedalen.nastavVychod("hore", ra);
        
        vratnica.nastavVychod("zapad", parkovisko);
        vratnica.nastavVychod("sever", ra);
        vratnica.nastavVychod("juh", rb);
        vratnica.nastavVychod("vychod", ic);
        
        ra.nastavVychod("dole", jedalen);
        ra.nastavVychod("zapad", ra6);
        ra.nastavVychod("vychod", wc);
        ra.nastavVychod("juh", vratnica);
        
        rb.nastavVychod("sever", vratnica);
        
        ra6.nastavVychod("vychod", ra);
        
        wc.nastavVychod("zapad", ra);
        
        ic.nastavVychod("zapad", vratnica);

        this.aktualnaMiestnost = parkovisko;  // startovacia miestnost hry
    }

    /**
     *  Hlavna metoda hry.
     *  Cyklicky opakuje kroky hry, kym hrac hru neukonci.
     */
    public void hraj() {            
        this.vypisPrivitanie();

        // Vstupny bod hlavneho cyklu.
        // Opakovane nacitava prikazy hraca
        // vykonava ich kym hrac nezada prikaz na ukoncenie hry.
                
        boolean jeKoniec;
        
        do {
            Prikaz prikaz = this.parser.nacitajPrikaz();
            jeKoniec = this.vykonajPrikaz(prikaz);
        } while (!jeKoniec);
        
        System.out.println("Maj sa fajn!");
    }

    /**
     * Vypise privitanie hraca do terminaloveho okna.
     */
    private void vypisPrivitanie() {
        System.out.println();
        System.out.println("Vitaj v hre World of FRI!");
        System.out.println("World of FRI je nova, neuveritelne nudna adventura.");
        System.out.println("Zadaj 'pomoc' ak potrebujes pomoc.");
        System.out.println();
        this.aktualnaMiestnost.infoOMiestnosti();
    }


    /**
     * Prevezne prikaz a vykona ho.
     * 
     * @param prikaz prikaz, ktory ma byt vykonany.
     * @return true ak prikaz ukonci hru, inak vrati false.
     */
    private boolean vykonajPrikaz(Prikaz prikaz) {
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
                this.chodDoMiestnosti(prikaz);
                return false;
            case "ukonci":
                return this.ukonciHru(prikaz);
            default:
                return false;
        }
    }

    // implementacie prikazov:

    /**
     * Vypise text pomocnika do terminaloveho okna.
     * Text obsahuje zoznam moznych prikazov.
     */
    private void vypisNapovedu() {
        System.out.println("Zabludil si. Si sam. Tulas sa po fakulte.");
        System.out.println();
        System.out.println("Mozes pouzit tieto prikazy:");
        System.out.println("   chod ukonci pomoc");
    }

    /** 
     * Vykona pokus o prechod do miestnosti urcenej danym smerom.
     * Ak je tym smerom vychod, hrac prejde do novej miestnosti.
     * Inak sa vypise chybova sprava do terminaloveho okna.
     */
    private void chodDoMiestnosti(Prikaz prikaz) {
        if (!prikaz.maParameter()) {
            // ak prikaz nema parameter - druhe slovo - nevedno kam ist
            System.out.println("Chod kam?");
            return;
        }

        String smer = prikaz.getParameter();

        // Pokus o opustenie aktualnej miestnosti danym vychodom.
        Miestnost novaMiestnost = this.aktualnaMiestnost.getVychod(smer);

        if (novaMiestnost == null) {
            System.out.println("Tam nie je vychod!");
        } else {
            this.aktualnaMiestnost = novaMiestnost;
            novaMiestnost.infoOMiestnosti();
        }
    }

    /** 
     * Ukonci hru.
     * Skotroluje cely prikaz a zisti, ci je naozaj koniec hry.
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
}
