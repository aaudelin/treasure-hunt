package helper.factory;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import model.entity.field.CoordinateField;

public class FieldFactoryTest {

	@Test
	public void testCreateCoordinateField() {
		int x = 8;
		int y = 9;
		int min = 0;
		CoordinateField field = FieldFactory.createCoordinateField(x, y);

		assertEquals(field.getMinXCoordinate(), min);
		assertEquals(field.getMinYCoordinate(), min);
		assertEquals(field.getMaxXCoordinate(), x);
		assertEquals(field.getMaxYCoordinate(), y);
	}

}
