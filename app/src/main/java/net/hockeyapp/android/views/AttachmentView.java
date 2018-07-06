package net.hockeyapp.android.views;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.text.TextUtils.TruncateAt;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.io.File;
import net.hockeyapp.android.a;
import net.hockeyapp.android.c.e;

public class AttachmentView extends FrameLayout {
    private static final int IMAGES_PER_ROW_LANDSCAPE = 2;
    private static final int IMAGES_PER_ROW_PORTRAIT = 3;
    private final e attachment;
    private final Uri attachmentUri;
    private final Context context;
    private final String filename;
    private int gap;
    private ImageView imageView;
    private int maxHeightLandscape;
    private int maxHeightPortrait;
    private int orientation;
    private final ViewGroup parent;
    private TextView textView;
    private int widthLandscape;
    private int widthPortrait;

    public AttachmentView(Context context, ViewGroup viewGroup, Uri uri, boolean z) {
        super(context);
        this.context = context;
        this.parent = viewGroup;
        this.attachment = null;
        this.attachmentUri = uri;
        this.filename = uri.getLastPathSegment();
        calculateDimensions(20);
        initializeView(context, z);
        this.textView.setText(this.filename);
        new AsyncTask<Void, Void, Bitmap>() {
            protected Bitmap doInBackground(Void... voidArr) {
                return AttachmentView.this.loadImageThumbnail();
            }

            protected void onPostExecute(Bitmap bitmap) {
                if (bitmap != null) {
                    AttachmentView.this.configureViewForThumbnail(bitmap, false);
                } else {
                    AttachmentView.this.configureViewForPlaceholder(false);
                }
            }
        }.execute(new Void[0]);
    }

    public AttachmentView(Context context, ViewGroup viewGroup, e eVar, boolean z) {
        super(context);
        this.context = context;
        this.parent = viewGroup;
        this.attachment = eVar;
        this.attachmentUri = Uri.fromFile(new File(a.getHockeyAppStorageDir(), eVar.getCacheId()));
        this.filename = eVar.getFilename();
        calculateDimensions(30);
        initializeView(context, z);
        this.orientation = 0;
        this.textView.setText("Loading...");
        configureViewForPlaceholder(false);
    }

    public e getAttachment() {
        return this.attachment;
    }

    public Uri getAttachmentUri() {
        return this.attachmentUri;
    }

    public int getWidthPortrait() {
        return this.widthPortrait;
    }

    public int getMaxHeightPortrait() {
        return this.maxHeightPortrait;
    }

    public int getWidthLandscape() {
        return this.widthLandscape;
    }

    public int getMaxHeightLandscape() {
        return this.maxHeightLandscape;
    }

    public int getGap() {
        return this.gap;
    }

    public int getEffectiveMaxHeight() {
        return this.orientation == 1 ? this.maxHeightLandscape : this.maxHeightPortrait;
    }

    public void remove() {
        this.parent.removeView(this);
    }

    public void setImage(Bitmap bitmap, int i) {
        this.textView.setText(this.filename);
        this.orientation = i;
        if (bitmap == null) {
            configureViewForPlaceholder(true);
        } else {
            configureViewForThumbnail(bitmap, true);
        }
    }

    public void signalImageLoadingError() {
        this.textView.setText("Error");
    }

    private void calculateDimensions(int i) {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        this.gap = Math.round(TypedValue.applyDimension(1, 10.0f, displayMetrics));
        int round = Math.round(TypedValue.applyDimension(1, (float) i, displayMetrics));
        int i2 = displayMetrics.widthPixels;
        int i3 = (i2 - (round * 2)) - (this.gap * 2);
        i2 = (i2 - (round * 2)) - (this.gap * 1);
        this.widthPortrait = i3 / 3;
        this.widthLandscape = i2 / 2;
        this.maxHeightPortrait = this.widthPortrait * 2;
        this.maxHeightLandscape = this.widthLandscape;
    }

