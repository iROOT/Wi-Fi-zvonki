package net.hockeyapp.android;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.Toast;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import net.hockeyapp.android.views.PaintView;

public class PaintActivity extends Activity {
    private static final int MENU_CLEAR_ID = 3;
    private static final int MENU_SAVE_ID = 1;
    private static final int MENU_UNDO_ID = 2;
    private String imageName;
    private PaintView paintView;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Uri uri = (Uri) getIntent().getExtras().getParcelable("imageUri");
        this.imageName = determineFilename(uri, uri.getLastPathSegment());
        int i = getResources().getDisplayMetrics().widthPixels;
        int i2 = getResources().getDisplayMetrics().heightPixels;
        int i3 = i > i2 ? 0 : 1;
        int determineOrientation = PaintView.determineOrientation(getContentResolver(), uri);
        setRequestedOrientation(determineOrientation);
        if (i3 != determineOrientation) {
            Log.d("HockeyApp", "Image loading skipped because activity will be destroyed for orientation change.");
            return;
        }
        this.paintView = new PaintView(this, uri, i, i2);
        View linearLayout = new LinearLayout(this);
        linearLayout.setLayoutParams(new LayoutParams(-1, -1));
        linearLayout.setGravity(17);
        linearLayout.setOrientation(1);
        View linearLayout2 = new LinearLayout(this);
        linearLayout2.setLayoutParams(new LayoutParams(-1, -1));
        linearLayout2.setGravity(17);
        linearLayout2.setOrientation(0);
        linearLayout.addView(linearLayout2);
        linearLayout2.addView(this.paintView);
        setContentView(linearLayout);
        Toast.makeText(this, k.get(k.PAINT_INDICATOR_TOAST_ID), 1000).show();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(0, 1, 0, k.get(k.PAINT_MENU_SAVE_ID));
        menu.add(0, 2, 0, k.get(k.PAINT_MENU_UNDO_ID));
        menu.add(0, 3, 0, k.get(k.PAINT_MENU_CLEAR_ID));
        return true;
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case 1:
                makeResult();
                return true;
            case 2:
                this.paintView.undo();
                return true;
            case 3:
                this.paintView.clearImage();
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4 || this.paintView.isClear()) {
            return super.onKeyDown(i, keyEvent);
        }
        OnClickListener anonymousClass1 = new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                switch (i) {
                    case -1:
                        PaintActivity.this.finish();
                        return;
                    default:
                        return;
                }
            }
        };
        new Builder(this).setMessage(k.get(k.PAINT_DIALOG_MESSAGE_ID)).setPositiveButton(k.get(k.PAINT_DIALOG_POSITIVE_BUTTON_ID), anonymousClass1).setNegativeButton(k.get(k.PAINT_DIALOG_NEGATIVE_BUTTON_ID), anonymousClass1).show();
        return true;
    }

    private void makeResult() {
        File file = new File(getCacheDir(), "HockeyApp");
        file.mkdir();
        File file2 = new File(file, this.imageName + ".jpg");
        int i = 1;
        while (file2.exists()) {
            file2 = new File(file, this.imageName + "_" + i + ".jpg");
            i++;
        }
        this.paintView.setDrawingCacheEnabled(true);
        final Bitmap drawingCache = this.paintView.getDrawingCache();
        new AsyncTask<File, Void, Void>() {
            protected Void doInBackground(File... fileArr) {
                try {
                    OutputStream fileOutputStream = new FileOutputStream(fileArr[0]);
                    drawingCache.compress(CompressFormat.JPEG, 100, fileOutputStream);
                    fileOutputStream.close();
                } catch (Throwable e) {
                    e.printStackTrace();
                    Log.e("HockeyApp", "Could not save image.", e);
                }
                return null;
            }
        }.execute(new File[]{file2});
        Intent intent = new Intent();
        intent.putExtra("imageUri", Uri.fromFile(file2));
        if (getParent() == null) {
            setResult(-1, intent);
        } else {
            getParent().setResult(-1, intent);
        }
        finish();
    }

    private String determineFilename(Uri uri, String str) {
        String str2 = null;
        Cursor query = getApplicationContext().getContentResolver().query(uri, new String[]{"_data"}, null, null, null);
        if (query != null) {
            try {
                if (query.moveToFirst()) {
                    str2 = query.getString(0);
                }
                query.close();
            } catch (Throwable th) {
                query.close();
            }
        }
        return str2 == null ? str : new File(str2).getName();
    }
}
