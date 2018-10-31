package es.iessaladillo.pedrojoya.pr05.ui.avatar;

import android.content.Intent;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.annotation.IdRes;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import es.iessaladillo.pedrojoya.pr05.R;
import es.iessaladillo.pedrojoya.pr05.data.local.Database;
import es.iessaladillo.pedrojoya.pr05.utils.ResourcesUtils;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withAlpha;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class AvatarActivityTest {

    @Rule
    public final IntentsTestRule<AvatarActivity> testRule = new IntentsTestRule<>(
            AvatarActivity.class, true, false);

    @Before
    public void setup() {
        testRule.launchActivity(
                new Intent().putExtra("EXTRA_AVATAR", Database.getInstance().getDefaultAvatar()));
    }

    @Test
    public void shouldSelectReceivedAvatar() {
        onView(withId(R.id.imgAvatar1)).check(matches(withAlpha(
                ResourcesUtils.getFloat(testRule.getActivity(),
                        R.dimen.avatar_selected_image_alpha))));
    }

    @Test
    public void shouldChangeSelectionWhenImageView1Clicked() {
        // Change the selection to a different avatar.
        onView(withId(R.id.imgAvatar2)).perform(click());
        onView(withId(R.id.imgAvatar1)).perform(click());
        onView(withId(R.id.imgAvatar1)).check(matches(withAlpha(
                ResourcesUtils.getFloat(testRule.getActivity(),
                        R.dimen.avatar_selected_image_alpha))));
        onView(withId(R.id.imgAvatar2)).check(matches(withAlpha(
                ResourcesUtils.getFloat(testRule.getActivity(),
                        R.dimen.avatar_not_selected_image_alpha))));
    }

    @Test
    public void shouldChangeSelectionWhenImageView2Clicked() {
        shouldChangeSelectionWhenViewClicked(R.id.imgAvatar2, R.id.imgAvatar2);
    }

    @Test
    public void shouldChangeSelectionWhenImageView3Clicked() {
        shouldChangeSelectionWhenViewClicked(R.id.imgAvatar3, R.id.imgAvatar3);
    }

    @Test
    public void shouldChangeSelectionWhenImageView4Clicked() {
        shouldChangeSelectionWhenViewClicked(R.id.imgAvatar4, R.id.imgAvatar4);
    }

    @Test
    public void shouldChangeSelectionWhenImageView5Clicked() {
        shouldChangeSelectionWhenViewClicked(R.id.imgAvatar5, R.id.imgAvatar5);
    }

    @Test
    public void shouldChangeSelectionWhenImageView6Clicked() {
        shouldChangeSelectionWhenViewClicked(R.id.imgAvatar6, R.id.imgAvatar6);
    }

    @Test
    public void shouldChangeSelectionWhenTextView2Clicked() {
        shouldChangeSelectionWhenViewClicked(R.id.lblAvatar2, R.id.imgAvatar2);
    }

    @Test
    public void shouldChangeSelectionWhenTextView3Clicked() {
        shouldChangeSelectionWhenViewClicked(R.id.lblAvatar3, R.id.imgAvatar3);
    }

    @Test
    public void shouldChangeSelectionWhenTextView4Clicked() {
        shouldChangeSelectionWhenViewClicked(R.id.lblAvatar4, R.id.imgAvatar4);
    }

    @Test
    public void shouldChangeSelectionWhenTextView5Clicked() {
        shouldChangeSelectionWhenViewClicked(R.id.lblAvatar5, R.id.imgAvatar5);
    }

    @Test
    public void shouldChangeSelectionWhenTextView6Clicked() {
        shouldChangeSelectionWhenViewClicked(R.id.lblAvatar6, R.id.imgAvatar6);
    }

    private void shouldChangeSelectionWhenViewClicked(@IdRes int clickedViewResId, @IdRes int
            selectedImageViewResId) {
        onView(withId(clickedViewResId)).perform(click());
        onView(withId(selectedImageViewResId)).check(matches(withAlpha(
                ResourcesUtils.getFloat(testRule.getActivity(),
                        R.dimen.avatar_selected_image_alpha))));
        onView(withId(R.id.imgAvatar1)).check(matches(withAlpha(
                ResourcesUtils.getFloat(testRule.getActivity(),
                        R.dimen.avatar_not_selected_image_alpha))));
    }

    @Test
    public void shouldChangeSelectionWhenTextView1Clicked() {
        // Change the selection to a different avatar.
        onView(withId(R.id.imgAvatar2)).perform(click());
        onView(withId(R.id.lblAvatar1)).perform(click());
        onView(withId(R.id.imgAvatar1)).check(matches(withAlpha(
                ResourcesUtils.getFloat(testRule.getActivity(),
                        R.dimen.avatar_selected_image_alpha))));
        onView(withId(R.id.imgAvatar2)).check(matches(withAlpha(
                ResourcesUtils.getFloat(testRule.getActivity(),
                        R.dimen.avatar_not_selected_image_alpha))));
    }

}
