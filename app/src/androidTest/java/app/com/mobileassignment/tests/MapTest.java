package app.com.mobileassignment.tests;

import android.os.RemoteException;

import androidx.test.filters.LargeTest;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.UiSelector;

import org.junit.Test;
import org.junit.runner.RunWith;

import app.com.mobileassignment.page.MapPage;
import app.com.mobileassignment.utility.TestCaseInfo;
import app.com.mobileassignment.page.HomePage;
import app.com.mobileassignment.utility.TestData;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MapTest extends BaseTest{

    @TestCaseInfo(author = "msuszczewicz", testCaseNumber = 9)
    @Test
    public void checkIfPinPointIsDisplayed(){
        HomePage.clickCityByText(TestData.cityName);
        MapPage.checkIfElementIsDisplayedById(MapPage.getPinPointId());
    }

    @TestCaseInfo(author = "msuszczewicz", testCaseNumber = 11)
    @Test
    public void verifyUserIsAbleToNavigateBackAndForthFromTheMap(){
        MapPage.selectCityAndGoBackAndForthXTimes(TestData.cityName, 2);
    }

    @TestCaseInfo(author = "msuszczewicz", testCaseNumber = 10)
    @Test
    public void verifyIfGoogleMapsImagesAreDisplayedAndClickable(){
        String getDirectionsView = "Get directions";
        String openInGoogleMapsView = "Open in Google Maps";

        HomePage.clickCityByText(TestData.cityName);
        MapPage.clickOnPinPoint();
        MapPage.verifyIfViewIsDisplayedByDescription(getDirectionsView);
        MapPage.verifyIfViewIsDisplayedByDescription(openInGoogleMapsView);
        MapPage.verifyIfViewIsClickableByDescription(getDirectionsView);
        MapPage.verifyIfViewIsClickableByDescription(openInGoogleMapsView);
    }
    @TestCaseInfo(author = "msuszczewicz", testCaseNumber = 19)
    @Test
    public void verifyMapViewAfterGoingBackFromBackground() throws RemoteException, UiObjectNotFoundException {
        HomePage.clickCityByText(TestData.cityName);
        MapPage.checkIfElementIsDisplayedById(MapPage.getPinPointId());
        device.pressHome();
        device.pressRecentApps();
        device.findObject(new UiSelector().descriptionContains("androidx.test.core.app")).click();
        MapPage.checkIfElementIsDisplayedById(MapPage.getPinPointId());
    }
}
