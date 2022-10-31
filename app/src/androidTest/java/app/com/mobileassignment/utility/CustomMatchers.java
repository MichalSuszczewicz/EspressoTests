package app.com.mobileassignment.utility;

import android.view.View;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

public final class CustomMatchers{

    /**
     * Matche viewElement at specific position
     * @param matcher matcher type
     * @param position desired position
     * @return true or false depending on existing of view
     */
    public static Matcher<View> getElementFromMatchAtPosition(final Matcher<View> matcher, final int position) {
        return new BaseMatcher<View>() {
            int counter = 0;

            @Override
            public boolean matches(final Object item) {
                        if(matcher.matches(item)) {
                            if (counter == position) {
                                counter++;
                                return true;
                            }
                            counter++;
                        }
                        return false;
            }

            @Override
            public void describeTo(final Description description) {
                description.appendText("Element at hierarchy position "+position);
            }
        };
    }



}
