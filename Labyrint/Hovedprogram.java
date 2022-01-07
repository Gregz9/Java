import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

class Hovedprogram{
  
    public static void main(String[] args) {
        JFrame vindu = new JFrame("Labyrint"); //Oppretter selve program vinduet 

        JPanel S = new JPanel(); 
    
        vindu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //Oppretter "Kryss"-knappen for aa kunne lukke vinduet

        File fil = null; 
        Labyrint l = null;
            
        JFileChooser V = new JFileChooser();  //Oppretter en filvelger for aa kunne aksessere oenskede filer
       
        int resultat = V.showOpenDialog(null);
        if (resultat == JFileChooser.APPROVE_OPTION) {
        fil = V.getSelectedFile();   //Setter filen til det merkerte objektet i JFileChooser-en
        }
        if (resultat == JFileChooser.CANCEL_OPTION) {
            System.out.println("IKKE VALGT NOE FIL");
            throw new NullPointerException();
        } 

        try{
            l = new Labyrint(fil, "detaljert");  //Har lagt inn kodeordet som medfører valgfri utskrift i terminalen paa forhaand.
            }  catch (FileNotFoundException e) {  // Jeg kunne i teorien skrevet kode med en JTextField og hente kodeordet derifra, men valgte aa ikke gjoere det her
               System.out.printf("FEIL: Kunne ikke lese");
            }
        vindu.add(l, BorderLayout.CENTER);  //Legger til Labyrinten i programvinduet
        l.initGUI(); //Initierer GUI i labyrinten
     
        vindu.pack();  //Pakker alt sammen og gjoer programmet synlig for brukeren
        vindu.setVisible(true);
    }
}