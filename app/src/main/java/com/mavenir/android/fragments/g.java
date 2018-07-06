package com.mavenir.android.fragments;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.n;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.fgmicrotec.mobile.android.fgvoip.FgVoIP;
import com.fgmicrotec.mobile.android.fgvoip.f.h;
import com.fgmicrotec.mobile.android.fgvoip.f.i;
import com.fgmicrotec.mobile.android.fgvoip.f.k;
import com.fgmicrotec.mobile.android.fgvoipcommon.CallService;
import com.mavenir.android.activity.PreferenceProfileAdvancedActivity;
import com.mavenir.android.common.q;
import com.mavenir.android.settings.a;
import com.mavenir.android.settings.c;
import com.mavenir.android.settings.c.p;

public class g extends ListFragment implements LoaderCallbacks<Cursor>, OnItemClickListener {
    private com.mavenir.android.a.g a;
    private int b;
    private boolean c = false;
    private long d;

    public /* synthetic */ void onLoadFinished(n nVar, Object obj) {
        a(nVar, (Cursor) obj);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        ActionBar supportActionBar = ((ActionBarActivity) getActivity()).getSupportActionBar();
        supportActionBar.setDisplayHomeAsUpEnabled(true);
        supportActionBar.setTitle(k.preference_profiles_title);
        setHasOptionsMenu(true);
        getListView().setItemsCanFocus(false);
        getListView().setOnItemClickListener(this);
        getListView().setChoiceMode(1);
        registerForContextMenu(getListView());
        this.c = FgVoIP.U().at();
        this.d = c.k.c();
        getLoaderManager().initLoader(0, null, this);
    }

