package org.spongycastle.crypto.tls;

import java.security.SecureRandom;
import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.params.ParametersWithIV;
import org.spongycastle.util.Arrays;

public class TlsBlockCipher implements TlsCipher {
    private static boolean h = false;
    protected TlsContext a;
    protected byte[] b = new byte[256];
    protected boolean c;
    protected BlockCipher d;
    protected BlockCipher e;
    protected TlsMac f;
    protected TlsMac g;

    public TlsBlockCipher(TlsContext tlsContext, BlockCipher blockCipher, BlockCipher blockCipher2, Digest digest, Digest digest2, int i) {
        int i2;
        byte[] bArr;
        this.a = tlsContext;
        tlsContext.a().nextBytes(this.b);
        this.c = TlsUtils.b(tlsContext);
        int b = ((i * 2) + digest.b()) + digest2.b();
        if (this.c) {
            i2 = b;
        } else {
            i2 = b + (blockCipher.b() + blockCipher2.b());
        }
        byte[] a = TlsUtils.a(tlsContext, i2);
        TlsMac tlsMac = new TlsMac(tlsContext, digest, a, 0, digest.b());
        int b2 = 0 + digest.b();
        TlsMac tlsMac2 = new TlsMac(tlsContext, digest2, a, b2, digest2.b());
        int b3 = digest2.b() + b2;
        CipherParameters keyParameter = new KeyParameter(a, b3, i);
        b3 += i;
        CipherParameters keyParameter2 = new KeyParameter(a, b3, i);
        int i3 = b3 + i;
        if (this.c) {
            a = new byte[blockCipher.b()];
            bArr = new byte[blockCipher2.b()];
            b3 = i3;
        } else {
            bArr = Arrays.a(a, i3, blockCipher.b() + i3);
            i3 += blockCipher.b();
            byte[] a2 = Arrays.a(a, i3, blockCipher2.b() + i3);
            b3 = blockCipher2.b() + i3;
            a = bArr;
            bArr = a2;
        }
        if (b3 != i2) {
            throw new TlsFatalAlert((short) 80);
        }
        CipherParameters parametersWithIV;
        CipherParameters parametersWithIV2;
        if (tlsContext.e()) {
            this.f = tlsMac2;
            this.g = tlsMac;
            this.d = blockCipher2;
            this.e = blockCipher;
            parametersWithIV = new ParametersWithIV(keyParameter2, bArr);
            parametersWithIV2 = new ParametersWithIV(keyParameter, a);
        } else {
            this.f = tlsMac;
            this.g = tlsMac2;
            this.d = blockCipher;
            this.e = blockCipher2;
            parametersWithIV = new ParametersWithIV(keyParameter, a);
            parametersWithIV2 = new ParametersWithIV(keyParameter2, bArr);
        }
        this.d.a(true, parametersWithIV);
        this.e.a(false, parametersWithIV2);
    }

    public byte[] a(long j, short s, byte[] bArr, int i, int i2) {
        int i3;
        int i4;
        int b = this.d.b();
        int a = this.f.a();
        ProtocolVersion d = this.a.d();
        if (h) {
            i3 = i2;
        } else {
            i3 = i2 + a;
        }
        i3 = (b - 1) - (i3 % b);
        if (!(d.c() || d.d())) {
            i3 += a(this.a.a(), (255 - i3) / b) * b;
        }
        a = ((a + i2) + i3) + 1;
        if (this.c) {
            a += b;
        }
        Object obj = new byte[a];
        if (this.c) {
            Object obj2 = new byte[b];
            this.a.a().nextBytes(obj2);
            this.d.a(true, new ParametersWithIV(null, obj2));
            System.arraycopy(obj2, 0, obj, 0, b);
            i4 = 0 + b;
        } else {
            i4 = 0;
        }
        System.arraycopy(bArr, i, obj, i4, i2);
        int i5 = i4 + i2;
        if (h) {
            a = i5;
        } else {
            Object a2 = this.f.a(j, s, bArr, i, i2);
            System.arraycopy(a2, 0, obj, i5, a2.length);
            a = a2.length + i5;
        }
        int i6 = a;
        a = 0;
        while (a <= i3) {
            int i7 = i6 + 1;
            obj[i6] = (byte) i3;
            a++;
            i6 = i7;
        }
        while (i4 < i6) {
            this.d.a(obj, i4, obj, i4);
            i4 += b;
        }
        if (h) {
            Object a3 = this.f.a(j, s, obj, 0, i6);
            System.arraycopy(a3, 0, obj, i6, a3.length);
            i3 = a3.length + i6;
        }
        return obj;
    }

