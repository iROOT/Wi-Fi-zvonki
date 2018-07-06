package org.spongycastle.ocsp;

import java.security.MessageDigest;
import java.security.Signature;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;
import org.spongycastle.asn1.cryptopro.CryptoProObjectIdentifiers;
import org.spongycastle.asn1.nist.NISTObjectIdentifiers;
import org.spongycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.spongycastle.asn1.teletrust.TeleTrusTObjectIdentifiers;
import org.spongycastle.asn1.x9.X9ObjectIdentifiers;

class OCSPUtil {
    private static Hashtable a = new Hashtable();
    private static Hashtable b = new Hashtable();
    private static Set c = new HashSet();

    OCSPUtil() {
    }

    static {
        a.put("MD2WITHRSAENCRYPTION", PKCSObjectIdentifiers.i_);
        a.put("MD2WITHRSA", PKCSObjectIdentifiers.i_);
        a.put("MD5WITHRSAENCRYPTION", PKCSObjectIdentifiers.e);
        a.put("MD5WITHRSA", PKCSObjectIdentifiers.e);
        a.put("SHA1WITHRSAENCRYPTION", PKCSObjectIdentifiers.j_);
        a.put("SHA1WITHRSA", PKCSObjectIdentifiers.j_);
        a.put("SHA224WITHRSAENCRYPTION", PKCSObjectIdentifiers.p_);
        a.put("SHA224WITHRSA", PKCSObjectIdentifiers.p_);
        a.put("SHA256WITHRSAENCRYPTION", PKCSObjectIdentifiers.m_);
        a.put("SHA256WITHRSA", PKCSObjectIdentifiers.m_);
        a.put("SHA384WITHRSAENCRYPTION", PKCSObjectIdentifiers.n_);
        a.put("SHA384WITHRSA", PKCSObjectIdentifiers.n_);
        a.put("SHA512WITHRSAENCRYPTION", PKCSObjectIdentifiers.o_);
        a.put("SHA512WITHRSA", PKCSObjectIdentifiers.o_);
        a.put("RIPEMD160WITHRSAENCRYPTION", TeleTrusTObjectIdentifiers.f);
        a.put("RIPEMD160WITHRSA", TeleTrusTObjectIdentifiers.f);
        a.put("RIPEMD128WITHRSAENCRYPTION", TeleTrusTObjectIdentifiers.g);
        a.put("RIPEMD128WITHRSA", TeleTrusTObjectIdentifiers.g);
        a.put("RIPEMD256WITHRSAENCRYPTION", TeleTrusTObjectIdentifiers.h);
        a.put("RIPEMD256WITHRSA", TeleTrusTObjectIdentifiers.h);
        a.put("SHA1WITHDSA", X9ObjectIdentifiers.V);
        a.put("DSAWITHSHA1", X9ObjectIdentifiers.V);
        a.put("SHA224WITHDSA", NISTObjectIdentifiers.F);
        a.put("SHA256WITHDSA", NISTObjectIdentifiers.G);
        a.put("SHA1WITHECDSA", X9ObjectIdentifiers.i);
        a.put("ECDSAWITHSHA1", X9ObjectIdentifiers.i);
        a.put("SHA224WITHECDSA", X9ObjectIdentifiers.m);
        a.put("SHA256WITHECDSA", X9ObjectIdentifiers.n);
        a.put("SHA384WITHECDSA", X9ObjectIdentifiers.o);
        a.put("SHA512WITHECDSA", X9ObjectIdentifiers.p);
        a.put("GOST3411WITHGOST3410", CryptoProObjectIdentifiers.k);
        a.put("GOST3411WITHGOST3410-94", CryptoProObjectIdentifiers.k);
        b.put(PKCSObjectIdentifiers.i_, "MD2WITHRSA");
        b.put(PKCSObjectIdentifiers.e, "MD5WITHRSA");
        b.put(PKCSObjectIdentifiers.j_, "SHA1WITHRSA");
        b.put(PKCSObjectIdentifiers.p_, "SHA224WITHRSA");
        b.put(PKCSObjectIdentifiers.m_, "SHA256WITHRSA");
        b.put(PKCSObjectIdentifiers.n_, "SHA384WITHRSA");
        b.put(PKCSObjectIdentifiers.o_, "SHA512WITHRSA");
        b.put(TeleTrusTObjectIdentifiers.f, "RIPEMD160WITHRSA");
        b.put(TeleTrusTObjectIdentifiers.g, "RIPEMD128WITHRSA");
        b.put(TeleTrusTObjectIdentifiers.h, "RIPEMD256WITHRSA");
        b.put(X9ObjectIdentifiers.V, "SHA1WITHDSA");
        b.put(NISTObjectIdentifiers.F, "SHA224WITHDSA");
        b.put(NISTObjectIdentifiers.G, "SHA256WITHDSA");
        b.put(X9ObjectIdentifiers.i, "SHA1WITHECDSA");
        b.put(X9ObjectIdentifiers.m, "SHA224WITHECDSA");
        b.put(X9ObjectIdentifiers.n, "SHA256WITHECDSA");
        b.put(X9ObjectIdentifiers.o, "SHA384WITHECDSA");
        b.put(X9ObjectIdentifiers.p, "SHA512WITHECDSA");
        b.put(CryptoProObjectIdentifiers.k, "GOST3411WITHGOST3410");
        c.add(X9ObjectIdentifiers.i);
        c.add(X9ObjectIdentifiers.m);
        c.add(X9ObjectIdentifiers.n);
        c.add(X9ObjectIdentifiers.o);
        c.add(X9ObjectIdentifiers.p);
        c.add(X9ObjectIdentifiers.V);
        c.add(NISTObjectIdentifiers.F);
        c.add(NISTObjectIdentifiers.G);
    }

    static MessageDigest a(String str, String str2) {
        if (str2 == null) {
            return MessageDigest.getInstance(str);
        }
        return MessageDigest.getInstance(str, str2);
    }

    static Signature b(String str, String str2) {
        if (str2 == null) {
            return Signature.getInstance(str);
        }
        return Signature.getInstance(str, str2);
    }
}
