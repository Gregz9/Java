import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;
import java.io.FileNotFoundException;

class VirusRun implements Runnable{
                
    int subLengde; 
    String line; 
    boolean syk; 
    Beholder Syk, Frisk; 
    Scanner leserScan;
    CountDownLatch count; 
    HashSet<String> SubSekvenser; 
    HashMap<String , SubSekvens> Individ;


                // line => en linje fra metadata.csv.
    VirusRun(int lengde, String line, Beholder Syk, Beholder Frisk, CountDownLatch count){
        subLengde = lengde;  
        this.line = line;
        this.Syk = Syk; 
        this.Frisk = Frisk; 
        this.count = count; 
 

    }
    @Override
    public void run(){

        try {
            String[] biter = line.split(","); // deler linjen i filnavn og boolean verdi.
            String filnavn = biter[0];
            syk = Boolean.parseBoolean(biter[1]);
            leserScan = new Scanner(new File(filnavn));
            SubSekvenser = new HashSet<>(); 
            Individ = new HashMap<>();
            String subsekvens, Immunreseptor; 

            while(leserScan.hasNextLine()) {
                Immunreseptor = leserScan.nextLine(); 
                for(int index = 0; index + subLengde < Immunreseptor.length(); index ++) {

                    subsekvens = Immunreseptor.substring(index, index + subLengde); //Deler i subsekvenser. 
                    SubSekvenser.add(subsekvens);
                }
            }
            leserScan.close();

            for(String Sub : SubSekvenser){
                Individ.put(Sub, new SubSekvens(Sub));
            }

            if(syk) {
                Syk.leggTil(Individ);
            }                          //Ut i fra boolean verdien tildelt filen, blir subsekvensene tilhoeherende
                                       // en person, lagt til enten Frisk eller Syk beholderen.
            else {
                Frisk.leggTil(Individ);
            }

            count.countDown();
        }
    
        catch (FileNotFoundException e){
            System.out.println(e);
        }   
     }

}