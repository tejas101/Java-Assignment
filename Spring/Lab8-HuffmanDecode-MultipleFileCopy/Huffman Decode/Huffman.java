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
 *This program implements Huffman coding and decoding
 * 
 *@author      Tejas Raval 
 *@author      Lipisha Chowdhry
 */
import java.io.*;
import java.nio.file.Files;
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
	static int[] freq=new int[1000];//to hold frequency of all character in the input file
	static String print="";
	static int uniqueCounter;//unique character count.
	static int allLength=0;//lengt of ecnoded binary string
	static String[] codeString = new String[freq.length];//store Hffman code for each character
	//static String outFileName="Encode.txt";//output file
	static String inFileName="",decodeFile="";//input file
	static String[] decodeArray= new String[freq.length];//To store the decode data from the header
	/**
	 * This methods counts the frequency of the all characters in the input text file.
	 * @param fileName        Input file which needes to be encoded
	 * @throws IOException 
	 */
	public void initializeFreq(String fileName) throws IOException {
		int temp;
		BufferedReader objReader = null;//war_and_peace
		objReader = new BufferedReader(new FileReader(inFileName));
		while ((temp = objReader.read()) != -1) {
			if(temp>-1&&temp<1000) {
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
		String str="";
		String temp;
		BufferedReader objReader = null;//war_and_peace
		objReader = new BufferedReader(new FileReader(inFileName));
		BufferedWriter writer = new BufferedWriter(new FileWriter(decodeFile));
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
			
			if(temp.length()==0) {
				writer.newLine();
				continue;
			}
			for(int j=0;j<temp.length();j++) {
				if((int)temp.charAt(j)>-1&&(int)temp.charAt(j)<7000)
					print+= codeString[(int)temp.charAt(j)];

			}
			int i=0;
			if(print.length()==7) {
				String hold=print.substring(0, 7);
				int intVal=Integer.parseInt(hold,2);
				if(intVal==10) {
					writer.write("Ç");
					continue;
				}
				if(intVal==13) {
					writer.write("È");
					continue;
				}
				char asci=(char) intVal;
				writer.write(asci);
				writer.write(""+0);
			}
			for( i=7;i<=print.length();i+=7) {
				String hold=print.substring(i-7, i);
				//System.out.println("Hold is "+hold);
				int intVal=Integer.parseInt(hold,2);
				if(intVal==10) {
					writer.write("Ç");
					continue;
				}
				if(intVal==13) {
					writer.write("È");
					continue;
				}
				char asci=(char) intVal;
				writer.write(asci);
				if(print.length()==i) {
					writer.write(""+0);
					writer.newLine();
				}
			}
			//Line which have binary string length not in multiples of 8 
			if(print.length()%7!=0) {
				String rem=print.substring(i-7,print.length());
				int intVal=Integer.parseInt(rem,2);
				if(intVal==10) {
					writer.write("Ç");
					continue;
				}
				if(intVal==13) {
					writer.write("È");
					continue;
				}
				char asci=(char) intVal;
				writer.write(asci);
				writer.write(""+print.length()%7);
				writer.newLine();
			}
		}
		writer.close();
	}
	
	public void decode() throws IOException {
		BufferedReader objReader = null;//war_and_peace
		objReader = new BufferedReader(new FileReader(inFileName));
		String temp;
		int ignoreCount=Integer.parseInt(objReader.readLine());
		for (int i = 0; i < ignoreCount+2; i++) {
			//manually fill the data for /n /r and space in the decodeArray. This array will be 
			//used for decoding
			String table=objReader.readLine();
			//System.out.println(table);
			if(i==1) {
				decodeArray[10]=table.substring(1, table.length());	
			}
			else {
			if(i==3) {
				decodeArray[13]=table.substring(1, table.length());	
			}
			else {
				if(i==4) {
					decodeArray[32]=table.substring(2, table.length());	
				}
				else{
				if(i>4) {
					decodeArray[(int)table.charAt(0)]=table.substring(2, table.length());	
				}
			}
			}
			}
			
			}

		BufferedWriter writer = new BufferedWriter(new FileWriter(decodeFile));
		//Now read the encoded file and replace ascii by huffman code line by line
		while ((temp = objReader.readLine()) != null) {
			String output="";
        if(temp.length()==0) {// This means a complete blank line
        	writer.newLine();
     	continue ;
     	}
			int rem=(int)temp.charAt(temp.length()-1);//The last information bit
	        
			/**0001010 and 0001101 will be replace by asci code of 199 and 200 respectivly
			** This is because, they are line breaks and breaks the encode string in middle
			**/
			if(49<=rem&&rem<=55) {
			for(int i=0;i<temp.length()-1;i++) {
		        if(i+1==temp.length()-1) {
		        	if((int)temp.charAt(i)==199) {
		        		output+="0001010";
						//continue;
					}
		        	else {
		        	if((int)temp.charAt(i)==200) {
		        		output+="0001101";
						//continue;
					}
		        	else {
		        	output+=String.format("%"+Integer.parseInt(""+temp.charAt(temp.length()-1))+"s",
		        			Integer.toBinaryString((int)temp.charAt(i))).replace(' ', '0');
		        	}
		        }
		        }
		        else {
		        	if((int)temp.charAt(i)==199) {
		        		output+="0001010";
						//continue;
					}
		        	else {
		        	if((int)temp.charAt(i)==200) {
		        		output+="0001101";
						//continue;
					}
		        	else {
		        	output+=String.format("%7s", Integer.toBinaryString((int)temp.charAt(i))).replace(' ', '0');}
		        }
		        }
		    }
			}
			else {
				if(48==(int)temp.charAt(temp.length()-1)) {
					for(int i=0;i<temp.length()-1;i++) {
						if((int)temp.charAt(i)==199) {
			        		output+="0001010";
							//continue;
						}
						else {
			        	if((int)temp.charAt(i)==200) {
			        		output+="0001101";
							//continue;
						}
			        	else {
				    	output+=String.format("%7s", Integer.toBinaryString((int)temp.charAt(i))).replace(' ', '0');
			        	}
						}
					}	
				}
			}
			//Now we have a binary string which can be replaced by corresponding huffman code's character
			String hold="";
			int i=0;
			int j=0;
			for(;j<=output.length();j++) {
				String returnCode=searchCode(output.substring(i, j));
				if(!returnCode.equals("-1")) {
					hold+=returnCode;
					i=j;
					j=i;
				}
			}

					writer.write(hold);//Write the decoded line to the decode file
					writer.newLine(); 
			} 
		writer.close();
	}
	
	public String searchCode(String check) {
		for(int i=0;i<decodeArray.length;i++) {
			if(decodeArray[i]!=null) {
			if(decodeArray[i].equals(check)) {
				return ""+(char)i;
			}
			}
		}
		return "-1";
	}

	public static void main(String args[]) throws IOException {
/**
Java Huffman -i test.txt -o enc.txt  -e

Java Huffman -i enc.txt -o dec.txt  -d

diff fileA fileB

**/
		if(args[0].equals("diff")) {
			Huffman.inFileName=args[1];//Input file name
			Huffman.decodeFile=args[2];//decode File name
			BufferedReader objReaderEnc = null;//war_and_peace
			objReaderEnc = new BufferedReader(new FileReader(Huffman.inFileName));
			BufferedReader objReaderDec = null;//war_and_peace
			objReaderDec = new BufferedReader(new FileReader(Huffman.decodeFile));
			String enc="",dec="";
			String temp="";
			while ((temp = objReaderEnc.readLine()) != null) {
				//System.out.println("ddd");
				enc+=temp;
				
			}
			temp="";
			while ((temp = objReaderDec.readLine()) != null) {
				dec+=temp;
				
			}
			
			System.out.println("org "+enc);
			System.out.println("dec "+dec);
			if(enc.equals(dec)) {
				System.out.println("Files are same");
				
			}
			else
			{
				System.out.println("Files are not same");
			}
			System.exit(0);
		}
		Huffman.inFileName=args[3];//Input file name
		Huffman.decodeFile=args[5];//decode File name
		Huffman work = new Huffman();
		
		if(args[6].equals("-e")) {
			work.initializeFreq(Huffman.inFileName);
			work.createList(freq);
			TreeNode root=work.merge(freq);
			work.makeString(codeString,root,"");
			BufferedWriter writer = new BufferedWriter(new FileWriter("key.csv"));//Write the ecoded data to key file
			for(int i=0;i<codeString.length;i++) {
				if(codeString[i]!=null) {
					String output=""+i+" "+(char)i+","+codeString[i]+'\n';
					//System.out.println(output);
					writer.write(output);
				}
			}
			writer.close();
			work.WriteToFile(codeString);//Write the encoded data to Encrypt file
		}else
		{
			if(args[6].equals("-d")) {
				work.decode();
			}
		}
		
		System.out.println("Work done");

	}
}