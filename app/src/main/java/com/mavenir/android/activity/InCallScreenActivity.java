package com.mavenir.android.activity;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.media.AudioManager.OnAudioFocusChangeListener;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.Toast;
import com.fgmicrotec.mobile.android.fgmag.VoIP;
import com.fgmicrotec.mobile.android.fgvoip.FgVoIP;
import com.fgmicrotec.mobile.android.fgvoip.d;
import com.fgmicrotec.mobile.android.fgvoip.f.g;
import com.fgmicrotec.mobile.android.fgvoip.f.h;
import com.fgmicrotec.mobile.android.fgvoip.f.k;
import com.fgmicrotec.mobile.android.fgvoipcommon.CallService;
import com.mavenir.android.common.CallManager;
import com.mavenir.android.common.f;
import com.mavenir.android.common.l;
import com.mavenir.android.common.q;
import com.mavenir.android.common.w;
import com.mavenir.android.fragments.CallAnswerFragment;
import com.mavenir.android.fragments.CallButtonFragment;
import com.mavenir.android.fragments.CallCardFragment;
import com.mavenir.android.fragments.CallConferenceManagerFragment;
import com.mavenir.android.fragments.CallDialpadFragment;
import com.mavenir.android.vtow.activation.ActivationAdapter;

public class InCallScreenActivity extends FragmentActivity implements OnAudioFocusChangeListener, f {
    private View a;
    private CallCardFragment b;
    private CallButtonFragment c;
    private CallAnswerFragment d;
    private CallDialpadFragment e;
    private CallConferenceManagerFragment f;
    private d g = null;
    private com.mavenir.android.fragments.b h = null;
    private CallManager i;
    private FrameLayout j = null;
    private ImageButton k = null;
    private View l = null;
    private boolean m = false;
    private Handler n = new Handler();
    private Runnable o = new Runnable(this) {
        final /* synthetic */ InCallScreenActivity a;

        {
            this.a = r1;
        }

        public void run() {
            this.a.n.removeCallbacks(this.a.o);
            this.a.a();
        }
    };

    private enum a {
        SHOW_ALL,
        SHOW_END,
        SHOW_NONE
    }

    private enum b {
        SHOW,
        HIDE,
        NO_CHANGE
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (CallManager.a()) {
            finish();
            return;
        }
        q.a("InCallScreenActivity", "onCreate()");
        getWindow().addFlags(2654208);
        requestWindowFeature(1);
        setContentView(h.call_screen_fragment);
        sendBroadcast(new Intent("com.fgmicrotec.mobile.android.voip.CancelIncomingCallInvitationTimeoutReq"));
        z();
        y();
        a(getIntent());
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        a(intent);
    }

    protected void onDestroy() {
        if (this.i != null) {
            q.a("InCallScreenActivity", "onDestroy(): Call manager not destroyed yet, do it now");
            this.i.e();
            this.i = null;
        }
        super.onDestroy();
    }

    public void onAudioFocusChange(int i) {
        switch (i) {
            case ActivationAdapter.VERS_INITIAL /*-3*/:
                q.a("InCallScreenActivity", "App has requested transient duck audio focus!");
                return;
            case -2:
                q.a("InCallScreenActivity", "App has requested transient audio focus!");
                return;
            case -1:
                q.a("InCallScreenActivity", "App has abandoned audio focus!");
                return;
            case 1:
                q.a("InCallScreenActivity", "App has requested audio focus!");
                return;
            default:
                q.a("InCallScreenActivity", "App has requested: " + i);
                return;
        }
    }

