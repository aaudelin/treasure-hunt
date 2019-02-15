package model.entity.position;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import helper.factory.FieldFactory;
import helper.factory.PositionFactory;
import model.common.EAvailableDirection;
import model.entity.field.AField;
import model.entity.order.MoveOrder;
import model.entity.order.RotateOrder;

public class CoordinatePositionTest {

	@Test
	public void testRotateLeftFromNorth() throws Exception {
		CoordinatePosition position = PositionFactory.createCoordinatePosition(0, 0, 'N');
		assertEquals(EAvailableDirection.DIRECTION_NORTH, position.getDirection());

		CoordinatePosition newPosition = position.computePositionFromOrder(new RotateOrder('G'));
		assertEquals(EAvailableDirection.DIRECTION_WEST, newPosition.getDirection());
	}

	@Test
	public void testRotateRightFromSouth() throws Exception {
		CoordinatePosition position = PositionFactory.createCoordinatePosition(0, 0, 'S');
		assertEquals(EAvailableDirection.DIRECTION_SOUTH, position.getDirection());

		CoordinatePosition newPosition = position.computePositionFromOrder(new RotateOrder('D'));
		assertEquals(EAvailableDirection.DIRECTION_WEST, newPosition.getDirection());

	}

	@Test
	public void testMoveForwardFromSouth() throws Exception {
		CoordinatePosition position = PositionFactory.createCoordinatePosition(0, 15, 'S');
		assertEquals(EAvailableDirection.DIRECTION_SOUTH, position.getDirection());
		assertEquals(15, position.getYCoordinate());

		CoordinatePosition newPosition = position.computePositionFromOrder(new MoveOrder('A'));
		assertEquals(EAvailableDirection.DIRECTION_SOUTH, newPosition.getDirection());
		assertEquals(16, newPosition.getYCoordinate());

	}

	@Test
	public void testMoveForwardFromEast() throws Exception {
		CoordinatePosition position = PositionFactory.createCoordinatePosition(6, 15, 'E');
		assertEquals(EAvailableDirection.DIRECTION_EAST, position.getDirection());
		assertEquals(6, position.getXCoordinate());

		CoordinatePosition newPosition = position.computePositionFromOrder(new MoveOrder('A'));
		assertEquals(EAvailableDirection.DIRECTION_EAST, newPosition.getDirection());
		assertEquals(7, newPosition.getXCoordinate());

	}
	
	@Test
	public void testIsIncludedInFile() throws Exception {
		CoordinatePosition position = PositionFactory.createCoordinatePosition(6, 15, 'E');
		AField field = FieldFactory.createMadreDeDiosField(30, 40);
		boolean result = position.isIncludedInField(field);
		
		assertTrue(result);
	}
	
	@Test
	public void testIsIncludedInFileLimit() throws Exception {
		CoordinatePosition position = PositionFactory.createCoordinatePosition(6, 15, 'E');
		AField field = FieldFactory.createMadreDeDiosField(6, 15);
		boolean result = position.isIncludedInField(field);
		
		assertTrue(result);
	}
	
	@Test
	public void testNotIsIncludedInFile() throws Exception {
		CoordinatePosition position = PositionFactory.createCoordinatePosition(6, 15, 'E');
		AField field = FieldFactory.createMadreDeDiosField(5, 5);
		boolean result = position.isIncludedInField(field);
		
		assertFalse(result);
	}
	
	@Test
	public void testSamePosition() throws Exception {
		CoordinatePosition position1 = PositionFactory.createCoordinatePosition(6, 15, 'E');
		CoordinatePosition position2 = PositionFactory.createCoordinatePosition(6, 15, 'E');
		boolean result = position1.isAtSamePosition(position2);
		
		assertTrue(result);
	}
	
	@Test
	public void testSamePositionNotDirection() throws Exception {
		CoordinatePosition position1 = PositionFactory.createCoordinatePosition(6, 15, 'E');
		CoordinatePosition position2 = PositionFactory.createCoordinatePosition(6, 15, 'W');
		boolean result = position1.isAtSamePosition(position2);
		
		assertTrue(result);
	}
	
	@Test
	public void testNotSamePosition() throws Exception {
		CoordinatePosition position1 = PositionFactory.createCoordinatePosition(6, 14, 'E');
		CoordinatePosition position2 = PositionFactory.createCoordinatePosition(6, 15, 'E');
		boolean result = position1.isAtSamePosition(position2);
		
		assertFalse(result);
	}
}
