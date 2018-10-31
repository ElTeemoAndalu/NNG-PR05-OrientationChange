package es.iessaladillo.pedrojoya.pr05.data.local;

import org.hamcrest.collection.IsIterableContainingInOrder;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import es.iessaladillo.pedrojoya.pr05.data.local.model.Avatar;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.junit.Assert.assertNull;

public class DatabaseTest {

    private Database database;

    @Before
    public void setUp() {
        database = Database.getInstance();
    }

    @Test
    public void shouldGetInstanceReturnNotNull() {
        assertNotNull(Database.getInstance());
    }

    @Test
    public void shouldQueryAvatarsReturnNotNullWhenNoAvatar() {
        database.setAvatars(Collections.emptyList());

        List<Avatar> avatars = database.queryAvatars();

        assertNotNull(avatars);
    }

    @Test
    public void shouldQueryAvatarsReturnData() {
        Avatar avatar1 = new Avatar(0, "Test1");
        Avatar avatar2 = new Avatar(0, "Test2");
        List<Avatar> data = Arrays.asList(avatar1, avatar2);
        database.setAvatars(data);

        List<Avatar> avatars = database.queryAvatars();

        assertThat(avatars, IsIterableContainingInOrder.contains(avatar1, avatar2));
    }

    @Test
    public void shouldGetDefaultAvatarReturnNullWhenNoData() {
        database.setAvatars(Collections.emptyList());

        Avatar defaultAvatar = database.getDefaultAvatar();

        assertNull(defaultAvatar);
    }

    @Test
    public void shouldGetDefaultAvatarReturnFirstAvatar() {
        Avatar avatar1 = new Avatar(0, "Test1");
        Avatar avatar2 = new Avatar(0, "Test2");
        List<Avatar> data = Arrays.asList(avatar1, avatar2);
        database.setAvatars(data);

        Avatar defaultAvatar = database.getDefaultAvatar();

        assertEquals(avatar1, defaultAvatar);
    }

    @Test
    public void shouldGetRandomAvatarReturnNullWhenNoData() {
        database.setAvatars(Collections.emptyList());

        Avatar randomAvatar = database.getRandomAvatar();

        assertNull(randomAvatar);
    }

    @Test
    public void shouldGetRandomAvatarReturnAvatar() {
        Avatar avatar1 = new Avatar(0, "Test1");
        Avatar avatar2 = new Avatar(0, "Test2");
        List<Avatar> data = Arrays.asList(avatar1, avatar2);
        database.setAvatars(data);

        Avatar randomAvatar = database.getRandomAvatar();

        assertThat(data, hasItem(randomAvatar));
    }


    @Test
    public void shouldQueryAvatarReturnNullWhenNotFound() {
        database.setAvatars(Collections.emptyList());
        Avatar avatar1 = new Avatar(0, "Test1");
        Avatar avatar2 = new Avatar(0, "Test2");
        database.insertAvatar(avatar1);
        database.insertAvatar(avatar2);

        Avatar avatar = database.queryAvatar(3);

        assertNull(avatar);
    }

    @Test
    public void shouldQueryAvatarReturnTheAvatar() {
        database.setAvatars(Collections.emptyList());
        Avatar avatar1 = new Avatar(0, "Test1");
        Avatar avatar2 = new Avatar(0, "Test2");
        database.insertAvatar(avatar1);
        database.insertAvatar(avatar2);

        Avatar avatar = database.queryAvatar(2);

        assertEquals(avatar2, avatar);
    }

}
