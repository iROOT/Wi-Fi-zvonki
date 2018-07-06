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
import android.text.TextUtils.TruncateAt;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import net.hockeyapp.android.c.h;
import net.hockeyapp.android.e;
import net.hockeyapp.android.k;

public class FeedbackView extends LinearLayout {
    public static final int ADD_ATTACHMENT_BUTTON_ID = 8208;
    public static final int ADD_RESPONSE_BUTTON_ID = 131088;
    public static final int EMAIL_EDIT_TEXT_ID = 8196;
    public static final int FEEDBACK_SCROLLVIEW_ID = 131095;
    public static final int LAST_UPDATED_TEXT_VIEW_ID = 8192;
    public static final int MESSAGES_LISTVIEW_ID = 131094;
    public static final int NAME_EDIT_TEXT_ID = 8194;
    public static final int REFRESH_BUTTON_ID = 131089;
    public static final int SEND_FEEDBACK_BUTTON_ID = 8201;
    public static final int SUBJECT_EDIT_TEXT_ID = 8198;
    public static final int TEXT_EDIT_TEXT_ID = 8200;
    public static final int WRAPPER_BASE_ID = 131090;
    public static final int WRAPPER_LAYOUT_ATTACHMENTS = 8209;
    public static final int WRAPPER_LAYOUT_BUTTONS_ID = 131092;
    public static final int WRAPPER_LAYOUT_FEEDBACK_AND_MESSAGES_ID = 131093;
    public static final int WRAPPER_LAYOUT_FEEDBACK_ID = 131091;
    private ScrollView feedbackScrollView;
    private ListView messagesListView;
    private LinearLayout wrapperBase;
    private ViewGroup wrapperLayoutAttachments;
    private LinearLayout wrapperLayoutButtons;
    private LinearLayout wrapperLayoutFeedback;
    private LinearLayout wrapperLayoutFeedbackAndMessages;

    public FeedbackView(Context context) {
        super(context);
        loadLayoutParams(context);
        loadWrapperBase(context);
        loadFeedbackScrollView(context);
        loadWrapperLayoutFeedback(context);
        loadWrapperLayoutFeedbackAndMessages(context);
        loadNameInput(context);
        loadEmailInput(context);
        loadSubjectInput(context);
        loadTextInput(context);
        loadAttachmentList(context);
        loadAddAttachmentButton(context);
        loadSendFeedbackButton(context);
        loadLastUpdatedLabel(context);
        loadWrapperLayoutButtons(context);
        loadAddResponseButton(context);
        loadRefreshButton(context);
        loadMessagesListView(context);
    }

    private void loadLayoutParams(Context context) {
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        setBackgroundColor(-1);
        setLayoutParams(layoutParams);
    }

    private void loadWrapperBase(Context context) {
        this.wrapperBase = new LinearLayout(context);
        this.wrapperBase.setId(WRAPPER_BASE_ID);
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
        layoutParams.gravity = 49;
        this.wrapperBase.setLayoutParams(layoutParams);
        this.wrapperBase.setPadding(0, 0, 0, 0);
        this.wrapperBase.setOrientation(1);
        addView(this.wrapperBase);
    }

    private void loadFeedbackScrollView(Context context) {
        this.feedbackScrollView = new ScrollView(context);
        this.feedbackScrollView.setId(FEEDBACK_SCROLLVIEW_ID);
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
        int applyDimension = (int) TypedValue.applyDimension(1, 10.0f, getResources().getDisplayMetrics());
        layoutParams.gravity = 17;
        this.feedbackScrollView.setLayoutParams(layoutParams);
        this.feedbackScrollView.setPadding(applyDimension, 0, applyDimension, 0);
        this.wrapperBase.addView(this.feedbackScrollView);
    }

