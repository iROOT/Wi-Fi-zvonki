package org.spongycastle.jcajce.provider.keystore.bc;

import android.support.v4.app.FragmentTransaction;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyStoreException;
import java.security.KeyStoreSpi;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import net.hockeyapp.android.k;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.PBEParametersGenerator;
import org.spongycastle.crypto.digests.SHA1Digest;
import org.spongycastle.crypto.generators.PKCS12ParametersGenerator;
import org.spongycastle.crypto.io.DigestInputStream;
import org.spongycastle.crypto.io.DigestOutputStream;
import org.spongycastle.crypto.io.MacInputStream;
import org.spongycastle.crypto.io.MacOutputStream;
import org.spongycastle.crypto.macs.HMac;
import org.spongycastle.jce.interfaces.BCKeyStore;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.io.Streams;
import org.spongycastle.util.io.TeeOutputStream;

public class BcKeyStoreSpi extends KeyStoreSpi implements BCKeyStore {
    protected Hashtable a = new Hashtable();
    protected SecureRandom b = new SecureRandom();
    protected int c;

    public static class BouncyCastleStore extends BcKeyStoreSpi {
        public BouncyCastleStore() {
            super(1);
        }

        public void engineLoad(InputStream inputStream, char[] cArr) {
            this.a.clear();
            if (inputStream != null) {
                InputStream dataInputStream = new DataInputStream(inputStream);
                int readInt = dataInputStream.readInt();
                if (readInt == 2 || readInt == 0 || readInt == 1) {
                    byte[] bArr = new byte[dataInputStream.readInt()];
                    if (bArr.length != 20) {
                        throw new IOException("Key store corrupted.");
                    }
                    dataInputStream.readFully(bArr);
                    int readInt2 = dataInputStream.readInt();
                    if (readInt2 < 0 || readInt2 > FragmentTransaction.TRANSIT_ENTER_MASK) {
                        throw new IOException("Key store corrupted.");
                    }
                    String str;
                    if (readInt == 0) {
                        str = "OldPBEWithSHAAndTwofish-CBC";
                    } else {
                        str = "PBEWithSHAAndTwofish-CBC";
                    }
                    InputStream cipherInputStream = new CipherInputStream(dataInputStream, a(str, 2, cArr, bArr, readInt2));
                    Digest sHA1Digest = new SHA1Digest();
                    a(new DigestInputStream(cipherInputStream, sHA1Digest));
                    byte[] bArr2 = new byte[sHA1Digest.b()];
                    sHA1Digest.a(bArr2, 0);
                    byte[] bArr3 = new byte[sHA1Digest.b()];
                    Streams.a(cipherInputStream, bArr3);
                    if (!Arrays.b(bArr2, bArr3)) {
                        this.a.clear();
                        throw new IOException("KeyStore integrity check failed.");
                    }
                    return;
                }
                throw new IOException("Wrong version of key store.");
            }
        }

        public void engineStore(OutputStream outputStream, char[] cArr) {
            OutputStream dataOutputStream = new DataOutputStream(outputStream);
            byte[] bArr = new byte[20];
            int nextInt = (this.b.nextInt() & 1023) + k.FEEDBACK_FAILED_TITLE_ID;
            this.b.nextBytes(bArr);
            dataOutputStream.writeInt(this.c);
            dataOutputStream.writeInt(bArr.length);
            dataOutputStream.write(bArr);
            dataOutputStream.writeInt(nextInt);
            OutputStream cipherOutputStream = new CipherOutputStream(dataOutputStream, a("PBEWithSHAAndTwofish-CBC", 1, cArr, bArr, nextInt));
            OutputStream digestOutputStream = new DigestOutputStream(new SHA1Digest());
            a(new TeeOutputStream(cipherOutputStream, digestOutputStream));
            cipherOutputStream.write(digestOutputStream.a());
            cipherOutputStream.close();
        }
    }

    public static class Std extends BcKeyStoreSpi {
        public Std() {
            super(2);
        }
    }

    private class StoreEntry {
        int a;
        String b;
        Object c;
        Certificate[] d;
        Date e;
        final /* synthetic */ BcKeyStoreSpi f;

        StoreEntry(BcKeyStoreSpi bcKeyStoreSpi, String str, Certificate certificate) {
            this.f = bcKeyStoreSpi;
            this.e = new Date();
            this.a = 1;
            this.b = str;
            this.c = certificate;
            this.d = null;
        }

