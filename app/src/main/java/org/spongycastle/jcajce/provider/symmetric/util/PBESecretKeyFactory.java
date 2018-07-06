package org.spongycastle.jcajce.provider.symmetric.util;

import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import javax.crypto.SecretKey;
import javax.crypto.spec.PBEKeySpec;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.jcajce.provider.symmetric.util.PBE.Util;

public class PBESecretKeyFactory extends BaseSecretKeyFactory implements PBE {
    private boolean c;
    private int d;
    private int e;
    private int f;
    private int g;

    public PBESecretKeyFactory(String str, ASN1ObjectIdentifier aSN1ObjectIdentifier, boolean z, int i, int i2, int i3, int i4) {
        super(str, aSN1ObjectIdentifier);
        this.c = z;
        this.d = i;
        this.e = i2;
        this.f = i3;
        this.g = i4;
    }

    protected SecretKey engineGenerateSecret(KeySpec keySpec) {
        if (keySpec instanceof PBEKeySpec) {
            PBEKeySpec pBEKeySpec = (PBEKeySpec) keySpec;
            if (pBEKeySpec.getSalt() == null) {
                return new BCPBEKey(this.a, this.b, this.d, this.e, this.f, this.g, pBEKeySpec, null);
            }
            CipherParameters a;
            if (this.c) {
                a = Util.a(pBEKeySpec, this.d, this.e, this.f, this.g);
            } else {
                a = Util.a(pBEKeySpec, this.d, this.e, this.f);
            }
            return new BCPBEKey(this.a, this.b, this.d, this.e, this.f, this.g, pBEKeySpec, a);
        }
        throw new InvalidKeySpecException("Invalid KeySpec");
    }
}
