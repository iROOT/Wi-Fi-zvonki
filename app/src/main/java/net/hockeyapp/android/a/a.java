package net.hockeyapp.android.a;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import net.hockeyapp.android.c.e;
import net.hockeyapp.android.c.f;
import net.hockeyapp.android.views.AttachmentListView;
import net.hockeyapp.android.views.AttachmentView;
import net.hockeyapp.android.views.FeedbackMessageView;

public class a extends BaseAdapter {
    private AttachmentListView attachmentListView;
    private TextView authorTextView;
    private Context context;
    private Date date;
    private TextView dateTextView;
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
    private SimpleDateFormat formatNew = new SimpleDateFormat("d MMM h:mm a");
    private TextView messageTextView;
    private ArrayList<f> messagesList;

    public a(Context context, ArrayList<f> arrayList) {
        this.context = context;
        this.messagesList = arrayList;
    }

    public int getCount() {
        return this.messagesList.size();
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        f fVar = (f) this.messagesList.get(i);
        if (view == null) {
            view = new FeedbackMessageView(this.context);
        } else {
            FeedbackMessageView feedbackMessageView = (FeedbackMessageView) view;
        }
        if (fVar != null) {
            this.authorTextView = (TextView) view.findViewById(12289);
            this.dateTextView = (TextView) view.findViewById(12290);
            this.messageTextView = (TextView) view.findViewById(12291);
            this.attachmentListView = (AttachmentListView) view.findViewById(12292);
            try {
                this.date = this.format.parse(fVar.getCreatedAt());
                this.dateTextView.setText(this.formatNew.format(this.date));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            this.authorTextView.setText(fVar.getName());
            this.messageTextView.setText(fVar.getText());
            this.attachmentListView.removeAllViews();
            for (e eVar : fVar.getFeedbackAttachments()) {
                View attachmentView = new AttachmentView(this.context, this.attachmentListView, eVar, false);
                net.hockeyapp.android.d.a.getInstance().download(eVar, attachmentView);
                this.attachmentListView.addView(attachmentView);
            }
        }
        view.setFeedbackMessageViewBgAndTextColor(i % 2 == 0 ? 0 : 1);
        return view;
    }

    public Object getItem(int i) {
        return this.messagesList.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public void clear() {
        if (this.messagesList != null) {
            this.messagesList.clear();
        }
    }

    public void add(f fVar) {
        if (fVar != null && this.messagesList != null) {
            this.messagesList.add(fVar);
        }
    }
}
