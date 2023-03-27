import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        mesa mesacompartilhada = new mesa();
     int numeroFilosofos = 4;

     var listFilosodos = new ArrayList<filosofos>();

     for (int i = 0;i<=numeroFilosofos;i++){ // criando filosofos na lista
         if(i ==0){
             listFilosodos.add(new filosofos("filosofo "+i,mesacompartilhada,new Semaphore(1),new Semaphore(1)));
         }else if(i == numeroFilosofos ){
             listFilosodos.add(new filosofos("filosofo "+i,mesacompartilhada,listFilosodos.get(i-1).getGarfoDireito(),listFilosodos.get(0).getGarfoEsquerdo()));

         }else{
             listFilosodos.add(new filosofos("filosofo "+i,mesacompartilhada,listFilosodos.get(i-1).getGarfoDireito(),new Semaphore(1)));
         }
     }

    }
}
