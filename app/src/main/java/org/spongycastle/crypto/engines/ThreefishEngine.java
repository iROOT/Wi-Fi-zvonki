package org.spongycastle.crypto.engines;

import net.hockeyapp.android.k;
import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.params.TweakableBlockCipherParameters;

public class ThreefishEngine implements BlockCipher {
    private static int[] a = new int[80];
    private static int[] b = new int[a.length];
    private static int[] c = new int[a.length];
    private static int[] d = new int[a.length];
    private int e;
    private int f;
    private long[] g;
    private long[] h = new long[5];
    private long[] i;
    private ThreefishCipher j;
    private boolean k;

    private static abstract class ThreefishCipher {
        protected final long[] a;
        protected final long[] b;

        abstract void a(long[] jArr, long[] jArr2);

        abstract void b(long[] jArr, long[] jArr2);

        protected ThreefishCipher(long[] jArr, long[] jArr2) {
            this.b = jArr;
            this.a = jArr2;
        }
    }

    private static final class Threefish1024Cipher extends ThreefishCipher {
        public Threefish1024Cipher(long[] jArr, long[] jArr2) {
            super(jArr, jArr2);
        }

        void a(long[] jArr, long[] jArr2) {
            long[] jArr3 = this.b;
            long[] jArr4 = this.a;
            int[] g = ThreefishEngine.b;
            int[] e = ThreefishEngine.d;
            if (jArr3.length != 33) {
                throw new IllegalArgumentException();
            } else if (jArr4.length != 5) {
                throw new IllegalArgumentException();
            } else {
                long j = jArr[0];
                long j2 = jArr[1];
                long j3 = jArr[2];
                long j4 = jArr[3];
                long j5 = jArr[4];
                long j6 = jArr[5];
                long j7 = jArr[6];
                long j8 = jArr[7];
                long j9 = jArr[8];
                long j10 = jArr[9];
                long j11 = jArr[10];
                long j12 = jArr[11];
                long j13 = jArr[12];
                long j14 = jArr[13];
                long j15 = jArr[14];
                long j16 = j + jArr3[0];
                long j17 = j2 + jArr3[1];
                long j18 = j3 + jArr3[2];
                long j19 = j4 + jArr3[3];
                long j20 = j5 + jArr3[4];
                long j21 = j6 + jArr3[5];
                long j22 = j7 + jArr3[6];
                long j23 = j8 + jArr3[7];
                j8 = j9 + jArr3[8];
                j7 = j10 + jArr3[9];
                j6 = j11 + jArr3[10];
                j5 = j12 + jArr3[11];
                j4 = j13 + jArr3[12];
                j3 = j14 + (jArr3[13] + jArr4[0]);
                j2 = j15 + (jArr3[14] + jArr4[1]);
                j = jArr3[15] + jArr[15];
                for (int i = 1; i < 20; i += 2) {
                    int i2 = g[i];
                    int i3 = e[i];
                    j16 += j17;
                    j17 = ThreefishEngine.a(j17, 24, j16);
                    j18 += j19;
                    j19 = ThreefishEngine.a(j19, 13, j18);
                    j20 += j21;
                    j21 = ThreefishEngine.a(j21, 8, j20);
                    j22 += j23;
                    j23 = ThreefishEngine.a(j23, 47, j22);
                    j8 += j7;
                    j7 = ThreefishEngine.a(j7, 8, j8);
                    j6 += j5;
                    j5 = ThreefishEngine.a(j5, 17, j6);
                    j4 += j3;
                    j3 = ThreefishEngine.a(j3, 22, j4);
                    j2 += j;
                    j = ThreefishEngine.a(j, 37, j2);
                    j16 += j7;
                    j7 = ThreefishEngine.a(j7, 38, j16);
                    j18 += j3;
                    j3 = ThreefishEngine.a(j3, 19, j18);
                    j22 += j5;
                    j5 = ThreefishEngine.a(j5, 10, j22);
                    j20 += j;
                    j = ThreefishEngine.a(j, 55, j20);
                    j6 += j23;
                    j23 = ThreefishEngine.a(j23, 49, j6);
                    j4 += j19;
                    j19 = ThreefishEngine.a(j19, 18, j4);
                    j2 += j21;
                    j21 = ThreefishEngine.a(j21, 23, j2);
                    j8 += j17;
                    j17 = ThreefishEngine.a(j17, 52, j8);
                    j16 += j23;
                    j23 = ThreefishEngine.a(j23, 33, j16);
                    j18 += j21;
                    j21 = ThreefishEngine.a(j21, 4, j18);
                    j20 += j19;
                    j19 = ThreefishEngine.a(j19, 51, j20);
                    j22 += j17;
                    j17 = ThreefishEngine.a(j17, 13, j22);
                    j4 += j;
                    j = ThreefishEngine.a(j, 34, j4);
                    j2 += j3;
                    j3 = ThreefishEngine.a(j3, 41, j2);
                    j8 += j5;
                    j5 = ThreefishEngine.a(j5, 59, j8);
                    j6 += j7;
                    j7 = ThreefishEngine.a(j7, 17, j6);
                    j16 += j;
                    j = ThreefishEngine.a(j, 5, j16);
                    j18 += j5;
                    j5 = ThreefishEngine.a(j5, 20, j18);
                    j22 += j3;
                    j3 = ThreefishEngine.a(j3, 48, j22);
                    j20 += j7;
                    j7 = ThreefishEngine.a(j7, 41, j20);
                    j2 += j17;
                    j17 = ThreefishEngine.a(j17, 47, j2);
                    j8 += j21;
                    j21 = ThreefishEngine.a(j21, 28, j8);
                    j6 += j19;
                    j19 = ThreefishEngine.a(j19, 16, j6);
                    j4 += j23;
                    j17 += jArr3[i2 + 1];
                    j18 += jArr3[i2 + 2];
                    j19 += jArr3[i2 + 3];
                    j20 += jArr3[i2 + 4];
                    j21 += jArr3[i2 + 5];
                    j22 += jArr3[i2 + 6];
                    j23 = ThreefishEngine.a(j23, 25, j4) + jArr3[i2 + 7];
                    j8 += jArr3[i2 + 8];
                    j7 += jArr3[i2 + 9];
                    j6 += jArr3[i2 + 10];
                    j5 += jArr3[i2 + 11];
                    j4 += jArr3[i2 + 12];
                    j3 += jArr3[i2 + 13] + jArr4[i3];
                    j2 += jArr3[i2 + 14] + jArr4[i3 + 1];
                    j += jArr3[i2 + 15] + ((long) i);
                    j16 = (j16 + jArr3[i2]) + j17;
                    j17 = ThreefishEngine.a(j17, 41, j16);
                    j18 += j19;
                    j19 = ThreefishEngine.a(j19, 9, j18);
                    j20 += j21;
                    j21 = ThreefishEngine.a(j21, 37, j20);
                    j22 += j23;
                    j23 = ThreefishEngine.a(j23, 31, j22);
                    j8 += j7;
                    j7 = ThreefishEngine.a(j7, 12, j8);
                    j6 += j5;
                    j5 = ThreefishEngine.a(j5, 47, j6);
                    j4 += j3;
                    j3 = ThreefishEngine.a(j3, 44, j4);
                    j2 += j;
                    j = ThreefishEngine.a(j, 30, j2);
                    j16 += j7;
                    j7 = ThreefishEngine.a(j7, 16, j16);
                    j18 += j3;
                    j3 = ThreefishEngine.a(j3, 34, j18);
                    j22 += j5;
                    j5 = ThreefishEngine.a(j5, 56, j22);
                    j20 += j;
                    j = ThreefishEngine.a(j, 51, j20);
                    j6 += j23;
                    j23 = ThreefishEngine.a(j23, 4, j6);
                    j4 += j19;
                    j19 = ThreefishEngine.a(j19, 53, j4);
                    j2 += j21;
                    j21 = ThreefishEngine.a(j21, 42, j2);
                    j8 += j17;
                    j17 = ThreefishEngine.a(j17, 41, j8);
                    j16 += j23;
                    j23 = ThreefishEngine.a(j23, 31, j16);
                    j18 += j21;
                    j21 = ThreefishEngine.a(j21, 44, j18);
                    j20 += j19;
                    j19 = ThreefishEngine.a(j19, 47, j20);
                    j22 += j17;
                    j17 = ThreefishEngine.a(j17, 46, j22);
                    j4 += j;
                    j = ThreefishEngine.a(j, 19, j4);
                    j2 += j3;
                    j3 = ThreefishEngine.a(j3, 42, j2);
                    j8 += j5;
                    j5 = ThreefishEngine.a(j5, 44, j8);
                    j6 += j7;
                    j7 = ThreefishEngine.a(j7, 25, j6);
                    j16 += j;
                    j = ThreefishEngine.a(j, 9, j16);
                    j18 += j5;
                    j5 = ThreefishEngine.a(j5, 48, j18);
                    j22 += j3;
                    j3 = ThreefishEngine.a(j3, 35, j22);
                    j20 += j7;
                    j7 = ThreefishEngine.a(j7, 52, j20);
                    j2 += j17;
                    j17 = ThreefishEngine.a(j17, 23, j2);
                    j8 += j21;
                    j21 = ThreefishEngine.a(j21, 31, j8);
                    j6 += j19;
                    j19 = ThreefishEngine.a(j19, 37, j6);
                    j4 += j23;
                    j16 += jArr3[i2 + 1];
                    j17 += jArr3[i2 + 2];
                    j18 += jArr3[i2 + 3];
                    j19 += jArr3[i2 + 4];
                    j20 += jArr3[i2 + 5];
                    j21 += jArr3[i2 + 6];
                    j22 += jArr3[i2 + 7];
                    j23 = ThreefishEngine.a(j23, 20, j4) + jArr3[i2 + 8];
                    j8 += jArr3[i2 + 9];
                    j7 += jArr3[i2 + 10];
                    j6 += jArr3[i2 + 11];
                    j5 += jArr3[i2 + 12];
                    j4 += jArr3[i2 + 13];
                    j3 += jArr3[i2 + 14] + jArr4[i3 + 1];
                    j2 += jArr4[i3 + 2] + jArr3[i2 + 15];
                    j += (jArr3[i2 + 16] + ((long) i)) + 1;
                }
                jArr2[0] = j16;
                jArr2[1] = j17;
                jArr2[2] = j18;
                jArr2[3] = j19;
                jArr2[4] = j20;
                jArr2[5] = j21;
                jArr2[6] = j22;
                jArr2[7] = j23;
                jArr2[8] = j8;
                jArr2[9] = j7;
                jArr2[10] = j6;
                jArr2[11] = j5;
                jArr2[12] = j4;
                jArr2[13] = j3;
                jArr2[14] = j2;
                jArr2[15] = j;
            }
        }

