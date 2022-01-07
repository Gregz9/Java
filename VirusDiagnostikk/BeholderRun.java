
import java.util.HashMap;
import java.util.ArrayList;
import java.util.concurrent.locks.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.*;

class BeholderRun implements Runnable{

    Beholder beholder; 
    CountDownLatch count;  

    BeholderRun(Beholder beholder, CountDownLatch count){
        this.beholder = beholder; 
        this.count = count;
    }

    public void run(){
        try{
                           //Trenger ikke noe while-løkke/sjekk (definert i metoden i monitoren).
        beholder.flettFunksjon();
        
        count.countDown();
    } catch (InterruptedException e) {
        System.out.println(e);
    }
    }

}