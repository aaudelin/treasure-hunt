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
	
	/**
	 * Try to pick a treasure
	 * @return true if a treasure has been picked
	 */
	public boolean pickTreasure() {
		if (this.quantity > 0) {
			this.quantity -= 1;
			return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(this.getType().getType())
			.append(FIELD_DELIMITOR)
			.append(this.getPosition().toString())
			.append(FIELD_DELIMITOR)
			.append(this.getQuantity());
		return builder.toString();
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
