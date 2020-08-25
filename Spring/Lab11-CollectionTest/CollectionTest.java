/* CollectionTest.java  
 * Version: 1
 *      
 * Revisions: 
 *     
 * Tejas Raval MS-CS 2018 RIT tr7550@rit.edu
 * Lipisha Chaudhary MS-CS 2018 RIT lc2919@rit.edu
 */
/** 
 * ----------Program Description-----------
 * Program is a instrumentation test for collection framework
 *@author      Tejas Raval 
 *@author      Lipisha Chaudhary
 */
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ThreadLocalRandom;



public class CollectionTest  {
	Random rand = new Random();
	NumberFormat formatter = new DecimalFormat("#0.00000");
	long start,end;
	float totalTime=0;

	public  void checker(Collection<Integer> e, String name) throws CloneNotSupportedException{

		//System.out.println(e.getClass());
		System.out.println();
		System.out.println("--------------Collection :"+name+"-----------------------------");
		System.out.println();
		//--------------------------------add----------------------------------------------------
		for(int i=0;i<100000;i++) {
			start = System.currentTimeMillis();
			e.add(i);
			end= System.currentTimeMillis();
			totalTime+=end-start;
		}

		totalTime/=100000;
		
		this.calculateTime(totalTime, name," add ");

		//--------------------------------collection equality----------------------------------------------------
		totalTime=0;
		switch (name)     
		{
		case "ArrayList":
			{
				ArrayList<Integer> test = new ArrayList<>(e);
				start = System.currentTimeMillis();
				e.equals(test);
				end= System.currentTimeMillis();
			}

		case "LinkedList":
		{
			LinkedList<Integer> test = new LinkedList<>(e);
			start = System.currentTimeMillis();
			e.equals(test);
			end= System.currentTimeMillis();
		}
		case "TreeSet":
		{
			TreeSet<Integer> test = new TreeSet<>(e);
			start = System.currentTimeMillis();
			e.equals(test);
			end= System.currentTimeMillis();
		}
		case "HashSet":
		{
			HashSet<Integer> test = new HashSet<>(e);
			start = System.currentTimeMillis();
			e.equals(test);
			end= System.currentTimeMillis();
		}
		}
		totalTime+=end-start;
		this.calculateTime(totalTime, name," collection equality  ");
		
		//--------------------------------contains----------------------------------------------------
		totalTime=0;
		for(int i=0;i<100000;i++) {
			start = System.currentTimeMillis();
				e.contains(i);
			end= System.currentTimeMillis();
			totalTime+=end-start;
		}
		totalTime/=100000;
		this.calculateTime(totalTime, name," contains ");
	
		//----------------------size--------------------------------------------------------------
		totalTime=0;
		for(int i=0;i<100000;i++) {
			start = System.currentTimeMillis();
			end= System.currentTimeMillis();
			totalTime+=end-start;
		}
		totalTime/=100000;

		this.calculateTime(totalTime, name," sizeOf ");
		//-------------------------remove-----------------------------------------------------------
				totalTime=0;
				for(int i=0;i<100000;i++) {
					start = System.currentTimeMillis();
						e.remove(i);
					end= System.currentTimeMillis();
					totalTime+=end-start;
				}
				totalTime/=100000;
				this.calculateTime(totalTime, name," remove ");
		//------------------------------------------------------------------------------------
				
				

	}

	public void changer(List<Integer> e,String name) {
		
		//Need to refill the list as previously it was emptied by remove operation. 
		//--------------------------------add----------------------------------------------------
				for(int i=0;i<100000;i++) { 
					e.add(i);		 
				}
		
		//----------------------change/set at location--------------------------------------------------------------
		totalTime=0;
		for(int i=0;i<100000;i++) {
			start = System.currentTimeMillis();
			e.set(i, i+3);
			end= System.currentTimeMillis();
			totalTime+=end-start;
		}
		totalTime/=100000;
		this.calculateTime(totalTime, name," set/change at location");

		//----------------------add at location--------------------------------------------------------------
		totalTime=0;
		for(int i=0;i<100000;i++) {
			start = System.currentTimeMillis();
			e.add(i, i+3);
			end= System.currentTimeMillis();
			totalTime+=end-start;
		}
		totalTime/=100000;
		this.calculateTime(totalTime, name," add at location ");
		//----------------------sort with custom comparator--------------------------------------------------------------
		start = System.currentTimeMillis();
		Collections.sort(e, new Comparator<Object>() 
		{

			public int compare(Object o1, Object o2) 
			{
				Integer sa = (Integer)o1;
				Integer sb = (Integer)o2;

				int v = sa.compareTo(sb);

				return -v;           
			}
		}  );
		end= System.currentTimeMillis();
		System.out.println("Operation : sort with custom comparator"+", Collection :"+name+", "
				+ "Time :"+formatter.format((end - start) / 1000d)+" seconds");

		//----------------------sort--------------------------------------------------------------
		start = System.currentTimeMillis();
		e.sort(null);
		end= System.currentTimeMillis();

		System.out.println("Operation : sort"+", Collection :"+name+", Time :"
		+formatter.format((end - start) / 1000d)+" seconds");

		//------------------------remove with index------------------------------------------------------------
		totalTime=0;

		for(int i=0;i<100000;i++) {
			start = System.currentTimeMillis();
			if(e.size()>5)
			{e.remove(5);}
			end= System.currentTimeMillis();
			totalTime+=end-start;
		}
		totalTime/=100000;
		this.calculateTime(totalTime, name," remove with index ");
		
		
	}

