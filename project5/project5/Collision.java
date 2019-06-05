package project5;
import java.util.ArrayList;


/**
* This class represents an NYPD-recorded Collision for an accident in
* one of five boroughs.
*
* @author Nikhil Gosike
* @version 12/07/2017
*/
public class Collision implements Comparable<Collision>{

	/* Initializes the data fields entries, date, zipcode, and key
	*/
	private ArrayList<String> entries;
	private Date date;
	private String zipcode;
	private String key;

	/**
	* Instatiates a Collision object using a data entry that includes
	* information about an accident.
	*
	* @param entries entries about about the collision given to the object.
	*
	* @throws IllegalArgumentException if the entries parameter is empty or
	* if the data in the parameter is not valid.
	*/
	public Collision(ArrayList<String> entries) throws IllegalArgumentException{

		if(entries.isEmpty())
			throw new IllegalArgumentException("Error: the entries array is empty.");

		// checks if the data in the entries array is valid
		checkDate(entries.get(0));
		checkZipCode(entries.get(3));
		checkKey(entries.get(23));

		for(int i = 10; i < 18; i++)
			checkInjuries(entries.get(i));

		this.entries = entries;

	}

	/**
	* Helper method that checks to see if the date in the entries array
	* is a valid date object.
	*
	* @param d d is the date as a String.
	*
	* @throws IllegalArguementException if the String cannot be made
	* into a valid Date object
	*/
	private void checkDate(String d){

		if(d.isEmpty()) throw new IllegalArgumentException("Error: Date entry is empty.");

		try{
			this.date = new Date(d);
		} catch(Exception ex){

			throw new IllegalArgumentException("Error: Date is not not a valid Date object.");
		}

	}

	/**
	* Helper method that checks if the zipcode in the entries array is valid.
	*
	* @param z z is the zipcode string.
	*
	* @throws IllegalArguementException if the zipcode is not five numbers.
	*/
	private void checkZipCode(String z){

		if (z.length() != 5) throw new IllegalArgumentException("Error: zipcode string is less than five characters.");

		for(int i = 0; i < z.length(); i++){
			char c = z.charAt(i);
			if(!(Character.isDigit(c))) throw new IllegalArgumentException("Error: this is not a valid zipcode.");
		}

		this.zipcode = z;
	}

	/**
	* Helper method to check if the injury data entries are above or equal to zero
	* 
	* @param i i is the injury data point that needs to be checked as a String.
	*
	* @throws IllegalArgumentException if the string cannot be parsed to an int
	* or if it less that zero
	*/
	private void checkInjuries(String i){

		int i2;

		try{
			i2 = Integer.parseInt(i);
		} catch(Exception ex){
			throw new IllegalArgumentException("Error: Injuries/Fatalities are not integers.");
		}

		if(i2 < 0) throw new IllegalArgumentException("Error: the number of injured/killed cannot be negative.");

	}

	/**
	* Helper method to check if the unique key is valid.
	*
	* @param k k is the unique key of each entry.
	*
	* @throws IllegalArgumentException if the unique key is an empty string.
	*/
	private void checkKey(String k){

		if(k.isEmpty())
			throw new IllegalArgumentException("Error: the unique key is an empty string.");

		this.key = k;

	}

	/**
	* Accesor method to get the date
	*
	* @return the private date data field 
	*/
	public Date getDate(){

		return date;

	}

	/**
	* Accesor method to get the zipcode
	*
	* @return the private zipcode data field 
	*/
	public String getZip(){

		return zipcode;

	}

	/**
	* Accesor method to get the unique key
	*
	* @return the private key data field 
	*/
	public String getKey(){

		return key;
	}

	/**
	* Accesor method to get the number of persons injured in the collision
	*
	* @return the number of persons injured from the entries ArrayList
	*/
	public int getPersonsInjured(){

		return Integer.parseInt(entries.get(10));
	}

	/**
	* Accesor method to get the number of persons killed in the collision
	*
	* @return the number of persons killed from the entries ArrayList
	*/
	public int getPersonsKilled(){

		return Integer.parseInt(entries.get(11));

	}

	/**
	* Accesor method to get the number of pedestrians injured in the collision
	*
	* @return the number of pedestrians injured from the entries ArrayList
	*/
	public int getPedestriansInjured(){

		return Integer.parseInt(entries.get(12));
	}

	/**
	* Accesor method to get the number of pedestrians killed in the collision
	*
	* @return the number of pedestrians killed from the entries ArrayList
	*/
	public int getPedestriansKilled(){

		return Integer.parseInt(entries.get(13));
	}

	/**
	* Accesor method to get the number of cyclists injured in the collision
	*
	* @return the number of cyclists injured from the entries ArrayList
	*/
	public int getCyclistsInjured(){

		return Integer.parseInt(entries.get(14));
	}

	/**
	* Accesor method to get the number of cyclists killed in the collision
	*
	* @return the number of cyclists killed from the entries ArrayList
	*/
	public int getCyclistsKilled(){

		return Integer.parseInt(entries.get(15));
	}

	/**
	* Accesor method to get the number of motorists injured in the collision
	*
	* @return the number of motorists injured from the entries ArrayList
	*/
	public int getMotoristsInjured(){

		return Integer.parseInt(entries.get(16));
	}

	/**
	* Accesor method to get the number of motorists killed in the collision
	*
	* @return the number of motorists killed from the entries ArrayList
	*/
	public int getMotoristsKilled(){

		return Integer.parseInt(entries.get(17));
	}

	/**
	* Overrides the compareTo method and compares two Collision
	* objects using their dates, zipcodes, and unique key.
	*
	* @return a negative integer, zero, or a positive integer as the specified date, zipcode, or unique key
	* is greater than, equal to, or less than this date, zipcode, or unique key.
	*/
	public int compareTo(Collision c){

		if(!(this.zipcode.equals(c.zipcode)))
			return this.zipcode.compareTo(c.zipcode);

		if(!(this.date.equals(c.date)))
			return this.date.compareTo(c.date);

		return this.key.compareTo(c.key);

	}

	/**
	* This method sees if two Collision objects by their
	* date, zipcodes, and unique keys, ignoring case considerations.
	*
	* @return true, if they are equal, false, if they are not.
	*/
	public boolean equals(Object o){

		if(this == o) return true;

		if(o == null) return false;

		if(!(o instanceof Collision)) return false;

		Collision other = (Collision) o;

		if(!(this.zipcode.equals(other.zipcode))) return false;

		if(!(this.date.equals(other.date))) return false;

		if(!(this.key.equals(other.key))) return false;

		return true;
	}


}