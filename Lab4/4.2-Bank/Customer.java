/* Customer.java  
  *Version: 
  *     1 
  * 
  * Revisions: 
  *     
  *     Tejas Raval MS-CS 2018 RIT tr7550@rit.edu
  *      
  */
/** 
  *This class is used to store all the details
  *of the users account 
  *@author      Tejas Raval 
  * 
  */ 
public class Customer extends RITBank 
implements accountIntrest {
    static String[] name = new String[arraySize];
    static int[] accountNo = new int[arraySize];
    static double[] balance = new double[arraySize];
    static double[] amountOwed = new double[arraySize];
    static double[] depositeAmount = new double[arraySize];
    static double[] intrest = new double[arraySize];
    static String[] accountType = new String[arraySize];
    /** 
      *openAccount() method is used to 
      *create a new account in the bank
      *@param accChoice Type of account, eg : 
      *                 Saving or checking etc
      *@param custName  Name of the customer
      *@param depAmount Amount to me deposited for
      *                 saving and checking account
      *@param i         Counter of the total accounts.
      *@param randomInt Account number 
      */
    public void openAccount(int accChoice, String custName,
    		double depAmount, int i, int randomInt) {
        name[i] = custName;
        if (accChoice == 0)
            accountType[i] = "Saving";
        else if (accChoice == 1)
            accountType[i] = "Checking";
        else if (accChoice == 2) {
            accountType[i] = "Liability";
            amountOwed[i] = 0;
        }

        depositeAmount[i] = depAmount;
        balance[i] += depAmount;
        accountNo[i] = randomInt;

        System.out.println("Account created "
        		+ "sucessfully!");
        System.out.println("Your account number "
        		+ "is " + randomInt);


    }
    /**
     *typeOfAccount() will retrun the type of account
     * eg: Saving, checking etc
     *@param    ID  Account number to be investigated
     *@return       Number for the account type
     */
    public int typeOfAccount(int ID) {
        int index = searchAccount(ID);

        if (index > -1) {
            if (accountType[index] == "Saving") {
                return 1;
            } else if (accountType[index] == "Checking") {
                return 2;
            } else if (accountType[index] == "Liability") {
                return 3;
            }
        }
        return -1;
    }
    /**
     *searchAccount() will check if the account is present
     *@param    ID     Account to be searched for
     *@return          Index where account is found or 
     *                 else -1
     */
    public int searchAccount(int ID) {
        int index = 0;
        for (index = 0; index < arraySize; index++) {
            if (accountNo[index] == ID) {

                return index;
            }


        }
        return -1;
    }
    public double calculateIntrest(double intrest,
    		double balance, int time) {
        double rate = ((intrest / 100) * balance) * time;
        return Math.round(rate * 100.0) / 100.0;
    }
    /**
      *deleteAccount() will delete the specific 
      *account 
      *@param      ID   ID/Number of the account
      * to be deleted.
      */
    public void deleteAccount(int ID) {

        if (searchAccount(ID) > -1) {

            for (int index = searchAccount(ID); 
            		index < arraySize - 1; index++) {
                accountNo[index] = accountNo[index + 1];
                name[index] = name[index + 1];
                balance[index] = balance[index + 1];
                depositeAmount[index] = depositeAmount[index + 1];
                accountType[index] = accountType[index + 1];
                amountOwed[index] = amountOwed[index + 1];


            }

            --Customer.counter;
            System.out.println("Account deleted");
        } else System.out.println("No account for "
        		+ "ID:" + ID + "." +
            " Please check the number entered");

    }
}