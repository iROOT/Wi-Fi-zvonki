package org.spongycastle.util.io.pem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PemObject implements PemObjectGenerator {
    private static final List a = Collections.unmodifiableList(new ArrayList());
    private String b;
    private List c;
    private byte[] d;

    public PemObject(String str, byte[] bArr) {
        this(str, a, bArr);
    }

    public PemObject(String str, List list, byte[] bArr) {
        this.b = str;
        this.c = Collections.unmodifiableList(list);
        this.d = bArr;
    }

    public String a() {
        return this.b;
    }

    public List b() {
        return this.c;
    }

    public byte[] c() {
        return this.d;
    }

    public PemObject d() {
        return this;
    }
}
