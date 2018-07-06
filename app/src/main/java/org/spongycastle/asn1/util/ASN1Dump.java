package org.spongycastle.asn1.util;

import java.io.IOException;
import java.util.Enumeration;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1Set;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.BERApplicationSpecific;
import org.spongycastle.asn1.BERConstructedOctetString;
import org.spongycastle.asn1.BEROctetString;
import org.spongycastle.asn1.BERSequence;
import org.spongycastle.asn1.BERSet;
import org.spongycastle.asn1.BERTaggedObject;
import org.spongycastle.asn1.DERApplicationSpecific;
import org.spongycastle.asn1.DERBMPString;
import org.spongycastle.asn1.DERBitString;
import org.spongycastle.asn1.DERBoolean;
import org.spongycastle.asn1.DEREnumerated;
import org.spongycastle.asn1.DERExternal;
import org.spongycastle.asn1.DERGeneralizedTime;
import org.spongycastle.asn1.DERIA5String;
import org.spongycastle.asn1.DERNull;
import org.spongycastle.asn1.DERPrintableString;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERT61String;
import org.spongycastle.asn1.DERUTCTime;
import org.spongycastle.asn1.DERUTF8String;
import org.spongycastle.asn1.DERVisibleString;
import org.spongycastle.util.encoders.Hex;