    private void loadWrapperLayoutFeedback(Context context) {
        this.wrapperLayoutFeedback = new LinearLayout(context);
        this.wrapperLayoutFeedback.setId(WRAPPER_LAYOUT_FEEDBACK_ID);
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
        int applyDimension = (int) TypedValue.applyDimension(1, 10.0f, getResources().getDisplayMetrics());
        layoutParams.gravity = 3;
        this.wrapperLayoutFeedback.setLayoutParams(layoutParams);
        this.wrapperLayoutFeedback.setPadding(applyDimension, applyDimension, applyDimension, applyDimension);
        this.wrapperLayoutFeedback.setGravity(48);
        this.wrapperLayoutFeedback.setOrientation(1);
        this.feedbackScrollView.addView(this.wrapperLayoutFeedback);
    }

    private void loadWrapperLayoutFeedbackAndMessages(Context context) {
        this.wrapperLayoutFeedbackAndMessages = new LinearLayout(context);
        this.wrapperLayoutFeedbackAndMessages.setId(WRAPPER_LAYOUT_FEEDBACK_AND_MESSAGES_ID);
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
        int applyDimension = (int) TypedValue.applyDimension(1, 10.0f, getResources().getDisplayMetrics());
        layoutParams.gravity = 17;
        this.wrapperLayoutFeedbackAndMessages.setLayoutParams(layoutParams);
        this.wrapperLayoutFeedbackAndMessages.setPadding(applyDimension, applyDimension, applyDimension, 0);
        this.wrapperLayoutFeedbackAndMessages.setGravity(48);
        this.wrapperLayoutFeedbackAndMessages.setOrientation(1);
        this.wrapperBase.addView(this.wrapperLayoutFeedbackAndMessages);
    }

    private void loadWrapperLayoutButtons(Context context) {
        this.wrapperLayoutButtons = new LinearLayout(context);
        this.wrapperLayoutButtons.setId(WRAPPER_LAYOUT_BUTTONS_ID);
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        int applyDimension = (int) TypedValue.applyDimension(1, 10.0f, getResources().getDisplayMetrics());
        layoutParams.gravity = 3;
        this.wrapperLayoutButtons.setLayoutParams(layoutParams);
        this.wrapperLayoutButtons.setPadding(0, applyDimension, 0, applyDimension);
        this.wrapperLayoutButtons.setGravity(48);
        this.wrapperLayoutButtons.setOrientation(0);
        this.wrapperLayoutFeedbackAndMessages.addView(this.wrapperLayoutButtons);
    }

    private void loadMessagesListView(Context context) {
        this.messagesListView = new ListView(context);
        this.messagesListView.setId(MESSAGES_LISTVIEW_ID);
        int applyDimension = (int) TypedValue.applyDimension(1, 10.0f, getResources().getDisplayMetrics());
        this.messagesListView.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        this.messagesListView.setPadding(0, applyDimension, 0, applyDimension);
        this.wrapperLayoutFeedbackAndMessages.addView(this.messagesListView);
    }

    private void loadNameInput(Context context) {
        int i = 0;
        View editText = new EditText(context);
        editText.setId(8194);
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        int applyDimension = (int) TypedValue.applyDimension(1, 20.0f, getResources().getDisplayMetrics());
        layoutParams.setMargins(0, applyDimension / 2, 0, applyDimension);
        editText.setLayoutParams(layoutParams);
        editText.setImeOptions(5);
        editText.setInputType(16385);
        editText.setSingleLine(true);
        editText.setTextColor(-7829368);
        editText.setTextSize(2, 15.0f);
        editText.setTypeface(null, 0);
        editText.setHint(k.get(k.FEEDBACK_NAME_INPUT_HINT_ID));
        editText.setHintTextColor(-3355444);
        setEditTextBackground(context, editText);
        if (e.getRequireUserName() == h.DONT_SHOW) {
            i = 8;
        }
        editText.setVisibility(i);
        this.wrapperLayoutFeedback.addView(editText);
    }

