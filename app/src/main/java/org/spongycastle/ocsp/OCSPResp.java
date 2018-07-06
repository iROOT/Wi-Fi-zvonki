package org.spongycastle.ocsp;

import java.io.IOException;
import java.io.InputStream;
import org.spongycastle.asn1.ASN1InputStream;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ocsp.BasicOCSPResponse;
import org.spongycastle.asn1.ocsp.OCSPObjectIdentifiers;
import org.spongycastle.asn1.ocsp.OCSPResponse;
import org.spongycastle.asn1.ocsp.ResponseBytes;

public class OCSPResp {
    private OCSPResponse a;

    public OCSPResp(InputStream inputStream) {
        this(new ASN1InputStream(inputStream));
    }

    private OCSPResp(ASN1InputStream aSN1InputStream) {
        try {
            this.a = OCSPResponse.a(aSN1InputStream.d());
        } catch (IllegalArgumentException e) {
            throw new IOException("malformed response: " + e.getMessage());
        } catch (ClassCastException e2) {
            throw new IOException("malformed response: " + e2.getMessage());
        }
    }

    public int a() {
        return this.a.d().d().intValue();
    }

    public Object b() {
        ResponseBytes e = this.a.e();
        if (e == null) {
            return null;
        }
        if (!e.d().equals(OCSPObjectIdentifiers.b)) {
            return e.e();
        }
        try {
            return new BasicOCSPResp(BasicOCSPResponse.a(ASN1Primitive.a(e.e().e())));
        } catch (Exception e2) {
            throw new OCSPException("problem decoding object: " + e2, e2);
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof OCSPResp)) {
            return false;
        }
        return this.a.equals(((OCSPResp) obj).a);
    }

    public int hashCode() {
        return this.a.hashCode();
    }
}
