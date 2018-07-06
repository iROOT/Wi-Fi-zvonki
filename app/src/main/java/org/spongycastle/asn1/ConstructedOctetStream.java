package org.spongycastle.asn1;

import java.io.InputStream;

class ConstructedOctetStream extends InputStream {
    private final ASN1StreamParser a;
    private boolean b = true;
    private InputStream c;

    ConstructedOctetStream(ASN1StreamParser aSN1StreamParser) {
        this.a = aSN1StreamParser;
    }

    public int read(byte[] bArr, int i, int i2) {
        ASN1OctetStringParser aSN1OctetStringParser;
        int i3 = 0;
        if (this.c == null) {
            if (!this.b) {
                return -1;
            }
            aSN1OctetStringParser = (ASN1OctetStringParser) this.a.a();
            if (aSN1OctetStringParser == null) {
                return -1;
            }
            this.b = false;
            this.c = aSN1OctetStringParser.d();
        }
        while (true) {
            int read = this.c.read(bArr, i + i3, i2 - i3);
            if (read >= 0) {
                read += i3;
                if (read == i2) {
                    return read;
                }
            } else {
                aSN1OctetStringParser = (ASN1OctetStringParser) this.a.a();
                if (aSN1OctetStringParser == null) {
                    break;
                }
                this.c = aSN1OctetStringParser.d();
                read = i3;
            }
            i3 = read;
        }
        this.c = null;
        if (i3 < 1) {
            i3 = -1;
        }
        return i3;
    }

    public int read() {
        ASN1OctetStringParser aSN1OctetStringParser;
        if (this.c == null) {
            if (!this.b) {
                return -1;
            }
            aSN1OctetStringParser = (ASN1OctetStringParser) this.a.a();
            if (aSN1OctetStringParser == null) {
                return -1;
            }
            this.b = false;
            this.c = aSN1OctetStringParser.d();
        }
        while (true) {
            int read = this.c.read();
            if (read >= 0) {
                return read;
            }
            aSN1OctetStringParser = (ASN1OctetStringParser) this.a.a();
            if (aSN1OctetStringParser == null) {
                this.c = null;
                return -1;
            }
            this.c = aSN1OctetStringParser.d();
        }
    }
}
