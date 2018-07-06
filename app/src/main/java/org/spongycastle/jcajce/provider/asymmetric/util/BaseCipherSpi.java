package org.spongycastle.jcajce.provider.asymmetric.util;

import java.security.AlgorithmParameters;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.BadPaddingException;
import javax.crypto.CipherSpi;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEParameterSpec;
import javax.crypto.spec.RC2ParameterSpec;
import javax.crypto.spec.RC5ParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.spongycastle.asn1.pkcs.PrivateKeyInfo;
import org.spongycastle.crypto.InvalidCipherTextException;
import org.spongycastle.crypto.Wrapper;
import org.spongycastle.jce.provider.BouncyCastleProvider;

public abstract class BaseCipherSpi extends CipherSpi {
    protected AlgorithmParameters a = null;
    protected Wrapper b = null;
    private Class[] c = new Class[]{IvParameterSpec.class, PBEParameterSpec.class, RC2ParameterSpec.class, RC5ParameterSpec.class};

    protected BaseCipherSpi() {
    }

    protected int engineGetBlockSize() {
        return 0;
    }

    protected byte[] engineGetIV() {
        return null;
    }

    protected int engineGetKeySize(Key key) {
        return key.getEncoded().length;
    }

    protected int engineGetOutputSize(int i) {
        return -1;
    }

    protected AlgorithmParameters engineGetParameters() {
        return null;
    }

    protected void engineSetMode(String str) {
        throw new NoSuchAlgorithmException("can't support mode " + str);
    }

    protected void engineSetPadding(String str) {
        throw new NoSuchPaddingException("Padding " + str + " unknown.");
    }

    protected byte[] engineWrap(Key key) {
        byte[] encoded = key.getEncoded();
        if (encoded == null) {
            throw new InvalidKeyException("Cannot wrap key, null encoding.");
        }
        try {
            if (this.b == null) {
                return engineDoFinal(encoded, 0, encoded.length);
            }
            return this.b.a(encoded, 0, encoded.length);
        } catch (BadPaddingException e) {
            throw new IllegalBlockSizeException(e.getMessage());
        }
    }

    protected Key engineUnwrap(byte[] bArr, String str, int i) {
        try {
            Object engineDoFinal;
            if (this.b == null) {
                engineDoFinal = engineDoFinal(bArr, 0, bArr.length);
            } else {
                engineDoFinal = this.b.b(bArr, 0, bArr.length);
            }
            if (i == 3) {
                return new SecretKeySpec(engineDoFinal, str);
            }
            if (str.equals("") && i == 2) {
                try {
                    PrivateKeyInfo a = PrivateKeyInfo.a(engineDoFinal);
                    Key a2 = BouncyCastleProvider.a(a);
                    if (a2 != null) {
                        return a2;
                    }
                    throw new InvalidKeyException("algorithm " + a.d().e() + " not supported");
                } catch (Exception e) {
                    throw new InvalidKeyException("Invalid key encoding.");
                }
            }
            try {
                KeyFactory instance = KeyFactory.getInstance(str, "SC");
                if (i == 1) {
                    return instance.generatePublic(new X509EncodedKeySpec(engineDoFinal));
                }
                if (i == 2) {
                    return instance.generatePrivate(new PKCS8EncodedKeySpec(engineDoFinal));
                }
                throw new InvalidKeyException("Unknown key type " + i);
            } catch (NoSuchProviderException e2) {
                throw new InvalidKeyException("Unknown key type " + e2.getMessage());
            } catch (NoSuchAlgorithmException e3) {
                throw new InvalidKeyException("Unknown key type " + e3.getMessage());
            } catch (InvalidKeySpecException e4) {
                throw new InvalidKeyException("Unknown key type " + e4.getMessage());
            }
        } catch (InvalidCipherTextException e5) {
            throw new InvalidKeyException(e5.getMessage());
        } catch (BadPaddingException e6) {
            throw new InvalidKeyException(e6.getMessage());
        } catch (IllegalBlockSizeException e7) {
            throw new InvalidKeyException(e7.getMessage());
        }
    }
}
