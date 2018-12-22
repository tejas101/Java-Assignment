/* ConsumerProducer.java  
 *Version: 
 *     1 
 * 
 * Revisions: 
 *     
 *     Tejas Raval MS-CS 2018 RIT tr7550@rit.edu
 */
/** 
 *This program implements N consumer, M Producer problem 
 *using Threads where now there are 3 items for production and consumption
 * 
 *@author      Tejas Raval 
 */

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class lessThanEqualtoZero extends Exception {

	public lessThanEqualtoZero(String message) {
		super(message);
	}
}

class wrongStorageSize extends Exception {

	public wrongStorageSize(String message) {
		super(message);
	}
}

public class PCT extends Exception {
	static ArrayList<Integer> tray = new ArrayList<Integer>();
	static int counter = 0;
	static Object mutex = new Object();
	static int s;
	static int plimit;
	static int climit;
	static ObjectOutputStream oos = null;
	static ObjectInputStream ois = null;
	static String threadName = "";

	public static void main(String args[])
			throws InterruptedException, lessThanEqualtoZero, 
			wrongStorageSize, UnknownHostException, IOException {
		/**
		 * The main program.
		 *
		 * @param args command line arguments
		 */
		Scanner sc = new Scanner(System.in);
		int c = Integer.parseInt(args[0]); // number of consumers
		int p = Integer.parseInt(args[1]); // number of producers
		int prodLimit = Integer.parseInt(args[3]);// limit to produce for producer
		int custLimit = Integer.parseInt(args[4]);// limit to consume for consumer
		s = Integer.parseInt(args[2]); // size of storage
		Socket soc = null;// Socket Connector
		// -----//

		// Socket sP = new Socket("localhost", 8080);
		// Socket sP = new Socket("129.21.30.37", 9090);
		// Socket sC = new Socket("localhost", 9091);
		// ----//

		if (s <= 0 || p <= 0 || c <= 0 || prodLimit <= 0 || custLimit <= 0) {
			throw new lessThanEqualtoZero("Input values should "
		+ "be greater than Zero");
		}

		if (s <= 9) {
			throw new wrongStorageSize("Wrong storage size "
					+ "as Consumer will need atleast 10 items"
					+ " to start consumption. Storage"
					+ " size must be greater than 9");
		}

		PCT CP = new PCT();

		Thread t1[] = new Thread[p];
		int i = 0;
		for (i = 0; i < p; i++) {
			t1[i] = new Thread(new Runnable() {

				public void run() {

					try {
						CP.Producer(s, prodLimit, soc);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
		}
		Thread t2[] = new Thread[c];
		int k = 0;
		for (k = 0; k < c; k++)
			t2[k] = new Thread(new Runnable() {

				public void run() {
					try {
						CP.Consumer(custLimit);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});

		for (int ip = 0; ip < p; ip++)
			t1[ip].start();
		for (int ic = 0; ic < c; ic++)
			t2[ic].start();

		for (int ic = 0; ic < c; ic++)
			t1[ic].join();
		for (int ip = 0; ip < p; ip++)
			t2[ip].join();
	}

	/**
	 * Producer() will implement producer function
	 * 
	 * @param s Size of the storage/tray
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	void Producer(int s, int prodLimit, Socket soc) throws
	InterruptedException, IOException, ClassNotFoundException {
		while (true) {

			synchronized (mutex) {
				// threadName=Thread.currentThread().getName();
				if (tray.size() < s && plimit <= prodLimit) {

					if (Collections.frequency(tray, 2) < 5) {
						for (int i = 0; i < 5; i++) {
							if (Collections.frequency(tray, 2) < 5 
									&& plimit < prodLimit) {
								// tray.add(0, 2);
								//make a socket connection
								soc = new Socket("129.21.30.37", 9290);
								//create a object output stream
								oos = new ObjectOutputStream(soc.getOutputStream());
								//write/send the tray and item number on the stream to Producer.java
								oos.writeObject(tray);
								oos.writeObject(2);
								oos.flush();
								//read the data sent by the Producer.java
								ois = new ObjectInputStream(soc.getInputStream());
								tray = (ArrayList<Integer>) ois.readObject();
								System.out.println(Thread.currentThread().getName() 
										+ " Producer" + " added item2");
								plimit++;
								ois.close();
								oos.flush();
								oos.close();
							} else {// ois.close();
									// oos.flush();
									// oos.close();
								break;
							}

						}
						if (plimit == prodLimit)
							plimit = 0;
						mutex.notifyAll();
						mutex.wait();
					}
					if (Collections.frequency(tray, 1) < 3) {
						for (int i = 0; i < 3; i++) {
							if (Collections.frequency(tray, 1) < 3 
									&& plimit < prodLimit) {
								soc = new Socket("129.21.30.37", 9290);
								oos = new ObjectOutputStream(soc.getOutputStream());
								oos.writeObject(tray);
								oos.writeObject(1);
								ois = new ObjectInputStream(soc.getInputStream());
								tray = (ArrayList<Integer>) ois.readObject();
								System.out.println(Thread.currentThread().getName()
										+ " Producer" + " added item1");
								plimit++;
								ois.close();
								oos.close();
							} else
								break;

						}
						if (plimit == prodLimit)
							plimit = 0;
						mutex.notifyAll();
						mutex.wait();
					}
					if (Collections.frequency(tray, 3) < 2) {
						for (int i = 0; i < 2; i++) {
							if (Collections.frequency(tray, 3) < 2 && plimit < prodLimit) {
								// tray.add(0, 3);
								soc = new Socket("129.21.30.37", 9290);
								oos = new ObjectOutputStream(soc.getOutputStream());
								oos.writeObject(tray);
								oos.writeObject(3);
								ois = new ObjectInputStream(soc.getInputStream());
								tray = (ArrayList<Integer>) ois.readObject();
								System.out.println(Thread.currentThread().getName()
										+ " Producer" + " added item3");
								plimit++;
								ois.close();
								oos.close();
							} else
								break;

						}
						if (plimit == prodLimit)
							plimit = 0;
						mutex.notifyAll();
						mutex.wait();
					}

				}
				mutex.notifyAll();
				if (tray.size() == s || plimit >= prodLimit) {
					System.out.println("producer is waiting");
					if (plimit > prodLimit)
						plimit = 0;
					mutex.wait();
				}
			}
			Thread.sleep(2000);
		}
	}

	/**
	 * Consumer() will implement consumer function
	 * 
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	void Consumer(int custLimit) throws InterruptedException,
IOException, ClassNotFoundException {
		while (true) {
			synchronized (mutex) {
				int item1 = Collections.frequency(tray, 1);
				int item2 = Collections.frequency(tray, 2);
				int item3 = Collections.frequency(tray, 3);
				while (climit < 10 && tray.size() != 0 && item1 > 2
						&& item2 > 4 && item3 > 1) {
					while (climit < 10) {
						for (int i = 0; i < 5; i++) {
							//make a socket connection
							Socket socP = new Socket("129.21.22.196", 9291);
							int element = tray.lastIndexOf(2);
							if (element != -1) {

								// tray.remove(element);
								//create the object output stream
								oos = new ObjectOutputStream(socP.getOutputStream());
								//write/send tray and the index of the item to be removed to consumer.java
								oos.writeObject(tray);
								oos.writeObject(element);
								//read the data sent  by the consumer.java
								ois = new ObjectInputStream(socP.getInputStream());
								tray = (ArrayList<Integer>) ois.readObject();
								climit++;
								System.out.println(Thread.currentThread().getName() 
										+ " Conusmer " + "removed item2");
								ois.close();
								oos.flush();
								oos.close();
							} else
								break;
						}

						for (int i = 0; i < 3; i++) {
							Socket socP = new Socket("129.21.22.196", 9291);
							int element = tray.lastIndexOf(1);
							if (element != -1) {

								// tray.remove(element);
								oos = new ObjectOutputStream(socP.getOutputStream());
								oos.writeObject(tray);
								oos.writeObject(element);
								ois = new ObjectInputStream(socP.getInputStream());
								tray = (ArrayList<Integer>) ois.readObject();
								climit++;
								System.out.println(Thread.currentThread().getName()
										+ " Conusmer " + "removed item1");
								ois.close();
								oos.flush();
								oos.close();
							} else
								break;
						}

						for (int i = 0; i < 2; i++) {
							Socket socP = new Socket("129.21.22.196", 9291);
							int element = tray.lastIndexOf(3);
							if (element != -1) {

								// tray.remove(element);
								oos = new ObjectOutputStream(socP.getOutputStream());
								oos.writeObject(tray);
								oos.writeObject(element);
								ois = new ObjectInputStream(socP.getInputStream());
								tray = (ArrayList<Integer>) ois.readObject();
								climit++;
								System.out.println(Thread.currentThread().getName() 
										+ " Conusmer " + "removed item3");
								ois.close();
								oos.flush();
								oos.close();
							} else
								break;
						}

					}
				}

				if (tray.size() == 0 || climit >= custLimit) {
					mutex.notifyAll();
					System.out.println("Consumer is waiting");
					climit = 0;
					mutex.wait();
				}
				mutex.notifyAll();
			}
			Thread.sleep(2000);
		}
	}
}