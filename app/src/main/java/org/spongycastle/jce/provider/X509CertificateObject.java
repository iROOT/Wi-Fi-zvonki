package org.spongycastle.jce.provider;

import android.support.v4.app.NotificationCompat;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.Principal;
import java.security.Provider;
import java.security.PublicKey;
import java.security.Security;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.security.auth.x500.X500Principal;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1InputStream;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1OutputStream;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1String;
import org.spongycastle.asn1.DERBitString;
import org.spongycastle.asn1.DERIA5String;
import org.spongycastle.asn1.DERNull;
import org.spongycastle.asn1.DERObjectIdentifier;
import org.spongycastle.asn1.misc.MiscObjectIdentifiers;
import org.spongycastle.asn1.misc.NetscapeCertType;
import org.spongycastle.asn1.misc.NetscapeRevocationURL;
import org.spongycastle.asn1.misc.VerisignCzagExtension;
import org.spongycastle.asn1.util.ASN1Dump;
import org.spongycastle.asn1.x500.X500Name;
import org.spongycastle.asn1.x500.style.RFC4519Style;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.asn1.x509.BasicConstraints;
import org.spongycastle.asn1.x509.Certificate;
import org.spongycastle.asn1.x509.Extension;
import org.spongycastle.asn1.x509.Extensions;
import org.spongycastle.asn1.x509.GeneralName;
import org.spongycastle.asn1.x509.KeyUsage;
import org.spongycastle.jcajce.provider.asymmetric.util.PKCS12BagAttributeCarrierImpl;
import org.spongycastle.jce.X509Principal;
import org.spongycastle.jce.interfaces.PKCS12BagAttributeCarrier;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.Integers;
import org.spongycastle.util.encoders.Hex;

public class X509CertificateObject extends X509Certificate implements PKCS12BagAttributeCarrier {
    private Certificate a;
    private BasicConstraints b;
    private boolean[] c;
    private boolean d;
    private int e;
    private PKCS12BagAttributeCarrier f = new PKCS12BagAttributeCarrierImpl();

    public X509CertificateObject(Certificate certificate) {
        int i = 9;
        this.a = certificate;
        try {
            byte[] a = a("2.5.29.19");
            if (a != null) {
                this.b = BasicConstraints.a(ASN1Primitive.a(a));
            }
            try {
                a = a("2.5.29.15");
                if (a != null) {
                    DERBitString a2 = DERBitString.a(ASN1Primitive.a(a));
                    byte[] d = a2.d();
                    int length = (d.length * 8) - a2.e();
                    if (length >= 9) {
                        i = length;
                    }
                    this.c = new boolean[i];
                    for (int i2 = 0; i2 != length; i2++) {
                        boolean z;
                        boolean[] zArr = this.c;
                        if ((d[i2 / 8] & (NotificationCompat.FLAG_HIGH_PRIORITY >>> (i2 % 8))) != 0) {
                            z = true;
                        } else {
                            z = false;
                        }
                        zArr[i2] = z;
                    }
                    return;
                }
                this.c = null;
            } catch (Exception e) {
                throw new CertificateParsingException("cannot construct KeyUsage: " + e);
            }
        } catch (Exception e2) {
            throw new CertificateParsingException("cannot construct BasicConstraints: " + e2);
        }
    }

    public void checkValidity() {
        checkValidity(new Date());
    }

    public void checkValidity(Date date) {
        if (date.getTime() > getNotAfter().getTime()) {
            throw new CertificateExpiredException("certificate expired on " + this.a.i().d());
        } else if (date.getTime() < getNotBefore().getTime()) {
            throw new CertificateNotYetValidException("certificate not valid till " + this.a.h().d());
        }
    }

    public int getVersion() {
        return this.a.e();
    }

    public BigInteger getSerialNumber() {
        return this.a.f().d();
    }

    public Principal getIssuerDN() {
        try {
            return new X509Principal(X500Name.a(this.a.g().b()));
        } catch (IOException e) {
            return null;
        }
    }

    public X500Principal getIssuerX500Principal() {
        try {
            OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            new ASN1OutputStream(byteArrayOutputStream).a(this.a.g());
            return new X500Principal(byteArrayOutputStream.toByteArray());
        } catch (IOException e) {
            throw new IllegalStateException("can't encode issuer DN");
        }
    }

    public Principal getSubjectDN() {
        return new X509Principal(X500Name.a(this.a.j().a()));
    }

    public X500Principal getSubjectX500Principal() {
        try {
            OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            new ASN1OutputStream(byteArrayOutputStream).a(this.a.j());
            return new X500Principal(byteArrayOutputStream.toByteArray());
        } catch (IOException e) {
            throw new IllegalStateException("can't encode issuer DN");
        }
    }

    public Date getNotBefore() {
        return this.a.h().e();
    }

