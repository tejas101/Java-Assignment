/* GravityCalculator.java  
 * Version: 1
 *      
 * Revisions: 0
 *     
 * Tejas Raval MS-CS 2018 RIT tr7550@rit.edu
 * Lipisha Chaudhary MS-CS 2018 RIT lc2919@rit.edu
 */
/** 
 * ----------Program Description-----------
 * The program performs some basic physics calculations.
 * It calculates the distance and velocity of an object
 * travelling in space.
 *@author      Tejas Raval 
 *@author      Lipisha Chaudhary
 */
import java.util.*;

public class GravityCalculator {
	public void Calculation(double planet_time, double planet_gravity) {
		/**
		 * The Calculations method takes in parameters of the specified 
		 * planet and calculates the distance and veloctiy at which an 
		 * object is travelling towards it.
		 * 
		 * @param planet_time	Time duration
		 * @param planet_gravity	Gravity of the planet
		 */
		double distance;
		double velocity;
		distance = Math.round((0.5 * planet_gravity * Math.pow(planet_time, 2))
				* 100.0) / 100.0;
		velocity = Math.round((planet_gravity * planet_time) * 100.0) / 100.0;
		System.out.printf("The object's postion after %.2f seconds"
				+ " is %.2f m from starting point.", planet_time, distance);
		System.out.printf("\nCurrent Velocity is %.2f m/s.", velocity);
		
	}

	public static void main(String[] args) {
		/**
		 * @param args (Planet, Time)
		 */
		if(args.length == 2) {
			String planet = "";
			String time = "";
			double earthGravity = 9.81;
			double marsGravity = 3.711;
			planet = args[0].toLowerCase();
			time = args[1];
			double result_time = Double.parseDouble(time);	
			if(result_time<0)
			{
				System.out.println("Time can't be negative. Sorry! Exiting the code.");
				System.exit(0);
			}
			GravityCalculator test = new GravityCalculator();
			if(planet.equals("earth")) {
				test.Calculation(result_time, earthGravity);
			}
			else if(planet.equals("mars")) {
				test.Calculation(result_time, marsGravity);
			}
			else{
				System.out.println("Enter the correct Planet!");
			}
		}
		else {
			System.out.println("Enter correct number of arguments!");
	}
	}
}
