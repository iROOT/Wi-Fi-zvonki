package com.mavenir.android.security;

import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import com.fgmicrotec.mobile.android.fgvoip.FgVoIP;
import com.mavenir.android.applog.AppLogAdapter;
import com.mavenir.android.applog.AppLogAdapter.b;
import com.mavenir.android.applog.AppLogAdapter.c;
import com.mavenir.android.applog.a;
import com.mavenir.android.common.q;
import com.mavenir.android.settings.c.g;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.Security;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import net.hockeyapp.android.e.d;
import org.spongycastle.jce.provider.BouncyCastleProvider;

public class CryptoAES {
    public static final boolean DEBUG = false;
    private static final String TAG = "CryptoAES";
    private static final int mIterationCount = 1000;
    private static byte[] mIv = null;
    private static final int mKeyLength = 128;
    private static final boolean mLogTimings = false;
    private static byte[] mSalt = null;
    private static final int mSaltLength = 16;
    private static SecretKey mSecretKey = null;
    private static final boolean mUseSameKey = true;
    private static CryptoAES sInstance = null;

    public static native byte[] infuse();

    public static native byte[] saltItUp();

    static {
        Provider bouncyCastleProvider = new BouncyCastleProvider();
        bouncyCastleProvider.remove("Signature.RSA");
        Security.addProvider(bouncyCastleProvider);
    }

    private CryptoAES() {
    }

    public static CryptoAES getInstance() {
        if (sInstance == null) {
            sInstance = new CryptoAES();
        }
        return sInstance;
    }

