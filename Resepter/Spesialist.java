class Spesialist extends Lege implements Godkjenningsfritak{ //Oppretter subklassen Spesialist som en utvidelse av Lege. I Tillegg blir det implementert et grensesnitt Godkjenningsfritak.
    
    private String kontrollID; //Deklarerer en streng KontrollID. 

    public Spesialist(String navn, String kontrollID){ //Skriver kontruktøren og bruker super() for å arve parametere fra Superklassen.
        super(navn);
        this.kontrollID = kontrollID;
    }
    
    //Metoden er opprinnelig hentet fra grensesnittet Godkjenningsfritak, og dens funksjon er å returnere strengen kontrollID.
    public String hentKontrollID(){
        return kontrollID;
    }

    //Overskriver metoden toString(). Denne metoden gjør akkurat det samme som metoden med samme signatur i Superklassen.
    @Override
    public String toString(){
        return super.toString() + ("\nKontrollID: " + kontrollID);
    }
    
}