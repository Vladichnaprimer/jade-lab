package jade.core.behaviours;

public class BookAgentBehaviour extends Behaviour implements Runnable {
    private int step;
    private final int MAX_THREAD_AMOUNT = 3;
    @Override
    public void action() {
        switch (step){
            case 0:
                step++;
                break;
            case 1:
                step++;
                break;
            case 2:
                step++;
                break;
        }
    }

    @Override
    public boolean done() {
        return step == 3;
    }

    public void checkIfStepsAmount(){
        while (Thread.activeCount() < MAX_THREAD_AMOUNT) {
            Thread thread1 = new Thread(new BookAgentBehaviour());
            thread1.start();
            Thread thread2 = new Thread(new BookAgentBehaviour());
            thread2.start();
            try {
                thread1.join();
                thread2.join();
                done();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
        action();
    }
}