        StoreEntry(BcKeyStoreSpi bcKeyStoreSpi, String str, byte[] bArr, Certificate[] certificateArr) {
            this.f = bcKeyStoreSpi;
            this.e = new Date();
            this.a = 3;
            this.b = str;
            this.c = bArr;
            this.d = certificateArr;
        }

        StoreEntry(BcKeyStoreSpi bcKeyStoreSpi, String str, Key key, char[] cArr, Certificate[] certificateArr) {
            this.f = bcKeyStoreSpi;
            this.e = new Date();
            this.a = 4;
            this.b = str;
            this.d = certificateArr;
            byte[] bArr = new byte[20];
            bcKeyStoreSpi.b.setSeed(System.currentTimeMillis());
            bcKeyStoreSpi.b.nextBytes(bArr);
            int nextInt = (bcKeyStoreSpi.b.nextInt() & 1023) + k.FEEDBACK_FAILED_TITLE_ID;
            OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            OutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            dataOutputStream.writeInt(bArr.length);
            dataOutputStream.write(bArr);
            dataOutputStream.writeInt(nextInt);
            DataOutputStream dataOutputStream2 = new DataOutputStream(new CipherOutputStream(dataOutputStream, bcKeyStoreSpi.a("PBEWithSHAAnd3-KeyTripleDES-CBC", 1, cArr, bArr, nextInt)));
            bcKeyStoreSpi.a(key, dataOutputStream2);
            dataOutputStream2.close();
            this.c = byteArrayOutputStream.toByteArray();
        }

        StoreEntry(BcKeyStoreSpi bcKeyStoreSpi, String str, Date date, int i, Object obj) {
            this.f = bcKeyStoreSpi;
            this.e = new Date();
            this.b = str;
            this.e = date;
            this.a = i;
            this.c = obj;
        }

        StoreEntry(BcKeyStoreSpi bcKeyStoreSpi, String str, Date date, int i, Object obj, Certificate[] certificateArr) {
            this.f = bcKeyStoreSpi;
            this.e = new Date();
            this.b = str;
            this.e = date;
            this.a = i;
            this.c = obj;
            this.d = certificateArr;
        }

        int a() {
            return this.a;
        }

        String b() {
            return this.b;
        }

        Object c() {
            return this.c;
        }

        Object a(char[] cArr) {
            Key a;
            if ((cArr == null || cArr.length == 0) && (this.c instanceof Key)) {
                return this.c;
            }
            if (this.a == 4) {
                InputStream dataInputStream = new DataInputStream(new ByteArrayInputStream((byte[]) this.c));
                byte[] bArr;
                try {
                    bArr = new byte[dataInputStream.readInt()];
                    dataInputStream.readFully(bArr);
                    return this.f.b(new DataInputStream(new CipherInputStream(dataInputStream, this.f.a("PBEWithSHAAnd3-KeyTripleDES-CBC", 2, cArr, bArr, dataInputStream.readInt()))));
                } catch (Exception e) {
                    dataInputStream = new DataInputStream(new ByteArrayInputStream((byte[]) this.c));
                    bArr = new byte[dataInputStream.readInt()];
                    dataInputStream.readFully(bArr);
                    int readInt = dataInputStream.readInt();
                    try {
                        a = this.f.b(new DataInputStream(new CipherInputStream(dataInputStream, this.f.a("BrokenPBEWithSHAAnd3-KeyTripleDES-CBC", 2, cArr, bArr, readInt))));
                    } catch (Exception e2) {
                        dataInputStream = new DataInputStream(new ByteArrayInputStream((byte[]) this.c));
                        bArr = new byte[dataInputStream.readInt()];
                        dataInputStream.readFully(bArr);
                        readInt = dataInputStream.readInt();
                        a = this.f.b(new DataInputStream(new CipherInputStream(dataInputStream, this.f.a("OldPBEWithSHAAnd3-KeyTripleDES-CBC", 2, cArr, bArr, readInt))));
                    }
                    if (a != null) {
                        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        OutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
                        dataOutputStream.writeInt(bArr.length);
                        dataOutputStream.write(bArr);
                        dataOutputStream.writeInt(readInt);
                        DataOutputStream dataOutputStream2 = new DataOutputStream(new CipherOutputStream(dataOutputStream, this.f.a("PBEWithSHAAnd3-KeyTripleDES-CBC", 1, cArr, bArr, readInt)));
                        this.f.a(a, dataOutputStream2);
                        dataOutputStream2.close();
                        this.c = byteArrayOutputStream.toByteArray();
                        return a;
                    }
                    throw new UnrecoverableKeyException("no match");
                } catch (Exception e3) {
                    throw new UnrecoverableKeyException("no match");
                }
            }
            throw new RuntimeException("forget something!");
        }

