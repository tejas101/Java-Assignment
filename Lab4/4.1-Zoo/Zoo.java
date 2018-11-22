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
/**
  *Interface Herbivore will have properties which generally 
  *Herbivore share. 
  */
interface Herbivore {
    String home = "Grasslands";
    /**
      *hasHorns() shows the information about 
      *the horns of herbivore animals if they have.
      */
    void hasHorns();

}
/**
  *Interface Carnivore will have properties which generally 
  *Herbivore share. 
  */
interface Carnivore {
    String home = "den";
    /**
      *getJawStrcutre() shows the information
      *related to Jaws of the Carnivore animal.
      */
    void getJawsStrcutre();
}
abstract class Animal {
    private String name = "";
    private String colour = "";
    private String species = "";
    private String foodType = "";
    /**
     *hungerStatus method shows the hunger status of the
     *animal by displaying its species and its activity
     *of searching for food.
     *
     *@param      species   Type of the species.
     *@param      activity  Way in which species
     *                       get food.
     * 
     */
    public void hungerStatus(String species, String activity) {
        System.out.println("Iam " + species + " "
        		+ "and I am hungry. Send me to " + activity);

    }

    Animal() {
        super();
    }
    /**
      *Constructor for setting basic details of a animal
      *@param      name       Name of the species
      *@param      colour     Color of the species
      *@param      species    Type of the species
      *@param      foodType   Food type: Herbivorous
      *                        or carnivorous
      */
    Animal(String name, String colour, String species,
    		String foodType) {
        this.name = name;
        this.colour = colour;
        this.species = species;
        this.foodType = foodType;

    }
    /**
      *myFoodType() will give details of the food type of 
      *the animal.
      */
    void myFoodType() {
        System.out.println("Hello, I am of type " + foodType);
    }
    /**
      *fact() will give show a fact about the animal 
      */
    abstract void fact();
    /**
      *homeSatus() will print the homeStatus of the 
      *animal
      */
    abstract void homeStatus();
    /**
      *info() will show basic information of the animal.
      */
    void info() {
        System.out.println("Hello, I am " + species + ". " +
            "My Name is " + name + ". My colour is  " + colour);
    }
}
/**
  *In the classes below with the animal's name,
  *a parameterized constructors as used to set 
  *basic values of animals. Super will refer
  *to constructors is the Animal class and
  *every single species eg:Tiger inherits
  *Animal class.
  *  
  *
 */
class Tiger extends Animal implements Carnivore {

    Tiger(String name, String colour, String species,
    		String foodType) {

        super(name, colour, species, foodType);

    }

    @Override
    void fact() {

        System.out.println("Fact: Tiger is the biggest"
        		+ " species of the cat family. ");
    }

    void homeStatus() {

        System.out.println("I reached home. And my"
        		+ " home is " + home);
    }
   
    public void getJawsStrcutre() {

        System.out.println("The tiger has canine"
        		+ " teeth and goes for hunt.");
    }
}


class Lion extends Animal implements Carnivore {

    Lion(String name, String colour, String species,
    		String foodType) {

        super(name, colour, species, foodType);

    }

    @Override
    void fact() {

        System.out.println("Fact: Lion live in"
        		+ " groups, called " +
            "prides, of around 30 lions. ");
    }

    void homeStatus() {

        System.out.println("I reached home. And "
        		+ "my home is " + home);
    }

    public void getJawsStrcutre() {

        System.out.println("The Lion has canine"
        		+ " teeth and usually" +
            " hunt at night and mostly females "
            + "do this task.");
    }
}

class Giraffe extends Animal implements Herbivore {

    Giraffe(String name, String colour, String species,
    		String foodType) {

        super(name, colour, species, foodType);

    }

    @Override
    void fact() {

        System.out.println("Fact: Giraffes are the " +
            "tallest mammals on Earth.");
    }

    void homeStatus() {

        System.out.println("I reached home. And "
        		+ "my home is " + home);
    }

    public void hasHorns() {

        System.out.println("Giraffes has 2 "
        		+ "small horns and" +
            " they are called as Ossicone ");
    }
}


class Gazelle extends Animal implements Herbivore {

    Gazelle(String name, String colour, String species,
    		String foodType) {

        super(name, colour, species, foodType);

    }

    @Override
    void fact() {

        System.out.println("Fact: Gazelles can reach speeds" +
            " up to 60 mph in short bursts.");
    }

    void homeStatus() {

        System.out.println("I reached home. And"
        		+ " my home is " + home);
    }

    public void hasHorns() {

        System.out.println("Gazelle has curved "
        		+ "ringged horns ");
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
	    
    public static void main(String args[]) {
        Tiger Whaag = new Tiger("Katya", "Yellow",
        		"Tiger", "Carnivore");
        Whaag.info();
        Whaag.myFoodType();
        Whaag.fact();
        Whaag.getJawsStrcutre();
        Whaag.hungerStatus("Tiger", "hunt");
        Whaag.homeStatus();

        System.out.println("-------------------------------");

        Lion Sher = new Lion("Simba", "Brown", 
        		"Lion", "Carnivore");
        Sher.info();
        Sher.myFoodType();
        Sher.fact();
        Sher.getJawsStrcutre();
        Sher.hungerStatus("Lion", "hunt");
        Sher.homeStatus();

        System.out.println("-------------------------------");

        Giraffe Jiru = new Giraffe("Lambu", "Yellow-Brown",
        		"Giraffe", "Herbivore");
        Jiru.info();
        Jiru.myFoodType();
        Jiru.fact();
        Jiru.hasHorns();
        Jiru.hungerStatus("Giraffe", "graze");
        Jiru.homeStatus();

        System.out.println("-------------------------------");

        Gazelle Gaz = new Gazelle("Nemo", "Reddish-Brown", 
        		"Gazelle", "Herbivore");
        Gaz.info();
        Gaz.myFoodType();
        Gaz.fact();
        Gaz.hasHorns();
        Gaz.hungerStatus("Gazelle", "graze");
        Gaz.homeStatus();

    }
}