        void b(long[] jArr, long[] jArr2) {
            long[] jArr3 = this.b;
            long[] jArr4 = this.a;
            int[] g = ThreefishEngine.b;
            int[] e = ThreefishEngine.d;
            if (jArr3.length != 33) {
                throw new IllegalArgumentException();
            } else if (jArr4.length != 5) {
                throw new IllegalArgumentException();
            } else {
                long j = jArr[0];
                long j2 = jArr[1];
                long j3 = jArr[2];
                long j4 = jArr[3];
                long j5 = jArr[4];
                long j6 = jArr[5];
                long j7 = jArr[6];
                long j8 = jArr[7];
                long j9 = jArr[8];
                long j10 = jArr[9];
                long j11 = jArr[10];
                long j12 = jArr[11];
                long j13 = jArr[12];
                long j14 = jArr[13];
                long j15 = jArr[14];
                long j16 = jArr[15];
                for (int i = 19; i >= 1; i -= 2) {
                    int i2 = g[i];
                    int i3 = e[i];
                    j -= jArr3[i2 + 1];
                    j2 -= jArr3[i2 + 2];
                    j3 -= jArr3[i2 + 3];
                    j4 -= jArr3[i2 + 4];
                    j5 -= jArr3[i2 + 5];
                    j6 -= jArr3[i2 + 6];
                    j7 -= jArr3[i2 + 7];
                    j8 -= jArr3[i2 + 8];
                    j9 -= jArr3[i2 + 9];
                    j10 -= jArr3[i2 + 10];
                    j11 -= jArr3[i2 + 11];
                    j12 -= jArr3[i2 + 12];
                    j13 -= jArr3[i2 + 13];
                    j14 -= jArr3[i2 + 14] + jArr4[i3 + 1];
                    j15 -= jArr3[i2 + 15] + jArr4[i3 + 2];
                    j16 = ThreefishEngine.b(j16 - ((jArr3[i2 + 16] + ((long) i)) + 1), 9, j);
                    j -= j16;
                    j12 = ThreefishEngine.b(j12, 48, j3);
                    j3 -= j12;
                    j14 = ThreefishEngine.b(j14, 35, j7);
                    j7 -= j14;
                    j10 = ThreefishEngine.b(j10, 52, j5);
                    j5 -= j10;
                    j2 = ThreefishEngine.b(j2, 23, j15);
                    j15 -= j2;
                    j6 = ThreefishEngine.b(j6, 31, j9);
                    j9 -= j6;
                    j4 = ThreefishEngine.b(j4, 37, j11);
                    j11 -= j4;
                    j8 = ThreefishEngine.b(j8, 20, j13);
                    j13 -= j8;
                    j8 = ThreefishEngine.b(j8, 31, j);
                    j -= j8;
                    j6 = ThreefishEngine.b(j6, 44, j3);
                    j3 -= j6;
                    j4 = ThreefishEngine.b(j4, 47, j5);
                    j5 -= j4;
                    j2 = ThreefishEngine.b(j2, 46, j7);
                    j7 -= j2;
                    j16 = ThreefishEngine.b(j16, 19, j13);
                    j13 -= j16;
                    j14 = ThreefishEngine.b(j14, 42, j15);
                    j15 -= j14;
                    j12 = ThreefishEngine.b(j12, 44, j9);
                    j9 -= j12;
                    j10 = ThreefishEngine.b(j10, 25, j11);
                    j11 -= j10;
                    j10 = ThreefishEngine.b(j10, 16, j);
                    j -= j10;
                    j14 = ThreefishEngine.b(j14, 34, j3);
                    j3 -= j14;
                    j12 = ThreefishEngine.b(j12, 56, j7);
                    j7 -= j12;
                    j16 = ThreefishEngine.b(j16, 51, j5);
                    j5 -= j16;
                    j8 = ThreefishEngine.b(j8, 4, j11);
                    j11 -= j8;
                    j4 = ThreefishEngine.b(j4, 53, j13);
                    j13 -= j4;
                    j6 = ThreefishEngine.b(j6, 42, j15);
                    j15 -= j6;
                    j2 = ThreefishEngine.b(j2, 41, j9);
                    j9 -= j2;
                    j2 = ThreefishEngine.b(j2, 41, j);
                    j -= j2;
                    j4 = ThreefishEngine.b(j4, 9, j3);
                    j3 -= j4;
                    j6 = ThreefishEngine.b(j6, 37, j5);
                    j5 -= j6;
                    j8 = ThreefishEngine.b(j8, 31, j7);
                    j7 -= j8;
                    j10 = ThreefishEngine.b(j10, 12, j9);
                    j9 -= j10;
                    j12 = ThreefishEngine.b(j12, 47, j11);
                    j11 -= j12;
                    j14 = ThreefishEngine.b(j14, 44, j13);
                    j13 -= j14;
                    j16 = ThreefishEngine.b(j16, 30, j15);
                    j -= jArr3[i2];
                    j2 -= jArr3[i2 + 1];
                    j3 -= jArr3[i2 + 2];
                    j4 -= jArr3[i2 + 3];
                    j5 -= jArr3[i2 + 4];
                    j6 -= jArr3[i2 + 5];
                    j7 -= jArr3[i2 + 6];
                    j8 -= jArr3[i2 + 7];
                    j9 -= jArr3[i2 + 8];
                    j10 -= jArr3[i2 + 9];
                    j11 -= jArr3[i2 + 10];
                    j12 -= jArr3[i2 + 11];
                    j13 -= jArr3[i2 + 12];
                    j14 -= jArr3[i2 + 13] + jArr4[i3];
                    j15 = (j15 - j16) - (jArr4[i3 + 1] + jArr3[i2 + 14]);
                    j16 = ThreefishEngine.b(j16 - (jArr3[i2 + 15] + ((long) i)), 5, j);
                    j -= j16;
                    j12 = ThreefishEngine.b(j12, 20, j3);
                    j3 -= j12;
                    j14 = ThreefishEngine.b(j14, 48, j7);
                    j7 -= j14;
                    j10 = ThreefishEngine.b(j10, 41, j5);
                    j5 -= j10;
                    j2 = ThreefishEngine.b(j2, 47, j15);
                    j15 -= j2;
                    j6 = ThreefishEngine.b(j6, 28, j9);
                    j9 -= j6;
                    j4 = ThreefishEngine.b(j4, 16, j11);
                    j11 -= j4;
                    j8 = ThreefishEngine.b(j8, 25, j13);
                    j13 -= j8;
                    j8 = ThreefishEngine.b(j8, 33, j);
                    j -= j8;
                    j6 = ThreefishEngine.b(j6, 4, j3);
                    j3 -= j6;
                    j4 = ThreefishEngine.b(j4, 51, j5);
                    j5 -= j4;
                    j2 = ThreefishEngine.b(j2, 13, j7);
                    j7 -= j2;
                    j16 = ThreefishEngine.b(j16, 34, j13);
                    j13 -= j16;
                    j14 = ThreefishEngine.b(j14, 41, j15);
                    j15 -= j14;
                    j12 = ThreefishEngine.b(j12, 59, j9);
                    j9 -= j12;
                    j10 = ThreefishEngine.b(j10, 17, j11);
                    j11 -= j10;
                    j10 = ThreefishEngine.b(j10, 38, j);
                    j -= j10;
                    j14 = ThreefishEngine.b(j14, 19, j3);
                    j3 -= j14;
                    j12 = ThreefishEngine.b(j12, 10, j7);
                    j7 -= j12;
                    j16 = ThreefishEngine.b(j16, 55, j5);
                    j5 -= j16;
                    j8 = ThreefishEngine.b(j8, 49, j11);
                    j11 -= j8;
                    j4 = ThreefishEngine.b(j4, 18, j13);
                    j13 -= j4;
                    j6 = ThreefishEngine.b(j6, 23, j15);
                    j15 -= j6;
                    j2 = ThreefishEngine.b(j2, 52, j9);
                    j9 -= j2;
                    j2 = ThreefishEngine.b(j2, 24, j);
                    j -= j2;
                    j4 = ThreefishEngine.b(j4, 13, j3);
                    j3 -= j4;
                    j6 = ThreefishEngine.b(j6, 8, j5);
                    j5 -= j6;
                    j8 = ThreefishEngine.b(j8, 47, j7);
                    j7 -= j8;
                    j10 = ThreefishEngine.b(j10, 8, j9);
                    j9 -= j10;
                    j12 = ThreefishEngine.b(j12, 17, j11);
                    j11 -= j12;
                    j14 = ThreefishEngine.b(j14, 22, j13);
                    j13 -= j14;
                    j16 = ThreefishEngine.b(j16, 37, j15);
                    j15 -= j16;
                }
                j -= jArr3[0];
                j2 -= jArr3[1];
                j3 -= jArr3[2];
                j4 -= jArr3[3];
                j5 -= jArr3[4];
                j6 -= jArr3[5];
                j7 -= jArr3[6];
                j8 -= jArr3[7];
                j9 -= jArr3[8];
                j10 -= jArr3[9];
                j11 -= jArr3[10];
                j12 -= jArr3[11];
                j13 -= jArr3[12];
                j14 -= jArr3[13] + jArr4[0];
                j15 -= jArr4[1] + jArr3[14];
                long j17 = j16 - jArr3[15];
                jArr2[0] = j;
                jArr2[1] = j2;
                jArr2[2] = j3;
                jArr2[3] = j4;
                jArr2[4] = j5;
                jArr2[5] = j6;
                jArr2[6] = j7;
                jArr2[7] = j8;
                jArr2[8] = j9;
                jArr2[9] = j10;
                jArr2[10] = j11;
                jArr2[11] = j12;
                jArr2[12] = j13;
                jArr2[13] = j14;
                jArr2[14] = j15;
                jArr2[15] = j17;
            }
        }
    }

