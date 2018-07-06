package com.fgmicrotec.mobile.android.fgmag;

public class VoIP {
    public static final int ATG_STATE_NO_ACID = 3;
    public static final int ATG_STATE_SERVICE_ACTIVE = 0;
    public static final int ATG_STATE_SERVICE_INACTIVE = 2;
    public static final int ATG_STATE_SERVICE_NOT_ENABLED = 1;
    public static final String AUDIO_BT_MODE = "bluetooth";
    public static final String AUDIO_HANDSET_MODE = "handset";
    public static final String AUDIO_HEADPHONES_MODE = "headphones";
    public static final String AUDIO_HEASDET_MODE = "headset";
    public static final String AUDIO_SPEAKER_MODE = "speakerOrHandsfree";
    public static final String DUMMY_URI = "sip:dummy@";
    public static final int ERR_ACTION_NOT_ALLOWED = 16;
    public static final int ERR_ALREADY_LOGGED_IN = 2;
    public static final int ERR_ALREADY_LOGGING_IN = 3;
    public static final int ERR_ALREADY_LOGGING_OUT = 4;
    public static final int ERR_AUTHENTICATION = 6;
    public static final int ERR_CONNECTION = 7;
    public static final int ERR_CONNECTION_BEARER_MISSMATCH = 32;
    public static final int ERR_FAILED_BIND_SOCKET = 9;
    public static final int ERR_FAILED_STUN = 10;
    public static final int ERR_FAILED_TO_CREATE_SOCKET = 8;
    public static final int ERR_FAILED_TO_INITIALIZE_SESSION = 14;
    public static final int ERR_FAILED_TO_INIT_SIP = 12;
    public static final int ERR_FAILED_TO_OPEN_RTP = 11;
    public static final int ERR_GENERAL = 1;
    public static final int ERR_NOT_LOGGED_IN = 5;
    public static final int ERR_NO_SRTP_AGREEMENT = 25;
    public static final int ERR_OK = 0;
    public static final int ERR_REGISTER_NOT_ALLOWED = 17;
    public static final int ERR_SDP_NEGOTIATION_FAILED = 24;
    public static final int ERR_SESSION_NOT_FOUND = 21;
    public static final int ERR_SIP_BYE_TIMEOUT = 27;
    public static final int ERR_SIP_CALL_DOES_NOT_EXIST = 31;
    public static final int ERR_SIP_REGISTER_NOT_ALLOWED = 29;
    public static final int ERR_SIP_REGISTER_PORT_MISMATCH = 28;
    public static final int ERR_SIP_REGISTER_SOCKET_INTERNAL = 30;
    public static final int ERR_SIP_SERVER_ERROR = 13;
    public static final int ERR_SIP_TIMED_OUT = 20;
    public static final int ERR_SOCKET_CONNECTION_BROKE = 19;
    public static final int ERR_SOCKET_CONNECTION_BROKE_NET_DOWN = 23;
    public static final int ERR_SOCKET_CONNECTION_TLS_ERROR = 26;
    public static final int ERR_TRANSPORT_ERROR = 22;
    public static final int ERR_USER_CANCELED_ACTION = 18;
    public static final int ERR_WRONG_PARAMETERS = 15;
    public static final int RCS_PROVISIONING_ERR_GENERAL = 8;
    public static final int RCS_PROVISIONING_ERR_INVALID_HTTPS_RESPONSE = 7;
    public static final int RCS_PROVISIONING_ERR_INVALID_HTTP_RESPONSE = 5;
    public static final int RCS_PROVISIONING_ERR_INVALID_REQUEST = 3;
    public static final int RCS_PROVISIONING_ERR_NO_HTTPS_RESPONSE = 6;
    public static final int RCS_PROVISIONING_ERR_NO_HTTP_RESPONSE = 4;
    public static final int RCS_PROVISIONING_ERR_WRONG_STATE = 2;
    public static final int RCS_PROVISIONING_NO_XML_RESPONSE = 1;
    public static final int RCS_PROVISIONING_OK = 0;
    public static final int REASON_CODE_BAD_REQUEST = 400;
    public static final int REASON_CODE_BUSY_EVERYWHERE = 600;
    public static final int REASON_CODE_CALLEE_BUSY = 486;
    public static final int REASON_CODE_CALLEE_TEMP_UNAVAILABLE = 480;
    public static final int REASON_CODE_CONFLICT = 409;
    public static final int REASON_CODE_CONNECTION_BROKE = 2;
    public static final int REASON_CODE_DECLINE = 603;
    public static final int REASON_CODE_DOES_NOT_EXIST = 604;
    public static final int REASON_CODE_FORBIDDEN = 403;
    public static final int REASON_CODE_GONE = 410;
    public static final int REASON_CODE_NETWORK_INITIATED = 4;
    public static final int REASON_CODE_NOT_ACCEPTABLE = 406;
    public static final int REASON_CODE_NOT_ALLOWED = 405;
    public static final int REASON_CODE_NOT_FOUND = 404;
    public static final int REASON_CODE_PAYMENT_REQUIRED = 402;
    public static final int REASON_CODE_PROXY_AUTH_REQUIRED = 407;
    public static final int REASON_CODE_REQUEST_TERMINATED = 487;
    public static final int REASON_CODE_REQUEST_TIMEOUT = 408;
    public static final int REASON_CODE_SERVER_BAD_GATEWAY = 502;
    public static final int REASON_CODE_SERVER_INTERNAL_ERROR = 500;
    public static final int REASON_CODE_SERVER_NOT_IMPLEMENTED = 501;
    public static final int REASON_CODE_SERVER_SERVICE_UNAVAILABLE = 503;
    public static final int REASON_CODE_SERVER_TIMEOUT = 504;
    public static final int REASON_CODE_SERVER_VERSION_NOT_SUPPORTED = 505;
    public static final int REASON_CODE_TERMINATION_MEDIA_RESOURCES_INIT = 8;
    public static final int REASON_CODE_TERMINATION_NORMAL = 0;
    public static final int REASON_CODE_TERMINATION_RTP_BROKEN = 1;
    public static final int REASON_CODE_TERMINATION_RTP_NET_DOWN = 4;
    public static final int REASON_CODE_TERMINATION_RTP_TIMEOUT = 2;
    public static final int REASON_CODE_TERMINATION_SIP_CALL_DOES_NOT_EXIST = 7;
    public static final int REASON_CODE_TERMINATION_SIP_REFRESH_TIMEOUT = 3;
    public static final int REASON_CODE_TERMINATION_SIP_UPDATE_ERROR = 5;
    public static final int REASON_CODE_TERMINATION_SIP_UPDATE_NO_RESPONSE_TIMEOUT = 6;
    public static final int REASON_CODE_TIMEOUT = 3;
    public static final int REASON_CODE_UNAUTHORIZED = 401;
    public static final int REASON_CODE_UNKNOWN = 0;
    public static final int REASON_CODE_USER_REQUESTED = 1;
    public static final int SIP_TRANSPORT_DEFAULT = 2;
    public static final int SIP_TRANSPORT_TCP = 0;
    public static final int SIP_TRANSPORT_UDP = 1;
    public static final int VOIP_SUPPL_3WAY_CALL = 12;
    public static final int VOIP_SUPPL_CALL_HOLD_OFF = 1;
    public static final int VOIP_SUPPL_CALL_HOLD_ON = 0;
    public static final int VOIP_SUPPL_CALL_TRANSFER = 7;
    public static final int VOIP_SUPPL_CONSULTATION_END = 5;
    public static final int VOIP_SUPPL_CONSULTATION_START = 4;
    public static final int VOIP_SUPPL_TOGGLE = 6;
    public static final int VOIP_SUPPL_VIDEO_CONSULTATION_END = 14;
    public static final int VOIP_SUPPL_VIDEO_CONSULTATION_START = 13;
    private b mObserver;

