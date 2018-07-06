package org.spongycastle.crypto.engines;

import android.support.v4.app.NotificationCompat;
import java.lang.reflect.Array;
import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.OutputLengthException;
import org.spongycastle.crypto.params.KeyParameter;

public class RijndaelEngine implements BlockCipher {
    static byte[][] a = new byte[][]{new byte[]{(byte) 0, (byte) 8, (byte) 16, (byte) 24}, new byte[]{(byte) 0, (byte) 8, (byte) 16, (byte) 24}, new byte[]{(byte) 0, (byte) 8, (byte) 16, (byte) 24}, new byte[]{(byte) 0, (byte) 8, (byte) 16, (byte) 32}, new byte[]{(byte) 0, (byte) 8, (byte) 24, (byte) 32}};
    static byte[][] b = new byte[][]{new byte[]{(byte) 0, (byte) 24, (byte) 16, (byte) 8}, new byte[]{(byte) 0, (byte) 32, (byte) 24, (byte) 16}, new byte[]{(byte) 0, (byte) 40, (byte) 32, (byte) 24}, new byte[]{(byte) 0, (byte) 48, (byte) 40, (byte) 24}, new byte[]{(byte) 0, (byte) 56, (byte) 40, (byte) 32}};
    private static final byte[] c = new byte[]{(byte) 0, (byte) 0, (byte) 25, (byte) 1, (byte) 50, (byte) 2, (byte) 26, (byte) -58, (byte) 75, (byte) -57, (byte) 27, (byte) 104, (byte) 51, (byte) -18, (byte) -33, (byte) 3, (byte) 100, (byte) 4, (byte) -32, (byte) 14, (byte) 52, (byte) -115, (byte) -127, (byte) -17, (byte) 76, (byte) 113, (byte) 8, (byte) -56, (byte) -8, (byte) 105, (byte) 28, (byte) -63, (byte) 125, (byte) -62, (byte) 29, (byte) -75, (byte) -7, (byte) -71, (byte) 39, (byte) 106, (byte) 77, (byte) -28, (byte) -90, (byte) 114, (byte) -102, (byte) -55, (byte) 9, (byte) 120, (byte) 101, (byte) 47, (byte) -118, (byte) 5, (byte) 33, (byte) 15, (byte) -31, (byte) 36, (byte) 18, (byte) -16, (byte) -126, (byte) 69, (byte) 53, (byte) -109, (byte) -38, (byte) -114, (byte) -106, (byte) -113, (byte) -37, (byte) -67, (byte) 54, (byte) -48, (byte) -50, (byte) -108, (byte) 19, (byte) 92, (byte) -46, (byte) -15, (byte) 64, (byte) 70, (byte) -125, (byte) 56, (byte) 102, (byte) -35, (byte) -3, (byte) 48, (byte) -65, (byte) 6, (byte) -117, (byte) 98, (byte) -77, (byte) 37, (byte) -30, (byte) -104, (byte) 34, (byte) -120, (byte) -111, (byte) 16, (byte) 126, (byte) 110, (byte) 72, (byte) -61, (byte) -93, (byte) -74, (byte) 30, (byte) 66, (byte) 58, (byte) 107, (byte) 40, (byte) 84, (byte) -6, (byte) -123, (byte) 61, (byte) -70, (byte) 43, (byte) 121, (byte) 10, (byte) 21, (byte) -101, (byte) -97, (byte) 94, (byte) -54, (byte) 78, (byte) -44, (byte) -84, (byte) -27, (byte) -13, (byte) 115, (byte) -89, (byte) 87, (byte) -81, (byte) 88, (byte) -88, (byte) 80, (byte) -12, (byte) -22, (byte) -42, (byte) 116, (byte) 79, (byte) -82, (byte) -23, (byte) -43, (byte) -25, (byte) -26, (byte) -83, (byte) -24, (byte) 44, (byte) -41, (byte) 117, (byte) 122, (byte) -21, (byte) 22, (byte) 11, (byte) -11, (byte) 89, (byte) -53, (byte) 95, (byte) -80, (byte) -100, (byte) -87, (byte) 81, (byte) -96, Byte.MAX_VALUE, (byte) 12, (byte) -10, (byte) 111, (byte) 23, (byte) -60, (byte) 73, (byte) -20, (byte) -40, (byte) 67, (byte) 31, (byte) 45, (byte) -92, (byte) 118, (byte) 123, (byte) -73, (byte) -52, (byte) -69, (byte) 62, (byte) 90, (byte) -5, (byte) 96, (byte) -79, (byte) -122, (byte) 59, (byte) 82, (byte) -95, (byte) 108, (byte) -86, (byte) 85, (byte) 41, (byte) -99, (byte) -105, (byte) -78, (byte) -121, (byte) -112, (byte) 97, (byte) -66, (byte) -36, (byte) -4, (byte) -68, (byte) -107, (byte) -49, (byte) -51, (byte) 55, (byte) 63, (byte) 91, (byte) -47, (byte) 83, (byte) 57, (byte) -124, (byte) 60, (byte) 65, (byte) -94, (byte) 109, (byte) 71, (byte) 20, (byte) 42, (byte) -98, (byte) 93, (byte) 86, (byte) -14, (byte) -45, (byte) -85, (byte) 68, (byte) 17, (byte) -110, (byte) -39, (byte) 35, (byte) 32, (byte) 46, (byte) -119, (byte) -76, (byte) 124, (byte) -72, (byte) 38, (byte) 119, (byte) -103, (byte) -29, (byte) -91, (byte) 103, (byte) 74, (byte) -19, (byte) -34, (byte) -59, (byte) 49, (byte) -2, (byte) 24, (byte) 13, (byte) 99, (byte) -116, Byte.MIN_VALUE, (byte) -64, (byte) -9, (byte) 112, (byte) 7};
    private static final byte[] d = new byte[]{(byte) 0, (byte) 3, (byte) 5, (byte) 15, (byte) 17, (byte) 51, (byte) 85, (byte) -1, (byte) 26, (byte) 46, (byte) 114, (byte) -106, (byte) -95, (byte) -8, (byte) 19, (byte) 53, (byte) 95, (byte) -31, (byte) 56, (byte) 72, (byte) -40, (byte) 115, (byte) -107, (byte) -92, (byte) -9, (byte) 2, (byte) 6, (byte) 10, (byte) 30, (byte) 34, (byte) 102, (byte) -86, (byte) -27, (byte) 52, (byte) 92, (byte) -28, (byte) 55, (byte) 89, (byte) -21, (byte) 38, (byte) 106, (byte) -66, (byte) -39, (byte) 112, (byte) -112, (byte) -85, (byte) -26, (byte) 49, (byte) 83, (byte) -11, (byte) 4, (byte) 12, (byte) 20, (byte) 60, (byte) 68, (byte) -52, (byte) 79, (byte) -47, (byte) 104, (byte) -72, (byte) -45, (byte) 110, (byte) -78, (byte) -51, (byte) 76, (byte) -44, (byte) 103, (byte) -87, (byte) -32, (byte) 59, (byte) 77, (byte) -41, (byte) 98, (byte) -90, (byte) -15, (byte) 8, (byte) 24, (byte) 40, (byte) 120, (byte) -120, (byte) -125, (byte) -98, (byte) -71, (byte) -48, (byte) 107, (byte) -67, (byte) -36, Byte.MAX_VALUE, (byte) -127, (byte) -104, (byte) -77, (byte) -50, (byte) 73, (byte) -37, (byte) 118, (byte) -102, (byte) -75, (byte) -60, (byte) 87, (byte) -7, (byte) 16, (byte) 48, (byte) 80, (byte) -16, (byte) 11, (byte) 29, (byte) 39, (byte) 105, (byte) -69, (byte) -42, (byte) 97, (byte) -93, (byte) -2, (byte) 25, (byte) 43, (byte) 125, (byte) -121, (byte) -110, (byte) -83, (byte) -20, (byte) 47, (byte) 113, (byte) -109, (byte) -82, (byte) -23, (byte) 32, (byte) 96, (byte) -96, (byte) -5, (byte) 22, (byte) 58, (byte) 78, (byte) -46, (byte) 109, (byte) -73, (byte) -62, (byte) 93, (byte) -25, (byte) 50, (byte) 86, (byte) -6, (byte) 21, (byte) 63, (byte) 65, (byte) -61, (byte) 94, (byte) -30, (byte) 61, (byte) 71, (byte) -55, (byte) 64, (byte) -64, (byte) 91, (byte) -19, (byte) 44, (byte) 116, (byte) -100, (byte) -65, (byte) -38, (byte) 117, (byte) -97, (byte) -70, (byte) -43, (byte) 100, (byte) -84, (byte) -17, (byte) 42, (byte) 126, (byte) -126, (byte) -99, (byte) -68, (byte) -33, (byte) 122, (byte) -114, (byte) -119, Byte.MIN_VALUE, (byte) -101, (byte) -74, (byte) -63, (byte) 88, (byte) -24, (byte) 35, (byte) 101, (byte) -81, (byte) -22, (byte) 37, (byte) 111, (byte) -79, (byte) -56, (byte) 67, (byte) -59, (byte) 84, (byte) -4, (byte) 31, (byte) 33, (byte) 99, (byte) -91, (byte) -12, (byte) 7, (byte) 9, (byte) 27, (byte) 45, (byte) 119, (byte) -103, (byte) -80, (byte) -53, (byte) 70, (byte) -54, (byte) 69, (byte) -49, (byte) 74, (byte) -34, (byte) 121, (byte) -117, (byte) -122, (byte) -111, (byte) -88, (byte) -29, (byte) 62, (byte) 66, (byte) -58, (byte) 81, (byte) -13, (byte) 14, (byte) 18, (byte) 54, (byte) 90, (byte) -18, (byte) 41, (byte) 123, (byte) -115, (byte) -116, (byte) -113, (byte) -118, (byte) -123, (byte) -108, (byte) -89, (byte) -14, (byte) 13, (byte) 23, (byte) 57, (byte) 75, (byte) -35, (byte) 124, (byte) -124, (byte) -105, (byte) -94, (byte) -3, (byte) 28, (byte) 36, (byte) 108, (byte) -76, (byte) -57, (byte) 82, (byte) -10, (byte) 1, (byte) 3, (byte) 5, (byte) 15, (byte) 17, (byte) 51, (byte) 85, (byte) -1, (byte) 26, (byte) 46, (byte) 114, (byte) -106, (byte) -95, (byte) -8, (byte) 19, (byte) 53, (byte) 95, (byte) -31, (byte) 56, (byte) 72, (byte) -40, (byte) 115, (byte) -107, (byte) -92, (byte) -9, (byte) 2, (byte) 6, (byte) 10, (byte) 30, (byte) 34, (byte) 102, (byte) -86, (byte) -27, (byte) 52, (byte) 92, (byte) -28, (byte) 55, (byte) 89, (byte) -21, (byte) 38, (byte) 106, (byte) -66, (byte) -39, (byte) 112, (byte) -112, (byte) -85, (byte) -26, (byte) 49, (byte) 83, (byte) -11, (byte) 4, (byte) 12, (byte) 20, (byte) 60, (byte) 68, (byte) -52, (byte) 79, (byte) -47, (byte) 104, (byte) -72, (byte) -45, (byte) 110, (byte) -78, (byte) -51, (byte) 76, (byte) -44, (byte) 103, (byte) -87, (byte) -32, (byte) 59, (byte) 77, (byte) -41, (byte) 98, (byte) -90, (byte) -15, (byte) 8, (byte) 24, (byte) 40, (byte) 120, (byte) -120, (byte) -125, (byte) -98, (byte) -71, (byte) -48, (byte) 107, (byte) -67, (byte) -36, Byte.MAX_VALUE, (byte) -127, (byte) -104, (byte) -77, (byte) -50, (byte) 73, (byte) -37, (byte) 118, (byte) -102, (byte) -75, (byte) -60, (byte) 87, (byte) -7, (byte) 16, (byte) 48, (byte) 80, (byte) -16, (byte) 11, (byte) 29, (byte) 39, (byte) 105, (byte) -69, (byte) -42, (byte) 97, (byte) -93, (byte) -2, (byte) 25, (byte) 43, (byte) 125, (byte) -121, (byte) -110, (byte) -83, (byte) -20, (byte) 47, (byte) 113, (byte) -109, (byte) -82, (byte) -23, (byte) 32, (byte) 96, (byte) -96, (byte) -5, (byte) 22, (byte) 58, (byte) 78, (byte) -46, (byte) 109, (byte) -73, (byte) -62, (byte) 93, (byte) -25, (byte) 50, (byte) 86, (byte) -6, (byte) 21, (byte) 63, (byte) 65, (byte) -61, (byte) 94, (byte) -30, (byte) 61, (byte) 71, (byte) -55, (byte) 64, (byte) -64, (byte) 91, (byte) -19, (byte) 44, (byte) 116, (byte) -100, (byte) -65, (byte) -38, (byte) 117, (byte) -97, (byte) -70, (byte) -43, (byte) 100, (byte) -84, (byte) -17, (byte) 42, (byte) 126, (byte) -126, (byte) -99, (byte) -68, (byte) -33, (byte) 122, (byte) -114, (byte) -119, Byte.MIN_VALUE, (byte) -101, (byte) -74, (byte) -63, (byte) 88, (byte) -24, (byte) 35, (byte) 101, (byte) -81, (byte) -22, (byte) 37, (byte) 111, (byte) -79, (byte) -56, (byte) 67, (byte) -59, (byte) 84, (byte) -4, (byte) 31, (byte) 33, (byte) 99, (byte) -91, (byte) -12, (byte) 7, (byte) 9, (byte) 27, (byte) 45, (byte) 119, (byte) -103, (byte) -80, (byte) -53, (byte) 70, (byte) -54, (byte) 69, (byte) -49, (byte) 74, (byte) -34, (byte) 121, (byte) -117, (byte) -122, (byte) -111, (byte) -88, (byte) -29, (byte) 62, (byte) 66, (byte) -58, (byte) 81, (byte) -13, (byte) 14, (byte) 18, (byte) 54, (byte) 90, (byte) -18, (byte) 41, (byte) 123, (byte) -115, (byte) -116, (byte) -113, (byte) -118, (byte) -123, (byte) -108, (byte) -89, (byte) -14, (byte) 13, (byte) 23, (byte) 57, (byte) 75, (byte) -35, (byte) 124, (byte) -124, (byte) -105, (byte) -94, (byte) -3, (byte) 28, (byte) 36, (byte) 108, (byte) -76, (byte) -57, (byte) 82, (byte) -10, (byte) 1};
    private static final byte[] e = new byte[]{(byte) 99, (byte) 124, (byte) 119, (byte) 123, (byte) -14, (byte) 107, (byte) 111, (byte) -59, (byte) 48, (byte) 1, (byte) 103, (byte) 43, (byte) -2, (byte) -41, (byte) -85, (byte) 118, (byte) -54, (byte) -126, (byte) -55, (byte) 125, (byte) -6, (byte) 89, (byte) 71, (byte) -16, (byte) -83, (byte) -44, (byte) -94, (byte) -81, (byte) -100, (byte) -92, (byte) 114, (byte) -64, (byte) -73, (byte) -3, (byte) -109, (byte) 38, (byte) 54, (byte) 63, (byte) -9, (byte) -52, (byte) 52, (byte) -91, (byte) -27, (byte) -15, (byte) 113, (byte) -40, (byte) 49, (byte) 21, (byte) 4, (byte) -57, (byte) 35, (byte) -61, (byte) 24, (byte) -106, (byte) 5, (byte) -102, (byte) 7, (byte) 18, Byte.MIN_VALUE, (byte) -30, (byte) -21, (byte) 39, (byte) -78, (byte) 117, (byte) 9, (byte) -125, (byte) 44, (byte) 26, (byte) 27, (byte) 110, (byte) 90, (byte) -96, (byte) 82, (byte) 59, (byte) -42, (byte) -77, (byte) 41, (byte) -29, (byte) 47, (byte) -124, (byte) 83, (byte) -47, (byte) 0, (byte) -19, (byte) 32, (byte) -4, (byte) -79, (byte) 91, (byte) 106, (byte) -53, (byte) -66, (byte) 57, (byte) 74, (byte) 76, (byte) 88, (byte) -49, (byte) -48, (byte) -17, (byte) -86, (byte) -5, (byte) 67, (byte) 77, (byte) 51, (byte) -123, (byte) 69, (byte) -7, (byte) 2, Byte.MAX_VALUE, (byte) 80, (byte) 60, (byte) -97, (byte) -88, (byte) 81, (byte) -93, (byte) 64, (byte) -113, (byte) -110, (byte) -99, (byte) 56, (byte) -11, (byte) -68, (byte) -74, (byte) -38, (byte) 33, (byte) 16, (byte) -1, (byte) -13, (byte) -46, (byte) -51, (byte) 12, (byte) 19, (byte) -20, (byte) 95, (byte) -105, (byte) 68, (byte) 23, (byte) -60, (byte) -89, (byte) 126, (byte) 61, (byte) 100, (byte) 93, (byte) 25, (byte) 115, (byte) 96, (byte) -127, (byte) 79, (byte) -36, (byte) 34, (byte) 42, (byte) -112, (byte) -120, (byte) 70, (byte) -18, (byte) -72, (byte) 20, (byte) -34, (byte) 94, (byte) 11, (byte) -37, (byte) -32, (byte) 50, (byte) 58, (byte) 10, (byte) 73, (byte) 6, (byte) 36, (byte) 92, (byte) -62, (byte) -45, (byte) -84, (byte) 98, (byte) -111, (byte) -107, (byte) -28, (byte) 121, (byte) -25, (byte) -56, (byte) 55, (byte) 109, (byte) -115, (byte) -43, (byte) 78, (byte) -87, (byte) 108, (byte) 86, (byte) -12, (byte) -22, (byte) 101, (byte) 122, (byte) -82, (byte) 8, (byte) -70, (byte) 120, (byte) 37, (byte) 46, (byte) 28, (byte) -90, (byte) -76, (byte) -58, (byte) -24, (byte) -35, (byte) 116, (byte) 31, (byte) 75, (byte) -67, (byte) -117, (byte) -118, (byte) 112, (byte) 62, (byte) -75, (byte) 102, (byte) 72, (byte) 3, (byte) -10, (byte) 14, (byte) 97, (byte) 53, (byte) 87, (byte) -71, (byte) -122, (byte) -63, (byte) 29, (byte) -98, (byte) -31, (byte) -8, (byte) -104, (byte) 17, (byte) 105, (byte) -39, (byte) -114, (byte) -108, (byte) -101, (byte) 30, (byte) -121, (byte) -23, (byte) -50, (byte) 85, (byte) 40, (byte) -33, (byte) -116, (byte) -95, (byte) -119, (byte) 13, (byte) -65, (byte) -26, (byte) 66, (byte) 104, (byte) 65, (byte) -103, (byte) 45, (byte) 15, (byte) -80, (byte) 84, (byte) -69, (byte) 22};
    private static final byte[] f = new byte[]{(byte) 82, (byte) 9, (byte) 106, (byte) -43, (byte) 48, (byte) 54, (byte) -91, (byte) 56, (byte) -65, (byte) 64, (byte) -93, (byte) -98, (byte) -127, (byte) -13, (byte) -41, (byte) -5, (byte) 124, (byte) -29, (byte) 57, (byte) -126, (byte) -101, (byte) 47, (byte) -1, (byte) -121, (byte) 52, (byte) -114, (byte) 67, (byte) 68, (byte) -60, (byte) -34, (byte) -23, (byte) -53, (byte) 84, (byte) 123, (byte) -108, (byte) 50, (byte) -90, (byte) -62, (byte) 35, (byte) 61, (byte) -18, (byte) 76, (byte) -107, (byte) 11, (byte) 66, (byte) -6, (byte) -61, (byte) 78, (byte) 8, (byte) 46, (byte) -95, (byte) 102, (byte) 40, (byte) -39, (byte) 36, (byte) -78, (byte) 118, (byte) 91, (byte) -94, (byte) 73, (byte) 109, (byte) -117, (byte) -47, (byte) 37, (byte) 114, (byte) -8, (byte) -10, (byte) 100, (byte) -122, (byte) 104, (byte) -104, (byte) 22, (byte) -44, (byte) -92, (byte) 92, (byte) -52, (byte) 93, (byte) 101, (byte) -74, (byte) -110, (byte) 108, (byte) 112, (byte) 72, (byte) 80, (byte) -3, (byte) -19, (byte) -71, (byte) -38, (byte) 94, (byte) 21, (byte) 70, (byte) 87, (byte) -89, (byte) -115, (byte) -99, (byte) -124, (byte) -112, (byte) -40, (byte) -85, (byte) 0, (byte) -116, (byte) -68, (byte) -45, (byte) 10, (byte) -9, (byte) -28, (byte) 88, (byte) 5, (byte) -72, (byte) -77, (byte) 69, (byte) 6, (byte) -48, (byte) 44, (byte) 30, (byte) -113, (byte) -54, (byte) 63, (byte) 15, (byte) 2, (byte) -63, (byte) -81, (byte) -67, (byte) 3, (byte) 1, (byte) 19, (byte) -118, (byte) 107, (byte) 58, (byte) -111, (byte) 17, (byte) 65, (byte) 79, (byte) 103, (byte) -36, (byte) -22, (byte) -105, (byte) -14, (byte) -49, (byte) -50, (byte) -16, (byte) -76, (byte) -26, (byte) 115, (byte) -106, (byte) -84, (byte) 116, (byte) 34, (byte) -25, (byte) -83, (byte) 53, (byte) -123, (byte) -30, (byte) -7, (byte) 55, (byte) -24, (byte) 28, (byte) 117, (byte) -33, (byte) 110, (byte) 71, (byte) -15, (byte) 26, (byte) 113, (byte) 29, (byte) 41, (byte) -59, (byte) -119, (byte) 111, (byte) -73, (byte) 98, (byte) 14, (byte) -86, (byte) 24, (byte) -66, (byte) 27, (byte) -4, (byte) 86, (byte) 62, (byte) 75, (byte) -58, (byte) -46, (byte) 121, (byte) 32, (byte) -102, (byte) -37, (byte) -64, (byte) -2, (byte) 120, (byte) -51, (byte) 90, (byte) -12, (byte) 31, (byte) -35, (byte) -88, (byte) 51, (byte) -120, (byte) 7, (byte) -57, (byte) 49, (byte) -79, (byte) 18, (byte) 16, (byte) 89, (byte) 39, Byte.MIN_VALUE, (byte) -20, (byte) 95, (byte) 96, (byte) 81, Byte.MAX_VALUE, (byte) -87, (byte) 25, (byte) -75, (byte) 74, (byte) 13, (byte) 45, (byte) -27, (byte) 122, (byte) -97, (byte) -109, (byte) -55, (byte) -100, (byte) -17, (byte) -96, (byte) -32, (byte) 59, (byte) 77, (byte) -82, (byte) 42, (byte) -11, (byte) -80, (byte) -56, (byte) -21, (byte) -69, (byte) 60, (byte) -125, (byte) 83, (byte) -103, (byte) 97, (byte) 23, (byte) 43, (byte) 4, (byte) 126, (byte) -70, (byte) 119, (byte) -42, (byte) 38, (byte) -31, (byte) 105, (byte) 20, (byte) 99, (byte) 85, (byte) 33, (byte) 12, (byte) 125};
    private static final int[] g = new int[]{1, 2, 4, 8, 16, 32, 64, NotificationCompat.FLAG_HIGH_PRIORITY, 27, 54, 108, 216, 171, 77, 154, 47, 94, 188, 99, 198, 151, 53, 106, 212, 179, 125, 250, 239, 197, 145};
    private int h;
    private long i;
    private int j;
    private int k;
    private long[][] l;
    private long m;
    private long n;
    private long o;
    private long p;
    private boolean q;
    private byte[] r;
    private byte[] s;

