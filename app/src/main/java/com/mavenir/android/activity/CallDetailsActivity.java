package com.mavenir.android.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import com.mavenir.android.common.q;
import com.mavenir.android.fragments.a;

public class CallDetailsActivity extends ActionBarActivity {
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        final FragmentManager supportFragmentManager = getSupportFragmentManager();
        if (supportFragmentManager.findFragmentById(16908290) == null) {
            new Handler().post(new Runnable(this) {
                final /* synthetic */ CallDetailsActivity c;

                public void run() {
                    if (bundle == null) {
                        try {
                            supportFragmentManager.beginTransaction().add(16908290, new a()).commit();
                        } catch (Throwable e) {
                            q.c("CallDetailsActivity", "inserting CallDetailsFragment", e);
                        }
                    }
                }
            });
        }
    }
}
