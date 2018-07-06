package org.spongycastle.asn1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SimpleTimeZone;
import java.util.TimeZone;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.Strings;

public class DERGeneralizedTime extends ASN1Primitive {
    private byte[] a;

    public static ASN1GeneralizedTime a(Object obj) {
        if (obj == null || (obj instanceof ASN1GeneralizedTime)) {
            return (ASN1GeneralizedTime) obj;
        }
        if (obj instanceof DERGeneralizedTime) {
            return new ASN1GeneralizedTime(((DERGeneralizedTime) obj).a);
        }
        if (obj instanceof byte[]) {
            try {
                return (ASN1GeneralizedTime) ASN1Primitive.a((byte[]) obj);
            } catch (Exception e) {
                throw new IllegalArgumentException("encoding error in getInstance: " + e.toString());
            }
        }
        throw new IllegalArgumentException("illegal object in getInstance: " + obj.getClass().getName());
    }

    public static ASN1GeneralizedTime a(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        Object l = aSN1TaggedObject.l();
        if (z || (l instanceof DERGeneralizedTime)) {
            return a(l);
        }
        return new ASN1GeneralizedTime(((ASN1OctetString) l).e());
    }

    public DERGeneralizedTime(String str) {
        this.a = Strings.d(str);
        try {
            e();
        } catch (ParseException e) {
            throw new IllegalArgumentException("invalid date string: " + e.getMessage());
        }
    }

    DERGeneralizedTime(byte[] bArr) {
        this.a = bArr;
    }

    public String d() {
        String b = Strings.b(this.a);
        if (b.charAt(b.length() - 1) == 'Z') {
            return b.substring(0, b.length() - 1) + "GMT+00:00";
        }
        int length = b.length() - 5;
        char charAt = b.charAt(length);
        if (charAt == '-' || charAt == '+') {
            return b.substring(0, length) + "GMT" + b.substring(length, length + 3) + ":" + b.substring(length + 3);
        }
        length = b.length() - 3;
        charAt = b.charAt(length);
        if (charAt == '-' || charAt == '+') {
            return b.substring(0, length) + "GMT" + b.substring(length) + ":00";
        }
        return b + f();
    }

    private String f() {
        String str = "+";
        TimeZone timeZone = TimeZone.getDefault();
        int rawOffset = timeZone.getRawOffset();
        if (rawOffset < 0) {
            str = "-";
            rawOffset = -rawOffset;
        }
        int i = rawOffset / 3600000;
        int i2 = (rawOffset - (((i * 60) * 60) * 1000)) / 60000;
        try {
            if (timeZone.useDaylightTime() && timeZone.inDaylightTime(e())) {
                rawOffset = (str.equals("+") ? 1 : -1) + i;
                return "GMT" + str + a(rawOffset) + ":" + a(i2);
            }
            rawOffset = i;
            return "GMT" + str + a(rawOffset) + ":" + a(i2);
        } catch (ParseException e) {
            rawOffset = i;
        }
    }

    private String a(int i) {
        if (i < 10) {
            return "0" + i;
        }
        return Integer.toString(i);
    }

    public Date e() {
        SimpleDateFormat simpleDateFormat;
        String str;
        String b = Strings.b(this.a);
        SimpleDateFormat simpleDateFormat2;
        String str2;
        if (b.endsWith("Z")) {
            if (k()) {
                simpleDateFormat2 = new SimpleDateFormat("yyyyMMddHHmmss.SSS'Z'");
            } else {
                simpleDateFormat2 = new SimpleDateFormat("yyyyMMddHHmmss'Z'");
            }
            simpleDateFormat2.setTimeZone(new SimpleTimeZone(0, "Z"));
            str2 = b;
            simpleDateFormat = simpleDateFormat2;
            str = str2;
        } else if (b.indexOf(45) > 0 || b.indexOf(43) > 0) {
            b = d();
            if (k()) {
                simpleDateFormat2 = new SimpleDateFormat("yyyyMMddHHmmss.SSSz");
            } else {
                simpleDateFormat2 = new SimpleDateFormat("yyyyMMddHHmmssz");
            }
            simpleDateFormat2.setTimeZone(new SimpleTimeZone(0, "Z"));
            str2 = b;
            simpleDateFormat = simpleDateFormat2;
            str = str2;
        } else {
            if (k()) {
                simpleDateFormat2 = new SimpleDateFormat("yyyyMMddHHmmss.SSS");
            } else {
                simpleDateFormat2 = new SimpleDateFormat("yyyyMMddHHmmss");
            }
            simpleDateFormat2.setTimeZone(new SimpleTimeZone(0, TimeZone.getDefault().getID()));
            str2 = b;
            simpleDateFormat = simpleDateFormat2;
            str = str2;
        }
        if (k()) {
            String substring = str.substring(14);
            int i = 1;
            while (i < substring.length()) {
                char charAt = substring.charAt(i);
                if ('0' > charAt || charAt > '9') {
                    break;
                }
                i++;
            }
            if (i - 1 > 3) {
                str = str.substring(0, 14) + (substring.substring(0, 4) + substring.substring(i));
            } else if (i - 1 == 1) {
                str = str.substring(0, 14) + (substring.substring(0, i) + "00" + substring.substring(i));
            } else if (i - 1 == 2) {
                str = str.substring(0, 14) + (substring.substring(0, i) + "0" + substring.substring(i));
            }
        }
        return simpleDateFormat.parse(str);
    }

    private boolean k() {
        int i = 0;
        while (i != this.a.length) {
            if (this.a[i] == (byte) 46 && i == 14) {
                return true;
            }
            i++;
        }
        return false;
    }

    boolean i() {
        return false;
    }

    int j() {
        int length = this.a.length;
        return length + (StreamUtil.a(length) + 1);
    }

    void a(ASN1OutputStream aSN1OutputStream) {
        aSN1OutputStream.a(24, this.a);
    }

    boolean a(ASN1Primitive aSN1Primitive) {
        if (aSN1Primitive instanceof DERGeneralizedTime) {
            return Arrays.a(this.a, ((DERGeneralizedTime) aSN1Primitive).a);
        }
        return false;
    }

    public int hashCode() {
        return Arrays.a(this.a);
    }
}
