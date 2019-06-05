package project2;

/**
 * A sorted collection.  The element in this list are stored in order determined
 * by the natural ordering of those elements (i.e., the {@code compareTo()} method
 * defined in the elements' class).  
 * The user can access elements by their integer index (position in
 * the list), and search for elements in the list.<p>
 *
 * The lists implementing this class allow duplicate elements.  
 * They do not allow null elements.<p>
 *
 * @param <E> the type of elements in this list
 *
 * @author  Nikhil Gosike
 */

public class OrderedLinkedList<E extends Comparable<E>> implements OrderedList<E>{

	/**
	* This is the node class which stores the elements on the list and creates
	* the references to the next object in the list
	* 
	* This is a private class that the Ordered Linked List uses to create the list
	*
	* @param <E> the type of element stored in the node
	*/

	public static class Node<E> {


		/**
		* Initializes the data fields element and next
		*/

		private E element;
		private Node<E> next;

		/**
		* Instantiates a node using an object
		* allows the next data field to be null
		* 
		* @param element element to be stored within this node
		*/

		public Node(E element){
			this.element = element;
		}

		/**
		* Accessor method to get the element data field
		*
		* @return the private element data field 
		*/

		public E getElement(){
			return element;
		}

		/**
		* Accessor method to the next data field
		*
		* @return next next node in the list
		*/

		public Node<E> getNext(){
			return next;
		}

		/**
		* This sets the element of the node to some object
		*
		*@param element element of type E to be stores
		*/

		public void setElement(E element){
			this.element = element;
		}

		/**
		* This sets the next reference for the node 
		*
		*@param next next node in the list 
		*/

		public void setNext(Node<E> next){
			this.next = next;
		}

	}


	/**
	* Intializes the head and size data fields
	*/
	private Node<E> head;
	private int size;

	/**
	* The constructor creates an empty list which will be filled with objects
	*/

	public OrderedLinkedList(){
		head = null;
		size = 0;
	}

	/**
	* Accessor method for the private data field head
	*
	* @return the head data field or first node in the list 
	*/

	public Node<E> getHead(){
		return head;
	}

	/**
     * Removes all of the elements from this list.
     * The list will be empty after this call returns.
     */

	public void clear(){
		head = null;
		size = 0;
	}

	/**
     * Returns the number of elements in this list.
     *
     * @return the number of elements in this list
     */

	public int size(){
		return size;
	}

	/**
     * Adds the specified element to  this list in a sorted order. 
     *
     * <p>The element added must implement Comparable<E> interface. This list 
     * does not permit null elements. 
     *
     * @param e element to be appended to this list
     * @return <tt>true</tt> if this collection changed as a result of the call
     * @throws ClassCastException if the class of the specified element
     *         does not implement Comparable<E> 
     * @throws NullPointerException if the specified element is null
     */

	public boolean add(E e){

		if (e == null)
			throw new NullPointerException("Error: this object is null");

		Node<E> newNode = new Node<E>(e);

		if (size() == 0){
			head = newNode;
			newNode.setNext(null);
			size++;
			return true;
		}

		if (e.compareTo(head.getElement()) <= 0)
			return addFirst(e);


		Node<E> current = head;



		while (current.getNext() != null){

			if(e.compareTo(current.getNext().getElement()) <= 0){
				newNode.setNext(current.getNext());
				current.setNext(newNode);
				size++;
				return true;
			}

			current = current.getNext();
		}


		return addLast(e);
	}

	/**
	* This method adds a node to the beginning of the list
	*
	* @param e e is the element to be added
	*
	* @return <tt>true</tt> once the element has been added
	*/

	private boolean addFirst(E e){

		Node<E> newNode = new Node<E> (e);
		newNode.setNext(head);

		head = newNode;
		size++;

		return true;

	}

	/**
	* This method adds a node to the end of the list
	*
	* @param e e is the element to be added
	*
	* @return <tt>true</tt> once the element has been added
	*/