    public void onBackPressed() {
        if (this.g != null) {
            this.g.b();
        }
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (keyEvent.getAction() == 0) {
            switch (keyEvent.getKeyCode()) {
                case 5:
                    q.a("InCallScreenActivity", "dispatchKeyEvent(): Keycode CALL");
                    if (this.i == null) {
                        q.d("InCallScreenActivity", "dispatchKeyEvent(): Keycode CALL, call manager is NULL");
                        break;
                    } else if (this.i.an() == com.mavenir.android.common.CallManager.b.INCOMING) {
                        q.a("InCallScreenActivity", "dispatchKeyEvent(): KEYCODE_CALL, answering call...");
                        this.i.P();
                        return true;
                    } else if (this.i.an() == com.mavenir.android.common.CallManager.b.ONE_CALL) {
                        q.a("InCallScreenActivity", "dispatchKeyEvent(): KEYCODE_CALL, ending call...");
                        this.i.T();
                        return true;
                    }
                    break;
                case VoIP.ERR_SDP_NEGOTIATION_FAILED /*24*/:
                case VoIP.ERR_NO_SRTP_AGREEMENT /*25*/:
                    int i;
                    if (keyEvent.getKeyCode() == 25) {
                        i = -1;
                    } else {
                        i = 1;
                    }
                    if (this.i == null) {
                        q.d("InCallScreenActivity", "dispatchKeyEvent(): Keycode VOLUME, call manager is NULL");
                        break;
                    }
                    q.b("InCallScreenActivity", "dispatchKeyEvent(): Keycode VOLUME");
                    if (this.i.b(i)) {
                        return true;
                    }
                    break;
            }
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    private void y() {
        if (this.a == null) {
            this.a = findViewById(g.call_screen_call_card_container);
        }
        if (this.b == null) {
            this.b = (CallCardFragment) getSupportFragmentManager().findFragmentById(g.callCardFragment);
            this.b.a(this.i);
        }
        if (this.c == null) {
            this.c = (CallButtonFragment) getSupportFragmentManager().findFragmentById(g.callButtonFragment);
            this.c.getView().setVisibility(4);
            this.c.a(this.i);
        }
        if (this.d == null) {
            this.d = (CallAnswerFragment) getSupportFragmentManager().findFragmentById(g.callAnswerFragment);
            this.d.a(this.i);
        }
        if (this.e == null) {
            this.e = (CallDialpadFragment) getSupportFragmentManager().findFragmentById(g.callDialpadFragment);
            this.e.getView().setVisibility(4);
            this.e.a(this.i);
        }
        if (this.f == null) {
            this.f = (CallConferenceManagerFragment) getSupportFragmentManager().findFragmentById(g.callConferenceManagerFragment);
            this.f.getView().setVisibility(4);
            this.f.a(this.i);
        }
        if (this.h == null && FgVoIP.U().s()) {
            this.h = (com.mavenir.android.fragments.b) getSupportFragmentManager().findFragmentById(g.callEarlyActionsFragment);
            this.h.getView().setVisibility(4);
            this.h.a(this.i);
        }
    }

    private void z() {
        this.i = new CallManager(this, this);
    }

    private void a(Intent intent) {
        if (intent != null) {
            q.a("InCallScreenActivity", "processIntentActions(): intent: " + intent.getAction());
            if (this.i != null) {
                this.i.a(intent);
                return;
            } else {
                q.c("InCallScreenActivity", "processIntentActions(): call manager is null");
                return;
            }
        }
        q.c("InCallScreenActivity", "processIntentActions(): intent is null");
        a();
    }

    public void a() {
        B();
        if (this.i != null) {
            if (this.i.h()) {
                q.a("InCallScreenActivity", "finishInCallScreen(): Displayed popup, delaying screen closing...");
                this.n.postDelayed(this.o, 500);
                return;
            }
            this.n.removeCallbacks(this.o);
            this.i.M();
            this.i.e();
            this.i = null;
            finish();
            q.b("InCallScreenActivity", " finishInCallScreen() finished");
        }
    }

    private void a(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, b bVar, a aVar) {
        int i;
        int i2 = 4;
        this.d.getView().setVisibility(z ? 0 : 4);
        View view = this.c.getView();
        if (z2) {
            i = 0;
        } else {
            i = 4;
        }
        view.setVisibility(i);
        view = this.b.getView();
        if (z3) {
            i = 0;
        } else {
            i = 4;
        }
        view.setVisibility(i);
        view = this.f.getView();
        if (z4) {
            i = 0;
        } else {
            i = 4;
        }
        view.setVisibility(i);
        View view2 = this.e.getView();
        if (z5) {
            i2 = 0;
        }
        view2.setVisibility(i2);
        if (FgVoIP.U().aj()) {
            if (bVar == b.SHOW && this.i != null) {
                a(this.i.q());
            } else if (bVar == b.HIDE) {
                s();
            }
        }
        if (FgVoIP.U().s()) {
            if (aVar == a.SHOW_NONE) {
                this.h.getView().setVisibility(8);
            } else if (aVar == a.SHOW_ALL) {
                this.h.getView().setVisibility(0);
                this.h.a(true, true, true);
            } else {
                this.h.getView().setVisibility(0);
                this.h.a(false, true, true);
            }
        }
        if (FgVoIP.U().aj() && !z3 && !z5 && !this.m) {
            this.j = (FrameLayout) findViewById(g.inCallVideoFragment);
            this.j.setVisibility(0);
            this.j.setBackgroundColor(-16777216);
            this.k = (ImageButton) findViewById(g.call_screen_video_off_button);
            this.k.setVisibility(0);
            this.k.setEnabled(true);
            if (A()) {
                this.m = true;
                this.k.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ InCallScreenActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(View view) {
                        this.a.i.ad();
                    }
                });
                return;
            }
            this.j.setVisibility(8);
            this.k.setVisibility(8);
            this.b.getView().setVisibility(0);
        }
    }

