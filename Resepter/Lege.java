class Lege implements Godkjenningsfritak{ //Oppretter klassen Lege.

    protected String navn; //Deklarerer en streng navn osm skal brukes i konstruktøren.

    Lege(String navn){ //Skriver konstruktøren med parameteren.
        this.navn = navn; 
    }
    
    //Metoden returnerer navn.
    public String hentNavn(){
        return navn;
    }
    

    //Denne metoden trenger ikke å befinne seg i klassen, men vi kan da sjekke om instansen av Lege-klassen har en kontrollID.
    public String hentKontrollID(){
        String kontrollID = null;
        return kontrollID;
    }
    
    //Metoden overskriver  programmets orginale toString() metode og returnerer all info fra konstruktøren som streng.
    public String toString(){
        return ("\nNavn: " + navn);
    }

}