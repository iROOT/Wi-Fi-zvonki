package com.mavenir.android.services;

import com.mavenir.android.services.SupplementaryServicesAdapter.b;
import com.mavenir.android.services.SupplementaryServicesAdapter.d;
import java.util.ArrayList;
import java.util.List;

public class SupplementaryService {
    private boolean mIsActive;
    private List<SupplementaryServiceRule> mRules;
    private d mServiceType;

    public SupplementaryService(int i, boolean z, ArrayList<SupplementaryServiceRule> arrayList) {
        setServiceType(d.values()[i]);
        setIsActive(z);
        setRules(arrayList);
    }

    public SupplementaryService(d dVar, boolean z, List<SupplementaryServiceRule> list) {
        setServiceType(dVar);
        setIsActive(z);
        setRules(list);
    }

    public d getServiceType() {
        return this.mServiceType;
    }

    public void setServiceType(d dVar) {
        this.mServiceType = dVar;
    }

    public boolean isActive() {
        return this.mIsActive;
    }

    public void setIsActive(boolean z) {
        this.mIsActive = z;
    }

    public List<SupplementaryServiceRule> getRules() {
        return this.mRules;
    }

    public void setRules(List<SupplementaryServiceRule> list) {
        this.mRules = list;
    }

    public SupplementaryServiceRule getRuleByType(b bVar) {
        if (this.mRules != null) {
            for (SupplementaryServiceRule supplementaryServiceRule : this.mRules) {
                if (supplementaryServiceRule.getRuleType() == bVar) {
                    return supplementaryServiceRule;
                }
            }
        }
        return null;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Service: ");
        stringBuffer.append("type: " + this.mServiceType.name());
        stringBuffer.append(", isActive: " + isActive());
        if (this.mRules != null) {
            for (SupplementaryServiceRule supplementaryServiceRule : this.mRules) {
                stringBuffer.append("\n\t" + supplementaryServiceRule.toString());
            }
        }
        return stringBuffer.toString();
    }
}
