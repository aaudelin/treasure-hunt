package helper.factory;

import java.util.List;

import exception.EntityException;
import exception.FieldElementException;
import model.entity.element.AdventurerElement;
import model.entity.element.MountainElement;
import model.entity.element.TreasureElement;
import model.entity.order.AOrder;
import model.entity.position.APosition;

/**
 * 
 * @author aaudelin
 *
 */
public class FieldElementFactory {

	/**
	 * Creates an adventurer able to move on a field
	 * 
	 * @param name      the name of the adventurer
	 * @param x         the x coordinate of the adventurer
	 * @param y         the y coordinate of the adventurer
	 * @param direction the direction of the adventurer
	 * @param orders    the list or orders of the adventurer
	 * @return a new adventurer
	 * @throws EntityException
	 */
	public static AdventurerElement createAdventurer(String name, int x, int y, char direction, String orders)
			throws EntityException {
		APosition position;
		List<AOrder> orderList;
		try {
			position = PositionFactory.createCoordinatePosition(x, y, direction);
			orderList = OrderFactory.createOrderList(orders);
		} catch (EntityException exception) {
			throw new FieldElementException("Error while creating adventurer, message : " + exception.getMessage());
		}
		return new AdventurerElement(position, name, orderList);
	}

	/**
	 * Creates a treasure
	 * 
	 * @param x        the x coordinate of the treasure
	 * @param y        the y coordinate of the treasure
	 * @param quantity the number of treasures
	 * @return a new treasure
	 */
	public static TreasureElement createTreasure(int x, int y, int quantity) {
		APosition position = PositionFactory.createCoordinatePosition(x, y);
		return new TreasureElement(position, quantity);
	}

	/**
	 * Creates a mountain
	 * 
	 * @param x the x coordinate of the mountain
	 * @param y the y coordinate of the mountain
	 * @return a new mountain
	 */
	public static MountainElement createMountain(int x, int y) {
		APosition position = PositionFactory.createCoordinatePosition(x, y);
		return new MountainElement(position);
	}

}
