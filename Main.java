import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) throws InterruptedException {

     int numeroFilosofos = 5;

     var listFilosodos = new ArrayList<filosofos>();

     // criando filosofos na lista

     for (int i = 0;i < numeroFilosofos;i++){ 
         if(i ==0){
             listFilosodos.add(new filosofos("filosofo "+i,new Semaphore(1),new Semaphore(1)));
         }else if(i == numeroFilosofos - 1){
             listFilosodos.add(new filosofos("filosofo "+i,listFilosodos.get(i-1).getGarfoDireito(),listFilosodos.get(0).getGarfoEsquerdo()));

         }else{
             listFilosodos.add(new filosofos("filosofo "+i,listFilosodos.get(i-1).getGarfoDireito(),new Semaphore(1)));
         }
     }

     
     //Executando durante 60s
     Thread.sleep(10000);


     for(int k = 0; k < numeroFilosofos; k++) {
         listFilosodos.get(k).setBreakPoint(false);
     }

        System.out.println("-- Resultados --");
        for(int j=0; j < numeroFilosofos; j++){
            System.out.println("Filosofo "+listFilosodos.get(j).getName()+" comeu: "+listFilosodos.get(j).getComeu());
        }

    }
}
