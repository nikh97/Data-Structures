package project5;

/**
 * The class provides a recursive implementation for an AVL tree. Based on the
 * implementation of the BST_Recursive class authored by Joanna Klukowska.
 * 
 * @author Nikhil Gosike
 * @version 12/07/2017
 */
public class CollisionsData{

	/**
	 * Node class is used to represent nodes in an AVL tree.
	 * The Node class only stores objects of type Collision
	 * 
	 * @author Nikhil Gosike 
	 */
	protected static class Node implements Comparable<Node>{

		protected Node left;
		protected Node right;
		protected Collision data;

		protected int height;

		/**
		 * Constructs an AVL tree node initializing the data part 
		 * according to the parameter and setting both 
		 * references to subtrees to null.
		 * @param data
		 *    data or Collision object to be stored in the node
		 */
		protected Node(Collision data){

			this.data = data;
			left = null;
			right = null;
			height = 0;	
		}

		/* (non-Javadoc)
		 * @see java.lang.Comparable#compareTo(java.lang.Object)
		 */
		@Override
		public int compareTo(Node other){

			return this.data.compareTo(other.data);
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString(){
			return data.toString();
		}
	}

	protected Node root;
	protected int numOfElements;
	private boolean found;

	private int[] combinedData = new int[7];

	/**
	* Initializes the root and the number of elements in the tree
	*/
	public CollisionsData(){
		this.root = null;
		numOfElements = 0;
	}

	/**
	 * Add the given Collision object to the tree. If item is null, the tree does not
	 * change. If item already exists, the tree does not change. 
	 * 
	 * @param item the new element to be added to the tree
	 */
	public void add(Collision item){
		if(item == null)
			return;
		root = add(root, item);

	}

	/*
	 * Actual recursive implementation of add.  
	 * 
	 * @param item the new element to be added to the tree
	 */
	private Node add(Node node, Collision item){
		if (node == null){

			numOfElements++;
			return new Node(item);
		}

		if (node.data.compareTo(item) > 0)
			node.left = add(node.left, item);
		else if(node.data.compareTo(item) < 0)
			node.right = add(node.right, item);
		else
			return node;

		updateHeight(node);
		int bF = balanceFactor(node);

		if(bF > 1 && balanceFactor(node.right) >= 0){

			return balanceRR(node);
		} 

		if(bF < -1 && balanceFactor(node.left) <= 0){

			return balanceLL(node);
		}

		if(bF > 1 && balanceFactor(node.right) < 0){

			return balanceRL(node);
		}

		if(bF < -1 && balanceFactor(node.left) > 0){

			return balanceLR(node);
		}

		return node;

	}

	/**
	 * Remove the Collision from the tree. If item is null the tree remains unchanged. If
	 * item is not found in the tree, the tree remains unchanged.
	 * 
	 * @param target the item to be removed from this tree 
	 */
	public boolean remove(Collision target)
	{
		root = recRemove(target, root);
		return found;
	}

	/*
	 * Actual recursive implementation of remove method: find the node to remove.  
	 * 
	 * @param target the item to be removed from this tree 
	 */
	private Node recRemove(Collision target, Node node)
	{
		if (node == null)
			found = false;
		else if (target.compareTo(node.data) < 0)
			node.left = recRemove(target, node.left);
		else if (target.compareTo(node.data) > 0)
			node.right = recRemove(target, node.right );
		else {
			node = removeNode(node);
			found = true;
		}

		if (root == null)
			return root;

		updateHeight(node);
		int bF = balanceFactor(node);

		if(bF > 1 && balanceFactor(node.right) >= 0){

			return balanceRR(node);
		} 

		if(bF < -1 && balanceFactor(node.left) <= 0){

			return balanceLL(node);
		}

		if(bF > 1 && balanceFactor(node.right) < 0){

			return balanceRL(node);
		}

		if(bF < -1 && balanceFactor(node.left) > 0){

			return balanceLR(node);
		}

		return node;
	}

