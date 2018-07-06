package org.spongycastle.jcajce.provider.symmetric.util;

import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.PBEParametersGenerator;
import org.spongycastle.crypto.digests.GOST3411Digest;
import org.spongycastle.crypto.digests.MD2Digest;
import org.spongycastle.crypto.digests.MD5Digest;
import org.spongycastle.crypto.digests.RIPEMD160Digest;
import org.spongycastle.crypto.digests.SHA1Digest;
import org.spongycastle.crypto.digests.SHA256Digest;
import org.spongycastle.crypto.digests.TigerDigest;
import org.spongycastle.crypto.generators.OpenSSLPBEParametersGenerator;
import org.spongycastle.crypto.generators.PKCS12ParametersGenerator;
import org.spongycastle.crypto.generators.PKCS5S1ParametersGenerator;
import org.spongycastle.crypto.generators.PKCS5S2ParametersGenerator;
import org.spongycastle.crypto.params.DESParameters;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.params.ParametersWithIV;

public interface PBE {

    public static class Util {
        private static PBEParametersGenerator a(int i, int i2) {
            if (i == 0 || i == 4) {
                switch (i2) {
                    case 0:
                        return new PKCS5S1ParametersGenerator(new MD5Digest());
                    case 1:
                        return new PKCS5S1ParametersGenerator(new SHA1Digest());
                    case 5:
                        return new PKCS5S1ParametersGenerator(new MD2Digest());
                    default:
                        throw new IllegalStateException("PKCS5 scheme 1 only supports MD2, MD5 and SHA1.");
                }
            } else if (i == 1 || i == 5) {
                switch (i2) {
                    case 0:
                        return new PKCS5S2ParametersGenerator(new MD5Digest());
                    case 1:
                        return new PKCS5S2ParametersGenerator(new SHA1Digest());
                    case 2:
                        return new PKCS5S2ParametersGenerator(new RIPEMD160Digest());
                    case 3:
                        return new PKCS5S2ParametersGenerator(new TigerDigest());
                    case 4:
                        return new PKCS5S2ParametersGenerator(new SHA256Digest());
                    case 5:
                        return new PKCS5S2ParametersGenerator(new MD2Digest());
                    case 6:
                        return new PKCS5S2ParametersGenerator(new GOST3411Digest());
                    default:
                        throw new IllegalStateException("unknown digest scheme for PBE PKCS5S2 encryption.");
                }
            } else if (i != 2) {
                return new OpenSSLPBEParametersGenerator();
            } else {
                switch (i2) {
                    case 0:
                        return new PKCS12ParametersGenerator(new MD5Digest());
                    case 1:
                        return new PKCS12ParametersGenerator(new SHA1Digest());
                    case 2:
                        return new PKCS12ParametersGenerator(new RIPEMD160Digest());
                    case 3:
                        return new PKCS12ParametersGenerator(new TigerDigest());
                    case 4:
                        return new PKCS12ParametersGenerator(new SHA256Digest());
                    case 5:
                        return new PKCS12ParametersGenerator(new MD2Digest());
                    case 6:
                        return new PKCS12ParametersGenerator(new GOST3411Digest());
                    default:
                        throw new IllegalStateException("unknown digest scheme for PBE encryption.");
                }
            }
        }

        public static CipherParameters a(BCPBEKey bCPBEKey, AlgorithmParameterSpec algorithmParameterSpec, String str) {
            if (algorithmParameterSpec == null || !(algorithmParameterSpec instanceof PBEParameterSpec)) {
                throw new IllegalArgumentException("Need a PBEParameter spec with a PBE key.");
            }
            byte[] bArr;
            CipherParameters a;
            PBEParameterSpec pBEParameterSpec = (PBEParameterSpec) algorithmParameterSpec;
            PBEParametersGenerator a2 = a(bCPBEKey.a(), bCPBEKey.b());
            byte[] encoded = bCPBEKey.getEncoded();
            if (bCPBEKey.g()) {
                bArr = new byte[2];
            } else {
                bArr = encoded;
            }
            a2.a(bArr, pBEParameterSpec.getSalt(), pBEParameterSpec.getIterationCount());
            if (bCPBEKey.d() != 0) {
                a = a2.a(bCPBEKey.c(), bCPBEKey.d());
            } else {
                a = a2.a(bCPBEKey.c());
            }
            if (str.startsWith("DES")) {
                if (a instanceof ParametersWithIV) {
                    DESParameters.a(((KeyParameter) ((ParametersWithIV) a).b()).a());
                } else {
                    DESParameters.a(((KeyParameter) a).a());
                }
            }
            for (int i = 0; i != bArr.length; i++) {
                bArr[i] = (byte) 0;
            }
            return a;
        }

        public static CipherParameters a(BCPBEKey bCPBEKey, AlgorithmParameterSpec algorithmParameterSpec) {
            if (algorithmParameterSpec == null || !(algorithmParameterSpec instanceof PBEParameterSpec)) {
                throw new IllegalArgumentException("Need a PBEParameter spec with a PBE key.");
            }
            PBEParameterSpec pBEParameterSpec = (PBEParameterSpec) algorithmParameterSpec;
            PBEParametersGenerator a = a(bCPBEKey.a(), bCPBEKey.b());
            byte[] encoded = bCPBEKey.getEncoded();
            if (bCPBEKey.g()) {
                encoded = new byte[2];
            }
            a.a(encoded, pBEParameterSpec.getSalt(), pBEParameterSpec.getIterationCount());
            CipherParameters b = a.b(bCPBEKey.c());
            for (int i = 0; i != encoded.length; i++) {
                encoded[i] = (byte) 0;
            }
            return b;
        }

        public static CipherParameters a(PBEKeySpec pBEKeySpec, int i, int i2, int i3, int i4) {
            CipherParameters a;
            PBEParametersGenerator a2 = a(i, i2);
            byte[] a3 = a(i, pBEKeySpec);
            a2.a(a3, pBEKeySpec.getSalt(), pBEKeySpec.getIterationCount());
            if (i4 != 0) {
                a = a2.a(i3, i4);
            } else {
                a = a2.a(i3);
            }
            for (int i5 = 0; i5 != a3.length; i5++) {
                a3[i5] = (byte) 0;
            }
            return a;
        }

        public static CipherParameters a(PBEKeySpec pBEKeySpec, int i, int i2, int i3) {
            PBEParametersGenerator a = a(i, i2);
            byte[] a2 = a(i, pBEKeySpec);
            a.a(a2, pBEKeySpec.getSalt(), pBEKeySpec.getIterationCount());
            CipherParameters b = a.b(i3);
            for (int i4 = 0; i4 != a2.length; i4++) {
                a2[i4] = (byte) 0;
            }
            return b;
        }

        private static byte[] a(int i, PBEKeySpec pBEKeySpec) {
            if (i == 2) {
                return PBEParametersGenerator.c(pBEKeySpec.getPassword());
            }
            if (i == 5 || i == 4) {
                return PBEParametersGenerator.b(pBEKeySpec.getPassword());
            }
            return PBEParametersGenerator.a(pBEKeySpec.getPassword());
        }
    }
}
