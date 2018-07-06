package org.spongycastle.jcajce.spec;

import java.security.spec.AlgorithmParameterSpec;
import java.util.HashMap;
import java.util.Map;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.cryptopro.CryptoProObjectIdentifiers;
import org.spongycastle.crypto.engines.GOST28147Engine;
import org.spongycastle.util.Arrays;

public class GOST28147ParameterSpec implements AlgorithmParameterSpec {
    private static Map c = new HashMap();
    private byte[] a;
    private byte[] b;

    public GOST28147ParameterSpec(String str) {
        this.a = null;
        this.b = null;
        this.b = GOST28147Engine.a(str);
    }

    public GOST28147ParameterSpec(ASN1ObjectIdentifier aSN1ObjectIdentifier, byte[] bArr) {
        this(a(aSN1ObjectIdentifier));
        this.a = Arrays.b(bArr);
    }

    public byte[] a() {
        return this.b;
    }

    public byte[] b() {
        if (this.a == null) {
            return null;
        }
        byte[] bArr = new byte[this.a.length];
        System.arraycopy(this.a, 0, bArr, 0, bArr.length);
        return bArr;
    }

    static {
        c.put(CryptoProObjectIdentifiers.e, "E-A");
        c.put(CryptoProObjectIdentifiers.f, "E-B");
        c.put(CryptoProObjectIdentifiers.g, "E-C");
        c.put(CryptoProObjectIdentifiers.h, "E-D");
    }

    private static String a(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        String str = (String) c.get(aSN1ObjectIdentifier);
        if (str != null) {
            return str;
        }
        throw new IllegalArgumentException("unknown OID: " + aSN1ObjectIdentifier);
    }
}
