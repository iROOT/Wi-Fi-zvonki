package com.mavenir.android.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.fgmicrotec.mobile.android.fgmag.SimpleCodecAL;
import com.fgmicrotec.mobile.android.fgvoip.f.k;
import com.fgmicrotec.mobile.android.fgvoipcommon.CallService;

public class USSIDialogActivity extends Activity {
    AlertDialog a;
    String b;
    String c;
    int d;
    int e;
    Handler f = new Handler();
    CallServiceIntentsReceiver g;
    private ProgressDialog h = null;
    private boolean i = false;
    private Runnable j = new Runnable(this) {
        final /* synthetic */ USSIDialogActivity a;

        {
            this.a = r1;
        }

        public void run() {
            Log.d("USSIDialogActivity", "handleCloseOnTimeout task");
            this.a.finish();
        }
    };

    public class CallServiceIntentsReceiver extends BroadcastReceiver {
        final /* synthetic */ USSIDialogActivity a;

        public CallServiceIntentsReceiver(USSIDialogActivity uSSIDialogActivity) {
            this.a = uSSIDialogActivity;
        }

        public void onReceive(Context context, Intent intent) {
            if (intent != null && intent.getAction() != null) {
                Log.d("USSIDialogActivity", "CallServiceIntentsReceiver: onReceive() " + intent.getAction());
                if ("CallManager.ActionBlockClosing".equals(intent.getAction())) {
                    this.a.d();
                    this.a.finish();
                }
            }
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Log.d("USSIDialogActivity", "onCreate");
        e();
        SimpleCodecAL.getInstance().setContext(this);
        a(getIntent());
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d("USSIDialogActivity", "onNewIntent");
        a(intent);
        b(intent);
    }

    public void onStart() {
        super.onStart();
        Log.d("USSIDialogActivity", "onStart");
        b(getIntent());
    }

    private void a(Intent intent) {
        this.b = intent.getAction();
        if (this.b.compareTo("USSIDialogActions.StringReceivedInd") == 0) {
            Log.d("USSIDialogActivity", "resolveIntent START_SENDING_USSD_MESSAGE" + this.i);
            if (intent.hasExtra("USSIDialogExtras.ExtraUSSIString") && intent.hasExtra("USSIDialogExtras.ExtraUSSIResultCode") && intent.hasExtra("USSIDialogExtras.ExtraUSSIResponseExpected")) {
                this.c = intent.getStringExtra("USSIDialogExtras.ExtraUSSIString");
                this.d = intent.getIntExtra("USSIDialogExtras.ExtraUSSIResultCode", 0);
                this.e = intent.getIntExtra("USSIDialogExtras.ExtraUSSIResponseExpected", 0);
            }
            if (intent.hasExtra("USSIDialogExtras.ExtraSENT")) {
                this.i = intent.getBooleanExtra("USSIDialogExtras.ExtraSENT", false);
                Log.d("USSIDialogActivity", "resolveIntent START_SENDING_USSD_MESSAGE" + this.i);
            }
        }
    }

    private void b(Intent intent) {
        Log.d("USSIDialogActivity", "prepareIntentaction() START_SENDING_USSD_MESSAGE" + this.i);
        if (this.b.compareTo("USSIDialogActions.StringReceivedInd") != 0) {
            finish();
        } else if (intent.hasExtra("USSIDialogExtras.ExtraSENT")) {
            this.i = intent.getBooleanExtra("USSIDialogExtras.ExtraSENT", false);
            Log.d("USSIDialogActivity", "prepareIntentaction()  hasExtra(ActivityIntents.USSIDialogExtras.EXTRA_USSI_STRING_SENT_START)" + this.i);
            if (this.i) {
                c();
            } else {
                d();
            }
        } else {
            d();
            a();
            b();
        }
    }

    private void a() {
        if (this.a != null) {
            this.a.dismiss();
        }
    }

    protected void onResume() {
        Log.d("USSIDialogActivity", "onResume");
        super.onResume();
    }

