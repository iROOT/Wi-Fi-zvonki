package org.spongycastle.pqc.jcajce.provider.rainbow;

import java.io.IOException;
import java.security.PrivateKey;
import java.util.Arrays;
import org.spongycastle.asn1.DERNull;
import org.spongycastle.asn1.pkcs.PrivateKeyInfo;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.pqc.asn1.PQCObjectIdentifiers;
import org.spongycastle.pqc.asn1.RainbowPrivateKey;
import org.spongycastle.pqc.crypto.rainbow.Layer;
import org.spongycastle.pqc.crypto.rainbow.RainbowPrivateKeyParameters;
import org.spongycastle.pqc.crypto.rainbow.util.RainbowUtil;
import org.spongycastle.pqc.jcajce.spec.RainbowPrivateKeySpec;

public class BCRainbowPrivateKey implements PrivateKey {
    private short[][] a;
    private short[] b;
    private short[][] c;
    private short[] d;
    private Layer[] e;
    private int[] f;

    public BCRainbowPrivateKey(short[][] sArr, short[] sArr2, short[][] sArr3, short[] sArr4, int[] iArr, Layer[] layerArr) {
        this.a = sArr;
        this.b = sArr2;
        this.c = sArr3;
        this.d = sArr4;
        this.f = iArr;
        this.e = layerArr;
    }

    public BCRainbowPrivateKey(RainbowPrivateKeySpec rainbowPrivateKeySpec) {
        this(rainbowPrivateKeySpec.b(), rainbowPrivateKeySpec.a(), rainbowPrivateKeySpec.d(), rainbowPrivateKeySpec.c(), rainbowPrivateKeySpec.f(), rainbowPrivateKeySpec.e());
    }

    public BCRainbowPrivateKey(RainbowPrivateKeyParameters rainbowPrivateKeyParameters) {
        this(rainbowPrivateKeyParameters.d(), rainbowPrivateKeyParameters.c(), rainbowPrivateKeyParameters.f(), rainbowPrivateKeyParameters.e(), rainbowPrivateKeyParameters.h(), rainbowPrivateKeyParameters.g());
    }

    public short[][] a() {
        return this.a;
    }

    public short[] b() {
        return this.b;
    }

    public short[] c() {
        return this.d;
    }

    public short[][] d() {
        return this.c;
    }

    public Layer[] e() {
        return this.e;
    }

    public int[] f() {
        return this.f;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        boolean z2 = false;
        if (obj != null && (obj instanceof BCRainbowPrivateKey)) {
            boolean z3;
            BCRainbowPrivateKey bCRainbowPrivateKey = (BCRainbowPrivateKey) obj;
            if ((RainbowUtil.a(this.a, bCRainbowPrivateKey.a())) && RainbowUtil.a(this.c, bCRainbowPrivateKey.d())) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (z3 && RainbowUtil.a(this.b, bCRainbowPrivateKey.b())) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (z3 && RainbowUtil.a(this.d, bCRainbowPrivateKey.c())) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (!(z3 && Arrays.equals(this.f, bCRainbowPrivateKey.f()))) {
                z = false;
            }
            if (this.e.length == bCRainbowPrivateKey.e().length) {
                z2 = z;
                for (int length = this.e.length - 1; length >= 0; length--) {
                    z2 &= this.e[length].equals(bCRainbowPrivateKey.e()[length]);
                }
            }
        }
        return z2;
    }

    public int hashCode() {
        int a = org.spongycastle.util.Arrays.a(this.f) + (((((((((this.e.length * 37) + org.spongycastle.util.Arrays.a(this.a)) * 37) + org.spongycastle.util.Arrays.a(this.b)) * 37) + org.spongycastle.util.Arrays.a(this.c)) * 37) + org.spongycastle.util.Arrays.a(this.d)) * 37);
        for (int length = this.e.length - 1; length >= 0; length--) {
            a = (a * 37) + this.e[length].hashCode();
        }
        return a;
    }

    public final String getAlgorithm() {
        return "Rainbow";
    }

    public byte[] getEncoded() {
        try {
            try {
                return new PrivateKeyInfo(new AlgorithmIdentifier(PQCObjectIdentifiers.a, DERNull.a), new RainbowPrivateKey(this.a, this.b, this.c, this.d, this.f, this.e)).b();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public String getFormat() {
        return "PKCS#8";
    }
}
