package org.spongycastle.jce.provider;

import android.support.v4.app.NotificationCompat;
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
import javax.crypto.SecretKey;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEParameterSpec;
import javax.crypto.spec.RC2ParameterSpec;
import javax.crypto.spec.RC5ParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.spongycastle.asn1.pkcs.PrivateKeyInfo;
import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.StreamBlockCipher;
import org.spongycastle.crypto.StreamCipher;
import org.spongycastle.crypto.engines.BlowfishEngine;
import org.spongycastle.crypto.engines.DESEngine;
import org.spongycastle.crypto.engines.DESedeEngine;
import org.spongycastle.crypto.engines.SkipjackEngine;
import org.spongycastle.crypto.engines.TwofishEngine;
import org.spongycastle.crypto.modes.CFBBlockCipher;
import org.spongycastle.crypto.modes.OFBBlockCipher;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.params.ParametersWithIV;
import org.spongycastle.jcajce.provider.symmetric.util.BCPBEKey;
import org.spongycastle.jcajce.provider.symmetric.util.PBE;
import org.spongycastle.jcajce.provider.symmetric.util.PBE.Util;

public class JCEStreamCipher extends CipherSpi implements PBE {
    private Class[] a = new Class[]{RC2ParameterSpec.class, RC5ParameterSpec.class, IvParameterSpec.class, PBEParameterSpec.class};
    private StreamCipher b;
    private ParametersWithIV c;
    private int d = 0;
    private PBEParameterSpec e = null;
    private String f = null;
    private AlgorithmParameters g;

    public static class Blowfish_CFB8 extends JCEStreamCipher {
        public Blowfish_CFB8() {
            super(new CFBBlockCipher(new BlowfishEngine(), 8), 64);
        }
    }

    public static class Blowfish_OFB8 extends JCEStreamCipher {
        public Blowfish_OFB8() {
            super(new OFBBlockCipher(new BlowfishEngine(), 8), 64);
        }
    }

    public static class DES_CFB8 extends JCEStreamCipher {
        public DES_CFB8() {
            super(new CFBBlockCipher(new DESEngine(), 8), 64);
        }
    }

    public static class DES_OFB8 extends JCEStreamCipher {
        public DES_OFB8() {
            super(new OFBBlockCipher(new DESEngine(), 8), 64);
        }
    }

    public static class DESede_CFB8 extends JCEStreamCipher {
        public DESede_CFB8() {
            super(new CFBBlockCipher(new DESedeEngine(), 8), 64);
        }
    }

    public static class DESede_OFB8 extends JCEStreamCipher {
        public DESede_OFB8() {
            super(new OFBBlockCipher(new DESedeEngine(), 8), 64);
        }
    }

    public static class Skipjack_CFB8 extends JCEStreamCipher {
        public Skipjack_CFB8() {
            super(new CFBBlockCipher(new SkipjackEngine(), 8), 64);
        }
    }

    public static class Skipjack_OFB8 extends JCEStreamCipher {
        public Skipjack_OFB8() {
            super(new OFBBlockCipher(new SkipjackEngine(), 8), 64);
        }
    }

    public static class Twofish_CFB8 extends JCEStreamCipher {
        public Twofish_CFB8() {
            super(new CFBBlockCipher(new TwofishEngine(), 8), NotificationCompat.FLAG_HIGH_PRIORITY);
        }
    }

    public static class Twofish_OFB8 extends JCEStreamCipher {
        public Twofish_OFB8() {
            super(new OFBBlockCipher(new TwofishEngine(), 8), NotificationCompat.FLAG_HIGH_PRIORITY);
        }
    }

    protected JCEStreamCipher(BlockCipher blockCipher, int i) {
        this.d = i;
        this.b = new StreamBlockCipher(blockCipher);
    }

    protected int engineGetBlockSize() {
        return 0;
    }

    protected byte[] engineGetIV() {
        return this.c != null ? this.c.a() : null;
    }

    protected int engineGetKeySize(Key key) {
        return key.getEncoded().length * 8;
    }

    protected int engineGetOutputSize(int i) {
        return i;
    }

    protected AlgorithmParameters engineGetParameters() {
        if (this.g != null || this.e == null) {
            return this.g;
        }
        try {
            AlgorithmParameters instance = AlgorithmParameters.getInstance(this.f, "SC");
            instance.init(this.e);
            return instance;
        } catch (Exception e) {
            return null;
        }
    }

    protected void engineSetMode(String str) {
        if (!str.equalsIgnoreCase("ECB")) {
            throw new IllegalArgumentException("can't support mode " + str);
        }
    }

    protected void engineSetPadding(String str) {
        if (!str.equalsIgnoreCase("NoPadding")) {
            throw new NoSuchPaddingException("Padding " + str + " unknown.");
        }
    }

