package com.mavenir.android.vtow.activation;

public class ConfigurationData {
    private boolean m_bDrvccFeatureEnable;
    private boolean m_bKeepAliveEnable;
    private int m_nAcceptableTimeDiff;
    private int m_nHSLostTmr;
    private int m_nInviteEarlyNoResponseTimeout;
    private int m_nKeepAliveIdleTime;
    private int m_nKeepAliveProbeCount;
    private int m_nKeepAliveProbeTime;
    private int m_nMaxJitter;
    private int m_nMaxRoundTripDelay;
    private int m_nMinWiFiThreshold;
    private int m_nNextSBCTmr;
    private int m_nNoMediaActivityTmr;
    private int m_nRTPMaxComulativeLoss;
    private int m_nRTPMaxFractionalLoss;
    private int m_nRegisterNoResponseRetryTimer;
    private int m_nRetryLoginTmr;
    private int m_nSIPPort;
    private int m_nSIPTransport;
    private int m_nSMSType;
    private int m_nStayAliveTmr;
    private int m_nTLSRevokeCheckTmr;
    private int m_nTLSSessionCacheTmr;
    private int m_nUTPort;
    private int m_nWifiHysterisisTmr;
    private int m_nWifiQoSThreshold;
    private Exception[] m_pExceptionListMsg;
    private int[] m_strBackOffTimerList;
    private String[] m_strCodec;
    private String m_strDrvccStnNumber;
    private String m_strEchoServiceNumber;
    private String[] m_strEmergencyNumbersList;
    private String m_strGetPublicIPURL;
    private String[] m_strNativeNumbersList;
    private String[] m_strSBCFqdn;
    private String m_strSMSC;
    private String m_strUTUri;
    private String m_strUserDeactivationMessage;

    public ConfigurationData(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12, int i13, int i14, int i15, int i16, int i17, int i18, int i19, boolean z, int i20, int i21, int i22, boolean z2, String str, String str2, String str3, String str4, String[] strArr, String[] strArr2, int[] iArr, String[] strArr3, String[] strArr4, String str5, String str6, int i23, int i24, Exception[] exceptionArr) {
        this.m_nSIPPort = i;
        this.m_nSIPTransport = i2;
        this.m_nSMSType = i3;
        this.m_nMinWiFiThreshold = i4;
        this.m_nWifiHysterisisTmr = i5;
        this.m_nNextSBCTmr = i6;
        this.m_nHSLostTmr = i7;
        this.m_nStayAliveTmr = i8;
        this.m_nRetryLoginTmr = i9;
        this.m_nNoMediaActivityTmr = i10;
        this.m_nWifiQoSThreshold = i11;
        this.m_nMaxJitter = i12;
        this.m_nMaxRoundTripDelay = i13;
        this.m_nRTPMaxComulativeLoss = i14;
        this.m_nRTPMaxFractionalLoss = i15;
        this.m_nTLSRevokeCheckTmr = i16;
        this.m_nTLSSessionCacheTmr = i17;
        this.m_nRegisterNoResponseRetryTimer = i18;
        this.m_nInviteEarlyNoResponseTimeout = i19;
        this.m_bKeepAliveEnable = z;
        this.m_nKeepAliveIdleTime = i20;
        this.m_nKeepAliveProbeTime = i21;
        this.m_nKeepAliveProbeCount = i22;
        this.m_bDrvccFeatureEnable = z2;
        this.m_strDrvccStnNumber = str;
        this.m_strEchoServiceNumber = str2;
        this.m_strSMSC = str3;
        this.m_strGetPublicIPURL = str4;
        this.m_strSBCFqdn = strArr;
        this.m_strCodec = strArr2;
        this.m_strBackOffTimerList = iArr;
        this.m_strEmergencyNumbersList = strArr3;
        this.m_strNativeNumbersList = strArr4;
        this.m_strUserDeactivationMessage = str5;
        this.m_strUTUri = str6;
        this.m_nUTPort = i23;
        this.m_nAcceptableTimeDiff = i24;
        this.m_pExceptionListMsg = exceptionArr;
    }

