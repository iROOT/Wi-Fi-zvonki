package org.spongycastle.pqc.jcajce.provider.gmss;

import java.security.PublicKey;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.pqc.asn1.GMSSPublicKey;
import org.spongycastle.pqc.asn1.PQCObjectIdentifiers;
import org.spongycastle.pqc.asn1.ParSet;
import org.spongycastle.pqc.crypto.gmss.GMSSParameters;
import org.spongycastle.pqc.jcajce.provider.util.KeyUtil;
import org.spongycastle.util.encoders.Hex;

public class BCGMSSPublicKey implements PublicKey, CipherParameters {
    private byte[] a;
    private GMSSParameters b;

    public String getAlgorithm() {
        return "GMSS";
    }

    public String toString() {
        String str = "GMSS public key : " + new String(Hex.a(this.a)) + "\n" + "Height of Trees: \n";
        for (int i = 0; i < this.b.b().length; i++) {
            str = str + "Layer " + i + " : " + this.b.b()[i] + " WinternitzParameter: " + this.b.c()[i] + " K: " + this.b.d()[i] + "\n";
        }
        return str;
    }

    public byte[] getEncoded() {
        return KeyUtil.a(new AlgorithmIdentifier(PQCObjectIdentifiers.g, new ParSet(this.b.a(), this.b.b(), this.b.c(), this.b.d()).a()), new GMSSPublicKey(this.a));
    }

    public String getFormat() {
        return "X.509";
    }
}
