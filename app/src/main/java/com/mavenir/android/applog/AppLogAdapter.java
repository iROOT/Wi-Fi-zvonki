package com.mavenir.android.applog;

public class AppLogAdapter {
    public static final String ACTION_EVENT_LIST_CNF = "AppLogAdapter.Action.EventListCnf";
    public static final String EXTRA_EVENT_LIST = "AppLogAdapter.Extra.EventList";
    public static final String EXTRA_EVENT_LIST_SIZE = "AppLogAdapter.Extra.EventListSize";
    public b mObserver;

    public enum a {
        FGAPPLOG_OK,
        FGAPPLOG_ERR_GENERAL,
        FGAPPLOG_ERR_SERVER,
        FGAPPLOG_ERR_CONNECTION,
        FGAPPLOG_ERR_SETUP,
        FGAPPLOG_ERR_BAD_PARAMETER,
        FGAPPLOG_ERR_NOT_SENT,
        FGAPPLOG_ERR_NOT_KNOWN,
        FGAPPLOG_ERR_UNKNOWN,
        FGAPPLOG_ERR_POST_FAILED
    }

    public enum b {
        FGAPPLOG_EVENT_GROUP_CALL_QOS_STATISTICS,
        FGAPPLOG_EVENT_GROUP_SIM_ERROR,
        FGAPPLOG_EVENT_GROUP_MO_SMS_ERROR,
        FGAPPLOG_EVENT_GROUP_PROVISIONING_ERROR,
        FGAPPLOG_EVENT_GROUP_CONFIGURATION_ERROR,
        FGAPPLOG_EVENT_GROUP_APP_ACTIVATED,
        FGAPPLOG_EVENT_GROUP_REGISTRATION_ERROR,
        FGAPPLOG_EVENT_GROUP_MO_CALL_ERROR,
        FGAPPLOG_EVENT_GROUP_MT_CALL_ERROR,
        FGAPPLOG_EVENT_GROUP_CALL_ERROR,
        FGAPPLOG_EVENT_GROUP_CALL_QOS_ISSUE,
        FGAPPLOG_EVENT_GROUP_MT_SMS_ERROR,
        FGAPPLOG_EVENT_GROUP_LOGGING_ERROR,
        FGAPPLOG_EVENT_GROUP_ROOTED_DEVICE,
        FGAPPLOG_EVENT_GROUP_MA_SMS_ERROR,
        FGAPPLOG_EVENT_GROUP_MO_MSG_ERROR,
        FGAPPLOG_EVENT_GROUP_MO_IMC_ERROR,
        FGAPPLOG_EVENT_GROUP_MT_IMC_ERROR,
        FGAPPLOG_EVENT_GROUP_NATIVE_CRASH,
        FGAPPLOG_EVENT_GROUP_LTE_800,
        FGAPPLOG_EVENT_GROUP_DATABASE_ERROR
    }

    public enum c {
        FGAPPLOG_EVENT_REASON_NONE,
        FGAPPLOG_EVENT_REASON_NO_SIM,
        FGAPPLOG_EVENT_REASON_SIM_REMOVED,
        FGAPPLOG_EVENT_REASON_SIM_HOT_SWAPPED,
        FGAPPLOG_EVENT_REASON_FORBIDDEN,
        FGAPPLOG_EVENT_REASON_INTERNAL_SERVER_ERROR,
        FGAPPLOG_EVENT_REASON_SERVICE_UNAVAILABLE,
        FGAPPLOG_EVENT_REASON_SERVER_TIMEOUT,
        FGAPPLOG_EVENT_REASON_NETWORK_AUTHENTICATION,
        FGAPPLOG_EVENT_REASON_OTHER,
        FGAPPLOG_EVENT_REASON_NO_RESPONSE,
        FGAPPLOG_EVENT_REASON_UNAUTHORIZED,
        FGAPPLOG_EVENT_REASON_NOT_FOUND,
        FGAPPLOG_EVENT_REASON_TIMEOUT,
        FGAPPLOG_EVENT_REASON_REQUEST_TIMEOUT,
        FGAPPLOG_EVENT_REASON_NOT_ACCEPTED_HERE,
        FGAPPLOG_EVENT_REASON_TEMPORARILY_UNAVAILABLE,
        FGAPPLOG_EVENT_REASON_MISSING_SDP,
        FGAPPLOG_EVENT_REASON_INVALID_SDP,
        FGAPPLOG_EVENT_REASON_NO_COMMON_CODEC,
        FGAPPLOG_EVENT_REASON_NO_SRTP,
        FGAPPLOG_EVENT_REASON_NO_CIPHER_AGREEMENT,
        FGAPPLOG_EVENT_REASON_NO_MEDIA,
        FGAPPLOG_EVENT_REASON_INTERNAL_MEDIA_ERROR,
        FGAPPLOG_EVENT_REASON_SIP_UPDATE_FAILED,
        FGAPPLOG_EVENT_REASON_SIP_UPDATE_TIMEOUT,
        FGAPPLOG_EVENT_REASON_BAD_REQUEST,
        FGAPPLOG_EVENT_REASON_UNSUPPORTED_TYPE,
        FGAPPLOG_EVENT_REASON_UNSUPPORTED_DATA_ENCODING,
        FGAPPLOG_EVENT_REASON_FRAGMENTS_MISSING,
        FGAPPLOG_EVENT_REASON_SIP_BYE_FAILED,
        FGAPPLOG_EVENT_REASON_SIP_BYE_TIMEOUT,
        FGAPPLOG_EVENT_REASON_PARSING_ERROR
    }

