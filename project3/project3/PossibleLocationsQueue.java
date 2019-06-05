package project3;

public class PossibleLocationsQueue implements PossibleLocations{

	private Location[] elementData;

	private int capacity;
	private int front, back;
	private int size;

	public PossibleLocationsQueue(){

		this(100);
	}

	public PossibleLocationsQueue(int c){

		if(c <= 0){

			capacity = 100;

		}else{

			capacity = c;
		}

		elementData = new Location[capacity];

		front = 0;

		back = capacity - 1;

		size = 0;

	}

	/**
	 * Add a SquarePosition object to the set.
	 * @param s
	 *    object to be added
	 */
	public void add ( Location s ){

		if(s == null) return;

		if(size == capacity) makeLarger();

		back = (back + 1) % capacity;

		elementData[back] = s;

		size++;

	}
	
	/**
	 * Remove the next object from the set. The specific
	 * item returned is determined by the underlying structure
	 * of set representation.
	 * @return
	 *    the next object, or null if set is empty
	 */
	public Location remove (){

		if(size == 0) return null;

		Location temp = elementData[front];

		front = (front + 1) % capacity;

		size--;

		return temp;

	}
	
	/**
	 * Determines if set is empty or not.
	 * @return
	 *    true, if set is empty,
	 *    false, otherwise.
	 */
	public boolean isEmpty(){

		if(size == 0) return true;

		return false;
	}

	private void makeLarger(){

		Location[] newArray = new Location[capacity*2];

		for(int i = 0; i < size; i++){

			newArray[i] = elementData[(front + i) % capacity];

		}

		front = 0;

		back = size - 1;

		capacity = newArray.length;

		elementData = newArray;
	}

}