    private static final class Threefish256Cipher extends ThreefishCipher {
        public Threefish256Cipher(long[] jArr, long[] jArr2) {
            super(jArr, jArr2);
        }

        void a(long[] jArr, long[] jArr2) {
            long[] jArr3 = this.b;
            long[] jArr4 = this.a;
            int[] d = ThreefishEngine.c;
            int[] e = ThreefishEngine.d;
            if (jArr3.length != 9) {
                throw new IllegalArgumentException();
            } else if (jArr4.length != 5) {
                throw new IllegalArgumentException();
            } else {
                long j = jArr[0];
                long j2 = jArr[1];
                long j3 = jArr[2];
                long j4 = j + jArr3[0];
                long j5 = j2 + (jArr3[1] + jArr4[0]);
                j2 = j3 + (jArr3[2] + jArr4[1]);
                j = jArr3[3] + jArr[3];
                for (int i = 1; i < 18; i += 2) {
                    int i2 = d[i];
                    int i3 = e[i];
                    j4 += j5;
                    j5 = ThreefishEngine.a(j5, 14, j4);
                    j2 += j;
                    j = ThreefishEngine.a(j, 16, j2);
                    j4 += j;
                    j = ThreefishEngine.a(j, 52, j4);
                    j2 += j5;
                    j5 = ThreefishEngine.a(j5, 57, j2);
                    j4 += j5;
                    j5 = ThreefishEngine.a(j5, 23, j4);
                    j2 += j;
                    j = ThreefishEngine.a(j, 40, j2);
                    j4 += j;
                    j = ThreefishEngine.a(j, 5, j4);
                    j2 += j5;
                    j5 = ThreefishEngine.a(j5, 37, j2) + (jArr3[i2 + 1] + jArr4[i3]);
                    j2 += jArr3[i2 + 2] + jArr4[i3 + 1];
                    j += jArr3[i2 + 3] + ((long) i);
                    j4 = (j4 + jArr3[i2]) + j5;
                    j5 = ThreefishEngine.a(j5, 25, j4);
                    j2 += j;
                    j = ThreefishEngine.a(j, 33, j2);
                    j4 += j;
                    j = ThreefishEngine.a(j, 46, j4);
                    j2 += j5;
                    j5 = ThreefishEngine.a(j5, 12, j2);
                    j4 += j5;
                    j5 = ThreefishEngine.a(j5, 58, j4);
                    j2 += j;
                    j = ThreefishEngine.a(j, 22, j2);
                    j4 += j;
                    j = ThreefishEngine.a(j, 32, j4);
                    j2 += j5;
                    j4 += jArr3[i2 + 1];
                    j5 = ThreefishEngine.a(j5, 32, j2) + (jArr3[i2 + 2] + jArr4[i3 + 1]);
                    j2 += jArr4[i3 + 2] + jArr3[i2 + 3];
                    j += (jArr3[i2 + 4] + ((long) i)) + 1;
                }
                jArr2[0] = j4;
                jArr2[1] = j5;
                jArr2[2] = j2;
                jArr2[3] = j;
            }
        }