        Certificate[] d() {
            return this.d;
        }

        Date e() {
            return this.e;
        }
    }

    public static class Version1 extends BcKeyStoreSpi {
        public Version1() {
            super(1);
        }
    }

    public BcKeyStoreSpi(int i) {
        this.c = i;
    }

    private void a(Certificate certificate, DataOutputStream dataOutputStream) {
        try {
            byte[] encoded = certificate.getEncoded();
            dataOutputStream.writeUTF(certificate.getType());
            dataOutputStream.writeInt(encoded.length);
            dataOutputStream.write(encoded);
        } catch (CertificateEncodingException e) {
            throw new IOException(e.toString());
        }
    }

    private Certificate a(DataInputStream dataInputStream) {
        String readUTF = dataInputStream.readUTF();
        byte[] bArr = new byte[dataInputStream.readInt()];
        dataInputStream.readFully(bArr);
        try {
            return CertificateFactory.getInstance(readUTF, "SC").generateCertificate(new ByteArrayInputStream(bArr));
        } catch (NoSuchProviderException e) {
            throw new IOException(e.toString());
        } catch (CertificateException e2) {
            throw new IOException(e2.toString());
        }
    }

    private void a(Key key, DataOutputStream dataOutputStream) {
        byte[] encoded = key.getEncoded();
        if (key instanceof PrivateKey) {
            dataOutputStream.write(0);
        } else if (key instanceof PublicKey) {
            dataOutputStream.write(1);
        } else {
            dataOutputStream.write(2);
        }
        dataOutputStream.writeUTF(key.getFormat());
        dataOutputStream.writeUTF(key.getAlgorithm());
        dataOutputStream.writeInt(encoded.length);
        dataOutputStream.write(encoded);
    }

    private Key b(DataInputStream dataInputStream) {
        KeySpec pKCS8EncodedKeySpec;
        int read = dataInputStream.read();
        String readUTF = dataInputStream.readUTF();
        String readUTF2 = dataInputStream.readUTF();
        byte[] bArr = new byte[dataInputStream.readInt()];
        dataInputStream.readFully(bArr);
        if (readUTF.equals("PKCS#8") || readUTF.equals("PKCS8")) {
            pKCS8EncodedKeySpec = new PKCS8EncodedKeySpec(bArr);
        } else if (readUTF.equals("X.509") || readUTF.equals("X509")) {
            pKCS8EncodedKeySpec = new X509EncodedKeySpec(bArr);
        } else if (readUTF.equals("RAW")) {
            return new SecretKeySpec(bArr, readUTF2);
        } else {
            throw new IOException("Key format " + readUTF + " not recognised!");
        }
        switch (read) {
            case 0:
                return KeyFactory.getInstance(readUTF2, "SC").generatePrivate(pKCS8EncodedKeySpec);
            case 1:
                return KeyFactory.getInstance(readUTF2, "SC").generatePublic(pKCS8EncodedKeySpec);
            case 2:
                return SecretKeyFactory.getInstance(readUTF2, "SC").generateSecret(pKCS8EncodedKeySpec);
            default:
                try {
                    throw new IOException("Key type " + read + " not recognised!");
                } catch (Exception e) {
                    throw new IOException("Exception creating key: " + e.toString());
                }
        }
        throw new IOException("Exception creating key: " + e.toString());
    }

    protected Cipher a(String str, int i, char[] cArr, byte[] bArr, int i2) {
        try {
            KeySpec pBEKeySpec = new PBEKeySpec(cArr);
            SecretKeyFactory instance = SecretKeyFactory.getInstance(str, "SC");
            AlgorithmParameterSpec pBEParameterSpec = new PBEParameterSpec(bArr, i2);
            Cipher instance2 = Cipher.getInstance(str, "SC");
            instance2.init(i, instance.generateSecret(pBEKeySpec), pBEParameterSpec);
            return instance2;
        } catch (Exception e) {
            throw new IOException("Error initialising store of key store: " + e);
        }
    }

    public Enumeration engineAliases() {
        return this.a.keys();
    }

    public boolean engineContainsAlias(String str) {
        return this.a.get(str) != null;
    }

