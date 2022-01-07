import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

class Hovedprogram{ //definerer klassen Hovedprogram


public static void main(String[] args)throws FileNotFoundException{ //throws FileNotFoundException må være med når vi bruker en fil-scanner 

Dataklynge abel = new Dataklynge("dataklynge4.txt"); //Oppretter et nytt Dataklynge-objekt 

System.out.println("Ny Dataklynge" + "\n");
System.out.println("Noder med minst 32 GB: " + abel.noderMedNokMinne(32)); //Skrivet ut antallet noder med minst 32 GB minne
System.out.println("Noder med minste 64 GB: " + abel.noderMedNokMinne(64));//Skrivet ut antallet noder med minst 64 GB minne
System.out.println("Noder med minst 128 GB: " + abel.noderMedNokMinne(128));//Skrivet ut antallet noder med minst 128 GB minne

System.out.println("Antall prosessorer: " + abel.antProsessorer()); //Skriver ut antallet prosessorer
System.out.println("Antall racks: " + abel.antRacks()); //Skriver ut antallet racks

}
}
