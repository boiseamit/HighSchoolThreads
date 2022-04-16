import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ThreadExample {

	public static void main(String[] args) throws InterruptedException {
		int totalPrints = Integer.parseInt(args[0]);
		int threads = Integer.parseInt(args[1]);
		System.out.println("Printing Hello World " + totalPrints +"x....");
		long startTime = System.nanoTime();
		
		if(threads == 0) 
			printWithNoThreads(totalPrints);
		
		else {
			int extra = totalPrints%threads;
			for(int i = 1; i <= threads; i++) {
				int numPrints = totalPrints / threads;
				if (extra > 0) {
					numPrints++;
					extra--;
				}
				HelloWorld thread = new HelloWorld("Thread " + i, numPrints);
				thread.start();
			}
		}
		printResults(startTime, threads, totalPrints);	
	}
	
	public static void printResults(long startTime, int threads, int numPrints) {
		while (Thread.activeCount() > 1) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		long endTime = System.nanoTime();
		double elaspedTime = (double) (endTime - startTime) / 1_000_000_000;
		String results = new DecimalFormat("0.00").format(elaspedTime);
		System.out.println("Took " + threads + " threads " + results + " seconds to print " + numPrints + "x");
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
