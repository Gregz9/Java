
class Tuppel{

    char tegn = '+';
    int rad, kolonne; 

    Tuppel(int kolonne, int rad){
        this.rad = rad; 
        this.kolonne = kolonne; 
    }

    @Override 
    public String toString(){
        return (kolonne + "," + rad);
    }

    int hentRad() {
        return rad;
    }

    int hentKolonne() {
        return kolonne;
    }
}