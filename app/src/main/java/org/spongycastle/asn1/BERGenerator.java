package org.spongycastle.asn1;

public class BERGenerator extends ASN1Generator {
    private boolean b;
    private boolean c;

    protected void a() {
        this.a.write(0);
        this.a.write(0);
        if (this.b && this.c) {
            this.a.write(0);
            this.a.write(0);
        }
    }
}