    public enum a {
        BUSY_TONE_USER,
        BUSY_TONE_NETWORK,
        BUSY_TONE_NONE
    }

    public enum b {
        FGVOIPCPROXY_DEACTIVATION_TLS,
        FGVOIPCPROXY_DEACTIVATION_NO_USER_PROFILE,
        FGVOIPCPROXY_DEACTIVATION_MUST_REPROVISION_CREDENTIALS
    }

    public enum c {
        FGVOIPCPROXY_OK,
        FGVOIPCPROXY_ERROR,
        FGVOIPCPROXY_WRONG_CONFIG_PARAMETERS,
        FGVOIPCPROXY_NOT_AVAILABLE
    }

    public enum d {
        FGVOIPCPROXY_POPUP_RESTARTING_SERVICE,
        FGVOIPCPROXY_POPUP_ISP_BLOCKED_ACCESS,
        FGVOIPCPROXY_POPUP_INVALID_SDP,
        FGVOIPCPROXY_POPUP_ISP_BLOCKED_ACCESS_IN_CALL,
        FGVOIPCPROXY_POPUP_NO_SRTP_AGREEMENT,
        FGVOIPCPROXY_POPUP_TLS_ERROR,
        FGVOIPCPROXY_POPUP_REGISTER_FAILED,
        FGVOIPCPROXY_POPUP_INVITE_SIP_ERROR,
        FGVOIPCPROXY_POPUP_INVITE_NOT_ACCEPTABLE,
        FGVOIPCPROXY_POPUP_WIFI_ISSUE,
        FGVOIPCPROXY_POPUP_LOCATION_ISSUE
    }

