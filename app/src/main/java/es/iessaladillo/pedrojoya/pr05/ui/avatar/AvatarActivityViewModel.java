package es.iessaladillo.pedrojoya.pr05.ui.avatar;

import java.util.List;

import androidx.lifecycle.ViewModel;
import es.iessaladillo.pedrojoya.pr05.data.local.Database;
import es.iessaladillo.pedrojoya.pr05.data.local.model.Avatar;

public class AvatarActivityViewModel extends ViewModel {
    private Avatar selectedAvatar;
    private Database database;
    private List<Avatar> avatarList;
    private boolean intentRetrieved = false;

    public List<Avatar> getAvatarList() {
        if (avatarList == null) {
            avatarList = getDatabase().queryAvatars();
        }
        return avatarList;
    }

    public boolean isIntentRetrieved() {
        return intentRetrieved;
    }

    public void setIntentRetrieved(boolean intentRetrieved) {
        this.intentRetrieved = intentRetrieved;
    }

    public Avatar getSelectedAvatar() {
        return selectedAvatar;
    }

    public void setSelectedAvatar(Avatar selectedAvatar) {
        this.selectedAvatar = selectedAvatar;
    }

    public Database getDatabase() {
        if (database == null) {
            database = Database.getInstance();
        }
        return database;
    }

}
