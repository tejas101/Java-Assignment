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
  *zoo is used which has 4 animals: Tiger, Lion, Giraffe and
  *Gazelle.
  * 
  *@author      Tejas Raval 
  */
abstract class Animal {
    private String name = "";
    private String colour = "";
    private String type="";
    String status="I am at home now. ";
    /**
     *hungerStatus method shows the hunt/graze status of the
     *Herbivore or Carnivore 
     *@param      activity  Way in which species
     *                       get food.
     * 
     */
    public void hungerStatus(String activity) {
        System.out.println("Thanks for sending me to " + activity);
        System.out.println("I am done with "+activity+"ing. ");
    }
    /**
     *hungerStatus method shows the hunt and graze status of the
     *Omnivore. 
     *Herbivore or Carnivore 
     *@param      activity1  1st way in which species
     *                       get food.
     *@param      activity2  2nd way in which species
     *                       get food.
     * 
     */
    public void hungerStatus(String activity1,String activity2) {
        System.out.println("Thanks for sending me to " 
    + activity1 +" and "+activity2);
        System.out.println("I am done with "+activity1+"ing and "+ activity2+"ing");
    }

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
    void foodType (String foodType) {
        System.out.println("Hello, I am of type " + foodType);
    }
    /**
      *homeSatus() will print the homeStatus of the 
      *animal
      *@param   home  home of the
      *Carnivore or Herbivore
      */
    void homeStatus(String home) {
        System.out.println(status+"And"
        		+ " my home is " + home);
    }
    /**
     *homeSatus() will print the homeStatus of the 
     *Omnivore
     *@param   home1  1st home of the animal
     *@param   home2  2nd home of the animal 
     */
    void homeStatus(String home1, String home2) {

        System.out.println(status+ home1+ 
        		" or "+home2+" can be my home.");
    }
    /**
      *info() will show basic information of the animal.
      */
    void info() {
        System.out.println("Hello, I am " + type + ". " +
            "My Name is " + name + ". My colour is  " + colour);
    }
    abstract void fact();
}
/**
 *Interface Omnivore will have properties which generally 
 *Omnivore share. 
 */
interface Omnivore {
	String species="Omnivore";
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
interface Herbivore  {
     String home = "Grasslands";
	 String activity="Graze";
	 String species="Herbivore";
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
	 String activity="Hunt";
	 String species="Carnivore";
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

class Ferret extends Animal implements Carnivore  {
	static String type="Ferret";
	static String colour="Black";

	Ferret(String name, String colour,String type) {

        super(name, colour, type);

    }

    @Override
    void fact() {

        System.out.println("Ferrets LOVE their sleep and"
        		+ " usually doze for 14-18 hours per day.");
    }
   public void getJawsStrcutre() {

        System.out.println("A Ferret’s teeth generally protrude below "
        		+ "his lips  and therefore are in full view. ");
    }
   
}
class PolarBear extends Animal implements Carnivore {
	
	static String type="Polar Bear";
	static String colour="White";
	
	PolarBear(String name, String colour, String type) {

        super(name, colour, type);

    }

    @Override
    void fact() {

        System.out.println("Polar bears are the largest land carnivores in the world");
    }

    

    public void getJawsStrcutre() {

        System.out.println("Larger teeth such as incisors and "
        + "canines are fixed rostrally the molars and premolars are "
        + "set caudally inside the mouth.");
    }
}

class Aardvark extends Animal implements Omnivore   {
	static String type="Aardvark";
	static String colour="Pinkish-white";
	Aardvark(String name, String colour,String type) {

        super(name, colour, type);

    }

	 @Override
    void fact() {

        System.out.println("The aardvark is a medium-sized,"
        		+ " nocturnal mammal native to Africa. ");
    }
   public void digestionSystem() {

        System.out.println("Aardvark have digestion system which can digest"
        		+ " meat and grass. But, they can't digest plant seeds");
    }
   
}

class Camel extends Animal implements Herbivore {

	static String type="Camel";
	static String colour="Yellow-Brown";
	static String home="Desert";
	Camel(String name, String colour, String type) {

        super(name, colour, type);

    }

    @Override
    void fact() {

        System.out.println("Fact: Camel is called ship of the desert.");
    }

