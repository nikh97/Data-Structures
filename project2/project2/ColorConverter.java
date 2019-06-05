/**
* This class takes a file and gives the user
* on the specific color that he or she inputs.
*
* @author Nikhil Gosike
* @version 09/18/17
*/

// This line places the Color class as part of package project 2.
package project2;

// Importing the Scanner, File and File Not Found Exception classes.
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class ColorConverter {

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
			Scanner colorFile = new Scanner(filename);

			// Instantiates a new ColorList object
			ColorList colorArray = new ColorList();

			// loop that adds all the objects to the color array
			while (colorFile.hasNext()){

				// assigns a line in the text file to "line"
				String line = colorFile.nextLine();

				// creates an array of the two elements
				String[] color = line.split(",");

				// Removes whitespace from array elements
				color[0] = color[0].trim();
				color[1] = color[1].trim();

				// instantiates a new Color object using array elements
				Color colorObject = new Color(color[1], color[0]);

				// Adds color object to Color array
				colorArray.add(colorObject); 
			}

			// closes file
			colorFile.close();

			// instantiates new Scanner object for input
			Scanner input = new Scanner(System.in);

			System.out.println();

			// Asks user to input a hexidecimal value
			System.out.println("Enter the color in HEX format (#RRGGBB) or \"quit\" to stop:");
			String s1 = input.next();

			// establishes while loop that only terminates when user says "quit"
			while (!(s1.equals("quit"))){

				// checks to see if color is in the list
				if(colorArray.getColorByHexValue(s1) == null){
					
					// checks to see if hex value is valid and adds new color object to list
					try {

						Color colorAdd = new Color(s1);
						colorArray.add(colorAdd);
						System.out.println();
						System.out.println("Color Information:");
						System.out.println(colorArray.getColorByHexValue(s1).toString());
						System.out.println();						
					}

					// catches invalid hex value errors and notifies user
					catch (IllegalArgumentException ex){
						System.err.printf("\nError: This is not a valid color specification.\n");
						System.out.println();						
					}
				}

				// if color is in array, prints out information about the color
				else{
					System.out.println();
					System.out.println("Color Information:");
					System.out.println(colorArray.getColorByHexValue(s1).toString());
					System.out.println();
				}
					
				// asks user to input a hexvalue or quit
				System.out.println("Enter the color in HEX format (#RRGGBB) or \"quit\" to stop:");
				s1 = input.next();
			}

			// closes input Scanner object
			input.close();
			System.out.println();

	}

}