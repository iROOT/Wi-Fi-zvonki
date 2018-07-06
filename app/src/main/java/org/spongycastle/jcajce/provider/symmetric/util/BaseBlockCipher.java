package org.spongycastle.jcajce.provider.symmetric.util;

import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.InvalidParameterException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEParameterSpec;
import javax.crypto.spec.RC2ParameterSpec;
import javax.crypto.spec.RC5ParameterSpec;
import org.spongycastle.asn1.cms.GCMParameters;
import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.BufferedBlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.InvalidCipherTextException;
import org.spongycastle.crypto.OutputLengthException;
import org.spongycastle.crypto.modes.AEADBlockCipher;
import org.spongycastle.crypto.modes.CBCBlockCipher;
import org.spongycastle.crypto.modes.CCMBlockCipher;
import org.spongycastle.crypto.modes.CFBBlockCipher;
import org.spongycastle.crypto.modes.CTSBlockCipher;
import org.spongycastle.crypto.modes.EAXBlockCipher;
import org.spongycastle.crypto.modes.GCFBBlockCipher;
import org.spongycastle.crypto.modes.GCMBlockCipher;
import org.spongycastle.crypto.modes.GOFBBlockCipher;
import org.spongycastle.crypto.modes.OCBBlockCipher;
import org.spongycastle.crypto.modes.OFBBlockCipher;
import org.spongycastle.crypto.modes.OpenPGPCFBBlockCipher;
import org.spongycastle.crypto.modes.PGPCFBBlockCipher;
import org.spongycastle.crypto.modes.SICBlockCipher;
import org.spongycastle.crypto.paddings.BlockCipherPadding;
import org.spongycastle.crypto.paddings.ISO10126d2Padding;
import org.spongycastle.crypto.paddings.ISO7816d4Padding;
import org.spongycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.spongycastle.crypto.paddings.TBCPadding;
import org.spongycastle.crypto.paddings.X923Padding;
import org.spongycastle.crypto.paddings.ZeroBytePadding;
import org.spongycastle.crypto.params.AEADParameters;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.params.ParametersWithIV;
import org.spongycastle.crypto.params.ParametersWithRandom;
import org.spongycastle.crypto.params.ParametersWithSBox;
import org.spongycastle.crypto.params.RC2Parameters;
import org.spongycastle.crypto.params.RC5Parameters;
import org.spongycastle.jcajce.provider.symmetric.util.PBE.Util;
import org.spongycastle.jcajce.spec.GOST28147ParameterSpec;
import org.spongycastle.jcajce.spec.RepeatedSecretKeySpec;
import org.spongycastle.util.Strings;

public class BaseBlockCipher extends BaseWrapCipher implements PBE {
    private static final Class e = a("javax.crypto.spec.GCMParameterSpec");
    private Class[] f;
    private BlockCipher g;
    private BlockCipherProvider h;
    private GenericBlockCipher i;
    private ParametersWithIV j;
    private AEADParameters k;
    private int l;
    private boolean m;
    private PBEParameterSpec n;
    private String o;
    private String p;

    private interface GenericBlockCipher {
        int a(int i);

        int a(byte[] bArr, int i);

        int a(byte[] bArr, int i, int i2, byte[] bArr2, int i3);

        void a(boolean z, CipherParameters cipherParameters);

        void a(byte[] bArr, int i, int i2);

        boolean a();

        int b(int i);

        BlockCipher b();
    }

    private static class AEADGenericBlockCipher implements GenericBlockCipher {
        private AEADBlockCipher a;

        AEADGenericBlockCipher(AEADBlockCipher aEADBlockCipher) {
            this.a = aEADBlockCipher;
        }

        public void a(boolean z, CipherParameters cipherParameters) {
            this.a.a(z, cipherParameters);
        }

        public boolean a() {
            return false;
        }

        public BlockCipher b() {
            return this.a.a();
        }

        public int a(int i) {
            return this.a.b(i);
        }

        public int b(int i) {
            return this.a.a(i);
        }

        public void a(byte[] bArr, int i, int i2) {
            this.a.a(bArr, i, i2);
        }

