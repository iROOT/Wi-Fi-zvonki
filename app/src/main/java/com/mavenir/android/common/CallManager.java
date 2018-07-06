package com.mavenir.android.common;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.media.AudioManager.OnAudioFocusChangeListener;
import android.media.ToneGenerator;
import android.net.ConnectivityManager;
import android.net.ConnectivityManager.NetworkCallback;
import android.net.NetworkInfo.State;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Handler;
import android.support.v4.content.o;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.fgmicrotec.mobile.android.fgmag.SimpleCodecAL;
import com.fgmicrotec.mobile.android.fgmag.VoIP;
import com.fgmicrotec.mobile.android.fgvoip.FgVoIP;
import com.fgmicrotec.mobile.android.fgvoip.MediaBroadcastReceiver;
import com.fgmicrotec.mobile.android.fgvoip.e;
import com.fgmicrotec.mobile.android.fgvoip.f.k;
import com.fgmicrotec.mobile.android.fgvoipcommon.CallService;
import com.fgmicrotec.mobile.android.fgvoipcommon.f;
import com.mavenir.android.settings.c.h;
import com.mavenir.android.settings.c.p;
import com.mavenir.android.settings.c.v;
import com.mavenir.android.vtow.activation.ActivationAdapter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;

public class CallManager implements OnAudioFocusChangeListener, com.mavenir.android.c.a.c {
    private static boolean a = false;
    private static final HashMap<Character, Integer> au = new HashMap();
    private static boolean b = false;
    private static boolean c = false;
    private static String d = null;
    private static long e = 0;
    private static String p;
    private static int q = 0;
    private int A = 0;
    private boolean B = false;
    private boolean C;
    private boolean D = false;
    private boolean E = false;
    private boolean F = false;
    private boolean G = false;
    private boolean H = false;
    private boolean I = false;
    private boolean J = false;
    private int K = 0;
    private int L = 2;
    private boolean M = false;
    private String N;
    private String O;
    private Bitmap P = null;
    private long Q = 0;
    private boolean R = false;
    private boolean S = false;
    private int T = 0;
    private int U = 2;
    private boolean V = false;
    private String W;
    private String X;
    private Bitmap Y = null;
    private long Z = 0;
    private Runnable aA = new Runnable(this) {
        final /* synthetic */ CallManager a;

        {
            this.a = r1;
        }

        public void run() {
            q.a("CallManager", "running handleConfirmVideoViewCreated");
            Intent intent = new Intent();
            intent.setAction("com.fgmicrotec.mobile.android.voip.ShouldDisplayVideoViewRes");
            intent.putExtra("extra_video_view_width", this.a.g.d());
            intent.putExtra("extra_video_view_height", this.a.g.e());
            this.a.f.sendBroadcast(intent);
        }
    };
    private Runnable aB = new Runnable(this) {
        final /* synthetic */ CallManager a;

        {
            this.a = r1;
        }

        public void run() {
            q.a("CallManager", "running handleRestoreSpeakerState");
            if (!this.a.ao) {
                return;
            }
            if (this.a.at) {
                this.a.aI();
            } else if (!this.a.l.isSpeakerphoneOn()) {
                this.a.l.setSpeakerphoneOn(this.a.ao);
            }
        }
    };
    private Runnable aC = new Runnable(this) {
        final /* synthetic */ CallManager a;

        {
            this.a = r1;
        }

        public void run() {
            this.a.u = false;
        }
    };
    private Runnable aD = new Runnable(this) {
        final /* synthetic */ CallManager a;

        {
            this.a = r1;
        }

        public void run() {
            this.a.a(false);
        }
    };
    private Runnable aE = new Runnable(this) {
        final /* synthetic */ CallManager a;

        {
            this.a = r1;
        }

        public void run() {
            new a(this.a).execute(new Void[0]);
        }
    };
    private boolean aa = false;
    private boolean ab = false;
    private int ac = 0;
    private long ad = 0;
    private List<h> ae;
    private String af = "";
    private boolean ag = false;
    private boolean ah = false;
    private int ai = 1;
    private String aj;
    private String ak;
    private String al;
    private String am = null;
    private boolean an = false;
    private boolean ao = false;
    private boolean ap = false;
    private boolean aq = false;
    private boolean ar = false;
    private boolean as = false;
    private boolean at = false;
    private Runnable av = new Runnable(this) {
        final /* synthetic */ CallManager a;

        {
            this.a = r1;
        }

        public void run() {
            if (this.a.h != b.CALL_ENDED) {
                q.a("CallManager", "processCallStateChange(): VCC enabled, ending call...");
                this.a.T();
                return;
            }
            q.a("CallManager", "Inside mCallEndAfterCSrouted runnable : call  ended mCurrentScreen == CallScreen.CALL_ENDED ");
        }
    };
    private c aw = new c(this, null);
    private Runnable ax = new Runnable(this) {
        final /* synthetic */ CallManager a;

        {
            this.a = r1;
        }

        public void run() {
            q.a("CallManager", "handleTerminateCall");
            this.a.an = g.a().k();
            if (!this.a.s) {
                this.a.a(this.a.N, this.a.O, this.a.L, this.a.Q);
            }
            this.a.C = false;
            this.a.G = false;
            FgVoIP.U().e(false);
            if (this.a.h == b.CALL_ENDED) {
                this.a.g.a();
            }
        }
    };
    private Runnable ay = new Runnable(this) {
        final /* synthetic */ CallManager a;

        {
            this.a = r1;
        }

        public void run() {
            q.a("CallManager", "running handleRejectIncomingCallInvitation");
            this.a.Q();
        }
    };
    private Runnable az = new Runnable(this) {
        final /* synthetic */ CallManager a;

        {
            this.a = r1;
        }

        public void run() {
            q.a("CallManager", "handleCancelOutgoingCallInvitation: there was an error, canceling call");
            this.a.T();
        }
    };
    private Activity f = null;
    private f g = null;
    private b h = null;
    private CallServiceIntentsReceiver i = null;
    private ComponentName j;
    private d k = null;
    private AudioManager l = null;
    private e m = null;
    private ToneGenerator n;
    private com.mavenir.android.c.a o;
    private boolean r = false;
    private boolean s = false;
    private boolean t;
    private boolean u = false;
    private Handler v;
    private BluetoothService w;
    private ConnectivityManager x;
    private NetworkCallback y = null;
    private AlertDialog z = null;

    public class CallServiceIntentsReceiver extends BroadcastReceiver {
        final /* synthetic */ CallManager a;

        public CallServiceIntentsReceiver(CallManager callManager) {
            this.a = callManager;
        }

        public void onReceive(Context context, Intent intent) {
            boolean z = true;
            if (intent != null && intent.getAction() != null) {
                q.b("CallManager", "CallServiceIntentsReceiver: onReceive() " + intent.getAction());
                if ("com.fgmicrotec.mobile.android.voip.LoginToServerCnf".equals(intent.getAction())) {
                    a(intent);
                } else if ("com.fgmicrotec.mobile.android.voip.CallInviteCnf".equals(intent.getAction())) {
                    b(intent);
                } else if ("com.fgmicrotec.mobile.android.voip.InvitationRingingInd".equals(intent.getAction())) {
                    c(intent);
                } else if ("CallManager.AcceptInvitationReq".equals(intent.getAction())) {
                    if (this.a.C) {
                        q.a("CallManager", "onReceive(): INVITATION_ACCEPT_REQ, ending call...");
                        this.a.T();
                        return;
                    }
                    q.a("CallManager", "onReceive(): INVITATION_ACCEPT_REQ, answering call...");
                    this.a.P();
                } else if ("com.fgmicrotec.mobile.android.voip.InvitationAcceptedInd".equals(intent.getAction())) {
                    d(intent);
                } else if ("com.fgmicrotec.mobile.android.voip.InvitationRejectedInd".equals(intent.getAction())) {
                    e(intent);
                } else if ("com.fgmicrotec.mobile.android.voip.InvitationSessionProgressInd".equals(intent.getAction())) {
                    f(intent);
                } else if ("CallManager.IncomingCallCanceledInd".equals(intent.getAction())) {
                    g(intent);
                } else if ("com.fgmicrotec.mobile.android.voip.SessionIdChangedInd".equals(intent.getAction())) {
                    h(intent);
                } else if ("com.fgmicrotec.mobile.android.voip.CallTerminateCnf".equals(intent.getAction())) {
                    i(intent);
                } else if ("com.fgmicrotec.mobile.android.voip.SessionEndedInd".equals(intent.getAction())) {
                    j(intent);
                } else if ("com.fgmicrotec.mobile.android.voip.LogoutFromServerInd".equals(intent.getAction())) {
                    a();
                } else if ("com.fgmicrotec.mobile.android.voip.CallHoldCnf".equals(intent.getAction())) {
                    k(intent);
                } else if ("com.fgmicrotec.mobile.android.voip.CallUnholdCnf".equals(intent.getAction())) {
                    l(intent);
                } else if ("com.fgmicrotec.mobile.android.voip.RejectInvitationCnf".equals(intent.getAction())) {
                    m(intent);
                } else if ("com.fgmicrotec.mobile.android.voip.CallTransferCnf".equals(intent.getAction())) {
                    n(intent);
                } else if ("com.fgmicrotec.mobile.android.voip.CallToggleCnf".equals(intent.getAction())) {
                    o(intent);
                } else if ("com.fgmicrotec.mobile.android.voip.Call3WayCnf".equals(intent.getAction())) {
                    p(intent);
                } else if ("com.fgmicrotec.mobile.android.voip.CallConferenceCreateCnf".equals(intent.getAction())) {
                    q(intent);
                } else if ("com.fgmicrotec.mobile.android.voip.CallHoldedInd".equals(intent.getAction())) {
                    r(intent);
                } else if ("com.fgmicrotec.mobile.android.voip.CallUnholdedInd".equals(intent.getAction())) {
                    s(intent);
                } else if ("com.fgmicrotec.mobile.android.voip.PlayAlertToneInd".equals(intent.getAction())) {
                    b();
                } else if ("com.fgmicrotec.mobile.android.voip.StopAlertToneInd".equals(intent.getAction())) {
                    c();
                } else if ("com.fgmicrotec.mobile.android.voip.ShouldDisplayVideoViewInd".equals(intent.getAction())) {
                    d();
                } else if ("com.fgmicrotec.mobile.android.voip.AddVideoCnf".equals(intent.getAction())) {
                    w(intent);
                } else if ("com.fgmicrotec.mobile.android.voip.RemoveVideoCnf".equals(intent.getAction())) {
                    x(intent);
                } else if ("com.fgmicrotec.mobile.android.voip.VideoAddedInd".equals(intent.getAction())) {
                    e();
                } else if ("com.fgmicrotec.mobile.android.voip.VideoRemovedInd".equals(intent.getAction())) {
                    f();
                } else if ("android.intent.action.PHONE_STATE".equals(intent.getAction())) {
                    t(intent);
                } else if ("Activity add call supplementary screen interrupted".equals(intent.getAction())) {
                    u(intent);
                } else if ("InCallActions.CallUpdate".equals(intent.getAction())) {
                    v(intent);
                } else if ("CallManager.ActionStopRingback".equals(intent.getAction())) {
                    this.a.an = g.a().k();
                } else if ("CallManager.ActionBlockClosing".equals(intent.getAction())) {
                    this.a.v.postDelayed(this.a.aC, 10000);
                    this.a.u = true;
                } else if ("CallManager.ActionUnblockClosing".equals(intent.getAction())) {
                    this.a.u = false;
                } else if ("CallManager.ActionEnableMute".equals(intent.getAction())) {
                    this.a.g.x();
                    if (intent.getBooleanExtra("use_spirit", false)) {
                        this.a.at = true;
                        if (this.a.N() && !this.a.ao && this.a.h == b.INCOMING) {
                            Intent intent2 = new Intent();
                            intent2.setAction("com.fgmicrotec.mobile.android.voip.UpdateAudioMode");
                            intent2.putExtra(VoIP.AUDIO_BT_MODE, true);
                            intent2.putExtra(VoIP.AUDIO_SPEAKER_MODE, this.a.ao);
                            intent2.putExtra(VoIP.AUDIO_HANDSET_MODE, false);
                            intent2.putExtra(VoIP.AUDIO_HEASDET_MODE, false);
                            intent2.putExtra(VoIP.AUDIO_HEADPHONES_MODE, false);
                            this.a.f.sendBroadcast(intent2);
                            return;
                        }
                        this.a.aI();
                    }
                } else if ("com.fgmicrotec.mobile.android.voip.VolumeChangeCnf".equals(intent.getAction())) {
                    int intExtra = intent.getIntExtra("extra_volume_change_direction", 15);
                    q.b("Voip", "onReceive(): VOLUME_CHANGE_CNF: new value: " + intExtra);
                    this.a.g.b(intExtra);
                } else if ("android.intent.action.HEADSET_PLUG".equals(intent.getAction()) || "android.intent.action.HEADSET_PLUG".equals(intent.getAction())) {
                    CallManager.a = intent.getIntExtra("state", 0) == 1;
                    if (intent.getIntExtra("microphone", 0) != 1) {
                        z = false;
                    }
                    CallManager.b = z;
                    if (this.a.at) {
                        this.a.aI();
                    }
                    q.b("Voip", "onReceive(): ACTION_HEADSET_PLUG - isPlugged=" + CallManager.a + " hasMicroPhone=" + CallManager.b);
                }
            }
        }

