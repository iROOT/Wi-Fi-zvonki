package org.spongycastle.pqc.jcajce.provider.mceliece;

import java.io.ByteArrayOutputStream;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.BadPaddingException;
import org.spongycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.spongycastle.asn1.x509.X509ObjectIdentifiers;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.digests.SHA1Digest;
import org.spongycastle.crypto.digests.SHA224Digest;
import org.spongycastle.crypto.digests.SHA256Digest;
import org.spongycastle.crypto.digests.SHA384Digest;
import org.spongycastle.crypto.digests.SHA512Digest;
import org.spongycastle.crypto.params.ParametersWithRandom;
import org.spongycastle.pqc.crypto.mceliece.McElieceCCA2KeyParameters;
import org.spongycastle.pqc.crypto.mceliece.McElieceKobaraImaiCipher;
import org.spongycastle.pqc.jcajce.provider.util.AsymmetricHybridCipher;

public class McElieceKobaraImaiCipherSpi extends AsymmetricHybridCipher implements PKCSObjectIdentifiers, X509ObjectIdentifiers {
    private Digest bD;
    private McElieceKobaraImaiCipher bE;
    private ByteArrayOutputStream bF;

    public static class McElieceKobaraImai224 extends McElieceKobaraImaiCipherSpi {
        public McElieceKobaraImai224() {
            super(new SHA224Digest(), new McElieceKobaraImaiCipher());
        }
    }

    public static class McElieceKobaraImai256 extends McElieceKobaraImaiCipherSpi {
        public McElieceKobaraImai256() {
            super(new SHA256Digest(), new McElieceKobaraImaiCipher());
        }
    }

    public static class McElieceKobaraImai384 extends McElieceKobaraImaiCipherSpi {
        public McElieceKobaraImai384() {
            super(new SHA384Digest(), new McElieceKobaraImaiCipher());
        }
    }

    public static class McElieceKobaraImai512 extends McElieceKobaraImaiCipherSpi {
        public McElieceKobaraImai512() {
            super(new SHA512Digest(), new McElieceKobaraImaiCipher());
        }
    }

    public static class McElieceKobaraImai extends McElieceKobaraImaiCipherSpi {
        public McElieceKobaraImai() {
            super(new SHA1Digest(), new McElieceKobaraImaiCipher());
        }
    }

    public McElieceKobaraImaiCipherSpi() {
        this.bF = new ByteArrayOutputStream();
        this.bF = new ByteArrayOutputStream();
    }

    protected McElieceKobaraImaiCipherSpi(Digest digest, McElieceKobaraImaiCipher mcElieceKobaraImaiCipher) {
        this.bF = new ByteArrayOutputStream();
        this.bD = digest;
        this.bE = mcElieceKobaraImaiCipher;
        this.bF = new ByteArrayOutputStream();
    }

    public byte[] a(byte[] bArr, int i, int i2) {
        this.bF.write(bArr, i, i2);
        return new byte[0];
    }

    public byte[] b(byte[] bArr, int i, int i2) {
        a(bArr, i, i2);
        if (this.w_ == 1) {
            try {
                return this.bE.a(c());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            if (this.w_ == 2) {
                byte[] toByteArray = this.bF.toByteArray();
                this.bF.reset();
                try {
                    return a(this.bE.b(toByteArray));
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            return null;
        }
    }

    protected int a(int i) {
        return 0;
    }

    protected int b(int i) {
        return 0;
    }

    protected void a(Key key, AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) {
        this.bF.reset();
        CipherParameters parametersWithRandom = new ParametersWithRandom(McElieceCCA2KeysToParams.a((PublicKey) key), secureRandom);
        this.bD.c();
        this.bE.a(true, parametersWithRandom);
    }

    protected void a(Key key, AlgorithmParameterSpec algorithmParameterSpec) {
        this.bF.reset();
        CipherParameters a = McElieceCCA2KeysToParams.a((PrivateKey) key);
        this.bD.c();
        this.bE.a(false, a);
    }

    public int a(Key key) {
        if (key instanceof PublicKey) {
            return this.bE.a((McElieceCCA2KeyParameters) McElieceCCA2KeysToParams.a((PublicKey) key));
        } else if (key instanceof PrivateKey) {
            return this.bE.a((McElieceCCA2KeyParameters) McElieceCCA2KeysToParams.a((PrivateKey) key));
        } else {
            throw new InvalidKeyException();
        }
    }

    private byte[] c() {
        this.bF.write(1);
        byte[] toByteArray = this.bF.toByteArray();
        this.bF.reset();
        return toByteArray;
    }

    private byte[] a(byte[] bArr) {
        int length = bArr.length - 1;
        while (length >= 0 && bArr[length] == (byte) 0) {
            length--;
        }
        if (bArr[length] != (byte) 1) {
            throw new BadPaddingException("invalid ciphertext");
        }
        Object obj = new byte[length];
        System.arraycopy(bArr, 0, obj, 0, length);
        return obj;
    }
}
