package model.entity.field;

import exception.EntityException;
import model.entity.element.AFieldElement;
import model.entity.element.AdventurerElement;
import model.entity.element.MountainElement;
import model.entity.element.TreasureElement;
import model.entity.position.APosition;

/**
 * 
 * @author aaudelin
 *
 */
public class MadreDeDiosField extends AField {

	int minXCoordinate = 0;

	int minYCoordinate = 0;

	int maxXCoordinate = 0;

	int maxYCoordinate = 0;
	
	@Override
	public boolean executeNextRound() throws EntityException {
		boolean needsNextRound = false;
		
		for (AFieldElement element : this.fieldElements) {
			if (!element.canMove()) {
				// If the element cannot move, skip to the next one
				continue;
			}
			
			if (!(element instanceof AdventurerElement)) {
				// If the element is not an adventurer it is not handled yet
				System.out.println("Movable element type not handled yet");
				continue;
			}
			
			AdventurerElement adventurer = (AdventurerElement) element;
			
			if (!adventurer.hasRemainingOrders()) {
				// If the adventurer has no next run assume no other turn must be made
				needsNextRound = false;
				continue;
			}
			needsNextRound = true;
			APosition nextAdvPosition = adventurer.findNextPosition();
			
			// If the next position is out do not move
			if (!nextAdvPosition.isIncludedInField(this)) {
				adventurer.doNotMove();
				continue;
			}
			
			// If the position is the same spot just move (example for rotation)
			if (nextAdvPosition.isAtSamePosition(adventurer.getPosition())) {
				adventurer.move();
				continue;
			}
			
			
			AFieldElement alreadyExistingElement = this.findElementAtPosition(nextAdvPosition);
			
			if (null == alreadyExistingElement) {
				// If it is an free space just go on it
				adventurer.move();
			} else if (alreadyExistingElement instanceof MountainElement) {
				// If the next position is a mountain do not move and skip to next order
				adventurer.doNotMove();
			} else if (alreadyExistingElement instanceof AdventurerElement) {
				// If it is an adventurer handle conflicts and move both or wait first the other to free the space
				AdventurerElement alreadyExistingAdv = (AdventurerElement) alreadyExistingElement;
				APosition existingAdvNextPos = alreadyExistingAdv.findNextPosition();
				
				if (existingAdvNextPos.isAtSamePosition(adventurer.getPosition())) {
					alreadyExistingAdv.move();
					adventurer.move();
				} else {
					adventurer.waitAvailability();
				}
				
			} else if (alreadyExistingElement instanceof TreasureElement) {
				// If it is a treasure pick it up
				adventurer.move();
				TreasureElement treasure = (TreasureElement) alreadyExistingElement;
				if (treasure.pickTreasure()) {
					adventurer.pickUpTreasure();
				}
			} 
		}
		
		return needsNextRound;
		
	}
	
	/**
	 * Find an element in the map that is located as the same position as the given one
	 * @param position the position to seek
	 * @return the element in the given position, null if no element exists
	 * @throws EntityException
	 */
	private AFieldElement findElementAtPosition(APosition position) throws EntityException {
		for (AFieldElement element : this.fieldElements) {
			if (element.isAtSamePosition(position)) {
				return element;
			}
		}
		return null;
		
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(this.getType().getType())
			.append(FIELD_DELIMITOR)
			.append(this.getMaxXCoordinate())
			.append(FIELD_DELIMITOR)
			.append(this.getMaxYCoordinate());
		return builder.toString();
	}

	public int getMinXCoordinate() {
		return minXCoordinate;
	}

	public void setMinXCoordinate(int minXCoordinate) {
		this.minXCoordinate = minXCoordinate;
	}

	public int getMinYCoordinate() {
		return minYCoordinate;
	}

	public void setMinYCoordinate(int minYCoordinate) {
		this.minYCoordinate = minYCoordinate;
	}

	public int getMaxXCoordinate() {
		return maxXCoordinate;
	}

	public void setMaxXCoordinate(int maxXCoordinate) {
		this.maxXCoordinate = maxXCoordinate;
	}

	public int getMaxYCoordinate() {
		return maxYCoordinate;
	}

	public void setMaxYCoordinate(int maxYCoordinate) {
		this.maxYCoordinate = maxYCoordinate;
	}
}
