package org.spongycastle.crypto.params;

import java.math.BigInteger;
import org.spongycastle.math.ec.ECConstants;
import org.spongycastle.math.ec.ECCurve;
import org.spongycastle.math.ec.ECPoint;
import org.spongycastle.util.Arrays;

public class ECDomainParameters implements ECConstants {
    private ECCurve a;
    private byte[] b;
    private ECPoint h;
    private BigInteger i;
    private BigInteger j;

    public ECDomainParameters(ECCurve eCCurve, ECPoint eCPoint, BigInteger bigInteger) {
        this(eCCurve, eCPoint, bigInteger, d, null);
    }

    public ECDomainParameters(ECCurve eCCurve, ECPoint eCPoint, BigInteger bigInteger, BigInteger bigInteger2) {
        this(eCCurve, eCPoint, bigInteger, bigInteger2, null);
    }

    public ECDomainParameters(ECCurve eCCurve, ECPoint eCPoint, BigInteger bigInteger, BigInteger bigInteger2, byte[] bArr) {
        this.a = eCCurve;
        this.h = eCPoint.k();
        this.i = bigInteger;
        this.j = bigInteger2;
        this.b = bArr;
    }

    public ECCurve a() {
        return this.a;
    }

    public ECPoint b() {
        return this.h;
    }

    public BigInteger c() {
        return this.i;
    }

    public BigInteger d() {
        return this.j;
    }

    public byte[] e() {
        return Arrays.b(this.b);
    }
}
