import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public abstract class Rute extends JButton{  //Skriver klassen Rute som en abstrakt subklasse JButton

    char tegn;
    int radKoor, kolKoor; 
    ArrayList<Rute> naboer;
    boolean besoekt; //Boolsk flagg til bruk for merkering av ruter
    Labyrint labyrint;
    Tuppel tuppel; 
  

    Rute(int Kol, int Rad, Labyrint lab){
        radKoor = Rad; 
        kolKoor = Kol;
        labyrint = lab;
        naboer = new ArrayList<>();
        tuppel = new Tuppel(Kol, Rad); //Utvidet konstruktoeren med Tuppel
        besoekt = false; //Hver rute starter med den boolske verdien lik false.
    }

    char tilTegn(){
        return tegn;
    }

    int hentKolKoord(){
        return kolKoor;
    }

    int hentRadKoord(){
        return radKoor;
    }

    ArrayList<Rute> hentNaboer(){
        return naboer; 
    }

    boolean sjekkOmBesoekt(){
        return besoekt; 
    }

    void settBesoekt(){
        besoekt = true; 
    }

    void settIkkeBesoekt(){
        besoekt = false; 
    }
    
    void Vest() {

    }

    void finnNaboer() throws IndexOutOfBoundsException{  //Denne metoden itererer over Labyrinten og tildeler hver 
        int kol = kolKoor;                              // rute deres naboer
        int rad = radKoor;
        Rute vest, oest, nord, soer = null; 

        if(kol -1 == -1){ return; }
        else{ vest = labyrint.hentRute(kol-1, rad); }
        
        if (kol +1 == labyrint.hentAntKolon()){ return; }
        else{ oest = labyrint.hentRute(kol+1, rad); }

        if (rad -1 == -1){ return; }
        else{ nord = labyrint.hentRute(kol, rad-1); }

        if (rad +1 == labyrint.hentAntRader()){ return; }
        else{ soer = labyrint.hentRute(kol, rad+1); }

        naboer.add(vest); naboer.add(oest); naboer.add(nord); naboer.add(soer);

        for(int i = 0; i < naboer.size(); i++){ //Til slutt fjenres elementer med referanseverdi lik "null"
            if(naboer.get(i) == null){
                naboer.remove(i);
            }
        }
        for(int i = 0; i < naboer.size(); i++){ //Ruter som er veger fjernes ogsaa, slik at programmet slipper aa gaa paa disse 
            if(naboer.get(i).tilTegn() == '#'){
                naboer.remove(i);
            }
        }
    }
        
    abstract void gaa( ArrayList<Tuppel> Sti); //Metoden gaa er abstarkt, og overskrives i subklassene


    void finnUtvei(){
        ArrayList<Tuppel> Sti = new ArrayList<>();  //FinnUtvei bruker gaa metoden til a finne en utvei. 
        gaa(Sti);  
          
    }

     void initGUI(){
         setSize(10, 10);  //Initierer GUI, Oppretter med andre ord en grafisk representasjon av ruten
     }
    
    void merkerVei(char C) { //Metoden som i begynnelsen ble brukt til aa merkere utveien
        setText(""+C); 
        tegn = C;
    }

}