    private byte a(int i) {
        if (i != 0) {
            return d[(c[i] & 255) + 25];
        }
        return (byte) 0;
    }

    private byte b(int i) {
        if (i != 0) {
            return d[(c[i] & 255) + 1];
        }
        return (byte) 0;
    }

    private byte c(int i) {
        if (i >= 0) {
            return d[i + 199];
        }
        return (byte) 0;
    }

    private byte d(int i) {
        if (i >= 0) {
            return d[i + 104];
        }
        return (byte) 0;
    }

    private byte e(int i) {
        if (i >= 0) {
            return d[i + 238];
        }
        return (byte) 0;
    }

    private byte f(int i) {
        if (i >= 0) {
            return d[i + 223];
        }
        return (byte) 0;
    }

    private void a(long[] jArr) {
        this.m ^= jArr[0];
        this.n ^= jArr[1];
        this.o ^= jArr[2];
        this.p ^= jArr[3];
    }

    private long a(long j, int i) {
        return ((j >>> i) | (j << (this.h - i))) & this.i;
    }

    private void a(byte[] bArr) {
        this.n = a(this.n, bArr[1]);
        this.o = a(this.o, bArr[2]);
        this.p = a(this.p, bArr[3]);
    }

    private long a(long j, byte[] bArr) {
        long j2 = 0;
        for (int i = 0; i < this.h; i += 8) {
            j2 |= ((long) (bArr[(int) ((j >> i) & 255)] & 255)) << i;
        }
        return j2;
    }

