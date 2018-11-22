import java.util.Scanner;

/* Zoo.java  
 *Version: 
 *     1 
 * 
 * Revisions: 
 *     
 *     Tejas Raval MS-CS 2018 RIT tr7550@rit.edu
 */
/** 
 *This program is a display for Java concepts of inheritance,
 *abstract class, interface. Here a simple example of
 *zoo is used which has Polar Bear, Alpaca, Aardvark etc.
 * 
 *@author      Tejas Raval 
 */
abstract class Animal {
    private String name = "";
    private String colour = "";
    private String type = "";
    Animal() {
        super();
    }
    /**
     *Constructor for setting basic details of a animal
     *@param      name       Name of the species
     *@param      colour     Color of the species
     *@param      Type       Which animal is this
     */
    Animal(String name, String colour, String type) {
        this.name = name;
        this.colour = colour;
        this.type = type;

    }
    /**
     *myFoodType() will give details of the food type of 
     *the animal.
     *@param      foodType   food habbits of Animal
     */
    void foodType(String foodType) {
        System.out.println("Hello, I am of type " + foodType);
    }
    /**
     *homeSatus() will print the homeStatus of the 
     *animal
     *@param   home  home of the
     *Carnivore or Herbivore
     */
    void homeStatus(String home) {
        System.out.println(" My home is " + home);
    }
    /**
     *homeSatus() will print the homeStatus of the 
     *Omnivore
     *@param   home1  1st home of the animal
     *@param   home2  2nd home of the animal 
     */
    void homeStatus(String home1, String home2) {

        System.out.println("My home can be " + home1 +
            " or " + home2);
    }
    /**
     *info() will show basic information of the animal.
     *@param   animal  ID of the animal
     */
    void info(int animal) {
        System.out.println("Hello, I am " + type + ". " +
            "My Name is " + name + ". My colour is  " +
            colour + ". We are total " + Zoo.count[animal] +
            ". Our ID is" + Zoo.ID[animal]);
    }
    abstract void fact();
}
/**
 *Interface Omnivore will have properties which generally 
 *Omnivore share. 
 */
interface Omnivore {
    String species = "Omnivore";
    /**
     *digestionSystem() give information about Omnivore's
     *digestive track
     */
    void digestionSystem();

}
/**
 *Interface Herbivore will have properties which generally 
 *Herbivore share. 
 */
interface Herbivore {
    String home = "Grasslands";
    String activity = "Graze";
    String species = "Herbivore";
    /**
     *hasHorns() shows the information about 
     *the horns of herbivore animals if they have.
     */
    void hasHorns();
}
/**
 *Interface Carnivore will have properties which generally 
 *Carnivore share. 
 */
interface Carnivore {
    String home = "Cave";
    String activity = "Hunt";
    String species = "Carnivore";
    /**
     *getJawStrcutre() shows the information
     *related to Jaws of the Carnivore animal.
     */
    void getJawsStrcutre();
}

/**
 *In the classes below with the animal's name,
 *a parameterized constructors as used to set 
 *basic values of animals. Super will refer
 *to constructors is the Animal class and
 *every single species eg:Polar Bear inherits
 *Animal class.
 *  
 *
 */


class PolarBear extends Animal implements Carnivore {

    static String type = "Polar Bear";
    static String colour = "White";

    PolarBear(String name, String colour, String type) {

        super(name, colour, type);

    }

    @Override
    void fact() {

        System.out.println("Polar bears are the largest"
        		+ " land carnivores in the world");
    }



    public void getJawsStrcutre() {

        System.out.println("Larger teeth such as incisors and " +
            "canines are fixed rostrally the molars and premolars are " +
            "set caudally inside the mouth.");
    }
}

class Alpaca extends Animal implements Herbivore {

    static String type = "Alpaca";
    static String colour = "Cotton-White";

    Alpaca(String name, String colour, String type) {

        super(name, colour, type);

    }

    @Override
    void fact() {

        System.out.println("Adorable, docile and soft," +
            " alpacas are prized as pets and cattle"
            + " around the world.");
    }

    public void hasHorns() {

        System.out.println("Alpaca has no horns but"
        		+ " ears looks like horns. ");
    }
}


