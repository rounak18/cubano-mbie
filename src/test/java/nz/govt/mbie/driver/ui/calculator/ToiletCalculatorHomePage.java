package nz.govt.mbie.driver.ui.calculator;

import java.util.List;

import org.concordion.cubano.driver.BrowserBasedTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import nz.govt.mbie.AppConfig;
import nz.govt.mbie.driver.ui.PageObject;

public class ToiletCalculatorHomePage extends PageObject<ToiletCalculatorHomePage> {
		
	@FindBy(xpath = "//h1[text()='Toilet calculator']")
	private WebElement pageTitle;

	@FindBy(css = ".intro-text")
	private WebElement introduction;

	@FindBy(xpath = "//iframe[@src='https://msg-tc-spa-as-prd.azurewebsites.net/']")
	private WebElement calculatoriframe;
	
	@FindBy(xpath = "//mat-card-title[text()='Calculate the number of toilet facilities here']")
	private WebElement calculatorHeading;

	@FindBy(xpath = "//label[@for='countKnownNo-input']")
	private WebElement countKnownNo;

	@FindBy(css = "#countKnownYes")
	private WebElement countKnownYes;

	@FindBy(css = "#buildingUse")
	private WebElement selectDropDownButton;

	@FindBy(css = "#occupantCount")
	private WebElement numberOfPeopleInputBox;

	@FindBy(xpath = "//mat-option[contains(@id,'mat-option')]")
	private List<WebElement> listOfBuildingType;
	

	public ToiletCalculatorHomePage(BrowserBasedTest test) {
        super(test);
        switchtoFrame(test.getBrowser().getDriver(), calculatoriframe);
    }

	@Override
	public ExpectedCondition<?> pageIsLoaded(Object... params) {
		return ExpectedConditions
				.visibilityOf(pageTitle);
	}

	public static ToiletCalculatorHomePage open(BrowserBasedTest test) {
		test.getBrowser().getDriver().navigate().to(AppConfig.getInstance().getBaseUrl());
		return new ToiletCalculatorHomePage(test);
	}
	
	public String getHomePageHeading() {
		
		switchToMainDocument();
		capturePage(pageTitle);
		return pageTitle.getText();
	}
	
	public String getHomePagIntro() {
		
		switchToMainDocument();
		capturePage(introduction);
		return introduction.getText();
	}

	public String getToiletCalculatorHeading(BrowserBasedTest test) {
		
		navigateToToiletCalculator(test);
		return calculatorHeading.getText();
		
	}
	
	private ToiletCalculatorHomePage navigateToToiletCalculator(BrowserBasedTest test) {
		
		scrollIntoView(test.getBrowser().getDriver(), calculatorHeading);
		capturePage(calculatorHeading);
	
		return this;
	}
	
	
	public ToiletCalculatorHomePage selectPeopleCount(BrowserBasedTest test, boolean countKnown) {
		navigateToToiletCalculator(test);
		if(!countKnown) {
		return capturePageAndClick(countKnownNo, ToiletCalculatorHomePage.class);
		}
		return capturePageAndClick(countKnownYes, ToiletCalculatorHomePage.class);
		
	}
	
	
	/**
	 * Method to select building Facility from Dropdown.
	 * @param buildingName name of building.
	 * @return page object for the building.
	 * Not fully implemented for other building type except Hospital and Museum.
	 */
	@SuppressWarnings("unchecked")
	public <T> T selectBuildingUse(String buildingName, BrowserBasedTest test) {

		capturePageAndClick(selectDropDownButton, ToiletCalculatorHomePage.class);
		capturePageAndClick(getElementFromListWithLable(buildingName, listOfBuildingType),ToiletCalculatorHomePage.class);
		capturePage(selectDropDownButton);

		if (buildingName.equalsIgnoreCase("Hospital")) {
			return (T) new HospitalMeasurementPage(test);
			} else if (buildingName.equalsIgnoreCase("Museum")) {
				return (T) new MuseumMeasurementPage(test);
			}
			throw new RuntimeException("Could not find page object class for " + buildingName);
	}

}
