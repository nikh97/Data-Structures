package project3;

import static org.junit.Assert.*;
import org.junit.Test;

public class PossibleLocationsStackTest{

	@Test
	public void testIsEmptyWithEmptyStack(){

		PossibleLocationsStack s = new PossibleLocationsStack();
		assertTrue("isEmpty() does not return true for empty Stack.", s.isEmpty());
	}

	@Test
	public void testIsEmptyWithFilledStack(){

		PossibleLocationsStack s = new PossibleLocationsStack();

		try{
			Location l = new Location(2,5);
			s.add(l);
		} catch(Exception e){
			fail("Unexpected exception caught when adding to the stack");
		}

		assertTrue("isEmpty() does not return false for filled Stack.", !(s.isEmpty()));
	}	
	@Test
	public void testAddNullToEmpty(){

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
		testAddSize(loc);
	}

	@Test
	public void testAddLocationToNonEmpty(){

		Location[] loc = {new Location(2,5), new Location(3,7), new Location(10, 8)};
		testAddSize(loc);
	}

	@Test
	public void testRemoveToEmpty(){

		PossibleLocationsStack stack = new PossibleLocationsStack();

		try{

			Location loc = stack.remove();

			if(loc != null)
				fail("The removed item from the empty stack is not null.");

			if(!(stack.isEmpty()))
				fail("Size must stay at 0 for an empty stack.");
		}

		catch(Exception e){

			fail("Unexpected exception caught when removing null element from the stack:\n" + e.toString());
		}

	}

	@Test
	public void testRemoveOnSizeOneStack(){

		PossibleLocationsStack stack = new PossibleLocationsStack();
		Location loc = new Location(2,3);

		try{

			stack.add(loc);
			assertFalse("Size is still zero at this point", stack.isEmpty());

		} catch(Exception e){

			fail("Unexpected exception when trying to add element to the stack:\n" + e.toString());
		}

		try{

			Location temp = stack.remove();

			if (!(temp.equals(loc)))
				fail("The remove() method failed to return the correct object");

			assertTrue("Size must be empty at this point", stack.isEmpty());

		} catch(Exception e){
				fail("Unexpected exception when trying to remove element from the stack:\n" + e.toString());
		}

	}

	@Test
	public void testAddRemovetoEmpty(){

		PossibleLocationsStack stack = new PossibleLocationsStack();
		Location[] locations =  {new Location(2,5), new Location(3,7), new Location(10, 8)};

		try{

			for(int i = 0; i<locations.length; i++){

				stack.add(locations[i]);
			}
		}catch(Exception e){

			fail("Unexpected exception when trying to add element to the stack:\n" + e.toString());

		}

		try{

			for(int i = 3; i > 0; i--){

				Location temp = stack.remove();

				if(!(temp.equals(locations[i-1])))
					fail("The remove method did not remove the correct object");
			}

		}catch(Exception e){

			fail("Unexpected exception when trying to remove element from the stack:\n" + e.toString());
		}

		assertTrue("List should be empty after removing all elements", isEmpty());
	}

	private void testAddSize(Location[] loc){

		PossibleLocationsStack stack = new PossibleLocationsStack();

		try{

			for(int i = 0; i < loc.length; i++)
				stack.add(loc[i]);

		} catch(Exception e){
				fail("Unexpected exception caught when adding elements from the stack:\n" + e.toString());
		}

		try{

			Location temp = new Location(2,3);

			stack.add(temp);

		} catch(Exception e){
			fail("Unexpected exception caught when adding elements from the stack:\n" + e.toString());
		}

	}


	private void testAddWithNull(Location[] loc){

		PossibleLocationsStack stack = new PossibleLocationsStack();
		assert stack != null : "Stack variable is null or empty";

		try{

			for(int i = 0; i < loc.length; i++)
				stack.add(loc[i]);

		}catch (Exception e){
			fail("Unexpected exception caught when adding to the stack " + ":\n" + e.toString());
		}

		try{

			stack.add(null);

		}catch(Exception e){

			fail("Unexpected exception caught when adding null to the stack " + ":\n" + e.toString());
		}
	}	
}