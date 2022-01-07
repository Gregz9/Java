class BlaaResept extends Resept{ //Oppretter subklassen HvitResept som utvidelse av superklassen Resept.

private String farge; //Deklarer en variablen som skal brukes i konstruktøren til klassen.

    BlaaResept(Legemiddel legemiddel, Lege utskrivendelege, int pasientID, int reit){ //Skriver konstruktøren og bruker spuer() til å arve parametere fra superklassen. 
        super(legemiddel, utskrivendelege, pasientID, reit);
        this.farge = "Blaa";  //Deklarerer parameteren farge.
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

    //Begynner med å overskrive den abstrakte metoden i superklassen, og deretter bruker metoden til Legemiddel klassen hentPris(),
    // slik at vi kan returnere prisen for legemiddelet. Det er 75 % rabatt på legemidler med blaaresept.
    @Override
    public int prisAaBetale(){
        int pris = legemiddel.hentPris();
        long pris1 = Math.round(pris*0.25);
        int nyPris = (int) pris1; //må caste nyPris variabelen til int, da Math.round gir variabler av klassen long.
        return nyPris;
    }

    
    //Bruker her @Override til å overskrive metoden i Superklassen, og super. for å arve metoden fra superklasen.
    //Metoden gjør akkurat det samme som metoden med samme signatur i superklassen Resept.
    @Override
    public String toString(){
        return super.toString() + ("\n" + farge);
    }
}

