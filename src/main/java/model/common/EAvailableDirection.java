package model.common;

/**
 * Describes all the coordinate directions available
 * 
 * @author aaudelin
 *
 */
public enum EAvailableDirection {

	DIRECTION_NORTH('N'),

	DIRECTION_SOUTH('S'),

	DIRECTION_EAST('E'),

	DIRECTION_WEST('W');

	private char direction;

	EAvailableDirection(char pDirection) {
		this.direction = pDirection;
	}

	public String toString() {
		return String.valueOf(this.direction);
	}

	public char getDirection() {
		return direction;
	}

	/**
	 * Checks if a char direction is valid
	 * 
	 * @param direction the direction to check
	 * @return true if the direction is valid, false otherwise
	 */
	public static boolean isValidDirection(char direction) {
		for (EAvailableDirection validDirection : EAvailableDirection.values()) {
			if (validDirection.getDirection() == direction) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Get an EAvailableDirection according to the char direction
	 * 
	 * @param direction the direction
	 * @return a EAvailableDirection constant
	 */
	public static EAvailableDirection getFromDirection(char direction) {
		for (EAvailableDirection validDirection : EAvailableDirection.values()) {
			if (validDirection.getDirection() == direction) {
				return validDirection;
			}
		}
		return EAvailableDirection.DIRECTION_NORTH;
	}

}
