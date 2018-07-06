package com.mavenir.android.services;

import com.mavenir.android.services.SupplementaryServicesAdapter.b;
import com.mavenir.android.services.SupplementaryServicesAdapter.c;

public class SupplementaryServiceRule {
    private boolean mActionAllowValue;
    private String mForwardingValue;
    private boolean mIsActive;
    private int mNoReplyTimerValue;
    private String mRuleId = "";
    private b mRuleType;
    private c mRuleValueType;

    public SupplementaryServiceRule(int i, int i2, boolean z, int i3, boolean z2, String str, String str2) {
        setRuleType(b.values()[i]);
        setRuleValueType(c.values()[i2]);
        setIsActive(z);
        setNoReplyTimerValue(i3);
        setActionAllowed(z2);
        setForwardingValue(str);
        setRuleId(str2);
    }

    public SupplementaryServiceRule(b bVar, c cVar, boolean z, int i, boolean z2, String str, String str2) {
        setRuleType(bVar);
        setRuleValueType(cVar);
        setIsActive(z);
        setNoReplyTimerValue(i);
        setActionAllowed(z2);
        setForwardingValue(str);
        setRuleId(str2);
    }

    public b getRuleType() {
        return this.mRuleType;
    }

    public void setRuleType(b bVar) {
        this.mRuleType = bVar;
    }

    public c getRuleValueType() {
        return this.mRuleValueType;
    }

    public void setRuleValueType(c cVar) {
        this.mRuleValueType = cVar;
    }

    public boolean isActive() {
        return this.mIsActive;
    }

    public void setIsActive(boolean z) {
        this.mIsActive = z;
    }

    public int getNoReplyTimerValue() {
        return this.mNoReplyTimerValue;
    }

    public void setNoReplyTimerValue(int i) {
        this.mNoReplyTimerValue = i;
    }

    public boolean isActionAllowed() {
        return this.mActionAllowValue;
    }

    public void setActionAllowed(boolean z) {
        this.mActionAllowValue = z;
    }

    public String getForwardingValue() {
        return this.mForwardingValue;
    }

    public void setForwardingValue(String str) {
        this.mForwardingValue = str;
    }

    public String getRuleId() {
        return this.mRuleId;
    }

    public void setRuleId(String str) {
        this.mRuleId = str;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Rule: id: " + this.mRuleId);
        stringBuffer.append(", type: " + this.mRuleType.name());
        stringBuffer.append(", isActive: " + this.mIsActive);
        stringBuffer.append(", ruleValueType: " + this.mRuleValueType.name());
        if (this.mRuleValueType == c.SS_RULE_TYPE_ALLOW) {
            stringBuffer.append(", allow: " + this.mActionAllowValue);
        } else if (this.mRuleValueType == c.SS_RULE_TYPE_FORWARDING) {
            stringBuffer.append(", Forward-to: " + this.mForwardingValue);
        } else if (this.mRuleValueType == c.SS_RULE_TYPE_NOREPLYTIMER) {
            stringBuffer.append(", No-reply timer: " + this.mNoReplyTimerValue);
        }
        return stringBuffer.toString();
    }
}
