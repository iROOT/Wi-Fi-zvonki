package com.mavenir.android.services;

import java.util.ArrayList;

public class SupplementaryServicesAdapter {
    public a mObserver;

    public enum a {
        SS_ERROR_OK,
        SS_ERROR_UNAUTHORIZED,
        SS_ERROR_NETWORK_ISSUE,
        SS_ERROR_HTTP_ERROR
    }

    public enum b {
        SS_RULE_NOREPLAYTIMER,
        SS_RULE_CFU,
        SS_RULE_CFB,
        SS_RULE_CFNR,
        SS_RULE_CFNRC,
        SS_RULE_BAIC,
        SS_RULE_BICROAM,
        SS_RULE_BAOC,
        SS_RULE_BOIC,
        SS_RULE_BOICExHC
    }

    public enum c {
        SS_RULE_TYPE_NOREPLYTIMER,
        SS_RULE_TYPE_FORWARDING,
        SS_RULE_TYPE_ALLOW
    }

    public enum d {
        SS_SERVICE_CD,
        SS_SERVICE_ICB,
        SS_SERVICE_OCB,
        SS_SERVICE_CW,
        SS_SERVICE_OIP,
        SS_SERVICE_OIPR,
        SS_SERVICE_TIP,
        SS_SERVICE_TIPR
    }

    public native void exit();

    public native void getServicesReq();

    public native void init();

    public native void setServiceReq(int i, boolean z, int i2, int i3, boolean z2, int i4, boolean z3, String str, String str2);

    public native void setUserInfoReq(String str, String str2, String str3, String str4, int i, String str5, boolean z);

    public SupplementaryServicesAdapter(a aVar) {
        this.mObserver = aVar;
    }

    public void setUserInfoCnf(int i) {
        this.mObserver.a(i);
    }

    public void getServicesCnf(int i, int i2, ArrayList<SupplementaryService> arrayList) {
        this.mObserver.a(i, i2, arrayList);
    }

    public void setServiceCnf(int i, int i2) {
        this.mObserver.a(i, i2);
    }
}