public class ASN1Dump {
    static void a(String str, boolean z, ASN1Primitive aSN1Primitive, StringBuffer stringBuffer) {
        String property = System.getProperty("line.separator");
        Enumeration e;
        String str2;
        Object nextElement;
        if (aSN1Primitive instanceof ASN1Sequence) {
            e = ((ASN1Sequence) aSN1Primitive).e();
            str2 = str + "    ";
            stringBuffer.append(str);
            if (aSN1Primitive instanceof BERSequence) {
                stringBuffer.append("BER Sequence");
            } else if (aSN1Primitive instanceof DERSequence) {
                stringBuffer.append("DER Sequence");
            } else {
                stringBuffer.append("Sequence");
            }
            stringBuffer.append(property);
            while (e.hasMoreElements()) {
                nextElement = e.nextElement();
                if (nextElement == null || nextElement.equals(DERNull.a)) {
                    stringBuffer.append(str2);
                    stringBuffer.append("NULL");
                    stringBuffer.append(property);
                } else if (nextElement instanceof ASN1Primitive) {
                    a(str2, z, (ASN1Primitive) nextElement, stringBuffer);
                } else {
                    a(str2, z, ((ASN1Encodable) nextElement).a(), stringBuffer);
                }
            }
        } else if (aSN1Primitive instanceof ASN1TaggedObject) {
            r0 = str + "    ";
            stringBuffer.append(str);
            if (aSN1Primitive instanceof BERTaggedObject) {
                stringBuffer.append("BER Tagged [");
            } else {
                stringBuffer.append("Tagged [");
            }
            ASN1TaggedObject aSN1TaggedObject = (ASN1TaggedObject) aSN1Primitive;
            stringBuffer.append(Integer.toString(aSN1TaggedObject.d()));
            stringBuffer.append(']');
            if (!aSN1TaggedObject.e()) {
                stringBuffer.append(" IMPLICIT ");
            }
            stringBuffer.append(property);
            if (aSN1TaggedObject.k()) {
                stringBuffer.append(r0);
                stringBuffer.append("EMPTY");
                stringBuffer.append(property);
                return;
            }
            a(r0, z, aSN1TaggedObject.l(), stringBuffer);
        } else if (aSN1Primitive instanceof ASN1Set) {
            e = ((ASN1Set) aSN1Primitive).d();
            str2 = str + "    ";
            stringBuffer.append(str);
            if (aSN1Primitive instanceof BERSet) {
                stringBuffer.append("BER Set");
            } else {
                stringBuffer.append("DER Set");
            }
            stringBuffer.append(property);
            while (e.hasMoreElements()) {
                nextElement = e.nextElement();
                if (nextElement == null) {
                    stringBuffer.append(str2);
                    stringBuffer.append("NULL");
                    stringBuffer.append(property);
                } else if (nextElement instanceof ASN1Primitive) {
                    a(str2, z, (ASN1Primitive) nextElement, stringBuffer);
                } else {
                    a(str2, z, ((ASN1Encodable) nextElement).a(), stringBuffer);
                }
            }
        } else if (aSN1Primitive instanceof ASN1OctetString) {
            ASN1OctetString aSN1OctetString = (ASN1OctetString) aSN1Primitive;
            if ((aSN1Primitive instanceof BEROctetString) || (aSN1Primitive instanceof BERConstructedOctetString)) {
                stringBuffer.append(str + "BER Constructed Octet String" + "[" + aSN1OctetString.e().length + "] ");
            } else {
                stringBuffer.append(str + "DER Octet String" + "[" + aSN1OctetString.e().length + "] ");
            }
            if (z) {
                stringBuffer.append(a(str, aSN1OctetString.e()));
            } else {
                stringBuffer.append(property);
            }
        } else if (aSN1Primitive instanceof ASN1ObjectIdentifier) {
            stringBuffer.append(str + "ObjectIdentifier(" + ((ASN1ObjectIdentifier) aSN1Primitive).d() + ")" + property);
        } else if (aSN1Primitive instanceof DERBoolean) {
            stringBuffer.append(str + "Boolean(" + ((DERBoolean) aSN1Primitive).d() + ")" + property);
        } else if (aSN1Primitive instanceof ASN1Integer) {
            stringBuffer.append(str + "Integer(" + ((ASN1Integer) aSN1Primitive).d() + ")" + property);
        } else if (aSN1Primitive instanceof DERBitString) {
            DERBitString dERBitString = (DERBitString) aSN1Primitive;
            stringBuffer.append(str + "DER Bit String" + "[" + dERBitString.d().length + ", " + dERBitString.e() + "] ");
            if (z) {
                stringBuffer.append(a(str, dERBitString.d()));
            } else {
                stringBuffer.append(property);
            }
        } else if (aSN1Primitive instanceof DERIA5String) {
            stringBuffer.append(str + "IA5String(" + ((DERIA5String) aSN1Primitive).a_() + ") " + property);
        } else if (aSN1Primitive instanceof DERUTF8String) {
            stringBuffer.append(str + "UTF8String(" + ((DERUTF8String) aSN1Primitive).a_() + ") " + property);
        } else if (aSN1Primitive instanceof DERPrintableString) {
            stringBuffer.append(str + "PrintableString(" + ((DERPrintableString) aSN1Primitive).a_() + ") " + property);
        } else if (aSN1Primitive instanceof DERVisibleString) {
            stringBuffer.append(str + "VisibleString(" + ((DERVisibleString) aSN1Primitive).a_() + ") " + property);
        } else if (aSN1Primitive instanceof DERBMPString) {
            stringBuffer.append(str + "BMPString(" + ((DERBMPString) aSN1Primitive).a_() + ") " + property);
        } else if (aSN1Primitive instanceof DERT61String) {
            stringBuffer.append(str + "T61String(" + ((DERT61String) aSN1Primitive).a_() + ") " + property);
        } else if (aSN1Primitive instanceof DERUTCTime) {
            stringBuffer.append(str + "UTCTime(" + ((DERUTCTime) aSN1Primitive).e() + ") " + property);
        } else if (aSN1Primitive instanceof DERGeneralizedTime) {
            stringBuffer.append(str + "GeneralizedTime(" + ((DERGeneralizedTime) aSN1Primitive).d() + ") " + property);
        } else if (aSN1Primitive instanceof BERApplicationSpecific) {
            stringBuffer.append(a("BER", str, z, aSN1Primitive, property));
        } else if (aSN1Primitive instanceof DERApplicationSpecific) {
            stringBuffer.append(a("DER", str, z, aSN1Primitive, property));
        } else if (aSN1Primitive instanceof DEREnumerated) {
            stringBuffer.append(str + "DER Enumerated(" + ((DEREnumerated) aSN1Primitive).d() + ")" + property);
        } else if (aSN1Primitive instanceof DERExternal) {
            DERExternal dERExternal = (DERExternal) aSN1Primitive;
            stringBuffer.append(str + "External " + property);
            r0 = str + "    ";
            if (dERExternal.e() != null) {
                stringBuffer.append(r0 + "Direct Reference: " + dERExternal.e().d() + property);
            }
            if (dERExternal.l() != null) {
                stringBuffer.append(r0 + "Indirect Reference: " + dERExternal.l().toString() + property);
            }
            if (dERExternal.d() != null) {
                a(r0, z, dERExternal.d(), stringBuffer);
            }
            stringBuffer.append(r0 + "Encoding: " + dERExternal.f() + property);
            a(r0, z, dERExternal.k(), stringBuffer);
        } else {
            stringBuffer.append(str + aSN1Primitive.toString() + property);
        }
    }

