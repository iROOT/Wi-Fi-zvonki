package org.spongycastle.jcajce.provider.asymmetric.dh;

import java.io.ByteArrayOutputStream;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.BadPaddingException;
import javax.crypto.CipherSpi;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.interfaces.DHKey;
import javax.crypto.interfaces.DHPrivateKey;
import javax.crypto.interfaces.DHPublicKey;
import org.spongycastle.crypto.AsymmetricCipherKeyPairGenerator;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.InvalidCipherTextException;
import org.spongycastle.crypto.KeyEncoder;
import org.spongycastle.crypto.agreement.DHBasicAgreement;
import org.spongycastle.crypto.digests.SHA1Digest;
import org.spongycastle.crypto.engines.AESEngine;
import org.spongycastle.crypto.engines.DESedeEngine;
import org.spongycastle.crypto.engines.IESEngine;
import org.spongycastle.crypto.generators.DHKeyPairGenerator;
import org.spongycastle.crypto.generators.EphemeralKeyPairGenerator;
import org.spongycastle.crypto.generators.KDF2BytesGenerator;
import org.spongycastle.crypto.macs.HMac;
import org.spongycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.crypto.params.DHKeyGenerationParameters;
import org.spongycastle.crypto.params.DHKeyParameters;
import org.spongycastle.crypto.params.DHParameters;
import org.spongycastle.crypto.params.DHPublicKeyParameters;
import org.spongycastle.crypto.params.IESWithCipherParameters;
import org.spongycastle.crypto.parsers.DHIESPublicKeyParser;
import org.spongycastle.jcajce.provider.asymmetric.util.DHUtil;
import org.spongycastle.jcajce.provider.asymmetric.util.IESUtil;
import org.spongycastle.jce.interfaces.IESKey;
import org.spongycastle.jce.spec.IESParameterSpec;
import org.spongycastle.util.BigIntegers;
import org.spongycastle.util.Strings;

public class IESCipher extends CipherSpi {
    private IESEngine a;
    private int b = -1;
    private ByteArrayOutputStream c = new ByteArrayOutputStream();
    private AlgorithmParameters d = null;
    private IESParameterSpec e = null;
    private AsymmetricKeyParameter f;
    private SecureRandom g;
    private boolean h = false;
    private AsymmetricKeyParameter i = null;

    public static class IES extends IESCipher {
        public IES() {
            super(new IESEngine(new DHBasicAgreement(), new KDF2BytesGenerator(new SHA1Digest()), new HMac(new SHA1Digest())));
        }
    }

    public static class IESwithAES extends IESCipher {
        public IESwithAES() {
            super(new IESEngine(new DHBasicAgreement(), new KDF2BytesGenerator(new SHA1Digest()), new HMac(new SHA1Digest()), new PaddedBufferedBlockCipher(new AESEngine())));
        }
    }

    public static class IESwithDESede extends IESCipher {
        public IESwithDESede() {
            super(new IESEngine(new DHBasicAgreement(), new KDF2BytesGenerator(new SHA1Digest()), new HMac(new SHA1Digest()), new PaddedBufferedBlockCipher(new DESedeEngine())));
        }
    }

    public IESCipher(IESEngine iESEngine) {
        this.a = iESEngine;
    }

    public int engineGetBlockSize() {
        if (this.a.a() != null) {
            return this.a.a().b();
        }
        return 0;
    }

    public int engineGetKeySize(Key key) {
        if (key instanceof DHKey) {
            return ((DHKey) key).getParams().getP().bitLength();
        }
        throw new IllegalArgumentException("not a DH key");
    }

    public byte[] engineGetIV() {
        return null;
    }

    public AlgorithmParameters engineGetParameters() {
        if (this.d == null && this.e != null) {
            try {
                this.d = AlgorithmParameters.getInstance("IES", "SC");
                this.d.init(this.e);
            } catch (Exception e) {
                throw new RuntimeException(e.toString());
            }
        }
        return this.d;
    }

    public void engineSetMode(String str) {
        String b = Strings.b(str);
        if (b.equals("NONE")) {
            this.h = false;
        } else if (b.equals("DHAES")) {
            this.h = true;
        } else {
            throw new IllegalArgumentException("can't support mode " + str);
        }
    }

    public int engineGetOutputSize(int i) {
        int b = this.a.b().b();
        if (this.f != null) {
            int bitLength = (((DHKey) this.f).getParams().getP().bitLength() / 8) + 1;
            if (this.a.a() != null) {
                if (this.b == 1 || this.b == 3) {
                    i = this.a.a().b(i);
                } else if (this.b == 2 || this.b == 4) {
                    i = this.a.a().b((i - b) - bitLength);
                } else {
                    throw new IllegalStateException("cipher not initialised");
                }
            }
            if (this.b == 1 || this.b == 3) {
                return (bitLength + (b + this.c.size())) + i;
            }
            if (this.b == 2 || this.b == 4) {
                return ((this.c.size() - b) - bitLength) + i;
            }
            throw new IllegalStateException("IESCipher not initialised");
        }
        throw new IllegalStateException("cipher not initialised");
    }

    public void engineSetPadding(String str) {
        String b = Strings.b(str);
        if (!b.equals("NOPADDING") && !b.equals("PKCS5PADDING") && !b.equals("PKCS7PADDING")) {
            throw new NoSuchPaddingException("padding not available with IESCipher");
        }
    }

