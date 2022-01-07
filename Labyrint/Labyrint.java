import java.util.ArrayList; 
import java.io.File; 
import java.util.Scanner; 
import java.awt.*; 
import java.awt.event.*; 
import javax.swing.*; 
import java.io.*;

class Labyrint extends JPanel{  //Setter labyrint til aa vaere en subklasse av JPanel
                                // Alle metodene fra JPanle kan naa brukes direkte i labyrint-klassen
    Rute[][] labyrint; //Labyrint opprettes i utgangspunktet som to-dimensjonal Array
    
    File fil;
    int antRader, antKolon, Indeks1, Indeks2; 
    ArrayList<ArrayList<Tuppel>> AlleStier; 
    String kodeOrd;
    ArrayList<Tuppel> kortest;
    JLabel Utveier = new JLabel();  //Oppretter to JLabels som skal brukes til å vise informasjon om antallet utveier fra en gitt rute, og vise utveinr. 
    JLabel UtveiNr = new JLabel();

    Labyrint(File fil, String kode) throws FileNotFoundException{ // Grunnet bruk av metoden TegnLabyrint i konstruktoeren, maa det kastes en Exception 
        this.fil = fil;
        labyrint = TegnLabyrint(fil);
        AlleStier = new ArrayList<>(); // ArrayList med alle mulige utveier fra ruten brukeren trykker paa. 
        kortest = new ArrayList<>();  //Den korteste utveien fra labyrinten. Denne ArrayListen for sitt innhold fra metoden hentKortesteUtvei. 
        kodeOrd = kode;  //et kodeord som maa legges inn for at uskrift som alle steg fra ruten til aapningen skal vises
        Indeks1 = 0; //Indeks som skal brukes til iterering over Array
       
    }

    void initGUI() {  //Oppretter metoden som vil initiare GUI for labyrinten
        
        setLayout(new BorderLayout()); // Setter oensket Layout for JPanel-et
        JPanel lab = new JPanel();  
        JPanel knapper = new JPanel();  //Oppretter JPanel-er som jeg vil igjen legge inn i Labyrinten.
        JPanel Info = new JPanel();     //Hvert JPanel blir tildelt bestemet type innhold
        
        knapper.setPreferredSize(new Dimension(200, 200));
        Info.setPreferredSize(new Dimension(200, 100)); //Setter oensket stoerrelse paa enkelte elementer i JPanel-et
        lab.setLayout(new GridLayout(antKolon, antRader));
        lab.setPreferredSize(new Dimension(antKolon*20, antRader*20)); 
        for(int K = 0; K < antKolon; K++){ 
            for(int R = 0; R < antRader; R++){ 
                
                lab.add(labyrint[K][R]); //Legger til hver rute i labyrint-panelet 
                labyrint[K][R].initGUI(); //Initierer GUI-delen i hver rute i layrint-panelet
            }
        }

        Utveier.setText("Antall utveier:    ");  
        UtveiNr.setText("UTVEINR:    "); 
        JButton Neste = new JButton("Neste utvei");
        Neste.setPreferredSize(new Dimension(100, 40));
        Neste.addActionListener(new VisNesteUtvei());    //Oppretter de forskjellige knappene og legger til deres funksjoner
        knapper.add(Neste);

        JButton Forrige = new JButton("Forrige utvei");
        Forrige.addActionListener(new VisForrigeUtvei());
        Forrige.setPreferredSize(new Dimension(100, 40));
        knapper.add(Forrige);

        JButton Kort = new JButton("Korteste utvei");
        Kort.addActionListener(new VisRaskesteUtvei());
        Kort.setPreferredSize(new Dimension(100, 40));
        knapper.add(Kort);

        Info.add(Utveier);
        Info.add(UtveiNr);  
        add(knapper, BorderLayout.WEST);
        add(lab, BorderLayout.CENTER);  //Legger til slutt JPanel-er i hoved JPanel-et
        add(Info, BorderLayout.SOUTH);
    }

