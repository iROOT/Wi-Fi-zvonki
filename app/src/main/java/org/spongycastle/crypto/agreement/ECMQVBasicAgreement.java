package org.spongycastle.crypto.agreement;

import java.math.BigInteger;
import org.spongycastle.crypto.BasicAgreement;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.params.ECDomainParameters;
import org.spongycastle.crypto.params.ECPrivateKeyParameters;
import org.spongycastle.crypto.params.ECPublicKeyParameters;
import org.spongycastle.crypto.params.MQVPrivateParameters;
import org.spongycastle.crypto.params.MQVPublicParameters;
import org.spongycastle.math.ec.ECAlgorithms;
import org.spongycastle.math.ec.ECConstants;
import org.spongycastle.math.ec.ECCurve;
import org.spongycastle.math.ec.ECPoint;

public class ECMQVBasicAgreement implements BasicAgreement {
    MQVPrivateParameters a;

    public void a(CipherParameters cipherParameters) {
        this.a = (MQVPrivateParameters) cipherParameters;
    }

    public int a() {
        return (this.a.a().b().a().a() + 7) / 8;
    }

    public BigInteger b(CipherParameters cipherParameters) {
        MQVPublicParameters mQVPublicParameters = (MQVPublicParameters) cipherParameters;
        ECPrivateKeyParameters a = this.a.a();
        ECPoint k = a(a.b(), a, this.a.b(), this.a.c(), mQVPublicParameters.a(), mQVPublicParameters.b()).k();
        if (!k.l()) {
            return k.c().a();
        }
        throw new IllegalStateException("Infinity is not a valid agreement value for MQV");
    }

    private ECPoint a(ECDomainParameters eCDomainParameters, ECPrivateKeyParameters eCPrivateKeyParameters, ECPrivateKeyParameters eCPrivateKeyParameters2, ECPublicKeyParameters eCPublicKeyParameters, ECPublicKeyParameters eCPublicKeyParameters2, ECPublicKeyParameters eCPublicKeyParameters3) {
        BigInteger c = eCDomainParameters.c();
        int bitLength = (c.bitLength() + 1) / 2;
        BigInteger shiftLeft = ECConstants.d.shiftLeft(bitLength);
        ECCurve a = eCDomainParameters.a();
        ECPoint[] eCPointArr = new ECPoint[3];
        eCPointArr[0] = ECAlgorithms.a(a, eCPublicKeyParameters == null ? eCDomainParameters.b().a(eCPrivateKeyParameters2.c()) : eCPublicKeyParameters.c());
        eCPointArr[1] = ECAlgorithms.a(a, eCPublicKeyParameters2.c());
        eCPointArr[2] = ECAlgorithms.a(a, eCPublicKeyParameters3.c());
        a.a(eCPointArr);
        ECPoint eCPoint = eCPointArr[0];
        ECPoint eCPoint2 = eCPointArr[1];
        ECPoint eCPoint3 = eCPointArr[2];
        BigInteger mod = eCPrivateKeyParameters.c().multiply(eCPoint.c().a().mod(shiftLeft).setBit(bitLength)).add(eCPrivateKeyParameters2.c()).mod(c);
        BigInteger bit = eCPoint3.c().a().mod(shiftLeft).setBit(bitLength);
        mod = eCDomainParameters.d().multiply(mod).mod(c);
        return ECAlgorithms.a(eCPoint2, bit.multiply(mod).mod(c), eCPoint3, mod);
    }
}
