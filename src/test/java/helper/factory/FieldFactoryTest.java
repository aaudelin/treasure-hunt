package helper.factory;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import model.entity.field.MadreDeDiosField;

public class FieldFactoryTest {

	@Test
	public void testCreateMadreDeDiosField() {
		int x = 8;
		int y = 9;
		int min = 0;
		MadreDeDiosField field = FieldFactory.createMadreDeDiosField(x, y);

		assertEquals(field.getMinXCoordinate(), min);
		assertEquals(field.getMinYCoordinate(), min);
		assertEquals(field.getMaxXCoordinate(), x);
		assertEquals(field.getMaxYCoordinate(), y);
	}

}
