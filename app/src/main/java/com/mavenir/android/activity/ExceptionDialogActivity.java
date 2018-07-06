package com.mavenir.android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import com.mavenir.android.common.q;
import com.mavenir.android.fragments.f;
import com.mavenir.android.fragments.f.a;

public class ExceptionDialogActivity extends ActionBarActivity {
    private int a = 0;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        a(getIntent());
    }

    private void a(Intent intent) {
        a aVar = (a) intent.getSerializableExtra("ExceptionDialogFragment.ExtraExceptionType");
        int intExtra = intent.getIntExtra("ExceptionDialogFragment.ExtraExceptionID", -1);
        int intExtra2 = intent.getIntExtra("ExceptionDialogFragment.SipErrorCode", -1);
        String stringExtra = intent.getStringExtra("ExceptionDialogFragment.ExtraErrorMessage");
        if (aVar != null) {
            q.a("ExceptionDialogActivity", "onCreate(): type: " + aVar.name());
            if (getSupportFragmentManager().findFragmentByTag("ExceptionDialog" + aVar.name()) == null) {
                f.a(aVar, intExtra, intExtra2, stringExtra, true).show(getSupportFragmentManager(), "ExceptionDialog" + aVar.name());
                this.a++;
            }
        }
        if (this.a == 0) {
            finish();
        }
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        a(intent);
    }

    public void finish() {
        super.finish();
        this.a = 0;
        overridePendingTransition(0, 0);
    }

    public void a() {
        this.a--;
        if (this.a == 0) {
            finish();
        }
    }
}