     Rute[][] TegnLabyrint(File fil) throws FileNotFoundException {  //Metoden som tegner Labyrinten. 
                                                                     //Ettersom metoden bruker en scanner og File, maa den kaste et unntak
        Scanner lesFil = new Scanner(fil);

        String[] maal = lesFil.nextLine().split(" ");
        antRader = Integer.parseInt(maal[0]); //Henter info om antallet kolonner og rader fra foerste linje i filen
        antKolon = Integer.parseInt(maal[1]);

        labyrint = new Rute[antKolon][antRader]; //Legger inn heltalls-variablene hentet fra filen i Arrayet
        
        char hvit = '.'; //For aa kunne opprette riktig innstase av hver Rute i Arrayet, trenger programmet disse tegnene
        char sort = '#';
        
        for(int rad = 0; rad < antRader; rad++){
            String linje = lesFil.nextLine();   //Lar programmet saa gaa videre gjennom filen   
            for(int kol = 0; kol < antKolon; kol++){
            
                if (kol > 0 && kol < antKolon-1 && rad > 0 && rad < antRader-1){ //Hvis ruten ikke er paa en av ytterkantene og representeres med tegnet '.' i filen, blir instanse av HvitRute opprettet
                    if(linje.charAt(kol) == hvit){ 
                        labyrint[kol][rad] = new HvitRute(rad, kol, this);
                    }
                
                    else if(linje.charAt(kol) == sort){  //Dersom ruten representeres med tegnet '#', blir instanse av SortRute oprettet 
                    labyrint[kol][rad] = new SortRute(rad, kol, this);
                    }
                 }
                 if (rad == 0 || rad == antRader -1 || kol == 0 || kol == antKolon -1){ //Dersom ruten ligger paa ytterkanten og er merkert med '.' i filen, vil instanse av Aapning oprettes 
                     
                    if(linje.charAt(kol) == hvit){
                        labyrint[kol][rad] = new Aapning(rad, kol, this);
                    }
                
                    else if(linje.charAt(kol) == sort){ //Ellers blir alle de andre rutene tildelt roller som instanser av SortRute 
                        labyrint[kol][rad] = new SortRute(rad, kol, this);
                    }
                }
            }
        }

        for(int rad = 0; rad < antRader; rad++){
            for(int kol = 0; kol < antKolon; kol++){  //Naar alle Rute-objekter er opprettet, maa de faa kunnskap om sine naboer
                labyrint[kol][rad].finnNaboer();
            }
        }
        
        lesFil.close();
        return labyrint;  //Avslutter metoden med a lukke filen
    }

    void skrivUt(){
        for(int i = 0; i < labyrint.length; i++){
            for(int j = 0; j < labyrint[i].length; j++){
                System.out.print(labyrint[i][j].tilTegn() + " ");  //Metoden soim skriver ut labyrintem, dersom GUI ikke oenskes aa bli brukt 
            }
            System.out.println();
        }
    }

    Rute hentRute(int kolonne, int rad){  //Metode som lar brukeren hente en bestemt rute ved hjelp av dens koordinater
        return(labyrint[kolonne][rad]);  
    }

    int hentAntRader(){   //Metode som returner rad-koordinaten til en rute 
        return antRader;
    }
    int hentAntKolon(){ //Metode som returner kolonne-koordinaten til en rute 
        return antKolon;
    }

    ArrayList<ArrayList<Tuppel>> finnUtveiFra(int kolonne, int rad){
        AlleStier.clear();  //For at det skal være mulig aa finne alle utveier fra hver rute, maa Arraylisten AlleStier bli toemt for hver gang programmet finner 
        this.hentRute(kolonne, rad).finnUtvei();                                              //utvei fra oensket rute. Dette fordi det ellers ville tatt med utveier til den forrige ruten
        hentKortesteUtvei();                                                                   //bruker har trykket paa. Noe som ville foert til flere problemer.
                             //For senere bruk i porgrammer maa korteste utvei hentes rett etter at alle utveier har blitt funnet og lagret. 
        Utveier.setText("Antall utveier:    " + AlleStier.size());
        Indeks1 = 0;
        System.out.println("Utvei " + Indeks1);
        UtveiNr.setText("UTVEINR:    " + (Indeks1+1));  
        merkerVei(AlleStier.get(0));
        return AlleStier; 
    }

    ArrayList<ArrayList<Tuppel>> hentAlleStier(){
        return AlleStier;
    }

    String hentKode(){
        return kodeOrd;  //Metode som skal hente kodeordet
    }
    