        public int a(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
            return this.a.a(bArr, i, i2, bArr2, i3);
        }

        public int a(byte[] bArr, int i) {
            return this.a.a(bArr, i);
        }
    }

    private static class BufferedGenericBlockCipher implements GenericBlockCipher {
        private BufferedBlockCipher a;

        BufferedGenericBlockCipher(BufferedBlockCipher bufferedBlockCipher) {
            this.a = bufferedBlockCipher;
        }

        BufferedGenericBlockCipher(BlockCipher blockCipher) {
            this.a = new PaddedBufferedBlockCipher(blockCipher);
        }

        BufferedGenericBlockCipher(BlockCipher blockCipher, BlockCipherPadding blockCipherPadding) {
            this.a = new PaddedBufferedBlockCipher(blockCipher, blockCipherPadding);
        }

        public void a(boolean z, CipherParameters cipherParameters) {
            this.a.a(z, cipherParameters);
        }

        public boolean a() {
            return !(this.a instanceof CTSBlockCipher);
        }

        public BlockCipher b() {
            return this.a.a();
        }

        public int a(int i) {
            return this.a.b(i);
        }

        public int b(int i) {
            return this.a.a(i);
        }

        public void a(byte[] bArr, int i, int i2) {
            throw new UnsupportedOperationException("AAD is not supported in the current mode.");
        }

        public int a(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
            return this.a.a(bArr, i, i2, bArr2, i3);
        }

        public int a(byte[] bArr, int i) {
            return this.a.a(bArr, i);
        }
    }

    private static Class a(String str) {
        try {
            return BaseBlockCipher.class.getClassLoader().loadClass(str);
        } catch (Exception e) {
            return null;
        }
    }

    protected BaseBlockCipher(BlockCipher blockCipher) {
        this.f = new Class[]{RC2ParameterSpec.class, RC5ParameterSpec.class, IvParameterSpec.class, PBEParameterSpec.class, GOST28147ParameterSpec.class, e};
        this.l = 0;
        this.n = null;
        this.o = null;
        this.p = null;
        this.g = blockCipher;
        this.i = new BufferedGenericBlockCipher(blockCipher);
    }

    protected BaseBlockCipher(BlockCipherProvider blockCipherProvider) {
        this.f = new Class[]{RC2ParameterSpec.class, RC5ParameterSpec.class, IvParameterSpec.class, PBEParameterSpec.class, GOST28147ParameterSpec.class, e};
        this.l = 0;
        this.n = null;
        this.o = null;
        this.p = null;
        this.g = blockCipherProvider.a();
        this.h = blockCipherProvider;
        this.i = new BufferedGenericBlockCipher(blockCipherProvider.a());
    }

    protected BaseBlockCipher(AEADBlockCipher aEADBlockCipher) {
        this.f = new Class[]{RC2ParameterSpec.class, RC5ParameterSpec.class, IvParameterSpec.class, PBEParameterSpec.class, GOST28147ParameterSpec.class, e};
        this.l = 0;
        this.n = null;
        this.o = null;
        this.p = null;
        this.g = aEADBlockCipher.a();
        this.l = this.g.b();
        this.i = new AEADGenericBlockCipher(aEADBlockCipher);
    }

    protected BaseBlockCipher(BlockCipher blockCipher, int i) {
        this.f = new Class[]{RC2ParameterSpec.class, RC5ParameterSpec.class, IvParameterSpec.class, PBEParameterSpec.class, GOST28147ParameterSpec.class, e};
        this.l = 0;
        this.n = null;
        this.o = null;
        this.p = null;
        this.g = blockCipher;
        this.i = new BufferedGenericBlockCipher(blockCipher);
        this.l = i / 8;
    }

    protected BaseBlockCipher(BufferedBlockCipher bufferedBlockCipher, int i) {
        this.f = new Class[]{RC2ParameterSpec.class, RC5ParameterSpec.class, IvParameterSpec.class, PBEParameterSpec.class, GOST28147ParameterSpec.class, e};
        this.l = 0;
        this.n = null;
        this.o = null;
        this.p = null;
        this.g = bufferedBlockCipher.a();
        this.i = new BufferedGenericBlockCipher(bufferedBlockCipher);
        this.l = i / 8;
    }

