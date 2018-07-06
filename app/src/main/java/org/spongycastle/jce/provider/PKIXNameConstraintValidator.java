package org.spongycastle.jce.provider;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERIA5String;
import org.spongycastle.asn1.x509.GeneralName;
import org.spongycastle.asn1.x509.GeneralSubtree;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.Integers;
import org.spongycastle.util.Strings;

public class PKIXNameConstraintValidator {
    private Set a = new HashSet();
    private Set b = new HashSet();
    private Set c = new HashSet();
    private Set d = new HashSet();
    private Set e = new HashSet();
    private Set f;
    private Set g;
    private Set h;
    private Set i;
    private Set j;

    private static boolean a(ASN1Sequence aSN1Sequence, ASN1Sequence aSN1Sequence2) {
        if (aSN1Sequence2.f() < 1 || aSN1Sequence2.f() > aSN1Sequence.f()) {
            return false;
        }
        for (int f = aSN1Sequence2.f() - 1; f >= 0; f--) {
            if (!aSN1Sequence2.a(f).equals(aSN1Sequence.a(f))) {
                return false;
            }
        }
        return true;
    }

    public void a(ASN1Sequence aSN1Sequence) {
        a(this.f, aSN1Sequence);
    }

    public void b(ASN1Sequence aSN1Sequence) {
        b(this.a, aSN1Sequence);
    }

    private void a(Set set, ASN1Sequence aSN1Sequence) {
        if (set != null) {
            if (!set.isEmpty() || aSN1Sequence.f() != 0) {
                for (ASN1Sequence a : set) {
                    if (a(aSN1Sequence, a)) {
                        return;
                    }
                }
                throw new PKIXNameConstraintValidatorException("Subject distinguished name is not from a permitted subtree");
            }
        }
    }

    private void b(Set set, ASN1Sequence aSN1Sequence) {
        if (!set.isEmpty()) {
            for (ASN1Sequence a : set) {
                if (a(aSN1Sequence, a)) {
                    throw new PKIXNameConstraintValidatorException("Subject distinguished name is from an excluded subtree");
                }
            }
        }
    }

    private Set a(Set set, Set set2) {
        Set hashSet = new HashSet();
        for (GeneralSubtree d : set2) {
            ASN1Sequence a = ASN1Sequence.a(d.d().e().a());
            if (set != null) {
                for (ASN1Sequence aSN1Sequence : set) {
                    if (a(a, aSN1Sequence)) {
                        hashSet.add(a);
                    } else if (a(aSN1Sequence, a)) {
                        hashSet.add(aSN1Sequence);
                    }
                }
            } else if (a != null) {
                hashSet.add(a);
            }
        }
        return hashSet;
    }

    private Set c(Set set, ASN1Sequence aSN1Sequence) {
        if (!set.isEmpty()) {
            Set hashSet = new HashSet();
            for (ASN1Sequence aSN1Sequence2 : set) {
                if (a(aSN1Sequence, aSN1Sequence2)) {
                    hashSet.add(aSN1Sequence2);
                } else if (a(aSN1Sequence2, aSN1Sequence)) {
                    hashSet.add(aSN1Sequence);
                } else {
                    hashSet.add(aSN1Sequence2);
                    hashSet.add(aSN1Sequence);
                }
            }
            return hashSet;
        } else if (aSN1Sequence == null) {
            return set;
        } else {
            set.add(aSN1Sequence);
            return set;
        }
    }

    private Set b(Set set, Set set2) {
        Set hashSet = new HashSet();
        for (GeneralSubtree d : set2) {
            String c = c(d.d());
            if (set != null) {
                for (String c2 : set) {
                    c(c, c2, hashSet);
                }
            } else if (c != null) {
                hashSet.add(c);
            }
        }
        return hashSet;
    }

    private Set b(Set set, String str) {
        if (!set.isEmpty()) {
            Set hashSet = new HashSet();
            for (String a : set) {
                a(a, str, hashSet);
            }
            return hashSet;
        } else if (str == null) {
            return set;
        } else {
            set.add(str);
            return set;
        }
    }

