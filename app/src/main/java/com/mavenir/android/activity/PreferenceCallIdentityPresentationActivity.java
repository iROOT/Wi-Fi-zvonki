package com.mavenir.android.activity;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.IBinder;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;
import android.preference.PreferenceScreen;
import android.widget.Toast;
import com.fgmicrotec.mobile.android.fgvoip.f.h;
import com.fgmicrotec.mobile.android.fgvoip.f.k;
import com.mavenir.android.common.q;
import com.mavenir.android.services.SupplementaryService;
import com.mavenir.android.services.SupplementaryServicesAdapter;
import com.mavenir.android.services.SupplementaryServicesAdapter.d;
import com.mavenir.android.services.SupplementaryServicesService;
import com.mavenir.android.services.SupplementaryServicesService.a;
import com.mavenir.android.services.SupplementaryServicesService.b;
import java.util.ArrayList;
import java.util.Iterator;

public class PreferenceCallIdentityPresentationActivity extends PreferenceActivity implements OnCancelListener, OnPreferenceClickListener, b {
    private PreferenceScreen a;
    private CheckBoxPreference b;
    private CheckBoxPreference c;
    private CheckBoxPreference d;
    private CheckBoxPreference e;
    private SupplementaryServicesService f;
    private CheckBoxPreference g;
    private boolean h = false;
    private ProgressDialog i;
    private boolean j = false;
    private ServiceConnection k = new ServiceConnection(this) {
        final /* synthetic */ PreferenceCallIdentityPresentationActivity a;

        {
            this.a = r1;
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            q.a("PreferenceCallIdentityPresentationActivity", "onServiceConnected()");
            this.a.f = ((a) iBinder).a();
            this.a.f.a(this.a);
            this.a.h = true;
            this.a.c();
        }

        public void onServiceDisconnected(ComponentName componentName) {
            q.a("PreferenceCallIdentityPresentationActivity", "onServiceDisconnected()");
            this.a.h = false;
        }
    };

