package app.com.mobileassignment.page;

import android.widget.TextView;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.pressBack;
import static androidx.test.espresso.action.ViewActions.swipeDown;
import static androidx.test.espresso.action.ViewActions.swipeUp;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.Visibility;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.startsWith;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withResourceName;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static app.com.mobileassignment.utility.CustomMatchers.getElementFromMatchAtPosition;

import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.assertion.ViewAssertions;

import app.com.mobileassignment.utility.CustomViewActions;

public class BasePage {

    public enum TextMatches{
        STARTS_WITH,
        CONTAINS,
        WITH_TEXT;

    }

    /**
     * verifies if view is visible using resource id
     * @param resourceId view id
     */
    public static void checkIfElementIsVisibleById(Integer resourceId){
        ViewInteraction view = onView(
                allOf(withId(resourceId)));
        view.check(matches(withEffectiveVisibility(Visibility.VISIBLE)));
    }

    /**
     * verifies if view is visible at specific position in list using startsWith(text) and resourceId
     * @param text text of element
     * @param resourceId id of view
     * @param position position on the list
     * @param textMatches text matcher type e.g. STARTS_WITH, CONTAINS, WITH_TEXT
     */
    public static void checkIfElementIsVisibleInSpecificPositionByText(String text, Integer resourceId, int position, TextMatches textMatches){
        ViewInteraction view;

        switch (textMatches){
            case STARTS_WITH:
                onView(isRoot()).perform(CustomViewActions.waitForViewWithMatcher(allOf(withId(resourceId),withText(startsWith(text))), 5000));

                view = onView(
                        allOf(withId(resourceId),
                                getElementFromMatchAtPosition(withText(startsWith(text)), position)));
                break;
            case CONTAINS:
                onView(isRoot()).perform(CustomViewActions.waitForViewWithMatcher(allOf(withId(resourceId),withText(containsString(text))), 5000));

                view = onView(
                        allOf(withId(resourceId),
                                getElementFromMatchAtPosition(withText(containsString(text)), position)));
                break;
            case WITH_TEXT:
                onView(isRoot()).perform(CustomViewActions.waitForViewWithMatcher(allOf(withId(resourceId),withText(text)), 5000));

                view = onView(
                        allOf(withId(resourceId),
                                getElementFromMatchAtPosition(withText(text), position)));
                break;
            default:
                throw new IllegalStateException("Unexpected text matcher: " + textMatches);
        }
        view.check(matches(withEffectiveVisibility(Visibility.VISIBLE)));

    }


    /**
     * verifies if view is displayed on the screen using resource id
     * @param resourceId view id
     */
    public static void checkIfElementIsDisplayedById(Integer resourceId){
        ViewInteraction view = onView(
                allOf(withId(resourceId)));
        view.check(matches(withEffectiveVisibility(Visibility.VISIBLE)));
    }

    /**
     * asserts app title in action bar if it matches expected string
     * @param text expected app title
     */

    public static void checkIfTitleIsDisplayed(String text) {
        ViewInteraction textView = onView(
                allOf(instanceOf(TextView.class),
                        withParent(withResourceName("action_bar"))));
        textView.check(matches(withText(text)));
    }

    /**
     * swipes the screen up or down to the defined position
     * @param resourceId resource of the list
     * @param position targeted position of the list
     * @param swipeUp true - swipes up / false - swipes down
     */
    public static void swipeListToPosition(Integer resourceId, int position, boolean swipeUp){
        DataInteraction list = onData(anything()).inAdapterView(allOf(withId(resourceId), isDisplayed()));
        if (swipeUp){
            list.atPosition(position).perform(swipeUp());
        }
        else{
            list.atPosition(position).perform(swipeDown());
        }
    }

    /**
     * verifies if activity with given content description is displayed
     * @param viewDescription contentDescription
     */
    public static void verifyIfViewIsDisplayedByDescription(String viewDescription){
        ViewInteraction view = onView(isRoot()).perform(CustomViewActions.waitForViewByDescription(viewDescription, 2000));
        view.check(ViewAssertions.matches(isDisplayed()));
    }

    /**
     * verifies if activity with given content description is clickable
     * @param viewDescription contentDescription
     */
    public static void verifyIfViewIsClickableByDescription(String viewDescription){
        onView(withContentDescription(viewDescription)).check(matches(isClickable()));
    }

    /**
     * Goes to previous screen using native back button
     */
    public static void goBack(){
        onView(isRoot()).perform(pressBack());
    }
}