    private Set c(Set set, Set set2) {
        Set hashSet = new HashSet();
        for (GeneralSubtree d : set2) {
            byte[] e = ASN1OctetString.a(d.d().e()).e();
            if (set != null) {
                for (byte[] b : set) {
                    hashSet.addAll(b(b, e));
                }
            } else if (e != null) {
                hashSet.add(e);
            }
        }
        return hashSet;
    }

    private Set a(Set set, byte[] bArr) {
        if (!set.isEmpty()) {
            Set hashSet = new HashSet();
            for (byte[] a : set) {
                hashSet.addAll(a(a, bArr));
            }
            return hashSet;
        } else if (bArr == null) {
            return set;
        } else {
            set.add(bArr);
            return set;
        }
    }

    private Set a(byte[] bArr, byte[] bArr2) {
        Set hashSet = new HashSet();
        if (Arrays.a(bArr, bArr2)) {
            hashSet.add(bArr);
        } else {
            hashSet.add(bArr);
            hashSet.add(bArr2);
        }
        return hashSet;
    }

    private Set b(byte[] bArr, byte[] bArr2) {
        if (bArr.length != bArr2.length) {
            return Collections.EMPTY_SET;
        }
        byte[][] d = d(bArr, bArr2);
        byte[] bArr3 = d[0];
        byte[] bArr4 = d[1];
        byte[] bArr5 = d[2];
        byte[] bArr6 = d[3];
        byte[][] a = a(bArr3, bArr4, bArr5, bArr6);
        if (h(f(a[0], a[2]), g(a[1], a[3])) == 1) {
            return Collections.EMPTY_SET;
        }
        return Collections.singleton(c(i(a[0], a[2]), i(bArr4, bArr6)));
    }

    private byte[] c(byte[] bArr, byte[] bArr2) {
        int length = bArr.length;
        Object obj = new byte[(length * 2)];
        System.arraycopy(bArr, 0, obj, 0, length);
        System.arraycopy(bArr2, 0, obj, length, length);
        return obj;
    }

    private byte[][] d(byte[] bArr, byte[] bArr2) {
        int length = bArr.length / 2;
        Object obj = new byte[length];
        System.arraycopy(bArr, 0, new byte[length], 0, length);
        System.arraycopy(bArr, length, obj, 0, length);
        Object obj2 = new byte[length];
        System.arraycopy(bArr2, 0, new byte[length], 0, length);
        System.arraycopy(bArr2, length, obj2, 0, length);
        return new byte[][]{r1, obj, r3, obj2};
    }

    private byte[][] a(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4) {
        int length = bArr.length;
        byte[] bArr5 = new byte[length];
        byte[] bArr6 = new byte[length];
        byte[] bArr7 = new byte[length];
        byte[] bArr8 = new byte[length];
        for (int i = 0; i < length; i++) {
            bArr5[i] = (byte) (bArr[i] & bArr2[i]);
            bArr6[i] = (byte) ((bArr[i] & bArr2[i]) | (bArr2[i] ^ -1));
            bArr7[i] = (byte) (bArr3[i] & bArr4[i]);
            bArr8[i] = (byte) ((bArr3[i] & bArr4[i]) | (bArr4[i] ^ -1));
        }
        return new byte[][]{bArr5, bArr6, bArr7, bArr8};
    }

    private void c(Set set, String str) {
        if (set != null) {
            for (String a : set) {
                if (a(str, a)) {
                    return;
                }
            }
            if (str.length() != 0 || set.size() != 0) {
                throw new PKIXNameConstraintValidatorException("Subject email address is not from a permitted subtree.");
            }
        }
    }

    private void d(Set set, String str) {
        if (!set.isEmpty()) {
            for (String a : set) {
                if (a(str, a)) {
                    throw new PKIXNameConstraintValidatorException("Email address is from an excluded subtree.");
                }
            }
        }
    }

