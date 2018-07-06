package com.mavenir.android.settings;

import android.provider.Settings.System;
import com.fgmicrotec.mobile.android.fgvoip.FgVoIP;
import com.fgmicrotec.mobile.android.fgvoip.f.k;
import com.mavenir.android.common.t.d;
import com.mavenir.android.settings.c.e;
import java.io.File;

public class b {
    public static final boolean A = FgVoIP.U().c(k.DEF_MSG_REQUEST_DISPLAY_REPORTS);
    public static final boolean B = (FgVoIP.U().c(k.DEF_MSG_ALLOW_DISPLAY_REPORTS));
    public static final int C = FgVoIP.U().b(k.DEF_SMS_VALIDITY);
    public static final int D = FgVoIP.U().b(k.DEF_SMS_ENC_NAT_NUMBERS);
    public static final int E = FgVoIP.U().b(k.DEF_SMS_ENC_INTL_NUMBERS);
    public static final String[] F = FgVoIP.U().d(com.fgmicrotec.mobile.android.fgvoip.f.b.DEF_EMERGENCY_NUMBERS);
    public static final String G = FgVoIP.U().a(k.DEF_PROVISIONING_FQDN_1);
    public static final String H = FgVoIP.U().a(k.DEF_PROVISIONING_FQDN_2);
    public static final String I = FgVoIP.U().a(k.DEF_USER_DEACTIVATION_MESSAGE);
    public static final String J = FgVoIP.U().a(k.DEF_PUBLIC_IP_SERVICE_URL);
    public static final boolean K = FgVoIP.U().c(k.DEF_CAP_PRESENCE_DISCOVERY);
    public static final boolean L = FgVoIP.U().c(k.DEF_CAP_SIP_MESSAGING);
    public static final boolean M = FgVoIP.U().c(k.DEF_CAP_IM_SESSION);
    public static final boolean N = FgVoIP.U().c(k.DEF_CAP_FILE_TRANSFER);
    public static final boolean O = FgVoIP.U().c(k.DEF_CAP_IMAGE_SHARE);
    public static final boolean P = FgVoIP.U().c(k.DEF_CAP_VIDEO_SHARE);
    public static final boolean Q = FgVoIP.U().c(k.DEF_CAP_IP_AUDIO_CALL);
    public static final boolean R = FgVoIP.U().c(k.DEF_CAP_IP_VIDEO_CALL);
    public static final boolean S = FgVoIP.U().c(k.DEF_CAP_SOCIAL_PRESENCE);
    public static final boolean T = FgVoIP.U().c(k.DEF_CAP_GEOLOCATION_PUSH);
    public static final boolean U = FgVoIP.U().c(k.DEF_CAP_GEOLOCATION_PULL);
    public static final boolean V = FgVoIP.U().c(k.DEF_CAP_GEOLOCATION_PULL_FT);
    public static final boolean W = FgVoIP.U().c(k.DEF_CAP_CPM_CHAT);
    public static final boolean X = FgVoIP.U().c(k.DEF_CAP_CPM_FT);
    public static final boolean Y = FgVoIP.U().c(k.DEF_CAP_CPM_STANDALONE_MSG);
    public static final String Z = FgVoIP.U().a(k.DEF_GROUP_CHAT_FACTORY_URI);
    public static final String a = FgVoIP.U().a(k.DEF_SIP_DOMAIN_PUBLIC);
    public static final String aa = FgVoIP.U().a(k.DEF_AUTH_GROUP);
    public static final String ab = FgVoIP.U().a(k.DEF_OAUTH_CLIENT_ID);
    public static final String ac = FgVoIP.U().a(k.DEF_OAUTH_CLIENT_SECRET);
    public static final String ad = FgVoIP.U().a(k.DEF_OAUTH_SCOPE);
    public static final String ae = FgVoIP.U().a(k.DEF_OAUTH_REDIRECT_URI);
    public static final String af = FgVoIP.U().a(k.DEF_OAUTH_AUTH_ENDPOINT);
    public static final String ag = FgVoIP.U().a(k.DEF_OAUTH_TOKEN_ENDPOINT);
    public static final String ah = FgVoIP.U().a(k.DEF_OAUTH_REVOKE_ENDPOINT);
    public static final String ai = FgVoIP.U().a(k.DEF_OAUTH_DEVICE_AGENT);
    public static final String aj = FgVoIP.U().a(k.DEF_ES_SERVER_URL);
    public static final String ak = FgVoIP.U().a(k.DEF_WSG_SERVER_URL);
    public static final String al = FgVoIP.U().a(k.DEF_IAM_SERVER_URL);
    public static final String am = FgVoIP.U().a(k.DEF_VPN_GATEWAY);
    public static final String an = FgVoIP.U().a(k.DEF_VPN_ID);
    public static final String ao = FgVoIP.U().a(k.DEF_VPN_ROUTING_SUBNET);
    public static final int ap = FgVoIP.U().b(k.DEF_VPN_SIP_SOC_FAMILY);
    public static final long aq = d.a("01/01/2014 11:11", "dd/MM/yyyy kk:mm").getTime();
    public static final int ar = FgVoIP.U().b(k.DEF_LTE_SIP_PORT);
    public static final String as = FgVoIP.U().a(k.DEF_IMAP_LOGIN_PASSWORD);
    public static final String at = FgVoIP.U().a(k.DEF_IMAP_MSTORE_IP);
    public static final int au = FgVoIP.U().b(k.DEF_IMAP_MSTORE_PORT);
    public static final int av = FgVoIP.U().b(k.DEF_IMAP_TLS_ENABLED);
    public static final String aw = (FgVoIP.U().getApplicationContext().getFilesDir() + File.separator + "voicemails");
    public static final int ax;
    public static final String b = FgVoIP.U().a(k.DEF_SIP_DOMAIN_PRIVATE);
    public static final String c = FgVoIP.U().a(k.DEF_SIP_PROXY);
    public static final int d = FgVoIP.U().b(k.DEF_SIP_PORT);
    public static final int e = (FgVoIP.U().a(k.DEF_SIP_URI_FORMAT).equalsIgnoreCase("tel") ? 0 : 1);
    public static final int f;
    public static final int g = FgVoIP.U().b(k.DEF_SIP_SESSION_EXP_TIME);
    public static final int h = FgVoIP.U().b(k.DEF_SIP_MIN_SESSION_EXP_TIME);
    public static final int i = FgVoIP.U().b(k.DEF_SIP_SESSION_REFRESHER);
    public static final int j = FgVoIP.U().b(k.DEF_SIP_SOC_FAMILY);
    public static final boolean k = (FgVoIP.U().c(k.DEF_RTP_USE_RTCP));
    public static final boolean l;
    public static final String m = FgVoIP.U().a(k.DEF_CALL_CONFERENCE_FACTORY_URI);
    public static final String n = FgVoIP.U().a(k.DEF_CALL_UT_SERVER_ADDRESS);
    public static final int o = FgVoIP.U().b(k.DEF_CALL_UT_SERVER_PORT);
    public static final boolean p = FgVoIP.U().c(k.DEF_CALL_ENABLE_USSD);
    public static final int q;
    public static final int r = e.a(e.f(), FgVoIP.U().a(k.DEF_MEDIA_CODEC_TYPE)).a();
    public static final int s = FgVoIP.U().b(k.DEF_MEDIA_CODEC_MODE);
    public static final int t = FgVoIP.U().b(k.DEF_MEDIA_DTMF_SIGNALIZATION);
    public static final int u = FgVoIP.U().b(k.DEF_MEDIA_ALERT_VOLUME);
    public static final String v = FgVoIP.U().a(k.DEF_SMS_SERVICE_CENTER);
    public static final int w = (FgVoIP.U().a(k.DEF_SMS_PREFERRED_PROTOCOL).equalsIgnoreCase("LEGACY_SMS") ? 1 : 0);
    public static final boolean x = FgVoIP.U().c(k.DEF_SMS_REQUEST_DELIVERY_REPORTS);
    public static final boolean y;
    public static final String z = System.DEFAULT_NOTIFICATION_URI.toString();

    static {
        boolean z;
        int i = 2;
        int i2 = 0;
        int i3 = FgVoIP.U().a(k.DEF_SIP_TRANSPORT).equalsIgnoreCase("tls") ? 2 : FgVoIP.U().a(k.DEF_SIP_TRANSPORT).equalsIgnoreCase("udp") ? 1 : 0;
        f = i3;
        if (FgVoIP.U().c(k.DEF_SRTP_MODE)) {
            z = true;
        } else {
            z = false;
        }
        l = z;
        if (FgVoIP.U().a(k.DEF_CONNECTION_PREFERENCE).equalsIgnoreCase("wifi_only")) {
            i = 0;
        } else if (!FgVoIP.U().a(k.DEF_CONNECTION_PREFERENCE).equalsIgnoreCase("cs_network_only")) {
            i = 1;
        }
        q = i;
        if (FgVoIP.U().c(k.DEF_AUTOMATIC_MMS_DOWNLOAD)) {
            z = true;
        } else {
            z = false;
        }
        y = z;
        if (!FgVoIP.U().a(k.DEF_VVM_DOWNLOAD_MODE).equalsIgnoreCase("Wi-fi and cellular data network")) {
            i2 = 1;
        }
        ax = i2;
    }
}