        private void a(Intent intent) {
            if (intent.getIntExtra("extra_error_code", -1) == 0) {
                this.a.as = true;
                if (!TextUtils.isEmpty(this.a.O) && FgVoIP.U().e(this.a.O)) {
                    q.a("CallManager", "CallServiceIntentsReceiver: onReceive(): starting emergency call");
                    Intent intent2 = new Intent();
                    intent2.setAction("com.fgmicrotec.mobile.android.voip.CallInviteReq");
                    intent2.putExtra("extra_uri_to_call", this.a.aj);
                    this.a.f.sendBroadcast(intent2);
                    return;
                }
                return;
            }
            this.a.b(-99, true);
        }

        private void b(Intent intent) {
            if (f.a != intent.getIntExtra("extra_error_code", -1)) {
                if (this.a.C) {
                    this.a.a(this.a.W, this.a.X, 2, this.a.Z);
                } else {
                    q.d("CallManager", "CALL_INVITE_CNF arrived with error mCurrentScreen = " + this.a.h);
                    this.a.a(this.a.N, this.a.O, 2, this.a.Q);
                    if (!FgVoIP.U().T()) {
                        this.a.s = true;
                        this.a.b(k.call_ended, true);
                    } else if (this.a.h == b.INCOMING) {
                        q.d("CallManager", "CALL_INVITE_CNF arrived with error BUT we have Incoming Call!");
                    } else if (g.a().f()) {
                        q.d("CallManager", "CALL_INVITE_CNF arrived with error BUT we have Incoming Call via CallNotifier!");
                        this.a.g.a();
                        this.a.O = g.a().g();
                        this.a.L = 1;
                        this.a.N = this.a.m.a(this.a.O, this.a.O, this.a.Q, true, false, false, false);
                    } else {
                        this.a.s = true;
                        this.a.b(k.call_ended, true);
                    }
                }
                q.d("CallManager", "CALL_INVITE_CNF arrived with error  " + intent.getIntExtra("extra_error_code", -1));
            } else if (this.a.D) {
                this.a.A = intent.getIntExtra("extra_session_id", this.a.A);
                this.a.T = this.a.A;
                this.a.U = 2;
            } else if (this.a.C) {
                this.a.A = intent.getIntExtra("extra_session_id", this.a.A);
                this.a.T = this.a.A;
                this.a.U = 2;
                this.a.g.l();
                this.a.aj = this.a.X;
            } else {
                this.a.A = intent.getIntExtra("extra_session_id", this.a.A);
                this.a.K = this.a.A;
                this.a.L = 2;
            }
        }

        private void c(Intent intent) {
            boolean booleanExtra = intent.getBooleanExtra("extra_stop_ringback", false);
            if (FgVoIP.U().aj()) {
                this.a.g.u();
                if (intent.getIntExtra("extra_media_about_to_start", 0) == 1) {
                    d(intent);
                } else if (booleanExtra) {
                    g.a().k();
                } else {
                    g.a().b(this.a.an);
                }
            } else if (booleanExtra) {
                g.a().k();
            } else {
                g.a().b(this.a.an);
            }
        }

        private void d(Intent intent) {
            this.a.an = g.a().k();
            this.a.v.removeCallbacks(this.a.az);
            this.a.E = false;
            if (!this.a.D) {
                if (this.a.C) {
                    int intExtra = intent.getIntExtra("extra_session_id", this.a.A);
                    if (this.a.ai == 1) {
                        if (intExtra != this.a.K) {
                            this.a.A = this.a.T;
                            this.a.Z = System.currentTimeMillis();
                            this.a.U = 2;
                            this.a.ai = 2;
                            this.a.B = false;
                            this.a.G();
                            return;
                        }
                        return;
                    } else if (this.a.ai != 2) {
                        this.a.g(true);
                        return;
                    } else {
                        return;
                    }
                }
                this.a.C = true;
                if (this.a.r) {
                    this.a.r = false;
                    this.a.A = intent.getIntExtra("extra_session_id", this.a.A);
                    this.a.ak = intent.getStringExtra("extra_uri_to_call");
                    if (intent.hasExtra("extra_display_name")) {
                        this.a.al = intent.getStringExtra("extra_display_name");
                    }
                    this.a.O = t.f.f(this.a.ak);
                    this.a.N = this.a.O;
                    this.a.K = this.a.A;
                    this.a.L = 1;
                }
                this.a.a(true);
            }
        }

        private void e(Intent intent) {
            this.a.an = g.a().k();
            this.a.v.removeCallbacks(this.a.az);
            int intExtra = intent.getIntExtra("extra_reason_code", 0);
            int intExtra2 = intent.getIntExtra("extra_busy_tone_type", 0);
            this.a.E = false;
            if (!this.a.D) {
                q.a("CallManager", "invitationRejected(): call established: " + this.a.C + ", numOfCalls: " + this.a.ai);
                if (!this.a.C) {
                    this.a.b(intExtra, true);
                    g.a().a(intExtra2);
                    if (FgVoIP.U().O() && this.a.L == 2) {
                        this.a.c(intExtra, true);
                    }
                } else if (this.a.ai == 0) {
                    this.a.a(this.a.N, this.a.O, this.a.L, this.a.Q);
                    this.a.s = true;
                    this.a.aA();
                    if (this.a.h == b.CALL_ENDED_POPUP) {
                        this.a.ai = -1;
                    } else {
                        this.a.b(intExtra, true);
                    }
                } else if (this.a.h == b.ONE_CALL && this.a.ai == 1) {
                    this.a.b(intExtra, true);
                    g.a().a(intExtra2);
                } else {
                    this.a.d(intent.getIntExtra("extra_session_id", this.a.A));
                }
            }
        }

        private void f(Intent intent) {
            this.a.an = true;
            if (FgVoIP.U().aj() && intent.getIntExtra("extra_media_about_to_start", 0) == 1) {
                d(intent);
            }
        }

        private void g(Intent intent) {
            this.a.ag = false;
            int intExtra = intent.getIntExtra("extra_session_id", -1);
            if (intExtra == this.a.A) {
                this.a.L = 3;
                this.a.a(this.a.N, this.a.O, this.a.L, System.currentTimeMillis());
                g.a().e();
                if (!FgVoIP.U().T()) {
                    this.a.g.a();
                } else if (this.a.ah) {
                    this.a.aA();
                } else {
                    this.a.g.a();
                }
            } else if (intExtra == this.a.T) {
                this.a.U = 3;
                this.a.a(this.a.W, this.a.X, this.a.U, System.currentTimeMillis());
                g.a().j();
                this.a.aD();
                if (this.a.ai == 0) {
                    this.a.f.sendBroadcast(new Intent("IncomingCallActions.ActionStopActivity"));
                    if (!this.a.ah) {
                        this.a.g.a();
                    }
                } else {
                    this.a.T = 0;
                    this.a.X = null;
                    this.a.W = null;
                    if (!FgVoIP.U().T()) {
                        this.a.a(false);
                    } else if (this.a.ah) {
                        this.a.g.f();
                        this.a.h = b.OUTGOING;
                    } else {
                        this.a.a(false);
                    }
                    this.a.f.sendBroadcast(new Intent("IncomingCallActions.ActionStopActivity"));
                }
            }
            this.a.m.a(this.a.al, t.f.f(this.a.ak), null, System.currentTimeMillis());
        }

        private void h(Intent intent) {
            int intExtra = intent.getIntExtra("extra_original_session_id", -1);
            int intExtra2 = intent.getIntExtra("extra_changed_session_id", -1);
            q.b("CallManager", "SESSION_ID_CHANGED_IND + originalSessionID = " + intExtra + " changedlSessionID = " + intExtra2);
            if (this.a.A == intExtra) {
                this.a.A = intExtra2;
            }
            if (this.a.K == intExtra) {
                this.a.K = intExtra2;
            }
            if (this.a.T == intExtra) {
                this.a.T = intExtra2;
            }
        }

        private void i(Intent intent) {
            int intExtra = intent.getIntExtra("extra_session_id", -1);
            q.a("CallManager", "callTerminateCnf(): sessionID: " + intExtra + ", firstCallSessionID: " + this.a.K + ", secondCallSessionId: " + this.a.T);
            if (this.a.t && intExtra == this.a.K) {
                this.a.v.removeCallbacks(this.a.ax);
                this.a.t = false;
                Intent intent2 = new Intent();
                intent2.setAction("com.fgmicrotec.mobile.android.voip.AcceptInvitationReq");
                intent2.putExtra("extra_session_id", this.a.T);
                this.a.f.sendBroadcast(intent2);
                this.a.g(true);
            }
            if (intExtra == this.a.K && this.a.T == 0) {
                this.a.A = 0;
                this.a.ai = 0;
            }
        }