    private void b(Set set, byte[] bArr) {
        if (set != null) {
            for (byte[] e : set) {
                if (e(bArr, e)) {
                    return;
                }
            }
            if (bArr.length != 0 || set.size() != 0) {
                throw new PKIXNameConstraintValidatorException("IP is not from a permitted subtree.");
            }
        }
    }

    private void c(Set set, byte[] bArr) {
        if (!set.isEmpty()) {
            for (byte[] e : set) {
                if (e(bArr, e)) {
                    throw new PKIXNameConstraintValidatorException("IP is from an excluded subtree.");
                }
            }
        }
    }

    private boolean e(byte[] bArr, byte[] bArr2) {
        int i = 0;
        int length = bArr.length;
        if (length != bArr2.length / 2) {
            return false;
        }
        Object obj = new byte[length];
        System.arraycopy(bArr2, length, obj, 0, length);
        byte[] bArr3 = new byte[length];
        byte[] bArr4 = new byte[length];
        while (i < length) {
            bArr3[i] = (byte) (bArr2[i] & obj[i]);
            bArr4[i] = (byte) (bArr[i] & obj[i]);
            i++;
        }
        return Arrays.a(bArr3, bArr4);
    }

    private boolean a(String str, String str2) {
        String substring = str.substring(str.indexOf(64) + 1);
        if (str2.indexOf(64) != -1) {
            if (str.equalsIgnoreCase(str2)) {
                return true;
            }
        } else if (str2.charAt(0) != '.') {
            if (substring.equalsIgnoreCase(str2)) {
                return true;
            }
        } else if (b(substring, str2)) {
            return true;
        }
        return false;
    }

    private boolean b(String str, String str2) {
        if (str2.startsWith(".")) {
            str2 = str2.substring(1);
        }
        String[] a = Strings.a(str2, '.');
        String[] a2 = Strings.a(str, '.');
        if (a2.length <= a.length) {
            return false;
        }
        int length = a2.length - a.length;
        for (int i = -1; i < a.length; i++) {
            if (i == -1) {
                if (a2[i + length].equals("")) {
                    return false;
                }
            } else if (!a[i].equalsIgnoreCase(a2[i + length])) {
                return false;
            }
        }
        return true;
    }

    private void e(Set set, String str) {
        if (set != null) {
            for (String str2 : set) {
                if (!b(str, str2)) {
                    if (str.equalsIgnoreCase(str2)) {
                        return;
                    }
                }
                return;
            }
            if (str.length() != 0 || set.size() != 0) {
                throw new PKIXNameConstraintValidatorException("DNS is not from a permitted subtree.");
            }
        }
    }

    private void f(Set set, String str) {
        if (!set.isEmpty()) {
            for (String str2 : set) {
                if (!b(str, str2)) {
                    if (str.equalsIgnoreCase(str2)) {
                    }
                }
                throw new PKIXNameConstraintValidatorException("DNS is from an excluded subtree.");
            }
        }
    }

    private void a(String str, String str2, Set set) {
        if (str.indexOf(64) != -1) {
            String substring = str.substring(str.indexOf(64) + 1);
            if (str2.indexOf(64) != -1) {
                if (str.equalsIgnoreCase(str2)) {
                    set.add(str);
                    return;
                }
                set.add(str);
                set.add(str2);
            } else if (str2.startsWith(".")) {
                if (b(substring, str2)) {
                    set.add(str2);
                    return;
                }
                set.add(str);
                set.add(str2);
            } else if (substring.equalsIgnoreCase(str2)) {
                set.add(str2);
            } else {
                set.add(str);
                set.add(str2);
            }
        } else if (str.startsWith(".")) {
            if (str2.indexOf(64) != -1) {
                if (b(str2.substring(str.indexOf(64) + 1), str)) {
                    set.add(str);
                    return;
                }
                set.add(str);
                set.add(str2);
            } else if (str2.startsWith(".")) {
                if (b(str, str2) || str.equalsIgnoreCase(str2)) {
                    set.add(str2);
                } else if (b(str2, str)) {
                    set.add(str);
                } else {
                    set.add(str);
                    set.add(str2);
                }
            } else if (b(str2, str)) {
                set.add(str);
            } else {
                set.add(str);
                set.add(str2);
            }
        } else if (str2.indexOf(64) != -1) {
            if (str2.substring(str.indexOf(64) + 1).equalsIgnoreCase(str)) {
                set.add(str);
                return;
            }
            set.add(str);
            set.add(str2);
        } else if (str2.startsWith(".")) {
            if (b(str, str2)) {
                set.add(str2);
                return;
            }
            set.add(str);
            set.add(str2);
        } else if (str.equalsIgnoreCase(str2)) {
            set.add(str);
        } else {
            set.add(str);
            set.add(str2);
        }
    }

