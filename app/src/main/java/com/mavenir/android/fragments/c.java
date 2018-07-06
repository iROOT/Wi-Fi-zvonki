package com.mavenir.android.fragments;

import android.app.AlertDialog.Builder;
import android.content.BroadcastReceiver;
import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import android.provider.CallLog.Calls;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.k;
import android.support.v4.content.n;
import android.support.v4.content.o;
import android.text.TextUtils;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import com.fgmicrotec.mobile.android.fgvoip.FgVoIP;
import com.fgmicrotec.mobile.android.fgvoip.e;
import com.fgmicrotec.mobile.android.fgvoip.f;
import com.fgmicrotec.mobile.android.fgvoip.f.g;
import com.fgmicrotec.mobile.android.fgvoip.f.h;
import com.fgmicrotec.mobile.android.fgvoip.f.i;
import com.mavenir.android.activity.CallDetailsActivity;
import com.mavenir.android.activity.DialerActivity;
import com.mavenir.android.common.q;
import com.mavenir.android.common.t;
import java.util.ArrayList;

public class c extends ListFragment implements LoaderCallbacks<Cursor>, OnClickListener, OnItemClickListener {
    private static final String[] j = new String[]{"_id", "type", "name", "number", "numbertype", "numberlabel", "date", "voicemail_uri"};
    private com.mavenir.android.a.c a;
    private a b;
    private com.mavenir.android.common.c c;
    private RadioGroup d;
    private RadioButton e;
    private RadioButton f;
    private boolean g = false;
    private String h = null;
    private boolean i = false;

    private class a extends BroadcastReceiver {
        final /* synthetic */ c a;

        private a(c cVar) {
            this.a = cVar;
        }

        /* synthetic */ a(c cVar, AnonymousClass1 anonymousClass1) {
            this(cVar);
        }

        public void onReceive(Context context, Intent intent) {
            if (intent != null && intent.getAction() != null && intent.getAction().equals("DialerActivity.ACTION_SEARCH")) {
                String stringExtra = intent.getStringExtra("DialerActivity.EXTRA_SEARCH_STRING");
                if (stringExtra == null || stringExtra.length() <= 0) {
                    this.a.h = null;
                } else {
                    this.a.h = stringExtra;
                }
                this.a.h();
                this.a.getLoaderManager().restartLoader(0, null, this.a);
            }
        }
    }

