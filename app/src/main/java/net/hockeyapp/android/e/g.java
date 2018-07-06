package net.hockeyapp.android.e;

import android.support.v4.app.FragmentTransaction;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

public class g {
    private static final char[] BOUNDARY_CHARS = "-_1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    private String boundary;
    private boolean isSetFirst = false;
    private boolean isSetLast = false;
    private ByteArrayOutputStream out = new ByteArrayOutputStream();

    public g() {
        int i = 0;
        StringBuffer stringBuffer = new StringBuffer();
        Random random = new Random();
        while (i < 30) {
            stringBuffer.append(BOUNDARY_CHARS[random.nextInt(BOUNDARY_CHARS.length)]);
            i++;
        }
        this.boundary = stringBuffer.toString();
    }

    public String getBoundary() {
        return this.boundary;
    }

    public void writeFirstBoundaryIfNeeds() {
        if (!this.isSetFirst) {
            this.out.write(("--" + this.boundary + "\r\n").getBytes());
        }
        this.isSetFirst = true;
    }

    public void writeLastBoundaryIfNeeds() {
        if (!this.isSetLast) {
            try {
                this.out.write(("\r\n--" + this.boundary + "--\r\n").getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.isSetLast = true;
        }
    }

    public void addPart(String str, String str2) {
        writeFirstBoundaryIfNeeds();
        this.out.write(("Content-Disposition: form-data; name=\"" + str + "\"\r\n").getBytes());
        this.out.write("Content-Type: text/plain; charset=UTF-8\r\n".getBytes());
        this.out.write("Content-Transfer-Encoding: 8bit\r\n\r\n".getBytes());
        this.out.write(str2.getBytes());
        this.out.write(("\r\n--" + this.boundary + "\r\n").getBytes());
    }

    public void addPart(String str, File file, boolean z) {
        addPart(str, file.getName(), new FileInputStream(file), z);
    }

    public void addPart(String str, String str2, InputStream inputStream, boolean z) {
        addPart(str, str2, inputStream, "application/octet-stream", z);
    }

    public void addPart(String str, String str2, InputStream inputStream, String str3, boolean z) {
        writeFirstBoundaryIfNeeds();
        try {
            String str4 = "Content-Type: " + str3 + "\r\n";
            this.out.write(("Content-Disposition: form-data; name=\"" + str + "\"; filename=\"" + str2 + "\"\r\n").getBytes());
            this.out.write(str4.getBytes());
            this.out.write("Content-Transfer-Encoding: binary\r\n\r\n".getBytes());
            byte[] bArr = new byte[FragmentTransaction.TRANSIT_ENTER_MASK];
            while (true) {
                int read = inputStream.read(bArr);
                if (read == -1) {
                    break;
                }
                this.out.write(bArr, 0, read);
            }
            this.out.flush();
            if (z) {
                writeLastBoundaryIfNeeds();
            } else {
                this.out.write(("\r\n--" + this.boundary + "\r\n").getBytes());
            }
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public long getContentLength() {
        writeLastBoundaryIfNeeds();
        return (long) this.out.toByteArray().length;
    }

    public String getContentType() {
        return "multipart/form-data; boundary=" + getBoundary();
    }

    public ByteArrayOutputStream getOutputStream() {
        writeLastBoundaryIfNeeds();
        return this.out;
    }
}