    public void onDestroy() {
        if (this.d != c.k.c()) {
            getActivity().sendBroadcast(new Intent("SettingsActions.ACTION_ACTIVE_PROFILE_CHANGED"));
        }
        FgVoIP.U().a("com.mavenir.android.messaging.service.MessagingService", "IntentActions.StartServiceReq");
        a();
        super.onDestroy();
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(i.profile_list_activity, menu);
        super.onCreateOptionsMenu(menu, menuInflater);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == com.fgmicrotec.mobile.android.fgvoip.f.g.menuProfileNew) {
            c();
            return true;
        } else if (itemId == com.fgmicrotec.mobile.android.fgvoip.f.g.menuProfileDelete) {
            if (getListView().getCheckedItemPosition() == 0) {
                e();
                return true;
            }
            b(getListView().getCheckedItemPosition());
            return true;
        } else if (itemId != com.fgmicrotec.mobile.android.fgvoip.f.g.menuProfileCopy) {
            return super.onOptionsItemSelected(menuItem);
        } else {
            a(getListView().getCheckedItemPosition());
            return true;
        }
    }

    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenuInfo contextMenuInfo) {
        switch (view.getId()) {
            case 16908298:
                getActivity().getMenuInflater().inflate(i.profile_list_context, contextMenu);
                break;
        }
        super.onCreateContextMenu(contextMenu, view, contextMenuInfo);
    }

    public boolean onContextItemSelected(MenuItem menuItem) {
        int i = ((AdapterContextMenuInfo) menuItem.getMenuInfo()).position;
        int itemId = menuItem.getItemId();
        if (itemId == com.fgmicrotec.mobile.android.fgvoip.f.g.menuProfileCopy) {
            a(i);
        } else if (itemId == com.fgmicrotec.mobile.android.fgvoip.f.g.menuProfileDelete) {
            if (i == 0) {
                e();
            } else {
                b(i);
            }
        }
        return super.onContextItemSelected(menuItem);
    }

    public n<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new android.support.v4.content.k(getActivity(), a.n.a, new String[]{"_id", "profile_name"}, null, null, null);
    }

    public void a(n<Cursor> nVar, Cursor cursor) {
        if (this.a == null) {
            this.a = new com.mavenir.android.a.g(getActivity(), cursor);
            setListAdapter(this.a);
        } else {
            this.a.b(cursor);
        }
        this.b = this.a.d();
        getListView().setItemChecked(this.b, true);
    }

    public void onLoaderReset(n<Cursor> nVar) {
        this.a.b(null);
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        q.a("PreferenceProfilesFragment", "onItemClick(): entering advanced profile preferences...");
        FgVoIP.U().b(true);
        Intent intent = new Intent(getActivity(), CallService.class);
        intent.setAction("com.fgmicrotec.mobile.android.voip.LogoutFromServerReq");
        getActivity().startService(intent);
        if (i == this.b) {
            startActivity(new Intent(getActivity(), PreferenceProfileAdvancedActivity.class));
        }
        this.b = i;
        a(this.a.a(getListView().getCheckedItemPosition()));
    }

    public void a() {
        q.a("PreferenceProfilesFragment", "onDestroy(): leaving profile list preferences...");
        FgVoIP.U().b(false);
        if (b()) {
            FgVoIP.U().a("com.fgmicrotec.mobile.android.voip.LoginToServerReq");
        }
        this.c = false;
    }

    private void a(long j) {
        if (c.k.c() != j) {
            c.k.a(j);
        }
    }

    private boolean b() {
        if (getString(k.app_name_short).equals("TinT")) {
            return true;
        }
        return this.c;
    }

    private void c() {
        View inflate = getActivity().getLayoutInflater().inflate(h.profile_name_enter, null);
        TextView textView = (TextView) inflate.findViewById(com.fgmicrotec.mobile.android.fgvoip.f.g.ProfileName_TextView01);
        final EditText editText = (EditText) inflate.findViewById(com.fgmicrotec.mobile.android.fgvoip.f.g.ProfileName_EditText01);
        Builder builder = new Builder(getActivity());
        builder.setTitle(k.preference_profile_create);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(getResources().getString(k.preference_profile_create_summary));
        textView.setText(stringBuffer.toString());
        builder.setIcon(17301659);
        builder.setView(inflate);
        builder.setOnCancelListener(new OnCancelListener(this) {
            final /* synthetic */ g a;

            {
                this.a = r1;
            }

            public void onCancel(DialogInterface dialogInterface) {
            }
        });
        builder.setPositiveButton(k.dialog_ok, new OnClickListener(this) {
            final /* synthetic */ g b;

            public void onClick(DialogInterface dialogInterface, int i) {
                String obj = editText.getText().toString();
                if (obj.length() > 0) {
                    if (p.b().contains(obj)) {
                        this.b.d();
                    } else {
                        com.mavenir.android.settings.c.g.a(1, obj);
                    }
                }
                this.b.a(editText);
            }
        });
        builder.setNegativeButton(k.dialog_cancel, new OnClickListener(this) {
            final /* synthetic */ g b;

            public void onClick(DialogInterface dialogInterface, int i) {
                this.b.a(editText);
            }
        });
        AlertDialog create = builder.create();
        create.show();
        ((InputMethodManager) getActivity().getSystemService("input_method")).showSoftInput(create.getWindow().getCurrentFocus(), 2);
    }

    private void d() {
        Builder builder = new Builder(getActivity());
        builder.setTitle(k.dialog_warning_title);
        builder.setIcon(17301543);
        builder.setMessage(k.dialog_profile_name_exists);
        builder.setOnCancelListener(new OnCancelListener(this) {
            final /* synthetic */ g a;

            {
                this.a = r1;
            }

            public void onCancel(DialogInterface dialogInterface) {
            }
        });
        builder.setPositiveButton(k.dialog_ok, new OnClickListener(this) {
            final /* synthetic */ g a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        builder.create().show();
    }

    private void a(final int i) {
        View inflate = getActivity().getLayoutInflater().inflate(h.profile_name_enter, null);
        TextView textView = (TextView) inflate.findViewById(com.fgmicrotec.mobile.android.fgvoip.f.g.ProfileName_TextView01);
        final EditText editText = (EditText) inflate.findViewById(com.fgmicrotec.mobile.android.fgvoip.f.g.ProfileName_EditText01);
        Builder builder = new Builder(getActivity());
        builder.setTitle(k.dialog_copy_profile_title);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(getResources().getString(k.dialog_copy_profile_message));
        textView.setText(stringBuffer.toString());
        StringBuffer stringBuffer2 = new StringBuffer();
        stringBuffer2.append(this.a.b(i));
        stringBuffer2.append("_");
        stringBuffer2.append(getResources().getString(k.copy));
        editText.setText(stringBuffer2.toString());
        builder.setIcon(17301659);
        builder.setView(inflate);
        builder.setOnCancelListener(new OnCancelListener(this) {
            final /* synthetic */ g a;

            {
                this.a = r1;
            }

            public void onCancel(DialogInterface dialogInterface) {
            }
        });
        builder.setPositiveButton(k.dialog_ok, new OnClickListener(this) {
            final /* synthetic */ g c;

            public void onClick(DialogInterface dialogInterface, int i) {
                String obj = editText.getText().toString();
                if (obj.length() > 0) {
                    if (p.b().contains(obj)) {
                        this.c.d();
                    } else {
                        com.mavenir.android.settings.c.g.a(this.c.a.a(i), obj);
                    }
                }
                this.c.a(editText);
            }
        });
        builder.setNegativeButton(k.dialog_cancel, new OnClickListener(this) {
            final /* synthetic */ g b;

            public void onClick(DialogInterface dialogInterface, int i) {
                this.b.a(editText);
            }
        });
        AlertDialog create = builder.create();
        create.show();
        ((InputMethodManager) getActivity().getSystemService("input_method")).showSoftInput(create.getWindow().getCurrentFocus(), 2);
    }

    private void b(final int i) {
        Builder builder = new Builder(getActivity());
        builder.setTitle(k.dialog_confirm_delete_title);
        builder.setIcon(17301543);
        builder.setMessage(k.dialog_confirm_delete_profile_message);
        builder.setOnCancelListener(new OnCancelListener(this) {
            final /* synthetic */ g a;

            {
                this.a = r1;
            }

            public void onCancel(DialogInterface dialogInterface) {
            }
        });
        builder.setPositiveButton(k.dialog_ok, new OnClickListener(this) {
            final /* synthetic */ g b;

            public void onClick(DialogInterface dialogInterface, int i) {
                if (this.b.getListView().getCheckedItemPosition() != -1) {
                    if (this.b.getListView().getCheckedItemPosition() == i) {
                        this.b.a(this.b.a.a(i - 1));
                    }
                    com.mavenir.android.settings.c.g.a(this.b.a.a(i));
                    return;
                }
                Toast.makeText(this.b.getActivity(), this.b.getString(k.invalid_selection), 0).show();
            }
        });
        builder.setNegativeButton(k.dialog_cancel, new OnClickListener(this) {
            final /* synthetic */ g a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        builder.create().show();
    }

    private void e() {
        Builder builder = new Builder(getActivity());
        builder.setTitle(k.dialog_warning_title);
        builder.setIcon(17301543);
        builder.setMessage(k.dialog_delete_main_profile_message);
        builder.setOnCancelListener(new OnCancelListener(this) {
            final /* synthetic */ g a;

            {
                this.a = r1;
            }

            public void onCancel(DialogInterface dialogInterface) {
            }
        });
        builder.setPositiveButton(k.dialog_ok, new OnClickListener(this) {
            final /* synthetic */ g a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        builder.create().show();
    }

    private void a(EditText editText) {
        ((InputMethodManager) getActivity().getSystemService("input_method")).hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }
}
