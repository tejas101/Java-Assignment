/* Zero.java  
 * Version: 1
 *      
 * Revisions: 0
 *     
 * Tejas Raval MS-CS 2018 RIT tr7550@rit.edu
 * Lipisha Chaudhary MS-CS 2018 RIT lc2919@rit.edu
 */
/** 
 * ----------Program Description-----------
 * The programs finds elements from the given set which sum's to zero
 *@author      Tejas Raval 
 *@author      Lipisha Chaudhary
 */
import java.math.BigInteger;
import java.util.Arrays;

public class Zero {
	static int arr[]= new int[]{7,-11,1,11,11,11,2,8,7};
	static boolean flag=false;


	static void checkZero(int start,int end) {
		/**
		 * The checkZero method the start and end location of the arrya from where
		 * the search for the elements should begin.
		 * @param start	inital postion for the array 
		 * @param end	end of the array
		 */
		if(arr.length==1 && arr[0]==0)
		{
			System.out.println("Just one element is in the set and "
					+ "it's zero its self");
		    return;
		}
		if(arr.length==1 && arr[0]!=0)
		{
			System.out.println("Just one element is in the set and its "
					+ " not zero, hence sum can't be zero");
		    return;
		}

		for (int i = start+1; i <= (int)Math.pow(2, end); i++) {
			int temp[]=new int[arr.length];
			int sum=0;
			for (int j = 0; j <end; j++) {
				if (((i >> j) & 1) % 2 == 1) { 
					temp[j]=arr[j];
					sum+=arr[j];
					if(sum==0) {
						flag=true;
						System.out.println("Checking " + Arrays.toString(arr)
						+ " - should be " + true);
						System.out.print("Found subset that sums to zero:");
						for(int ik=0;ik<temp.length;ik++)
						{  
							if(temp[ik]!=0)
								System.out.print(temp[ik]+" ");
						}
						System.out.println();
						return;
					}
				}
			}

			
		}

		if(!flag) {
			System.out.println("Checking " + Arrays.toString(arr)
			+ " - should be " + false);
			System.out.print("Unable to find subset that sums to zero ");
		} 
	}
	public static void main(String args[]) {
		checkZero(0,arr.length);

	}

}