    public byte[] b(long j, short s, byte[] bArr, int i, int i2) {
        int i3;
        int b = this.e.b();
        int a = this.g.a();
        if (h) {
            i3 = b + a;
        } else {
            i3 = Math.max(b, a + 1);
        }
        if (this.c) {
            i3 += b;
        }
        if (i2 < i3) {
            throw new TlsFatalAlert((short) 50);
        }
        if (h) {
            i3 = i2 - a;
        } else {
            i3 = i2;
        }
        if (i3 % b != 0) {
            throw new TlsFatalAlert((short) 21);
        }
        int i4;
        int i5;
        int i6;
        if (h) {
            i4 = i + i2;
            if ((!Arrays.b(this.g.a(j, s, bArr, i, i2 - a), Arrays.a(bArr, i4 - a, i4)) ? 1 : null) != null) {
                throw new TlsFatalAlert((short) 20);
            }
        }
        if (this.c) {
            this.e.a(false, new ParametersWithIV(null, bArr, i, b));
            i5 = i + b;
            i6 = i3 - b;
        } else {
            i6 = i3;
            i5 = i;
        }
        for (i3 = 0; i3 < i6; i3 += b) {
            this.e.a(bArr, i5 + i3, bArr, i5 + i3);
        }
        i4 = a(bArr, i5, i6, b, h ? 0 : a);
        b = i6 - i4;
        if (!h) {
            b -= a;
            i3 = i5 + b;
            if ((!Arrays.b(this.g.a(j, s, bArr, i5, b, i6 - a, this.b), Arrays.a(bArr, i3, i3 + a)) ? 1 : null) != null || i4 == 0) {
                throw new TlsFatalAlert((short) 20);
            }
        }
        return Arrays.a(bArr, i5, i5 + b);
    }

    protected int a(byte[] bArr, int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        int i7 = i + i2;
        byte b = bArr[i7 - 1];
        int i8 = (b & 255) + 1;
        if ((!TlsUtils.a(this.a) || i8 <= i3) && i4 + i8 <= i2) {
            i5 = i7 - i8;
            int i9 = 0;
            while (true) {
                i6 = i5 + 1;
                i5 = (byte) ((bArr[i5] ^ b) | i9);
                if (i6 >= i7) {
                    break;
                }
                i9 = i5;
                i5 = i6;
            }
            if (i5 != 0) {
                i6 = i8;
                i8 = 0;
            } else {
                i6 = i8;
            }
        } else {
            i5 = (byte) 0;
            i6 = 0;
            i8 = 0;
        }
        byte[] bArr2 = this.b;
        for (i6 = 
/*
Method generation error in method: org.spongycastle.crypto.tls.TlsBlockCipher.a(byte[], int, int, int, int):int, dex: classes.dex
jadx.core.utils.exceptions.CodegenException: Error generate insn: PHI: (r1_4 'i6' int) = (r1_1 'i6' int), (r1_2 'i6' int), (r1_3 'i6' int) binds: {(r1_1 'i6' int)=B:14:0x0039, (r1_2 'i6' int)=B:17:0x0043, (r1_3 'i6' int)=B:5:0x0019} in method: org.spongycastle.crypto.tls.TlsBlockCipher.a(byte[], int, int, int, int):int, dex: classes.dex
	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:226)
	at jadx.core.codegen.RegionGen.makeLoop(RegionGen.java:184)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:61)
	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:87)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:53)
	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:186)
	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:320)
	at jadx.core.codegen.ClassGen.addMethods(ClassGen.java:257)
	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:220)
	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:110)
	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:75)
	at jadx.core.codegen.CodeGen.visit(CodeGen.java:12)
	at jadx.core.ProcessClass.process(ProcessClass.java:40)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
Caused by: jadx.core.utils.exceptions.CodegenException: PHI can be used only in fallback mode
	at jadx.core.codegen.InsnGen.fallbackOnlyInsn(InsnGen.java:537)
	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:509)
	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:220)
	... 15 more

*/

        protected int a(SecureRandom secureRandom, int i) {
            return Math.min(a(secureRandom.nextInt()), i);
        }

        protected int a(int i) {
            if (i == 0) {
                return 32;
            }
            int i2 = 0;
            while ((i & 1) == 0) {
                i2++;
                i >>= 1;
            }
            return i2;
        }
    }