        private void j(Intent intent) {
            g.a().e();
            g.a().m();
            int intExtra = intent.getIntExtra("extra_session_id", -1);
            int intExtra2 = intent.getIntExtra("extra_reason_code", 0);
            Intent intent2;
            if (this.a.ai == 1) {
                if (!(this.a.h == b.OUTGOING_CONSULTATION || this.a.h == b.TRANSFER_LOCATIONS || this.a.h == b.ADD_NEW_CALL || this.a.h == b.TRANSFER_PROGRESS || this.a.h == b.INCOMING_SUPPLEMENTARY)) {
                    this.a.a(this.a.N, this.a.O, this.a.L, this.a.Q);
                    this.a.s = true;
                    if (FgVoIP.U().O() && this.a.L == 2) {
                        this.a.c(intExtra2, false);
                    }
                    this.a.b(k.call_ended, true);
                }
                if (this.a.h == b.ADD_NEW_CALL) {
                    this.a.b(k.call_ended, true);
                    this.a.f.sendBroadcast(new Intent("MainTabActions.ActionStopActivityMainInherits"));
                } else if (this.a.h == b.OUTGOING_CONSULTATION) {
                    this.a.a(this.a.N, this.a.O, this.a.L, this.a.Q);
                    CallManager.d = null;
                    this.a.g.m();
                    this.a.h = b.OUTGOING;
                    this.a.aA();
                    this.a.C = false;
                    this.a.T = 0;
                    this.a.V = false;
                    this.a.B = false;
                } else if (this.a.h == b.INCOMING_SUPPLEMENTARY) {
                    this.a.a(this.a.N, this.a.O, this.a.L, this.a.Q);
                    this.a.ai = 0;
                    intent2 = new Intent();
                    intent2.setAction("IncomingCallActions.ActionOriginalCallWasTerminated");
                    intent2.putExtra("IncomingCallExtras.ExtraTerminatedName", this.a.N);
                    intent2.putExtra("IncomingCallExtras.ExtraTerminatedNumber", this.a.O);
                    intent2.putExtra("IncomingCallExtras.ExtraTerminatedDuration", this.a.ao());
                    this.a.f.sendBroadcast(intent2);
                }
                if (this.a.A == intExtra) {
                    this.a.A = 0;
                    this.a.ai = 0;
                }
            } else if (this.a.G) {
                this.a.aB();
            } else {
                this.a.d(intExtra);
                if (intExtra2 != 0) {
                    intent2 = new Intent();
                    intent2.setAction("com.fgmicrotec.mobile.android.voip.CallUnholdReq");
                    intent2.putExtra("extra_session_id", this.a.A);
                    this.a.f.sendBroadcast(intent2);
                }
            }
        }

        private void a() {
            this.a.an = g.a().k();
            this.a.d(this.a.A);
        }

        private void k(Intent intent) {
            if (!this.a.t) {
                if (f.a == intent.getIntExtra("extra_error_code", -1)) {
                    this.a.B = true;
                    if (this.a.ai != 1) {
                        int intExtra = intent.getIntExtra("extra_session_id", -1);
                        if (intExtra > -1) {
                            if (intExtra == this.a.K) {
                                this.a.A = this.a.T;
                            } else {
                                this.a.A = this.a.K;
                            }
                            this.a.g.r();
                        }
                    }
                } else {
                    q.d("CallManager", "onReceive(): " + intent.getAction() + " ERROR");
                }
                if (this.a.S) {
                    this.a.g.c();
                }
                this.a.g.n();
            }
        }

        private void l(Intent intent) {
            int intExtra = intent.getIntExtra("extra_error_code", -1);
            q.a("CallManager", "processCallServiceIntent_Unhold");
            if (intExtra == f.a) {
                this.a.B = false;
                this.a.v.postDelayed(this.a.aB, 1000);
                if (this.a.ai != 1) {
                    intExtra = intent.getIntExtra("extra_session_id", -1);
                    if (intExtra > -1) {
                        if (intExtra == this.a.K) {
                            this.a.A = this.a.K;
                        } else {
                            this.a.A = this.a.T;
                        }
                        this.a.g.r();
                    }
                } else if (this.a.H) {
                    this.a.ab();
                    this.a.F = false;
                }
                this.a.X();
            } else {
                q.d("CallManager", "onReceive(): " + intent.getAction() + " ERROR");
            }
            this.a.H = false;
            if (this.a.S) {
                this.a.g.b();
            }
            this.a.g.n();
        }

        private void m(Intent intent) {
            int intExtra = intent.getIntExtra("extra_session_id", 0);
            int intExtra2 = intent.getIntExtra("extra_error_code", -1);
            q.a("CallManager", "REJECT_INVITATION_CNF sessionId=" + intExtra + ", errCode=" + intExtra2);
            if (intExtra2 == f.a) {
                if (!this.a.J && this.a.K > 0 && this.a.K == intExtra) {
                    q.a("CallManager", "rejected from notification - exiting");
                    this.a.s = true;
                    this.a.b(0, true);
                } else if (this.a.h == b.INCOMING_SUPPLEMENTARY && this.a.T == intExtra) {
                    q.a("CallManager", "rejected supplementary - returning to prev. call");
                    this.a.T = 0;
                    this.a.aD();
                    this.a.a(false);
                } else if (this.a.ai == 1 && !this.a.B && this.a.H) {
                    this.a.ab();
                    this.a.F = false;
                }
            }
            this.a.H = false;
        }

        private void n(Intent intent) {
            if (intent.getIntExtra("extra_error_code", -1) == f.a) {
                this.a.g.a();
            }
        }

        private void o(Intent intent) {
            if (intent.getIntExtra("extra_error_code", -1) != f.a) {
                q.d("CallManager", "onReceive(): " + intent.getAction() + " ERROR");
            } else if (this.a.ai == 2) {
                int intExtra = intent.getIntExtra("extra_session_id", -1);
                if (intExtra > -1) {
                    if (intExtra == this.a.K) {
                        this.a.A = this.a.T;
                    } else {
                        this.a.A = this.a.K;
                    }
                    this.a.g.r();
                }
            }
        }

        private void p(Intent intent) {
            if (intent.getIntExtra("extra_error_code", -1) == f.a) {
                this.a.G = true;
                this.a.g.c(false);
                this.a.h = b.CONFERENCE;
            }
        }

        private void q(Intent intent) {
            int intExtra = intent.getIntExtra("extra_error_code", -1);
            int intExtra2 = intent.getIntExtra("extra_session_id", -1);
            if (intExtra == f.a) {
                this.a.C = true;
                this.a.G = true;
                this.a.ac = intExtra2;
                this.a.A = this.a.ac;
                this.a.H();
            } else if (this.a.h == b.CONFERENCE) {
                this.a.ac = 0;
                this.a.s = true;
                CallManager.d = null;
                this.a.g.a(intExtra);
                this.a.h = b.CALL_ENDED;
                this.a.v.postDelayed(this.a.ax, 3000);
            }
        }

        private void r(Intent intent) {
            String stringExtra = intent.getStringExtra("extra_uri_to_call");
            q.b("CallManager", "CALL_HOLDED_IND + indicationSenderUri = " + stringExtra);
            if (stringExtra.length() > 0) {
                stringExtra = t.f.f(stringExtra);
                if (stringExtra != null) {
                    if (this.a.ai == 2) {
                        if (stringExtra.equals(this.a.O)) {
                            this.a.R = true;
                        } else {
                            this.a.aa = true;
                        }
                    } else if (stringExtra.equals(this.a.O)) {
                        this.a.R = true;
                    }
                }
            }
            if (this.a.S) {
                this.a.g.c();
            }
            this.a.g.o();
        }

        private void s(Intent intent) {
            String stringExtra = intent.getStringExtra("extra_uri_to_call");
            q.b("CallManager", "CALL_UNHOLDED_IND + indicationSenderUri = " + stringExtra);
            if (stringExtra.length() > 0) {
                stringExtra = t.f.f(stringExtra);
                if (stringExtra != null) {
                    if (this.a.ai == 2) {
                        if (stringExtra.equals(this.a.O)) {
                            this.a.R = false;
                        } else {
                            this.a.aa = false;
                        }
                    } else if (stringExtra.equals(this.a.O)) {
                        this.a.R = false;
                    }
                }
            }
            if (this.a.S) {
                this.a.g.b();
            }
            if (this.a.o() || this.a.v()) {
                this.a.X();
            }
            this.a.g.o();
        }

        private void t(Intent intent) {
            String stringExtra = intent.getStringExtra("state");
            if (this.a.a(stringExtra, this.a.an())) {
                q.a("CallManager", "processCallServiceIntent_CallStateChange finishInCallScreen " + stringExtra);
                this.a.g.a();
            }
        }

        private void u(Intent intent) {
            String stringExtra = intent.getStringExtra("InCallExtras.ExtraSupplementaryNumber");
            this.a.ag = true;
            CallManager callManager = this.a;
            if (stringExtra == null) {
                stringExtra = "";
            }
            callManager.af = stringExtra;
        }

        private void v(Intent intent) {
            if (intent.hasExtra("InCallExtras.ExtraStatusText")) {
                intent.getStringExtra("InCallExtras.ExtraStatusText");
            }
        }

        private void b() {
            g.a().c();
        }

        private void c() {
            g.a().d();
        }

        private void d() {
            if (this.a.S || this.a.ab) {
                this.a.g.b();
                this.a.v.postDelayed(this.a.aA, 200);
            }
        }

        private void w(Intent intent) {
            if (intent.getIntExtra("extra_error_code", -1) == f.a) {
                this.a.S = true;
                this.a.g.b();
                this.a.g.g(true);
                return;
            }
            this.a.g.g(false);
        }

        private void x(Intent intent) {
            if (intent.getIntExtra("extra_error_code", -1) == f.a) {
                this.a.S = false;
                this.a.g.c();
                this.a.g.g(false);
                return;
            }
            this.a.g.g(true);
        }

        private void e() {
            this.a.S = true;
            this.a.g.b();
            this.a.g.g(true);
        }

        private void f() {
            this.a.S = false;
            this.a.g.c();
            this.a.g.g(false);
        }
    }