    public int getSIPPort() {
        return this.m_nSIPPort;
    }

    public void setSIPPort(int i) {
        this.m_nSIPPort = i;
    }

    public int getSIPTransport() {
        return this.m_nSIPTransport;
    }

    public void setSIPTransport(int i) {
        this.m_nSIPTransport = i;
    }

    public int getSMSType() {
        return this.m_nSMSType;
    }

    public void setSMSType(int i) {
        this.m_nSMSType = i;
    }

    public int getMinWiFiThreshold() {
        return this.m_nMinWiFiThreshold;
    }

    public void setMinWiFiThreshold(int i) {
        this.m_nMinWiFiThreshold = i;
    }

    public int getWifiHysterisisTmr() {
        return this.m_nWifiHysterisisTmr;
    }

    public void setWifiHysterisisTmr(int i) {
        this.m_nWifiHysterisisTmr = i;
    }

    public int getNextSBCTmr() {
        return this.m_nNextSBCTmr;
    }

    public void setNextSBCTmr(int i) {
        this.m_nNextSBCTmr = i;
    }

    public int getHSLostTmr() {
        return this.m_nHSLostTmr;
    }

    public void setHSLostTmr(int i) {
        this.m_nHSLostTmr = i;
    }

    public int getStayAliveTmr() {
        return this.m_nStayAliveTmr;
    }

    public void setStayAliveTmr(int i) {
        this.m_nStayAliveTmr = i;
    }

    public int getRetryLoginTmr() {
        return this.m_nRetryLoginTmr;
    }

    public int getNoMediaActivityTmr() {
        return this.m_nNoMediaActivityTmr;
    }

    public void setNoMediaActivityTmr(int i) {
        this.m_nNoMediaActivityTmr = i;
    }

    public int getWifiQoSThreshold() {
        return this.m_nWifiQoSThreshold;
    }

    public void setWifiQoSThreshold(int i) {
        this.m_nWifiQoSThreshold = i;
    }

    public int getMaxJitter() {
        return this.m_nMaxJitter;
    }

    public void setMaxJitter(int i) {
        this.m_nMaxJitter = i;
    }

    public int getMaxRoundTripDelay() {
        return this.m_nMaxRoundTripDelay;
    }

    public void setMaxRoundTripDelay(int i) {
        this.m_nMaxRoundTripDelay = i;
    }

    public int getRTPMaxComulativeLoss() {
        return this.m_nRTPMaxComulativeLoss;
    }

    public void setRTPMaxComulativeLoss(int i) {
        this.m_nRTPMaxComulativeLoss = i;
    }

    public int getRTPMaxFractionalLoss() {
        return this.m_nRTPMaxFractionalLoss;
    }

    public void setRTPMaxFractionalLoss(int i) {
        this.m_nRTPMaxFractionalLoss = i;
    }

    public int getTLSRevokeCheckTmr() {
        return this.m_nTLSRevokeCheckTmr;
    }

    public void setTLSRevokeCheckTmr(int i) {
        this.m_nTLSRevokeCheckTmr = i;
    }

    public int getTLSSessionCacheTmr() {
        return this.m_nTLSSessionCacheTmr;
    }

    public void setTLSSessionCacheTmr(int i) {
        this.m_nTLSSessionCacheTmr = i;
    }

    public int getRegisterNoResponseRetryTimer() {
        return this.m_nRegisterNoResponseRetryTimer;
    }

    public void setRegisterNoResponseRetryTimer(int i) {
        this.m_nRegisterNoResponseRetryTimer = i;
    }

    public int getInviteEarlyNoResponseTimeout() {
        return this.m_nInviteEarlyNoResponseTimeout;
    }

    public void setInviteEarlyNoResponseTimeout(int i) {
        this.m_nInviteEarlyNoResponseTimeout = i;
    }

    public boolean isKeepAliveEnable() {
        return this.m_bKeepAliveEnable;
    }

    public void setKeepAliveEnable(boolean z) {
        this.m_bKeepAliveEnable = z;
    }

    public int getKeepAliveIdleTime() {
        return this.m_nKeepAliveIdleTime;
    }