	public void calculateTime(float t,String  name, String operation) {
		System.out.println("Operation :"+operation+", Collection :"+name+", Time :"+(t)+" millisecond");
	}
	public void checker(Map<Integer,Integer> e, String name){
		System.out.println();
		System.out.println("--------------Collection :"+name+"-----------------------------");
		totalTime=0;
		//--------------------------------add----------------------------------------------------
		for(int i=0;i<100000;i++) {
			start = System.currentTimeMillis();
			e.put(i,i);
			end= System.currentTimeMillis();
			//System.out.println("e - s is "+(end-start));
			totalTime+=end-start;
		}

		totalTime/=100000;
		this.calculateTime(totalTime, name," add ");
		//-------------------------Collection equals-----------------------------------------------------------
		totalTime=0;
		switch (name)     
		{
		case "TreeMap":
			{
				TreeMap<Integer,Integer> test = new TreeMap<>(e);
				start = System.currentTimeMillis();
				e.equals(test);
				end= System.currentTimeMillis();
			}

		case "HashMap":
		{
			HashMap<Integer,Integer> test = new HashMap<>(e);
			start = System.currentTimeMillis();
			e.equals(test);
			end= System.currentTimeMillis();
		}
		}
		totalTime+=end-start;
		this.calculateTime(totalTime, name," collection equality  ");
		
		//-------------------------Replace-----------------------------------------------------------
		totalTime=0;
		for(int i=0;i<100000;i++) {
			start = System.currentTimeMillis();

			e.replace(i,i+3);
			end= System.currentTimeMillis();
			totalTime+=end-start;
		}
		totalTime/=100000;

		this.calculateTime(totalTime, name," replace ");


		//--------------------------------containsKey----------------------------------------------------
		totalTime=0;
		for(int i=0;i<100000;i++) {
			start = System.currentTimeMillis();
			e.containsKey(i);
			end= System.currentTimeMillis();
			totalTime+=end-start;
		}
		totalTime/=100000;
		this.calculateTime(totalTime, name," containsKey ");

		//--------------------------------containsVal----------------------------------------------------
		totalTime=0;
		for(int i=0;i<100000;i++) {
			start = System.currentTimeMillis();
			e.containsValue(i+3);
			end= System.currentTimeMillis();
			totalTime+=end-start;
		}
		totalTime/=100000;
		this.calculateTime(totalTime, name," containsValue ");
		//----------------------size--------------------------------------------------------------
		totalTime=0;
		for(int i=0;i<100000;i++) {
			start = System.currentTimeMillis();
			end= System.currentTimeMillis();
			totalTime+=end-start;
		}
		totalTime/=100000;

		this.calculateTime(totalTime, name," sizeOf ");

		//-------------------------remove-----------------------------------------------------------
		totalTime=0;
		for(int i=0;i<100000;i++) {
			start = System.currentTimeMillis();

			e.remove(i);
			end= System.currentTimeMillis();
			totalTime+=end-start;
		}
		totalTime/=100000;

		this.calculateTime(totalTime, name," remove ");
	}
	public static void main(String args[]) throws CloneNotSupportedException {
		CollectionTest ctObj= new CollectionTest();
		List<Integer> al = new ArrayList<>();
		ctObj.checker(al, "ArrayList");
		ctObj.changer(al, "ArrayList");
		
		List<Integer> ll = new LinkedList<>();
		ctObj.checker(ll, "LinkedList");
		ctObj.changer(ll, "LinkedList");
		Set<Integer> ts = new TreeSet<>();
		ctObj.checker(ts, "TreeSet");
		Set<Integer> hs = new HashSet<>();
		ctObj.checker(hs, "HashSet");
		Map<Integer, Integer> hm = new HashMap<>();
		ctObj.checker(hm, "HashMap");
		Map<Integer, Integer> tm = new TreeMap<>();
		ctObj.checker(tm, "TreeMap");

	}
}
