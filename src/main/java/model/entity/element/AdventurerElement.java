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
		this(pPosition);
		this.name = pName;
		this.orders = pOrders;
	}
	
	public AdventurerElement(APosition pPosition) {
		super(pPosition);
		this.canMove = true;
		this.type = EAvailableEntityTypes.TYPE_ADVENTURER;
	}
	
	@Override
	public APosition computeNextPositionFromOrder(AOrder order) throws EntityException {
		return position.computePositionFromOrder(order);
		
	}
	
	/**
	 * Find the next position according to the first order of the adventurer
	 * 
	 * @return the estimated new position
	 * @throws EntityException
	 */
	public APosition findNextPosition() throws EntityException {
		if (!this.hasRemainingOrders()) {
			return this.position;
		}
		return this.computeNextPositionFromOrder(this.orders.get(0));	
	}
	
	/**
	 * Moves an adventurer
	 * @throws EntityException 
	 */
	public void move() throws EntityException {
		if (!this.hasRemainingOrders()) {
			return;
		}
		APosition nextPosition = position.computePositionFromOrder(this.orders.get(0));
		this.position = nextPosition;
		this.orders.remove(0);
	}
	
	/**
	 * Do not move an adventurer
	 * It will skip to the next order
	 * @throws EntityException 
	 */
	public void doNotMove() throws EntityException {
		if (!this.hasRemainingOrders()) {
			return;
		}
		this.orders.remove(0);
	}
	
	/**
	 * Do not move an adventurer
	 * It will wait for availability
	 * @throws EntityException 
	 */
	public void waitAvailability() throws EntityException {
		return;
	}
	
	/**
	 * Indicates whether an adventurer has remaining orders to execute
	 * 
	 * @return true if some orders must be executed, false otherwise
	 */
	public boolean hasRemainingOrders() {
		return !this.orders.isEmpty();
	}
	
	/**
	 * Add a treasure count to the count of the adventurer
	 */
	public void pickUpTreasure() {
		this.treasureCount += 1;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(this.getType().getType())
			.append(FIELD_DELIMITOR)
			.append(this.getName())
			.append(FIELD_DELIMITOR)
			.append(this.getPosition().toString())
			.append(FIELD_DELIMITOR)
			.append(this.getTreasureCount());
		return builder.toString();
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
