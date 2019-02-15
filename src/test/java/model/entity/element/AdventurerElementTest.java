package model.entity.element;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import model.common.EAvailableDirection;
import model.entity.order.AOrder;
import model.entity.order.MoveOrder;
import model.entity.position.APosition;
import model.entity.position.CoordinatePosition;

public class AdventurerElementTest {

	@Test
	public void testFindNextPositionToMove() throws Exception {
		CoordinatePosition position = new CoordinatePosition();
		position.setDirection(EAvailableDirection.DIRECTION_NORTH);
		position.setXCoordinate(5);
		position.setYCoordinate(6);
		List<AOrder> orders = new ArrayList<AOrder>();
		
		orders.add(new MoveOrder('A'));
		
		AdventurerElement adv = new AdventurerElement(position, "Name", orders);
		
		APosition positionAdv = adv.findNextPosition();
		
		assertFalse(position.isAtSamePosition(positionAdv));
		
	}
	
	@Test
	public void testMove() throws Exception {
		CoordinatePosition position = new CoordinatePosition();
		position.setDirection(EAvailableDirection.DIRECTION_NORTH);
		position.setXCoordinate(5);
		position.setYCoordinate(6);
		List<AOrder> orders = new ArrayList<AOrder>();
		
		orders.add(new MoveOrder('A'));
		
		AdventurerElement adv = new AdventurerElement(position, "Name", orders);
		
		APosition positionAdv = adv.findNextPosition();
		
		assertFalse(position.isAtSamePosition(positionAdv));
		CoordinatePosition positionNext = new CoordinatePosition();
		positionNext.setXCoordinate(5);
		positionNext.setYCoordinate(5);	
		adv.move();
		assertTrue(adv.getPosition().isAtSamePosition(positionNext));
		assertTrue(adv.getOrders().isEmpty());;
		
	}
	
	@Test
	public void testDoNotMove() throws Exception {
		CoordinatePosition position = new CoordinatePosition();
		position.setDirection(EAvailableDirection.DIRECTION_NORTH);
		position.setXCoordinate(5);
		position.setYCoordinate(6);
		List<AOrder> orders = new ArrayList<AOrder>();
		
		orders.add(new MoveOrder('A'));
		
		AdventurerElement adv = new AdventurerElement(position, "Name", orders);
		
		adv.doNotMove();
		assertTrue(adv.getOrders().isEmpty());;
		
	}
	
	@Test
	public void testWait() throws Exception {
		CoordinatePosition position = new CoordinatePosition();
		position.setDirection(EAvailableDirection.DIRECTION_NORTH);
		position.setXCoordinate(5);
		position.setYCoordinate(6);
		List<AOrder> orders = new ArrayList<AOrder>();
		
		orders.add(new MoveOrder('A'));
		
		AdventurerElement adv = new AdventurerElement(position, "Name", orders);
		
		adv.waitAvailability();
		assertFalse(adv.getOrders().isEmpty());;
		
	}

}
