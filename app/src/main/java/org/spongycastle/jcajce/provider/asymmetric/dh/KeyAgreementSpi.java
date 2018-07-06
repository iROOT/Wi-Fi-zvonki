package org.spongycastle.jcajce.provider.asymmetric.dh;

import android.support.v4.app.NotificationCompat;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Hashtable;
import javax.crypto.SecretKey;
import javax.crypto.ShortBufferException;
import javax.crypto.interfaces.DHPrivateKey;
import javax.crypto.interfaces.DHPublicKey;
import javax.crypto.spec.DHParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.spongycastle.crypto.params.DESParameters;
import org.spongycastle.util.Integers;
import org.spongycastle.util.Strings;

public class KeyAgreementSpi extends javax.crypto.KeyAgreementSpi {
    private static final Hashtable e = new Hashtable();
    private BigInteger a;
    private BigInteger b;
    private BigInteger c;
    private BigInteger d;

    static {
        Integer a = Integers.a(64);
        Integer a2 = Integers.a(192);
        Integer a3 = Integers.a(NotificationCompat.FLAG_HIGH_PRIORITY);
        Integer a4 = Integers.a(256);
        e.put("DES", a);
        e.put("DESEDE", a2);
        e.put("BLOWFISH", a3);
        e.put("AES", a4);
    }

    private byte[] a(BigInteger bigInteger) {
        int bitLength = (this.b.bitLength() + 7) / 8;
        Object toByteArray = bigInteger.toByteArray();
        if (toByteArray.length == bitLength) {
            return toByteArray;
        }
        if (toByteArray[0] == (byte) 0 && toByteArray.length == bitLength + 1) {
            Object obj = new byte[(toByteArray.length - 1)];
            System.arraycopy(toByteArray, 1, obj, 0, obj.length);
            return obj;
        }
        obj = new byte[bitLength];
        System.arraycopy(toByteArray, 0, obj, obj.length - toByteArray.length, toByteArray.length);
        return obj;
    }

    protected Key engineDoPhase(Key key, boolean z) {
        if (this.a == null) {
            throw new IllegalStateException("Diffie-Hellman not initialised.");
        } else if (key instanceof DHPublicKey) {
            DHPublicKey dHPublicKey = (DHPublicKey) key;
            if (!dHPublicKey.getParams().getG().equals(this.c) || !dHPublicKey.getParams().getP().equals(this.b)) {
                throw new InvalidKeyException("DHPublicKey not for this KeyAgreement!");
            } else if (z) {
                this.d = ((DHPublicKey) key).getY().modPow(this.a, this.b);
                return null;
            } else {
                this.d = ((DHPublicKey) key).getY().modPow(this.a, this.b);
                return new BCDHPublicKey(this.d, dHPublicKey.getParams());
            }
        } else {
            throw new InvalidKeyException("DHKeyAgreement doPhase requires DHPublicKey");
        }
    }

    protected byte[] engineGenerateSecret() {
        if (this.a != null) {
            return a(this.d);
        }
        throw new IllegalStateException("Diffie-Hellman not initialised.");
    }

    protected int engineGenerateSecret(byte[] bArr, int i) {
        if (this.a == null) {
            throw new IllegalStateException("Diffie-Hellman not initialised.");
        }
        Object a = a(this.d);
        if (bArr.length - i < a.length) {
            throw new ShortBufferException("DHKeyAgreement - buffer too short");
        }
        System.arraycopy(a, 0, bArr, i, a.length);
        return a.length;
    }

    protected SecretKey engineGenerateSecret(String str) {
        if (this.a == null) {
            throw new IllegalStateException("Diffie-Hellman not initialised.");
        }
        String b = Strings.b(str);
        Object a = a(this.d);
        if (!e.containsKey(b)) {
            return new SecretKeySpec(a, str);
        }
        Object obj = new byte[(((Integer) e.get(b)).intValue() / 8)];
        System.arraycopy(a, 0, obj, 0, obj.length);
        if (b.startsWith("DES")) {
            DESParameters.a(obj);
        }
        return new SecretKeySpec(obj, str);
    }

    protected void engineInit(Key key, AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) {
        if (key instanceof DHPrivateKey) {
            DHPrivateKey dHPrivateKey = (DHPrivateKey) key;
            if (algorithmParameterSpec == null) {
                this.b = dHPrivateKey.getParams().getP();
                this.c = dHPrivateKey.getParams().getG();
            } else if (algorithmParameterSpec instanceof DHParameterSpec) {
                DHParameterSpec dHParameterSpec = (DHParameterSpec) algorithmParameterSpec;
                this.b = dHParameterSpec.getP();
                this.c = dHParameterSpec.getG();
            } else {
                throw new InvalidAlgorithmParameterException("DHKeyAgreement only accepts DHParameterSpec");
            }
            BigInteger x = dHPrivateKey.getX();
            this.d = x;
            this.a = x;
            return;
        }
        throw new InvalidKeyException("DHKeyAgreement requires DHPrivateKey for initialisation");
    }

    protected void engineInit(Key key, SecureRandom secureRandom) {
        if (key instanceof DHPrivateKey) {
            DHPrivateKey dHPrivateKey = (DHPrivateKey) key;
            this.b = dHPrivateKey.getParams().getP();
            this.c = dHPrivateKey.getParams().getG();
            BigInteger x = dHPrivateKey.getX();
            this.d = x;
            this.a = x;
            return;
        }
        throw new InvalidKeyException("DHKeyAgreement requires DHPrivateKey");
    }
}
