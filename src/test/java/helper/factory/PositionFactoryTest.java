package helper.factory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import exception.PositionException;
import model.common.EAvailableDirection;
import model.entity.position.CoordinatePosition;

public class PositionFactoryTest {

	@Test(expected = PositionException.class)
	public void testInvalidDirection() throws Exception {
		PositionFactory.createCoordinatePosition(1, 1, 'Z');
	}
	
	@Test
	public void testValidPositonWithNoDirection() throws Exception {
		int x = 1;
		int y = 3;

		CoordinatePosition position = PositionFactory.createCoordinatePosition(x, y);

		assertNull(position.getDirection());
		assertEquals(position.getXCoordinate(), x);
		assertEquals(position.getYCoordinate(), y);
	}
	
	@Test
	public void testValidPosition() throws Exception {
		int x = 1;
		int y = 3;
		EAvailableDirection direction = EAvailableDirection.DIRECTION_NORTH;

		CoordinatePosition position = PositionFactory.createCoordinatePosition(x, y, direction.getDirection());

		assertEquals(position.getDirection(), direction);
		assertEquals(position.getXCoordinate(), x);
		assertEquals(position.getYCoordinate(), y);
	}

}
