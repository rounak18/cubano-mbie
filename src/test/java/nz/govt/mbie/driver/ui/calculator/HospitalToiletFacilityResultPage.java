package nz.govt.mbie.driver.ui.calculator;

import java.awt.AWTException;

import org.concordion.cubano.driver.BrowserBasedTest;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import nz.govt.mbie.driver.ui.PageObject;
import nz.govt.mbie.driver.util.RobotAutomationHelper;

public class HospitalToiletFacilityResultPage extends PageObject<HospitalToiletFacilityResultPage>{
	
	@FindBy(xpath = "//iframe[@src='https://msg-tc-spa-as-prd.azurewebsites.net/']")
	private WebElement calculatoriframe;
	
	@FindBy(xpath = "//div[text()='Calculated number of toilet facilities']")
	private WebElement pageTitle;

	@FindBy(xpath = "//div[text()='Calculated occupant densities (based on G1/AS1 Table 4) ']")
	private WebElement subheading;

	@FindBy(xpath = "//span[text()='Print']//ancestor::button[1]")
	private WebElement printButton;
	
	public HospitalToiletFacilityResultPage(BrowserBasedTest test) {
		super(test);
		switchtoFrame(test.getBrowser().getDriver(), calculatoriframe);
	}

	@Override
	public ExpectedCondition<?> pageIsLoaded(Object... params) {
		return ExpectedConditions
				.visibilityOf(calculatoriframe);
	}
	
	/**
	 * Method to select building Facility from Dropdown.
	 * @param buildingName name of building.
	 * @return page object for the building.
	 * Not fully implemented for other building type except Hospital and Museum.
	 */
	public String getResultHeading(BrowserBasedTest test) {

		scrollIntoView(test.getBrowser().getDriver(), printButton);
		capturePage(pageTitle);
		return pageTitle.getText();

	}

	/**
	 * Method to print and save pdf of hospital toilet facility result.
	 * @param fileName is location of the file to store result.
	 * @return object HospitalToiletFacilityResult page.
	 */
	public HospitalToiletFacilityResultPage printandSaveResultAsPdf(BrowserBasedTest test, String fileName) throws AWTException {

		capturePage(pageTitle);
		scrollIntoView(test.getBrowser().getDriver(), printButton);
		capturePageAndClick(printButton, HospitalToiletFacilityResultPage.class);

		RobotAutomationHelper
		.saveFileWindowPopupHandler(fileName);

		return this;

	}
}