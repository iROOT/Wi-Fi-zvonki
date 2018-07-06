package org.spongycastle.jcajce.provider.symmetric.util;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Hashtable;
import java.util.Map;
import javax.crypto.MacSpi;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEParameterSpec;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.Mac;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.params.ParametersWithIV;
import org.spongycastle.crypto.params.SkeinParameters.Builder;
import org.spongycastle.jcajce.provider.symmetric.util.PBE.Util;
import org.spongycastle.jcajce.spec.SkeinParameterSpec;

public class BaseMac extends MacSpi implements PBE {
    private Mac a;
    private int b = 2;
    private int c = 1;
    private int d = 160;

    protected BaseMac(Mac mac) {
        this.a = mac;
    }

    protected BaseMac(Mac mac, int i, int i2, int i3) {
        this.a = mac;
        this.b = i;
        this.c = i2;
        this.d = i3;
    }

    protected void engineInit(Key key, AlgorithmParameterSpec algorithmParameterSpec) {
        if (key == null) {
            throw new InvalidKeyException("key is null");
        }
        CipherParameters e;
        if (key instanceof BCPBEKey) {
            BCPBEKey bCPBEKey = (BCPBEKey) key;
            if (bCPBEKey.e() != null) {
                e = bCPBEKey.e();
            } else if (algorithmParameterSpec instanceof PBEParameterSpec) {
                e = Util.a(bCPBEKey, algorithmParameterSpec);
            } else {
                throw new InvalidAlgorithmParameterException("PBE requires PBE parameters to be set.");
            }
        } else if (algorithmParameterSpec instanceof IvParameterSpec) {
            e = new ParametersWithIV(new KeyParameter(key.getEncoded()), ((IvParameterSpec) algorithmParameterSpec).getIV());
        } else if (algorithmParameterSpec instanceof SkeinParameterSpec) {
            e = new Builder(a(((SkeinParameterSpec) algorithmParameterSpec).a())).a(key.getEncoded()).a();
        } else if (algorithmParameterSpec == null) {
            e = new KeyParameter(key.getEncoded());
        } else {
            throw new InvalidAlgorithmParameterException("unknown parameter type.");
        }
        this.a.a(e);
    }

    protected int engineGetMacLength() {
        return this.a.b();
    }

    protected void engineReset() {
        this.a.c();
    }

    protected void engineUpdate(byte b) {
        this.a.a(b);
    }

    protected void engineUpdate(byte[] bArr, int i, int i2) {
        this.a.a(bArr, i, i2);
    }

    protected byte[] engineDoFinal() {
        byte[] bArr = new byte[engineGetMacLength()];
        this.a.a(bArr, 0);
        return bArr;
    }

    private static Hashtable a(Map map) {
        Hashtable hashtable = new Hashtable();
        for (Object next : map.keySet()) {
            hashtable.put(next, map.get(next));
        }
        return hashtable;
    }
}
