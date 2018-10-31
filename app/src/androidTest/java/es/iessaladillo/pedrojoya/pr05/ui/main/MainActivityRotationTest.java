package es.iessaladillo.pedrojoya.pr05.ui.main;


import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import es.iessaladillo.pedrojoya.pr05.R;
import es.iessaladillo.pedrojoya.pr05.utils.Rotation;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withTagValue;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityRotationTest {

    @Rule
    public final IntentsTestRule<MainActivity> testRule = new IntentsTestRule<>(MainActivity.class);

    @Before
    public void setup() {
        onView(withId(R.id.txtName)).perform(closeSoftKeyboard());
    }

    // Avatar

    @Test
    public void shouldHaveSameAvatarAfterRotation() {
        onView(withId(R.id.imgAvatar)).perform(click());
        // Perform click on AvatarActivity to send result and finish.
        onView(withId(R.id.imgAvatar2)).perform(click());
        onView(withId(R.id.mnuSelect)).perform(click());
        Rotation.rotateScreen(testRule.getActivity());
        // Check result set to views.
        onView(withId(R.id.imgAvatar)).check(matches(withTagValue(equalTo(R.drawable.cat2))));
        onView(withId(R.id.lblAvatar)).check(
                matches(withText(testRule.getActivity().getString(R.string.avatar2_name))));
    }

    // ImageView should get disabled when EditText content is invalid.

    @Test
    public void shouldEmailIconBeDisabledWhenInvalidDataAfterRotation() {
        onView(withId(R.id.txtEmail)).perform(replaceText("test"));
        Rotation.rotateScreen(testRule.getActivity());
        onView(withId(R.id.imgEmail)).check(matches(not(isEnabled())));
    }

    @Test
    public void shouldPhonenumberIconBeDisabledWhenInvalidDataAfterRotation() {
        onView(withId(R.id.txtPhonenumber)).perform(replaceText("1"));
        Rotation.rotateScreen(testRule.getActivity());
        onView(withId(R.id.imgPhonenumber)).check(matches(not(isEnabled())));
    }

    @Test
    public void shouldAddressIconBeDisabledWhenInvalidDataAfterRotation() {
        onView(withId(R.id.txtAddress)).perform(replaceText(""));
        Rotation.rotateScreen(testRule.getActivity());
        onView(withId(R.id.imgAddress)).check(matches(not(isEnabled())));
    }

    @Test
    public void shouldWebIconBeDisabledWhenInvalidDataAfterRotation() {
        onView(withId(R.id.txtWeb)).perform(replaceText("test"));
        Rotation.rotateScreen(testRule.getActivity());
        onView(withId(R.id.imgWeb)).check(matches(not(isEnabled())));
    }

    // EditText should show error when content is invalid.

    @Test
    public void shouldNameEditTextShowErrorWhenInvalidDataAfterRotation() {
        onView(withId(R.id.txtName)).perform(replaceText(""));
        Rotation.rotateScreen(testRule.getActivity());
        onView(withId(R.id.txtName)).check(matches(hasErrorText(
                testRule.getActivity().getString(R.string.main_invalid_data))));
    }

    @Test
    public void shouldEmailEditTextShowErrorWhenInvalidDataAfterRotation() {
        onView(withId(R.id.txtEmail)).perform(replaceText("test"));
        Rotation.rotateScreen(testRule.getActivity());
        onView(withId(R.id.txtEmail)).check(matches(hasErrorText(
                testRule.getActivity().getString(R.string.main_invalid_data))));
    }

    @Test
    public void shouldPhonenumberEditTextShowErrorWhenInvalidDataAfterRotation() {
        onView(withId(R.id.txtPhonenumber)).perform(replaceText("1"));
        Rotation.rotateScreen(testRule.getActivity());
        onView(withId(R.id.txtPhonenumber)).check(matches(hasErrorText(
                testRule.getActivity().getString(R.string.main_invalid_data))));
    }

    @Test
    public void shouldAddressEditTextShowErrorWhenInvalidDataAfterRotation() {
        onView(withId(R.id.txtAddress)).perform(replaceText(""));
        Rotation.rotateScreen(testRule.getActivity());
        onView(withId(R.id.txtAddress)).check(matches(hasErrorText(
                testRule.getActivity().getString(R.string.main_invalid_data))));
    }

    @Test
    public void shouldWebEditTextShowErrorWhenInvalidDataAfterRotation() {
        onView(withId(R.id.txtWeb)).perform(replaceText("test"));
        Rotation.rotateScreen(testRule.getActivity());
        onView(withId(R.id.txtWeb)).check(matches(hasErrorText(
                testRule.getActivity().getString(R.string.main_invalid_data))));
    }

    // TextView should get disabled when EditText content is invalid.

    @Test
    public void shouldEmailLabelBeDisabledWhenInvalidDataAfterRotation() {
        onView(withId(R.id.txtEmail)).perform(replaceText("test"));
        onView(withId(R.id.lblEmail)).check(matches(not(isEnabled())));
        Rotation.rotateScreen(testRule.getActivity());
    }

    @Test
    public void shouldPhonenumberLabelBeDisabledWhenInvalidDataAfterRotation() {
        onView(withId(R.id.txtPhonenumber)).perform(replaceText("1"));
        Rotation.rotateScreen(testRule.getActivity());
        onView(withId(R.id.lblPhonenumber)).check(matches(not(isEnabled())));
    }

    @Test
    public void shouldAddressLabelBeDisabledWhenInvalidDataAfterRotation() {
        onView(withId(R.id.txtAddress)).perform(replaceText(""));
        Rotation.rotateScreen(testRule.getActivity());
        onView(withId(R.id.lblAddress)).check(matches(not(isEnabled())));
    }

    @Test
    public void shouldWebLabelBeDisabledWhenInvalidDataAfterRotation() {
        onView(withId(R.id.txtWeb)).perform(replaceText("test"));
        Rotation.rotateScreen(testRule.getActivity());
        onView(withId(R.id.lblWeb)).check(matches(not(isEnabled())));
    }

}
