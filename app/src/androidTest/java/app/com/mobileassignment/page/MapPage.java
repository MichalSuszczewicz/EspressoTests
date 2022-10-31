package app.com.mobileassignment.page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import static app.com.mobileassignment.page.HomePage.clickCityByText;

import app.com.mobileassignment.R;

public class MapPage extends BasePage {

    public static int getPinPointId(){return R.id.insert_point;}

    /**
     * clicks on map PinPoint
     */
    public static void clickOnPinPoint(){
        onView(withId(R.id.insert_point)).perform(click());
    }

    /**
     * Goes from HomePag to MapPage for specific city X times
     * @param cityName city name
     * @param triesCount amount of tries
     */
    public static void selectCityAndGoBackAndForthXTimes(String cityName, int triesCount){
        for(int i = 0;  i<triesCount; i++) {
            clickCityByText(cityName);
            goBack();
            checkIfElementIsVisibleById(HomePage.getListOfCitiesContainer());
        }
    }

}