        void b(long[] jArr, long[] jArr2) {
            long[] jArr3 = this.b;
            long[] jArr4 = this.a;
            int[] d = ThreefishEngine.c;
            int[] e = ThreefishEngine.d;
            if (jArr3.length != 9) {
                throw new IllegalArgumentException();
            } else if (jArr4.length != 5) {
                throw new IllegalArgumentException();
            } else {
                long j = jArr[0];
                long j2 = jArr[1];
                long j3 = jArr[2];
                long j4 = jArr[3];
                for (int i = 17; i >= 1; i -= 2) {
                    int i2 = d[i];
                    int i3 = e[i];
                    j -= jArr3[i2 + 1];
                    j2 -= jArr3[i2 + 2] + jArr4[i3 + 1];
                    j3 -= jArr3[i2 + 3] + jArr4[i3 + 2];
                    j4 = ThreefishEngine.b(j4 - ((jArr3[i2 + 4] + ((long) i)) + 1), 32, j);
                    j -= j4;
                    j2 = ThreefishEngine.b(j2, 32, j3);
                    j3 -= j2;
                    j2 = ThreefishEngine.b(j2, 58, j);
                    j -= j2;
                    j4 = ThreefishEngine.b(j4, 22, j3);
                    j3 -= j4;
                    j4 = ThreefishEngine.b(j4, 46, j);
                    j -= j4;
                    j2 = ThreefishEngine.b(j2, 12, j3);
                    j3 -= j2;
                    j2 = ThreefishEngine.b(j2, 25, j);
                    j -= j2;
                    j4 = ThreefishEngine.b(j4, 33, j3);
                    j -= jArr3[i2];
                    j2 -= jArr3[i2 + 1] + jArr4[i3];
                    j3 = (j3 - j4) - (jArr4[i3 + 1] + jArr3[i2 + 2]);
                    j4 = ThreefishEngine.b(j4 - (jArr3[i2 + 3] + ((long) i)), 5, j);
                    j -= j4;
                    j2 = ThreefishEngine.b(j2, 37, j3);
                    j3 -= j2;
                    j2 = ThreefishEngine.b(j2, 23, j);
                    j -= j2;
                    j4 = ThreefishEngine.b(j4, 40, j3);
                    j3 -= j4;
                    j4 = ThreefishEngine.b(j4, 52, j);
                    j -= j4;
                    j2 = ThreefishEngine.b(j2, 57, j3);
                    j3 -= j2;
                    j2 = ThreefishEngine.b(j2, 14, j);
                    j -= j2;
                    j4 = ThreefishEngine.b(j4, 16, j3);
                    j3 -= j4;
                }
                j -= jArr3[0];
                j2 -= jArr3[1] + jArr4[0];
                j3 -= jArr4[1] + jArr3[2];
                long j5 = j4 - jArr3[3];
                jArr2[0] = j;
                jArr2[1] = j2;
                jArr2[2] = j3;
                jArr2[3] = j5;
            }
        }
    }