    private void b(byte[] bArr) {
        this.m = a(this.m, bArr);
        this.n = a(this.n, bArr);
        this.o = a(this.o, bArr);
        this.p = a(this.p, bArr);
    }

    private void d() {
        long j = 0;
        long j2 = 0;
        long j3 = 0;
        long j4 = 0;
        for (int i = 0; i < this.h; i += 8) {
            int i2 = (int) ((this.m >> i) & 255);
            int i3 = (int) ((this.n >> i) & 255);
            int i4 = (int) ((this.o >> i) & 255);
            int i5 = (int) ((this.p >> i) & 255);
            j4 |= ((long) ((((a(i2) ^ b(i3)) ^ i4) ^ i5) & 255)) << i;
            j3 |= ((long) ((((a(i3) ^ b(i4)) ^ i5) ^ i2) & 255)) << i;
            j2 |= ((long) ((((a(i4) ^ b(i5)) ^ i2) ^ i3) & 255)) << i;
            j |= ((long) ((((b(i2) ^ a(i5)) ^ i3) ^ i4) & 255)) << i;
        }
        this.m = j4;
        this.n = j3;
        this.o = j2;
        this.p = j;
    }

    private void e() {
        long j = 0;
        long j2 = 0;
        long j3 = 0;
        long j4 = 0;
        for (int i = 0; i < this.h; i += 8) {
            int i2 = (int) ((this.m >> i) & 255);
            int i3 = (int) ((this.n >> i) & 255);
            int i4 = (int) ((this.o >> i) & 255);
            int i5 = (int) ((this.p >> i) & 255);
            int i6 = i2 != 0 ? c[i2 & 255] & 255 : -1;
            int i7 = i3 != 0 ? c[i3 & 255] & 255 : -1;
            i3 = i4 != 0 ? c[i4 & 255] & 255 : -1;
            i2 = i5 != 0 ? c[i5 & 255] & 255 : -1;
            j4 |= ((long) ((((f(i6) ^ d(i7)) ^ e(i3)) ^ c(i2)) & 255)) << i;
            j3 |= ((long) ((((f(i7) ^ d(i3)) ^ e(i2)) ^ c(i6)) & 255)) << i;
            j2 |= ((long) ((((f(i3) ^ d(i2)) ^ e(i6)) ^ c(i7)) & 255)) << i;
            j |= ((long) ((((f(i2) ^ d(i6)) ^ e(i7)) ^ c(i3)) & 255)) << i;
        }
        this.m = j4;
        this.n = j3;
        this.o = j2;
        this.p = j;
    }

