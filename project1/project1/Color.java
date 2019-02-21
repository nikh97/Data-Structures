/**
* This class represents a color that consists of its
* hexidecimal value, RGB value, and name.
*
* @author Nikhil Gosike
* @version 09/18/17
*/

// This line places the Color class as part of package project 1.
package project1;

public class Color implements Comparable<Color>{

	/*Initializes the data fields colorHexValue, colorName,
	* red, blue, and green.
	*/
	private String colorHexValue;
	private String colorName;
	private int red,blue,green;
	
	/**
	*Instantiates a Color object using its hexidecimal value.
	*
	* @param colorHexValue colorHexValue given to the Color object.
	*/
	public Color (String colorHexValue){

		// calls this method to make sure hex value is valid
		checkColorHexValue(colorHexValue);

		// sets red, green and blue RGB values using these methods
		red = hexToInt(getHexValue(), 1);
		green = hexToInt(getHexValue(), 3);
		blue = hexToInt(getHexValue(), 5);
	}
	
	/**
	*Instantiates a Color object using its hexidecimal value, and name.
	*
	* @param colorHexValue colorHexValue given to the Color object.
	*
	* @param colorName colorName given to the Color object.
	*/
	public Color (String colorHexValue, String colorName){

		// calls this method to make sure hex value is valid
		checkColorHexValue(colorHexValue);

		// sets red, green and blue RGB values using these methods
		red = hexToInt(getHexValue(), 1);
		green = hexToInt(getHexValue(), 3);
		blue = hexToInt(getHexValue(), 5);

		// calls method to set the color name of the object
		setColorName(colorName);
	}
	
	/**
	*Instantiates a Color object using its RGB value.
	*
	* @param red red rgb value given to the Color object.
	*
	* @param green green rgb calue given to the Color object.
	*
	* @param blue blue rgb value given to the Color object
	*/
	public Color (int red, int green, int blue){

		// calls this method to check if RGB values are valid
		this.red = colorNumCheck(red);
		this.blue = colorNumCheck(blue);
		this.green = colorNumCheck(green);

		// calls this method to convert valid values to hexidecimal
		colorHexValue = setColorHexValue(red, green, blue);
	}
	
	/**
	* This method checks the hexidecimal value inputed into the 
	* constructors to make sure they are valid and sets it if
	* it is.
	*
	* @param colorHexValue colorHexValue checked against criteria.
	*
	* @throws IllegalArguementException if the hexidecimal value
	* does not start with a '#', is not 7 characters long, and
	* if the proceeding colors are not 0-9 or A-F.
	*/
	private void checkColorHexValue(String colorHexValue){

		// sets to lowercase in order to ignore case
		String s = colorHexValue.toLowerCase();
		
		// makes sure hex values start with '#'
		if(s.charAt(0) != '#')
			throw new IllegalArgumentException("Error: This is not a valid color specification.");

		// checks to see if hex value string is 7 characters long
		else if (s.length()!=7)
			throw new IllegalArgumentException("Error: This is not a valid color specification.");

		// checks to see if characters are between 0-9 or A-F
		else{
			for (int i = 1; i<s.length();i++){

				// takes char at each index and converts them to integer equivalent
				int c = (int) s.charAt(i);

				// throws exception if they are not 0-9 or A-F
				if((c<48 || c>57) && (c<97 || c>102))
					throw new IllegalArgumentException("Error: This is not a valid color specification.");
			}	
		}
		
		// returns hex value if valid in uppercase
		this.colorHexValue = colorHexValue.toUpperCase();
	}
	
	/**
	* This class sets Color instances' color name.
	*
	* @param colorName colorName set for instances.
	*/
	public void setColorName(String colorName){
		this.colorName = colorName;
	}
	
