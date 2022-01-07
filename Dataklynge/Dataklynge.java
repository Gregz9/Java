import java.util.ArrayList; //Importerer ArrayList-klassen
import java.util.Scanner; //Importerer Scanner-klassen for lesing av filer
import java.io.File; //Importerer File-klassen for å deklarere et objekt som en fil 
import java.io.FileNotFoundException; //Importerer denne klassen for bruk under lesing av tekstfilen i konstruktøren


class Dataklynge{ //Definerer klassen Dataklynge
ArrayList<Rack> dataklynge; //Oppretter ArrayList-objekt som skal holde objekter av klassen Rack()
int nodPrRck; //Oppretter en heltallsvariabel som står for maksimalt antall noder per rack

public Dataklynge(String filnavn) throws FileNotFoundException{ //Ny konstruktør for klassen //throws FileNotFoundException er nødvendig under
  //bruk av fil-scanner

  dataklynge = new ArrayList<>(); //Definerer listen som skal holde på Rack-objekter som en ny ArrayList

  File fil = new File(filnavn); //Oppretter et objekt av klassen File som får navnet sitt fra parameter i klassen Dataklynge 
  // når et Dataklynge-objekt opprettes i hovedprogrammet 
  Scanner scanFil = new Scanner(fil); 
  nodPrRck = scanFil.nextInt(); //Setter variabelen lik første tallet/linjen i tesktfilen 

  while(scanFil.hasNextLine()){ //Oppretter en while-løkke for å lese fra filen

    int[] nodeInfo = new int[3]; //Lager en Array av heltallsvariabler med plass til tre variabler/objekter. 
    nodeInfo[0] = scanFil.nextInt(); //Setter første element lik det andre tallet i tesktfilen; Antallet av noder av bestemt type
    nodeInfo[1] = scanFil.nextInt(); //Setter det andre elementet lik det tredje tallet; Minnen noden har 
    nodeInfo[2] = scanFil.nextInt(); //Setter det tredje elementet lik det fjerde tallet; Antallet prosessorer i noden

    int antNoder = nodeInfo[0]; //Antall noder
    int minnePrNode = nodeInfo[1]; //Minne per node
    int antProsPrNode = nodeInfo[2]; //Antall prosessorer per node

    for (int i = 0; i < antNoder; i++){ //For-løkke som skaper antallet Node-objekter av bestemt type lik verdien i variabelen antNoder
      Node nyNode = new Node(minnePrNode, antProsPrNode);
      settInnNode(nyNode); //Setter de nye nodene inn i racks
    }
  }
scanFil.close(); //Lukker scanneren
}

public void settInnNode(Node nyNode){ //Metoden setter inn Node-objekter inn i racks med ledig plass, og hvis ingen racks med ledig plass 
  //finnes, så blir det oppretter et nytt Rack-objekt
 boolean ingenLedigeRacks = false; //Dersom det finnes et ledig rack, setter vi noden inn i den
 for (Rack r : dataklynge) {
 if (r.getAntNoder() < nodPrRck){
    r.settInn(nyNode);
    ingenLedigeRacks = true; //Bekrefter at vi har satt inn noden/nodene
 }
 }
 if(!ingenLedigeRacks) { //Dersom det ike finnes noe ledig rack
 Rack nyRack = new Rack(); //Oppretter et nytt Rack-object 
 nyRack.settInn(nyNode); //Setter noden inn i Rack-objektet 
 dataklynge.add(nyRack); //Setter noden inn i listen av racks
 }
}

public int antProsessorer(){ //Metoden returnerer det totale antallet prosessorer i dataklyngen
  int antPros = 0;
  for (int i = 0; i < dataklynge.size(); i++){
    Rack rack = dataklynge.get(i);
    antPros += rack.antProsessorer();
  }
  return antPros;
}

public int noderMedNokMinne(int paakrevdMinne){ //Metoden sjekker hvor mange noder med bestemt minnekapasitet det finnes og returner dette tallet
  int antNoder = 0;
  for (int i = 0; i < dataklynge.size(); i++){
    Rack rack = dataklynge.get(i);
    antNoder += rack.noderMedNokMinne(paakrevdMinne);
  }
  return antNoder;
}

public int antRacks(){ //Metoden returnerer antallet racks i dataklyngen
  int antRack = 0;
for(Rack racks : dataklynge){
  antRack++;
}
return antRack;
}
}