    public void engineDeleteEntry(String str) {
        if (this.a.get(str) != null) {
            this.a.remove(str);
        }
    }

    public Certificate engineGetCertificate(String str) {
        StoreEntry storeEntry = (StoreEntry) this.a.get(str);
        if (storeEntry != null) {
            if (storeEntry.a() == 1) {
                return (Certificate) storeEntry.c();
            }
            Certificate[] d = storeEntry.d();
            if (d != null) {
                return d[0];
            }
        }
        return null;
    }

    public String engineGetCertificateAlias(Certificate certificate) {
        Enumeration elements = this.a.elements();
        while (elements.hasMoreElements()) {
            StoreEntry storeEntry = (StoreEntry) elements.nextElement();
            if (!(storeEntry.c() instanceof Certificate)) {
                Certificate[] d = storeEntry.d();
                if (d != null && d[0].equals(certificate)) {
                    return storeEntry.b();
                }
            } else if (((Certificate) storeEntry.c()).equals(certificate)) {
                return storeEntry.b();
            }
        }
        return null;
    }

    public Certificate[] engineGetCertificateChain(String str) {
        StoreEntry storeEntry = (StoreEntry) this.a.get(str);
        if (storeEntry != null) {
            return storeEntry.d();
        }
        return null;
    }

    public Date engineGetCreationDate(String str) {
        StoreEntry storeEntry = (StoreEntry) this.a.get(str);
        if (storeEntry != null) {
            return storeEntry.e();
        }
        return null;
    }

    public Key engineGetKey(String str, char[] cArr) {
        StoreEntry storeEntry = (StoreEntry) this.a.get(str);
        if (storeEntry == null || storeEntry.a() == 1) {
            return null;
        }
        return (Key) storeEntry.a(cArr);
    }

    public boolean engineIsCertificateEntry(String str) {
        StoreEntry storeEntry = (StoreEntry) this.a.get(str);
        if (storeEntry == null || storeEntry.a() != 1) {
            return false;
        }
        return true;
    }

    public boolean engineIsKeyEntry(String str) {
        StoreEntry storeEntry = (StoreEntry) this.a.get(str);
        if (storeEntry == null || storeEntry.a() == 1) {
            return false;
        }
        return true;
    }

    public void engineSetCertificateEntry(String str, Certificate certificate) {
        StoreEntry storeEntry = (StoreEntry) this.a.get(str);
        if (storeEntry == null || storeEntry.a() == 1) {
            this.a.put(str, new StoreEntry(this, str, certificate));
            return;
        }
        throw new KeyStoreException("key store already has a key entry with alias " + str);
    }

    public void engineSetKeyEntry(String str, byte[] bArr, Certificate[] certificateArr) {
        this.a.put(str, new StoreEntry(this, str, bArr, certificateArr));
    }

    public void engineSetKeyEntry(String str, Key key, char[] cArr, Certificate[] certificateArr) {
        if ((key instanceof PrivateKey) && certificateArr == null) {
            throw new KeyStoreException("no certificate chain for private key");
        }
        try {
            this.a.put(str, new StoreEntry(this, str, key, cArr, certificateArr));
        } catch (Exception e) {
            throw new KeyStoreException(e.toString());
        }
    }

    public int engineSize() {
        return this.a.size();
    }

    protected void a(InputStream inputStream) {
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        for (int read = dataInputStream.read(); read > 0; read = dataInputStream.read()) {
            String readUTF = dataInputStream.readUTF();
            Date date = new Date(dataInputStream.readLong());
            int readInt = dataInputStream.readInt();
            Certificate[] certificateArr = null;
            if (readInt != 0) {
                certificateArr = new Certificate[readInt];
                for (int i = 0; i != readInt; i++) {
                    certificateArr[i] = a(dataInputStream);
                }
            }
            switch (read) {
                case 1:
                    this.a.put(readUTF, new StoreEntry(this, readUTF, date, 1, a(dataInputStream)));
                    break;
                case 2:
                    this.a.put(readUTF, new StoreEntry(this, readUTF, date, 2, b(dataInputStream), certificateArr));
                    break;
                case 3:
                case 4:
                    Object obj = new byte[dataInputStream.readInt()];
                    dataInputStream.readFully(obj);
                    this.a.put(readUTF, new StoreEntry(this, readUTF, date, read, obj, certificateArr));
                    break;
                default:
                    throw new RuntimeException("Unknown object type in store.");
            }
        }
    }

