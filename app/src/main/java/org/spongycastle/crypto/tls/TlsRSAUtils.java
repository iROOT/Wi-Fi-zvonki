package org.spongycastle.crypto.tls;

import java.io.OutputStream;
import org.spongycastle.crypto.InvalidCipherTextException;
import org.spongycastle.crypto.encodings.PKCS1Encoding;
import org.spongycastle.crypto.engines.RSABlindedEngine;
import org.spongycastle.crypto.params.ParametersWithRandom;
import org.spongycastle.crypto.params.RSAKeyParameters;

public class TlsRSAUtils {
    public static byte[] a(TlsContext tlsContext, RSAKeyParameters rSAKeyParameters, OutputStream outputStream) {
        byte[] bArr = new byte[48];
        tlsContext.a().nextBytes(bArr);
        TlsUtils.a(tlsContext.c(), bArr, 0);
        PKCS1Encoding pKCS1Encoding = new PKCS1Encoding(new RSABlindedEngine());
        pKCS1Encoding.a(true, new ParametersWithRandom(rSAKeyParameters, tlsContext.a()));
        try {
            byte[] a = pKCS1Encoding.a(bArr, 0, bArr.length);
            if (TlsUtils.a(tlsContext)) {
                outputStream.write(a);
            } else {
                TlsUtils.b(a, outputStream);
            }
            return bArr;
        } catch (InvalidCipherTextException e) {
            throw new TlsFatalAlert((short) 80);
        }
    }

    public static byte[] a(TlsContext tlsContext, TlsEncryptionCredentials tlsEncryptionCredentials, byte[] bArr) {
        ProtocolVersion c = tlsContext.c();
        byte[] bArr2 = new byte[48];
        tlsContext.a().nextBytes(bArr2);
        byte[] bArr3 = TlsUtils.a;
        try {
            bArr3 = tlsEncryptionCredentials.a(bArr);
        } catch (Exception e) {
        }
        if (bArr3.length != 48) {
            TlsUtils.a(c, bArr2, 0);
            return bArr2;
        }
        TlsUtils.a(c, bArr3, 0);
        return bArr3;
    }
}
