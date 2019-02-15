package model.entity.order;

import model.common.EAvailableActions;

/**
 * 
 * @author aaudelin
 *
 */
public abstract class AOrder {
	private EAvailableActions action;

	public AOrder(char pAction) {
		this.action = EAvailableActions.getFromAction(pAction);
	}

	public EAvailableActions getAction() {
		return this.action;
	}

	public void setAction(EAvailableActions action) {
		this.action = action;
	}
}
