package helper.files;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import model.entity.element.TreasureElement;
import model.entity.field.AField;

/**
 * 
 * @author aaudelin
 *
 */
public class MadreDeDiosMapWriter {

	private static final MadreDeDiosMapWriter instance = new MadreDeDiosMapWriter();

	/**
	 * Returns the singleton instance
	 * 
	 * @return instance
	 */
	public static MadreDeDiosMapWriter getInstance() {
		return instance;
	}

	/**
	 * Write madre de dios map and its elements
	 * 
	 * @param field    the field to print
	 * @param filePath the output file
	 * @throws IOException
	 */
	public void writeMapToFile(AField field, String filePath) throws IOException {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
			System.out.println("---- RESULT : Output Madre de Dios final position ---");
			String fieldStr = field.toString();
			// Write to stdout
			System.out.println(fieldStr);
			try {
				// Write to file
				writer.write(fieldStr);
				writer.newLine();
			} catch (IOException exception) {
				System.out.println("Impossible to write Madre de Dios map : " + fieldStr);
				exception.printStackTrace();
			}

			field.getFieldElements().stream()
				.filter((element) -> {
					if (element instanceof TreasureElement) {
						TreasureElement elementT = (TreasureElement) element;
						return elementT.getQuantity() != 0;
					}
					return true;
				})
				.forEach((element) -> {
					
	
					String elementStr = element.toString();
	
					// Write to stdout
					System.out.println(elementStr);
					try {
						// Write to file
						writer.write(elementStr);
						writer.newLine();
					} catch (IOException exception) {
						System.out.println("Impossible to write element : " + elementStr);
						exception.printStackTrace();
					}
				});
		}
	}

}
