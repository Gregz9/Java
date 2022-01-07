 abstract public class Legemiddel{ //Oppretter Legemiddel som en abstrakt klasse, slik at det ikke 
                                   // skal være mulig å opprette instanser av klassen. 
    protected String navn; 
    private static int iD = 1; //Oppretter forskjellige variabler og tekststrenger som skal brukes 
    private int minID;         // i klassen konstruktør som parametre. Oppretter her også en statisk heltalls-variabel
    protected int pris;        // for å bruke den til å tildele hvert nye instans en unik ID.
    protected double mgAvVirkestoff; 

    Legemiddel(String navn, int pris, double mgAvVirkestoff){ //Deklarer konstruktøren med parametre
        this.navn = navn; 
        this.pris = pris;
        this.mgAvVirkestoff = mgAvVirkestoff; 
        this.minID = iD; 
        iD ++;
    }
    //Metoden returnener minID som er den unike heltalls-ID tildelt hvert instanse av klassen Legemiddel og dets subklasser.
    public int hentID(){
        return minID;
    }
    //Metodenen returnerer legemiddelets navn.
    public String hentNavn(){
        return navn;
    }
    //Metoden returnerer prisen på legemiddelet.
    public int hentPris(){
        return pris; 
    }
    //Metoden returnerer mengeden av virkestoff i legemiddelet oppgitt i miligram.
    public double hentVirkestoff(){
        return mgAvVirkestoff;
    }
    //Metoden modifiserer/endrer verdien på konstruktør-parameteren pris, og gir dermed instansen av Legemiddel en ny pris.
    public void settNyPris(int pris){
        this.pris = pris; 
    }
    //Metoden overskriver klassen toString(), det gjør at vi kan enkelt skrive ut all tilgjengelig info om objektet.
    public String toString(){
       return ("Legemiddel: " + navn + "\nPris: " + pris + "\nAntall mg av virkestoff: " + mgAvVirkestoff);
  }


}