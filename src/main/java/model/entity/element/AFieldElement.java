package model.entity.element;

import exception.EntityException;
import model.common.EAvailableEntityTypes;
import model.entity.field.AField;
import model.entity.order.AOrder;
import model.entity.position.APosition;

/**
 * 
 * @author aaudelin
 *
 */
public abstract class AFieldElement {
	
	public static final String FIELD_DELIMITOR = " - ";
	
	APosition position;
	
	boolean canMove = false;
	
	EAvailableEntityTypes type;
	
	public AFieldElement(APosition pPosition) {
		this.position = pPosition;
	}
	
	/**
	 * Checks if two elements are in the same position
	 * 
	 * @param pPosition the position to compare
	 * @return true if the two position are in the same spot, false otherwise
	 * @throws EntityException 
	 */
	public boolean isAtSamePosition(APosition pPosition) throws EntityException {
		return this.position.isAtSamePosition(pPosition);
	}
	
	/**
	 * Computes the next position according to the given order
	 * 
	 * @param order the order to execute
	 * @return the next estimated position
	 * @throws EntityException 
	 */
	public APosition computeNextPositionFromOrder(AOrder order) throws EntityException {
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

	public APosition getPosition() {
		return position;
	}

	public void setPosition(APosition position) {
		this.position = position;
	}

	public boolean canMove() {
		return canMove;
	}

	public void setCanMove(boolean canMove) {
		this.canMove = canMove;
	}

	public EAvailableEntityTypes getType() {
		return type;
	}

	public void setType(EAvailableEntityTypes type) {
		this.type = type;
	}
	
}
