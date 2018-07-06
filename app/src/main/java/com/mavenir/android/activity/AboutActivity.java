package com.mavenir.android.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import com.fgmicrotec.mobile.android.fgvoip.FgVoIP;
import com.fgmicrotec.mobile.android.fgvoip.f.g;
import com.fgmicrotec.mobile.android.fgvoip.f.h;
import com.mavenir.android.common.l;

public class AboutActivity extends Activity {
    private TextView a;
    private TextView b;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.setContentView(h.about);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        this.a = (TextView) findViewById(g.About_TextView_Name);
        this.b = (TextView) findViewById(g.About_TextView_Build);
        String str = "";
        str = "";
        CharSequence charSequence = l.a((Context) this).a() + " v" + l.a((Context) this).a(false);
        CharSequence e = l.a((Context) this).e();
        this.a.setText(charSequence);
        this.b.setText(e);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case 16908332:
                FgVoIP.U().c((Activity) this);
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }
}
