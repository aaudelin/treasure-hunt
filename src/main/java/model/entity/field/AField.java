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
	
	EAvailableEntityTypes type = EAvailableEntityTypes.TYPE_MAP;
	
	List<AFieldElement> fieldElements;
	
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
		
		for (AFieldElement elementItem : fieldElements) {
			if (elementItem.isAtSamePosition(element.getPosition())) {
				throw new FieldException("An element is already at the requested position");
			}
		}
		
		this.fieldElements.add(element);
		return fieldElements;
	}
	

}