	/**
	* This takes the RGB values of a color and converts them to theie
	* hexidecimal value.
	*
	* @param red red rgb value converted.
	* 
	* @param green green rgb value converted.
	*
	* @param blue blue rgb value converted.
	*
	* @return the converted hexidecimal value  
	*/
	private String setColorHexValue(int red, int green, int blue){

		// initializes hexidecimal variables of colors
		String hexR, hexG, hexB;

		// converts red, green, and blue to hexidecimal equivalents
		hexR = Integer.toHexString(red);
		hexG = Integer.toHexString(green);
		hexB = Integer.toHexString(blue);

		// makes sure each hexidecimal string is 2 characters long
		String hexValue = "#"+String.format("%2s", hexR)+String.format("%2s", hexG)+String.format("%2s", hexB);

		// replaces white space with '0'
		hexValue = hexValue.replace(' ', '0');

		// returns hexidecimal value of color
		return hexValue;

	}

	/**
	* This class checks to see if the RGB value 
	* given is valid by if it is within the range
	* (0, 255), inclusive.
	*
	* @param number number checked to be within range.
	*
	* @return the number if it is valid.
	*/
	private int colorNumCheck(int number){

		// checks if color number is within range
		if (number >= 0 && number <= 255)
			return number;

		// throws exception otherwise
		else
			throw new IllegalArgumentException("Error: This is not a valid color specification.");
	}
	
	/**
	* Accessor method for the red RGB value.
	*
	* @return the private red data field.
	*/
	public int getRed(){
		return red;	
	}
	
	/**
	* Accessor method for the blue RGB value.
	*
	* @return the private blue data field.
	*/
	public int getBlue(){
		return blue;
	}
	
	/**
	* Accessor method for the green RGB value.
	*
	* @return the private green data field.
	*/
	public int getGreen(){
		return green;
	}

	/**
	* Accessor method for the name of the color.
	*
	* @return the private colorName data field.
	*/
	public String getName(){
		return colorName;
	}
	
	/**
	* Accessor method for the hexidecimal value of the color.
	*
	* @return the private colorHexValue data field.
	*/
	public String getHexValue(){
		return colorHexValue;
	}
	
	/**
	* This method sees if two Color objects by their
	* hexidecimal values, ignoring case considerations.
	*
	* @return true, if they are equal, false, if they are not.
	*/
	public boolean equals(Color c){
		return (this.colorHexValue.equalsIgnoreCase(c.getHexValue()));
	}
	
	/**
	* Overrides the compareTo method and compares two color
	* objects using their hexidecimal values, ignoring case considerations.
	*
	* @return a negative integer, zero, or a positive integer as the specified hexidecimal value 
	* is greater than, equal to, or less than this hexidecimal value, ignoring case considerations.
	*/
	public int compareTo(Color c){
		return (this.colorHexValue.compareToIgnoreCase(c.getHexValue()));
	}

	/**
	* This method takes a hexidecimal value and converts
	* it to an integer.
	*
	* @param colorHexValue colorHexValue to get hexidecimal 
	* values from.
	*
	* @param start start is the where in the string the conversion
	* should start.
	*
	* @return the integer or RGB value of the hexidecimal number.
	*/
	private int hexToInt(String colorHexValue, int start){

		// initializes RGB number of color
		int rgbNum;

		// takes substring for whichever color
		String s = colorHexValue.substring(start, start+2);

		// converts hexidecimal string to an integer
		rgbNum = Integer.parseInt(s, 16);

		// returns the RGB number
		return rgbNum;
	}

	/**
	* This method provides a String with the basic
	* information of the color (i.e. hexidecimal value,
	* RGB value, and name).
	*
	* @return the String representation of the color object.
	*/
	public String toString(){

		// assigns the string to be returned by first showing the color's hex value
		String s1 = (getHexValue() + ", (");

		// formats and adds RGB value to the string
		s1 += String.format("%3d", getRed()) + "," + String.format("%3d", getGreen()) + "," + String.format("%3d", getBlue()) + ")";

		// adds color name if it is not null
		if (colorName == null)
			s1 += ", ";
		else 
			s1 += ", " + getName();

		// returns string with information about color
		return s1; 
	}
}













