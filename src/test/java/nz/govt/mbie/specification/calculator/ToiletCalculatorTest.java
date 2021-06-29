package nz.govt.mbie.specification.calculator;

import nz.govt.mbie.driver.ui.calculator.HospitalMeasurementPage;
import nz.govt.mbie.driver.ui.calculator.HospitalToiletFacilityResultPage;
import nz.govt.mbie.driver.ui.calculator.ToiletCalculatorHomePage;
import nz.govt.mbie.driver.util.DeleteFileHelper;
import nz.govt.mbie.driver.util.PdfComparisonHelper;
import nz.govt.mbie.driver.util.testdata.dataentry.HospitalMeasurementsData;
import nz.govt.mbie.framework.CubanoMbieBrowserFixture;


public class ToiletCalculatorTest extends CubanoMbieBrowserFixture {

	private ToiletCalculatorHomePage toiletcalculatorhomepage;
	private HospitalMeasurementPage hospitalMeasurementPage;
	private HospitalToiletFacilityResultPage hospitalToiletFacilityResultPage;

    public  Object getHomePageHeading() {    	
		getStoryboard().addSectionContainer("MBIE Toilet Facility Calculator Home Page Heading Message");
		
    	return openHomePage()
				.getHomePageHeading();    	
    }
    
    public  Object getPageIntroMessage() {    	
		getStoryboard().addSectionContainer("MBIE Toilet Facility Calculator Intro Message");
		
		return openHomePage()
				.getHomePagIntro();
    }

    public  Object getToiletCalcualtorHeading() {    	
	    getStoryboard().addSectionContainer("MBIE Toilet Facility Calculator Tool Heading Message");
	    
		return openHomePage()
				.getToiletCalculatorHeading(this);    	
    }		
   
    private ToiletCalculatorHomePage openHomePage(){
    	
    	return toiletcalculatorhomepage = ToiletCalculatorHomePage
				.open(this);		
    }

    public ToiletCalculatorHomePage selectPeopleCount(String count){
    
	    getStoryboard().addSectionContainer("Select Count Of People for Facility");

	    if(count.equalsIgnoreCase("known")) {
    	return openHomePage()
    	.selectPeopleCount(this, true);
    	}
    	else if(count.equalsIgnoreCase("unknown")){
    	return openHomePage()
    	    	.selectPeopleCount(this, false);
    	}
		throw new RuntimeException("incorrect count type");
    }
    
    public  HospitalMeasurementPage  selectBuildingType(String buildingType){
    	
    	getStoryboard().addSectionContainer("Select building type as " +buildingType);

	    return hospitalMeasurementPage = toiletcalculatorhomepage
	    .selectBuildingUse(buildingType, this);
	    	
    }
    
    public HospitalToiletFacilityResultPage completeFormAndSubmit() {
    	
	    getStoryboard().addSectionContainer("Enter applicable measuerment and submit form");

    	return hospitalToiletFacilityResultPage = hospitalMeasurementPage
    			.calculateOccupantDensity(this, new HospitalMeasurementsData());
    	
    }
    
	public String getResultHeading() {
		
	    getStoryboard().addSectionContainer("Toilet Facility Calculator Result Genrated Message");

		return hospitalToiletFacilityResultPage
		.getResultHeading(this);
		
	}
	
	public boolean printSaveAndCompareResult() throws Exception {
		
		   getStoryboard().addSectionContainer("Print, Save and Compare Result with Source");
		   	
		   DeleteFileHelper
			.deletePdf("pdfgenrated\\hospital");
		   
		   hospitalToiletFacilityResultPage
			.printandSaveResultAsPdf(this, "hospital\\HospitalToiletCalculatorResult_Genrated");

		return PdfComparisonHelper.comparePdf("hospital\\HospitalToiletCalculatorResult_Source.pdf",
				"hospital\\HospitalToiletCalculatorResult_Genrated.pdf",
				"hospital\\HospitalToiletCalculatorResult_Comparison");
	}
}