    protected void a(OutputStream outputStream) {
        Enumeration elements = this.a.elements();
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        while (elements.hasMoreElements()) {
            StoreEntry storeEntry = (StoreEntry) elements.nextElement();
            dataOutputStream.write(storeEntry.a());
            dataOutputStream.writeUTF(storeEntry.b());
            dataOutputStream.writeLong(storeEntry.e().getTime());
            Certificate[] d = storeEntry.d();
            if (d == null) {
                dataOutputStream.writeInt(0);
            } else {
                dataOutputStream.writeInt(d.length);
                for (int i = 0; i != d.length; i++) {
                    a(d[i], dataOutputStream);
                }
            }
            switch (storeEntry.a()) {
                case 1:
                    a((Certificate) storeEntry.c(), dataOutputStream);
                    break;
                case 2:
                    a((Key) storeEntry.c(), dataOutputStream);
                    break;
                case 3:
                case 4:
                    byte[] bArr = (byte[]) storeEntry.c();
                    dataOutputStream.writeInt(bArr.length);
                    dataOutputStream.write(bArr);
                    break;
                default:
                    throw new RuntimeException("Unknown object type in store.");
            }
        }
        dataOutputStream.write(0);
    }

    public void engineLoad(InputStream inputStream, char[] cArr) {
        this.a.clear();
        if (inputStream != null) {
            InputStream dataInputStream = new DataInputStream(inputStream);
            int readInt = dataInputStream.readInt();
            if (readInt == 2 || readInt == 0 || readInt == 1) {
                int readInt2 = dataInputStream.readInt();
                if (readInt2 <= 0) {
                    throw new IOException("Invalid salt detected");
                }
                byte[] bArr = new byte[readInt2];
                dataInputStream.readFully(bArr);
                int readInt3 = dataInputStream.readInt();
                HMac hMac = new HMac(new SHA1Digest());
                if (cArr == null || cArr.length == 0) {
                    a(dataInputStream);
                    dataInputStream.readFully(new byte[hMac.b()]);
                    return;
                }
                CipherParameters b;
                byte[] c = PBEParametersGenerator.c(cArr);
                PBEParametersGenerator pKCS12ParametersGenerator = new PKCS12ParametersGenerator(new SHA1Digest());
                pKCS12ParametersGenerator.a(c, bArr, readInt3);
                if (readInt != 2) {
                    b = pKCS12ParametersGenerator.b(hMac.b());
                } else {
                    b = pKCS12ParametersGenerator.b(hMac.b() * 8);
                }
                Arrays.a(c, (byte) 0);
                hMac.a(b);
                a(new MacInputStream(dataInputStream, hMac));
                byte[] bArr2 = new byte[hMac.b()];
                hMac.a(bArr2, 0);
                bArr = new byte[hMac.b()];
                dataInputStream.readFully(bArr);
                if (!Arrays.b(bArr2, bArr)) {
                    this.a.clear();
                    throw new IOException("KeyStore integrity check failed.");
                }
                return;
            }
            throw new IOException("Wrong version of key store.");
        }
    }

    public void engineStore(OutputStream outputStream, char[] cArr) {
        OutputStream dataOutputStream = new DataOutputStream(outputStream);
        byte[] bArr = new byte[20];
        int nextInt = (this.b.nextInt() & 1023) + k.FEEDBACK_FAILED_TITLE_ID;
        this.b.nextBytes(bArr);
        dataOutputStream.writeInt(this.c);
        dataOutputStream.writeInt(bArr.length);
        dataOutputStream.write(bArr);
        dataOutputStream.writeInt(nextInt);
        HMac hMac = new HMac(new SHA1Digest());
        OutputStream macOutputStream = new MacOutputStream(hMac);
        PBEParametersGenerator pKCS12ParametersGenerator = new PKCS12ParametersGenerator(new SHA1Digest());
        byte[] c = PBEParametersGenerator.c(cArr);
        pKCS12ParametersGenerator.a(c, bArr, nextInt);
        if (this.c < 2) {
            hMac.a(pKCS12ParametersGenerator.b(hMac.b()));
        } else {
            hMac.a(pKCS12ParametersGenerator.b(hMac.b() * 8));
        }
        for (int i = 0; i != c.length; i++) {
            c[i] = (byte) 0;
        }
        a(new TeeOutputStream(dataOutputStream, macOutputStream));
        bArr = new byte[hMac.b()];
        hMac.a(bArr, 0);
        dataOutputStream.write(bArr);
        dataOutputStream.close();
    }
}