    private void loadEmailInput(Context context) {
        int i = 0;
        View editText = new EditText(context);
        editText.setId(EMAIL_EDIT_TEXT_ID);
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.setMargins(0, 0, 0, (int) TypedValue.applyDimension(1, 20.0f, getResources().getDisplayMetrics()));
        editText.setLayoutParams(layoutParams);
        editText.setImeOptions(5);
        editText.setInputType(33);
        editText.setSingleLine(true);
        editText.setTextColor(-7829368);
        editText.setTextSize(2, 15.0f);
        editText.setTypeface(null, 0);
        editText.setHint(k.get(k.FEEDBACK_EMAIL_INPUT_HINT_ID));
        editText.setHintTextColor(-3355444);
        setEditTextBackground(context, editText);
        if (e.getRequireUserEmail() == h.DONT_SHOW) {
            i = 8;
        }
        editText.setVisibility(i);
        this.wrapperLayoutFeedback.addView(editText);
    }

    private void loadSubjectInput(Context context) {
        View editText = new EditText(context);
        editText.setId(SUBJECT_EDIT_TEXT_ID);
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.setMargins(0, 0, 0, (int) TypedValue.applyDimension(1, 20.0f, getResources().getDisplayMetrics()));
        editText.setLayoutParams(layoutParams);
        editText.setImeOptions(5);
        editText.setInputType(16433);
        editText.setSingleLine(true);
        editText.setTextColor(-7829368);
        editText.setTextSize(2, 15.0f);
        editText.setTypeface(null, 0);
        editText.setHint(k.get(k.FEEDBACK_SUBJECT_INPUT_HINT_ID));
        editText.setHintTextColor(-3355444);
        setEditTextBackground(context, editText);
        this.wrapperLayoutFeedback.addView(editText);
    }

    private void loadTextInput(Context context) {
        View editText = new EditText(context);
        editText.setId(TEXT_EDIT_TEXT_ID);
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        int applyDimension = (int) TypedValue.applyDimension(1, 100.0f, getResources().getDisplayMetrics());
        layoutParams.setMargins(0, 0, 0, (int) TypedValue.applyDimension(1, 20.0f, getResources().getDisplayMetrics()));
        editText.setLayoutParams(layoutParams);
        editText.setImeOptions(5);
        editText.setInputType(16385);
        editText.setSingleLine(false);
        editText.setTextColor(-7829368);
        editText.setTextSize(2, 15.0f);
        editText.setTypeface(null, 0);
        editText.setMinimumHeight(applyDimension);
        editText.setHint(k.get(k.FEEDBACK_MESSAGE_INPUT_HINT_ID));
        editText.setHintTextColor(-3355444);
        setEditTextBackground(context, editText);
        this.wrapperLayoutFeedback.addView(editText);
    }

    private void loadLastUpdatedLabel(Context context) {
        View textView = new TextView(context);
        textView.setId(8192);
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        int applyDimension = (int) TypedValue.applyDimension(1, 10.0f, getResources().getDisplayMetrics());
        layoutParams.setMargins(0, 0, 0, 0);
        textView.setLayoutParams(layoutParams);
        textView.setPadding(0, applyDimension, 0, applyDimension);
        textView.setEllipsize(TruncateAt.END);
        textView.setShadowLayer(1.0f, 0.0f, 1.0f, -1);
        textView.setSingleLine(true);
        textView.setText(k.get(k.FEEDBACK_LAST_UPDATED_TEXT_ID));
        textView.setTextColor(-7829368);
        textView.setTextSize(2, 15.0f);
        textView.setTypeface(null, 0);
        this.wrapperLayoutFeedbackAndMessages.addView(textView);
    }

    private void loadAttachmentList(Context context) {
        this.wrapperLayoutAttachments = new AttachmentListView(context);
        this.wrapperLayoutAttachments.setId(WRAPPER_LAYOUT_ATTACHMENTS);
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
        int applyDimension = (int) TypedValue.applyDimension(1, 10.0f, getResources().getDisplayMetrics());
        layoutParams.gravity = 3;
        this.wrapperLayoutAttachments.setLayoutParams(layoutParams);
        this.wrapperLayoutAttachments.setPadding(0, 0, 0, applyDimension);
        this.wrapperLayoutFeedback.addView(this.wrapperLayoutAttachments);
    }

