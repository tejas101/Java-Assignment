/* Summary.java  
 *Version: 
 *     1 
 * 
 * Revisions: 
 *     
 *     Tejas Raval MS-CS 2018 RIT tr7550@rit.edu
 *      
 */
/** 
  * 
  *@author      Tejas Raval 
  */

class Summary {

   /**
     *getSummary() is used to present a 
     *summary of all the accounts 
     */
    public void getSummary() {

        for (int i = 0; i < Customer.counter; i++) {
            System.out.println();
            System.out.println("---------------");
            System.out.println("Customer Name:" +
            Customer.name[i]);
            System.out.println("Account Number:" + 
            Customer.accountNo[i]);

            System.out.println("Account Type:" + 
            Customer.accountType[i]);
            if (Customer.accountType[i] == "Liability") {
                System.out.println("Amount Owned:" +
            Customer.amountOwed[i] + "$");
            } else {
                System.out.println("Balance:" + 
            Customer.balance[i] + "$");
            }
        }
    }
}