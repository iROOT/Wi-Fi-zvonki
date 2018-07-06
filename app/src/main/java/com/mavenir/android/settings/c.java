package com.mavenir.android.settings;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;
import android.provider.Settings.Secure;
import android.text.TextUtils;
import android.util.Log;
import com.fgmicrotec.mobile.android.fgvoip.FgVoIP;
import com.mavenir.android.security.CryptoAES;
import com.mavenir.android.vtow.activation.ActivationAdapter;
import java.util.ArrayList;
import java.util.List;

public class c {

    public static class a {
        public static ContentValues a() {
            ContentValues contentValues = new ContentValues();
            contentValues.put("acid_string", g.a(true, ""));
            contentValues.put("atg_state", g.a(true, false));
            return contentValues;
        }

        public static ContentValues a(long j) {
            Exception e;
            Throwable th;
            ContentResolver contentResolver = FgVoIP.U().getContentResolver();
            ContentValues contentValues = new ContentValues();
            Cursor query;
            try {
                query = contentResolver.query(com.mavenir.android.settings.a.a.a, null, "_id=?", new String[]{String.valueOf(j)}, null);
                if (query != null) {
                    try {
                        if (query.moveToFirst()) {
                            contentValues.put("acid_string", query.getString(query.getColumnIndex("acid_string")));
                            contentValues.put("atg_state", query.getString(query.getColumnIndex("atg_state")));
                        }
                    } catch (Exception e2) {
                        e = e2;
                        try {
                            com.mavenir.android.common.q.c("ClientSettingsInterface", "ATG: getValues(): " + e.getLocalizedMessage(), e.getCause());
                            if (!(query == null || query.isClosed())) {
                                query.close();
                            }
                            return contentValues;
                        } catch (Throwable th2) {
                            th = th2;
                            query.close();
                            throw th;
                        }
                    }
                }
                if (!(query == null || query.isClosed())) {
                    query.close();
                }
            } catch (Exception e3) {
                e = e3;
                query = null;
                com.mavenir.android.common.q.c("ClientSettingsInterface", "ATG: getValues(): " + e.getLocalizedMessage(), e.getCause());
                query.close();
                return contentValues;
            } catch (Throwable th3) {
                th = th3;
                query = null;
                if (!(query == null || query.isClosed())) {
                    query.close();
                }
                throw th;
            }
            return contentValues;
        }

        public static String b() {
            return g.c(com.mavenir.android.settings.a.a.a, "acid_string", "", "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static boolean c() {
            return g.a("atg_state", g.c(com.mavenir.android.settings.a.a.a, "atg_state", String.valueOf(false), "_id=?", new String[]{String.valueOf(k.c())}), Boolean.valueOf(false)).booleanValue();
        }
    }

    public static class aa {
        public static ContentValues a() {
            ContentValues contentValues = new ContentValues();
            contentValues.put("vvm_client_id", g.a(true, ""));
            contentValues.put("vvm_client_passwd", g.a(true, b.as));
            contentValues.put("vvm_server_addr", g.a(true, b.at));
            contentValues.put("vvm_server_port", g.a(true, b.au));
            contentValues.put("vvm_tls_enabled", g.a(true, b.av));
            contentValues.put("vvm_pin_num", g.a(true, 1234));
            contentValues.put("vvm_folder_path", g.a(true, b.aw));
            contentValues.put("vvm_sit_encode_enabled", g.a(true, 0));
            return contentValues;
        }