    protected int engineGetBlockSize() {
        return this.g.b();
    }

    protected byte[] engineGetIV() {
        return this.j != null ? this.j.a() : null;
    }

    protected int engineGetKeySize(Key key) {
        return key.getEncoded().length * 8;
    }

    protected int engineGetOutputSize(int i) {
        return this.i.a(i);
    }

    protected AlgorithmParameters engineGetParameters() {
        if (this.c == null) {
            if (this.n != null) {
                try {
                    this.c = AlgorithmParameters.getInstance(this.o, "SC");
                    this.c.init(this.n);
                } catch (Exception e) {
                    return null;
                }
            } else if (this.j != null) {
                String a = this.i.b().a();
                if (a.indexOf(47) >= 0) {
                    a = a.substring(0, a.indexOf(47));
                }
                try {
                    this.c = AlgorithmParameters.getInstance(a, "SC");
                    this.c.init(this.j.a());
                } catch (Exception e2) {
                    throw new RuntimeException(e2.toString());
                }
            } else if (this.k != null) {
                try {
                    this.c = AlgorithmParameters.getInstance("GCM", "SC");
                    this.c.init(new GCMParameters(this.k.d(), this.k.b()).b());
                } catch (Exception e22) {
                    throw new RuntimeException(e22.toString());
                }
            }
        }
        return this.c;
    }

    protected void engineSetMode(String str) {
        this.p = Strings.b(str);
        if (this.p.equals("ECB")) {
            this.l = 0;
            this.i = new BufferedGenericBlockCipher(this.g);
        } else if (this.p.equals("CBC")) {
            this.l = this.g.b();
            this.i = new BufferedGenericBlockCipher(new CBCBlockCipher(this.g));
        } else if (this.p.startsWith("OFB")) {
            this.l = this.g.b();
            if (this.p.length() != 3) {
                this.i = new BufferedGenericBlockCipher(new OFBBlockCipher(this.g, Integer.parseInt(this.p.substring(3))));
                return;
            }
            this.i = new BufferedGenericBlockCipher(new OFBBlockCipher(this.g, this.g.b() * 8));
        } else if (this.p.startsWith("CFB")) {
            this.l = this.g.b();
            if (this.p.length() != 3) {
                this.i = new BufferedGenericBlockCipher(new CFBBlockCipher(this.g, Integer.parseInt(this.p.substring(3))));
                return;
            }
            this.i = new BufferedGenericBlockCipher(new CFBBlockCipher(this.g, this.g.b() * 8));
        } else if (this.p.startsWith("PGP")) {
            boolean equalsIgnoreCase = this.p.equalsIgnoreCase("PGPCFBwithIV");
            this.l = this.g.b();
            this.i = new BufferedGenericBlockCipher(new PGPCFBBlockCipher(this.g, equalsIgnoreCase));
        } else if (this.p.equalsIgnoreCase("OpenPGPCFB")) {
            this.l = 0;
            this.i = new BufferedGenericBlockCipher(new OpenPGPCFBBlockCipher(this.g));
        } else if (this.p.startsWith("SIC")) {
            this.l = this.g.b();
            if (this.l < 16) {
                throw new IllegalArgumentException("Warning: SIC-Mode can become a twotime-pad if the blocksize of the cipher is too small. Use a cipher with a block size of at least 128 bits (e.g. AES)");
            }
            this.i = new BufferedGenericBlockCipher(new BufferedBlockCipher(new SICBlockCipher(this.g)));
        } else if (this.p.startsWith("CTR")) {
            this.l = this.g.b();
            this.i = new BufferedGenericBlockCipher(new BufferedBlockCipher(new SICBlockCipher(this.g)));
        } else if (this.p.startsWith("GOFB")) {
            this.l = this.g.b();
            this.i = new BufferedGenericBlockCipher(new BufferedBlockCipher(new GOFBBlockCipher(this.g)));
        } else if (this.p.startsWith("GCFB")) {
            this.l = this.g.b();
            this.i = new BufferedGenericBlockCipher(new BufferedBlockCipher(new GCFBBlockCipher(this.g)));
        } else if (this.p.startsWith("CTS")) {
            this.l = this.g.b();
            this.i = new BufferedGenericBlockCipher(new CTSBlockCipher(new CBCBlockCipher(this.g)));
        } else if (this.p.startsWith("CCM")) {
            this.l = 13;
            this.i = new AEADGenericBlockCipher(new CCMBlockCipher(this.g));
        } else if (this.p.startsWith("OCB")) {
            if (this.h != null) {
                this.l = 15;
                this.i = new AEADGenericBlockCipher(new OCBBlockCipher(this.g, this.h.a()));
                return;
            }
            throw new NoSuchAlgorithmException("can't support mode " + str);
        } else if (this.p.startsWith("EAX")) {
            this.l = this.g.b();
            this.i = new AEADGenericBlockCipher(new EAXBlockCipher(this.g));
        } else if (this.p.startsWith("GCM")) {
            this.l = this.g.b();
            this.i = new AEADGenericBlockCipher(new GCMBlockCipher(this.g));
        } else {
            throw new NoSuchAlgorithmException("can't support mode " + str);
        }
    }

