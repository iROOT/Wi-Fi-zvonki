package com.mavenir.android.vtow.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import com.fgmicrotec.mobile.android.fgvoip.f.h;
import com.fgmicrotec.mobile.android.fgvoip.f.k;
import com.mavenir.android.common.q;

public class ActivationTermsActivity extends ActionBarActivity {
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(h.activation_terms_activity);
        Uri data = getIntent().getData();
        if (data == null || data.getLastPathSegment() == null) {
            q.d("ActivationInitialActivity", "onCreate(): URI (" + data + ") is invalid, finishing...");
            finish();
            return;
        }
        q.a("ActivationInitialActivity", "onCreate(): URI=" + data);
        a(data);
    }

    private void a(Uri uri) {
        String lastPathSegment = uri.getLastPathSegment();
        if (lastPathSegment != null && lastPathSegment.equals("tnc")) {
            a();
        } else if (lastPathSegment.equals("eula")) {
            b();
        } else {
            q.d("ActivationInitialActivity", "displayTermsText(): Wrong term type: " + lastPathSegment);
        }
    }

    private void a() {
        setTitle(k.activation_terms_title);
        startActivity(new Intent("android.intent.action.VIEW", Uri.parse(getString(k.exception_url_help_2))));
        finish();
    }

    private void b() {
        setTitle(k.activation_eula_title);
        startActivity(new Intent("android.intent.action.VIEW", Uri.parse(getString(k.exception_url_help_3))));
        finish();
    }
}
