package com.example.simpleparadox.listycity;
import android.app.Activity;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;
import android.widget.EditText;
import android.widget.ListView;
import com.robotium.solo.Solo;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
/**
 * Test class for MainActivity. All the UI tests are written here. Robotium test framework is
 used
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    private Solo solo;
    @Rule
    public ActivityTestRule<MainActivity> rule =
            new ActivityTestRule<>(MainActivity.class, true, true);

    /**
     * Runs before all tests and creates solo instance.
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        solo = new Solo(InstrumentationRegistry.getInstrumentation(), rule.getActivity());
    }

    /**
     * Gets the Activity
     *
     * @throws Exception
     */
    @Test
    public void start() throws Exception {
        Activity activity = rule.getActivity();
    }

    /**
     * Add a city to the listview and check the city name using assertTrue
     * Clear all the cities from the listview and check again with assertFalse
     */
    @Test
    public void checkList() {
        solo.assertCurrentActivity("Wrong Activity", MainActivity.class);
        solo.clickOnButton("ADD CITY");

        solo.enterText((EditText) solo.getView(R.id.editText_name), "Dhaka");
        solo.clickOnButton("CONFIRM");
        solo.clearEditText((EditText) solo.getView(R.id.editText_name));

        assertTrue(solo.waitForText("Dhaka", 1, 2000));
        solo.clickOnButton("ClEAR ALL");

        assertFalse(solo.searchText("Dhaka"));
    }

    /**
     * Check item taken from the listview
     */
    @Test
    public void checkCiyListItem() {
        solo.assertCurrentActivity("Wrong Activity", MainActivity.class);
        solo.clickOnButton("ADD CITY");
        solo.enterText((EditText) solo.getView(R.id.editText_name), "Khulna");
        solo.clickOnButton("CONFIRM");
        solo.waitForText("Khulna", 1, 2000);

        MainActivity activity = (MainActivity) solo.getCurrentActivity();
        final ListView cityList = activity.cityList;
        String city = (String) cityList.getItemAtPosition(0);
        assertEquals("Khulna", city);
    }

    @Test
    public  void  ListItemClick(){
        solo.assertCurrentActivity("Wrong Activity", MainActivity.class);
        solo.clickOnButton("ADD CITY");
        solo.enterText((EditText) solo.getView(R.id.editText_name), "Manikganj");
        solo.clickOnButton("CONFIRM");
        solo.clearEditText((EditText) solo.getView(R.id.editText_name));
        assertTrue(solo.waitForText("Manikganj", 1, 2000));

        solo.clickOnText("Manikganj");
        solo.assertCurrentActivity("ShowActivity", ShowActivity.class);
        assertTrue(solo.waitForText("Manikganj", 1, 2000));
        solo.clickOnButton("Back");

        solo.assertCurrentActivity("ShowActivity", MainActivity.class);
    }
    /**
     * Close activity after each test
     *
     * @throws Exception
     */
    @After
    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
    }
}