    public void engineInit(int i, Key key, AlgorithmParameters algorithmParameters, SecureRandom secureRandom) {
        AlgorithmParameterSpec algorithmParameterSpec = null;
        if (algorithmParameters != null) {
            try {
                algorithmParameterSpec = algorithmParameters.getParameterSpec(IESParameterSpec.class);
            } catch (Exception e) {
                throw new InvalidAlgorithmParameterException("cannot recognise parameters: " + e.toString());
            }
        }
        this.d = algorithmParameters;
        engineInit(i, key, algorithmParameterSpec, secureRandom);
    }

    public void engineInit(int i, Key key, AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) {
        if (algorithmParameterSpec == null) {
            this.e = IESUtil.a(this.a);
        } else if (algorithmParameterSpec instanceof IESParameterSpec) {
            this.e = (IESParameterSpec) algorithmParameterSpec;
        } else {
            throw new InvalidAlgorithmParameterException("must be passed IES parameters");
        }
        IESKey iESKey;
        if (i == 1 || i == 3) {
            if (key instanceof DHPublicKey) {
                this.f = DHUtil.a((PublicKey) key);
            } else if (key instanceof IESKey) {
                iESKey = (IESKey) key;
                this.f = DHUtil.a(iESKey.a());
                this.i = DHUtil.a(iESKey.b());
            } else {
                throw new InvalidKeyException("must be passed recipient's public DH key for encryption");
            }
        } else if (i != 2 && i != 4) {
            throw new InvalidKeyException("must be passed EC key");
        } else if (key instanceof DHPrivateKey) {
            this.f = DHUtil.a((PrivateKey) key);
        } else if (key instanceof IESKey) {
            iESKey = (IESKey) key;
            this.i = DHUtil.a(iESKey.a());
            this.f = DHUtil.a(iESKey.b());
        } else {
            throw new InvalidKeyException("must be passed recipient's private DH key for decryption");
        }
        this.g = secureRandom;
        this.b = i;
        this.c.reset();
    }

    public void engineInit(int i, Key key, SecureRandom secureRandom) {
        try {
            engineInit(i, key, (AlgorithmParameterSpec) null, secureRandom);
        } catch (InvalidAlgorithmParameterException e) {
            throw new IllegalArgumentException("can't handle supplied parameter spec");
        }
    }

    public byte[] engineUpdate(byte[] bArr, int i, int i2) {
        this.c.write(bArr, i, i2);
        return null;
    }

    public int engineUpdate(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
        this.c.write(bArr, i, i2);
        return 0;
    }

    public byte[] engineDoFinal(byte[] bArr, int i, int i2) {
        if (i2 != 0) {
            this.c.write(bArr, i, i2);
        }
        byte[] toByteArray = this.c.toByteArray();
        this.c.reset();
        CipherParameters iESWithCipherParameters = new IESWithCipherParameters(this.e.a(), this.e.b(), this.e.c(), this.e.d());
        DHParameters b = ((DHKeyParameters) this.f).b();
        if (this.i != null) {
            try {
                if (this.b == 1 || this.b == 3) {
                    this.a.a(true, this.i, this.f, iESWithCipherParameters);
                } else {
                    this.a.a(false, this.f, this.i, iESWithCipherParameters);
                }
                return this.a.a(toByteArray, 0, toByteArray.length);
            } catch (Exception e) {
                throw new BadPaddingException(e.getMessage());
            }
        } else if (this.b == 1 || this.b == 3) {
            AsymmetricCipherKeyPairGenerator dHKeyPairGenerator = new DHKeyPairGenerator();
            dHKeyPairGenerator.a(new DHKeyGenerationParameters(this.g, b));
            try {
                this.a.a(this.f, iESWithCipherParameters, new EphemeralKeyPairGenerator(dHKeyPairGenerator, new KeyEncoder(this) {
                    final /* synthetic */ IESCipher a;

                    {
                        this.a = r1;
                    }

                    public byte[] a(AsymmetricKeyParameter asymmetricKeyParameter) {
                        Object obj = new byte[((((DHKeyParameters) asymmetricKeyParameter).b().a().bitLength() + 7) / 8)];
                        Object a = BigIntegers.a(((DHPublicKeyParameters) asymmetricKeyParameter).c());
                        if (a.length > obj.length) {
                            throw new IllegalArgumentException("Senders's public key longer than expected.");
                        }
                        System.arraycopy(a, 0, obj, obj.length - a.length, a.length);
                        return obj;
                    }
                }));
                return this.a.a(toByteArray, 0, toByteArray.length);
            } catch (Exception e2) {
                throw new BadPaddingException(e2.getMessage());
            }
        } else if (this.b == 2 || this.b == 4) {
            try {
                this.a.a(this.f, iESWithCipherParameters, new DHIESPublicKeyParser(((DHKeyParameters) this.f).b()));
                return this.a.a(toByteArray, 0, toByteArray.length);
            } catch (InvalidCipherTextException e3) {
                throw new BadPaddingException(e3.getMessage());
            }
        } else {
            throw new IllegalStateException("IESCipher not initialised");
        }
    }

    public int engineDoFinal(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
        Object engineDoFinal = engineDoFinal(bArr, i, i2);
        System.arraycopy(engineDoFinal, 0, bArr2, i3, engineDoFinal.length);
        return engineDoFinal.length;
    }
}
