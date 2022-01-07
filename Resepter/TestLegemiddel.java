class TestLegemiddel{ //Oppretter klassen TestLegemiddel 

    public static void main(String[] args){

        Vanedannende legemiddel1 = new Vanedannende("Nostrix", 259, 25.50, 7);
        Narkotisk legemiddel2 = new Narkotisk("Hydragenum", 450, 560.50, 10);  //Oppretter en instanse av hver av subklassene, hvorav en instanse blir oppretter med en peker av superklassen.
        Vanlig legemiddel3 = new Vanlig("Aether", 230, 750.00);
        Legemiddel legemiddel4 = new Narkotisk("Marihuana", 1500, 1200, 5);

        System.out.println(legemiddel1.hentID());  //Bruker så metoden hentID() for å sjekke om at hvert nye objekt for tildelt hvert sitt unike ID-nummer ved opprettelse. 
        System.out.println(legemiddel2.hentID());
        System.out.println(legemiddel3.hentID());
        System.out.println(legemiddel4.hentID());

        System.out.println("\n" + legemiddel2.hentNavn()); //Tester metoden hentNavn().
        System.out.println(legemiddel2.hentNarkotiskStyrke());//Tester metoden hentNarkotiskStyrke().

        System.out.println("\n" + legemiddel1.hentPris()); //Tester metoden hentPris().
        legemiddel1.settNyPris(399);  //Tester metoden settNyPris().
        System.out.println("Ny pris er: " + legemiddel1.hentPris()); //Tester igjen med metoden hentPris() for å se om metoden settNyPris() virkelig har endret prise.

        System.out.println(legemiddel4.hentVirkestoff());// Tester metoden hentVirkestoff().
        System.out.println("\n" + legemiddel4);
       
        }
}