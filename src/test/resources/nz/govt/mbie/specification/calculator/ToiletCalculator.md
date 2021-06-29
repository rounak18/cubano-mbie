# MBIE Toilet Facility Calculator 

This Test Verify In a Building/	Open Space with known/ unknown people count, Calculate How many toilet pans, basins and urinals to include in a Toilet Facility using MBIE Toilet Facility CalCulator

## Acceptance Criteria:
    # Generate Result for toilet facility with below option
      * Option 1 - Unisex toilet facilities
      * Option 2 - Single sex toilet facilities only
      * Option 3 - Single sex toilet facilities with urinals for males
      * Option 4 - Single sex toilet facilities only, plus accessible unisex
      * Option 5 - Single sex toilet facilities with urinals for males, plus accessible unisex
    # Print and Save Result as pdf for calculated toilet facility in building
    
## Limitations of the calculator:
     # The calculator, on its own, will not produce a Building Code compliant solution as it does not cover
       *  allocation and distribution of accessible toilet facilities
       *  definition of, or the distribution of unisex toilet facilities
       *  space requirements for toilets or accessible layouts
       *  the 'floor area' options are extensive and allow for a number of different building configurations
       *  Single sex toilet facilities with urinals for males, plus accessible unisex
       *  calculation of showers


## [MBIE Toilet Calculator Home Page Test](-)
Given Bob The Builder wants to explore Online MBIE Toilet Calculator Facility
When Bob navigate to  MBIE Toilet Calculator Page
Then Bob should be able to see Page loaded with Heading __[Toilet calculator](- "c:assertEquals=getHomePageHeading()")__
And Page Intro message __[Use the toilet calculator to help work out how many toilet pans, basins and urinals to include in buildings.](- "c:assertEquals=getPageIntroMessage()")__
And Toilet Facility Calculator tool with Heading __[Calculate the number of toilet facilities here](- "c:assertEquals=getToiletCalcualtorHeading()")__

## [Generate Toilet Facility Result for Hospital with people capacity count unknown using Toilet Facility Calculator Tool Test](-)
Given Bob The Builder wants to use MBIE Toilet Calculator tool to calculate Toilet Facility requirement
When Bob open Toilet Facility Calculator and select option people capacity count __[unknown](- "selectPeopleCount(#TEXT)")__
And Bob selects building type as __[Hospital](- "selectBuildingType(#TEXT)")__
Then Bob can see Enter applicable measurement form appear
When after updating all applicable measurements details __[Bob Submit the form](- "completeFormAndSubmit()")__
Then Bob should be able to see Toilet Facility Result generated with heading  __[Calculated number of toilet facilities](- "c:assertEquals=getResultHeading()")__
And Bob should be able __[print and save result as pdf for calculated toilet facility in building](- "c:assertTrue=printSaveAndCompareResult()")__

## [Generate Toilet Facility Result for Museum with people capacity count 100 using Toilet Facility Calculator Tool Test](-)
Given Jim The Builder wants to use MBIE Toilet Calculator tool to calculate Toilet Facility requirement
When Jim open Toilet Facility Calculator and select option people capacity count **known**
And Jim selects building type as **Museum** with Occupant Count as **100** 
And Jim Submit the form
Then Jim should be able to see Toilet Facility Result generated with heading  **Calculated number of toilet facilities**
And Jim should be able [print and save result as pdf for calculated toilet facility in building