class Aardvark extends Animal implements Omnivore {
    static String type = "Aardvark";
    static String colour = "Pinkish-white";
    Aardvark(String name, String colour, String type) {

        super(name, colour, type);

    }

    @Override
    void fact() {

        System.out.println("The aardvark is a medium-sized," +
            " nocturnal mammal native to Africa. ");
    }
    public void digestionSystem() {

        System.out.println("Aardvark have digestion "
        		+ "system which can digest" +
            " meat and grass. But, they can't digest plant seeds");
    }

}

class GoldFish extends Animal implements Omnivore {
    static String type = "GoldFish";
    static String colour = "Reddish-Gold";
    static String home = "Sea/Aquarium";
    GoldFish(String name, String colour, String type) {

        super(name, colour, type);

    }

    @Override
    void fact() {

        System.out.println("Goldfish can get used to humans and will" +
            " eventually stop considering them a threat.");
    }
    public void digestionSystem() {

        System.out.println("The metabolism of a goldfish will slow " +
            "down significantly in cold water, almost like "
            + "they are hibernating.");
    }

}
abstract class Tree {
    private String name = "";
    private String colour = "";
    private String type = "";
    Tree() {
        super();
    }
    /**
     *Constructor for setting basic details of a animal
     *@param      name       Name of the species
     *@param      colour     Color of the species
     *@param      Type       Which tree is this
     */
    Tree(String name, String colour, String type) {
        this.name = name;
        this.colour = colour;
        this.type = type;

    }
    /**
     *info() will show basic information of the animal.
     *@param   count  number of trees
     *@param   ID     Id of the tree
     */
    void info(int count, int ID) {
        System.out.println("Hello, I am " + type + ". " +
            "My Name is " + name + ". My colour is  " +
            colour + ". We are total " + count +
            ". Our ID is " + ID);
    }
    abstract void fact();

}
/**
 *In the classes below with the Tree's name,
 *a parameterized constructors as used to set 
 *basic values of Tree. Super will refer
 *to constructors is the Tree class.
 *  
 *
 */
class PalmTree extends Tree {
    static String type = "PalmTree";
    static String colour = "Green-Brown";

    PalmTree(String name, String colour, String type) {

        super(name, colour, type);

    }

    @Override
    void fact() {

        System.out.println("The tallest palm tree can grow "
        		+ "up to 197 feet tall!");
    }
}
/**
 *Main class of the program is in class Zoo and an object
 *for every animal is created here.Also, basic information
 *of animal like name, color etc is passed to the respective
 *class from here.
 */
public class Zoo {

    /**
     *askHungry() will ask Animal if he is hungry.
     */
    void askHungry(String choice, int animalChoice) {


        if (choice.equalsIgnoreCase("yes")) {
            if (locationList[animalLocation[animalChoice]] == locationList[1])
                System.out.println("You are in Hunt/Graze"
                		+ " area. Search food there");
            else {
                System.out.println("I am moving you to"
                		+ " Hunt/Graze area");
                animalLocation[animalChoice] = 1;
            }
        } else if (choice.equalsIgnoreCase("no")) {
            if (locationList[animalLocation[animalChoice]]
            		!= locationList[0]) {
                System.out.println("Now its time to go home."
                		+ " I am moving you.");
                animalLocation[animalChoice] = 0;
            } else {
                System.out.println("Ok. Stay at home.");
            }
        } else {
            System.out.println("Wrong input.");

        }

    }
    static int totalAnimal = 4;
    static int totalLocation = 4;
    static int[] animalLocation = new int[totalAnimal];
    static int[] ID = {1,2,3,4,5};
    static int[] count = {4,20,34,897};
    static String[] list = {"PolarBear","Alpaca","Aardvark","GoldFish"};
    static String[] locationList = {"Home","Hunt/Graze area","Medical "
    		+ "Center","Anima Safari"};

    void getLocation(int option) {
        System.out.println("I am at " + 
    locationList[animalLocation[option]]);
    }
    void moveAnimal(int animal, int location) {
        if (animalLocation[animal] == location)
            System.out.println("I am already there. "
            		+ "Select some new place");
        else {
            animalLocation[animal] = location;
            System.out.println("Thanks for shifiting");
        }

    }
    /**
     *The main program.
     *
     *@param    args    command line arguments (ignored)
     */
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        Zoo z = new Zoo();
        System.out.println("-------------------------------");

