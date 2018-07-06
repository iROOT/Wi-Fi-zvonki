package com.mavenir.android.fragments;

import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.n;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ListView;
import com.fgmicrotec.mobile.android.fgvoip.FgVoIP;
import com.fgmicrotec.mobile.android.fgvoip.f.g;
import com.fgmicrotec.mobile.android.fgvoip.f.h;
import com.fgmicrotec.mobile.android.fgvoip.f.k;
import com.mavenir.android.a.i;
import com.mavenir.android.common.aa;
import com.mavenir.android.common.q;
import com.mavenir.android.settings.a.x;
import com.mavenir.android.settings.c.y;

public class PreferenceWhitelistFragment extends DialogFragment implements LoaderCallbacks<Cursor> {
    private i a;
    private ListView b;
    private String c = null;

    public /* synthetic */ void onLoadFinished(n nVar, Object obj) {
        a(nVar, (Cursor) obj);
    }

    public static PreferenceWhitelistFragment a() {
        return a(true);
    }

    public static PreferenceWhitelistFragment a(boolean z) {
        PreferenceWhitelistFragment preferenceWhitelistFragment = new PreferenceWhitelistFragment();
        Bundle bundle = new Bundle(1);
        bundle.putBoolean("ARG_USE_ACTION_BAR", z);
        preferenceWhitelistFragment.setArguments(bundle);
        return preferenceWhitelistFragment;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        ViewGroup viewGroup2 = (ViewGroup) layoutInflater.inflate(h.whitelist_activity, viewGroup, false);
        a(viewGroup2);
        return viewGroup2;
    }

    public void a(ViewGroup viewGroup) {
        int i = (getArguments() == null || getArguments().getBoolean("ARG_USE_ACTION_BAR", true)) ? true : 0;
        if (i != 0) {
            ActionBar supportActionBar = ((ActionBarActivity) getActivity()).getSupportActionBar();
            if (supportActionBar != null) {
                supportActionBar.setDisplayHomeAsUpEnabled(true);
                supportActionBar.setTitle(k.preference_whitelist_title);
            }
            setHasOptionsMenu(true);
        }
        if (getDialog() != null) {
            getDialog().setTitle(k.activation_title_whitelist);
        }
        this.b = (ListView) viewGroup.findViewById(16908298);
        this.b.setItemsCanFocus(true);
        this.b.setChoiceMode(0);
        this.a = new i(getActivity());
        this.b.setAdapter(this.a);
        getLoaderManager().initLoader(0, null, this);
    }

    public void onDismiss(DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
        if (aa.a(getActivity()).e() && FgVoIP.U().at()) {
            q.a("PreferenceWhitelistFragment", "onDismiss(): logging out due to hotspot removing from whitelist");
            FgVoIP.U().a("com.fgmicrotec.mobile.android.voip.LogoutFromServerReq");
        } else if (!(!aa.a(getActivity()).d() || FgVoIP.U().at() || FgVoIP.U().au())) {
            q.a("PreferenceWhitelistFragment", "onDismiss(): logging in due to hotspot adding to whitelist");
            FgVoIP.U().a("com.fgmicrotec.mobile.android.voip.LoginToServerReq");
        }
        if (this.a.d()) {
            FgVoIP.U().a(getActivity(), "com.mavenir.android.action_backup_whitelist");
            this.a.a(false);
        }
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.a.d()) {
            FgVoIP.U().a(getActivity(), "com.mavenir.android.action_backup_whitelist");
            this.a.a(false);
        }
    }

    public n<Cursor> onCreateLoader(int i, Bundle bundle) {
        String str;
        String[] strArr = null;
        if (this.c != null) {
            str = "ignore=?";
            strArr = new String[1];
            strArr[0] = this.c.equals("allowed") ? "false" : "true";
        } else {
            str = null;
        }
        return new android.support.v4.content.k(getActivity(), x.a, i.j, str, strArr, "ignore ASC");
    }

    public void a(n<Cursor> nVar, Cursor cursor) {
        this.a.b(cursor);
    }

    public void onLoaderReset(n<Cursor> nVar) {
        this.a.b(null);
    }

    public boolean onContextItemSelected(MenuItem menuItem) {
        long a = this.a.a(((AdapterContextMenuInfo) menuItem.getMenuInfo()).position);
        int itemId = menuItem.getItemId();
        if (itemId == g.menu_item_delete) {
            a(a);
        } else if (itemId == g.menu_item_delete_all) {
            b();
        }
        return super.onContextItemSelected(menuItem);
    }

    private void a(final long j) {
        CharSequence charSequence = getString(k.dialog_whitelist_delete_title) + ": " + y.b(j);
        Builder builder = new Builder(getActivity());
        builder.setTitle(charSequence);
        builder.setIcon(17301543);
        builder.setMessage(k.dialog_whitelist_delete_message);
        builder.setOnCancelListener(new OnCancelListener(this) {
            final /* synthetic */ PreferenceWhitelistFragment a;

            {
                this.a = r1;
            }

            public void onCancel(DialogInterface dialogInterface) {
            }
        });
        builder.setPositiveButton(k.dialog_ok, new OnClickListener(this) {
            final /* synthetic */ PreferenceWhitelistFragment b;

            public void onClick(DialogInterface dialogInterface, int i) {
                aa.a(this.b.getActivity()).a(j);
            }
        });
        builder.setNegativeButton(k.dialog_cancel, new OnClickListener(this) {
            final /* synthetic */ PreferenceWhitelistFragment a;

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
        builder.setTitle(k.dialog_whitelist_clear_title);
        builder.setIcon(17301543);
        builder.setMessage(k.dialog_whitelist_clear_message);
        builder.setPositiveButton(k.dialog_ok, new OnClickListener(this) {
            final /* synthetic */ PreferenceWhitelistFragment a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                aa.a(this.a.getActivity()).b();
            }
        });
        builder.setNegativeButton(k.dialog_cancel, new OnClickListener(this) {
            final /* synthetic */ PreferenceWhitelistFragment a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        builder.create().show();
    }
}
