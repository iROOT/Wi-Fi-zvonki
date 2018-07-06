package org.spongycastle.ocsp;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1OutputStream;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERBitString;
import org.spongycastle.asn1.DERNull;
import org.spongycastle.asn1.DERObjectIdentifier;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.ocsp.OCSPRequest;
import org.spongycastle.asn1.ocsp.Request;
import org.spongycastle.asn1.ocsp.Signature;
import org.spongycastle.asn1.ocsp.TBSRequest;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.asn1.x509.Extensions;
import org.spongycastle.asn1.x509.GeneralName;
import org.spongycastle.asn1.x509.X509CertificateStructure;
import org.spongycastle.asn1.x509.X509Extensions;

public class OCSPReqGenerator {
    private List a = new ArrayList();
    private GeneralName b = null;
    private X509Extensions c = null;

    private class RequestObject {
        CertificateID a;
        X509Extensions b;
        final /* synthetic */ OCSPReqGenerator c;

        public RequestObject(OCSPReqGenerator oCSPReqGenerator, CertificateID certificateID, X509Extensions x509Extensions) {
            this.c = oCSPReqGenerator;
            this.a = certificateID;
            this.b = x509Extensions;
        }

        public Request a() {
            return new Request(this.a.a(), Extensions.a(this.b));
        }
    }

    public void a(CertificateID certificateID) {
        this.a.add(new RequestObject(this, certificateID, null));
    }

    public void a(X509Extensions x509Extensions) {
        this.c = x509Extensions;
    }

    private OCSPReq a(DERObjectIdentifier dERObjectIdentifier, PrivateKey privateKey, X509Certificate[] x509CertificateArr, String str, SecureRandom secureRandom) {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        for (RequestObject a : this.a) {
            try {
                aSN1EncodableVector.a(a.a());
            } catch (Exception e) {
                throw new OCSPException("exception creating Request", e);
            }
        }
        ASN1Encodable tBSRequest = new TBSRequest(this.b, new DERSequence(aSN1EncodableVector), this.c);
        Signature signature = null;
        if (dERObjectIdentifier != null) {
            if (this.b == null) {
                throw new OCSPException("requestorName must be specified if request is signed.");
            }
            try {
                java.security.Signature b = OCSPUtil.b(dERObjectIdentifier.d(), str);
                if (secureRandom != null) {
                    b.initSign(privateKey, secureRandom);
                } else {
                    b.initSign(privateKey);
                }
                try {
                    OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    new ASN1OutputStream(byteArrayOutputStream).a(tBSRequest);
                    b.update(byteArrayOutputStream.toByteArray());
                    DERBitString dERBitString = new DERBitString(b.sign());
                    AlgorithmIdentifier algorithmIdentifier = new AlgorithmIdentifier(dERObjectIdentifier, DERNull.a);
                    if (x509CertificateArr == null || x509CertificateArr.length <= 0) {
                        signature = new Signature(algorithmIdentifier, dERBitString);
                    } else {
                        ASN1EncodableVector aSN1EncodableVector2 = new ASN1EncodableVector();
                        int i = 0;
                        while (i != x509CertificateArr.length) {
                            try {
                                aSN1EncodableVector2.a(new X509CertificateStructure((ASN1Sequence) ASN1Primitive.a(x509CertificateArr[i].getEncoded())));
                                i++;
                            } catch (Exception e2) {
                                throw new OCSPException("error processing certs", e2);
                            } catch (Exception e22) {
                                throw new OCSPException("error encoding certs", e22);
                            }
                        }
                        signature = new Signature(algorithmIdentifier, dERBitString, new DERSequence(aSN1EncodableVector2));
                    }
                } catch (Exception e222) {
                    throw new OCSPException("exception processing TBSRequest: " + e222, e222);
                }
            } catch (NoSuchProviderException e3) {
                throw e3;
            } catch (Exception e2222) {
                throw new OCSPException("exception creating signature: " + e2222, e2222);
            }
        }
        return new OCSPReq(new OCSPRequest(tBSRequest, signature));
    }

    public OCSPReq a() {
        try {
            return a(null, null, null, null, null);
        } catch (Exception e) {
            throw new OCSPException("no provider! - " + e, e);
        }
    }
}
