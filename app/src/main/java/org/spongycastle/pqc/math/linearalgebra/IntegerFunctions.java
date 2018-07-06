package org.spongycastle.pqc.math.linearalgebra;

import java.math.BigInteger;
import java.security.SecureRandom;

public final class IntegerFunctions {
    private static final BigInteger a = BigInteger.valueOf(0);
    private static final BigInteger b = BigInteger.valueOf(1);
    private static final BigInteger c = BigInteger.valueOf(2);
    private static final BigInteger d = BigInteger.valueOf(4);
    private static final int[] e = new int[]{3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41};
    private static SecureRandom f = null;
    private static final int[] g = new int[]{0, 1, 0, -1, 0, -1, 0, 1};

    private IntegerFunctions() {
    }

    public static int a(int i) {
        if (i == 0) {
            return 1;
        }
        if (i < 0) {
            i = -i;
        }
        int i2 = 0;
        for (i = 
/*
Method generation error in method: org.spongycastle.pqc.math.linearalgebra.IntegerFunctions.a(int):int, dex: classes.dex
jadx.core.utils.exceptions.CodegenException: Error generate insn: PHI: (r1_2 'i' int) = (r1_0 'i' int), (r1_1 'i' int) binds: {(r1_0 'i' int)=B:2:0x0004, (r1_1 'i' int)=B:3:0x0006} in method: org.spongycastle.pqc.math.linearalgebra.IntegerFunctions.a(int):int, dex: classes.dex
	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:226)
	at jadx.core.codegen.RegionGen.makeLoop(RegionGen.java:184)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:61)
	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:87)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:53)
	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:87)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:53)
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
	... 19 more

*/

        public static BigInteger a(int i, int i2) {
            BigInteger bigInteger = b;
            if (i != 0) {
                if (i2 > (i >>> 1)) {
                    i2 = i - i2;
                }
                int i3 = 1;
                while (i3 <= i2) {
                    BigInteger divide = bigInteger.multiply(BigInteger.valueOf((long) (i - (i3 - 1)))).divide(BigInteger.valueOf((long) i3));
                    i3++;
                    bigInteger = divide;
                }
                return bigInteger;
            } else if (i2 == 0) {
                return bigInteger;
            } else {
                return a;
            }
        }
    }
