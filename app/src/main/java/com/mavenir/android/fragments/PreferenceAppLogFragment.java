package com.mavenir.android.fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.MatrixCursor;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.content.o;
import android.support.v4.widget.n;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import com.fgmicrotec.mobile.android.fgvoip.f.g;
import com.fgmicrotec.mobile.android.fgvoip.f.h;
import com.fgmicrotec.mobile.android.fgvoip.f.k;
import com.mavenir.android.applog.AppLogAdapter;
import com.mavenir.android.applog.a;
import com.mavenir.android.common.q;

public class PreferenceAppLogFragment extends ListFragment {
    private AppLogBroadcastReceiver a;
    private n b;
    private MatrixCursor c;

    public class AppLogBroadcastReceiver extends BroadcastReceiver {
        final /* synthetic */ PreferenceAppLogFragment a;

        public AppLogBroadcastReceiver(PreferenceAppLogFragment preferenceAppLogFragment) {
            this.a = preferenceAppLogFragment;
        }

        public void onReceive(Context context, Intent intent) {
            if (intent != null && intent.getAction() != null && intent.getAction().equals(AppLogAdapter.ACTION_EVENT_LIST_CNF)) {
                this.a.a(intent.getIntExtra(AppLogAdapter.EXTRA_EVENT_LIST_SIZE, 0), intent.getStringArrayExtra(AppLogAdapter.EXTRA_EVENT_LIST));
            }
        }
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        q.a("PreferenceAppLogFragment", "onActivityCreated()");
        ActionBar supportActionBar = ((ActionBarActivity) getActivity()).getSupportActionBar();
        supportActionBar.setDisplayHomeAsUpEnabled(true);
        supportActionBar.setTitle(k.preference_dev_app_log_title);
        a();
        b();
        c();
    }

    private void a() {
        this.b = new n(getActivity(), h.app_log_list_item, this.c, new String[]{getString(k.preference_dev_app_log_column_time), getString(k.preference_dev_app_log_column_event)}, new int[]{g.appLogTimestampTextView, g.appLogMessageTextView}, 0);
        setListAdapter(this.b);
        setListShown(false);
        setEmptyText(getString(k.preference_dev_app_log_empty));
    }

    public void onDestroy() {
        super.onDestroy();
        o.a(getActivity()).a(this.a);
        if (this.c != null) {
            this.c.close();
            this.c = null;
        }
    }

    private void b() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(AppLogAdapter.ACTION_EVENT_LIST_CNF);
        this.a = new AppLogBroadcastReceiver(this);
        o.a(getActivity()).a(this.a, intentFilter);
    }

    private void c() {
        new Thread(new Runnable(this) {
            final /* synthetic */ PreferenceAppLogFragment a;

            {
                this.a = r1;
            }

            public void run() {
                a.a(this.a.getActivity()).i();
            }
        }).start();
    }

    private void a(int i, String[] strArr) {
        this.c = new MatrixCursor(new String[]{"_id", getString(k.preference_dev_app_log_column_time), getString(k.preference_dev_app_log_column_event)});
        int length = strArr.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            String str = strArr[i2];
            int i4 = i3 + 1;
            long j = (long) i4;
            i3 = str.indexOf(10);
            if (i3 < 0) {
                i3 = 0;
            }
            String substring = str.substring(0, i3);
            this.c.newRow().add(Long.valueOf(j)).add(substring).add(str.substring(i3 + 1));
            i2++;
            i3 = i4;
        }
        this.b.b(this.c);
        setListShown(true);
    }
}
