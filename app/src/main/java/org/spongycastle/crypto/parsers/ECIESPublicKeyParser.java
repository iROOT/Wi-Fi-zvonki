package org.spongycastle.crypto.parsers;

import java.io.IOException;
import java.io.InputStream;
import org.spongycastle.crypto.KeyParser;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.crypto.params.ECDomainParameters;
import org.spongycastle.crypto.params.ECPublicKeyParameters;

public class ECIESPublicKeyParser implements KeyParser {
    private ECDomainParameters a;

    public ECIESPublicKeyParser(ECDomainParameters eCDomainParameters) {
        this.a = eCDomainParameters;
    }

    public AsymmetricKeyParameter a(InputStream inputStream) {
        byte[] bArr;
        int read = inputStream.read();
        switch (read) {
            case 0:
                throw new IOException("Sender's public key invalid.");
            case 2:
            case 3:
                bArr = new byte[(((this.a.a().a() + 7) / 8) + 1)];
                break;
            case 4:
            case 6:
            case 7:
                bArr = new byte[((((this.a.a().a() + 7) / 8) * 2) + 1)];
                break;
            default:
                throw new IOException("Sender's public key has invalid point encoding 0x" + Integer.toString(read, 16));
        }
        bArr[0] = (byte) read;
        inputStream.read(bArr, 1, bArr.length - 1);
        return new ECPublicKeyParameters(this.a.a().a(bArr), this.a);
    }
}
