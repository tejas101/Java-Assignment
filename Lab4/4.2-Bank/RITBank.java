/* RITBank.java  
  *Version: 
  *     1 
  * 
  * Revisions: 
  *     
  *     Tejas Raval MS-CS 2018 RIT tr7550@rit.edu
  *     
  */
/** 
  *This program is a banking application where you can
  *create/delete a bank account. Also, you can make
  *transcations like credit debit etc. This program
  *has 3 type of account : Saving account, Checking
  *account and Liability(credit) account
  * 
  *@author      Tejas Raval 
  */ 
import java.util.Scanner;
import java.util.Random;

interface transcation {
	/**
	 *creditAccount() method is used to make credit transaction
	 *on 3 accounts
	 *@param    creditAmount Amount for the transaction
	 *@param    creditID     Account on which this transaction 
	 *                       will happen
	 */
    void creditAccount(double creditAmount, int creditID);
    /**
	 *debitAccount() method is used to make debit transaction
	 *on 3 accounts
	 *@param    debitAcc     Amount for the transaction
	 *@param    debitAmt     Account on which this transaction 
	 *                       will happen
	 */
    void debitAccount(int debitAcc, double debitAmt);
    /**
      *geTimeSummary() will return the summary of all the accounts
      *after the a specific time interval
      * 
      *@param   time  Time interval in months in which we want
      *                the summary.
      */
    void geTimeSummary(int time);
}
interface accountIntrest {
	/**
	  *calculateIntrest() will calculate interest on an account
	  *for a time interval.
	  *
	  *@param     intrest  The rate on intrest on the account
	  *@param     balance  The balance in the account
	  *@param     time     The time interval in month
	  *@return
	  */
    double calculateIntrest(double intrest, double balance, int time);
}
public class RITBank {
    static int counter = 0;//To keep the current count of the accounts
    static int arraySize = 5;//Number of accounts which can be created
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        String choice;
        int ch = 0;
        Customer cust = new Customer();
        Credit crdObj = new Credit();
        Checking check = new Checking();
        Saving save = new Saving();
        Summary sum = new Summary();
        Random random = new Random();
        //Random number in range of 55 to 100 for account number
        int randomInt = random.nextInt(100 - 55 + 1) + 55;
     

        do {
            System.out.println();
            System.out.println("---------------");
            System.out.println("Main Menu");
            System.out.println("time - pass certain"
            		+ " amount of time");
            System.out.println("open - open a new account");
            System.out.println("close - close an account");
            System.out.println("credit - credit an account");
            System.out.println("debit - debit an account");
            System.out.println("summary - display current"
            		+ " bank accounts");
            System.out.println("exit - exit program");
            System.out.println("What do you want to do?:");
            choice = sc.nextLine();
            if (choice.equalsIgnoreCase("time"))
                ch = 1;
            else if (choice.equalsIgnoreCase("open"))
                ch = 2;
            else if (choice.equalsIgnoreCase("close"))
                ch = 3;
            else if (choice.equalsIgnoreCase("credit"))
                ch = 4;
            else if (choice.equalsIgnoreCase("debit"))
                ch = 5;
            else if (choice.equalsIgnoreCase("summary"))
                ch = 6;
            else if (choice.equalsIgnoreCase("exit"))
                ch = 7;
            switch (ch) {
                case 1:
                	if(counter!=0)
                	{
                    System.out.println("Enter time");
                    int time = sc.nextInt();
                    sc.nextLine();
                    crdObj.geTimeSummary(time);
                    check.geTimeSummary(time);
                    save.geTimeSummary(time);
                	}
                	else System.out.println("No accounts are created yet");
                    break;

                case 2:
                    {
                        if (counter != 5) {
                            int accChoice;
                            double depAmount = 0;
                            String custName;
                            System.out.println("0 - for savings");
                            System.out.println("1 - for checking");
                            System.out.println("2 - for credit card?");
                            accChoice = sc.nextInt();
                            sc.nextLine();
                            if(accChoice==1||accChoice==2||accChoice==0)
                            {
                            System.out.println("Customer Name");
                            custName = sc.nextLine();
                            
                            if (accChoice != 2) {
                                System.out.println("Amount to be deposited");
                                depAmount = sc.nextDouble();
                                sc.nextLine();
                            }
                            cust.openAccount(accChoice, custName,
                            		depAmount, counter++, randomInt++);
                            }
                            else System.out.println("Wrong option");
                        } else {

                            System.out.println("Account limit "
                            		+ "reached");
                        }
                    }
                    break;
                case 3:
                	if(counter!=0)
                	{
                    System.out.println("Enter the account number"
                    		+ " to be deleted");
                    int ID = sc.nextInt();
                    sc.nextLine();
                    cust.deleteAccount(ID);
                	}
                	else System.out.println("No accounts are created yet.");
                    break;
                case 4:
                	if(counter!=0)
                	{
                    System.out.println("Enter the  account number");
                    int creditID = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Enter the amount");
                    double creditAmount = sc.nextDouble();
                    sc.nextLine();
                    int valueCredit = cust.typeOfAccount(creditID);
                    if (valueCredit == 1) {
                        save.creditAccount(creditAmount, creditID);
                    } else if (valueCredit == 2) {
                        check.creditAccount(creditAmount, creditID);
                    } else if (valueCredit == 3)
                        crdObj.creditAccount(creditAmount, creditID);

                    else System.out.println("Wrong account "
                    		+ "number. Try again");
                	}
                	else System.out.println("No accounts are created yet");
                    break;
                case 5:
                    {
                    	if(counter!=0)
                    	{
                        System.out.println("Enter the account to "
                        		+ "be debited");
                        int debitAcc = sc.nextInt();
                        sc.nextLine();
                        System.out.println("Enter the amount to "
                        		+ "be debited");
                        double debitAmt = sc.nextDouble();
                        int valueDebit = cust.typeOfAccount(debitAcc);
                        if (valueDebit == 1) {
                            save.debitAccount(debitAcc, debitAmt);
                        } else if (valueDebit == 2) {
                            check.debitAccount(debitAcc, debitAmt);
                        } else if (valueDebit == 3)
                            crdObj.debitAccount(debitAcc, debitAmt);
                        else System.out.println("Wrong account "
                        		+ "number. Try again");
                    	}
                    	else System.out.println("No accounts created yet");
                    }
                    break;
                case 6:
                    {
                        if (counter != 0) {
                            sum.getSummary();
                        } else
                            System.out.println("No accounts "
                            		+ "created yet.");
                    }
                    break;
                case 7:
                    System.out.println("Thanks");
                    System.exit(0);
                    break;
            }
            ch = 0;
        }
        while (ch != 7);



    }

}