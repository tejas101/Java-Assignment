/* StorageTest.java  
 *Version: 
 *     1 
 * 
 * Revisions: 
 *     
 *     Tejas Raval MS-CS 2018 RIT tr7550@rit.edu
 *     Lipisha Chowdhry MS-CS 2018 RIT lc2919@rit.edu
 */
 /** 
 *This program implements test cases for Generic ArrayList and Linked List
 * 
 *@author      Tejas Raval 
 *@author      Lipisha Chowdhry
 */
public class StorageTest
{
    public static void individualTestString(StorageInterface is)  
    {   System.out.println("Testing String Class");
        System.out.println("Adding even lenght String.True should be the output");
        System.out.println(is.add("CS"));
        System.out.println("Adding odd lenght String. Exception will be thrown");
        System.out.println(is.add("CdS"));
        System.out.println("After Exception, storage should be : CS");
        System.out.println(is);
        System.out.println("Adding all even lenght values : qq,ee");
        System.out.println(is.addAll(new String[]{"qq","ee"}));
        System.out.println("Now Storage should be CS-->qq-->ee");
        System.out.println(is);
        System.out.println("Adding all odd lenght values : qrq,ere. Exception will be thrown");
        System.out.println(is.addAll(new String[]{"qrq","ere"}));
        System.out.println("Storage should be CS-->qq-->ee");
        System.out.println(is);
        System.out.println("Adding one odd lenght value and one even lenght value : qrrq,ere. Exception will be thrown");
        System.out.println(is.addAll(new String[]{"qrrq","ere"}));
        System.out.println("Storage should be CS-->qq-->ee");
        System.out.println(is);
        System.out.println("Trying to set 1st value to and odd value: Exception should be thrown");
        System.out.println("Return value "+is.set(0, "y"));
        System.out.println("Storage should be CS-->qq-->ee");
        System.out.println(is);
        System.out.println("Trying to set 1st value to and even value.");
        System.out.println("Return value "+is.set(0, "tt"));
        System.out.println("Storage should be tt-->qq-->ee");
        System.out.println(is);
    }
    
    public static void individualTestInteger(StorageInterface is)  
    {   
    	System.out.println("Testing Integer Class");
        System.out.println("Adding even Integer.True should be the output");
        System.out.println(is.add(2));
        System.out.println("Adding odd Integer. Exception will be thrown");
        System.out.println(is.add(3));
        System.out.println("After Exception, storage should be : 2");
        System.out.println(is);
        System.out.println("Adding all even  values : 6,8");
        System.out.println(is.addAll(new Integer[]{6,8}));
        System.out.println("Now Storage should be 2-->6-->8");
        System.out.println(is);
        System.out.println("Adding all odd  values : 3,5. Exception will be thrown");
        System.out.println(is.addAll(new Integer[]{3,5}));
        System.out.println("Storage should be 2-->6-->8");
        System.out.println(is);
        System.out.println("Adding one odd  value and one even  value : 88,99. Exception will be thrown");
        System.out.println(is.addAll(new String[]{"qrrq","ere"}));
        System.out.println("Storage should be 2-->6-->8");
        System.out.println(is);
        System.out.println("Trying to set 1st value to and odd value: Exception should be thrown");
        System.out.println("Return value "+is.set(0, 5));
        System.out.println("Storage should be  2-->6-->8");
        System.out.println(is);
        System.out.println("Trying to set 1st value to and even value.");
        System.out.println("Return value "+is.set(0, 66));
        System.out.println("Storage should be 66-->6-->8");
        System.out.println(is);
    }

    public static void main(String[] args)  
    {   System.out.println("----------------------------Testing Generic LinkeList----------------------------------------------------------------");
    	StorageInterface<String> illString = new GenericLinkedListException<>();
    	StorageInterface<Integer> illInteger = new GenericLinkedListException<>();
    	StorageInterface<Integer> illInteger1 = new GenericLinkedListException<>();
        individualTestString(illString);
    	//illString.add("44");
        //illString.add("4c4");
       // System.out.println(illString.addAll(new String[]{"Pdd","AB"}));
        /*illString.add("dd");
        //illString.add("ddd");
        illString.add("drrd");
        System.out.println(illString.set(0, "tgt"));
        System.out.println(illString);
        individualTestInteger(illInteger);
        //StorageInterface ial = new IntegerArrayList();
        //individualTest(ial);
        individualTestInteger(illInteger1);
        System.out.println("Should 24 be\n\tfalse\n\t" + illInteger1.contentEquals(illInteger));
        illInteger1.add(1);
        System.out.println("Should 25 be\n\tfalse\n\t" + illInteger1.contentEquals(illInteger));
        illInteger.add(1);
        System.out.println("Should 26 be\n\ttrue\n\t" + illInteger1.contentEquals(illInteger));*/
        
        System.out.println("--------------Now Testing  Generic ArrayList-----------------------------------------------------------");
        
    	StorageInterface<Integer> ialInteger = new GenericArrayListException<>();
    	
    	individualTestInteger(ialInteger);
        /*individualTestInteger(ialInteger);
        //StorageInterface ial = new IntegerArrayList();
        //individualTest(ial);
        individualTestInteger(ialInteger1);
        System.out.println("Should 27 be\n\tfalse\n\t" + ialInteger1.contentEquals(ialInteger));
        ialInteger1.add(1);
        System.out.println("Should 28 be\n\tfalse\n\t" + ialInteger1.contentEquals(ialInteger));
        ialInteger.add(1);
        System.out.println("Should 29 be\n\ttrue\n\t" + ialInteger1.contentEquals(ialInteger));*/
         
    }


}