    private void initializeView(Context context, boolean z) {
        setLayoutParams(new LayoutParams(-2, -2, 80));
        setPadding(0, this.gap, 0, 0);
        this.imageView = new ImageView(context);
        View linearLayout = new LinearLayout(context);
        linearLayout.setLayoutParams(new LayoutParams(-1, -2, 80));
        linearLayout.setGravity(3);
        linearLayout.setOrientation(1);
        linearLayout.setBackgroundColor(Color.parseColor("#80262626"));
        this.textView = new TextView(context);
        this.textView.setLayoutParams(new LayoutParams(-1, -2, 17));
        this.textView.setGravity(17);
        this.textView.setTextColor(Color.parseColor("#FFFFFF"));
        this.textView.setSingleLine();
        this.textView.setEllipsize(TruncateAt.MIDDLE);
        if (z) {
            View imageButton = new ImageButton(context);
            imageButton.setLayoutParams(new LayoutParams(-1, -2, 80));
            imageButton.setAdjustViewBounds(true);
            imageButton.setImageDrawable(getSystemIcon("ic_menu_delete"));
            imageButton.setBackgroundResource(0);
            imageButton.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    AttachmentView.this.remove();
                }
            });
            linearLayout.addView(imageButton);
        }
        linearLayout.addView(this.textView);
        addView(this.imageView);
        addView(linearLayout);
    }

    private void configureViewForThumbnail(Bitmap bitmap, final boolean z) {
        int i = this.orientation == 1 ? this.widthLandscape : this.widthPortrait;
        int i2 = this.orientation == 1 ? this.maxHeightLandscape : this.maxHeightPortrait;
        this.textView.setMaxWidth(i);
        this.textView.setMinWidth(i);
        this.imageView.setLayoutParams(new LayoutParams(-2, -2));
        this.imageView.setAdjustViewBounds(true);
        this.imageView.setMinimumWidth(i);
        this.imageView.setMaxWidth(i);
        this.imageView.setMaxHeight(i2);
        this.imageView.setScaleType(ScaleType.CENTER_INSIDE);
        this.imageView.setImageBitmap(bitmap);
        this.imageView.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (z) {
                    Intent intent = new Intent();
                    intent.setAction("android.intent.action.VIEW");
                    intent.setDataAndType(AttachmentView.this.attachmentUri, "image/*");
                    AttachmentView.this.context.startActivity(intent);
                }
            }
        });
    }

    private void configureViewForPlaceholder(final boolean z) {
        this.textView.setMaxWidth(this.widthPortrait);
        this.textView.setMinWidth(this.widthPortrait);
        this.imageView.setLayoutParams(new LayoutParams(-2, -2));
        this.imageView.setAdjustViewBounds(false);
        this.imageView.setBackgroundColor(Color.parseColor("#eeeeee"));
        this.imageView.setMinimumHeight((int) (((float) this.widthPortrait) * 1.2f));
        this.imageView.setMinimumWidth(this.widthPortrait);
        this.imageView.setScaleType(ScaleType.FIT_CENTER);
        this.imageView.setImageDrawable(getSystemIcon("ic_menu_attachment"));
        this.imageView.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (z) {
                    Intent intent = new Intent();
                    intent.setAction("android.intent.action.VIEW");
                    intent.setDataAndType(AttachmentView.this.attachmentUri, "*/*");
                    AttachmentView.this.context.startActivity(intent);
                }
            }
        });
    }

    private Bitmap loadImageThumbnail() {
        try {
            this.orientation = net.hockeyapp.android.e.e.determineOrientation(this.context, this.attachmentUri);
            return net.hockeyapp.android.e.e.decodeSampledBitmap(this.context, this.attachmentUri, this.orientation == 1 ? this.widthLandscape : this.widthPortrait, this.orientation == 1 ? this.maxHeightLandscape : this.maxHeightPortrait);
        } catch (Throwable th) {
            return null;
        }
    }

    private Drawable getSystemIcon(String str) {
        return getResources().getDrawable(getResources().getIdentifier(str, "drawable", "android"));
    }
}
