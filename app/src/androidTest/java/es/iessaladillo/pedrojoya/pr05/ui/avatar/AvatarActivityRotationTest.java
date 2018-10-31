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
import es.iessaladillo.pedrojoya.pr05.utils.ResourcesUtils;
import es.iessaladillo.pedrojoya.pr05.utils.Rotation;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withAlpha;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class AvatarActivityRotationTest {

    @Rule
    public final IntentsTestRule<AvatarActivity> testRule = new IntentsTestRule<>(
            AvatarActivity.class, true, false);

    @Before
    public void setup() {
        testRule.launchActivity(
                new Intent().putExtra("EXTRA_AVATAR", Database.getInstance().getDefaultAvatar()));
    }

    // Rotation

    @Test
    public void shouldHaveSameAvatarSelectedAfterRotation() {
        onView(withId(R.id.imgAvatar2)).perform(click());
        Rotation.rotateScreen(testRule.getActivity());
        onView(withId(R.id.imgAvatar2)).check(matches(withAlpha(
                ResourcesUtils.getFloat(testRule.getActivity(),
                        R.dimen.avatar_selected_image_alpha))));
    }

}