    private void b(String str, String str2, Set set) {
        if (str.indexOf(64) != -1) {
            String substring = str.substring(str.indexOf(64) + 1);
            if (str2.indexOf(64) != -1) {
                if (str.equalsIgnoreCase(str2)) {
                    set.add(str);
                    return;
                }
                set.add(str);
                set.add(str2);
            } else if (str2.startsWith(".")) {
                if (b(substring, str2)) {
                    set.add(str2);
                    return;
                }
                set.add(str);
                set.add(str2);
            } else if (substring.equalsIgnoreCase(str2)) {
                set.add(str2);
            } else {
                set.add(str);
                set.add(str2);
            }
        } else if (str.startsWith(".")) {
            if (str2.indexOf(64) != -1) {
                if (b(str2.substring(str.indexOf(64) + 1), str)) {
                    set.add(str);
                    return;
                }
                set.add(str);
                set.add(str2);
            } else if (str2.startsWith(".")) {
                if (b(str, str2) || str.equalsIgnoreCase(str2)) {
                    set.add(str2);
                } else if (b(str2, str)) {
                    set.add(str);
                } else {
                    set.add(str);
                    set.add(str2);
                }
            } else if (b(str2, str)) {
                set.add(str);
            } else {
                set.add(str);
                set.add(str2);
            }
        } else if (str2.indexOf(64) != -1) {
            if (str2.substring(str.indexOf(64) + 1).equalsIgnoreCase(str)) {
                set.add(str);
                return;
            }
            set.add(str);
            set.add(str2);
        } else if (str2.startsWith(".")) {
            if (b(str, str2)) {
                set.add(str2);
                return;
            }
            set.add(str);
            set.add(str2);
        } else if (str.equalsIgnoreCase(str2)) {
            set.add(str);
        } else {
            set.add(str);
            set.add(str2);
        }
    }

    private Set d(Set set, Set set2) {
        Set hashSet = new HashSet();
        for (GeneralSubtree d : set2) {
            String c = c(d.d());
            if (set != null) {
                for (String str : set) {
                    if (b(str, c)) {
                        hashSet.add(str);
                    } else if (b(c, str)) {
                        hashSet.add(c);
                    }
                }
            } else if (c != null) {
                hashSet.add(c);
            }
        }
        return hashSet;
    }

    protected Set a(Set set, String str) {
        if (!set.isEmpty()) {
            Set hashSet = new HashSet();
            for (String str2 : set) {
                if (b(str2, str)) {
                    hashSet.add(str);
                } else if (b(str, str2)) {
                    hashSet.add(str2);
                } else {
                    hashSet.add(str2);
                    hashSet.add(str);
                }
            }
            return hashSet;
        } else if (str == null) {
            return set;
        } else {
            set.add(str);
            return set;
        }
    }