	/*
	 * Actual recursive implementation of remove method: perform the removal.  
	 * 
	 * @param target the item to be removed from this tree 
	 * @return a reference to the node itself, or to the modified subtree 
	 */
	private Node removeNode(Node node)
	{
		Collision data;
		if (node.left == null)
			return node.right ;
		else if (node.right  == null)
			return node.left;
		else {
			data = getPredecessor(node.left);
			node.data = data;
			node.left = recRemove(data, node.left);
			return node;
		}
	}

	/**
	 * Produces tree like string representation of this BST.
	 * @return string containing tree-like representation of this BST.
	 */
	public String toStringTreeFormat() {

		StringBuilder s = new StringBuilder();

		preOrderPrint(root, 0, s);
		return s.toString();
	}

	/**
	* Produces a report about the number of fatalities and injuries for
	* a number of Collision during a time period in a specific zipcode.
	*
	* @param zip is the zipcode that the user wants data on.
	*
	* @param dateBegin is the beginning of the time period the user wants data for.
	*
	* @param dateEnd is the end of the time period the user wants data for.
	*
	* @return the String that represents the report created.
	*/
	public String getReport(String zip, Date dateBegin, Date dateEnd){

		int numCollisions = 0;
		getReport(zip, dateBegin, dateEnd, root);

		StringBuilder report = new StringBuilder();
		report.append("Motor Vehicle Collisions for zipcode " + zip + "(" + dateBegin.toString() + " - " + dateEnd.toString() + ")\n");
		for(int i = 0; i < 67; i++){

			report.append("=");
		}

		report.append("\n");

		report.append("Total number of collisions: " + this.combinedData[6] + "\n");
		report.append("Number of fatalities: " + (this.combinedData[3] + this.combinedData[4] + this.combinedData[5]) + "\n");
		report.append("         pedestrians: " + this.combinedData[3] + "\n");
		report.append("            cyclists: " + this.combinedData[4] + "\n");
		report.append("           motorists: " + this.combinedData[5] + "\n");
		report.append("Number of injuries: " + (this.combinedData[0] + this.combinedData[1] + this.combinedData[2]) + "\n");
		report.append("       pedestrians: " + this.combinedData[0] + "\n");
		report.append("          cyclists: " + this.combinedData[1] + "\n");
		report.append("         motorists: " + this.combinedData[2] + "\n");


		return report.toString();


	}

	/*
	* Helper method that traverses the tree to find the nodes with the data needed.
	* Adds data if the Collision is within the date range and in thre zipcode.
	* 
	* 
	* @param zip is the zipcode that the user wants data on.
	*
	* @param dateBegin is the beginning of the time period the user wants data for.
	*
	* @param dateEnd is the end of the time period the user wants data for.
	*
	* @param n is the node that method is checking to see if it has the necessary data.
	*/
	private void getReport(String zip, Date dateBegin, Date dateEnd, Node n){

		if(n == null)
			return;

		if(n.data.getZip().compareTo(zip) < 0)
			getReport(zip, dateBegin, dateEnd, n.right);
		if(n.data.getZip().compareTo(zip) > 0)
			getReport(zip, dateBegin, dateEnd, n.left);
		if(n.data.getZip().equals(zip) && n.data.getDate().compareTo(dateBegin) < 0)
			getReport(zip, dateBegin, dateEnd, n.right);
		if(n.data.getZip().equals(zip) && n.data.getDate().compareTo(dateEnd) > 0)
			getReport(zip, dateBegin, dateEnd, n.left);

		if(n.data.getZip().equals(zip) && n.data.getDate().compareTo(dateBegin) >= 0 && n.data.getDate().compareTo(dateEnd) <= 0){
			this.combinedData[0] += n.data.getPedestriansInjured();
			this.combinedData[1] += n.data.getCyclistsInjured();
			this.combinedData[2] += n.data.getMotoristsInjured();
			this.combinedData[3] += n.data.getPedestriansKilled();
			this.combinedData[4] += n.data.getCyclistsKilled();
			this.combinedData[5] += n.data.getMotoristsKilled();
			this.combinedData[6]++;

			getReport(zip, dateBegin, dateEnd, n.left);
			getReport(zip, dateBegin, dateEnd, n.right);
		}

		
		

	}

