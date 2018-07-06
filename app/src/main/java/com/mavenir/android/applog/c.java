package com.mavenir.android.applog;

import com.fgmicrotec.mobile.android.fgmag.VoIP;
import com.mavenir.android.applog.AppLogAdapter.b;
import com.mavenir.android.applog.AppLogAdapter.d;

public class c {

    /* renamed from: com.mavenir.android.applog.c$1 */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = new int[b.values().length];
        static final /* synthetic */ int[] b = new int[d.values().length];
        static final /* synthetic */ int[] c = new int[com.mavenir.android.applog.AppLogAdapter.c.values().length];

        static {
            try {
                c[com.mavenir.android.applog.AppLogAdapter.c.FGAPPLOG_EVENT_REASON_FORBIDDEN.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                c[com.mavenir.android.applog.AppLogAdapter.c.FGAPPLOG_EVENT_REASON_INTERNAL_SERVER_ERROR.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                c[com.mavenir.android.applog.AppLogAdapter.c.FGAPPLOG_EVENT_REASON_SERVICE_UNAVAILABLE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                c[com.mavenir.android.applog.AppLogAdapter.c.FGAPPLOG_EVENT_REASON_SERVER_TIMEOUT.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                c[com.mavenir.android.applog.AppLogAdapter.c.FGAPPLOG_EVENT_REASON_NETWORK_AUTHENTICATION.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                c[com.mavenir.android.applog.AppLogAdapter.c.FGAPPLOG_EVENT_REASON_OTHER.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                c[com.mavenir.android.applog.AppLogAdapter.c.FGAPPLOG_EVENT_REASON_NO_SIM.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                c[com.mavenir.android.applog.AppLogAdapter.c.FGAPPLOG_EVENT_REASON_SIM_REMOVED.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                c[com.mavenir.android.applog.AppLogAdapter.c.FGAPPLOG_EVENT_REASON_SIM_HOT_SWAPPED.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            try {
                c[com.mavenir.android.applog.AppLogAdapter.c.FGAPPLOG_EVENT_REASON_NO_RESPONSE.ordinal()] = 10;
            } catch (NoSuchFieldError e10) {
            }
            try {
                c[com.mavenir.android.applog.AppLogAdapter.c.FGAPPLOG_EVENT_REASON_UNAUTHORIZED.ordinal()] = 11;
            } catch (NoSuchFieldError e11) {
            }
            try {
                c[com.mavenir.android.applog.AppLogAdapter.c.FGAPPLOG_EVENT_REASON_NOT_FOUND.ordinal()] = 12;
            } catch (NoSuchFieldError e12) {
            }
            try {
                c[com.mavenir.android.applog.AppLogAdapter.c.FGAPPLOG_EVENT_REASON_TIMEOUT.ordinal()] = 13;
            } catch (NoSuchFieldError e13) {
            }
            try {
                c[com.mavenir.android.applog.AppLogAdapter.c.FGAPPLOG_EVENT_REASON_REQUEST_TIMEOUT.ordinal()] = 14;
            } catch (NoSuchFieldError e14) {
            }
            try {
                c[com.mavenir.android.applog.AppLogAdapter.c.FGAPPLOG_EVENT_REASON_NOT_ACCEPTED_HERE.ordinal()] = 15;
            } catch (NoSuchFieldError e15) {
            }
            try {
                c[com.mavenir.android.applog.AppLogAdapter.c.FGAPPLOG_EVENT_REASON_TEMPORARILY_UNAVAILABLE.ordinal()] = 16;
            } catch (NoSuchFieldError e16) {
            }
            try {
                c[com.mavenir.android.applog.AppLogAdapter.c.FGAPPLOG_EVENT_REASON_MISSING_SDP.ordinal()] = 17;
            } catch (NoSuchFieldError e17) {
            }
            try {
                c[com.mavenir.android.applog.AppLogAdapter.c.FGAPPLOG_EVENT_REASON_INVALID_SDP.ordinal()] = 18;
            } catch (NoSuchFieldError e18) {
            }
            try {
                c[com.mavenir.android.applog.AppLogAdapter.c.FGAPPLOG_EVENT_REASON_NO_COMMON_CODEC.ordinal()] = 19;
            } catch (NoSuchFieldError e19) {
            }
            try {
                c[com.mavenir.android.applog.AppLogAdapter.c.FGAPPLOG_EVENT_REASON_NO_SRTP.ordinal()] = 20;
            } catch (NoSuchFieldError e20) {
            }
            try {
                c[com.mavenir.android.applog.AppLogAdapter.c.FGAPPLOG_EVENT_REASON_NO_CIPHER_AGREEMENT.ordinal()] = 21;
            } catch (NoSuchFieldError e21) {
            }
            try {
                c[com.mavenir.android.applog.AppLogAdapter.c.FGAPPLOG_EVENT_REASON_NO_MEDIA.ordinal()] = 22;
            } catch (NoSuchFieldError e22) {
            }
            try {
                c[com.mavenir.android.applog.AppLogAdapter.c.FGAPPLOG_EVENT_REASON_INTERNAL_MEDIA_ERROR.ordinal()] = 23;
            } catch (NoSuchFieldError e23) {
            }
            try {
                c[com.mavenir.android.applog.AppLogAdapter.c.FGAPPLOG_EVENT_REASON_SIP_UPDATE_FAILED.ordinal()] = 24;
            } catch (NoSuchFieldError e24) {
            }
            try {
                c[com.mavenir.android.applog.AppLogAdapter.c.FGAPPLOG_EVENT_REASON_SIP_UPDATE_TIMEOUT.ordinal()] = 25;
            } catch (NoSuchFieldError e25) {
            }
            try {
                c[com.mavenir.android.applog.AppLogAdapter.c.FGAPPLOG_EVENT_REASON_BAD_REQUEST.ordinal()] = 26;
            } catch (NoSuchFieldError e26) {
            }
            try {
                c[com.mavenir.android.applog.AppLogAdapter.c.FGAPPLOG_EVENT_REASON_UNSUPPORTED_TYPE.ordinal()] = 27;
            } catch (NoSuchFieldError e27) {
            }
            try {
                c[com.mavenir.android.applog.AppLogAdapter.c.FGAPPLOG_EVENT_REASON_UNSUPPORTED_DATA_ENCODING.ordinal()] = 28;
            } catch (NoSuchFieldError e28) {
            }
            try {
                c[com.mavenir.android.applog.AppLogAdapter.c.FGAPPLOG_EVENT_REASON_FRAGMENTS_MISSING.ordinal()] = 29;
            } catch (NoSuchFieldError e29) {
            }
            try {
                c[com.mavenir.android.applog.AppLogAdapter.c.FGAPPLOG_EVENT_REASON_SIP_BYE_FAILED.ordinal()] = 30;
            } catch (NoSuchFieldError e30) {
            }
            try {
                c[com.mavenir.android.applog.AppLogAdapter.c.FGAPPLOG_EVENT_REASON_SIP_BYE_TIMEOUT.ordinal()] = 31;
            } catch (NoSuchFieldError e31) {
            }
            try {
                c[com.mavenir.android.applog.AppLogAdapter.c.FGAPPLOG_EVENT_REASON_NONE.ordinal()] = 32;
            } catch (NoSuchFieldError e32) {
            }
            try {
                b[d.FGAPPLOG_EVENT_TYPE_DECRYPTION_ERROR.ordinal()] = 1;
            } catch (NoSuchFieldError e33) {
            }
            try {
                b[d.FGAPPLOG_EVENT_TYPE_ENCRYPTION_ERROR.ordinal()] = 2;
            } catch (NoSuchFieldError e34) {
            }
            try {
                b[d.FGAPPLOG_EVENT_TYPE_KEY_ERROR.ordinal()] = 3;
            } catch (NoSuchFieldError e35) {
            }
            try {
                b[d.FGAPPLOG_EVENT_TYPE_ACTIVATED.ordinal()] = 4;
            } catch (NoSuchFieldError e36) {
            }
            try {
                b[d.FGAPPLOG_EVENT_TYPE_TLS_ERROR.ordinal()] = 5;
            } catch (NoSuchFieldError e37) {
            }
            try {
                b[d.FGAPPLOG_EVENT_TYPE_HTTP_ERROR.ordinal()] = 6;
            } catch (NoSuchFieldError e38) {
            }
            try {
                b[d.FGAPPLOG_EVENT_TYPE_NO_RESPONSE.ordinal()] = 7;
            } catch (NoSuchFieldError e39) {
            }
            try {
                b[d.FGAPPLOG_EVENT_TYPE_DEVICE_NOT_SUPPORTED.ordinal()] = 8;
            } catch (NoSuchFieldError e40) {
            }
            try {
                b[d.FGAPPLOG_EVENT_TYPE_IMSI_MISMATCH.ordinal()] = 9;
            } catch (NoSuchFieldError e41) {
            }
            try {
                b[d.FGAPPLOG_EVENT_TYPE_NO_RECORD.ordinal()] = 10;
            } catch (NoSuchFieldError e42) {
            }
            try {
                b[d.FGAPPLOG_EVENT_TYPE_BUSY.ordinal()] = 11;
            } catch (NoSuchFieldError e43) {
            }
            try {
                b[d.FGAPPLOG_EVENT_TYPE_DEVICE_BLOCKED.ordinal()] = 12;
            } catch (NoSuchFieldError e44) {
            }
            try {
                b[d.FGAPPLOG_EVENT_TYPE_DEVICE_CONFIGURATION_UNAVAILABLE.ordinal()] = 13;
            } catch (NoSuchFieldError e45) {
            }
            try {
                b[d.FGAPPLOG_EVENT_TYPE_DEACTIVATED.ordinal()] = 14;
            } catch (NoSuchFieldError e46) {
            }
            try {
                b[d.FGAPPLOG_EVENT_TYPE_SIP_ERROR.ordinal()] = 15;
            } catch (NoSuchFieldError e47) {
            }
            try {
                b[d.FGAPPLOG_EVENT_TYPE_SDP_ERROR.ordinal()] = 16;
            } catch (NoSuchFieldError e48) {
            }
            try {
                b[d.FGAPPLOG_EVENT_TYPE_SRTP_ERROR.ordinal()] = 17;
            } catch (NoSuchFieldError e49) {
            }
            try {
                b[d.FGAPPLOG_EVENT_TYPE_CALL_DROPPED.ordinal()] = 18;
            } catch (NoSuchFieldError e50) {
            }
            try {
                b[d.FGAPPLOG_EVENT_TYPE_PACKET_LOSS.ordinal()] = 19;
            } catch (NoSuchFieldError e51) {
            }
            try {
                b[d.FGAPPLOG_EVENT_TYPE_POOR_SIGNAL.ordinal()] = 20;
            } catch (NoSuchFieldError e52) {
            }
            try {
                b[d.FGAPPLOG_EVENT_TYPE_SUBMIT_ERROR.ordinal()] = 21;
            } catch (NoSuchFieldError e53) {
            }
            try {
                b[d.FGAPPLOG_EVENT_TYPE_STATUS_ERROR.ordinal()] = 22;
            } catch (NoSuchFieldError e54) {
            }
            try {
                b[d.FGAPPLOG_EVENT_TYPE_PDU_ERROR.ordinal()] = 23;
            } catch (NoSuchFieldError e55) {
            }
            try {
                b[d.FGAPPLOG_EVENT_TYPE_CONCATENATION_ERROR.ordinal()] = 24;
            } catch (NoSuchFieldError e56) {
            }
            try {
                b[d.FGAPPLOG_EVENT_TYPE_SYNC_PROBLEM.ordinal()] = 25;
            } catch (NoSuchFieldError e57) {
            }
            try {
                b[d.FGAPPLOG_EVENT_TYPE_ACTIVATION_FAILED.ordinal()] = 26;
            } catch (NoSuchFieldError e58) {
            }
            try {
                b[d.FGAPPLOG_EVENT_TYPE_DEACTIVATION_FAILED.ordinal()] = 27;
            } catch (NoSuchFieldError e59) {
            }
            try {
                b[d.FGAPPLOG_EVENT_TYPE_NONE.ordinal()] = 28;
            } catch (NoSuchFieldError e60) {
            }
            try {
                a[b.FGAPPLOG_EVENT_GROUP_LTE_800.ordinal()] = 1;
            } catch (NoSuchFieldError e61) {
            }
            try {
                a[b.FGAPPLOG_EVENT_GROUP_PROVISIONING_ERROR.ordinal()] = 2;
            } catch (NoSuchFieldError e62) {
            }
            try {
                a[b.FGAPPLOG_EVENT_GROUP_CONFIGURATION_ERROR.ordinal()] = 3;
            } catch (NoSuchFieldError e63) {
            }
            try {
                a[b.FGAPPLOG_EVENT_GROUP_APP_ACTIVATED.ordinal()] = 4;
            } catch (NoSuchFieldError e64) {
            }
            try {
                a[b.FGAPPLOG_EVENT_GROUP_SIM_ERROR.ordinal()] = 5;
            } catch (NoSuchFieldError e65) {
            }
            try {
                a[b.FGAPPLOG_EVENT_GROUP_REGISTRATION_ERROR.ordinal()] = 6;
            } catch (NoSuchFieldError e66) {
            }
            try {
                a[b.FGAPPLOG_EVENT_GROUP_MO_CALL_ERROR.ordinal()] = 7;
            } catch (NoSuchFieldError e67) {
            }
            try {
                a[b.FGAPPLOG_EVENT_GROUP_MT_CALL_ERROR.ordinal()] = 8;
            } catch (NoSuchFieldError e68) {
            }
            try {
                a[b.FGAPPLOG_EVENT_GROUP_CALL_ERROR.ordinal()] = 9;
            } catch (NoSuchFieldError e69) {
            }
            try {
                a[b.FGAPPLOG_EVENT_GROUP_CALL_QOS_ISSUE.ordinal()] = 10;
            } catch (NoSuchFieldError e70) {
            }
            try {
                a[b.FGAPPLOG_EVENT_GROUP_MO_SMS_ERROR.ordinal()] = 11;
            } catch (NoSuchFieldError e71) {
            }
            try {
                a[b.FGAPPLOG_EVENT_GROUP_MT_SMS_ERROR.ordinal()] = 12;
            } catch (NoSuchFieldError e72) {
            }
            try {
                a[b.FGAPPLOG_EVENT_GROUP_MA_SMS_ERROR.ordinal()] = 13;
            } catch (NoSuchFieldError e73) {
            }
            try {
                a[b.FGAPPLOG_EVENT_GROUP_MO_MSG_ERROR.ordinal()] = 14;
            } catch (NoSuchFieldError e74) {
            }
            try {
                a[b.FGAPPLOG_EVENT_GROUP_CALL_QOS_STATISTICS.ordinal()] = 15;
            } catch (NoSuchFieldError e75) {
            }
            try {
                a[b.FGAPPLOG_EVENT_GROUP_LOGGING_ERROR.ordinal()] = 16;
            } catch (NoSuchFieldError e76) {
            }
            try {
                a[b.FGAPPLOG_EVENT_GROUP_ROOTED_DEVICE.ordinal()] = 17;
            } catch (NoSuchFieldError e77) {
            }
            try {
                a[b.FGAPPLOG_EVENT_GROUP_DATABASE_ERROR.ordinal()] = 18;
            } catch (NoSuchFieldError e78) {
            }
            try {
                a[b.FGAPPLOG_EVENT_GROUP_MO_IMC_ERROR.ordinal()] = 19;
            } catch (NoSuchFieldError e79) {
            }
            try {
                a[b.FGAPPLOG_EVENT_GROUP_MT_IMC_ERROR.ordinal()] = 20;
            } catch (NoSuchFieldError e80) {
            }
            try {
                a[b.FGAPPLOG_EVENT_GROUP_NATIVE_CRASH.ordinal()] = 21;
            } catch (NoSuchFieldError e81) {
            }
        }
    }

    public static String a(b bVar, d dVar, com.mavenir.android.applog.AppLogAdapter.c cVar) {
        String a = a(bVar);
        if (dVar != d.FGAPPLOG_EVENT_TYPE_NONE) {
            a = (a + " - ") + a(dVar);
        }
        if (cVar == com.mavenir.android.applog.AppLogAdapter.c.FGAPPLOG_EVENT_REASON_NONE) {
            return a;
        }
        return (a + " - ") + a(cVar);
    }

    public static String a(b bVar) {
        switch (AnonymousClass1.a[bVar.ordinal()]) {
            case 1:
                return "LTE-800";
            case 2:
                return "Provisioning Error";
            case 3:
                return "Configuration Error";
            case 4:
                return "App Activated";
            case 5:
                return "SIM Error";
            case 6:
                return "Registration Error";
            case 7:
                return "MO Call Error";
            case 8:
                return "MT Call Error";
            case 9:
                return "Call Error";
            case 10:
                return "Call QoS Issue";
            case 11:
                return "MO SMS Error";
            case 12:
                return "MT SMS Error";
            case 13:
                return "MA SMS Error";
            case 14:
                return "MO Message Error";
            case 15:
                return "Call QoS Statistics";
            case 16:
                return "Logging Error";
            case 17:
                return "Rooted Device";
            case 18:
                return "Database error";
            case 19:
                return "MO IMC Error";
            case VoIP.ERR_SIP_TIMED_OUT /*20*/:
                return "MT IMC Error";
            case VoIP.ERR_SESSION_NOT_FOUND /*21*/:
                return "Native Crash";
            default:
                return "";
        }
    }

    public static String a(d dVar) {
        switch (AnonymousClass1.b[dVar.ordinal()]) {
            case 1:
                return "Decryption error";
            case 2:
                return "Encryption error";
            case 3:
                return "Key error";
            case 4:
                return "Activated";
            case 5:
                return "TLS Error";
            case 6:
                return "HTTP Error";
            case 7:
                return "No Response";
            case 8:
                return "Device Not Supported";
            case 9:
                return "IMSI Mismatch";
            case 10:
                return "No Record";
            case 11:
                return "Busy";
            case 12:
                return "Device Blocked";
            case 13:
                return "Device Configuration Unavailable";
            case 14:
                return "De-Activated";
            case 15:
                return "SIP Error";
            case 16:
                return "SDP Error";
            case 17:
                return "SRTP Error";
            case 18:
                return "Call Dropped";
            case 19:
                return "Packet Loss";
            case VoIP.ERR_SIP_TIMED_OUT /*20*/:
                return "Poor Signal";
            case VoIP.ERR_SESSION_NOT_FOUND /*21*/:
                return "Submit Error";
            case VoIP.ERR_TRANSPORT_ERROR /*22*/:
                return "Status Error";
            case VoIP.ERR_SOCKET_CONNECTION_BROKE_NET_DOWN /*23*/:
                return "PDU Error";
            case VoIP.ERR_SDP_NEGOTIATION_FAILED /*24*/:
                return "Concatenation Error";
            case VoIP.ERR_NO_SRTP_AGREEMENT /*25*/:
                return "Sync Problem";
            case VoIP.ERR_SOCKET_CONNECTION_TLS_ERROR /*26*/:
                return "Activation Failed";
            case VoIP.ERR_SIP_BYE_TIMEOUT /*27*/:
                return "De-Activation Failed";
            case VoIP.ERR_SIP_REGISTER_PORT_MISMATCH /*28*/:
                return "";
            default:
                return "";
        }
    }

    public static String a(com.mavenir.android.applog.AppLogAdapter.c cVar) {
        switch (AnonymousClass1.c[cVar.ordinal()]) {
            case 1:
                return "Forbidden";
            case 2:
                return "Internal Server Error";
            case 3:
                return "Service Unavailable";
            case 4:
                return "Server Timeout";
            case 5:
                return "Network Authentication";
            case 6:
                return "Other";
            case 7:
                return "No SIM";
            case 8:
                return "SIM Removed";
            case 9:
                return "SIM Hot Swapped";
            case 10:
                return "No Response";
            case 11:
                return "Unauthorized";
            case 12:
                return "Not Found";
            case 13:
                return "Timeout";
            case 14:
                return "Request Timeout";
            case 15:
                return "Not Accepted Here";
            case 16:
                return "Temporarily Unavailable";
            case 17:
                return "Missing SDP";
            case 18:
                return "Invalid SDP";
            case 19:
                return "No Common Codec";
            case VoIP.ERR_SIP_TIMED_OUT /*20*/:
                return "No SRTP";
            case VoIP.ERR_SESSION_NOT_FOUND /*21*/:
                return "No Cipher Agreement";
            case VoIP.ERR_TRANSPORT_ERROR /*22*/:
                return "No Media";
            case VoIP.ERR_SOCKET_CONNECTION_BROKE_NET_DOWN /*23*/:
                return "Internal Media Error";
            case VoIP.ERR_SDP_NEGOTIATION_FAILED /*24*/:
                return "SIP Update Failed";
            case VoIP.ERR_NO_SRTP_AGREEMENT /*25*/:
                return "SIP Update Timeout";
            case VoIP.ERR_SOCKET_CONNECTION_TLS_ERROR /*26*/:
                return "Bad Request";
            case VoIP.ERR_SIP_BYE_TIMEOUT /*27*/:
                return "Unsupported Type";
            case VoIP.ERR_SIP_REGISTER_PORT_MISMATCH /*28*/:
                return "Unsupported Data Encoding";
            case VoIP.ERR_SIP_REGISTER_NOT_ALLOWED /*29*/:
                return "Fragments Missing";
            case VoIP.ERR_SIP_REGISTER_SOCKET_INTERNAL /*30*/:
                return "SIP Bye Failed";
            case VoIP.ERR_SIP_CALL_DOES_NOT_EXIST /*31*/:
                return "SIP Bye Timeout";
            case 32:
                return "";
            default:
                return "";
        }
    }
}
