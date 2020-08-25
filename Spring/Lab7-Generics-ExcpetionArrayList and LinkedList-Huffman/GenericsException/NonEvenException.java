/* NonEvenException.java  
 *Version: 
 *     1 
 * 
 * Revisions: 
 *     
 *     Tejas Raval MS-CS 2018 RIT tr7550@rit.edu
 *     Lipisha Chowdhry MS-CS 2018 RIT lc2919@rit.edu
 */
class NonEvenException extends Exception 
{
	NonEvenException()
    {
		//System.out.print("Odd number exception!!");
		super("Odd number/lenght exception!!");
    }
	NonEvenException(String msg)
    {
        super(msg);
    }
	
}



