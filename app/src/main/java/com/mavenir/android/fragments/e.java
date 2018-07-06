package com.mavenir.android.fragments;

import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract.Contacts;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.n;
import android.support.v4.view.o;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;
import com.b.a.b.d;
import com.b.a.b.f.c;
import com.fgmicrotec.mobile.android.fgvoip.FgVoIP;
import com.fgmicrotec.mobile.android.fgvoip.f.g;
import com.fgmicrotec.mobile.android.fgvoip.f.i;
import com.mavenir.android.a.f;
import com.mavenir.android.activity.ContactDetailsActivity;
import com.mavenir.android.common.q;
import com.mavenir.android.common.u;
import com.mavenir.android.common.u.a;
import com.mavenir.android.settings.c.k;
import com.mavenir.android.view.DockedSearchView;
import com.mavenir.android.view.DockedSearchView.b;

public class e extends ListFragment implements LoaderCallbacks<Cursor>, OnClickListener, OnItemClickListener, a, DockedSearchView.a, b {
    static final String[] a = new String[]{"_id", "display_name", "starred", "times_contacted", "contact_presence", "photo_id", "lookup", "has_phone_number", "display_name_alt"};
    private f b;
    private DockedSearchView c;
    private String d;
    private MenuItem e = null;
    private MenuItem f = null;
    private int g = k.y();
    private int h = k.z();

    public /* synthetic */ void onLoadFinished(n nVar, Object obj) {
        a(nVar, (Cursor) obj);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        setHasOptionsMenu(true);
        OnScrollListener cVar = new c(d.a(), false, true);
        getListView().setFastScrollEnabled(true);
        getListView().setTextFilterEnabled(false);
        getListView().setOnItemClickListener(this);
        getListView().setDivider(null);
        getListView().setOnScrollListener(cVar);
        if (FgVoIP.U().av()) {
            setEmptyText(getResources().getString(com.fgmicrotec.mobile.android.fgvoip.f.k.contacts_empty_permission));
        } else {
            setEmptyText(getResources().getString(com.fgmicrotec.mobile.android.fgvoip.f.k.contacts_empty));
        }
        getLoaderManager().initLoader(0, null, this);
    }

    public boolean getUserVisibleHint() {
        return super.getUserVisibleHint();
    }

    public void onResume() {
        super.onResume();
        if (e()) {
            getLoaderManager().restartLoader(0, null, this);
        }
    }

