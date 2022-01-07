class TestResept{ //Oppretter klassen TestResept.

    public static void main(String[] args){
        
        Vanedannende legemiddel1 = new Vanedannende("Nostrix", 259, 25.50, 7); //Oppretter en instanse av hver av subklassene fra Legemiddel-hierarkiet.
        Narkotisk legemiddel2 = new Narkotisk("Hydragenum", 450, 560.50, 10);
        Vanlig legemiddel3 = new Vanlig("Aether", 230, 750.00);
        Legemiddel legemiddel4 = new Narkotisk("Marihuana", 1500, 1200, 5);

        Lege lege1 = new Lege("Aleksander Steenshaven");            //Oppretter en instanse av Lege og en av Spesialist.
        Spesialist lege2 = new Spesialist("Ole Elvestad", "O39E4LD");

        Resept resept1 = new HvitResept(legemiddel3, lege1, 140585, 3); //Oppretter en instanse av hver av subklassene fra Resept-hierarikiet.
        BlaaResept resept2 = new BlaaResept(legemiddel4, lege2, 210797, 1);
        MilitaerResept resept3 = new MilitaerResept(legemiddel2, lege2, 492978, 2);
        PResept resept4 = new PResept(legemiddel1, lege1, 974635);

        System.out.println(resept1.hentID()); //Brruker metoden hentID() til å teste at hver av instansene av Resept for en unik heltalls-ID.
        System.out.println(resept2.hentID());
        System.out.println(resept3.hentID());
        System.out.println(resept4.hentID());
        
        System.out.println("\n" + resept1.farge()); //Tester om metoden farge() returnerer fargen på resepten.
        System.out.println(resept2.farge());
        System.out.println(resept3.farge());
        
        System.out.println("\n" + resept3.hentReit()); //Tester metoden hentReit() returnerer riktig verdi for reit.
        resept3.bruk();  //Bruker så bruk() metoden for å se om resepten kan brukes på nytt eller ikke.
        System.out.println(resept3.hentReit());
        resept3.bruk();

        System.out.println("\n" + resept3.prisAaBetale()); //Tester om metoden prisAaBetale() returnerer riktige verdier når det er ulik rabatt på forskjellige resepter.
        System.out.println(resept4.prisAaBetale());

        System.out.println("\n" + resept1.hentLege()); //Tester om metoden hentLege() returnerer lege-objektet.

        System.out.println("\n" + resept4.hentLegemiddel()); //Tester om metoden hentLegemiddel() returener legemiddel-objektet.

        System.out.print("\n" + resept3); //Tester om toString() metoden som er overskrevet i klassen, returnerer all informasjon fra konstruktøren i form av streng.

    }
}