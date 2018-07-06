package com.mavenir.android.messaging.service;

public class MessagingAdapter {
    public static final int FGSIMM_ERR_ALREADY_SENT = 6;
    public static final int FGSIMM_ERR_GENERAL = 1;
    public static final int FGSIMM_ERR_INVALID_SESSION = 5;
    public static final int FGSIMM_ERR_INVALID_STATE = 4;
    public static final int FGSIMM_ERR_SERVER = 2;
    public static final int FGSIMM_ERR_SETUP = 3;
    public static final int FGSIMM_ERR_UNKNOWN = 7;
    public static final int FGSIMM_OK = 0;
    public static final int FGSIMM_STATUS_SMS_DELIVERY = 1;
    public static final int FGSIMM_STATUS_SMS_SUBMIT = 0;
    private a mObserver;

    public native void exit();

    public native void init();

    public native void setSmsSettingsReq(String str, String str2, int i, boolean z, int i2, int i3, int i4);

    public native void sipMessageSendReq(int i, int i2, String str, String str2, int i3, String[] strArr, String[] strArr2);

    public native void sipSmsSendReq(int i, int i2, String str, int i3, boolean[] zArr, String[] strArr, String[] strArr2);

    public MessagingAdapter(a aVar) {
        this.mObserver = aVar;
    }

    public int mapErrorCodes(int i) {
        return i;
    }

    public void sipMessageSendCnf(int i, int i2) {
        this.mObserver.a(i, i2);
    }

    public void sipMessageSendResp(int i, String str, String str2) {
        this.mObserver.a(i, str, str2);
    }

    public void sipMessageReceivedInd(int i, int i2, String str, String str2, String str3, String str4) {
        this.mObserver.a(i, i2, str, str2, str3, str4);
    }

    public void sipSmsSendCnf(int i, int i2, int i3, int[] iArr, String[] strArr, int[] iArr2, int[] iArr3) {
        this.mObserver.a(i, i2, i3, iArr, strArr, iArr2, iArr3);
    }

    public void sipSmsStatusInd(int i, int i2, int i3, String str) {
        this.mObserver.a(i, i2, i3, str);
    }

    public void sipSmsReceivedInd(int i, int i2, String str, String str2) {
        this.mObserver.a(i, i2, str, str2);
    }
}
