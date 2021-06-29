package nz.govt.mbie.driver.ui.calculator;

import org.concordion.cubano.driver.BrowserBasedTest;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import nz.govt.mbie.driver.ui.PageObject;
import nz.govt.mbie.driver.util.testdata.dataentry.HospitalMeasurementsData;

public class HospitalMeasurementPage extends PageObject<HospitalMeasurementPage>{
	
	@FindBy(xpath = "//iframe[@src='https://msg-tc-spa-as-prd.azurewebsites.net/']")
	private WebElement calculatoriframe;
	
	@FindBy(xpath = "//span[text()='Hospital']")
	private WebElement pageTitle;

	@FindBy(xpath = "//mat-label[text()='Enter applicable measurements to calculate occupant density:']")
	private WebElement intro;

	@FindBy(xpath = "//mat-card-title[contains(text(),'Calculate the number of toilet facilities here')]//ancestor::mat-card[1]")
	private WebElement calculator;

	@FindBy(xpath = "//mat-label[contains(text(),'Dining, beverage and cafeteria spaces')]//ancestor::div[1]//input")
	private WebElement diningAreaInputBox;

	@FindBy(xpath = "//mat-label[contains(text(),'Interview rooms')]//ancestor::div[1]//input")
	private WebElement interviewAreaInputBox;

	@FindBy(xpath = "//mat-label[contains(text(),'Kitchens')]//ancestor::div[1]//input")
	private WebElement kitchenAreaInputBox;

	@FindBy(xpath = "//mat-label[contains(text(),'Laundry and house keeping facilities')]//ancestor::div[1]//input")
	private WebElement houseKeepingAreaInputBox;

	@FindBy(xpath = "//mat-label[contains(text(),'Lobbies and foyers')]//ancestor::div[1]//input")
	private WebElement lobbieAreaInputBox;

	@FindBy(xpath = "//mat-label[contains(text(),'Offices and staffrooms')]//ancestor::div[1]//input")
	private WebElement officeAreaInputBox;

	@FindBy(xpath = "//mat-label[contains(text(),'Personal service facilities')]//ancestor::div[1]//input")
	private WebElement serviceFacilityAreaInputBox;

	@FindBy(xpath = "//mat-label[contains(text(),'Reception areas')]//ancestor::div[1]//input")
	private WebElement receptionAreaInputBox;

	@FindBy(xpath = "//mat-label[contains(text(),'Toilets and subordinate spaces (no occupants counted)')]//ancestor::div[1]//input")
	private WebElement subordinatespaceAreaInputBox;

	@FindBy(xpath = "//mat-label[contains(text(),'Beds')]//ancestor::div[1]//input")
	private WebElement numberOfBedsInputBox;

	@FindBy(css = "#submit")
	private WebElement submitButton;
	
	public HospitalMeasurementPage(BrowserBasedTest test) {
		super(test);
		switchtoFrame(test.getBrowser().getDriver(), calculatoriframe);
	}

	public ExpectedCondition<?> pageIsLoaded(Object... params) {
		return ExpectedConditions
				.visibilityOf(calculatoriframe);
	}

	
	/**
	 * Method to update and submit occupant density form.
	 * @param hospitalMeasurementsData is object of HospitalToiletFacilityResult data to be addded in the form.
	 * @return object HospitalToiletFacilityResult page.
	 */
	public HospitalToiletFacilityResultPage calculateOccupantDensity(BrowserBasedTest test, HospitalMeasurementsData hospitalMeasurementsData) {


		diningAreaInputBox.sendKeys(hospitalMeasurementsData.getDiningArea());
		interviewAreaInputBox.sendKeys(hospitalMeasurementsData.getInterviewArea());
		kitchenAreaInputBox.sendKeys(hospitalMeasurementsData.getKitchenArea());
		houseKeepingAreaInputBox.sendKeys(hospitalMeasurementsData.getHouseKeepingArea());
		lobbieAreaInputBox.sendKeys(hospitalMeasurementsData.getLobbieArea());
		officeAreaInputBox.sendKeys(hospitalMeasurementsData.getOfficeArea());
		serviceFacilityAreaInputBox.sendKeys(hospitalMeasurementsData.getServiceFacilityArea());
		receptionAreaInputBox.sendKeys(hospitalMeasurementsData.getReceptionArea());
		subordinatespaceAreaInputBox.sendKeys(hospitalMeasurementsData.getSubordinatespaceArea());
		numberOfBedsInputBox.sendKeys(hospitalMeasurementsData.getNumberOfBeds());

		capturePage(calculator);
		return capturePageAndClick(submitButton, HospitalToiletFacilityResultPage.class);

	}
	

	
}
