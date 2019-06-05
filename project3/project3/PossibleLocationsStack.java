package project3;

public class PossibleLocationsStack implements PossibleLocations{

	static class Node{

		private Location element;
		private Node next;

		public Node (Location element){
			this.element = element;
		}

		public Node getNext(){
			return next;
		}

		public void setNext(Node next){
			this.next = next;
		}

		public Location getElement(){

			return element;
		}

	}

	private Node top;
	private int size;

	PossibleLocationsStack(){

		top = null;
		size = 0;

	}

	/**
	 * Add a SquarePosition object to the set.
	 * @param s
	 *    object to be added
	 */
	public void add ( Location s ){

		if(s == null) 
			return;

		if(size == 0){

			top = new Node(s);
			size++;
		} else{

		Node temp = new Node(s);

		temp.setNext(top);

		top = temp;
		size++;
		}
	}
	
	/**
	 * Remove the next object from the set. The specific
	 * item returned is determined by the underlying structure
	 * of set representation.
	 * @return
	 *    the next object, or null if set is empty
	 */
	public Location remove (){

		if (size == 0) return null;

		Location removed = top.getElement();

		if(size == 1)
			top = null;
		else
			top = top.getNext();

		size--;

		return removed;

	}
	
	/**
	 * Determines if set is empty or not.
	 * @return
	 *    true, if set is empty,
	 *    false, otherwise.
	 */
	public boolean isEmpty(){

		if (size == 0) return true;

		return false;

	}

}