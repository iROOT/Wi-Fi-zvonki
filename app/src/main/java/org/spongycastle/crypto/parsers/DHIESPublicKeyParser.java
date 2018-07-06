package org.spongycastle.crypto.parsers;

import java.io.InputStream;
import java.math.BigInteger;
import org.spongycastle.crypto.KeyParser;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.crypto.params.DHParameters;
import org.spongycastle.crypto.params.DHPublicKeyParameters;

public class DHIESPublicKeyParser implements KeyParser {
    private DHParameters a;

    public DHIESPublicKeyParser(DHParameters dHParameters) {
        this.a = dHParameters;
    }

    public AsymmetricKeyParameter a(InputStream inputStream) {
        byte[] bArr = new byte[((this.a.a().bitLength() + 7) / 8)];
        inputStream.read(bArr, 0, bArr.length);
        return new DHPublicKeyParameters(new BigInteger(1, bArr), this.a);
    }
}
