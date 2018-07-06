package com.mavenir.android.vtow.activity;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.FragmentTransaction;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.inputmethod.InputMethodManager;
import com.fgmicrotec.mobile.android.fgmag.DataConnectionManager;
import com.fgmicrotec.mobile.android.fgvoip.FgVoIP;
import com.fgmicrotec.mobile.android.fgvoip.f.d;
import com.fgmicrotec.mobile.android.fgvoip.f.e;
import com.fgmicrotec.mobile.android.fgvoip.f.f;
import com.fgmicrotec.mobile.android.fgvoip.f.g;
import com.fgmicrotec.mobile.android.fgvoip.f.h;
import com.fgmicrotec.mobile.android.fgvoip.f.i;
import com.fgmicrotec.mobile.android.fgvoip.f.k;
import com.fgmicrotec.mobile.android.fgvoipcommon.CallService;
import com.mavenir.android.activity.AboutActivity;
import com.mavenir.android.activity.PreferenceGeneralActivity;
import com.mavenir.android.activity.PreferenceMainActivity;
import com.mavenir.android.activity.PreferenceWhitelistActivity;
import com.mavenir.android.common.CallManager;
import com.mavenir.android.common.aa;
import com.mavenir.android.common.l;
import com.mavenir.android.common.q;
import com.mavenir.android.common.w;
import com.mavenir.android.fragments.PreferenceWhitelistFragment;
import com.mavenir.android.settings.c;
import com.mavenir.android.settings.c.j;
import com.mavenir.android.settings.c.m;
import com.mavenir.android.vtow.activation.b;
import java.util.List;

public class MainTabActivity extends ActionBarActivity implements TabListener {
    public static String a = "activeTab";
    SensorEventListener b = new SensorEventListener(this) {
        final /* synthetic */ MainTabActivity a;

        {
            this.a = r1;
        }

        public void onSensorChanged(SensorEvent sensorEvent) {
            this.a.a((double) sensorEvent.values[0], (double) sensorEvent.values[1], (double) sensorEvent.values[2]);
        }

        public void onAccuracyChanged(Sensor sensor, int i) {
        }
    };
    private final String c = "MainTabActivity";
    private ActionBar d;
    private AlertDialog e;
    private final int f = 1;
    private final double g = 3.0d;
    private double h = 0.0d;
    private double i = 0.0d;
    private double j = 0.0d;
    private Handler k;
    private a l;
    private boolean m = false;
    private ViewPager n;
    private com.mavenir.android.vtow.a.a o;
    private Runnable p = new Runnable(this) {
        final /* synthetic */ MainTabActivity a;

        {
            this.a = r1;
        }

        public void run() {
            b.a(this.a).b();
        }
    };
    private Runnable q = new Runnable(this) {
        final /* synthetic */ MainTabActivity a;

        {
            this.a = r1;
        }

        public void run() {
            if (CallManager.a()) {
                this.a.startActivity(FgVoIP.U().aI());
            }
        }
    };

    private class a extends BroadcastReceiver {
        final /* synthetic */ MainTabActivity a;

        private a(MainTabActivity mainTabActivity) {
            this.a = mainTabActivity;
        }

