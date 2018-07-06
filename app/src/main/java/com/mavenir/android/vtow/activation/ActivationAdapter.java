package com.mavenir.android.vtow.activation;

public class ActivationAdapter {
    public static final int OP_ACTIVATION = 100;
    public static final int OP_CONFIGURATION_APP_UPDATE = 201;
    public static final int OP_CONFIGURATION_DAILY = 202;
    public static final int OP_CONFIGURATION_INITIAL = 200;
    public static final int OP_LTE_PS_ACTIVATION = 203;
    public static final int OP_LTE_PS_DEACTIVATION = 204;
    public static final int OP_NON_LTE_REQ = 0;
    public static final int REASON_APP_USER_FRIENDLY_MESSAGE = 305;
    public static final int REASON_APP_VERSION_BLOCKED = 304;
    public static final int REASON_DEVICE_BLOCKED = 302;
    public static final int REASON_DEVICE_BLOCKED_ROOTED = 303;
    public static final int REASON_INVALID_URL_REQ = 309;
    public static final int REASON_MALFORMED_PARAMS = 308;
    public static final int REASON_MSISDN_HASH_MISMATCH = 314;
    public static final int REASON_NETWORK_PROVISIONING_ERROR = 307;
    public static final int REASON_NEW_CONFIG_DATA = 300;
    public static final int REASON_NO_NEW_CONFIG_DATA = 301;
    public static final int REASON_SERVICE_ACTIVATION_OK = 311;
    public static final int REASON_SERVICE_BLOCKED = 313;
    public static final int REASON_SERVICE_DEACTIVATION_OK = 312;
    public static final int REASON_UNKNOWN_CMS_ERROR = 310;
    public static final int REASON_USER_PROVISIONING_ERROR = 306;
    public static final int VERS_INITIAL = -3;
    public static final int VERS_PROVISIONED = -1;
    private a mObserver;

    public enum a {
        FGVOIPCPROXY_DEACTIVATION_TLS,
        FGVOIPCPROXY_DEACTIVATION_NO_USER_PROFILE,
        FGVOIPCPROXY_DEACTIVATION_MUST_REPROVISION_CREDENTIALS
    }

    public enum b {
        PROVISIONING_OK,
        PROVISIONING_CONFIGURATION_UNAVAILABLE,
        PROVISIONING_FAILED_NO_RECORD,
        PROVISIONING_FAILED_BLOCKED,
        PROVISIONING_BUSY,
        PROVISIONING_HTTP_ERROR,
        PROVISIONING_NO_RESPONSE,
        PROVISIONING_PARSING_FAILED,
        PROVISIONING_TLS_ERROR,
        PROVISIONING_TLS_OCSP_ERROR_GROUP_1,
        PROVISIONING_TLS_OCSP_ERROR_GROUP_2,
        PROVISIONING_OCSP_NO_RESPONSE_REACHABLE,
        PROVISIONING_OCSP_NO_RESPONSE_NOT_REACHABLE,
        PROVISIONING_INVALID_REQUEST,
        PROVISIONING_IMSI_MISMATCH,
        H3GPROVISIONING_LTE_RESPONSE
    }

    public native void configurationReq(int i, int i2, String str, String str2, String str3, boolean z, boolean z2, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, int i3, String[] strArr, int i4, String str12);

    public native void exit();

    public native void init();

    public native void provisioningExtReq(int i, String str, String str2, boolean z, int i2, String[] strArr, String str3, String str4);

    public native void provisioningReq(int i, int i2, String str, String str2, String str3, boolean z, int i3, String[] strArr, int i4);

    public ActivationAdapter(a aVar) {
        this.mObserver = aVar;
    }

    public void provisioningCnf(int i, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, int i2, String[] strArr, int i3, String str9, String str10) {
        this.mObserver.a(i, str, str2, str3, str4, str5, str6, str7, str8, i2, strArr, i3, str9, str10);
    }

    public void configurationCnf(int i, int i2, int i3, ConfigurationData configurationData, ConfigurationDataLte configurationDataLte, int i4, String str, String str2) {
        this.mObserver.a(i, i2, i3, configurationData, configurationDataLte, i4, str, str2);
    }

    public void deactivationInd(int i) {
        this.mObserver.a(i);
    }
}