    private class a extends AsyncTask<Void, Void, Boolean> {
        final /* synthetic */ CallManager a;

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return a((Void[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            a((Boolean) obj);
        }

        public a(CallManager callManager) {
            this.a = callManager;
        }

        protected void onPreExecute() {
            super.onPreExecute();
        }

        protected Boolean a(Void... voidArr) {
            return Boolean.valueOf(this.a.aH());
        }

        protected void a(Boolean bool) {
            super.onPostExecute(bool);
            q.a("CallManager", "onPostExecute(): " + bool);
        }
    }

    public enum b {
        ADD_NEW_CALL,
        CALL_ENDED,
        CALL_ENDED_POPUP,
        CONFERENCE,
        INCOMING,
        INCOMING_SUPPLEMENTARY,
        ONE_CALL,
        OUTGOING,
        OUTGOING_CONSULTATION,
        TWO_CALLS,
        TRANSFER_LOCATIONS,
        TRANSFER_PROGRESS
    }

    private class c implements Runnable {
        Intent a;
        final /* synthetic */ CallManager b;

        public c(CallManager callManager, Intent intent) {
            this.b = callManager;
            this.a = intent;
        }

        public void run() {
            this.b.f.sendBroadcast(this.a);
        }
    }

    private class d extends BroadcastReceiver {
        final /* synthetic */ CallManager a;

        private d(CallManager callManager) {
            this.a = callManager;
        }

        /* synthetic */ d(CallManager callManager, AnonymousClass1 anonymousClass1) {
            this(callManager);
        }

        public void onReceive(Context context, Intent intent) {
            if (intent != null && intent.getAction() != null) {
                String action = intent.getAction();
                q.b("CallManager", "LocalBroadcastReceiver: onReceive() " + action);
                boolean booleanExtra;
                if (action.equals("BluetoothService.ActionDeviceConnectionChanged")) {
                    booleanExtra = intent.getBooleanExtra("BluetoothService.ExtraState", false);
                    if (booleanExtra) {
                        q.a("CallManager", "LocalBroadcastReceiver: onReceive(): Device connected: " + booleanExtra + ", starting SCO");
                        this.a.v.postDelayed(new Runnable(this) {
                            final /* synthetic */ d a;

                            {
                                this.a = r1;
                            }

                            public void run() {
                                if (this.a.a.at) {
                                    Intent intent = new Intent();
                                    intent.setAction("com.fgmicrotec.mobile.android.voip.UpdateAudioMode");
                                    intent.putExtra(VoIP.AUDIO_BT_MODE, true);
                                    intent.putExtra(VoIP.AUDIO_SPEAKER_MODE, false);
                                    intent.putExtra(VoIP.AUDIO_HANDSET_MODE, false);
                                    intent.putExtra(VoIP.AUDIO_HEASDET_MODE, false);
                                    intent.putExtra(VoIP.AUDIO_HEADPHONES_MODE, false);
                                    this.a.a.f.sendBroadcast(intent);
                                    return;
                                }
                                this.a.a.l.startBluetoothSco();
                                this.a.a.l.setBluetoothScoOn(true);
                            }
                        }, 1000);
                    } else if (!this.a.at && this.a.l.isBluetoothScoOn()) {
                        q.a("CallManager", "LocalBroadcastReceiver: onReceive(): Device connected: " + booleanExtra + ", stopping SCO");
                        this.a.l.stopBluetoothSco();
                    }
                    this.a.g.j(false);
                } else if (action.equals("BluetoothService.ActionScoAudioConnectionChanged")) {
                    booleanExtra = intent.getBooleanExtra("BluetoothService.ExtraState", false);
                    q.a("CallManager", "LocalBroadcastReceiver: onReceive(): SCO audio connected: " + booleanExtra);
                    if ((!FgVoIP.U().B() && booleanExtra && !this.a.ap) || (!booleanExtra && this.a.ap)) {
                        this.a.V();
                    }
                }
            }
        }
    }

    static {
        au.put(Character.valueOf('1'), Integer.valueOf(1));
        au.put(Character.valueOf('2'), Integer.valueOf(2));
        au.put(Character.valueOf('3'), Integer.valueOf(3));
        au.put(Character.valueOf('4'), Integer.valueOf(4));
        au.put(Character.valueOf('5'), Integer.valueOf(5));
        au.put(Character.valueOf('6'), Integer.valueOf(6));
        au.put(Character.valueOf('7'), Integer.valueOf(7));
        au.put(Character.valueOf('8'), Integer.valueOf(8));
        au.put(Character.valueOf('9'), Integer.valueOf(9));
        au.put(Character.valueOf('0'), Integer.valueOf(0));
        au.put(Character.valueOf('#'), Integer.valueOf(11));
        au.put(Character.valueOf('*'), Integer.valueOf(10));
    }

    public static boolean a() {
        return c;
    }

    public boolean b() {
        return this.S;
    }

    public static String c() {
        return d;
    }

    public static long d() {
        return e;
    }

    public static void a(long j) {
        e = j;
    }

    public CallManager(Activity activity, f fVar) {
        q.a("CallManager", "CallManager created!");
        c = true;
        this.f = activity;
        this.g = fVar;
        this.w = FgVoIP.U().Z();
        this.l = (AudioManager) this.f.getSystemService("audio");
        this.x = (ConnectivityManager) this.f.getSystemService("connectivity");
        at();
        f();
        FgVoIP.U().j(true);
        FgVoIP.U().a(true, true, false);
        this.v = new Handler();
        this.m = e.a();
        this.j = new ComponentName(this.f, MediaBroadcastReceiver.class);
        this.t = false;
        au();
        if (FgVoIP.U().ai()) {
            aF();
        }
        if (FgVoIP.U().M()) {
            this.f.sendBroadcast(new Intent("com.mitel.mobile.android.voip.ActionConnectionManagerCallStarted"));
        }
    }

    public void e() {
        if (FgVoIP.U().ai()) {
            aG();
        }
        FgVoIP.U().a(0, this.ao);
        if (FgVoIP.U().x()) {
            FgVoIP.U().e(false);
            if (this.as) {
                q.a("CallManager", "destroy(): unregistering after emergency call");
                Intent intent = new Intent();
                intent.setAction("com.fgmicrotec.mobile.android.voip.LogoutFromServerReq");
                this.f.sendBroadcast(intent);
            }
        }
        this.v.removeCallbacks(this.aw);
        this.v.removeCallbacks(this.ay);
        this.v.removeCallbacks(this.az);
        this.v.removeCallbacks(this.ax);
        this.v.removeCallbacks(this.aA);
        this.v.removeCallbacks(this.aD);
        this.v.removeCallbacks(this.aC);
        this.v.removeCallbacks(this.aE);
        this.v.removeCallbacks(this.aB);
        if (FgVoIP.U().p()) {
            this.v.removeCallbacks(this.av);
        }
        this.m.g();
        if (!this.at && this.l.isSpeakerphoneOn()) {
            this.l.setSpeakerphoneOn(false);
        }
        if (!this.at && N()) {
            q.a("CallManager", "destroy(): stopping Bluetooth SCO");
            this.l.setBluetoothScoOn(false);
            this.l.stopBluetoothSco();
        }
        this.P = null;
        this.Y = null;
        this.an = g.a().k();
        g.a().h();
        g.a().j();
        g.a().l();
        g.a().d();
        q.a("CallManager", "Stopped Tones.");
        aa();
        this.f.setVolumeControlStream(Integer.MIN_VALUE);
        this.f.unregisterReceiver(this.i);
        o.a(this.f).a(this.k);
        SimpleCodecAL.getInstance().setContext(null);
        SimpleCodecAL.stopPlayerAndRecorderLoops();
        q.a("CallManager", "Unregistered receivers.");
        new Thread(new Runnable(this) {
            final /* synthetic */ CallManager a;

            {
                this.a = r1;
            }

            public void run() {
                q.a("CallManager", "DTMF Generator released in new thread.");
                if (this.a.n != null) {
                    this.a.n.release();
                }
                if (!this.a.at) {
                    this.a.l.setMode(0);
                }
            }
        }).start();
        c = false;
        this.at = false;
        if (FgVoIP.U().M()) {
            this.f.sendBroadcast(new Intent("com.mitel.mobile.android.voip.ActionConnectionManagerCallEnded"));
        }
        q.a("CallManager", "CallManager destroyed");
    }

    public void onAudioFocusChange(int i) {
        switch (i) {
            case ActivationAdapter.VERS_INITIAL /*-3*/:
                q.a("CallManager", "onAudioFocusChange(): AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK");
                return;
            case -2:
                q.a("CallManager", "onAudioFocusChange(): AUDIOFOCUS_LOSS_TRANSIENT");
                return;
            case -1:
                q.a("CallManager", "onAudioFocusChange(): AUDIOFOCUS_LOSS");
                return;
            case 1:
                q.a("CallManager", "onAudioFocusChange(): AUDIOFOCUS_GAIN");
                return;
            default:
                q.a("CallManager", "onAudioFocusChange(): App has requested: " + i);
                return;
        }
    }

    private void at() {
        this.i = new CallServiceIntentsReceiver(this);
        IntentFilter intentFilter = new IntentFilter();
        if (FgVoIP.U().x()) {
            intentFilter.addAction("com.fgmicrotec.mobile.android.voip.LoginToServerCnf");
        }
        intentFilter.addAction("com.fgmicrotec.mobile.android.voip.InvitationRingingInd");
        intentFilter.addAction("com.fgmicrotec.mobile.android.voip.InvitationAcceptedInd");
        intentFilter.addAction("CallManager.AcceptInvitationReq");
        intentFilter.addAction("com.fgmicrotec.mobile.android.voip.InvitationSessionProgressInd");
        intentFilter.addAction("CallManager.IncomingCallCanceledInd");
        intentFilter.addAction("com.fgmicrotec.mobile.android.voip.SessionEndedInd");
        intentFilter.addAction("com.fgmicrotec.mobile.android.voip.InvitationRejectedInd");
        intentFilter.addAction("com.fgmicrotec.mobile.android.voip.CallInviteCnf");
        intentFilter.addAction("com.fgmicrotec.mobile.android.voip.CallTerminateCnf");
        intentFilter.addAction("com.fgmicrotec.mobile.android.voip.InviteCancelCnf");
        intentFilter.addAction("com.fgmicrotec.mobile.android.voip.CallHoldCnf");
        intentFilter.addAction("com.fgmicrotec.mobile.android.voip.CallUnholdCnf");
        intentFilter.addAction("com.fgmicrotec.mobile.android.voip.CallTransferCnf");
        intentFilter.addAction("com.fgmicrotec.mobile.android.voip.CallToggleCnf");
        intentFilter.addAction("com.fgmicrotec.mobile.android.voip.Call3WayCnf");
        intentFilter.addAction("com.fgmicrotec.mobile.android.voip.CallConferenceCreateCnf");
        intentFilter.addAction("com.fgmicrotec.mobile.android.voip.CallHoldedInd");
        intentFilter.addAction("com.fgmicrotec.mobile.android.voip.CallUnholdedInd");
        intentFilter.addAction("com.fgmicrotec.mobile.android.voip.RejectInvitationCnf");
        intentFilter.addAction("com.fgmicrotec.mobile.android.voip.PlayAlertToneInd");
        intentFilter.addAction("com.fgmicrotec.mobile.android.voip.StopAlertToneInd");
        intentFilter.addAction("com.fgmicrotec.mobile.android.voip.SessionIdChangedInd");
        intentFilter.addAction("com.fgmicrotec.mobile.android.voip.ShouldDisplayVideoViewInd");
        intentFilter.addAction("com.fgmicrotec.mobile.android.voip.AddVideoCnf");
        intentFilter.addAction("com.fgmicrotec.mobile.android.voip.RemoveVideoCnf");
        intentFilter.addAction("com.fgmicrotec.mobile.android.voip.VideoAddedInd");
        intentFilter.addAction("com.fgmicrotec.mobile.android.voip.VideoRemovedInd");
        intentFilter.addAction("com.fgmicrotec.mobile.android.voip.VolumeChangeCnf");
        intentFilter.addAction("Activity add call supplementary screen interrupted");
        intentFilter.addAction("InCallActions.CallUpdate");
        intentFilter.addAction("CallManager.ActionStopRingback");
        intentFilter.addAction("CallManager.ActionBlockClosing");
        intentFilter.addAction("CallManager.ActionUnblockClosing");
        intentFilter.addAction("CallManager.ActionEnableMute");
        intentFilter.addAction("android.intent.action.VOICE_COMMAND");
        if (VERSION.SDK_INT > 21) {
            intentFilter.addAction("android.intent.action.HEADSET_PLUG");
        } else {
            intentFilter.addAction("android.intent.action.HEADSET_PLUG");
        }
        intentFilter.addAction("android.intent.action.PHONE_STATE");
        this.f.registerReceiver(this.i, intentFilter);
        intentFilter = new IntentFilter();
        intentFilter.addAction("BluetoothService.ActionDeviceConnectionChanged");
        intentFilter.addAction("BluetoothService.ActionA2dpAudioConnectionChanged");
        intentFilter.addAction("BluetoothService.ActionScoAudioConnectionChanged");
        this.k = new d();
        o.a(this.f).a(this.k, intentFilter);
    }

    public void f() {
        g();
    }

    public void g() {
        if (this.n == null) {
            try {
                this.n = new ToneGenerator(8, 80);
            } catch (RuntimeException e) {
                q.d("CallManager", "setupDTMFToneGenerator(): failed to create: " + e);
                this.n = null;
            }
        }
    }

    public void a(String str) {
        p = str;
    }

    public boolean h() {
        return this.u;
    }

    public boolean i() {
        return this.B;
    }

    public boolean j() {
        return this.ao;
    }

    @Deprecated
    public boolean k() {
        return this.aq;
    }

    public int l() {
        return this.A;
    }

    public int m() {
        return this.K;
    }

    public int n() {
        return this.ai;
    }

    public boolean o() {
        return this.M;
    }

    public String p() {
        return this.N;
    }

    public String q() {
        return this.O;
    }

    public Bitmap r() {
        return this.P;
    }

    public long s() {
        return this.Q;
    }

    public boolean t() {
        return this.R;
    }

    public boolean u() {
        return this.S;
    }

    public boolean v() {
        return this.V;
    }

    public String w() {
        return this.W;
    }

    public String x() {
        return this.X;
    }

    public Bitmap y() {
        return this.Y;
    }

    public long z() {
        return this.Z;
    }

    public List<h> A() {
        return this.ae;
    }

    public boolean B() {
        return this.aa;
    }

    public void C() {
        if (this.A == this.K) {
            D();
        } else if (this.A == this.T) {
            E();
        } else if (this.A == this.ac) {
            F();
        }
    }

    public void D() {
        this.Q = System.currentTimeMillis();
    }

    public void E() {
        this.Z = System.currentTimeMillis();
    }

    public void F() {
        this.ad = System.currentTimeMillis();
    }

    public void a(Intent intent) {
        if (intent != null) {
            String action = intent.getAction();
            if (action != null && !action.equals("InCallActions.UserPressedNotification")) {
                String stringExtra = intent.getStringExtra(com.fgmicrotec.mobile.android.fgvoip.a.a.b);
                String stringExtra2 = intent.getStringExtra(com.fgmicrotec.mobile.android.fgvoip.a.a.d);
                String stringExtra3 = intent.getStringExtra("InCallExtras.ExtraSupplementaryNumber");
                int intExtra = intent.getIntExtra("InCallExtras.ExtraSupplementaryType", 0);
                int intExtra2 = intent.getIntExtra(com.fgmicrotec.mobile.android.fgvoip.a.a.a, 0);
                boolean booleanExtra = intent.getBooleanExtra(com.fgmicrotec.mobile.android.fgvoip.a.a.c, false);
                ArrayList stringArrayListExtra = intent.getStringArrayListExtra("InCallExtras.ExtraConferenceParticipants");
                if (action.equals("InCallActions.ActionOutgoingCall") && this.h != null) {
                    action = "InCallActions.SupplementaryScreenCompleted";
                    stringExtra3 = t.f.f(stringExtra);
                    intExtra = 0;
                }
                if (action.equals("InCallActions.ActionOutgoingVideoCall") && this.h != null) {
                    action = "InCallActions.SupplementaryScreenCompleted";
                    stringExtra3 = t.f.f(stringExtra);
                    intExtra = 1;
                }
                q.a("CallManager", "processCallEvent(): action=" + action + ", callUri=" + stringExtra + ", callName=" + stringExtra2 + ", activeCallSessionID=" + intExtra2 + ", isPickup=" + booleanExtra + ", participants=" + com.mavenir.android.common.t.b.a(stringArrayListExtra, ";"));
                if (action.compareTo("InCallActions.ActionOutgoingCall") == 0) {
                    a(action);
                    a(booleanExtra, stringExtra);
                } else if ("InCallActions.ActionIncomingCallInd".equals(action)) {
                    if (this.A == 0) {
                        a(action);
                        a(stringExtra, stringExtra2, intExtra2, false);
                    } else if (this.A != intExtra2) {
                        b(stringExtra, stringExtra2, intExtra2, false);
                        return;
                    } else {
                        return;
                    }
                } else if ("InCallActions.ActionIncomingCallAcceptReq".equals(action)) {
                    a("InCallActions.ActionIncomingCallInd");
                    a(stringExtra, stringExtra2, intExtra2, true);
                } else if ("InCallActions.ActionIncomingCallAcceptVideoReq".equals(action)) {
                    this.S = true;
                    a("InCallActions.ActionIncomingVideoCallInd");
                    a(stringExtra, stringExtra2, intExtra2, true);
                } else if (action.compareTo("InCallActions.ActionOutgoingVideoCall") == 0) {
                    a(action);
                    b(stringExtra);
                } else if ("InCallActions.ActionIncomingVideoCallInd".equals(action)) {
                    if (this.A == 0) {
                        a(action);
                        this.S = true;
                        a(stringExtra, stringExtra2, intExtra2, false);
                    } else if (this.A != intExtra2) {
                        this.ab = true;
                        b(stringExtra, stringExtra2, intExtra2, false);
                        return;
                    } else {
                        return;
                    }
                } else if ("InCallActions.ActionConferenceCall".equals(action)) {
                    a(action);
                    a(stringArrayListExtra);
                } else if ("InCallActions.SupplementaryScreenCompleted".equals(action)) {
                    a(stringExtra3, intExtra);
                    return;
                } else if ("InCallActions.AddPArticipantScreenCompleted".equals(action)) {
                    e(stringExtra3);
                    return;
                } else {
                    this.g.a();
                    return;
                }
                FgVoIP.U().a(q, this.ao);
                SimpleCodecAL.getInstance().setContext(this.f);
                f();
            }
        }
    }

    public void a(String str, String str2, int i, boolean z) {
        this.ak = str;
        if (FgVoIP.U().T()) {
            this.v.removeCallbacks(this.ax);
        }
        this.A = i;
        this.K = this.A;
        this.L = 1;
        if (this.ai == 0) {
            this.ai++;
        }
        if (this.ak == null) {
            this.g.a();
        }
        this.O = t.f.f(this.ak);
        this.al = k.a(this.O, this.O);
        this.N = this.al;
        TelephonyManager telephonyManager = (TelephonyManager) this.f.getSystemService("phone");
        boolean z2 = FgVoIP.U().ad() == com.fgmicrotec.mobile.android.fgvoip.FgVoIP.a.VToW ? telephonyManager != null && (telephonyManager.getCallState() == 1 || telephonyManager.getCallState() == 2) : telephonyManager != null && telephonyManager.getCallState() == 1;
        if (z2) {
            aw();
            g.a().e();
            g.a().a(true);
            Intent intent = new Intent();
            intent.setAction("com.fgmicrotec.mobile.android.voip.RejectInvitationReq");
            intent.putExtra("extra_session_id", this.A);
            this.f.sendBroadcast(intent);
            this.L = 3;
            a(this.N, this.O, this.L, System.currentTimeMillis());
            this.g.a();
            return;
        }
        this.P = k.c(this.O);
        this.g.g();
        this.h = b.INCOMING;
        q = 1;
        this.f.setVolumeControlStream(2);
        aw();
        if (z) {
            q.a("CallManager", "processIncomingCall(): auto-accepting the call");
            P();
            return;
        }
        if (telephonyManager == null || telephonyManager.getCallState() == 0 || telephonyManager.getCallState() == 1) {
            g.a().a(this.O);
        } else {
            g.a().a(false);
        }
        this.v.postDelayed(this.ay, 35000);
    }

    public void b(String str, String str2, int i, boolean z) {
        boolean z2;
        q.a("CallManager", "processSupplementaryIncomingCall(): id:" + i);
        if (this.h == b.INCOMING || this.h == b.OUTGOING_CONSULTATION || this.h == b.OUTGOING || this.h == b.INCOMING_SUPPLEMENTARY) {
            q.a("CallManager", "processSupplementaryIncomingCall(): Rejecting from current state");
            z2 = true;
        } else {
            z2 = false;
        }
        if (!(z2 || this.T == 0)) {
            z2 = true;
        }
        if (FgVoIP.U().T() && this.h == b.OUTGOING) {
            q.a("CallManager", "Not Rejecting from current state. Offers user to decide.");
            this.ah = true;
            z2 = false;
        }
        if (!FgVoIP.U().q()) {
            z2 = true;
        }
        String f = t.f.f(str);
        if (z2) {
            q.a("CallManager", "processSupplementaryIncomingCall(): automatically rejected");
            Intent intent = new Intent();
            intent.setAction("com.fgmicrotec.mobile.android.voip.RejectInvitationReq");
            intent.putExtra("extra_session_id", i);
            this.f.sendBroadcast(intent);
            a(str2, f, 3, System.currentTimeMillis());
            this.m.a(str2, f, null, System.currentTimeMillis());
            return;
        }
        q.a("CallManager", "processSupplementaryIncomingCall(): Accepting secondary incoming call invitation");
        if (this.h == b.ADD_NEW_CALL) {
            this.f.sendBroadcast(new Intent("MainTabActions.ActionStopActivityIncomingCall"));
        }
        this.T = i;
        this.U = 1;
        this.X = t.f.f(str);
        this.W = this.m.a(this.W, this.X, this.Z, true, false, false, false);
        this.Y = k.c(this.X);
        g.a().i();
        this.g.h();
        this.h = b.INCOMING_SUPPLEMENTARY;
        q = 1;
        FgVoIP.U().a(q, this.ao);
    }

    public void a(boolean z, String str) {
        this.r = z;
        this.aj = str == null ? "" : str;
        if (str == null && !z) {
            this.g.a();
        }
        if (this.r) {
            this.O = "";
        } else {
            this.O = t.f.f(this.aj);
        }
        this.N = this.m.a(this.N, this.O, this.Q, false, true, false, false);
        this.P = k.c(this.O);
        this.g.f();
        this.h = b.OUTGOING;
        this.f.setVolumeControlStream(0);
        q = 2;
        aw();
        X();
        if (this.aj == null || this.aj.length() <= 0 || this.O.compareTo(FgVoIP.U().aA()) != 0) {
            Intent intent;
            if (!FgVoIP.U().e(this.O) || !FgVoIP.U().x()) {
                intent = new Intent();
                intent.setAction("com.fgmicrotec.mobile.android.voip.CallInviteReq");
                if (this.r) {
                    intent.putExtra("extra_is_call_pickup", 1);
                } else {
                    intent.putExtra("extra_uri_to_call", this.aj);
                }
                if (FgVoIP.U().s()) {
                    q.a("CallManager", "processOutgoingAudioCall(): pre-call actions enabled, delaying invite by 3000 ms");
                    this.aw = new c(this, intent);
                    this.v.postDelayed(this.aw, 3000);
                } else {
                    this.f.sendBroadcast(intent);
                }
            } else if (!l.a(this.f).C() && !l.a(this.f).A()) {
                b(-98, true);
            } else if (FgVoIP.U().at()) {
                intent = new Intent();
                intent.setAction("com.fgmicrotec.mobile.android.voip.CallInviteReq");
                intent.putExtra("extra_uri_to_call", this.aj);
                this.f.sendBroadcast(intent);
            } else {
                intent = new Intent();
                intent.setAction("com.fgmicrotec.mobile.android.voip.LoginToServerReq");
                this.f.sendBroadcast(intent);
            }
            this.v.postDelayed(this.az, 60000);
            return;
        }
        b(this.f.getIntent().getIntExtra("extra_reason_code", 0), true);
    }

    public void b(String str) {
        this.aj = str == null ? "" : str;
        if (str == null) {
            this.g.a();
        }
        this.S = true;
        this.O = t.f.f(this.aj);
        this.N = this.O;
        this.P = k.c(this.O);
        this.g.f();
        this.h = b.OUTGOING;
        this.f.setVolumeControlStream(0);
        aw();
        if (this.aj == null || this.aj.length() <= 0 || this.O.compareTo(FgVoIP.U().aA()) != 0) {
            Intent intent = new Intent();
            intent.setAction("com.fgmicrotec.mobile.android.voip.VideoCallInviteReq");
            intent.putExtra("extra_uri_to_call", this.aj);
            this.f.sendBroadcast(intent);
            return;
        }
        b(this.f.getIntent().getIntExtra("extra_reason_code", 0), true);
    }

    public void a(boolean z) {
        if (this.ai < 1) {
            b(0, true);
            return;
        }
        if (this.B) {
            this.N = this.m.a(this.N, this.O, this.Q, false, false, true, false);
        } else {
            if (z || this.Q == 0) {
                this.Q = System.currentTimeMillis();
            }
            this.N = this.m.a(this.N, this.O, this.Q, false, true, false, false);
        }
        X();
        this.g.o();
        q = 2;
        this.f.setVolumeControlStream(0);
        if (!(com.fgmicrotec.mobile.android.fgvoipcommon.e.a() || this.l.getMode() == 3)) {
            this.l.setMode(3);
        }
        FgVoIP.U().a(q, this.ao);
        al();
        d = this.O;
        this.g.b(z);
        this.h = b.ONE_CALL;
        this.ah = false;
    }

    public void G() {
        this.W = this.m.a(this.W, this.X, this.Z, false, true, false, false);
        this.Y = k.c(this.X);
        X();
        this.g.o();
        q = 2;
        FgVoIP.U().a(q, this.ao);
        d = this.X;
        this.g.i();
        this.h = b.TWO_CALLS;
    }

    public void a(ArrayList<String> arrayList) {
        String b = com.mavenir.android.settings.c.c.b();
        int g = v.g();
        List arrayList2 = new ArrayList();
        this.ae = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            this.ae.add(h.a(str));
            arrayList2.add(t.f.a(str, b, g));
        }
        Intent intent = new Intent("com.fgmicrotec.mobile.android.voip.CallConferenceCreateReq");
        intent.putExtra("extra_conf_participants", com.mavenir.android.common.t.b.b(arrayList2));
        this.f.sendBroadcast(intent);
        X();
        aw();
        q = 2;
        FgVoIP.U().a(q, this.ao);
        this.g.c(true);
        this.h = b.CONFERENCE;
    }

