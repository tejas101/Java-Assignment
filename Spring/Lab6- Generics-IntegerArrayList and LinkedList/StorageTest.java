public class StorageTest
{
    public static void individualTestString(StorageInterface is)
    {   System.out.println("Testing String Class");
        System.out.println("Testing " + is.getClass());
        System.out.println("Should 1 be\n\t0\n\t" + is.size());
        is.add("1");
        is.addAll(new String[]{"2", "3", "4"});
        System.out.println("Should 2 be\n\t4\n\t" + is.size());
        System.out.println("Should 3  be\n\t{1, 2, 3, 4}\n\t" + is);
        is.addAll(0, new String[]{"5", "6", "7"});
        System.out.println("Should 4 be\n\t{5, 6, 7, 1, 2, 3, 4}\n\t" + is);
        is.sort();
        System.out.println("Should 5 be\n\t{1, 2, 3, 4, 5, 6, 7}\n\t" + is);
        is.add(1, "3");
        System.out.println("Should 6 be\n\t{1, 3, 2, 3, 4, 5, 6, 7}\n\t" + is);
        System.out.println("Should 7 be\n\t1\n\t" + is.indexOf("3"));
        System.out.println("Should 8 be\n\t3\n\t" + is.lastIndexOf("3"));
        is.remove("3");
        System.out.println("Should 9 be\n\t{1, 2, 3, 4, 5, 6, 7}\n\t" + is);
        is.remove(6);
        System.out.println("Should 10 be\n\t{1, 2, 3, 4, 5, 6}\n\t" + is);
        System.out.println("Should 11 be\n\tfalse\n\t" + is.contains("7"));
        System.out.println("Should 12 be\n\ttrue\n\t" + is.contains("4"));
        System.out.println("Should 13 be\n\tfalse\n\t" + is.containsAll(new String[]{"4", "5", "6", "7"}));
        System.out.println("Should 14 be\n\ttrue\n\t" + is.containsAll(new String[]{"4", "5", "6"}));
        System.out.println("Should 15 be\n\t21\n\t" + is.hashCode());
        System.out.println("Should 16 be\n\tfalse\n\t" + is.removeAll(new String[]{"7", "8", "9"}));
        System.out.println("Should 17 be\n\t{1, 2, 3, 4, 5, 6}\n\t" + is);
        System.out.println("Should 18 be\n\ttrue\n\t" + is.removeAll(new String[]{"6", "7", "8"}));
        
        System.out.println("Should 19 be\n\t{1, 2, 3, 4, 5}\n\t" + is);
        System.out.println("Should 20 be\n\ttrue\n\t" + is.removeAll(new String[]{"4", "5"}));
        System.out.println("Should 21 be\n\t{1, 2, 3}\n\t" + is);
        is.clear();
        System.out.println("should 22 be\n\tempty\n\t" + is.isEmpty() + " : " + is);
        System.out.println("Should 23 be\n\t0\n\t" + is.hashCode());
    }
    public static void individualTestInteger(StorageInterface is)
    {   
    	System.out.println("Testing Integer Class");
        System.out.println("Testing " + is.getClass());
        System.out.println("Should 1 be\n\t0\n\t" + is.size());
        is.add(1);
        is.addAll(new Integer[]{2, 3, 4});
        System.out.println("Should 2 be\n\t4\n\t" + is.size());
        System.out.println("Should 3  be\n\t{1, 2, 3, 4}\n\t" + is);
        is.addAll(0, new Integer[]{5, 6, 7});
        System.out.println("Should 4 be\n\t{5, 6, 7, 1, 2, 3, 4}\n\t" + is);
        is.sort();
        System.out.println("Should 5 be\n\t{1, 2, 3, 4, 5, 6, 7}\n\t" + is);
        is.add(1, 3);
        System.out.println("Should 6 be\n\t{1, 3, 2, 3, 4, 5, 6, 7}\n\t" + is);
        System.out.println("Should 7 be\n\t1\n\t" + is.indexOf(3));
        System.out.println("Should 8 be\n\t3\n\t" + is.lastIndexOf(3));
        is.remove((Integer)3);
        System.out.println("Should 9 be\n\t{1, 2, 3, 4, 5, 6, 7}\n\t" + is);
        is.remove(6);
        System.out.println("Should 10 be\n\t{1, 2, 3, 4, 5, 6}\n\t" + is);
        System.out.println("Should 11 be\n\tfalse\n\t" + is.contains(7));
        System.out.println("Should 12 be\n\ttrue\n\t" + is.contains(4));
        System.out.println("Should 13 be\n\tfalse\n\t" + is.containsAll(new Integer[]{4, 5, 6, 7}));
        System.out.println("Should 14 be\n\ttrue\n\t" + is.containsAll(new Integer[]{4, 5, 6}));
        System.out.println("Should 15 be\n\t21\n\t" + is.hashCode());
        System.out.println("Should 16 be\n\tfalse\n\t" + is.removeAll(new Integer[]{7, 8, 9}));
        System.out.println("Should 17 be\n\t{1, 2, 3, 4, 5, 6}\n\t" + is);
        System.out.println("Should 18 be\n\ttrue\n\t" + is.removeAll(new Integer[]{6, 7, 8}));
        
        System.out.println("Should 19 be\n\t{1, 2, 3, 4, 5}\n\t" + is);
        System.out.println("Should 20 be\n\ttrue\n\t" + is.removeAll(new Integer[]{4, 5}));
        System.out.println("Should 21 be\n\t{1, 2, 3}\n\t" + is);
        is.clear();
        System.out.println("should 22 be\n\tempty\n\t" + is.isEmpty() + " : " + is);
        System.out.println("Should 23 be\n\t0\n\t" + is.hashCode());
    }

    public static void main(String[] args)
    {   System.out.println("----------------------------Testing Generic LinkeList----------------------------------------------------------------");
    	StorageInterface<String> illString = new GenericLinkedList<>();
    	StorageInterface<Integer> illInteger = new GenericLinkedList<>();
    	StorageInterface<Integer> illInteger1 = new GenericLinkedList<>();
        individualTestString(illString);
        individualTestInteger(illInteger);
        //StorageInterface ial = new IntegerArrayList();
        //individualTest(ial);
        individualTestInteger(illInteger1);
        System.out.println("Should 24 be\n\tfalse\n\t" + illInteger1.contentEquals(illInteger));
        illInteger1.add(1);
        System.out.println("Should 25 be\n\tfalse\n\t" + illInteger1.contentEquals(illInteger));
        illInteger.add(1);
        System.out.println("Should 26 be\n\ttrue\n\t" + illInteger1.contentEquals(illInteger));
        
        System.out.println("--------------Now Testing  Generic ArrayList-----------------------------------------------------------");
        
        
        StorageInterface<String> ialString = new GenericArryaList<>();
    	StorageInterface<Integer> ialInteger = new GenericArryaList<>();
    	StorageInterface<Integer> ialInteger1 = new GenericArryaList<>();
        individualTestString(ialString);
        individualTestInteger(ialInteger);
        //StorageInterface ial = new IntegerArrayList();
        //individualTest(ial);
        individualTestInteger(ialInteger1);
        System.out.println("Should 27 be\n\tfalse\n\t" + ialInteger1.contentEquals(ialInteger));
        ialInteger1.add(1);
        System.out.println("Should 28 be\n\tfalse\n\t" + ialInteger1.contentEquals(ialInteger));
        ialInteger.add(1);
        System.out.println("Should 29 be\n\ttrue\n\t" + ialInteger1.contentEquals(ialInteger));
         
    }


}
