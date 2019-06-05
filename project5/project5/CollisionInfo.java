package project5;

/**
* This class takes in a CSV file and gives the user
* information about collisions within a date range and in a 
* specified zipcode.
*
* @author Nikhil Gosike
* @vesion 12/11/2017
*/

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class CollisionInfo{

	public static void main(String[] args) throws FileNotFoundException{

		/* Makes sure that the user provides a file in the arguments
			* and exits if there is none provided.
			*/
			if (args.length != 1){
				System.out.println();

				// Shows the user his or her error and says the correct usage.
				System.err.printf("Error: incorrect usage.\n");
				System.err.printf("Usage Error: the program expects file name as an argument.\n");
				System.out.println();

				// Exits the system if error is triggered
				System.exit(1);
			}

			// assigns the arguments to a new instance of File
			File filename = new File(args[0]);

			// Checks to see if file can be opened and read
			if (!filename.exists() || !filename.canRead()){
				System.out.println();

				// Tells user the error
				System.err.printf("Error: the file \"" + args[0] + "\" cannot be opened.\n");
				System.out.println();

				// Exits system if error is triggered
				System.exit(1);
			}

			// creates an instance of Scanner to read the file
			Scanner nypdFile = new Scanner(filename);

			//Instantiates a new CollisionsData object
			CollisionsData collisionData = new CollisionsData();

			//skips the first line of the csv (with headers)
			String line = nypdFile.nextLine();

			//this loop adds Collision objects to the collisionData
			while(nypdFile.hasNext()){

				// assigns a line in the text file to "line"
				line = nypdFile.nextLine();

				//creates an ArrayList from the given line
				ArrayList<String> entries = splitCSVLine(line);

				// instantiates a new Color object using the ArrayList object
				Collision c = validLine(entries);

				//if the collision object can be instantiated it adds it the to collisionData
				if(c != null){

					collisionData.add(c);
				}


			}

			//closes file
			nypdFile.close();

			// instantiates new Scanner object for input
			Scanner input = new Scanner(System.in);

			System.out.println();

			//Instantiates three strings to help
			String zip, d1, d2;

			//Asks user to enter a zipcode
			System.out.print("Enter a zip code ('quit' to exit): ");
			zip = input.next();

			// establishes while loop and only ends when user says "quit"
			while(!(zip.equals("quit"))){

				// keep asking to re-enter zipcode if it is invalid
				while(!(isValidZip(zip))){

					System.out.println("Invalid zip code. Try again.");
					System.out.println();
					System.out.print("Enter a zip code ('quit' to exit): ");
					zip = input.next();
				}

				// Ask user for the start and end dates
				System.out.print("Enter start date (MM/DD/YYYY): ");
				d1 = input.next();

				System.out.print("Enter end date (MM/DD/YYYY): ");
				d2  = input.next();

				//try and create two date objects
				Date dateBegin = validDate(d1);
				Date dateEnd = validDate(d2);

				// keep asking to re-enter dates if they are invalid
				while(dateBegin == null || dateEnd == null){

					System.out.println("Invalid date format. Try again.");
					System.out.println();

					System.out.println("Enter a zip code ('quit' to exit): " + zip);
					System.out.print("Enter start date (MM/DD/YYYY): ");
					d1 = input.next();

					System.out.print("Enter end date (MM/DD/YYYY): ");
					d2  = input.next();

					dateBegin = validDate(d1);
					dateEnd = validDate(d2);
				}

				//print the report 
				System.out.println();
				System.out.println(collisionData.getReport(zip, dateBegin, dateEnd));

				// asks user to input a hexvalue or quit
				System.out.println();
				System.out.print("Enter a zip code ('quit' to exit): ");
				zip = input.next();
			}	

			//close the Scanner object
			input.close();
			System.out.println();
	}

	/*
	* Helper method to check if the date is a valid object
	*
	* @param d is the date taken in as a String by the user
	*
	* @return null is if cannot be instantiated into a date object
	* or return the date object created
	*/
	public static Date validDate(String d){

		@SuppressWarnings("unused")
		Date date;

		try{

			date = new Date(d);

		}catch(Exception ex){

			return null;
		}

		return date;
	}

	/*
	* Helper method to check if the zipcode is valid
	*
	* @param zip is the zipcode taken in as a String by the user
	*
	* @return true if it is valid or false is it is not
	*/
	public static boolean isValidZip(String zip){

		if (zip.length() != 5) return false;

		for(int i = 0; i < zip.length(); i++){
			char c = zip.charAt(i);
			if(!(Character.isDigit(c))) return false;;
		}

		return true;
	}

	/*
	* Helper method to see if line of CSV can be converted into a 
	* Collision object
	*
	* @param entries is the line of the CSV as an ArrayList of Strings
	*
	* @return the Collision object is it can be made or null if it cannot
	*/
	public static Collision validLine(ArrayList<String> entries){

		if(entries.size() < 24) return null;

		@SuppressWarnings("unused")
		Collision c;

		try{

			c = new Collision(entries);
		
		} catch(Exception ex){
			return null;
		}

		return c;

	}

	/**
	 * Splits the given line of a CSV file according to commas and double quotes
	 * (double quotes are used to surround multi-word entries so that they may contain commas)
	 * @author Joanna Klukowska
	 * @param textLine	a line of text to be passed
	 * @return an Arraylist object containing all individual entries found on that line
	 */
	public static ArrayList<String> splitCSVLine(String textLine){
		
		ArrayList<String> entries = new ArrayList<String>(); 
		int lineLength = textLine.length(); 
		StringBuffer nextWord = new StringBuffer(); 
		char nextChar; 
		boolean insideQuotes = false; 
		boolean insideEntry= false;
		
		// iterate over all characters in the textLine
		for (int i = 0; i < lineLength; i++) {
			nextChar = textLine.charAt(i);
			
			// handle smart quotes as well as regular quotes
			if (nextChar == '"' || nextChar == '\u201C' || nextChar =='\u201D') {
					
				// change insideQuotes flag when nextChar is a quote
				if (insideQuotes) {
					insideQuotes = false; 
					insideEntry = false;
				}else {
					insideQuotes = true; 
					insideEntry = true;
				}
			} else if (Character.isWhitespace(nextChar)) {
				if ( insideQuotes || insideEntry ) {
				// add it to the current entry 
					nextWord.append( nextChar );
				}else { // skip all spaces between entries
					continue; 
				}
			} else if ( nextChar == ',') {
				if (insideQuotes){ // comma inside an entry
					nextWord.append(nextChar); 
				} else { // end of entry found
					insideEntry = false;
					entries.add(nextWord.toString());
					nextWord = new StringBuffer();
				}
			} else {
				// add all other characters to the nextWord
				nextWord.append(nextChar);
				insideEntry = true;
			} 
			
		}
		// add the last word ( assuming not empty ) 
		// trim the white space before adding to the list 
		if (!nextWord.toString().equals("")) {
			entries.add(nextWord.toString().trim());
		}

		return entries;
	}
}