    ArrayList<Tuppel> hentKortesteUtvei(){
        skjulVei(kortest);  //Metoden som henter den korteste utvei maa foerst skjule den forrige korteste utveien 
        kortest.clear();    //Deretter slettes innholdet i Arraylisten, slik at en ny utvei kan lagres 
        if(AlleStier.get(0) != null){
            
            kortest = AlleStier.get(0);

            for(int i = 0; i < AlleStier.size(); i++){
                ArrayList<Tuppel> nyKort1 = AlleStier.get(i);
                if(nyKort1.size() < kortest.size()){
                    kortest = nyKort1;
                }
            }
            return kortest;
        }
        return null;
    }

    void merkerVei(ArrayList<Tuppel> lis) {
        if(AlleStier.get(0) != null){
        for( int i = 0; i < AlleStier.size(); i++) {
            ArrayList<Tuppel> lis1 = AlleStier.get(i);  //Metoden som merkerer veien, setter bakgrunnsfargen til hvit foerst, slik at alle utveier blir hvite
                for(Tuppel t: lis1){
                  hentRute(t.hentKolonne(), t.hentRad()).setBackground(Color.white);
                }
            }
        }
        for(Tuppel l2 : lis) { //Deretter fargelegger metoden den oenskede veien
            hentRute(l2.hentKolonne(), l2.hentRad()).setBackground(Color.red);
        }

    }

    void skjulVei(ArrayList<Tuppel> lis) {  //metoden skjulVei fargelegger bagkrunnen av ruten hvit, slik at den blir skjult og ikke vises
        for(Tuppel l2 : lis) {
            hentRute(l2.hentKolonne(), l2.hentRad()).setBackground(Color.white);
        }
    }

    ArrayList<Tuppel> NesteVei(int I) {  //NesteVei henter bare neste element i ArrayList-en over alle utveier 
        return AlleStier.get(I);
    }

    class VisRaskesteUtvei implements ActionListener {  //Oppretter en indre-klasse som implementerer grensesnittet ActionListener
        @Override                                       //Overskriver actionPerformed metoden, for aa kunne legge inn oensket funskjonalitet i knappen
        public void actionPerformed(ActionEvent e) {
            merkerVei(kortest);   
            Indeks1 = AlleStier.indexOf(kortest); //Denne knappen merkerer den raskeste utveien fra labyrinten
            UtveiNr.setText("UTVEINR:    " + (Indeks1+1));  //Indeksen settes til indeksen korteste utvei har i AlleStier 
        }
    }
    
    class VisNesteUtvei implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            
            if(AlleStier.size() == 0) {  //Denne metoden skal synliggjoere neste utvei naar vi trykekr paa knappen "Neste utvei"
                return;                  //Begynner med aa sjekke om AlleStier har noe innhold.
            }
            else if(AlleStier.size() != 0){
                if(Indeks1 < AlleStier.size()){ //om Indeks1 er mindre enn stoerrelsen av AlleStier, oeker indeks1 med 1
                    Indeks1 ++; 
                    if(Indeks1 == AlleStier.size()) { //sjekker tallverdien av indeks1, om den er lik stoerrelsen, settes indeks1 til 0
                        Indeks1 = 0;  
                    }
                    if(Indeks1 < AlleStier.size()) { //Hvis indeks1 fortsatt er mindre enn stoerrelsen av AlleStier, merkeres stien
                        UtveiNr.setText("UTVEINR:    " + (Indeks1+1));  
                        merkerVei(NesteVei(Indeks1));
                    }
                }
            }
        }
    }

    class VisForrigeUtvei implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) { //Denne metoden har oppbygging lik metoden for knappen "Neste utvei", men den viser forrige vei. 
                                                     // Det innebaerer at alle loekkene er skrevet med omvendt funksjonalitet. Det vil si at de starter paa slutten av ArrayListen 
            if(AlleStier.size() == 0) {              // og trekker 1 fra indeks1. Dersom indeksen naar AlleStier.size()-1, settes tallverdien av indeks1 til AlleSteir.size()-1
                return; 
            }
            else if(AlleStier.size() != 0){
                if(Indeks1 <= AlleStier.size()-1){
                    Indeks1 --; 
                    if(Indeks1 == -1) {
                        Indeks1 = AlleStier.size()-1;
                     
                        
                    }
                    if(Indeks1 >= 0) {
                        UtveiNr.setText("UTVEINR:    " + (Indeks1+1));  
                        merkerVei(NesteVei(Indeks1));
                    }
                }
            }

        }     
    }

}
    