    public Date getNotAfter() {
        return this.a.i().e();
    }

    public byte[] getTBSCertificate() {
        try {
            return this.a.d().a("DER");
        } catch (IOException e) {
            throw new CertificateEncodingException(e.toString());
        }
    }

    public byte[] getSignature() {
        return this.a.m().d();
    }

    public String getSigAlgName() {
        Provider provider = Security.getProvider("SC");
        if (provider != null) {
            String property = provider.getProperty("Alg.Alias.Signature." + getSigAlgOID());
            if (property != null) {
                return property;
            }
        }
        Provider[] providers = Security.getProviders();
        for (int i = 0; i != providers.length; i++) {
            String property2 = providers[i].getProperty("Alg.Alias.Signature." + getSigAlgOID());
            if (property2 != null) {
                return property2;
            }
        }
        return getSigAlgOID();
    }

    public String getSigAlgOID() {
        return this.a.l().e().d();
    }

    public byte[] getSigAlgParams() {
        byte[] bArr = null;
        if (this.a.l().f() != null) {
            try {
                bArr = this.a.l().f().a().a("DER");
            } catch (IOException e) {
            }
        }
        return bArr;
    }

    public boolean[] getIssuerUniqueID() {
        DERBitString l = this.a.d().l();
        if (l == null) {
            return null;
        }
        byte[] d = l.d();
        boolean[] zArr = new boolean[((d.length * 8) - l.e())];
        for (int i = 0; i != zArr.length; i++) {
            boolean z;
            if ((d[i / 8] & (NotificationCompat.FLAG_HIGH_PRIORITY >>> (i % 8))) != 0) {
                z = true;
            } else {
                z = false;
            }
            zArr[i] = z;
        }
        return zArr;
    }

    public boolean[] getSubjectUniqueID() {
        DERBitString m = this.a.d().m();
        if (m == null) {
            return null;
        }
        byte[] d = m.d();
        boolean[] zArr = new boolean[((d.length * 8) - m.e())];
        for (int i = 0; i != zArr.length; i++) {
            boolean z;
            if ((d[i / 8] & (NotificationCompat.FLAG_HIGH_PRIORITY >>> (i % 8))) != 0) {
                z = true;
            } else {
                z = false;
            }
            zArr[i] = z;
        }
        return zArr;
    }

    public boolean[] getKeyUsage() {
        return this.c;
    }

    public List getExtendedKeyUsage() {
        byte[] a = a("2.5.29.37");
        if (a == null) {
            return null;
        }
        try {
            ASN1Sequence aSN1Sequence = (ASN1Sequence) new ASN1InputStream(a).d();
            List arrayList = new ArrayList();
            for (int i = 0; i != aSN1Sequence.f(); i++) {
                arrayList.add(((ASN1ObjectIdentifier) aSN1Sequence.a(i)).d());
            }
            return Collections.unmodifiableList(arrayList);
        } catch (Exception e) {
            throw new CertificateParsingException("error processing extended key usage extension");
        }
    }

    public int getBasicConstraints() {
        if (this.b == null || !this.b.d()) {
            return -1;
        }
        if (this.b.e() == null) {
            return Integer.MAX_VALUE;
        }
        return this.b.e().intValue();
    }

    public Collection getSubjectAlternativeNames() {
        return a(a(Extension.e.d()));
    }

    public Collection getIssuerAlternativeNames() {
        return a(a(Extension.f.d()));
    }

    public Set getCriticalExtensionOIDs() {
        if (getVersion() == 3) {
            Set hashSet = new HashSet();
            Extensions n = this.a.d().n();
            if (n != null) {
                Enumeration d = n.d();
                while (d.hasMoreElements()) {
                    ASN1ObjectIdentifier aSN1ObjectIdentifier = (ASN1ObjectIdentifier) d.nextElement();
                    if (n.a(aSN1ObjectIdentifier).e()) {
                        hashSet.add(aSN1ObjectIdentifier.d());
                    }
                }
                return hashSet;
            }
        }
        return null;
    }

    private byte[] a(String str) {
        Extensions n = this.a.d().n();
        if (n != null) {
            Extension a = n.a(new ASN1ObjectIdentifier(str));
            if (a != null) {
                return a.f().e();
            }
        }
        return null;
    }

    public byte[] getExtensionValue(String str) {
        Extensions n = this.a.d().n();
        if (n != null) {
            Extension a = n.a(new ASN1ObjectIdentifier(str));
            if (a != null) {
                try {
                    return a.f().b();
                } catch (Exception e) {
                    throw new IllegalStateException("error parsing " + e.toString());
                }
            }
        }
        return null;
    }

