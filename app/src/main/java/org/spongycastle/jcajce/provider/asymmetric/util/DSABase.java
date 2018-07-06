package org.spongycastle.jcajce.provider.asymmetric.util;

import java.math.BigInteger;
import java.security.SignatureException;
import java.security.SignatureSpi;
import java.security.spec.AlgorithmParameterSpec;
import org.spongycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.spongycastle.asn1.x509.X509ObjectIdentifiers;
import org.spongycastle.crypto.DSA;
import org.spongycastle.crypto.Digest;

public abstract class DSABase extends SignatureSpi implements PKCSObjectIdentifiers, X509ObjectIdentifiers {
    protected Digest bD;
    protected DSA bE;
    protected DSAEncoder bF;

    protected DSABase(Digest digest, DSA dsa, DSAEncoder dSAEncoder) {
        this.bD = digest;
        this.bE = dsa;
        this.bF = dSAEncoder;
    }

    protected void engineUpdate(byte b) {
        this.bD.a(b);
    }

    protected void engineUpdate(byte[] bArr, int i, int i2) {
        this.bD.a(bArr, i, i2);
    }

    protected byte[] engineSign() {
        byte[] bArr = new byte[this.bD.b()];
        this.bD.a(bArr, 0);
        try {
            BigInteger[] a = this.bE.a(bArr);
            return this.bF.a(a[0], a[1]);
        } catch (Exception e) {
            throw new SignatureException(e.toString());
        }
    }

    protected boolean engineVerify(byte[] bArr) {
        byte[] bArr2 = new byte[this.bD.b()];
        this.bD.a(bArr2, 0);
        try {
            BigInteger[] a = this.bF.a(bArr);
            return this.bE.a(bArr2, a[0], a[1]);
        } catch (Exception e) {
            throw new SignatureException("error decoding signature bytes.");
        }
    }

    protected void engineSetParameter(AlgorithmParameterSpec algorithmParameterSpec) {
        throw new UnsupportedOperationException("engineSetParameter unsupported");
    }

    protected void engineSetParameter(String str, Object obj) {
        throw new UnsupportedOperationException("engineSetParameter unsupported");
    }

    protected Object engineGetParameter(String str) {
        throw new UnsupportedOperationException("engineSetParameter unsupported");
    }
}
