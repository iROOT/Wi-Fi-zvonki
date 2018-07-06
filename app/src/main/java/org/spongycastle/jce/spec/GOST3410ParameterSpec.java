package org.spongycastle.jce.spec;

import java.security.spec.AlgorithmParameterSpec;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.cryptopro.CryptoProObjectIdentifiers;
import org.spongycastle.asn1.cryptopro.GOST3410NamedParameters;
import org.spongycastle.asn1.cryptopro.GOST3410ParamSetParameters;
import org.spongycastle.asn1.cryptopro.GOST3410PublicKeyAlgParameters;
import org.spongycastle.jce.interfaces.GOST3410Params;

public class GOST3410ParameterSpec implements AlgorithmParameterSpec, GOST3410Params {
    private GOST3410PublicKeyParameterSetSpec a;
    private String b;
    private String c;
    private String d;

    public GOST3410ParameterSpec(String str, String str2, String str3) {
        GOST3410ParamSetParameters gOST3410ParamSetParameters = null;
        try {
            gOST3410ParamSetParameters = GOST3410NamedParameters.a(new ASN1ObjectIdentifier(str));
        } catch (IllegalArgumentException e) {
            ASN1ObjectIdentifier a = GOST3410NamedParameters.a(str);
            if (a != null) {
                str = a.d();
                gOST3410ParamSetParameters = GOST3410NamedParameters.a(a);
            }
        }
        if (gOST3410ParamSetParameters == null) {
            throw new IllegalArgumentException("no key parameter set for passed in name/OID.");
        }
        this.a = new GOST3410PublicKeyParameterSetSpec(gOST3410ParamSetParameters.d(), gOST3410ParamSetParameters.e(), gOST3410ParamSetParameters.f());
        this.b = str;
        this.c = str2;
        this.d = str3;
    }

    public GOST3410ParameterSpec(String str, String str2) {
        this(str, str2, null);
    }

    public GOST3410ParameterSpec(String str) {
        this(str, CryptoProObjectIdentifiers.m.d(), null);
    }

    public GOST3410ParameterSpec(GOST3410PublicKeyParameterSetSpec gOST3410PublicKeyParameterSetSpec) {
        this.a = gOST3410PublicKeyParameterSetSpec;
        this.c = CryptoProObjectIdentifiers.m.d();
        this.d = null;
    }

    public String a() {
        return this.b;
    }

    public GOST3410PublicKeyParameterSetSpec d() {
        return this.a;
    }

    public String b() {
        return this.c;
    }

    public String c() {
        return this.d;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof GOST3410ParameterSpec)) {
            return false;
        }
        GOST3410ParameterSpec gOST3410ParameterSpec = (GOST3410ParameterSpec) obj;
        if (!this.a.equals(gOST3410ParameterSpec.a) || !this.c.equals(gOST3410ParameterSpec.c)) {
            return false;
        }
        if (this.d == gOST3410ParameterSpec.d || (this.d != null && this.d.equals(gOST3410ParameterSpec.d))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (this.d != null ? this.d.hashCode() : 0) ^ (this.c.hashCode() ^ this.a.hashCode());
    }

    public static GOST3410ParameterSpec a(GOST3410PublicKeyAlgParameters gOST3410PublicKeyAlgParameters) {
        if (gOST3410PublicKeyAlgParameters.f() != null) {
            return new GOST3410ParameterSpec(gOST3410PublicKeyAlgParameters.d().d(), gOST3410PublicKeyAlgParameters.e().d(), gOST3410PublicKeyAlgParameters.f().d());
        }
        return new GOST3410ParameterSpec(gOST3410PublicKeyAlgParameters.d().d(), gOST3410PublicKeyAlgParameters.e().d());
    }
}
