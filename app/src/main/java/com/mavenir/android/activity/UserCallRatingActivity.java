package com.mavenir.android.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.TextView;
import com.fgmicrotec.mobile.android.fgvoip.f.k;
import com.fgmicrotec.mobile.android.fgvoipcommon.CallService;
import com.mavenir.android.common.CallManager;

public class UserCallRatingActivity extends Activity {
    AlertDialog a;
    String b;
    boolean c = false;
    String d;
    String e;
    int f;
    Handler g = new Handler();
    private Runnable h = new Runnable(this) {
        final /* synthetic */ UserCallRatingActivity a;

        {
            this.a = r1;
        }

        public void run() {
            this.a.a(-1);
            this.a.finish();
        }
    };

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        this.b = intent.getAction();
        if (this.b.compareTo("UserCallRatingDialogActions.CallRatingReq") == 0 && intent.hasExtra("UserCallRatingDialogExtras.ExtraRatingCallDuration") && intent.hasExtra("UserCallRatingDialogExtras.ExtraRatingNetworkBearer") && intent.hasExtra("UserCallRatingDialogExtras.ExtraRatingPhoneNumber")) {
            this.d = intent.getStringExtra("UserCallRatingDialogExtras.ExtraRatingPhoneNumber");
            this.e = intent.getStringExtra("UserCallRatingDialogExtras.ExtraRatingNetworkBearer");
            this.f = intent.getIntExtra("UserCallRatingDialogExtras.ExtraRatingCallDuration", 0);
        }
    }

    public void onStart() {
        super.onStart();
        a();
    }

    public void onStop() {
        this.a.dismiss();
        super.onStop();
    }

    public void onDestroy() {
        this.g.removeCallbacks(this.h);
        super.onDestroy();
    }

    private void a() {
        View linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(1);
        linearLayout.setLayoutParams(new LayoutParams(-2, -2));
        View textView = new TextView(this);
        textView.setText("Please rate the call quality");
        textView.setTextSize(2, 20.0f);
        textView.setPadding(10, 10, 10, 10);
        linearLayout.addView(textView);
        OnRatingBarChangeListener anonymousClass1 = new OnRatingBarChangeListener(this) {
            final /* synthetic */ UserCallRatingActivity a;

            {
                this.a = r1;
            }

            public void onRatingChanged(RatingBar ratingBar, float f, boolean z) {
                this.a.c = true;
            }
        };
        final View ratingBar = new RatingBar(this);
        ratingBar.setNumStars(5);
        ratingBar.setLayoutParams(new LayoutParams(-2, -2));
        ratingBar.setStepSize(1.0f);
        ratingBar.setOnRatingBarChangeListener(anonymousClass1);
        linearLayout.addView(ratingBar);
        Builder builder = new Builder(this);
        builder.setTitle("");
        builder.setCancelable(true);
        builder.setPositiveButton(k.dialog_ok, new OnClickListener(this) {
            final /* synthetic */ UserCallRatingActivity b;

            public void onClick(DialogInterface dialogInterface, int i) {
                if (this.b.c) {
                    CallManager.a(System.currentTimeMillis());
                    this.b.a((int) ratingBar.getRating());
                } else {
                    this.b.a(-1);
                }
                this.b.finish();
            }
        });
        builder.setNegativeButton(k.dialog_cancel, new OnClickListener(this) {
            final /* synthetic */ UserCallRatingActivity a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                this.a.a(-1);
                this.a.finish();
            }
        });
        this.g.postDelayed(this.h, 60000);
        linearLayout.setPadding(20, 20, 20, 20);
        builder.setView(linearLayout);
        this.a = builder.create();
        this.a.setOnDismissListener(new OnDismissListener(this) {
            final /* synthetic */ UserCallRatingActivity a;

            {
                this.a = r1;
            }

            public void onDismiss(DialogInterface dialogInterface) {
                this.a.finish();
            }
        });
        this.a.show();
    }

    private void a(int i) {
        Intent intent = new Intent(this, CallService.class);
        intent.setAction("UserCallRatingDialogActions.CallRatingRes");
        intent.putExtra("UserCallRatingDialogExtras.ExtraRatingCallDuration", this.f);
        intent.putExtra("UserCallRatingDialogExtras.ExtraRatingNetworkBearer", this.e);
        intent.putExtra("UserCallRatingDialogExtras.ExtraRatingPhoneNumber", this.d);
        intent.putExtra("UserCallRatingDialogExtras.ExtraRatingStars", i);
        startService(intent);
    }
}