    public void setKeepAliveIdleTime(int i) {
        this.m_nKeepAliveIdleTime = i;
    }

    public int getKeepAliveProbeTime() {
        return this.m_nKeepAliveProbeTime;
    }

    public void setKeepAliveProbeTime(int i) {
        this.m_nKeepAliveProbeTime = i;
    }

    public int getKeepAliveProbeCount() {
        return this.m_nKeepAliveProbeCount;
    }

    public void setKeepAliveProbeCount(int i) {
        this.m_nKeepAliveProbeCount = i;
    }

    public boolean isDrvccFeatureEnable() {
        return this.m_bDrvccFeatureEnable;
    }

    public void setDrvccFeatureEnable(boolean z) {
        this.m_bDrvccFeatureEnable = z;
    }

    public String getDrvccStnNumber() {
        return this.m_strDrvccStnNumber;
    }

    public void setEchoServiceNumber(String str) {
        this.m_strEchoServiceNumber = str;
    }

    public String getEchoServiceNumber() {
        return this.m_strEchoServiceNumber;
    }

    public void setDrvccStnNumber(String str) {
        this.m_strDrvccStnNumber = str;
    }

    public String getSMSC() {
        return this.m_strSMSC;
    }

    public void setSMSC(String str) {
        this.m_strSMSC = str;
    }

    public String getGetPublicIPURL() {
        return this.m_strGetPublicIPURL;
    }

    public void setGetPublicIPURL(String str) {
        this.m_strGetPublicIPURL = str;
    }

    public String[] getSBCFqdn() {
        return this.m_strSBCFqdn;
    }

    public void setSBCFqdn(String[] strArr) {
        this.m_strSBCFqdn = strArr;
    }

    public String[] getCodec() {
        return this.m_strCodec;
    }

    public void setCodec(String[] strArr) {
        this.m_strCodec = strArr;
    }

    public int[] getBackOffTimerList() {
        return this.m_strBackOffTimerList;
    }

    public void setBackOffTimerList(int[] iArr) {
        this.m_strBackOffTimerList = iArr;
    }

    public String[] getEmergencyNumbersList() {
        return this.m_strEmergencyNumbersList;
    }

    public void setEmergencyNumbersList(String[] strArr) {
        this.m_strEmergencyNumbersList = strArr;
    }

    public String[] getNativeNumbersList() {
        return this.m_strNativeNumbersList;
    }

    public void setNativeNumbersList(String[] strArr) {
        this.m_strNativeNumbersList = strArr;
    }

    public String getUserDeactivationMessage() {
        return this.m_strUserDeactivationMessage;
    }

    public void setUserDeactivationMessage(String str) {
        this.m_strUserDeactivationMessage = str;
    }

    public String getUTUri() {
        return this.m_strUTUri;
    }

    public void setUTUri(String str) {
        this.m_strUTUri = str;
    }

    public int getUTPort() {
        return this.m_nUTPort;
    }

    public void setUTPort(int i) {
        this.m_nUTPort = i;
    }

    public int getAcceptableTimeDiff() {
        return this.m_nAcceptableTimeDiff;
    }

    public void setAcceptableTimeDiff(int i) {
        this.m_nAcceptableTimeDiff = i;
    }

    public Exception[] getExceptionListMsg() {
        return this.m_pExceptionListMsg;
    }

    public void setExceptionListMsg(Exception[] exceptionArr) {
        this.m_pExceptionListMsg = exceptionArr;
    }

