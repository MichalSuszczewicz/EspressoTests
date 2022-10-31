package app.com.mobileassignment.tests;

import android.os.RemoteException;

import androidx.test.filters.LargeTest;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.UiSelector;

import org.junit.Test;
import org.junit.runner.RunWith;
import app.com.mobileassignment.utility.TestCaseInfo;
import app.com.mobileassignment.page.HomePage;
import app.com.mobileassignment.utility.TestData;


@LargeTest
@RunWith(AndroidJUnit4.class)
public class HomeTest extends BaseTest{

    @TestCaseInfo(author = "msuszczewicz", testCaseNumber = 1)
    @Test
    public void verifyHomeLayout(){
        HomePage.checkIfElementIsVisibleById(HomePage.getSearchInputId());
        HomePage.checkIfElementIsVisibleById(HomePage.getResultsContainerId());
        HomePage.checkIfElementIsVisibleById(HomePage.getListOfCitiesContainer());
    }

    @TestCaseInfo(author = "msuszczewicz", testCaseNumber = 2)
    @Test
    public void verifyPageTitle(){
        HomePage.checkIfTitleIsDisplayed("Mobile Assignment");
    }

    @TestCaseInfo(author = "msuszczewicz", testCaseNumber = 17)
    @Test
    public void verifyHomeViewAfterGoingBackFromBackground() throws RemoteException, UiObjectNotFoundException {
        HomePage.checkIfElementIsVisibleById(HomePage.getListOfCitiesContainer());
        device.pressHome();
        device.pressRecentApps();
        device.findObject(new UiSelector().descriptionContains("androidx.test.core.app")).click();
        HomePage.checkIfElementIsVisibleById(HomePage.getSearchInputId());
        HomePage.checkIfElementIsVisibleById(HomePage.getResultsContainerId());
        HomePage.checkIfElementIsVisibleById(HomePage.getListOfCitiesContainer());
    }

    /**
     * Precondition:
     * Disabled shortcut for opening camera by double click of power button.
     */
    @TestCaseInfo(author = "msuszczewicz", testCaseNumber = 18)
    @Test
    public void verifyHomeViewAfterWakeUp() throws RemoteException{
        HomePage.checkIfElementIsVisibleById(HomePage.getListOfCitiesContainer());
        device.sleep();
        device.wakeUp();
        HomePage.checkIfElementIsVisibleById(HomePage.getSearchInputId());
        HomePage.checkIfElementIsVisibleById(HomePage.getResultsContainerId());
        HomePage.checkIfElementIsVisibleById(HomePage.getListOfCitiesContainer());
    }

    @TestCaseInfo(author = "msuszczewicz", testCaseNumber = 3)
    @Test
    public void verifyCityIsSearchable(){
        HomePage.searchCity(TestData.searchCityName, false);
        HomePage.checkIfElementIsVisibleInSpecificPositionByText(TestData.searchCityName, HomePage.getCityNameId(),0, HomePage.TextMatches.CONTAINS);
 }

    @TestCaseInfo(author = "msuszczewicz", testCaseNumber = 4)
    @Test
    public void verifyPartiallySearch(){
        String partialCityName = "Sz";
        HomePage.searchCity(partialCityName, false);
        HomePage.checkIfElementIsVisibleInSpecificPositionByText(partialCityName, HomePage.getCityNameId(),0, HomePage.TextMatches.STARTS_WITH);
    }

    @TestCaseInfo(author = "msuszczewicz", testCaseNumber = 6)
    @Test
    public void verifyIfListIsScrollable(){
        HomePage.swipeListToPosition(HomePage.getListOfCitiesContainer(), 1000, true);
    }

    @TestCaseInfo(author = "msuszczewicz", testCaseNumber = 7)
    @Test
    public void verifySearchResultsAreDistinct() throws Exception {
        HomePage.searchCity(TestData.searchCityName, false);
        HomePage.checkResultsForAmbiguousViews(TestData.searchCityCountryName);
    }

    @TestCaseInfo(author = "msuszczewicz", testCaseNumber = 8)
    @Test
    public void verifyNavigationToTheMapView(){
        HomePage.clickCityByText(TestData.cityName);
        HomePage.verifyIfViewIsDisplayedByDescription("Google Map");
    }
}
