import java.util.ArrayList; //Importerer ArrayList for bruk i klassen

class Node{ //Definerer klassen Node

 int minne; //Definerer nye heltall-variabler 
 int antPros; 


public Node(int pcMinne, int antallPros){ //Oppretter konstkruktør for klassen Node med to heltalls-parametre
minne = pcMinne;  //Setter heltallsvariabelen minne lik verdien til parameteren pcMinne i konsturktøren
antPros = antallPros; //Setter heltallsvariabelen antPros lik verdien til parameteren AntallPros i konsturktøren
}

public int hentMinne(){ //Denne metoden returnerer verdien på parameteren minne
  return minne;
}

public int hentAntPros(){ //Metoden returnerer verdien til parameteren antPros
  return antPros;
}

public boolean nokMinne(int paakrevdMinne){ //Ved å kalle på metoden av typen boolean sjekker metoden om noden har nok minne sammenlignet 
  //med parameteren paakrevdMinne
  if (paakrevdMinne <= minne){
    return true;
  }
    return false;
  }
}
