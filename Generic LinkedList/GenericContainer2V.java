import javax.swing.text.FlowView.FlowStrategy;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings("unchecked")
public class GenericContainer2V<T extends Comparable<T>> implements Iterable<T> {

    Node<T> first ; 
    Node<T> last ; 
    int size; 

    class Node<T extends Comparable<T>> {  //Need this inner class Node to store data, and retrieve it
        T data; 
        Node prev; 
        Node next;

        Node(T data) {
            this.data = data;
        }

        public T getData() {
            return data;
        }
    }

    //Since this class implements Iterable<T>, we need an inner class called Iterator in addition to
    //the Node-class

    class GenericContainer2VIterator<T extends Comparable<T>> implements Iterator<T>{
        Node thisOne; 
        
        GenericContainer2VIterator(GenericContainer2V<T> element){
           // thisOne = new Node<>(element.first.data);
           thisOne = element.first;
        }

        @Override 
        public boolean hasNext(){
            Node<T> point = thisOne;
            return point != null; 
        }

        @Override
        public T next(){
            Node<T> point = thisOne; 
            if(point.data == null){    
                return null; 
            }
            point = point.next; 
            return point.data;
        }
    }

    public GenericContainer2VIterator<T> iterator() {
        return new GenericContainer2VIterator<T>(this);
    }
    //Methods of the main class start here
    public void add(T I) {

        Node<T> new_Node = new Node<>(I);
        Node<T> iter = first;

        if(first == null) { 
            first = new_Node; 
            last = new_Node;
            size++;
            return; 
        } //If the value is less than or equal to zero, than we put it at the beginning of the container. 
        
        else if(first.data.compareTo(I) > 0) { 
            new_Node.next = first; 
            last = first; 
            first = new_Node; 
            last.prev = first;  
            size++;
            return; 
        }

        else{ 
            while(iter.next != null && iter.next.data.compareTo(I) <= 0) { 
                iter = iter.next;
            }

            if(iter.next == null) { //Adding this if-statement simply to define the last element when adding new data to the 
                iter.next = new_Node;                              //container
                last = new_Node; 
                last.prev = iter; 
                size++;
                return;
            }
            else{
                new_Node.next = iter.next; 
                new_Node.prev = iter;
                iter.next = new_Node;
                size++;
                return;
            } 
        }
    }

    public void add(int pos, T D) {

        int counter = 0; 
        Node<T> new_Node = new Node<>(D);
        Node<T> iter = first; 

        if(pos < 0 || pos >= size) {
            throw new InvalidIndex(pos);
        }

        else if(pos == 0) {
            new_Node.next = first; 
            first.prev = new_Node; 
            first = new_Node; 
            size++;
            return; 
        }
        else if(pos == size -1) {
            while(iter.next != null) {
                iter = iter.next;
            }
            new_Node.next = iter.next; 
            iter.next = new_Node; 
            last = new_Node; 
            last.prev = iter; 
            size++;
            return;
        }
        
         else {
            while(iter != null) {
                if(counter == pos-1 && counter < size-1) {
                    new_Node.next = iter.next; 
                    iter.next = new_Node; 
                    new_Node.prev = iter; 
                    size++;
                    return;
                }
            counter++;
            iter = iter.next;
            }
        }
    }

    public void set(int pos, T D) {
      //  Node<T> new_Node = new Node<>(D);
        Node<T> iter = first; 
        int counter = 0;

        if(pos < 0 || pos >= size) {
            throw new InvalidIndex(pos);
        }                                       //Need to fix problem with the previous value of the new Node 
        else if(pos == 0) {                     // as it doesn't work right. Must be located within the last 
         /*   new_Node.next = first.next;         // block of code within method, the "else"-statement
            new_Node.prev =  first.prev; */
            first.data = D; 
            return;
        }
        else if(pos == size-1) {
            while(iter.next != null) {
                iter = iter.next;
            }
         /*   new_Node.next = iter.next; 
            new_Node.prev = iter.prev; */
            iter.data = D; 
            last = iter; 
            return;
        }
        else{
            while(iter.next != null) {  //Could have used a for-loop.
                if(counter == pos && counter < size-1) {
                    iter.data = D;
                    return;
                }
            counter++;
            iter = iter.next;
            }
        }
    }

    public T getFirst() {
        T data = first.data; 
        return data;
    }

    public T getLast() {
        T data = last.data;
        return data;
    }
    
    public T removeFirst() {
        if(first != null){
            T data = first.data; 
            first = first.next; 
            size--;
            return data;
        }
        else{
            throw new EmptyContainer();
        }
    }

    public T get(int pos) {
        Node<T> iter = first;
        int counter = 0;

        if(pos < 0 || pos > size-1) {
            throw new InvalidIndex(pos);
        }

        else {
            for(int i = 0; i < pos; i++) {
                iter = iter.next; 
            }
            return iter.data;
        }
    }

    public T remove(int pos) {
        T data = null;
        Node<T> iter = first;
        int counter = 0;

        if(pos < 0 || pos > size-1) {
            throw new InvalidIndex(pos);
        }

        else if(pos == 0) {
            data = first.data;
            first = first.next; 
            size--;
        }

        else if(pos == size -1) {
            while(iter.next != null) {
                iter = iter.next; 
            }
            data = iter.data; 
            iter = iter.prev; 
            last = iter; 
            size--;
        }

        else{ 
            while(iter.next != null) {
                if(counter == pos-1 && counter < size-1) {
                    data = (T) iter.next.data; 
                    iter.next = iter.next.next;
                    size--;
                   // return data;
                }
            iter = iter.next;
            counter++;
            }
        }
        return data;
    }

    public ArrayList<T> remove(T Data) {
        int[] indexes = getIndexes(Data); 
        ArrayList<T> objects = new ArrayList<>();
        
        for(int index = 0; index < indexes.length-1; index++) {
            objects.add(remove(indexes[index]));
        }
        return objects;
    }

    public T clear() {
        while(size != 0) {
            removeFirst();
        }
        return null;
    }

    public int getIndex(T Data) {
        int index = 0;
        Node<T> iter = first; 

        while(iter.next != null) {
            if(iter.data == Data) {
                return index;
            }
            index++; 
            iter = iter.next;
        }
        return index;
    }

    public int[] getIndexes(T Data) {
        int index = 0; 
        int indexOFArray = 0; 
        Node<T> iter = first; 
        int[] indexes = new int[checkNumberOfOccurances(Data)];

        while(iter.next != null) {
            if(iter.data == Data) {
                indexes[indexOFArray] = index;
                indexOFArray++;
            }
            index++; 
            iter = iter.next; 
        }
        return indexes;
    }

    public int checkNumberOfOccurances(T Data) {
        Node<T> iter = first; 
        int counter = 0; 

        while(iter.next != null) {
            if(iter.data == Data) {
                counter++;
            }
            iter = iter.next;
        }
        return counter;
    }

    public void removeDuplicates(T Data) {
        int[] indexes = getIndexes(Data);

        for(int index = 1; index < indexes.length-1; index++) {
            remove(indexes[index]);
        }
    }
}