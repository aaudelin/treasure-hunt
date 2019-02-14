package model.entity.field;

import java.util.ArrayList;
import java.util.List;

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
	 */
	public List<AFieldElement> addFieldElement(AFieldElement element) {
		if (null == fieldElements) {
			this.fieldElements = new ArrayList<AFieldElement>();
		}
		
		this.fieldElements.add(element);
		return fieldElements;
	}
	

}
