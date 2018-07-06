package org.spongycastle.crypto.tls;

import android.support.v4.app.NotificationCompat;
import com.fgmicrotec.mobile.android.fgmag.VoIP;

public abstract class DefaultTlsClient extends AbstractTlsClient {
    public TlsKeyExchange d() {
        switch (this.g) {
            case 1:
            case 2:
            case 4:
            case 5:
            case 10:
            case 47:
            case 53:
            case 59:
            case 60:
            case 61:
            case 65:
            case 132:
            case 150:
            case 156:
            case 157:
            case 49308:
            case 49309:
            case 49312:
            case 49313:
            case 65280:
            case 65281:
            case 65296:
            case 65297:
                return f();
            case 13:
            case 48:
            case 54:
            case 62:
            case 66:
            case 104:
            case 133:
            case 151:
            case 164:
            case 165:
                return b(7);
            case 16:
            case 49:
            case 55:
            case 63:
            case 67:
            case 105:
            case 134:
            case 152:
            case 160:
            case 161:
                return b(9);
            case 19:
            case 50:
            case 56:
            case NotificationCompat.FLAG_FOREGROUND_SERVICE /*64*/:
            case 68:
            case 106:
            case 135:
            case 153:
            case 162:
            case 163:
                return c(3);
            case VoIP.ERR_TRANSPORT_ERROR /*22*/:
            case 51:
            case 57:
            case 69:
            case 103:
            case 107:
            case 136:
            case 154:
            case 158:
            case 159:
            case 49310:
            case 49311:
            case 49314:
            case 49315:
            case 65282:
            case 65283:
            case 65298:
            case 65299:
                return c(5);
            case 49153:
            case 49154:
            case 49155:
            case 49156:
            case 49157:
            case 49189:
            case 49190:
            case 49197:
            case 49198:
                return d(16);
            case 49158:
            case 49159:
            case 49160:
            case 49161:
            case 49162:
            case 49187:
            case 49188:
            case 49195:
            case 49196:
            case 65286:
            case 65287:
            case 65302:
            case 65303:
                return e(17);
            case 49163:
            case 49164:
            case 49165:
            case 49166:
            case 49167:
            case 49193:
            case 49194:
            case 49201:
            case 49202:
                return d(18);
            case 49168:
            case 49169:
            case 49170:
            case 49171:
            case 49172:
            case 49191:
            case 49192:
            case 49199:
            case 49200:
            case 65284:
            case 65285:
            case 65300:
            case 65301:
                return e(19);
            default:
                throw new TlsFatalAlert((short) 80);
        }
    }

    public TlsCipher e() {
        switch (this.g) {
            case 1:
                return this.a.a(this.b, 0, 1);
            case 2:
            case 49153:
            case 49158:
            case 49163:
            case 49168:
                return this.a.a(this.b, 0, 2);
            case 4:
                return this.a.a(this.b, 2, 1);
            case 5:
            case 49154:
            case 49159:
            case 49164:
            case 49169:
                return this.a.a(this.b, 2, 2);
            case 10:
            case 13:
            case 16:
            case 19:
            case VoIP.ERR_TRANSPORT_ERROR /*22*/:
            case 49155:
            case 49160:
            case 49165:
            case 49170:
                return this.a.a(this.b, 7, 2);
            case 47:
            case 48:
            case 49:
            case 50:
            case 51:
            case 49156:
            case 49161:
            case 49166:
            case 49171:
                return this.a.a(this.b, 8, 2);
            case 53:
            case 54:
            case 55:
            case 56:
            case 57:
            case 49157:
            case 49162:
            case 49167:
            case 49172:
                return this.a.a(this.b, 9, 2);
            case 59:
                return this.a.a(this.b, 0, 3);
            case 60:
            case 62:
            case 63:
            case NotificationCompat.FLAG_FOREGROUND_SERVICE /*64*/:
            case 103:
            case 49187:
            case 49189:
            case 49191:
            case 49193:
                return this.a.a(this.b, 8, 3);
            case 61:
            case 104:
            case 105:
            case 106:
            case 107:
                return this.a.a(this.b, 9, 3);
            case 65:
            case 66:
            case 67:
            case 68:
            case 69:
                return this.a.a(this.b, 12, 2);
            case 132:
            case 133:
            case 134:
            case 135:
            case 136:
                return this.a.a(this.b, 13, 2);
            case 150:
            case 151:
            case 152:
            case 153:
            case 154:
                return this.a.a(this.b, 14, 2);
            case 156:
            case 158:
            case 160:
            case 162:
            case 164:
            case 49195:
            case 49197:
            case 49199:
            case 49201:
                return this.a.a(this.b, 10, 0);
            case 157:
            case 159:
            case 161:
            case 163:
            case 165:
            case 49196:
            case 49198:
            case 49200:
            case 49202:
                return this.a.a(this.b, 11, 0);
            case 49188:
            case 49190:
            case 49192:
            case 49194:
                return this.a.a(this.b, 9, 4);
            case 49308:
            case 49310:
                return this.a.a(this.b, 15, 0);
            case 49309:
            case 49311:
                return this.a.a(this.b, 17, 0);
            case 49312:
            case 49314:
                return this.a.a(this.b, 16, 0);
            case 49313:
            case 49315:
                return this.a.a(this.b, 18, 0);
            case 65280:
            case 65282:
            case 65284:
            case 65286:
                return this.a.a(this.b, 100, 2);
            case 65281:
            case 65283:
            case 65285:
            case 65287:
                return this.a.a(this.b, 101, 2);
            case 65296:
            case 65298:
            case 65300:
            case 65302:
                return this.a.a(this.b, 100, 100);
            case 65297:
            case 65299:
            case 65301:
            case 65303:
                return this.a.a(this.b, 101, 100);
            default:
                throw new TlsFatalAlert((short) 80);
        }
    }

    protected TlsKeyExchange b(int i) {
        return new TlsDHKeyExchange(i, this.c, null);
    }

    protected TlsKeyExchange c(int i) {
        return new TlsDHEKeyExchange(i, this.c, null);
    }

    protected TlsKeyExchange d(int i) {
        return new TlsECDHKeyExchange(i, this.c, this.d, this.e, this.f);
    }

    protected TlsKeyExchange e(int i) {
        return new TlsECDHEKeyExchange(i, this.c, this.d, this.e, this.f);
    }

    protected TlsKeyExchange f() {
        return new TlsRSAKeyExchange(this.c);
    }
}
