package net.hockeyapp.android;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import com.fgmicrotec.mobile.android.fgmag.VoIP;
import com.mavenir.android.vtow.activation.ActivationAdapter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import net.hockeyapp.android.a.a;
import net.hockeyapp.android.c.c;
import net.hockeyapp.android.c.f;
import net.hockeyapp.android.c.g;
import net.hockeyapp.android.d.h;
import net.hockeyapp.android.d.i;
import net.hockeyapp.android.views.AttachmentListView;
import net.hockeyapp.android.views.AttachmentView;
import net.hockeyapp.android.views.FeedbackView;

public class FeedbackActivity extends Activity implements OnClickListener {
    private static final int MAX_ATTACHMENTS_PER_MSG = 3;
    private final int ATTACH_FILE = 2;
    private final int ATTACH_PICTURE = 1;
    private final int DIALOG_ERROR_ID = 0;
    private final int PAINT_IMAGE = 3;
    private Button addAttachmentButton;
    private Button addResponseButton;
    private Context context;
    private EditText emailInput;
    private c error;
    private Handler feedbackHandler;
    private ArrayList<f> feedbackMessages;
    private ScrollView feedbackScrollView;
    private boolean feedbackViewInitialized;
    private boolean inSendFeedback;
    private List<Uri> initialAttachments;
    private TextView lastUpdatedTextView;
    private a messagesAdapter;
    private ListView messagesListView;
    private EditText nameInput;
    private Handler parseFeedbackHandler;
    private h parseFeedbackTask;
    private Button refreshButton;
    private Button sendFeedbackButton;
    private i sendFeedbackTask;
    private EditText subjectInput;
    private EditText textInput;
    private String token;
    private String url;
    private LinearLayout wrapperLayoutFeedbackAndMessages;

    public void enableDisableSendFeedbackButton(boolean z) {
        if (this.sendFeedbackButton != null) {
            this.sendFeedbackButton.setEnabled(z);
        }
    }

    public ViewGroup getLayoutView() {
        return new FeedbackView(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case FeedbackView.SEND_FEEDBACK_BUTTON_ID /*8201*/:
                sendFeedback();
                return;
            case FeedbackView.ADD_ATTACHMENT_BUTTON_ID /*8208*/:
                if (((ViewGroup) findViewById(FeedbackView.WRAPPER_LAYOUT_ATTACHMENTS)).getChildCount() >= 3) {
                    Toast.makeText(this, String.format("", new Object[]{Integer.valueOf(3)}), 1000).show();
                    return;
                }
                openContextMenu(view);
                return;
            case FeedbackView.ADD_RESPONSE_BUTTON_ID /*131088*/:
                configureFeedbackView(false);
                this.inSendFeedback = true;
                return;
            case FeedbackView.REFRESH_BUTTON_ID /*131089*/:
                sendFetchFeedback(this.url, null, null, null, null, null, net.hockeyapp.android.e.f.getInstance().getFeedbackTokenFromPrefs(this.context), this.feedbackHandler, true);
                return;
            default:
                return;
        }
    }

