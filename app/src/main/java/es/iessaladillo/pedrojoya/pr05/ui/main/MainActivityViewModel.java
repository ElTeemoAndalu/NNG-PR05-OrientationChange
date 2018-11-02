package es.iessaladillo.pedrojoya.pr05.ui.main;

import androidx.lifecycle.ViewModel;
import es.iessaladillo.pedrojoya.pr05.data.local.Database;
import es.iessaladillo.pedrojoya.pr05.data.local.model.Avatar;

public class MainActivityViewModel extends ViewModel {
    private Database database;
    private Avatar profileAvatar;

    public Avatar getProfileAvatar() {
        return profileAvatar;
    }

    public void setProfileAvatar(Avatar profileAvatar) {
        this.profileAvatar = profileAvatar;
    }

    public Database getDatabase() {
        if (database == null) {
            database = Database.getInstance();
        }
        return database;
    }

    public void setDefaultAvatar(){
        setProfileAvatar(getDatabase().getDefaultAvatar());

    }


}
