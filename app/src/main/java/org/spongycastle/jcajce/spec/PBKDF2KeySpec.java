package org.spongycastle.jcajce.spec;

import javax.crypto.spec.PBEKeySpec;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;

public class PBKDF2KeySpec extends PBEKeySpec {
    private AlgorithmIdentifier a;

    public PBKDF2KeySpec(char[] cArr, byte[] bArr, int i, int i2, AlgorithmIdentifier algorithmIdentifier) {
        super(cArr, bArr, i, i2);
        this.a = algorithmIdentifier;
    }

    public AlgorithmIdentifier a() {
        return this.a;
    }
}