	private boolean addLast(E e){
		Node<E> newNode = new Node<E>(e);

		Node<E> current = head;

		while(current.getNext() != null){
			current = current.getNext();
		}

		newNode.setNext(null);

		current.setNext(newNode);

		size++;

		return true;
	}

	/**
     * Returns the element at the specified position in this list.
     *
     * @param index index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException if the index is out of range 
     * <tt>(index < 0 || index >= size())</tt>
     */

	public E get(int index){

		if (index < 0 || index >= size())
			throw new IndexOutOfBoundsException("Error: this index is out of bounds.");

		Node<E> current = head;
		int thisIndex = 0;

		while (current.getNext() != null && thisIndex != index){
			current = current.getNext();
			thisIndex++;
		}

		return current.getElement();

	}

	/**
     * Removes the first occurrence of the specified element from this list,
     * if it is present.  If this list does not contain the element, it is
     * unchanged.  More formally, removes the element with the lowest index
     * {@code i} such that
     * <tt>(o.equals(get(i))</tt>
     * (if such an element exists).  Returns {@code true} if this list
     * contained the specified element (or equivalently, if this list
     * changed as a result of the call).
     *
     * @param o element to be removed from this list, if present
     * @return {@code true} if this list contained the specified element
     * @throws ClassCastException if the type of the specified element
     *         is incompatible with this list
     * @throws NullPointerException if the specified element is null and this
     *         list does not permit null elements
     */

	public boolean remove(Object o){

		if (o == null)
			throw new NullPointerException("Cannot remove null value.");

		if (o.getClass() != head.getElement().getClass())
			throw new NullPointerException("This object is incompatible with this list.");

		if (!(contains(o)))
			return false;

		if(indexOf(o) == 0)
			removeFirst();
		else if(indexOf(o) == size() - 1)
			removeLast();
		else{

			int index = indexOf(o);
			
			remove(index);
		}

		return true;


	}

	/**
     * Removes the element at the specified position in this list.  Shifts any
     * subsequent elements to the left (subtracts one from their indices if such exist).
     * Returns the element that was removed from the list.
     *
     * @param index the index of the element to be removed
     * @return the element previously at the specified position
     * @throws IndexOutOfBoundsException  if the index is out of range 
     * <tt>(index < 0 || index >= size())</tt>
     */

	public E remove(int index){

		if(index < 0 || index >= size())
			throw new IndexOutOfBoundsException("Error: this index is out of bounds");

		Node<E> current = head;
		//int thisIndex = 0;

		if (index == 0){
			return removeFirst();
		}

		if (index == size() - 1){
			return removeLast();
		}

		for(int i = 1; i < index; i++){
			current = current.getNext();
		}

		E removedObj = current.getNext().getElement();
		current.setNext(current.getNext().getNext());
		size--;
		return removedObj;
	}

	/**
     * Removes the first node in the list
     *
     * @return the element of the node that was removed
     *
     */

	private E removeFirst(){
		if (size() == 0 || head == null)
			return null;
		E removedObj = head.getElement();
		head = head.getNext();

		size--;

		return removedObj;

	}

	/**
     * Removes the last node in the list
     *
     * @return the element of the node that was removed
     *
     */

	private E removeLast(){

		if (size() == 0)
			return null;

		Node<E> current = head;

		while(current.getNext().getNext() != null){
			current = current.getNext();
		}

		E removedObj = current.getNext().getElement();
		current.setNext(null);
		size--;
		return removedObj;		

	}

	/**
     * Compares the specified object with this list for equality.  Returns
     * {@code true} if and only if the specified object is also a list, both
     * lists have the same size, and all corresponding pairs of elements in
     * the two lists are <i>equal</i>.  (Two elements {@code e1} and
     * {@code e2} are <i>equal</i> if {@code e1.equals(e2)}.)  
     * In other words, two lists are defined to be
     * equal if they contain the same elements in the same order.<p>
     *
     * Implementation based on equals() method from Data Structures and Algorithms
     * by Michael T. Goodrich, Roberto Tamassia, and Michael H. Goldwasser in
     * Section 3.5.2
     *
     * @param o the object to be compared for equality with this list
     * @return {@code true} if the specified object is equal to this list
     */

