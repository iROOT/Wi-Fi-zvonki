package com.mavenir.android.fragments;

import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.k;
import android.support.v4.content.n;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.fgmicrotec.mobile.android.fgvoip.f;
import com.fgmicrotec.mobile.android.fgvoip.f.g;
import com.mavenir.android.a.h;
import com.mavenir.android.applog.AppLogAdapter.b;
import com.mavenir.android.applog.AppLogAdapter.c;
import com.mavenir.android.applog.AppLogAdapter.d;
import com.mavenir.android.applog.a;
import com.mavenir.android.common.q;
import com.mavenir.android.common.t;
import com.mavenir.android.settings.a.p;
import com.mavenir.android.settings.c.r;

public class i extends ListFragment implements LoaderCallbacks<Cursor>, OnClickListener {
    private boolean a = false;
    private TextView b;
    private h c;

    public /* synthetic */ void onLoadFinished(n nVar, Object obj) {
        a(nVar, (Cursor) obj);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        ViewGroup viewGroup2 = (ViewGroup) layoutInflater.inflate(f.h.qos_test_ui_activity, viewGroup, false);
        viewGroup2.addView(super.onCreateView(layoutInflater, viewGroup, bundle));
        return viewGroup2;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        q.a("PreferenceTestUiQosFragment", "onActivityCreated()");
        ((ActionBarActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setHasOptionsMenu(true);
        getListView().setItemsCanFocus(false);
        this.b = (TextView) getActivity().findViewById(g.ssidTextView);
        this.c = new h(getActivity());
        setListAdapter(this.c);
        getLoaderManager().initLoader(0, null, this);
    }

    public n<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new k(getActivity(), p.a, h.j, null, null, null);
    }

    public void a(n<Cursor> nVar, Cursor cursor) {
        if (this.c.d(cursor)) {
            this.a = true;
            if (this.b != null) {
                this.b.setVisibility(8);
            }
        }
        this.c.b(cursor);
        if (isResumed()) {
            setListShown(true);
        } else {
            setListShownNoAnimation(true);
        }
    }

    public void onLoaderReset(n<Cursor> nVar) {
        this.c.b(null);
    }

    public void onClick(View view) {
        view.getId();
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        super.onCreateOptionsMenu(menu, menuInflater);
        menuInflater.inflate(com.fgmicrotec.mobile.android.fgvoip.f.i.test_ui_qos_activity, menu);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == g.menu_qos_send) {
            a();
            return true;
        } else if (itemId != g.menu_qos_clear) {
            return super.onOptionsItemSelected(menuItem);
        } else {
            b();
            return true;
        }
    }

    private void a() {
        Builder builder = new Builder(getActivity());
        builder.setTitle(f.k.dialog_confirm_send_data);
        builder.setIcon(17301543);
        builder.setMessage(f.k.dialog_confirm_send_data_message);
        builder.setOnCancelListener(new OnCancelListener(this) {
            final /* synthetic */ i a;

            {
                this.a = r1;
            }

            public void onCancel(DialogInterface dialogInterface) {
            }
        });
        builder.setPositiveButton(f.k.dialog_ok, new DialogInterface.OnClickListener(this) {
            final /* synthetic */ i a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                a.a(this.a.getActivity()).a(b.FGAPPLOG_EVENT_GROUP_CALL_QOS_STATISTICS, d.FGAPPLOG_EVENT_TYPE_NONE, c.FGAPPLOG_EVENT_REASON_NONE, this.a.c());
            }
        });
        builder.setNegativeButton(f.k.dialog_cancel, new DialogInterface.OnClickListener(this) {
            final /* synthetic */ i a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        builder.create().show();
    }

    private void b() {
        Builder builder = new Builder(getActivity());
        builder.setTitle(f.k.dialog_confirm_delete_title);
        builder.setIcon(17301543);
        builder.setMessage(f.k.dialog_confirm_delete_qos_message);
        builder.setOnCancelListener(new OnCancelListener(this) {
            final /* synthetic */ i a;

            {
                this.a = r1;
            }

            public void onCancel(DialogInterface dialogInterface) {
            }
        });
        builder.setPositiveButton(f.k.dialog_ok, new DialogInterface.OnClickListener(this) {
            final /* synthetic */ i a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                r.a();
            }
        });
        builder.setNegativeButton(f.k.dialog_cancel, new DialogInterface.OnClickListener(this) {
            final /* synthetic */ i a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        builder.create().show();
    }

    private String c() {
        Cursor a = this.c.a();
        StringBuilder stringBuilder = new StringBuilder();
        String str = this.a ? "| %1$-4s | %2$-20s | %3$-15s | %4$-15s | %5$-15s |\n" : "| %1$-4s | %2$-20s | %3$-20s | %4$-15s | %5$-15s | %6$-15s |\n";
        String str2 = this.a ? "| %1$-25s timestamp: %2$5ss (%3$-8s) %4$-26s |\n" : "| %1$-37s timestamp: %2$5ss (%3$-8s) %4$-37s |\n";
        if (a != null) {
            String format;
            int i;
            a.moveToPosition(-1);
            if (this.a) {
                format = String.format(str, new Object[]{"N", "Round trip delay", "Jitter", "Cumulative loss", "Fractional loss"});
            } else {
                format = String.format(str, new Object[]{"N", "Wifi signal strength", "Round trip delay", "Jitter", "Cumulative loss", "Fractional loss"});
            }
            String format2 = String.format("%s", new Object[]{"|"});
            for (i = 0; i < format.length() - 3; i++) {
                format2 = format2 + String.format("%s", new Object[]{"-"});
            }
            format2 = format2 + String.format("%s\n", new Object[]{"|"});
            stringBuilder.append("QoS data\n");
            stringBuilder.append(format2);
            stringBuilder.append(format);
            stringBuilder.append(format2);
            int i2 = 0;
            while (a.moveToNext()) {
                String format3;
                i2++;
                i = a.getInt(a.getColumnIndex("wifi_signal_strength"));
                int i3 = a.getInt(a.getColumnIndex("round_trip_delay"));
                int i4 = a.getInt(a.getColumnIndex("jitter"));
                int i5 = a.getInt(a.getColumnIndex("cumulative_loss"));
                int i6 = a.getInt(a.getColumnIndex("fraction_loss"));
                String string = a.getString(a.getColumnIndex("timestamp"));
                String d = t.d.d(Long.valueOf(string).longValue());
                if (!string.equals("0")) {
                    format3 = String.format(str2, new Object[]{"", string, d, ""});
                } else if (this.a) {
                    format3 = String.format(str, new Object[]{Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4), Integer.valueOf(i5), Integer.valueOf(i6)});
                } else {
                    format3 = String.format(str, new Object[]{Integer.valueOf(i2), Integer.valueOf(i), Integer.valueOf(i3), Integer.valueOf(i4), Integer.valueOf(i5), Integer.valueOf(i6)});
                }
                stringBuilder.append(format3);
            }
            stringBuilder.append(format2);
        }
        return stringBuilder.toString();
    }
}
