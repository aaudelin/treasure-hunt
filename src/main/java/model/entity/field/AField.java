package model.entity.field;

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

}
