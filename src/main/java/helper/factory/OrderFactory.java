package helper.factory;

import java.util.ArrayList;
import java.util.List;

import exception.OrderException;
import model.common.EAvailableActions;
import model.entity.order.AOrder;
import model.entity.order.MoveOrder;
import model.entity.order.RotateOrder;

/**
 * 
 * @author aaudelin
 *
 */
public class OrderFactory {

	/**
	 * Creates the list of orders according to list of characters (G, A, D) Each
	 * type of order is implied by the character value
	 * 
	 * @param order the list of character orders
	 * @return a list of orders
	 * @throws OrderException
	 */
	public static List<AOrder> createOrderList(String order) throws OrderException {
		List<AOrder> orders = new ArrayList<AOrder>();
		for (char orderChar : order.toCharArray()) {
			if (EAvailableActions.isMovement(orderChar)) {
				orders.add(new MoveOrder(orderChar));
			} else if (EAvailableActions.isRotation(orderChar)) {
				orders.add(new RotateOrder(orderChar));
			} else {
				throw new OrderException("Impossible to save all orders : action invalid " + orderChar);
			}
		}
		return orders;
	}

}
