package org.spongycastle.jcajce.provider.asymmetric.ec;

import android.support.v4.app.NotificationCompat;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Hashtable;
import javax.crypto.SecretKey;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.SecretKeySpec;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.nist.NISTObjectIdentifiers;
import org.spongycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.spongycastle.asn1.x9.X9IntegerConverter;
import org.spongycastle.crypto.BasicAgreement;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DerivationFunction;
import org.spongycastle.crypto.DerivationParameters;
import org.spongycastle.crypto.agreement.ECDHBasicAgreement;
import org.spongycastle.crypto.agreement.ECDHCBasicAgreement;
import org.spongycastle.crypto.agreement.ECMQVBasicAgreement;
import org.spongycastle.crypto.agreement.kdf.DHKDFParameters;
import org.spongycastle.crypto.agreement.kdf.ECDHKEKGenerator;
import org.spongycastle.crypto.digests.SHA1Digest;
import org.spongycastle.crypto.params.ECDomainParameters;
import org.spongycastle.crypto.params.ECPrivateKeyParameters;
import org.spongycastle.crypto.params.ECPublicKeyParameters;
import org.spongycastle.crypto.params.MQVPrivateParameters;
import org.spongycastle.crypto.params.MQVPublicParameters;
import org.spongycastle.jcajce.provider.asymmetric.util.ECUtil;
import org.spongycastle.jce.interfaces.ECPrivateKey;
import org.spongycastle.jce.interfaces.ECPublicKey;
import org.spongycastle.jce.interfaces.MQVPrivateKey;
import org.spongycastle.jce.interfaces.MQVPublicKey;
import org.spongycastle.util.Integers;

public class KeyAgreementSpi extends javax.crypto.KeyAgreementSpi {
    private static final X9IntegerConverter a = new X9IntegerConverter();
    private static final Hashtable b = new Hashtable();
    private String c;
    private BigInteger d;
    private ECDomainParameters e;
    private BasicAgreement f;
    private DerivationFunction g;

    public static class DH extends KeyAgreementSpi {
        public DH() {
            super("ECDH", new ECDHBasicAgreement(), null);
        }
    }

    public static class DHC extends KeyAgreementSpi {
        public DHC() {
            super("ECDHC", new ECDHCBasicAgreement(), null);
        }
    }

    public static class DHwithSHA1KDF extends KeyAgreementSpi {
        public DHwithSHA1KDF() {
            super("ECDHwithSHA1KDF", new ECDHBasicAgreement(), new ECDHKEKGenerator(new SHA1Digest()));
        }
    }

    public static class MQV extends KeyAgreementSpi {
        public MQV() {
            super("ECMQV", new ECMQVBasicAgreement(), null);
        }
    }

    public static class MQVwithSHA1KDF extends KeyAgreementSpi {
        public MQVwithSHA1KDF() {
            super("ECMQVwithSHA1KDF", new ECMQVBasicAgreement(), new ECDHKEKGenerator(new SHA1Digest()));
        }
    }

    static {
        Integer a = Integers.a(NotificationCompat.FLAG_HIGH_PRIORITY);
        Integer a2 = Integers.a(192);
        Integer a3 = Integers.a(256);
        b.put(NISTObjectIdentifiers.k.d(), a);
        b.put(NISTObjectIdentifiers.r.d(), a2);
        b.put(NISTObjectIdentifiers.y.d(), a3);
        b.put(NISTObjectIdentifiers.n.d(), a);
        b.put(NISTObjectIdentifiers.u.d(), a2);
        b.put(NISTObjectIdentifiers.B.d(), a3);
        b.put(PKCSObjectIdentifiers.bB.d(), a2);
    }

    private byte[] a(BigInteger bigInteger) {
        return a.a(bigInteger, a.a(this.e.b().c()));
    }

    protected KeyAgreementSpi(String str, BasicAgreement basicAgreement, DerivationFunction derivationFunction) {
        this.c = str;
        this.f = basicAgreement;
        this.g = derivationFunction;
    }

