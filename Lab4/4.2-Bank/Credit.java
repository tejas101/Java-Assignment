/* Credit.java  
 *Version: 
 *     1 
 * 
 * Revisions: 
 *     
 *     Tejas Raval MS-CS 2018 RIT tr7550@rit.edu
 *      
 */
/** 
  *Credit class is used to do operations on Credit account
  *@author      Tejas Raval 
  */

public class Credit extends Customer implements transcation {
  double intrest=20;//Interest rate of credit account
  
	public void creditAccount(double creditAmount,int creditID)
	{
			
		amountOwed[searchAccount(creditID)]+=creditAmount;	
		System.out.println("Transaction completed!"
				+ " You owed "+creditAmount+"$");
		
		 
	}
	
	public void debitAccount(int debitAcc,double debitAmt)
	{
			
		amountOwed[searchAccount(debitAcc)]-=debitAmt;	
		System.out.println("Transaction completed! "
				+ "You paid your credit card bill.");
		
		 
	}
	
	public void geTimeSummary(int time)
	{
		for(int i=0; i<Customer.counter;i++)
		{ 
			if(accountType[i]=="Liability")
			{   if(amountOwed[i]!=0)
			{   
				
				for(int j=1;j<=time;j++)
				{
				double rate=calculateIntrest(intrest,
						amountOwed[i],1);
				amountOwed[i]+=rate;
				System.out.println(name[i]+" is charged "+
				rate+" $ in interest after 1 months."
						+ " Account Balance is now "+
				amountOwed[i]+"$");
				System.out.println("Credit Card bill sent to"
						+ " customer "+ name[i] +" for account "
					    + "number "+ accountNo[i] +" in the amount "
					    + "of " +amountOwed[i]+"$");
				}
			}
			else
				System.out.println(name[i]+" has not owed"
						+ " any amount yet.");
			}
			
		}
	}
}
