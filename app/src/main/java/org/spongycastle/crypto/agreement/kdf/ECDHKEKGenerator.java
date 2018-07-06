package org.spongycastle.crypto.agreement.kdf;

import java.io.IOException;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.DERNull;
import org.spongycastle.asn1.DEROctetString;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERTaggedObject;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.crypto.DerivationParameters;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.DigestDerivationFunction;
import org.spongycastle.crypto.generators.KDF2BytesGenerator;
import org.spongycastle.crypto.params.KDFParameters;
import org.spongycastle.crypto.util.Pack;

public class ECDHKEKGenerator implements DigestDerivationFunction {
    private DigestDerivationFunction a;
    private ASN1ObjectIdentifier b;
    private int c;
    private byte[] d;

    public ECDHKEKGenerator(Digest digest) {
        this.a = new KDF2BytesGenerator(digest);
    }

    public void a(DerivationParameters derivationParameters) {
        DHKDFParameters dHKDFParameters = (DHKDFParameters) derivationParameters;
        this.b = dHKDFParameters.a();
        this.c = dHKDFParameters.b();
        this.d = dHKDFParameters.c();
    }

    public int a(byte[] bArr, int i, int i2) {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.a(new AlgorithmIdentifier(this.b, DERNull.a));
        aSN1EncodableVector.a(new DERTaggedObject(true, 2, new DEROctetString(Pack.a(this.c))));
        try {
            this.a.a(new KDFParameters(this.d, new DERSequence(aSN1EncodableVector).a("DER")));
            return this.a.a(bArr, i, i2);
        } catch (IOException e) {
            throw new IllegalArgumentException("unable to initialise kdf: " + e.getMessage());
        }
    }
}
