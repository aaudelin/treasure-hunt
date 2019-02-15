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
	
	/**
	 * Execute a round for all elements available in the field
	 * 
	 * @return true if a next round should be executed
	 * @throws EntityException 
	 */
	public boolean executeNextRound() throws EntityException {
		boolean needsNextRound = false;
		
		for (AFieldElement element : this.fieldElements) {
			if (!element.canMove()) {
				// If the element cannot move, skip to the next one
				continue;
			}
			
			if (!(element instanceof AdventurerElement)) {
				// If the element is not an adventurer it is not handled yet
				System.out.println("Movable element not handled yet");
				continue;
			}
			needsNextRound = true;
			AdventurerElement adventurer = (AdventurerElement) element;
			APosition nextAdvPosition = adventurer.getPosition();
			
			// If the next position is out do not move
			if (!nextAdvPosition.isIncludedInField(this)) {
				adventurer.doNotMove();
				continue;
			}
			
			AFieldElement alreadyExistingElement = this.findElementAtPosition(nextAdvPosition);
			
			if (alreadyExistingElement instanceof MountainElement) {
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
