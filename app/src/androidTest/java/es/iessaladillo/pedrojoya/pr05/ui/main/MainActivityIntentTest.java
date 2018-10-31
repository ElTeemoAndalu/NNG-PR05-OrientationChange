package es.iessaladillo.pedrojoya.pr05.ui.main;


import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import es.iessaladillo.pedrojoya.pr05.R;
import es.iessaladillo.pedrojoya.pr05.data.local.Database;
import es.iessaladillo.pedrojoya.pr05.ui.avatar.AvatarActivity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.Intents.intending;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasData;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withTagValue;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.AllOf.allOf;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityIntentTest {

    @Rule
    public final IntentsTestRule<MainActivity> testRule = new IntentsTestRule<>(MainActivity.class);

    @Before
    public void setup() {
        onView(withId(R.id.txtName)).perform(closeSoftKeyboard());
    }

    // Implicit intents

    @Test
    public void shouldEmailSendIntent() {
        onView(withId(R.id.txtEmail)).perform(typeText("baldomero@gmail.com"),
                closeSoftKeyboard());
        onView(withId(R.id.imgEmail)).perform(click());
        intended(Matchers.allOf(hasAction(Intent.ACTION_SENDTO), hasData("mailto:baldomero@gmail.com")));
    }

    @Test
    public void shouldPhonenumberSendIntent() {
        onView(withId(R.id.txtPhonenumber)).perform(typeText("666666666"),
                closeSoftKeyboard());
        onView(withId(R.id.imgPhonenumber)).perform(click());
        intended(Matchers.allOf(hasAction(Intent.ACTION_DIAL), hasData("tel:666666666")));
    }

    @Test
    public void shouldAddressSendIntent() {
        onView(withId(R.id.txtAddress)).perform(typeText("Avda. Duque de Rivas"),
                closeSoftKeyboard());
        onView(withId(R.id.imgAddress)).perform(click());
        intended(Matchers.allOf(hasAction(Intent.ACTION_VIEW), hasData("geo:0,0?q=Avda. Duque de Rivas")));
    }

    @Test
    public void shouldWebSendIntent() {
        ViewInteraction txtWeb = onView(withId(R.id.txtWeb));
        txtWeb.perform(click(), replaceText("http://www.iessaladillo.es"), closeSoftKeyboard());
        onView(withId(R.id.imgWeb)).perform(click());
        intended(allOf(hasAction(Intent.ACTION_VIEW), hasData("http://www.iessaladillo.es")));
    }

    // Explicit Intent

    @Test
    public void shouldAvatarSendIntent() {
        onView(withId(R.id.imgAvatar)).perform(click());
        // Check sending intent
        intended(allOf(hasComponent(AvatarActivity.class.getName()),
                hasExtra(AvatarActivity.EXTRA_AVATAR, Database.getInstance().queryAvatar(1))));
    }

    @Test
    public void shouldReceiveRightIntent() {
        // Setup the result intent. Needed if is not one of your activities.
        Intent resultData = new Intent();
        resultData.putExtra(AvatarActivity.EXTRA_AVATAR, Database.getInstance().queryAvatar(2));
        Instrumentation.ActivityResult result =
                new Instrumentation.ActivityResult(Activity.RESULT_OK, resultData);
        // We simulate the result intent.
        intending(hasComponent(AvatarActivity.class.getName())).respondWith(result);

        onView(withId(R.id.imgAvatar)).perform(click());
        // Check result set to views.
        onView(withId(R.id.imgAvatar)).check(matches(withTagValue(equalTo(R.drawable.cat2))));
        onView(withId(R.id.lblAvatar)).check(
                matches(withText(testRule.getActivity().getString(R.string.avatar2_name))));
    }

}