    public enum e {
        FGTLS_OK,
        FGTLS_ERROR_NOT_TRUSTED,
        FGTLS_ERROR_REVOKED,
        FGTLS_ERROR_SELF_SIGNED,
        FGTLS_ERROR_EXPIRED,
        FGTLS_ERROR_GENERAL,
        FGTLS_ERROR_ALERT,
        FGTLS_ERROR_OCSP_UNAUTHORIZED,
        FGTLS_ERROR_OCSP_INTERNAL_ERROR,
        FGTLS_ERROR_OCSP_MALFORMED_REQUEST,
        FGTLS_ERROR_OCSP_SIGREQUIRED,
        FGTLS_ERROR_OCSP_TRY_LATER,
        FGTLS_ERROR_OCSP_UNKNOWN,
        FGTLS_ERROR_OCSP_NO_RESPONSE
    }

    public enum f {
        FG_VOIP_CONFERENCE_ERR_OK,
        FG_VOIP_CONFERENCE_ERR_ALREADY_IN_PROGRESS,
        FG_VOIP_CONFERENCE_ERR_TOO_LESS_PARTICIPANTS,
        FG_VOIP_CONFERENCE_ERR_TOO_MANY_PARTICIPANTS,
        FG_VOIP_CONFERENCE_ERR_CONFERENCE_FACTORY_URI_MISSING,
        FG_VOIP_CONFERENCE_ERR_INVITE_FAILED,
        FG_VOIP_CONFERENCE_ERR_UNKNOWN_SESSION_ID,
        FG_VOIP_CONFERENCE_ERR_UNKNOWN_URI,
        FG_VOIP_CONFERENCE_ERR_INVALID_SESSION_STATE,
        FG_VOIP_CONFERENCE_ERR_WRONG_PARTICIPANT,
        FG_VOIP_CONFERENCE_ERR_UNKNOWN_SUPPLEMENTARY_SERVICE,
        FG_VOIP_CONFERENCE_ERR_UNKNOWN_CONFERENCE_URI,
        FG_VOIP_CONFERENCE_ERR_REFER_FAILED,
        FG_VOIP_CONFERENCE_ERR_PARTICIPANT_TERMINATED_CALL,
        FG_VOIP_CONFERENCE_ERR_SERVER_TERMINATED_CALL,
        FG_VOIP_CONFERENCE_ERR_NOTIFY_TERMINATION_FAILED,
        FG_VOIP_CONFERENCE_ERR_GENERAL,
        FG_VOIP_CONFERENCE_ERR_DISCONNECT_SESSIONS_IF_ACTIVE
    }

    public enum g {
        SESSION_UNKNOWN,
        SESSION_VOIP_CALL,
        SESSION_GSM_CALL,
        SESSION_DCE_IP_CALL,
        SESSION_IP_VIDEO_CALL,
        SESSION_ANY_CALL
    }

    public native void acceptInvitationReq(int i);

    public native void addVideoReq();

    public native void callConferenceAddParticipantReq(String str, String str2, int i, String str3, boolean z);

    public native void callConferenceCreateReq(String str, String[] strArr, int i);

    public native void callConferenceMergeCallsReq(String str, String str2, int i, String str3, int i2, boolean z);

    public native void disconnectSessionReq(int i);

    public native void exit();

    public native void genericSendDTMF(String str);

    public native void genericStopSendingDTMF();

    public native void init();

    public native void inviteCancelReq(String str, int i);

    public native void inviteReq(String str, int i, boolean z, int i2, int i3);

    public native void loginAllowedQueryRes(int i, int i2);

    public native void loginReq(int i);

    public native void logoutReq();

    public native void muteAudioReq(boolean z);

    public native void provisioningAuthRes(int i, String str, String str2);

