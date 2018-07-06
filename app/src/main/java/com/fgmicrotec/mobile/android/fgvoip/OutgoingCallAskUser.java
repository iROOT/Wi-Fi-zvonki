package com.fgmicrotec.mobile.android.fgvoip;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;
import com.fgmicrotec.mobile.android.fgvoip.f.g;
import com.fgmicrotec.mobile.android.fgvoip.f.h;
import com.fgmicrotec.mobile.android.fgvoip.f.k;
import com.mavenir.android.settings.c;

public class OutgoingCallAskUser extends Activity {
    private static int d = 50;
    private AlertDialog a;
    private String b;
    private Handler c;
    private Runnable e = new Runnable(this) {
        final /* synthetic */ OutgoingCallAskUser a;

        {
            this.a = r1;
        }

        public void run() {
            FgVoIP.U().m(this.a.b);
        }
    };

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        Intent intent = getIntent();
        if (intent == null) {
            finish();
        } else if (intent.hasExtra("OutgoingCallAskUser.InternalIntents.ExtraNumberToCall")) {
            this.b = intent.getStringExtra("OutgoingCallAskUser.InternalIntents.ExtraNumberToCall");
            if (this.b == null || this.b.length() == 0) {
                finish();
            }
        } else {
            finish();
        }
        this.c = new Handler();
    }

    public void onStart() {
        b();
        super.onStart();
    }

    private void b() {
        Builder builder = new Builder(this);
        builder.setTitle(k.outgoing_call_complete_action_using);
        View inflate = getLayoutInflater().inflate(h.outgoing_call_complete_action_dialog, null);
        final TextView textView = (TextView) inflate.findViewById(g.Complete_TextView01);
        final TextView textView2 = (TextView) inflate.findViewById(g.Complete_TextView02);
        final CheckBox checkBox = (CheckBox) inflate.findViewById(g.Complete_CheckBox01);
        final TextView textView3 = (TextView) inflate.findViewById(g.Complete_TextView03);
        textView3.setVisibility(8);
        textView.setOnTouchListener(new OnTouchListener(this) {
            final /* synthetic */ OutgoingCallAskUser c;

            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == 0) {
                    textView.setBackgroundColor(-12285714);
                    if (checkBox.isChecked()) {
                        this.c.a(1);
                    }
                    this.c.c.postDelayed(this.c.e, (long) OutgoingCallAskUser.d);
                    this.c.finish();
                }
                return false;
            }
        });
        textView2.setOnTouchListener(new OnTouchListener(this) {
            final /* synthetic */ OutgoingCallAskUser c;

            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == 0) {
                    textView2.setBackgroundColor(-12285714);
                    if (checkBox.isChecked()) {
                        this.c.a(0);
                    }
                    FgVoIP.U().j(this.c.b);
                    this.c.finish();
                }
                return false;
            }
        });
        checkBox.setOnCheckedChangeListener(new OnCheckedChangeListener(this) {
            final /* synthetic */ OutgoingCallAskUser b;

            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    textView3.setVisibility(0);
                } else {
                    textView3.setVisibility(8);
                }
            }
        });
        builder.setView(inflate);
        builder.setOnCancelListener(new OnCancelListener(this) {
            final /* synthetic */ OutgoingCallAskUser a;

            {
                this.a = r1;
            }

            public void onCancel(DialogInterface dialogInterface) {
                this.a.finish();
            }
        });
        this.a = builder.create();
        this.a.show();
    }

    private void a(int i) {
        c.k.c(i);
    }

    public void onStop() {
        this.a.dismiss();
        super.onStop();
    }

    public void onDestroy() {
        this.c.removeCallbacks(this.e);
        super.onDestroy();
    }
}
