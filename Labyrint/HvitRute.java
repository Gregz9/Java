import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class HvitRute extends Rute{  //HvitRute er en subklasse av Rute

    char tegn = '.';

    HvitRute(int Kol, int Rad, Labyrint lab){ super(Rad, Kol, lab); }

    char tilTegn(){ return tegn; }

    @Override  //Metoden som gjoer det mulig til aa videre til neste rute i labyrinten
    void gaa(ArrayList<Tuppel> Sti) {
        if(besoekt) { //For aa kunne gaa videre kan ikke ruten vaere besoekt. 
            return; 
        }
        ArrayList<Tuppel> nySti = new ArrayList<>(Sti); //Opprette en hard-copy av listen av alle utveier.
        nySti.add(this.tuppel); 
        besoekt = true; //Endrer til ''true'' for å merkere at ruten er besoekt.
        for(Rute nabo : this.naboer){
            if(nabo != this) {  //Hvis naboruten ikke er denne ruten, sa kan vi gaa videre. 
                nabo.gaa(nySti); 
            }
        }
        besoekt = false; //Setter veriden til ''false'' for at det skal vaere mulig aa finne alle utveier i sykliske labyrinter. 
    } //Dette kan også loeses uten bruk av et boolsk flagg

    @Override  //Overskriver metoden som initierer GUI
    void initGUI(){
    super.initGUI();
    setBorder(BorderFactory.createLineBorder(Color.black));  //Farger kantene paa ruten sort
  
    setBackground(Color.white); //Setter bakgrunnsfargen paa alle hvite ruter til hvit
        class Velger implements ActionListener {  //lar HvitRute implementere ActionListener. Dette gjoer at den som knapp for sin funksjon
            @Override
            public void actionPerformed(ActionEvent e) {
                for( int i = 0; i < labyrint.hentAlleStier().size(); i++) {
                    ArrayList<Tuppel> lis1 = labyrint.hentAlleStier().get(i);  //Ved markering av neste utvei, maa den tidligeres farge settes til hvit igjen.
                        for(Tuppel t: lis1){   
                          labyrint.hentRute(t.hentKolonne(), t.hentRad()).setBackground(Color.white);
                        }
                }
                labyrint.finnUtveiFra(kolKoor, radKoor);  //Trykk paa hvit rute finner utveien fra den.
            }  
        }
        addActionListener(new Velger()); //Legger til funksjonen i selve ruten. 
    }
}