    public void onStop() {
        if (this.c != null) {
            ((InputMethodManager) getActivity().getSystemService("input_method")).hideSoftInputFromWindow(this.c.getWindowToken(), 0);
        }
        a("");
        super.onStop();
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(i.contact_list_activity, menu);
        this.e = menu.findItem(g.menu_add_contact);
        super.onCreateOptionsMenu(menu, menuInflater);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == g.menu_search) {
            a(menuItem);
            return true;
        } else if (itemId != g.menu_add_contact) {
            return super.onOptionsItemSelected(menuItem);
        } else {
            c();
            return true;
        }
    }

    private void c() {
        FgVoIP.U().g(null);
    }

    public void onClick(View view) {
        int intValue = ((Integer) view.getTag()).intValue();
        Cursor cursor = null;
        if (this.b != null) {
            cursor = this.b.a();
            if (cursor != null) {
                cursor.moveToPosition(intValue);
            }
        }
        intValue = view.getId();
        if (intValue == g.call_button) {
            a(cursor);
        } else if (intValue == g.sms_button) {
            b(cursor);
        }
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        Cursor cursor = (Cursor) this.b.getItem(i);
        long j2 = cursor.getLong(cursor.getColumnIndex("_id"));
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setClass(getActivity(), ContactDetailsActivity.class);
        intent.setData(ContentUris.withAppendedId(Contacts.CONTENT_URI, j2));
        startActivity(intent);
    }

    public void a(String str, boolean z) {
        b(str, z);
    }

    public boolean a() {
        q.a("ContactsListFragment", "onSearchViewDetached()");
        if (this.e.isVisible()) {
            o.a(this.e, 2);
        }
        a("");
        return true;
    }

    public boolean b() {
        q.a("ContactsListFragment", "onSearchViewRequestClosing()");
        a("");
        d();
        return true;
    }

    public boolean a(String str) {
        String str2 = !TextUtils.isEmpty(str) ? str.toString() : null;
        if (this.d == null && str2 == null) {
            return false;
        }
        if (this.d != null && this.d.equals(str2)) {
            return false;
        }
        this.d = str2;
        getLoaderManager().restartLoader(0, null, this);
        return true;
    }

    public n<Cursor> onCreateLoader(int i, Bundle bundle) {
        Uri withAppendedPath;
        q.a("ContactsListFragment", "onCreateLoader(): cursor -> new");
        String str = "has_phone_number=?";
        String[] strArr = new String[]{"1"};
        if (this.d != null) {
            withAppendedPath = Uri.withAppendedPath(Contacts.CONTENT_FILTER_URI, Uri.encode(this.d));
        } else {
            withAppendedPath = Contacts.CONTENT_URI;
        }
        if (k.z() == 0) {
            return new android.support.v4.content.k(getActivity(), withAppendedPath, a, str, strArr, "display_name COLLATE LOCALIZED ASC");
        }
        return new android.support.v4.content.k(getActivity(), withAppendedPath, a, str, strArr, "sort_key_alt COLLATE LOCALIZED ASC");
    }

    public void a(n<Cursor> nVar, Cursor cursor) {
        this.b = new f(this, this);
        setListAdapter(this.b);
        this.b.b(cursor);
        if (isResumed()) {
            setListShownNoAnimation(true);
        } else {
            setListShownNoAnimation(true);
        }
    }

    public void onLoaderReset(n<Cursor> nVar) {
        this.b.b(null);
    }

    private boolean a(Cursor cursor) {
        return a(cursor, false);
    }

    private boolean b(Cursor cursor) {
        return a(cursor, true);
    }

    private boolean a(Cursor cursor, boolean z) {
        Object e;
        Throwable th;
        String str = null;
        if (cursor != null) {
            if (cursor.getInt(cursor.getColumnIndex("has_phone_number")) != 0) {
                Cursor a;
                try {
                    a = a(cursor.getLong(cursor.getColumnIndex("_id")));
                    if (a != null) {
                        try {
                            if (a.getCount() != 0) {
                                if (a.getCount() != 1) {
                                    a.moveToPosition(-1);
                                    while (a.moveToNext()) {
                                        if (a.getInt(a.getColumnIndex("is_super_primary")) != 0) {
                                            str = a.getString(a.getColumnIndex("data1"));
                                            break;
                                        }
                                    }
                                }
                                str = a.getString(a.getColumnIndex("data1"));
                                if (str == null) {
                                    new u(getActivity(), this, a, z).a();
                                } else {
                                    b(str, z);
                                }
                                if (!(a == null || a.isClosed())) {
                                    a.close();
                                }
                            }
                        } catch (Exception e2) {
                            e = e2;
                            try {
                                q.d("ContactsListFragment", "callOrSmsContact(): " + e);
                                if (!(a == null || a.isClosed())) {
                                    a.close();
                                }
                                return false;
                            } catch (Throwable th2) {
                                th = th2;
                                a.close();
                                throw th;
                            }
                        }
                    }
                    Toast.makeText(getActivity(), getString(com.fgmicrotec.mobile.android.fgvoip.f.k.call_invalid_number), 0).show();
                    if (!(a == null || a.isClosed())) {
                        a.close();
                    }
                } catch (Exception e3) {
                    e = e3;
                    a = null;
                    q.d("ContactsListFragment", "callOrSmsContact(): " + e);
                    a.close();
                    return false;
                } catch (Throwable th3) {
                    th = th3;
                    a = null;
                    if (!(a == null || a.isClosed())) {
                        a.close();
                    }
                    throw th;
                }
            }
            Toast.makeText(getActivity(), getString(com.fgmicrotec.mobile.android.fgvoip.f.k.call_invalid_number), 0).show();
        }
        return false;
    }

    private void b(String str, boolean z) {
        if (z) {
            c(str);
        } else {
            b(str);
        }
    }

    private void b(String str) {
        c(str, false);
    }

    private void c(String str, boolean z) {
        FgVoIP.U().j(str);
    }

    private void c(String str) {
        com.mavenir.android.activity.a.a(getActivity(), str, null, false);
    }

    private Cursor a(long j) {
        Uri withAppendedPath = Uri.withAppendedPath(ContentUris.withAppendedId(Contacts.CONTENT_URI, j), "data");
        Cursor query = getActivity().getContentResolver().query(withAppendedPath, new String[]{"_id", "data1", "is_super_primary"}, "mimetype=?", new String[]{"vnd.android.cursor.item/phone_v2"}, null);
        if (query == null || !query.moveToFirst()) {
            return null;
        }
        return query;
    }

    private void a(MenuItem menuItem) {
        this.c = new DockedSearchView(getActivity());
        this.c.setSearchHint(getActivity().getResources().getString(com.fgmicrotec.mobile.android.fgvoip.f.k.hint_search_contacts));
        this.c.setOnSearchTextListener(this);
        this.c.setOnSearchViewDetachedListener(this);
        if (this.e.isVisible()) {
            o.a(this.e, 0);
        }
        o.a(menuItem, this.c);
        o.a(menuItem, 2);
        this.f = menuItem;
    }

    private void d() {
        if (this.f.isVisible()) {
            o.a(this.f, null);
            o.a(this.f, 2);
        }
    }

    public void a(View view, Cursor cursor) {
    }

    private boolean e() {
        int y = k.y();
        int z = k.z();
        if (this.g == y && this.h == z) {
            return false;
        }
        this.g = y;
        this.h = z;
        return true;
    }
}
