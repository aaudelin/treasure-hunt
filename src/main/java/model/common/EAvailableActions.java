package model.common;

/**
 * Describes all the available actions to give as an order
 * 
 * @author aaudelin
 *
 */
public enum EAvailableActions {

	ROTATE_RIGHT('D'),

	ROTATE_LEFT('G'),

	MOVE_FORWARD('A');

	private char action;

	EAvailableActions(char pAction) {
		this.action = pAction;
	}

	/**
	 * Checks if a char action is valid
	 * 
	 * @param action the action to validate
	 * @return true if the action is valid, false otherwise
	 */
	public static boolean isValidAction(char action) {
		for (EAvailableActions validAction : EAvailableActions.values()) {
			if (validAction.getAction() == action) {
				return true;
			}
		}
		return false;

	}

	/**
	 * Checks if an action is a rotation
	 * 
	 * @param action the input action
	 * @return true if the action is a rotation, false otherwise
	 */
	public static boolean isRotation(char action) {
		return ROTATE_LEFT.getAction() == action || ROTATE_RIGHT.getAction() == action;
	}

	/**
	 * Checks if an action is a moving action
	 * 
	 * @param action the input action
	 * @return true if the action is a moving action, false otherwise
	 */
	public static boolean isMovement(char action) {
		return MOVE_FORWARD.getAction() == action;
	}

	@Override
	public String toString() {
		return String.valueOf(this.action);
	}

	public char getAction() {
		return action;
	}

}
