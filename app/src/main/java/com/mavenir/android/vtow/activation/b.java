package com.mavenir.android.vtow.activation;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.o;
import android.text.TextUtils;
import com.fgmicrotec.mobile.android.fgmag.DataConnectionManager;
import com.fgmicrotec.mobile.android.fgvoip.FgVoIP;
import com.fgmicrotec.mobile.android.fgvoip.f.k;
import com.fgmicrotec.mobile.android.fgvoipcommon.CallService;
import com.mavenir.android.applog.AppLogAdapter;
import com.mavenir.android.applog.AppLogAdapter.d;
import com.mavenir.android.c.a.c;
import com.mavenir.android.common.l;
import com.mavenir.android.common.q;
import com.mavenir.android.common.t;
import com.mavenir.android.common.t.h;
import com.mavenir.android.settings.c.e;
import com.mavenir.android.settings.c.i;
import com.mavenir.android.settings.c.j;
import com.mavenir.android.settings.c.m;
import com.mavenir.android.settings.c.n;
import com.mavenir.android.settings.c.p;
import com.mavenir.android.settings.c.s;
import com.mavenir.android.settings.c.v;
import com.mavenir.android.settings.c.w;
import com.mavenir.android.settings.c.x;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class b implements c, a {
    private static b b = null;
    private Context a;
    private ActivationAdapter c;
    private com.mavenir.android.c.a d;
    private a e;
    private boolean f;
    private boolean g;
    private Handler h;
    private Runnable i;

    public interface a {
        void a(com.mavenir.android.c.a.b bVar);
    }

    public void a(com.mavenir.android.c.a.b bVar) {
        q.a("ActivationService", "hipriStateChanged(): new state = " + bVar.name());
        if (this.e != null) {
            this.e.a(bVar);
        }
    }

    private b(Context context) {
        this.a = null;
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = false;
        this.g = false;
        this.h = new Handler(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void handleMessage(Message message) {
                q.a("ActivationService", "handleMessage(): what=" + message.what);
                boolean I = l.a(this.a.a).I();
                if (message.what == 100) {
                    if (FgVoIP.U().H()) {
                        this.a.a((int) ActivationAdapter.OP_CONFIGURATION_DAILY, false);
                    } else {
                        this.a.a(0, false);
                    }
                } else if (message.what == 101 || message.what == 104 || message.what == 102) {
                    if (!FgVoIP.U().f(false)) {
                        FgVoIP.U().a(com.mavenir.android.fragments.f.a.H3G_ACTIVATION, 0, 0, this.a.a.getString(k.activation_exception_lte_timeout));
                    } else if (message.what == 102) {
                        this.a.a((int) ActivationAdapter.OP_CONFIGURATION_DAILY, false);
                    } else {
                        boolean A = l.a(this.a.a).A();
                        if (!I) {
                            if (A) {
                                this.a.a((int) ActivationAdapter.OP_CONFIGURATION_DAILY, false);
                            } else {
                                this.a.a((int) ActivationAdapter.OP_LTE_PS_ACTIVATION, false);
                            }
                        }
                    }
                } else if (message.what == 103) {
                    if (FgVoIP.U().f(false) && !I) {
                        this.a.a((int) ActivationAdapter.OP_LTE_PS_ACTIVATION, false);
                    }
                } else if (message.what != 105) {
                    super.handleMessage(message);
                } else if (!FgVoIP.U().f(false)) {
                    FgVoIP.U().a(com.mavenir.android.fragments.f.a.H3G_ACTIVATION, 0, 0, this.a.a.getString(k.activation_exception_lte_timeout));
                } else if (!I) {
                    this.a.a((int) ActivationAdapter.OP_LTE_PS_DEACTIVATION, false);
                }
            }
        };
        this.i = new Runnable(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void run() {
                FgVoIP.U().i();
            }
        };
        this.c = new ActivationAdapter(this);
        this.c.init();
        this.a = context.getApplicationContext();
    }

    public static b a(Context context) {
        if (b == null) {
            b = new b(context);
        }
        return b;
    }

    private String h() {
        StringBuilder stringBuilder = new StringBuilder(64);
        if (FgVoIP.U().getString(k.app_name).equals("WiFi Talk")) {
            stringBuilder.append("Optus");
        } else {
            stringBuilder.append(FgVoIP.U().getString(k.app_name_short).replace(" ", ""));
        }
        stringBuilder.append("_");
        stringBuilder.append(FgVoIP.U().getString(k.app_country));
        stringBuilder.append("_Android_");
        stringBuilder.append(l.a(this.a).c());
        stringBuilder.append("_XML3");
        return stringBuilder.toString();
    }

    public void b(int i) {
        String h = h();
        String p = l.a(this.a).p();
        String r = l.a(this.a).r();
        q.a("ActivationService", "IMSI 2 details" + r);
        boolean ag = FgVoIP.U().ag();
        com.fgmicrotec.mobile.android.fgmag.DataConnectionManager.a H = l.a(this.a).H();
        Object i2 = i();
        int length = i2 == null ? 0 : i2.length;
        q.a("ActivationService", "provisioningReq(): , opCode= " + i + ", vers=" + -3 + ", appID=" + h + ", deviceIMSI=" + p + ", deviceIMSI2=" + r + ", FQDN count=" + i2 + ", bearer: " + H.name());
        this.c.provisioningReq(i, -3, h, p, r, ag, length, i2, H.ordinal());
    }

    public void a(String str, String str2, String[] strArr) {
        String h = h();
        String p = l.a(this.a).p();
        boolean ag = FgVoIP.U().ag();
        String[] i = i();
        int length = i == null ? 0 : i.length;
        q.a("ActivationService", "provisioningExtReq(): vers=" + -3 + ", appID=" + h + ", deviceIMSI=" + p + ", FQDN count=" + strArr.length + ", headerIMSI=" + str + ", headerMSIDN=" + str2);
        this.c.provisioningExtReq(-3, h, p, ag, length, strArr, str, str2);
    }

    public void a(int i, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, int i2, String[] strArr, int i3, String str9, String str10) {
        if (i3 == com.mavenir.android.vtow.activation.ActivationAdapter.b.PROVISIONING_OK.ordinal()) {
            a(i, str, str2, str3, str4, str5, str6, str7, str8, i2, strArr, str10);
        }
        Intent intent = new Intent();
        intent.setAction("com.mavenir.android.activation.action_provisioning_cnf");
        intent.putExtra("com.mavenir.android.activation.extra_vers", i);
        intent.putExtra("com.mavenir.android.activation.extra_error_code", i3);
        intent.putExtra("com.mavenir.android.activation.error_message", str9);
        o.a(this.a).a(intent);
    }

    public void a(int i, boolean z) {
        if (this.g) {
            q.a("ActivationService", "configurationReq(): configuration in progress, aborting new request...");
        } else if (FgVoIP.U().f(z)) {
            j();
            int d = com.mavenir.android.settings.c.k.d();
            String h = h();
            boolean ag = FgVoIP.U().ag();
            boolean d2 = com.mavenir.android.settings.c.l.d();
            String h2 = l.a(this.a).h();
            String f = p.f();
            String g = p.g();
            String h3 = p.h();
            String i2 = p.i();
            String j = p.j();
            if (j != null) {
                j = j.toLowerCase();
            }
            String k = p.k();
            String l = p.l();
            String m = p.m();
            String n = p.n();
            String[] split = p.p().split(";");
            com.fgmicrotec.mobile.android.fgmag.DataConnectionManager.a H = l.a(this.a).H();
            q.a("ActivationService", "configurationReq():, opCode= " + i + ", vers=" + d + ", appVers=" + h + ", OSVers=" + h2 + ", Rooted=" + (ag ? "yes" : "no") + ", IMPU=" + FgVoIP.U().c(f) + ", IMPI=" + FgVoIP.U().c(g) + ", Pass=" + FgVoIP.U().c(h3) + ", hashedMSISDN=" + i2 + ", doubleHashedMSISDN=" + j + ", MSISDN=" + k + ", TAC=" + l + ", SVN=" + m + ", networkIMSI=" + FgVoIP.U().c(n) + ", FQDN count=" + split.length + ", bearer=" + H.name() + ", IMEI=" + p.o());
            if (a()) {
                this.h.removeMessages(104);
                this.g = true;
                this.c.configurationReq(i, d, h, h2, j, ag, d2, f, g, h3, i2, k, l, m, n, split.length, split, H.ordinal(), p.o());
                return;
            }
            this.g = false;
            FgVoIP.U().a(this.a, "com.mavenir.android.action_backup_deactivated", com.mavenir.android.vtow.activation.ActivationAdapter.a.FGVOIPCPROXY_DEACTIVATION_MUST_REPROVISION_CREDENTIALS.ordinal());
            com.mavenir.android.applog.a.a(this.a).a(com.mavenir.android.applog.AppLogAdapter.b.FGAPPLOG_EVENT_GROUP_PROVISIONING_ERROR, d.FGAPPLOG_EVENT_TYPE_DEACTIVATED, AppLogAdapter.c.FGAPPLOG_EVENT_REASON_OTHER, this.a.getString(k.activation_provisioning_reason_wrong_parameters));
        }
    }

    public boolean a() {
        if (!FgVoIP.U().getString(k.app_name).equals("Three inTouch") || p.f().contains("dev.mavenir.lab")) {
            return true;
        }
        String[] split = p.p().split(";");
        if (split == null || split.length == 0 || ((split.length == 1 && TextUtils.isEmpty(split[0])) || (split.length == 2 && TextUtils.isEmpty(split[0]) && TextUtils.isEmpty(split[1])))) {
            q.d("ActivationService", "checkConfigurationParams(): No valid FQDN set");
            return false;
        } else if (TextUtils.isEmpty(p.f())) {
            q.d("ActivationService", "checkConfigurationParams(): No valid IMPU");
            return false;
        } else if (TextUtils.isEmpty(p.g())) {
            q.d("ActivationService", "checkConfigurationParams(): No valid IMPI");
            return false;
        } else if (TextUtils.isEmpty(p.l())) {
            q.d("ActivationService", "checkConfigurationParams(): No valid TAC");
            return false;
        } else if (TextUtils.isEmpty(p.m())) {
            q.d("ActivationService", "checkConfigurationParams(): No valid SVN");
            return false;
        } else {
            Object i = p.i();
            if (!TextUtils.isEmpty(i) && i.length() >= 32) {
                return true;
            }
            q.d("ActivationService", "checkConfigurationParams(): No valid hashed MSISDN");
            return false;
        }
    }

    public void a(int i, int i2, int i3, ConfigurationData configurationData, ConfigurationDataLte configurationDataLte, int i4, String str, String str2) {
        b(i, i2, i3, configurationData, configurationDataLte, i4, str, str2);
        if (i4 == com.mavenir.android.vtow.activation.ActivationAdapter.b.H3GPROVISIONING_LTE_RESPONSE.ordinal()) {
            c(i, i2, i3, configurationData, configurationDataLte, i4, str, str2);
        } else if (i3 == ActivationAdapter.OP_LTE_PS_ACTIVATION || i3 == ActivationAdapter.OP_LTE_PS_ACTIVATION) {
            a(i3, i4);
            c();
            FgVoIP.U().a("com.mavenir.android.ActionConectivityChange");
        } else {
            if (i4 == com.mavenir.android.vtow.activation.ActivationAdapter.b.PROVISIONING_OK.ordinal()) {
                com.mavenir.android.settings.c.q.a(i2);
                a(configurationData);
                if (com.mavenir.android.settings.c.k.u()) {
                    FgVoIP.U().a("com.mavenir.android.messaging.service.MessagingService", "IntentActions.StartServiceReq");
                }
            } else if (i4 == com.mavenir.android.vtow.activation.ActivationAdapter.b.PROVISIONING_CONFIGURATION_UNAVAILABLE.ordinal()) {
                com.mavenir.android.settings.c.q.a(i2);
                if (!TextUtils.isEmpty(str)) {
                    FgVoIP.U().a(com.mavenir.android.fragments.f.a.H3G_ACTIVATION, str);
                }
                if (i > 0) {
                    i4 = com.mavenir.android.vtow.activation.ActivationAdapter.b.PROVISIONING_OK.ordinal();
                }
            } else if (i4 == com.mavenir.android.vtow.activation.ActivationAdapter.b.PROVISIONING_FAILED_BLOCKED.ordinal()) {
                com.mavenir.android.settings.c.q.a(i2);
            }
            com.mavenir.android.settings.c.q.b(System.currentTimeMillis());
            c();
            this.h.post(this.i);
            if (com.mavenir.android.settings.c.k.d() > 0 && i < 0) {
                FgVoIP.U().a(com.mavenir.android.fragments.f.a.H3G_DEACTIVATION, str);
                if (FgVoIP.U().at()) {
                    q.a("ActivationService", "configurationCnf(): logging out due to app deactivation");
                    FgVoIP.U().a("com.fgmicrotec.mobile.android.voip.LogoutFromServerReq");
                }
            }
            com.mavenir.android.settings.c.k.a(i);
            DataConnectionManager.clearAllConnectionsData();
            Intent intent = new Intent();
            intent.setAction("com.mavenir.android.activation.action_configuration_cnf");
            intent.putExtra("com.mavenir.android.activation.extra_op_code", i3);
            intent.putExtra("com.mavenir.android.activation.extra_error_code", i4);
            intent.putExtra("com.mavenir.android.activation.error_message", str);
            o.a(this.a).a(intent);
        }
        this.g = false;
    }

    private void b(int i, int i2, int i3, ConfigurationData configurationData, ConfigurationDataLte configurationDataLte, int i4, String str, String str2) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("configurationCnf(): ");
        stringBuilder.append("vers: " + i);
        stringBuilder.append(", validity: " + i2);
        stringBuilder.append(", opCode: " + i3);
        if (configurationData != null) {
            stringBuilder.append(", ");
            stringBuilder.append(configurationData.toString());
        }
        if (configurationDataLte != null) {
            stringBuilder.append(", ");
            stringBuilder.append(configurationDataLte.toString());
        }
        stringBuilder.append(", error: " + i4);
        stringBuilder.append(", msg: " + str);
        stringBuilder.append(", help URI: " + str2);
        q.a("ActivationService", stringBuilder.toString());
    }

    public void a(int i) {
        q.b("ActivationService", "deactivationInd(): reason = " + com.mavenir.android.vtow.activation.ActivationAdapter.a.values()[i].name());
        FgVoIP.U().a(this.a, "com.mavenir.android.action_backup_deactivated", i);
    }

    private String[] i() {
        if (com.mavenir.android.settings.c.q.v().length() > 1) {
            return new String[]{com.mavenir.android.settings.c.q.u(), com.mavenir.android.settings.c.q.v()};
        } else if (com.mavenir.android.settings.c.q.u().length() <= 1) {
            return null;
        } else {
            return new String[]{com.mavenir.android.settings.c.q.u()};
        }
    }

    private void a(int i, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, int i2, String[] strArr, String str9) {
        com.mavenir.android.settings.c.k.a(i);
        p.c(str);
        p.d(str2);
        p.e(str3);
        p.h(str5);
        p.f(str4);
        p.i(str6);
        p.j(str7);
        p.k(str8);
        p.m(com.mavenir.android.common.t.b.a(strArr, ";"));
        p.l(str9);
        com.mavenir.android.settings.c.k.b(-1);
        j();
    }

    private void a(ConfigurationData configurationData) {
        com.mavenir.android.settings.c.q.b(System.currentTimeMillis());
        com.mavenir.android.settings.c.q.b(configurationData.getMinWiFiThreshold());
        com.mavenir.android.settings.c.q.c(configurationData.getWifiHysterisisTmr());
        com.mavenir.android.settings.c.q.d(configurationData.getNextSBCTmr());
        com.mavenir.android.settings.c.q.e(configurationData.getHSLostTmr());
        com.mavenir.android.settings.c.q.f(configurationData.getStayAliveTmr());
        com.mavenir.android.settings.c.q.g(configurationData.getRetryLoginTmr());
        com.mavenir.android.settings.c.q.h(configurationData.getWifiQoSThreshold());
        com.mavenir.android.settings.c.q.i(configurationData.getMaxJitter());
        com.mavenir.android.settings.c.q.j(configurationData.getMaxRoundTripDelay());
        com.mavenir.android.settings.c.q.k(configurationData.getRTPMaxComulativeLoss());
        com.mavenir.android.settings.c.q.l(configurationData.getRTPMaxFractionalLoss());
        com.mavenir.android.settings.c.q.m(configurationData.getTLSRevokeCheckTmr());
        com.mavenir.android.settings.c.q.n(configurationData.getTLSSessionCacheTmr());
        com.mavenir.android.settings.c.q.o(configurationData.getRegisterNoResponseRetryTimer());
        com.mavenir.android.settings.c.q.p(configurationData.getInviteEarlyNoResponseTimeout());
        com.mavenir.android.settings.c.q.a(configurationData.getGetPublicIPURL());
        com.mavenir.android.settings.c.q.a(configurationData.getBackOffTimerList());
        com.mavenir.android.settings.c.q.b(configurationData.getUserDeactivationMessage());
        if (configurationData.getAcceptableTimeDiff() >= 0) {
            com.mavenir.android.settings.c.q.q(configurationData.getAcceptableTimeDiff());
        }
        com.mavenir.android.settings.c.k.j(configurationData.isKeepAliveEnable());
        n.e(configurationData.getKeepAliveIdleTime());
        n.f(configurationData.getKeepAliveProbeTime());
        n.g(configurationData.getKeepAliveProbeCount());
        x.a(configurationData.isDrvccFeatureEnable());
        x.a(configurationData.getDrvccStnNumber());
        x.a(configurationData.getWifiQoSThreshold());
        s.b(configurationData.getNoMediaActivityTmr());
        v.a(configurationData.getSBCFqdn());
        if (configurationData.getSIPPort() != 0) {
            v.a(configurationData.getSIPPort());
        }
        if (configurationData.getSIPTransport() != -1) {
            v.e(configurationData.getSIPTransport());
        }
        if (configurationData.getSMSType() != -1) {
            w.a(configurationData.getSMSType());
        }
        w.a(configurationData.getSMSC());
        com.mavenir.android.settings.c.q.a(configurationData.getCodec());
        e eVar = e.g()[0];
        m.a(eVar.a());
        if (eVar == e.AMRWB) {
            m.b(8);
            m.a(false);
        } else if (eVar == e.AMR) {
            m.b(7);
            m.a(false);
        }
        i.a(configurationData.getEmergencyNumbersList());
        com.mavenir.android.settings.c.o.a(configurationData.getNativeNumbersList());
        if (!TextUtils.isEmpty(configurationData.getUTUri())) {
            com.mavenir.android.settings.c.c.f(configurationData.getUTUri());
        }
        if (configurationData.getUTPort() > 0) {
            com.mavenir.android.settings.c.c.a(configurationData.getUTPort());
        }
        com.mavenir.android.settings.c.c.g(configurationData.getEchoServiceNumber());
        a(configurationData.getExceptionListMsg());
        com.mavenir.android.settings.c.k.b(-1);
    }

    private void a(ConfigurationDataLte configurationDataLte) {
        if (configurationDataLte != null) {
            com.mavenir.android.settings.c.l.c(configurationDataLte.isLTENetworkEnabled());
            com.mavenir.android.settings.c.l.b(configurationDataLte.isLTENetworkEnabled());
            if (!TextUtils.isEmpty(configurationDataLte.getLTEExpires())) {
                com.mavenir.android.settings.c.l.a(configurationDataLte.getLTEExpiresLong());
            }
            if (configurationDataLte.isLTEDeviceCapable() != null) {
                com.mavenir.android.settings.c.l.a(configurationDataLte.isLTEDeviceCapable().booleanValue());
            }
            if (configurationDataLte.getPSMNC() >= 0) {
                com.mavenir.android.settings.c.l.b(configurationDataLte.getPSMNC());
            }
            if (configurationDataLte.getPSMCC() >= 0) {
                com.mavenir.android.settings.c.l.c(configurationDataLte.getPSMCC());
            }
            if (configurationDataLte.getLTETacMin() >= 0) {
                com.mavenir.android.settings.c.l.d(configurationDataLte.getLTETacMin());
            }
            if (configurationDataLte.getLTETacMax() >= 0) {
                com.mavenir.android.settings.c.l.e(configurationDataLte.getLTETacMax());
            }
            if (configurationDataLte.getLTESBCFqdn() != null) {
                com.mavenir.android.settings.c.l.a(configurationDataLte.getLTESBCFqdn());
            }
            if (configurationDataLte.getSIPPort() > 0) {
                com.mavenir.android.settings.c.l.a(configurationDataLte.getSIPPort());
            }
        }
    }

    private void a(Exception[] exceptionArr) {
        if (exceptionArr != null && exceptionArr.length > 0) {
            j.a();
            for (Exception exception : exceptionArr) {
                j.a(exception.getExceptionId(), exception.getExceptionMsg(), exception.getExceptionUrl());
            }
        }
    }

    private void j() {
        if (TextUtils.isEmpty(p.j())) {
            p.g(h.b("SHA256", p.i().concat(p.l())).toLowerCase());
        }
    }

    public void b() {
        if (FgVoIP.U().H()) {
            int d = com.mavenir.android.settings.c.k.d();
            Object x = com.mavenir.android.settings.c.k.x();
            String c = l.a(this.a).c();
            if (d <= -3 || (!TextUtils.isEmpty(x) && x.equals(c))) {
                c();
                return;
            } else {
                a((int) ActivationAdapter.OP_CONFIGURATION_APP_UPDATE, false);
                return;
            }
        }
        c();
    }

    public void c() {
        int b = com.mavenir.android.settings.c.q.b();
        long c = com.mavenir.android.settings.c.q.c();
        long currentTimeMillis = System.currentTimeMillis();
        if (b > 0 && c > 0) {
            long f = f();
            q.a("ActivationService", "setupConfigurationTimer(): currentTime = " + currentTimeMillis + "ms; expiryTime = " + f);
            this.h.removeMessages(100);
            if (!this.h.hasMessages(100)) {
                if (f - currentTimeMillis < 0) {
                    q.a("ActivationService", "setupConfigurationTimer(): Validity expired");
                    this.h.sendEmptyMessage(100);
                    return;
                }
                q.a("ActivationService", "setupConfigurationTimer(): timer set for " + (f - currentTimeMillis) + "ms");
                this.h.sendEmptyMessageDelayed(100, f - currentTimeMillis);
            }
        }
        d();
    }

    public void d() {
        if (FgVoIP.U().H()) {
            long currentTimeMillis = System.currentTimeMillis();
            boolean b = com.mavenir.android.settings.c.l.b();
            boolean c = com.mavenir.android.settings.c.l.c();
            boolean d = com.mavenir.android.settings.c.l.d();
            long e = com.mavenir.android.settings.c.l.e();
            if (e == com.mavenir.android.settings.b.aq) {
                q.a("ActivationService", "setupLteConfigurationTimer(): LTE capability expiry not set, returning...");
            } else if (this.f) {
                q.a("ActivationService", "setupLteConfigurationTimer(): LTE capability expiry is in the past, returning...");
            } else {
                this.h.removeMessages(101);
                this.h.removeMessages(102);
                if (this.h.hasMessages(101) || this.h.hasMessages(102)) {
                    e();
                } else if (b && c && d && currentTimeMillis > e) {
                    boolean A = l.a(this.a).A();
                    q.a("ActivationService", "setupLteConfigurationTimer(): LTE validity expired");
                    if (A) {
                        a(com.mavenir.android.applog.AppLogAdapter.b.FGAPPLOG_EVENT_GROUP_LTE_800, d.FGAPPLOG_EVENT_TYPE_SYNC_PROBLEM, AppLogAdapter.c.FGAPPLOG_EVENT_REASON_NONE, "Device Connected to LTE-800,  but LTE-800 Capability Expires parameter has expired");
                        q.a("ActivationService", "setupLteConfigurationTimer(): LTE connected, sending 202");
                        this.h.sendEmptyMessage(102);
                        return;
                    }
                    q.a("ActivationService", "setupLteConfigurationTimer(): LTE disconnected, sending 203");
                    this.h.sendEmptyMessage(101);
                } else {
                    if (b && c && d) {
                        currentTimeMillis = e - currentTimeMillis;
                        q.a("ActivationService", "setupLteConfigurationTimer(): timer set for " + currentTimeMillis + " ms");
                        this.h.sendEmptyMessageDelayed(101, currentTimeMillis);
                    }
                    e();
                }
            }
        }
    }

    public void e() {
        boolean z = true;
        if (l.a(this.a).A()) {
            boolean b = com.mavenir.android.settings.c.l.b();
            boolean c = com.mavenir.android.settings.c.l.c();
            boolean d = com.mavenir.android.settings.c.l.d();
            if (!b) {
                a(com.mavenir.android.applog.AppLogAdapter.b.FGAPPLOG_EVENT_GROUP_LTE_800, d.FGAPPLOG_EVENT_TYPE_SYNC_PROBLEM, AppLogAdapter.c.FGAPPLOG_EVENT_REASON_NONE, "Device Connected, but TAC indicates it is not an LTE-800 Capable Device");
                b = CallService.m();
                if (CallService.o() != com.fgmicrotec.mobile.android.fgmag.DataConnectionManager.a.LTE) {
                    z = false;
                }
                if (b && z) {
                    FgVoIP.U().a("com.fgmicrotec.mobile.android.voip.LogoutFromServerReq");
                }
                q.a("ActivationService", "synchronizeLteSettings(): LTE settings not in sync, deactivating LTE");
                this.h.sendEmptyMessage(105);
            } else if (d && !c) {
                a(com.mavenir.android.applog.AppLogAdapter.b.FGAPPLOG_EVENT_GROUP_LTE_800, d.FGAPPLOG_EVENT_TYPE_SYNC_PROBLEM, AppLogAdapter.c.FGAPPLOG_EVENT_REASON_NONE, "Device Connected to LTE-800, but UI setting was indicating deactivated");
                q.a("ActivationService", "synchronizeLteSettings(): LTE settings not in sync, setting User activated to true");
                com.mavenir.android.settings.c.l.b(true);
            }
        }
    }

    public long f() {
        return (((long) com.mavenir.android.settings.c.q.b()) * 1000) + com.mavenir.android.settings.c.q.c();
    }

    private void a(String str) {
        if (str != null && !str.equals("1.1.2014 11:11")) {
            Date a = t.d.a(str, "dd.MM.yyyy kk:mm");
            Date date = new Date(System.currentTimeMillis());
            long currentTimeMillis = System.currentTimeMillis();
            int w = com.mavenir.android.settings.c.q.w();
            long abs = Math.abs(currentTimeMillis - a.getTime());
            q.a("ActivationService", "checkNetworkTime(): network: " + a.toString() + "(" + a.getTime() + "), current: " + date.toString() + "(" + currentTimeMillis + "), timeDiff = " + abs + ", acceptable = " + (((w * 60) * 60) * 1000));
            if (com.mavenir.android.settings.c.k.d() > 0 && abs > ((long) (((w * 60) * 60) * 1000))) {
                FgVoIP.U().a(com.mavenir.android.fragments.f.a.CUSTOM, 0, 0, this.a.getString(k.activation_exception_time_difference));
                a(com.mavenir.android.applog.AppLogAdapter.b.FGAPPLOG_EVENT_GROUP_LTE_800, d.FGAPPLOG_EVENT_TYPE_SYNC_PROBLEM, AppLogAdapter.c.FGAPPLOG_EVENT_REASON_NONE, "Time-Date mismatch");
            }
        }
    }

    private void a(long j) {
        if (j < System.currentTimeMillis()) {
            this.f = true;
        } else {
            this.f = false;
        }
    }

    public void a(a aVar) {
        CharSequence u = com.mavenir.android.settings.c.q.u();
        CharSequence v = com.mavenir.android.settings.c.q.v();
        List arrayList = new ArrayList();
        if (!TextUtils.isEmpty(u)) {
            arrayList.add(u);
        }
        if (!TextUtils.isEmpty(v)) {
            arrayList.add(v);
        }
        if (this.d == null) {
            this.d = new com.mavenir.android.c.a(this.a, this);
        }
        this.e = aVar;
        this.d.a(arrayList);
    }

    public void g() {
        if (this.d != null) {
            this.d.a();
        } else {
            q.d("ActivationService", "closeHipriConnection(): unable to deactivate");
        }
    }

    public void a(com.mavenir.android.applog.AppLogAdapter.b bVar, d dVar, AppLogAdapter.c cVar, String str) {
        final com.mavenir.android.applog.AppLogAdapter.b bVar2 = bVar;
        final d dVar2 = dVar;
        final AppLogAdapter.c cVar2 = cVar;
        final String str2 = str;
        new Thread(new Runnable(this) {
            final /* synthetic */ b e;

            public void run() {
                com.mavenir.android.applog.a.a(this.e.a).a(bVar2, dVar2, cVar2, str2);
            }
        }).start();
    }

    public void a(int i, int i2, int i3, boolean z) {
        com.mavenir.android.applog.AppLogAdapter.b bVar = com.mavenir.android.applog.AppLogAdapter.b.FGAPPLOG_EVENT_GROUP_APP_ACTIVATED;
        d dVar = d.FGAPPLOG_EVENT_TYPE_NONE;
        AppLogAdapter.c cVar = AppLogAdapter.c.FGAPPLOG_EVENT_REASON_NONE;
        String str = "";
        boolean d = com.mavenir.android.settings.c.l.d();
        switch (i2) {
            case ActivationAdapter.REASON_NEW_CONFIG_DATA /*300*/:
            case ActivationAdapter.REASON_APP_USER_FRIENDLY_MESSAGE /*305*/:
                if (z == d || !d) {
                    if (z == d || d) {
                        if (z != d || !d) {
                            bVar = com.mavenir.android.applog.AppLogAdapter.b.FGAPPLOG_EVENT_GROUP_APP_ACTIVATED;
                            dVar = d.FGAPPLOG_EVENT_TYPE_NONE;
                            break;
                        }
                        bVar = com.mavenir.android.applog.AppLogAdapter.b.FGAPPLOG_EVENT_GROUP_LTE_800;
                        dVar = d.FGAPPLOG_EVENT_TYPE_ACTIVATED;
                        break;
                    }
                    bVar = com.mavenir.android.applog.AppLogAdapter.b.FGAPPLOG_EVENT_GROUP_LTE_800;
                    dVar = d.FGAPPLOG_EVENT_TYPE_DEACTIVATED;
                    break;
                }
                bVar = com.mavenir.android.applog.AppLogAdapter.b.FGAPPLOG_EVENT_GROUP_LTE_800;
                dVar = d.FGAPPLOG_EVENT_TYPE_ACTIVATED;
                break;
                break;
            case ActivationAdapter.REASON_NO_NEW_CONFIG_DATA /*301*/:
                break;
            case ActivationAdapter.REASON_DEVICE_BLOCKED /*302*/:
            case ActivationAdapter.REASON_DEVICE_BLOCKED_ROOTED /*303*/:
            case ActivationAdapter.REASON_APP_VERSION_BLOCKED /*304*/:
            case ActivationAdapter.REASON_USER_PROVISIONING_ERROR /*306*/:
            case ActivationAdapter.REASON_NETWORK_PROVISIONING_ERROR /*307*/:
            case ActivationAdapter.REASON_MALFORMED_PARAMS /*308*/:
            case ActivationAdapter.REASON_INVALID_URL_REQ /*309*/:
            case ActivationAdapter.REASON_UNKNOWN_CMS_ERROR /*310*/:
            case ActivationAdapter.REASON_MSISDN_HASH_MISMATCH /*314*/:
                bVar = com.mavenir.android.applog.AppLogAdapter.b.FGAPPLOG_EVENT_GROUP_LTE_800;
                if (i != ActivationAdapter.OP_LTE_PS_DEACTIVATION) {
                    dVar = d.FGAPPLOG_EVENT_TYPE_ACTIVATION_FAILED;
                    break;
                } else {
                    dVar = d.FGAPPLOG_EVENT_TYPE_DEACTIVATION_FAILED;
                    break;
                }
            default:
                q.d("ActivationService", "postConfigurationAppLogEvent(): unknown reason code: " + i2);
                break;
        }
        AppLogAdapter.c cVar2 = AppLogAdapter.c.FGAPPLOG_EVENT_REASON_NONE;
        String str2 = "Reason code: " + i2;
        q.a("ActivationService", "postConfigurationAppLogEvent(): oldLteEnabled: " + z + ", newLteEnabled: " + d + ", group: " + bVar.name() + ", type: " + dVar.name());
        if (i3 != com.mavenir.android.settings.c.k.d() || z != d) {
            a(bVar, dVar, cVar2, str2);
        }
    }

    public void a(int i, int i2, int i3) {
        d dVar;
        com.mavenir.android.applog.AppLogAdapter.b bVar = com.mavenir.android.applog.AppLogAdapter.b.FGAPPLOG_EVENT_GROUP_LTE_800;
        d dVar2 = d.FGAPPLOG_EVENT_TYPE_ACTIVATION_FAILED;
        AppLogAdapter.c cVar = AppLogAdapter.c.FGAPPLOG_EVENT_REASON_NONE;
        String str = "";
        String str2;
        switch (i2) {
            case ActivationAdapter.REASON_DEVICE_BLOCKED /*302*/:
            case ActivationAdapter.REASON_DEVICE_BLOCKED_ROOTED /*303*/:
            case ActivationAdapter.REASON_APP_VERSION_BLOCKED /*304*/:
            case ActivationAdapter.REASON_MALFORMED_PARAMS /*308*/:
            case ActivationAdapter.REASON_INVALID_URL_REQ /*309*/:
            case ActivationAdapter.REASON_UNKNOWN_CMS_ERROR /*310*/:
            case ActivationAdapter.REASON_SERVICE_BLOCKED /*313*/:
            case ActivationAdapter.REASON_MSISDN_HASH_MISMATCH /*314*/:
                if (i == ActivationAdapter.OP_LTE_PS_ACTIVATION) {
                    dVar2 = d.FGAPPLOG_EVENT_TYPE_ACTIVATION_FAILED;
                } else {
                    dVar2 = d.FGAPPLOG_EVENT_TYPE_DEACTIVATION_FAILED;
                }
                str2 = "Reason code: " + i2;
                dVar = dVar2;
                str = str2;
                break;
            case ActivationAdapter.REASON_SERVICE_ACTIVATION_OK /*311*/:
                dVar = d.FGAPPLOG_EVENT_TYPE_ACTIVATED;
                str = "Reason code: " + i2;
                break;
            case ActivationAdapter.REASON_SERVICE_DEACTIVATION_OK /*312*/:
                dVar = d.FGAPPLOG_EVENT_TYPE_DEACTIVATED;
                str = "Reason code: " + i2;
                break;
            default:
                q.d("ActivationService", "postLteActivationAppLogEvent(): unknown reason code: " + i2);
                if (i == ActivationAdapter.OP_LTE_PS_ACTIVATION) {
                    dVar2 = d.FGAPPLOG_EVENT_TYPE_ACTIVATION_FAILED;
                } else {
                    dVar2 = d.FGAPPLOG_EVENT_TYPE_DEACTIVATION_FAILED;
                }
                str2 = "Error code: " + com.mavenir.android.vtow.activation.ActivationAdapter.b.values()[i3].name() + " (" + i3 + ")";
                dVar = dVar2;
                str = str2;
                break;
        }
        a(bVar, dVar, AppLogAdapter.c.FGAPPLOG_EVENT_REASON_NONE, str);
    }

    private void c(int i, int i2, int i3, ConfigurationData configurationData, ConfigurationDataLte configurationDataLte, int i4, String str, String str2) {
        if (configurationDataLte == null) {
            q.d("ActivationService", "configurationCnf(): No LTE configuration data, something went wrong");
            return;
        }
        switch (i3) {
            case ActivationAdapter.OP_CONFIGURATION_INITIAL /*200*/:
            case ActivationAdapter.OP_CONFIGURATION_APP_UPDATE /*201*/:
            case ActivationAdapter.OP_CONFIGURATION_DAILY /*202*/:
                d(i, i2, i3, configurationData, configurationDataLte, i4, str, str2);
                break;
            case ActivationAdapter.OP_LTE_PS_ACTIVATION /*203*/:
            case ActivationAdapter.OP_LTE_PS_DEACTIVATION /*204*/:
                a(i3, configurationDataLte, i4);
                break;
            default:
                q.d("ActivationService", "configurationCnfLte: unknown op code: " + i3);
                break;
        }
        long lTEExpiresLong = configurationDataLte.getLTEExpiresLong();
        if (lTEExpiresLong > 0) {
            a(lTEExpiresLong);
        }
        c();
        a(configurationDataLte.getNetworkTime());
    }

    private void d(int i, int i2, int i3, ConfigurationData configurationData, ConfigurationDataLte configurationDataLte, int i4, String str, String str2) {
        int reasonCode = configurationDataLte.getReasonCode();
        switch (reasonCode) {
            case ActivationAdapter.REASON_NEW_CONFIG_DATA /*300*/:
            case ActivationAdapter.REASON_APP_USER_FRIENDLY_MESSAGE /*305*/:
                e(i, i2, i3, configurationData, configurationDataLte, i4, str, str2);
                break;
            case ActivationAdapter.REASON_NO_NEW_CONFIG_DATA /*301*/:
                j(i, i2, i3, configurationData, configurationDataLte, i4, str, str2);
                break;
            case ActivationAdapter.REASON_DEVICE_BLOCKED /*302*/:
            case ActivationAdapter.REASON_DEVICE_BLOCKED_ROOTED /*303*/:
            case ActivationAdapter.REASON_APP_VERSION_BLOCKED /*304*/:
                f(i, i2, i3, configurationData, configurationDataLte, i4, str, str2);
                break;
            case ActivationAdapter.REASON_USER_PROVISIONING_ERROR /*306*/:
                g(i, i2, i3, configurationData, configurationDataLte, i4, str, str2);
                break;
            case ActivationAdapter.REASON_NETWORK_PROVISIONING_ERROR /*307*/:
                h(i, i2, i3, configurationData, configurationDataLte, i4, str, str2);
                break;
            case ActivationAdapter.REASON_MALFORMED_PARAMS /*308*/:
            case ActivationAdapter.REASON_INVALID_URL_REQ /*309*/:
            case ActivationAdapter.REASON_UNKNOWN_CMS_ERROR /*310*/:
            case ActivationAdapter.REASON_MSISDN_HASH_MISMATCH /*314*/:
                i(i, i2, i3, configurationData, configurationDataLte, i4, str, str2);
                break;
            default:
                q.d("ActivationService", "configurationCnfInitial(): unknown reason code: " + reasonCode);
                break;
        }
        com.mavenir.android.settings.c.q.b(System.currentTimeMillis());
        com.mavenir.android.settings.c.k.c(l.a(this.a).c());
        this.h.post(this.i);
        if (i3 == ActivationAdapter.OP_CONFIGURATION_DAILY || i3 == ActivationAdapter.OP_CONFIGURATION_APP_UPDATE) {
            FgVoIP.U().a("com.mavenir.android.ActionConectivityChange");
        }
        Boolean isLTEDeviceCapable = configurationDataLte.isLTEDeviceCapable();
        Boolean isLTEUserActivated = configurationDataLte.isLTEUserActivated();
        Boolean valueOf = Boolean.valueOf(configurationDataLte.isLTENetworkEnabled());
        if (isLTEDeviceCapable != null && isLTEUserActivated != null && valueOf != null && isLTEDeviceCapable.booleanValue()) {
            if (!isLTEUserActivated.booleanValue() && valueOf.booleanValue()) {
                q.c("ActivationService", "configurationCnfInitial(): deactivating (LTE user activated: " + isLTEUserActivated + ", LTE network enabled: " + valueOf + ")");
                this.h.sendEmptyMessage(105);
            } else if (isLTEUserActivated.booleanValue() && !valueOf.booleanValue() && reasonCode != ActivationAdapter.REASON_NETWORK_PROVISIONING_ERROR) {
                q.c("ActivationService", "configurationCnfInitial(): reactivating (LTE user activated: " + isLTEUserActivated + ", LTE network enabled: " + valueOf + ")");
                this.h.sendEmptyMessage(104);
            }
        }
    }

    private void a(int i, ConfigurationDataLte configurationDataLte, int i2) {
        int reasonCode = configurationDataLte.getReasonCode();
        switch (reasonCode) {
            case ActivationAdapter.REASON_DEVICE_BLOCKED /*302*/:
            case ActivationAdapter.REASON_DEVICE_BLOCKED_ROOTED /*303*/:
            case ActivationAdapter.REASON_APP_VERSION_BLOCKED /*304*/:
            case ActivationAdapter.REASON_USER_PROVISIONING_ERROR /*306*/:
            case ActivationAdapter.REASON_NETWORK_PROVISIONING_ERROR /*307*/:
            case ActivationAdapter.REASON_MALFORMED_PARAMS /*308*/:
            case ActivationAdapter.REASON_INVALID_URL_REQ /*309*/:
            case ActivationAdapter.REASON_UNKNOWN_CMS_ERROR /*310*/:
            case ActivationAdapter.REASON_SERVICE_BLOCKED /*313*/:
            case ActivationAdapter.REASON_MSISDN_HASH_MISMATCH /*314*/:
                d(i, configurationDataLte, i2);
                break;
            case ActivationAdapter.REASON_SERVICE_ACTIVATION_OK /*311*/:
                b(i, configurationDataLte, i2);
                return;
            case ActivationAdapter.REASON_SERVICE_DEACTIVATION_OK /*312*/:
                c(i, configurationDataLte, i2);
                return;
            default:
                q.d("ActivationService", "configurationCnfServiceActivation(): unknown reason code: " + reasonCode);
                e(i, configurationDataLte, i2);
                break;
        }
        FgVoIP.U().a("com.mavenir.android.ActionConectivityChange");
    }

    private String a(String str, String str2) {
        StringBuilder stringBuilder = new StringBuilder();
        if (!TextUtils.isEmpty(str)) {
            stringBuilder.append(str);
            if (!TextUtils.isEmpty(str2)) {
                stringBuilder.append("<br/><br/>");
                stringBuilder.append("<a href='").append(str2).append("'>");
                stringBuilder.append(str2).append("</a>");
            }
        }
        return stringBuilder.toString();
    }

    private void e(int i, int i2, int i3, ConfigurationData configurationData, ConfigurationDataLte configurationDataLte, int i4, String str, String str2) {
        String string;
        int d = com.mavenir.android.settings.c.k.d();
        boolean d2 = com.mavenir.android.settings.c.l.d();
        com.mavenir.android.settings.c.k.a(i);
        com.mavenir.android.settings.c.q.a(i2);
        a(configurationData);
        a(configurationDataLte);
        if (TextUtils.isEmpty(str)) {
            string = this.a.getString(k.activation_exception_lte_success);
        } else {
            string = a(str, str2);
        }
        if (i3 != ActivationAdapter.OP_CONFIGURATION_INITIAL) {
            FgVoIP.U().a(com.mavenir.android.fragments.f.a.H3G_ACTIVATION, i4, 0, string);
        }
        a(i3, configurationDataLte.getReasonCode(), d, d2);
        Intent intent = new Intent();
        intent.setAction("com.mavenir.android.activation.action_configuration_cnf");
        intent.putExtra("com.mavenir.android.activation.extra_op_code", i3);
        intent.putExtra("com.mavenir.android.activation.extra_error_code", i4);
        intent.putExtra("com.mavenir.android.activation.extra_reason_code", configurationDataLte.getReasonCode());
        intent.putExtra("com.mavenir.android.activation.error_message", string);
        o.a(this.a).a(intent);
    }

    private void f(int i, int i2, int i3, ConfigurationData configurationData, ConfigurationDataLte configurationDataLte, int i4, String str, String str2) {
        String string;
        com.mavenir.android.settings.c.k.a(i);
        com.mavenir.android.settings.c.q.a(i2);
        a(configurationDataLte);
        if (TextUtils.isEmpty(str)) {
            string = this.a.getString(k.activation_lte_no_message);
        } else {
            string = a(str, str2);
        }
        if (i3 != ActivationAdapter.OP_CONFIGURATION_INITIAL) {
            FgVoIP.U().a(com.mavenir.android.fragments.f.a.H3G_ACTIVATION, i4, 0, string);
        }
        a(i3, configurationDataLte.getReasonCode(), -4, false);
        Intent intent = new Intent();
        intent.setAction("com.mavenir.android.activation.action_configuration_cnf");
        intent.putExtra("com.mavenir.android.activation.extra_op_code", i3);
        intent.putExtra("com.mavenir.android.activation.extra_error_code", i4);
        intent.putExtra("com.mavenir.android.activation.extra_reason_code", configurationDataLte.getReasonCode());
        intent.putExtra("com.mavenir.android.activation.error_message", string);
        o.a(this.a).a(intent);
    }

    private void g(int i, int i2, int i3, ConfigurationData configurationData, ConfigurationDataLte configurationDataLte, int i4, String str, String str2) {
        String string;
        int d = com.mavenir.android.settings.c.k.d();
        boolean d2 = com.mavenir.android.settings.c.l.d();
        com.mavenir.android.settings.c.k.a(i);
        com.mavenir.android.settings.c.q.a(i2);
        a(configurationData);
        a(configurationDataLte);
        if (TextUtils.isEmpty(str)) {
            string = this.a.getString(k.activation_exception_user_error);
        } else {
            string = a(str, str2);
        }
        if (i3 != ActivationAdapter.OP_CONFIGURATION_INITIAL) {
            FgVoIP.U().a(com.mavenir.android.fragments.f.a.H3G_ACTIVATION, i4, 0, string);
        }
        a(i3, configurationDataLte.getReasonCode(), d, d2);
        Intent intent = new Intent();
        intent.setAction("com.mavenir.android.activation.action_configuration_cnf");
        intent.putExtra("com.mavenir.android.activation.extra_op_code", i3);
        intent.putExtra("com.mavenir.android.activation.extra_error_code", i4);
        intent.putExtra("com.mavenir.android.activation.extra_reason_code", configurationDataLte.getReasonCode());
        intent.putExtra("com.mavenir.android.activation.error_message", string);
        o.a(this.a).a(intent);
    }

    private void h(int i, int i2, int i3, ConfigurationData configurationData, ConfigurationDataLte configurationDataLte, int i4, String str, String str2) {
        com.mavenir.android.settings.c.k.a(i);
        com.mavenir.android.settings.c.q.a(i2);
        a(configurationData);
        a(configurationDataLte);
        String str3 = null;
        if (!TextUtils.isEmpty(str)) {
            str3 = a(str, str2);
            if (i3 != ActivationAdapter.OP_CONFIGURATION_INITIAL) {
                FgVoIP.U().a(com.mavenir.android.fragments.f.a.H3G_ACTIVATION, i4, 0, str3);
            }
        }
        a(i3, configurationDataLte.getReasonCode(), -4, false);
        if (!(i3 == ActivationAdapter.OP_LTE_PS_ACTIVATION || i3 == ActivationAdapter.OP_LTE_PS_DEACTIVATION)) {
            q.a("ActivationService", "configurationNetworkError(): setting 5 minutes retry timer ");
            this.h.removeMessages(300000);
            this.h.sendEmptyMessageDelayed(104, 300000);
        }
        Intent intent = new Intent();
        intent.setAction("com.mavenir.android.activation.action_configuration_cnf");
        intent.putExtra("com.mavenir.android.activation.extra_op_code", i3);
        intent.putExtra("com.mavenir.android.activation.extra_error_code", i4);
        intent.putExtra("com.mavenir.android.activation.extra_reason_code", configurationDataLte.getReasonCode());
        intent.putExtra("com.mavenir.android.activation.error_message", str3);
        o.a(this.a).a(intent);
    }

    private void i(int i, int i2, int i3, ConfigurationData configurationData, ConfigurationDataLte configurationDataLte, int i4, String str, String str2) {
        com.mavenir.android.settings.c.k.a(i);
        com.mavenir.android.settings.c.q.a(i2);
        String str3 = null;
        if (!TextUtils.isEmpty(str)) {
            str3 = a(str, str2);
            if (i3 != ActivationAdapter.OP_CONFIGURATION_INITIAL) {
                FgVoIP.U().a(com.mavenir.android.fragments.f.a.H3G_ACTIVATION, i4, 0, str3);
            }
        }
        a(i3, configurationDataLte.getReasonCode(), -4, false);
        Intent intent = new Intent();
        intent.setAction("com.mavenir.android.activation.action_configuration_cnf");
        intent.putExtra("com.mavenir.android.activation.extra_op_code", i3);
        intent.putExtra("com.mavenir.android.activation.extra_error_code", i4);
        intent.putExtra("com.mavenir.android.activation.extra_reason_code", configurationDataLte.getReasonCode());
        intent.putExtra("com.mavenir.android.activation.error_message", str3);
        o.a(this.a).a(intent);
    }

    private void j(int i, int i2, int i3, ConfigurationData configurationData, ConfigurationDataLte configurationDataLte, int i4, String str, String str2) {
        com.mavenir.android.settings.c.k.a(i);
        com.mavenir.android.settings.c.q.a(i2);
        boolean d = com.mavenir.android.settings.c.l.d();
        a(configurationDataLte);
        boolean d2 = com.mavenir.android.settings.c.l.d();
        String str3 = null;
        if (!TextUtils.isEmpty(str)) {
            str3 = a(str, str2);
        } else if (!d && d2) {
            str3 = this.a.getString(k.activation_exception_lte_success);
        }
        if (!(i3 == ActivationAdapter.OP_CONFIGURATION_INITIAL || TextUtils.isEmpty(str3))) {
            FgVoIP.U().a(com.mavenir.android.fragments.f.a.H3G_ACTIVATION, i4, 0, str3);
        }
        if (i3 == ActivationAdapter.OP_CONFIGURATION_DAILY && d && !d2) {
            this.h.sendEmptyMessageDelayed(103, 2000);
        }
    }

    private void b(int i, ConfigurationDataLte configurationDataLte, int i2) {
        a(configurationDataLte);
        int reasonCode = configurationDataLte.getReasonCode();
        String string = this.a.getString(k.activation_exception_lte_success);
        c();
        a(i, reasonCode, i2);
        Intent intent = new Intent();
        intent.setAction("com.mavenir.android.activation.action_lte_activation_cnf");
        intent.putExtra("com.mavenir.android.activation.extra_op_code", i);
        intent.putExtra("com.mavenir.android.activation.extra_error_code", i2);
        intent.putExtra("com.mavenir.android.activation.extra_reason_code", reasonCode);
        intent.putExtra("com.mavenir.android.activation.error_message", string);
        o.a(this.a).a(intent);
    }

    private void c(int i, ConfigurationDataLte configurationDataLte, int i2) {
        a(configurationDataLte);
        int reasonCode = configurationDataLte.getReasonCode();
        a(i, reasonCode, i2);
        Intent intent = new Intent();
        intent.setAction("com.mavenir.android.activation.action_lte_deactivation_cnf");
        intent.putExtra("com.mavenir.android.activation.extra_op_code", i);
        intent.putExtra("com.mavenir.android.activation.extra_error_code", i2);
        intent.putExtra("com.mavenir.android.activation.extra_reason_code", reasonCode);
        intent.putExtra("com.mavenir.android.activation.error_message", null);
        o.a(this.a).a(intent);
    }

    private void d(int i, ConfigurationDataLte configurationDataLte, int i2) {
        a(configurationDataLte);
        Intent intent = new Intent();
        int reasonCode = configurationDataLte != null ? configurationDataLte.getReasonCode() : -1;
        String str = null;
        if (i == ActivationAdapter.OP_LTE_PS_ACTIVATION) {
            intent.setAction("com.mavenir.android.activation.action_lte_activation_cnf");
            if (reasonCode == ActivationAdapter.REASON_SERVICE_BLOCKED) {
                str = this.a.getString(k.activation_exception_lte_b);
            } else {
                str = this.a.getString(k.activation_exception_lte_a);
            }
        } else if (i == ActivationAdapter.OP_LTE_PS_DEACTIVATION) {
            str = this.a.getString(k.activation_exception_lte_deactivation);
            intent.setAction("com.mavenir.android.activation.action_lte_deactivation_cnf");
        }
        a(i, reasonCode, i2);
        intent.putExtra("com.mavenir.android.activation.extra_op_code", i);
        intent.putExtra("com.mavenir.android.activation.extra_error_code", i2);
        intent.putExtra("com.mavenir.android.activation.extra_reason_code", reasonCode);
        intent.putExtra("com.mavenir.android.activation.error_message", str);
        o.a(this.a).a(intent);
    }

    private void a(int i, int i2) {
        Intent intent = new Intent();
        String str = null;
        if (i == ActivationAdapter.OP_LTE_PS_ACTIVATION) {
            intent.setAction("com.mavenir.android.activation.action_lte_activation_cnf");
            str = this.a.getString(k.activation_exception_lte_a);
        } else if (i == ActivationAdapter.OP_LTE_PS_DEACTIVATION) {
            intent.setAction("com.mavenir.android.activation.action_lte_deactivation_cnf");
            str = this.a.getString(k.activation_exception_lte_deactivation);
        }
        a(i, -1, i2);
        intent.putExtra("com.mavenir.android.activation.extra_op_code", i);
        intent.putExtra("com.mavenir.android.activation.extra_error_code", i2);
        intent.putExtra("com.mavenir.android.activation.error_message", str);
        o.a(this.a).a(intent);
    }

    private void e(int i, ConfigurationDataLte configurationDataLte, int i2) {
        int reasonCode = configurationDataLte.getReasonCode();
        Intent intent = new Intent();
        String string = this.a.getString(k.activation_exception_lte_unkown);
        if (i == ActivationAdapter.OP_LTE_PS_ACTIVATION) {
            intent.setAction("com.mavenir.android.activation.action_lte_activation_cnf");
        } else if (i == ActivationAdapter.OP_LTE_PS_DEACTIVATION) {
            intent.setAction("com.mavenir.android.activation.action_lte_deactivation_cnf");
        }
        intent.putExtra("com.mavenir.android.activation.extra_op_code", i);
        intent.putExtra("com.mavenir.android.activation.extra_error_code", i2);
        intent.putExtra("com.mavenir.android.activation.extra_reason_code", reasonCode);
        intent.putExtra("com.mavenir.android.activation.error_message", string);
        o.a(this.a).a(intent);
    }
}
