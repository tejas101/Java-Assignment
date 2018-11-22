/* Checking.java  
 *Version: 
 *     1 
 * 
 * Revisions: 
 *     
 *     Tejas Raval MS-CS 2018 RIT tr7550@rit.edu
 *      
 */
/** 
  *Checking class is used to do operations on Checking account
  *@author      Tejas Raval 
  */
public class Checking extends Customer implements transcation {
  double intrest=15;//Interest rate of checking account
  public void creditAccount(double creditAmount,int creditID)
	{
			
		balance[searchAccount(creditID)]-=creditAmount;	
		System.out.println("Transaction completed! You"
				+ " withdrawn "+creditAmount+"$");
		
		 
	}
  public void debitAccount(int debitAcc,double debitAmt)
	{	
		balance[searchAccount(debitAcc)]+=debitAmt;	
		System.out.println("Transaction completed!"
				+ " Money deposited"
				+ "into checking account");		 
	}
	
	public void geTimeSummary(int time)
	{
		for(int i=0; i<Customer.counter;i++)
		{ 
			if(accountType[i]=="Checking")
			{   double rate=calculateIntrest(intrest,
					balance[i],time);
			  balance[i]+=rate;
				System.out.println(name[i]+" earned "+
			  rate+" $ in interest"
				+ " after "+time+" months. Account Balance"
						+ " is now "+balance[i]+"$");
				
				System.out.println(balance[i]/5 +"$ "
						+ "shopping is free on"
						+ " this Checking account");			
			}
			
		}
	}
}