    public String toString() {
        int i = 0;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Configuration data: { ");
        stringBuilder.append("minWifi: " + this.m_nMinWiFiThreshold);
        stringBuilder.append(", HysteresisTmr: " + this.m_nWifiHysterisisTmr);
        stringBuilder.append(", NextSBCTmr: " + this.m_nNextSBCTmr);
        stringBuilder.append(", HSLostTmr: " + this.m_nHSLostTmr);
        stringBuilder.append(", StayAliveTmr: " + this.m_nStayAliveTmr);
        stringBuilder.append(", RetryLoginTmr: " + this.m_nRetryLoginTmr);
        stringBuilder.append(", NoMediaTmr: " + this.m_nNoMediaActivityTmr);
        stringBuilder.append(", WifiQoSThreshold: " + this.m_nWifiQoSThreshold);
        stringBuilder.append(", MaxJitter: " + this.m_nMaxJitter);
        stringBuilder.append(", RTD: " + this.m_nMaxRoundTripDelay);
        stringBuilder.append(", MaxCumulLoss: " + this.m_nRTPMaxComulativeLoss);
        stringBuilder.append(", MaxFracLoss: " + this.m_nRTPMaxFractionalLoss);
        stringBuilder.append(", TLSCertRevokeTmr: " + this.m_nTLSRevokeCheckTmr);
        stringBuilder.append(", TLSSessionCacheTmr: " + this.m_nTLSSessionCacheTmr);
        stringBuilder.append(", RegisterNoResponseRetryTimer: " + this.m_nRegisterNoResponseRetryTimer);
        stringBuilder.append(", InviteEarlyNoResponseTimeout: " + this.m_nInviteEarlyNoResponseTimeout);
        stringBuilder.append(", bKeepAliveEnable: " + this.m_bKeepAliveEnable);
        stringBuilder.append(", nKeepAliveIdleTime: " + this.m_nKeepAliveIdleTime);
        stringBuilder.append(", nKeepAliveProbeTime: " + this.m_nKeepAliveProbeTime);
        stringBuilder.append(", nKeepAliveProbeCount: " + this.m_nKeepAliveProbeCount);
        stringBuilder.append(", bDrvccFeatureEnable: " + this.m_bDrvccFeatureEnable);
        stringBuilder.append(", DRVCC STN Number: " + this.m_strDrvccStnNumber);
        stringBuilder.append(", SMS type: " + this.m_nSMSType);
        stringBuilder.append(", SMSC: " + this.m_strSMSC);
        stringBuilder.append(", publicIPAddress: " + this.m_strGetPublicIPURL);
        if (this.m_strSBCFqdn != null) {
            stringBuilder.append(", SBC FQDN { ");
            for (String str : this.m_strSBCFqdn) {
                stringBuilder.append(str + " ");
            }
            stringBuilder.append("}");
        }
        stringBuilder.append(", SIP port: " + this.m_nSIPPort);
        stringBuilder.append(", SIP transport type: " + this.m_nSIPTransport);
        if (this.m_strCodec != null) {
            stringBuilder.append(", Codec { ");
            for (String str2 : this.m_strCodec) {
                stringBuilder.append(str2 + " ");
            }
            stringBuilder.append("}");
        }
        if (this.m_strBackOffTimerList != null) {
            stringBuilder.append(", BackOffTimer { ");
            for (int i2 : this.m_strBackOffTimerList) {
                stringBuilder.append(i2 + " ");
            }
            stringBuilder.append("}");
        }
        if (this.m_strEmergencyNumbersList != null) {
            stringBuilder.append(", Emergency number { ");
            for (String str22 : this.m_strEmergencyNumbersList) {
                stringBuilder.append(str22 + " ");
            }
            stringBuilder.append("}");
        }
        if (this.m_strNativeNumbersList != null) {
            stringBuilder.append(", Native number { ");
            String[] strArr = this.m_strNativeNumbersList;
            int length = strArr.length;
            while (i < length) {
                stringBuilder.append(strArr[i] + " ");
                i++;
            }
            stringBuilder.append("}");
        }
        stringBuilder.append(", User deactivation msg: " + this.m_strUserDeactivationMessage);
        stringBuilder.append(", UT URI: " + this.m_strUTUri);
        stringBuilder.append(", UT port: " + this.m_nUTPort);
        stringBuilder.append(", Acceptable time diff: " + this.m_nAcceptableTimeDiff);
        if (this.m_pExceptionListMsg != null) {
            stringBuilder.append(", nExceptionListSize: " + this.m_pExceptionListMsg.length);
        }
        stringBuilder.append(" }");
        return stringBuilder.toString();
    }
}
