package model.entity.field;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import exception.FieldException;
import helper.factory.FieldElementFactory;
import helper.factory.FieldFactory;
import helper.factory.PositionFactory;
import model.common.EAvailableDirection;
import model.entity.element.AdventurerElement;
import model.entity.element.MountainElement;
import model.entity.element.TreasureElement;
import model.entity.position.CoordinatePosition;

public class MadreDeDiosFieldTest {
	
	@Test
	public void testAddElement() throws Exception {
		MadreDeDiosField field = FieldFactory.createMadreDeDiosField(5, 5);
		MountainElement mountain = FieldElementFactory.createMountain(1, 1);
		field.addFieldElement(mountain);
	}
	
	@Test(expected = FieldException.class)
	public void testAddElementSamePosition() throws Exception {
		MadreDeDiosField field = FieldFactory.createMadreDeDiosField(5, 5);
		MountainElement mountain = FieldElementFactory.createMountain(1, 1);
		MountainElement mountain2 = FieldElementFactory.createMountain(1, 1);
		field.addFieldElement(mountain);
		field.addFieldElement(mountain2);
	}
	
	@Test
	public void testNextRounds() throws Exception {
		MadreDeDiosField field = FieldFactory.createMadreDeDiosField(5, 5);
		AdventurerElement adventurer = FieldElementFactory.createAdventurer("Test", 0, 1, 'E', "A");
		AdventurerElement adventurer2 = FieldElementFactory.createAdventurer("Test", 2, 1, 'E', "AA");
		field.addFieldElement(adventurer);
		field.addFieldElement(adventurer2);
		
		
		boolean result1 = field.executeNextRound();
		boolean result2 = field.executeNextRound();
		boolean result3 = field.executeNextRound();
		
		assertTrue(result1);
		assertTrue(result2);
		assertFalse(result3);
	}

	@Test
	public void testAdventurerToMountain() throws Exception {
		MadreDeDiosField field = FieldFactory.createMadreDeDiosField(5, 5);
		MountainElement mountain = FieldElementFactory.createMountain(1, 1);
		AdventurerElement adventurer = FieldElementFactory.createAdventurer("Test", 0, 1, 'E', "A");
		field.addFieldElement(mountain);
		field.addFieldElement(adventurer);
		
		
		CoordinatePosition position = new CoordinatePosition();
		position.setXCoordinate(0);
		position.setYCoordinate(1);
		
		boolean result = field.executeNextRound();
		
		assertTrue(result);
		assertTrue(adventurer.getPosition().isAtSamePosition(position));
		assertFalse(adventurer.getPosition().isAtSamePosition(mountain.getPosition()));
	}
	
	@Test
	public void testAdventurerToAdventurer() throws Exception {
		MadreDeDiosField field = FieldFactory.createMadreDeDiosField(5, 5);
		AdventurerElement adventurer = FieldElementFactory.createAdventurer("Test", 0, 1, 'E', "A");
		AdventurerElement adventurer2 = FieldElementFactory.createAdventurer("Test", 1, 1, 'E', "A");
		field.addFieldElement(adventurer);
		field.addFieldElement(adventurer2);
		
		
		CoordinatePosition position1 = new CoordinatePosition();
		position1.setXCoordinate(0);
		position1.setYCoordinate(1);
		
		CoordinatePosition position2 = new CoordinatePosition();
		position2.setXCoordinate(2);
		position2.setYCoordinate(1);
		
		boolean result = field.executeNextRound();
		
		assertTrue(result);
		assertTrue(adventurer.getPosition().isAtSamePosition(position1));
		assertTrue(adventurer2.getPosition().isAtSamePosition(position2));
	}
	
	@Test
	public void testAdventurerToAdventurerConflict() throws Exception {
		MadreDeDiosField field = FieldFactory.createMadreDeDiosField(5, 5);
		AdventurerElement adventurer = FieldElementFactory.createAdventurer("Test", 0, 1, 'E', "A");
		AdventurerElement adventurer2 = FieldElementFactory.createAdventurer("Test", 1, 1, 'W', "A");
		field.addFieldElement(adventurer);
		field.addFieldElement(adventurer2);
		
		
		CoordinatePosition position1 = new CoordinatePosition();
		position1.setXCoordinate(0);
		position1.setYCoordinate(1);
		
		CoordinatePosition position2 = new CoordinatePosition();
		position2.setXCoordinate(1);
		position2.setYCoordinate(1);
		
		boolean result = field.executeNextRound();
		
		assertFalse(result);
		assertTrue(adventurer.getPosition().isAtSamePosition(position2));
		assertTrue(adventurer2.getPosition().isAtSamePosition(position1));
	}
	
	@Test
	public void testAdventurerToTreasure() throws Exception {
		MadreDeDiosField field = FieldFactory.createMadreDeDiosField(5, 5);
		TreasureElement treasure = FieldElementFactory.createTreasure(1, 1, 2);
		AdventurerElement adventurer = FieldElementFactory.createAdventurer("Test", 0, 1, 'E', "A");
		field.addFieldElement(treasure);
		field.addFieldElement(adventurer);
		
		
		boolean result = field.executeNextRound();
		
		assertTrue(result);
		assertTrue(adventurer.getPosition().isAtSamePosition(treasure.getPosition()));
		assertEquals(1, adventurer.getTreasureCount());
		assertEquals(1, treasure.getQuantity());
	}
	
	@Test
	public void testAdventurerToTreasurePickOnlyOne() throws Exception {
		MadreDeDiosField field = FieldFactory.createMadreDeDiosField(5, 5);
		TreasureElement treasure = FieldElementFactory.createTreasure(1, 1, 2);
		AdventurerElement adventurer = FieldElementFactory.createAdventurer("Test", 0, 1, 'E', "AG");
		field.addFieldElement(treasure);
		field.addFieldElement(adventurer);
		
		
		boolean result = field.executeNextRound();
		boolean result2 = field.executeNextRound();
		
		assertTrue(result);
		assertTrue(result2);
		assertTrue(adventurer.getPosition().isAtSamePosition(treasure.getPosition()));
		assertEquals(1, adventurer.getTreasureCount());
		assertEquals(1, treasure.getQuantity());
	}
	
	@Test
	public void testAdventurerIsOut() throws Exception {
		MadreDeDiosField field = FieldFactory.createMadreDeDiosField(5, 5);
		AdventurerElement adventurer = FieldElementFactory.createAdventurer("Test", 5, 5, 'E', "AG");
		CoordinatePosition position = PositionFactory.createCoordinatePosition(5, 5);
		field.addFieldElement(adventurer);
		
		
		boolean result = field.executeNextRound();
		boolean result2 = field.executeNextRound();
		CoordinatePosition advPosition = (CoordinatePosition) adventurer.getPosition();
		
		assertTrue(result);
		assertTrue(result2);
		assertTrue(adventurer.getPosition().isAtSamePosition(position));
		assertEquals(EAvailableDirection.DIRECTION_NORTH, advPosition.getDirection());
	}

}
