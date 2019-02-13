package helper.factory;

import model.entity.field.CoordinateField;

/**
 * 
 * @author aaudelin
 *
 */
public class FieldFactory {

	/**
	 * Create a coordinate field
	 * 
	 * @param xPos max x position
	 * @param yPos max y position
	 * @return the field with its positions
	 */
	public static CoordinateField createCoordinateField(int xPos, int yPos) {
		CoordinateField field = new CoordinateField();
		field.setMinXCoordinate(0);
		field.setMinYCoordinate(0);
		field.setMaxXCoordinate(xPos);
		field.setMaxYCoordinate(yPos);

		return field;

	}

}
