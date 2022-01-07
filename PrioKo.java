class Prioko<T> {

    private class Node { 
        T data; 
        int prioritet;
        Node neste = null; 
        Node forrige = null; 

        Node(T inn, int prioritet) {
            data = inn; this.prioritet = prioritet;
        }
    }

    public void settInn(T a) {
        Node nyNode = new Node(a, a.tid); 

        if(forste == null) {
            forste = nyNode; 
        }
        else if(nyNode.tid >= siste.tid) {
            siste.forrige = siste;
            siste = nyNode; 
        }
        else {
            Node peker = siste; 
            //Invariant peker.tid < nyNode.tid; 
            while(peker.forrige != null && peker.forrige.tid < nyNode.tid) {
                peker = peker.forrige; 
                if(peker.tid == nyNode.tid) {
                    nyNode.forrige = peker.forrige; 
                    nyNode.forrige = peker;
                }
            }
        }
    }

    public T hentUt() {
        if(forste == null) { return null;}

        T dataUt = forste.data; 
        forste = forste.neste; 
        return dataUt;
    }

} 