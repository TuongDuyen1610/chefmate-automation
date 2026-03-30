package flows;

import core.base.BaseFlow;
import screens.ProfileScreen;

public class ProfileFlow extends BaseFlow {
    private final ProfileScreen profileScreen = new ProfileScreen();

    public String getUsername() {
        return profileScreen.getUsername();
    }
}

