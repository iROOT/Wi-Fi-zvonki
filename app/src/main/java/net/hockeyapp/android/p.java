package net.hockeyapp.android;

import java.util.Date;
import org.json.JSONArray;

public abstract class p extends j {
    public Class<? extends UpdateActivity> getUpdateActivityClass() {
        return UpdateActivity.class;
    }

    public Class<? extends m> getUpdateFragmentClass() {
        return m.class;
    }

    public void onNoUpdateAvailable() {
    }

    public void onUpdateAvailable() {
    }

    public void onCancel() {
    }

    public void onUpdateAvailable(JSONArray jSONArray, String str) {
        onUpdateAvailable();
    }

    public Date getExpiryDate() {
        return null;
    }

    public boolean onBuildExpired() {
        return true;
    }

    public boolean canUpdateInMarket() {
        return false;
    }

    public void onUpdatePermissionsNotGranted() {
    }
}
