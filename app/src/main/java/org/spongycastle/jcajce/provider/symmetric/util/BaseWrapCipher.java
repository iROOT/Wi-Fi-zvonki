package org.spongycastle.jcajce.provider.symmetric.util;

import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
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
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.InvalidCipherTextException;
import org.spongycastle.crypto.Wrapper;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.params.ParametersWithIV;
import org.spongycastle.jcajce.provider.symmetric.util.PBE.Util;
import org.spongycastle.jce.provider.BouncyCastleProvider;

public abstract class BaseWrapCipher extends CipherSpi implements PBE {
    protected int a;
    protected int b;
    protected AlgorithmParameters c;
    protected Wrapper d;
    private Class[] e;
    private int f;
    private byte[] g;

    protected BaseWrapCipher() {
        this.e = new Class[]{IvParameterSpec.class, PBEParameterSpec.class, RC2ParameterSpec.class, RC5ParameterSpec.class};
        this.a = 2;
        this.b = 1;
        this.c = null;
        this.d = null;
    }

    protected BaseWrapCipher(Wrapper wrapper) {
        this(wrapper, 0);
    }

    protected BaseWrapCipher(Wrapper wrapper, int i) {
        this.e = new Class[]{IvParameterSpec.class, PBEParameterSpec.class, RC2ParameterSpec.class, RC5ParameterSpec.class};
        this.a = 2;
        this.b = 1;
        this.c = null;
        this.d = null;
        this.d = wrapper;
        this.f = i;
    }

    protected int engineGetBlockSize() {
        return 0;
    }

    protected byte[] engineGetIV() {
        return (byte[]) this.g.clone();
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

    protected void engineInit(int i, Key key, AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) {
        CipherParameters a;
        if (key instanceof BCPBEKey) {
            BCPBEKey bCPBEKey = (BCPBEKey) key;
            if (algorithmParameterSpec instanceof PBEParameterSpec) {
                a = Util.a(bCPBEKey, algorithmParameterSpec, this.d.a());
            } else if (bCPBEKey.e() != null) {
                a = bCPBEKey.e();
            } else {
                throw new InvalidAlgorithmParameterException("PBE requires PBE parameters to be set.");
            }
        }
        a = new KeyParameter(key.getEncoded());
        if (algorithmParameterSpec instanceof IvParameterSpec) {
            a = new ParametersWithIV(a, ((IvParameterSpec) algorithmParameterSpec).getIV());
        }
        if ((a instanceof KeyParameter) && this.f != 0) {
            this.g = new byte[this.f];
            secureRandom.nextBytes(this.g);
            a = new ParametersWithIV(a, this.g);
        }
        switch (i) {
            case 1:
            case 2:
                throw new IllegalArgumentException("engine only valid for wrapping");
            case 3:
                this.d.a(true, a);
                return;
            case 4:
                this.d.a(false, a);
                return;
            default:
                System.out.println("eeek!");
                return;
        }
    }

    protected void engineInit(int i, Key key, AlgorithmParameters algorithmParameters, SecureRandom secureRandom) {
        AlgorithmParameterSpec parameterSpec;
        if (algorithmParameters != null) {
            int i2 = 0;
            while (i2 != this.e.length) {
                try {
                    parameterSpec = algorithmParameters.getParameterSpec(this.e[i2]);
                    break;
                } catch (Exception e) {
                    i2++;
                }
            }
            parameterSpec = null;
            if (parameterSpec == null) {
                throw new InvalidAlgorithmParameterException("can't handle parameter " + algorithmParameters.toString());
            }
        }
        parameterSpec = null;
        this.c = algorithmParameters;
        engineInit(i, key, parameterSpec, secureRandom);
    }

    protected void engineInit(int i, Key key, SecureRandom secureRandom) {
        try {
            engineInit(i, key, (AlgorithmParameterSpec) null, secureRandom);
        } catch (InvalidAlgorithmParameterException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    protected byte[] engineUpdate(byte[] bArr, int i, int i2) {
        throw new RuntimeException("not supported for wrapping");
    }

    protected int engineUpdate(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
        throw new RuntimeException("not supported for wrapping");
    }

    protected byte[] engineDoFinal(byte[] bArr, int i, int i2) {
        return null;
    }

    protected int engineDoFinal(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
        return 0;
    }

    protected byte[] engineWrap(Key key) {
        byte[] encoded = key.getEncoded();
        if (encoded == null) {
            throw new InvalidKeyException("Cannot wrap key, null encoding.");
        }
        try {
            if (this.d == null) {
                return engineDoFinal(encoded, 0, encoded.length);
            }
            return this.d.a(encoded, 0, encoded.length);
        } catch (BadPaddingException e) {
            throw new IllegalBlockSizeException(e.getMessage());
        }
    }

    protected Key engineUnwrap(byte[] bArr, String str, int i) {
        try {
            Object engineDoFinal;
            if (this.d == null) {
                engineDoFinal = engineDoFinal(bArr, 0, bArr.length);
            } else {
                engineDoFinal = this.d.b(bArr, 0, bArr.length);
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
            } catch (InvalidKeySpecException e3) {
                throw new InvalidKeyException("Unknown key type " + e3.getMessage());
            }
        } catch (InvalidCipherTextException e4) {
            throw new InvalidKeyException(e4.getMessage());
        } catch (BadPaddingException e5) {
            throw new InvalidKeyException(e5.getMessage());
        } catch (IllegalBlockSizeException e6) {
            throw new InvalidKeyException(e6.getMessage());
        }
    }
}
