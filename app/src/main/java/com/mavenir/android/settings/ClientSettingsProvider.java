package com.mavenir.android.settings;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.os.Handler;
import com.fgmicrotec.mobile.android.fgmag.VoIP;
import com.fgmicrotec.mobile.android.fgvoip.FgVoIP;
import com.mavenir.android.common.q;
import com.mavenir.android.settings.c.aa;
import com.mavenir.android.settings.c.b;
import com.mavenir.android.settings.c.c;
import com.mavenir.android.settings.c.d;
import com.mavenir.android.settings.c.f;
import com.mavenir.android.settings.c.g;
import com.mavenir.android.settings.c.h;
import com.mavenir.android.settings.c.i;
import com.mavenir.android.settings.c.k;
import com.mavenir.android.settings.c.l;
import com.mavenir.android.settings.c.m;
import com.mavenir.android.settings.c.n;
import com.mavenir.android.settings.c.p;
import com.mavenir.android.settings.c.s;
import com.mavenir.android.settings.c.t;
import com.mavenir.android.settings.c.u;
import com.mavenir.android.settings.c.v;
import com.mavenir.android.settings.c.w;
import com.mavenir.android.settings.c.x;
import com.mavenir.android.settings.c.z;
import java.util.HashMap;

public class ClientSettingsProvider extends ContentProvider {
    private static Context A = null;
    private static HashMap<String, String> a = new HashMap();
    private static HashMap<String, String> b = new HashMap();
    private static HashMap<String, String> c = new HashMap();
    private static HashMap<String, String> d = new HashMap();
    private static HashMap<String, String> e = new HashMap();
    private static HashMap<String, String> f = new HashMap();
    private static HashMap<String, String> g = new HashMap();
    private static HashMap<String, String> h = new HashMap();
    private static HashMap<String, String> i = new HashMap();
    private static HashMap<String, String> j = new HashMap();
    private static HashMap<String, String> k = new HashMap();
    private static HashMap<String, String> l = new HashMap();
    private static HashMap<String, String> m = new HashMap();
    private static HashMap<String, String> n = new HashMap();
    private static HashMap<String, String> o = new HashMap();
    private static HashMap<String, String> p = new HashMap();
    private static HashMap<String, String> q = new HashMap();
    private static HashMap<String, String> r = new HashMap();
    private static HashMap<String, String> s = new HashMap();
    private static HashMap<String, String> t = new HashMap();
    private static HashMap<String, String> u = new HashMap();
    private static HashMap<String, String> v = new HashMap();
    private static HashMap<String, String> w = new HashMap();
    private static HashMap<String, String> x = new HashMap();
    private static HashMap<String, String> y = new HashMap();
    private static final UriMatcher z = new UriMatcher(-1);
    private a B;

    private static class a extends SQLiteOpenHelper {
        a(Context context) {
            super(context, "client_preferences.db", null, 82);
        }

        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            q.a("ClientSettingsProvider", "onCreate(): creating new database");
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS profile_settings;");
            sQLiteDatabase.execSQL("CREATE TABLE profile_settings (_id INTEGER PRIMARY KEY,profile_name TEXT,display_name TEXT,impu TEXT,impi TEXT,password TEXT,hashed_msisdn TEXT,double_hashed_msisdn TEXT,msisdn TEXT,tac TEXT,svn TEXT,network_imsi TEXT,cc_fqdn TEXT,uuid TEXT,reg_id INTEGER,first_name TEXT,last_name TEXT,email TEXT,network_imei TEXT);");
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS sip_settings;");
            sQLiteDatabase.execSQL("CREATE TABLE sip_settings (_id INTEGER PRIMARY KEY,sip_proxy TEXT,sip_port TEXT,sbc_fqdns TEXT,sip_local_port TEXT,sip_reg_exp_time TEXT,sip_uri_format TEXT,sip_transport TEXT,sip_route TEXT,sip_user_agent TEXT,session_expires_time TEXT,min_session_expires_time TEXT,session_refresher TEXT,soc_family TEXT);");
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS rtp_settings;");
            sQLiteDatabase.execSQL("CREATE TABLE rtp_settings (_id INTEGER PRIMARY KEY,rtp_local_port TEXT,rtp_decoder_timeout TEXT,rtp_use_rtcp TEXT,rtp_rtcp_period TEXT,srtp_mode TEXT,srtp_encryption_type TEXT,srtp_auth_type TEXT);");
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS call_settings;");
            sQLiteDatabase.execSQL("CREATE TABLE call_settings (_id INTEGER PRIMARY KEY,call_uri_template TEXT,call_pickup_template TEXT,call_conference_factory_uri TEXT, call_ut_server_address TEXT, call_ut_server_port TEXT, call_enable_ussd TEXT, call_echo_test_number TEXT);");
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS nat_settings;");
            sQLiteDatabase.execSQL("CREATE TABLE nat_settings (_id INTEGER PRIMARY KEY,nat_traversal_mode TEXT,nat_stun_server TEXT,nat_stun_port TEXT,nat_stun_query_mode TEXT,nat_sip_keep_alive TEXT,tcp_keep_alive_idle_time TEXT,tcp_keep_alive_probe_time TEXT,tcp_keep_alive_probes_count TEXT);");
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS wifi_settings;");
            sQLiteDatabase.execSQL("CREATE TABLE wifi_settings (_id INTEGER PRIMARY KEY,connection_preference TEXT,wifi_call_manual_selection TEXT,wifi_weak_lower_threshold TEXT,wifi_weak_upper_threshold TEXT,wifi_weak_timeout TEXT);");
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS media_settings;");
            sQLiteDatabase.execSQL("CREATE TABLE media_settings (_id INTEGER PRIMARY KEY,media_codec_type TEXT,media_codec_mode TEXT,amr_format_octet_aligned TEXT,media_overflow_mark TEXT,media_high_watermark TEXT,media_low_watermark TEXT,media_ati_on_off TEXT,media_dtmf_signalization TEXT,media_alert_volume TEXT,media_video_codec_type TEXT,media_video_size_index TEXT);");
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS atg_settings;");
            sQLiteDatabase.execSQL("CREATE TABLE atg_settings (_id INTEGER PRIMARY KEY,acid_string TEXT,atg_state TEXT);");
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS sms_settings;");
            sQLiteDatabase.execSQL("CREATE TABLE sms_settings (_id INTEGER PRIMARY KEY,sms_service_center TEXT,preferred_messaging_protocol TEXT,reference_number TEXT,sms_validity TEXT,enc_nat_number TEXT,enc_intl_number TEXT)");
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS developer_settings;");
            sQLiteDatabase.execSQL("CREATE TABLE developer_settings (_id INTEGER PRIMARY KEY,trace_write_mode TEXT,trace_enabled TEXT,trace_level_data TEXT,trace_level_external TEXT,trace_level_gui TEXT,trace_level_media TEXT,trace_level_sip TEXT,enable_qos_test_ui TEXT,enable_adb_hooks TEXT);");
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS general_settings;");
            sQLiteDatabase.execSQL("CREATE TABLE general_settings (_id INTEGER PRIMARY KEY,active_profile_id INTEGER,provisioning_vers TEXT,provisioning_imsi TEXT,provisioning_deactivated TEXT,login_status TEXT,save_user_data TEXT,start_after_phone_boot TEXT,enable_status_icon TEXT,outgoing_calls_preference TEXT,enable_video_calls TEXT,sms_request_delivery_reports TEXT,sms_delete_old_messages TEXT,sms_messages_per_conversation_limit TEXT,sms_notify_incoming TEXT,sms_notifications_ringtone TEXT,enable_qos_test_ui TEXT,emergency_numbers TEXT,msg_request_display_reports TEXT,msg_allow_display_reports TEXT,enable_tcp_keep_alive TEXT, enable_messaging TEXT, contacts_display_order TEXT, contacts_sort_order TEXT, last_root_check_time TEXT,is_device_rooted TEXT,device_name TEXT,app_mode TEXT,sim_msisdn TEXT,mms_auto_download_messages TEXT, last_activated_app_vers TEXT);");
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS qos_settings;");
            sQLiteDatabase.execSQL("CREATE TABLE qos_settings (_id INTEGER PRIMARY KEY,validity TEXT,configuration_time TEXT,min_wifi_threshold TEXT,wifi_hysteresis_timer TEXT,next_sbc_timer TEXT,hs_lost_timer TEXT,stay_alive_timer TEXT,retry_login_timer TEXT,no_media_timer TEXT,wifi_qos_threshold TEXT,max_jitter TEXT,max_round_trip_delay TEXT,rtp_max_cumulative_loss TEXT,rtp_max_fraction_loss TEXT,tls_cert_revoke_tmr TEXT,tls_session_cache_tmr TEXT,reg_no_resp_retry_tmr TEXT,inv_early_no_resp_timeout TEXT,codecs TEXT,back_off_timer_list TEXT,user_deactivation_message TEXT,provisioning_fqdn TEXT,provisioning_fqdn_2 TEXT,public_ip_service_url TEXT,acceptable_time_difference TEXT);");
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS qos_statistics;");
            sQLiteDatabase.execSQL("CREATE TABLE qos_statistics (_id INTEGER PRIMARY KEY,wifi_signal_strength TEXT,jitter TEXT,round_trip_delay TEXT,cumulative_loss TEXT,fraction_loss TEXT,timestamp TEXT);");
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS whitelist;");
            sQLiteDatabase.execSQL("CREATE TABLE whitelist (_id INTEGER PRIMARY KEY,ssid TEXT,bssid TEXT,ignore TEXT);");
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS exception_message;");
            sQLiteDatabase.execSQL("CREATE TABLE exception_message (_id INTEGER PRIMARY KEY,exception_id TEXT,exception_message TEXT,exception_url TEXT);");
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS emergency_numbers;");
            sQLiteDatabase.execSQL("CREATE TABLE emergency_numbers (_id INTEGER PRIMARY KEY,profile_id INTEGER NOT NULL,number TEXT NOT NULL);");
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS native_numbers;");
            sQLiteDatabase.execSQL("CREATE TABLE native_numbers (_id INTEGER PRIMARY KEY,profile_id INTEGER NOT NULL,number TEXT NOT NULL);");
            if (FgVoIP.U().p()) {
                sQLiteDatabase.execSQL("DROP TABLE IF EXISTS vcc_settings;");
                sQLiteDatabase.execSQL("CREATE TABLE vcc_settings (_id INTEGER PRIMARY KEY,cs_enable TEXT,cs_threshold TEXT, cs_number TEXT);");
            }
            if (FgVoIP.U().aj()) {
                sQLiteDatabase.execSQL("DROP TABLE IF EXISTS capabilities;");
                sQLiteDatabase.execSQL("CREATE TABLE capabilities (_id INTEGER PRIMARY KEY,capability_info_expiry TEXT,capability_polling_period TEXT,last_info_update_time TEXT,last_polling_update_time TEXT,cap_presence_discovery TEXT,cap_sip_messaging TEXT,cap_im_session TEXT,cap_file_transfer TEXT,cap_image_share TEXT,cap_video_share TEXT,cap_ip_audio_call TEXT,cap_ip_video_call TEXT,cap_social_presence TEXT,cap_geolocation_push TEXT,cap_geolocation_pull TEXT,cap_geolocation_pull_ft TEXT,cap_cpm_chat TEXT,cap_cpm_ft TEXT,cap_cpm_standalone_msg TEXT);");
                sQLiteDatabase.execSQL("DROP TABLE IF EXISTS rcs_presence;");
                sQLiteDatabase.execSQL("CREATE TABLE rcs_presence (_id INTEGER PRIMARY KEY,xdms_root_uri TEXT,xdms_proxy_address TEXT,xdms_proxy_port TEXT,presence_enabled TEXT);");
                sQLiteDatabase.execSQL("DROP TABLE IF EXISTS rcs_chat_ft;");
                sQLiteDatabase.execSQL("CREATE TABLE rcs_chat_ft (_id INTEGER PRIMARY KEY,group_chat_factory_uri TEXT);");
            }
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS auth_settings;");
            sQLiteDatabase.execSQL("CREATE TABLE auth_settings (_id INTEGER PRIMARY KEY,auth_group TEXT,oauth_client_id TEXT,oauth_client_secret TEXT,oauth_scope TEXT,oauth_redirect_uri TEXT,oauth_auth_endpoint TEXT,oauth_token_endpoint TEXT,oauth_revoke_endpoint TEXT,oauth_device_agent TEXT,es_server_url TEXT,wsg_server_url TEXT,iam_server_url TEXT,key_private TEXT,key_public TEXT,cert TEXT,epdg_address TEXT,eap_prefix TEXT,vimsi TEXT,realm TEXT,device_id TEXT,vpn_force TEXT,vpn_gateway TEXT,vpn_id TEXT,vpn_password TEXT,vpn_type TEXT,vpn_user_cert_alias TEXT,vpn_routing_subnet TEXT,vpn_sip_soc_family TEXT);");
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS custom_settings;");
            sQLiteDatabase.execSQL("CREATE TABLE custom_settings (_id INTEGER PRIMARY KEY,network_performance TEXT);");
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS lte_settings;");
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS lte_settings (_id INTEGER PRIMARY KEY,lte_800_capable TEXT,lte_800_user_activated TEXT,lte_800_network_enabled TEXT,lte_800_expiry TEXT,sbc_fqdns TEXT,sip_port TEXT,mnc TEXT,mcc TEXT,tac_min TEXT,tac_max TEXT);");
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS vvm_settings;");
            sQLiteDatabase.execSQL("CREATE TABLE vvm_settings (_id INTEGER PRIMARY KEY,vvm_download_mode TEXT,vvm_client_id TEXT,vvm_client_passwd TEXT,vvm_server_addr TEXT,vvm_server_port TEXT,vvm_tls_enabled TEXT,vvm_pin_num TEXT,vvm_folder_path TEXT,vvm_sit_encode_enabled TEXT);");
            a(sQLiteDatabase);
        }