    private void c(String str, String str2, Set set) {
        if (str.indexOf(64) != -1) {
            String substring = str.substring(str.indexOf(64) + 1);
            if (str2.indexOf(64) != -1) {
                if (str.equalsIgnoreCase(str2)) {
                    set.add(str);
                }
            } else if (str2.startsWith(".")) {
                if (b(substring, str2)) {
                    set.add(str);
                }
            } else if (substring.equalsIgnoreCase(str2)) {
                set.add(str);
            }
        } else if (str.startsWith(".")) {
            if (str2.indexOf(64) != -1) {
                if (b(str2.substring(str.indexOf(64) + 1), str)) {
                    set.add(str2);
                }
            } else if (str2.startsWith(".")) {
                if (b(str, str2) || str.equalsIgnoreCase(str2)) {
                    set.add(str);
                } else if (b(str2, str)) {
                    set.add(str2);
                }
            } else if (b(str2, str)) {
                set.add(str2);
            }
        } else if (str2.indexOf(64) != -1) {
            if (str2.substring(str2.indexOf(64) + 1).equalsIgnoreCase(str)) {
                set.add(str2);
            }
        } else if (str2.startsWith(".")) {
            if (b(str, str2)) {
                set.add(str);
            }
        } else if (str.equalsIgnoreCase(str2)) {
            set.add(str);
        }
    }

    private void g(Set set, String str) {
        if (!set.isEmpty()) {
            for (String c : set) {
                if (c(str, c)) {
                    throw new PKIXNameConstraintValidatorException("URI is from an excluded subtree.");
                }
            }
        }
    }

    private Set e(Set set, Set set2) {
        Set hashSet = new HashSet();
        for (GeneralSubtree d : set2) {
            String c = c(d.d());
            if (set != null) {
                for (String d2 : set) {
                    d(d2, c, hashSet);
                }
            } else if (c != null) {
                hashSet.add(c);
            }
        }
        return hashSet;
    }

    private Set h(Set set, String str) {
        if (!set.isEmpty()) {
            Set hashSet = new HashSet();
            for (String b : set) {
                b(b, str, hashSet);
            }
            return hashSet;
        } else if (str == null) {
            return set;
        } else {
            set.add(str);
            return set;
        }
    }

    private void d(String str, String str2, Set set) {
        if (str.indexOf(64) != -1) {
            String substring = str.substring(str.indexOf(64) + 1);
            if (str2.indexOf(64) != -1) {
                if (str.equalsIgnoreCase(str2)) {
                    set.add(str);
                }
            } else if (str2.startsWith(".")) {
                if (b(substring, str2)) {
                    set.add(str);
                }
            } else if (substring.equalsIgnoreCase(str2)) {
                set.add(str);
            }
        } else if (str.startsWith(".")) {
            if (str2.indexOf(64) != -1) {
                if (b(str2.substring(str.indexOf(64) + 1), str)) {
                    set.add(str2);
                }
            } else if (str2.startsWith(".")) {
                if (b(str, str2) || str.equalsIgnoreCase(str2)) {
                    set.add(str);
                } else if (b(str2, str)) {
                    set.add(str2);
                }
            } else if (b(str2, str)) {
                set.add(str2);
            }
        } else if (str2.indexOf(64) != -1) {
            if (str2.substring(str2.indexOf(64) + 1).equalsIgnoreCase(str)) {
                set.add(str2);
            }
        } else if (str2.startsWith(".")) {
            if (b(str, str2)) {
                set.add(str);
            }
        } else if (str.equalsIgnoreCase(str2)) {
            set.add(str);
        }
    }

    private void i(Set set, String str) {
        if (set != null) {
            for (String c : set) {
                if (c(str, c)) {
                    return;
                }
            }
            if (str.length() != 0 || set.size() != 0) {
                throw new PKIXNameConstraintValidatorException("URI is not from a permitted subtree.");
            }
        }
    }

    private boolean c(String str, String str2) {
        String a = a(str);
        if (str2.startsWith(".")) {
            if (b(a, str2)) {
                return true;
            }
        } else if (a.equalsIgnoreCase(str2)) {
            return true;
        }
        return false;
    }

    private static String a(String str) {
        String substring = str.substring(str.indexOf(58) + 1);
        if (substring.indexOf("//") != -1) {
            substring = substring.substring(substring.indexOf("//") + 2);
        }
        if (substring.lastIndexOf(58) != -1) {
            substring = substring.substring(0, substring.lastIndexOf(58));
        }
        substring = substring.substring(substring.indexOf(58) + 1);
        substring = substring.substring(substring.indexOf(64) + 1);
        if (substring.indexOf(47) != -1) {
            return substring.substring(0, substring.indexOf(47));
        }
        return substring;
    }

