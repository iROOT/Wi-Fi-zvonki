package com.mavenir.android.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import com.fgmicrotec.mobile.android.fgvoip.FgVoIP;
import com.mavenir.android.fragments.d;

public class ContactDetailsActivity extends ActionBarActivity {
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final Intent intent = getIntent();
        if (intent == null) {
            finish();
            return;
        }
        final d a = a();
        if (bundle == null) {
            new Handler().post(new Runnable(this) {
                final /* synthetic */ ContactDetailsActivity c;

                public void run() {
                    Uri data = intent.getData();
                    String stringExtra = intent.getStringExtra("EXTRA_PHONE_NUMBER");
                    if (stringExtra != null) {
                        a.a(stringExtra);
                    } else {
                        a.a(data);
                    }
                }
            });
        }
    }

    private d a() {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        d dVar = (d) supportFragmentManager.findFragmentById(16908290);
        if (dVar == null) {
            try {
                dVar = (d) Class.forName("com.mavenir.android.rcs.fragments.RcsContactDetailsFragment").newInstance();
            } catch (ClassNotFoundException e) {
            } catch (InstantiationException e2) {
                e2.printStackTrace();
            } catch (IllegalAccessException e3) {
                e3.printStackTrace();
            }
            if (dVar == null) {
                dVar = new d();
            }
            supportFragmentManager.beginTransaction().add(16908290, (Fragment) dVar).commit();
        }
        return dVar;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
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
