package org.spongycastle.jcajce.provider.asymmetric.ies;

import android.support.v4.app.NotificationCompat;
import java.io.ByteArrayOutputStream;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.BadPaddingException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.interfaces.DHPrivateKey;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.InvalidCipherTextException;
import org.spongycastle.crypto.agreement.DHBasicAgreement;
import org.spongycastle.crypto.digests.SHA1Digest;
import org.spongycastle.crypto.engines.IESEngine;
import org.spongycastle.crypto.generators.KDF2BytesGenerator;
import org.spongycastle.crypto.macs.HMac;
import org.spongycastle.crypto.params.IESParameters;
import org.spongycastle.jcajce.provider.asymmetric.util.DHUtil;
import org.spongycastle.jcajce.provider.asymmetric.util.ECUtil;
import org.spongycastle.jce.interfaces.ECPrivateKey;
import org.spongycastle.jce.interfaces.ECPublicKey;
import org.spongycastle.jce.interfaces.IESKey;
import org.spongycastle.jce.spec.IESParameterSpec;

public class CipherSpi extends javax.crypto.CipherSpi {
    private IESEngine a;
    private int b = -1;
    private ByteArrayOutputStream c = new ByteArrayOutputStream();
    private AlgorithmParameters d = null;
    private IESParameterSpec e = null;
    private Class[] f = new Class[]{IESParameterSpec.class};

    public static class IES extends CipherSpi {
        public IES() {
            super(new IESEngine(new DHBasicAgreement(), new KDF2BytesGenerator(new SHA1Digest()), new HMac(new SHA1Digest())));
        }
    }

    public CipherSpi(IESEngine iESEngine) {
        this.a = iESEngine;
    }

    protected int engineGetBlockSize() {
        return 0;
    }

    protected byte[] engineGetIV() {
        return null;
    }

    protected int engineGetKeySize(Key key) {
        if (key instanceof IESKey) {
            IESKey iESKey = (IESKey) key;
            if (iESKey.b() instanceof DHPrivateKey) {
                return ((DHPrivateKey) iESKey.b()).getX().bitLength();
            }
            if (iESKey.b() instanceof ECPrivateKey) {
                return ((ECPrivateKey) iESKey.b()).d().bitLength();
            }
            throw new IllegalArgumentException("not an IE key!");
        }
        throw new IllegalArgumentException("must be passed IE key");
    }

    protected int engineGetOutputSize(int i) {
        if (this.b == 1 || this.b == 3) {
            return (this.c.size() + i) + 20;
        }
        if (this.b == 2 || this.b == 4) {
            return (this.c.size() + i) - 20;
        }
        throw new IllegalStateException("cipher not initialised");
    }

    protected AlgorithmParameters engineGetParameters() {
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

    protected void engineSetMode(String str) {
        throw new IllegalArgumentException("can't support mode " + str);
    }

    protected void engineSetPadding(String str) {
        throw new NoSuchPaddingException(str + " unavailable with RSA.");
    }

    protected void engineInit(int i, Key key, AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) {
        if (key instanceof IESKey) {
            IESParameterSpec iESParameterSpec;
            CipherParameters a;
            CipherParameters a2;
            if (algorithmParameterSpec == null && (i == 1 || i == 3)) {
                byte[] bArr = new byte[16];
                byte[] bArr2 = new byte[16];
                if (secureRandom == null) {
                    secureRandom = new SecureRandom();
                }
                secureRandom.nextBytes(bArr);
                secureRandom.nextBytes(bArr2);
                iESParameterSpec = new IESParameterSpec(bArr, bArr2, NotificationCompat.FLAG_HIGH_PRIORITY);
            } else if (algorithmParameterSpec instanceof IESParameterSpec) {
                AlgorithmParameterSpec iESParameterSpec2 = algorithmParameterSpec;
            } else {
                throw new InvalidAlgorithmParameterException("must be passed IES parameters");
            }
            IESKey iESKey = (IESKey) key;
            if (iESKey.a() instanceof ECPublicKey) {
                a = ECUtil.a(iESKey.a());
                a2 = ECUtil.a(iESKey.b());
            } else {
                a = DHUtil.a(iESKey.a());
                a2 = DHUtil.a(iESKey.b());
            }
            this.e = iESParameterSpec2;
            CipherParameters iESParameters = new IESParameters(this.e.a(), this.e.b(), this.e.c());
            this.b = i;
            this.c.reset();
            switch (i) {
                case 1:
                case 3:
                    this.a.a(true, a2, a, iESParameters);
                    return;
                case 2:
                case 4:
                    this.a.a(false, a2, a, iESParameters);
                    return;
                default:
                    System.out.println("eeek!");
                    return;
            }
        }
        throw new InvalidKeyException("must be passed IES key");
    }

    protected void engineInit(int i, Key key, AlgorithmParameters algorithmParameters, SecureRandom secureRandom) {
        AlgorithmParameterSpec parameterSpec;
        if (algorithmParameters != null) {
            int i2 = 0;
            while (i2 != this.f.length) {
                try {
                    parameterSpec = algorithmParameters.getParameterSpec(this.f[i2]);
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
        this.d = algorithmParameters;
        engineInit(i, key, parameterSpec, secureRandom);
    }

    protected void engineInit(int i, Key key, SecureRandom secureRandom) {
        if (i == 1 || i == 3) {
            try {
                engineInit(i, key, (AlgorithmParameterSpec) null, secureRandom);
                return;
            } catch (InvalidAlgorithmParameterException e) {
            }
        }
        throw new IllegalArgumentException("can't handle null parameter spec in IES");
    }

    protected byte[] engineUpdate(byte[] bArr, int i, int i2) {
        this.c.write(bArr, i, i2);
        return null;
    }

    protected int engineUpdate(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
        this.c.write(bArr, i, i2);
        return 0;
    }

    protected byte[] engineDoFinal(byte[] bArr, int i, int i2) {
        if (i2 != 0) {
            this.c.write(bArr, i, i2);
        }
        try {
            byte[] toByteArray = this.c.toByteArray();
            this.c.reset();
            return this.a.a(toByteArray, 0, toByteArray.length);
        } catch (InvalidCipherTextException e) {
            throw new BadPaddingException(e.getMessage());
        }
    }

    protected int engineDoFinal(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
        if (i2 != 0) {
            this.c.write(bArr, i, i2);
        }
        try {
            byte[] toByteArray = this.c.toByteArray();
            this.c.reset();
            Object a = this.a.a(toByteArray, 0, toByteArray.length);
            System.arraycopy(a, 0, bArr2, i3, a.length);
            return a.length;
        } catch (InvalidCipherTextException e) {
            throw new BadPaddingException(e.getMessage());
        }
    }
}