    protected void engineSetPadding(String str) {
        String b = Strings.b(str);
        if (b.equals("NOPADDING")) {
            if (this.i.a()) {
                this.i = new BufferedGenericBlockCipher(new BufferedBlockCipher(this.i.b()));
            }
        } else if (b.equals("WITHCTS")) {
            this.i = new BufferedGenericBlockCipher(new CTSBlockCipher(this.i.b()));
        } else {
            this.m = true;
            if (b(this.p)) {
                throw new NoSuchPaddingException("Only NoPadding can be used with AEAD modes.");
            } else if (b.equals("PKCS5PADDING") || b.equals("PKCS7PADDING")) {
                this.i = new BufferedGenericBlockCipher(this.i.b());
            } else if (b.equals("ZEROBYTEPADDING")) {
                this.i = new BufferedGenericBlockCipher(this.i.b(), new ZeroBytePadding());
            } else if (b.equals("ISO10126PADDING") || b.equals("ISO10126-2PADDING")) {
                this.i = new BufferedGenericBlockCipher(this.i.b(), new ISO10126d2Padding());
            } else if (b.equals("X9.23PADDING") || b.equals("X923PADDING")) {
                this.i = new BufferedGenericBlockCipher(this.i.b(), new X923Padding());
            } else if (b.equals("ISO7816-4PADDING") || b.equals("ISO9797-1PADDING")) {
                this.i = new BufferedGenericBlockCipher(this.i.b(), new ISO7816d4Padding());
            } else if (b.equals("TBCPADDING")) {
                this.i = new BufferedGenericBlockCipher(this.i.b(), new TBCPadding());
            } else {
                throw new NoSuchPaddingException("Padding " + str + " unknown.");
            }
        }
    }