    private boolean A() {
        if (this.l != null) {
            return true;
        }
        if (w.a() != null) {
            this.l = w.a().c(this);
            LayoutParams layoutParams = new LayoutParams(-1, -1);
            if (this.l != null) {
                this.j.addView(this.l, layoutParams);
                q.b("InCallScreenActivity", "IN CALL SCREEN - ON CREATE - createVideoView() SUCCESS");
                return true;
            }
            q.b("InCallScreenActivity", "IN CALL SCREEN - ON CREATE - createVideoView() NON-SUCCESS");
            return false;
        }
        q.d("InCallScreenActivity", "MainTabActivity TSM is null");
        return false;
    }

    public void b() {
        a(false, true, false, false, false, b.HIDE, a.SHOW_NONE);
    }

    public void c() {
        a(false, true, true, false, false, b.SHOW, a.SHOW_NONE);
        B();
    }

    private void B() {
        if (this.l != null) {
            this.m = false;
            this.l.setVisibility(8);
            this.j.removeView(this.l);
            this.l = null;
        }
    }

    public int d() {
        if (this.j != null) {
            return this.j.getWidth();
        }
        return 0;
    }

    public int e() {
        if (this.j != null) {
            return this.j.getHeight();
        }
        return 0;
    }

    public void a(boolean z) {
        boolean z2;
        q.a("InCallScreenActivity", "displayDialpad(): " + z);
        if (this.b.getView().getVisibility() == 0) {
            z2 = true;
        } else {
            z2 = false;
        }
        a(false, true, z2, false, z, b.NO_CHANGE, a.SHOW_END);
    }

    public void f() {
        q.a("InCallScreenActivity", "displayOutgoingCallScreen()");
        a(false, true, true, false, false, b.HIDE, a.SHOW_ALL);
        this.c.a();
        this.b.a(this.i.p(), this.i.q(), null, this.i.r(), false);
        this.b.c(false);
        this.b.d(true);
        this.b.d(getString(k.call_screen_status_dialing));
    }

    public void g() {
        q.a("InCallScreenActivity", "displayIncomingCallScreen()");
        a(true, false, true, false, false, b.HIDE, a.SHOW_NONE);
        this.b.a(this.i.p(), this.i.q(), null, this.i.r(), false);
        this.b.c(false);
        this.b.d(true);
        this.b.d(getString(k.call_screen_status_incoming));
        this.d.a(true);
        if (FgVoIP.U().P()) {
            this.d.e();
        }
    }

    public void h() {
        q.a("InCallScreenActivity", "displaySupplementaryIncomingCall()");
        a(true, false, true, false, false, b.HIDE, a.SHOW_NONE);
        this.b.a(this.i.w(), this.i.x(), null, this.i.y(), false);
        this.b.c(false);
        this.b.d(true);
        this.b.d(getString(k.call_screen_status_incoming));
        this.d.a(true);
        if (FgVoIP.U().P()) {
            this.d.e();
        }
    }

    public void b(boolean z) {
        q.a("InCallScreenActivity", "displayOnOneCallScreen()");
        if (this.i == null || !this.i.u()) {
            a(false, true, true, false, false, b.SHOW, a.SHOW_END);
        } else {
            a(false, true, false, false, false, b.HIDE, a.SHOW_END);
        }
        this.c.b();
        this.b.a(false);
        if (this.i != null) {
            this.b.a(this.i.p(), this.i.q(), null, this.i.r(), false);
        }
        this.b.c(true);
        this.b.d(false);
        this.b.f(false);
        this.b.g(z);
    }

