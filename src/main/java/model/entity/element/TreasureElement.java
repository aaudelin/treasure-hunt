package model.entity.element;

import model.common.EAvailableEntityTypes;
import model.entity.position.APosition;

/**
 * 
 * @author aaudelin
 *
 */
public class TreasureElement extends AFieldElement {

	private int quantity;
	
	public TreasureElement(APosition pPosition, int pQuantity) {
		this(pPosition);
		this.setQuantity(pQuantity);
	}
	
	public TreasureElement(APosition pPosition) {
		super(pPosition);
		this.canMove = false;
		this.type = EAvailableEntityTypes.TYPE_TREASURE;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