	/*
	 * Actual recursive implementation of preorder traversal to produce tree-like string
	 * representation of this tree.
	 * 
	 * @param tree the root of the current subtree
	 * @param level level (depth) of the current recursive call in the tree to
	 *   determine the indentation of each item
	 * @param output the string that accumulated the string representation of this
	 *   BST
	 */
	private void preOrderPrint(Node tree, int level, StringBuilder output) {
		if (tree != null) {
			String spaces = "\n";
			if (level > 0) {
				for (int i = 0; i < level - 1; i++)
					spaces += "   ";
				spaces += "|--";
			}
			output.append(spaces);
			output.append(tree.data);
			preOrderPrint(tree.left, level + 1, output);
			preOrderPrint(tree.right , level + 1, output);
		}
		// uncomment the part below to show "null children" in the output
		else {
			String spaces = "\n";
			if (level > 0) {
				for (int i = 0; i < level - 1; i++)
					spaces += "   ";
				spaces += "|--";
			}
			output.append(spaces);
			output.append("null");
		}
	}

	/*
	 * Returns the information held in the rightmost node of subtree  
	 * 
	 * @param subtree root of the subtree within which to search for the rightmost node 
	 * @return returns data stored in the rightmost node of subtree  
	 */
	private Collision getPredecessor(Node subtree)
	{
		if (subtree == null) throw new NullPointerException("getPredecessor called with an empty subtree");
		Node temp = subtree;
		while (temp.right  != null)
			temp = temp.right ;
		return temp.data;
	}

	/*
	* Returns the new "root" of the tree or subtree after performing
	* a Left-Right rotation to balance the tree
	*
	* @param a is the node where the imbalance occurs
	*
	* returns the node that swap positions with the original node
	*/
	private Node balanceLR(Node a){

		Node b = a.left;
		Node c = b.right;

		a.left = c.right;
		b.right = c.left;

		c.right = a;
		c.left  = b;

		updateHeight(a);
		updateHeight(b);
		updateHeight(c);

		return c;

	}

	/*
	* Returns the new "root" of the tree or subtree after performing
	* a Right-Left rotation to balance the tree
	*
	* @param a is the node where the imbalance occurs
	*
	* returns the node that swap positions with the original node
	*/
	private Node balanceRL(Node a){

		Node b = a.right;
		Node c = b.left;

		a.right = c.left;
		b.left = c.right;
		c.right = b;
		c.left = a;

		updateHeight(a);
		updateHeight(b);
		updateHeight(c);

		return c;
	}

	/*
	* Returns the new "root" of the tree or subtree after performing
	* a Left-Left rotation to balance the tree
	*
	* @param a is the node where the imbalance occurs
	*
	* returns the node that swap positions with the original node
	*/
	private Node balanceLL(Node a){

		Node b = a.left;

		a.left = b.right;

		b.right = a;

		updateHeight(a);
		updateHeight(b);

		return b;
	}

	/*
	* Returns the new "root" of the tree or subtree after performing
	* a Right-Right rotation to balance the tree
	*
	* @param a is the node where the imbalance occurs
	*
	* returns the node that swap positions with the original node
	*/
	private Node balanceRR(Node a){

		Node b = a.right;

		a.right = b.left;

		b.left = a;

		updateHeight(a);
		updateHeight(b);

		return b; 
	}

	/* Caluculates the balance factor of a given node
	* 
	* @param n is the node where the balance factor is being calculated
	*
	* @return the balance factor as an integer
	*/
	private int balanceFactor(Node n){

		if(n.right == null)
			return -n.height;

		if(n.left == null)
			return n.height;

		return n.right.height - n.left.height;

	}

	/*
	* Recaluculates the height of the given node
	*
	* @param n is the node that is being updated
	*/
	private void updateHeight(Node n){

		if(n.left == null && n.right == null)
			n.height = 0;

		else if(n.left == null)
			n.height = n.right.height + 1;

		else if(n.right == null)
			n.height = n.left.height + 1;

		else
			n.height = Math.max(n.right.height, n.left.height) + 1;

	}

}