    public void i() {
        q.a("InCallScreenActivity", "displayOnTwoCallsScreen()");
        a(false, true, true, false, false, b.HIDE, a.SHOW_END);
        this.c.c();
        this.b.a(true);
        this.b.a(this.i.w(), this.i.x(), null, this.i.y(), false);
        this.b.b(this.i.p(), this.i.q(), null, this.i.r(), false);
        this.b.c(true);
        this.b.f(false);
        this.b.g(true);
    }

    public void c(boolean z) {
        q.a("InCallScreenActivity", "displayOnConferenceCallScreen() isVideo = " + this.i.u());
        if (this.i.u()) {
            a(false, true, false, false, false, b.HIDE, a.SHOW_END);
        } else {
            a(false, true, true, false, false, b.HIDE, a.SHOW_END);
        }
        this.b.a(false);
        this.b.a(null, null, null, null, true);
        if (z) {
            this.c.d();
            this.b.c(false);
            this.b.d(true);
            this.b.d(getString(k.call_screen_status_conf_create));
            return;
        }
        this.c.e();
        this.b.c(true);
        this.b.d(false);
        this.b.g(true);
        this.f.a();
    }

    public void a(int i) {
        q.a("InCallScreenActivity", "displayOnConferenceEnded(): reason code: " + i);
        a(false, true, true, false, false, b.HIDE, a.SHOW_END);
        this.c.a(false);
        this.b.a();
        this.b.a(null, null, null, null, true);
        this.b.c(true);
        this.b.d(true);
        this.b.d(this.i.c(i));
        h(false);
    }

    public void d(boolean z) {
        boolean z2;
        q.a("InCallScreenActivity", "displayManageConferenceScreen(): " + z);
        if (this.b.getView().getVisibility() == 0) {
            z2 = true;
        } else {
            z2 = false;
        }
        a(false, true, z2, z, false, b.NO_CHANGE, a.SHOW_END);
    }

    public void j() {
        q.a("InCallScreenActivity", "displayAddNewCallScreen()");
        Intent intent = new Intent("com.mavenir.action.LAUNCH_MAIN_TAB");
        intent.putExtra("MainTabExtras.ExtraOnHoldName", this.i.p());
        startActivity(intent);
    }

    public void k() {
        q.a("InCallScreenActivity", "displayAddNewParticipantScreen()");
        this.n.postDelayed(new Runnable(this) {
            final /* synthetic */ InCallScreenActivity a;

            {
                this.a = r1;
            }

            public void run() {
                Intent intent = new Intent(FgVoIP.U(), FgVoIP.U().aH());
                intent.setAction("InCallActions.AddPArticipantScreenCompleted");
                intent.putExtra("InCallExtras.ExtraSupplementaryNumber", "12147771617");
                this.a.startActivity(intent);
            }
        }, 1000);
    }

    public void l() {
        q.a("InCallScreenActivity", "displayOutgoingConsultationCallScreen()");
        a(false, true, true, false, false, b.HIDE, a.SHOW_ALL);
        this.c.a();
        this.b.a(this.i.w(), this.i.x(), null, this.i.y(), false);
        this.b.c(false);
        this.b.d(true);
        this.b.d(getString(k.call_screen_status_dialing));
    }