        /* synthetic */ a(MainTabActivity mainTabActivity, AnonymousClass1 anonymousClass1) {
            this(mainTabActivity);
        }

        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                return;
            }
            if ("IntentActions.ActionExit".equals(intent.getAction())) {
                this.a.m();
            } else if ("MainTabActions.LoginStatusChanged".equals(intent.getAction())) {
                q.a("MainTabActivity", "onReceive(): ACTION_LOGIN_STATUS_CHANGED");
                this.a.a(FgVoIP.U().at());
            } else if ("ActivationActions.ActionRefreshAfterProvisioning".equals(intent.getAction())) {
                FgVoIP.U().a(this.a.getSupportActionBar(), 1, "");
            } else if ("MainTabActions.ActionStopActivityIncomingCall".equals(intent.getAction())) {
                Intent intent2 = new Intent();
                intent2.setAction("Activity add call supplementary screen interrupted");
                this.a.sendBroadcast(intent2);
                this.a.finish();
            } else if ("MainTabActions.ActionStopActivityMainInherits".equals(intent.getAction())) {
                this.a.f();
                if (CallManager.a()) {
                    this.a.startActivity(FgVoIP.U().aI());
                }
            } else if ("MainTabActions.ActionStopActivityCallInitiated".equals(intent.getAction())) {
                this.a.finish();
            } else if (intent.getAction().equals("com.mavenir.android.ActionWifiWhitelist")) {
                if (this.a.hasWindowFocus()) {
                    this.a.a();
                }
            } else if (intent.getAction().equals("com.mavenir.action.UPDATE_MAIN_TAB")) {
                this.a.b();
            }
        }
    }

    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt("MainTabExtras.ExtraSelectedTabIndex", getSupportActionBar().getSelectedNavigationIndex());
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        q.a("MainTabActivity", "onActivityResult: requestCode=" + i + ", resultCode=" + i2 + ", data=" + intent);
        switch (i) {
            case 1:
                if (i2 == -1) {
                    break;
                }
                break;
        }
        super.onActivityResult(i, i2, intent);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.setContentView(h.main_tab_activity);
        this.k = new Handler();
        g();
        q.b("MainTabActivity", "MainTabActivity onCreate()");
        a(bundle);
        FgVoIP.U().c();
        FgVoIP.U().b(false);
        this.l = new a();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("IntentActions.ActionExit");
        intentFilter.addAction("MainTabActions.LoginStatusChanged");
        intentFilter.addAction("ActivationActions.ActionRefreshAfterProvisioning");
        intentFilter.addAction("MainTabActions.ActionStopActivityIncomingCall");
        intentFilter.addAction("MainTabActions.ActionStopActivityMainInherits");
        intentFilter.addAction("com.mavenir.android.ActionWifiWhitelist");
        intentFilter.addAction("ExceptionDialogFragment.ActionDisplayPopup");
        intentFilter.addAction("com.mavenir.action.UPDATE_MAIN_TAB");
        registerReceiver(this.l, intentFilter);
        this.m = true;
        if (!CallService.k()) {
            FgVoIP.U().a(getSupportActionBar(), 1, getString(k.status_message_logging_in));
        }
        if (!FgVoIP.U().ay()) {
            Intent intent = new Intent(this, CallService.class);
            intent.setAction("com.fgmicrotec.mobile.android.voip.LoginToServerReq");
            startService(intent);
        }
        int intExtra = getIntent().getIntExtra("MainTabExtras.ExtraSelectedTabIndex", 0);
        if (intExtra > 0 && intExtra < this.o.getCount()) {
            this.d.setSelectedNavigationItem(intExtra);
        }
        FgVoIP.U().b((Activity) this);
        this.k.postDelayed(this.p, 2000);
        w.a().b(this);
    }

    public void onTabReselected(Tab tab, FragmentTransaction fragmentTransaction) {
    }

    public void onTabSelected(Tab tab, FragmentTransaction fragmentTransaction) {
        if (this.n.getCurrentItem() != tab.getPosition()) {
            this.n.a(tab.getPosition(), false);
            if (getCurrentFocus() != null) {
                ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            }
        }
    }

    public void onTabUnselected(Tab tab, FragmentTransaction fragmentTransaction) {
    }

    public void onResume() {
        super.onResume();
        q.a("MainTabActivity", "onResume()");
        FgVoIP.U().b(false);
        a(FgVoIP.U().at());
        if (FgVoIP.U().aE()) {
            a(null);
            FgVoIP.U().h(false);
        } else {
            int i;
            if (getIntent().hasExtra("MainTabExtras.ExtraSelectedTabIndex")) {
                i = getIntent().getExtras().getInt("MainTabExtras.ExtraSelectedTabIndex");
            } else {
                i = getSupportActionBar().getSelectedNavigationIndex();
            }
            if (!(FgVoIP.U().ad() == com.fgmicrotec.mobile.android.fgvoip.FgVoIP.a.VOIP_WITH_SMS || FgVoIP.U().ad() == com.fgmicrotec.mobile.android.fgvoip.FgVoIP.a.VToW)) {
                android.support.v7.app.ActionBar supportActionBar = getSupportActionBar();
                if (i == 1) {
                    i = 0;
                }
                supportActionBar.setSelectedNavigationItem(i);
            }
            if (c.k.u()) {
                b();
            }
        }
        a(getIntent());
        FgVoIP.U().a((Activity) this);
        if (CallService.q()) {
            q.a("MainTabActivity", "MainTabActivity onResume() - SPIRIT Restart");
            CallService.r();
            w.a().b();
            w.a().b(this);
            DataConnectionManager.recoverAfterSilentIPChange();
            return;
        }
        w.a().a(this);
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        accessibilityEvent.setContentDescription(this.o.getPageTitle(getSupportActionBar().getSelectedNavigationIndex()));
        q.a("MainTabActivity", "dispatchPopulateAccessibilityEvent(): " + accessibilityEvent.getContentDescription());
        return super.dispatchPopulateAccessibilityEvent(accessibilityEvent);
    }

    protected void onPostResume() {
        super.onPostResume();
        q.b("MainTabActivity", "onPostResume()");
        a();
    }

    private void a() {
        c();
        PreferenceWhitelistFragment preferenceWhitelistFragment = (PreferenceWhitelistFragment) getSupportFragmentManager().findFragmentByTag("whitelistDialog");
        if (preferenceWhitelistFragment == null || !preferenceWhitelistFragment.isVisible()) {
            b(false);
        }
    }

    public void onStop() {
        q.b("MainTabActivity", "MainTabActivity onStop()");
        super.onStop();
    }

    public void onDestroy() {
        q.b("MainTabActivity", "MainTabActivity onDestroy()");
        this.k.removeCallbacks(this.q);
        this.k.removeCallbacks(this.p);
        if (this.m) {
            unregisterReceiver(this.l);
            this.m = false;
        }
        super.onDestroy();
    }

    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        int intExtra = intent.getIntExtra("MainTabExtras.ExtraSelectedTabIndex", 0);
        if (intExtra > 0) {
            this.d.setSelectedNavigationItem(intExtra);
        }
        a(intent);
    }

    public void onBackPressed() {
        q.a("MainTabActivity", "onBackPressed(): User pressed back");
        e();
        super.onBackPressed();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        q.a("MainTabActivity", "onCreateOptionsMenu()");
        menu.clear();
        getMenuInflater().inflate(i.main_tab_activity, menu);
        a(menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        q.a("MainTabActivity", "onPrepareOptionsMenu()");
        a(menu);
        return super.onPrepareOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == g.menu_settings) {
            q.a("MainTabActivity", "onOptionsItemSelected(): User pressed settings");
            h();
            return true;
        } else if (itemId == g.menu_echo_test) {
            q.a("MainTabActivity", "onOptionsItemSelected(): User pressed Echo test");
            String h = c.c.h();
            if (!FgVoIP.U().at()) {
                q.d("MainTabActivity", "adjustMenuItemsVisibility(): Not logged in!!");
                return true;
            } else if (TextUtils.isEmpty(h)) {
                q.d("MainTabActivity", "adjustMenuItemsVisibility(): Echo Test number is not available");
                return true;
            } else {
                FgVoIP.U().j(h);
                return true;
            }
        } else if (itemId == g.menu_return_to_call) {
            q.a("MainTabActivity", "onOptionsItemSelected(): User pressed return to call");
            e();
            return true;
        } else if (itemId == g.menu_whitelist) {
            q.a("MainTabActivity", "onOptionsItemSelected(): User pressed whitelist");
            i();
            return true;
        } else if (itemId == g.menu_help) {
            q.a("MainTabActivity", "onOptionsItemSelected(): User pressed help");
            j();
            return true;
        } else if (itemId == g.menu_about) {
            q.a("MainTabActivity", "onOptionsItemSelected(): User pressed about");
            k();
            return true;
        } else if (itemId != g.menu_exit) {
            return super.onOptionsItemSelected(menuItem);
        } else {
            q.a("MainTabActivity", "onOptionsItemSelected(): User pressed exit");
            m();
            return true;
        }
    }

    private void a(Menu menu) {
        MenuItem findItem;
        boolean z;
        boolean z2 = true;
        boolean a = CallManager.a();
        MenuItem findItem2 = menu.findItem(g.menu_return_to_call);
        if (findItem2 != null) {
            findItem2.setVisible(a);
        }
        if (FgVoIP.U().J()) {
            findItem = menu.findItem(g.menu_whitelist);
            if (findItem != null) {
                findItem.setVisible(!a);
            }
        }
        findItem = menu.findItem(g.menu_settings);
        if (findItem != null) {
            if (a) {
                z = false;
            } else {
                z = true;
            }
            findItem.setVisible(z);
        }
        findItem = menu.findItem(g.menu_echo_test);
        CharSequence h = c.c.h();
        if (!FgVoIP.U().at()) {
            q.d("MainTabActivity", "adjustMenuItemsVisibility(): Not logged in!!");
            z = false;
        } else if (TextUtils.isEmpty(h)) {
            q.d("MainTabActivity", "adjustMenuItemsVisibility(): Echo Test number is not available");
            z = false;
        } else {
            z = true;
        }
        if (findItem != null) {
            findItem.setEnabled(z);
        }
        findItem = menu.findItem(g.menu_help);
        if (findItem != null) {
            if (a) {
                z = false;
            } else {
                z = true;
            }
            findItem.setVisible(z);
        }
        findItem = menu.findItem(g.menu_about);
        if (findItem != null) {
            if (a) {
                z = false;
            } else {
                z = true;
            }
            findItem.setVisible(z);
        }
        findItem2 = menu.findItem(g.menu_exit);
        if (findItem2 != null) {
            if (a) {
                z2 = false;
            }
            findItem2.setVisible(z2);
        }
    }

    private void a(Bundle bundle) {
        boolean z = true;
        boolean z2 = getResources().getBoolean(d.abc_split_action_bar_is_narrow);
        this.d = getActionBar();
        this.d.setNavigationMode(2);
        this.d.setDisplayShowHomeEnabled(!z2);
        ActionBar actionBar = this.d;
        if (z2) {
            z = false;
        }
        actionBar.setDisplayShowTitleEnabled(z);
        this.o = new com.mavenir.android.vtow.a.a(getSupportFragmentManager());
        this.n = (ViewPager) findViewById(g.pager);
        this.n.setOffscreenPageLimit(2);
        this.n.setAdapter(this.o);
        this.n.setOnPageChangeListener(new ViewPager.i(this) {
            final /* synthetic */ MainTabActivity a;

            {
                this.a = r1;
            }

            public void a(int i) {
                this.a.d.setSelectedNavigationItem(i);
            }

            public void b(int i) {
                if (i == 0) {
                    ((InputMethodManager) this.a.getSystemService("input_method")).hideSoftInputFromWindow(this.a.n.getWindowToken(), 0);
                }
            }
        });
        this.d.removeAllTabs();
        this.d.addTab(this.d.newTab().setContentDescription(k.cd_maintab_contacts).setText(k.actionbar_tab_contacts).setIcon(f.tab_indicator_ab_icon_contacts).setTabListener(this));
        this.d.addTab(this.d.newTab().setContentDescription(k.cd_maintab_calllog).setText(k.actionbar_tab_call_log).setIcon(f.tab_indicator_ab_icon_calllog).setTabListener(this));
        if (c.k.u()) {
            this.d.addTab(this.d.newTab().setContentDescription(k.cd_maintab_messages).setText(k.actionbar_tab_messages).setIcon(f.tab_indicator_ab_icon_messages).setTabListener(this));
        }
        if (bundle != null) {
            this.d.setSelectedNavigationItem(bundle.getInt("tab", 0));
        }
    }

    private void b() {
        List b = com.mavenir.android.messaging.provider.d.b(this);
        int size = b != null ? b.size() : 0;
        if (getActionBar().getTabCount() == 3) {
            getActionBar().getTabAt(2).setIcon(size > 0 ? f.tab_indicator_ab_icon_messages_unread : f.tab_indicator_ab_icon_messages);
        }
    }

    private void a(boolean z) {
        View findViewById = findViewById(g.loginStatusBar);
        if (findViewById != null && FgVoIP.U().E()) {
            int i;
            if (z) {
                i = 0;
            } else {
                i = getResources().getColor(e.app_unregistered);
            }
            findViewById.setBackgroundColor(i);
        }
    }

    private void c() {
        if (aa.a((Context) this).a()) {
            d();
        }
    }

    private void d() {
        try {
            PreferenceWhitelistFragment.a(false).show(getSupportFragmentManager(), "whitelistDialog");
        } catch (Throwable e) {
            q.c("MainTabActivity", "displayWhiteListScreen()", e);
        }
    }

    private void a(Intent intent) {
        if (!CallManager.a()) {
            FgVoIP.U().a(getSupportActionBar(), 1, "");
        } else if (intent.hasExtra("MainTabExtras.ExtraOnHoldName")) {
            FgVoIP.U().a(getSupportActionBar(), 1, "Active call - " + intent.getStringExtra("MainTabExtras.ExtraOnHoldName"));
        }
        supportInvalidateOptionsMenu();
    }

    private void e() {
        if (CallManager.a()) {
            Intent intent = new Intent(this, FgVoIP.U().aH());
            intent.setAction("InCallActions.SupplementaryScreenCompleted");
            startActivity(intent);
            return;
        }
        invalidateOptionsMenu();
    }

    private void f() {
        FgVoIP.U().a(getSupportActionBar(), 1, "");
        invalidateOptionsMenu();
    }

    private void g() {
        if (m.i() == 10 && m.h() == 50 && m.g() == 80) {
            m.d(20);
            m.c(30);
        }
    }

    private void h() {
        startActivity(new Intent(this, FgVoIP.U().ah() ? PreferenceMainActivity.class : PreferenceGeneralActivity.class));
    }

    private void i() {
        startActivity(new Intent(this, PreferenceWhitelistActivity.class));
    }

    private void j() {
        String b = j.b(6);
        if (TextUtils.isEmpty(b)) {
            b = getString(k.exception_url_help_6);
        }
        startActivity(new Intent("android.intent.action.VIEW", Uri.parse(b)));
    }

    private void k() {
        startActivity(new Intent(this, AboutActivity.class));
    }

    private void l() {
        if (FgVoIP.U().at()) {
            q.a("MainTabActivity", "login(): user requested logout");
            FgVoIP.U().a("com.fgmicrotec.mobile.android.voip.LogoutFromServerReq");
        } else if (FgVoIP.U().an()) {
            q.a("MainTabActivity", "login(): user requested login");
            FgVoIP.U().a("com.fgmicrotec.mobile.android.voip.LoginToServerReq");
        } else {
            q.c("MainTabActivity", "login(): Failed - no network connection");
            b(true);
        }
    }

    private void m() {
        q.b("MainTabActivity", "exitApplication() - user called exit from main menu");
        FgVoIP.U().a();
        finish();
    }

    private void b(boolean z) {
        if (this.e != null && this.e.isShowing()) {
            this.e.cancel();
        }
        boolean C = l.a((Context) this).C();
        if (!C && !z) {
            q.a("MainTabActivity", "displayAddToWhitelistDialog(): not connected");
        } else if (FgVoIP.U().at() || !FgVoIP.U().ax()) {
            q.a("MainTabActivity", "displayAddToWhitelistDialog(): already logged");
        } else {
            final String P = l.a((Context) this).P();
            final String Q = l.a((Context) this).Q();
            if (!C || aa.a((Context) this).b(P, Q)) {
                q.a("MainTabActivity", "displayAddToWhitelistDialog(): not wifi or ignored: ssid: " + P + ", bssid: " + Q);
            } else if (aa.a((Context) this).a(P, Q)) {
                q.a("MainTabActivity", "displayAddToWhitelistDialog(): in whitelist: ssid: " + P + ", bssid: " + Q);
            } else {
                CharSequence string = getString(k.dialog_whitelist_add_message, new Object[]{P});
                Builder builder = new Builder(this);
                builder.setTitle(null);
                builder.setIcon(17301659);
                builder.setMessage(string);
                builder.setOnCancelListener(new OnCancelListener(this) {
                    final /* synthetic */ MainTabActivity a;

                    {
                        this.a = r1;
                    }

                    public void onCancel(DialogInterface dialogInterface) {
                    }
                });
                builder.setNegativeButton(k.dialog_whitelist_ignore, new OnClickListener(this) {
                    final /* synthetic */ MainTabActivity c;

                    public void onClick(DialogInterface dialogInterface, int i) {
                        aa.a(this.c).a(P, Q, true);
                        FgVoIP.U().d(false);
                    }
                });
                builder.setPositiveButton(k.dialog_whitelist_add, new OnClickListener(this) {
                    final /* synthetic */ MainTabActivity c;

                    public void onClick(DialogInterface dialogInterface, int i) {
                        aa.a(this.c).a(P, Q, false);
                        FgVoIP.U().d(false);
                        this.c.l();
                    }
                });
                this.e = builder.create();
                this.e.show();
            }
        }
    }

    private void a(double d, double d2, double d3) {
        if (d != 0.0d && d2 != 0.0d && d3 != 0.0d) {
            if (!(this.h == 0.0d && this.i == 0.0d && this.j == 0.0d)) {
                double sqrt = Math.sqrt((((d - this.h) * (d - this.h)) + ((d2 - this.i) * (d2 - this.i))) + ((d3 - this.j) * (d3 - this.j)));
                if (sqrt > 3.0d) {
                    q.a("MainTabActivity", "magnitude " + sqrt);
                }
            }
            this.h = d;
            this.i = d2;
            this.j = d3;
        }
    }
}
