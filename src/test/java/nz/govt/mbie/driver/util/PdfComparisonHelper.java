package nz.govt.mbie.driver.util;

import java.io.IOException;

import de.redsix.pdfcompare.PdfComparator;
import de.redsix.pdfcompare.RenderingException;

public class PdfComparisonHelper {
	
	private static String EXPECTEDPDFPATH = System.getProperty("user.dir") 
			+ "\\src\\test\\java\\nz\\govt\\mbie\\driver\\util\\testdata\\pdfsource\\";
	
	private static String ACTUALPDFPATH = System.getProperty("user.dir")
			+ "\\src\\test\\java\\nz\\govt\\mbie\\driver\\util\\testdata\\pdfgenrated\\";
	
	private static String COMPAREPDFPATH = System.getProperty("user.dir")
			+ "\\src\\test\\java\\nz\\govt\\mbie\\driver\\util\\testdata\\pdfcomparison\\";

	private PdfComparisonHelper() {

	}
	

	/**
	 * Method to compare pdf file and store compariosn result.
	 * @param expected source file details.
	 * @param actual genrated file details.
	 * @param compared file comparison details.
	 * @return boolean respone of compariosn.
	 */
	@SuppressWarnings("rawtypes")
	public static boolean comparePdf(String expected, String actual, String compared)
			throws RenderingException, IOException {

		String expectedPdf = EXPECTEDPDFPATH + expected;

		String actualPdf = ACTUALPDFPATH+ actual;

		String comparisonPdf = COMPAREPDFPATH + compared;

		boolean compare = new PdfComparator(actualPdf, expectedPdf)
				.withIgnore("ignore.conf").compare()
				.writeTo(comparisonPdf);

		return compare;
	}

}