    public void a(int i, boolean z, int i2, String str) {
        q.a("InCallScreenActivity", "displayOnCallEnded(): reason code: " + i);
        a(false, true, true, false, false, b.HIDE, a.SHOW_END);
        this.c.a(false);
        if (FgVoIP.U().s()) {
            this.h.b(false, false, false);
            this.h.a(0);
        }
        this.b.a();
        if (this.i != null) {
            this.b.a(this.i.p(), this.i.q(), null, this.i.r(), false);
        }
        this.b.c(true);
        this.b.d(true);
        if (this.i != null) {
            this.b.d(this.i.c(i));
        }
        if (this.i != null) {
            this.i.W();
        }
        if (FgVoIP.U().x()) {
            if (i == -98) {
                Toast.makeText(this, getString(k.call_ended_no_wifi), 1).show();
            } else if (i == -99) {
                Toast.makeText(this, getString(k.call_ended_cannot_login), 1).show();
            }
        }
        h(false);
        if (FgVoIP.U().L() && z) {
            com.fgmicrotec.mobile.android.fgmag.DataConnectionManager.a H = l.a((Context) this).H();
            Intent intent;
            if (System.currentTimeMillis() - CallManager.d() <= 57600000 || i2 <= 600000) {
                intent = new Intent(this, CallService.class);
                intent.setAction("UserCallRatingDialogActions.CallRatingRes");
                intent.putExtra("UserCallRatingDialogExtras.ExtraRatingCallDuration", i2);
                intent.putExtra("UserCallRatingDialogExtras.ExtraRatingNetworkBearer", H.name());
                intent.putExtra("UserCallRatingDialogExtras.ExtraRatingPhoneNumber", str);
                intent.putExtra("UserCallRatingDialogExtras.ExtraRatingStars", -1);
                startService(intent);
                return;
            }
            intent = new Intent();
            intent.setAction("UserCallRatingDialogActions.CallRatingReq");
            intent.putExtra("UserCallRatingDialogExtras.ExtraRatingCallDuration", i2);
            intent.putExtra("UserCallRatingDialogExtras.ExtraRatingNetworkBearer", H.name());
            intent.putExtra("UserCallRatingDialogExtras.ExtraRatingPhoneNumber", str);
            intent.addFlags(268435456);
            startActivity(intent);
        }
    }

    public void m() {
    }

    public void e(boolean z) {
        q.a("InCallScreenActivity", "displayOnOneOfTwoCallsEnded(): " + (z ? " first" : " second") + " call ended");
        a(false, false, true, false, false, b.HIDE, a.SHOW_END);
        this.b.g(false);
        if (z) {
            this.b.a(this.i.w(), this.i.x(), null, this.i.y(), false);
            this.b.b(this.i.p(), this.i.q(), null, this.i.r(), false);
        } else {
            this.b.a(this.i.p(), this.i.q(), null, this.i.r(), false);
            this.b.b(this.i.w(), this.i.x(), null, this.i.y(), false);
        }
        this.b.f(true);
        this.b.h(getString(k.call_ended));
    }

    public void f(boolean z) {
        this.c.a(false, z);
        if (z && this.g != null && this.g.isAdded()) {
            this.g.a();
        }
    }

    public void n() {
        this.c.a(true, this.i.i());
        this.b.b();
        if (this.g != null && this.g.isAdded()) {
            this.g.a(this.i.i());
        }
    }

    public void o() {
        if (!(!this.b.c() || this.g == null || this.i == null)) {
            this.g.b(this.i.t());
        }
        if (this.i != null) {
            q.a("InCallScreenActivity", "  updateHeldIndicationState()" + this.i.k());
        }
    }

    public void p() {
        this.c.p(false);
    }

    public void q() {
        if (this.k != null) {
            this.k.setEnabled(false);
        }
    }

    public void g(boolean z) {
        boolean z2 = true;
        this.c.p(true);
        CallButtonFragment callButtonFragment = this.c;
        if (z) {
            z2 = false;
        }
        callButtonFragment.q(z2);
    }

    public void r() {
        this.b.d();
        this.b.g(false);
    }

    public void a(String str) {
        q.a("InCallScreenActivity", "addInCallShareFragment: phone: " + str);
        if (str == null) {
            s();
            return;
        }
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        String str2 = "InCallShare-" + str;
        Fragment b = b(str);
        if (this.g == b) {
            q.a("InCallScreenActivity", "addInCallShareFragment: exists " + str2);
            return;
        }
        q.a("InCallScreenActivity", "addInCallShareFragment: does not exists " + str2);
        FragmentTransaction beginTransaction = supportFragmentManager.beginTransaction();
        if (this.g != null) {
            q.a("InCallScreenActivity", "addInCallShareFragment: removing previous " + this.g.getTag());
            this.g.d();
            beginTransaction.detach(this.g);
        }
        if (b.isAdded()) {
            q.a("InCallScreenActivity", "addInCallShareFragment: reattaching existing one " + b.getTag());
            beginTransaction.attach(b);
        } else {
            q.a("InCallScreenActivity", "addInCallShareFragment: adding new as " + str2);
            beginTransaction.add(g.inCallShareFragment, b, str2);
        }
        beginTransaction.commitAllowingStateLoss();
        this.g = b;
        a(true, false);
    }

