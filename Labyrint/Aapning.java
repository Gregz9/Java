import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList; 

class Aapning extends HvitRute{

    String tekst = "VEG";

 Aapning(int Kol, int Rad, Labyrint lab){
        super(Kol, Rad, lab);
    }

    char tilTegn(){
        return tegn;
    }
    
    @Override
    void gaa(ArrayList<Tuppel> Sti){

        ArrayList<Tuppel> nySti = new ArrayList<>(Sti); 
        nySti.add(this.tuppel);
        labyrint.hentAlleStier().add(nySti);
        
        if(labyrint.hentKode().equals("detaljert")){
            System.out.println("Aapning: " + hentKolKoord() + "," + hentRadKoord());
        } 
   
    }

    @Override
    void initGUI(){
        super.initGUI();
        setBorder(BorderFactory.createLineBorder(Color.black));
        setBackground(Color.red);

        class Velger implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(kolKoor + " " + radKoor); 
            
            }
        }
        addActionListener(new Velger());
    }
    
}