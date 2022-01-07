import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.io.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.io.FileNotFoundException;

class Diagnostikk{

   static private int antallTraaderTilFlett; 
   static int SubLengde = 3; 

    public static void main(String[] args){
        Scanner antTrader = new Scanner(System.in);
        ArrayList<String> filerMedData = new ArrayList<>(); 
        System.out.println("Velkommen til virus-diagnostiseringsprogrammet.");

        try{
            Scanner leser = 
                new Scanner(new File("metadata.csv"));	//Kan endres til input fra bruker.
        		
            leser.nextLine(); //Hopper over første linje i metadataFilen
            
            while(leser.hasNextLine()) {
    
                filerMedData.add(leser.nextLine());	    //Legger alle linjene i en ArrayList
            }
            
            leser.close();
        } catch (FileNotFoundException e) {System.out.println(e); }
        
        Beholder Syk = new Beholder();  
        Beholder Frisk = new Beholder(); 
        
       
        antallTraaderTilFlett = Integer.parseInt(args[0]); //Ved oppstart av programmet kan brukeren legge inn oensket antall

        CountDownLatch count1 = new CountDownLatch(filerMedData.size()); 

       
        for(int index = 0; index < filerMedData.size(); index ++){
            

            VirusRun Vir =  new VirusRun(SubLengde, filerMedData.get(index), Syk, Frisk, count1); //Oppretter traader til innlesing,
                                                                                                  // en traad per fil.
           Thread T = new Thread(Vir);
           T.start(); 
        } 

        try{
            count1.await();
        } catch (InterruptedException e){

        }

        CountDownLatch count2 = new CountDownLatch(antallTraaderTilFlett);
        

       for(int r = 0; r < antallTraaderTilFlett/2; r++){
           new Thread(new BeholderRun(Syk, count2)).start(); //Fletter sammen hashmappene til det kun er en igjen i hver av beholderne
       }      

       for(int t = 0; t < antallTraaderTilFlett/2; t++){  
           new Thread(new BeholderRun(Frisk, count2)).start();
       }

       try{
           count2.await();
       
       } catch (InterruptedException e){
           System.out.println(e);
       }
      
       System.out.println("Programmet er ferdig med innlesing og fletting");

       //Skrive ut en enkel statisktisk test 
       HashMap<String, SubSekvens> friskeHashMap = Frisk.TaUtEn();
        for (SubSekvens subSyk : Syk.TaUtEn().values()) {
            for (SubSekvens subFrisk : friskeHashMap.values()) {
                if (subSyk.toString().equals( subFrisk.toString() )){

                    int differanse = subSyk.hentAntall() - subFrisk.hentAntall();
                    if (differanse >= 5){
                        System.out.println(subSyk.toString() +" : syke["+ subSyk.hentAntall() +"]  friske["+ 
                        subFrisk.hentAntall() +"]  diff["+ differanse +"]");
                    }
                }
            }
        }
    }
}
