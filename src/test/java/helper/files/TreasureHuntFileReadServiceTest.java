package helper.files;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import exception.FileReadException;
import model.common.EAvailableActions;
import model.common.EAvailableDirection;
import model.entity.element.AFieldElement;
import model.entity.element.AdventurerElement;
import model.entity.element.MountainElement;
import model.entity.element.TreasureElement;
import model.entity.field.AField;
import model.entity.field.MadreDeDiosField;
import model.entity.order.MoveOrder;
import model.entity.order.RotateOrder;
import model.entity.position.CoordinatePosition;

public class TreasureHuntFileReadServiceTest {

	@Test
	public void testEmptyLineForField() throws Exception {
		
		TreasureHuntFileReadService instance = TreasureHuntFileReadService.getInstance();
		AField field = instance.createField("");
		
		assertNull(field);
		
	}
	
	@Test
	public void testSpaceLineForField() throws Exception {
		
		TreasureHuntFileReadService instance = TreasureHuntFileReadService.getInstance();
		AField field = instance.createField(" ");
		
		assertNull(field);
		
	}
	
	@Test
	public void testNotHandledLineTypeForField() throws Exception {
		
		TreasureHuntFileReadService instance = TreasureHuntFileReadService.getInstance();
		AField field = instance.createField("G");
		
		assertNull(field);
		
	}
	
	@Test(expected = FileReadException.class)
	public void testInvalidFormatForField() throws Exception {
		TreasureHuntFileReadService instance = TreasureHuntFileReadService.getInstance();
		AField field = instance.createField("C - 15");
		
	}
	
	@Test(expected = FileReadException.class)
	public void testInvalidFormatIntegerForField() throws Exception {
		TreasureHuntFileReadService instance = TreasureHuntFileReadService.getInstance();
		AField field = instance.createField("C - 15 - G");
		
	}
	
	@Test
	public void testValidFormatForField() throws Exception {
		TreasureHuntFileReadService instance = TreasureHuntFileReadService.getInstance();
		MadreDeDiosField field = (MadreDeDiosField) instance.createField("C - 15 - 18");
		
		assertEquals(field.getMaxXCoordinate(), 15);
		assertEquals(field.getMaxYCoordinate(), 18);
		assertEquals(field.getMinXCoordinate(), 0);
		assertEquals(field.getMinYCoordinate(), 0);
		
	}
	
	@Test
	public void testValidFormatForFieldIgnoreWhitespaces() throws Exception {
		TreasureHuntFileReadService instance = TreasureHuntFileReadService.getInstance();
		MadreDeDiosField field = (MadreDeDiosField) instance.createField(" C -15- 18");
		
		assertEquals(field.getMaxXCoordinate(), 15);
		assertEquals(field.getMaxYCoordinate(), 18);
		assertEquals(field.getMinXCoordinate(), 0);
		assertEquals(field.getMinYCoordinate(), 0);
		
	}
	
	@Test
	public void testEmptyLineForFieldElement() throws Exception {
		
		TreasureHuntFileReadService instance = TreasureHuntFileReadService.getInstance();
		AFieldElement element = instance.createFieldElement("");
		
		assertNull(element);
		
	}
	
	@Test
	public void testSpaceLineForFieldElement() throws Exception {
		
		TreasureHuntFileReadService instance = TreasureHuntFileReadService.getInstance();
		AFieldElement element = instance.createFieldElement(" ");
		
		assertNull(element);
		
	}
	
	@Test
	public void testNotHandledLineTypeForFieldElement() throws Exception {
		
		TreasureHuntFileReadService instance = TreasureHuntFileReadService.getInstance();
		AFieldElement element = instance.createFieldElement("G");
		
		assertNull(element);
		
	}
	
	@Test(expected = FileReadException.class)
	public void testInvalidFormatForFieldElement() throws Exception {
		TreasureHuntFileReadService instance = TreasureHuntFileReadService.getInstance();
		AFieldElement element = instance.createFieldElement("C - 15");
		
	}
	
	@Test(expected = FileReadException.class)
	public void testInvalidAdventurer() throws Exception {
		TreasureHuntFileReadService instance = TreasureHuntFileReadService.getInstance();
		instance.createFieldElement("A -  1 - 1 - S - AADADAGGA");
	}
	
	@Test(expected = FileReadException.class)
	public void testInvalidTreasure() throws Exception {
		TreasureHuntFileReadService instance = TreasureHuntFileReadService.getInstance();
		instance.createFieldElement("T -  1 - 1 - S");
	}
	
	@Test(expected = FileReadException.class)
	public void testInvalidMountain() throws Exception {
		TreasureHuntFileReadService instance = TreasureHuntFileReadService.getInstance();
		instance.createFieldElement("M -  1 ");
	}
	
	@Test
	public void testValidFormatForAdventurer() throws Exception {
		TreasureHuntFileReadService instance = TreasureHuntFileReadService.getInstance();
		AdventurerElement adv = (AdventurerElement) instance.createFieldElement("A - Lara - 1 - 1 - S - AADADAGGA");
		
		assertEquals("Lara", adv.getName());
		assertTrue(adv.getPosition() instanceof CoordinatePosition);
		CoordinatePosition position = (CoordinatePosition) adv.getPosition();
		assertEquals(position.getXCoordinate(), 1);
		assertEquals(position.getYCoordinate(), 1);
		assertEquals(position.getDirection(), EAvailableDirection.DIRECTION_SOUTH);
		assertEquals(adv.getOrders().size(), 9);
		assertEquals(0, adv.getTreasureCount());
		
		// Check some orders
		assertTrue(adv.getOrders().get(0) instanceof MoveOrder);
		assertEquals(EAvailableActions.MOVE_FORWARD, adv.getOrders().get(0).getAction());
		assertTrue(adv.getOrders().get(2) instanceof RotateOrder);
		assertEquals(EAvailableActions.ROTATE_RIGHT, adv.getOrders().get(2).getAction());
		assertTrue(adv.getOrders().get(7) instanceof RotateOrder);
		assertEquals(EAvailableActions.ROTATE_LEFT, adv.getOrders().get(7).getAction());
	}
	
	@Test
	public void testValidFormatForTreasure() throws Exception {
		TreasureHuntFileReadService instance = TreasureHuntFileReadService.getInstance();
		TreasureElement element = (TreasureElement) instance.createFieldElement("T - 0 - 3 - 2 ");
		
		assertTrue(element.getPosition() instanceof CoordinatePosition);
		CoordinatePosition position = (CoordinatePosition) element.getPosition();
		assertEquals(position.getXCoordinate(), 0);
		assertEquals(position.getYCoordinate(), 3);
		assertNull(position.getDirection());
		assertEquals(2, element.getQuantity());
	}
	
	@Test
	public void testValidFormatForMountain() throws Exception {
		TreasureHuntFileReadService instance = TreasureHuntFileReadService.getInstance();
		MountainElement element = (MountainElement) instance.createFieldElement("M - 0 - 3 ");
		
		assertTrue(element.getPosition() instanceof CoordinatePosition);
		CoordinatePosition position = (CoordinatePosition) element.getPosition();
		assertEquals(position.getXCoordinate(), 0);
		assertEquals(position.getYCoordinate(), 3);
		assertNull(position.getDirection());
	}
		

}
