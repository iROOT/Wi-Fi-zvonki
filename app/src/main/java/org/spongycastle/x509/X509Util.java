package org.spongycastle.x509;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.security.Security;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.DERNull;
import org.spongycastle.asn1.cryptopro.CryptoProObjectIdentifiers;
import org.spongycastle.asn1.nist.NISTObjectIdentifiers;
import org.spongycastle.asn1.oiw.OIWObjectIdentifiers;
import org.spongycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.spongycastle.asn1.pkcs.RSASSAPSSparams;
import org.spongycastle.asn1.teletrust.TeleTrusTObjectIdentifiers;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.asn1.x9.X9ObjectIdentifiers;
import org.spongycastle.util.Strings;

class X509Util {
    private static Hashtable a = new Hashtable();
    private static Hashtable b = new Hashtable();
    private static Set c = new HashSet();

    static class Implementation {
        Object a;
        Provider b;

        Implementation(Object obj, Provider provider) {
            this.a = obj;
            this.b = provider;
        }

        Object a() {
            return this.a;
        }

        Provider b() {
            return this.b;
        }
    }

    X509Util() {
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
        a.put("SHA1WITHRSAANDMGF1", PKCSObjectIdentifiers.k);
        a.put("SHA224WITHRSAANDMGF1", PKCSObjectIdentifiers.k);
        a.put("SHA256WITHRSAANDMGF1", PKCSObjectIdentifiers.k);
        a.put("SHA384WITHRSAANDMGF1", PKCSObjectIdentifiers.k);
        a.put("SHA512WITHRSAANDMGF1", PKCSObjectIdentifiers.k);
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
        a.put("SHA384WITHDSA", NISTObjectIdentifiers.H);
        a.put("SHA512WITHDSA", NISTObjectIdentifiers.I);
        a.put("SHA1WITHECDSA", X9ObjectIdentifiers.i);
        a.put("ECDSAWITHSHA1", X9ObjectIdentifiers.i);
        a.put("SHA224WITHECDSA", X9ObjectIdentifiers.m);
        a.put("SHA256WITHECDSA", X9ObjectIdentifiers.n);
        a.put("SHA384WITHECDSA", X9ObjectIdentifiers.o);
        a.put("SHA512WITHECDSA", X9ObjectIdentifiers.p);
        a.put("GOST3411WITHGOST3410", CryptoProObjectIdentifiers.k);
        a.put("GOST3411WITHGOST3410-94", CryptoProObjectIdentifiers.k);
        a.put("GOST3411WITHECGOST3410", CryptoProObjectIdentifiers.l);
        a.put("GOST3411WITHECGOST3410-2001", CryptoProObjectIdentifiers.l);
        a.put("GOST3411WITHGOST3410-2001", CryptoProObjectIdentifiers.l);
        c.add(X9ObjectIdentifiers.i);
        c.add(X9ObjectIdentifiers.m);
        c.add(X9ObjectIdentifiers.n);
        c.add(X9ObjectIdentifiers.o);
        c.add(X9ObjectIdentifiers.p);
        c.add(X9ObjectIdentifiers.V);
        c.add(NISTObjectIdentifiers.F);
        c.add(NISTObjectIdentifiers.G);
        c.add(NISTObjectIdentifiers.H);
        c.add(NISTObjectIdentifiers.I);
        c.add(CryptoProObjectIdentifiers.k);
        c.add(CryptoProObjectIdentifiers.l);
        b.put("SHA1WITHRSAANDMGF1", a(new AlgorithmIdentifier(OIWObjectIdentifiers.i, DERNull.a), 20));
        b.put("SHA224WITHRSAANDMGF1", a(new AlgorithmIdentifier(NISTObjectIdentifiers.f, DERNull.a), 28));
        b.put("SHA256WITHRSAANDMGF1", a(new AlgorithmIdentifier(NISTObjectIdentifiers.c, DERNull.a), 32));
        b.put("SHA384WITHRSAANDMGF1", a(new AlgorithmIdentifier(NISTObjectIdentifiers.d, DERNull.a), 48));
        b.put("SHA512WITHRSAANDMGF1", a(new AlgorithmIdentifier(NISTObjectIdentifiers.e, DERNull.a), 64));
    }

    private static RSASSAPSSparams a(AlgorithmIdentifier algorithmIdentifier, int i) {
        return new RSASSAPSSparams(algorithmIdentifier, new AlgorithmIdentifier(PKCSObjectIdentifiers.l_, (ASN1Encodable) algorithmIdentifier), new ASN1Integer((long) i), new ASN1Integer(1));
    }

    static Implementation a(String str, String str2, Provider provider) {
        String b = Strings.b(str2);
        while (true) {
            String property = provider.getProperty("Alg.Alias." + str + "." + b);
            if (property == null) {
                break;
            }
            b = property;
        }
        String property2 = provider.getProperty(str + "." + b);
        if (property2 != null) {
            try {
                Class loadClass;
                ClassLoader classLoader = provider.getClass().getClassLoader();
                if (classLoader != null) {
                    loadClass = classLoader.loadClass(property2);
                } else {
                    loadClass = Class.forName(property2);
                }
                return new Implementation(loadClass.newInstance(), provider);
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException("algorithm " + b + " in provider " + provider.getName() + " but no class \"" + property2 + "\" found!");
            } catch (Exception e2) {
                throw new IllegalStateException("algorithm " + b + " in provider " + provider.getName() + " but class \"" + property2 + "\" inaccessible!");
            }
        }
        throw new NoSuchAlgorithmException("cannot find implementation " + b + " for provider " + provider.getName());
    }

    static Provider a(String str) {
        Provider provider = Security.getProvider(str);
        if (provider != null) {
            return provider;
        }
        throw new NoSuchProviderException("Provider " + str + " not found");
    }
}
