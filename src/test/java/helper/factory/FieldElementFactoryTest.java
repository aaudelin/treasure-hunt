package helper.factory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import exception.EntityException;
import model.entity.element.AdventurerElement;
import model.entity.element.MountainElement;
import model.entity.element.TreasureElement;
import model.entity.order.AOrder;
import model.entity.order.MoveOrder;
import model.entity.order.RotateOrder;
import model.entity.position.CoordinatePosition;

public class FieldElementFactoryTest {

	@Test(expected = EntityException.class)
	public void testAdventurerInvalidDirection() throws Exception {
		FieldElementFactory.createAdventurer("name", 0, 1, 'Z', "A");
	}

	@Test(expected = EntityException.class)
	public void testAdventurerInvalidOrders() throws Exception {
		FieldElementFactory.createAdventurer("name", 0, 1, 'N', "AGRED");
	}

	@Test
	public void testAdventurerValid() throws Exception {
		String name = "Name";
		int x = 6;
		int y = 8;
		char direction = 'S';
		String orders = "AGD";
		
		AdventurerElement adv = FieldElementFactory.createAdventurer(name, x, y, direction, orders);

		assertEquals(name, adv.getName());
		assertTrue(adv.getPosition() instanceof CoordinatePosition);
		CoordinatePosition position = (CoordinatePosition) adv.getPosition();
		
		assertEquals(x, position.getXCoordinate());
		assertEquals(y, position.getYCoordinate());
		assertEquals(direction, position.getDirection().getDirection());
		
		List<AOrder> ordersList = adv.getOrders();
		assertEquals(3, ordersList.size());
		assertTrue(ordersList.get(0) instanceof MoveOrder);
		assertTrue(ordersList.get(1) instanceof RotateOrder);
		assertTrue(ordersList.get(2) instanceof RotateOrder);
		
	}
	

	@Test
	public void testTreasure() {
		int quantity = 3;
		int x = 5;
		int y = 12;
		TreasureElement treasure = FieldElementFactory.createTreasure(x, y, quantity);
		
		assertEquals(quantity, treasure.getQuantity());
		
		assertTrue(treasure.getPosition() instanceof CoordinatePosition);
		CoordinatePosition position = (CoordinatePosition) treasure.getPosition();
		
		assertEquals(x, position.getXCoordinate());
		assertEquals(y, position.getYCoordinate());
		assertNull(position.getDirection());		
		
	}
	
	@Test
	public void testMountain() {
		int x = 5;
		int y = 12;
		MountainElement mountain = FieldElementFactory.createMountain(x, y);
		
		assertTrue(mountain.getPosition() instanceof CoordinatePosition);
		CoordinatePosition position = (CoordinatePosition) mountain.getPosition();
		
		assertEquals(x, position.getXCoordinate());
		assertEquals(y, position.getYCoordinate());
		assertNull(position.getDirection());		
		
	}
	

}
