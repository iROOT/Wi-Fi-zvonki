package org.spongycastle.pqc.jcajce.provider.rainbow;

import java.security.PublicKey;
import org.spongycastle.asn1.DERNull;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.pqc.asn1.PQCObjectIdentifiers;
import org.spongycastle.pqc.asn1.RainbowPublicKey;
import org.spongycastle.pqc.crypto.rainbow.RainbowPublicKeyParameters;
import org.spongycastle.pqc.crypto.rainbow.util.RainbowUtil;
import org.spongycastle.pqc.jcajce.provider.util.KeyUtil;
import org.spongycastle.pqc.jcajce.spec.RainbowPublicKeySpec;
import org.spongycastle.util.Arrays;

public class BCRainbowPublicKey implements PublicKey {
    private short[][] a;
    private short[][] b;
    private short[] c;
    private int d;

    public BCRainbowPublicKey(int i, short[][] sArr, short[][] sArr2, short[] sArr3) {
        this.d = i;
        this.a = sArr;
        this.b = sArr2;
        this.c = sArr3;
    }

    public BCRainbowPublicKey(RainbowPublicKeySpec rainbowPublicKeySpec) {
        this(rainbowPublicKeySpec.a(), rainbowPublicKeySpec.b(), rainbowPublicKeySpec.c(), rainbowPublicKeySpec.d());
    }

    public BCRainbowPublicKey(RainbowPublicKeyParameters rainbowPublicKeyParameters) {
        this(rainbowPublicKeyParameters.b(), rainbowPublicKeyParameters.c(), rainbowPublicKeyParameters.d(), rainbowPublicKeyParameters.e());
    }

    public int a() {
        return this.d;
    }

    public short[][] b() {
        return this.a;
    }

    public short[][] c() {
        short[][] sArr = new short[this.b.length][];
        for (int i = 0; i != this.b.length; i++) {
            sArr[i] = Arrays.b(this.b[i]);
        }
        return sArr;
    }

    public short[] d() {
        return Arrays.b(this.c);
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof BCRainbowPublicKey)) {
            return false;
        }
        BCRainbowPublicKey bCRainbowPublicKey = (BCRainbowPublicKey) obj;
        if (this.d == bCRainbowPublicKey.a() && RainbowUtil.a(this.a, bCRainbowPublicKey.b()) && RainbowUtil.a(this.b, bCRainbowPublicKey.c()) && RainbowUtil.a(this.c, bCRainbowPublicKey.d())) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((this.d * 37) + Arrays.a(this.a)) * 37) + Arrays.a(this.b)) * 37) + Arrays.a(this.c);
    }

    public final String getAlgorithm() {
        return "Rainbow";
    }

    public String getFormat() {
        return "X.509";
    }

    public byte[] getEncoded() {
        return KeyUtil.a(new AlgorithmIdentifier(PQCObjectIdentifiers.a, DERNull.a), new RainbowPublicKey(this.d, this.a, this.b, this.c));
    }
}
