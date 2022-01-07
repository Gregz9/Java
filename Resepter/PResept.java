class PResept extends HvitResept{ //Oppretter subklassen PResept som utvidelse av klassen HvitResept.

    private String farge; //Deklarer en variablen som skal brukes i konstruktøren til klassen.

    PResept(Legemiddel legemiddel, Lege utskrivendelege, int pasientID){  //Skriver konstruktøren og bruker spuer() til å arve parametere fra superklassen. 
        super(legemiddel, utskrivendelege, pasientID, 3);
        this.farge = "Hvit P-resept"; //Deklarerer parameteren farge.
    }

    //Bruker her @Override til å overskrive metoden i Superklassen, og super. for å arve metoden fra superklasen.
    //Metoden gjør akkurat det samme som metoden med samme signatur i superklassen Resept.
    @Override
    public int hentID(){
        return super.hentID();
    }

    //Bruker her @Override til å overskrive metoden i Superklassen, og super. for å arve metoden fra superklasen.
    //Metoden gjør akkurat det samme som metoden med samme signatur i superklassen Resept.
    @Override
    public Legemiddel hentLegemiddel(){
        return super.hentLegemiddel();
    }

    // @Override til å overskrive metoden i Superklassen, og super. for å arve metoden fra superklasen.
    //Metoden gjør akkurat det samme som metoden med samme signatur i superklassen Resept.
    @Override
    public Lege hentLege(){
        return super.hentLege();
    }

    //\ @Override til å overskrive metoden i Superklassen, og super. for å arve metoden fra superklasen.
    //Metoden gjør akkurat det samme som metoden med samme signatur i superklassen Resept.
    @Override
    public int hentReit(){
        return super.hentReit(); 
    }

    //Bruker her @Override til å overskrive metoden i Superklassen, og super. for å arve metoden fra superklasen.
    //Metoden gjør akkurat det samme som metoden med samme signatur i superklassen Resept.
    @Override
    public boolean bruk(){
        return super.bruk();
    }  

    // Override for å overskrive den abstrakte metoden i Superklassen. Metoden returnerer informasjonen lagret
    // i parameteren farge. .
    @Override
    public String farge(){
        return farge;
    }

    //Overskriver den abstrakte metoden fra Superklassen og metoden med samme signatur fra HvitResept. For PResept gjelder et fast avslag på 108, men 
    // det er ikke mulig å betale mindre enn 0. Metoden returner prisen på respeten etter at rabatten er trukket. Hvis prisen er lik rabatten eller mindre
    // returnerer metoden 0.
    @Override
    public int prisAaBetale(){
        int nyPris = legemiddel.hentPris()-108;
        if(nyPris > 0){
            return nyPris;
        }
        else {
            return 0;
        }
    }

    //Bruker her @Override til å overskrive metoden i Superklassen, og super. for å arve metoden fra superklasen.
    //Metoden gjør akkurat det samme som metoden med samme signatur i superklassen Resept.
    @Override
    public String toString(){
        return super.toString();
    }
}