    private static final class Threefish512Cipher extends ThreefishCipher {
        protected Threefish512Cipher(long[] jArr, long[] jArr2) {
            super(jArr, jArr2);
        }

        public void a(long[] jArr, long[] jArr2) {
            long[] jArr3 = this.b;
            long[] jArr4 = this.a;
            int[] f = ThreefishEngine.a;
            int[] e = ThreefishEngine.d;
            if (jArr3.length != 17) {
                throw new IllegalArgumentException();
            } else if (jArr4.length != 5) {
                throw new IllegalArgumentException();
            } else {
                long j = jArr[0];
                long j2 = jArr[1];
                long j3 = jArr[2];
                long j4 = jArr[3];
                long j5 = jArr[4];
                long j6 = jArr[5];
                long j7 = jArr[6];
                long j8 = j + jArr3[0];
                long j9 = j2 + jArr3[1];
                long j10 = j3 + jArr3[2];
                long j11 = j4 + jArr3[3];
                j4 = j5 + jArr3[4];
                j3 = j6 + (jArr3[5] + jArr4[0]);
                j2 = j7 + (jArr3[6] + jArr4[1]);
                j = jArr3[7] + jArr[7];
                for (int i = 1; i < 18; i += 2) {
                    int i2 = f[i];
                    int i3 = e[i];
                    j8 += j9;
                    j9 = ThreefishEngine.a(j9, 46, j8);
                    j10 += j11;
                    j11 = ThreefishEngine.a(j11, 36, j10);
                    j4 += j3;
                    j3 = ThreefishEngine.a(j3, 19, j4);
                    j2 += j;
                    j = ThreefishEngine.a(j, 37, j2);
                    j10 += j9;
                    j9 = ThreefishEngine.a(j9, 33, j10);
                    j4 += j;
                    j = ThreefishEngine.a(j, 27, j4);
                    j2 += j3;
                    j3 = ThreefishEngine.a(j3, 14, j2);
                    j8 += j11;
                    j11 = ThreefishEngine.a(j11, 42, j8);
                    j4 += j9;
                    j9 = ThreefishEngine.a(j9, 17, j4);
                    j2 += j11;
                    j11 = ThreefishEngine.a(j11, 49, j2);
                    j8 += j3;
                    j3 = ThreefishEngine.a(j3, 36, j8);
                    j10 += j;
                    j = ThreefishEngine.a(j, 39, j10);
                    j2 += j9;
                    j9 = ThreefishEngine.a(j9, 44, j2);
                    j8 += j;
                    j = ThreefishEngine.a(j, 9, j8);
                    j10 += j3;
                    j3 = ThreefishEngine.a(j3, 54, j10);
                    j4 += j11;
                    j9 += jArr3[i2 + 1];
                    j10 += jArr3[i2 + 2];
                    j11 = ThreefishEngine.a(j11, 56, j4) + jArr3[i2 + 3];
                    j4 += jArr3[i2 + 4];
                    j3 += jArr3[i2 + 5] + jArr4[i3];
                    j2 += jArr3[i2 + 6] + jArr4[i3 + 1];
                    j += jArr3[i2 + 7] + ((long) i);
                    j8 = (j8 + jArr3[i2]) + j9;
                    j9 = ThreefishEngine.a(j9, 39, j8);
                    j10 += j11;
                    j11 = ThreefishEngine.a(j11, 30, j10);
                    j4 += j3;
                    j3 = ThreefishEngine.a(j3, 34, j4);
                    j2 += j;
                    j = ThreefishEngine.a(j, 24, j2);
                    j10 += j9;
                    j9 = ThreefishEngine.a(j9, 13, j10);
                    j4 += j;
                    j = ThreefishEngine.a(j, 50, j4);
                    j2 += j3;
                    j3 = ThreefishEngine.a(j3, 10, j2);
                    j8 += j11;
                    j11 = ThreefishEngine.a(j11, 17, j8);
                    j4 += j9;
                    j9 = ThreefishEngine.a(j9, 25, j4);
                    j2 += j11;
                    j11 = ThreefishEngine.a(j11, 29, j2);
                    j8 += j3;
                    j3 = ThreefishEngine.a(j3, 39, j8);
                    j10 += j;
                    j = ThreefishEngine.a(j, 43, j10);
                    j2 += j9;
                    j9 = ThreefishEngine.a(j9, 8, j2);
                    j8 += j;
                    j = ThreefishEngine.a(j, 35, j8);
                    j10 += j3;
                    j3 = ThreefishEngine.a(j3, 56, j10);
                    j4 += j11;
                    j8 += jArr3[i2 + 1];
                    j9 += jArr3[i2 + 2];
                    j10 += jArr3[i2 + 3];
                    j11 = ThreefishEngine.a(j11, 22, j4) + jArr3[i2 + 4];
                    j4 += jArr3[i2 + 5];
                    j3 += jArr3[i2 + 6] + jArr4[i3 + 1];
                    j2 += jArr4[i3 + 2] + jArr3[i2 + 7];
                    j += (jArr3[i2 + 8] + ((long) i)) + 1;
                }
                jArr2[0] = j8;
                jArr2[1] = j9;
                jArr2[2] = j10;
                jArr2[3] = j11;
                jArr2[4] = j4;
                jArr2[5] = j3;
                jArr2[6] = j2;
                jArr2[7] = j;
            }
        }