    public native void provisioningReq(String str, int i, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10);

    public native void proxyConfigurationReq(int i, int[] iArr, int i2, int i3, int i4, String[] strArr, int i5, String[] strArr2, int i6, int i7, String str, int i8);

    public native void qosPaceForExternalMediaEngine();

    public native void qosReportMarkEntryReq();

    public native void qosReportReq();

    public native void rejectInvitationReq(int i);

    public native void reloginReq();

    public native void removeVideoReq();

    public native void renewSpiritLocalIPAddress(String str);

    public native void setATGInfoReq(String str, int i);

    public native void setCallContinuityReq(boolean z);

    public native void setConnectionInfoReq(String str, int i, int i2, int i3, boolean z, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12, boolean z2, boolean z3, String str2, int i13);

    public native void setConnectionTypeReq(int i);

    public native void setMediaInfoReq(int i, int i2, int i3, int i4, int i5, int i6, int i7, boolean z, boolean z2, boolean z3, boolean z4, int i8, int i9, boolean z5, int i10);

    public native void setQoSThresholdsReq(int i, int i2, int i3, int i4, int i5);

    public native void setRTCPInterval(int i);

    public native void setSRTPModeReq(int i, int i2, int i3, int i4);

    public native void setSTUNInfoReq(String str, int i);

    public native void setSessionInfoReq(int i, int i2, int i3);

    public native void setSpiritTestPreferencesReq(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8);

    public native void setUserAgentReq(String str, boolean z);

    public native void setUserInfoReq(String str, String str2, String str3, String str4, String str5, int i, String str6);

    public native void setVideoCallPreferencesReq(int i, int i2, int i3, int i4, int i5);

    public native void setVolumeChange(int i);

    public native void setWifiSignalStrengthInd(int i);

    public native void supplementaryServiceReq(String str, int i, int i2);

    public native void tlsSetTimersReq(int i, int i2);

    public native void tlsVerifyCertCnf(byte[] bArr, int i);

    public native void unregisterConnNeededRes(int i, int i2);

    public native void updateAudioMode(int i, int i2, int i3, int i4, int i5);

    public native void updateMessagingFeatureReq(boolean z);

    public native void userCallQualityRatingRes(int i, int i2, String str, String str2);

    public native void ussdStringReceivedRes(String str);

    public native void videoViewShouldBeDisplayedRes(int i, int i2);

    public VoIP(b bVar) {
        this.mObserver = bVar;
    }

    public int mapErrorCodes(int i) {
        return i;
    }

    public void proxyConfigurationCnf(int i) {
        this.mObserver.k(i);
    }

    public void unregisterConnNeededInd() {
        this.mObserver.f();
    }

    private void setConnectionInfoCnf(int i) {
        this.mObserver.a(i);
    }

    private void setConnectionTypeCnf(int i) {
        this.mObserver.c(i);
    }

    private void setQoSThresholdsCnf(int i) {
        this.mObserver.b(i);
    }

    private void setUserInfoCnf(int i) {
        this.mObserver.d(i);
    }

    private void setMediaInfoCnf(int i) {
        this.mObserver.e(i);
    }

    private void setSessionInfoCnf(int i) {
        this.mObserver.f(i);
    }

    private void setSTUNInfoCnf(int i) {
        this.mObserver.g(i);
    }

    private void setSRTPModeCnf(int i) {
        this.mObserver.h(i);
    }

    private void loginStartedInd() {
        this.mObserver.a();
    }

    private void loginCnf(int i, int i2, String str, int i3, String str2) {
        this.mObserver.a(i, i2, str, i3, str2);
    }

    private void loginInd() {
        this.mObserver.b();
    }

    private void logoutCnf(int i, int i2) {
        this.mObserver.a(i, i2);
    }

    private void logoutInd(int i, int i2) {
        this.mObserver.b(i, i2);
    }

    private void loginAllowedQueryInd() {
        this.mObserver.c();
    }

    private void inviteCnf(int i, int i2) {
        this.mObserver.c(i, i2);
    }

    private void inviteCancelCnf(int i, String str, int i2) {
        this.mObserver.a(i, str, i2);
    }

    private void invitationSessionProgressInd(String str, String str2, int i) {
        this.mObserver.a(str, str2, i);
    }

    private void invitationRingingInd(String str, String str2, int i, int i2, boolean z) {
        this.mObserver.a(str, str2, i, i2, z);
    }