    public void a(GeneralName generalName) {
        switch (generalName.d()) {
            case 1:
                c(this.h, c(generalName));
                return;
            case 2:
                e(this.g, DERIA5String.a(generalName.e()).a_());
                return;
            case 4:
                a(ASN1Sequence.a(generalName.e().a()));
                return;
            case 6:
                i(this.i, DERIA5String.a(generalName.e()).a_());
                return;
            case 7:
                b(this.j, ASN1OctetString.a(generalName.e()).e());
                return;
            default:
                return;
        }
    }

    public void b(GeneralName generalName) {
        switch (generalName.d()) {
            case 1:
                d(this.c, c(generalName));
                return;
            case 2:
                f(this.b, DERIA5String.a(generalName.e()).a_());
                return;
            case 4:
                b(ASN1Sequence.a(generalName.e().a()));
                return;
            case 6:
                g(this.d, DERIA5String.a(generalName.e()).a_());
                return;
            case 7:
                c(this.e, ASN1OctetString.a(generalName.e()).e());
                return;
            default:
                return;
        }
    }

    public void a(GeneralSubtree[] generalSubtreeArr) {
        Map hashMap = new HashMap();
        for (int i = 0; i != generalSubtreeArr.length; i++) {
            GeneralSubtree generalSubtree = generalSubtreeArr[i];
            Integer a = Integers.a(generalSubtree.d().d());
            if (hashMap.get(a) == null) {
                hashMap.put(a, new HashSet());
            }
            ((Set) hashMap.get(a)).add(generalSubtree);
        }
        for (Entry entry : hashMap.entrySet()) {
            switch (((Integer) entry.getKey()).intValue()) {
                case 1:
                    this.h = b(this.h, (Set) entry.getValue());
                    break;
                case 2:
                    this.g = d(this.g, (Set) entry.getValue());
                    break;
                case 4:
                    this.f = a(this.f, (Set) entry.getValue());
                    break;
                case 6:
                    this.i = e(this.i, (Set) entry.getValue());
                    break;
                case 7:
                    this.j = c(this.j, (Set) entry.getValue());
                    break;
                default:
                    break;
            }
        }
    }

    private String c(GeneralName generalName) {
        return DERIA5String.a(generalName.e()).a_();
    }

    public void a(GeneralSubtree generalSubtree) {
        GeneralName d = generalSubtree.d();
        switch (d.d()) {
            case 1:
                this.c = b(this.c, c(d));
                return;
            case 2:
                this.b = a(this.b, c(d));
                return;
            case 4:
                this.a = c(this.a, (ASN1Sequence) d.e().a());
                return;
            case 6:
                this.d = h(this.d, c(d));
                return;
            case 7:
                this.e = a(this.e, ASN1OctetString.a(d.e()).e());
                return;
            default:
                return;
        }
    }

    private static byte[] f(byte[] bArr, byte[] bArr2) {
        for (int i = 0; i < bArr.length; i++) {
            if ((bArr[i] & 65535) > (bArr2[i] & 65535)) {
                return bArr;
            }
        }
        return bArr2;
    }

    private static byte[] g(byte[] bArr, byte[] bArr2) {
        for (int i = 0; i < bArr.length; i++) {
            if ((bArr[i] & 65535) < (bArr2[i] & 65535)) {
                return bArr;
            }
        }
        return bArr2;
    }

    private static int h(byte[] bArr, byte[] bArr2) {
        if (Arrays.a(bArr, bArr2)) {
            return 0;
        }
        if (Arrays.a(f(bArr, bArr2), bArr)) {
            return 1;
        }
        return -1;
    }

    private static byte[] i(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[bArr.length];
        for (int i = 0; i < bArr.length; i++) {
            bArr3[i] = (byte) (bArr[i] | bArr2[i]);
        }
        return bArr3;
    }

    public int hashCode() {
        return ((((((((a(this.a) + a(this.b)) + a(this.c)) + a(this.e)) + a(this.d)) + a(this.f)) + a(this.g)) + a(this.h)) + a(this.j)) + a(this.i);
    }

