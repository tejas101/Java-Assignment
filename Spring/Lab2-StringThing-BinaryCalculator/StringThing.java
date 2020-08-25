public class StringThing
{
    private static boolean equalityTest1(String a, String b)
    {
        return a == b;
    }

    private static boolean equalityTest2(String a, String b)
    {
        return a.equals(b);
    }

    private static boolean equalityTest3(String a, String b)
    {
        return System.identityHashCode(a) == System.identityHashCode(b);
    }

    private static boolean equalityTest4(String a, String b)
    {
        if(a.length() == 0 && b.length() == 0)
        {
            return true;
        }
        else
        {
            if(a.length() == 0 || b.length() == 0)
            {
                return false;
            }
            if(a.charAt(0) == b.charAt(0))
            {
                return equalityTest4(a.substring(1), b.substring(1));
            }
            else
            {
                return false;
            }
        }
    }

    private static boolean equalityTest5(String a, String b)
    {
        return a.hashCode() == b.hashCode();
    }

    public static void main(String[] args)
    {
        String abcV1 = "abc";
        String abcV2 = "a" + "b" + "c";
        String abcV3 = "abcd".substring(0, abcV1.length());
        String abcV4 = "" + abcV2;
        String abcV5 = "a" + (char)98 + 99;
        String abcV6 = new String("abc");
        String abcV7 = abcV3.intern();
        String abcv8 = abcV6;

        // using abcV1 as first parameter ...
        // all possible trues for equalityTest1
		/* equalityTest1 compare the memory location of the Strings.
		
		True : 	abcV2 :: The String memory location of both abcV1 and abcV2 are same i.e. 
						 the String intern pool.
		
				abcV7 :: Even though String abcV3 and String abcV1 have the same content,
				String abcV3 resides on a whole different memory location, so technically
				the output should have been false'. But since we have explicitly defined 
				.intern() with abcV3, the String location points to the intern pool where
				abcV1 resides, hence true.
				
		False : abcV3, abcV4, abcV5, abcV6, abcV8
		*/

        // all possible trues for equalityTest2
		/* equalityTest1 compares the content of the Strings.
			True : abcV2, abcV3, abcV4, abcV6, abcV7, abcV8
			False: abcV5
			
			
        // all possible trues for equalityTest3
            /*For  System.identityHashCode(obj),the content are not
			taken in consideration, and hence, identityHashCode(obj)
			will be different for 2 different Strings even if they hold
			same value but hashcode will return the same hashcode in this case.
			True: abcV2,abcV7
			False : abcV3, abcV4, abcV5, abcV6, abcV8*/
			
        // all possible trues for equalityTest4
         /* True:abcV2,abcV7,abcV3, abcV4,abcV6, abcV8
		 False:abcV5
		 */
        // all possible trues for equalityTest5
		/*hashCode() calculates the hashcode of the content in the string and
		hence if 2 differnt Strings has same content, then hascode will be same
		True:All except abcV5

    }
}