        public void b(long[] jArr, long[] jArr2) {
            long[] jArr3 = this.b;
            long[] jArr4 = this.a;
            int[] f = ThreefishEngine.a;
            int[] e = ThreefishEngine.d;
            if (jArr3.length != 17) {
                throw new IllegalArgumentException();
            } else if (jArr4.length != 5) {
                throw new IllegalArgumentException();
            } else {
                long j = jArr[0];
                long j2 = jArr[1];
                long j3 = jArr[2];
                long j4 = jArr[3];
                long j5 = jArr[4];
                long j6 = jArr[5];
                long j7 = jArr[6];
                long j8 = jArr[7];
                for (int i = 17; i >= 1; i -= 2) {
                    int i2 = f[i];
                    int i3 = e[i];
                    j -= jArr3[i2 + 1];
                    j3 -= jArr3[i2 + 3];
                    j4 -= jArr3[i2 + 4];
                    j5 -= jArr3[i2 + 5];
                    j6 -= jArr3[i2 + 6] + jArr4[i3 + 1];
                    j7 -= jArr3[i2 + 7] + jArr4[i3 + 2];
                    j8 -= (jArr3[i2 + 8] + ((long) i)) + 1;
                    j2 = ThreefishEngine.b(j2 - jArr3[i2 + 2], 8, j7);
                    j7 -= j2;
                    j8 = ThreefishEngine.b(j8, 35, j);
                    j -= j8;
                    j6 = ThreefishEngine.b(j6, 56, j3);
                    j3 -= j6;
                    j4 = ThreefishEngine.b(j4, 22, j5);
                    j5 -= j4;
                    j2 = ThreefishEngine.b(j2, 25, j5);
                    j5 -= j2;
                    j4 = ThreefishEngine.b(j4, 29, j7);
                    j7 -= j4;
                    j6 = ThreefishEngine.b(j6, 39, j);
                    j -= j6;
                    j8 = ThreefishEngine.b(j8, 43, j3);
                    j3 -= j8;
                    j2 = ThreefishEngine.b(j2, 13, j3);
                    j3 -= j2;
                    j8 = ThreefishEngine.b(j8, 50, j5);
                    j5 -= j8;
                    j6 = ThreefishEngine.b(j6, 10, j7);
                    j7 -= j6;
                    j4 = ThreefishEngine.b(j4, 17, j);
                    j -= j4;
                    j2 = ThreefishEngine.b(j2, 39, j);
                    j -= j2;
                    j4 = ThreefishEngine.b(j4, 30, j3);
                    j3 -= j4;
                    j6 = ThreefishEngine.b(j6, 34, j5);
                    j5 -= j6;
                    j8 = ThreefishEngine.b(j8, 24, j7);
                    j -= jArr3[i2];
                    j2 -= jArr3[i2 + 1];
                    j3 -= jArr3[i2 + 2];
                    j4 -= jArr3[i2 + 3];
                    j5 -= jArr3[i2 + 4];
                    j6 -= jArr3[i2 + 5] + jArr4[i3];
                    j7 = (j7 - j8) - (jArr4[i3 + 1] + jArr3[i2 + 6]);
                    j8 -= jArr3[i2 + 7] + ((long) i);
                    j2 = ThreefishEngine.b(j2, 44, j7);
                    j7 -= j2;
                    j8 = ThreefishEngine.b(j8, 9, j);
                    j -= j8;
                    j6 = ThreefishEngine.b(j6, 54, j3);
                    j3 -= j6;
                    j4 = ThreefishEngine.b(j4, 56, j5);
                    j5 -= j4;
                    j2 = ThreefishEngine.b(j2, 17, j5);
                    j5 -= j2;
                    j4 = ThreefishEngine.b(j4, 49, j7);
                    j7 -= j4;
                    j6 = ThreefishEngine.b(j6, 36, j);
                    j -= j6;
                    j8 = ThreefishEngine.b(j8, 39, j3);
                    j3 -= j8;
                    j2 = ThreefishEngine.b(j2, 33, j3);
                    j3 -= j2;
                    j8 = ThreefishEngine.b(j8, 27, j5);
                    j5 -= j8;
                    j6 = ThreefishEngine.b(j6, 14, j7);
                    j7 -= j6;
                    j4 = ThreefishEngine.b(j4, 42, j);
                    j -= j4;
                    j2 = ThreefishEngine.b(j2, 46, j);
                    j -= j2;
                    j4 = ThreefishEngine.b(j4, 36, j3);
                    j3 -= j4;
                    j6 = ThreefishEngine.b(j6, 19, j5);
                    j5 -= j6;
                    j8 = ThreefishEngine.b(j8, 37, j7);
                    j7 -= j8;
                }
                j -= jArr3[0];
                j2 -= jArr3[1];
                j3 -= jArr3[2];
                j4 -= jArr3[3];
                j5 -= jArr3[4];
                j6 -= jArr3[5] + jArr4[0];
                j7 -= jArr4[1] + jArr3[6];
                long j9 = j8 - jArr3[7];
                jArr2[0] = j;
                jArr2[1] = j2;
                jArr2[2] = j3;
                jArr2[3] = j4;
                jArr2[4] = j5;
                jArr2[5] = j6;
                jArr2[6] = j7;
                jArr2[7] = j9;
            }
        }
    }

