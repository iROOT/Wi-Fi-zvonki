package org.spongycastle.asn1;

import android.support.v4.app.NotificationCompat;
import com.fgmicrotec.mobile.android.fgmag.VoIP;
import java.io.ByteArrayInputStream;
import java.io.EOFException;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.spongycastle.util.io.Streams;

public class ASN1InputStream extends FilterInputStream implements BERTags {
    private final int a;
    private final boolean b;
    private final byte[][] c;

    public ASN1InputStream(InputStream inputStream) {
        this(inputStream, StreamUtil.a(inputStream));
    }

    public ASN1InputStream(byte[] bArr) {
        this(new ByteArrayInputStream(bArr), bArr.length);
    }

    public ASN1InputStream(byte[] bArr, boolean z) {
        this(new ByteArrayInputStream(bArr), bArr.length, z);
    }

    public ASN1InputStream(InputStream inputStream, int i) {
        this(inputStream, i, false);
    }

    public ASN1InputStream(InputStream inputStream, boolean z) {
        this(inputStream, StreamUtil.a(inputStream), z);
    }

    public ASN1InputStream(InputStream inputStream, int i, boolean z) {
        super(inputStream);
        this.a = i;
        this.b = z;
        this.c = new byte[11][];
    }

    int a() {
        return this.a;
    }

    protected int b() {
        return b(this, this.a);
    }

    protected ASN1Primitive a(int i, int i2, int i3) {
        int i4 = 0;
        boolean z = (i & 32) != 0;
        DefiniteLengthInputStream definiteLengthInputStream = new DefiniteLengthInputStream(this, i3);
        if ((i & 64) != 0) {
            return new DERApplicationSpecific(z, i2, definiteLengthInputStream.b());
        }
        if ((i & NotificationCompat.FLAG_HIGH_PRIORITY) != 0) {
            return new ASN1StreamParser(definiteLengthInputStream).a(z, i2);
        }
        if (!z) {
            return a(i2, definiteLengthInputStream, this.c);
        }
        switch (i2) {
            case 4:
                ASN1EncodableVector a = a(definiteLengthInputStream);
                ASN1OctetString[] aSN1OctetStringArr = new ASN1OctetString[a.a()];
                while (i4 != aSN1OctetStringArr.length) {
                    aSN1OctetStringArr[i4] = (ASN1OctetString) a.a(i4);
                    i4++;
                }
                return new BEROctetString(aSN1OctetStringArr);
            case 8:
                return new DERExternal(a(definiteLengthInputStream));
            case 16:
                if (this.b) {
                    return new LazyEncodedSequence(definiteLengthInputStream.b());
                }
                return DERFactory.a(a(definiteLengthInputStream));
            case 17:
                return DERFactory.b(a(definiteLengthInputStream));
            default:
                throw new IOException("unknown tag " + i2 + " encountered");
        }
    }

