
import java.util.ArrayList; //Importerer klassen ArrayList 

class Rack{  //Definerer klassen Rack()

ArrayList<Node> rack; //Lager en Arraylist fylt med Node-objekter

public Rack(){  //Skriver konstruktøren til klassen Rack(). Denne inneholder kun en parameter, objekt av typen ArrayList som holder 
  //på Node-objekter

rack = new ArrayList<Node>();

}

public void settInn(Node node){ //Lager en metode som setter inn et Node-objekt inn i listen 
  rack.add(node);
}

public int getAntNoder(){  //Metoden returnerer antallet Node-objekter som befinner seg i listen
  return rack.size();
  }

public int antProsessorer(){ // Metoden itererer over listen av Node-objekter og returnerer totalt antall Prosessorer fra alle Node-objektene
  int sum = 0;
  for (Node noder : rack){
    
    sum += noder.hentAntPros(); //Bruker metoden hentAntPros() fra Node-klassen som returnerer antallet prosessorer i et enkelt 
    //Node-objekt
  }
  return sum;
}

public int noderMedNokMinne(int paakrevdMinne){ //Metoden bruker nokMinne(paakrevdMinne) fra Node-klassen for å sjekke hvor mange Node-objekter 
  // med nok minne det finnes i listen
  int antall = 0;
  for (int i = 0; i < rack.size(); i++){
    Node node = rack.get(i);
    if (node.nokMinne(paakrevdMinne)){
      antall += 1;
    }
  }
  return antall;
}

}