    private long[][] c(byte[] bArr) {
        int i;
        int i2;
        int length = bArr.length * 8;
        byte[][] bArr2 = (byte[][]) Array.newInstance(Byte.TYPE, new int[]{4, 64});
        long[][] jArr = (long[][]) Array.newInstance(Long.TYPE, new int[]{15, 4});
        switch (length) {
            case NotificationCompat.FLAG_HIGH_PRIORITY /*128*/:
                i = 4;
                break;
            case 160:
                i = 5;
                break;
            case 192:
                i = 6;
                break;
            case 224:
                i = 7;
                break;
            case 256:
                i = 8;
                break;
            default:
                throw new IllegalArgumentException("Key length not 128/160/192/224/256 bits.");
        }
        if (length >= this.k) {
            this.j = i + 6;
        } else {
            this.j = (this.h / 8) + 6;
        }
        int i3 = 0;
        length = 0;
        while (length < bArr.length) {
            i2 = i3 + 1;
            bArr2[length % 4][length / 4] = bArr[i3];
            length++;
            i3 = i2;
        }
        length = 0;
        for (i2 = 0; i2 < i && length < (this.j + 1) * (this.h / 8); i2++) {
            for (i3 = 0; i3 < 4; i3++) {
                long[] jArr2 = jArr[length / (this.h / 8)];
                jArr2[i3] = jArr2[i3] | (((long) (bArr2[i3][i2] & 255)) << ((length * 8) % this.h));
            }
            length++;
        }
        i2 = length;
        length = 0;
        while (i2 < (this.j + 1) * (this.h / 8)) {
            byte[] bArr3;
            int i4;
            for (i3 = 0; i3 < 4; i3++) {
                bArr3 = bArr2[i3];
                bArr3[0] = (byte) (bArr3[0] ^ e[bArr2[(i3 + 1) % 4][i - 1] & 255]);
            }
            bArr3 = bArr2[0];
            i3 = length + 1;
            bArr3[0] = (byte) (g[length] ^ bArr3[0]);
            byte[] bArr4;
            if (i <= 6) {
                for (i4 = 1; i4 < i; i4++) {
                    for (length = 0; length < 4; length++) {
                        bArr4 = bArr2[length];
                        bArr4[i4] = (byte) (bArr4[i4] ^ bArr2[length][i4 - 1]);
                    }
                }
            } else {
                for (i4 = 1; i4 < 4; i4++) {
                    for (length = 0; length < 4; length++) {
                        bArr4 = bArr2[length];
                        bArr4[i4] = (byte) (bArr4[i4] ^ bArr2[length][i4 - 1]);
                    }
                }
                for (length = 0; length < 4; length++) {
                    bArr3 = bArr2[length];
                    bArr3[4] = (byte) (bArr3[4] ^ e[bArr2[length][3] & 255]);
                }
                for (i4 = 5; i4 < i; i4++) {
                    for (length = 0; length < 4; length++) {
                        bArr4 = bArr2[length];
                        bArr4[i4] = (byte) (bArr4[i4] ^ bArr2[length][i4 - 1]);
                    }
                }
            }
            length = i2;
            for (i4 = 0; i4 < i && length < (this.j + 1) * (this.h / 8); i4++) {
                for (i2 = 0; i2 < 4; i2++) {
                    jArr2 = jArr[length / (this.h / 8)];
                    jArr2[i2] = jArr2[i2] | (((long) (bArr2[i2][i4] & 255)) << ((length * 8) % this.h));
                }
                length++;
            }
            i2 = length;
            length = i3;
        }
        return jArr;
    }

