package com.fgmicrotec.mobile.android.fgvoip;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.Toast;
import com.fgmicrotec.mobile.android.fgvoip.HelpTopicListFragment.a;
import com.fgmicrotec.mobile.android.fgvoip.b.b;
import com.fgmicrotec.mobile.android.fgvoip.f.h;
import com.fgmicrotec.mobile.android.fgvoip.f.k;
import com.mavenir.android.settings.c.j;

public class HelpTopicListActivity extends ActionBarActivity implements a {
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(h.help_topic_list_activity);
        setTitle(getString(k.app_name) + " " + getString(k.help));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void a(int i) {
        if (i == b.HELP.ordinal()) {
            Object b = j.b(6);
            if (TextUtils.isEmpty(b)) {
                Toast.makeText(this, k.help_unavailable, 0).show();
                return;
            } else {
                startActivity(new Intent("android.intent.action.VIEW", Uri.parse(b)));
                return;
            }
        }
        Intent intent = new Intent(this, HelpTopicDetailActivity.class);
        intent.putExtra("topic_id", i);
        startActivity(intent);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case 16908332:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }
}
