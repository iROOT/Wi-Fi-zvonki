package net.hockeyapp.android.d;

import android.content.Context;
import java.net.URL;
import net.hockeyapp.android.b.a;

public class f extends e {
    private long size;

    public f(Context context, String str, a aVar) {
        super(context, str, aVar);
    }

    protected Long doInBackground(Void... voidArr) {
        try {
            return Long.valueOf((long) createConnection(new URL(getURLString()), 6).getContentLength());
        } catch (Exception e) {
            e.printStackTrace();
            return Long.valueOf(0);
        }
    }

    protected void onProgressUpdate(Integer... numArr) {
    }

    protected void onPostExecute(Long l) {
        this.size = l.longValue();
        if (this.size > 0) {
            this.notifier.downloadSuccessful(this);
        } else {
            this.notifier.downloadFailed(this, Boolean.valueOf(false));
        }
    }

    public long getSize() {
        return this.size;
    }
}