    public void H() {
        this.an = g.a().k();
        X();
        this.g.o();
        q = 2;
        this.f.setVolumeControlStream(0);
        if (!(com.fgmicrotec.mobile.android.fgvoipcommon.e.a() || this.l.getMode() == 3)) {
            this.l.setMode(3);
        }
        FgVoIP.U().a(q, this.ao);
        this.g.c(false);
        this.h = b.CONFERENCE;
    }

    public void I() {
        this.g.j();
        this.h = b.ADD_NEW_CALL;
    }

    public void a(String str, int i) {
        String a = t.f.a(str);
        if (a != null && !a.equals(FgVoIP.U().aA())) {
            this.X = a;
            this.W = this.X;
            if (FgVoIP.U().q()) {
                a = t.f.a(a, com.mavenir.android.settings.c.c.b(), v.g());
                this.E = true;
                this.h = b.OUTGOING_CONSULTATION;
                Intent intent = new Intent();
                if (i == 1) {
                    intent.setAction("com.fgmicrotec.mobile.android.voip.CallVideoConsultationReq");
                    this.ab = true;
                } else {
                    intent.setAction("com.fgmicrotec.mobile.android.voip.CallConsultationReq");
                    this.ab = false;
                }
                intent.putExtra("extra_uri_to_call", a);
                this.f.sendBroadcast(intent);
            }
        } else if (this.ai == 0) {
            this.g.a();
        } else if (this.B) {
            ab();
        }
    }