    private void loadAddAttachmentButton(Context context) {
        View button = new Button(context);
        button.setId(ADD_ATTACHMENT_BUTTON_ID);
        int applyDimension = (int) TypedValue.applyDimension(1, 10.0f, getResources().getDisplayMetrics());
        int applyDimension2 = (int) TypedValue.applyDimension(1, 30.0f, getResources().getDisplayMetrics());
        int applyDimension3 = (int) TypedValue.applyDimension(1, 10.0f, getResources().getDisplayMetrics());
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.setMargins(0, 0, 0, applyDimension3);
        layoutParams.gravity = 1;
        button.setLayoutParams(layoutParams);
        button.setBackgroundDrawable(getButtonSelector());
        button.setPadding(applyDimension2, applyDimension, applyDimension2, applyDimension);
        button.setText(k.get(k.FEEDBACK_ATTACHMENT_BUTTON_TEXT_ID));
        button.setTextColor(-1);
        button.setTextSize(2, 15.0f);
        this.wrapperLayoutFeedback.addView(button);
    }

    private void loadSendFeedbackButton(Context context) {
        View button = new Button(context);
        button.setId(SEND_FEEDBACK_BUTTON_ID);
        int applyDimension = (int) TypedValue.applyDimension(1, 10.0f, getResources().getDisplayMetrics());
        int applyDimension2 = (int) TypedValue.applyDimension(1, 30.0f, getResources().getDisplayMetrics());
        int applyDimension3 = (int) TypedValue.applyDimension(1, 10.0f, getResources().getDisplayMetrics());
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.setMargins(0, 0, 0, applyDimension3);
        layoutParams.gravity = 1;
        button.setLayoutParams(layoutParams);
        button.setBackgroundDrawable(getButtonSelector());
        button.setPadding(applyDimension2, applyDimension, applyDimension2, applyDimension);
        button.setText(k.get(k.FEEDBACK_SEND_BUTTON_TEXT_ID));
        button.setTextColor(-1);
        button.setTextSize(2, 15.0f);
        this.wrapperLayoutFeedback.addView(button);
    }

    private void loadAddResponseButton(Context context) {
        View button = new Button(context);
        button.setId(ADD_RESPONSE_BUTTON_ID);
        int applyDimension = (int) TypedValue.applyDimension(1, 10.0f, getResources().getDisplayMetrics());
        int applyDimension2 = (int) TypedValue.applyDimension(1, 10.0f, getResources().getDisplayMetrics());
        int applyDimension3 = (int) TypedValue.applyDimension(1, 5.0f, getResources().getDisplayMetrics());
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.setMargins(0, 0, applyDimension3, applyDimension2);
        layoutParams.gravity = 1;
        layoutParams.weight = 1.0f;
        button.setLayoutParams(layoutParams);
        button.setBackgroundDrawable(getButtonSelector());
        button.setPadding(0, applyDimension, 0, applyDimension);
        button.setGravity(17);
        button.setText(k.get(k.FEEDBACK_RESPONSE_BUTTON_TEXT_ID));
        button.setTextColor(-1);
        button.setTextSize(2, 15.0f);
        this.wrapperLayoutButtons.addView(button);
    }

    private void loadRefreshButton(Context context) {
        View button = new Button(context);
        button.setId(REFRESH_BUTTON_ID);
        int applyDimension = (int) TypedValue.applyDimension(1, 10.0f, getResources().getDisplayMetrics());
        int applyDimension2 = (int) TypedValue.applyDimension(1, 10.0f, getResources().getDisplayMetrics());
        int applyDimension3 = (int) TypedValue.applyDimension(1, 5.0f, getResources().getDisplayMetrics());
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.setMargins(applyDimension3, 0, 0, applyDimension2);
        layoutParams.gravity = 1;
        button.setLayoutParams(layoutParams);
        button.setBackgroundDrawable(getButtonSelector());
        button.setPadding(0, applyDimension, 0, applyDimension);
        button.setGravity(17);
        button.setText(k.get(k.FEEDBACK_REFRESH_BUTTON_TEXT_ID));
        button.setTextColor(-1);
        button.setTextSize(2, 15.0f);
        layoutParams.weight = 1.0f;
        this.wrapperLayoutButtons.addView(button);
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