    static {
        for (int i = 0; i < a.length; i++) {
            b[i] = i % 17;
            a[i] = i % 9;
            c[i] = i % 5;
            d[i] = i % 3;
        }
    }

    public ThreefishEngine(int i) {
        this.e = i / 8;
        this.f = this.e / 8;
        this.g = new long[this.f];
        this.i = new long[((this.f * 2) + 1)];
        switch (i) {
            case 256:
                this.j = new Threefish256Cipher(this.i, this.h);
                return;
            case 512:
                this.j = new Threefish512Cipher(this.i, this.h);
                return;
            case k.FEEDBACK_FAILED_TITLE_ID /*1024*/:
                this.j = new Threefish1024Cipher(this.i, this.h);
                return;
            default:
                throw new IllegalArgumentException("Invalid blocksize - Threefish is defined with block size of 256, 512, or 1024 bits");
        }
    }

    public void a(boolean z, CipherParameters cipherParameters) {
        byte[] b;
        byte[] bArr;
        long[] jArr;
        long[] jArr2;
        if (cipherParameters instanceof TweakableBlockCipherParameters) {
            TweakableBlockCipherParameters tweakableBlockCipherParameters = (TweakableBlockCipherParameters) cipherParameters;
            byte[] a = tweakableBlockCipherParameters.a().a();
            b = tweakableBlockCipherParameters.b();
            bArr = a;
        } else if (cipherParameters instanceof KeyParameter) {
            b = null;
            bArr = ((KeyParameter) cipherParameters).a();
        } else {
            throw new IllegalArgumentException("Invalid parameter passed to Threefish init - " + cipherParameters.getClass().getName());
        }
        if (bArr == null) {
            jArr = null;
        } else if (bArr.length != this.e) {
            throw new IllegalArgumentException("Threefish key must be same size as block (" + this.e + " bytes)");
        } else {
            jArr = new long[this.f];
            for (int i = 0; i < jArr.length; i++) {
                jArr[i] = a(bArr, i * 8);
            }
        }
        if (b == null) {
            jArr2 = null;
        } else if (b.length != 16) {
            throw new IllegalArgumentException("Threefish tweak must be 16 bytes");
        } else {
            jArr2 = new long[]{a(b, 0), a(b, 8)};
        }
        a(z, jArr, jArr2);
    }