    protected void engineInit(int i, Key key, AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) {
        this.n = null;
        this.o = null;
        this.c = null;
        this.k = null;
        if (!(key instanceof SecretKey)) {
            throw new InvalidKeyException("Key for algorithm " + key.getAlgorithm() + " not suitable for symmetric enryption.");
        } else if (algorithmParameterSpec == null && this.g.a().startsWith("RC5-64")) {
            throw new InvalidAlgorithmParameterException("RC5 requires an RC5ParametersSpec to be passed in.");
        } else {
            CipherParameters e;
            CipherParameters parametersWithSBox;
            ParametersWithIV parametersWithIV;
            if (key instanceof BCPBEKey) {
                BCPBEKey bCPBEKey = (BCPBEKey) key;
                if (bCPBEKey.f() != null) {
                    this.o = bCPBEKey.f().d();
                } else {
                    this.o = bCPBEKey.getAlgorithm();
                }
                if (bCPBEKey.e() != null) {
                    e = bCPBEKey.e();
                    if (algorithmParameterSpec instanceof IvParameterSpec) {
                        parametersWithIV = new ParametersWithIV(e, ((IvParameterSpec) algorithmParameterSpec).getIV());
                    } else if (algorithmParameterSpec instanceof GOST28147ParameterSpec) {
                        ParametersWithIV parametersWithIV2;
                        GOST28147ParameterSpec gOST28147ParameterSpec = (GOST28147ParameterSpec) algorithmParameterSpec;
                        parametersWithSBox = new ParametersWithSBox(e, gOST28147ParameterSpec.a());
                        if (gOST28147ParameterSpec.b() == null || this.l == 0) {
                            e = parametersWithSBox;
                        } else {
                            parametersWithIV2 = new ParametersWithIV(parametersWithSBox, gOST28147ParameterSpec.b());
                        }
                        parametersWithIV = parametersWithIV2;
                    } else {
                        parametersWithSBox = e;
                    }
                } else if (algorithmParameterSpec instanceof PBEParameterSpec) {
                    this.n = (PBEParameterSpec) algorithmParameterSpec;
                    parametersWithIV = Util.a(bCPBEKey, algorithmParameterSpec, this.i.b().a());
                } else {
                    throw new InvalidAlgorithmParameterException("PBE requires PBE parameters to be set.");
                }
                if (parametersWithIV instanceof ParametersWithIV) {
                    this.j = parametersWithIV;
                }
                e = parametersWithIV;
            } else if (algorithmParameterSpec == null) {
                r0 = new KeyParameter(key.getEncoded());
            } else if (algorithmParameterSpec instanceof IvParameterSpec) {
                if (this.l != 0) {
                    IvParameterSpec ivParameterSpec = (IvParameterSpec) algorithmParameterSpec;
                    if (ivParameterSpec.getIV().length == this.l || b(this.p)) {
                        if (key instanceof RepeatedSecretKeySpec) {
                            parametersWithIV = new ParametersWithIV(null, ivParameterSpec.getIV());
                            this.j = parametersWithIV;
                        } else {
                            parametersWithIV = new ParametersWithIV(new KeyParameter(key.getEncoded()), ivParameterSpec.getIV());
                            this.j = parametersWithIV;
                        }
                        r0 = parametersWithIV;
                    } else {
                        throw new InvalidAlgorithmParameterException("IV must be " + this.l + " bytes long.");
                    }
                } else if (this.p == null || !this.p.equals("ECB")) {
                    r0 = new KeyParameter(key.getEncoded());
                } else {
                    throw new InvalidAlgorithmParameterException("ECB mode does not use an IV");
                }
            } else if (algorithmParameterSpec instanceof GOST28147ParameterSpec) {
                GOST28147ParameterSpec gOST28147ParameterSpec2 = (GOST28147ParameterSpec) algorithmParameterSpec;
                r2 = new ParametersWithSBox(new KeyParameter(key.getEncoded()), ((GOST28147ParameterSpec) algorithmParameterSpec).a());
                if (gOST28147ParameterSpec2.b() == null || this.l == 0) {
                    parametersWithSBox = r2;
                } else {
                    parametersWithIV = new ParametersWithIV(r2, gOST28147ParameterSpec2.b());
                    this.j = parametersWithIV;
                }
                r0 = parametersWithIV;
            } else if (algorithmParameterSpec instanceof RC2ParameterSpec) {
                RC2ParameterSpec rC2ParameterSpec = (RC2ParameterSpec) algorithmParameterSpec;
                r2 = new RC2Parameters(key.getEncoded(), ((RC2ParameterSpec) algorithmParameterSpec).getEffectiveKeyBits());
                if (rC2ParameterSpec.getIV() == null || this.l == 0) {
                    parametersWithSBox = r2;
                } else {
                    parametersWithIV = new ParametersWithIV(r2, rC2ParameterSpec.getIV());
                    this.j = parametersWithIV;
                }
                r0 = parametersWithIV;
            } else if (algorithmParameterSpec instanceof RC5ParameterSpec) {
                RC5ParameterSpec rC5ParameterSpec = (RC5ParameterSpec) algorithmParameterSpec;
                r2 = new RC5Parameters(key.getEncoded(), ((RC5ParameterSpec) algorithmParameterSpec).getRounds());
                if (this.g.a().startsWith("RC5")) {
                    if (this.g.a().equals("RC5-32")) {
                        if (rC5ParameterSpec.getWordSize() != 32) {
                            throw new InvalidAlgorithmParameterException("RC5 already set up for a word size of 32 not " + rC5ParameterSpec.getWordSize() + ".");
                        }
                    } else if (this.g.a().equals("RC5-64") && rC5ParameterSpec.getWordSize() != 64) {
                        throw new InvalidAlgorithmParameterException("RC5 already set up for a word size of 64 not " + rC5ParameterSpec.getWordSize() + ".");
                    }
                    if (rC5ParameterSpec.getIV() == null || this.l == 0) {
                        parametersWithSBox = r2;
                    } else {
                        parametersWithIV = new ParametersWithIV(r2, rC5ParameterSpec.getIV());
                        this.j = parametersWithIV;
                    }
                    r0 = parametersWithIV;
                } else {
                    throw new InvalidAlgorithmParameterException("RC5 parameters passed to a cipher that is not RC5.");
                }
            } else if (e == null || !e.isInstance(algorithmParameterSpec)) {
                throw new InvalidAlgorithmParameterException("unknown parameter type.");
            } else if (b(this.p) || (this.i instanceof AEADGenericBlockCipher)) {
                try {
                    Method declaredMethod = e.getDeclaredMethod("getTLen", new Class[0]);
                    Method declaredMethod2 = e.getDeclaredMethod("getIV", new Class[0]);
                    AEADParameters aEADParameters;
                    if (key instanceof RepeatedSecretKeySpec) {
                        aEADParameters = new AEADParameters(null, ((Integer) declaredMethod.invoke(algorithmParameterSpec, new Object[0])).intValue(), (byte[]) declaredMethod2.invoke(algorithmParameterSpec, new Object[0]));
                        this.k = aEADParameters;
                        r0 = aEADParameters;
                    } else {
                        aEADParameters = new AEADParameters(new KeyParameter(key.getEncoded()), ((Integer) declaredMethod.invoke(algorithmParameterSpec, new Object[0])).intValue(), (byte[]) declaredMethod2.invoke(algorithmParameterSpec, new Object[0]));
                        this.k = aEADParameters;
                        r0 = aEADParameters;
                    }
                } catch (Exception e2) {
                    throw new InvalidAlgorithmParameterException("Cannot process GCMParameterSpec.");
                }
            } else {
                throw new InvalidAlgorithmParameterException("GCMParameterSpec can only be used with AEAD modes.");
            }
            if (!(this.l == 0 || (e instanceof ParametersWithIV) || (e instanceof AEADParameters))) {
                SecureRandom secureRandom2;
                if (secureRandom == null) {
                    secureRandom2 = new SecureRandom();
                } else {
                    secureRandom2 = secureRandom;
                }
                if (i == 1 || i == 3) {
                    byte[] bArr = new byte[this.l];
                    secureRandom2.nextBytes(bArr);
                    parametersWithSBox = new ParametersWithIV(e, bArr);
                    this.j = (ParametersWithIV) parametersWithSBox;
                    if (secureRandom == null && this.m) {
                        e = new ParametersWithRandom(parametersWithSBox, secureRandom);
                    } else {
                        e = parametersWithSBox;
                    }
                    switch (i) {
                        case 1:
                        case 3:
                            this.i.a(true, e);
                            return;
                        case 2:
                        case 4:
                            this.i.a(false, e);
                            return;
                        default:
                            try {
                                throw new InvalidParameterException("unknown opmode " + i + " passed");
                            } catch (Exception e3) {
                                throw new InvalidKeyException(e3.getMessage());
                            }
                    }
                    throw new InvalidKeyException(e3.getMessage());
                } else if (this.i.b().a().indexOf("PGPCFB") < 0) {
                    throw new InvalidAlgorithmParameterException("no IV set when one expected");
                }
            }
            parametersWithSBox = e;
            if (secureRandom == null) {
            }
            e = parametersWithSBox;
            switch (i) {
                case 1:
                case 3:
                    this.i.a(true, e);
                    return;
                case 2:
                case 4:
                    this.i.a(false, e);
                    return;
                default:
                    throw new InvalidParameterException("unknown opmode " + i + " passed");
            }
            throw new InvalidKeyException(e3.getMessage());
        }
    }

