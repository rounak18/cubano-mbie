package nz.govt.mbie.driver.ui.calculator;

import org.concordion.cubano.driver.BrowserBasedTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import nz.govt.mbie.driver.ui.PageObject;

public class MuseumMeasurementPage extends PageObject<MuseumMeasurementPage>{
	
	@FindBy(xpath = "//iframe[@src='https://msg-tc-spa-as-prd.azurewebsites.net/']")
	private WebElement calculatoriframe;
	
	@FindBy(xpath = "//span[text()='Hospital']")
	private WebElement pageTitle;

	public MuseumMeasurementPage(BrowserBasedTest test) {
		super(test);
		switchtoFrame(test.getBrowser().getDriver(), calculatoriframe);
	}

	@Override
	public ExpectedCondition<?> pageIsLoaded(Object... params) {
		return ExpectedConditions
				.visibilityOf(calculatoriframe);
	}

}
