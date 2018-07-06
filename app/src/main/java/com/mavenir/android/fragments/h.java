package com.mavenir.android.fragments;

import android.annotation.SuppressLint;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import com.fgmicrotec.mobile.android.fgvoip.f.g;
import com.fgmicrotec.mobile.android.fgvoip.f.i;
import com.fgmicrotec.mobile.android.fgvoip.f.k;
import com.mavenir.android.common.t.b;
import com.mavenir.android.settings.c.l;
import com.mavenir.android.settings.c.v;
import java.util.ArrayList;

public class h extends ListFragment {
    private int a = 0;
    private ArrayList<String> b;
    private ArrayAdapter<String> c;

    public enum a {
        WIFI,
        LTE
    }

    public static h a(int i) {
        h hVar = new h();
        Bundle bundle = new Bundle(1);
        bundle.putInt("ARG_SBC_LIST_TYPE", i);
        hVar.setArguments(bundle);
        return hVar;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (getArguments() != null) {
            this.a = getArguments().getInt("ARG_SBC_LIST_TYPE");
        }
        ActionBar supportActionBar = ((ActionBarActivity) getActivity()).getSupportActionBar();
        supportActionBar.setDisplayHomeAsUpEnabled(true);
        if (this.a == a.LTE.ordinal()) {
            supportActionBar.setTitle(k.preference_lte_sbc_category);
        } else {
            supportActionBar.setTitle(k.preference_sip_sbc_category);
        }
        setHasOptionsMenu(true);
        registerForContextMenu(getListView());
        this.c = new ArrayAdapter(getActivity(), 17367043);
        setEmptyText(getString(k.preference_lte_sbc_list_empty));
        getListView().setItemsCanFocus(false);
        getListView().setChoiceMode(1);
        getListView().setAdapter(this.c);
        a();
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(i.sbc_list_activity, menu);
        super.onCreateOptionsMenu(menu, menuInflater);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == g.menu_new_sbc) {
            c();
            return true;
        } else if (itemId != g.menu_clear_sbc) {
            return super.onOptionsItemSelected(menuItem);
        } else {
            d();
            return true;
        }
    }

    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenuInfo contextMenuInfo) {
        switch (view.getId()) {
            case 16908298:
                getActivity().getMenuInflater().inflate(i.sbc_list_context, contextMenu);
                break;
        }
        super.onCreateContextMenu(contextMenu, view, contextMenuInfo);
    }

    public boolean onContextItemSelected(MenuItem menuItem) {
        int i = ((AdapterContextMenuInfo) menuItem.getMenuInfo()).position;
        if (menuItem.getItemId() == g.menu_delete_sbc) {
            b(i);
        }
        return super.onContextItemSelected(menuItem);
    }

    @SuppressLint({"NewApi"})
    private void a() {
        b();
        this.c.clear();
        if (this.b != null) {
            this.c.addAll(this.b);
        }
        this.c.notifyDataSetChanged();
        if (isResumed()) {
            setListShown(true);
        } else {
            setListShownNoAnimation(true);
        }
    }

    private void b() {
        String[] f;
        if (this.a == a.LTE.ordinal()) {
            f = l.f();
        } else {
            f = v.c();
        }
        this.b = new ArrayList();
        if (f != null) {
            for (Object obj : f) {
                if (!TextUtils.isEmpty(obj) || !TextUtils.isEmpty(obj.trim())) {
                    this.b.add(obj);
                }
            }
        }
    }

    private void c() {
        Builder builder = new Builder(getActivity());
        builder.setTitle(k.preference_lte_dialog_add_title);
        final View editText = new EditText(getActivity());
        editText.requestFocus();
        builder.setView(editText);
        builder.setPositiveButton(k.dialog_ok, new OnClickListener(this) {
            final /* synthetic */ h b;

            public void onClick(DialogInterface dialogInterface, int i) {
                this.b.a(editText.getText().toString());
                this.b.a();
                dialogInterface.dismiss();
            }
        });
        builder.setNegativeButton(k.dialog_cancel, null);
        builder.create().show();
    }

    private void d() {
        Builder builder = new Builder(getActivity());
        builder.setTitle(k.preference_lte_dialog_clear_title);
        builder.setMessage(k.preference_lte_dialog_clear_message);
        builder.setPositiveButton(k.dialog_yes, new OnClickListener(this) {
            final /* synthetic */ h a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                this.a.e();
                this.a.a();
                dialogInterface.dismiss();
            }
        });
        builder.setNegativeButton(k.dialog_no, null);
        builder.create().show();
    }

    private void b(final int i) {
        Builder builder = new Builder(getActivity());
        builder.setTitle(k.preference_lte_dialog_delete_title);
        builder.setMessage(k.preference_lte_dialog_delete_message);
        builder.setPositiveButton(k.dialog_yes, new OnClickListener(this) {
            final /* synthetic */ h b;

            public void onClick(DialogInterface dialogInterface, int i) {
                this.b.c(i);
                this.b.a();
                dialogInterface.dismiss();
            }
        });
        builder.setNegativeButton(k.dialog_no, null);
        builder.create().show();
    }

    private void a(String str) {
        if (!TextUtils.isEmpty(str)) {
            if (this.b == null) {
                this.b = new ArrayList();
            }
            this.b.add(str);
            f();
        }
    }

    private void e() {
        this.b.clear();
        f();
    }

    private void c(int i) {
        if (this.b != null) {
            this.b.remove(i);
            f();
        }
    }

    private void f() {
        String[] b = b.b(this.b);
        if (this.a == a.LTE.ordinal()) {
            l.a(b);
        } else {
            v.a(b);
        }
    }
}
