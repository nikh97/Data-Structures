/**
* This class represents an list that stores the data of multiple
* Color objects.
*
* @author Nikhil Gosike
* @version 09/18/17
*/

/*These lines place the class with the package project2
*/
package project2;

public class ColorList extends OrderedLinkedList<Color>{
	
	/**
	* Constructs a list of Color objects with the intial size 0f zero
	*/
	public ColorList(){

		// takes OrderedLinkedList's constructor and establishes empty list
		super();
	}
	
	/**
	* This method uses colorName to find the Color object with the same name.
	*
	* @param colorName colorName used to search the list
	*
	* @return the Color object with the same name, null, if it is not within
	* the list.
	*
	*/
	public Color getColorByName(String colorName){

		// iterates through the list of colors
		for(int i = 0; i<this.size();i++){

			// gets the color name of each item in the list
			String name = this.get(i).getName();

			// if the color is in the list and the name is not null, it returns the color object
			if(name != null && name.equalsIgnoreCase(colorName))
				return this.get(i);
		}
		
		// returns null if it is not found
		return null;
		
	}
	
	/**
	* This method uses colorHexValue to find the Color object with the same hex value.
	*
	* @param colorHexValue colorHexValue used to search the list
	*
	* @return the Color object with the same hex value, null, if it is not within
	* the list.
	*
	*/
	public Color getColorByHexValue(String colorHexValue){

		// iterates through list of colors
		for(int i = 0; i<this.size();i++){

			// if the color is in the list, it returns it
			if(this.get(i).getHexValue().equalsIgnoreCase(colorHexValue))
				return this.get(i);
		}
		
		// returns null if it is not found
		return null;
	}
}