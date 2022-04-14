import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ThreadExample {

	public static void main(String[] args) throws InterruptedException {
		int numPrints = Integer.parseInt(args[0]);
		int threads = Integer.parseInt(args[1]);
		System.out.println("Printing Hello World " + numPrints +"x....");
		long startTime = System.nanoTime();
		
		if(threads == 0) 
			printWithNoThreads(numPrints);
		
		else {
			int extra = numPrints%threads;
			for(int i = 1; i <= threads; i++) {
				int numPrints = numPrints / threads;
				if (extra > 0) {
					numprints++;
					extra--;
				}
				HelloWorld thread = new HelloWorld("Thread " + i, numPrints);
				thread.start();
			}
		}
		printResults(startTime, threads, numPrints);	
	}
	
	public static void printResults(long startTime, int threads, int numPrints) {
		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				long endTime = System.nanoTime();
				double elaspedTime = (double) (endTime - startTime) / 1_000_000_000;
				String results = new DecimalFormat("0.00").format(elaspedTime);
				System.out.println("Took " + threads + " threads " + results + " seconds to print " + numPrints + "x");
			}
		});
	}
	public static void printWithNoThreads(int numPrints) throws InterruptedException {
		for(int i = 0; i < numPrints; i++) {
			Thread.sleep(1000);
			System.out.println("Hello World!");
		}
	}

}

class HelloWorld extends Thread {
	private String name;
	private int numPrints;

	public HelloWorld(String name, int numPrints) {
		this.numPrints = numPrints;
		this.name = name;
	}

	public void run() {
		try {
			
			for (int i = 0; i != numPrints; i++) {
				Thread.sleep(1000);
				System.out.println(name + ": Hello World!");
			}
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
