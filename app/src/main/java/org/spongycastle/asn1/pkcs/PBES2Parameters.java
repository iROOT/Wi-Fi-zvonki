package org.spongycastle.asn1.pkcs;

import java.util.Enumeration;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERSequence;

public class PBES2Parameters extends ASN1Object implements PKCSObjectIdentifiers {
    private KeyDerivationFunc bD;
    private EncryptionScheme bE;

    public static PBES2Parameters a(Object obj) {
        if (obj instanceof PBES2Parameters) {
            return (PBES2Parameters) obj;
        }
        if (obj != null) {
            return new PBES2Parameters(ASN1Sequence.a(obj));
        }
        return null;
    }

    private PBES2Parameters(ASN1Sequence aSN1Sequence) {
        Enumeration e = aSN1Sequence.e();
        ASN1Sequence a = ASN1Sequence.a(((ASN1Encodable) e.nextElement()).a());
        if (a.a(0).equals(z)) {
            this.bD = new KeyDerivationFunc(z, PBKDF2Params.a(a.a(1)));
        } else {
            this.bD = KeyDerivationFunc.a(a);
        }
        this.bE = EncryptionScheme.a(e.nextElement());
    }

    public KeyDerivationFunc d() {
        return this.bD;
    }

    public EncryptionScheme e() {
        return this.bE;
    }

    public ASN1Primitive a() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.a(this.bD);
        aSN1EncodableVector.a(this.bE);
        return new DERSequence(aSN1EncodableVector);
    }
}
