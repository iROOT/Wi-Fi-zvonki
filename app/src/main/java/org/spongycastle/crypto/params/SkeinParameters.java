package org.spongycastle.crypto.params;

import java.util.Enumeration;
import java.util.Hashtable;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.util.Integers;

public class SkeinParameters implements CipherParameters {
    private Hashtable a;

    public static class Builder {
        private Hashtable a = new Hashtable();

        public Builder(Hashtable hashtable) {
            Enumeration keys = hashtable.keys();
            while (keys.hasMoreElements()) {
                Integer num = (Integer) keys.nextElement();
                this.a.put(num, hashtable.get(num));
            }
        }

        public Builder a(int i, byte[] bArr) {
            if (bArr == null) {
                throw new IllegalArgumentException("Parameter value must not be null.");
            } else if (i != 0 && (i <= 4 || i >= 63 || i == 48)) {
                throw new IllegalArgumentException("Parameter types must be in the range 0,5..47,49..62.");
            } else if (i == 4) {
                throw new IllegalArgumentException("Parameter type 4 is reserved for internal use.");
            } else {
                this.a.put(Integers.a(i), bArr);
                return this;
            }
        }

        public Builder a(byte[] bArr) {
            return a(0, bArr);
        }

        public SkeinParameters a() {
            return new SkeinParameters(this.a);
        }
    }

    public SkeinParameters() {
        this(new Hashtable());
    }

    private SkeinParameters(Hashtable hashtable) {
        this.a = hashtable;
    }

    public Hashtable a() {
        return this.a;
    }

    public byte[] b() {
        return (byte[]) this.a.get(Integers.a(0));
    }
}
