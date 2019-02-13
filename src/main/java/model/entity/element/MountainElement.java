package model.entity.element;

import model.common.EAvailableEntityTypes;
import model.entity.position.APosition;

/**
 * 
 * @author aaudelin
 *
 */
public class MountainElement extends AFieldElement {

	public MountainElement(APosition pPosition) {
		super(pPosition);
		this.canMove = false;
		this.type = EAvailableEntityTypes.TYPE_MOUNTAIN;
	}

}