    public d b(String str) {
        Throwable e;
        d dVar;
        try {
            dVar = (d) getSupportFragmentManager().findFragmentByTag("InCallShare-" + str);
            if (dVar == null) {
                try {
                    q.a("InCallScreenActivity", "getInCallShareFragment: creating new for " + str);
                    dVar = (d) Class.forName("com.mavenir.android.rcs.fragments.InCallShareFragment").newInstance();
                    Bundle bundle = new Bundle();
                    bundle.putString("ARG_PHONE", str);
                    bundle.putString("ARG_NAME", this.i.p());
                    dVar.setArguments(bundle);
                    dVar.a(this.i);
                    return dVar;
                } catch (ClassNotFoundException e2) {
                    this.g = null;
                    return dVar;
                } catch (InstantiationException e3) {
                    e = e3;
                    this.g = null;
                    q.c("InCallScreenActivity", "startInCallShareFragment", e);
                    return dVar;
                } catch (IllegalAccessException e4) {
                    e = e4;
                    this.g = null;
                    q.c("InCallScreenActivity", "startInCallShareFragment", e);
                    return dVar;
                }
            }
            q.a("InCallScreenActivity", "getInCallShareFragment: found existing one for " + str);
            return dVar;
        } catch (ClassNotFoundException e5) {
            dVar = null;
            this.g = null;
            return dVar;
        } catch (Throwable e6) {
            e = e6;
            dVar = null;
            this.g = null;
            q.c("InCallScreenActivity", "startInCallShareFragment", e);
            return dVar;
        } catch (Throwable e62) {
            e = e62;
            dVar = null;
            this.g = null;
            q.c("InCallScreenActivity", "startInCallShareFragment", e);
            return dVar;
        }
    }

    public void s() {
        if (this.g != null) {
            q.a("InCallScreenActivity", "removeInCallShareFragment: " + this.g.getTag());
            FragmentManager supportFragmentManager = getSupportFragmentManager();
            this.g.d();
            supportFragmentManager.beginTransaction().detach(this.g).commitAllowingStateLoss();
            a(false, false);
        }
    }

    public void t() {
        if (this.g != null) {
            this.g.c();
        }
    }

    public void a(boolean z, boolean z2) {
        this.c.y(z);
        this.c.z(z2);
    }

    public void h(boolean z) {
        if (z) {
            setRequestedOrientation(-1);
        } else {
            setRequestedOrientation(1);
        }
    }

    public void i(boolean z) {
        this.c.h(z);
    }

    public void j(boolean z) {
        this.c.k(z);
    }

    public void u() {
        if (this.h != null) {
            q.a("InCallScreenActivity", "notifyOutgoingCallIsRinging()");
            this.h.a();
        }
    }

    public void c(final String str) {
        Builder builder = new Builder(this);
        builder.setTitle("");
        final CharSequence[] stringArray = getResources().getStringArray(com.fgmicrotec.mobile.android.fgvoip.f.b.reject_call_sms_texts);
        builder.setItems(stringArray, new DialogInterface.OnClickListener(this) {
            final /* synthetic */ InCallScreenActivity c;

            public void onClick(DialogInterface dialogInterface, int i) {
                this.c.i.Q();
                if (i == stringArray.length - 1) {
                    a.a(this.c, str, null, false);
                } else {
                    a.a(this.c, str, stringArray[i], true);
                }
                dialogInterface.dismiss();
            }
        });
        builder.setOnCancelListener(new OnCancelListener(this) {
            final /* synthetic */ InCallScreenActivity a;

            {
                this.a = r1;
            }

            public void onCancel(DialogInterface dialogInterface) {
                this.a.i.Q();
            }
        });
        AlertDialog create = builder.create();
        create.setCancelable(true);
        create.setCanceledOnTouchOutside(true);
        create.show();
    }

    public void v() {
        if (FgVoIP.U().s()) {
            q.a("InCallScreenActivity", "notifyRejectWithAction()");
            a(false, true, true, false, false, b.HIDE, a.SHOW_ALL);
            if (this.h != null) {
                this.h.b();
            }
        }
    }

    public void w() {
        if (FgVoIP.U().s()) {
            q.a("InCallScreenActivity", "notifyAnswerCall()");
            if (this.h != null) {
                this.h.c();
            }
        }
    }

    public void x() {
        q.a("InCallScreenActivity", "enableMuteOperation()");
        this.c.m(true);
    }

    public void b(int i) {
    }
}
