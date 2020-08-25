/* Huffman.java  
 *Version: 
 *     1 
 * 
 * Revisions: 
 *     
 *     Tejas Raval MS-CS 2018 RIT tr7550@rit.edu
 *     Lipisha Chowdhry MS-CS 2018 RIT lc2919@rit.edu
 */
/** 
 *This program implements Huffman coding
 * 
 *@author      Tejas Raval 
 *@author      Lipisha Chowdhry
 */
import java.io.*;
import java.util.Scanner;


class TreeNode implements Comparable<TreeNode> {
	char c;// store the character
	int freq;//store the frequency of the character
	TreeNode left=null;
	TreeNode right=null;

	TreeNode(char ch, int freq, TreeNode left, TreeNode right) {
		this.c    = ch;
		this.freq  = freq;
		this.left  = left;
		this.right = right;

	}
	@Override
	public int compareTo(TreeNode e) {
		// TODO Auto-generated method stub
		return this.freq - e.freq;
	}

}
class Huffman{
	Scanner sc ;
	static GenericLinkedList<TreeNode> gll;//Using Generic linkedlist has a priority queue
	static int[] freq=new int[400];//to hold frequency of all character in the input file
	static String print="";
	static int uniqueCounter;//unique character count.
	static int allLength=0;//lengt of ecnoded binary string
	static String[] codeString = new String[freq.length];//store Hffman code for each character
	static String outFileName="Encode.txt";//output file
	static String inFileName="";//input file
	/**
	 * This methods counts the frequency of the all characters in the input text file.
	 * @param fileName        Input file which needes to be encoded
	 * @throws IOException 
	 */
	public void initializeFreq(String fileName) throws IOException {
		int temp;
		BufferedReader objReader = null;//war_and_peace
		objReader = new BufferedReader(new FileReader("C:\\Users\\Tejas Raval\\eclipse-workspace\\Huffman\\"+inFileName+".txt"));
		while ((temp = objReader.read()) != -1) {
			if(temp>-1&&temp<400) {
				freq[temp]++;//type cast char to int 
			}
		}
		if (objReader != null)
			objReader.close();
	}
	/**
	 * This methods creates a linkedlist(priority queue of the elements in the frequency array)
	 * @param freq  frequency array
	 */
	public void createList(int freq[]) {
		gll=new GenericLinkedList<>();
		for (int i = 0; i <freq.length ; i++) {
			if (freq[i] != 0) {
				uniqueCounter++;//count the unique characters in the file to add it later in header
				gll.add(new TreeNode((char)i, freq[i], null, null));
			}
		}
		gll.sort();//Make LinkedList behave like Priotiy 
	}
    /**
     * This method merges the linkedlist and make a tree out of the elements
     * @param freq  frequency array
     * @return  Root node of the Huffman tree.
     */
	private TreeNode merge(int freq[]) {
		if(gll.size()==1) {
			if(freq[(int)'0']==0) {
				gll.add(new TreeNode('0',0,null,null));
			}
			else {
				gll.add(new TreeNode('1',0,null,null));
			}
		}

		while(gll.size()>1) {
			TreeNode left=gll.remove(0);
			TreeNode right=gll.remove(0);
			TreeNode parent= new TreeNode('0',left.freq+right.freq,left,right);
			gll.add(parent);
			gll.sort();
		}
		return gll.remove(0);

	}
	/**
	 * Check if the node is leaf or not
	 * @param t Node to be checked
	 * @return  boolean 
	 */
	private boolean checkLeaf(TreeNode t) {
		if(t.left==null&&t.right==null) {
			return true;
		}
		return false;

	}
	/**
	 * This fucntion makes a Huffman code for every character passed
	 * @param codeString String which holds all the codes
	 * @param e   Tree
	 * @param temp Character whos code is needed
	 */
	private void makeString(String[] codeString, TreeNode e, String temp) {

		if(!(this.checkLeaf(e))) {
			makeString(codeString,e.left,temp+"0");
			makeString(codeString,e.right,temp+"1");
		}
		else {
			codeString[(int)e.c]=temp;
		}

	}
	/**
	 * This function writes the code to a txt file
	 * @param codeString  String which stores the code
	 * @throws IOException
	 */
	public void WriteToFile(String[] codeString) 
			throws IOException {
		//String InfileName="Tejas.txt";
		String str="";
		String temp;
		BufferedReader objReader = null;//war_and_peace
		objReader = new BufferedReader(new FileReader("C:\\Users\\Tejas Raval\\eclipse-workspace\\Huffman\\"+inFileName+".txt"));
		BufferedWriter writer = new BufferedWriter(new FileWriter(outFileName));
		writer.write(""+uniqueCounter);
		writer.newLine();
		for(int k=0;k<codeString.length;k++) {
			if(codeString[k]!=null) {
				String output=(char)k+","+codeString[k];
				writer.write(output);
				writer.newLine();
			}
		}
		while ((temp = objReader.readLine()) != null) {
			print="";
			for(int j=0;j<temp.length();j++) {
				if((int)temp.charAt(j)>-1&&(int)temp.charAt(j)<400)
					print+= codeString[(int)temp.charAt(j)];
				//System.out.println("i is "+i);

			}
			//System.out.println("Encodes "+print);
			int i=0;
			if(print.length()==8) {
				String hold=print.substring(0, 8);
				int intVal=Integer.parseInt(hold,2);
				char asci=(char) intVal;
				writer.write(asci);
				//System.out.println("writing "+intVal);
			}
			for( i=8;i<print.length();i+=8) {
				String hold=print.substring(i-8, i);
				int intVal=Integer.parseInt(hold,2);
				char asci=(char) intVal;
				writer.write(asci);
				//System.out.println("writing "+intVal);
			}
			//Line which have binary string length not in multiples of 8 
			if(print.length()%8!=0) {
				String rem=print.substring(i-8,print.length());
				int intVal=Integer.parseInt(rem,2);
				char asci=(char) intVal;
				writer.write(asci);
				writer.newLine();
				//System.out.println("writing "+intVal);
			}
		}
		writer.close();
	}

	public static void main(String args[]) throws IOException {
		String s=args[0];//to check if encryption to decryption  
		Huffman.inFileName=args[1];//Input file name


		Huffman work = new Huffman();
		work.initializeFreq(Huffman.inFileName);
		work.createList(freq);
		TreeNode root=work.merge(freq);
		work.makeString(codeString,root,"");
		BufferedWriter writer = new BufferedWriter(new FileWriter("key.csv"));//Write the ecoded data to key file
		for(int i=0;i<codeString.length;i++) {
			if(codeString[i]!=null) {
				String output=(char)i+","+codeString[i]+'\n';
				//System.out.println(output);
				writer.write(output);
			}
		}
		writer.close();
		work.WriteToFile(codeString);//Write the encoded data to Encrypt file
		//System.out.println("Unique counter is "+uniqueCounter);

	}
}