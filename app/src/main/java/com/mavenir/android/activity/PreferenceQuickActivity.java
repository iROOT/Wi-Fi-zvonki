package com.mavenir.android.activity;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.fgmicrotec.mobile.android.fgvoip.FgVoIP;
import com.fgmicrotec.mobile.android.fgvoip.f.g;
import com.fgmicrotec.mobile.android.fgvoip.f.h;
import com.fgmicrotec.mobile.android.fgvoip.f.i;
import com.fgmicrotec.mobile.android.fgvoip.f.k;
import com.fgmicrotec.mobile.android.fgvoipcommon.CallService;
import com.mavenir.android.common.l;
import com.mavenir.android.settings.b;
import com.mavenir.android.settings.c;
import com.mavenir.android.settings.c.aa;
import com.mavenir.android.settings.c.p;
import com.mavenir.android.settings.c.q;
import com.mavenir.android.settings.c.v;

public class PreferenceQuickActivity extends ActionBarActivity implements OnClickListener {
    private EditText a;
    private EditText b;
    private EditText c;
    private EditText d;
    private EditText e;
    private EditText f;
    private EditText g;
    private EditText h;
    private Spinner i;
    private EditText j;
    private boolean k;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.setContentView(h.quick_account_settings_activity);
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setDisplayHomeAsUpEnabled(true);
        supportActionBar.setTitle(k.preference_quick_category);
        this.a = (EditText) findViewById(g.displayNameEditText);
        this.b = (EditText) findViewById(g.publicIdEditText);
        this.c = (EditText) findViewById(g.publicDomainEditText);
        this.d = (EditText) findViewById(g.privateIdEditText);
        this.e = (EditText) findViewById(g.privateDomainEditText);
        this.f = (EditText) findViewById(g.passwordEditText);
        this.g = (EditText) findViewById(g.proxyEditText);
        this.h = (EditText) findViewById(g.portEditText);
        this.i = (Spinner) findViewById(g.transportSpinner);
        this.j = (EditText) findViewById(g.imeiEditText);
        b();
    }

    protected void onDestroy() {
        if (this.k && !FgVoIP.U().at()) {
            Intent intent = new Intent(this, CallService.class);
            intent.setAction("com.fgmicrotec.mobile.android.voip.LoginToServerReq");
            startService(intent);
        }
        super.onDestroy();
    }

    public void onClick(View view) {
    }

    private void a() {
        c();
    }

    private void b() {
        this.a.setText(p.e());
        String[] split = p.f().split("@");
        if (split.length <= 1 || split[1].trim().length() == 0) {
            this.b.setText("");
            this.c.setText(b.a);
        } else {
            this.b.setText(split[0].startsWith("sip:") ? split[0].substring(4) : split[0]);
            this.c.setText(split[1]);
        }
        String[] split2 = p.g().split("@");
        if (split2.length <= 1 || split2[1].trim().length() == 0) {
            this.d.setText("");
            this.e.setText(b.b);
        } else {
            this.d.setText(split2[0]);
            this.e.setText(split2[1]);
        }
        this.f.setText(p.h());
        this.g.setText(v.d());
        if (this.g.getText().toString().trim().length() < 1) {
            this.g.setText(b.c);
        }
        this.h.setText(String.valueOf(v.b()));
        this.i.setSelection(v.h());
        this.j.setText(p.o());
    }

    private void a(String str) {
        if (d()) {
            p.a(str);
            p.b(this.a.getText().toString().trim());
            p.e(this.f.getText().toString().trim());
            String trim = this.b.getText().toString().trim();
            String str2 = "sip:" + this.b.getText().toString().trim() + "@" + this.c.getText().toString().trim();
            String str3 = this.d.getText().toString().trim() + "@" + this.e.getText().toString().trim();
            p.c(str2);
            p.d(str3);
            p.h(trim);
            p.f("00000000000000000000000000000000");
            p.i("000000");
            p.j("00");
            p.m("some-fqdn.com");
            v.a(new String[]{this.g.getText().toString().trim()});
            v.a(Integer.valueOf(this.h.getText().toString().trim()).intValue());
            v.e(this.i.getSelectedItemPosition());
            p.l(this.j.getText().toString().trim());
            c.k.a(l.a((Context) this).p());
            p.k(l.a((Context) this).p());
            c.k.b(-1);
            aa.a(p.g().split("@")[0]);
            q.b(System.currentTimeMillis());
            if (FgVoIP.U().ai()) {
                c.k.a(1);
            }
            if (FgVoIP.U().I()) {
                c.k.a(1000);
            }
            FgVoIP.U().a("com.mavenir.android.messaging.service.MessagingService", "IntentActions.StartServiceReq");
            FgVoIP.U().a("com.fgmicrotec.mobile.android.voip.LoginToServerReq");
            if (FgVoIP.U().I()) {
                if (FgVoIP.U().N()) {
                    FgVoIP.U().j();
                }
                startActivity(new Intent("com.mavenir.action.LAUNCH_MAIN_TAB"));
            }
            finish();
        }
    }

    private void c() {
        this.k = FgVoIP.U().at();
        if (this.k) {
            com.mavenir.android.common.q.a("PreferenceQuickActivity", "displayNameChangeAlert(): logging out due to SIM error");
            FgVoIP.U().a("com.fgmicrotec.mobile.android.voip.LogoutFromServerReq");
        }
        View inflate = getLayoutInflater().inflate(h.profile_name_enter, null);
        TextView textView = (TextView) inflate.findViewById(g.ProfileName_TextView01);
        final EditText editText = (EditText) inflate.findViewById(g.ProfileName_EditText01);
        Builder builder = new Builder(this);
        builder.setTitle(k.preference_profile_create);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(getResources().getString(k.preference_profile_create_summary));
        textView.setText(stringBuffer.toString());
        editText.setText(p.d());
        builder.setIcon(17301659);
        builder.setView(inflate);
        builder.setOnCancelListener(new OnCancelListener(this) {
            final /* synthetic */ PreferenceQuickActivity a;

            {
                this.a = r1;
            }

            public void onCancel(DialogInterface dialogInterface) {
            }
        });
        builder.setPositiveButton(k.dialog_ok, new DialogInterface.OnClickListener(this) {
            final /* synthetic */ PreferenceQuickActivity b;

            public void onClick(DialogInterface dialogInterface, int i) {
                String obj = editText.getText().toString();
                if (obj.length() > 0) {
                    this.b.a(obj);
                }
                this.b.a(editText);
            }
        });
        builder.setNegativeButton(k.dialog_cancel, new DialogInterface.OnClickListener(this) {
            final /* synthetic */ PreferenceQuickActivity b;

            public void onClick(DialogInterface dialogInterface, int i) {
                this.b.a(editText);
            }
        });
        AlertDialog create = builder.create();
        create.show();
        ((InputMethodManager) getSystemService("input_method")).showSoftInput(create.getWindow().getCurrentFocus(), 2);
    }

    private boolean d() {
        if (this.b.getText().toString().length() == 0) {
            Toast.makeText(this, getString(k.preference_account_error_user_id_empty), 0).show();
            this.b.requestFocus();
            return false;
        } else if (this.f.getText().toString().length() == 0) {
            Toast.makeText(this, getString(k.preference_account_error_password_empty), 0).show();
            this.f.requestFocus();
            return false;
        } else if (this.c.getText().toString().length() == 0) {
            Toast.makeText(this, getString(k.preference_account_error_domain_empty), 0).show();
            this.c.requestFocus();
            return false;
        } else if (this.g.getText().toString().length() == 0) {
            Toast.makeText(this, getString(k.preference_account_error_proxy_empty), 0).show();
            this.g.requestFocus();
            return false;
        } else if (this.h.getText().toString().length() != 0) {
            return true;
        } else {
            Toast.makeText(this, getString(k.preference_account_error_proxy_port_empty), 0).show();
            this.h.requestFocus();
            return false;
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(i.quick_setup_activity, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == 16908332) {
            finish();
        } else if (itemId == g.menu_save) {
            a();
        }
        return true;
    }

    private void a(EditText editText) {
        ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }
}
