/*
 * Sticks.java 
 * 
 * Version: 
 *     1 
 * 
 * Revisions: 
 *     0 
 *     
 *     
 *     Tejas Raval MS-CS 2018 RIT tr7550@rit.edu
 *     
 *     Algorithm used : Dymamic Problem solving with boolen table.
 */
/** 
 * This program calculates the sum of subset from stickLengths array for 
 * every number in unknowStickLengths array
 * 
 * @author      Tejas Raval 
 */
public class Sticks {
    static int[] stickLengths = { 1, 5, 8, 12, 12, 35, 35, 35, 61 };
    static int[] unknowStickLengths = { 1, 6, 9, 24, 110, 111, 115, 62, 24, 202, 203, 204, 205 };
 /**
  * The main program.
  *
  * @param    args    command line arguments (ignored)
  */
    
    
    public static void main( String[] arguments ) {
        for ( int index = 0; index < unknowStickLengths.length; index ++ )
                doTestLength(unknowStickLengths[index]);
    }
  /**
   * doTestLength method checks whether a sum of subset can be formed 
   * for a number in unknowStickLengths array
   *
   * @param      check    check will hold the value for which sum of subset is calculated
   * 
   */
    public static void doTestLength(int check)//
    {   
    	boolean [][] matrix = new boolean[stickLengths.length+1][check+1]; // Boolean matrix for Dynamic problem solving table.
    	for (int i = 0; i <= stickLengths.length; i++) {
            matrix[i][0]=true; // Make the first column true as if we need remaining sum is zero
        }
 
    	
    	 for(int i=1;i<=stickLengths.length; i++)
    		 for(int j=1; j<=check; j++)
    		 {
    			 if(stickLengths[i-1]>j) {
    			 matrix[i][j]=matrix[i-1][j];
    			 /** 
                  *If the current stick length under processing is greater than the partial sum which we want, 
                  *then just copy the value from the above cell
                  */
    			 }else {
    				 matrix[i][j]=(matrix[i-1][j]||matrix[i-1][j-stickLengths[i-1]]); 
    				 /**  
                      *Do an OR operation between the one cell above the current cell and
                      * the reminder sum which we are looking at      
                      */
    		 }}
    	 
   
    	 if (matrix[stickLengths.length][check]) {
    		 /** if this cell value is true, 
				 *then we have got a solution and now show the subset to user
				 */
    		 System.out.print(+check+" inch: \tyes; used sticklenghts = ");
    	        for (int i=stickLengths.length; i>=1; i--) {
    	            if (!(matrix[i-1][check])&&check!=0){
    	            	/** If the value for the cell above the matrix[i][check] 
                         *is false, select the current cell for the solution
                         */
    	                System.out.print(stickLengths[i-1]+" inch; ");
    	                check -= stickLengths[i-1];
    	                /** 
                         *Subtract the value of the selected cell from the
                         *variable check and now look for the reminder 
                         *value from the table
                         */
    	            }
    	        }
    	        System.out.print("\n");
    	    } else {
    		 System.out.println(check+" inch:\tno;"); 
    	    }
    }
    		
    	
    }