    private static String a(String str, String str2, boolean z, ASN1Primitive aSN1Primitive, String str3) {
        DERApplicationSpecific dERApplicationSpecific = (DERApplicationSpecific) aSN1Primitive;
        StringBuffer stringBuffer = new StringBuffer();
        if (!dERApplicationSpecific.i()) {
            return str2 + str + " ApplicationSpecific[" + dERApplicationSpecific.e() + "] (" + new String(Hex.a(dERApplicationSpecific.d())) + ")" + str3;
        }
        try {
            ASN1Sequence a = ASN1Sequence.a(dERApplicationSpecific.a(16));
            stringBuffer.append(str2 + str + " ApplicationSpecific[" + dERApplicationSpecific.e() + "]" + str3);
            Enumeration e = a.e();
            while (e.hasMoreElements()) {
                a(str2 + "    ", z, (ASN1Primitive) e.nextElement(), stringBuffer);
            }
        } catch (IOException e2) {
            stringBuffer.append(e2);
        }
        return stringBuffer.toString();
    }

    public static String a(Object obj) {
        return a(obj, false);
    }

    public static String a(Object obj, boolean z) {
        StringBuffer stringBuffer = new StringBuffer();
        if (obj instanceof ASN1Primitive) {
            a("", z, (ASN1Primitive) obj, stringBuffer);
        } else if (!(obj instanceof ASN1Encodable)) {
            return "unknown object type " + obj.toString();
        } else {
            a("", z, ((ASN1Encodable) obj).a(), stringBuffer);
        }
        return stringBuffer.toString();
    }

    private static String a(String str, byte[] bArr) {
        String property = System.getProperty("line.separator");
        StringBuffer stringBuffer = new StringBuffer();
        String str2 = str + "    ";
        stringBuffer.append(property);
        for (int i = 0; i < bArr.length; i += 32) {
            if (bArr.length - i > 32) {
                stringBuffer.append(str2);
                stringBuffer.append(new String(Hex.a(bArr, i, 32)));
                stringBuffer.append("    ");
                stringBuffer.append(a(bArr, i, 32));
                stringBuffer.append(property);
            } else {
                stringBuffer.append(str2);
                stringBuffer.append(new String(Hex.a(bArr, i, bArr.length - i)));
                for (int length = bArr.length - i; length != 32; length++) {
                    stringBuffer.append("  ");
                }
                stringBuffer.append("    ");
                stringBuffer.append(a(bArr, i, bArr.length - i));
                stringBuffer.append(property);
            }
        }
        return stringBuffer.toString();
    }

    private static String a(byte[] bArr, int i, int i2) {
        StringBuffer stringBuffer = new StringBuffer();
        int i3 = i;
        while (i3 != i + i2) {
            if (bArr[i3] >= (byte) 32 && bArr[i3] <= (byte) 126) {
                stringBuffer.append((char) bArr[i3]);
            }
            i3++;
        }
        return stringBuffer.toString();
    }
}
