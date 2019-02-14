package model.entity.position;

import java.util.HashMap;
import java.util.Map;

import exception.PositionException;
import helper.factory.PositionFactory;
import model.common.EAvailableActions;
import model.common.EAvailableDirection;
import model.entity.field.AField;
import model.entity.field.MadreDeDiosField;
import model.entity.order.AOrder;
import model.entity.order.MoveOrder;
import model.entity.order.RotateOrder;

/**
 * Sensor that indicates and computes the position according
 * to the field and the orders
 * 
 * Works with coordinate objects
 * 
 * @author aaudelin
 *
 */
public class CoordinatePosition extends APosition {

	public static final String INFO_DELIMITER = " ";

	EAvailableDirection direction = null;

	int xCoordinate = 0;

	int yCoordinate = 0;
	
	@Override
	public CoordinatePosition computePositionFromOrder(AOrder order) throws PositionException {
		if (order instanceof RotateOrder) {
			EAvailableDirection direction = this.rotateFromAction(order.getAction());
			return PositionFactory.createCoordinatePosition(this.xCoordinate, this.yCoordinate,
					direction.getDirection());
		}

		if (order instanceof MoveOrder) {
			int x = this.moveXFromAction();
			int y = this.moveYFromAction();
			return PositionFactory.createCoordinatePosition(x, y, this.getDirection().getDirection());
		}
		return null;
	}

	@Override
	public boolean isAtSamePosition(APosition pPosition) throws PositionException {
		if (!(pPosition instanceof CoordinatePosition)) {
			throw new PositionException(
					"Invalid field type not handled by the position : " + pPosition.getClass().getName());
		}
		CoordinatePosition pCooPosition = (CoordinatePosition) pPosition;
		return this.getXCoordinate() == pCooPosition.getXCoordinate()
				&& this.getYCoordinate() == pCooPosition.getYCoordinate();
	}
	
	@Override
	public boolean isIncludedInField(AField field) throws PositionException {
		if (!(field instanceof MadreDeDiosField)) {
			throw new PositionException(
					"Invalid field type not handled by the position : " + field.getClass().getName());
		}
		MadreDeDiosField cField = (MadreDeDiosField) field;
		return this.getXCoordinate() >= cField.getMinXCoordinate() 
				&& this.getXCoordinate() <= cField.getMaxXCoordinate()
				&& this.getYCoordinate() >= cField.getMinYCoordinate() 
				&& this.getYCoordinate() <= cField.getMaxYCoordinate();
	}
	

	/**
	 * Computes a movement action to the x coordinate
	 * 
	 * @return the next x position
	 */
	private int moveXFromAction() {
		EAvailableDirection direction = this.getDirection();
		if (EAvailableDirection.DIRECTION_WEST.equals(direction)) {
			return this.xCoordinate - 1;
		}
		if (EAvailableDirection.DIRECTION_EAST.equals(direction)) {
			return this.xCoordinate + 1;
		}
		return this.xCoordinate;

	}

	/**
	 * Computes a movement action to the y coordinate
	 * 
	 * @return the next y position
	 */
	private int moveYFromAction() {
		EAvailableDirection direction = this.getDirection();
		if (EAvailableDirection.DIRECTION_NORTH.equals(direction)) {
			return this.yCoordinate - 1;
		}
		if (EAvailableDirection.DIRECTION_SOUTH.equals(direction)) {
			return this.yCoordinate + 1;
		}
		return this.yCoordinate;

	}

	/**
	 * Computes a rotation
	 * 
	 * @param action the rotation type
	 * @return the next direction, focus on NORTH by default
	 */
	private EAvailableDirection rotateFromAction(EAvailableActions action) {
		Map<EAvailableDirection, EAvailableDirection> mapLeft = new HashMap<EAvailableDirection, EAvailableDirection>();
		mapLeft.put(EAvailableDirection.DIRECTION_NORTH, EAvailableDirection.DIRECTION_WEST);
		mapLeft.put(EAvailableDirection.DIRECTION_WEST, EAvailableDirection.DIRECTION_SOUTH);
		mapLeft.put(EAvailableDirection.DIRECTION_SOUTH, EAvailableDirection.DIRECTION_EAST);
		mapLeft.put(EAvailableDirection.DIRECTION_EAST, EAvailableDirection.DIRECTION_NORTH);

		Map<EAvailableDirection, EAvailableDirection> mapRight = new HashMap<EAvailableDirection, EAvailableDirection>();
		mapRight.put(EAvailableDirection.DIRECTION_NORTH, EAvailableDirection.DIRECTION_EAST);
		mapRight.put(EAvailableDirection.DIRECTION_EAST, EAvailableDirection.DIRECTION_SOUTH);
		mapRight.put(EAvailableDirection.DIRECTION_SOUTH, EAvailableDirection.DIRECTION_WEST);
		mapRight.put(EAvailableDirection.DIRECTION_WEST, EAvailableDirection.DIRECTION_NORTH);

		EAvailableDirection direction = EAvailableDirection.DIRECTION_NORTH;
		if (EAvailableActions.ROTATE_LEFT.equals(action)) {
			direction = mapLeft.get(this.getDirection());
		} else if (EAvailableActions.ROTATE_RIGHT.equals(action)) {
			direction = mapRight.get(this.getDirection());
		}
		return direction;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(this.xCoordinate);
		builder.append(INFO_DELIMITER);
		builder.append(this.yCoordinate);
		builder.append(INFO_DELIMITER);
		builder.append(this.direction);
		return builder.toString();
	}

	
	public EAvailableDirection getDirection() {
		return direction;
	}

	public void setDirection(EAvailableDirection direction) {
		this.direction = direction;
	}

	public int getXCoordinate() {
		return xCoordinate;
	}

	public void setXCoordinate(int xCoordinate) {
		this.xCoordinate = xCoordinate;
	}

	public int getYCoordinate() {
		return yCoordinate;
	}

	public void setYCoordinate(int yCoordinate) {
		this.yCoordinate = yCoordinate;
	}
}