    private int a(Collection collection) {
        if (collection == null) {
            return 0;
        }
        int i = 0;
        for (Object next : collection) {
            int a;
            if (next instanceof byte[]) {
                a = Arrays.a((byte[]) next) + i;
            } else {
                a = next.hashCode() + i;
            }
            i = a;
        }
        return i;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof PKIXNameConstraintValidator)) {
            return false;
        }
        PKIXNameConstraintValidator pKIXNameConstraintValidator = (PKIXNameConstraintValidator) obj;
        if (a(pKIXNameConstraintValidator.a, this.a) && a(pKIXNameConstraintValidator.b, this.b) && a(pKIXNameConstraintValidator.c, this.c) && a(pKIXNameConstraintValidator.e, this.e) && a(pKIXNameConstraintValidator.d, this.d) && a(pKIXNameConstraintValidator.f, this.f) && a(pKIXNameConstraintValidator.g, this.g) && a(pKIXNameConstraintValidator.h, this.h) && a(pKIXNameConstraintValidator.j, this.j) && a(pKIXNameConstraintValidator.i, this.i)) {
            return true;
        }
        return false;
    }

    private boolean a(Collection collection, Collection collection2) {
        if (collection == collection2) {
            return true;
        }
        if (collection == null || collection2 == null) {
            return false;
        }
        if (collection.size() != collection2.size()) {
            return false;
        }
        for (Object next : collection) {
            boolean z;
            for (Object a : collection2) {
                if (a(next, a)) {
                    z = true;
                    continue;
                    break;
                }
            }
            z = false;
            continue;
            if (!z) {
                return false;
            }
        }
        return true;
    }

    private boolean a(Object obj, Object obj2) {
        if (obj == obj2) {
            return true;
        }
        if (obj == null || obj2 == null) {
            return false;
        }
        if ((obj instanceof byte[]) && (obj2 instanceof byte[])) {
            return Arrays.a((byte[]) obj, (byte[]) obj2);
        }
        return obj.equals(obj2);
    }

    private String a(byte[] bArr) {
        int i;
        String str = "";
        for (i = 0; i < bArr.length / 2; i++) {
            str = str + Integer.toString(bArr[i] & 255) + ".";
        }
        str = str.substring(0, str.length() - 1) + "/";
        for (i = bArr.length / 2; i < bArr.length; i++) {
            str = str + Integer.toString(bArr[i] & 255) + ".";
        }
        return str.substring(0, str.length() - 1);
    }

    private String a(Set set) {
        String str = "" + "[";
        for (byte[] a : set) {
            str = str + a(a) + ",";
        }
        if (str.length() > 1) {
            str = str.substring(0, str.length() - 1);
        }
        return str + "]";
    }

    public String toString() {
        String str = "" + "permitted:\n";
        if (this.f != null) {
            str = (str + "DN:\n") + this.f.toString() + "\n";
        }
        if (this.g != null) {
            str = (str + "DNS:\n") + this.g.toString() + "\n";
        }
        if (this.h != null) {
            str = (str + "Email:\n") + this.h.toString() + "\n";
        }
        if (this.i != null) {
            str = (str + "URI:\n") + this.i.toString() + "\n";
        }
        if (this.j != null) {
            str = (str + "IP:\n") + a(this.j) + "\n";
        }
        str = str + "excluded:\n";
        if (!this.a.isEmpty()) {
            str = (str + "DN:\n") + this.a.toString() + "\n";
        }
        if (!this.b.isEmpty()) {
            str = (str + "DNS:\n") + this.b.toString() + "\n";
        }
        if (!this.c.isEmpty()) {
            str = (str + "Email:\n") + this.c.toString() + "\n";
        }
        if (!this.d.isEmpty()) {
            str = (str + "URI:\n") + this.d.toString() + "\n";
        }
        if (this.e.isEmpty()) {
            return str;
        }
        return (str + "IP:\n") + a(this.e) + "\n";
    }
}
