package nz.govt.mbie.driver.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class DeleteFileHelper {

	private static String BASEFOLDERPATH = System.getProperty("user.dir")
			+ "\\src\\test\\java\\nz\\govt\\mbie\\driver\\util\\testdata\\";

	
	private DeleteFileHelper() {

	}

	/**
	 * Method to delete pdf file from folder.
	 * @param folderPath path of folder to be deleted.
	 * @throws IOException
	 */
	public static void deletePdf(String folderPath) throws IOException {

		FileUtils
		.cleanDirectory(new File(BASEFOLDERPATH + folderPath));

	}
}