    public /* synthetic */ void onLoadFinished(n nVar, Object obj) {
        a(nVar, (Cursor) obj);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        ViewGroup viewGroup2 = (ViewGroup) layoutInflater.inflate(h.call_log_listview_activity, viewGroup, false);
        viewGroup2.addView(super.onCreateView(layoutInflater, viewGroup, bundle));
        return viewGroup2;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        q.a("CallLogListFragment", "onActivityCreated");
        setHasOptionsMenu(true);
        a();
        setListShown(false);
        g();
        getLoaderManager().initLoader(0, null, this);
    }

    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        q.a("CallLogListFragment", "setUserVisibleHint(): " + z);
        if (this.a == null) {
            return;
        }
        if (z) {
            d();
            this.a.f();
            this.a.d();
            this.a.notifyDataSetChanged();
            return;
        }
        this.a.e();
    }

    public void onResume() {
        super.onResume();
        q.a("CallLogListFragment", "onResume()");
        d();
        if (this.a != null) {
            this.a.f();
            this.a.d();
            this.a.notifyDataSetChanged();
        }
    }

    public void onPause() {
        super.onPause();
        q.a("CallLogListFragment", "onPause");
        this.a.e();
    }

    public void onDestroy() {
        super.onDestroy();
        q.a("CallLogListFragment", "onDestroy");
        this.a.e();
        o.a(getActivity()).a(this.b);
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        try {
            long j2;
            Cursor cursor = (Cursor) this.a.getItem(i);
            long j3 = cursor.getLong(cursor.getColumnIndex("_id"));
            String string = cursor.getString(cursor.getColumnIndex("name"));
            String string2 = cursor.getString(cursor.getColumnIndex("number"));
            String charSequence = Phone.getTypeLabel(getResources(), cursor.getInt(cursor.getColumnIndex("numbertype")), cursor.getString(cursor.getColumnIndex("numberlabel"))).toString();
            int i2 = cursor.getInt(cursor.getColumnIndex("log_count"));
            long j4 = cursor.getLong(cursor.getColumnIndex("date"));
            if (cursor.moveToNext()) {
                j2 = cursor.getLong(cursor.getColumnIndex("date"));
            } else {
                j2 = -1;
            }
            Intent intent = new Intent(getActivity(), CallDetailsActivity.class);
            intent.putExtra("ExtraLogId", j3);
            intent.putExtra("ExtraName", string);
            intent.putExtra("ExtraNumber", string2);
            intent.putExtra("ExtraType", charSequence);
            intent.putExtra("ExtraCount", i2);
            intent.putExtra("ExtraStartDate", j4);
            intent.putExtra("ExtraEndDate", j2);
            intent.putExtra("ExtraMissedOnly", this.g);
            startActivity(intent);
        } catch (Exception e) {
            q.c("CallLogListFragment", e.getLocalizedMessage(), e.getCause());
        }
    }

    public n<Cursor> onCreateLoader(int i, Bundle bundle) {
        String str;
        String str2;
        String[] strArr;
        String[] strArr2 = null;
        Uri uri = Calls.CONTENT_URI;
        if (this.g) {
            str = "type=?";
            strArr2 = new String[]{String.valueOf(3)};
        } else {
            str = null;
        }
        if (this.h != null) {
            str2 = "number LIKE ?";
            strArr = new String[]{"%" + this.h + "%"};
        } else {
            strArr = strArr2;
            str2 = str;
        }
        return new k(getActivity(), uri, j, str2, strArr, "date DESC");
    }

    public void a(n<Cursor> nVar, Cursor cursor) {
        this.a.b(cursor);
        if (isResumed()) {
            setListShown(true);
        } else {
            setListShownNoAnimation(true);
        }
    }

    public void onLoaderReset(n<Cursor> nVar) {
        q.a("CallLogListFragment", "onLoadReset(): cursor -> null");
        this.a.b(null);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(i.call_log_activity, menu);
        super.onCreateOptionsMenu(menu, menuInflater);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == g.menu_create_conference) {
            b();
            return true;
        } else if (itemId == g.menu_clear) {
            c();
            return true;
        } else if (itemId != g.menu_open_dialer) {
            return super.onOptionsItemSelected(menuItem);
        } else {
            f();
            return true;
        }
    }

    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenuInfo contextMenuInfo) {
        switch (view.getId()) {
            case 16908298:
                getActivity().getMenuInflater().inflate(i.call_log_context, contextMenu);
                contextMenu.setHeaderTitle(f.k.menu_calllog_context_title);
                break;
        }
        super.onCreateContextMenu(contextMenu, view, contextMenuInfo);
    }

    public boolean onContextItemSelected(MenuItem menuItem) {
        int i = ((AdapterContextMenuInfo) menuItem.getMenuInfo()).position;
        int itemId = menuItem.getItemId();
        if (itemId == g.menu_call) {
            a(i);
        } else if (itemId == g.menu_sms) {
            b(i);
        } else if (itemId == g.menu_delete) {
            d(i);
        }
        return super.onContextItemSelected(menuItem);
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == g.call_log_all_radio) {
            this.g = false;
            getLoaderManager().restartLoader(0, null, this);
        } else if (id == g.call_log_missed_radio) {
            this.g = true;
            getLoaderManager().restartLoader(0, null, this);
        } else if (id == g.call_button) {
            a(((Integer) view.getTag()).intValue());
        } else if (id == g.play_button) {
            c(((Integer) view.getTag()).intValue());
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        q.a("CallLogListFragment", "onActivityResult(): requestCode=" + i + ", resultCode=" + i2 + ", data=" + intent);
        if (i == 100 && i2 == -1) {
            ArrayList stringArrayListExtra = intent.getStringArrayListExtra("ContactsSelectionActivity.EXTRA_NUMBERS");
            if (stringArrayListExtra != null && stringArrayListExtra.size() > 1) {
                FgVoIP.U().a(stringArrayListExtra);
            } else if (stringArrayListExtra != null && stringArrayListExtra.size() == 1) {
                FgVoIP.U().j((String) stringArrayListExtra.get(0));
            }
        }
    }

    private void a() {
        this.a = new com.mavenir.android.a.c(getActivity(), this);
        setListAdapter(this.a);
        getListView().setFastScrollEnabled(false);
        getListView().setTextFilterEnabled(false);
        getListView().setOnItemClickListener(this);
        registerForContextMenu(getListView());
        if (FgVoIP.U().av()) {
            setEmptyText(getResources().getString(f.k.call_log_empty_permission));
        } else {
            setEmptyText(getResources().getString(f.k.call_log_empty));
        }
        this.d = (RadioGroup) getActivity().findViewById(g.filterRadioGroup);
        this.e = (RadioButton) getActivity().findViewById(g.call_log_all_radio);
        this.e.setOnClickListener(this);
        this.f = (RadioButton) getActivity().findViewById(g.call_log_missed_radio);
        this.f.setOnClickListener(this);
    }

    private void a(int i) {
        String b = this.a.b(i);
        if (TextUtils.isEmpty(b) || t.f.j(b)) {
            Toast.makeText(getActivity(), getString(f.k.call_invalid_number), 0).show();
        } else {
            FgVoIP.U().j(b);
        }
    }

    private void b(int i) {
        String b = this.a.b(i);
        if (TextUtils.isEmpty(b) || t.f.j(b)) {
            Toast.makeText(getActivity(), getString(f.k.call_invalid_number), 0).show();
        } else {
            com.mavenir.android.activity.a.a(getActivity(), b, null, false);
        }
    }

    private void c(int i) {
        Object c = this.a.c(i);
        if (!TextUtils.isEmpty(c)) {
            if (this.c == null) {
                this.c = new com.mavenir.android.common.c();
            }
            if (this.c != null) {
                this.c.a(c, 3);
            }
        }
    }

    private void b() {
        startActivityForResult(FgVoIP.U().aa(), 100);
    }

    private void c() {
        Builder builder = new Builder(getActivity());
        builder.setTitle(f.k.dialog_confirm_delete_title);
        builder.setIcon(17301543);
        builder.setMessage(f.k.dialog_confirm_clear_log_message);
        builder.setPositiveButton(f.k.dialog_ok, new DialogInterface.OnClickListener(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                this.a.e();
            }
        });
        builder.setNegativeButton(f.k.dialog_cancel, new DialogInterface.OnClickListener(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        builder.create().show();
    }

    private void d(final int i) {
        Builder builder = new Builder(getActivity());
        builder.setTitle(f.k.dialog_confirm_delete_title);
        builder.setIcon(17301543);
        builder.setMessage(f.k.dialog_confirm_delete_log_message);
        builder.setPositiveButton(f.k.dialog_ok, new DialogInterface.OnClickListener(this) {
            final /* synthetic */ c b;

            public void onClick(DialogInterface dialogInterface, int i) {
                this.b.e(i);
            }
        });
        builder.setNegativeButton(f.k.dialog_cancel, new DialogInterface.OnClickListener(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        builder.create().show();
    }

    private void d() {
        if (!this.i && getUserVisibleHint()) {
            new Thread(new Runnable(this) {
                final /* synthetic */ c a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.i = true;
                    ContentResolver contentResolver = this.a.getActivity().getContentResolver();
                    try {
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("new", Integer.valueOf(0));
                        q.a("CallLogListFragment", "markLogsAsRead(): updated " + contentResolver.update(Calls.CONTENT_URI, contentValues, "new=?", new String[]{"1"}) + " new logs");
                    } catch (Exception e) {
                        q.d("CallLogListFragment", "markLogsAsRead(): " + e);
                    }
                    e.a().h();
                    this.a.i = false;
                }
            }).start();
        }
    }

    private void e() {
        try {
            getActivity().getContentResolver().delete(Calls.CONTENT_URI, null, null);
        } catch (Exception e) {
            q.d("CallLogListFragment", "clearCallLog(): " + e);
        }
    }

    private void e(int i) {
        String a = this.a.a(i);
        Uri uri = Calls.CONTENT_URI;
        if (a != null) {
            ArrayList arrayList = new ArrayList();
            String[] split = a.split(",");
            for (int i2 = 0; i2 < split.length; i2++) {
                arrayList.add(ContentProviderOperation.newDelete(uri).withSelection("_id=?", new String[]{String.valueOf(split[i2])}).build());
            }
            try {
                getActivity().getContentResolver().applyBatch("call_log", arrayList);
            } catch (RemoteException e) {
                e.printStackTrace();
            } catch (OperationApplicationException e2) {
                e2.printStackTrace();
            }
        }
    }

    private void f() {
        startActivityForResult(new Intent(getActivity(), DialerActivity.class), 10);
    }

    private void g() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("DialerActivity.ACTION_SEARCH");
        this.b = new a();
        o.a(getActivity()).a(this.b, intentFilter);
    }

    private void h() {
        if (this.h == null || this.h.length() <= 0) {
            this.d.setVisibility(0);
        } else {
            this.d.setVisibility(8);
        }
    }
}
