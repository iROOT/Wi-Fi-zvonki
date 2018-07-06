package com.mavenir.android.vtow.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import com.fgmicrotec.mobile.android.fgvoip.f.g;
import com.fgmicrotec.mobile.android.fgvoip.f.h;
import com.fgmicrotec.mobile.android.fgvoip.f.k;

public class HelpActivity extends Activity {
    private ActionBar a;
    private int b = -1;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.a = getActionBar();
        this.a.setDisplayHomeAsUpEnabled(true);
        setTitle(getString(k.app_name) + " " + getString(k.help));
        if (getIntent().hasExtra("InternalIntents.ExtraHelpTopic")) {
            this.b = getIntent().getExtras().getInt("InternalIntents.ExtraHelpTopic");
            a(this.b);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case 16908332:
                a();
                break;
        }
        return true;
    }

    public void onBackPressed() {
        a();
    }

    private void a() {
        if (this.b == k.help_topic_activation) {
            b();
        }
        finish();
    }

    private void b() {
        Intent intent = new Intent(this, MainTabActivity.class);
        intent.setAction("ActivationActions.ActionStartInitialProvisioning");
        startActivity(intent);
    }

    private void a(int i) {
        if (i == k.help_topic_activation) {
            c();
        }
    }

    private void c() {
        super.setContentView(h.help_topic_layout);
        TextView textView = (TextView) findViewById(g.topicTextView);
        TextView textView2 = (TextView) findViewById(g.contentTextView);
        CharSequence fromHtml = Html.fromHtml(String.format(getString(k.help_text_activation), new Object[]{getString(k.activation_auto), getString(k.activation_manual)}));
        textView.setText(k.help_topic_activation);
        textView2.setText(fromHtml);
    }
}