    protected void engineInit(int i, Key key, AlgorithmParameters algorithmParameters, SecureRandom secureRandom) {
        AlgorithmParameterSpec parameterSpec;
        if (algorithmParameters != null) {
            for (int i2 = 0; i2 != this.f.length; i2++) {
                if (this.f[i2] != null) {
                    try {
                        parameterSpec = algorithmParameters.getParameterSpec(this.f[i2]);
                        break;
                    } catch (Exception e) {
                    }
                }
            }
            parameterSpec = null;
            if (parameterSpec == null) {
                throw new InvalidAlgorithmParameterException("can't handle parameter " + algorithmParameters.toString());
            }
        }
        parameterSpec = null;
        engineInit(i, key, parameterSpec, secureRandom);
        this.c = algorithmParameters;
    }

    protected void engineInit(int i, Key key, SecureRandom secureRandom) {
        try {
            engineInit(i, key, (AlgorithmParameterSpec) null, secureRandom);
        } catch (InvalidAlgorithmParameterException e) {
            throw new InvalidKeyException(e.getMessage());
        }
    }

    protected void engineUpdateAAD(byte[] bArr, int i, int i2) {
        this.i.a(bArr, i, i2);
    }

    protected void engineUpdateAAD(ByteBuffer byteBuffer) {
        engineUpdateAAD(byteBuffer.array(), byteBuffer.arrayOffset() + byteBuffer.position(), byteBuffer.limit() - byteBuffer.position());
    }