    public RijndaelEngine() {
        this(NotificationCompat.FLAG_HIGH_PRIORITY);
    }

    public RijndaelEngine(int i) {
        switch (i) {
            case NotificationCompat.FLAG_HIGH_PRIORITY /*128*/:
                this.h = 32;
                this.i = 4294967295L;
                this.r = a[0];
                this.s = b[0];
                break;
            case 160:
                this.h = 40;
                this.i = 1099511627775L;
                this.r = a[1];
                this.s = b[1];
                break;
            case 192:
                this.h = 48;
                this.i = 281474976710655L;
                this.r = a[2];
                this.s = b[2];
                break;
            case 224:
                this.h = 56;
                this.i = 72057594037927935L;
                this.r = a[3];
                this.s = b[3];
                break;
            case 256:
                this.h = 64;
                this.i = -1;
                this.r = a[4];
                this.s = b[4];
                break;
            default:
                throw new IllegalArgumentException("unknown blocksize to Rijndael");
        }
        this.k = i;
    }

    public void a(boolean z, CipherParameters cipherParameters) {
        if (cipherParameters instanceof KeyParameter) {
            this.l = c(((KeyParameter) cipherParameters).a());
            this.q = z;
            return;
        }
        throw new IllegalArgumentException("invalid parameter passed to Rijndael init - " + cipherParameters.getClass().getName());
    }

