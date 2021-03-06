package model.entity.field;

import java.util.ArrayList;
import java.util.List;

import exception.EntityException;
import exception.FieldException;
import model.common.EAvailableEntityTypes;
import model.entity.element.AFieldElement;

/**
 * 
 * @author aaudelin
 *
 */
public abstract class AField {
	
	public static final String FIELD_DELIMITOR = " - ";
	
	EAvailableEntityTypes type = EAvailableEntityTypes.TYPE_MAP;
	
	List<AFieldElement> fieldElements;
	
	/**
	 * Execute a round for all elements available in the field
	 * 
	 * @return true if a next round should be executed
	 * @throws EntityException 
	 */
	public abstract boolean executeNextRound() throws EntityException;
	
	/**
	 * Add an element to the field
	 * @param element the element to add
	 * @return the field's list of elements
	 * @throws EntityException 
	 */
	public List<AFieldElement> addFieldElement(AFieldElement element) throws EntityException {
		if (null == fieldElements) {
			this.fieldElements = new ArrayList<AFieldElement>();
		}
		
		if (null == element) {
			return this.fieldElements;
		}
		
		if (!element.isIncludedInField(this)) {
			throw new FieldException("The element is not included in the field");
		}
		
		for (AFieldElement elementItem : fieldElements) {
			if (elementItem.isAtSamePosition(element.getPosition())) {
				throw new FieldException("An element is already at the requested position");
			}
		}
		
		this.fieldElements.add(element);
		return fieldElements;
	}

	public EAvailableEntityTypes getType() {
		return type;
	}

	public List<AFieldElement> getFieldElements() {
		return fieldElements;
	}
	

}
