package org.spongycastle.jcajce.provider.asymmetric.x509;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.security.NoSuchProviderException;
import java.security.cert.CertPath;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import javax.security.auth.x500.X500Principal;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1InputStream;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERSet;
import org.spongycastle.asn1.pkcs.ContentInfo;
import org.spongycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.spongycastle.asn1.pkcs.SignedData;
import org.spongycastle.util.io.pem.PemObject;
import org.spongycastle.util.io.pem.PemWriter;

public class PKIXCertPath extends CertPath {
    static final List a;
    private List b;

    static {
        List arrayList = new ArrayList();
        arrayList.add("PkiPath");
        arrayList.add("PEM");
        arrayList.add("PKCS7");
        a = Collections.unmodifiableList(arrayList);
    }

    private List a(List list) {
        if (list.size() < 2) {
            return list;
        }
        int i;
        int i2;
        X500Principal issuerX500Principal = ((X509Certificate) list.get(0)).getIssuerX500Principal();
        for (i = 1; i != list.size(); i++) {
            if (!issuerX500Principal.equals(((X509Certificate) list.get(i)).getSubjectX500Principal())) {
                i2 = 0;
                break;
            }
            issuerX500Principal = ((X509Certificate) list.get(i)).getIssuerX500Principal();
        }
        i2 = 1;
        if (i2 != 0) {
            return list;
        }
        List arrayList = new ArrayList(list.size());
        ArrayList arrayList2 = new ArrayList(list);
        for (int i3 = 0; i3 < list.size(); i3++) {
            X509Certificate x509Certificate = (X509Certificate) list.get(i3);
            X500Principal subjectX500Principal = x509Certificate.getSubjectX500Principal();
            for (int i4 = 0; i4 != list.size(); i4++) {
                if (((X509Certificate) list.get(i4)).getIssuerX500Principal().equals(subjectX500Principal)) {
                    i = 1;
                    break;
                }
            }
            i = 0;
            if (i == 0) {
                arrayList.add(x509Certificate);
                list.remove(i3);
            }
        }
        if (arrayList.size() > 1) {
            return arrayList2;
        }
        for (i = 0; i != arrayList.size(); i++) {
            issuerX500Principal = ((X509Certificate) arrayList.get(i)).getIssuerX500Principal();
            for (int i5 = 0; i5 < list.size(); i5++) {
                x509Certificate = (X509Certificate) list.get(i5);
                if (issuerX500Principal.equals(x509Certificate.getSubjectX500Principal())) {
                    arrayList.add(x509Certificate);
                    list.remove(i5);
                    break;
                }
            }
        }
        if (list.size() > 0) {
            return arrayList2;
        }
        return arrayList;
    }

    PKIXCertPath(List list) {
        super("X.509");
        this.b = a(new ArrayList(list));
    }

    PKIXCertPath(InputStream inputStream, String str) {
        super("X.509");
        try {
            if (str.equalsIgnoreCase("PkiPath")) {
                ASN1Primitive d = new ASN1InputStream(inputStream).d();
                if (d instanceof ASN1Sequence) {
                    Enumeration e = ((ASN1Sequence) d).e();
                    this.b = new ArrayList();
                    CertificateFactory instance = CertificateFactory.getInstance("X.509", "SC");
                    while (e.hasMoreElements()) {
                        this.b.add(0, instance.generateCertificate(new ByteArrayInputStream(((ASN1Encodable) e.nextElement()).a().a("DER"))));
                    }
                } else {
                    throw new CertificateException("input stream does not contain a ASN1 SEQUENCE while reading PkiPath encoded data to load CertPath");
                }
            } else if (str.equalsIgnoreCase("PKCS7") || str.equalsIgnoreCase("PEM")) {
                InputStream bufferedInputStream = new BufferedInputStream(inputStream);
                this.b = new ArrayList();
                CertificateFactory instance2 = CertificateFactory.getInstance("X.509", "SC");
                while (true) {
                    Certificate generateCertificate = instance2.generateCertificate(bufferedInputStream);
                    if (generateCertificate == null) {
                        break;
                    }
                    this.b.add(generateCertificate);
                }
            } else {
                throw new CertificateException("unsupported encoding: " + str);
            }
            this.b = a(this.b);
        } catch (IOException e2) {
            throw new CertificateException("IOException throw while decoding CertPath:\n" + e2.toString());
        } catch (NoSuchProviderException e3) {
            throw new CertificateException("BouncyCastle provider not found while trying to get a CertificateFactory:\n" + e3.toString());
        }
    }

    public Iterator getEncodings() {
        return a.iterator();
    }

    public byte[] getEncoded() {
        Iterator encodings = getEncodings();
        if (encodings.hasNext()) {
            Object next = encodings.next();
            if (next instanceof String) {
                return getEncoded((String) next);
            }
        }
        return null;
    }

    public byte[] getEncoded(String str) {
        if (str.equalsIgnoreCase("PkiPath")) {
            ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
            ListIterator listIterator = this.b.listIterator(this.b.size());
            while (listIterator.hasPrevious()) {
                aSN1EncodableVector.a(a((X509Certificate) listIterator.previous()));
            }
            return a(new DERSequence(aSN1EncodableVector));
        } else if (str.equalsIgnoreCase("PKCS7")) {
            ContentInfo contentInfo = new ContentInfo(PKCSObjectIdentifiers.O, null);
            ASN1EncodableVector aSN1EncodableVector2 = new ASN1EncodableVector();
            for (r1 = 0; r1 != this.b.size(); r1++) {
                aSN1EncodableVector2.a(a((X509Certificate) this.b.get(r1)));
            }
            return a(new ContentInfo(PKCSObjectIdentifiers.P, new SignedData(new ASN1Integer(1), new DERSet(), contentInfo, new DERSet(aSN1EncodableVector2), null, new DERSet())));
        } else if (str.equalsIgnoreCase("PEM")) {
            OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            PemWriter pemWriter = new PemWriter(new OutputStreamWriter(byteArrayOutputStream));
            r1 = 0;
            while (r1 != this.b.size()) {
                try {
                    pemWriter.a(new PemObject("CERTIFICATE", ((X509Certificate) this.b.get(r1)).getEncoded()));
                    r1++;
                } catch (Exception e) {
                    throw new CertificateEncodingException("can't encode certificate for PEM encoded path");
                }
            }
            pemWriter.close();
            return byteArrayOutputStream.toByteArray();
        } else {
            throw new CertificateEncodingException("unsupported encoding: " + str);
        }
    }

    public List getCertificates() {
        return Collections.unmodifiableList(new ArrayList(this.b));
    }

    private ASN1Primitive a(X509Certificate x509Certificate) {
        try {
            return new ASN1InputStream(x509Certificate.getEncoded()).d();
        } catch (Exception e) {
            throw new CertificateEncodingException("Exception while encoding certificate: " + e.toString());
        }
    }

    private byte[] a(ASN1Encodable aSN1Encodable) {
        try {
            return aSN1Encodable.a().a("DER");
        } catch (IOException e) {
            throw new CertificateEncodingException("Exception thrown: " + e);
        }
    }
}
