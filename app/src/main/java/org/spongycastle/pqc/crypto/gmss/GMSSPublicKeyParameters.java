package org.spongycastle.pqc.crypto.gmss;

public class GMSSPublicKeyParameters extends GMSSKeyParameters {
    private byte[] b;

    public GMSSPublicKeyParameters(byte[] bArr, GMSSParameters gMSSParameters) {
        super(false, gMSSParameters);
        this.b = bArr;
    }

    public byte[] c() {
        return this.b;
    }
}