    public void onStop() {
        d();
        a();
        super.onStop();
    }

    public void onDestroy() {
        Log.d("USSIDialogActivity", "onDestroy");
        unregisterReceiver(this.g);
        this.f.removeCallbacks(this.j);
        SimpleCodecAL.getInstance().setContext(null);
        super.onDestroy();
    }

    private void b() {
        Log.d("USSIDialogActivity", "displayDialogWithServerMessage()" + this.i);
        View linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(1);
        View textView = new TextView(this);
        textView.setText(this.c);
        textView.setTextSize(2, 20.0f);
        textView.setPadding(10, 10, 10, 10);
        linearLayout.addView(textView);
        Builder builder = new Builder(this);
        builder.setTitle("");
        builder.setCancelable(true);
        if (this.e == 1) {
            final View editText = new EditText(this);
            editText.setPadding(10, 10, 10, 10);
            linearLayout.addView(editText);
            builder.setPositiveButton(k.dialog_ok, new OnClickListener(this) {
                final /* synthetic */ USSIDialogActivity b;

                public void onClick(DialogInterface dialogInterface, int i) {
                    if (!TextUtils.isEmpty(editText.getText().toString())) {
                        this.b.a(editText.getText().toString());
                    }
                }
            });
        } else {
            this.f.postDelayed(this.j, 10000);
        }
        linearLayout.setPadding(20, 20, 20, 20);
        builder.setView(linearLayout);
        this.a = builder.create();
        this.a.setOnDismissListener(new OnDismissListener(this) {
            final /* synthetic */ USSIDialogActivity a;

            {
                this.a = r1;
            }

            public void onDismiss(DialogInterface dialogInterface) {
                Log.d("USSIDialogActivity", "onDismiss displayDialogWithServerMessage");
                this.a.finish();
            }
        });
        this.a.setOnCancelListener(new OnCancelListener(this) {
            final /* synthetic */ USSIDialogActivity a;

            {
                this.a = r1;
            }

            public void onCancel(DialogInterface dialogInterface) {
                this.a.f.removeCallbacks(this.a.j);
                Log.d("USSIDialogActivity", "OnCancel, displayDialogWithServerMessage");
                this.a.finish();
            }
        });
        this.a.show();
    }

    private void a(String str) {
        Intent intent = new Intent(this, CallService.class);
        intent.setAction("USSIDialogActions.StringReceivedRes");
        intent.putExtra("USSIDialogExtras.ExtraUSSIString", str);
        startService(intent);
        c();
    }

    private void c() {
        d();
        a();
        Log.d("USSIDialogActivity", "showProgressDialog START_SENDING_USSD_MESSAGE" + this.i);
        this.h = new ProgressDialog(this);
        this.h.setCanceledOnTouchOutside(false);
        this.h.setCancelable(false);
        this.h.setTitle(null);
        this.h.setMessage(getString(k.dialog_ussd_processing_message));
        this.h.setIndeterminate(true);
        this.h.show();
        final Handler handler = new Handler();
        final Runnable anonymousClass5 = new Runnable(this) {
            final /* synthetic */ USSIDialogActivity a;

            {
                this.a = r1;
            }

            public void run() {
                if (this.a.h != null && this.a.h.isShowing()) {
                    this.a.h.dismiss();
                    this.a.finish();
                }
            }
        };
        this.h.setOnCancelListener(new OnCancelListener(this) {
            final /* synthetic */ USSIDialogActivity c;

            public void onCancel(DialogInterface dialogInterface) {
                handler.removeCallbacks(anonymousClass5);
                Log.d("USSIDialogActivity", "OnCancel");
                this.c.finish();
            }
        });
        handler.postDelayed(anonymousClass5, 30000);
    }

    private void d() {
        if (this.h != null) {
            this.h.dismiss();
            this.h = null;
        }
    }

    private void e() {
        this.g = new CallServiceIntentsReceiver(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("CallManager.ActionBlockClosing");
        registerReceiver(this.g, intentFilter);
    }
}