    ASN1EncodableVector c() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        while (true) {
            ASN1Encodable d = d();
            if (d == null) {
                return aSN1EncodableVector;
            }
            aSN1EncodableVector.a(d);
        }
    }

    ASN1EncodableVector a(DefiniteLengthInputStream definiteLengthInputStream) {
        return new ASN1InputStream((InputStream) definiteLengthInputStream).c();
    }

    public ASN1Primitive d() {
        int read = read();
        if (read > 0) {
            int a = a((InputStream) this, read);
            boolean z = (read & 32) != 0;
            int b = b();
            if (b >= 0) {
                try {
                    return a(read, a, b);
                } catch (Throwable e) {
                    throw new ASN1Exception("corrupted stream detected", e);
                }
            } else if (z) {
                ASN1StreamParser aSN1StreamParser = new ASN1StreamParser(new IndefiniteLengthInputStream(this, this.a), this.a);
                if ((read & 64) != 0) {
                    return new BERApplicationSpecificParser(a, aSN1StreamParser).f();
                }
                if ((read & NotificationCompat.FLAG_HIGH_PRIORITY) != 0) {
                    return new BERTaggedObjectParser(true, a, aSN1StreamParser).f();
                }
                switch (a) {
                    case 4:
                        return new BEROctetStringParser(aSN1StreamParser).f();
                    case 8:
                        return new DERExternalParser(aSN1StreamParser).f();
                    case 16:
                        return new BERSequenceParser(aSN1StreamParser).f();
                    case 17:
                        return new BERSetParser(aSN1StreamParser).f();
                    default:
                        throw new IOException("unknown BER object encountered");
                }
            } else {
                throw new IOException("indefinite length primitive encoding encountered");
            }
        } else if (read != 0) {
            return null;
        } else {
            throw new IOException("unexpected end-of-contents marker");
        }
    }

    static int a(InputStream inputStream, int i) {
        int i2 = i & 31;
        if (i2 != 31) {
            return i2;
        }
        int i3 = 0;
        i2 = inputStream.read();
        if ((i2 & 127) == 0) {
            throw new IOException("corrupted stream - invalid high tag number found");
        }
        while (i2 >= 0 && (i2 & NotificationCompat.FLAG_HIGH_PRIORITY) != 0) {
            i3 = ((i2 & 127) | i3) << 7;
            i2 = inputStream.read();
        }
        if (i2 >= 0) {
            return (i2 & 127) | i3;
        }
        throw new EOFException("EOF found inside tag value.");
    }

    static int b(InputStream inputStream, int i) {
        int i2 = 0;
        int read = inputStream.read();
        if (read < 0) {
            throw new EOFException("EOF found when length expected");
        } else if (read == NotificationCompat.FLAG_HIGH_PRIORITY) {
            return -1;
        } else {
            if (read <= 127) {
                return read;
            }
            int i3 = read & 127;
            if (i3 > 4) {
                throw new IOException("DER length more than 4 bytes: " + i3);
            }
            read = 0;
            while (i2 < i3) {
                int read2 = inputStream.read();
                if (read2 < 0) {
                    throw new EOFException("EOF found reading length");
                }
                i2++;
                read = read2 + (read << 8);
            }
            if (read < 0) {
                throw new IOException("corrupted stream - negative length found");
            } else if (read < i) {
                return read;
            } else {
                throw new IOException("corrupted stream - out of bounds length found");
            }
        }
    }

    private static byte[] a(DefiniteLengthInputStream definiteLengthInputStream, byte[][] bArr) {
        int a = definiteLengthInputStream.a();
        if (definiteLengthInputStream.a() >= bArr.length) {
            return definiteLengthInputStream.b();
        }
        byte[] bArr2 = bArr[a];
        if (bArr2 == null) {
            bArr2 = new byte[a];
            bArr[a] = bArr2;
        }
        Streams.a((InputStream) definiteLengthInputStream, bArr2);
        return bArr2;
    }

    private static char[] b(DefiniteLengthInputStream definiteLengthInputStream) {
        int a = definiteLengthInputStream.a() / 2;
        char[] cArr = new char[a];
        int i = 0;
        while (i < a) {
            int read = definiteLengthInputStream.read();
            if (read >= 0) {
                int read2 = definiteLengthInputStream.read();
                if (read2 < 0) {
                    break;
                }
                int i2 = i + 1;
                cArr[i] = (char) ((read << 8) | (read2 & 255));
                i = i2;
            } else {
                break;
            }
        }
        return cArr;
    }

    static ASN1Primitive a(int i, DefiniteLengthInputStream definiteLengthInputStream, byte[][] bArr) {
        switch (i) {
            case 1:
                return DERBoolean.b(a(definiteLengthInputStream, bArr));
            case 2:
                return new ASN1Integer(definiteLengthInputStream.b());
            case 3:
                return DERBitString.a(definiteLengthInputStream.a(), (InputStream) definiteLengthInputStream);
            case 4:
                return new DEROctetString(definiteLengthInputStream.b());
            case 5:
                return DERNull.a;
            case 6:
                return DERObjectIdentifier.b(a(definiteLengthInputStream, bArr));
            case 10:
                return DEREnumerated.b(a(definiteLengthInputStream, bArr));
            case 12:
                return new DERUTF8String(definiteLengthInputStream.b());
            case 18:
                return new DERNumericString(definiteLengthInputStream.b());
            case 19:
                return new DERPrintableString(definiteLengthInputStream.b());
            case VoIP.ERR_SIP_TIMED_OUT /*20*/:
                return new DERT61String(definiteLengthInputStream.b());
            case VoIP.ERR_TRANSPORT_ERROR /*22*/:
                return new DERIA5String(definiteLengthInputStream.b());
            case VoIP.ERR_SOCKET_CONNECTION_BROKE_NET_DOWN /*23*/:
                return new ASN1UTCTime(definiteLengthInputStream.b());
            case VoIP.ERR_SDP_NEGOTIATION_FAILED /*24*/:
                return new ASN1GeneralizedTime(definiteLengthInputStream.b());
            case VoIP.ERR_SOCKET_CONNECTION_TLS_ERROR /*26*/:
                return new DERVisibleString(definiteLengthInputStream.b());
            case VoIP.ERR_SIP_BYE_TIMEOUT /*27*/:
                return new DERGeneralString(definiteLengthInputStream.b());
            case VoIP.ERR_SIP_REGISTER_PORT_MISMATCH /*28*/:
                return new DERUniversalString(definiteLengthInputStream.b());
            case VoIP.ERR_SIP_REGISTER_SOCKET_INTERNAL /*30*/:
                return new DERBMPString(b(definiteLengthInputStream));
            default:
                throw new IOException("unknown tag " + i + " encountered");
        }
    }
}