        public static String b() {
            return g.c(com.mavenir.android.settings.a.v.a, "vvm_client_id", "", "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static int a(String str) {
            return g.d(com.mavenir.android.settings.a.v.a, "vvm_client_id", str, "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static String c() {
            return g.c(com.mavenir.android.settings.a.v.a, "vvm_client_passwd", b.as, "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static int b(String str) {
            return g.d(com.mavenir.android.settings.a.v.a, "vvm_client_passwd", str, "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static String d() {
            return g.c(com.mavenir.android.settings.a.v.a, "vvm_server_addr", b.at, "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static int c(String str) {
            return g.d(com.mavenir.android.settings.a.v.a, "vvm_server_addr", str, "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static int e() {
            return Integer.parseInt(g.c(com.mavenir.android.settings.a.v.a, "vvm_server_port", String.valueOf(b.au), "_id=?", new String[]{String.valueOf(k.c())}));
        }

        public static int d(String str) {
            return g.d(com.mavenir.android.settings.a.v.a, "vvm_server_port", str, "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static int f() {
            return g.a("vvm_tls_enabled", g.c(com.mavenir.android.settings.a.v.a, "vvm_tls_enabled", String.valueOf(b.av), "_id=?", new String[]{String.valueOf(k.c())}), b.av);
        }

        public static int e(String str) {
            return g.d(com.mavenir.android.settings.a.v.a, "vvm_tls_enabled", str, "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static String g() {
            return g.c(com.mavenir.android.settings.a.v.a, "vvm_folder_path", b.aw, "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static int f(String str) {
            return g.d(com.mavenir.android.settings.a.v.a, "vvm_folder_path", str, "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static int h() {
            return g.a("vvm_sit_encode_enabled", g.c(com.mavenir.android.settings.a.v.a, "vvm_sit_encode_enabled", String.valueOf(0), "_id=?", new String[]{String.valueOf(k.c())}), 0);
        }

        public static int g(String str) {
            return g.d(com.mavenir.android.settings.a.v.a, "vvm_sit_encode_enabled", str, "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static int i() {
            return g.a("vvm_download_mode", g.c(com.mavenir.android.settings.a.v.a, "vvm_download_mode", String.valueOf(b.ax), "_id=?", new String[]{String.valueOf(k.c())}), b.ax);
        }

        public static int a(int i) {
            return g.d(com.mavenir.android.settings.a.v.a, "vvm_download_mode", String.valueOf(i), "_id=?", new String[]{String.valueOf(k.c())});
        }
    }

    public static class b {
        public static ContentValues a() {
            ContentValues contentValues = new ContentValues();
            contentValues.put("auth_group", g.a(true, b.aa));
            contentValues.put("oauth_client_id", g.a(true, b.ab));
            contentValues.put("oauth_client_secret", g.a(true, b.ac));
            contentValues.put("oauth_scope", g.a(true, b.ad));
            contentValues.put("oauth_redirect_uri", g.a(true, b.ae));
            contentValues.put("oauth_auth_endpoint", g.a(true, b.af));
            contentValues.put("oauth_token_endpoint", g.a(true, b.ag));
            contentValues.put("oauth_revoke_endpoint", g.a(true, b.ah));
            contentValues.put("oauth_device_agent", g.a(true, b.ai));
            contentValues.put("es_server_url", g.a(true, b.aj));
            contentValues.put("wsg_server_url", g.a(true, b.ak));
            contentValues.put("iam_server_url", g.a(true, b.al));
            contentValues.put("key_private", g.a(true, ""));
            contentValues.put("key_public", g.a(true, ""));
            contentValues.put("cert", g.a(true, ""));
            contentValues.put("epdg_address", g.a(true, ""));
            contentValues.put("eap_prefix", g.a(true, ""));
            contentValues.put("vimsi", g.a(true, ""));
            contentValues.put("realm", g.a(true, ""));
            contentValues.put("device_id", g.a(true, ""));
            contentValues.put("vpn_force", g.a(true, false));
            contentValues.put("vpn_gateway", g.a(true, b.am));
            contentValues.put("vpn_id", g.a(true, b.an));
            contentValues.put("vpn_password", g.a(true, ""));
            contentValues.put("vpn_type", g.a(true, 2));
            contentValues.put("vpn_user_cert_alias", g.a(true, ""));
            contentValues.put("vpn_routing_subnet", g.a(true, b.ao));
            contentValues.put("vpn_sip_soc_family", g.a(true, b.ap));
            return contentValues;
        }

        public static String b() {
            return g.c(com.mavenir.android.settings.a.b.a, "oauth_client_id", b.ab, "_id=?", new String[]{"1"});
        }

        public static int a(String str) {
            return g.d(com.mavenir.android.settings.a.b.a, "oauth_client_id", str, "_id=?", new String[]{"1"});
        }

        public static String c() {
            return g.c(com.mavenir.android.settings.a.b.a, "oauth_client_secret", b.ac, "_id=?", new String[]{"1"});
        }

        public static int b(String str) {
            return g.d(com.mavenir.android.settings.a.b.a, "oauth_client_secret", str, "_id=?", new String[]{"1"});
        }

        public static String d() {
            return g.c(com.mavenir.android.settings.a.b.a, "oauth_scope", b.ad, "_id=?", new String[]{"1"});
        }

        public static int c(String str) {
            return g.d(com.mavenir.android.settings.a.b.a, "oauth_scope", str, "_id=?", new String[]{"1"});
        }

        public static String e() {
            return g.c(com.mavenir.android.settings.a.b.a, "oauth_redirect_uri", b.ae, "_id=?", new String[]{"1"});
        }

        public static int d(String str) {
            return g.d(com.mavenir.android.settings.a.b.a, "oauth_redirect_uri", str, "_id=?", new String[]{"1"});
        }

        public static String f() {
            return g.c(com.mavenir.android.settings.a.b.a, "oauth_auth_endpoint", b.af, "_id=?", new String[]{"1"});
        }

        public static int e(String str) {
            return g.d(com.mavenir.android.settings.a.b.a, "oauth_auth_endpoint", str, "_id=?", new String[]{"1"});
        }

        public static String g() {
            return g.c(com.mavenir.android.settings.a.b.a, "oauth_token_endpoint", b.ag, "_id=?", new String[]{"1"});
        }

        public static int f(String str) {
            return g.d(com.mavenir.android.settings.a.b.a, "oauth_token_endpoint", str, "_id=?", new String[]{"1"});
        }

        public static String h() {
            return g.c(com.mavenir.android.settings.a.b.a, "oauth_revoke_endpoint", b.ah, "_id=?", new String[]{"1"});
        }

        public static int g(String str) {
            return g.d(com.mavenir.android.settings.a.b.a, "oauth_revoke_endpoint", str, "_id=?", new String[]{"1"});
        }

        public static String i() {
            return g.c(com.mavenir.android.settings.a.b.a, "oauth_device_agent", b.ai, "_id=?", new String[]{"1"});
        }

        public static int h(String str) {
            return g.d(com.mavenir.android.settings.a.b.a, "oauth_device_agent", str, "_id=?", new String[]{"1"});
        }

        public static String j() {
            return g.c(com.mavenir.android.settings.a.b.a, "es_server_url", b.aj, "_id=?", new String[]{"1"});
        }

        public static int i(String str) {
            return g.d(com.mavenir.android.settings.a.b.a, "es_server_url", str, "_id=?", new String[]{"1"});
        }

        public static String k() {
            return g.c(com.mavenir.android.settings.a.b.a, "wsg_server_url", b.ak, "_id=?", new String[]{"1"});
        }

        public static int j(String str) {
            return g.d(com.mavenir.android.settings.a.b.a, "wsg_server_url", str, "_id=?", new String[]{"1"});
        }

        public static String l() {
            return g.c(com.mavenir.android.settings.a.b.a, "iam_server_url", b.al, "_id=?", new String[]{"1"});
        }

        public static int k(String str) {
            return g.d(com.mavenir.android.settings.a.b.a, "iam_server_url", str, "_id=?", new String[]{"1"});
        }
    }

    public static class c {
        public static ContentValues a() {
            ContentValues contentValues = new ContentValues();
            contentValues.put("call_uri_template", g.a(true, "sip:@"));
            contentValues.put("call_pickup_template", g.a(true, "pickup:@"));
            contentValues.put("call_conference_factory_uri", g.a(true, b.m));
            contentValues.put("call_ut_server_address", g.a(true, b.n));
            contentValues.put("call_ut_server_port", g.a(true, b.o));
            contentValues.put("call_enable_ussd", g.a(true, b.p));
            contentValues.put("call_echo_test_number", g.a(true, "+79779943530"));
            return contentValues;
        }

        public static ContentValues a(long j) {
            Exception e;
            Throwable th;
            ContentResolver contentResolver = FgVoIP.U().getContentResolver();
            ContentValues contentValues = new ContentValues();
            Cursor query;
            try {
                query = contentResolver.query(com.mavenir.android.settings.a.c.a, null, "_id=?", new String[]{String.valueOf(j)}, null);
                if (query != null) {
                    try {
                        if (query.moveToFirst()) {
                            contentValues.put("call_uri_template", query.getString(query.getColumnIndex("call_uri_template")));
                            contentValues.put("call_pickup_template", query.getString(query.getColumnIndex("call_pickup_template")));
                            contentValues.put("call_conference_factory_uri", query.getString(query.getColumnIndex("call_conference_factory_uri")));
                            contentValues.put("call_ut_server_address", query.getString(query.getColumnIndex("call_ut_server_address")));
                            contentValues.put("call_ut_server_port", query.getString(query.getColumnIndex("call_ut_server_port")));
                            contentValues.put("call_enable_ussd", query.getString(query.getColumnIndex("call_enable_ussd")));
                            contentValues.put("call_echo_test_number", query.getString(query.getColumnIndex("call_echo_test_number")));
                        }
                    } catch (Exception e2) {
                        e = e2;
                        try {
                            com.mavenir.android.common.q.c("ClientSettingsInterface", "Call: getValues(): " + e.getLocalizedMessage(), e.getCause());
                            if (!(query == null || query.isClosed())) {
                                query.close();
                            }
                            return contentValues;
                        } catch (Throwable th2) {
                            th = th2;
                            if (!(query == null || query.isClosed())) {
                                query.close();
                            }
                            throw th;
                        }
                    }
                }
                if (!(query == null || query.isClosed())) {
                    query.close();
                }
            } catch (Exception e3) {
                e = e3;
                query = null;
                com.mavenir.android.common.q.c("ClientSettingsInterface", "Call: getValues(): " + e.getLocalizedMessage(), e.getCause());
                query.close();
                return contentValues;
            } catch (Throwable th3) {
                th = th3;
                query = null;
                query.close();
                throw th;
            }
            return contentValues;
        }

        public static String a(String str) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("sip:");
            if (str != null && str.startsWith("sip:")) {
                int indexOf = str.indexOf(64);
                if (indexOf > 3 && indexOf < str.length()) {
                    stringBuffer.append(str.substring(indexOf, str.length()));
                }
            }
            return stringBuffer.toString();
        }

        public static String b() {
            return g.c(com.mavenir.android.settings.a.c.a, "call_uri_template", "sip:@", "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static int b(String str) {
            return g.d(com.mavenir.android.settings.a.c.a, "call_uri_template", str, "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static String c(String str) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("pickup:");
            if (str.startsWith("sip:")) {
                int indexOf = str.indexOf(64);
                if (indexOf > 3 && indexOf < str.length()) {
                    stringBuffer.append(str.substring(indexOf, str.length()));
                }
            }
            return stringBuffer.toString();
        }

        public static String c() {
            return g.c(com.mavenir.android.settings.a.c.a, "call_pickup_template", "pickup:@", "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static int d(String str) {
            return g.d(com.mavenir.android.settings.a.c.a, "call_pickup_template", str, "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static String d() {
            return g.c(com.mavenir.android.settings.a.c.a, "call_conference_factory_uri", b.m, "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static int e(String str) {
            return g.d(com.mavenir.android.settings.a.c.a, "call_conference_factory_uri", str, "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static String e() {
            return g.c(com.mavenir.android.settings.a.c.a, "call_ut_server_address", b.n, "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static int f(String str) {
            com.mavenir.android.common.q.a("ClientSettingsInterface", "Call: setCallUtServerAddress()" + str);
            return g.d(com.mavenir.android.settings.a.c.a, "call_ut_server_address", str, "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static int f() {
            return g.a("call_ut_server_port", g.c(com.mavenir.android.settings.a.c.a, "call_ut_server_port", String.valueOf(b.o), "_id=?", new String[]{String.valueOf(k.c())}), b.o);
        }

        public static int a(int i) {
            return g.d(com.mavenir.android.settings.a.c.a, "call_ut_server_port", String.valueOf(i), "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static boolean g() {
            return FgVoIP.U().l() ? g.a("call_enable_ussd", g.c(com.mavenir.android.settings.a.c.a, "call_enable_ussd", String.valueOf(b.p), "_id=?", new String[]{"1"}), Boolean.valueOf(b.p)).booleanValue() : b.p;
        }

        public static int a(boolean z) {
            return g.d(com.mavenir.android.settings.a.c.a, "call_enable_ussd", String.valueOf(z), "_id=?", new String[]{"1"});
        }

        public static String h() {
            return g.c(com.mavenir.android.settings.a.c.a, "call_echo_test_number", "+79779943530", "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static int g(String str) {
            com.mavenir.android.common.q.a("ClientSettingsInterface", "Call: setEchoServiceNumber() :" + str);
            return g.d(com.mavenir.android.settings.a.c.a, "call_echo_test_number", str, "_id=?", new String[]{String.valueOf(k.c())});
        }
    }

    public static class d {
        public static ContentValues a() {
            ContentValues contentValues = new ContentValues();
            contentValues.put("capability_info_expiry", g.a(true, 86400));
            contentValues.put("capability_polling_period", g.a(true, 3600));
            contentValues.put("last_info_update_time", g.a(true, 0));
            contentValues.put("last_polling_update_time", g.a(true, 0));
            contentValues.put("cap_presence_discovery", g.a(true, b.K));
            contentValues.put("cap_sip_messaging", g.a(true, b.L));
            contentValues.put("cap_im_session", g.a(true, b.M));
            contentValues.put("cap_file_transfer", g.a(true, b.N));
            contentValues.put("cap_image_share", g.a(true, b.O));
            contentValues.put("cap_video_share", g.a(true, b.P));
            contentValues.put("cap_ip_audio_call", g.a(true, b.Q));
            contentValues.put("cap_ip_video_call", g.a(true, b.R));
            contentValues.put("cap_social_presence", g.a(true, b.S));
            contentValues.put("cap_geolocation_push", g.a(true, b.T));
            contentValues.put("cap_geolocation_pull", g.a(true, b.U));
            contentValues.put("cap_geolocation_pull_ft", g.a(true, b.V));
            contentValues.put("cap_cpm_chat", g.a(true, b.W));
            contentValues.put("cap_cpm_ft", g.a(true, b.X));
            contentValues.put("cap_cpm_standalone_msg", g.a(true, b.Y));
            return contentValues;
        }

        public static ContentValues a(long j) {
            Exception e;
            Throwable th;
            ContentResolver contentResolver = FgVoIP.U().getContentResolver();
            ContentValues contentValues = new ContentValues();
            Cursor query;
            try {
                query = contentResolver.query(com.mavenir.android.settings.a.d.a, null, "_id=?", new String[]{String.valueOf(j)}, null);
                if (query != null) {
                    try {
                        if (query.moveToFirst()) {
                            contentValues.put("capability_info_expiry", query.getString(query.getColumnIndex("capability_info_expiry")));
                            contentValues.put("capability_polling_period", query.getString(query.getColumnIndex("capability_polling_period")));
                            contentValues.put("last_info_update_time", query.getString(query.getColumnIndex("last_info_update_time")));
                            contentValues.put("last_polling_update_time", query.getString(query.getColumnIndex("last_polling_update_time")));
                            contentValues.put("cap_presence_discovery", query.getString(query.getColumnIndex("cap_presence_discovery")));
                            contentValues.put("cap_sip_messaging", query.getString(query.getColumnIndex("cap_sip_messaging")));
                            contentValues.put("cap_im_session", query.getString(query.getColumnIndex("cap_im_session")));
                            contentValues.put("cap_file_transfer", query.getString(query.getColumnIndex("cap_file_transfer")));
                            contentValues.put("cap_image_share", query.getString(query.getColumnIndex("cap_image_share")));
                            contentValues.put("cap_video_share", query.getString(query.getColumnIndex("cap_video_share")));
                            contentValues.put("cap_ip_audio_call", query.getString(query.getColumnIndex("cap_ip_audio_call")));
                            contentValues.put("cap_ip_video_call", query.getString(query.getColumnIndex("cap_ip_video_call")));
                            contentValues.put("cap_social_presence", query.getString(query.getColumnIndex("cap_social_presence")));
                            contentValues.put("cap_geolocation_push", query.getString(query.getColumnIndex("cap_geolocation_push")));
                            contentValues.put("cap_geolocation_pull", query.getString(query.getColumnIndex("cap_geolocation_pull")));
                            contentValues.put("cap_geolocation_pull_ft", query.getString(query.getColumnIndex("cap_geolocation_pull_ft")));
                            contentValues.put("cap_cpm_chat", query.getString(query.getColumnIndex("cap_cpm_chat")));
                            contentValues.put("cap_cpm_ft", query.getString(query.getColumnIndex("cap_cpm_ft")));
                            contentValues.put("cap_cpm_standalone_msg", query.getString(query.getColumnIndex("cap_cpm_standalone_msg")));
                        }
                    } catch (Exception e2) {
                        e = e2;
                        try {
                            com.mavenir.android.common.q.c("ClientSettingsInterface", "Capabilities: getValues(): " + e.getLocalizedMessage(), e.getCause());
                            if (!(query == null || query.isClosed())) {
                                query.close();
                            }
                            return contentValues;
                        } catch (Throwable th2) {
                            th = th2;
                            query.close();
                            throw th;
                        }
                    }
                }
                if (!(query == null || query.isClosed())) {
                    query.close();
                }
            } catch (Exception e3) {
                e = e3;
                query = null;
                com.mavenir.android.common.q.c("ClientSettingsInterface", "Capabilities: getValues(): " + e.getLocalizedMessage(), e.getCause());
                query.close();
                return contentValues;
            } catch (Throwable th3) {
                th = th3;
                query = null;
                if (!(query == null || query.isClosed())) {
                    query.close();
                }
                throw th;
            }
            return contentValues;
        }
    }

    public enum e {
        AMRWB(102, "AMR-WB", "AMR-WB", new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8}, Boolean.FALSE),
        AMR(96, "AMR-NB", "AMR", new int[]{0, 1, 2, 3, 4, 5, 6, 7}, Boolean.FALSE),
        PCMA(8, "PCMA", "PCMa", null, null),
        PCMU(0, "PCMU", "PCMu", null, null),
        G729(18, "G729", "G729", null, null),
        OPUS(120, "opus", "OPUS", null, null);
        
        private static e[] g;
        private static e[] h;
        private final int i;
        private final String j;
        private final String k;
        private final int[] l;
        private final Boolean m;

        static {
            g = new e[]{AMRWB, AMR, PCMA, PCMU, G729, OPUS};
            h = new e[]{AMRWB, AMR, PCMA, PCMU};
        }

        private e(int i, String str, String str2, int[] iArr, Boolean bool) {
            this.i = i;
            this.j = str;
            this.k = str2;
            this.l = iArr;
            this.m = bool;
        }

        public int a() {
            return ordinal();
        }

        public int b() {
            return this.i;
        }

        public int[] c() {
            return this.l;
        }

        public String[] d() {
            if (this.l == null || this.l.length == 0) {
                return null;
            }
            String[] strArr = new String[this.l.length];
            for (int i = 0; i < this.l.length; i++) {
                strArr[i] = Integer.toString(this.l[i]);
            }
            return strArr;
        }

        public Boolean e() {
            return this.m;
        }

        public static e a(e[] eVarArr, int i) {
            for (e eVar : eVarArr) {
                if (eVar.a() == i) {
                    return eVar;
                }
            }
            return null;
        }

        public static e a(e[] eVarArr, String str) {
            if (str != null) {
                for (e eVar : eVarArr) {
                    if (str.equalsIgnoreCase(eVar.j)) {
                        return eVar;
                    }
                }
            }
            return null;
        }

        public static e b(e[] eVarArr, String str) {
            if (str != null) {
                for (e eVar : eVarArr) {
                    if (str.equalsIgnoreCase(eVar.k)) {
                        return eVar;
                    }
                }
            }
            return null;
        }

        public static e[] a(e[] eVarArr, String[] strArr) {
            if (strArr == null || strArr.length == 0) {
                return null;
            }
            ArrayList arrayList = new ArrayList(strArr.length);
            for (String b : strArr) {
                e b2 = b(eVarArr, b);
                if (b2 != null) {
                    arrayList.add(b2);
                }
            }
            if (arrayList.isEmpty()) {
                return null;
            }
            return (e[]) arrayList.toArray(new e[arrayList.size()]);
        }

        public static String[] a(e[] eVarArr) {
            if (eVarArr == null || eVarArr.length == 0) {
                return null;
            }
            String[] strArr = new String[eVarArr.length];
            for (int i = 0; i < eVarArr.length; i++) {
                strArr[i] = eVarArr[i].j;
            }
            return strArr;
        }

        public static e[] f() {
            if (FgVoIP.U().ai()) {
                return h;
            }
            return g;
        }

        public static e[] g() {
            e[] f = f();
            if (!FgVoIP.U().ai()) {
                return f;
            }
            f = a(f, q.r());
            if (f == null) {
                return h;
            }
            return f;
        }

        public static e[] h() {
            Object g = g();
            e d = m.d();
            int b = b((e[]) g, d.a());
            if (b < 0) {
                return g;
            }
            Object obj;
            if (b > 0) {
                obj = new e[g.length];
                obj[0] = d;
                System.arraycopy(g, 0, obj, 1, b);
                int length = g.length - (b + 1);
                if (length > 0) {
                    System.arraycopy(g, b + 1, obj, b + 1, length);
                }
            } else {
                obj = g;
            }
            return obj;
        }

        public static int b(e[] eVarArr, int i) {
            if (eVarArr != null && eVarArr.length > 0) {
                for (int i2 = 0; i2 < eVarArr.length; i2++) {
                    if (eVarArr[i2].a() == i) {
                        return i2;
                    }
                }
            }
            return -1;
        }
    }

    public static class f {
        public static ContentValues a() {
            ContentValues contentValues = new ContentValues();
            contentValues.put("network_performance", g.a(true, false));
            return contentValues;
        }

        public static boolean b() {
            return g.a("network_performance", g.c(com.mavenir.android.settings.a.e.a, "network_performance", String.valueOf(false), "_id=?", new String[]{"1"}), Boolean.valueOf(false)).booleanValue();
        }

        public static int a(boolean z) {
            return g.d(com.mavenir.android.settings.a.e.a, "network_performance", String.valueOf(z), "_id=?", new String[]{"1"});
        }
    }

    public static class g {
        public static void a() {
            ContentResolver contentResolver = FgVoIP.U().getContentResolver();
            long a = p.a(contentResolver.insert(com.mavenir.android.settings.a.n.a, p.a()));
            contentResolver.insert(com.mavenir.android.settings.a.t.a, a(a, v.a()));
            contentResolver.insert(com.mavenir.android.settings.a.s.a, a(a, s.a()));
            contentResolver.insert(com.mavenir.android.settings.a.c.a, a(a, c.a()));
            contentResolver.insert(com.mavenir.android.settings.a.l.a, a(a, n.a()));
            contentResolver.insert(com.mavenir.android.settings.a.y.a, a(a, z.a()));
            contentResolver.insert(com.mavenir.android.settings.a.k.a, a(a, m.a()));
            contentResolver.insert(com.mavenir.android.settings.a.a.a, a(a, a.a()));
            contentResolver.insert(com.mavenir.android.settings.a.u.a, a(a, w.a()));
            contentResolver.insert(com.mavenir.android.settings.a.f.a, a(a, h.a()));
            contentResolver.insert(com.mavenir.android.settings.a.o.a, a(a, q.a()));
            contentResolver.insert(com.mavenir.android.settings.a.i.a, a(a, k.a()));
            if (FgVoIP.U().aj()) {
                contentResolver.insert(com.mavenir.android.settings.a.d.a, a(a, d.a()));
                contentResolver.insert(com.mavenir.android.settings.a.r.a, a(a, u.a()));
                contentResolver.insert(com.mavenir.android.settings.a.q.a, a(a, t.a()));
            }
            contentResolver.bulkInsert(com.mavenir.android.settings.a.g.a, i.a());
            contentResolver.insert(com.mavenir.android.settings.a.b.a, a(1, b.a()));
            contentResolver.insert(com.mavenir.android.settings.a.e.a, a(a, f.a()));
            contentResolver.insert(com.mavenir.android.settings.a.j.a, a(a, l.a()));
        }

        public static void a(long j, String str) {
            ContentResolver contentResolver = FgVoIP.U().getContentResolver();
            ContentValues a = p.a(j);
            a.put("profile_name", a(true, str));
            long a2 = p.a(contentResolver.insert(com.mavenir.android.settings.a.n.a, a));
            contentResolver.insert(com.mavenir.android.settings.a.t.a, a(a2, v.a(j)));
            contentResolver.insert(com.mavenir.android.settings.a.s.a, a(a2, s.a(j)));
            contentResolver.insert(com.mavenir.android.settings.a.c.a, a(a2, c.a(j)));
            contentResolver.insert(com.mavenir.android.settings.a.l.a, a(a2, n.a(j)));
            contentResolver.insert(com.mavenir.android.settings.a.y.a, a(a2, z.a(j)));
            contentResolver.insert(com.mavenir.android.settings.a.k.a, a(a2, m.a(j)));
            contentResolver.insert(com.mavenir.android.settings.a.a.a, a(a2, a.a(j)));
            contentResolver.insert(com.mavenir.android.settings.a.u.a, a(a2, w.a(j)));
            contentResolver.insert(com.mavenir.android.settings.a.f.a, a(a2, h.a(j)));
            contentResolver.insert(com.mavenir.android.settings.a.o.a, a(a2, q.a(j)));
            contentResolver.bulkInsert(com.mavenir.android.settings.a.g.a, i.a(a2, j));
            if (FgVoIP.U().aj()) {
                contentResolver.insert(com.mavenir.android.settings.a.d.a, a(a2, d.a(j)));
                contentResolver.insert(com.mavenir.android.settings.a.r.a, a(a2, u.a(j)));
                contentResolver.insert(com.mavenir.android.settings.a.q.a, a(a2, t.a(j)));
            }
        }

        public static void a(long j) {
            ContentResolver contentResolver = FgVoIP.U().getContentResolver();
            contentResolver.delete(ContentUris.withAppendedId(com.mavenir.android.settings.a.n.a, j), null, null);
            contentResolver.delete(ContentUris.withAppendedId(com.mavenir.android.settings.a.t.a, j), null, null);
            contentResolver.delete(ContentUris.withAppendedId(com.mavenir.android.settings.a.s.a, j), null, null);
            contentResolver.delete(ContentUris.withAppendedId(com.mavenir.android.settings.a.c.a, j), null, null);
            contentResolver.delete(ContentUris.withAppendedId(com.mavenir.android.settings.a.l.a, j), null, null);
            contentResolver.delete(ContentUris.withAppendedId(com.mavenir.android.settings.a.y.a, j), null, null);
            contentResolver.delete(ContentUris.withAppendedId(com.mavenir.android.settings.a.k.a, j), null, null);
            contentResolver.delete(ContentUris.withAppendedId(com.mavenir.android.settings.a.a.a, j), null, null);
            contentResolver.delete(ContentUris.withAppendedId(com.mavenir.android.settings.a.u.a, j), null, null);
            contentResolver.delete(ContentUris.withAppendedId(com.mavenir.android.settings.a.f.a, j), null, null);
            contentResolver.delete(ContentUris.withAppendedId(com.mavenir.android.settings.a.o.a, j), null, null);
            if (FgVoIP.U().aj()) {
                contentResolver.delete(ContentUris.withAppendedId(com.mavenir.android.settings.a.d.a, j), null, null);
                contentResolver.delete(ContentUris.withAppendedId(com.mavenir.android.settings.a.r.a, j), null, null);
                contentResolver.delete(ContentUris.withAppendedId(com.mavenir.android.settings.a.q.a, j), null, null);
            }
        }

        public static ContentValues a(long j, ContentValues contentValues) {
            contentValues.put("_id", Long.valueOf(j));
            return contentValues;
        }

        public static String a(boolean z, String str, String str2) {
            if (!z) {
                return str2;
            }
            try {
                return CryptoAES.getInstance().decrypt(c.a(), str, str2, false, true);
            } catch (Exception e) {
                com.mavenir.android.common.q.c("ClientSettingsInterface", "decryptValue():" + e, e.getCause());
                return null;
            }
        }

        public static String a(boolean z, String str, String str2, String str3) {
            if (!z) {
                return str3;
            }
            try {
                return CryptoAES.getInstance().decrypt(str, str2, str3, true, false);
            } catch (Exception e) {
                Log.e("ClientSettingsInterface", "decryptValue():" + e, e.getCause());
                return null;
            }
        }

        public static String a(boolean z, String str) {
            if (z) {
                try {
                    str = CryptoAES.getInstance().encrypt(c.a(), str);
                } catch (Exception e) {
                    com.mavenir.android.common.q.d("ClientSettingsInterface", "enryptValue(): " + e.getLocalizedMessage());
                }
            }
            return str;
        }

        public static String a(boolean z, int i) {
            return a(z, String.valueOf(i));
        }

        public static String a(boolean z, long j) {
            return a(z, String.valueOf(j));
        }

        public static String a(boolean z, boolean z2) {
            return a(z, String.valueOf(z2));
        }

        private static long c(Uri uri, String str, long j, String str2, String[] strArr) {
            Object e;
            Throwable th;
            Cursor query;
            try {
                query = FgVoIP.U().getContentResolver().query(uri, new String[]{str}, str2, strArr, null);
                if (query != null) {
                    try {
                        if (query.moveToFirst()) {
                            int columnIndexOrThrow = query.getColumnIndexOrThrow(str);
                            if (!query.isNull(columnIndexOrThrow)) {
                                j = query.getLong(columnIndexOrThrow);
                            } else if (!str.equals("active_profile_id")) {
                                com.mavenir.android.common.q.c("ClientSettingsInterface", "getDatabaseLongValue(): Attribute " + str + " is NULL");
                            }
                        }
                    } catch (Exception e2) {
                        e = e2;
                        try {
                            if (!str.equals("active_profile_id")) {
                                com.mavenir.android.common.q.d("ClientSettingsInterface", "getDatabaseLongValue(): " + e);
                            }
                            if (!(query == null || query.isClosed())) {
                                query.close();
                            }
                            return j;
                        } catch (Throwable th2) {
                            th = th2;
                            if (!(query == null || query.isClosed())) {
                                query.close();
                            }
                            throw th;
                        }
                    }
                }
                if (!(query == null || query.isClosed())) {
                    query.close();
                }
            } catch (Exception e3) {
                e = e3;
                query = null;
                if (str.equals("active_profile_id")) {
                    com.mavenir.android.common.q.d("ClientSettingsInterface", "getDatabaseLongValue(): " + e);
                }
                query.close();
                return j;
            } catch (Throwable th3) {
                th = th3;
                query = null;
                query.close();
                throw th;
            }
            return j;
        }

        private static int d(Uri uri, String str, long j, String str2, String[] strArr) {
            int update;
            Object e;
            try {
                ContentValues contentValues = new ContentValues();
                contentValues.put(str, Long.valueOf(j));
                update = FgVoIP.U().getContentResolver().update(uri, contentValues, str2, strArr);
                if (update == 0) {
                    try {
                        com.mavenir.android.common.q.c("ClientSettingsInterface", "setDatabaseLongValue(): Attribute " + str + " not set");
                    } catch (Exception e2) {
                        e = e2;
                        com.mavenir.android.common.q.d("ClientSettingsInterface", "setDatabaseLongValue(): " + e);
                        return update;
                    }
                }
            } catch (Exception e3) {
                Exception exception = e3;
                update = 0;
                e = exception;
                com.mavenir.android.common.q.d("ClientSettingsInterface", "setDatabaseLongValue(): " + e);
                return update;
            }
            return update;
        }

        private static String c(Uri uri, String str, String str2, String str3, String[] strArr) {
            String string;
            Object e;
            Throwable th;
            Cursor query;
            try {
                query = FgVoIP.U().getContentResolver().query(uri, new String[]{str}, str3, strArr, null);
                if (query != null) {
                    try {
                        if (query.moveToFirst()) {
                            int columnIndexOrThrow = query.getColumnIndexOrThrow(str);
                            if (!query.isNull(columnIndexOrThrow)) {
                                string = query.getString(columnIndexOrThrow);
                                if (string != null) {
                                    string = a(true, str, string);
                                    if (!(query == null || query.isClosed())) {
                                        query.close();
                                    }
                                    return string == null ? string : str2;
                                }
                            } else if (!str.equals("trace_enabled")) {
                                com.mavenir.android.common.q.c("ClientSettingsInterface", "getDatabaseStringValue(): Attribute " + str + " is NULL");
                            }
                        }
                    } catch (Exception e2) {
                        e = e2;
                        try {
                            if (!str.equals("trace_enabled")) {
                                com.mavenir.android.common.q.d("ClientSettingsInterface", "getDatabaseStringValue(): " + e);
                            }
                            if (query != null || query.isClosed()) {
                                string = str2;
                            } else {
                                query.close();
                                string = str2;
                            }
                            if (string == null) {
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            if (!(query == null || query.isClosed())) {
                                query.close();
                            }
                            throw th;
                        }
                    }
                }
                string = str2;
                query.close();
            } catch (Exception e3) {
                e = e3;
                query = null;
                if (str.equals("trace_enabled")) {
                    com.mavenir.android.common.q.d("ClientSettingsInterface", "getDatabaseStringValue(): " + e);
                }
                if (query != null) {
                }
                string = str2;
                if (string == null) {
                }
            } catch (Throwable th3) {
                th = th3;
                query = null;
                query.close();
                throw th;
            }
            if (string == null) {
            }
        }

        private static String c(Uri uri, String str, String str2, String str3, String[] strArr, boolean z) {
            Object e;
            String str4;
            Throwable th;
            Cursor query;
            String string;
            try {
                query = FgVoIP.U().getContentResolver().query(uri, new String[]{str}, str3, strArr, null);
                if (query != null) {
                    try {
                        if (query.moveToFirst()) {
                            int columnIndexOrThrow = query.getColumnIndexOrThrow(str);
                            if (query.isNull(columnIndexOrThrow)) {
                                com.mavenir.android.common.q.c("ClientSettingsInterface", "getDatabaseStringValue(): Attribute " + str + " is NULL");
                            } else {
                                string = query.getString(columnIndexOrThrow);
                                if (string != null) {
                                    try {
                                        string = a(z, str, string);
                                    } catch (Exception e2) {
                                        e = e2;
                                        try {
                                            com.mavenir.android.common.q.d("ClientSettingsInterface", "getDatabaseStringValue(): " + e);
                                            query.close();
                                            str4 = string;
                                            return str4 != null ? str2 : str4;
                                        } catch (Throwable th2) {
                                            th = th2;
                                            query.close();
                                            throw th;
                                        }
                                    }
                                }
                                if (!(query == null || query.isClosed())) {
                                    query.close();
                                    str4 = string;
                                    if (str4 != null) {
                                    }
                                }
                                str4 = string;
                                if (str4 != null) {
                                }
                            }
                        }
                    } catch (Exception e3) {
                        e = e3;
                        string = str2;
                        com.mavenir.android.common.q.d("ClientSettingsInterface", "getDatabaseStringValue(): " + e);
                        query.close();
                        str4 = string;
                        if (str4 != null) {
                        }
                    }
                }
                string = str2;
                query.close();
                str4 = string;
            } catch (Exception e4) {
                e = e4;
                query = null;
                string = str2;
                com.mavenir.android.common.q.d("ClientSettingsInterface", "getDatabaseStringValue(): " + e);
                if (!(query == null || query.isClosed())) {
                    query.close();
                    str4 = string;
                    if (str4 != null) {
                    }
                }
                str4 = string;
                if (str4 != null) {
                }
            } catch (Throwable th3) {
                th = th3;
                query = null;
                if (!(query == null || query.isClosed())) {
                    query.close();
                }
                throw th;
            }
            if (str4 != null) {
            }
        }

        private static int d(Uri uri, String str, String str2, String str3, String[] strArr) {
            int update;
            Object e;
            try {
                String str4 = "";
                if (str2 != null) {
                    str4 = a(true, str2);
                    if (str4 == null) {
                        str4 = "";
                    }
                }
                ContentValues contentValues = new ContentValues();
                contentValues.put(str, str4);
                update = FgVoIP.U().getContentResolver().update(uri, contentValues, str3, strArr);
                if (update == 0) {
                    try {
                        com.mavenir.android.common.q.c("ClientSettingsInterface", "setDatabaseStringValue(): Attribute " + str + " not set");
                    } catch (Exception e2) {
                        e = e2;
                        com.mavenir.android.common.q.d("ClientSettingsInterface", "setDatabaseStringValue(): " + e);
                        return update;
                    }
                }
            } catch (Exception e3) {
                Exception exception = e3;
                update = 0;
                e = exception;
                com.mavenir.android.common.q.d("ClientSettingsInterface", "setDatabaseStringValue(): " + e);
                return update;
            }
            return update;
        }

        private static int d(Uri uri, String str, String str2, String str3, String[] strArr, boolean z) {
            int update;
            Object e;
            try {
                String str4 = "";
                if (str2 != null) {
                    str4 = a(z, str2);
                    if (str4 == null) {
                        str4 = "";
                    }
                }
                ContentValues contentValues = new ContentValues();
                contentValues.put(str, str4);
                update = FgVoIP.U().getContentResolver().update(uri, contentValues, str3, strArr);
                if (update == 0) {
                    try {
                        com.mavenir.android.common.q.c("ClientSettingsInterface", "setDatabaseStringValue(): Attribute " + str + " not set");
                    } catch (Exception e2) {
                        e = e2;
                        com.mavenir.android.common.q.d("ClientSettingsInterface", "setDatabaseStringValue(): " + e);
                        return update;
                    }
                }
            } catch (Exception e3) {
                Exception exception = e3;
                update = 0;
                e = exception;
                com.mavenir.android.common.q.d("ClientSettingsInterface", "setDatabaseStringValue(): " + e);
                return update;
            }
            return update;
        }

        private static int b(Uri uri, ContentValues[] contentValuesArr) {
            int bulkInsert;
            Object e;
            try {
                bulkInsert = FgVoIP.U().getContentResolver().bulkInsert(uri, contentValuesArr);
                if (bulkInsert == 0) {
                    try {
                        com.mavenir.android.common.q.c("ClientSettingsInterface", "databaseBulkInsert(): nothing inserted into table " + uri.getLastPathSegment());
                    } catch (Exception e2) {
                        e = e2;
                        com.mavenir.android.common.q.d("ClientSettingsInterface", "databaseBulkInsert(): " + e);
                        return bulkInsert;
                    }
                }
            } catch (Exception e3) {
                Exception exception = e3;
                bulkInsert = 0;
                e = exception;
                com.mavenir.android.common.q.d("ClientSettingsInterface", "databaseBulkInsert(): " + e);
                return bulkInsert;
            }
            return bulkInsert;
        }

        private static int b(Uri uri, String str, String[] strArr) {
            int delete;
            Object e;
            try {
                delete = FgVoIP.U().getContentResolver().delete(uri, str, strArr);
                if (delete == 0) {
                    try {
                        com.mavenir.android.common.q.c("ClientSettingsInterface", "databaseDeleteValues(): nothing deleted from table " + uri.getLastPathSegment());
                    } catch (Exception e2) {
                        e = e2;
                        com.mavenir.android.common.q.d("ClientSettingsInterface", "databaseDeleteValues(): " + e);
                        return delete;
                    }
                }
            } catch (Exception e3) {
                Exception exception = e3;
                delete = 0;
                e = exception;
                com.mavenir.android.common.q.d("ClientSettingsInterface", "databaseDeleteValues(): " + e);
                return delete;
            }
            return delete;
        }

        public static Boolean a(String str, String str2, Boolean bool) {
            if (str2 != null) {
                try {
                    bool = Boolean.valueOf(str2);
                } catch (Exception e) {
                    com.mavenir.android.common.q.d("ClientSettingsInterface", "parseBoolean(): failed to parse value: " + str2);
                    if (!com.mavenir.android.applog.a.d()) {
                        com.mavenir.android.applog.a.a(FgVoIP.U()).a(com.mavenir.android.applog.AppLogAdapter.b.FGAPPLOG_EVENT_GROUP_DATABASE_ERROR, com.mavenir.android.applog.AppLogAdapter.d.FGAPPLOG_EVENT_TYPE_DECRYPTION_ERROR, com.mavenir.android.applog.AppLogAdapter.c.FGAPPLOG_EVENT_REASON_PARSING_ERROR, "Failed to parse boolean " + str + ", value: " + str2);
                    }
                }
                return bool;
            }
            com.mavenir.android.common.q.c("ClientSettingsInterface", "parseBoolean(): returning default value: " + bool);
            return bool;
        }

        public static int a(String str, String str2, int i) {
            if (str2 != null) {
                try {
                    i = Integer.valueOf(str2).intValue();
                } catch (Exception e) {
                    com.mavenir.android.common.q.d("ClientSettingsInterface", "parseInteger(): failed to parse value: " + str2);
                    if (!com.mavenir.android.applog.a.d()) {
                        com.mavenir.android.applog.a.a(FgVoIP.U()).a(com.mavenir.android.applog.AppLogAdapter.b.FGAPPLOG_EVENT_GROUP_DATABASE_ERROR, com.mavenir.android.applog.AppLogAdapter.d.FGAPPLOG_EVENT_TYPE_DECRYPTION_ERROR, com.mavenir.android.applog.AppLogAdapter.c.FGAPPLOG_EVENT_REASON_PARSING_ERROR, "Failed to parse integer " + str + ", value: " + str2);
                    }
                }
                return i;
            }
            com.mavenir.android.common.q.c("ClientSettingsInterface", "parseInteger(): " + str + ", returning default value: " + i);
            return i;
        }

        public static long a(String str, String str2, long j) {
            if (str2 != null) {
                try {
                    j = Long.valueOf(str2).longValue();
                } catch (Exception e) {
                    com.mavenir.android.common.q.d("ClientSettingsInterface", "parseLong(): failed to parse value: " + str2);
                    if (!com.mavenir.android.applog.a.d()) {
                        com.mavenir.android.applog.a.a(FgVoIP.U()).a(com.mavenir.android.applog.AppLogAdapter.b.FGAPPLOG_EVENT_GROUP_DATABASE_ERROR, com.mavenir.android.applog.AppLogAdapter.d.FGAPPLOG_EVENT_TYPE_DECRYPTION_ERROR, com.mavenir.android.applog.AppLogAdapter.c.FGAPPLOG_EVENT_REASON_PARSING_ERROR, "Failed to parse long " + str + ", value: " + str2);
                    }
                }
                return j;
            }
            com.mavenir.android.common.q.c("ClientSettingsInterface", "parseLong(): returning default value: " + j);
            return j;
        }

        public static boolean b() {
            try {
                ContentResolver contentResolver = FgVoIP.U().getContentResolver();
                contentResolver.delete(com.mavenir.android.settings.a.i.a, null, null);
                contentResolver.delete(com.mavenir.android.settings.a.n.a, null, null);
                contentResolver.delete(com.mavenir.android.settings.a.t.a, null, null);
                contentResolver.delete(com.mavenir.android.settings.a.s.a, null, null);
                contentResolver.delete(com.mavenir.android.settings.a.c.a, null, null);
                contentResolver.delete(com.mavenir.android.settings.a.l.a, null, null);
                contentResolver.delete(com.mavenir.android.settings.a.y.a, null, null);
                contentResolver.delete(com.mavenir.android.settings.a.k.a, null, null);
                contentResolver.delete(com.mavenir.android.settings.a.a.a, null, null);
                contentResolver.delete(com.mavenir.android.settings.a.u.a, null, null);
                contentResolver.delete(com.mavenir.android.settings.a.f.a, null, null);
                contentResolver.delete(com.mavenir.android.settings.a.o.a, null, null);
                contentResolver.delete(com.mavenir.android.settings.a.x.a, null, null);
                contentResolver.delete(com.mavenir.android.settings.a.g.a, null, null);
                contentResolver.delete(com.mavenir.android.settings.a.m.a, null, null);
                if (FgVoIP.U().aj()) {
                    contentResolver.delete(com.mavenir.android.settings.a.d.a, null, null);
                    contentResolver.delete(com.mavenir.android.settings.a.r.a, null, null);
                    contentResolver.delete(com.mavenir.android.settings.a.q.a, null, null);
                }
                contentResolver.delete(com.mavenir.android.settings.a.b.a, null, null);
                contentResolver.delete(com.mavenir.android.settings.a.e.a, null, null);
                contentResolver.delete(com.mavenir.android.settings.a.j.a, null, null);
                a();
                return true;
            } catch (Exception e) {
                com.mavenir.android.common.q.c("ClientSettingsInterface", "resetDatabase(): " + e.getLocalizedMessage(), e.getCause());
                return false;
            }
        }

        public static boolean c() {
            try {
                FgVoIP.U().getContentResolver().delete(com.mavenir.android.settings.a.x.a, null, null);
                return true;
            } catch (Exception e) {
                com.mavenir.android.common.q.c("ClientSettingsInterface", "resetWhitelist(): " + e.getLocalizedMessage(), e.getCause());
                return false;
            }
        }

        public static boolean d() {
            try {
                k.a(true);
                k.b(true);
                k.c(true);
                k.k(true);
                k.d(b.x);
                k.g(true);
                FgVoIP.U().a(FgVoIP.U(), "com.mavenir.android.action_restore_user_prefs");
                return true;
            } catch (Exception e) {
                com.mavenir.android.common.q.c("ClientSettingsInterface", "resetGeneralPreferences(): " + e.getLocalizedMessage(), e.getCause());
                return false;
            }
        }

        public static void a(boolean z) {
            ContentValues contentValues = null;
            if (z) {
                contentValues = e();
            }
            b();
            com.mavenir.android.common.q.a("ClientSettingsInterface", "recoverDatabase(): database reset");
            if (z) {
                com.mavenir.android.common.q.b("ClientSettingsInterface", "recoverDatabase(): database " + (a(contentValues) ? "restored successfully" : "restoration failed"));
            }
        }

        private static ContentValues e() {
            ContentValues contentValues = new ContentValues();
            contentValues.put("provisioning_vers", Integer.valueOf(-1));
            a(contentValues, com.mavenir.android.settings.a.n.a, "cc_fqdn");
            a(contentValues, com.mavenir.android.settings.a.n.a, "impu");
            a(contentValues, com.mavenir.android.settings.a.n.a, "impi");
            a(contentValues, com.mavenir.android.settings.a.n.a, "password");
            a(contentValues, com.mavenir.android.settings.a.n.a, "msisdn");
            a(contentValues, com.mavenir.android.settings.a.n.a, "hashed_msisdn");
            a(contentValues, com.mavenir.android.settings.a.n.a, "tac");
            a(contentValues, com.mavenir.android.settings.a.n.a, "svn");
            a(contentValues, com.mavenir.android.settings.a.n.a, "network_imsi");
            return contentValues;
        }

        private static void a(ContentValues contentValues, Uri uri, String str) {
            Uri uri2 = uri;
            String str2 = str;
            str2 = c(uri2, str2, null, "_id=?", new String[]{"1"}, false);
            String a = a(true, Secure.getString(FgVoIP.U().getContentResolver(), "android_id"), str, str2);
            if (TextUtils.isEmpty(a)) {
                a = a(true, com.mavenir.android.common.l.a(FgVoIP.U()).k(), str, str2);
            }
            if (TextUtils.isEmpty(a)) {
                a = a(true, "android_id", str, str2);
            }
            contentValues.put(str, a);
        }

        private static boolean a(ContentValues contentValues) {
            if (contentValues != null && contentValues.size() > 0) {
                Integer asInteger = contentValues.getAsInteger("provisioning_vers");
                Object asString = contentValues.getAsString("cc_fqdn");
                Object asString2 = contentValues.getAsString("impu");
                Object asString3 = contentValues.getAsString("impi");
                Object asString4 = contentValues.getAsString("password");
                Object asString5 = contentValues.getAsString("msisdn");
                Object asString6 = contentValues.getAsString("hashed_msisdn");
                Object asString7 = contentValues.getAsString("tac");
                Object asString8 = contentValues.getAsString("svn");
                Object asString9 = contentValues.getAsString("network_imsi");
                if (!(asInteger.intValue() != -1 || TextUtils.isEmpty(asString) || TextUtils.isEmpty(asString2) || TextUtils.isEmpty(asString3) || TextUtils.isEmpty(asString4) || TextUtils.isEmpty(asString5) || TextUtils.isEmpty(asString6) || TextUtils.isEmpty(asString7) || TextUtils.isEmpty(asString8) || TextUtils.isEmpty(asString9))) {
                    k.a(asInteger.intValue());
                    p.m(asString);
                    p.c(asString2);
                    p.d(asString3);
                    p.e(asString4);
                    p.h(asString5);
                    p.f(asString6);
                    p.i(asString7);
                    p.j(asString8);
                    p.k(asString9);
                    return true;
                }
            }
            return false;
        }

        public static boolean a(String str) {
            if (str.equals("ssid") || str.equals("bssid")) {
                return true;
            }
            return false;
        }

        public static boolean b(String str) {
            if (str.equals("save_user_data") || str.equals("enable_status_icon") || str.equals("enable_messaging") || str.equals("sms_request_delivery_reports") || str.equals("sms_notify_incoming") || str.equals("media_alert_volume") || str.equals("enable_tcp_keep_alive")) {
                return true;
            }
            return false;
        }

        public static boolean c(String str) {
            if (str.equals("trace_enabled") || str.equals("trace_level_data") || str.equals("trace_level_external") || str.equals("trace_level_gui") || str.equals("trace_level_media") || str.equals("trace_level_sip") || str.equals("trace_write_mode") || str.equals("enable_adb_hooks") || str.equals("enable_qos_test_ui")) {
                return true;
            }
            return false;
        }
    }

    public static class h {
        public static ContentValues a() {
            ContentValues contentValues = new ContentValues();
            contentValues.put("trace_enabled", g.a(false, false));
            contentValues.put("trace_write_mode", g.a(true, false));
            contentValues.put("trace_level_data", g.a(true, 0));
            contentValues.put("trace_level_external", g.a(true, 0));
            contentValues.put("trace_level_gui", g.a(true, 0));
            contentValues.put("trace_level_media", g.a(true, 0));
            contentValues.put("trace_level_sip", g.a(true, 0));
            contentValues.put("enable_qos_test_ui", g.a(true, false));
            contentValues.put("enable_adb_hooks", g.a(true, false));
            return contentValues;
        }

        public static ContentValues a(long j) {
            Exception e;
            Throwable th;
            ContentResolver contentResolver = FgVoIP.U().getContentResolver();
            ContentValues contentValues = new ContentValues();
            Cursor query;
            try {
                query = contentResolver.query(com.mavenir.android.settings.a.f.a, null, "_id=?", new String[]{String.valueOf(j)}, null);
                if (query != null) {
                    try {
                        if (query.moveToFirst()) {
                            contentValues.put("trace_write_mode", query.getString(query.getColumnIndex("trace_write_mode")));
                            contentValues.put("trace_enabled", query.getString(query.getColumnIndex("trace_enabled")));
                            contentValues.put("trace_level_data", query.getString(query.getColumnIndex("trace_level_data")));
                            contentValues.put("trace_level_external", query.getString(query.getColumnIndex("trace_level_external")));
                            contentValues.put("trace_level_gui", query.getString(query.getColumnIndex("trace_level_gui")));
                            contentValues.put("trace_level_media", query.getString(query.getColumnIndex("trace_level_media")));
                            contentValues.put("trace_level_sip", query.getString(query.getColumnIndex("trace_level_sip")));
                            contentValues.put("enable_qos_test_ui", query.getString(query.getColumnIndex("enable_qos_test_ui")));
                        }
                    } catch (Exception e2) {
                        e = e2;
                        try {
                            com.mavenir.android.common.q.c("ClientSettingsInterface", "Developer: getValues(): " + e.getLocalizedMessage(), e.getCause());
                            if (!(query == null || query.isClosed())) {
                                query.close();
                            }
                            return contentValues;
                        } catch (Throwable th2) {
                            th = th2;
                            if (!(query == null || query.isClosed())) {
                                query.close();
                            }
                            throw th;
                        }
                    }
                }
                if (!(query == null || query.isClosed())) {
                    query.close();
                }
            } catch (Exception e3) {
                e = e3;
                query = null;
                com.mavenir.android.common.q.c("ClientSettingsInterface", "Developer: getValues(): " + e.getLocalizedMessage(), e.getCause());
                query.close();
                return contentValues;
            } catch (Throwable th3) {
                th = th3;
                query = null;
                query.close();
                throw th;
            }
            return contentValues;
        }

        public static boolean b() {
            return g.a("trace_write_mode", g.c(com.mavenir.android.settings.a.f.a, "trace_write_mode", String.valueOf(false), "_id=?", new String[]{String.valueOf(k.c())}), Boolean.valueOf(false)).booleanValue();
        }

        public static int a(boolean z) {
            return g.d(com.mavenir.android.settings.a.f.a, "trace_write_mode", String.valueOf(z), "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static boolean c() {
            return g.a("trace_enabled", g.c(com.mavenir.android.settings.a.f.a, "trace_enabled", String.valueOf(false), "_id=?", new String[]{String.valueOf(k.c())}, false), Boolean.valueOf(false)).booleanValue();
        }

        public static int b(boolean z) {
            return g.d(com.mavenir.android.settings.a.f.a, "trace_enabled", String.valueOf(z), "_id=?", new String[]{String.valueOf(k.c())}, false);
        }

        public static int d() {
            return g.a("trace_level_data", g.c(com.mavenir.android.settings.a.f.a, "trace_level_data", String.valueOf(0), "_id=?", new String[]{String.valueOf(k.c())}), 0);
        }

        public static int a(int i) {
            return g.d(com.mavenir.android.settings.a.f.a, "trace_level_data", String.valueOf(i), "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static int e() {
            return g.a("trace_level_external", g.c(com.mavenir.android.settings.a.f.a, "trace_level_external", String.valueOf(0), "_id=?", new String[]{String.valueOf(k.c())}), 0);
        }

        public static int b(int i) {
            return g.d(com.mavenir.android.settings.a.f.a, "trace_level_external", String.valueOf(i), "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static int f() {
            return g.a("trace_level_gui", g.c(com.mavenir.android.settings.a.f.a, "trace_level_gui", String.valueOf(0), "_id=?", new String[]{String.valueOf(k.c())}), 0);
        }

        public static int c(int i) {
            return g.d(com.mavenir.android.settings.a.f.a, "trace_level_gui", String.valueOf(i), "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static int g() {
            return g.a("trace_level_media", g.c(com.mavenir.android.settings.a.f.a, "trace_level_media", String.valueOf(0), "_id=?", new String[]{String.valueOf(k.c())}), 0);
        }

        public static int d(int i) {
            return g.d(com.mavenir.android.settings.a.f.a, "trace_level_media", String.valueOf(i), "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static int h() {
            return g.a("trace_level_sip", g.c(com.mavenir.android.settings.a.f.a, "trace_level_sip", String.valueOf(0), "_id=?", new String[]{String.valueOf(k.c())}), 0);
        }

        public static int e(int i) {
            return g.d(com.mavenir.android.settings.a.f.a, "trace_level_sip", String.valueOf(i), "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static boolean i() {
            return g.a("enable_qos_test_ui", g.c(com.mavenir.android.settings.a.f.a, "enable_qos_test_ui", String.valueOf(false), "_id=?", new String[]{String.valueOf(k.c())}), Boolean.valueOf(false)).booleanValue();
        }

        public static int c(boolean z) {
            return g.d(com.mavenir.android.settings.a.f.a, "enable_qos_test_ui", String.valueOf(z), "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static boolean j() {
            return g.a("enable_adb_hooks", g.c(com.mavenir.android.settings.a.f.a, "enable_adb_hooks", String.valueOf(false), "_id=?", new String[]{String.valueOf(k.c())}), Boolean.valueOf(false)).booleanValue();
        }

        public static int d(boolean z) {
            return g.d(com.mavenir.android.settings.a.f.a, "enable_adb_hooks", String.valueOf(z), "_id=?", new String[]{String.valueOf(k.c())});
        }
    }

    public static class i {
        public static ContentValues[] a() {
            ContentValues[] contentValuesArr = new ContentValues[b.F.length];
            for (int i = 0; i < b.F.length; i++) {
                contentValuesArr[i] = new ContentValues();
                contentValuesArr[i].put("profile_id", Integer.valueOf(1));
                contentValuesArr[i].put("number", g.a(true, b.F[i]));
            }
            return contentValuesArr;
        }

        public static ContentValues[] a(long j, long j2) {
            ContentValues[] contentValuesArr;
            Exception exception;
            Throwable th;
            ContentValues[] contentValuesArr2 = new ContentValues[0];
            Cursor query;
            try {
                query = FgVoIP.U().getContentResolver().query(com.mavenir.android.settings.a.g.a, null, "profile_id=?", new String[]{String.valueOf(j2)}, null);
                if (query != null) {
                    try {
                        ContentValues[] contentValuesArr3 = new ContentValues[query.getCount()];
                        int i = 0;
                        while (query.moveToNext()) {
                            try {
                                contentValuesArr3[i] = new ContentValues();
                                contentValuesArr3[i].put("profile_id", Long.valueOf(j));
                                contentValuesArr3[i].put("number", query.getString(query.getColumnIndex("number")));
                                i++;
                            } catch (Exception e) {
                                Exception exception2 = e;
                                contentValuesArr = contentValuesArr3;
                                exception = exception2;
                            }
                        }
                        contentValuesArr = contentValuesArr3;
                    } catch (Exception e2) {
                        exception = e2;
                        contentValuesArr = contentValuesArr2;
                        try {
                            com.mavenir.android.common.q.c("ClientSettingsInterface", "EmergencyNumbers: getValues(): " + exception.getLocalizedMessage(), exception.getCause());
                            if (!(query == null || query.isClosed())) {
                                query.close();
                            }
                            return contentValuesArr;
                        } catch (Throwable th2) {
                            th = th2;
                            query.close();
                            throw th;
                        }
                    }
                }
                contentValuesArr = contentValuesArr2;
                if (!(query == null || query.isClosed())) {
                    query.close();
                }
            } catch (Exception e22) {
                exception = e22;
                query = null;
                contentValuesArr = contentValuesArr2;
                com.mavenir.android.common.q.c("ClientSettingsInterface", "EmergencyNumbers: getValues(): " + exception.getLocalizedMessage(), exception.getCause());
                query.close();
                return contentValuesArr;
            } catch (Throwable th3) {
                th = th3;
                query = null;
                if (!(query == null || query.isClosed())) {
                    query.close();
                }
                throw th;
            }
            return contentValuesArr;
        }

        public static List<String> b() {
            Object e;
            Throwable th;
            Cursor cursor = null;
            List<String> arrayList = new ArrayList();
            Cursor query;
            try {
                query = FgVoIP.U().getContentResolver().query(com.mavenir.android.settings.a.g.a, new String[]{"number"}, "profile_id=?", new String[]{"" + p.c()}, null);
                if (query != null) {
                    while (query.moveToNext()) {
                        try {
                            arrayList.add(g.a(true, "number", query.getString(query.getColumnIndex("number"))));
                        } catch (Exception e2) {
                            e = e2;
                        }
                    }
                }
                if (query != null) {
                    query.close();
                }
            } catch (Exception e3) {
                e = e3;
                query = null;
                try {
                    com.mavenir.android.common.q.d("ClientSettingsInterface", "EmergencyNumbers: getEmergencyNumbers(): " + e);
                    if (query != null) {
                        query.close();
                    }
                    return arrayList;
                } catch (Throwable th2) {
                    th = th2;
                    cursor = query;
                    if (cursor != null) {
                        cursor.close();
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                if (cursor != null) {
                    cursor.close();
                }
                throw th;
            }
            return arrayList;
        }

        public static int a(String[] strArr) {
            ContentValues[] contentValuesArr;
            int i = 0;
            long c = p.c();
            ContentValues[] contentValuesArr2;
            if (strArr == null || strArr.length <= 0) {
                String[] strArr2 = b.F;
                contentValuesArr2 = new ContentValues[strArr2.length];
                while (i < strArr2.length) {
                    contentValuesArr2[i] = new ContentValues();
                    contentValuesArr2[i].put("profile_id", Long.valueOf(c));
                    contentValuesArr2[i].put("number", g.a(true, strArr2[i]));
                    i++;
                }
                contentValuesArr = contentValuesArr2;
            } else {
                contentValuesArr2 = new ContentValues[strArr.length];
                while (i < strArr.length) {
                    contentValuesArr2[i] = new ContentValues();
                    contentValuesArr2[i].put("profile_id", Long.valueOf(c));
                    contentValuesArr2[i].put("number", g.a(true, strArr[i]));
                    i++;
                }
                contentValuesArr = contentValuesArr2;
            }
            c();
            return g.b(com.mavenir.android.settings.a.g.a, contentValuesArr);
        }

        public static int c() {
            return g.b(com.mavenir.android.settings.a.g.a, "profile_id=?", new String[]{"" + p.c()});
        }
    }

    public static class j {
        public static Uri a(int i, String str, String str2) {
            try {
                ContentValues contentValues = new ContentValues();
                contentValues.put("exception_id", g.a(true, i));
                contentValues.put("exception_message", g.a(true, str));
                contentValues.put("exception_url", a(str2));
                return FgVoIP.U().getContentResolver().insert(com.mavenir.android.settings.a.h.a, contentValues);
            } catch (Exception e) {
                com.mavenir.android.common.q.d("ClientSettingsInterface", "ExceptionMessage: addException(): " + e);
                return null;
            }
        }

        private static String a(String str) {
            if (str == null || TextUtils.isEmpty(str.trim()) || TextUtils.equals(str, "null")) {
                return "";
            }
            return g.a(true, str);
        }

        public static int a() {
            try {
                return FgVoIP.U().getContentResolver().delete(com.mavenir.android.settings.a.h.a, null, null);
            } catch (Exception e) {
                com.mavenir.android.common.q.d("ClientSettingsInterface", "ExceptionMessage: deleteAllExceptions(): " + e);
                return -1;
            }
        }

        public static String a(int i) {
            return g.c(com.mavenir.android.settings.a.h.a, "exception_message", "", "exception_id=?", new String[]{g.a(true, i)});
        }

        public static String b(int i) {
            String a = g.c(com.mavenir.android.settings.a.h.a, "exception_url", "", "exception_id=?", new String[]{g.a(true, i)});
            if (a.equals("null") || TextUtils.isEmpty(a.trim())) {
                return null;
            }
            return a;
        }
    }

    public static class k {
        public static ContentValues a() {
            ContentValues contentValues = new ContentValues();
            contentValues.put("active_profile_id", Integer.valueOf(1));
            contentValues.put("provisioning_vers", g.a(true, -3));
            contentValues.put("provisioning_imsi", g.a(true, ""));
            contentValues.put("provisioning_deactivated", g.a(true, -1));
            contentValues.put("login_status", g.a(true, false));
            contentValues.put("save_user_data", g.a(true, true));
            contentValues.put("start_after_phone_boot", g.a(false, true));
            contentValues.put("enable_status_icon", g.a(true, true));
            contentValues.put("outgoing_calls_preference", g.a(true, 2));
            contentValues.put("enable_video_calls", g.a(true, true));
            contentValues.put("sms_request_delivery_reports", g.a(true, b.x));
            contentValues.put("mms_auto_download_messages", g.a(true, b.y));
            contentValues.put("sms_delete_old_messages", g.a(true, true));
            contentValues.put("sms_messages_per_conversation_limit", g.a(true, (int) ActivationAdapter.OP_CONFIGURATION_INITIAL));
            contentValues.put("sms_notify_incoming", g.a(true, true));
            contentValues.put("sms_notifications_ringtone", g.a(true, b.z));
            contentValues.put("enable_qos_test_ui", g.a(true, false));
            contentValues.put("emergency_numbers", g.a(true, com.mavenir.android.common.t.b.b(b.F, ",")));
            contentValues.put("msg_request_display_reports", g.a(true, b.A));
            contentValues.put("msg_allow_display_reports", g.a(true, b.B));
            contentValues.put("enable_tcp_keep_alive", g.a(true, false));
            contentValues.put("enable_messaging", g.a(true, true));
            contentValues.put("contacts_display_order", g.a(true, 0));
            contentValues.put("contacts_sort_order", g.a(true, 0));
            contentValues.put("last_root_check_time", g.a(true, "0"));
            contentValues.put("device_name", g.a(true, ""));
            contentValues.put("app_mode", g.a(true, 0));
            contentValues.put("sim_msisdn", g.a(true, ""));
            return contentValues;
        }

        public static boolean b() {
            if (p.d() == null) {
                return false;
            }
            if (p.g().length() == 0) {
                com.mavenir.android.common.q.d("ClientSettingsInterface", FgVoIP.U().getString(com.fgmicrotec.mobile.android.fgvoip.f.k.preference_account_error_private_uri_empty));
                return false;
            } else if (p.f().length() == 0) {
                com.mavenir.android.common.q.d("ClientSettingsInterface", FgVoIP.U().getString(com.fgmicrotec.mobile.android.fgvoip.f.k.preference_account_error_public_uri_empty));
                return false;
            } else if (p.h().length() == 0) {
                com.mavenir.android.common.q.d("ClientSettingsInterface", FgVoIP.U().getString(com.fgmicrotec.mobile.android.fgvoip.f.k.preference_account_error_password_empty));
                return false;
            } else {
                String d = v.d();
                if (d == null || d.length() == 0) {
                    com.mavenir.android.common.q.d("ClientSettingsInterface", FgVoIP.U().getString(com.fgmicrotec.mobile.android.fgvoip.f.k.preference_account_error_proxy_empty));
                    return false;
                }
                String[] split = p.f().split("@");
                if (split.length <= 1) {
                    com.mavenir.android.common.q.d("ClientSettingsInterface", FgVoIP.U().getString(com.fgmicrotec.mobile.android.fgvoip.f.k.preference_account_error_domain_empty));
                    return false;
                } else if (split[1].length() != 0) {
                    return true;
                } else {
                    com.mavenir.android.common.q.d("ClientSettingsInterface", FgVoIP.U().getString(com.fgmicrotec.mobile.android.fgvoip.f.k.preference_account_error_domain_empty));
                    return false;
                }
            }
        }

        public static long c() {
            return g.c(com.mavenir.android.settings.a.i.a, "active_profile_id", 1, "_id=?", new String[]{"1"});
        }

        public static int a(long j) {
            return g.d(com.mavenir.android.settings.a.i.a, "active_profile_id", j, "_id=?", new String[]{"1"});
        }

        public static int d() {
            return g.a("provisioning_vers", g.c(com.mavenir.android.settings.a.i.a, "provisioning_vers", String.valueOf(-3), "_id=?", new String[]{"1"}), -3);
        }

        public static int a(int i) {
            return g.d(com.mavenir.android.settings.a.i.a, "provisioning_vers", String.valueOf(i), "_id=?", new String[]{"1"});
        }

        public static String e() {
            return g.c(com.mavenir.android.settings.a.i.a, "provisioning_imsi", "", "_id=?", new String[]{"1"});
        }

        public static int a(String str) {
            return g.d(com.mavenir.android.settings.a.i.a, "provisioning_imsi", str, "_id=?", new String[]{"1"});
        }

        public static int f() {
            return g.a("provisioning_deactivated", g.c(com.mavenir.android.settings.a.i.a, "provisioning_deactivated", String.valueOf(-1), "_id=?", new String[]{"1"}), -1);
        }

        public static int b(int i) {
            return g.d(com.mavenir.android.settings.a.i.a, "provisioning_deactivated", String.valueOf(i), "_id=?", new String[]{"1"});
        }

        public static boolean g() {
            return g.a("save_user_data", g.c(com.mavenir.android.settings.a.i.a, "save_user_data", String.valueOf(true), "_id=?", new String[]{"1"}), Boolean.valueOf(true)).booleanValue();
        }

        public static int a(boolean z) {
            return g.d(com.mavenir.android.settings.a.i.a, "save_user_data", String.valueOf(z), "_id=?", new String[]{"1"});
        }

        public static boolean h() {
            return g.a("start_after_phone_boot", g.c(com.mavenir.android.settings.a.i.a, "start_after_phone_boot", String.valueOf(true), "_id=?", new String[]{"1"}, false), Boolean.valueOf(true)).booleanValue();
        }

        public static int b(boolean z) {
            return g.d(com.mavenir.android.settings.a.i.a, "start_after_phone_boot", String.valueOf(z), "_id=?", new String[]{"1"}, false);
        }

        public static boolean i() {
            return g.a("enable_status_icon", g.c(com.mavenir.android.settings.a.i.a, "enable_status_icon", String.valueOf(true), "_id=?", new String[]{"1"}), Boolean.valueOf(true)).booleanValue();
        }

        public static int c(boolean z) {
            return g.d(com.mavenir.android.settings.a.i.a, "enable_status_icon", String.valueOf(z), "_id=?", new String[]{"1"});
        }

        public static int j() {
            return g.a("outgoing_calls_preference", g.c(com.mavenir.android.settings.a.i.a, "outgoing_calls_preference", String.valueOf(2), "_id=?", new String[]{"1"}), 2);
        }

        public static int c(int i) {
            return g.d(com.mavenir.android.settings.a.i.a, "outgoing_calls_preference", String.valueOf(i), "_id=?", new String[]{"1"});
        }

        public static boolean k() {
            return g.a("enable_video_calls", g.c(com.mavenir.android.settings.a.i.a, "enable_video_calls", String.valueOf(true), "_id=?", new String[]{"1"}), Boolean.valueOf(true)).booleanValue();
        }

        public static boolean l() {
            return FgVoIP.U().K() ? g.a("sms_request_delivery_reports", g.c(com.mavenir.android.settings.a.i.a, "sms_request_delivery_reports", String.valueOf(b.x), "_id=?", new String[]{"1"}), Boolean.valueOf(b.x)).booleanValue() : b.x;
        }

        public static int d(boolean z) {
            return g.d(com.mavenir.android.settings.a.i.a, "sms_request_delivery_reports", String.valueOf(z), "_id=?", new String[]{"1"});
        }

        public static boolean m() {
            return g.a("mms_auto_download_messages", g.c(com.mavenir.android.settings.a.i.a, "mms_auto_download_messages", String.valueOf(b.y), "_id=?", new String[]{"1"}), Boolean.valueOf(b.y)).booleanValue();
        }

        public static int e(boolean z) {
            return g.d(com.mavenir.android.settings.a.i.a, "mms_auto_download_messages", String.valueOf(z), "_id=?", new String[]{"1"});
        }

        public static boolean n() {
            return g.a("sms_delete_old_messages", g.c(com.mavenir.android.settings.a.i.a, "sms_delete_old_messages", String.valueOf(true), "_id=?", new String[]{"1"}), Boolean.valueOf(true)).booleanValue();
        }

        public static int f(boolean z) {
            return g.d(com.mavenir.android.settings.a.i.a, "sms_delete_old_messages", String.valueOf(z), "_id=?", new String[]{"1"});
        }

        public static int o() {
            return g.a("sms_messages_per_conversation_limit", g.c(com.mavenir.android.settings.a.i.a, "sms_messages_per_conversation_limit", String.valueOf(ActivationAdapter.OP_CONFIGURATION_INITIAL), "_id=?", new String[]{"1"}), (int) ActivationAdapter.OP_CONFIGURATION_INITIAL);
        }

        public static int d(int i) {
            return g.d(com.mavenir.android.settings.a.i.a, "sms_messages_per_conversation_limit", String.valueOf(i), "_id=?", new String[]{"1"});
        }

        public static boolean p() {
            return g.a("sms_notify_incoming", g.c(com.mavenir.android.settings.a.i.a, "sms_notify_incoming", String.valueOf(true), "_id=?", new String[]{"1"}), Boolean.valueOf(true)).booleanValue();
        }

        public static int g(boolean z) {
            return g.d(com.mavenir.android.settings.a.i.a, "sms_notify_incoming", String.valueOf(z), "_id=?", new String[]{"1"});
        }

        public static String q() {
            return g.c(com.mavenir.android.settings.a.i.a, "sms_notifications_ringtone", b.z, "_id=?", new String[]{"1"});
        }

        public static int b(String str) {
            return g.d(com.mavenir.android.settings.a.i.a, "sms_notifications_ringtone", str, "_id=?", new String[]{"1"});
        }

        public static boolean r() {
            return g.a("msg_request_display_reports", g.c(com.mavenir.android.settings.a.i.a, "msg_request_display_reports", String.valueOf(b.A), "_id=?", new String[]{"1"}), Boolean.valueOf(b.A)).booleanValue();
        }

        public static int h(boolean z) {
            return g.d(com.mavenir.android.settings.a.i.a, "msg_request_display_reports", String.valueOf(z), "_id=?", new String[]{"1"});
        }

        public static boolean s() {
            return g.a("msg_allow_display_reports", g.c(com.mavenir.android.settings.a.i.a, "msg_allow_display_reports", String.valueOf(b.B), "_id=?", new String[]{"1"}), Boolean.valueOf(b.B)).booleanValue();
        }

        public static int i(boolean z) {
            return g.d(com.mavenir.android.settings.a.i.a, "msg_allow_display_reports", String.valueOf(z), "_id=?", new String[]{"1"});
        }

        public static boolean t() {
            return g.a("enable_tcp_keep_alive", g.c(com.mavenir.android.settings.a.i.a, "enable_tcp_keep_alive", String.valueOf(false), "_id=?", new String[]{"1"}), Boolean.valueOf(false)).booleanValue();
        }

        public static int j(boolean z) {
            return g.d(com.mavenir.android.settings.a.i.a, "enable_tcp_keep_alive", String.valueOf(z), "_id=?", new String[]{"1"});
        }

        public static boolean u() {
            return FgVoIP.U().G() ? g.a("enable_messaging", g.c(com.mavenir.android.settings.a.i.a, "enable_messaging", String.valueOf(true), "_id=?", new String[]{"1"}), Boolean.valueOf(true)).booleanValue() : true;
        }

        public static int k(boolean z) {
            return g.d(com.mavenir.android.settings.a.i.a, "enable_messaging", String.valueOf(z), "_id=?", new String[]{"1"});
        }

        public static long v() {
            return g.a("last_root_check_time", g.c(com.mavenir.android.settings.a.i.a, "last_root_check_time", String.valueOf(0), "_id=?", new String[]{"1"}), 0);
        }

        public static int b(long j) {
            return g.d(com.mavenir.android.settings.a.i.a, "last_root_check_time", String.valueOf(j), "_id=?", new String[]{"1"});
        }

        public static Boolean w() {
            return g.a("is_device_rooted", g.c(com.mavenir.android.settings.a.i.a, "is_device_rooted", null, "_id=?", new String[]{"1"}), null);
        }

        public static int l(boolean z) {
            return g.d(com.mavenir.android.settings.a.i.a, "is_device_rooted", String.valueOf(z), "_id=?", new String[]{"1"});
        }

        public static String x() {
            return g.c(com.mavenir.android.settings.a.i.a, "last_activated_app_vers", "", "_id=?", new String[]{"1"});
        }

        public static int c(String str) {
            return g.d(com.mavenir.android.settings.a.i.a, "last_activated_app_vers", str, "_id=?", new String[]{"1"});
        }

        public static int y() {
            return Integer.valueOf(g.c(com.mavenir.android.settings.a.i.a, "contacts_display_order", String.valueOf(0), "_id=?", new String[]{"1"})).intValue();
        }

        public static int e(int i) {
            return g.d(com.mavenir.android.settings.a.i.a, "contacts_display_order", String.valueOf(i), "_id=?", new String[]{"1"});
        }

        public static int z() {
            return Integer.valueOf(g.c(com.mavenir.android.settings.a.i.a, "contacts_sort_order", String.valueOf(0), "_id=?", new String[]{"1"})).intValue();
        }

        public static int f(int i) {
            return g.d(com.mavenir.android.settings.a.i.a, "contacts_sort_order", String.valueOf(i), "_id=?", new String[]{"1"});
        }
    }

    public static class l {
        public static ContentValues a() {
            ContentValues contentValues = new ContentValues();
            contentValues.put("lte_800_capable", g.a(true, false));
            contentValues.put("lte_800_user_activated", g.a(true, false));
            contentValues.put("lte_800_network_enabled", g.a(true, false));
            contentValues.put("lte_800_expiry", g.a(true, b.aq));
            contentValues.put("sbc_fqdns", g.a(true, ""));
            contentValues.put("sip_port", g.a(true, b.ar));
            contentValues.put("mnc", g.a(true, 0));
            contentValues.put("mcc", g.a(true, 0));
            contentValues.put("tac_min", g.a(true, 0));
            contentValues.put("tac_max", g.a(true, 0));
            return contentValues;
        }

        public static boolean b() {
            return g.a("lte_800_capable", g.c(com.mavenir.android.settings.a.j.a, "lte_800_capable", String.valueOf(false), "_id=?", new String[]{String.valueOf(k.c())}), Boolean.valueOf(false)).booleanValue();
        }

        public static int a(boolean z) {
            return g.d(com.mavenir.android.settings.a.j.a, "lte_800_capable", String.valueOf(z), "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static boolean c() {
            return g.a("lte_800_user_activated", g.c(com.mavenir.android.settings.a.j.a, "lte_800_user_activated", String.valueOf(false), "_id=?", new String[]{String.valueOf(k.c())}), Boolean.valueOf(false)).booleanValue();
        }

        public static int b(boolean z) {
            return g.d(com.mavenir.android.settings.a.j.a, "lte_800_user_activated", String.valueOf(z), "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static boolean d() {
            return g.a("lte_800_network_enabled", g.c(com.mavenir.android.settings.a.j.a, "lte_800_network_enabled", String.valueOf(false), "_id=?", new String[]{String.valueOf(k.c())}), Boolean.valueOf(false)).booleanValue();
        }

        public static int c(boolean z) {
            return g.d(com.mavenir.android.settings.a.j.a, "lte_800_network_enabled", String.valueOf(z), "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static long e() {
            return Long.parseLong(g.c(com.mavenir.android.settings.a.j.a, "lte_800_expiry", String.valueOf(b.aq), "_id=?", new String[]{String.valueOf(k.c())}));
        }

        public static int a(long j) {
            return g.d(com.mavenir.android.settings.a.j.a, "lte_800_expiry", String.valueOf(j), "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static String[] f() {
            return com.mavenir.android.common.t.b.b(g.c(com.mavenir.android.settings.a.j.a, "sbc_fqdns", String.valueOf(""), "_id=?", new String[]{String.valueOf(k.c())}), ";");
        }

        public static int a(String[] strArr) {
            return g.d(com.mavenir.android.settings.a.j.a, "sbc_fqdns", com.mavenir.android.common.t.b.b(strArr, ";"), "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static int g() {
            return g.a("sip_port", g.c(com.mavenir.android.settings.a.j.a, "sip_port", String.valueOf(b.ar), "_id=?", new String[]{String.valueOf(k.c())}), b.ar);
        }

        public static int a(int i) {
            return g.d(com.mavenir.android.settings.a.j.a, "sip_port", String.valueOf(i), "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static int h() {
            return Integer.parseInt(g.c(com.mavenir.android.settings.a.j.a, "mnc", String.valueOf(0), "_id=?", new String[]{String.valueOf(k.c())}));
        }

        public static int b(int i) {
            return g.d(com.mavenir.android.settings.a.j.a, "mnc", String.valueOf(i), "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static int i() {
            return Integer.parseInt(g.c(com.mavenir.android.settings.a.j.a, "mcc", String.valueOf(0), "_id=?", new String[]{String.valueOf(k.c())}));
        }

        public static int c(int i) {
            return g.d(com.mavenir.android.settings.a.j.a, "mcc", String.valueOf(i), "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static int j() {
            return Integer.parseInt(g.c(com.mavenir.android.settings.a.j.a, "tac_min", String.valueOf(0), "_id=?", new String[]{String.valueOf(k.c())}));
        }

        public static int d(int i) {
            return g.d(com.mavenir.android.settings.a.j.a, "tac_min", String.valueOf(i), "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static int k() {
            return Integer.parseInt(g.c(com.mavenir.android.settings.a.j.a, "tac_max", String.valueOf(0), "_id=?", new String[]{String.valueOf(k.c())}));
        }

        public static int e(int i) {
            return g.d(com.mavenir.android.settings.a.j.a, "tac_max", String.valueOf(i), "_id=?", new String[]{String.valueOf(k.c())});
        }
    }

    public static class m {
        public static ContentValues a() {
            ContentValues contentValues = new ContentValues();
            contentValues.put("media_codec_type", g.a(true, b.r));
            contentValues.put("media_codec_mode", g.a(true, b.s));
            contentValues.put("amr_format_octet_aligned", g.a(true, false));
            contentValues.put("media_overflow_mark", g.a(true, 30));
            contentValues.put("media_high_watermark", g.a(true, 20));
            contentValues.put("media_low_watermark", g.a(true, 10));
            contentValues.put("media_ati_on_off", g.a(true, 0));
            contentValues.put("media_dtmf_signalization", g.a(true, b.t));
            contentValues.put("media_alert_volume", g.a(true, b.u));
            contentValues.put("media_video_codec_type", g.a(true, 0));
            contentValues.put("media_video_size_index", g.a(true, 1));
            return contentValues;
        }

        public static ContentValues a(long j) {
            Exception e;
            Throwable th;
            ContentResolver contentResolver = FgVoIP.U().getContentResolver();
            ContentValues contentValues = new ContentValues();
            Cursor query;
            try {
                query = contentResolver.query(com.mavenir.android.settings.a.k.a, null, "_id=?", new String[]{String.valueOf(j)}, null);
                if (query != null) {
                    try {
                        if (query.moveToFirst()) {
                            contentValues.put("media_codec_type", query.getString(query.getColumnIndex("media_codec_type")));
                            contentValues.put("media_codec_mode", query.getString(query.getColumnIndex("media_codec_mode")));
                            contentValues.put("amr_format_octet_aligned", query.getString(query.getColumnIndex("amr_format_octet_aligned")));
                            contentValues.put("media_overflow_mark", query.getString(query.getColumnIndex("media_overflow_mark")));
                            contentValues.put("media_high_watermark", query.getString(query.getColumnIndex("media_high_watermark")));
                            contentValues.put("media_low_watermark", query.getString(query.getColumnIndex("media_low_watermark")));
                            contentValues.put("media_ati_on_off", query.getString(query.getColumnIndex("media_ati_on_off")));
                            contentValues.put("media_dtmf_signalization", query.getString(query.getColumnIndex("media_dtmf_signalization")));
                            contentValues.put("media_alert_volume", query.getString(query.getColumnIndex("media_alert_volume")));
                            contentValues.put("media_video_codec_type", query.getString(query.getColumnIndex("media_video_codec_type")));
                            contentValues.put("media_video_size_index", query.getString(query.getColumnIndex("media_video_size_index")));
                        }
                    } catch (Exception e2) {
                        e = e2;
                        try {
                            com.mavenir.android.common.q.c("ClientSettingsInterface", "Media: getValues(): " + e.getLocalizedMessage(), e.getCause());
                            if (!(query == null || query.isClosed())) {
                                query.close();
                            }
                            return contentValues;
                        } catch (Throwable th2) {
                            th = th2;
                            if (!(query == null || query.isClosed())) {
                                query.close();
                            }
                            throw th;
                        }
                    }
                }
                if (!(query == null || query.isClosed())) {
                    query.close();
                }
            } catch (Exception e3) {
                e = e3;
                query = null;
                com.mavenir.android.common.q.c("ClientSettingsInterface", "Media: getValues(): " + e.getLocalizedMessage(), e.getCause());
                query.close();
                return contentValues;
            } catch (Throwable th3) {
                th = th3;
                query = null;
                query.close();
                throw th;
            }
            return contentValues;
        }

        public static int b() {
            return g.a("media_codec_type", g.c(com.mavenir.android.settings.a.k.a, "media_codec_type", String.valueOf(b.r), "_id=?", new String[]{String.valueOf(k.c())}), b.r);
        }

        public static int a(int i) {
            return g.d(com.mavenir.android.settings.a.k.a, "media_codec_type", String.valueOf(i), "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static int c() {
            return d().b();
        }

        public static e d() {
            int b = b();
            e[] g = e.g();
            e a = e.a(g, b);
            if (a == null) {
                return g[0];
            }
            return a;
        }

        public static int e() {
            return g.a("media_codec_mode", g.c(com.mavenir.android.settings.a.k.a, "media_codec_mode", String.valueOf(b.s), "_id=?", new String[]{String.valueOf(k.c())}), b.s);
        }

        public static int b(int i) {
            return g.d(com.mavenir.android.settings.a.k.a, "media_codec_mode", String.valueOf(i), "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static boolean f() {
            return g.a("amr_format_octet_aligned", g.c(com.mavenir.android.settings.a.k.a, "amr_format_octet_aligned", String.valueOf(false), "_id=?", new String[]{String.valueOf(k.c())}), Boolean.valueOf(false)).booleanValue();
        }

        public static int a(boolean z) {
            return g.d(com.mavenir.android.settings.a.k.a, "amr_format_octet_aligned", String.valueOf(z), "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static int g() {
            return g.a("media_overflow_mark", g.c(com.mavenir.android.settings.a.k.a, "media_overflow_mark", String.valueOf(30), "_id=?", new String[]{String.valueOf(k.c())}), 30);
        }

        public static int c(int i) {
            return g.d(com.mavenir.android.settings.a.k.a, "media_overflow_mark", String.valueOf(i), "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static int h() {
            return g.a("media_high_watermark", g.c(com.mavenir.android.settings.a.k.a, "media_high_watermark", String.valueOf(20), "_id=?", new String[]{String.valueOf(k.c())}), 20);
        }

        public static int d(int i) {
            return g.d(com.mavenir.android.settings.a.k.a, "media_high_watermark", String.valueOf(i), "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static int i() {
            return g.a("media_low_watermark", g.c(com.mavenir.android.settings.a.k.a, "media_low_watermark", String.valueOf(10), "_id=?", new String[]{String.valueOf(k.c())}), 10);
        }

        public static int e(int i) {
            return g.d(com.mavenir.android.settings.a.k.a, "media_low_watermark", String.valueOf(i), "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static int j() {
            return g.a("media_dtmf_signalization", g.c(com.mavenir.android.settings.a.k.a, "media_dtmf_signalization", String.valueOf(b.t), "_id=?", new String[]{String.valueOf(k.c())}), b.t);
        }

        public static int f(int i) {
            return g.d(com.mavenir.android.settings.a.k.a, "media_dtmf_signalization", String.valueOf(i), "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static int k() {
            return FgVoIP.U().Q() ? g.a("media_alert_volume", g.c(com.mavenir.android.settings.a.k.a, "media_alert_volume", String.valueOf(b.u), "_id=?", new String[]{String.valueOf(k.c())}), b.u) : b.u;
        }

        public static int g(int i) {
            return g.d(com.mavenir.android.settings.a.k.a, "media_alert_volume", String.valueOf(i), "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static int l() {
            return g.a("media_video_codec_type", g.c(com.mavenir.android.settings.a.k.a, "media_video_codec_type", String.valueOf(0), "_id=?", new String[]{String.valueOf(k.c())}), 0);
        }

        public static int h(int i) {
            return g.d(com.mavenir.android.settings.a.k.a, "media_video_codec_type", String.valueOf(i), "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static int m() {
            return g.a("media_video_size_index", g.c(com.mavenir.android.settings.a.k.a, "media_video_size_index", String.valueOf(1), "_id=?", new String[]{String.valueOf(k.c())}), 1);
        }

        public static int i(int i) {
            return g.d(com.mavenir.android.settings.a.k.a, "media_video_size_index", String.valueOf(i), "_id=?", new String[]{String.valueOf(k.c())});
        }
    }

    public static class n {
        public static ContentValues a() {
            ContentValues contentValues = new ContentValues();
            contentValues.put("nat_traversal_mode", g.a(true, 0));
            contentValues.put("nat_stun_server", g.a(true, ""));
            contentValues.put("nat_stun_port", g.a(true, 0));
            contentValues.put("nat_stun_query_mode", g.a(true, 0));
            contentValues.put("nat_sip_keep_alive", g.a(true, 0));
            contentValues.put("tcp_keep_alive_idle_time", g.a(true, 7));
            contentValues.put("tcp_keep_alive_probe_time", g.a(true, 5));
            contentValues.put("tcp_keep_alive_probes_count", g.a(true, 5));
            return contentValues;
        }

        public static ContentValues a(long j) {
            Exception e;
            Throwable th;
            ContentResolver contentResolver = FgVoIP.U().getContentResolver();
            ContentValues contentValues = new ContentValues();
            Cursor query;
            try {
                query = contentResolver.query(com.mavenir.android.settings.a.l.a, null, "_id=?", new String[]{String.valueOf(j)}, null);
                if (query != null) {
                    try {
                        if (query.moveToFirst()) {
                            contentValues.put("nat_traversal_mode", query.getString(query.getColumnIndex("nat_traversal_mode")));
                            contentValues.put("nat_stun_server", query.getString(query.getColumnIndex("nat_stun_server")));
                            contentValues.put("nat_stun_port", query.getString(query.getColumnIndex("nat_stun_port")));
                            contentValues.put("nat_stun_query_mode", query.getString(query.getColumnIndex("nat_stun_query_mode")));
                            contentValues.put("nat_sip_keep_alive", query.getString(query.getColumnIndex("nat_sip_keep_alive")));
                            contentValues.put("tcp_keep_alive_idle_time", query.getString(query.getColumnIndex("tcp_keep_alive_idle_time")));
                            contentValues.put("tcp_keep_alive_probe_time", query.getString(query.getColumnIndex("tcp_keep_alive_probe_time")));
                            contentValues.put("tcp_keep_alive_probes_count", query.getString(query.getColumnIndex("tcp_keep_alive_probes_count")));
                        }
                    } catch (Exception e2) {
                        e = e2;
                        try {
                            com.mavenir.android.common.q.c("ClientSettingsInterface", "NAT: getValues(): " + e.getLocalizedMessage(), e.getCause());
                            if (!(query == null || query.isClosed())) {
                                query.close();
                            }
                            return contentValues;
                        } catch (Throwable th2) {
                            th = th2;
                            if (!(query == null || query.isClosed())) {
                                query.close();
                            }
                            throw th;
                        }
                    }
                }
                if (!(query == null || query.isClosed())) {
                    query.close();
                }
            } catch (Exception e3) {
                e = e3;
                query = null;
                com.mavenir.android.common.q.c("ClientSettingsInterface", "NAT: getValues(): " + e.getLocalizedMessage(), e.getCause());
                query.close();
                return contentValues;
            } catch (Throwable th3) {
                th = th3;
                query = null;
                query.close();
                throw th;
            }
            return contentValues;
        }

        public static int b() {
            return g.a("nat_traversal_mode", g.c(com.mavenir.android.settings.a.l.a, "nat_traversal_mode", String.valueOf(0), "_id=?", new String[]{String.valueOf(k.c())}), 0);
        }

        public static int a(int i) {
            return g.d(com.mavenir.android.settings.a.l.a, "nat_traversal_mode", String.valueOf(i), "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static String c() {
            return g.c(com.mavenir.android.settings.a.l.a, "nat_stun_server", "", "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static int a(String str) {
            return g.d(com.mavenir.android.settings.a.l.a, "nat_stun_server", str, "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static int d() {
            return g.a("nat_stun_port", g.c(com.mavenir.android.settings.a.l.a, "nat_stun_port", String.valueOf(0), "_id=?", new String[]{String.valueOf(k.c())}), 0);
        }

        public static int b(int i) {
            return g.d(com.mavenir.android.settings.a.l.a, "nat_stun_port", String.valueOf(i), "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static int c(int i) {
            return g.d(com.mavenir.android.settings.a.l.a, "nat_stun_query_mode", String.valueOf(i), "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static int e() {
            return g.a("nat_sip_keep_alive", g.c(com.mavenir.android.settings.a.l.a, "nat_sip_keep_alive", String.valueOf(0), "_id=?", new String[]{String.valueOf(k.c())}), 0);
        }

        public static int d(int i) {
            return g.d(com.mavenir.android.settings.a.l.a, "nat_sip_keep_alive", String.valueOf(i), "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static int f() {
            return g.a("tcp_keep_alive_idle_time", g.c(com.mavenir.android.settings.a.l.a, "tcp_keep_alive_idle_time", String.valueOf(7), "_id=?", new String[]{String.valueOf(k.c())}), 7);
        }

        public static int e(int i) {
            return g.d(com.mavenir.android.settings.a.l.a, "tcp_keep_alive_idle_time", String.valueOf(i), "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static int g() {
            return g.a("tcp_keep_alive_probe_time", g.c(com.mavenir.android.settings.a.l.a, "tcp_keep_alive_probe_time", String.valueOf(5), "_id=?", new String[]{String.valueOf(k.c())}), 5);
        }

        public static int f(int i) {
            return g.d(com.mavenir.android.settings.a.l.a, "tcp_keep_alive_probe_time", String.valueOf(i), "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static int h() {
            return g.a("tcp_keep_alive_probes_count", g.c(com.mavenir.android.settings.a.l.a, "tcp_keep_alive_probes_count", String.valueOf(5), "_id=?", new String[]{String.valueOf(k.c())}), 5);
        }

        public static int g(int i) {
            return g.d(com.mavenir.android.settings.a.l.a, "tcp_keep_alive_probes_count", String.valueOf(i), "_id=?", new String[]{String.valueOf(k.c())});
        }
    }

    public static class o {
        public static List<String> a() {
            Object e;
            Throwable th;
            Cursor cursor = null;
            List<String> arrayList = new ArrayList();
            Cursor query;
            try {
                query = FgVoIP.U().getContentResolver().query(com.mavenir.android.settings.a.m.a, new String[]{"number"}, "profile_id=?", new String[]{"" + p.c()}, null);
                if (query != null) {
                    while (query.moveToNext()) {
                        try {
                            arrayList.add(g.a(true, "number", query.getString(query.getColumnIndex("number"))));
                        } catch (Exception e2) {
                            e = e2;
                        }
                    }
                }
                if (query != null) {
                    query.close();
                }
            } catch (Exception e3) {
                e = e3;
                query = null;
                try {
                    com.mavenir.android.common.q.d("ClientSettingsInterface", "NativeNumbers: getNativeNumbers(): " + e);
                    if (query != null) {
                        query.close();
                    }
                    return arrayList;
                } catch (Throwable th2) {
                    th = th2;
                    cursor = query;
                    if (cursor != null) {
                        cursor.close();
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                if (cursor != null) {
                    cursor.close();
                }
                throw th;
            }
            return arrayList;
        }

        public static int a(String[] strArr) {
            int i = 0;
            if (strArr == null || strArr.length <= 0) {
                return 0;
            }
            long c = p.c();
            ContentValues[] contentValuesArr = new ContentValues[strArr.length];
            while (i < strArr.length) {
                contentValuesArr[i] = new ContentValues();
                contentValuesArr[i].put("profile_id", Long.valueOf(c));
                contentValuesArr[i].put("number", g.a(true, strArr[i]));
                i++;
            }
            b();
            return g.b(com.mavenir.android.settings.a.m.a, contentValuesArr);
        }

        public static int b() {
            return g.b(com.mavenir.android.settings.a.m.a, "profile_id=?", new String[]{"" + p.c()});
        }
    }

    public static class p {
        public static ContentValues a() {
            ContentValues contentValues = new ContentValues();
            contentValues.put("profile_name", g.a(true, "Default"));
            contentValues.put("display_name", g.a(true, "Default"));
            contentValues.put("impu", g.a(true, "sip:"));
            contentValues.put("impi", g.a(true, ""));
            contentValues.put("password", g.a(true, ""));
            contentValues.put("hashed_msisdn", g.a(true, ""));
            contentValues.put("double_hashed_msisdn", g.a(true, ""));
            contentValues.put("msisdn", g.a(true, ""));
            contentValues.put("tac", g.a(true, ""));
            contentValues.put("svn", g.a(true, ""));
            contentValues.put("network_imsi", g.a(true, ""));
            contentValues.put("cc_fqdn", g.a(true, ""));
            contentValues.put("uuid", g.a(true, "0"));
            contentValues.put("reg_id", g.a(true, 0));
            contentValues.put("first_name", g.a(true, ""));
            contentValues.put("last_name", g.a(true, ""));
            contentValues.put("email", g.a(true, ""));
            contentValues.put("network_imei", g.a(true, ""));
            return contentValues;
        }

        public static ContentValues a(long j) {
            Exception e;
            Throwable th;
            ContentResolver contentResolver = FgVoIP.U().getContentResolver();
            ContentValues contentValues = new ContentValues();
            Cursor query;
            try {
                query = contentResolver.query(com.mavenir.android.settings.a.n.a, null, "_id=?", new String[]{String.valueOf(j)}, null);
                if (query != null) {
                    try {
                        if (query.moveToFirst()) {
                            contentValues.put("profile_name", query.getString(query.getColumnIndex("profile_name")));
                            contentValues.put("display_name", query.getString(query.getColumnIndex("display_name")));
                            contentValues.put("impu", query.getString(query.getColumnIndex("impu")));
                            contentValues.put("impi", query.getString(query.getColumnIndex("impi")));
                            contentValues.put("password", query.getString(query.getColumnIndex("password")));
                            contentValues.put("hashed_msisdn", query.getString(query.getColumnIndex("hashed_msisdn")));
                            contentValues.put("double_hashed_msisdn", query.getString(query.getColumnIndex("double_hashed_msisdn")));
                            contentValues.put("msisdn", query.getString(query.getColumnIndex("msisdn")));
                            contentValues.put("tac", query.getString(query.getColumnIndex("tac")));
                            contentValues.put("svn", query.getString(query.getColumnIndex("svn")));
                            contentValues.put("network_imsi", query.getString(query.getColumnIndex("network_imsi")));
                            contentValues.put("cc_fqdn", query.getString(query.getColumnIndex("cc_fqdn")));
                            contentValues.put("uuid", query.getString(query.getColumnIndex("uuid")));
                            contentValues.put("reg_id", query.getString(query.getColumnIndex("reg_id")));
                            contentValues.put("first_name", query.getString(query.getColumnIndex("first_name")));
                            contentValues.put("last_name", query.getString(query.getColumnIndex("last_name")));
                            contentValues.put("email", query.getString(query.getColumnIndex("email")));
                            contentValues.put("network_imei", query.getString(query.getColumnIndex("network_imei")));
                        }
                    } catch (Exception e2) {
                        e = e2;
                        try {
                            com.mavenir.android.common.q.c("ClientSettingsInterface", "Profile: getValues(): " + e.getLocalizedMessage(), e.getCause());
                            if (!(query == null || query.isClosed())) {
                                query.close();
                            }
                            return contentValues;
                        } catch (Throwable th2) {
                            th = th2;
                            query.close();
                            throw th;
                        }
                    }
                }
                if (!(query == null || query.isClosed())) {
                    query.close();
                }
            } catch (Exception e3) {
                e = e3;
                query = null;
                com.mavenir.android.common.q.c("ClientSettingsInterface", "Profile: getValues(): " + e.getLocalizedMessage(), e.getCause());
                query.close();
                return contentValues;
            } catch (Throwable th3) {
                th = th3;
                query = null;
                if (!(query == null || query.isClosed())) {
                    query.close();
                }
                throw th;
            }
            return contentValues;
        }

        public static List<String> b() {
            Exception e;
            Throwable th;
            List<String> arrayList = new ArrayList();
            ContentResolver contentResolver = FgVoIP.U().getContentResolver();
            Cursor query;
            try {
                query = contentResolver.query(com.mavenir.android.settings.a.n.a, new String[]{"profile_name"}, null, null, null);
                if (query != null) {
                    try {
                        if (query.getCount() > 0) {
                            while (query.moveToNext()) {
                                arrayList.add(g.a(true, "profile_name", query.getString(query.getColumnIndex("profile_name"))));
                            }
                        }
                    } catch (Exception e2) {
                        e = e2;
                        try {
                            com.mavenir.android.common.q.c("ClientSettingsInterface", "Profile: getAllProfileNames(): " + e.getLocalizedMessage(), e.getCause());
                            if (!(query == null || query.isClosed())) {
                                query.close();
                            }
                            return arrayList;
                        } catch (Throwable th2) {
                            th = th2;
                            if (!(query == null || query.isClosed())) {
                                query.close();
                            }
                            throw th;
                        }
                    }
                }
                if (!(query == null || query.isClosed())) {
                    query.close();
                }
            } catch (Exception e3) {
                e = e3;
                query = null;
                com.mavenir.android.common.q.c("ClientSettingsInterface", "Profile: getAllProfileNames(): " + e.getLocalizedMessage(), e.getCause());
                query.close();
                return arrayList;
            } catch (Throwable th3) {
                th = th3;
                query = null;
                query.close();
                throw th;
            }
            return arrayList;
        }

        public static long a(Uri uri) {
            return g.c(uri, "_id", 0, null, null);
        }

        public static long c() {
            return g.c(com.mavenir.android.settings.a.n.a, "_id", 1, "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static String d() {
            return g.c(com.mavenir.android.settings.a.n.a, "profile_name", "Default", "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static int a(String str) {
            return g.d(com.mavenir.android.settings.a.n.a, "profile_name", str, "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static String e() {
            return g.c(com.mavenir.android.settings.a.n.a, "display_name", "Default", "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static int b(String str) {
            return g.d(com.mavenir.android.settings.a.n.a, "display_name", str, "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static String f() {
            return g.c(com.mavenir.android.settings.a.n.a, "impu", "sip:", "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static int c(String str) {
            int b = g.d(com.mavenir.android.settings.a.n.a, "impu", str, "_id=?", new String[]{String.valueOf(k.c())});
            if (b > 0) {
                c.b(c.a(str));
                c.d(c.c(str));
                h(com.mavenir.android.common.t.f.f(str));
            }
            return b;
        }

        public static String g() {
            return g.c(com.mavenir.android.settings.a.n.a, "impi", "", "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static int d(String str) {
            return g.d(com.mavenir.android.settings.a.n.a, "impi", str, "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static String h() {
            return g.c(com.mavenir.android.settings.a.n.a, "password", "", "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static int e(String str) {
            return g.d(com.mavenir.android.settings.a.n.a, "password", str, "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static String i() {
            return g.c(com.mavenir.android.settings.a.n.a, "hashed_msisdn", "", "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static int f(String str) {
            return g.d(com.mavenir.android.settings.a.n.a, "hashed_msisdn", str, "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static String j() {
            return g.c(com.mavenir.android.settings.a.n.a, "double_hashed_msisdn", "", "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static int g(String str) {
            return g.d(com.mavenir.android.settings.a.n.a, "double_hashed_msisdn", str, "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static String k() {
            return g.c(com.mavenir.android.settings.a.n.a, "msisdn", "", "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static int h(String str) {
            return g.d(com.mavenir.android.settings.a.n.a, "msisdn", str, "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static String l() {
            return g.c(com.mavenir.android.settings.a.n.a, "tac", "", "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static int i(String str) {
            return g.d(com.mavenir.android.settings.a.n.a, "tac", str, "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static String m() {
            return g.c(com.mavenir.android.settings.a.n.a, "svn", "", "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static int j(String str) {
            return g.d(com.mavenir.android.settings.a.n.a, "svn", str, "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static String n() {
            return g.c(com.mavenir.android.settings.a.n.a, "network_imsi", "", "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static int k(String str) {
            return g.d(com.mavenir.android.settings.a.n.a, "network_imsi", str, "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static String o() {
            return g.c(com.mavenir.android.settings.a.n.a, "network_imei", "", "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static int l(String str) {
            return g.d(com.mavenir.android.settings.a.n.a, "network_imei", str, "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static String p() {
            return g.c(com.mavenir.android.settings.a.n.a, "cc_fqdn", "", "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static int m(String str) {
            return g.d(com.mavenir.android.settings.a.n.a, "cc_fqdn", str, "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static String q() {
            return g.c(com.mavenir.android.settings.a.n.a, "uuid", "0", "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static int n(String str) {
            if (str == null) {
                str = "";
            }
            return g.d(com.mavenir.android.settings.a.n.a, "uuid", str, "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static int r() {
            return g.a("reg_id", g.c(com.mavenir.android.settings.a.n.a, "reg_id", String.valueOf(0), "_id=?", new String[]{String.valueOf(k.c())}), 0);
        }

        public static int a(int i) {
            return g.d(com.mavenir.android.settings.a.n.a, "reg_id", String.valueOf(i), "_id=?", new String[]{String.valueOf(k.c())});
        }
    }

    public static class q {
        public static ContentValues a() {
            ContentValues contentValues = new ContentValues();
            contentValues.put("validity", g.a(true, 0));
            contentValues.put("configuration_time", g.a(true, 0));
            contentValues.put("min_wifi_threshold", g.a(true, -85));
            contentValues.put("wifi_hysteresis_timer", g.a(true, 3));
            contentValues.put("next_sbc_timer", g.a(true, 5));
            contentValues.put("hs_lost_timer", g.a(true, 15));
            contentValues.put("stay_alive_timer", g.a(true, 0));
            contentValues.put("retry_login_timer", g.a(true, 0));
            contentValues.put("no_media_timer", g.a(true, 30));
            contentValues.put("wifi_qos_threshold", g.a(true, -85));
            contentValues.put("max_jitter", g.a(true, 370));
            contentValues.put("max_round_trip_delay", g.a(true, 1000));
            contentValues.put("rtp_max_cumulative_loss", g.a(true, (int) ActivationAdapter.REASON_NEW_CONFIG_DATA));
            contentValues.put("rtp_max_fraction_loss", g.a(true, 30));
            contentValues.put("reg_no_resp_retry_tmr", g.a(true, 5));
            contentValues.put("tls_cert_revoke_tmr", g.a(true, 24));
            contentValues.put("tls_session_cache_tmr", g.a(true, 24));
            contentValues.put("inv_early_no_resp_timeout", g.a(true, 10));
            contentValues.put("codecs", g.a(true, ""));
            contentValues.put("provisioning_fqdn", g.a(true, b.G));
            contentValues.put("provisioning_fqdn_2", g.a(true, b.H));
            contentValues.put("public_ip_service_url", g.a(true, b.J));
            contentValues.put("back_off_timer_list", g.a(true, "5;10;15;60"));
            contentValues.put("user_deactivation_message", g.a(true, b.I));
            contentValues.put("acceptable_time_difference", g.a(true, 0));
            return contentValues;
        }

        public static ContentValues a(long j) {
            Exception e;
            Throwable th;
            ContentResolver contentResolver = FgVoIP.U().getContentResolver();
            ContentValues contentValues = new ContentValues();
            Cursor query;
            try {
                query = contentResolver.query(com.mavenir.android.settings.a.o.a, null, "_id=?", new String[]{String.valueOf(j)}, null);
                if (query != null) {
                    try {
                        if (query.moveToFirst()) {
                            contentValues.put("validity", query.getString(query.getColumnIndex("validity")));
                            contentValues.put("configuration_time", query.getString(query.getColumnIndex("configuration_time")));
                            contentValues.put("min_wifi_threshold", query.getString(query.getColumnIndex("min_wifi_threshold")));
                            contentValues.put("wifi_hysteresis_timer", query.getString(query.getColumnIndex("wifi_hysteresis_timer")));
                            contentValues.put("next_sbc_timer", query.getString(query.getColumnIndex("next_sbc_timer")));
                            contentValues.put("hs_lost_timer", query.getString(query.getColumnIndex("hs_lost_timer")));
                            contentValues.put("stay_alive_timer", query.getString(query.getColumnIndex("stay_alive_timer")));
                            contentValues.put("retry_login_timer", query.getString(query.getColumnIndex("retry_login_timer")));
                            contentValues.put("no_media_timer", query.getString(query.getColumnIndex("no_media_timer")));
                            contentValues.put("wifi_qos_threshold", query.getString(query.getColumnIndex("wifi_qos_threshold")));
                            contentValues.put("max_jitter", query.getString(query.getColumnIndex("max_jitter")));
                            contentValues.put("max_round_trip_delay", query.getString(query.getColumnIndex("max_round_trip_delay")));
                            contentValues.put("rtp_max_cumulative_loss", query.getString(query.getColumnIndex("rtp_max_cumulative_loss")));
                            contentValues.put("rtp_max_fraction_loss", query.getString(query.getColumnIndex("rtp_max_fraction_loss")));
                            contentValues.put("tls_cert_revoke_tmr", query.getString(query.getColumnIndex("tls_cert_revoke_tmr")));
                            contentValues.put("tls_session_cache_tmr", query.getString(query.getColumnIndex("tls_session_cache_tmr")));
                            contentValues.put("reg_no_resp_retry_tmr", query.getString(query.getColumnIndex("reg_no_resp_retry_tmr")));
                            contentValues.put("inv_early_no_resp_timeout", query.getString(query.getColumnIndex("inv_early_no_resp_timeout")));
                            contentValues.put("user_deactivation_message", query.getString(query.getColumnIndex("user_deactivation_message")));
                            contentValues.put("codecs", query.getString(query.getColumnIndex("codecs")));
                            contentValues.put("provisioning_fqdn", query.getString(query.getColumnIndex("provisioning_fqdn")));
                            contentValues.put("provisioning_fqdn_2", query.getString(query.getColumnIndex("provisioning_fqdn_2")));
                            contentValues.put("public_ip_service_url", query.getString(query.getColumnIndex("public_ip_service_url")));
                            contentValues.put("back_off_timer_list", query.getString(query.getColumnIndex("back_off_timer_list")));
                            contentValues.put("acceptable_time_difference", query.getString(query.getColumnIndex("acceptable_time_difference")));
                        }
                    } catch (Exception e2) {
                        e = e2;
                        try {
                            com.mavenir.android.common.q.c("ClientSettingsInterface", "QoS: getValues(): " + e.getLocalizedMessage(), e.getCause());
                            if (!(query == null || query.isClosed())) {
                                query.close();
                            }
                            return contentValues;
                        } catch (Throwable th2) {
                            th = th2;
                            if (!(query == null || query.isClosed())) {
                                query.close();
                            }
                            throw th;
                        }
                    }
                }
                if (!(query == null || query.isClosed())) {
                    query.close();
                }
            } catch (Exception e3) {
                e = e3;
                query = null;
                com.mavenir.android.common.q.c("ClientSettingsInterface", "QoS: getValues(): " + e.getLocalizedMessage(), e.getCause());
                query.close();
                return contentValues;
            } catch (Throwable th3) {
                th = th3;
                query = null;
                query.close();
                throw th;
            }
            return contentValues;
        }

        public static int b() {
            return g.a("validity", g.c(com.mavenir.android.settings.a.o.a, "validity", String.valueOf(0), "_id=?", new String[]{String.valueOf(k.c())}), 0);
        }

        public static int a(int i) {
            return g.d(com.mavenir.android.settings.a.o.a, "validity", String.valueOf(i), "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static long c() {
            return g.a("configuration_time", g.c(com.mavenir.android.settings.a.o.a, "configuration_time", String.valueOf(0), "_id=?", new String[]{String.valueOf(k.c())}), 0);
        }

        public static int b(long j) {
            return g.d(com.mavenir.android.settings.a.o.a, "configuration_time", String.valueOf(j), "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static int d() {
            return g.a("min_wifi_threshold", g.c(com.mavenir.android.settings.a.o.a, "min_wifi_threshold", String.valueOf(-85), "_id=?", new String[]{String.valueOf(k.c())}), -85);
        }

        public static int b(int i) {
            return g.d(com.mavenir.android.settings.a.o.a, "min_wifi_threshold", String.valueOf(i), "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static int e() {
            return g.a("wifi_hysteresis_timer", g.c(com.mavenir.android.settings.a.o.a, "wifi_hysteresis_timer", String.valueOf(3), "_id=?", new String[]{String.valueOf(k.c())}), 3);
        }

        public static int c(int i) {
            return g.d(com.mavenir.android.settings.a.o.a, "wifi_hysteresis_timer", String.valueOf(i), "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static int f() {
            return g.a("next_sbc_timer", g.c(com.mavenir.android.settings.a.o.a, "next_sbc_timer", String.valueOf(5), "_id=?", new String[]{String.valueOf(k.c())}), 5);
        }

        public static int d(int i) {
            return g.d(com.mavenir.android.settings.a.o.a, "next_sbc_timer", String.valueOf(i), "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static int g() {
            return g.a("hs_lost_timer", g.c(com.mavenir.android.settings.a.o.a, "hs_lost_timer", String.valueOf(15), "_id=?", new String[]{String.valueOf(k.c())}), 15);
        }

        public static int e(int i) {
            return g.d(com.mavenir.android.settings.a.o.a, "hs_lost_timer", String.valueOf(i), "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static int f(int i) {
            return g.d(com.mavenir.android.settings.a.o.a, "stay_alive_timer", String.valueOf(i), "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static int g(int i) {
            return g.d(com.mavenir.android.settings.a.o.a, "retry_login_timer", String.valueOf(i), "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static int h() {
            return g.a("wifi_qos_threshold", g.c(com.mavenir.android.settings.a.o.a, "wifi_qos_threshold", String.valueOf(-85), "_id=?", new String[]{String.valueOf(k.c())}), -85);
        }

        public static int h(int i) {
            return g.d(com.mavenir.android.settings.a.o.a, "wifi_qos_threshold", String.valueOf(i), "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static int i() {
            return g.a("max_jitter", g.c(com.mavenir.android.settings.a.o.a, "max_jitter", String.valueOf(370), "_id=?", new String[]{String.valueOf(k.c())}), 370);
        }

        public static int i(int i) {
            return g.d(com.mavenir.android.settings.a.o.a, "max_jitter", String.valueOf(i), "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static int j() {
            return g.a("max_round_trip_delay", g.c(com.mavenir.android.settings.a.o.a, "max_round_trip_delay", String.valueOf(1000), "_id=?", new String[]{String.valueOf(k.c())}), 1000);
        }

        public static int j(int i) {
            return g.d(com.mavenir.android.settings.a.o.a, "max_round_trip_delay", String.valueOf(i), "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static int k() {
            return g.a("rtp_max_cumulative_loss", g.c(com.mavenir.android.settings.a.o.a, "rtp_max_cumulative_loss", String.valueOf(ActivationAdapter.REASON_NEW_CONFIG_DATA), "_id=?", new String[]{String.valueOf(k.c())}), (int) ActivationAdapter.REASON_NEW_CONFIG_DATA);
        }

        public static int k(int i) {
            return g.d(com.mavenir.android.settings.a.o.a, "rtp_max_cumulative_loss", String.valueOf(i), "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static int l() {
            return g.a("rtp_max_fraction_loss", g.c(com.mavenir.android.settings.a.o.a, "rtp_max_fraction_loss", String.valueOf(30), "_id=?", new String[]{String.valueOf(k.c())}), 30);
        }

        public static int l(int i) {
            return g.d(com.mavenir.android.settings.a.o.a, "rtp_max_fraction_loss", String.valueOf(i), "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static int m() {
            return g.a("tls_cert_revoke_tmr", g.c(com.mavenir.android.settings.a.o.a, "tls_cert_revoke_tmr", String.valueOf(24), "_id=?", new String[]{String.valueOf(k.c())}), 24);
        }

        public static int m(int i) {
            return g.d(com.mavenir.android.settings.a.o.a, "tls_cert_revoke_tmr", String.valueOf(i), "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static int n() {
            return g.a("tls_session_cache_tmr", g.c(com.mavenir.android.settings.a.o.a, "tls_session_cache_tmr", String.valueOf(24), "_id=?", new String[]{String.valueOf(k.c())}), 24);
        }

        public static int n(int i) {
            return g.d(com.mavenir.android.settings.a.o.a, "tls_session_cache_tmr", String.valueOf(i), "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static int o() {
            return g.a("reg_no_resp_retry_tmr", g.c(com.mavenir.android.settings.a.o.a, "reg_no_resp_retry_tmr", String.valueOf(5), "_id=?", new String[]{String.valueOf(k.c())}), 5);
        }

        public static int o(int i) {
            return g.d(com.mavenir.android.settings.a.o.a, "reg_no_resp_retry_tmr", String.valueOf(i), "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static int p() {
            return g.a("inv_early_no_resp_timeout", g.c(com.mavenir.android.settings.a.o.a, "inv_early_no_resp_timeout", String.valueOf(10), "_id=?", new String[]{String.valueOf(k.c())}), 10);
        }

        public static int p(int i) {
            return g.d(com.mavenir.android.settings.a.o.a, "inv_early_no_resp_timeout", String.valueOf(i), "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static String q() {
            return g.c(com.mavenir.android.settings.a.o.a, "public_ip_service_url", b.J, "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static int a(String str) {
            return g.d(com.mavenir.android.settings.a.o.a, "public_ip_service_url", str, "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static String[] r() {
            return com.mavenir.android.common.t.b.b(g.c(com.mavenir.android.settings.a.o.a, "codecs", "", "_id=?", new String[]{String.valueOf(k.c())}), ";");
        }

        public static int a(String[] strArr) {
            return g.d(com.mavenir.android.settings.a.o.a, "codecs", com.mavenir.android.common.t.b.b(strArr, ";"), "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static int[] s() {
            return com.mavenir.android.common.t.b.c(g.c(com.mavenir.android.settings.a.o.a, "back_off_timer_list", "5;10;15;60", "_id=?", new String[]{String.valueOf(k.c())}), ";");
        }

        public static int a(int[] iArr) {
            return g.d(com.mavenir.android.settings.a.o.a, "back_off_timer_list", com.mavenir.android.common.t.b.a(iArr, ";"), "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static String t() {
            return g.c(com.mavenir.android.settings.a.o.a, "user_deactivation_message", b.I, "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static int b(String str) {
            return g.d(com.mavenir.android.settings.a.o.a, "user_deactivation_message", str, "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static String u() {
            return g.c(com.mavenir.android.settings.a.o.a, "provisioning_fqdn", b.G, "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static int c(String str) {
            return g.d(com.mavenir.android.settings.a.o.a, "provisioning_fqdn", str, "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static String v() {
            return g.c(com.mavenir.android.settings.a.o.a, "provisioning_fqdn_2", b.H, "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static int d(String str) {
            return g.d(com.mavenir.android.settings.a.o.a, "provisioning_fqdn_2", str, "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static int w() {
            return g.a("acceptable_time_difference", g.c(com.mavenir.android.settings.a.o.a, "acceptable_time_difference", String.valueOf(0), "_id=?", new String[]{String.valueOf(k.c())}), 0);
        }

        public static int q(int i) {
            return g.d(com.mavenir.android.settings.a.o.a, "acceptable_time_difference", String.valueOf(i), "_id=?", new String[]{String.valueOf(k.c())});
        }
    }

    public static class r {
        public static Uri a(int i, int i2, int i3, int i4, int i5, int i6) {
            try {
                ContentValues contentValues = new ContentValues();
                contentValues.put("wifi_signal_strength", Integer.valueOf(i));
                contentValues.put("round_trip_delay", Integer.valueOf(i2));
                contentValues.put("jitter", Integer.valueOf(i3));
                contentValues.put("cumulative_loss", Integer.valueOf(i4));
                contentValues.put("fraction_loss", Integer.valueOf(i5));
                contentValues.put("timestamp", Integer.valueOf(i6));
                return FgVoIP.U().getContentResolver().insert(com.mavenir.android.settings.a.p.a, contentValues);
            } catch (Exception e) {
                com.mavenir.android.common.q.c("ClientSettingsInterface", "QosStats: addValues(): " + e.getLocalizedMessage(), e.getCause());
                return null;
            }
        }

        public static void a() {
            g.b(com.mavenir.android.settings.a.p.a, null, null);
        }
    }

    public static class s {
        public static ContentValues a() {
            ContentValues contentValues = new ContentValues();
            contentValues.put("rtp_local_port", g.a(true, 0));
            contentValues.put("rtp_decoder_timeout", g.a(true, 30));
            contentValues.put("rtp_use_rtcp", g.a(true, b.k));
            contentValues.put("rtp_rtcp_period", g.a(true, 0));
            contentValues.put("srtp_mode", g.a(true, b.l));
            contentValues.put("srtp_encryption_type", g.a(true, 0));
            contentValues.put("srtp_auth_type", g.a(true, 1));
            return contentValues;
        }

        public static ContentValues a(long j) {
            Exception e;
            Throwable th;
            ContentResolver contentResolver = FgVoIP.U().getContentResolver();
            ContentValues contentValues = new ContentValues();
            Cursor query;
            try {
                query = contentResolver.query(com.mavenir.android.settings.a.s.a, null, "_id=?", new String[]{String.valueOf(j)}, null);
                if (query != null) {
                    try {
                        if (query.moveToFirst()) {
                            contentValues.put("rtp_local_port", query.getString(query.getColumnIndex("rtp_local_port")));
                            contentValues.put("rtp_decoder_timeout", query.getString(query.getColumnIndex("rtp_decoder_timeout")));
                            contentValues.put("rtp_use_rtcp", query.getString(query.getColumnIndex("rtp_use_rtcp")));
                            contentValues.put("rtp_rtcp_period", query.getString(query.getColumnIndex("rtp_rtcp_period")));
                            contentValues.put("srtp_mode", query.getString(query.getColumnIndex("srtp_mode")));
                            contentValues.put("srtp_encryption_type", query.getString(query.getColumnIndex("srtp_encryption_type")));
                            contentValues.put("srtp_auth_type", query.getString(query.getColumnIndex("srtp_auth_type")));
                        }
                    } catch (Exception e2) {
                        e = e2;
                        try {
                            com.mavenir.android.common.q.c("ClientSettingsInterface", "RTP: getValues(): " + e.getLocalizedMessage(), e.getCause());
                            if (!(query == null || query.isClosed())) {
                                query.close();
                            }
                            return contentValues;
                        } catch (Throwable th2) {
                            th = th2;
                            if (!(query == null || query.isClosed())) {
                                query.close();
                            }
                            throw th;
                        }
                    }
                }
                if (!(query == null || query.isClosed())) {
                    query.close();
                }
            } catch (Exception e3) {
                e = e3;
                query = null;
                com.mavenir.android.common.q.c("ClientSettingsInterface", "RTP: getValues(): " + e.getLocalizedMessage(), e.getCause());
                query.close();
                return contentValues;
            } catch (Throwable th3) {
                th = th3;
                query = null;
                query.close();
                throw th;
            }
            return contentValues;
        }

        public static int b() {
            return g.a("rtp_local_port", g.c(com.mavenir.android.settings.a.s.a, "rtp_local_port", String.valueOf(0), "_id=?", new String[]{String.valueOf(k.c())}), 0);
        }

        public static int a(int i) {
            return g.d(com.mavenir.android.settings.a.s.a, "rtp_local_port", String.valueOf(i), "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static int c() {
            return g.a("rtp_decoder_timeout", g.c(com.mavenir.android.settings.a.s.a, "rtp_decoder_timeout", String.valueOf(30), "_id=?", new String[]{String.valueOf(k.c())}), 30);
        }

        public static int b(int i) {
            return g.d(com.mavenir.android.settings.a.s.a, "rtp_decoder_timeout", String.valueOf(i), "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static boolean d() {
            return g.a("rtp_use_rtcp", g.c(com.mavenir.android.settings.a.s.a, "rtp_use_rtcp", String.valueOf(b.k), "_id=?", new String[]{String.valueOf(k.c())}), Boolean.valueOf(b.k)).booleanValue();
        }

        public static int a(boolean z) {
            return g.d(com.mavenir.android.settings.a.s.a, "rtp_use_rtcp", String.valueOf(z), "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static int e() {
            return g.a("rtp_rtcp_period", g.c(com.mavenir.android.settings.a.s.a, "rtp_rtcp_period", String.valueOf(0), "_id=?", new String[]{String.valueOf(k.c())}), 0);
        }

        public static boolean f() {
            return g.a("srtp_mode", g.c(com.mavenir.android.settings.a.s.a, "srtp_mode", String.valueOf(b.l), "_id=?", new String[]{String.valueOf(k.c())}), Boolean.valueOf(b.l)).booleanValue();
        }

        public static int b(boolean z) {
            return g.d(com.mavenir.android.settings.a.s.a, "srtp_mode", String.valueOf(z), "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static int g() {
            return g.a("srtp_encryption_type", g.c(com.mavenir.android.settings.a.s.a, "srtp_encryption_type", String.valueOf(0), "_id=?", new String[]{String.valueOf(k.c())}), 0);
        }

        public static int c(int i) {
            return g.d(com.mavenir.android.settings.a.s.a, "srtp_encryption_type", String.valueOf(i), "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static int h() {
            return g.a("srtp_auth_type", g.c(com.mavenir.android.settings.a.s.a, "srtp_auth_type", String.valueOf(1), "_id=?", new String[]{String.valueOf(k.c())}), 1);
        }

        public static int d(int i) {
            return g.d(com.mavenir.android.settings.a.s.a, "srtp_auth_type", String.valueOf(i), "_id=?", new String[]{String.valueOf(k.c())});
        }
    }

    public static class t implements BaseColumns {
        public static ContentValues a() {
            ContentValues contentValues = new ContentValues();
            contentValues.put("group_chat_factory_uri", g.a(true, b.Z));
            return contentValues;
        }

        public static ContentValues a(long j) {
            Exception e;
            Throwable th;
            ContentResolver contentResolver = FgVoIP.U().getContentResolver();
            ContentValues contentValues = new ContentValues();
            Cursor query;
            try {
                query = contentResolver.query(com.mavenir.android.settings.a.q.a, null, "_id=?", new String[]{String.valueOf(j)}, null);
                if (query != null) {
                    try {
                        if (query.moveToFirst()) {
                            contentValues.put("group_chat_factory_uri", query.getString(query.getColumnIndex("group_chat_factory_uri")));
                        }
                    } catch (Exception e2) {
                        e = e2;
                        try {
                            com.mavenir.android.common.q.c("ClientSettingsInterface", "RcsChatFTSettings: getValues(): " + e.getLocalizedMessage(), e.getCause());
                            if (!(query == null || query.isClosed())) {
                                query.close();
                            }
                            return contentValues;
                        } catch (Throwable th2) {
                            th = th2;
                            if (!(query == null || query.isClosed())) {
                                query.close();
                            }
                            throw th;
                        }
                    }
                }
                if (!(query == null || query.isClosed())) {
                    query.close();
                }
            } catch (Exception e3) {
                e = e3;
                query = null;
                com.mavenir.android.common.q.c("ClientSettingsInterface", "RcsChatFTSettings: getValues(): " + e.getLocalizedMessage(), e.getCause());
                query.close();
                return contentValues;
            } catch (Throwable th3) {
                th = th3;
                query = null;
                query.close();
                throw th;
            }
            return contentValues;
        }
    }

    public static class u {
        public static ContentValues a() {
            ContentValues contentValues = new ContentValues();
            contentValues.put("xdms_root_uri", g.a(true, "http://xdms.dev.mavenir.lab:80"));
            contentValues.put("xdms_proxy_address", g.a(true, "72.16.232.126"));
            contentValues.put("xdms_proxy_port", g.a(true, 8888));
            contentValues.put("presence_enabled", g.a(true, false));
            return contentValues;
        }

        public static ContentValues a(long j) {
            Exception e;
            Throwable th;
            ContentResolver contentResolver = FgVoIP.U().getContentResolver();
            ContentValues contentValues = new ContentValues();
            Cursor query;
            try {
                query = contentResolver.query(com.mavenir.android.settings.a.r.a, null, "_id=?", new String[]{String.valueOf(j)}, null);
                if (query != null) {
                    try {
                        if (query.moveToFirst()) {
                            contentValues.put("xdms_root_uri", query.getString(query.getColumnIndex("xdms_root_uri")));
                            contentValues.put("xdms_proxy_address", query.getString(query.getColumnIndex("xdms_proxy_address")));
                            contentValues.put("xdms_proxy_port", query.getString(query.getColumnIndex("xdms_proxy_port")));
                            contentValues.put("presence_enabled", query.getString(query.getColumnIndex("presence_enabled")));
                        }
                    } catch (Exception e2) {
                        e = e2;
                        try {
                            com.mavenir.android.common.q.c("ClientSettingsInterface", "RcsPresence: getValues(): " + e.getLocalizedMessage(), e.getCause());
                            if (!(query == null || query.isClosed())) {
                                query.close();
                            }
                            return contentValues;
                        } catch (Throwable th2) {
                            th = th2;
                            query.close();
                            throw th;
                        }
                    }
                }
                if (!(query == null || query.isClosed())) {
                    query.close();
                }
            } catch (Exception e3) {
                e = e3;
                query = null;
                com.mavenir.android.common.q.c("ClientSettingsInterface", "RcsPresence: getValues(): " + e.getLocalizedMessage(), e.getCause());
                query.close();
                return contentValues;
            } catch (Throwable th3) {
                th = th3;
                query = null;
                if (!(query == null || query.isClosed())) {
                    query.close();
                }
                throw th;
            }
            return contentValues;
        }
    }

    public static class v {
        public static ContentValues a() {
            ContentValues contentValues = new ContentValues();
            contentValues.put("sip_proxy", g.a(true, b.c));
            contentValues.put("sip_port", g.a(true, b.d));
            contentValues.put("sbc_fqdns", g.a(true, ""));
            contentValues.put("sip_local_port", g.a(true, 0));
            contentValues.put("sip_reg_exp_time", g.a(true, 900));
            contentValues.put("sip_uri_format", g.a(true, b.e));
            contentValues.put("sip_transport", g.a(true, b.f));
            contentValues.put("sip_route", g.a(true, true));
            contentValues.put("sip_user_agent", g.a(true, ""));
            contentValues.put("session_expires_time", g.a(true, b.g));
            contentValues.put("min_session_expires_time", g.a(true, b.h));
            contentValues.put("session_refresher", g.a(true, b.i));
            contentValues.put("soc_family", g.a(true, b.j));
            return contentValues;
        }

        public static ContentValues a(long j) {
            Exception e;
            Throwable th;
            ContentResolver contentResolver = FgVoIP.U().getContentResolver();
            ContentValues contentValues = new ContentValues();
            Cursor query;
            try {
                query = contentResolver.query(com.mavenir.android.settings.a.t.a, null, "_id=?", new String[]{String.valueOf(j)}, null);
                if (query != null) {
                    try {
                        if (query.moveToFirst()) {
                            contentValues.put("sip_proxy", query.getString(query.getColumnIndex("sip_proxy")));
                            contentValues.put("sip_port", query.getString(query.getColumnIndex("sip_port")));
                            contentValues.put("sbc_fqdns", query.getString(query.getColumnIndex("sbc_fqdns")));
                            contentValues.put("sip_local_port", query.getString(query.getColumnIndex("sip_local_port")));
                            contentValues.put("sip_reg_exp_time", query.getString(query.getColumnIndex("sip_reg_exp_time")));
                            contentValues.put("sip_uri_format", query.getString(query.getColumnIndex("sip_uri_format")));
                            contentValues.put("sip_transport", query.getString(query.getColumnIndex("sip_transport")));
                            contentValues.put("sip_route", query.getString(query.getColumnIndex("sip_route")));
                            contentValues.put("sip_user_agent", query.getString(query.getColumnIndex("sip_user_agent")));
                            contentValues.put("session_expires_time", query.getString(query.getColumnIndex("session_expires_time")));
                            contentValues.put("min_session_expires_time", query.getString(query.getColumnIndex("min_session_expires_time")));
                            contentValues.put("session_refresher", query.getString(query.getColumnIndex("session_refresher")));
                            contentValues.put("soc_family", query.getString(query.getColumnIndex("soc_family")));
                        }
                    } catch (Exception e2) {
                        e = e2;
                        try {
                            com.mavenir.android.common.q.c("ClientSettingsInterface", "SIP: getValues(): " + e.getLocalizedMessage(), e.getCause());
                            query.close();
                            return contentValues;
                        } catch (Throwable th2) {
                            th = th2;
                            if (!(query == null || query.isClosed())) {
                                query.close();
                            }
                            throw th;
                        }
                    }
                }
                if (!(query == null || query.isClosed())) {
                    query.close();
                }
            } catch (Exception e3) {
                e = e3;
                query = null;
                com.mavenir.android.common.q.c("ClientSettingsInterface", "SIP: getValues(): " + e.getLocalizedMessage(), e.getCause());
                if (!(query == null || query.isClosed())) {
                    query.close();
                }
                return contentValues;
            } catch (Throwable th3) {
                th = th3;
                query = null;
                query.close();
                throw th;
            }
            return contentValues;
        }

        public static int b() {
            return g.a("sip_port", g.c(com.mavenir.android.settings.a.t.a, "sip_port", String.valueOf(b.d), "_id=?", new String[]{String.valueOf(k.c())}), b.d);
        }

        public static int a(int i) {
            return g.d(com.mavenir.android.settings.a.t.a, "sip_port", String.valueOf(i), "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static String[] c() {
            return g.c(com.mavenir.android.settings.a.t.a, "sbc_fqdns", "", "_id=?", new String[]{String.valueOf(k.c())}).split(";");
        }

        public static String d() {
            String[] c = c();
            if (c == null || c.length <= 0) {
                return null;
            }
            return c[0];
        }

        public static int a(String[] strArr) {
            return g.d(com.mavenir.android.settings.a.t.a, "sbc_fqdns", com.mavenir.android.common.t.b.a(strArr, ";"), "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static int e() {
            return g.a("sip_local_port", g.c(com.mavenir.android.settings.a.t.a, "sip_local_port", String.valueOf(0), "_id=?", new String[]{String.valueOf(k.c())}), 0);
        }

        public static int b(int i) {
            return g.d(com.mavenir.android.settings.a.t.a, "sip_local_port", String.valueOf(i), "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static int f() {
            return g.a("sip_reg_exp_time", g.c(com.mavenir.android.settings.a.t.a, "sip_reg_exp_time", String.valueOf(900), "_id=?", new String[]{String.valueOf(k.c())}), -1);
        }

        public static int c(int i) {
            return g.d(com.mavenir.android.settings.a.t.a, "sip_reg_exp_time", String.valueOf(i), "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static int g() {
            return g.a("sip_uri_format", g.c(com.mavenir.android.settings.a.t.a, "sip_uri_format", String.valueOf(b.e), "_id=?", new String[]{String.valueOf(k.c())}), b.e);
        }

        public static int d(int i) {
            return g.d(com.mavenir.android.settings.a.t.a, "sip_uri_format", String.valueOf(i), "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static int h() {
            return g.a("sip_transport", g.c(com.mavenir.android.settings.a.t.a, "sip_transport", String.valueOf(b.f), "_id=?", new String[]{String.valueOf(k.c())}), b.f);
        }

        public static int e(int i) {
            return g.d(com.mavenir.android.settings.a.t.a, "sip_transport", String.valueOf(i), "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static boolean i() {
            return g.a("sip_route", g.c(com.mavenir.android.settings.a.t.a, "sip_route", String.valueOf(true), "_id=?", new String[]{String.valueOf(k.c())}), Boolean.valueOf(true)).booleanValue();
        }

        public static int a(boolean z) {
            return g.d(com.mavenir.android.settings.a.t.a, "sip_route", String.valueOf(z), "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static int j() {
            return g.a("session_expires_time", g.c(com.mavenir.android.settings.a.t.a, "session_expires_time", String.valueOf(b.g), "_id=?", new String[]{String.valueOf(k.c())}), b.g);
        }

        public static int f(int i) {
            return g.d(com.mavenir.android.settings.a.t.a, "session_expires_time", String.valueOf(i), "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static int k() {
            return g.a("min_session_expires_time", g.c(com.mavenir.android.settings.a.t.a, "min_session_expires_time", String.valueOf(b.h), "_id=?", new String[]{String.valueOf(k.c())}), b.h);
        }

        public static int g(int i) {
            return g.d(com.mavenir.android.settings.a.t.a, "min_session_expires_time", String.valueOf(i), "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static int l() {
            return g.a("session_refresher", g.c(com.mavenir.android.settings.a.t.a, "session_refresher", String.valueOf(b.i), "_id=?", new String[]{String.valueOf(k.c())}), b.i);
        }

        public static int h(int i) {
            return g.d(com.mavenir.android.settings.a.t.a, "session_refresher", String.valueOf(i), "_id=?", new String[]{String.valueOf(k.c())});
        }
    }

    public static class w {
        public static ContentValues a() {
            ContentValues contentValues = new ContentValues();
            contentValues.put("sms_service_center", g.a(true, b.v));
            contentValues.put("preferred_messaging_protocol", g.a(true, b.w));
            contentValues.put("sms_validity", g.a(true, b.C));
            contentValues.put("enc_nat_number", g.a(true, b.D));
            contentValues.put("enc_intl_number", g.a(true, b.E));
            return contentValues;
        }

        public static ContentValues a(long j) {
            Exception e;
            Throwable th;
            ContentResolver contentResolver = FgVoIP.U().getContentResolver();
            ContentValues contentValues = new ContentValues();
            Cursor query;
            try {
                query = contentResolver.query(com.mavenir.android.settings.a.u.a, null, "_id=?", new String[]{String.valueOf(j)}, null);
                if (query != null) {
                    try {
                        if (query.moveToFirst()) {
                            contentValues.put("sms_service_center", query.getString(query.getColumnIndex("sms_service_center")));
                            contentValues.put("preferred_messaging_protocol", query.getString(query.getColumnIndex("preferred_messaging_protocol")));
                            contentValues.put("sms_validity", query.getString(query.getColumnIndex("sms_validity")));
                            contentValues.put("enc_nat_number", query.getString(query.getColumnIndex("enc_nat_number")));
                            contentValues.put("enc_intl_number", query.getString(query.getColumnIndex("enc_intl_number")));
                        }
                    } catch (Exception e2) {
                        e = e2;
                        try {
                            com.mavenir.android.common.q.c("ClientSettingsInterface", "SMS: getValues(): " + e.getLocalizedMessage(), e.getCause());
                            if (!(query == null || query.isClosed())) {
                                query.close();
                            }
                            return contentValues;
                        } catch (Throwable th2) {
                            th = th2;
                            query.close();
                            throw th;
                        }
                    }
                }
                if (!(query == null || query.isClosed())) {
                    query.close();
                }
            } catch (Exception e3) {
                e = e3;
                query = null;
                com.mavenir.android.common.q.c("ClientSettingsInterface", "SMS: getValues(): " + e.getLocalizedMessage(), e.getCause());
                query.close();
                return contentValues;
            } catch (Throwable th3) {
                th = th3;
                query = null;
                if (!(query == null || query.isClosed())) {
                    query.close();
                }
                throw th;
            }
            return contentValues;
        }

        public static String b() {
            return g.c(com.mavenir.android.settings.a.u.a, "sms_service_center", b.v, "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static int a(String str) {
            return g.d(com.mavenir.android.settings.a.u.a, "sms_service_center", str, "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static int c() {
            return g.a("preferred_messaging_protocol", g.c(com.mavenir.android.settings.a.u.a, "preferred_messaging_protocol", String.valueOf(b.w), "_id=?", new String[]{String.valueOf(k.c())}), b.w);
        }

        public static int a(int i) {
            return g.d(com.mavenir.android.settings.a.u.a, "preferred_messaging_protocol", String.valueOf(i), "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static int d() {
            return g.a("reference_number", g.c(com.mavenir.android.settings.a.u.a, "reference_number", String.valueOf(0), "_id=?", new String[]{String.valueOf(k.c())}), 0);
        }

        public static int b(int i) {
            return g.d(com.mavenir.android.settings.a.u.a, "reference_number", String.valueOf(i), "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static int e() {
            return g.a("sms_validity", g.c(com.mavenir.android.settings.a.u.a, "sms_validity", String.valueOf(b.C), "_id=?", new String[]{String.valueOf(k.c())}), b.C);
        }

        public static int c(int i) {
            return g.d(com.mavenir.android.settings.a.u.a, "sms_validity", String.valueOf(i), "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static int f() {
            return g.a("enc_nat_number", g.c(com.mavenir.android.settings.a.u.a, "enc_nat_number", String.valueOf(b.D), "_id=?", new String[]{String.valueOf(k.c())}), b.D);
        }

        public static int d(int i) {
            return g.d(com.mavenir.android.settings.a.u.a, "enc_nat_number", String.valueOf(i), "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static int g() {
            return g.a("enc_intl_number", g.c(com.mavenir.android.settings.a.u.a, "enc_intl_number", String.valueOf(b.E), "_id=?", new String[]{String.valueOf(k.c())}), b.E);
        }

        public static int e(int i) {
            return g.d(com.mavenir.android.settings.a.u.a, "enc_intl_number", String.valueOf(i), "_id=?", new String[]{String.valueOf(k.c())});
        }
    }

    public static class x {
        public static ContentValues a() {
            ContentValues contentValues = new ContentValues();
            contentValues.put("cs_enable", g.a(true, false));
            contentValues.put("cs_threshold", g.a(true, 0));
            contentValues.put("cs_number", g.a(true, ""));
            return contentValues;
        }

        public static boolean b() {
            return g.a("cs_enable", g.c(com.mavenir.android.settings.a.w.a, "cs_enable", String.valueOf(false), "_id=?", new String[]{String.valueOf(k.c())}), Boolean.valueOf(false)).booleanValue();
        }

        public static int a(boolean z) {
            return g.d(com.mavenir.android.settings.a.w.a, "cs_enable", String.valueOf(z), "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static int c() {
            return g.a("cs_threshold", g.c(com.mavenir.android.settings.a.w.a, "cs_threshold", String.valueOf(0), "_id=?", new String[]{String.valueOf(k.c())}), 0);
        }

        public static int a(int i) {
            return g.d(com.mavenir.android.settings.a.w.a, "cs_threshold", String.valueOf(i), "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static String d() {
            return g.c(com.mavenir.android.settings.a.w.a, "cs_number", "", "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static int a(String str) {
            return g.d(com.mavenir.android.settings.a.w.a, "cs_number", str, "_id=?", new String[]{String.valueOf(k.c())});
        }
    }

    public static class y {
        public static Uri a(String str, String str2, boolean z) {
            Uri uri = null;
            if (a(str) == -1) {
                try {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("ssid", g.a(true, str));
                    contentValues.put("bssid", g.a(true, str2));
                    contentValues.put("ignore", String.valueOf(z));
                    uri = FgVoIP.U().getContentResolver().insert(com.mavenir.android.settings.a.x.a, contentValues);
                } catch (Exception e) {
                    com.mavenir.android.common.q.c("ClientSettingsInterface", "Whitelist: addWhiteListEntry(): " + e.getLocalizedMessage(), e.getCause());
                }
            }
            return uri;
        }

        public static int a(long j, String str, String str2, boolean z) {
            try {
                ContentValues contentValues = new ContentValues();
                contentValues.put("ssid", g.a(true, str));
                contentValues.put("bssid", g.a(true, str2));
                contentValues.put("ignore", String.valueOf(z));
                return FgVoIP.U().getContentResolver().update(ContentUris.withAppendedId(com.mavenir.android.settings.a.x.a, j), contentValues, null, null);
            } catch (Exception e) {
                com.mavenir.android.common.q.c("ClientSettingsInterface", "Whitelist: updateWhiteListEntry(): " + e.getLocalizedMessage(), e.getCause());
                return 0;
            }
        }

        public static int a(long j) {
            try {
                return FgVoIP.U().getContentResolver().delete(ContentUris.withAppendedId(com.mavenir.android.settings.a.x.a, j), null, null);
            } catch (Exception e) {
                com.mavenir.android.common.q.c("ClientSettingsInterface", "Whitelist: removeWhiteListEntry(): " + e.getLocalizedMessage(), e.getCause());
                return 0;
            }
        }

        public static int a() {
            try {
                return FgVoIP.U().getContentResolver().delete(com.mavenir.android.settings.a.x.a, null, null);
            } catch (Exception e) {
                com.mavenir.android.common.q.c("ClientSettingsInterface", "Whitelist: clearWhitelist(): " + e.getLocalizedMessage(), e.getCause());
                return 0;
            }
        }

        public static long a(String str) {
            return g.c(com.mavenir.android.settings.a.x.a, "_id", -1, "ssid=?", new String[]{g.a(true, str)});
        }

        public static String b(long j) {
            return g.c(ContentUris.withAppendedId(com.mavenir.android.settings.a.x.a, j), "ssid", null, null, null);
        }

        public static String c(long j) {
            return g.c(ContentUris.withAppendedId(com.mavenir.android.settings.a.x.a, j), "bssid", null, null, null);
        }

        public static boolean d(long j) {
            return g.a("ignore", g.c(ContentUris.withAppendedId(com.mavenir.android.settings.a.x.a, j), "ignore", String.valueOf(false), null, null, false), Boolean.valueOf(false)).booleanValue();
        }

        public static int a(long j, boolean z) {
            return g.d(ContentUris.withAppendedId(com.mavenir.android.settings.a.x.a, j), "ignore", String.valueOf(z), null, null, false);
        }

        public static boolean a(String str, String str2) {
            String str3 = "ssid=?";
            String[] strArr = new String[]{g.a(true, str)};
            if (str2 != null) {
                str3 = str3 + " OR bssid=?";
                strArr = new String[]{g.a(true, str), g.a(true, str2)};
            }
            return g.a("ignore", g.c(com.mavenir.android.settings.a.x.a, "ignore", String.valueOf(false), str3, strArr, false), Boolean.valueOf(false)).booleanValue();
        }

        public static List<com.mavenir.android.common.z> b() {
            Exception e;
            Cursor cursor;
            Throwable th;
            List<com.mavenir.android.common.z> arrayList = new ArrayList();
            Cursor query;
            try {
                query = FgVoIP.U().getContentResolver().query(com.mavenir.android.settings.a.x.a, null, null, null, null);
                if (query != null) {
                    while (query.moveToNext()) {
                        try {
                            long j = query.getLong(query.getColumnIndex("_id"));
                            String a = g.a(true, "ssid", query.getString(query.getColumnIndex("ssid")));
                            String a2 = g.a(true, "bssid", query.getString(query.getColumnIndex("bssid")));
                            String string = query.getString(query.getColumnIndex("ignore"));
                            arrayList.add(new com.mavenir.android.common.z(j, a, a2, string != null ? Boolean.parseBoolean(string) : false));
                        } catch (Exception e2) {
                            e = e2;
                            cursor = query;
                        } catch (Throwable th2) {
                            th = th2;
                        }
                    }
                }
                if (!(query == null || query.isClosed())) {
                    query.close();
                }
            } catch (Exception e3) {
                e = e3;
                cursor = null;
                try {
                    com.mavenir.android.common.q.c("ClientSettingsInterface", "Whitelist: getWhitelist(): " + e.getLocalizedMessage(), e.getCause());
                    if (!(cursor == null || cursor.isClosed())) {
                        cursor.close();
                    }
                    return arrayList;
                } catch (Throwable th3) {
                    th = th3;
                    query = cursor;
                    if (!(query == null || query.isClosed())) {
                        query.close();
                    }
                    throw th;
                }
            } catch (Throwable th4) {
                th = th4;
                query = null;
                query.close();
                throw th;
            }
            return arrayList;
        }

        public static boolean b(String str, String str2) {
            Exception e;
            Throwable th;
            Cursor cursor = null;
            Cursor query;
            try {
                ContentResolver contentResolver = FgVoIP.U().getContentResolver();
                String str3 = "ssid=?";
                String[] strArr = new String[]{g.a(true, str)};
                if (str2 != null) {
                    str3 = str3 + " OR bssid=?";
                    strArr = new String[]{g.a(true, str), g.a(true, str2)};
                }
                query = contentResolver.query(com.mavenir.android.settings.a.x.a, new String[]{"_id", "ssid"}, str3, strArr, null);
                if (query != null) {
                    try {
                        if (query.moveToFirst()) {
                            if (!(query == null || query.isClosed())) {
                                query.close();
                            }
                            return true;
                        }
                    } catch (Exception e2) {
                        e = e2;
                        try {
                            com.mavenir.android.common.q.c("ClientSettingsInterface", "Whitelist: isInWhitelist(): " + e.getLocalizedMessage(), e.getCause());
                            query.close();
                            return false;
                        } catch (Throwable th2) {
                            th = th2;
                            cursor = query;
                            if (!(cursor == null || cursor.isClosed())) {
                                cursor.close();
                            }
                            throw th;
                        }
                    }
                }
                if (!(query == null || query.isClosed())) {
                    query.close();
                }
            } catch (Exception e3) {
                e = e3;
                query = null;
                com.mavenir.android.common.q.c("ClientSettingsInterface", "Whitelist: isInWhitelist(): " + e.getLocalizedMessage(), e.getCause());
                if (!(query == null || query.isClosed())) {
                    query.close();
                }
                return false;
            } catch (Throwable th3) {
                th = th3;
                cursor.close();
                throw th;
            }
            return false;
        }
    }

    public static class z {
        public static ContentValues a() {
            ContentValues contentValues = new ContentValues();
            contentValues.put("connection_preference", g.a(true, b.q));
            contentValues.put("wifi_call_manual_selection", g.a(true, false));
            contentValues.put("wifi_weak_lower_threshold", g.a(true, -80));
            contentValues.put("wifi_weak_upper_threshold", g.a(true, -90));
            contentValues.put("wifi_weak_timeout", g.a(true, 3));
            return contentValues;
        }

        public static ContentValues a(long j) {
            Exception e;
            Throwable th;
            ContentResolver contentResolver = FgVoIP.U().getContentResolver();
            ContentValues contentValues = new ContentValues();
            Cursor query;
            try {
                query = contentResolver.query(com.mavenir.android.settings.a.y.a, null, "_id=?", new String[]{String.valueOf(j)}, null);
                if (query != null) {
                    try {
                        if (query.moveToFirst()) {
                            contentValues.put("connection_preference", query.getString(query.getColumnIndex("connection_preference")));
                            contentValues.put("wifi_call_manual_selection", query.getString(query.getColumnIndex("wifi_call_manual_selection")));
                            contentValues.put("wifi_weak_lower_threshold", query.getString(query.getColumnIndex("wifi_weak_lower_threshold")));
                            contentValues.put("wifi_weak_upper_threshold", query.getString(query.getColumnIndex("wifi_weak_upper_threshold")));
                            contentValues.put("wifi_weak_timeout", query.getString(query.getColumnIndex("wifi_weak_timeout")));
                        }
                    } catch (Exception e2) {
                        e = e2;
                        try {
                            com.mavenir.android.common.q.c("ClientSettingsInterface", "WiFi: getValues(): " + e.getLocalizedMessage(), e.getCause());
                            if (!(query == null || query.isClosed())) {
                                query.close();
                            }
                            return contentValues;
                        } catch (Throwable th2) {
                            th = th2;
                            query.close();
                            throw th;
                        }
                    }
                }
                if (!(query == null || query.isClosed())) {
                    query.close();
                }
            } catch (Exception e3) {
                e = e3;
                query = null;
                com.mavenir.android.common.q.c("ClientSettingsInterface", "WiFi: getValues(): " + e.getLocalizedMessage(), e.getCause());
                query.close();
                return contentValues;
            } catch (Throwable th3) {
                th = th3;
                query = null;
                if (!(query == null || query.isClosed())) {
                    query.close();
                }
                throw th;
            }
            return contentValues;
        }

        public static int b() {
            return g.a("connection_preference", g.c(com.mavenir.android.settings.a.y.a, "connection_preference", String.valueOf(b.q), "_id=?", new String[]{String.valueOf(k.c())}), b.q);
        }

        public static int a(int i) {
            return g.d(com.mavenir.android.settings.a.y.a, "connection_preference", String.valueOf(i), "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static boolean c() {
            return g.a("wifi_call_manual_selection", g.c(com.mavenir.android.settings.a.y.a, "wifi_call_manual_selection", String.valueOf(false), "_id=?", new String[]{String.valueOf(k.c())}), Boolean.valueOf(false)).booleanValue();
        }

        public static int a(boolean z) {
            return g.d(com.mavenir.android.settings.a.y.a, "wifi_call_manual_selection", String.valueOf(z), "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static int d() {
            return g.a("wifi_weak_lower_threshold", g.c(com.mavenir.android.settings.a.y.a, "wifi_weak_lower_threshold", String.valueOf(-80), "_id=?", new String[]{String.valueOf(k.c())}), -80);
        }

        public static int b(int i) {
            return g.d(com.mavenir.android.settings.a.y.a, "wifi_weak_lower_threshold", String.valueOf(i), "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static int e() {
            return g.a("wifi_weak_upper_threshold", g.c(com.mavenir.android.settings.a.y.a, "wifi_weak_upper_threshold", String.valueOf(-90), "_id=?", new String[]{String.valueOf(k.c())}), -90);
        }

        public static int c(int i) {
            return g.d(com.mavenir.android.settings.a.y.a, "wifi_weak_upper_threshold", String.valueOf(i), "_id=?", new String[]{String.valueOf(k.c())});
        }

        public static int f() {
            return g.a("wifi_weak_timeout", g.c(com.mavenir.android.settings.a.y.a, "wifi_weak_timeout", String.valueOf(3), "_id=?", new String[]{String.valueOf(k.c())}), 3);
        }

        public static int d(int i) {
            return g.d(com.mavenir.android.settings.a.y.a, "wifi_weak_timeout", String.valueOf(i), "_id=?", new String[]{String.valueOf(k.c())});
        }
    }

    public static String a() {
        Object string = Secure.getString(FgVoIP.U().getContentResolver(), "android_id");
        Object a = d.a(FgVoIP.U(), "android_id", "");
        if (!(TextUtils.isEmpty(a) || a.equals(string))) {
            Log.e("ClientSettingsInterface", "init(): android ID does not match last ID used for activation");
        }
        if (TextUtils.isEmpty(string)) {
            Log.e("ClientSettingsInterface", "init(): unable to initialize");
        }
        return string;
    }
}
