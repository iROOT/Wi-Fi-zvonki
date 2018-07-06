package com.mavenir.android.apps.tele2;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.fgmicrotec.mobile.android.fgvoip.FgVoIP;
import com.fgmicrotec.mobile.android.fgvoip.f.g;
import com.fgmicrotec.mobile.android.fgvoip.f.h;
import com.fgmicrotec.mobile.android.fgvoip.f.k;
import com.mavenir.android.activity.ActivationActivity;
import com.mavenir.android.activity.PreferenceMainActivity;
import com.mavenir.android.common.q;
import com.mavenir.android.settings.c;

public class ActivationInitialActivity extends ActionBarActivity implements OnClickListener, OnTouchListener {
    private Handler a;
    private CheckBox b;
    private TextView c;
    private Button d;
    private View e;
    private Runnable f = new Runnable(this) {
        final /* synthetic */ ActivationInitialActivity a;

        {
            this.a = r1;
        }

        public void run() {
            this.a.c();
        }
    };

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.a = new Handler();
        a();
    }

    protected void onDestroy() {
        super.onDestroy();
    }

    protected void onResume() {
        super.onResume();
        FgVoIP.U().a((Activity) this);
    }

    public void onBackPressed() {
        q.a("ActivationInitialActivity", "onBackPressed(): User requested app exit");
        FgVoIP.U().d();
        super.onBackPressed();
    }

    public void onClick(View view) {
        if (view.getId() == g.activationActivateButton && b()) {
            Intent intent;
            if (TextUtils.isEmpty(c.q.u()) && TextUtils.isEmpty(c.q.v())) {
                intent = new Intent(this, ActivationActivity.class);
                intent.putExtra("com.mavenir.android.activity.ActivationActivity.ExtraAutoProvisioning", true);
            } else {
                intent = new Intent(this, com.mavenir.android.vtow.activity.ActivationInitialActivity.class);
                intent.putExtra("com.mavenir.android.vtow.activity.ActivationInitialActivity.ExtraAutoProvisioning", true);
            }
            startActivity(intent);
            finish();
        }
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (view.getId() == g.activationEngineeringMode) {
            switch (motionEvent.getActionMasked()) {
                case 0:
                    this.a.postDelayed(this.f, 4000);
                    return true;
                case 1:
                case 3:
                case 4:
                    this.a.removeCallbacks(this.f);
                    return true;
            }
        }
        return false;
    }

    private void a() {
        super.setContentView(h.activation_initial_activity);
        this.b = (CheckBox) findViewById(g.activationTCCheckBox);
        this.c = (TextView) findViewById(g.activationTCTextview);
        this.d = (Button) findViewById(g.activationActivateButton);
        this.d.setOnClickListener(this);
        this.c.setMovementMethod(LinkMovementMethod.getInstance());
        this.e = findViewById(g.activationEngineeringMode);
        this.e.setOnTouchListener(this);
        TextView textView = (TextView) findViewById(g.activationMotoTextView);
        if (textView != null) {
            textView.setMovementMethod(LinkMovementMethod.getInstance());
        }
    }

    private boolean b() {
        if (this.b.isChecked()) {
            return true;
        }
        Toast.makeText(this, getString(k.activation_initial_terms_accept), 0).show();
        return false;
    }

    private void c() {
        final View editText = new EditText(this);
        editText.setInputType(3);
        editText.setSingleLine();
        AlertDialog create = new Builder(this).setTitle(k.activation_enter_engineering_code).setView(editText).setPositiveButton(k.dialog_ok, new DialogInterface.OnClickListener(this) {
            final /* synthetic */ ActivationInitialActivity b;

            public void onClick(DialogInterface dialogInterface, int i) {
                this.b.a(editText.getText().toString());
            }
        }).setNegativeButton(k.dialog_cancel, new DialogInterface.OnClickListener(this) {
            final /* synthetic */ ActivationInitialActivity a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
            }
        }).create();
        create.show();
        create.getWindow().clearFlags(131080);
        create.getWindow().setSoftInputMode(5);
    }

    private void a(String str) {
        FgVoIP U = FgVoIP.U();
        if (U.d(str)) {
            U.a(true);
            TaskStackBuilder.create(this).addNextIntentWithParentStack(new Intent(this, PreferenceMainActivity.class)).startActivities();
            finish();
        }
    }
}
