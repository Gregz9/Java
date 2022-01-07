class Vanlig extends Legemiddel{ //Oppretter subklassen Vanlig som en utvidelse av superklassen Legemiddel.

   //Subklassen har ingen nye egenskaper sammenlignet med Superklasen.

    Vanlig(String navn, int pris, double mgAvVirkestoff){ //Oppretter konsturkør med parametere som arves fra superklassen etter bruk av super().
        super(navn, pris, mgAvVirkestoff);

    }
    
    //Metoden Overskriver samme metoden med samme signatur i superklassen.
    @Override
    public String toString(){
        return super.toString(); //Metoden returner objektet i form av streng, dvs. All informasjon lagret i parameterne blir returnert som strenger via metoden. 
    }
   
}