    public void a(int i) {
        this.ag = false;
        if (i == 1) {
            Intent intent = new Intent();
            intent.setAction("com.fgmicrotec.mobile.android.voip.CallTerminateReq");
            intent.putExtra("extra_session_id", this.K);
            this.f.sendBroadcast(intent);
            this.t = true;
            return;
        }
        intent = new Intent();
        intent.setAction("com.fgmicrotec.mobile.android.voip.AcceptInvitationReq");
        intent.putExtra("extra_session_id", this.T);
        this.f.sendBroadcast(intent);
        if (this.ai == 1) {
            this.A = this.T;
            this.Z = System.currentTimeMillis();
            this.U = 1;
            this.ai = 2;
            this.B = false;
            if (i == 2) {
                aE();
                return;
            }
            d = this.X;
            this.g.i();
            this.h = b.TWO_CALLS;
            return;
        }
        g(true);
    }

    public void J() {
        g.a().j();
        Intent intent = new Intent("com.fgmicrotec.mobile.android.voip.RejectInvitationReq");
        intent.putExtra("extra_session_id", this.T);
        this.f.sendBroadcast(intent);
        a(this.W, this.X, 1, System.currentTimeMillis());
        if (this.ai < 1) {
            this.N = this.X;
            this.O = this.X;
            this.Q = this.Z;
            this.P = this.Y;
        }
        this.T = 0;
        this.X = null;
        this.W = null;
        this.A = this.K;
        a(false);
    }

    public void b(boolean z) {
        this.v.postDelayed(this.aD, 3000);
    }

    public void K() {
        this.g.k();
    }

    private void e(String str) {
        if (!TextUtils.isEmpty(str) && FgVoIP.U().q()) {
            String b = com.mavenir.android.settings.c.c.b();
            int g = v.g();
            String a = t.f.a(this.O, b, g);
            b = t.f.a(str, b, g);
            this.ae = new ArrayList();
            this.ae.add(h.a(this.O));
            this.ae.add(h.a(this.X));
            Intent intent = new Intent("com.fgmicrotec.mobile.android.voip.CallConferenceAddParticipantReq");
            intent.putExtra("extra_conf_active_call_uri", a);
            intent.putExtra("extra_conf_active_session_id", this.K);
            intent.putExtra("extra_conf_participant", b);
            this.f.sendBroadcast(intent);
        }
    }

    public boolean a(String str, b bVar) {
        if (str == null) {
            return false;
        }
        Intent intent;
        if (str.equals(TelephonyManager.EXTRA_STATE_IDLE)) {
            aa();
            q.b("CallManager", "ACTION_PHONE_STATE_CHANGED : TelephonyManager.EXTRA_STATE_IDLE mNumberOfCalls = " + this.ai);
            if (!this.F && this.B && this.ai == 1) {
                ab();
            } else if (this.ai == 2) {
                intent = new Intent();
                intent.setAction("com.fgmicrotec.mobile.android.voip.CallUnholdReq");
                intent.putExtra("extra_session_id", this.A);
                this.f.sendBroadcast(intent);
            }
            this.F = false;
            return false;
        } else if (str.equals(TelephonyManager.EXTRA_STATE_OFFHOOK)) {
            q.b("CallManager", "ACTION_PHONE_STATE_CHANGED : TelephonyManager.EXTRA_STATE_OFFHOOK currentCallScreen = " + bVar + " mNumberOfCalls = " + this.ai);
            aa();
            boolean B = CallService.B();
            q.a("CallManager", "CallService. vcc_callstarted " + B);
            if (!B) {
                this.F = this.B;
                if (!this.B) {
                    ab();
                }
            }
            q.a("CallManager", "mCallWasHoldWhenGSMCallArrived " + this.F);
            if (!FgVoIP.U().p() || !B) {
                return false;
            }
            this.v.postDelayed(this.av, 7000);
            return false;
        } else if (!str.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
            return false;
        } else {
            Z();
            q.b("CallManager", "ACTION_PHONE_STATE_CHANGED : TelephonyManager.EXTRA_STATE_RINGING currentCallScreen = " + bVar + " mNumberOfCalls = " + this.ai);
            Intent intent2;
            if (bVar == b.INCOMING) {
                g.a().e();
                intent2 = new Intent();
                intent2.setAction("com.fgmicrotec.mobile.android.voip.RejectInvitationReq");
                intent2.putExtra("extra_session_id", this.A);
                this.f.sendBroadcast(intent2);
                this.L = 3;
                a(this.N, this.O, this.L, System.currentTimeMillis());
                return true;
            } else if (bVar == b.INCOMING_SUPPLEMENTARY) {
                this.H = true;
                this.f.sendBroadcast(new Intent("IncomingCallActions.ActionInterruptedByIncomingGSMCall"));
                return false;
            } else if (bVar == b.OUTGOING) {
                this.an = g.a().k();
                intent2 = new Intent();
                intent2.setAction("com.fgmicrotec.mobile.android.voip.InviteCancelReq");
                intent2.putExtra("extra_uri_to_call", this.aj);
                intent2.putExtra("extra_session_id", this.A);
                this.f.sendBroadcast(intent2);
                return true;
            } else if (bVar == b.OUTGOING_CONSULTATION) {
                this.H = true;
                L();
                return false;
            } else if (this.ai != 2) {
                return false;
            } else {
                intent = new Intent();
                intent.setAction("com.fgmicrotec.mobile.android.voip.CallHoldReq");
                intent.putExtra("extra_session_id", this.A);
                this.f.sendBroadcast(intent);
                return false;
            }
        }
    }

    public void L() {
        Intent intent = new Intent();
        intent.setAction("com.fgmicrotec.mobile.android.voip.InviteCancelReq");
        intent.putExtra("extra_uri_to_call", this.X);
        intent.putExtra("extra_session_id", this.T);
        this.f.sendBroadcast(intent);
        this.T = 0;
        this.ai = 1;
        this.B = false;
        this.A = this.K;
        a(false);
    }

    public void a(String str, String str2, int i, long j) {
        e.b(str, str2, i, j, this.am);
    }

    public void M() {
        q = 0;
        FgVoIP.U().a(q, j());
        FgVoIP.U().j(false);
        FgVoIP.U().a(false, true, false);
        SimpleCodecAL.getInstance().releaseAudioStream();
        ax();
        c = false;
    }

    public boolean N() {
        return this.w != null && this.w.b();
    }

    public boolean O() {
        return this.w != null && this.w.c();
    }

    private void au() {
        this.v.postDelayed(new Runnable(this) {
            final /* synthetic */ CallManager a;

            {
                this.a = r1;
            }

            public void run() {
                if (this.a.N()) {
                    try {
                        if (this.a.at) {
                            q.b("CallManager", "spiritChannelIsCreated while setting up initial audio. BT routing will be handled by SPIRIT.");
                        } else {
                            this.a.l.startBluetoothSco();
                            this.a.ao = false;
                            this.a.ap = true;
                            this.a.l.setSpeakerphoneOn(this.a.ao);
                            this.a.l.setBluetoothScoOn(this.a.ap);
                        }
                        FgVoIP.U().a(CallManager.q, this.a.ao);
                        this.a.av();
                        return;
                    } catch (Exception e) {
                        q.d("CallManager", "setupInitialAudio(): " + e);
                        return;
                    }
                }
                if (this.a.at) {
                    q.b("CallManager", "spiritChannelIsCreated while setting up initial audio.");
                } else {
                    q.b("CallManager", "setupInitialAudio(): speaker OFF");
                    this.a.l.setSpeakerphoneOn(false);
                }
                this.a.av();
            }
        }, 500);
    }

    private void av() {
        if (this.l.isSpeakerphoneOn()) {
            q.b("CallManager", "adjustAudioMode(): Speaker ON");
        } else if (this.l.isBluetoothScoOn()) {
            q.b("CallManager", "adjustAudioMode(): Bluetooth SCO ON");
        } else if (this.l.isBluetoothA2dpOn()) {
            q.b("CallManager", "adjustAudioMode(): Bluetooth A2DP ON");
        } else {
            q.b("CallManager", "adjustAudioMode(): Earpiece ON");
        }
        if (O()) {
            q.b("CallManager", "adjustAudioMode(): isBluetoothScoAudioConnected");
        }
        this.g.i(this.ao);
        this.g.j(this.ap);
    }

