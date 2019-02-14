package model.entity.element;

import java.util.List;

import exception.EntityException;
import model.common.EAvailableEntityTypes;
import model.entity.order.AOrder;
import model.entity.position.APosition;

/**
 * 
 * @author aaudelin
 *
 */
public class AdventurerElement extends AFieldElement {

	private String name;
	
	private int treasureCount;
	
	private List<AOrder> orders;
	
	public AdventurerElement(APosition pPosition, String pName, List<AOrder> pOrders) {
		super(pPosition);
		this.name = pName;
		this.orders = pOrders;
	}
	
	public AdventurerElement(APosition pPosition) {
		super(pPosition);
		this.canMove = true;
		this.type = EAvailableEntityTypes.TYPE_ADVENTURER;
	}
	
	@Override
	public APosition computeNextPosition(AOrder order) throws EntityException {
		return position.computePositionFromOrder(order);
		
	}
	
	public List<AOrder> getOrders() {
		return orders;
	}

	public void setOrders(List<AOrder> orders) {
		this.orders = orders;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTreasureCount() {
		return treasureCount;
	}

	public void setTreasureCount(int treasureCount) {
		this.treasureCount = treasureCount;
	}


}
