package org.spongycastle.asn1.x9;

import java.math.BigInteger;
import org.spongycastle.math.ec.ECCurve;
import org.spongycastle.math.ec.ECFieldElement;

public class X9IntegerConverter {
    public int a(ECCurve eCCurve) {
        return (eCCurve.a() + 7) / 8;
    }

    public int a(ECFieldElement eCFieldElement) {
        return (eCFieldElement.b() + 7) / 8;
    }

    public byte[] a(BigInteger bigInteger, int i) {
        Object toByteArray = bigInteger.toByteArray();
        if (i < toByteArray.length) {
            Object obj = new byte[i];
            System.arraycopy(toByteArray, toByteArray.length - obj.length, obj, 0, obj.length);
            return obj;
        } else if (i <= toByteArray.length) {
            return toByteArray;
        } else {
            byte[] bArr = new byte[i];
            System.arraycopy(toByteArray, 0, bArr, bArr.length - toByteArray.length, toByteArray.length);
            return bArr;
        }
    }
}