    public boolean b(int i) {
        if (q == 1) {
            g.a().e();
            return true;
        }
        String str;
        String str2 = "CallManager";
        StringBuilder append = new StringBuilder().append("dispatchKeyEvent(): Volume ");
        if (i > 0) {
            str = " up";
        } else {
            str = " down";
        }
        q.a(str2, append.append(str).toString());
        return false;
    }

    private void aw() {
        if (this.l != null) {
            q.a("CallManager", "requestAudioFocus(): " + (this.l.requestAudioFocus(this, 3, 2) == 1 ? " granted" : " failed"));
            this.l.registerMediaButtonEventReceiver(this.j);
        }
    }

    private void ax() {
        if (this.l != null) {
            q.a("CallManager", "abandonAudioFocus(): " + (this.l.abandonAudioFocus(this) == 1 ? " granted" : " failed"));
            this.l.unregisterMediaButtonEventReceiver(this.j);
        }
    }

    public void P() {
        this.v.removeCallbacks(this.ay);
        if (FgVoIP.U().s() && !this.ar) {
            this.ar = true;
            this.g.w();
        } else if (this.h == b.INCOMING) {
            g.a().e();
            this.an = g.a().k();
            r0 = new Intent();
            r0.setAction("com.fgmicrotec.mobile.android.voip.AcceptInvitationReq");
            r0.putExtra("extra_session_id", this.A);
            this.f.sendBroadcast(r0);
            this.C = true;
            a(true);
        } else if (this.h == b.INCOMING_SUPPLEMENTARY) {
            g.a().j();
            if (!FgVoIP.U().q()) {
                this.A = this.K;
                this.t = true;
                ay();
            } else if (this.ai == 0) {
                a(1);
            } else if (!FgVoIP.U().T()) {
                aC();
            } else if (this.ah) {
                r0 = new Intent();
                r0.setAction("com.fgmicrotec.mobile.android.voip.InviteCancelReq");
                r0.putExtra("extra_uri_to_call", this.aj);
                r0.putExtra("extra_session_id", this.A);
                this.f.sendBroadcast(r0);
                g.a().e();
                this.an = g.a().k();
                r0 = new Intent();
                r0.setAction("com.fgmicrotec.mobile.android.voip.AcceptInvitationReq");
                r0.putExtra("extra_session_id", this.T);
                this.f.sendBroadcast(r0);
                aA();
                this.C = true;
                a(true);
            } else {
                aC();
            }
        }
    }

    public void Q() {
        if (this.h == b.INCOMING) {
            this.J = true;
            ay();
        } else if (this.h == b.INCOMING_SUPPLEMENTARY) {
            J();
        }
    }

    public void R() {
        if (this.C) {
            this.g.c(this.X);
        } else {
            this.g.c(this.O);
        }
    }

    public void S() {
        this.g.v();
    }

    public void c(boolean z) {
        this.g.d(z);
    }

    public void T() {
        ay();
    }

    public void d(boolean z) {
        this.g.a(z);
    }

    public void U() {
        this.ao = !this.ao;
        if (N()) {
            this.ap = false;
            if (!this.at) {
                this.l.stopBluetoothSco();
                this.l.setBluetoothScoOn(this.ap);
            }
            q.b("CallManager", "toggleSpeaker(): stopping bluetooth sco");
        }
        if (this.at) {
            aI();
        } else {
            this.l.setSpeakerphoneOn(this.ao);
        }
        av();
        FgVoIP.U().a(q, this.ao);
    }

    public void V() {
        if (N()) {
            this.ap = !this.ap;
            this.ao = false;
            if (!(this.at || O() || !this.ap)) {
                q.b("CallManager", "toggleBluetooth(): turning BT SCO on");
                this.l.startBluetoothSco();
            }
        } else {
            this.ap = false;
        }
        q.a("CallManager", "toggleBluetooth(): connected: " + N() + ", BT: " + this.ap);
        if (this.at) {
            aI();
        } else {
            this.l.setSpeakerphoneOn(this.ao);
            this.l.setBluetoothScoOn(this.ap);
        }
        FgVoIP.U().a(q, this.ao);
        av();
    }

    public void W() {
        if (!this.at && N()) {
            this.l.setBluetoothScoOn(false);
            this.l.stopBluetoothSco();
        }
    }

    public void X() {
        Intent intent = new Intent();
        if (this.A == this.K) {
            if (this.M) {
                intent.setAction("com.fgmicrotec.mobile.android.voip.MuteAudioReq");
            } else {
                intent.setAction("com.fgmicrotec.mobile.android.voip.UnmuteAudioReq");
            }
        } else if (this.V) {
            intent.setAction("com.fgmicrotec.mobile.android.voip.MuteAudioReq");
        } else {
            intent.setAction("com.fgmicrotec.mobile.android.voip.UnmuteAudioReq");
        }
        this.f.sendBroadcast(intent);
    }

    public void Y() {
        boolean z = true;
        if (this.A == this.K) {
            if (this.M) {
                z = false;
            }
            this.M = z;
        } else {
            if (this.V) {
                z = false;
            }
            this.V = z;
        }
        X();
    }

    public void Z() {
        try {
            if (!this.at) {
                this.l.setStreamMute(2, true);
                this.l.setRingerMode(0);
            }
            g.a().a(false);
            this.I = true;
        } catch (Exception e) {
            this.I = false;
        }
    }

    public void aa() {
        if (this.I) {
            q.a("CallManager", "Unmuting GSM Ringer.");
            try {
                if (!this.at) {
                    this.l.setStreamMute(2, false);
                    this.l.setRingerMode(2);
                }
                g.a().h();
                this.I = false;
            } catch (Exception e) {
            }
            q.a("CallManager", "Unmuted GSM Ringer.");
        }
    }

    public void ab() {
        boolean z = true;
        if (this.ai == 1) {
            Intent intent = new Intent();
            intent.setAction(this.B ? "com.fgmicrotec.mobile.android.voip.CallUnholdReq" : "com.fgmicrotec.mobile.android.voip.CallHoldReq");
            intent.putExtra("extra_session_id", this.A);
            this.f.sendBroadcast(intent);
            if (this.B) {
                this.m.a(this.N, this.O, this.Q, false, true, false, false);
            } else {
                this.m.a(this.N, this.O, this.Q, false, false, true, false);
            }
            f fVar = this.g;
            if (this.B) {
                z = false;
            }
            fVar.f(z);
        }
    }

    public void ac() {
        q.a("CallManager", "upgradeToVideoCall(): mFirstCallHasVideo " + this.S);
        if (this.ai == 1) {
            Intent intent = new Intent();
            intent.setAction("com.fgmicrotec.mobile.android.voip.AddVideoReq");
            this.f.sendBroadcast(intent);
            this.g.p();
        }
    }

    public void ad() {
        q.a("CallManager", "downgradeToAudioCall(): mFirstCallHasVideo " + this.S);
        if (this.ai == 1) {
            Intent intent = new Intent();
            intent.setAction("com.fgmicrotec.mobile.android.voip.RemoveVideoReq");
            this.f.sendBroadcast(intent);
            this.g.q();
        }
    }

    public void ae() {
        Intent intent = new Intent();
        intent.setAction("com.fgmicrotec.mobile.android.voip.CallToggleReq");
        intent.putExtra("extra_session_id", this.A);
        this.f.sendBroadcast(intent);
    }

    public void af() {
        if (!this.B) {
            ab();
        }
        I();
    }

    public void ag() {
        K();
    }

    public void ah() {
        boolean z;
        String a;
        String a2;
        int i;
        String b = com.mavenir.android.settings.c.c.b();
        int g = v.g();
        if (this.S && this.ab) {
            z = true;
        } else {
            z = false;
        }
        q.a("CallManager", "merge(): mFirstCallHasVideo = " + this.S + ", mSecondCallHasVideo = " + this.ab);
        a(this.N, this.O, this.L, System.currentTimeMillis());
        a(this.W, this.X, this.U, System.currentTimeMillis());
        if (this.A == this.K) {
            a = t.f.a(this.O, b, g);
            a2 = t.f.a(this.X, b, g);
            i = this.T;
        } else {
            a = t.f.a(this.X, b, g);
            a2 = t.f.a(this.O, b, g);
            i = this.K;
        }
        this.ae = new ArrayList();
        this.ae.add(h.a(this.O));
        this.ae.add(h.a(this.X));
        Intent intent = new Intent("com.fgmicrotec.mobile.android.voip.CallConferenceMergeReq");
        intent.putExtra("extra_conf_active_call_uri", a);
        intent.putExtra("extra_conf_held_call_uri", a2);
        intent.putExtra("extra_conf_active_session_id", this.A);
        intent.putExtra("extra_conf_held_session_id", i);
        intent.putExtra("extra_video_call", z);
        this.f.sendBroadcast(intent);
    }

    public void a(int i, boolean z) {
        g();
        if (this.n == null) {
            return;
        }
        if (z) {
            this.n.startTone(i, ActivationAdapter.OP_CONFIGURATION_INITIAL);
        } else {
            this.n.startTone(i, 950);
        }
    }

    public void ai() {
        if (this.n != null) {
            this.n.stopTone();
        }
    }

    public void a(char c, boolean z) {
        q.a("CallManager", "processDTMF(): digit: " + c);
        if (!this.at) {
            this.l.setMicrophoneMute(true);
        }
        c(String.valueOf(c));
        if (!this.at) {
            a(((Integer) au.get(Character.valueOf(c))).intValue(), z);
        }
    }

    public void aj() {
        q.a("CallManager", "stopProcessingDTMF()");
        ai();
        ak();
        if (!this.at) {
            this.l.setMicrophoneMute(false);
        }
    }

    public void c(String str) {
        Intent intent = new Intent();
        intent.setAction("com.fgmicrotec.mobile.android.voip.SendGenericDTMFReq");
        intent.putExtra("extra_dtmf_string", str);
        this.f.sendBroadcast(intent);
    }

    public void ak() {
        Intent intent = new Intent();
        intent.setAction("com.fgmicrotec.mobile.android.voip.StopSendingGenericDTMFReq");
        this.f.sendBroadcast(intent);
    }

    public void al() {
        if (h.i()) {
            Intent intent = new Intent(this.f, CallService.class);
            intent.setAction("com.fgmicrotec.mobile.android.voip.QosReportReq");
            this.f.startService(intent);
        }
    }

    public void am() {
        if (h.i()) {
            Intent intent = new Intent(this.f, CallService.class);
            intent.setAction("com.fgmicrotec.mobile.android.voip.QosMarkReportReq");
            this.f.startService(intent);
        }
    }

    public b an() {
        return this.h;
    }

    public String ao() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss", this.f.getResources().getConfiguration().locale);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        long currentTimeMillis = System.currentTimeMillis();
        long j = 0;
        if (this.A == this.K) {
            j = (long) ((int) ((currentTimeMillis - this.Q) + 500));
        } else if (this.A == this.T) {
            j = (long) ((int) ((currentTimeMillis - this.Z) + 500));
        } else if (this.A == this.ac) {
            j = (long) ((int) ((currentTimeMillis - this.ad) + 500));
        }
        return simpleDateFormat.format(new Date(j));
    }

    public String c(int i) {
        if (i == VoIP.REASON_CODE_CALLEE_BUSY || i == VoIP.REASON_CODE_DECLINE || i == VoIP.REASON_CODE_BUSY_EVERYWHERE || i == VoIP.REASON_CODE_CALLEE_TEMP_UNAVAILABLE) {
            return this.f.getString(k.call_busy);
        }
        if (i == VoIP.REASON_CODE_REQUEST_TERMINATED || i == VoIP.REASON_CODE_REQUEST_TIMEOUT) {
            return this.f.getString(k.call_timeout);
        }
        return this.f.getString(k.call_ended);
    }