    protected void engineInit(int i, Key key, AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) {
        this.e = null;
        this.f = null;
        this.g = null;
        if (key instanceof SecretKey) {
            CipherParameters cipherParameters;
            ParametersWithIV parametersWithIV;
            if (key instanceof BCPBEKey) {
                CipherParameters e;
                BCPBEKey bCPBEKey = (BCPBEKey) key;
                if (bCPBEKey.f() != null) {
                    this.f = bCPBEKey.f().d();
                } else {
                    this.f = bCPBEKey.getAlgorithm();
                }
                if (bCPBEKey.e() != null) {
                    e = bCPBEKey.e();
                    this.e = new PBEParameterSpec(bCPBEKey.getSalt(), bCPBEKey.getIterationCount());
                } else if (algorithmParameterSpec instanceof PBEParameterSpec) {
                    e = Util.a(bCPBEKey, algorithmParameterSpec, this.b.a());
                    this.e = (PBEParameterSpec) algorithmParameterSpec;
                } else {
                    throw new InvalidAlgorithmParameterException("PBE requires PBE parameters to be set.");
                }
                if (bCPBEKey.d() != 0) {
                    this.c = (ParametersWithIV) e;
                }
                cipherParameters = e;
            } else if (algorithmParameterSpec == null) {
                cipherParameters = new KeyParameter(key.getEncoded());
            } else if (algorithmParameterSpec instanceof IvParameterSpec) {
                parametersWithIV = new ParametersWithIV(new KeyParameter(key.getEncoded()), ((IvParameterSpec) algorithmParameterSpec).getIV());
                this.c = parametersWithIV;
                cipherParameters = parametersWithIV;
            } else {
                throw new IllegalArgumentException("unknown parameter type.");
            }
            if (!(this.d == 0 || (cipherParameters instanceof ParametersWithIV))) {
                if (secureRandom == null) {
                    secureRandom = new SecureRandom();
                }
                if (i == 1 || i == 3) {
                    byte[] bArr = new byte[this.d];
                    secureRandom.nextBytes(bArr);
                    parametersWithIV = new ParametersWithIV(cipherParameters, bArr);
                    this.c = parametersWithIV;
                    cipherParameters = parametersWithIV;
                } else {
                    throw new InvalidAlgorithmParameterException("no IV set when one expected");
                }
            }
            switch (i) {
                case 1:
                case 3:
                    this.b.a(true, cipherParameters);
                    return;
                case 2:
                case 4:
                    this.b.a(false, cipherParameters);
                    return;
                default:
                    System.out.println("eeek!");
                    return;
            }
        }
        throw new InvalidKeyException("Key for algorithm " + key.getAlgorithm() + " not suitable for symmetric enryption.");
    }

    protected void engineInit(int i, Key key, AlgorithmParameters algorithmParameters, SecureRandom secureRandom) {
        AlgorithmParameterSpec parameterSpec;
        if (algorithmParameters != null) {
            int i2 = 0;
            while (i2 != this.a.length) {
                try {
                    parameterSpec = algorithmParameters.getParameterSpec(this.a[i2]);
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
        engineInit(i, key, parameterSpec, secureRandom);
        this.g = algorithmParameters;
    }

    protected void engineInit(int i, Key key, SecureRandom secureRandom) {
        try {
            engineInit(i, key, (AlgorithmParameterSpec) null, secureRandom);
        } catch (InvalidAlgorithmParameterException e) {
            throw new InvalidKeyException(e.getMessage());
        }
    }

    protected byte[] engineUpdate(byte[] bArr, int i, int i2) {
        byte[] bArr2 = new byte[i2];
        this.b.a(bArr, i, i2, bArr2, 0);
        return bArr2;
    }

    protected int engineUpdate(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
        try {
            this.b.a(bArr, i, i2, bArr2, i3);
            return i2;
        } catch (DataLengthException e) {
            throw new ShortBufferException(e.getMessage());
        }
    }

    protected byte[] engineDoFinal(byte[] bArr, int i, int i2) {
        if (i2 != 0) {
            byte[] engineUpdate = engineUpdate(bArr, i, i2);
            this.b.b();
            return engineUpdate;
        }
        this.b.b();
        return new byte[0];
    }

    protected int engineDoFinal(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
        if (i2 != 0) {
            this.b.a(bArr, i, i2, bArr2, i3);
        }
        this.b.b();
        return i2;
    }

    protected byte[] engineWrap(Key key) {
        byte[] encoded = key.getEncoded();
        if (encoded == null) {
            throw new InvalidKeyException("Cannot wrap key, null encoding.");
        }
        try {
            return engineDoFinal(encoded, 0, encoded.length);
        } catch (BadPaddingException e) {
            throw new IllegalBlockSizeException(e.getMessage());
        }
    }

    protected Key engineUnwrap(byte[] bArr, String str, int i) {
        try {
            Object engineDoFinal = engineDoFinal(bArr, 0, bArr.length);
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
        } catch (BadPaddingException e5) {
            throw new InvalidKeyException(e5.getMessage());
        } catch (IllegalBlockSizeException e6) {
            throw new InvalidKeyException(e6.getMessage());
        }
    }
}
