package es.iessaladillo.pedrojoya.pr05.ui.avatar;

import android.content.Intent;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import es.iessaladillo.pedrojoya.pr05.R;
import es.iessaladillo.pedrojoya.pr05.data.local.Database;

import static android.app.Activity.RESULT_OK;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static androidx.test.espresso.matcher.ViewMatchers.assertThat;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.is;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class AvatarActivityIntentTest {

    @Rule
    public final IntentsTestRule<AvatarActivity> testRule = new IntentsTestRule<>(
            AvatarActivity.class, true, false);

    @Before
    public void setup() {
        testRule.launchActivity(
                new Intent().putExtra("EXTRA_AVATAR", Database.getInstance().getDefaultAvatar()));
    }

    // Sending result intent

    @Test
    public void shouldReturnAvatarWhenSelectMenuClicked() {
        onView(withId(R.id.imgAvatar2)).perform(click());
        onView(withId(R.id.mnuSelect)).perform(click());
        int resultCode = testRule.getActivityResult().getResultCode();
        Intent intent = testRule.getActivityResult().getResultData();
        assertThat(resultCode, is(RESULT_OK));
        assertThat(intent, hasExtra("EXTRA_AVATAR", Database.getInstance().queryAvatar(2)));
    }

}