    public void a(boolean z, long[] jArr, long[] jArr2) {
        this.k = z;
        if (jArr != null) {
            a(jArr);
        }
        if (jArr2 != null) {
            b(jArr2);
        }
    }

    private void a(long[] jArr) {
        if (jArr.length != this.f) {
            throw new IllegalArgumentException("Threefish key must be same size as block (" + this.f + " words)");
        }
        long j = 2004413935125273122L;
        for (int i = 0; i < this.f; i++) {
            this.i[i] = jArr[i];
            j ^= this.i[i];
        }
        this.i[this.f] = j;
        System.arraycopy(this.i, 0, this.i, this.f + 1, this.f);
    }

    private void b(long[] jArr) {
        if (jArr.length != 2) {
            throw new IllegalArgumentException("Tweak must be 2 words.");
        }
        this.h[0] = jArr[0];
        this.h[1] = jArr[1];
        this.h[2] = this.h[0] ^ this.h[1];
        this.h[3] = this.h[0];
        this.h[4] = this.h[1];
    }

    public String a() {
        return "Threefish-" + (this.e * 8);
    }

    public int b() {
        return this.e;
    }

    public void c() {
    }

    public int a(byte[] bArr, int i, byte[] bArr2, int i2) {
        int i3 = 0;
        if (this.e + i2 > bArr2.length) {
            throw new DataLengthException("Output buffer too short");
        } else if (this.e + i > bArr.length) {
            throw new DataLengthException("Input buffer too short");
        } else {
            for (int i4 = 0; i4 < this.e; i4 += 8) {
                this.g[i4 >> 3] = a(bArr, i + i4);
            }
            a(this.g, this.g);
            while (i3 < this.e) {
                a(this.g[i3 >> 3], bArr2, i2 + i3);
                i3 += 8;
            }
            return this.e;
        }
    }

    public int a(long[] jArr, long[] jArr2) {
        if (this.i[this.f] == 0) {
            throw new IllegalStateException("Threefish engine not initialised");
        } else if (jArr.length != this.f) {
            throw new DataLengthException("Input buffer too short");
        } else if (jArr2.length != this.f) {
            throw new DataLengthException("Output buffer too short");
        } else {
            if (this.k) {
                this.j.a(jArr, jArr2);
            } else {
                this.j.b(jArr, jArr2);
            }
            return this.f;
        }
    }

    public static long a(byte[] bArr, int i) {
        if (i + 8 > bArr.length) {
            throw new IllegalArgumentException();
        }
        int i2 = i + 1;
        int i3 = i2 + 1;
        i2 = i3 + 1;
        i3 = i2 + 1;
        i2 = i3 + 1;
        i3 = i2 + 1;
        i2 = i3 + 1;
        long j = ((((((((long) bArr[i]) & 255) | ((((long) bArr[i2]) & 255) << 8)) | ((((long) bArr[i3]) & 255) << 16)) | ((((long) bArr[i2]) & 255) << 24)) | ((((long) bArr[i3]) & 255) << 32)) | ((((long) bArr[i2]) & 255) << 40)) | ((((long) bArr[i3]) & 255) << 48);
        i3 = i2 + 1;
        return ((((long) bArr[i2]) & 255) << 56) | j;
    }

    public static void a(long j, byte[] bArr, int i) {
        if (i + 8 > bArr.length) {
            throw new IllegalArgumentException();
        }
        int i2 = i + 1;
        bArr[i] = (byte) ((int) j);
        int i3 = i2 + 1;
        bArr[i2] = (byte) ((int) (j >> 8));
        i2 = i3 + 1;
        bArr[i3] = (byte) ((int) (j >> 16));
        i3 = i2 + 1;
        bArr[i2] = (byte) ((int) (j >> 24));
        i2 = i3 + 1;
        bArr[i3] = (byte) ((int) (j >> 32));
        i3 = i2 + 1;
        bArr[i2] = (byte) ((int) (j >> 40));
        i2 = i3 + 1;
        bArr[i3] = (byte) ((int) (j >> 48));
        i3 = i2 + 1;
        bArr[i2] = (byte) ((int) (j >> 56));
    }

    static long a(long j, int i, long j2) {
        return ((j << i) | (j >>> (-i))) ^ j2;
    }

    static long b(long j, int i, long j2) {
        long j3 = j ^ j2;
        return (j3 << (-i)) | (j3 >>> i);
    }
}
