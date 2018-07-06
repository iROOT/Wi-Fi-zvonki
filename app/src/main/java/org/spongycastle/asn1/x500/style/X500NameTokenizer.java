package org.spongycastle.asn1.x500.style;

class X500NameTokenizer {
    private String a;
    private int b;
    private char c;
    private StringBuffer d;

    public X500NameTokenizer(String str) {
        this(str, ',');
    }

    public X500NameTokenizer(String str, char c) {
        this.d = new StringBuffer();
        this.a = str;
        this.b = -1;
        this.c = c;
    }

    public boolean a() {
        return this.b != this.a.length();
    }

    public String b() {
        if (this.b == this.a.length()) {
            return null;
        }
        int i = this.b + 1;
        this.d.setLength(0);
        int i2 = 0;
        int i3 = i;
        i = 0;
        while (i3 != this.a.length()) {
            char charAt = this.a.charAt(i3);
            if (charAt == '\"') {
                i = i == 0 ? i2 == 0 ? 1 : 0 : i2;
                this.d.append(charAt);
                i2 = i;
                i = 0;
            } else if (i != 0 || i2 != 0) {
                this.d.append(charAt);
                i = 0;
            } else if (charAt == '\\') {
                this.d.append(charAt);
                i = 1;
            } else if (charAt == this.c) {
                break;
            } else {
                this.d.append(charAt);
            }
            i3++;
        }
        this.b = i3;
        return this.d.toString();
    }
}