    public Set getNonCriticalExtensionOIDs() {
        if (getVersion() == 3) {
            Set hashSet = new HashSet();
            Extensions n = this.a.d().n();
            if (n != null) {
                Enumeration d = n.d();
                while (d.hasMoreElements()) {
                    ASN1ObjectIdentifier aSN1ObjectIdentifier = (ASN1ObjectIdentifier) d.nextElement();
                    if (!n.a(aSN1ObjectIdentifier).e()) {
                        hashSet.add(aSN1ObjectIdentifier.d());
                    }
                }
                return hashSet;
            }
        }
        return null;
    }

    public boolean hasUnsupportedCriticalExtension() {
        if (getVersion() == 3) {
            Extensions n = this.a.d().n();
            if (n != null) {
                Enumeration d = n.d();
                while (d.hasMoreElements()) {
                    ASN1ObjectIdentifier aSN1ObjectIdentifier = (ASN1ObjectIdentifier) d.nextElement();
                    String d2 = aSN1ObjectIdentifier.d();
                    if (!d2.equals(RFC3280CertPathUtilities.m) && !d2.equals(RFC3280CertPathUtilities.a) && !d2.equals(RFC3280CertPathUtilities.b) && !d2.equals(RFC3280CertPathUtilities.c) && !d2.equals(RFC3280CertPathUtilities.i) && !d2.equals(RFC3280CertPathUtilities.d) && !d2.equals(RFC3280CertPathUtilities.f) && !d2.equals(RFC3280CertPathUtilities.g) && !d2.equals(RFC3280CertPathUtilities.h) && !d2.equals(RFC3280CertPathUtilities.j) && !d2.equals(RFC3280CertPathUtilities.k) && n.a(aSN1ObjectIdentifier).e()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public PublicKey getPublicKey() {
        try {
            return BouncyCastleProvider.a(this.a.k());
        } catch (IOException e) {
            return null;
        }
    }

    public byte[] getEncoded() {
        try {
            return this.a.a("DER");
        } catch (IOException e) {
            throw new CertificateEncodingException(e.toString());
        }
    }

    public boolean equals(Object obj) {
        boolean z = false;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof java.security.cert.Certificate)) {
            return z;
        }
        try {
            return Arrays.a(getEncoded(), ((java.security.cert.Certificate) obj).getEncoded());
        } catch (CertificateEncodingException e) {
            return z;
        }
    }

    public synchronized int hashCode() {
        if (!this.d) {
            this.e = b();
            this.d = true;
        }
        return this.e;
    }

    private int b() {
        try {
            byte[] encoded = getEncoded();
            int i = 1;
            int i2 = 0;
            while (i < encoded.length) {
                int i3 = (encoded[i] * i) + i2;
                i++;
                i2 = i3;
            }
            return i2;
        } catch (CertificateEncodingException e) {
            return 0;
        }
    }

    public void a(ASN1ObjectIdentifier aSN1ObjectIdentifier, ASN1Encodable aSN1Encodable) {
        this.f.a(aSN1ObjectIdentifier, aSN1Encodable);
    }

    public ASN1Encodable a(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return this.f.a(aSN1ObjectIdentifier);
    }

    public Enumeration a() {
        return this.f.a();
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        String property = System.getProperty("line.separator");
        stringBuffer.append("  [0]         Version: ").append(getVersion()).append(property);
        stringBuffer.append("         SerialNumber: ").append(getSerialNumber()).append(property);
        stringBuffer.append("             IssuerDN: ").append(getIssuerDN()).append(property);
        stringBuffer.append("           Start Date: ").append(getNotBefore()).append(property);
        stringBuffer.append("           Final Date: ").append(getNotAfter()).append(property);
        stringBuffer.append("            SubjectDN: ").append(getSubjectDN()).append(property);
        stringBuffer.append("           Public Key: ").append(getPublicKey()).append(property);
        stringBuffer.append("  Signature Algorithm: ").append(getSigAlgName()).append(property);
        byte[] signature = getSignature();
        stringBuffer.append("            Signature: ").append(new String(Hex.a(signature, 0, 20))).append(property);
        for (int i = 20; i < signature.length; i += 20) {
            if (i < signature.length - 20) {
                stringBuffer.append("                       ").append(new String(Hex.a(signature, i, 20))).append(property);
            } else {
                stringBuffer.append("                       ").append(new String(Hex.a(signature, i, signature.length - i))).append(property);
            }
        }
        Extensions n = this.a.d().n();
        if (n != null) {
            Enumeration d = n.d();
            if (d.hasMoreElements()) {
                stringBuffer.append("       Extensions: \n");
            }
            while (d.hasMoreElements()) {
                ASN1ObjectIdentifier aSN1ObjectIdentifier = (ASN1ObjectIdentifier) d.nextElement();
                Extension a = n.a(aSN1ObjectIdentifier);
                if (a.f() != null) {
                    ASN1InputStream aSN1InputStream = new ASN1InputStream(a.f().e());
                    stringBuffer.append("                       critical(").append(a.e()).append(") ");
                    try {
                        if (aSN1ObjectIdentifier.equals(Extension.g)) {
                            stringBuffer.append(BasicConstraints.a(aSN1InputStream.d())).append(property);
                        } else if (aSN1ObjectIdentifier.equals(Extension.c)) {
                            stringBuffer.append(KeyUsage.a(aSN1InputStream.d())).append(property);
                        } else if (aSN1ObjectIdentifier.equals(MiscObjectIdentifiers.b)) {
                            stringBuffer.append(new NetscapeCertType((DERBitString) aSN1InputStream.d())).append(property);
                        } else if (aSN1ObjectIdentifier.equals(MiscObjectIdentifiers.d)) {
                            stringBuffer.append(new NetscapeRevocationURL((DERIA5String) aSN1InputStream.d())).append(property);
                        } else if (aSN1ObjectIdentifier.equals(MiscObjectIdentifiers.k)) {
                            stringBuffer.append(new VerisignCzagExtension((DERIA5String) aSN1InputStream.d())).append(property);
                        } else {
                            stringBuffer.append(aSN1ObjectIdentifier.d());
                            stringBuffer.append(" value = ").append(ASN1Dump.a(aSN1InputStream.d())).append(property);
                        }
                    } catch (Exception e) {
                        stringBuffer.append(aSN1ObjectIdentifier.d());
                        stringBuffer.append(" value = ").append("*****").append(property);
                    }
                } else {
                    stringBuffer.append(property);
                }
            }
        }
        return stringBuffer.toString();
    }

    public final void verify(PublicKey publicKey) {
        Signature instance;
        String a = X509SignatureUtil.a(this.a.l());
        try {
            instance = Signature.getInstance(a, "SC");
        } catch (Exception e) {
            instance = Signature.getInstance(a);
        }
        a(publicKey, instance);
    }

    public final void verify(PublicKey publicKey, String str) {
        a(publicKey, Signature.getInstance(X509SignatureUtil.a(this.a.l()), str));
    }

    private void a(PublicKey publicKey, Signature signature) {
        if (a(this.a.l(), this.a.d().f())) {
            X509SignatureUtil.a(signature, this.a.l().f());
            signature.initVerify(publicKey);
            signature.update(getTBSCertificate());
            if (!signature.verify(getSignature())) {
                throw new SignatureException("certificate does not verify with supplied key");
            }
            return;
        }
        throw new CertificateException("signature algorithm in TBS cert not same as outer cert");
    }

    private boolean a(AlgorithmIdentifier algorithmIdentifier, AlgorithmIdentifier algorithmIdentifier2) {
        if (!algorithmIdentifier.e().equals(algorithmIdentifier2.e())) {
            return false;
        }
        if (algorithmIdentifier.f() == null) {
            if (algorithmIdentifier2.f() == null || algorithmIdentifier2.f().equals(DERNull.a)) {
                return true;
            }
            return false;
        } else if (algorithmIdentifier2.f() != null) {
            return algorithmIdentifier.f().equals(algorithmIdentifier2.f());
        } else {
            if (algorithmIdentifier.f() == null || algorithmIdentifier.f().equals(DERNull.a)) {
                return true;
            }
            return false;
        }
    }

    private static Collection a(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        Collection arrayList = new ArrayList();
        Enumeration e = ASN1Sequence.a((Object) bArr).e();
        while (e.hasMoreElements()) {
            GeneralName a = GeneralName.a(e.nextElement());
            List arrayList2 = new ArrayList();
            arrayList2.add(Integers.a(a.d()));
            switch (a.d()) {
                case 0:
                case 3:
                case 5:
                    arrayList2.add(a.b());
                    break;
                case 1:
                case 2:
                case 6:
                    arrayList2.add(((ASN1String) a.e()).a_());
                    break;
                case 4:
                    arrayList2.add(X500Name.a(RFC4519Style.R, a.e()).toString());
                    break;
                case 7:
                    try {
                        try {
                            arrayList2.add(InetAddress.getByAddress(ASN1OctetString.a((Object) a.e()).e()).getHostAddress());
                            break;
                        } catch (Exception e2) {
                            throw new CertificateParsingException(e2.getMessage());
                        }
                    } catch (UnknownHostException e3) {
                        break;
                    }
                case 8:
                    arrayList2.add(DERObjectIdentifier.a((Object) a.e()).d());
                    break;
                default:
                    throw new IOException("Bad tag number: " + a.d());
            }
            arrayList.add(Collections.unmodifiableList(arrayList2));
        }
        if (arrayList.size() == 0) {
            return null;
        }
        return Collections.unmodifiableCollection(arrayList);
    }
}
