class MilitaerResept extends HvitResept{ //Oppretter subklassen MilitaerResept som utvidelse av klassen HvitResept.

    private String farge; //Deklarer en variablen som skal brukes i konstruktøren til klassen.

    MilitaerResept(Legemiddel legemiddel, Lege utskrivendelege, int pasientID, int reit){ //Skriver konstruktøren og bruker spuer() til å arve parametere fra superklassen. 
        super(legemiddel, utskrivendelege, pasientID, reit);
        this.farge = "Hvit Militaer-resept"; //Deklarerer parameteren farge.
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

    //Bruker her @Override til å overskrive metoden i Superklassen, og super. for å arve metoden fra superklasen.
    //Metoden gjør akkurat det samme som metoden med samme signatur i superklassen Resept.
    @Override
    public Lege hentLege(){
        return super.hentLege();
    }

    //Bruker her @Override til å overskrive metoden i Superklassen, og super. for å arve metoden fra superklasen.
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

    //Bruker Override for å overskrive den abstrakte metoden i Superklassen. Metoden returnerer informasjonen lagret
    // i parameteren farge.    
    @Override
    public String farge(){
        return farge;
    }

    //Bruker her @Override til å overskrive metoden i Superklassen, og super. for å arve metoden fra superklasen.
    //MilitaerResept har 100 % avslag, det betyr at metoden returnerer 0.
    @Override
    public int prisAaBetale(){
        return (legemiddel.hentPris()*0);
    }
    
   //Bruker her @Override til å overskrive metoden i Superklassen, og super. for å arve metoden fra superklasen.
    //Metoden gjør akkurat det samme som metoden med samme signatur i superklassen Resept.
    @Override
    public String toString(){
        return super.toString();
    }
}