        PolarBear Pol = new PolarBear("Polu", PolarBear.colour,
        		PolarBear.type);
        Pol.info(0);
        Pol.foodType(Carnivore.species);
        Pol.fact();
        Pol.getJawsStrcutre();
        Pol.homeStatus(Carnivore.home);

        System.out.println("-------------------------------");

        Alpaca Gaz = new Alpaca("Nemo", Alpaca.colour, Alpaca.type);
        Gaz.info(1);
        Gaz.foodType(Herbivore.species);
        Gaz.fact();
        Gaz.hasHorns();
        Gaz.homeStatus(Herbivore.home);

        System.out.println("-------------------------------");

        Aardvark Adu = new Aardvark("Addo", Aardvark.colour,
        		Aardvark.type);
        Adu.info(2);
        Adu.foodType(Omnivore.species);
        Adu.fact();
        Adu.digestionSystem();
        Adu.homeStatus(Carnivore.home, Herbivore.home);

        System.out.println("-------------------------------");

        GoldFish Goli = new GoldFish("Goli", GoldFish.colour,
        		GoldFish.type);
        Goli.info(3);
        Goli.foodType(Omnivore.species);
        Goli.fact();
        Goli.digestionSystem();
        Goli.homeStatus(GoldFish.home);

        System.out.println("-------------------------------");

        PalmTree Palm = new PalmTree("Palm", PalmTree.colour, PalmTree.type);
        Palm.info(7, 5);
        Palm.fact();

        int ch;
        do {

            System.out.println();
            System.out.println("---------------");
            System.out.println("Main Menu");
            System.out.println("1-Get location of animals");
            System.out.println("2-Move them to new location");
            System.out.println("3-Send them to eat or home");
            System.out.println("4-Exit");
            System.out.println("What do you want to do?:");
            ch = sc.nextInt();
            sc.nextLine();
            if (0 < ch && ch > 5) {
                System.out.println("Wrong input");

            }
            switch (ch) {
                case 1:
                    {
                        System.out.println("Enter the name of the animal");
                        for (int i = 0; i < list.length; i++) {
                            System.out.println((i + 1) + "-" + list[i]);
                        }
                        int animalChoice = sc.nextInt();

                        if (0 < animalChoice && animalChoice < 6) {
                            z.getLocation(animalChoice - 1);
                        } else System.out.println("Wrong input");

                        break;

                    }
                case 2:
                    {
                        System.out.println("Enter the name of the animal");
                        for (int i = 0; i < list.length; i++) {
                            System.out.println((i + 1) + "-" + list[i]);
                        }
                        int animalChoice = sc.nextInt();
                        sc.nextLine();
                        if (0 < animalChoice && animalChoice < totalAnimal) {
                            System.out.println("Enter the location");
                            for (int i = 0; i < locationList.length; i++) {
                                System.out.println((i + 1) + "-" + 
                            locationList[i]);
                            }
                            int location = sc.nextInt();
                            sc.nextLine();

                            if (0 < location && location < totalLocation) {
                                z.moveAnimal(animalChoice - 1, location - 1);
                            } else System.out.println("Wrong input");
                        } else System.out.println("Wrong input");
                        break;
                    }
                case 3:
                    {
                        System.out.println("Enter the name of the animal");
                        for (int i = 0; i < list.length; i++) {
                            System.out.println((i + 1) + "-" + list[i]);
                        }
                        int animalChoice = sc.nextInt();
                        sc.nextLine();
                        if (0 < animalChoice && animalChoice < totalAnimal) {
                            System.out.println("Are you hungry:Yes/No");
                            String choice = sc.nextLine();
                            z.askHungry(choice, animalChoice - 1);
                        } else System.out.println("Wrong input");
                        break;
                    }
                case 4:
                    {
                        System.out.println("Thanks");
                        System.exit(0);
                    }

                    ch = 0;
            }
        } while (ch != 4);
    }
}