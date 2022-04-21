
/**
 * Basic example of creating and running threads.
 * 
 * @author amit
 */
public class Dance extends Thread
{

    public Dance(String s) {
        super(s);
    }

    public void run() {
        for (int i = 0; i < 20; i++) {
            System.out.println("This is the " + this.getName() + " thread.");
            /* this.yield(); */
        }
    }

    public static void main(String args[]) {
        new Dance("Dab").start();
        new Dance("Wobble").start();
        new Dance("Shuffle").start();
        new Dance("Sprinkler").start();
        new Dance("Fik-shun").start();
    }
}