    protected byte[] engineUpdate(byte[] bArr, int i, int i2) {
        int b = this.i.b(i2);
        if (b > 0) {
            byte[] bArr2 = new byte[b];
            int a = this.i.a(bArr, i, i2, bArr2, 0);
            if (a == 0) {
                return null;
            }
            if (a == bArr2.length) {
                return bArr2;
            }
            Object obj = new byte[a];
            System.arraycopy(bArr2, 0, obj, 0, a);
            return obj;
        }
        this.i.a(bArr, i, i2, null, 0);
        return null;
    }

    protected int engineUpdate(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
        try {
            return this.i.a(bArr, i, i2, bArr2, i3);
        } catch (DataLengthException e) {
            throw new ShortBufferException(e.getMessage());
        }
    }

    protected byte[] engineDoFinal(byte[] bArr, int i, int i2) {
        int a;
        byte[] bArr2 = new byte[engineGetOutputSize(i2)];
        if (i2 != 0) {
            a = this.i.a(bArr, i, i2, bArr2, 0);
        } else {
            a = 0;
        }
        try {
            int a2 = this.i.a(bArr2, a) + a;
            if (a2 == bArr2.length) {
                return bArr2;
            }
            Object obj = new byte[a2];
            System.arraycopy(bArr2, 0, obj, 0, a2);
            return obj;
        } catch (DataLengthException e) {
            throw new IllegalBlockSizeException(e.getMessage());
        } catch (InvalidCipherTextException e2) {
            throw new BadPaddingException(e2.getMessage());
        }
    }

    protected int engineDoFinal(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
        int i4 = 0;
        if (i2 != 0) {
            try {
                i4 = this.i.a(bArr, i, i2, bArr2, i3);
            } catch (OutputLengthException e) {
                throw new ShortBufferException(e.getMessage());
            } catch (DataLengthException e2) {
                throw new IllegalBlockSizeException(e2.getMessage());
            } catch (InvalidCipherTextException e3) {
                throw new BadPaddingException(e3.getMessage());
            }
        }
        return i4 + this.i.a(bArr2, i3 + i4);
    }

    private boolean b(String str) {
        return "CCM".equals(str) || "EAX".equals(str) || "GCM".equals(str) || "OCB".equals(str);
    }
}