        private static void a(SQLiteDatabase sQLiteDatabase) {
            int insert = (int) sQLiteDatabase.insert("profile_settings", "profile_name", p.a());
            sQLiteDatabase.insert("sip_settings", "sip_proxy", g.a((long) insert, v.a()));
            sQLiteDatabase.insert("rtp_settings", "rtp_local_port", g.a((long) insert, s.a()));
            sQLiteDatabase.insert("call_settings", "call_uri_template", g.a((long) insert, c.a()));
            sQLiteDatabase.insert("nat_settings", "nat_traversal_mode", g.a((long) insert, n.a()));
            sQLiteDatabase.insert("wifi_settings", "connection_preference", g.a((long) insert, z.a()));
            sQLiteDatabase.insert("media_settings", "media_codec_type", g.a((long) insert, m.a()));
            sQLiteDatabase.insert("atg_settings", "acid_string", g.a((long) insert, com.mavenir.android.settings.c.a.a()));
            sQLiteDatabase.insert("sms_settings", "sms_service_center", g.a((long) insert, w.a()));
            sQLiteDatabase.insert("developer_settings", "trace_write_mode", g.a((long) insert, h.a()));
            sQLiteDatabase.insert("qos_settings", "validity", g.a((long) insert, c.q.a()));
            sQLiteDatabase.insert("general_settings", "active_profile_id", g.a((long) insert, k.a()));
            for (ContentValues insert2 : i.a()) {
                sQLiteDatabase.insert("emergency_numbers", "number", insert2);
            }
            if (FgVoIP.U().p()) {
                sQLiteDatabase.insert("vcc_settings", "cs_number", g.a((long) insert, x.a()));
            }
            if (FgVoIP.U().aj()) {
                sQLiteDatabase.insert("capabilities", "capability_info_expiry", g.a((long) insert, d.a()));
                sQLiteDatabase.insert("rcs_presence", "xdms_root_uri", g.a((long) insert, u.a()));
                sQLiteDatabase.insert("rcs_chat_ft", "group_chat_factory_uri", g.a((long) insert, t.a()));
            }
            sQLiteDatabase.insert("auth_settings", "oauth_client_id", g.a(1, b.a()));
            sQLiteDatabase.insert("custom_settings", "network_performance", g.a(1, f.a()));
            sQLiteDatabase.insert("lte_settings", "sbc_fqdns", g.a(1, l.a()));
            sQLiteDatabase.insert("vvm_settings", "vvm_client_id", g.a((long) insert, aa.a()));
        }

        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            Object obj;
            Exception e;
            Throwable th;
            q.a("ClientSettingsProvider", "onUpgrade(): old version=" + i + ", new version=" + i2);
            Object obj2 = null;
            while (i < i2) {
                Cursor cursor;
                if (i == 17) {
                    sQLiteDatabase.execSQL("CREATE TABLE whitelist (_id INTEGER PRIMARY KEY,ssid TEXT,bssid TEXT,ignore TEXT);");
                    obj = obj2;
                } else if (i == 18 && FgVoIP.U().aj()) {
                    sQLiteDatabase.execSQL("CREATE TABLE capabilities (_id INTEGER PRIMARY KEY,capability_info_expiry TEXT,capability_polling_period TEXT,last_info_update_time TEXT,last_polling_update_time TEXT,cap_presence_discovery TEXT,cap_sip_messaging TEXT,cap_im_session TEXT,cap_file_transfer TEXT,cap_image_share TEXT,cap_video_share TEXT,cap_ip_audio_call TEXT,cap_ip_video_call TEXT,cap_social_presence TEXT,cap_geolocation_push TEXT,cap_geolocation_pull TEXT,cap_geolocation_pull_ft TEXT,cap_cpm_chat TEXT,cap_cpm_ft TEXT,cap_cpm_standalone_msg TEXT);");
                    sQLiteDatabase.insert("capabilities", "capability_info_expiry", g.a(1, d.a()));
                    obj = obj2;
                } else if (i == 19 && FgVoIP.U().aj()) {
                    sQLiteDatabase.execSQL("CREATE TABLE rcs_presence (_id INTEGER PRIMARY KEY,xdms_root_uri TEXT,xdms_proxy_address TEXT,xdms_proxy_port TEXT,presence_enabled TEXT);");
                    sQLiteDatabase.insert("rcs_presence", "xdms_root_uri", g.a(1, u.a()));
                    obj = obj2;
                } else if (i == 20) {
                    r0 = "ALTER TABLE general_settings ADD COLUMN provisioning_imsi STRING NOT NULL DEFAULT '';";
                    q.b("ClientSettingsProvider", r0);
                    sQLiteDatabase.execSQL(r0);
                    obj = obj2;
                } else if (i == 21) {
                    r0 = "ALTER TABLE general_settings ADD COLUMN emergency_numbers STRING NOT NULL DEFAULT '112,911,999';";
                    q.b("ClientSettingsProvider", r0);
                    sQLiteDatabase.execSQL(r0);
                    obj = obj2;
                } else if (i == 22) {
                    r0 = "ALTER TABLE general_settings ADD COLUMN provisioning_deactivated TEXT NOT NULL DEFAULT '';";
                    q.b("ClientSettingsProvider", r0);
                    try {
                        sQLiteDatabase.execSQL(r0);
                    } catch (Exception e2) {
                    }
                    r0 = "ALTER TABLE sip_settings ADD COLUMN sbc_fqdns TEXT NOT NULL DEFAULT '';";
                    q.b("ClientSettingsProvider", r0);
                    try {
                        sQLiteDatabase.execSQL(r0);
                    } catch (Exception e3) {
                    }
                    obj = obj2;
                } else if (i == 23) {
                    q.b("ClientSettingsProvider", "Disabling traces in settings");
                    r0 = new ContentValues();
                    r0.put("trace_write_mode", g.a(true, false));
                    r0.put("trace_level_data", g.a(true, 0));
                    r0.put("trace_level_external", g.a(true, 0));
                    r0.put("trace_level_gui", g.a(true, 0));
                    r0.put("trace_level_media", g.a(true, 0));
                    r0.put("trace_level_sip", g.a(true, 0));
                    r0.put("enable_qos_test_ui", g.a(true, false));
                    q.b("ClientSettingsProvider", "Traces disabled for " + sQLiteDatabase.update("developer_settings", r0, null, null) + " rows");
                    obj = obj2;
                } else {
                    if (i == 24) {
                        if (FgVoIP.U().aj()) {
                            r0 = "ALTER TABLE rcs_presence ADD COLUMN presence_enabled TEXT NOT NULL DEFAULT '';";
                            q.b("ClientSettingsProvider", r0);
                            sQLiteDatabase.execSQL(r0);
                            r0 = new ContentValues();
                            r0.put("presence_enabled", g.a(true, false));
                            q.b("ClientSettingsProvider", "Presence table updated for " + sQLiteDatabase.update("rcs_presence", r0, null, null) + " rows");
                            r0 = new ContentValues();
                            r0.put("cap_social_presence", g.a(true, false));
                            q.b("ClientSettingsProvider", "Capabilities table updated for " + sQLiteDatabase.update("capabilities", r0, null, null) + " rows");
                            r0 = new ContentValues();
                            r0.put("connection_preference", g.a(true, b.q));
                            q.b("ClientSettingsProvider", "Wifi table updated for " + sQLiteDatabase.update("wifi_settings", r0, null, null) + " rows");
                            obj = obj2;
                        }
                    } else if (i == 25) {
                        sQLiteDatabase.execSQL("ALTER TABLE qos_settings ADD COLUMN tls_cert_revoke_tmr TEXT NOT NULL DEFAULT '';");
                        sQLiteDatabase.execSQL("ALTER TABLE qos_settings ADD COLUMN tls_session_cache_tmr TEXT NOT NULL DEFAULT '';");
                        r0 = new ContentValues();
                        r0.put("tls_cert_revoke_tmr", g.a(true, 24));
                        r0.put("tls_session_cache_tmr", g.a(true, 24));
                        q.b("ClientSettingsProvider", "QoS table updated for " + sQLiteDatabase.update("qos_settings", r0, null, null) + " rows");
                        obj = obj2;
                    } else if (i == 26) {
                        sQLiteDatabase.execSQL("ALTER TABLE qos_statistics ADD COLUMN timestamp TEXT NOT NULL DEFAULT '0';");
                        obj = obj2;
                    } else if (i == 27) {
                        sQLiteDatabase.execSQL("ALTER TABLE developer_settings ADD COLUMN trace_enabled TEXT NOT NULL DEFAULT '" + g.a(true, false) + "';");
                        obj = obj2;
                    } else if (i == 28) {
                        sQLiteDatabase.execSQL("CREATE TABLE exception_message (_id INTEGER PRIMARY KEY,exception_id TEXT,exception_message TEXT,exception_url TEXT);");
                        obj = obj2;
                    } else if (i == 29) {
                        sQLiteDatabase.execSQL("ALTER TABLE qos_settings ADD COLUMN configuration_time TEXT NOT NULL DEFAULT '" + g.a(true, 0) + "';");
                        obj = obj2;
                    } else if (i == 30) {
                        sQLiteDatabase.execSQL("ALTER TABLE sms_settings ADD COLUMN reference_number TEXT NOT NULL DEFAULT '" + g.a(true, 0) + "';");
                        obj = obj2;
                    } else if (i == 31) {
                        if (FgVoIP.U().aj()) {
                            sQLiteDatabase.execSQL("ALTER TABLE capabilities ADD COLUMN cap_cpm_chat TEXT NOT NULL DEFAULT '" + g.a(true, "cap_cpm_chat") + "';");
                            sQLiteDatabase.execSQL("ALTER TABLE capabilities ADD COLUMN cap_cpm_ft TEXT NOT NULL DEFAULT '" + g.a(true, "cap_cpm_ft") + "';");
                            obj = obj2;
                        }
                    } else if (i == 32) {
                        sQLiteDatabase.execSQL("ALTER TABLE qos_settings ADD COLUMN provisioning_fqdn TEXT NOT NULL DEFAULT '" + g.a(true, b.G) + "';");
                        obj = obj2;
                    } else if (i == 33) {
                        sQLiteDatabase.execSQL("ALTER TABLE general_settings ADD COLUMN msg_request_display_reports TEXT NOT NULL DEFAULT '" + g.a(true, b.A) + "';");
                        sQLiteDatabase.execSQL("ALTER TABLE general_settings ADD COLUMN msg_allow_display_reports TEXT NOT NULL DEFAULT '" + g.a(true, b.B) + "';");
                        obj = obj2;
                    } else if (i == 34) {
                        sQLiteDatabase.execSQL("ALTER TABLE qos_settings ADD COLUMN public_ip_service_url TEXT NOT NULL DEFAULT '" + g.a(true, b.J) + "';");
                        obj = obj2;
                    } else if (i == 35) {
                        sQLiteDatabase.execSQL("ALTER TABLE qos_settings ADD COLUMN provisioning_fqdn_2 TEXT NOT NULL DEFAULT '" + g.a(true, b.H) + "';");
                        obj = obj2;
                    } else if (i == 36) {
                        sQLiteDatabase.execSQL("ALTER TABLE qos_settings ADD COLUMN reg_no_resp_retry_tmr TEXT NOT NULL DEFAULT '" + g.a(true, 5) + "';");
                        sQLiteDatabase.execSQL("ALTER TABLE qos_settings ADD COLUMN inv_early_no_resp_timeout TEXT NOT NULL DEFAULT '" + g.a(true, 10) + "';");
                        sQLiteDatabase.execSQL("ALTER TABLE qos_settings ADD COLUMN next_sbc_timer TEXT NOT NULL DEFAULT '" + g.a(true, 5) + "';");
                        sQLiteDatabase.execSQL("ALTER TABLE qos_settings ADD COLUMN user_deactivation_message TEXT NOT NULL DEFAULT '" + g.a(true, b.I) + "';");
                        sQLiteDatabase.execSQL("ALTER TABLE qos_settings ADD COLUMN back_off_timer_list TEXT NOT NULL DEFAULT '" + g.a(true, "5;10;15;60") + "';");
                        obj = obj2;
                    } else if (i == 37) {
                        sQLiteDatabase.execSQL("ALTER TABLE general_settings ADD COLUMN enable_tcp_keep_alive TEXT NOT NULL DEFAULT '" + g.a(true, false) + "';");
                        r0 = new ContentValues();
                        r0.put("tcp_keep_alive_idle_time", g.a(true, 7));
                        r0.put("tcp_keep_alive_probe_time", g.a(true, 5));
                        r0.put("tcp_keep_alive_probes_count", g.a(true, 5));
                        sQLiteDatabase.update("nat_settings", r0, "_id=?", new String[]{"" + b(sQLiteDatabase)});
                        obj = obj2;
                    } else if (i == 38) {
                        sQLiteDatabase.execSQL("ALTER TABLE developer_settings ADD COLUMN enable_adb_hooks TEXT NOT NULL DEFAULT '" + g.a(true, false) + "';");
                        obj = obj2;
                    } else if (i == 39) {
                        sQLiteDatabase.execSQL("ALTER TABLE general_settings ADD COLUMN save_user_data TEXT NOT NULL DEFAULT '" + g.a(true, true) + "';");
                        obj = obj2;
                    } else if (i == 40) {
                        sQLiteDatabase.execSQL("CREATE TABLE emergency_numbers (_id INTEGER PRIMARY KEY,profile_id INTEGER NOT NULL,number TEXT NOT NULL);");
                        long b = b(sQLiteDatabase);
                        for (String str : b.F) {
                            ContentValues contentValues = new ContentValues();
                            contentValues.put("profile_id", Long.valueOf(b));
                            contentValues.put("number", g.a(true, str));
                            sQLiteDatabase.insert("emergency_numbers", "number", contentValues);
                        }
                        sQLiteDatabase.execSQL("CREATE TABLE native_numbers (_id INTEGER PRIMARY KEY,profile_id INTEGER NOT NULL,number TEXT NOT NULL);");
                        obj = obj2;
                    } else if (i == 41) {
                        sQLiteDatabase.execSQL("ALTER TABLE general_settings ADD COLUMN enable_messaging TEXT NOT NULL DEFAULT '" + g.a(true, true) + "';");
                        obj = obj2;
                    } else if (i == 42) {
                        if (FgVoIP.U().p()) {
                            sQLiteDatabase.execSQL("CREATE TABLE vcc_settings (_id INTEGER PRIMARY KEY,cs_enable TEXT,cs_threshold TEXT, cs_number TEXT);");
                            r0 = new ContentValues();
                            r0.put("_id", Long.valueOf(b(sQLiteDatabase)));
                            r0.put("cs_enable", g.a(true, false));
                            r0.put("cs_threshold", g.a(true, 0));
                            r0.put("cs_number", g.a(true, ""));
                            sQLiteDatabase.insert("vcc_settings", "cs_number", r0);
                            obj = obj2;
                        }
                    } else if (i == 43) {
                        sQLiteDatabase.execSQL("ALTER TABLE call_settings ADD COLUMN call_conference_factory_uri TEXT NOT NULL DEFAULT '" + g.a(true, b.m) + "';");
                        obj = obj2;
                    } else if (i == 44) {
                        sQLiteDatabase.execSQL("ALTER TABLE general_settings ADD COLUMN is_device_rooted TEXT;");
                        obj = obj2;
                    } else if (i == 45) {
                        sQLiteDatabase.execSQL("ALTER TABLE call_settings ADD COLUMN call_ut_server_address TEXT NOT NULL DEFAULT '" + g.a(true, b.n) + "';");
                        obj = obj2;
                    } else if (i == 46) {
                        sQLiteDatabase.execSQL("ALTER TABLE general_settings ADD COLUMN last_root_check_time TEXT NOT NULL DEFAULT '" + g.a(true, "0") + "';");
                        obj = obj2;
                    } else if (i == 47) {
                        sQLiteDatabase.execSQL("ALTER TABLE call_settings ADD COLUMN call_ut_server_port TEXT NOT NULL DEFAULT '" + g.a(true, "" + b.o) + "';");
                        obj = obj2;
                    } else if (i == 48) {
                        obj = obj2;
                    } else if (i == 49) {
                        sQLiteDatabase.execSQL("CREATE TABLE auth_settings (_id INTEGER PRIMARY KEY,auth_group TEXT,oauth_client_id TEXT,oauth_client_secret TEXT,oauth_scope TEXT,oauth_redirect_uri TEXT,oauth_auth_endpoint TEXT,oauth_token_endpoint TEXT,oauth_revoke_endpoint TEXT,oauth_device_agent TEXT,es_server_url TEXT,wsg_server_url TEXT,iam_server_url TEXT,key_private TEXT,key_public TEXT,cert TEXT,epdg_address TEXT,eap_prefix TEXT,vimsi TEXT,realm TEXT,device_id TEXT,vpn_force TEXT,vpn_gateway TEXT,vpn_id TEXT,vpn_password TEXT,vpn_type TEXT,vpn_user_cert_alias TEXT,vpn_routing_subnet TEXT,vpn_sip_soc_family TEXT);");
                        sQLiteDatabase.insert("auth_settings", "oauth_client_id", b.a());
                        obj = 1;
                    } else if (i == 50) {
                        sQLiteDatabase.execSQL("ALTER TABLE sip_settings ADD COLUMN session_expires_time TEXT NOT NULL DEFAULT '" + g.a(true, "" + b.g) + "';");
                        sQLiteDatabase.execSQL("ALTER TABLE sip_settings ADD COLUMN min_session_expires_time TEXT NOT NULL DEFAULT '" + g.a(true, "" + b.h) + "';");
                        sQLiteDatabase.execSQL("ALTER TABLE sip_settings ADD COLUMN session_refresher TEXT NOT NULL DEFAULT '" + g.a(true, "" + b.i) + "';");
                        obj = obj2;
                    } else if (i == 51) {
                        sQLiteDatabase.execSQL("ALTER TABLE emergency_numbers ADD COLUMN profile_id TEXT NOT NULL DEFAULT 1;");
                        sQLiteDatabase.execSQL("UPDATE emergency_numbers SET profile_id = (SELECT profile._id FROM profile_settings profile WHERE profile.profile_name = profile_name)");
                        sQLiteDatabase.execSQL("ALTER TABLE native_numbers ADD COLUMN profile_id TEXT NOT NULL DEFAULT 1;");
                        sQLiteDatabase.execSQL("UPDATE native_numbers SET profile_id = (SELECT profile._id FROM profile_settings profile WHERE profile.profile_name = profile_name)");
                        obj = obj2;
                    } else if (i == 52) {
                        cursor = null;
                        Cursor query;
                        try {
                            cursor = sQLiteDatabase.query("emergency_numbers", null, null, null, null, null, null);
                            try {
                                sQLiteDatabase.execSQL("DROP TABLE IF EXISTS emergency_numbers;");
                                sQLiteDatabase.execSQL("CREATE TABLE emergency_numbers (_id INTEGER PRIMARY KEY,profile_id INTEGER NOT NULL,number TEXT NOT NULL);");
                                if (cursor != null) {
                                    while (cursor.moveToNext()) {
                                        r0 = new ContentValues();
                                        r0.put("profile_id", Integer.valueOf(cursor.getInt(cursor.getColumnIndex("profile_id"))));
                                        r0.put("profile_id", cursor.getString(cursor.getColumnIndex("number")));
                                        sQLiteDatabase.insert("emergency_numbers", "number", r0);
                                    }
                                }
                                if (cursor != null) {
                                    cursor.close();
                                }
                                query = sQLiteDatabase.query("native_numbers", null, null, null, null, null, null);
                                try {
                                    sQLiteDatabase.execSQL("DROP TABLE IF EXISTS native_numbers;");
                                    sQLiteDatabase.execSQL("CREATE TABLE native_numbers (_id INTEGER PRIMARY KEY,profile_id INTEGER NOT NULL,number TEXT NOT NULL);");
                                    if (query != null) {
                                        while (query.moveToNext()) {
                                            r0 = new ContentValues();
                                            r0.put("profile_id", Integer.valueOf(query.getInt(query.getColumnIndex("profile_id"))));
                                            r0.put("profile_id", query.getString(query.getColumnIndex("number")));
                                            sQLiteDatabase.insert("native_numbers", "number", r0);
                                        }
                                    }
                                    if (query != null) {
                                        query.close();
                                    }
                                } catch (Exception e4) {
                                    e = e4;
                                    try {
                                        e.printStackTrace();
                                        if (query != null) {
                                            query.close();
                                        }
                                        obj = obj2;
                                        i++;
                                        obj2 = obj;
                                    } catch (Throwable th2) {
                                        th = th2;
                                        cursor = query;
                                    }
                                }
                            } catch (Exception e5) {
                                e = e5;
                                query = cursor;
                                e.printStackTrace();
                                if (query != null) {
                                    query.close();
                                }
                                obj = obj2;
                                i++;
                                obj2 = obj;
                            } catch (Throwable th3) {
                                th = th3;
                            }
                        } catch (Exception e6) {
                            e = e6;
                            query = cursor;
                            e.printStackTrace();
                            if (query != null) {
                                query.close();
                            }
                            obj = obj2;
                            i++;
                            obj2 = obj;
                        } catch (Throwable th32) {
                            th = th32;
                        }
                        obj = obj2;
                    } else if (i == 53 && obj2 == null) {
                        r1 = new String[]{"key_private", "key_public", "cert", "epdg_address", "eap_prefix", "vimsi", "realm"};
                        for (String str2 : r1) {
                            sQLiteDatabase.execSQL("ALTER TABLE auth_settings ADD COLUMN " + str2 + " TEXT NOT NULL DEFAULT '" + g.a(true, "") + "';");
                        }
                        obj = obj2;
                    } else if (i == 54 && obj2 == null) {
                        r1 = new String[]{"device_id"};
                        for (String str22 : r1) {
                            sQLiteDatabase.execSQL("ALTER TABLE auth_settings ADD COLUMN " + str22 + " TEXT NOT NULL DEFAULT '" + g.a(true, "") + "';");
                        }
                        obj = obj2;
                    } else if (i == 55) {
                        sQLiteDatabase.execSQL("ALTER TABLE sms_settings ADD COLUMN sms_validity TEXT NOT NULL DEFAULT '" + g.a(true, b.C) + "';");
                        sQLiteDatabase.execSQL("ALTER TABLE sms_settings ADD COLUMN enc_nat_number TEXT NOT NULL DEFAULT '" + g.a(true, b.D) + "';");
                        sQLiteDatabase.execSQL("ALTER TABLE sms_settings ADD COLUMN enc_intl_number TEXT NOT NULL DEFAULT '" + g.a(true, b.E) + "';");
                        obj = obj2;
                    } else if (i == 56 && obj2 == null) {
                        sQLiteDatabase.execSQL("ALTER TABLE auth_settings ADD COLUMN vpn_force TEXT NOT NULL DEFAULT '" + g.a(true, false) + "';");
                        sQLiteDatabase.execSQL("ALTER TABLE auth_settings ADD COLUMN vpn_gateway TEXT NOT NULL DEFAULT '" + g.a(true, b.am) + "';");
                        sQLiteDatabase.execSQL("ALTER TABLE auth_settings ADD COLUMN vpn_id TEXT NOT NULL DEFAULT '" + g.a(true, b.an) + "';");
                        sQLiteDatabase.execSQL("ALTER TABLE auth_settings ADD COLUMN vpn_password TEXT NOT NULL DEFAULT '" + g.a(true, "") + "';");
                        sQLiteDatabase.execSQL("ALTER TABLE auth_settings ADD COLUMN vpn_type TEXT NOT NULL DEFAULT '" + g.a(true, 2) + "';");
                        sQLiteDatabase.execSQL("ALTER TABLE auth_settings ADD COLUMN vpn_user_cert_alias TEXT NOT NULL DEFAULT '" + g.a(true, "") + "';");
                        obj = obj2;
                    } else if (i == 57) {
                        sQLiteDatabase.execSQL("CREATE TABLE custom_settings (_id INTEGER PRIMARY KEY,network_performance TEXT);");
                        sQLiteDatabase.insert("custom_settings", "network_performance", g.a(1, f.a()));
                        if (FgVoIP.U().aj()) {
                            sQLiteDatabase.execSQL("ALTER TABLE capabilities ADD COLUMN cap_cpm_standalone_msg TEXT NOT NULL DEFAULT '" + g.a(true, b.Y) + "';");
                            obj = obj2;
                        }
                    } else if (i == 58) {
                        sQLiteDatabase.execSQL("ALTER TABLE general_settings ADD COLUMN device_name TEXT NOT NULL DEFAULT '" + g.a(true, "") + "';");
                        sQLiteDatabase.execSQL("ALTER TABLE general_settings ADD COLUMN app_mode TEXT NOT NULL DEFAULT '" + g.a(true, 0) + "';");
                        obj = obj2;
                    } else if (i == 59) {
                        sQLiteDatabase.execSQL("ALTER TABLE general_settings ADD COLUMN sim_msisdn TEXT NOT NULL DEFAULT '" + g.a(true, "") + "';");
                        obj = obj2;
                    } else if (i == 61 && obj2 == null) {
                        sQLiteDatabase.execSQL("ALTER TABLE auth_settings ADD COLUMN vpn_routing_subnet TEXT NOT NULL DEFAULT '" + g.a(true, b.ao) + "';");
                        obj = obj2;
                    } else if (i == 62) {
                        sQLiteDatabase.execSQL("ALTER TABLE general_settings ADD COLUMN enable_status_icon TEXT NOT NULL DEFAULT '" + g.a(true, true) + "';");
                        obj = obj2;
                    } else if (i == 63) {
                        if (sQLiteDatabase.query("general_settings", new String[]{"mms_auto_download_messages"}, null, null, null, null, null) == null) {
                            sQLiteDatabase.execSQL("ALTER TABLE general_settings ADD COLUMN mms_auto_download_messages TEXT NOT NULL DEFAULT '" + g.a(true, b.y) + "';");
                        }
                        if (sQLiteDatabase.query("rcs_chat_ft", new String[]{"group_chat_factory_uri"}, null, null, null, null, null) == null && FgVoIP.U().aj()) {
                            sQLiteDatabase.execSQL("CREATE TABLE rcs_chat_ft (_id INTEGER PRIMARY KEY,group_chat_factory_uri TEXT);");
                            sQLiteDatabase.insert("rcs_chat_ft", "group_chat_factory_uri", g.a(1, t.a()));
                        }
                        obj = obj2;
                    } else if (i == 64) {
                        obj = obj2;
                    } else if (i == 65) {
                        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS lte_settings (_id INTEGER PRIMARY KEY,lte_800_capable TEXT,lte_800_user_activated TEXT,lte_800_network_enabled TEXT,lte_800_expiry TEXT,sbc_fqdns TEXT,sip_port TEXT,mnc TEXT,mcc TEXT,tac_min TEXT,tac_max TEXT);");
                        r0 = l.a();
                        r0.put("_id", Long.valueOf(b(sQLiteDatabase)));
                        sQLiteDatabase.insert("lte_settings", "sbc_fqdns", r0);
                        obj = obj2;
                    } else if (i == 66) {
                        a(sQLiteDatabase, "general_settings", "last_activated_app_vers", "TEXT", g.a(true, ""));
                        obj = obj2;
                    } else if (i == 68) {
                        a(sQLiteDatabase, "profile_settings", "double_hashed_msisdn", "TEXT", g.a(true, ""));
                        obj = obj2;
                    } else if (i == 69) {
                        a(sQLiteDatabase, "qos_settings", "acceptable_time_difference", "TEXT", g.a(true, 0));
                        obj = obj2;
                    } else if (i == 70) {
                        a(sQLiteDatabase, "profile_settings", "first_name", "TEXT", g.a(true, ""));
                        a(sQLiteDatabase, "profile_settings", "last_name", "TEXT", g.a(true, ""));
                        a(sQLiteDatabase, "profile_settings", "email", "TEXT", g.a(true, ""));
                        obj = obj2;
                    } else if (i == 71) {
                        a(sQLiteDatabase, "auth_settings", "iam_server_url", "TEXT", g.a(true, b.al));
                        obj = obj2;
                    } else if (i == 72) {
                        a(sQLiteDatabase, "lte_settings", "sip_port", "TEXT", g.a(true, b.ar));
                        obj = obj2;
                    } else if (i == 73) {
                        sQLiteDatabase.execSQL("CREATE TABLE vvm_settings (_id INTEGER PRIMARY KEY,vvm_download_mode TEXT,vvm_client_id TEXT,vvm_client_passwd TEXT,vvm_server_addr TEXT,vvm_server_port TEXT,vvm_tls_enabled TEXT,vvm_pin_num TEXT,vvm_folder_path TEXT,vvm_sit_encode_enabled TEXT);");
                        r0 = aa.a();
                        r0.put("_id", Long.valueOf(b(sQLiteDatabase)));
                        sQLiteDatabase.insert("vvm_settings", "vvm_client_id", r0);
                        obj = obj2;
                    } else if (i == 74) {
                        sQLiteDatabase.execSQL("ALTER TABLE sip_settings ADD COLUMN soc_family TEXT NOT NULL DEFAULT '" + g.a(true, b.j) + "';");
                        if (obj2 == null) {
                            sQLiteDatabase.execSQL("ALTER TABLE auth_settings ADD COLUMN vpn_sip_soc_family TEXT NOT NULL DEFAULT '" + g.a(true, b.ap) + "';");
                        }
                        obj = obj2;
                    } else if (i == 75) {
                        if (obj2 == null) {
                            sQLiteDatabase.execSQL("ALTER TABLE auth_settings ADD COLUMN auth_group TEXT NOT NULL DEFAULT '" + g.a(true, b.aa) + "';");
                            obj = obj2;
                        }
                    } else if (i == 76) {
                        a(sQLiteDatabase, "call_settings", "call_enable_ussd", "TEXT", g.a(true, b.p));
                        obj = obj2;
                    } else if (i == 77) {
                        a(sQLiteDatabase, "profile_settings", "network_imei", "TEXT", g.a(true, ""));
                        a(sQLiteDatabase, "qos_settings", "retry_login_timer", "TEXT", g.a(true, 0));
                        a(sQLiteDatabase, "general_settings", "contacts_display_order", "TEXT", g.a(true, 0));
                        a(sQLiteDatabase, "general_settings", "contacts_sort_order", "TEXT", g.a(true, 0));
                        new Handler().post(new Runnable(this) {
                            final /* synthetic */ a a;

                            {
                                this.a = r1;
                            }

                            public void run() {
                                FgVoIP.U().a("com.fgmicrotec.mobile.android.voip.LogoutFromServerReq");
                                FgVoIP.U().a(ClientSettingsProvider.A, "com.mavenir.android.action_backup_deactivated", -1);
                            }
                        });
                        obj = obj2;
                    } else if (i == 78) {
                        a(sQLiteDatabase, "call_settings", "call_echo_test_number", "TEXT", g.a(true, "+79779943530"));
                        obj = obj2;
                    } else if (i == 79) {
                        onCreate(sQLiteDatabase);
                        obj = obj2;
                    } else if (i == 80 || i == 81) {
                        k.b(true);
                        h.b(false);
                    }
                    obj = obj2;
                }
                i++;
                obj2 = obj;
            }
            return;
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }

        private long b(SQLiteDatabase sQLiteDatabase) {
            Exception e;
            Cursor cursor;
            Throwable th;
            Cursor cursor2 = null;
            try {
                long j;
                Cursor query = sQLiteDatabase.query("general_settings", new String[]{"active_profile_id"}, "_id=?", new String[]{"1"}, null, null, null);
                if (query != null) {
                    try {
                        if (query.moveToFirst()) {
                            j = query.getLong(0);
                            if (query == null && !query.isClosed()) {
                                query.close();
                                return j;
                            }
                        }
                    } catch (Exception e2) {
                        e = e2;
                        cursor = query;
                        try {
                            q.c("ClientSettingsProvider", e.getLocalizedMessage(), e.getCause());
                            if (cursor != null || cursor.isClosed()) {
                                return 1;
                            }
                            cursor.close();
                            return 1;
                        } catch (Throwable th2) {
                            th = th2;
                            cursor2 = cursor;
                            if (!(cursor2 == null || cursor2.isClosed())) {
                                cursor2.close();
                            }
                            throw th;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        cursor2 = query;
                        cursor2.close();
                        throw th;
                    }
                }
                j = 1;
                return query == null ? j : j;
            } catch (Exception e3) {
                e = e3;
                cursor = null;
                q.c("ClientSettingsProvider", e.getLocalizedMessage(), e.getCause());
                if (cursor != null) {
                }
                return 1;
            } catch (Throwable th4) {
                th = th4;
                cursor2.close();
                throw th;
            }
        }

        public void onOpen(SQLiteDatabase sQLiteDatabase) {
            super.onOpen(sQLiteDatabase);
            q.a(true);
        }

        public void a(SQLiteDatabase sQLiteDatabase, String str, String str2, String str3, String str4) {
            Cursor cursor = null;
            try {
                cursor = sQLiteDatabase.rawQuery("PRAGMA table_info(" + str + ")", null);
                if (cursor != null) {
                    while (cursor.moveToNext()) {
                        if (cursor.getString(cursor.getColumnIndex("name")).equals(str2)) {
                            q.a("ClientSettingsProvider", "alterTableAddColumn(): column exist: " + str2);
                            if (cursor != null) {
                                cursor.close();
                                return;
                            }
                            return;
                        }
                    }
                }
                q.a("ClientSettingsProvider", "alterTableAddColumn(): adding column: " + str2);
                if (cursor != null) {
                    cursor.close();
                }
            } catch (Exception e) {
                q.d("ClientSettingsProvider", "alterTableAddColumn(): " + e);
                if (cursor != null) {
                    cursor.close();
                }
            } catch (Throwable th) {
                if (cursor != null) {
                    cursor.close();
                }
            }
            StringBuilder append = new StringBuilder().append("ALTER TABLE ").append(str).append(" ADD COLUMN ").append(str2).append(" ").append(str3).append(" NOT NULL DEFAULT ");
            if (str3.toLowerCase().equals("text")) {
                str4 = "'" + str4 + "'";
            }
            sQLiteDatabase.execSQL(append.append(str4).append(";").toString());
        }
    }

    static {
        z.addURI("com.mavenir.provider.mingle", "general_settings", 21);
        z.addURI("com.mavenir.provider.mingle", "general_settings/#", 22);
        z.addURI("com.mavenir.provider.mingle", "profile_settings", 1);
        z.addURI("com.mavenir.provider.mingle", "profile_settings/#", 2);
        z.addURI("com.mavenir.provider.mingle", "sip_settings", 3);
        z.addURI("com.mavenir.provider.mingle", "sip_settings/#", 4);
        z.addURI("com.mavenir.provider.mingle", "rtp_settings", 5);
        z.addURI("com.mavenir.provider.mingle", "rtp_settings/#", 6);
        z.addURI("com.mavenir.provider.mingle", "call_settings", 7);
        z.addURI("com.mavenir.provider.mingle", "call_settings/#", 8);
        z.addURI("com.mavenir.provider.mingle", "vcc_settings", 39);
        z.addURI("com.mavenir.provider.mingle", "vcc_settings/#", 40);
        z.addURI("com.mavenir.provider.mingle", "nat_settings", 9);
        z.addURI("com.mavenir.provider.mingle", "nat_settings/#", 10);
        z.addURI("com.mavenir.provider.mingle", "wifi_settings", 11);
        z.addURI("com.mavenir.provider.mingle", "wifi_settings/#", 12);
        z.addURI("com.mavenir.provider.mingle", "media_settings", 13);
        z.addURI("com.mavenir.provider.mingle", "media_settings/#", 14);
        z.addURI("com.mavenir.provider.mingle", "atg_settings", 15);
        z.addURI("com.mavenir.provider.mingle", "atg_settings/#", 16);
        z.addURI("com.mavenir.provider.mingle", "sms_settings", 17);
        z.addURI("com.mavenir.provider.mingle", "sms_settings/#", 18);
        z.addURI("com.mavenir.provider.mingle", "developer_settings", 19);
        z.addURI("com.mavenir.provider.mingle", "developer_settings/#", 20);
        z.addURI("com.mavenir.provider.mingle", "qos_settings", 23);
        z.addURI("com.mavenir.provider.mingle", "qos_settings/#", 24);
        z.addURI("com.mavenir.provider.mingle", "qos_statistics", 25);
        z.addURI("com.mavenir.provider.mingle", "qos_statistics/#", 26);
        z.addURI("com.mavenir.provider.mingle", "exception_message", 33);
        z.addURI("com.mavenir.provider.mingle", "exception_message/#", 34);
        z.addURI("com.mavenir.provider.mingle", "emergency_numbers", 35);
        z.addURI("com.mavenir.provider.mingle", "emergency_numbers/#", 36);
        z.addURI("com.mavenir.provider.mingle", "native_numbers", 37);
        z.addURI("com.mavenir.provider.mingle", "native_numbers/#", 38);
        z.addURI("com.mavenir.provider.mingle", "whitelist", 27);
        z.addURI("com.mavenir.provider.mingle", "whitelist/#", 28);
        z.addURI("com.mavenir.provider.mingle", "capabilities", 29);
        z.addURI("com.mavenir.provider.mingle", "capabilities/#", 30);
        z.addURI("com.mavenir.provider.mingle", "rcs_presence", 31);
        z.addURI("com.mavenir.provider.mingle", "rcs_presence/#", 32);
        z.addURI("com.mavenir.provider.mingle", "auth_settings", 41);
        z.addURI("com.mavenir.provider.mingle", "auth_settings/#", 42);
        z.addURI("com.mavenir.provider.mingle", "custom_settings", 43);
        z.addURI("com.mavenir.provider.mingle", "custom_settings/#", 44);
        z.addURI("com.mavenir.provider.mingle", "rcs_chat_ft", 45);
        z.addURI("com.mavenir.provider.mingle", "rcs_chat_ft/#", 46);
        z.addURI("com.mavenir.provider.mingle", "lte_settings", 47);
        z.addURI("com.mavenir.provider.mingle", "lte_settings/#", 48);
        z.addURI("com.mavenir.provider.mingle", "vvm_settings", 49);
        z.addURI("com.mavenir.provider.mingle", "vvm_settings/#", 50);
        l.put("_id", "_id");
        l.put("provisioning_vers", "provisioning_vers");
        l.put("provisioning_imsi", "provisioning_imsi");
        l.put("provisioning_deactivated", "provisioning_deactivated");
        l.put("active_profile_id", "active_profile_id");
        l.put("login_status", "login_status");
        l.put("save_user_data", "save_user_data");
        l.put("start_after_phone_boot", "start_after_phone_boot");
        l.put("enable_status_icon", "enable_status_icon");
        l.put("enable_video_calls", "enable_video_calls");
        l.put("outgoing_calls_preference", "outgoing_calls_preference");
        l.put("sms_request_delivery_reports", "sms_request_delivery_reports");
        l.put("mms_auto_download_messages", "mms_auto_download_messages");
        l.put("sms_delete_old_messages", "sms_delete_old_messages");
        l.put("sms_messages_per_conversation_limit", "sms_messages_per_conversation_limit");
        l.put("sms_notify_incoming", "sms_notify_incoming");
        l.put("sms_notifications_ringtone", "sms_notifications_ringtone");
        l.put("enable_qos_test_ui", "enable_qos_test_ui");
        l.put("emergency_numbers", "emergency_numbers");
        l.put("msg_request_display_reports", "msg_request_display_reports");
        l.put("msg_allow_display_reports", "msg_allow_display_reports");
        l.put("enable_tcp_keep_alive", "enable_tcp_keep_alive");
        l.put("enable_messaging", "enable_messaging");
        l.put("contacts_display_order", "contacts_display_order");
        l.put("contacts_sort_order", "contacts_sort_order");
        l.put("last_root_check_time", "last_root_check_time");
        l.put("is_device_rooted", "is_device_rooted");
        l.put("device_name", "device_name");
        l.put("app_mode", "app_mode");
        l.put("sim_msisdn", "sim_msisdn");
        l.put("last_activated_app_vers", "last_activated_app_vers");
        a.put("_id", "_id");
        a.put("profile_name", "profile_name");
        a.put("display_name", "display_name");
        a.put("impu", "impu");
        a.put("impi", "impi");
        a.put("password", "password");
        a.put("hashed_msisdn", "hashed_msisdn");
        a.put("double_hashed_msisdn", "double_hashed_msisdn");
        a.put("msisdn", "msisdn");
        a.put("tac", "tac");
        a.put("svn", "svn");
        a.put("network_imsi", "network_imsi");
        a.put("cc_fqdn", "cc_fqdn");
        a.put("uuid", "uuid");
        a.put("reg_id", "reg_id");
        a.put("first_name", "first_name");
        a.put("last_name", "last_name");
        a.put("email", "email");
        a.put("network_imei", "network_imei");
        b.put("_id", "_id");
        b.put("sip_proxy", "sip_proxy");
        b.put("sip_port", "sip_port");
        b.put("sbc_fqdns", "sbc_fqdns");
        b.put("sip_local_port", "sip_local_port");
        b.put("sip_reg_exp_time", "sip_reg_exp_time");
        b.put("sip_transport", "sip_transport");
        b.put("sip_uri_format", "sip_uri_format");
        b.put("sip_route", "sip_route");
        b.put("sip_user_agent", "sip_user_agent");
        b.put("session_expires_time", "session_expires_time");
        b.put("min_session_expires_time", "min_session_expires_time");
        b.put("session_refresher", "session_refresher");
        b.put("soc_family", "soc_family");
        c.put("_id", "_id");
        c.put("rtp_local_port", "rtp_local_port");
        c.put("rtp_decoder_timeout", "rtp_decoder_timeout");
        c.put("rtp_use_rtcp", "rtp_use_rtcp");
        c.put("rtp_rtcp_period", "rtp_rtcp_period");
        c.put("srtp_mode", "srtp_mode");
        c.put("srtp_encryption_type", "srtp_encryption_type");
        c.put("srtp_auth_type", "srtp_auth_type");
        d.put("_id", "_id");
        d.put("call_uri_template", "call_uri_template");
        d.put("call_pickup_template", "call_pickup_template");
        d.put("call_conference_factory_uri", "call_conference_factory_uri");
        d.put("call_ut_server_address", "call_ut_server_address");
        d.put("call_ut_server_port", "call_ut_server_port");
        d.put("call_enable_ussd", "call_enable_ussd");
        d.put("call_echo_test_number", "call_echo_test_number");
        e.put("_id", "_id");
        e.put("cs_enable", "cs_enable");
        e.put("cs_threshold", "cs_threshold");
        e.put("cs_number", "cs_number");
        f.put("_id", "_id");
        f.put("nat_traversal_mode", "nat_traversal_mode");
        f.put("nat_stun_server", "nat_stun_server");
        f.put("nat_stun_port", "nat_stun_port");
        f.put("nat_stun_query_mode", "nat_stun_query_mode");
        f.put("nat_sip_keep_alive", "nat_sip_keep_alive");
        f.put("tcp_keep_alive_idle_time", "tcp_keep_alive_idle_time");
        f.put("tcp_keep_alive_probe_time", "tcp_keep_alive_probe_time");
        f.put("tcp_keep_alive_probes_count", "tcp_keep_alive_probes_count");
        g.put("_id", "_id");
        g.put("connection_preference", "connection_preference");
        g.put("wifi_call_manual_selection", "wifi_call_manual_selection");
        g.put("wifi_weak_upper_threshold", "wifi_weak_upper_threshold");
        g.put("wifi_weak_lower_threshold", "wifi_weak_lower_threshold");
        g.put("wifi_weak_timeout", "wifi_weak_timeout");
        h.put("_id", "_id");
        h.put("media_codec_type", "media_codec_type");
        h.put("media_codec_mode", "media_codec_mode");
        h.put("amr_format_octet_aligned", "amr_format_octet_aligned");
        h.put("media_overflow_mark", "media_overflow_mark");
        h.put("media_high_watermark", "media_high_watermark");
        h.put("media_low_watermark", "media_low_watermark");
        h.put("media_ati_on_off", "media_ati_on_off");
        h.put("media_dtmf_signalization", "media_dtmf_signalization");
        h.put("media_alert_volume", "media_alert_volume");
        h.put("media_video_codec_type", "media_video_codec_type");
        h.put("media_video_size_index", "media_video_size_index");
        i.put("_id", "_id");
        i.put("acid_string", "acid_string");
        i.put("atg_state", "atg_state");
        j.put("_id", "_id");
        j.put("sms_service_center", "sms_service_center");
        j.put("preferred_messaging_protocol", "preferred_messaging_protocol");
        j.put("reference_number", "reference_number");
        j.put("sms_validity", "sms_validity");
        j.put("enc_nat_number", "enc_nat_number");
        j.put("enc_intl_number", "enc_intl_number");
        k.put("_id", "_id");
        k.put("trace_write_mode", "trace_write_mode");
        k.put("trace_enabled", "trace_enabled");
        k.put("trace_level_data", "trace_level_data");
        k.put("trace_level_external", "trace_level_external");
        k.put("trace_level_gui", "trace_level_gui");
        k.put("trace_level_media", "trace_level_media");
        k.put("trace_level_sip", "trace_level_sip");
        k.put("enable_qos_test_ui", "enable_qos_test_ui");
        k.put("enable_adb_hooks", "enable_adb_hooks");
        m.put("_id", "_id");
        m.put("validity", "validity");
        m.put("configuration_time", "configuration_time");
        m.put("min_wifi_threshold", "min_wifi_threshold");
        m.put("wifi_hysteresis_timer", "wifi_hysteresis_timer");
        m.put("next_sbc_timer", "next_sbc_timer");
        m.put("hs_lost_timer", "hs_lost_timer");
        m.put("stay_alive_timer", "stay_alive_timer");
        m.put("retry_login_timer", "retry_login_timer");
        m.put("no_media_timer", "no_media_timer");
        m.put("wifi_qos_threshold", "wifi_qos_threshold");
        m.put("max_jitter", "max_jitter");
        m.put("max_round_trip_delay", "max_round_trip_delay");
        m.put("rtp_max_cumulative_loss", "rtp_max_cumulative_loss");
        m.put("rtp_max_fraction_loss", "rtp_max_fraction_loss");
        m.put("reg_no_resp_retry_tmr", "reg_no_resp_retry_tmr");
        m.put("inv_early_no_resp_timeout", "inv_early_no_resp_timeout");
        m.put("codecs", "codecs");
        m.put("back_off_timer_list", "back_off_timer_list");
        m.put("provisioning_fqdn", "provisioning_fqdn");
        m.put("provisioning_fqdn_2", "provisioning_fqdn_2");
        m.put("public_ip_service_url", "public_ip_service_url");
        m.put("user_deactivation_message", "user_deactivation_message");
        m.put("tls_cert_revoke_tmr", "tls_cert_revoke_tmr");
        m.put("tls_session_cache_tmr", "tls_session_cache_tmr");
        m.put("acceptable_time_difference", "acceptable_time_difference");
        n.put("_id", "_id");
        n.put("wifi_signal_strength", "wifi_signal_strength");
        n.put("jitter", "jitter");
        n.put("round_trip_delay", "round_trip_delay");
        n.put("cumulative_loss", "cumulative_loss");
        n.put("fraction_loss", "fraction_loss");
        n.put("timestamp", "timestamp");
        o.put("_id", "_id");
        o.put("exception_id", "exception_id");
        o.put("exception_message", "exception_message");
        o.put("exception_url", "exception_url");
        p.put("_id", "_id");
        p.put("ssid", "ssid");
        p.put("bssid", "bssid");
        p.put("ignore", "ignore");
        q.put("_id", "_id");
        q.put("profile_id", "profile_id");
        q.put("number", "number");
        r.put("_id", "_id");
        r.put("profile_id", "profile_id");
        r.put("number", "number");
        s.put("_id", "_id");
        s.put("capability_info_expiry", "capability_info_expiry");
        s.put("capability_polling_period", "capability_polling_period");
        s.put("last_info_update_time", "last_info_update_time");
        s.put("last_polling_update_time", "last_polling_update_time");
        s.put("cap_presence_discovery", "cap_presence_discovery");
        s.put("cap_sip_messaging", "cap_sip_messaging");
        s.put("cap_im_session", "cap_im_session");
        s.put("cap_file_transfer", "cap_file_transfer");
        s.put("cap_image_share", "cap_image_share");
        s.put("cap_video_share", "cap_video_share");
        s.put("cap_ip_audio_call", "cap_ip_audio_call");
        s.put("cap_ip_video_call", "cap_ip_video_call");
        s.put("cap_social_presence", "cap_social_presence");
        s.put("cap_geolocation_push", "cap_geolocation_push");
        s.put("cap_geolocation_pull", "cap_geolocation_pull");
        s.put("cap_geolocation_pull_ft", "cap_geolocation_pull_ft");
        s.put("cap_cpm_chat", "cap_cpm_chat");
        s.put("cap_cpm_ft", "cap_cpm_ft");
        s.put("cap_cpm_standalone_msg", "cap_cpm_standalone_msg");
        t.put("_id", "_id");
        t.put("xdms_root_uri", "xdms_root_uri");
        t.put("xdms_proxy_address", "xdms_proxy_address");
        t.put("xdms_proxy_port", "xdms_proxy_port");
        t.put("presence_enabled", "presence_enabled");
        u.put("_id", "_id");
        u.put("group_chat_factory_uri", "group_chat_factory_uri");
        v.put("_id", "_id");
        v.put("auth_group", "auth_group");
        v.put("oauth_client_id", "oauth_client_id");
        v.put("oauth_client_secret", "oauth_client_secret");
        v.put("oauth_scope", "oauth_scope");
        v.put("oauth_redirect_uri", "oauth_redirect_uri");
        v.put("oauth_auth_endpoint", "oauth_auth_endpoint");
        v.put("oauth_token_endpoint", "oauth_token_endpoint");
        v.put("oauth_revoke_endpoint", "oauth_revoke_endpoint");
        v.put("oauth_device_agent", "oauth_device_agent");
        v.put("es_server_url", "es_server_url");
        v.put("wsg_server_url", "wsg_server_url");
        v.put("iam_server_url", "iam_server_url");
        v.put("key_private", "key_private");
        v.put("key_public", "key_public");
        v.put("cert", "cert");
        v.put("epdg_address", "epdg_address");
        v.put("eap_prefix", "eap_prefix");
        v.put("vimsi", "vimsi");
        v.put("realm", "realm");
        v.put("device_id", "device_id");
        v.put("vpn_force", "vpn_force");
        v.put("vpn_gateway", "vpn_gateway");
        v.put("vpn_id", "vpn_id");
        v.put("vpn_password", "vpn_password");
        v.put("vpn_type", "vpn_type");
        v.put("vpn_user_cert_alias", "vpn_user_cert_alias");
        v.put("vpn_routing_subnet", "vpn_routing_subnet");
        v.put("vpn_sip_soc_family", "vpn_sip_soc_family");
        x.put("_id", "_id");
        x.put("network_performance", "network_performance");
        y.put("_id", "_id");
        y.put("lte_800_capable", "lte_800_capable");
        y.put("lte_800_user_activated", "lte_800_user_activated");
        y.put("lte_800_network_enabled", "lte_800_network_enabled");
        y.put("lte_800_expiry", "lte_800_expiry");
        y.put("sbc_fqdns", "sbc_fqdns");
        y.put("sip_port", "sip_port");
        y.put("mnc", "mnc");
        y.put("mcc", "mcc");
        y.put("tac_min", "tac_min");
        y.put("tac_max", "tac_max");
        w.put("_id", "_id");
        w.put("vvm_download_mode", "vvm_download_mode");
        w.put("vvm_client_id", "vvm_client_id");
        w.put("vvm_client_passwd", "vvm_client_passwd");
        w.put("vvm_server_addr", "vvm_server_addr");
        w.put("vvm_server_port", "vvm_server_port");
        w.put("vvm_tls_enabled", "vvm_tls_enabled");
        w.put("vvm_pin_num", "vvm_pin_num");
        w.put("vvm_folder_path", "vvm_folder_path");
        w.put("vvm_sit_encode_enabled", "vvm_sit_encode_enabled");
    }

    public boolean onCreate() {
        A = getContext();
        this.B = new a(getContext());
        return true;
    }

    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
        switch (z.match(uri)) {
            case 1:
                sQLiteQueryBuilder.setTables("profile_settings");
                sQLiteQueryBuilder.setProjectionMap(a);
                break;
            case 2:
                sQLiteQueryBuilder.setTables("profile_settings");
                sQLiteQueryBuilder.setProjectionMap(a);
                sQLiteQueryBuilder.appendWhere("_id=" + uri.getLastPathSegment());
                break;
            case 3:
                sQLiteQueryBuilder.setTables("sip_settings");
                sQLiteQueryBuilder.setProjectionMap(b);
                break;
            case 4:
                sQLiteQueryBuilder.setTables("sip_settings");
                sQLiteQueryBuilder.setProjectionMap(b);
                sQLiteQueryBuilder.appendWhere("_id=" + uri.getLastPathSegment());
                break;
            case 5:
                sQLiteQueryBuilder.setTables("rtp_settings");
                sQLiteQueryBuilder.setProjectionMap(c);
                break;
            case 6:
                sQLiteQueryBuilder.setTables("rtp_settings");
                sQLiteQueryBuilder.setProjectionMap(c);
                sQLiteQueryBuilder.appendWhere("_id=" + uri.getLastPathSegment());
                break;
            case 7:
                sQLiteQueryBuilder.setTables("call_settings");
                sQLiteQueryBuilder.setProjectionMap(d);
                break;
            case 8:
                sQLiteQueryBuilder.setTables("call_settings");
                sQLiteQueryBuilder.setProjectionMap(d);
                sQLiteQueryBuilder.appendWhere("_id=" + uri.getLastPathSegment());
                break;
            case 9:
                sQLiteQueryBuilder.setTables("nat_settings");
                sQLiteQueryBuilder.setProjectionMap(f);
                break;
            case 10:
                sQLiteQueryBuilder.setTables("nat_settings");
                sQLiteQueryBuilder.setProjectionMap(f);
                sQLiteQueryBuilder.appendWhere("_id=" + uri.getLastPathSegment());
                break;
            case 11:
                sQLiteQueryBuilder.setTables("wifi_settings");
                sQLiteQueryBuilder.setProjectionMap(g);
                break;
            case 12:
                sQLiteQueryBuilder.setTables("wifi_settings");
                sQLiteQueryBuilder.setProjectionMap(g);
                sQLiteQueryBuilder.appendWhere("_id=" + uri.getLastPathSegment());
                break;
            case 13:
                sQLiteQueryBuilder.setTables("media_settings");
                sQLiteQueryBuilder.setProjectionMap(h);
                break;
            case 14:
                sQLiteQueryBuilder.setTables("media_settings");
                sQLiteQueryBuilder.setProjectionMap(h);
                sQLiteQueryBuilder.appendWhere("_id=" + uri.getLastPathSegment());
                break;
            case 15:
                sQLiteQueryBuilder.setTables("atg_settings");
                sQLiteQueryBuilder.setProjectionMap(i);
                break;
            case 16:
                sQLiteQueryBuilder.setTables("atg_settings");
                sQLiteQueryBuilder.setProjectionMap(i);
                sQLiteQueryBuilder.appendWhere("_id=" + uri.getLastPathSegment());
                break;
            case 17:
                sQLiteQueryBuilder.setTables("sms_settings");
                sQLiteQueryBuilder.setProjectionMap(j);
                break;
            case 18:
                sQLiteQueryBuilder.setTables("sms_settings");
                sQLiteQueryBuilder.setProjectionMap(j);
                sQLiteQueryBuilder.appendWhere("_id=" + uri.getLastPathSegment());
                break;
            case 19:
                sQLiteQueryBuilder.setTables("developer_settings");
                sQLiteQueryBuilder.setProjectionMap(k);
                break;
            case VoIP.ERR_SIP_TIMED_OUT /*20*/:
                sQLiteQueryBuilder.setTables("developer_settings");
                sQLiteQueryBuilder.setProjectionMap(k);
                sQLiteQueryBuilder.appendWhere("_id=" + uri.getLastPathSegment());
                break;
            case VoIP.ERR_SESSION_NOT_FOUND /*21*/:
                sQLiteQueryBuilder.setTables("general_settings");
                sQLiteQueryBuilder.setProjectionMap(l);
                break;
            case VoIP.ERR_TRANSPORT_ERROR /*22*/:
                sQLiteQueryBuilder.setTables("general_settings");
                sQLiteQueryBuilder.setProjectionMap(l);
                sQLiteQueryBuilder.appendWhere("_id=" + uri.getLastPathSegment());
                break;
            case VoIP.ERR_SOCKET_CONNECTION_BROKE_NET_DOWN /*23*/:
                sQLiteQueryBuilder.setTables("qos_settings");
                sQLiteQueryBuilder.setProjectionMap(m);
                break;
            case VoIP.ERR_SDP_NEGOTIATION_FAILED /*24*/:
                sQLiteQueryBuilder.setTables("qos_settings");
                sQLiteQueryBuilder.setProjectionMap(m);
                sQLiteQueryBuilder.appendWhere("_id=" + uri.getLastPathSegment());
                break;
            case VoIP.ERR_NO_SRTP_AGREEMENT /*25*/:
                sQLiteQueryBuilder.setTables("qos_statistics");
                sQLiteQueryBuilder.setProjectionMap(n);
                break;
            case VoIP.ERR_SOCKET_CONNECTION_TLS_ERROR /*26*/:
                sQLiteQueryBuilder.setTables("qos_statistics");
                sQLiteQueryBuilder.setProjectionMap(n);
                sQLiteQueryBuilder.appendWhere("_id=" + uri.getLastPathSegment());
                break;
            case VoIP.ERR_SIP_BYE_TIMEOUT /*27*/:
                sQLiteQueryBuilder.setTables("whitelist");
                sQLiteQueryBuilder.setProjectionMap(p);
                break;
            case VoIP.ERR_SIP_REGISTER_PORT_MISMATCH /*28*/:
                sQLiteQueryBuilder.setTables("whitelist");
                sQLiteQueryBuilder.setProjectionMap(p);
                sQLiteQueryBuilder.appendWhere("_id=" + uri.getLastPathSegment());
                break;
            case VoIP.ERR_SIP_REGISTER_NOT_ALLOWED /*29*/:
                sQLiteQueryBuilder.setTables("capabilities");
                sQLiteQueryBuilder.setProjectionMap(s);
                break;
            case VoIP.ERR_SIP_REGISTER_SOCKET_INTERNAL /*30*/:
                sQLiteQueryBuilder.setTables("capabilities");
                sQLiteQueryBuilder.setProjectionMap(s);
                sQLiteQueryBuilder.appendWhere("_id=" + uri.getLastPathSegment());
                break;
            case VoIP.ERR_SIP_CALL_DOES_NOT_EXIST /*31*/:
                sQLiteQueryBuilder.setTables("rcs_presence");
                sQLiteQueryBuilder.setProjectionMap(t);
                break;
            case 32:
                sQLiteQueryBuilder.setTables("rcs_presence");
                sQLiteQueryBuilder.setProjectionMap(t);
                sQLiteQueryBuilder.appendWhere("_id=" + uri.getLastPathSegment());
                break;
            case 33:
                sQLiteQueryBuilder.setTables("exception_message");
                sQLiteQueryBuilder.setProjectionMap(o);
                break;
            case 34:
                sQLiteQueryBuilder.setTables("exception_message");
                sQLiteQueryBuilder.setProjectionMap(o);
                sQLiteQueryBuilder.appendWhere("_id=" + uri.getLastPathSegment());
                break;
            case 35:
                sQLiteQueryBuilder.setTables("emergency_numbers");
                sQLiteQueryBuilder.setProjectionMap(q);
                break;
            case 36:
                sQLiteQueryBuilder.setTables("emergency_numbers");
                sQLiteQueryBuilder.setProjectionMap(q);
                sQLiteQueryBuilder.appendWhere("_id=" + uri.getLastPathSegment());
                break;
            case 37:
                sQLiteQueryBuilder.setTables("native_numbers");
                sQLiteQueryBuilder.setProjectionMap(r);
                break;
            case 38:
                sQLiteQueryBuilder.setTables("native_numbers");
                sQLiteQueryBuilder.setProjectionMap(r);
                sQLiteQueryBuilder.appendWhere("_id=" + uri.getLastPathSegment());
                break;
            case 39:
                sQLiteQueryBuilder.setTables("vcc_settings");
                sQLiteQueryBuilder.setProjectionMap(e);
                break;
            case 40:
                sQLiteQueryBuilder.setTables("vcc_settings");
                sQLiteQueryBuilder.setProjectionMap(e);
                sQLiteQueryBuilder.appendWhere("_id=" + uri.getLastPathSegment());
                break;
            case 41:
                sQLiteQueryBuilder.setTables("auth_settings");
                sQLiteQueryBuilder.setProjectionMap(v);
                break;
            case 42:
                sQLiteQueryBuilder.setTables("auth_settings");
                sQLiteQueryBuilder.setProjectionMap(v);
                sQLiteQueryBuilder.appendWhere("_id=" + uri.getLastPathSegment());
                break;
            case 43:
                sQLiteQueryBuilder.setTables("custom_settings");
                sQLiteQueryBuilder.setProjectionMap(x);
                break;
            case 44:
                sQLiteQueryBuilder.setTables("custom_settings");
                sQLiteQueryBuilder.setProjectionMap(x);
                sQLiteQueryBuilder.appendWhere("_id=" + uri.getLastPathSegment());
                break;
            case 45:
                sQLiteQueryBuilder.setTables("rcs_chat_ft");
                sQLiteQueryBuilder.setProjectionMap(u);
                break;
            case 46:
                sQLiteQueryBuilder.setTables("rcs_chat_ft");
                sQLiteQueryBuilder.setProjectionMap(u);
                sQLiteQueryBuilder.appendWhere("_id=" + uri.getLastPathSegment());
                break;
            case 47:
                sQLiteQueryBuilder.setTables("lte_settings");
                sQLiteQueryBuilder.setProjectionMap(y);
                break;
            case 48:
                sQLiteQueryBuilder.setTables("lte_settings");
                sQLiteQueryBuilder.setProjectionMap(y);
                sQLiteQueryBuilder.appendWhere("_id=" + uri.getLastPathSegment());
                break;
            case 49:
                sQLiteQueryBuilder.setTables("vvm_settings");
                sQLiteQueryBuilder.setProjectionMap(w);
                break;
            case 50:
                sQLiteQueryBuilder.setTables("vvm_settings");
                sQLiteQueryBuilder.setProjectionMap(w);
                sQLiteQueryBuilder.appendWhere("_id=" + uri.getLastPathSegment());
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        Cursor query = sQLiteQueryBuilder.query(this.B.getReadableDatabase(), strArr, str, strArr2, null, null, str2, null);
        query.setNotificationUri(getContext().getContentResolver(), uri);
        return query;
    }

    public String getType(Uri uri) {
        switch (z.match(uri)) {
            case 1:
                return "vnd.android.cursor.dir/vnd.mavenir.provider.client.profile_settings";
            case 2:
                return "vnd.android.cursor.item/vnd.mavenir.provider.client.profile_settings";
            case 3:
                return "vnd.android.cursor.dir/vnd.mavenir.provider.client.sip_settings";
            case 4:
                return "vnd.android.cursor.item/vnd.mavenir.provider.client.sip_settings";
            case 5:
                return "vnd.android.cursor.dir/vnd.mavenir.provider.client.rtp_settings";
            case 6:
                return "vnd.android.cursor.item/vnd.mavenir.provider.client.rtp_settings";
            case 7:
                return "vnd.android.cursor.dir/vnd.mavenir.provider.client.call_settings";
            case 8:
                return "vnd.android.cursor.item/vnd.mavenir.provider.client.call_settings";
            case 9:
                return "vnd.android.cursor.dir/vnd.mavenir.provider.client.nat_settings";
            case 10:
                return "vnd.android.cursor.item/vnd.mavenir.provider.client.nat_settings";
            case 11:
                return "vnd.android.cursor.dir/vnd.mavenir.provider.client.wifi_settings";
            case 12:
                return "vnd.android.cursor.item/vnd.mavenir.provider.client.wifi_settings";
            case 13:
                return "vnd.android.cursor.dir/vnd.mavenir.provider.client.media_settings";
            case 14:
                return "vnd.android.cursor.item/vnd.mavenir.provider.client.media_settings";
            case 15:
                return "vnd.android.cursor.dir/vnd.mavenir.provider.client.atg_settings";
            case 16:
                return "vnd.android.cursor.item/vnd.mavenir.provider.client.atg_settings";
            case 17:
                return "vnd.android.cursor.dir/vnd.mavenir.provider.client.sms_settings";
            case 18:
                return "vnd.android.cursor.item/vnd.mavenir.provider.client.sms_settings";
            case 19:
                return "vnd.android.cursor.dir/vnd.mavenir.provider.client.developer_settings";
            case VoIP.ERR_SIP_TIMED_OUT /*20*/:
                return "vnd.android.cursor.item/vnd.mavenir.provider.client.developer_settings";
            case VoIP.ERR_SESSION_NOT_FOUND /*21*/:
                return "vnd.android.cursor.dir/vnd.mavenir.provider.client.general_settings";
            case VoIP.ERR_TRANSPORT_ERROR /*22*/:
                return "vnd.android.cursor.item/vnd.mavenir.provider.client.general_settings";
            case VoIP.ERR_SOCKET_CONNECTION_BROKE_NET_DOWN /*23*/:
                return "vnd.android.cursor.dir/vnd.mavenir.provider.client.qos_settings";
            case VoIP.ERR_SDP_NEGOTIATION_FAILED /*24*/:
                return "vnd.android.cursor.item/vnd.mavenir.provider.client.qos_settings";
            case VoIP.ERR_NO_SRTP_AGREEMENT /*25*/:
                return "vnd.android.cursor.dir/vnd.mavenir.provider.client.qos_statistics";
            case VoIP.ERR_SOCKET_CONNECTION_TLS_ERROR /*26*/:
                return "vnd.android.cursor.item/vnd.mavenir.provider.client.qos_statistics";
            case VoIP.ERR_SIP_BYE_TIMEOUT /*27*/:
                return "vnd.android.cursor.dir/vnd.mavenir.provider.client.whitelist";
            case VoIP.ERR_SIP_REGISTER_PORT_MISMATCH /*28*/:
                return "vnd.android.cursor.item/vnd.mavenir.provider.client.whitelist";
            case VoIP.ERR_SIP_REGISTER_NOT_ALLOWED /*29*/:
                return "vnd.android.cursor.dir/vnd.mavenir.provider.client.capabilities";
            case VoIP.ERR_SIP_REGISTER_SOCKET_INTERNAL /*30*/:
                return "vnd.android.cursor.item/vnd.mavenir.provider.client.capabilities";
            case VoIP.ERR_SIP_CALL_DOES_NOT_EXIST /*31*/:
                return "vnd.android.cursor.dir/vnd.mavenir.provider.client.rcs_presence";
            case 32:
                return "vnd.android.cursor.item/vnd.mavenir.provider.client.rcs_presence";
            case 33:
                return "vnd.android.cursor.dir/vnd.mavenir.provider.client.exception_message";
            case 34:
                return "vnd.android.cursor.item/vnd.mavenir.provider.client.exception_message";
            case 35:
                return "vnd.android.cursor.dir/vnd.mavenir.provider.client.emergency_numbers";
            case 36:
                return "vnd.android.cursor.item/vnd.mavenir.provider.client.emergency_numbers";
            case 37:
                return "vnd.android.cursor.dir/vnd.mavenir.provider.client.native_numbers";
            case 38:
                return "vnd.android.cursor.item/vnd.mavenir.provider.client.native_numbers";
            case 39:
                return "vnd.android.cursor.dir/vnd.mavenir.provider.client.vcc_settings";
            case 40:
                return "vnd.android.cursor.item/vnd.mavenir.provider.client.vcc_settings";
            case 41:
                return "vnd.android.cursor.dir/vnd.mavenir.provider.client.auth_settings";
            case 42:
                return "vnd.android.cursor.item/vnd.mavenir.provider.client.auth_settings";
            case 43:
                return "vnd.android.cursor.dir/vnd.mavenir.provider.client.custom_settings";
            case 44:
                return "vnd.android.cursor.item/vnd.mavenir.provider.client.custom_settings";
            case 45:
                return "vnd.android.cursor.dir/vnd.mavenir.provider.client.rcs_chat_ft";
            case 46:
                return "vnd.android.cursor.item/vnd.mavenir.provider.client.rcs_chat_ft";
            case 47:
                return "vnd.android.cursor.dir/vnd.mavenir.provider.client.lte_settings";
            case 48:
                return "vnd.android.cursor.item/vnd.mavenir.provider.client.lte_settings";
            case 49:
                return "vnd.android.cursor.dir/vnd.mavenir.provider.client.vvm_settings";
            case 50:
                return "vnd.android.cursor.item/vnd.mavenir.provider.client.vvm_settings";
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
    }

    public Uri insert(Uri uri, ContentValues contentValues) {
        String str;
        switch (z.match(uri)) {
            case 1:
                str = "profile_settings";
                break;
            case 3:
                str = "sip_settings";
                break;
            case 5:
                str = "rtp_settings";
                break;
            case 7:
                str = "call_settings";
                break;
            case 9:
                str = "nat_settings";
                break;
            case 11:
                str = "wifi_settings";
                break;
            case 13:
                str = "media_settings";
                break;
            case 15:
                str = "atg_settings";
                break;
            case 17:
                str = "sms_settings";
                break;
            case 19:
                str = "developer_settings";
                break;
            case VoIP.ERR_SESSION_NOT_FOUND /*21*/:
                str = "general_settings";
                break;
            case VoIP.ERR_SOCKET_CONNECTION_BROKE_NET_DOWN /*23*/:
                str = "qos_settings";
                break;
            case VoIP.ERR_NO_SRTP_AGREEMENT /*25*/:
                str = "qos_statistics";
                break;
            case VoIP.ERR_SIP_BYE_TIMEOUT /*27*/:
                str = "whitelist";
                break;
            case VoIP.ERR_SIP_REGISTER_NOT_ALLOWED /*29*/:
                str = "capabilities";
                break;
            case VoIP.ERR_SIP_CALL_DOES_NOT_EXIST /*31*/:
                str = "rcs_presence";
                break;
            case 33:
                str = "exception_message";
                break;
            case 35:
                str = "emergency_numbers";
                break;
            case 37:
                str = "native_numbers";
                break;
            case 39:
                str = "vcc_settings";
                break;
            case 41:
                str = "auth_settings";
                break;
            case 43:
                str = "custom_settings";
                break;
            case 45:
                str = "rcs_chat_ft";
                break;
            case 47:
                str = "lte_settings";
                break;
            case 49:
                str = "vvm_settings";
                break;
            default:
                throw new IllegalArgumentException("Unknown or unsupported delete URI " + uri);
        }
        long insert = this.B.getWritableDatabase().insert(str, null, contentValues);
        if (insert > -1) {
            Uri withAppendedId = ContentUris.withAppendedId(uri, insert);
            getContext().getContentResolver().notifyChange(withAppendedId, null);
            return withAppendedId;
        }
        throw new SQLException("Failed to insert row into " + uri);
    }

    public int delete(Uri uri, String str, String[] strArr) {
        int delete;
        SQLiteDatabase writableDatabase = this.B.getWritableDatabase();
        switch (z.match(uri)) {
            case 1:
                delete = writableDatabase.delete("profile_settings", str, strArr);
                break;
            case 2:
                delete = writableDatabase.delete("profile_settings", "_id=?", new String[]{String.valueOf(ContentUris.parseId(uri))});
                break;
            case 3:
                delete = writableDatabase.delete("sip_settings", str, strArr);
                break;
            case 4:
                delete = writableDatabase.delete("sip_settings", "_id=?", new String[]{String.valueOf(ContentUris.parseId(uri))});
                break;
            case 5:
                delete = writableDatabase.delete("rtp_settings", str, strArr);
                break;
            case 6:
                delete = writableDatabase.delete("rtp_settings", "_id=?", new String[]{String.valueOf(ContentUris.parseId(uri))});
                break;
            case 7:
                delete = writableDatabase.delete("call_settings", str, strArr);
                break;
            case 8:
                delete = writableDatabase.delete("call_settings", "_id=?", new String[]{String.valueOf(ContentUris.parseId(uri))});
                break;
            case 9:
                delete = writableDatabase.delete("nat_settings", str, strArr);
                break;
            case 10:
                delete = writableDatabase.delete("nat_settings", "_id=?", new String[]{String.valueOf(ContentUris.parseId(uri))});
                break;
            case 11:
                delete = writableDatabase.delete("wifi_settings", str, strArr);
                break;
            case 12:
                delete = writableDatabase.delete("wifi_settings", "_id=?", new String[]{String.valueOf(ContentUris.parseId(uri))});
                break;
            case 13:
                delete = writableDatabase.delete("media_settings", str, strArr);
                break;
            case 14:
                delete = writableDatabase.delete("media_settings", "_id=?", new String[]{String.valueOf(ContentUris.parseId(uri))});
                break;
            case 15:
                delete = writableDatabase.delete("atg_settings", str, strArr);
                break;
            case 16:
                delete = writableDatabase.delete("atg_settings", "_id=?", new String[]{String.valueOf(ContentUris.parseId(uri))});
                break;
            case 17:
                delete = writableDatabase.delete("sms_settings", str, strArr);
                break;
            case 18:
                delete = writableDatabase.delete("sms_settings", "_id=?", new String[]{String.valueOf(ContentUris.parseId(uri))});
                break;
            case 19:
                delete = writableDatabase.delete("developer_settings", str, strArr);
                break;
            case VoIP.ERR_SIP_TIMED_OUT /*20*/:
                delete = writableDatabase.delete("developer_settings", "_id=?", new String[]{String.valueOf(ContentUris.parseId(uri))});
                break;
            case VoIP.ERR_SESSION_NOT_FOUND /*21*/:
                delete = writableDatabase.delete("general_settings", str, strArr);
                break;
            case VoIP.ERR_TRANSPORT_ERROR /*22*/:
                delete = writableDatabase.delete("general_settings", "_id=?", new String[]{String.valueOf(ContentUris.parseId(uri))});
                break;
            case VoIP.ERR_SOCKET_CONNECTION_BROKE_NET_DOWN /*23*/:
                delete = writableDatabase.delete("qos_settings", str, strArr);
                break;
            case VoIP.ERR_SDP_NEGOTIATION_FAILED /*24*/:
                delete = writableDatabase.delete("qos_settings", "_id=?", new String[]{String.valueOf(ContentUris.parseId(uri))});
                break;
            case VoIP.ERR_NO_SRTP_AGREEMENT /*25*/:
                delete = writableDatabase.delete("qos_statistics", str, strArr);
                break;
            case VoIP.ERR_SOCKET_CONNECTION_TLS_ERROR /*26*/:
                delete = writableDatabase.delete("qos_statistics", "_id=?", new String[]{String.valueOf(ContentUris.parseId(uri))});
                break;
            case VoIP.ERR_SIP_BYE_TIMEOUT /*27*/:
                delete = writableDatabase.delete("whitelist", str, strArr);
                break;
            case VoIP.ERR_SIP_REGISTER_PORT_MISMATCH /*28*/:
                delete = writableDatabase.delete("whitelist", "_id=?", new String[]{String.valueOf(ContentUris.parseId(uri))});
                break;
            case VoIP.ERR_SIP_REGISTER_NOT_ALLOWED /*29*/:
                delete = writableDatabase.delete("capabilities", str, strArr);
                break;
            case VoIP.ERR_SIP_REGISTER_SOCKET_INTERNAL /*30*/:
                delete = writableDatabase.delete("capabilities", "_id=?", new String[]{String.valueOf(ContentUris.parseId(uri))});
                break;
            case VoIP.ERR_SIP_CALL_DOES_NOT_EXIST /*31*/:
                delete = writableDatabase.delete("rcs_presence", str, strArr);
                break;
            case 32:
                delete = writableDatabase.delete("rcs_presence", "_id=?", new String[]{String.valueOf(ContentUris.parseId(uri))});
                break;
            case 33:
                delete = writableDatabase.delete("exception_message", str, strArr);
                break;
            case 34:
                delete = writableDatabase.delete("exception_message", "_id=?", new String[]{String.valueOf(ContentUris.parseId(uri))});
                break;
            case 35:
                delete = writableDatabase.delete("emergency_numbers", str, strArr);
                break;
            case 36:
                delete = writableDatabase.delete("emergency_numbers", "_id=?", new String[]{String.valueOf(ContentUris.parseId(uri))});
                break;
            case 37:
                delete = writableDatabase.delete("native_numbers", str, strArr);
                break;
            case 38:
                delete = writableDatabase.delete("native_numbers", "_id=?", new String[]{String.valueOf(ContentUris.parseId(uri))});
                break;
            case 39:
                delete = writableDatabase.delete("vcc_settings", str, strArr);
                break;
            case 40:
                delete = writableDatabase.delete("vcc_settings", "_id=?", new String[]{String.valueOf(ContentUris.parseId(uri))});
                break;
            case 41:
                delete = writableDatabase.delete("auth_settings", str, strArr);
                break;
            case 42:
                delete = writableDatabase.delete("auth_settings", "_id=?", new String[]{String.valueOf(ContentUris.parseId(uri))});
                break;
            case 43:
                delete = writableDatabase.delete("custom_settings", str, strArr);
                break;
            case 44:
                delete = writableDatabase.delete("custom_settings", "_id=?", new String[]{String.valueOf(ContentUris.parseId(uri))});
                break;
            case 45:
                delete = writableDatabase.delete("rcs_chat_ft", str, strArr);
                break;
            case 46:
                delete = writableDatabase.delete("rcs_chat_ft", "_id=?", new String[]{String.valueOf(ContentUris.parseId(uri))});
                break;
            case 47:
                delete = writableDatabase.delete("lte_settings", str, strArr);
                break;
            case 48:
                delete = writableDatabase.delete("lte_settings", "_id=?", new String[]{String.valueOf(ContentUris.parseId(uri))});
                break;
            case 49:
                delete = writableDatabase.delete("vvm_settings", str, strArr);
                break;
            case 50:
                delete = writableDatabase.delete("vvm_settings", "_id=?", new String[]{String.valueOf(ContentUris.parseId(uri))});
                break;
            default:
                throw new IllegalArgumentException("Unknown or unsupported delete URI " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return delete;
    }

    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        int update;
        SQLiteDatabase writableDatabase = this.B.getWritableDatabase();
        switch (z.match(uri)) {
            case 1:
                update = writableDatabase.update("profile_settings", contentValues, str, strArr);
                break;
            case 2:
                update = writableDatabase.update("profile_settings", contentValues, "_id=?", new String[]{String.valueOf(ContentUris.parseId(uri))});
                break;
            case 3:
                update = writableDatabase.update("sip_settings", contentValues, str, strArr);
                break;
            case 4:
                update = writableDatabase.update("sip_settings", contentValues, "_id=?", new String[]{String.valueOf(ContentUris.parseId(uri))});
                break;
            case 5:
                update = writableDatabase.update("rtp_settings", contentValues, str, strArr);
                break;
            case 6:
                update = writableDatabase.update("rtp_settings", contentValues, "_id=?", new String[]{String.valueOf(ContentUris.parseId(uri))});
                break;
            case 7:
                update = writableDatabase.update("call_settings", contentValues, str, strArr);
                break;
            case 8:
                update = writableDatabase.update("call_settings", contentValues, "_id=?", new String[]{String.valueOf(ContentUris.parseId(uri))});
                break;
            case 9:
                update = writableDatabase.update("nat_settings", contentValues, str, strArr);
                break;
            case 10:
                update = writableDatabase.update("nat_settings", contentValues, "_id=?", new String[]{String.valueOf(ContentUris.parseId(uri))});
                break;
            case 11:
                update = writableDatabase.update("wifi_settings", contentValues, str, strArr);
                break;
            case 12:
                update = writableDatabase.update("wifi_settings", contentValues, "_id=?", new String[]{String.valueOf(ContentUris.parseId(uri))});
                break;
            case 13:
                update = writableDatabase.update("media_settings", contentValues, str, strArr);
                break;
            case 14:
                update = writableDatabase.update("media_settings", contentValues, "_id=?", new String[]{String.valueOf(ContentUris.parseId(uri))});
                break;
            case 15:
                update = writableDatabase.update("atg_settings", contentValues, str, strArr);
                break;
            case 16:
                update = writableDatabase.update("atg_settings", contentValues, "_id=?", new String[]{String.valueOf(ContentUris.parseId(uri))});
                break;
            case 17:
                update = writableDatabase.update("sms_settings", contentValues, str, strArr);
                break;
            case 18:
                update = writableDatabase.update("sms_settings", contentValues, "_id=?", new String[]{String.valueOf(ContentUris.parseId(uri))});
                break;
            case 19:
                update = writableDatabase.update("developer_settings", contentValues, str, strArr);
                break;
            case VoIP.ERR_SIP_TIMED_OUT /*20*/:
                update = writableDatabase.update("developer_settings", contentValues, "_id=?", new String[]{String.valueOf(ContentUris.parseId(uri))});
                break;
            case VoIP.ERR_SESSION_NOT_FOUND /*21*/:
                update = writableDatabase.update("general_settings", contentValues, str, strArr);
                break;
            case VoIP.ERR_TRANSPORT_ERROR /*22*/:
                update = writableDatabase.update("general_settings", contentValues, "_id=?", new String[]{String.valueOf(ContentUris.parseId(uri))});
                break;
            case VoIP.ERR_SOCKET_CONNECTION_BROKE_NET_DOWN /*23*/:
                update = writableDatabase.update("qos_settings", contentValues, str, strArr);
                break;
            case VoIP.ERR_SDP_NEGOTIATION_FAILED /*24*/:
                update = writableDatabase.update("qos_settings", contentValues, "_id=?", new String[]{String.valueOf(ContentUris.parseId(uri))});
                break;
            case VoIP.ERR_NO_SRTP_AGREEMENT /*25*/:
                update = writableDatabase.update("qos_statistics", contentValues, str, strArr);
                break;
            case VoIP.ERR_SOCKET_CONNECTION_TLS_ERROR /*26*/:
                update = writableDatabase.update("qos_statistics", contentValues, "_id=?", new String[]{String.valueOf(ContentUris.parseId(uri))});
                break;
            case VoIP.ERR_SIP_BYE_TIMEOUT /*27*/:
                update = writableDatabase.update("whitelist", contentValues, str, strArr);
                break;
            case VoIP.ERR_SIP_REGISTER_PORT_MISMATCH /*28*/:
                update = writableDatabase.update("whitelist", contentValues, "_id=?", new String[]{String.valueOf(ContentUris.parseId(uri))});
                break;
            case VoIP.ERR_SIP_REGISTER_NOT_ALLOWED /*29*/:
                update = writableDatabase.update("capabilities", contentValues, str, strArr);
                break;
            case VoIP.ERR_SIP_REGISTER_SOCKET_INTERNAL /*30*/:
                update = writableDatabase.update("capabilities", contentValues, "_id=?", new String[]{String.valueOf(ContentUris.parseId(uri))});
                break;
            case VoIP.ERR_SIP_CALL_DOES_NOT_EXIST /*31*/:
                update = writableDatabase.update("rcs_presence", contentValues, str, strArr);
                break;
            case 32:
                update = writableDatabase.update("rcs_presence", contentValues, "_id=?", new String[]{String.valueOf(ContentUris.parseId(uri))});
                break;
            case 33:
                update = writableDatabase.update("exception_message", contentValues, str, strArr);
                break;
            case 34:
                update = writableDatabase.update("exception_message", contentValues, "_id=?", new String[]{String.valueOf(ContentUris.parseId(uri))});
                break;
            case 35:
                update = writableDatabase.update("emergency_numbers", contentValues, str, strArr);
                break;
            case 36:
                update = writableDatabase.update("emergency_numbers", contentValues, "_id=?", new String[]{String.valueOf(ContentUris.parseId(uri))});
                break;
            case 37:
                update = writableDatabase.update("native_numbers", contentValues, str, strArr);
                break;
            case 38:
                update = writableDatabase.update("native_numbers", contentValues, "_id=?", new String[]{String.valueOf(ContentUris.parseId(uri))});
                break;
            case 39:
                update = writableDatabase.update("vcc_settings", contentValues, str, strArr);
                break;
            case 40:
                update = writableDatabase.update("vcc_settings", contentValues, "_id=?", new String[]{String.valueOf(ContentUris.parseId(uri))});
                break;
            case 41:
                update = writableDatabase.update("auth_settings", contentValues, str, strArr);
                break;
            case 42:
                update = writableDatabase.update("auth_settings", contentValues, "_id=?", new String[]{String.valueOf(ContentUris.parseId(uri))});
                break;
            case 43:
                update = writableDatabase.update("custom_settings", contentValues, str, strArr);
                break;
            case 44:
                update = writableDatabase.update("custom_settings", contentValues, "_id=?", new String[]{String.valueOf(ContentUris.parseId(uri))});
                break;
            case 45:
                update = writableDatabase.update("rcs_chat_ft", contentValues, str, strArr);
                break;
            case 46:
                update = writableDatabase.update("rcs_chat_ft", contentValues, "_id=?", new String[]{String.valueOf(ContentUris.parseId(uri))});
                break;
            case 47:
                update = writableDatabase.update("lte_settings", contentValues, str, strArr);
                break;
            case 48:
                update = writableDatabase.update("lte_settings", contentValues, "_id=?", new String[]{String.valueOf(ContentUris.parseId(uri))});
                break;
            case 49:
                update = writableDatabase.update("vvm_settings", contentValues, str, strArr);
                break;
            case 50:
                update = writableDatabase.update("vvm_settings", contentValues, "_id=?", new String[]{String.valueOf(ContentUris.parseId(uri))});
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return update;
    }
}
