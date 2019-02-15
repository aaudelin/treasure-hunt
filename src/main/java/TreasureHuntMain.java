import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import exception.EntityException;
import exception.FileReadException;
import exception.MissingArgumentException;
import helper.files.MadreDeDiosMapWriter;
import helper.files.TreasureHuntFileReadService;
import model.entity.element.AFieldElement;
import model.entity.field.AField;
import model.entity.field.MadreDeDiosField;

/**
 * 
 * @author aaudelin
 *
 */
public class TreasureHuntMain {

	/**
	 * Execute all the rounds of the game
	 * 
	 * @param args - first argument : input file name
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		if (args.length != 1) {
			throw new MissingArgumentException("Missing file path or too much arguments provided");
		}
		String filePath = args[0];

		// Read input file to load field
		AField field = null;
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			// Read field
			String strRead;
			while((strRead = br.readLine()) != null) {
				if (!(field instanceof MadreDeDiosField)) {
					System.out.println("Try to create field : " + strRead);
					field = TreasureHuntFileReadService.getInstance().createField(strRead);
				} else {
					System.out.println("Try to create field element : " + strRead);
					AFieldElement element = TreasureHuntFileReadService.getInstance().createFieldElement(strRead);
					field.addFieldElement(element);
				}
			}
		} catch (IOException exception) {
			System.out.println("Impossible to read file from path : " + filePath);
			throw exception;
		} catch (FileReadException exception) {
			System.out.println("Overall file format exception for file : " + filePath);
			throw exception;
		} catch (EntityException exception) {
			System.out.println("Impossible to create one of the object for file : " + filePath);
			throw exception;
		}

		// Begin processing the game
		boolean keepPlaying = true;
		while(keepPlaying) {
			keepPlaying = field.executeNextRound();
		}
		

		// Write in sysout and output file
		MadreDeDiosMapWriter.getInstance().writeMapToFile(field, filePath + "-output");

	}

}
