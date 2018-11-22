/*Saving.java  
 *Version: 
 *     1 
 * 
 * Revisions: 
 *     
 *     Tejas Raval MS-CS 2018 RIT tr7550@rit.edu
 *      
 */
/** 
  *Saving class is used to do operations on Saving account
  *@author      Tejas Raval 
  */
public class Saving extends Customer implements transcation {
  double intrest=18;//Interest rate of saving account
	
  
  public void creditAccount(double creditAmount,int creditID)
	{  
	  double balanceAMT=balance[searchAccount(creditID)];
		if(creditAmount<=balanceAMT)	
		{	balance[searchAccount(creditID)]-=creditAmount;	
		System.out.println("Transaction completed! You withdrawn "
		+creditAmount+"$");
		}
		else System.out.println("Insufficent balance.");
		 
	}
  public void debitAccount(int debitAcc,double debitAmt)
	{
			
		balance[searchAccount(debitAcc)]+=debitAmt;	
		System.out.println("Transaction completed! Money deposited"
				+ " into saving account");
		
		 
	}
	
	public void geTimeSummary(int time)
	{
		for(int i=0; i<Customer.counter;i++)
		{ 
			if(accountType[i]=="Saving")
			{   double rate=calculateIntrest(intrest,balance[i],time);
			  balance[i]+=rate;
				System.out.println(name[i]+" earned "+rate+" $ in"
			+ " interest after "+time+" months. Account Balance"
					+ " is now "+balance[i]+"$");
				
				System.out.println("Bonus:"+balance[i]/10 +"$"
						+ " Air tickets are free on"
						+ " this Saving account");
				
			}
			
		}
	}
}