    private void ay() {
        q.a("CallManager", " handleCallTerminationRequested()");
        this.v.removeCallbacks(this.aw);
        this.v.removeCallbacks(this.ay);
        if (FgVoIP.U().p()) {
            this.v.removeCallbacks(this.av);
        }
        Intent intent;
        if (this.G) {
            intent = new Intent();
            intent.setAction("com.fgmicrotec.mobile.android.voip.CallTerminateReq");
            intent.putExtra("extra_session_id", this.A);
            this.f.sendBroadcast(intent);
            az();
        } else if (this.ai == 2) {
            intent = new Intent();
            intent.setAction("com.fgmicrotec.mobile.android.voip.CallTerminateReq");
            intent.putExtra("extra_session_id", this.A);
            this.f.sendBroadcast(intent);
            d(this.A);
        } else if (this.C && this.E) {
            this.E = false;
            intent = new Intent();
            intent.setAction("com.fgmicrotec.mobile.android.voip.InviteCancelReq");
            intent.putExtra("extra_uri_to_call", this.aj);
            intent.putExtra("extra_session_id", this.A);
            this.f.sendBroadcast(intent);
            d(this.A);
        } else {
            b(0, !this.t);
            if (this.C) {
                intent = new Intent();
                intent.setAction("com.fgmicrotec.mobile.android.voip.CallTerminateReq");
                intent.putExtra("extra_session_id", this.A);
                this.f.sendBroadcast(intent);
                a(this.N, this.O, this.L, this.Q);
                this.s = true;
                this.at = false;
            } else if ("InCallActions.ActionOutgoingCall".equals(p) || "InCallActions.ActionOutgoingVideoCall".equals(p)) {
                this.an = g.a().k();
                intent = new Intent();
                intent.setAction("com.fgmicrotec.mobile.android.voip.InviteCancelReq");
                intent.putExtra("extra_uri_to_call", this.aj);
                intent.putExtra("extra_session_id", this.A);
                this.f.sendBroadcast(intent);
            } else {
                if (this.m != null) {
                    this.m.g();
                }
                g.a().e();
                intent = new Intent();
                intent.setAction("com.fgmicrotec.mobile.android.voip.RejectInvitationReq");
                intent.putExtra("extra_session_id", this.A);
                this.f.sendBroadcast(intent);
                this.L = 1;
                a(this.N, this.O, this.L, System.currentTimeMillis());
                this.s = true;
            }
        }
    }

    private void az() {
        d = null;
        this.g.a(0);
        this.h = b.CALL_ENDED;
        this.v.postDelayed(this.ax, 3000);
        this.s = true;
        this.A = 0;
        this.K = 0;
        this.T = 0;
        this.V = false;
        this.ai = 0;
        this.B = false;
    }

    private void d(int i) {
        if (this.G) {
            if (i == this.K) {
                this.A = this.T;
                this.K = this.T;
                this.Q = this.Z;
            } else {
                this.A = this.K;
            }
            this.T = 0;
            this.V = false;
            this.ai = 1;
            this.B = false;
        } else {
            if (i == this.K) {
                a(this.N, this.O, this.L, this.Q);
                this.g.e(true);
                aA();
            } else {
                a(this.W, this.X, this.U, this.Z);
                this.A = this.K;
                this.T = 0;
                this.V = false;
                this.ai = 1;
                this.B = false;
                this.g.e(false);
            }
            b(false);
        }
        X();
        this.g.o();
    }

    private void g(boolean z) {
        aA();
        a(false);
    }

    private void aA() {
        this.O = this.X;
        this.N = this.W;
        this.P = this.Y;
        this.M = this.V;
        this.R = this.aa;
        this.Q = this.Z;
        this.A = this.T;
        this.K = this.T;
        this.T = 0;
        this.L = this.U;
        this.ai = 1;
        this.B = false;
        this.V = false;
    }

    private void aB() {
        a(this.N, this.O, this.L, this.Q);
        a(this.W, this.X, this.U, this.Z);
        this.A = 0;
        this.K = 0;
        this.T = 0;
        this.ai = 0;
        this.B = false;
        this.C = false;
        this.G = false;
        b(0, false);
        this.g.a();
    }

    private void b(int i, boolean z) {
        d = null;
        this.g.a(i, this.C, (int) (System.currentTimeMillis() - this.Q), this.O);
        this.h = b.CALL_ENDED;
        if (z) {
            q.a("CallManager", "displayOnCallEnded(): delaying by 3000ms");
            this.v.postDelayed(this.ax, 3000);
        }
        this.at = false;
    }

    public void ap() {
        this.g.t();
    }

    private void aC() {
        Builder builder = new Builder(this.f);
        builder.setTitle(k.card_title_incoming_call);
        CharSequence[] charSequenceArr = new String[2];
        charSequenceArr[0] = this.f.getResources().getString(k.incoming_put_first_on_hold, new Object[]{this.N});
        charSequenceArr[1] = this.f.getResources().getString(k.incoming_end_first_and_answer, new Object[]{this.N});
        builder.setItems(charSequenceArr, new OnClickListener(this) {
            final /* synthetic */ CallManager a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                this.a.a(i);
                dialogInterface.dismiss();
                this.a.z = null;
            }
        });
        this.z = builder.create();
        this.z.setCancelable(false);
        this.z.setCanceledOnTouchOutside(false);
        this.z.show();
    }

    private void aD() {
        if (this.z != null) {
            this.z.dismiss();
            this.z = null;
        }
    }

    private void aE() {
        Intent intent = new Intent();
        intent.setAction("com.fgmicrotec.mobile.android.voip.Call3WayReq");
        intent.putExtra("extra_uri_to_call", p.f().replaceFirst(":", ":conf_"));
        this.f.sendBroadcast(intent);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void c(int r4, boolean r5) {
        /*
        r3 = this;
        r0 = 1;
        r1 = 0;
        if (r5 == 0) goto L_0x003c;
    L_0x0004:
        r2 = 400; // 0x190 float:5.6E-43 double:1.976E-321;
        if (r4 < r2) goto L_0x002a;
    L_0x0008:
        r2 = 486; // 0x1e6 float:6.81E-43 double:2.4E-321;
        if (r4 == r2) goto L_0x002a;
    L_0x000c:
        r2 = 600; // 0x258 float:8.41E-43 double:2.964E-321;
        if (r4 == r2) goto L_0x002a;
    L_0x0010:
        r2 = 603; // 0x25b float:8.45E-43 double:2.98E-321;
        if (r4 == r2) goto L_0x002a;
    L_0x0014:
        if (r0 == 0) goto L_0x0029;
    L_0x0016:
        r0 = new android.content.Intent;
        r1 = "MainTabActions.RetryLastCall";
        r0.<init>(r1);
        r1 = "MainTabExtras.ExtraNumberToRetry";
        r2 = r3.O;
        r0.putExtra(r1, r2);
        r1 = r3.f;
        r1.sendBroadcast(r0);
    L_0x0029:
        return;
    L_0x002a:
        r2 = 24;
        if (r4 == r2) goto L_0x0014;
    L_0x002e:
        r2 = 25;
        if (r4 == r2) goto L_0x0014;
    L_0x0032:
        r2 = 11;
        if (r4 == r2) goto L_0x0014;
    L_0x0036:
        r2 = 16;
        if (r4 == r2) goto L_0x0014;
    L_0x003a:
        r0 = r1;
        goto L_0x0014;
    L_0x003c:
        r2 = 8;
        if (r4 != r2) goto L_0x003a;
    L_0x0040:
        goto L_0x0014;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mavenir.android.common.CallManager.c(int, boolean):void");
    }

    private void aF() {
        if (FgVoIP.U().aq()) {
            if (this.o == null) {
                this.o = new com.mavenir.android.c.a(this.f, this);
            }
            List arrayList = new ArrayList();
            arrayList.add(CallService.u());
            this.o.a(arrayList);
        }
    }

    private void aG() {
        if (this.o != null) {
            this.o.a();
        } else {
            q.d("CallManager", "deactivateHipri(): unable to deactivate");
        }
    }

    private boolean aH() {
        q.a("CallManager", "activateHipri(): activating HIPRI");
        if (this.x != null) {
            int startUsingNetworkFeature = this.x.startUsingNetworkFeature(0, "enableHIPRI");
            q.a("CallManager", "activateHipri(): HIPRI " + (startUsingNetworkFeature >= 0 ? "enabled" : "disabled"));
            if (startUsingNetworkFeature == -1) {
                return false;
            }
            State state = this.x.getNetworkInfo(5).getState();
            startUsingNetworkFeature = 0;
            while (startUsingNetworkFeature < 10) {
                state = this.x.getNetworkInfo(5).getState();
                if (state.compareTo(State.CONNECTED) == 0) {
                    q.b("CallManager", "activateHipri(): current HIPRI State = " + state);
                    break;
                }
                try {
                    Thread.sleep(300);
                    startUsingNetworkFeature++;
                } catch (InterruptedException e) {
                }
            }
            if (state == State.CONNECTED) {
                String u = CallService.u();
                if (this.x.requestRouteToHost(5, t.e.a(u))) {
                    q.b("CallManager", "activateHipri(): HIPRI routed to: " + u);
                    this.v.postDelayed(this.aE, 30000);
                    return true;
                }
                q.d("CallManager", "activateHipri(): cannot route HIPRI routed to: " + u);
            } else {
                q.d("CallManager", "activateHipri(): HIPRI connection failed");
            }
        }
        return false;
    }

    public void a(com.mavenir.android.c.a.b bVar) {
        q.a("CallManager", "hipriStateChanged(): " + bVar.name());
    }

    private void aI() {
        Intent intent;
        if (this.ao || this.ap) {
            intent = new Intent();
            intent.setAction("com.fgmicrotec.mobile.android.voip.UpdateAudioMode");
            intent.putExtra(VoIP.AUDIO_BT_MODE, this.ap);
            intent.putExtra(VoIP.AUDIO_SPEAKER_MODE, this.ao);
            intent.putExtra(VoIP.AUDIO_HANDSET_MODE, false);
            intent.putExtra(VoIP.AUDIO_HEASDET_MODE, false);
            intent.putExtra(VoIP.AUDIO_HEADPHONES_MODE, false);
            this.f.sendBroadcast(intent);
        } else if (this.l.isWiredHeadsetOn()) {
            intent = new Intent();
            intent.setAction("com.fgmicrotec.mobile.android.voip.UpdateAudioMode");
            intent.putExtra(VoIP.AUDIO_BT_MODE, false);
            intent.putExtra(VoIP.AUDIO_SPEAKER_MODE, false);
            intent.putExtra(VoIP.AUDIO_HANDSET_MODE, false);
            intent.putExtra(VoIP.AUDIO_HEASDET_MODE, aK());
            intent.putExtra(VoIP.AUDIO_HEADPHONES_MODE, aJ());
            this.f.sendBroadcast(intent);
        } else {
            intent = new Intent();
            intent.setAction("com.fgmicrotec.mobile.android.voip.UpdateAudioMode");
            intent.putExtra(VoIP.AUDIO_BT_MODE, false);
            intent.putExtra(VoIP.AUDIO_SPEAKER_MODE, false);
            intent.putExtra(VoIP.AUDIO_HANDSET_MODE, true);
            intent.putExtra(VoIP.AUDIO_HEASDET_MODE, false);
            intent.putExtra(VoIP.AUDIO_HEADPHONES_MODE, false);
            this.f.sendBroadcast(intent);
        }
    }

    private boolean aJ() {
        return a && !b;
    }

    private boolean aK() {
        return a && b;
    }
}
