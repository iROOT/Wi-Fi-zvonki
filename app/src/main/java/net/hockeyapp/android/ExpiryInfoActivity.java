package net.hockeyapp.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import net.hockeyapp.android.views.ExpiryInfoView;

public class ExpiryInfoActivity extends Activity {
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTitle(getStringResource(k.EXPIRY_INFO_TITLE_ID));
        setContentView(getLayoutView());
    }

    protected View getLayoutView() {
        return new ExpiryInfoView(this, getStringResource(k.EXPIRY_INFO_TEXT_ID));
    }

    protected String getStringResource(int i) {
        return k.get(o.getLastListener(), i);
    }
}