    public String a() {
        return "Rijndael";
    }

    public int b() {
        return this.h / 2;
    }

    public int a(byte[] bArr, int i, byte[] bArr2, int i2) {
        if (this.l == null) {
            throw new IllegalStateException("Rijndael engine not initialised");
        } else if ((this.h / 2) + i > bArr.length) {
            throw new DataLengthException("input buffer too short");
        } else if ((this.h / 2) + i2 > bArr2.length) {
            throw new OutputLengthException("output buffer too short");
        } else {
            if (this.q) {
                a(bArr, i);
                a(this.l);
                b(bArr2, i2);
            } else {
                a(bArr, i);
                b(this.l);
                b(bArr2, i2);
            }
            return this.h / 2;
        }
    }

    public void c() {
    }

    private void a(byte[] bArr, int i) {
        int i2 = i + 1;
        this.m = (long) (bArr[i] & 255);
        int i3 = i2 + 1;
        this.n = (long) (bArr[i2] & 255);
        i2 = i3 + 1;
        this.o = (long) (bArr[i3] & 255);
        i3 = i2 + 1;
        this.p = (long) (bArr[i2] & 255);
        for (i2 = 8; i2 != this.h; i2 += 8) {
            int i4 = i3 + 1;
            this.m |= ((long) (bArr[i3] & 255)) << i2;
            i3 = i4 + 1;
            this.n |= ((long) (bArr[i4] & 255)) << i2;
            i4 = i3 + 1;
            this.o |= ((long) (bArr[i3] & 255)) << i2;
            i3 = i4 + 1;
            this.p |= ((long) (bArr[i4] & 255)) << i2;
        }
    }

    private void b(byte[] bArr, int i) {
        for (int i2 = 0; i2 != this.h; i2 += 8) {
            int i3 = i + 1;
            bArr[i] = (byte) ((int) (this.m >> i2));
            int i4 = i3 + 1;
            bArr[i3] = (byte) ((int) (this.n >> i2));
            i3 = i4 + 1;
            bArr[i4] = (byte) ((int) (this.o >> i2));
            i = i3 + 1;
            bArr[i3] = (byte) ((int) (this.p >> i2));
        }
    }

    private void a(long[][] jArr) {
        a(jArr[0]);
        for (int i = 1; i < this.j; i++) {
            b(e);
            a(this.r);
            d();
            a(jArr[i]);
        }
        b(e);
        a(this.r);
        a(jArr[this.j]);
    }

    private void b(long[][] jArr) {
        a(jArr[this.j]);
        b(f);
        a(this.s);
        for (int i = this.j - 1; i > 0; i--) {
            a(jArr[i]);
            e();
            b(f);
            a(this.s);
        }
        a(jArr[0]);
    }
}
