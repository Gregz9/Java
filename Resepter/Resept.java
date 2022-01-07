abstract public class Resept{ //Oppretter superklassen Resept som en abstrakt klasse, slik at det ikke skal være mulig å opprette instanser av den. 

    protected Legemiddel legemiddel; //Deklarerer variabler som skal brukes i klassens konstruktør som parametere. Merk at legemiddel referer til objekt av klassen Legemiddel og Lege 
    protected  Lege ustrkivendeLege;  //referer til en instans av Lege.
    private static int iD = 1; 
    private int minID;        
    protected int pasientID; 
    protected int reit;

    Resept(Legemiddel legemiddel, Lege ustrkivendeLege, int pasientID, int reit){ //Oppretter klassens kontruktør med parametere.
        this.legemiddel = legemiddel;
        this.ustrkivendeLege = ustrkivendeLege; 
        this.pasientID = pasientID; 
        this.minID = iD;
        iD ++;
        this.reit = reit;  
    }
 
    //Metoden returnerer en helltalls-ID som er unik for hver objekt av klassen.
    public int hentID(){
        return minID;
    }

    //Metoden returnerer legemiddel parameteren. Merk at ettersom vi har overskrevet metoden toString()
    // i klassen Legemiddel, vil objektet returneres som en streng.
    public Legemiddel hentLegemiddel(){
        return legemiddel;
    }
    
    //Metoden returner uskrivendeLege som er en instanse av klassen Lege. Ettersom metoden toString() også
    // er overskrevet i klassen Lege, returneres denne som en streng også.
    public Lege hentLege(){
        return ustrkivendeLege;
    }

    //Metoden returnerer tallet reit som står for antallet ganger vi kan bruke resepten.
    public int hentReit(){
        return reit; 
    }
    
    //Metoden sjekker først og fremst om vi kan bruke resepten, eller om den er brukt opp. Dersom respeten
    // fortsatt kan benyttes, trekkes det fra 1 på reit og metoden returner true. Hvis reit = 0, skrives det ut 
    // en beskjed om at resepten er brukt opp og metoden returnerer false.
    public boolean bruk(){
        if (reit != 0){
            reit -= 1;
        }
        if (reit == 0){
            System.out.println("Resepten er brukt opp.");
            return false;
        }
    
    return true;
    }

    //Abstrakt metode som ikke kan benyttes som er tom. Denne vil overskrives i subklassene og returnere
    // fargen på resepten.
    abstract public String farge();


    //Dette er en abstrakt klasse, noe som betyr at denne ikke kan brukes. Denne bil overskrives av en metode
    // med samme signatur i subklassene og vil returnere prisen for resepten. 
    abstract public int prisAaBetale();


    //Denne metoden overskriver klassens orginale toString() og returner all informasjon om objektet i form 
    // av streng. 
    public String toString(){
        return (legemiddel + "\n" + ustrkivendeLege + "\nPasientID: " + pasientID + "\nReit: " + reit);
    }

}