package org.spongycastle.util.encoders;

public class UrlBase64Encoder extends Base64Encoder {
    public UrlBase64Encoder() {
        this.a[this.a.length - 2] = (byte) 45;
        this.a[this.a.length - 1] = (byte) 95;
        this.b = (byte) 46;
        a();
    }
}