    public boolean onContextItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case 1:
            case 2:
                return addAttachment(menuItem.getItemId());
            default:
                return super.onContextItemSelected(menuItem);
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(getLayoutView());
        setTitle(k.get(k.FEEDBACK_TITLE_ID));
        this.context = this;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.url = extras.getString("url");
            Parcelable[] parcelableArray = extras.getParcelableArray("initialAttachments");
            if (parcelableArray != null) {
                this.initialAttachments = new ArrayList();
                for (Parcelable parcelable : parcelableArray) {
                    this.initialAttachments.add((Uri) parcelable);
                }
            }
        }
        if (bundle != null) {
            this.feedbackViewInitialized = bundle.getBoolean("feedbackViewInitialized");
            this.inSendFeedback = bundle.getBoolean("inSendFeedback");
        } else {
            this.inSendFeedback = false;
            this.feedbackViewInitialized = false;
        }
        ((NotificationManager) getSystemService("notification")).cancel(2);
        initFeedbackHandler();
        initParseFeedbackHandler();
        configureAppropriateView();
    }

    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenuInfo contextMenuInfo) {
        super.onCreateContextMenu(contextMenu, view, contextMenuInfo);
        contextMenu.add(0, 2, 0, k.get(k.FEEDBACK_ATTACH_FILE_ID));
        contextMenu.add(0, 1, 0, k.get(k.FEEDBACK_ATTACH_PICTURE_ID));
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return super.onKeyDown(i, keyEvent);
        }
        if (this.inSendFeedback) {
            this.inSendFeedback = false;
            configureAppropriateView();
        } else {
            finish();
        }
        return true;
    }

    public Object onRetainNonConfigurationInstance() {
        if (this.sendFeedbackTask != null) {
            this.sendFeedbackTask.detach();
        }
        return this.sendFeedbackTask;
    }

    protected void configureFeedbackView(boolean z) {
        this.feedbackScrollView = (ScrollView) findViewById(FeedbackView.FEEDBACK_SCROLLVIEW_ID);
        this.wrapperLayoutFeedbackAndMessages = (LinearLayout) findViewById(FeedbackView.WRAPPER_LAYOUT_FEEDBACK_AND_MESSAGES_ID);
        this.messagesListView = (ListView) findViewById(FeedbackView.MESSAGES_LISTVIEW_ID);
        if (z) {
            this.wrapperLayoutFeedbackAndMessages.setVisibility(0);
            this.feedbackScrollView.setVisibility(8);
            this.lastUpdatedTextView = (TextView) findViewById(8192);
            this.addResponseButton = (Button) findViewById(FeedbackView.ADD_RESPONSE_BUTTON_ID);
            this.addResponseButton.setOnClickListener(this);
            this.refreshButton = (Button) findViewById(FeedbackView.REFRESH_BUTTON_ID);
            this.refreshButton.setOnClickListener(this);
            return;
        }
        this.wrapperLayoutFeedbackAndMessages.setVisibility(8);
        this.feedbackScrollView.setVisibility(0);
        this.nameInput = (EditText) findViewById(8194);
        this.emailInput = (EditText) findViewById(FeedbackView.EMAIL_EDIT_TEXT_ID);
        this.subjectInput = (EditText) findViewById(FeedbackView.SUBJECT_EDIT_TEXT_ID);
        this.textInput = (EditText) findViewById(FeedbackView.TEXT_EDIT_TEXT_ID);
        if (!this.feedbackViewInitialized) {
            String nameEmailFromPrefs = net.hockeyapp.android.e.f.getInstance().getNameEmailFromPrefs(this.context);
            if (nameEmailFromPrefs != null) {
                String[] split = nameEmailFromPrefs.split("\\|");
                if (split != null && split.length >= 2) {
                    this.nameInput.setText(split[0]);
                    this.emailInput.setText(split[1]);
                    if (split.length >= 3) {
                        this.subjectInput.setText(split[2]);
                        this.textInput.requestFocus();
                    } else {
                        this.subjectInput.requestFocus();
                    }
                }
            } else {
                this.nameInput.setText("");
                this.emailInput.setText("");
                this.subjectInput.setText("");
                this.nameInput.requestFocus();
            }
            this.feedbackViewInitialized = true;
        }
        this.textInput.setText("");
        if (net.hockeyapp.android.e.f.getInstance().getFeedbackTokenFromPrefs(this.context) != null) {
            this.subjectInput.setVisibility(8);
        } else {
            this.subjectInput.setVisibility(0);
        }
        ViewGroup viewGroup = (ViewGroup) findViewById(FeedbackView.WRAPPER_LAYOUT_ATTACHMENTS);
        viewGroup.removeAllViews();
        if (this.initialAttachments != null) {
            for (Uri attachmentView : this.initialAttachments) {
                viewGroup.addView(new AttachmentView((Context) this, viewGroup, attachmentView, true));
            }
        }
        this.addAttachmentButton = (Button) findViewById(FeedbackView.ADD_ATTACHMENT_BUTTON_ID);
        this.addAttachmentButton.setOnClickListener(this);
        registerForContextMenu(this.addAttachmentButton);
        this.sendFeedbackButton = (Button) findViewById(FeedbackView.SEND_FEEDBACK_BUTTON_ID);
        this.sendFeedbackButton.setOnClickListener(this);
    }

    protected void onSendFeedbackResult(boolean z) {
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        if (i2 == -1) {
            if (i == 2) {
                Uri data = intent.getData();
                if (data != null) {
                    ViewGroup viewGroup = (ViewGroup) findViewById(FeedbackView.WRAPPER_LAYOUT_ATTACHMENTS);
                    viewGroup.addView(new AttachmentView((Context) this, viewGroup, data, true));
                }
            } else if (i == 1) {
                Parcelable data2 = intent.getData();
                if (data2 != null) {
                    try {
                        Intent intent2 = new Intent(this, PaintActivity.class);
                        intent2.putExtra("imageUri", data2);
                        startActivityForResult(intent2, 3);
                    } catch (Throwable e) {
                        Log.e("HockeyApp", "Paint activity not declared!", e);
                    }
                }
            } else if (i == 3) {
                Uri uri = (Uri) intent.getParcelableExtra("imageUri");
                if (uri != null) {
                    ViewGroup viewGroup2 = (ViewGroup) findViewById(FeedbackView.WRAPPER_LAYOUT_ATTACHMENTS);
                    viewGroup2.addView(new AttachmentView((Context) this, viewGroup2, uri, true));
                }
            }
        }
    }

    protected Dialog onCreateDialog(int i) {
        switch (i) {
            case 0:
                return new Builder(this).setMessage(k.get(k.DIALOG_ERROR_MESSAGE_ID)).setCancelable(false).setTitle(k.get(k.DIALOG_ERROR_TITLE_ID)).setIcon(17301543).setPositiveButton(k.get(k.DIALOG_POSITIVE_BUTTON_ID), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FeedbackActivity.this.error = null;
                        dialogInterface.cancel();
                    }
                }).create();
            default:
                return null;
        }
    }

    protected void onPrepareDialog(int i, Dialog dialog) {
        switch (i) {
            case 0:
                AlertDialog alertDialog = (AlertDialog) dialog;
                if (this.error != null) {
                    alertDialog.setMessage(this.error.getMessage());
                    return;
                } else {
                    alertDialog.setMessage(k.get(k.FEEDBACK_GENERIC_ERROR_ID));
                    return;
                }
            default:
                return;
        }
    }

    protected void onRestoreInstanceState(Bundle bundle) {
        if (bundle != null) {
            ViewGroup viewGroup = (ViewGroup) findViewById(FeedbackView.WRAPPER_LAYOUT_ATTACHMENTS);
            Iterator it = bundle.getParcelableArrayList("attachments").iterator();
            while (it.hasNext()) {
                viewGroup.addView(new AttachmentView((Context) this, viewGroup, (Uri) it.next(), true));
            }
            this.feedbackViewInitialized = bundle.getBoolean("feedbackViewInitialized");
        }
        super.onRestoreInstanceState(bundle);
    }

    protected void onSaveInstanceState(Bundle bundle) {
        bundle.putParcelableArrayList("attachments", ((AttachmentListView) findViewById(FeedbackView.WRAPPER_LAYOUT_ATTACHMENTS)).getAttachments());
        bundle.putBoolean("feedbackViewInitialized", this.feedbackViewInitialized);
        bundle.putBoolean("inSendFeedback", this.inSendFeedback);
        super.onSaveInstanceState(bundle);
    }

    private boolean addAttachment(int i) {
        Intent intent;
        if (i == 2) {
            intent = new Intent();
            intent.setType("*/*");
            intent.setAction("android.intent.action.GET_CONTENT");
            startActivityForResult(Intent.createChooser(intent, k.get(k.FEEDBACK_SELECT_FILE_ID)), 2);
            return true;
        } else if (i != 1) {
            return false;
        } else {
            intent = new Intent();
            intent.setType("image/*");
            intent.setAction("android.intent.action.GET_CONTENT");
            startActivityForResult(Intent.createChooser(intent, k.get(k.FEEDBACK_SELECT_PICTURE_ID)), 1);
            return true;
        }
    }

    private void configureAppropriateView() {
        this.token = net.hockeyapp.android.e.f.getInstance().getFeedbackTokenFromPrefs(this);
        if (this.token == null || this.inSendFeedback) {
            configureFeedbackView(false);
            return;
        }
        configureFeedbackView(true);
        sendFetchFeedback(this.url, null, null, null, null, null, this.token, this.feedbackHandler, true);
    }

    private void createParseFeedbackTask(String str, String str2) {
        this.parseFeedbackTask = new h(this, str, this.parseFeedbackHandler, str2);
    }

    private void hideKeyboard() {
        if (this.textInput != null) {
            ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(this.textInput.getWindowToken(), 0);
        }
    }

    private void initFeedbackHandler() {
        this.feedbackHandler = new Handler() {
            public void handleMessage(Message message) {
                boolean z = false;
                FeedbackActivity.this.error = new c();
                if (message == null || message.getData() == null) {
                    FeedbackActivity.this.error.setMessage(k.get(k.FEEDBACK_SEND_GENERIC_ERROR_ID));
                } else {
                    Bundle data = message.getData();
                    String string = data.getString("feedback_response");
                    String string2 = data.getString("feedback_status");
                    String string3 = data.getString("request_type");
                    if (string3.equals("send") && (string == null || Integer.parseInt(string2) != ActivationAdapter.OP_CONFIGURATION_APP_UPDATE)) {
                        FeedbackActivity.this.error.setMessage(k.get(k.FEEDBACK_SEND_GENERIC_ERROR_ID));
                    } else if (string3.equals("fetch") && string2 != null && (Integer.parseInt(string2) == VoIP.REASON_CODE_NOT_FOUND || Integer.parseInt(string2) == 422)) {
                        FeedbackActivity.this.resetFeedbackView();
                        z = true;
                    } else if (string != null) {
                        FeedbackActivity.this.startParseFeedbackTask(string, string3);
                        z = true;
                    } else {
                        FeedbackActivity.this.error.setMessage(k.get(k.FEEDBACK_SEND_NETWORK_ERROR_ID));
                    }
                }
                if (!z) {
                    FeedbackActivity.this.runOnUiThread(new Runnable() {
                        public void run() {
                            FeedbackActivity.this.enableDisableSendFeedbackButton(true);
                            FeedbackActivity.this.showDialog(0);
                        }
                    });
                }
                FeedbackActivity.this.onSendFeedbackResult(z);
            }
        };
    }

    private void initParseFeedbackHandler() {
        this.parseFeedbackHandler = new Handler() {
            public void handleMessage(Message message) {
                boolean z;
                FeedbackActivity.this.error = new c();
                if (!(message == null || message.getData() == null)) {
                    g gVar = (g) message.getData().getSerializable("parse_feedback_response");
                    if (gVar != null) {
                        if (!gVar.getStatus().equalsIgnoreCase("success")) {
                            z = false;
                        } else if (gVar.getToken() != null) {
                            net.hockeyapp.android.e.f.getInstance().saveFeedbackTokenToPrefs(FeedbackActivity.this.context, gVar.getToken());
                            FeedbackActivity.this.loadFeedbackMessages(gVar);
                            FeedbackActivity.this.inSendFeedback = false;
                            z = true;
                        } else {
                            z = true;
                        }
                        if (!z) {
                            FeedbackActivity.this.runOnUiThread(new Runnable() {
                                public void run() {
                                    FeedbackActivity.this.showDialog(0);
                                }
                            });
                        }
                        FeedbackActivity.this.enableDisableSendFeedbackButton(true);
                    }
                }
                z = false;
                if (z) {
                    FeedbackActivity.this.runOnUiThread(/* anonymous class already generated */);
                }
                FeedbackActivity.this.enableDisableSendFeedbackButton(true);
            }
        };
    }

    private void loadFeedbackMessages(final g gVar) {
        runOnUiThread(new Runnable() {
            public void run() {
                FeedbackActivity.this.configureFeedbackView(true);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
                SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("d MMM h:mm a");
                if (gVar != null && gVar.getFeedback() != null && gVar.getFeedback().getMessages() != null && gVar.getFeedback().getMessages().size() > 0) {
                    FeedbackActivity.this.feedbackMessages = gVar.getFeedback().getMessages();
                    Collections.reverse(FeedbackActivity.this.feedbackMessages);
                    try {
                        Date parse = simpleDateFormat.parse(((f) FeedbackActivity.this.feedbackMessages.get(0)).getCreatedAt());
                        FeedbackActivity.this.lastUpdatedTextView.setText(String.format(k.get(k.FEEDBACK_LAST_UPDATED_TEXT_ID) + " %s", new Object[]{simpleDateFormat2.format(parse)}));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    if (FeedbackActivity.this.messagesAdapter == null) {
                        FeedbackActivity.this.messagesAdapter = new a(FeedbackActivity.this.context, FeedbackActivity.this.feedbackMessages);
                    } else {
                        FeedbackActivity.this.messagesAdapter.clear();
                        Iterator it = FeedbackActivity.this.feedbackMessages.iterator();
                        while (it.hasNext()) {
                            FeedbackActivity.this.messagesAdapter.add((f) it.next());
                        }
                        FeedbackActivity.this.messagesAdapter.notifyDataSetChanged();
                    }
                    FeedbackActivity.this.messagesListView.setAdapter(FeedbackActivity.this.messagesAdapter);
                }
            }
        });
    }

    private void resetFeedbackView() {
        runOnUiThread(new Runnable() {
            public void run() {
                net.hockeyapp.android.e.f.getInstance().saveFeedbackTokenToPrefs(FeedbackActivity.this, null);
                net.hockeyapp.android.e.f.applyChanges(FeedbackActivity.this.getSharedPreferences(h.PREFERENCES_NAME, 0).edit().remove(h.ID_LAST_MESSAGE_SEND).remove(h.ID_LAST_MESSAGE_PROCESSED));
                FeedbackActivity.this.configureFeedbackView(false);
            }
        });
    }

    private void sendFeedback() {
        enableDisableSendFeedbackButton(false);
        hideKeyboard();
        String feedbackTokenFromPrefs = net.hockeyapp.android.e.f.getInstance().getFeedbackTokenFromPrefs(this.context);
        String trim = this.nameInput.getText().toString().trim();
        String trim2 = this.emailInput.getText().toString().trim();
        String trim3 = this.subjectInput.getText().toString().trim();
        Object trim4 = this.textInput.getText().toString().trim();
        if (TextUtils.isEmpty(trim3)) {
            this.subjectInput.setVisibility(0);
            setError(this.subjectInput, k.FEEDBACK_VALIDATE_SUBJECT_ERROR_ID);
        } else if (e.getRequireUserName() == net.hockeyapp.android.c.h.REQUIRED && TextUtils.isEmpty(trim)) {
            setError(this.nameInput, k.FEEDBACK_VALIDATE_NAME_ERROR_ID);
        } else if (e.getRequireUserEmail() == net.hockeyapp.android.c.h.REQUIRED && TextUtils.isEmpty(trim2)) {
            setError(this.emailInput, k.FEEDBACK_VALIDATE_EMAIL_EMPTY_ID);
        } else if (TextUtils.isEmpty(trim4)) {
            setError(this.textInput, k.FEEDBACK_VALIDATE_TEXT_ERROR_ID);
        } else if (e.getRequireUserEmail() != net.hockeyapp.android.c.h.REQUIRED || net.hockeyapp.android.e.h.isValidEmail(trim2)) {
            net.hockeyapp.android.e.f.getInstance().saveNameEmailSubjectToPrefs(this.context, trim, trim2, trim3);
            sendFetchFeedback(this.url, trim, trim2, trim3, trim4, ((AttachmentListView) findViewById(FeedbackView.WRAPPER_LAYOUT_ATTACHMENTS)).getAttachments(), feedbackTokenFromPrefs, this.feedbackHandler, false);
        } else {
            setError(this.emailInput, k.FEEDBACK_VALIDATE_EMAIL_ERROR_ID);
        }
    }

    private void setError(EditText editText, int i) {
        editText.setError(k.get(i));
        enableDisableSendFeedbackButton(true);
    }

    private void sendFetchFeedback(String str, String str2, String str3, String str4, String str5, List<Uri> list, String str6, Handler handler, boolean z) {
        this.sendFeedbackTask = new i(this.context, str, str2, str3, str4, str5, list, str6, handler, z);
        net.hockeyapp.android.e.a.execute(this.sendFeedbackTask);
    }

    private void startParseFeedbackTask(String str, String str2) {
        createParseFeedbackTask(str, str2);
        net.hockeyapp.android.e.a.execute(this.parseFeedbackTask);
    }
}
