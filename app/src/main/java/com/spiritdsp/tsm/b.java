package com.spiritdsp.tsm;

import com.fgmicrotec.mobile.android.fgmag.VoIP;

public class b {
    static boolean a;
    static boolean b = true;
    static Integer[] c = new Integer[]{null, null};
    static boolean[] d = new boolean[]{false, false};
    static boolean e;
    static boolean f;

    static {
        a = true;
        e = false;
        f = false;
        switch (TSM_impl.mDeviceID) {
            case 19:
                e = true;
                return;
            case VoIP.ERR_SESSION_NOT_FOUND /*21*/:
                e = true;
                return;
            case VoIP.ERR_TRANSPORT_ERROR /*22*/:
            case VoIP.ERR_SOCKET_CONNECTION_BROKE_NET_DOWN /*23*/:
                e = true;
                return;
            case VoIP.REASON_CODE_REQUEST_TIMEOUT /*408*/:
                c[1] = Integer.valueOf(0);
                return;
            case VoIP.REASON_CODE_CONFLICT /*409*/:
            case VoIP.REASON_CODE_GONE /*410*/:
            case 413:
            case 414:
                d[0] = true;
                d[1] = true;
                return;
            case 420:
                f = true;
                return;
            case 423:
                c[0] = Integer.valueOf(270);
                d[0] = true;
                d[1] = true;
                e = true;
                return;
            case 425:
                e = true;
                return;
            case 2202:
                d[0] = true;
                d[1] = true;
                return;
            case 2600:
                d[0] = true;
                a = false;
                return;
            case 2601:
                a = false;
                return;
            case 2603:
                d[0] = true;
                d[1] = true;
                return;
            case 3100:
                c[1] = Integer.valueOf(270);
                return;
            default:
                return;
        }
    }

    static void a() {
        if (c[0] == null) {
            c[0] = Integer.valueOf(90);
        }
        if (c[1] == null) {
            c[1] = Integer.valueOf(90);
        }
    }
}