    public void hasHorns() {

        System.out.println("Camel has no horns but a hump on its back");
    }
}

class Skunk extends Animal implements Omnivore   {
	
	static String type="Skunk";
	static String colour="Black-white";
	Skunk(String name, String colour,String type) {

        super(name, colour, type);

    }

	 @Override
    void fact() {

        System.out.println("Skunk do not hibernate, but they tend"
        		+ " to be inactive during the coldest months in winter");
    }
   public void digestionSystem() {

        System.out.println("Skunks do not secrete amylase, an enzyme "
        		+ "used to break down carbohydrates, in their saliva.");
    }
   
}

class Alpaca extends Animal implements Herbivore {
	
	static String type="Alpaca";
	static String colour="Cotton-White";
	
	Alpaca(String name, String colour, String type) {

        super(name, colour, type);

    }

    @Override
    void fact() {

        System.out.println("Adorable, docile and soft,"
        		+ " alpacas are prized as pets and cattle around the world.");
    }
    
    public void hasHorns() {

        System.out.println("Alpaca has no horns but ears looks like horns. ");
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
  *The main program.
  *
  *@param    args    command line arguments (ignored)
  */
	    Scanner sc = new Scanner(System.in);
	/**
	  *askHungry() will ask Animal if he is hungry.
	  */  
	int askHungry() {
		
		System.out.println("Are you hungry:Yes/No");
		String choice=sc.nextLine();
		if(choice.equalsIgnoreCase("yes"))
			return 1;
		else if(choice.equalsIgnoreCase("no"))
			return -1;
		else 
		{
			System.out.println("Enter only yes or no");
			askHungry();
		}
		return -1;
	}
    public static void main(String args[]) {
    	Zoo z = new Zoo();
    	Ferret Fero = new Ferret("Katya", Ferret.colour,Ferret.type);
    	Fero.info();
    	Fero.foodType(Carnivore.species);
    	Fero.fact();
    	Fero.getJawsStrcutre();
        if(z.askHungry()==1)
        	Fero.hungerStatus(Carnivore.activity);
        Fero.homeStatus(Carnivore.home);

        System.out.println("-------------------------------");
        PolarBear Pol = new PolarBear("Polu", PolarBear.colour, PolarBear.type);
        Pol.info();
        Pol.foodType(Carnivore.species);
        Pol.fact();
        Pol.getJawsStrcutre();
        if(z.askHungry()==1)
        	Pol.hungerStatus(Carnivore.activity);
        Pol.homeStatus(Carnivore.home);
        System.out.println("-------------------------------");
        
        Camel Jiru = new Camel("Lambu", Camel.colour,Camel.type);
        Jiru.info();
        Jiru.foodType(Herbivore.species);
        Jiru.fact();
        Jiru.hasHorns();
        if(z.askHungry()==1)
        	Jiru.hungerStatus(Herbivore.activity);
        Jiru.homeStatus(Camel.home);

        System.out.println("-------------------------------");

        Aardvark Adu = new Aardvark("Addo", Aardvark.colour,Aardvark.type);
        Adu.info();
        Adu.foodType(Omnivore.species);
        Adu.fact();
        Adu.digestionSystem();
        if(z.askHungry()==1)
        	Adu.hungerStatus(Carnivore.activity,Herbivore.activity);
        Adu.homeStatus(Carnivore.home,Herbivore.home);
        
        System.out.println("-------------------------------");

        Alpaca Gaz = new Alpaca("Nemo",Alpaca.colour, Alpaca.type);
        Gaz.info();
        Gaz.foodType(Herbivore.species);
        Gaz.fact();
        Gaz.hasHorns();
        if(z.askHungry()==1)
        	Gaz.hungerStatus(Herbivore.activity);
        Gaz.homeStatus(Herbivore.home);
        
        System.out.println("-------------------------------");
        
        Skunk San = new Skunk("Sanky",Skunk.colour, Skunk.type);
        San.info();
        San.foodType(Omnivore.species);
        San.fact();
        San.digestionSystem();
        if(z.askHungry()==1)
        	San.hungerStatus(Carnivore.activity, Herbivore.activity);
        San.homeStatus(Carnivore.home,Herbivore.home);
        
        System.out.println("Thanks. Program ends");

    }
}
