package com.mavenir.android.vtow.activation;

import com.mavenir.android.common.t.d;
import java.util.Date;

public class ConfigurationDataLte {
    private Boolean m_bLTEDeviceCapable;
    private boolean m_bLTENetworkEnabled;
    private Boolean m_bLTEUserActivated;
    private int m_nLTESIPPort;
    private int m_nLTETacMax;
    private int m_nLTETacMin;
    private int m_nPSMCC;
    private int m_nPSMNC;
    private int m_nReasonCode;
    private String m_strLTEExpires;
    private String[] m_strLTESBCFqdn;
    private String m_strNetworkTime;

    public ConfigurationDataLte(int i, boolean z, String str, String str2, Boolean bool, Boolean bool2, int i2, int i3, int i4, int i5, String[] strArr, int i6) {
        this.m_nReasonCode = i;
        this.m_bLTENetworkEnabled = z;
        this.m_strLTEExpires = str;
        this.m_strNetworkTime = str2;
        this.m_bLTEDeviceCapable = bool;
        this.m_bLTEUserActivated = bool2;
        this.m_nPSMNC = i2;
        this.m_nPSMCC = i3;
        this.m_nLTETacMin = i4;
        this.m_nLTETacMax = i5;
        this.m_strLTESBCFqdn = strArr;
        this.m_nLTESIPPort = i6;
    }

    public int getReasonCode() {
        return this.m_nReasonCode;
    }

    public void setReasonCode(int i) {
        this.m_nReasonCode = i;
    }

    public boolean isLTENetworkEnabled() {
        return this.m_bLTENetworkEnabled;
    }

    public void setLTENetworkEnabled(boolean z) {
        this.m_bLTENetworkEnabled = z;
    }

    public String getLTEExpires() {
        return this.m_strLTEExpires;
    }

    public long getLTEExpiresLong() {
        Date a = d.a(this.m_strLTEExpires, "dd.MM.yyyy kk:mm");
        return a != null ? a.getTime() : -1;
    }

    public void setLTEExpires(String str) {
        this.m_strLTEExpires = str;
    }

    public String getNetworkTime() {
        return this.m_strNetworkTime;
    }

    public void setNetworkTime(String str) {
        this.m_strNetworkTime = str;
    }

    public Boolean isLTEDeviceCapable() {
        return this.m_bLTEDeviceCapable;
    }

    public void setLTEDeviceCapable(Boolean bool) {
        this.m_bLTEDeviceCapable = bool;
    }

    public Boolean isLTEUserActivated() {
        return this.m_bLTEUserActivated;
    }

    public void setLTEUserActivated(Boolean bool) {
        this.m_bLTEUserActivated = bool;
    }

    public int getPSMNC() {
        return this.m_nPSMNC;
    }

    public void setPSMNC(int i) {
        this.m_nPSMNC = i;
    }

    public int getPSMCC() {
        return this.m_nPSMCC;
    }

    public void setPSMCC(int i) {
        this.m_nPSMCC = i;
    }

    public int getLTETacMin() {
        return this.m_nLTETacMin;
    }

    public void setLTETacMin(int i) {
        this.m_nLTETacMin = i;
    }

    public int getLTETacMax() {
        return this.m_nLTETacMax;
    }

    public void setLTETacMax(int i) {
        this.m_nLTETacMax = i;
    }

    public String[] getLTESBCFqdn() {
        return this.m_strLTESBCFqdn;
    }

    public void setLTESBCFqdn(String[] strArr) {
        this.m_strLTESBCFqdn = strArr;
    }

    public int getSIPPort() {
        return this.m_nLTESIPPort;
    }

    public void setSIPPort(int i) {
        this.m_nLTESIPPort = i;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Configuration data LTE: { ");
        stringBuilder.append("Reason code: " + this.m_nReasonCode);
        stringBuilder.append(", LTE device capable: " + this.m_bLTEDeviceCapable);
        stringBuilder.append(", LTE network enabled: " + this.m_bLTENetworkEnabled);
        stringBuilder.append(", LTE expires: " + this.m_strLTEExpires);
        stringBuilder.append(", Network time: " + this.m_strNetworkTime);
        stringBuilder.append(", PS MNC: " + this.m_nPSMNC);
        stringBuilder.append(", PS_MCC: " + this.m_nPSMCC);
        stringBuilder.append(", LTE TAC min: " + this.m_nLTETacMin);
        stringBuilder.append(", LTE TAC max: " + this.m_nLTETacMax);
        if (this.m_strLTESBCFqdn != null) {
            stringBuilder.append(", LTE SBC { ");
            for (String str : this.m_strLTESBCFqdn) {
                stringBuilder.append(str + " ");
            }
            stringBuilder.append("}");
        }
        stringBuilder.append(", LTE port: " + this.m_nLTESIPPort);
        stringBuilder.append(" }");
        return stringBuilder.toString();
    }
}
