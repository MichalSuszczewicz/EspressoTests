package app.com.mobileassignment.tests;

import android.app.Instrumentation;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.IdlingResource;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.uiautomator.UiDevice;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;

import app.com.mobileassignment.views.MainActivity;

public class BaseTest {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityTestRule =
            new ActivityScenarioRule<MainActivity>(MainActivity.class);

        private IdlingResource mIdlingResource;


    Instrumentation instrumentationRegistry = InstrumentationRegistry.getInstrumentation();
    UiDevice device = UiDevice.getInstance(instrumentationRegistry);


    @Before
        public void setupUp() {
            ActivityScenario activityScenario = mActivityTestRule.getScenario();

            activityScenario.onActivity((ActivityScenario.ActivityAction<MainActivity>) activity -> {
                mIdlingResource = activity.getIdlingResource();
                IdlingRegistry.getInstance().register(mIdlingResource);
            });
        }

    @After
    public void tearDown() {
        if (mIdlingResource != null) {
            IdlingRegistry.getInstance().unregister(mIdlingResource);
        }
    }
}
