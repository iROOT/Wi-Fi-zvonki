package com.fgmicrotec.mobile.android.fgvoip;

import com.fgmicrotec.mobile.android.fgmag.VoIP;

public class g {
    public static String a(int i) {
        String str = "";
        switch (i) {
            case 1:
                return "Error: General";
            case 2:
                return "Error: Already logged in";
            case 3:
                return "Error: Already logging in";
            case 4:
                return "Error: Already logging out";
            case 5:
                return "Error: Not logged in";
            case 6:
                return "Error: Authentication";
            case 7:
                return "Error: Connection";
            case 8:
                return "Error: Cannot create socket";
            case 9:
                return "Error: Cannot bind socket";
            case 10:
                return "Error: STUN failed";
            case 12:
                return "Error: SIP init failed";
            case 13:
                return "Error: SIP server";
            case 14:
                return "Error: Session init";
            case 15:
                return "Error: Wrong parameters";
            case 16:
                return "Error: Not allowed";
            case 18:
                return "Error: User canceled";
            case 19:
                return "Error: Socket connection broke";
            case VoIP.ERR_SIP_TIMED_OUT /*20*/:
                return "Error: SIP timeout";
            case VoIP.ERR_SESSION_NOT_FOUND /*21*/:
                return "Error: Session not found";
            default:
                return "Error: Unknown!";
        }
    }
}
