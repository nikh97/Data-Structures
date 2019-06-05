package project3;

import static org.junit.Assert.*;
import org.junit.Test;

public class PossibleLocationsQueueTest{

	@Test
	public void testConstructorInvalid(){

		System.out.println("Parameters used: String number");

		try{

			@SuppressWarnings("unused")
			PossibleLocationsQueue q = new PossibleLocationsQueue(new String("number"));

			fail("IllegalArgumentException not thrown with invalid constructor parameters.");
		} catch(IllegalArgumentException e){
			//do nothing, correct
		} catch(Exception e){

			fail("Incorrect exception type thrown.");
		}

	}

	@Test
	public void testConstructorValid(){

		System.out.println("Parameters used: int 50");

		try{

			@SuppressWarnings("unused")
			PossibleLocationsQueue q = new PossibleLocationsQueue(50);

		}catch (IllegalArgumentException e ) {
			fail("Exception thrown incorrectly (for valid arguments)."+e.getMessage());
		}catch (Exception e ) {
			fail("Exception thrown incorrectly (for valid arguments).");
		}
	}

	@Test
	public void testConstructorNegParam(){

		System.out.println("Parameters used: int -10");
		try{

			@SuppressWarnings("unused")
			PossibleLocationsQueue q = new PossibleLocationsQueue(-10);
		} catch(Exception e){

			fail("Unexpected exception caught when trying to create queue.");
		}

	}

	@Test
	public void testIsEmptyWithEmptyQueue(){

		PossibleLocationsQueue q = new PossibleLocationsQueue();
		assertTrue("isEmpty() does not return true for empty queue.", q.isEmpty());
	}

	@Test
	public void testIsEmptyWithFilledQueue(){

		PossibleLocationsQueue q = new PossibleLocationsQueue();

		try{
			Location l = new Location(2,5);
			q.add(l);
		} catch(Exception e){
			fail("Unexpected exception caught when adding to the queue");
		}

		assertTrue("isEmpty() does not return false for filled queue.", !(q.isEmpty()));
	}

	@Test
	public void testAddNulltoEmpty(){

		Location[] loc = {};
		testAddWithNull(loc);

	}

	@Test
	public void testAddNullToNonEmpty(){

		Location[] loc = {new Location(2,5), new Location(3,7), new Location(10, 8)};
		testAddWithNull(loc);
	}

	@Test
	public void testAddLocationToEmpty(){

		Location[] loc = {};
		PossibleLocationsQueue q = new PossibleLocationsQueue();
		testAdd(loc, q);
	}

	@Test
	public void testAddLocationToNonEmpty(){

		Location[] loc = {new Location(2,5), new Location(3,7), new Location(10, 8)};
		PossibleLocationsQueue q = new PossibleLocationsQueue();
		testAdd(loc, q);
	}

	@Test
	public void testAddLocationtoFull(){
		Location[] loc = {new Location(2,5), new Location(3,7), new Location(10, 8)};
		PossibleLocationsQueue q = new PossibleLocationsQueue(loc.length);
		testAdd(loc, q);
	}

	@Test
	public void testRemoveToEmpty(){

		PossibleLocationsQueue q = new PossibleLocationsQueue();

		try{

			Location loc = q.remove();

			if(loc != null)
				fail("The removed item from the empty queue is not null.");

			if(!(q.isEmpty()))
				fail("Size must stay at 0 for an empty queue.");
		}

		catch(Exception e){

			fail("Unexpected exception caught when removing null element from the queue:\n" + e.toString());
		}
	}
	
	@Test
	public void testRemoveOnSizeOneStack(){

		PossibleLocationsQueue q = new PossibleLocationsQueue();
		Location loc = new Location(2,3);

		try{

			q.add(loc);
			assertFalse("Size is still zero at this point", q.isEmpty());

		} catch(Exception e){

			fail("Unexpected exception when trying to add element to the queue:\n" + e.toString());
		}

		try{

			Location temp = q.remove();

			if (!(temp.equals(loc)))
				fail("The remove() method failed to return the correct object");

			assertTrue("Size must be empty at this point", q.isEmpty());

		} catch(Exception e){
				fail("Unexpected exception when trying to remove element from the queue:\n" + e.toString());
		}

	}	

	@Test
	public void testAddRemovetoEmpty(){

		PossibleLocationsQueue q = new PossibleLocationsQueue();
		Location[] locations =  {new Location(2,5), new Location(3,7), new Location(10, 8)};

		try{

			for(int i = 0; i<locations.length; i++){

				q.add(locations[i]);
			}
		}catch(Exception e){

			fail("Unexpected exception when trying to add element to the stack:\n" + e.toString());

		}

		try{

			for(int i = 0; i < 3; i++){

				Location temp = q.remove();

				if(!(temp.equals(locations[i])))
					fail("The remove method did not remove the correct object");
			}
		}catch(Exception e){

			fail("Unexpected exception when trying to remove element from the queue:\n" + e.toString());
		}

		assertTrue("List should be empty after removing all elements", q.isEmpty());

	}

	private void testAdd(Location[] loc, PossibleLocationsQueue q){

		try{

			for(int i = 0; i < loc.length; i++)
				q.add(loc[i]);

		} catch(Exception e){
				fail("Unexpected exception caught when adding elements from the stack:\n" + e.toString());
		}

		try{

			Location temp = new Location(2,3);

			q.add(temp);

		} catch(Exception e){
			fail("Unexpected exception caught when adding elements from the stack:\n" + e.toString());
		}

	}

	private void testAddWithNull(Location[] loc){

		PossibleLocationsQueue q = new PossibleLocationsQueue();
		assert q != null : "Stack variable is null or empty";

		try{

			for(int i = 0; i < loc.length; i++)
				q.add(loc[i]);

		}catch (Exception e){
			fail("Unexpected exception caught when adding to the stack " + e.toString());
		}

		try{

			q.add(null);

		}catch(Exception e){

			fail("Unexpected exception caught when adding null to the stack " + ":\n" + e.toString());
		}
	}	
}