import java.util.Random;

/**
 * A simple program that simulates a widget factory. Each widget takes a
 * variable (random) amount of time to make. The student should convert this
 * program to be multithreaded and see how much faster it works.
 * 
 * @author amit
 * @author taylor
 */
public class FasterWidgetMaker extends Thread
{
    private Random generator = new Random();
    private final int RANGE = 100;
    private final int BASE = 100;
    private static final int INCORRECT_ARGUMENTS = 1;
    private int count;

    public FasterWidgetMaker(int count) {
        this.count = count;
    }


    public void run() {
        make();
    }


    public void make() {
        for (int i = 0; i < count; i++) {
            int time = generator.nextInt(RANGE) + BASE;
            // simulate variable amount of time to make one widget
            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Widget# " + i + " ready.");
        }
    }


    /**
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {

        if (args.length != 1) {
            System.out.println("Usage: java WidgetMaker <number of widgets>");
            System.exit(INCORRECT_ARGUMENTS);
        }
        int n = Integer.parseInt(args[0]);

        long startTime = System.currentTimeMillis();
        int numThreads = Runtime.getRuntime().availableProcessors();
        // int numThreads = 4;

        FasterWidgetMaker[] robotFactory = new FasterWidgetMaker[numThreads];
        for (int i = 0; i < numThreads - 1; i++) {
            robotFactory[i] = new FasterWidgetMaker(n / numThreads);
        }
        robotFactory[numThreads - 1] = new FasterWidgetMaker(n / numThreads + n % numThreads);

        for (int i = 0; i < numThreads; i++) {
            robotFactory[i].start();
        }

        for (int i = 0; i < numThreads; i++) {
            robotFactory[i].join();
        }

        long totalTime = System.currentTimeMillis() - startTime;
        System.out.println("WidgetMaker: made " + n + " widgets in " + totalTime / 1000.0 + " seonds");
    }

}
