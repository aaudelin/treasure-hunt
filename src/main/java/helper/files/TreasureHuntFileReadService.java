package helper.files;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import exception.EntityException;
import exception.FileReadException;
import helper.factory.FieldElementFactory;
import helper.factory.FieldFactory;
import model.common.EAvailableEntityTypes;
import model.entity.element.AFieldElement;
import model.entity.field.AField;

/**
 * Reads a coordinate file type to create all coordinate types objects
 * 
 * @author aaudelin
 *
 */
public class TreasureHuntFileReadService {

	/** Delimiter to read file infos */
	public static final String FILE_FIELD_DELIMITER = "-";

	private static TreasureHuntFileReadService instance = new TreasureHuntFileReadService();

	/**
	 * Returns singleton
	 * 
	 * @return
	 */
	public static TreasureHuntFileReadService getInstance() {
		return instance;
	}

	/**
	 * Create an according field according a string line
	 * 
	 * @param fieldLine the field line
	 * @return a new coordinate field
	 * @throws FileReadException
	 */
	public AField createField(String fieldLine) throws FileReadException {
		// Skip empty line
		if (fieldLine.isBlank()) {
			return null;
		}
		
		List<String> fieldInfos = this.parseLine(fieldLine);
		
		// If the line is not a valid type skip to the next line
		if (fieldInfos.size() > 0 && EAvailableEntityTypes.TYPE_MAP.getType() != fieldInfos.get(0).charAt(0)) {
			return null;
		}

		if (fieldInfos.size() != 3) {
			throw new FileReadException("Impossible to read field from file, illegal file format "+fieldLine);
		}
		
		int xCoordinate;		
		int yCoordinate;		
		try {
			xCoordinate = Integer.parseInt(fieldInfos.get(1));
			yCoordinate = Integer.parseInt(fieldInfos.get(2));
		} catch (NumberFormatException exception) {
			throw new FileReadException("Impossible to read field coordinates from file : illegal file format "+fieldLine);
		}

		return FieldFactory.createCoordinateField(xCoordinate, yCoordinate);
	}

	/**
	 * Dispatch to the correct function to create the object matching the input file line
	 * 
	 * @param lineInfos
	 * @return an element to add to the field
	 * @throws FileReadException
	 * @throws EntityException
	 */
	public AFieldElement createFieldElement(String lineInfos) throws FileReadException, EntityException {
		// Skip empty line
		if (lineInfos.isBlank()) {
			return null;
		}
		
		List<String> elementInfos = this.parseLine(lineInfos);
		
		if(elementInfos.isEmpty()) {
			// Empty line, skip to the next
			return null;
		}
		
		EAvailableEntityTypes type = EAvailableEntityTypes.getFromType(elementInfos.get(0).charAt(0));
		if(null == type) {
			// No handled type, skip to the next
			return null;
		}
		
		if (EAvailableEntityTypes.TYPE_MAP.equals(type)) {
			throw new FileReadException("Impossible to define two different fields, invalid file format");
		}
		
		// DIspatch according to the type
		if (EAvailableEntityTypes.TYPE_ADVENTURER.equals(type)) {
			return this.createAdventurer(elementInfos);
		}
		
		if (EAvailableEntityTypes.TYPE_MOUNTAIN.equals(type)) {
			return this.createMountain(elementInfos);
		}
		
		if (EAvailableEntityTypes.TYPE_TREASURE.equals(type)) {
			return this.createTreasure(elementInfos);
		}
		return null;		
	}

	/**
	 * Creates a treasure from an input line
	 * @param elementInfos the line infos
	 * @return a treasure
	 * @throws FileReadException
	 */
	private AFieldElement createTreasure(List<String> elementInfos) throws FileReadException {
		
		if (elementInfos.size() != 4) {
			throw new FileReadException("Impossible to read treasure from file, illegal file format");
		}
		
		int xCoordinate;		
		int yCoordinate;
		int quantity;
		try {
			xCoordinate = Integer.parseInt(elementInfos.get(1));
			yCoordinate = Integer.parseInt(elementInfos.get(2));
			quantity = Integer.parseInt(elementInfos.get(3));
		} catch (NumberFormatException exception) {
			throw new FileReadException("Impossible to read treasure coordinates and quantity from file : illegal file format");
		}
		return FieldElementFactory.createTreasure(xCoordinate, yCoordinate, quantity);
	}

	/**
	 * Creates a mountain from an input line
	 * @param elementInfos the line infos
	 * @return a mountain
	 * @throws FileReadException
	 */
	private AFieldElement createMountain(List<String> elementInfos) throws FileReadException {
		if (elementInfos.size() != 3) {
			throw new FileReadException("Impossible to read mountain from file, illegal file format");
		}
		
		int xCoordinate;		
		int yCoordinate;
		try {
			xCoordinate = Integer.parseInt(elementInfos.get(1));
			yCoordinate = Integer.parseInt(elementInfos.get(2));
		} catch (NumberFormatException exception) {
			throw new FileReadException("Impossible to read mountain coordinates from file : illegal file format");
		}
		return FieldElementFactory.createMountain(xCoordinate, yCoordinate);
	}

	/**
	 * Creates an adventurer from an input line
	 * @param elementInfos the line infos
	 * @return an adventurer
	 * @throws FileReadException
	 * @throws EntityException 
	 */
	private AFieldElement createAdventurer(List<String> elementInfos) throws FileReadException, EntityException {
		if (elementInfos.size() != 6) {
			throw new FileReadException("Impossible to read adventurer from file, illegal file format");
		}
		
		String name = elementInfos.get(1);
		int xCoordinate;		
		int yCoordinate;
		char direction = elementInfos.get(4).charAt(0);
		String orders = elementInfos.get(5);
		try {
			xCoordinate = Integer.parseInt(elementInfos.get(2));
			yCoordinate = Integer.parseInt(elementInfos.get(3));
		} catch (NumberFormatException exception) {
			throw new FileReadException("Impossible to read mountain coordinates from file : illegal file format");
		}
		return FieldElementFactory.createAdventurer(name, xCoordinate, yCoordinate, direction, orders);
	}
	
	/**
	 * Parse a file line and split it
	 * @param line the line to read
	 * @return a list of element
	 */
	private List<String> parseLine(String line) {
		return Arrays.stream(line.split(TreasureHuntFileReadService.FILE_FIELD_DELIMITER))
				.map((info) -> info.trim())
				.collect(Collectors.toList());
	}

}