    @TargetApi(11)
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (VERSION.SDK_INT >= 11) {
            ActionBar actionBar = getActionBar();
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(k.preference_call_id_presentation_title);
        }
        a();
        bindService(new Intent(this, SupplementaryServicesService.class), this.k, 1);
        a(false);
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.h) {
            q.a("PreferenceCallIdentityPresentationActivity", "onDestroy(): unbinding service");
            unbindService(this.k);
            this.h = false;
        }
    }

    public void onCancel(DialogInterface dialogInterface) {
        if (!this.j) {
            finish();
        }
    }

    public void a(SupplementaryServicesAdapter.a aVar) {
    }

    public void b(SupplementaryServicesAdapter.a aVar) {
        q.a("PreferenceCallIdentityPresentationActivity", "onSetServiceCnf(): errorType: " + aVar.name());
        b();
        if (aVar != SupplementaryServicesAdapter.a.SS_ERROR_OK) {
            a(k.preference_call_additional_error_update);
            runOnUiThread(new Runnable(this) {
                final /* synthetic */ PreferenceCallIdentityPresentationActivity a;

                {
                    this.a = r1;
                }

                public void run() {
                    if (this.a.g != null) {
                        this.a.a(this.a.g, !this.a.g.isChecked());
                    }
                }
            });
        }
    }

    public boolean onPreferenceClick(Preference preference) {
        if (preference instanceof CheckBoxPreference) {
            CheckBoxPreference checkBoxPreference = (CheckBoxPreference) preference;
            if (preference.getKey().equals(getString(k.preference_call_id_presentation_orig))) {
                a(checkBoxPreference, this.f.a(d.SS_SERVICE_OIP));
            } else if (preference.getKey().equals(getString(k.preference_call_id_presentation_orig_rest))) {
                a(checkBoxPreference, this.f.a(d.SS_SERVICE_OIPR));
            } else if (preference.getKey().equals(getString(k.preference_call_id_presentation_term))) {
                a(checkBoxPreference, this.f.a(d.SS_SERVICE_TIP));
            } else if (preference.getKey().equals(getString(k.preference_call_id_presentation_term_rest))) {
                a(checkBoxPreference, this.f.a(d.SS_SERVICE_TIPR));
            }
        }
        return false;
    }

    private void a() {
        this.a = getPreferenceManager().createPreferenceScreen(this);
        setPreferenceScreen(this.a);
        this.b = new CheckBoxPreference(this);
        this.b.setLayoutResource(h.custom_checkbox_preference);
        this.b.setKey(getString(k.preference_call_id_presentation_orig));
        this.b.setTitle(k.preference_call_id_presentation_orig);
        this.b.setPersistent(false);
        this.b.setChecked(false);
        this.b.setOnPreferenceClickListener(this);
        this.b.setEnabled(false);
        this.c = new CheckBoxPreference(this);
        this.c.setLayoutResource(h.custom_checkbox_preference);
        this.c.setKey(getString(k.preference_call_id_presentation_orig_rest));
        this.c.setTitle(k.preference_call_id_presentation_orig_rest);
        this.c.setPersistent(false);
        this.c.setChecked(false);
        this.c.setOnPreferenceClickListener(this);
        this.c.setEnabled(false);
        this.d = new CheckBoxPreference(this);
        this.d.setLayoutResource(h.custom_checkbox_preference);
        this.d.setKey(getString(k.preference_call_id_presentation_term));
        this.d.setTitle(k.preference_call_id_presentation_term);
        this.d.setPersistent(false);
        this.d.setChecked(false);
        this.d.setOnPreferenceClickListener(this);
        this.d.setEnabled(false);
        this.e = new CheckBoxPreference(this);
        this.e.setLayoutResource(h.custom_checkbox_preference);
        this.e.setKey(getString(k.preference_call_id_presentation_term_rest));
        this.e.setTitle(k.preference_call_id_presentation_term_rest);
        this.e.setPersistent(false);
        this.e.setChecked(false);
        this.e.setOnPreferenceClickListener(this);
        this.e.setEnabled(false);
    }

    private void a(boolean z) {
        CharSequence string;
        boolean z2 = false;
        this.i = new ProgressDialog(this);
        this.i.setCanceledOnTouchOutside(false);
        ProgressDialog progressDialog = this.i;
        if (!z) {
            z2 = true;
        }
        progressDialog.setCancelable(z2);
        this.i.setTitle(null);
        progressDialog = this.i;
        if (z) {
            string = getString(k.preference_call_additional_progress_update);
        } else {
            string = getString(k.preference_call_additional_progress);
        }
        progressDialog.setMessage(string);
        this.i.setIndeterminate(true);
        this.i.setOnCancelListener(this);
        this.i.show();
    }

    private void b() {
        this.j = true;
        if (this.i != null && this.i.isShowing()) {
            this.i.dismiss();
        }
    }

    private void c() {
        if (this.f != null) {
            ArrayList c = this.f.c();
            if (c != null) {
                Iterator it = c.iterator();
                while (it.hasNext()) {
                    SupplementaryService supplementaryService = (SupplementaryService) it.next();
                    if (supplementaryService.getServiceType() == d.SS_SERVICE_OIP) {
                        this.a.addPreference(this.b);
                        a(this.b, supplementaryService.isActive());
                    } else if (supplementaryService.getServiceType() == d.SS_SERVICE_OIPR) {
                        this.a.addPreference(this.c);
                        a(this.c, !supplementaryService.isActive());
                    } else if (supplementaryService.getServiceType() == d.SS_SERVICE_TIP) {
                        this.a.addPreference(this.d);
                        a(this.d, supplementaryService.isActive());
                    } else if (supplementaryService.getServiceType() == d.SS_SERVICE_TIPR) {
                        this.a.addPreference(this.e);
                        a(this.e, supplementaryService.isActive());
                    }
                }
            }
        }
        b();
    }

    private void a(CheckBoxPreference checkBoxPreference, SupplementaryService supplementaryService) {
        boolean z = false;
        boolean z2 = true;
        boolean isChecked = checkBoxPreference.isChecked();
        if (supplementaryService != null) {
            if (supplementaryService.getServiceType() != d.SS_SERVICE_OIPR) {
                z = isChecked;
            } else if (!isChecked) {
                z = true;
            }
            supplementaryService.setIsActive(z);
            a(true);
            this.f.a(supplementaryService, null);
            this.g = checkBoxPreference;
            return;
        }
        if (isChecked) {
            z2 = false;
        }
        a(checkBoxPreference, z2);
    }

    private void a(CheckBoxPreference checkBoxPreference, boolean z) {
        checkBoxPreference.setOnPreferenceClickListener(null);
        checkBoxPreference.setChecked(z);
        checkBoxPreference.setEnabled(true);
        checkBoxPreference.setOnPreferenceClickListener(this);
    }

    private void a(final int i) {
        runOnUiThread(new Runnable(this) {
            final /* synthetic */ PreferenceCallIdentityPresentationActivity b;

            public void run() {
                Toast.makeText(this.b.getApplicationContext(), i, 0).show();
            }
        });
    }
}
