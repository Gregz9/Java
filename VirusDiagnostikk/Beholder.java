import java.util.HashMap;
import java.util.ArrayList;
import java.util.concurrent.locks.*;
import java.util.concurrent.CountDownLatch;

@SuppressWarnings("unchecked")
class Beholder {
    
    private ArrayList<HashMap<String,SubSekvens> > HashMaps = new ArrayList<>(); 
    private int antall = 0;
 
    private ReentrantLock laas = new ReentrantLock();
    Condition Tom = laas.newCondition();
    Condition IkkeTom = laas.newCondition(); 
       				
    public int antall() {
        return antall;
    }

    public HashMap<String, SubSekvens> TaUtEnRef(){ //Tar ut bare en referanse til siste hashmap, brukes etter fletting 
            return HashMaps.get(0);
        
    }

    public HashMap<String, SubSekvens> TaUtEn(){

            antall--;   //Ingen laaser paa denne metoden, da den ikke brukes av traader
            return HashMaps.remove(0);
    }

    public void leggTil(HashMap<String,SubSekvens> hInn) {
        laas.lock();
        try{
            antall++;
            HashMaps.add(hInn); 
            if(antall > 1){
                IkkeTom.signalAll();    //leggTil singaliserer naar det er nok i beholderen til at en traad kan igjen hente ut elementer
            }   
        }
        finally{ laas.unlock(); } 
    }
     //For aa hente ut to elementer samtidig, legges de foerst inn i en ArrayList 
    public  ArrayList<HashMap<String, SubSekvens>> TaUtTo() throws InterruptedException{ //Metoden TaUtTo maa endres, den foerer til feil i hovedprogrammet
        laas.lock(); 
        try{
            ArrayList<HashMap<String, SubSekvens>> hash = new ArrayList<>();
        if(antall < 2){
                Tom.signalAll();  //Dersom antallet er mindreenn 2, så signaliserer metoden om det, og traadene venter
                IkkeTom.await();
            }
        for(int i = 0; i<2; i++){
            antall--;
            hash.add(HashMaps.remove(0));
        }
            return hash; 
            
        }finally{
            
            laas.unlock();
    }
}

    //Metoden tar Arraylist med hashmapper som parameter
    public static HashMap<String,SubSekvens> flett (ArrayList<HashMap<String, SubSekvens>> hash) throws InterruptedException{
       
        HashMap<String, SubSekvens> FlettetHash = hash.remove(0); 
        HashMap<String, SubSekvens> HashSomSkalFlettes = hash.remove(0); 

            for(String sub1:  HashSomSkalFlettes.keySet()) {

                SubSekvens S = HashSomSkalFlettes.get(sub1);
                        
                if(FlettetHash.containsKey(sub1)){   //Sjekker om den andre hashmappen inneholder noen av elementene til den første
                    S.leggTilAnt(FlettetHash.get(sub1).hentAntall()); //oeker antallet hvis ja
                }
            
            FlettetHash.put(sub1, S); //Hvis subsekvensen ikke er lik noen av de andre, så legges den bare til 
        }			 							
            return 	FlettetHash;
        
    }

    public void flettFunksjon() throws InterruptedException{
        laas.lock(); 
        try{
            while (antall > 1) {  
            leggTil(flett(TaUtTo()));
            }
     } finally{
            
            laas.unlock();
        }
    }

}
