package com.fgmicrotec.mobile.android.fgvoip;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;

public class HelpTopicDetailActivity extends ActionBarActivity {
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (bundle == null) {
            Bundle bundle2 = new Bundle();
            bundle2.putInt("topic_id", getIntent().getIntExtra("topic_id", 0));
            Fragment cVar = new c();
            cVar.setArguments(bundle2);
            getSupportFragmentManager().beginTransaction().add(16908290, cVar).commit();
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case 16908332:
                NavUtils.navigateUpTo(this, new Intent(this, HelpTopicListActivity.class));
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }
}
