package net.hockeyapp.android.views;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.os.Build.VERSION;
import android.support.v4.app.NotificationCompat;
import android.text.method.PasswordTransformationMethod;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import net.hockeyapp.android.k;

public class LoginView extends LinearLayout {
    public static final int EMAIL_INPUT_ID = 12291;
    public static final int HEADLINE_TEXT_ID = 12290;
    public static final int LOGIN_BUTTON_ID = 12293;
    public static final int PASSWORD_INPUT_ID = 12292;
    public static final int WRAPPER_BASE_ID = 12289;
    private LinearLayout wrapperBase;

    public LoginView(Context context) {
        this(context, 0);
    }

    public LoginView(Context context, int i) {
        super(context);
        loadLayoutParams(context);
        loadWrapperBase(context);
        loadHeadlineTextView(context);
        loadEmailInput(context);
        loadPasswordInput(context);
        loadLoginButton(context);
    }

    private void loadLayoutParams(Context context) {
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        setBackgroundColor(-1);
        setLayoutParams(layoutParams);
    }

    private void loadWrapperBase(Context context) {
        this.wrapperBase = new LinearLayout(context);
        this.wrapperBase.setId(12289);
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
        int applyDimension = (int) TypedValue.applyDimension(1, 20.0f, getResources().getDisplayMetrics());
        layoutParams.gravity = 49;
        this.wrapperBase.setLayoutParams(layoutParams);
        this.wrapperBase.setPadding(applyDimension, applyDimension, applyDimension, applyDimension);
        this.wrapperBase.setOrientation(1);
        addView(this.wrapperBase);
    }

    private void loadHeadlineTextView(Context context) {
        View textView = new TextView(context);
        textView.setId(12290);
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.setMargins(0, 0, 0, (int) TypedValue.applyDimension(1, 30.0f, getResources().getDisplayMetrics()));
        textView.setLayoutParams(layoutParams);
        textView.setText(k.get(k.LOGIN_HEADLINE_TEXT_ID));
        textView.setTextColor(-7829368);
        textView.setTextSize(2, 18.0f);
        textView.setTypeface(null, 0);
        this.wrapperBase.addView(textView);
    }

    private void loadEmailInput(Context context) {
        View editText = new EditText(context);
        editText.setId(12291);
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.setMargins(0, 0, 0, (int) TypedValue.applyDimension(1, 30.0f, getResources().getDisplayMetrics()));
        editText.setLayoutParams(layoutParams);
        editText.setHint(k.get(k.LOGIN_EMAIL_INPUT_HINT_ID));
        editText.setImeOptions(5);
        editText.setInputType(33);
        editText.setTextColor(-7829368);
        editText.setTextSize(2, 15.0f);
        editText.setTypeface(null, 0);
        editText.setHintTextColor(-3355444);
        setEditTextBackground(context, editText);
        this.wrapperBase.addView(editText);
    }

    private void loadPasswordInput(Context context) {
        View editText = new EditText(context);
        editText.setId(12292);
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.setMargins(0, 0, 0, (int) TypedValue.applyDimension(1, 30.0f, getResources().getDisplayMetrics()));
        editText.setLayoutParams(layoutParams);
        editText.setHint(k.get(k.LOGIN_PASSWORD_INPUT_HINT_ID));
        editText.setImeOptions(5);
        editText.setInputType(NotificationCompat.FLAG_HIGH_PRIORITY);
        editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
        editText.setTextColor(-7829368);
        editText.setTextSize(2, 15.0f);
        editText.setTypeface(null, 0);
        editText.setHintTextColor(-3355444);
        setEditTextBackground(context, editText);
        this.wrapperBase.addView(editText);
    }

    private void loadLoginButton(Context context) {
        View button = new Button(context);
        button.setId(LOGIN_BUTTON_ID);
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.setMargins(0, 0, 0, (int) TypedValue.applyDimension(1, 30.0f, getResources().getDisplayMetrics()));
        button.setLayoutParams(layoutParams);
        button.setBackgroundDrawable(getButtonSelector());
        button.setText(k.get(k.LOGIN_LOGIN_BUTTON_TEXT_ID));
        button.setTextColor(-1);
        button.setTextSize(2, 15.0f);
        this.wrapperBase.addView(button);
    }

    private Drawable getButtonSelector() {
        Drawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{-16842919}, new ColorDrawable(-16777216));
        stateListDrawable.addState(new int[]{-16842919, 16842908}, new ColorDrawable(-12303292));
        stateListDrawable.addState(new int[]{16842919}, new ColorDrawable(-7829368));
        return stateListDrawable;
    }

    private void setEditTextBackground(Context context, EditText editText) {
        if (VERSION.SDK_INT < 11) {
            editText.setBackgroundDrawable(getEditTextBackground(context));
        }
    }

    private Drawable getEditTextBackground(Context context) {
        int i = (int) (context.getResources().getDisplayMetrics().density * 10.0f);
        ShapeDrawable shapeDrawable = new ShapeDrawable(new RectShape());
        Paint paint = shapeDrawable.getPaint();
        paint.setColor(-1);
        paint.setStyle(Style.FILL_AND_STROKE);
        paint.setStrokeWidth(1.0f);
        shapeDrawable.setPadding(i, i, i, i);
        i = (int) (((double) context.getResources().getDisplayMetrics().density) * 1.5d);
        ShapeDrawable shapeDrawable2 = new ShapeDrawable(new RectShape());
        Paint paint2 = shapeDrawable2.getPaint();
        paint2.setColor(-12303292);
        paint2.setStyle(Style.FILL_AND_STROKE);
        paint2.setStrokeWidth(1.0f);
        shapeDrawable2.setPadding(0, 0, 0, i);
        return new LayerDrawable(new Drawable[]{shapeDrawable2, shapeDrawable});
    }
}
