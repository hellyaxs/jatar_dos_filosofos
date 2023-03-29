public class Philosopher implements Runnable {

    private final Object leftFork;
    private final Object rightFork;

    public Philosopher(Object leftFork, Object rightFork) {
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }

    private void doAction(String action) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " " + action);
    }

    @Override
    public void run() {
        try {
            while (true) {
                // Filósofo pensa
                doAction(": Thinking");
                Thread.sleep((long) (Math.random()*100));
                synchronized (leftFork) {
                    // Filósofo pega o garfo da esquerda;
                    synchronized (rightFork) {
                        // Filósofo pega o garfo da direita e come
                        doAction(": Picked up both forks - eating");
                        Thread.sleep(1500);
                        // Filósofo termina de comer e coloca os garfos de volta na mesa
                    }
                    // Filósofo coloca o garfo da esquerda de volta na mesa
                    doAction(": Returning to thinking.");
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }
    }

    public static void main(String[] args) {
        int numPhilosophers = 5;
        Philosopher[] philosophers = new Philosopher[numPhilosophers];
        Object[] forks = new Object[numPhilosophers];

        for (int i = 0; i < numPhilosophers; i++) {
            forks[i] = new Object();
        }

        for (int i = 0; i < numPhilosophers; i++) {
            Object leftFork = forks[i];
            Object rightFork = forks[(i + 1) % numPhilosophers];

            philosophers[i] = new Philosopher(leftFork, rightFork);

            Thread t = new Thread(philosophers[i], "Philosopher " + (i + 1));
            t.start();
        }
    }
}