	public boolean equals(Object obj){

		if(obj == null)
			return false;

		if(this.getClass() != obj.getClass())
			return false;

		@SuppressWarnings("unchecked")
		OrderedLinkedList<E> other = (OrderedLinkedList<E>) obj;

		if(this.size() != other.size())
			return false;

		Node<E> objA = this.getHead();
		Node<E> objB = other.getHead();

		while (objA != null){
			if (!objA.getElement( ).equals(objB.getElement( ))) 
				return false;
			objA = objA.getNext();
			objB = objB.getNext();
		}

		return true;
	}

	/**
     * Returns a shallow copy of this list. (The elements
     * themselves are not cloned.)
     *
     *Implementation based on clone() method from Data Structures and Algorithms
     * by Michael T. Goodrich, Roberto Tamassia, and Michael H. Goldwasser in
     * Section 3.6.2
     *
     * @return a shallow copy of this list instance
     */

	public Object clone() throws CloneNotSupportedException{

		@SuppressWarnings("unchecked")
		OrderedLinkedList<E> other = (OrderedLinkedList<E>) super.clone();

		if (size > 0){
			other.head = new Node<>(head.getElement());
			Node<E> current = other.getHead();
			Node<E> walk = head.getNext();
			while (walk != null){
				Node<E> newNode = new Node<E>(walk.getElement());
				current.setNext(newNode);
				current = newNode;
				walk = walk.getNext();
			}

		}

		return other;
	}

	/**
     * Returns <tt>true</tt> if this list contains the specified element.
     *
     * @param o element whose presence in this list is to be tested
     * @return <tt>true</tt> if this list contains the specified element
     * @throws ClassCastException if the type of the specified element
     *         is incompatible with this list
     * @throws NullPointerException if the specified element is null 
     */


	public boolean contains(Object o){

		if (o == null)
			throw new NullPointerException("Error: object is null.");

		if (size() == 0)
			return false;

		if (o.getClass() != head.getElement().getClass())
			throw new ClassCastException("Error: this object is incompatible with this list.");

		return (indexOf(o) >= 0);
	}

	/**
     * Returns the index of the first occurrence of the specified element
     * in this list, or -1 if this list does not contain the element.
     *
     * @param o element to search for
     * @return the index of the first occurrence of the specified element in
     *         this list, or -1 if this list does not contain the element
     */

	public int indexOf(Object o){

		if (o == null)
			return (-1);

		if (size() == 0)
			return (-1);

		if ((o.getClass() != head.getElement().getClass()))
			return (-1);

		int index = 0;

		Node<E> current = head;

		@SuppressWarnings("unchecked")
		E objA = (E) o;

		if(objA.equals(head.getElement()))
			return index;

		while(current.getNext() != null){

			if(objA.equals(current.getElement()))
				return index;

			current = current.getNext();
			index++;
		}

		if(objA.equals(current.getElement()))
			return index;

		return (-1);
	}

	/**
     * Returns a string representation of this list.  The string
     * representation consists of a list of the list's elements in the
     * order they are stored in this list, enclosed in square brackets
     * (<tt>"[]"</tt>).  Adjacent elements are separated by the characters
     * <tt>", "</tt> (comma and space).  Elements are converted to strings 
     * by the {@code toString()} method of those elements.
     *
     * @return a string representation of this list
     */

	public String toString(){

		StringBuilder s1 = new StringBuilder("[");

		if (size ==0){
			s1.append("]");
			return s1.toString();
		}

		Node<E> current = head;

		while(current.getNext() != null){
			s1.append(current.getElement().toString() + ", ");
			current = current.getNext();
		}

		s1.append(current.getElement().toString() + "]");

		return s1.toString();

	}


}






