    protected Key engineDoPhase(Key key, boolean z) {
        if (this.e == null) {
            throw new IllegalStateException(this.c + " not initialised.");
        } else if (z) {
            CipherParameters mQVPublicParameters;
            if (this.f instanceof ECMQVBasicAgreement) {
                if (key instanceof MQVPublicKey) {
                    MQVPublicKey mQVPublicKey = (MQVPublicKey) key;
                    mQVPublicParameters = new MQVPublicParameters((ECPublicKeyParameters) ECUtil.a(mQVPublicKey.a()), (ECPublicKeyParameters) ECUtil.a(mQVPublicKey.b()));
                } else {
                    throw new InvalidKeyException(this.c + " key agreement requires " + a(MQVPublicKey.class) + " for doPhase");
                }
            } else if (key instanceof PublicKey) {
                mQVPublicParameters = ECUtil.a((PublicKey) key);
            } else {
                throw new InvalidKeyException(this.c + " key agreement requires " + a(ECPublicKey.class) + " for doPhase");
            }
            this.d = this.f.b(mQVPublicParameters);
            return null;
        } else {
            throw new IllegalStateException(this.c + " can only be between two parties.");
        }
    }

    protected byte[] engineGenerateSecret() {
        if (this.g == null) {
            return a(this.d);
        }
        throw new UnsupportedOperationException("KDF can only be used when algorithm is known");
    }

    protected int engineGenerateSecret(byte[] bArr, int i) {
        Object engineGenerateSecret = engineGenerateSecret();
        if (bArr.length - i < engineGenerateSecret.length) {
            throw new ShortBufferException(this.c + " key agreement: need " + engineGenerateSecret.length + " bytes");
        }
        System.arraycopy(engineGenerateSecret, 0, bArr, i, engineGenerateSecret.length);
        return engineGenerateSecret.length;
    }

    protected SecretKey engineGenerateSecret(String str) {
        byte[] bArr;
        byte[] a = a(this.d);
        if (this.g == null) {
            bArr = a;
        } else if (b.containsKey(str)) {
            int intValue = ((Integer) b.get(str)).intValue();
            DerivationParameters dHKDFParameters = new DHKDFParameters(new ASN1ObjectIdentifier(str), intValue, a);
            bArr = new byte[(intValue / 8)];
            this.g.a(dHKDFParameters);
            this.g.a(bArr, 0, bArr.length);
        } else {
            throw new NoSuchAlgorithmException("unknown algorithm encountered: " + str);
        }
        return new SecretKeySpec(bArr, str);
    }

    protected void engineInit(Key key, AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) {
        a(key);
    }

    protected void engineInit(Key key, SecureRandom secureRandom) {
        a(key);
    }

    private void a(Key key) {
        ECPrivateKeyParameters eCPrivateKeyParameters;
        if (this.f instanceof ECMQVBasicAgreement) {
            if (key instanceof MQVPrivateKey) {
                MQVPrivateKey mQVPrivateKey = (MQVPrivateKey) key;
                eCPrivateKeyParameters = (ECPrivateKeyParameters) ECUtil.a(mQVPrivateKey.a());
                ECPrivateKeyParameters eCPrivateKeyParameters2 = (ECPrivateKeyParameters) ECUtil.a(mQVPrivateKey.b());
                ECPublicKeyParameters eCPublicKeyParameters = null;
                if (mQVPrivateKey.c() != null) {
                    eCPublicKeyParameters = (ECPublicKeyParameters) ECUtil.a(mQVPrivateKey.c());
                }
                CipherParameters mQVPrivateParameters = new MQVPrivateParameters(eCPrivateKeyParameters, eCPrivateKeyParameters2, eCPublicKeyParameters);
                this.e = eCPrivateKeyParameters.b();
                this.f.a(mQVPrivateParameters);
                return;
            }
            throw new InvalidKeyException(this.c + " key agreement requires " + a(MQVPrivateKey.class) + " for initialisation");
        } else if (key instanceof PrivateKey) {
            eCPrivateKeyParameters = (ECPrivateKeyParameters) ECUtil.a((PrivateKey) key);
            this.e = eCPrivateKeyParameters.b();
            this.f.a(eCPrivateKeyParameters);
        } else {
            throw new InvalidKeyException(this.c + " key agreement requires " + a(ECPrivateKey.class) + " for initialisation");
        }
    }

    private static String a(Class cls) {
        String name = cls.getName();
        return name.substring(name.lastIndexOf(46) + 1);
    }
}