    private void invitationAcceptedInd(String str, String str2, String str3, String str4, int i) {
        this.mObserver.a(str, str2, str3, str4, i);
    }

    private void invitationRejectedInd(String str, int i, String str2, int i2, boolean z, int i3) {
        this.mObserver.a(str, i, str2, i2, z, i3);
    }

    private void sessionIdChangedInd(int i, int i2) {
        this.mObserver.d(i, i2);
    }

    private void incomingCallInd(String str, String str2, String str3, String str4, int i, int i2, int i3) {
        this.mObserver.a(str, str2, str3, str4, i, i2, i3);
    }

    private void incomingCallCancelledInd(int i) {
        this.mObserver.i(i);
    }

    private void missedCallInd(String str, String str2, int i) {
        this.mObserver.b(str, str2, i);
    }

    private void acceptInvitationCnf(int i, int i2) {
        this.mObserver.e(i, i2);
    }

    private void rejectInvitationCnf(int i, int i2) {
        this.mObserver.f(i, i2);
    }

    private void disconnectSessionCnf(int i, int i2) {
        this.mObserver.g(i, i2);
    }

    private void sessionEndedInd(int i, int i2) {
        this.mObserver.h(i, i2);
    }

    private void supplementaryServiceCnf(int i, int i2, int i3) {
        this.mObserver.a(i, i2, i3);
    }

    private void callConferenceMergeCallsCnf(int i, int i2) {
        this.mObserver.i(i, i2);
    }

    private void callConferenceAddParticipantCnf(int i, int i2) {
        this.mObserver.j(i, i2);
    }

    private void callConferenceCreateCnf(int i, int i2) {
        this.mObserver.k(i, i2);
    }

    private void setUserAgentCnf(int i) {
        this.mObserver.j(i);
    }

    private void getJSONDataCnf(int i) {
    }

    private void getJSONDataInd(int i, int i2, String str, String str2) {
    }

    private void getJSONDataStopCnf(int i) {
    }

    private void callParkInd(boolean z) {
        this.mObserver.a(z);
    }

    private void rtcpRRInd(boolean z, int i, int i2, int i3, int i4) {
        this.mObserver.a(z, i, i2, i3, i4);
    }

    private void rtcpSRInd(boolean z, int i) {
        this.mObserver.a(z, i);
    }

    private void callHoldedInd(String str, String str2) {
        this.mObserver.a(str, str2);
    }

    private void callUnholdedInd(String str, String str2) {
        this.mObserver.b(str, str2);
    }

    private void provisioningCnf(int i, int i2, int i3, String str, int i4, String str2, String str3, String str4, String str5, String str6) {
        this.mObserver.a(i, i2, i3, str, i4, str2, str3, str4, str5, str6);
    }

    private void provisioningAuthInd(int i, String str, String str2) {
        this.mObserver.a(i, str, str2);
    }

    private void tlsVerifyCertReq(byte[] bArr, byte[][] bArr2, boolean z) {
        this.mObserver.a(bArr, bArr2, z);
    }

    private void playAlertToneInd() {
        this.mObserver.d();
    }

    private void qosStatsImprovedInd() {
        this.mObserver.e();
    }

    private void qosReportCnf(int i, int[] iArr, int[] iArr2, int[] iArr3, int[] iArr4, int[] iArr5, int[] iArr6) {
        this.mObserver.a(i, iArr, iArr2, iArr3, iArr4, iArr5, iArr6);
    }

    private void proxyPopupDisplayNeededInd(int i, int i2) {
        this.mObserver.l(i, i2);
    }

    private void videoViewShouldBeDisplayedInd() {
        this.mObserver.g();
    }

    private void unrecoverableSpiritErrorOccuredInd() {
        this.mObserver.h();
    }

    public void addVideoCnf(int i) {
        this.mObserver.m(i);
    }

    public void removeVideoCnf(int i) {
        this.mObserver.n(i);
    }

    public void videoAddedInd() {
        this.mObserver.i();
    }

    public void videoRemovedInd() {
        this.mObserver.j();
    }

    private void setCallContinuityCnf(int i) {
        this.mObserver.l(i);
    }

    private void stnReceivedInd(String str) {
        this.mObserver.a(str);
    }

    public void ussdStringReceivedInd(int i, String str, int i2) {
        this.mObserver.b(i, str, i2);
    }

    public void notifyRegStateInd(String str, int i, String str2) {
        this.mObserver.a(str, i, str2);
    }
}