    public boolean encryptFile(String str, File file, File file2) {
        FileInputStream fileInputStream;
        FileOutputStream fileOutputStream;
        Exception e;
        CipherOutputStream cipherOutputStream;
        FileInputStream fileInputStream2;
        FileOutputStream fileOutputStream2;
        Throwable th;
        boolean z = mUseSameKey;
        CipherOutputStream cipherOutputStream2 = null;
        if (TextUtils.isEmpty(str)) {
            Log.d(TAG, "encryptFile(): empty master key");
            return false;
        } else if (file == null || !file.exists() || file2 == null) {
            return false;
        } else {
            try {
                Key generateSecretKey = generateSecretKey(saltItUp(), str);
                Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
                instance.init(1, generateSecretKey, new IvParameterSpec(infuse()));
                fileInputStream = new FileInputStream(file);
                try {
                    fileOutputStream = new FileOutputStream(file2);
                } catch (Exception e2) {
                    e = e2;
                    cipherOutputStream = null;
                    fileInputStream2 = fileInputStream;
                    try {
                        q.d(TAG, "encryptFile():" + e.getLocalizedMessage());
                        closeStream(cipherOutputStream);
                        closeStream(fileInputStream2);
                        closeStream(fileOutputStream2);
                        z = false;
                        return z;
                    } catch (Throwable th2) {
                        th = th2;
                        fileInputStream = fileInputStream2;
                        fileOutputStream = fileOutputStream2;
                        cipherOutputStream2 = cipherOutputStream;
                        closeStream(cipherOutputStream2);
                        closeStream(fileInputStream);
                        closeStream(fileOutputStream);
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    fileOutputStream = null;
                    closeStream(cipherOutputStream2);
                    closeStream(fileInputStream);
                    closeStream(fileOutputStream);
                    throw th;
                }
                try {
                    cipherOutputStream = new CipherOutputStream(fileOutputStream, instance);
                    try {
                        byte[] bArr = new byte[256];
                        while (true) {
                            int read = fileInputStream.read(bArr);
                            if (read == -1) {
                                break;
                            }
                            cipherOutputStream.write(bArr, 0, read);
                        }
                        closeStream(cipherOutputStream);
                        closeStream(fileInputStream);
                        closeStream(fileOutputStream);
                    } catch (Exception e3) {
                        e = e3;
                        fileOutputStream2 = fileOutputStream;
                        fileInputStream2 = fileInputStream;
                        q.d(TAG, "encryptFile():" + e.getLocalizedMessage());
                        closeStream(cipherOutputStream);
                        closeStream(fileInputStream2);
                        closeStream(fileOutputStream2);
                        z = false;
                        return z;
                    } catch (Throwable th4) {
                        th = th4;
                        cipherOutputStream2 = cipherOutputStream;
                        closeStream(cipherOutputStream2);
                        closeStream(fileInputStream);
                        closeStream(fileOutputStream);
                        throw th;
                    }
                } catch (Exception e4) {
                    e = e4;
                    cipherOutputStream = null;
                    fileOutputStream2 = fileOutputStream;
                    fileInputStream2 = fileInputStream;
                    q.d(TAG, "encryptFile():" + e.getLocalizedMessage());
                    closeStream(cipherOutputStream);
                    closeStream(fileInputStream2);
                    closeStream(fileOutputStream2);
                    z = false;
                    return z;
                } catch (Throwable th5) {
                    th = th5;
                    closeStream(cipherOutputStream2);
                    closeStream(fileInputStream);
                    closeStream(fileOutputStream);
                    throw th;
                }
            } catch (Exception e5) {
                e = e5;
                cipherOutputStream = null;
                fileInputStream2 = null;
                q.d(TAG, "encryptFile():" + e.getLocalizedMessage());
                closeStream(cipherOutputStream);
                closeStream(fileInputStream2);
                closeStream(fileOutputStream2);
                z = false;
                return z;
            } catch (Throwable th6) {
                th = th6;
                fileOutputStream = null;
                fileInputStream = null;
                closeStream(cipherOutputStream2);
                closeStream(fileInputStream);
                closeStream(fileOutputStream);
                throw th;
            }
            return z;
        }
    }

    public boolean decryptFile(String str, File file, File file2) {
        FileOutputStream fileOutputStream;
        CipherInputStream cipherInputStream;
        Exception e;
        FileOutputStream fileOutputStream2;
        FileInputStream fileInputStream;
        Throwable th;
        CipherInputStream cipherInputStream2 = null;
        if (TextUtils.isEmpty(str)) {
            Log.d(TAG, "decryptFile(): empty master key");
            return false;
        } else if (file == null || !file.exists() || file2 == null) {
            return false;
        } else {
            FileInputStream fileInputStream2;
            try {
                Key generateSecretKey = generateSecretKey(saltItUp(), str);
                Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
                instance.init(2, generateSecretKey, new IvParameterSpec(infuse()));
                fileInputStream2 = new FileInputStream(file);
                try {
                    fileOutputStream = new FileOutputStream(file2);
                    try {
                        cipherInputStream = new CipherInputStream(fileInputStream2, instance);
                    } catch (Exception e2) {
                        e = e2;
                        cipherInputStream = null;
                        fileOutputStream2 = fileOutputStream;
                        fileInputStream = fileInputStream2;
                        try {
                            q.d(TAG, "decryptFile(): " + e.getLocalizedMessage());
                            closeStream(cipherInputStream);
                            closeStream(fileInputStream);
                            closeStream(fileOutputStream2);
                            return false;
                        } catch (Throwable th2) {
                            th = th2;
                            fileInputStream2 = fileInputStream;
                            fileOutputStream = fileOutputStream2;
                            cipherInputStream2 = cipherInputStream;
                            closeStream(cipherInputStream2);
                            closeStream(fileInputStream2);
                            closeStream(fileOutputStream);
                            throw th;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        closeStream(cipherInputStream2);
                        closeStream(fileInputStream2);
                        closeStream(fileOutputStream);
                        throw th;
                    }
                    try {
                        byte[] bArr = new byte[256];
                        while (true) {
                            int read = cipherInputStream.read(bArr);
                            if (read != -1) {
                                fileOutputStream.write(bArr, 0, read);
                            } else {
                                closeStream(cipherInputStream);
                                closeStream(fileInputStream2);
                                closeStream(fileOutputStream);
                                return mUseSameKey;
                            }
                        }
                    } catch (Exception e3) {
                        e = e3;
                        fileOutputStream2 = fileOutputStream;
                        fileInputStream = fileInputStream2;
                        q.d(TAG, "decryptFile(): " + e.getLocalizedMessage());
                        closeStream(cipherInputStream);
                        closeStream(fileInputStream);
                        closeStream(fileOutputStream2);
                        return false;
                    } catch (Throwable th4) {
                        th = th4;
                        cipherInputStream2 = cipherInputStream;
                        closeStream(cipherInputStream2);
                        closeStream(fileInputStream2);
                        closeStream(fileOutputStream);
                        throw th;
                    }
                } catch (Exception e4) {
                    e = e4;
                    cipherInputStream = null;
                    fileInputStream = fileInputStream2;
                    q.d(TAG, "decryptFile(): " + e.getLocalizedMessage());
                    closeStream(cipherInputStream);
                    closeStream(fileInputStream);
                    closeStream(fileOutputStream2);
                    return false;
                } catch (Throwable th5) {
                    th = th5;
                    fileOutputStream = null;
                    closeStream(cipherInputStream2);
                    closeStream(fileInputStream2);
                    closeStream(fileOutputStream);
                    throw th;
                }
            } catch (Exception e5) {
                e = e5;
                cipherInputStream = null;
                fileInputStream = null;
                q.d(TAG, "decryptFile(): " + e.getLocalizedMessage());
                closeStream(cipherInputStream);
                closeStream(fileInputStream);
                closeStream(fileOutputStream2);
                return false;
            } catch (Throwable th6) {
                th = th6;
                fileOutputStream = null;
                fileInputStream2 = null;
                closeStream(cipherInputStream2);
                closeStream(fileInputStream2);
                closeStream(fileOutputStream);
                throw th;
            }
        }
    }

    private void closeStream(FileInputStream fileInputStream) {
        if (fileInputStream != null) {
            try {
                fileInputStream.close();
            } catch (Exception e) {
                q.d(TAG, "closeStream(): error closing stream: " + e);
            }
        }
    }

    private void closeStream(FileOutputStream fileOutputStream) {
        if (fileOutputStream != null) {
            try {
                fileOutputStream.close();
            } catch (Exception e) {
                q.d(TAG, "closeStream(): error closing stream: " + e);
            }
        }
    }

    private void closeStream(CipherInputStream cipherInputStream) {
        if (cipherInputStream != null) {
            try {
                cipherInputStream.close();
            } catch (Exception e) {
                q.d(TAG, "closeStream(): error closing stream: " + e);
            }
        }
    }

    private void closeStream(CipherOutputStream cipherOutputStream) {
        if (cipherOutputStream != null) {
            try {
                cipherOutputStream.close();
            } catch (Exception e) {
                q.d(TAG, "closeStream(): error closing stream: " + e);
            }
        }
    }

    public String encrypt(String str, String str2) {
        String str3 = null;
        if (TextUtils.isEmpty(str)) {
            Log.d(TAG, "Encryption not possible...");
        } else if (str2 != null) {
            try {
                Key secretKey = getSecretKey(false, saltItUp(), str);
                Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
                instance.init(1, secretKey, new IvParameterSpec(infuse()));
                str3 = Base64.encodeToString(instance.doFinal(str2.getBytes(d.DEFAULT_CHARSET)), 0);
                com.mavenir.android.settings.d.b(FgVoIP.U(), "db_encryption_problem", false);
            } catch (Exception e) {
                Log.e(TAG, "encrypt():" + e, e.getCause());
                if (!com.mavenir.android.settings.d.a(FgVoIP.U(), "db_encryption_problem", false)) {
                    com.mavenir.android.settings.d.b(FgVoIP.U(), "db_encryption_problem", (boolean) mUseSameKey);
                    a.a(FgVoIP.U()).b(b.FGAPPLOG_EVENT_GROUP_DATABASE_ERROR, AppLogAdapter.d.FGAPPLOG_EVENT_TYPE_ENCRYPTION_ERROR, c.FGAPPLOG_EVENT_REASON_NONE, e.toString());
                }
            }
        }
        return str3;
    }

    public String decrypt(String str, String str2, String str3, boolean z, boolean z2) {
        Exception e;
        if (TextUtils.isEmpty(str)) {
            Log.d(TAG, "Decryption not possible...");
            return null;
        } else if (str3 == null) {
            return null;
        } else {
            String str4;
            try {
                if (str3.length() == 0) {
                    return null;
                }
                byte[] decode = Base64.decode(str3, 0);
                Key secretKey = getSecretKey(z, saltItUp(), str);
                Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
                instance.init(2, secretKey, new IvParameterSpec(infuse()));
                str4 = new String(instance.doFinal(decode), d.DEFAULT_CHARSET);
                try {
                    com.mavenir.android.settings.d.b(FgVoIP.U(), "db_encryption_problem", false);
                    return str4;
                } catch (Exception e2) {
                    e = e2;
                    Log.e(TAG, "decrypt(): " + e, e.getCause());
                    return !z2 ? str4 : str4;
                }
            } catch (Exception e3) {
                Exception exception = e3;
                str4 = null;
                e = exception;
                Log.e(TAG, "decrypt(): " + e, e.getCause());
                if (!z2 && !com.mavenir.android.settings.d.a(FgVoIP.U(), "db_encryption_problem", false)) {
                    com.mavenir.android.settings.d.b(FgVoIP.U(), "db_encryption_problem", (boolean) mUseSameKey);
                    boolean a = g.a(str2);
                    boolean b = g.b(str2);
                    boolean c = g.c(str2);
                    if (a || b || c) {
                        if (a) {
                            g.c();
                        }
                        if (!b) {
                            return str4;
                        }
                        g.d();
                        return str4;
                    }
                    a.a(FgVoIP.U()).b(b.FGAPPLOG_EVENT_GROUP_DATABASE_ERROR, AppLogAdapter.d.FGAPPLOG_EVENT_TYPE_DECRYPTION_ERROR, c.FGAPPLOG_EVENT_REASON_NONE, str2 + ", Exception: " + e.toString());
                    g.a((boolean) mUseSameKey);
                    return str4;
                }
            }
        }
    }

    private SecretKey getSecretKey(boolean z, byte[] bArr, String str) {
        if (z) {
            mSecretKey = generateSecretKey(bArr, str);
        } else if (mSecretKey == null) {
            mSecretKey = generateSecretKey(bArr, str);
        }
        return mSecretKey;
    }

    private SecretKey generateSecretKey(byte[] bArr, String str) {
        SecretKey secretKeySpec;
        try {
            secretKeySpec = new SecretKeySpec(SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1").generateSecret(new PBEKeySpec(str.toCharArray(), bArr, mIterationCount, 128)).getEncoded(), "AES");
        } catch (NoSuchAlgorithmException e) {
            secretKeySpec = null;
        } catch (Exception e2) {
            secretKeySpec = null;
        }
        if (secretKeySpec == null) {
            try {
                KeyGenerator instance = KeyGenerator.getInstance("AES");
                SecureRandom instance2 = SecureRandom.getInstance("SHA1PRNG", "Crypto");
                Object bytes = str.getBytes(d.DEFAULT_CHARSET);
                Object obj = new byte[(bytes.length + bArr.length)];
                System.arraycopy(bytes, 0, obj, 0, bytes.length);
                System.arraycopy(bArr, 0, obj, bytes.length, bArr.length);
                instance2.setSeed(obj);
                instance.init(128, instance2);
                secretKeySpec = instance.generateKey();
            } catch (Exception e3) {
            }
        }
        return secretKeySpec;
    }

    private byte[] getRandom(int i) {
        byte[] bArr = new byte[i];
        new SecureRandom().nextBytes(bArr);
        return bArr;
    }
}
