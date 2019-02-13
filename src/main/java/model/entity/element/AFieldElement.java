package model.entity.element;

import exception.EntityException;
import model.entity.field.AField;
import model.entity.order.AOrder;
import model.entity.position.APosition;

/**
 * 
 * @author aaudelin
 *
 */
public abstract class AFieldElement {
	
	APosition position;
	
	boolean canMove = false;
	
	/**
	 * Checks if two elements are in the same position
	 * @param pPosition the position to compare
	 * @return true if the two position are in the same spot, false otherwise
	 * @throws EntityException 
	 */
	public boolean isAtSamePosition(APosition pPosition) throws EntityException {
		return this.position.isAtSamePosition(pPosition);
	}
	
	/**
	 * Computes the next position of the mower according to the given order
	 * 
	 * @param order the order to execute
	 * @return the next estimated position
	 */
	public APosition computeNextPosition(AOrder order) {
		return this.position;
	}
	
	/**
	 * Checks if a position is included within a field
	 * 
	 * @param field the field
	 * @return true if the position is available in the field, false otherwise
	 * @throws EntityException
	 */
	public boolean isIncludedInField(AField field) throws EntityException {
		return this.position.isIncludedInField(field);
	}

}
