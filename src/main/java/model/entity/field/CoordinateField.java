package model.entity.field;


/**
 * 
 * @author aaudelin
 *
 */
public class CoordinateField extends AField {

	int minXCoordinate = 0;

	int minYCoordinate = 0;

	int maxXCoordinate = 0;

	int maxYCoordinate = 0;

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
