/**
* This class represents an array that stores the data of multiple
* Color objects.
*
* @author Nikhil Gosike
* @version 09/18/17
*/

/*These lines place the class with the package project1 and
* imports the ArrayList class which will be implemented.
*/
package project1;

import java.util.ArrayList;

public class ColorList extends ArrayList<Color>{
	
	/**
	* Constructs an array of Color objects with the intial size 0f zero
	*/
	public ColorList(){

		// takes ArrayList's constructor and establishes empty array
		super(0);
	}
	
	/**
	* This method uses colorName to find the Color object with the same name.
	*
	* @param colorName colorName used to search the array
	*
	* @return the Color object with the same name, null, if it is not within
	* the array.
	*
	*/
	public Color getColorByName(String colorName){

		// iterates through array of colors
		for(int i = 0; i<this.size();i++){

			// gets the color name of each item in the array
			String name = this.get(i).getName();

			// if the color is in the array and the name is not null, it returns the color object
			if(name != null && name.equalsIgnoreCase(colorName))
				return this.get(i);
		}
		
		// returns null if it is not found
		return null;
		
	}
	
	/**
	* This method uses colorHexValue to find the Color object with the same hex value.
	*
	* @param colorHexValue colorHexValue used to search the array
	*
	* @return the Color object with the same hex value, null, if it is not within
	* the array.
	*
	*/
	public Color getColorByHexValue(String colorHexValue){

		// iterates through array of colors
		for(int i = 0; i<this.size();i++){

			// if the color is in the array, it returns it
			if(this.get(i).getHexValue().equalsIgnoreCase(colorHexValue))
				return this.get(i);
		}
		
		// returns null if it is not found
		return null;
	}
}