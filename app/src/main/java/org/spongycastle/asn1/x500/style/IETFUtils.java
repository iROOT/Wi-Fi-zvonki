package org.spongycastle.asn1.x500.style;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Vector;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1String;
import org.spongycastle.asn1.DERUniversalString;
import org.spongycastle.asn1.x500.AttributeTypeAndValue;
import org.spongycastle.asn1.x500.RDN;
import org.spongycastle.asn1.x500.X500NameBuilder;
import org.spongycastle.asn1.x500.X500NameStyle;
import org.spongycastle.util.Strings;
import org.spongycastle.util.encoders.Hex;

public class IETFUtils {
    private static String c(String str) {
        if (str.length() == 0 || (str.indexOf(92) < 0 && str.indexOf(34) < 0)) {
            return str.trim();
        }
        int i;
        char[] toCharArray = str.toCharArray();
        StringBuffer stringBuffer = new StringBuffer(str.length());
        if (toCharArray[0] == '\\' && toCharArray[1] == '#') {
            i = 2;
            stringBuffer.append("\\#");
        } else {
            i = 0;
        }
        char c = '\u0000';
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        for (i = 
/*
Method generation error in method: org.spongycastle.asn1.x500.style.IETFUtils.c(java.lang.String):java.lang.String, dex: classes.dex
jadx.core.utils.exceptions.CodegenException: Error generate insn: PHI: (r0_7 'i' int) = (r0_6 'i' int), (r0_19 'i' int) binds: {(r0_6 'i' int)=B:11:0x0036, (r0_19 'i' int)=B:48:0x00b9} in method: org.spongycastle.asn1.x500.style.IETFUtils.c(java.lang.String):java.lang.String, dex: classes.dex
	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:226)
	at jadx.core.codegen.RegionGen.makeLoop(RegionGen.java:184)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:61)
	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:87)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:53)
	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:87)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:53)
	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:87)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:53)
	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:186)
	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:320)
	at jadx.core.codegen.ClassGen.addMethods(ClassGen.java:257)
	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:220)
	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:110)
	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:75)
	at jadx.core.codegen.CodeGen.visit(CodeGen.java:12)
	at jadx.core.ProcessClass.process(ProcessClass.java:40)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
Caused by: jadx.core.utils.exceptions.CodegenException: PHI can be used only in fallback mode
	at jadx.core.codegen.InsnGen.fallbackOnlyInsn(InsnGen.java:537)
	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:509)
	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:220)
	... 19 more

*/

        private static boolean a(char c) {
            return ('0' <= c && c <= '9') || (('a' <= c && c <= 'f') || ('A' <= c && c <= 'F'));
        }

        private static int b(char c) {
            if ('0' <= c && c <= '9') {
                return c - 48;
            }
            if ('a' > c || c > 'f') {
                return (c - 65) + 10;
            }
            return (c - 97) + 10;
        }

        public static RDN[] a(String str, X500NameStyle x500NameStyle) {
            X500NameTokenizer x500NameTokenizer = new X500NameTokenizer(str);
            X500NameBuilder x500NameBuilder = new X500NameBuilder(x500NameStyle);
            while (x500NameTokenizer.a()) {
                String b = x500NameTokenizer.b();
                X500NameTokenizer x500NameTokenizer2;
                if (b.indexOf(43) > 0) {
                    x500NameTokenizer2 = new X500NameTokenizer(b, '+');
                    X500NameTokenizer x500NameTokenizer3 = new X500NameTokenizer(x500NameTokenizer2.b(), '=');
                    String b2 = x500NameTokenizer3.b();
                    if (x500NameTokenizer3.a()) {
                        b = x500NameTokenizer3.b();
                        ASN1ObjectIdentifier a = x500NameStyle.a(b2.trim());
                        if (x500NameTokenizer2.a()) {
                            Vector vector = new Vector();
                            Vector vector2 = new Vector();
                            vector.addElement(a);
                            vector2.addElement(c(b));
                            while (x500NameTokenizer2.a()) {
                                x500NameTokenizer3 = new X500NameTokenizer(x500NameTokenizer2.b(), '=');
                                b2 = x500NameTokenizer3.b();
                                if (x500NameTokenizer3.a()) {
                                    b = x500NameTokenizer3.b();
                                    vector.addElement(x500NameStyle.a(b2.trim()));
                                    vector2.addElement(c(b));
                                } else {
                                    throw new IllegalArgumentException("badly formatted directory string");
                                }
                            }
                            x500NameBuilder.a(b(vector), a(vector2));
                        } else {
                            x500NameBuilder.a(a, c(b));
                        }
                    } else {
                        throw new IllegalArgumentException("badly formatted directory string");
                    }
                }
                x500NameTokenizer2 = new X500NameTokenizer(b, '=');
                b = x500NameTokenizer2.b();
                if (x500NameTokenizer2.a()) {
                    x500NameBuilder.a(x500NameStyle.a(b.trim()), c(x500NameTokenizer2.b()));
                } else {
                    throw new IllegalArgumentException("badly formatted directory string");
                }
            }
            return x500NameBuilder.a().d();
        }

        private static String[] a(Vector vector) {
            String[] strArr = new String[vector.size()];
            for (int i = 0; i != strArr.length; i++) {
                strArr[i] = (String) vector.elementAt(i);
            }
            return strArr;
        }

        private static ASN1ObjectIdentifier[] b(Vector vector) {
            ASN1ObjectIdentifier[] aSN1ObjectIdentifierArr = new ASN1ObjectIdentifier[vector.size()];
            for (int i = 0; i != aSN1ObjectIdentifierArr.length; i++) {
                aSN1ObjectIdentifierArr[i] = (ASN1ObjectIdentifier) vector.elementAt(i);
            }
            return aSN1ObjectIdentifierArr;
        }

        public static ASN1ObjectIdentifier a(String str, Hashtable hashtable) {
            if (Strings.b(str).startsWith("OID.")) {
                return new ASN1ObjectIdentifier(str.substring(4));
            }
            if (str.charAt(0) >= '0' && str.charAt(0) <= '9') {
                return new ASN1ObjectIdentifier(str);
            }
            ASN1ObjectIdentifier aSN1ObjectIdentifier = (ASN1ObjectIdentifier) hashtable.get(Strings.c(str));
            if (aSN1ObjectIdentifier != null) {
                return aSN1ObjectIdentifier;
            }
            throw new IllegalArgumentException("Unknown object id - " + str + " - passed to distinguished name");
        }

        public static ASN1Encodable a(String str, int i) {
            byte[] bArr = new byte[((str.length() - i) / 2)];
            for (int i2 = 0; i2 != bArr.length; i2++) {
                bArr[i2] = (byte) ((b(str.charAt((i2 * 2) + i)) << 4) | b(str.charAt(((i2 * 2) + i) + 1)));
            }
            return ASN1Primitive.a(bArr);
        }

        public static void a(StringBuffer stringBuffer, RDN rdn, Hashtable hashtable) {
            if (rdn.d()) {
                AttributeTypeAndValue[] f = rdn.f();
                Object obj = 1;
                for (int i = 0; i != f.length; i++) {
                    if (obj != null) {
                        obj = null;
                    } else {
                        stringBuffer.append('+');
                    }
                    a(stringBuffer, f[i], hashtable);
                }
                return;
            }
            a(stringBuffer, rdn.e(), hashtable);
        }

        public static void a(StringBuffer stringBuffer, AttributeTypeAndValue attributeTypeAndValue, Hashtable hashtable) {
            String str = (String) hashtable.get(attributeTypeAndValue.d());
            if (str != null) {
                stringBuffer.append(str);
            } else {
                stringBuffer.append(attributeTypeAndValue.d().d());
            }
            stringBuffer.append('=');
            stringBuffer.append(a(attributeTypeAndValue.e()));
        }

        public static String a(ASN1Encodable aSN1Encodable) {
            int i = 2;
            StringBuffer stringBuffer = new StringBuffer();
            if (!(aSN1Encodable instanceof ASN1String) || (aSN1Encodable instanceof DERUniversalString)) {
                try {
                    stringBuffer.append("#" + a(Hex.a(aSN1Encodable.a().a("DER"))));
                } catch (IOException e) {
                    throw new IllegalArgumentException("Other value has no encoded form");
                }
            }
            String a_ = ((ASN1String) aSN1Encodable).a_();
            if (a_.length() <= 0 || a_.charAt(0) != '#') {
                stringBuffer.append(a_);
            } else {
                stringBuffer.append("\\" + a_);
            }
            int length = stringBuffer.length();
            if (!(stringBuffer.length() >= 2 && stringBuffer.charAt(0) == '\\' && stringBuffer.charAt(1) == '#')) {
                i = 0;
            }
            while (i != length) {
                if (stringBuffer.charAt(i) == ',' || stringBuffer.charAt(i) == '\"' || stringBuffer.charAt(i) == '\\' || stringBuffer.charAt(i) == '+' || stringBuffer.charAt(i) == '=' || stringBuffer.charAt(i) == '<' || stringBuffer.charAt(i) == '>' || stringBuffer.charAt(i) == ';') {
                    stringBuffer.insert(i, "\\");
                    i++;
                    length++;
                }
                i++;
            }
            if (stringBuffer.length() > 0) {
                i = 0;
                while (stringBuffer.length() > i && stringBuffer.charAt(i) == ' ') {
                    stringBuffer.insert(i, "\\");
                    i += 2;
                }
            }
            i = stringBuffer.length() - 1;
            while (i >= 0 && stringBuffer.charAt(i) == ' ') {
                stringBuffer.insert(i, '\\');
                i--;
            }
            return stringBuffer.toString();
        }

        private static String a(byte[] bArr) {
            char[] cArr = new char[bArr.length];
            for (int i = 0; i != cArr.length; i++) {
                cArr[i] = (char) (bArr[i] & 255);
            }
            return new String(cArr);
        }

        public static String a(String str) {
            String c;
            String c2 = Strings.c(str.trim());
            if (c2.length() > 0 && c2.charAt(0) == '#') {
                ASN1Primitive d = d(c2);
                if (d instanceof ASN1String) {
                    c = Strings.c(((ASN1String) d).a_().trim());
                    return b(c);
                }
            }
            c = c2;
            return b(c);
        }

        private static ASN1Primitive d(String str) {
            try {
                return ASN1Primitive.a(Hex.a(str.substring(1)));
            } catch (IOException e) {
                throw new IllegalStateException("unknown encoding in name: " + e);
            }
        }

        public static String b(String str) {
            StringBuffer stringBuffer = new StringBuffer();
            if (str.length() != 0) {
                char charAt = str.charAt(0);
                stringBuffer.append(charAt);
                int i = 1;
                while (i < str.length()) {
                    char charAt2 = str.charAt(i);
                    if (charAt != ' ' || charAt2 != ' ') {
                        stringBuffer.append(charAt2);
                    }
                    i++;
                    charAt = charAt2;
                }
            }
            return stringBuffer.toString();
        }

        public static boolean a(RDN rdn, RDN rdn2) {
            if (rdn.d()) {
                if (!rdn2.d()) {
                    return false;
                }
                AttributeTypeAndValue[] f = rdn.f();
                AttributeTypeAndValue[] f2 = rdn2.f();
                if (f.length != f2.length) {
                    return false;
                }
                for (int i = 0; i != f.length; i++) {
                    if (!a(f[i], f2[i])) {
                        return false;
                    }
                }
                return true;
            } else if (rdn2.d()) {
                return false;
            } else {
                return a(rdn.e(), rdn2.e());
            }
        }

        private static boolean a(AttributeTypeAndValue attributeTypeAndValue, AttributeTypeAndValue attributeTypeAndValue2) {
            if (attributeTypeAndValue == attributeTypeAndValue2) {
                return true;
            }
            if (attributeTypeAndValue == null) {
                return false;
            }
            if (attributeTypeAndValue2 == null) {
                return false;
            }
            if (!attributeTypeAndValue.d().equals(attributeTypeAndValue2.d())) {
                return false;
            }
            if (a(a(attributeTypeAndValue.e())).equals(a(a(attributeTypeAndValue2.e())))) {
                return true;
            }
            return false;
        }
    }
