package org.spongycastle.asn1.cryptopro;

import java.math.BigInteger;
import java.util.Hashtable;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.crypto.params.ECDomainParameters;
import org.spongycastle.math.ec.ECCurve;
import org.spongycastle.math.ec.ECCurve.Fp;

public class ECGOST3410NamedCurves {
    static final Hashtable a = new Hashtable();
    static final Hashtable b = new Hashtable();
    static final Hashtable c = new Hashtable();

    static {
        BigInteger bigInteger = new BigInteger("115792089237316195423570985008687907853269984665640564039457584007913129639319");
        BigInteger bigInteger2 = new BigInteger("115792089237316195423570985008687907853073762908499243225378155805079068850323");
        ECCurve fp = new Fp(bigInteger, new BigInteger("115792089237316195423570985008687907853269984665640564039457584007913129639316"), new BigInteger("166"));
        b.put(CryptoProObjectIdentifiers.u, new ECDomainParameters(fp, fp.a(new BigInteger("1"), new BigInteger("64033881142927202683649881450433473985931760268884941288852745803908878638612")), bigInteger2));
        bigInteger = new BigInteger("115792089237316195423570985008687907853269984665640564039457584007913129639319");
        bigInteger2 = new BigInteger("115792089237316195423570985008687907853073762908499243225378155805079068850323");
        fp = new Fp(bigInteger, new BigInteger("115792089237316195423570985008687907853269984665640564039457584007913129639316"), new BigInteger("166"));
        b.put(CryptoProObjectIdentifiers.x, new ECDomainParameters(fp, fp.a(new BigInteger("1"), new BigInteger("64033881142927202683649881450433473985931760268884941288852745803908878638612")), bigInteger2));
        bigInteger = new BigInteger("57896044618658097711785492504343953926634992332820282019728792003956564823193");
        bigInteger2 = new BigInteger("57896044618658097711785492504343953927102133160255826820068844496087732066703");
        fp = new Fp(bigInteger, new BigInteger("57896044618658097711785492504343953926634992332820282019728792003956564823190"), new BigInteger("28091019353058090096996979000309560759124368558014865957655842872397301267595"));
        b.put(CryptoProObjectIdentifiers.v, new ECDomainParameters(fp, fp.a(new BigInteger("1"), new BigInteger("28792665814854611296992347458380284135028636778229113005756334730996303888124")), bigInteger2));
        bigInteger = new BigInteger("70390085352083305199547718019018437841079516630045180471284346843705633502619");
        bigInteger2 = new BigInteger("70390085352083305199547718019018437840920882647164081035322601458352298396601");
        fp = new Fp(bigInteger, new BigInteger("70390085352083305199547718019018437841079516630045180471284346843705633502616"), new BigInteger("32858"));
        b.put(CryptoProObjectIdentifiers.y, new ECDomainParameters(fp, fp.a(new BigInteger("0"), new BigInteger("29818893917731240733471273240314769927240550812383695689146495261604565990247")), bigInteger2));
        bigInteger = new BigInteger("70390085352083305199547718019018437841079516630045180471284346843705633502619");
        bigInteger2 = new BigInteger("70390085352083305199547718019018437840920882647164081035322601458352298396601");
        fp = new Fp(bigInteger, new BigInteger("70390085352083305199547718019018437841079516630045180471284346843705633502616"), new BigInteger("32858"));
        b.put(CryptoProObjectIdentifiers.w, new ECDomainParameters(fp, fp.a(new BigInteger("0"), new BigInteger("29818893917731240733471273240314769927240550812383695689146495261604565990247")), bigInteger2));
        a.put("GostR3410-2001-CryptoPro-A", CryptoProObjectIdentifiers.u);
        a.put("GostR3410-2001-CryptoPro-B", CryptoProObjectIdentifiers.v);
        a.put("GostR3410-2001-CryptoPro-C", CryptoProObjectIdentifiers.w);
        a.put("GostR3410-2001-CryptoPro-XchA", CryptoProObjectIdentifiers.x);
        a.put("GostR3410-2001-CryptoPro-XchB", CryptoProObjectIdentifiers.y);
        c.put(CryptoProObjectIdentifiers.u, "GostR3410-2001-CryptoPro-A");
        c.put(CryptoProObjectIdentifiers.v, "GostR3410-2001-CryptoPro-B");
        c.put(CryptoProObjectIdentifiers.w, "GostR3410-2001-CryptoPro-C");
        c.put(CryptoProObjectIdentifiers.x, "GostR3410-2001-CryptoPro-XchA");
        c.put(CryptoProObjectIdentifiers.y, "GostR3410-2001-CryptoPro-XchB");
    }

    public static ECDomainParameters a(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return (ECDomainParameters) b.get(aSN1ObjectIdentifier);
    }

    public static ECDomainParameters a(String str) {
        ASN1ObjectIdentifier aSN1ObjectIdentifier = (ASN1ObjectIdentifier) a.get(str);
        if (aSN1ObjectIdentifier != null) {
            return (ECDomainParameters) b.get(aSN1ObjectIdentifier);
        }
        return null;
    }

    public static String b(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return (String) c.get(aSN1ObjectIdentifier);
    }

    public static ASN1ObjectIdentifier b(String str) {
        return (ASN1ObjectIdentifier) a.get(str);
    }
}
