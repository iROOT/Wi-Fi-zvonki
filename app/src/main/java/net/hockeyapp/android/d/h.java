package net.hockeyapp.android.d;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import java.util.ArrayList;
import net.hockeyapp.android.FeedbackActivity;
import net.hockeyapp.android.c.f;
import net.hockeyapp.android.c.g;
import net.hockeyapp.android.e;
import net.hockeyapp.android.e.c;

public class h extends AsyncTask<Void, Void, g> {
    public static final String ID_LAST_MESSAGE_PROCESSED = "idLastMessageProcessed";
    public static final String ID_LAST_MESSAGE_SEND = "idLastMessageSend";
    public static final int NEW_ANSWER_NOTIFICATION_ID = 2;
    public static final String PREFERENCES_NAME = "net.hockeyapp.android.feedback";
    private Context context;
    private String feedbackResponse;
    private Handler handler;
    private String requestType;
    private String urlString = null;

    public h(Context context, String str, Handler handler, String str2) {
        this.context = context;
        this.feedbackResponse = str;
        this.handler = handler;
        this.requestType = str2;
    }

    public void setUrlString(String str) {
        this.urlString = str;
    }

    protected g doInBackground(Void... voidArr) {
        if (this.context == null || this.feedbackResponse == null) {
            return null;
        }
        g parseFeedbackResponse = c.getInstance().parseFeedbackResponse(this.feedbackResponse);
        if (parseFeedbackResponse == null || parseFeedbackResponse.getFeedback() == null) {
            return parseFeedbackResponse;
        }
        ArrayList messages = parseFeedbackResponse.getFeedback().getMessages();
        if (messages == null || messages.isEmpty()) {
            return parseFeedbackResponse;
        }
        checkForNewAnswers(messages);
        return parseFeedbackResponse;
    }

    protected void onPostExecute(g gVar) {
        if (gVar != null && this.handler != null) {
            Message message = new Message();
            Bundle bundle = new Bundle();
            bundle.putSerializable("parse_feedback_response", gVar);
            message.setData(bundle);
            this.handler.sendMessage(message);
        }
    }

    private void checkForNewAnswers(ArrayList<f> arrayList) {
        f fVar = (f) arrayList.get(arrayList.size() - 1);
        int id = fVar.getId();
        SharedPreferences sharedPreferences = this.context.getSharedPreferences(PREFERENCES_NAME, 0);
        if (this.requestType.equals("send")) {
            net.hockeyapp.android.e.f.applyChanges(sharedPreferences.edit().putInt(ID_LAST_MESSAGE_SEND, id).putInt(ID_LAST_MESSAGE_PROCESSED, id));
        } else if (this.requestType.equals("fetch")) {
            int i = sharedPreferences.getInt(ID_LAST_MESSAGE_SEND, -1);
            int i2 = sharedPreferences.getInt(ID_LAST_MESSAGE_PROCESSED, -1);
            if (id != i && id != i2) {
                boolean feedbackAnswered;
                net.hockeyapp.android.e.f.applyChanges(sharedPreferences.edit().putInt(ID_LAST_MESSAGE_PROCESSED, id));
                net.hockeyapp.android.f lastListener = e.getLastListener();
                if (lastListener != null) {
                    feedbackAnswered = lastListener.feedbackAnswered(fVar);
                } else {
                    feedbackAnswered = false;
                }
                if (!feedbackAnswered) {
                    startNotification(this.context);
                }
            }
        }
    }

    private void startNotification(Context context) {
        if (this.urlString != null) {
            NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
            int identifier = context.getResources().getIdentifier("ic_menu_refresh", "drawable", "android");
            Class cls = null;
            if (e.getLastListener() != null) {
                cls = e.getLastListener().getFeedbackActivityClass();
            }
            if (cls == null) {
                cls = FeedbackActivity.class;
            }
            Intent intent = new Intent();
            intent.setFlags(805306368);
            intent.setClass(context, cls);
            intent.putExtra("url", this.urlString);
            Notification createNotification = net.hockeyapp.android.e.h.createNotification(context, PendingIntent.getActivity(context, 0, intent, 1073741824), "HockeyApp Feedback", "A new answer to your feedback is available.", identifier);
            if (createNotification != null) {
                notificationManager.notify(2, createNotification);
            }
        }
    }
}
