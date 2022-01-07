import java.util.ArrayList; 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
class SortRute extends Rute{
   
    char tegn = '#'; 

    SortRute(int Kol, int Rad, Labyrint lab){
        super(Rad, Kol, lab);

    }

    char tilTegn(){
        return tegn;
    }

    @Override
    void gaa(ArrayList<Tuppel> Sti){
        return; 
    }

    @Override
    void initGUI(){
        super.initGUI();
        setBorder(BorderFactory.createLineBorder(Color.black));
        setBackground(Color.black);

        class Velger implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(kolKoor + " " + radKoor);
                return; 
            }
        }
        addActionListener(new Velger());
    }

}
