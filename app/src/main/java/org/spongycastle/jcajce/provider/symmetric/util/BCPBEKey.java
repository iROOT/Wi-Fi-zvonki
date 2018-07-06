package org.spongycastle.jcajce.provider.symmetric.util;

import javax.crypto.interfaces.PBEKey;
import javax.crypto.spec.PBEKeySpec;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.PBEParametersGenerator;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.params.ParametersWithIV;

public class BCPBEKey implements PBEKey {
    String a;
    ASN1ObjectIdentifier b;
    int c;
    int d;
    int e;
    int f;
    CipherParameters g;
    PBEKeySpec h;
    boolean i = false;

    public BCPBEKey(String str, ASN1ObjectIdentifier aSN1ObjectIdentifier, int i, int i2, int i3, int i4, PBEKeySpec pBEKeySpec, CipherParameters cipherParameters) {
        this.a = str;
        this.b = aSN1ObjectIdentifier;
        this.c = i;
        this.d = i2;
        this.e = i3;
        this.f = i4;
        this.h = pBEKeySpec;
        this.g = cipherParameters;
    }

    public String getAlgorithm() {
        return this.a;
    }

    public String getFormat() {
        return "RAW";
    }

    public byte[] getEncoded() {
        if (this.g != null) {
            KeyParameter keyParameter;
            if (this.g instanceof ParametersWithIV) {
                keyParameter = (KeyParameter) ((ParametersWithIV) this.g).b();
            } else {
                keyParameter = (KeyParameter) this.g;
            }
            return keyParameter.a();
        } else if (this.c == 2) {
            return PBEParametersGenerator.c(this.h.getPassword());
        } else {
            if (this.c == 5) {
                return PBEParametersGenerator.b(this.h.getPassword());
            }
            return PBEParametersGenerator.a(this.h.getPassword());
        }
    }

    int a() {
        return this.c;
    }

    int b() {
        return this.d;
    }

    int c() {
        return this.e;
    }

    public int d() {
        return this.f;
    }

    public CipherParameters e() {
        return this.g;
    }

    public char[] getPassword() {
        return this.h.getPassword();
    }

    public byte[] getSalt() {
        return this.h.getSalt();
    }

    public int getIterationCount() {
        return this.h.getIterationCount();
    }

    public ASN1ObjectIdentifier f() {
        return this.b;
    }

    public void a(boolean z) {
        this.i = z;
    }

    boolean g() {
        return this.i;
    }
}
