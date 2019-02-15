package helper.factory;

import model.entity.field.MadreDeDiosField;

/**
 * 
 * @author aaudelin
 *
 */
public class FieldFactory {

	/**
	 * Create a madre de dios field
	 * 
	 * @param xPos max x position
	 * @param yPos max y position
	 * @return the field with its positions
	 */
	public static MadreDeDiosField createMadreDeDiosField(int xPos, int yPos) {
		MadreDeDiosField field = new MadreDeDiosField();
		field.setMinXCoordinate(0);
		field.setMinYCoordinate(0);
		field.setMaxXCoordinate(xPos);
		field.setMaxYCoordinate(yPos);

		return field;

	}

}