    public enum d {
        FGAPPLOG_EVENT_TYPE_NONE,
        FGAPPLOG_EVENT_TYPE_DEACTIVATED,
        FGAPPLOG_EVENT_TYPE_SUBMIT_ERROR,
        FGAPPLOG_EVENT_TYPE_TLS_ERROR,
        FGAPPLOG_EVENT_TYPE_HTTP_ERROR,
        FGAPPLOG_EVENT_TYPE_NO_RESPONSE,
        FGAPPLOG_EVENT_TYPE_DEVICE_NOT_SUPPORTED,
        FGAPPLOG_EVENT_TYPE_IMSI_MISMATCH,
        FGAPPLOG_EVENT_TYPE_NO_RECORD,
        FGAPPLOG_EVENT_TYPE_BUSY,
        FGAPPLOG_EVENT_TYPE_DEVICE_BLOCKED,
        FGAPPLOG_EVENT_TYPE_DEVICE_CONFIGURATION_UNAVAILABLE,
        FGAPPLOG_EVENT_TYPE_SIP_ERROR,
        FGAPPLOG_EVENT_TYPE_SDP_ERROR,
        FGAPPLOG_EVENT_TYPE_SRTP_ERROR,
        FGAPPLOG_EVENT_TYPE_CALL_DROPPED,
        FGAPPLOG_EVENT_TYPE_PACKET_LOSS,
        FGAPPLOG_EVENT_TYPE_POOR_SIGNAL,
        FGAPPLOG_EVENT_TYPE_STATUS_ERROR,
        FGAPPLOG_EVENT_TYPE_PDU_ERROR,
        FGAPPLOG_EVENT_TYPE_CONCATENATION_ERROR,
        FGAPPLOG_EVENT_TYPE_SYNC_PROBLEM,
        FGAPPLOG_EVENT_TYPE_ACTIVATED,
        FGAPPLOG_EVENT_TYPE_ACTIVATION_FAILED,
        FGAPPLOG_EVENT_TYPE_DEACTIVATION_FAILED,
        FGAPPLOG_EVENT_TYPE_ENCRYPTION_ERROR,
        FGAPPLOG_EVENT_TYPE_DECRYPTION_ERROR,
        FGAPPLOG_EVENT_TYPE_KEY_ERROR
    }

    public enum e {
        FGAPPLOG_HTTP_METHOD_GET,
        FGAPPLOG_HTTP_METHOD_POST,
        FGAPPLOG_HTTP_METHOD_PUT,
        FGAPPLOG_HTTP_METHOD_DELETE
    }

    public enum f {
        FGAPPLOG_HTTP_STATUS_OK,
        FGAPPLOG_HTTP_STATUS_INVALID_REQUEST,
        FGAPPLOG_HTTP_STATUS_CONNECT_ERROR,
        FGAPPLOG_HTTP_STATUS_SSL_ERROR,
        FGAPPLOG_HTTP_STATUS_TIMEOUT,
        FGAPPLOG_HTTP_STATUS_GENERAL_ERROR
    }

    public native void additionalInfoNeededRes(int i, int i2, int i3, String str, String str2, String str3, boolean z, String str4, int i4, int i5, int i6, int i7, int i8, boolean z2, boolean z3, boolean z4, String str5);

    public native void appLogConfigureReq(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11);

    public native void eventLoggingReq(int i, int i2, int i3, String str, int i4);

    public native void exit();

    public native void getEventListReq();

    public native void init();

    public native void ipConnectionInfoSetupReq(int i, String str, String str2);

    public native void loggingServerInfoSetupReq(String str, String str2);

    public native void reportDeviceRootedReq(boolean z);

    public native void sendHttpRequestCnf(int i, int i2);

    public native void setEventLoggingParametersReq(int i, int i2, int i3);

    public native void timeZoneInfoSetupReq(int i);

    public native void updateRoamingStatusReq(boolean z);

    public native void wifiAccessInfoSetupReq(boolean z, String str, String str2);

    public native void writeJavaLog(String str);

    public AppLogAdapter(b bVar) {
        this.mObserver = bVar;
    }

    public void appLogConfigureCnf(int i) {
        this.mObserver.a(i);
    }

    public void timeZoneInfoSetupCnf(int i) {
        this.mObserver.b(i);
    }

    public void wifiAccessInfoSetupCnf(int i) {
        this.mObserver.c(i);
    }

    public void eventLoggingCnf(int i) {
        this.mObserver.f(i);
    }

    public void ipConnectionInfoSetupCnf(int i) {
        this.mObserver.d(i);
    }

    public void loggingServerInfoSetupCnf(int i) {
        this.mObserver.e(i);
    }

    public void additionalInfoNeededInd(boolean z) {
        this.mObserver.a(z);
    }

    public void setEventLoggingPeriodCnf(int i) {
        this.mObserver.g(i);
    }

    public void getEventListCnf(int i, String[] strArr) {
        this.mObserver.a(i, strArr);
    }

    public void sendHttpRequestReq(String str, int i, String str2, String str3, String str4, String str5) {
        this.mObserver.a(str, i, str2, str3, str4, str5);
    }
}
