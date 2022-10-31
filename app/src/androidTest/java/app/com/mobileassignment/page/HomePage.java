package app.com.mobileassignment.page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.pressImeActionButton;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.startsWith;

import androidx.test.espresso.AmbiguousViewMatcherException;

import app.com.mobileassignment.R;
import app.com.mobileassignment.utility.CustomViewActions;


public class HomePage extends BasePage {
    public static int getSearchInputId(){return R.id.search;}
    public static int getResultsContainerId(){return R.id.results;}
    public static int getListOfCitiesContainer(){return R.id.citiesList;}
    public static int getCityNameId(){return R.id.cityName;}

    /**
     * Type text in to a search bar by id and text
     * @param cityName text to passed into input
     * @param replace true - replaceText / false - typeText
     */
    public static void searchCity(String cityName, boolean replace){
        if(replace){
            onView(withId(R.id.search)).perform(click(), replaceText(cityName));

        } else{
            onView(withId(R.id.search)).perform(typeText(cityName));

        }
        onView(withId(R.id.search)).perform(pressImeActionButton());
    }

    /**
     * clicks city form the list using text
     * @param cityName full name of city with country
     */
    public static void clickCityByText(String cityName){
        onView(withText(cityName)).perform(click());
    }

    /**
     * verifies if search results contains only one view
     * @param text text of element
     */
    public static void checkResultsForAmbiguousViews(String text) throws Exception {

        onView(isRoot()).perform(CustomViewActions.waitForViewWithMatcher(withText(startsWith(text)), 2000));

        try {
           onView(allOf(withId(R.id.cityName),withText(text))).check(matches(isDisplayed()));
        }catch(AmbiguousViewMatcherException e){
            throw new Exception("Multiply cities with the same resourceId and Name are displayed");
        }
    }

}
