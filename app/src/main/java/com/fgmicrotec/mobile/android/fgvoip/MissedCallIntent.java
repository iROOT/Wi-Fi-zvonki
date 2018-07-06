package com.fgmicrotec.mobile.android.fgvoip;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class MissedCallIntent extends Activity {
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        e.a().h();
        Intent intent = new Intent("android.intent.action.VIEW", null);
        intent.setFlags(268435456);
        intent.setType("vnd.android.cursor.dir/calls");
        startActivity(intent);
        finish();
    }
}
