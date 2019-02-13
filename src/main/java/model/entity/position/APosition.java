package model.entity.position;

import exception.PositionException;
import model.entity.field.AField;
import model.entity.order.AOrder;

/**
 * 
 * @author aaudelin
 *
 */
public abstract class APosition {

	/**
	 * Computes the next position of the mower according to the given order
	 * 
	 * @param order the order to execute
	 * @return the next estimated position
	 * @throws PositionException
	 */
	public abstract APosition computePositionFromOrder(AOrder order) throws PositionException;

	/**
	 * Checks if a position is included within a coordinate field
	 * 
	 * @param field the field to mow
	 * @return true if the position is available in the field, false otherwise
	 * @throws PositionException
	 */
	public abstract boolean isIncludedInField(AField field) throws PositionException;

	/**
	 * Checks if two positions are the same spot
	 * @param pPosition the position to compare
	 * @return true if the two position are in the same spot, false otherwise
	 * @throws PositionException 
	 */
	public abstract boolean isAtSamePosition(APosition pPosition) throws PositionException;
}
