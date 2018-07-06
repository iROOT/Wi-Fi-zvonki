package com.mavenir.android.messaging.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.view.MenuItem;
import com.fgmicrotec.mobile.android.fgvoip.FgVoIP;
import com.mavenir.android.common.q;
import com.mavenir.android.common.t.b;
import com.mavenir.android.common.t.f;
import com.mavenir.android.messaging.b.e;
import com.mavenir.android.messaging.c.d;
import com.mavenir.android.messaging.provider.g;
import com.mavenir.android.vtow.activity.SplashScreenActivity;
import java.util.ArrayList;

public class ConversationActivity extends ActionBarActivity {
    private FragmentManager a;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.a = getSupportFragmentManager();
        if (FgVoIP.U().ax() || FgVoIP.U().ah()) {
            a(getIntent());
            return;
        }
        startActivity(new Intent(this, SplashScreenActivity.class));
        finish();
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (FgVoIP.U().ax() || FgVoIP.U().ah()) {
            a(intent);
            return;
        }
        startActivity(new Intent(this, SplashScreenActivity.class));
        finish();
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

    public void a(Intent intent) {
        Uri data = intent.getData();
        if (data == null || !("smsto".equals(data.getScheme()) || "sms".equals(data.getScheme()))) {
            Bundle bundle = new Bundle();
            if (!(intent == null || intent.getExtras() == null)) {
                bundle.putAll(intent.getExtras());
                getIntent().putExtras(intent.getExtras());
                setIntent(intent);
            }
            Fragment eVar = new e();
            eVar.setArguments(bundle);
            if (this.a.findFragmentById(16908290) != null) {
                this.a.beginTransaction().replace(16908290, eVar).commit();
                return;
            } else {
                this.a.beginTransaction().add(16908290, eVar).commit();
                return;
            }
        }
        a(data);
        finish();
    }

    private void a(Uri uri) {
        String schemeSpecificPart = uri.getSchemeSpecificPart();
        if (schemeSpecificPart != null) {
            Object a = f.a(schemeSpecificPart);
            if (!TextUtils.isEmpty(a)) {
                newMessage(this, a, null, false);
            }
        }
    }

    public static void newMessage(Context context, String str, String str2, boolean z) {
        if (str != null) {
            CharSequence str3 = str3.trim();
        }
        if (TextUtils.isEmpty(str3)) {
            q.d("ConversationActivity", "newMessage: empty number");
            return;
        }
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(str3);
        newMessage(context, arrayList, str2, null, z);
    }

    public static void newMessage(Context context, ArrayList<String> arrayList, String str, String str2, boolean z) {
        if (arrayList == null || arrayList.isEmpty()) {
            q.d("ConversationActivity", "newMessage: empty numbers");
        }
        Intent intent = new Intent(context, ConversationActivity.class);
        intent.setAction("ACTION_NEW_MESSAGE");
        intent.putExtra("extra_conversationId", -1);
        intent.putExtra("EXTRA_PHONE_NUMBERS", b.b(arrayList));
        if (str2 != null) {
            intent.putExtra("EXTRA_FILE_PATH", str2);
        } else if (!TextUtils.isEmpty(str)) {
            intent.putExtra("EXTRA_MESSAGE", str);
            intent.putExtra("EXTRA_DO_SEND", z);
        }
        context.startActivity(intent);
    }

    public static void a(Context context, long j, String str) {
        Intent intent = new Intent(context, ConversationActivity.class);
        intent.setAction("ACTION_FORWARD_MESSAGE");
        intent.putExtra("EXTRA_MESSAGE_ID", j);
        intent.putExtra("EXTRA_MESSAGE_TYPE", str);
        context.startActivity(intent);
    }

    public static void b(Context context, long j, String str) {
        d a = com.mavenir.android.messaging.provider.d.a(context, j);
        if (a != null) {
            q.a("ConversationActivity", "resendMessage(): moving SMS " + a.a() + " to queued folder");
            g.f.a(context, a.a(), 6, 0);
            Intent intent = new Intent();
            intent.setAction("com.fgmicrotec.mobile.android.voip.SendMessageReq");
            context.sendBroadcast(intent);
        }
    }
}
