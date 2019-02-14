package model.common;

/**
 * Describes all the available types of entities
 * 
 * @author aaudelin
 *
 */
public enum EAvailableEntityTypes {

	TYPE_MAP('C'),
	
	TYPE_MOUNTAIN('M'),
	
	TYPE_TREASURE('T'),
	
	TYPE_ADVENTURER('A');
	
	private char type;
	
	EAvailableEntityTypes(char pType) {
		this.type = pType;
	}

	public char getType() {
		return type;
	}
	
	public static EAvailableEntityTypes getFromType(char type) {
		for (EAvailableEntityTypes validType : EAvailableEntityTypes.values()) {
			if (validType.getType() == type) {
				return validType;
			}
		}
		return null;
	}
	
	
}
