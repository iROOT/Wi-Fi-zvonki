package net.hockeyapp.android.d;

import android.os.AsyncTask;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import net.hockeyapp.android.k;

public abstract class d<Params, Progress, Result> extends AsyncTask<Params, Progress, Result> {
    protected static String getStringFromConnection(HttpURLConnection httpURLConnection) {
        InputStream bufferedInputStream = new BufferedInputStream(httpURLConnection.getInputStream());
        String convertStreamToString = convertStreamToString(bufferedInputStream);
        bufferedInputStream.close();
        return convertStreamToString;
    }

    private static String convertStreamToString(InputStream inputStream) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream), k.FEEDBACK_FAILED_TITLE_ID);
        StringBuilder stringBuilder = new StringBuilder();
        while (true) {
            try {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    stringBuilder.append(readLine + "\n");
                } else {
                    try {
                        break;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } catch (IOException e2) {
                e2.printStackTrace();
                try {
                    inputStream.close();
                } catch (IOException e22) {
                    e22.printStackTrace();
                }
            } catch (Throwable th) {
                try {
                    inputStream.close();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
                throw th;
            }
        }
        inputStream.close();
        return stringBuilder.toString();
    }
}
