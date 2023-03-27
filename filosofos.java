import java.util.Random;
import java.util.concurrent.Semaphore;

public class filosofos  implements  Runnable{

    String name;

    Semaphore garfoEsquerdo;
    Semaphore garfoDireito;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Semaphore getGarfoEsquerdo() {
        return garfoEsquerdo;
    }

    public void setGarfoEsquerdo(Semaphore garfoEsquerdo) {
        this.garfoEsquerdo = garfoEsquerdo;
    }

    public Semaphore getGarfoDireito() {
        return garfoDireito;
    }

    public void setGarfoDireito(Semaphore garfoDireito) {
        this.garfoDireito = garfoDireito;
    }

    public filosofos(String name, Semaphore garfoEsquerdo, Semaphore garfoDireito) {
        this.name = name;
        this.garfoEsquerdo = garfoEsquerdo;
        this.garfoDireito = garfoDireito;
        new Thread(this).start();
    }


    @Override
    public void run() {
        while(true){
           var pegouE =  garfoEsquerdo.tryAcquire();
            if(pegouE){
               var pegouD = garfoDireito.tryAcquire();
               if(pegouD){
                   try {
                       this.comer();
                       garfoEsquerdo.release();
                       garfoDireito.release();
                   } catch (InterruptedException e) {
                       throw new RuntimeException(e);
                   }
               }else{
                   garfoEsquerdo.release();
               }
            }else{
                try {
                    this.pensando();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }

        }
    }

    private void pensando() throws InterruptedException {
        var timerun = new Random().nextInt(10)+1;
        System.out.println("filoso "+name+" esta pensando,no tempo de:"+timerun+"s");
        Thread.sleep(timerun*1000);
    }

    private void comer() throws InterruptedException {
        var timerun = new Random().nextInt(10)+1;
        System.out.println("filoso "+name+" esta comendo,no tempo de:"+timerun+"s");
        Thread.sleep(timerun*1000);
    }
}
