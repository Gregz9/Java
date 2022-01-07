public class SubSekvens {

    private String subsekvens;
    private int antall;

    public SubSekvens(String subsekvens) {
        this.subsekvens = subsekvens;
        this.antall = 1;
    }

    public void leggTilAnt(int okning) {
        antall += okning;
    }

    public int hentAntall() {
        return antall;
    }

    public String subS() {
        return subsekvens;
    }

    @Override
    public String toString() {
        return subsekvens;
    }
}