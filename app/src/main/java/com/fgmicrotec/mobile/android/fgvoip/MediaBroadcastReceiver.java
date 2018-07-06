package com.fgmicrotec.mobile.android.fgvoip;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;
import com.mavenir.android.common.q;

public class MediaBroadcastReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        q.a("MediaBroadcastReceiver", "onReceive(): action: " + intent.getAction());
        if (intent.getAction().equals("android.intent.action.MEDIA_BUTTON")) {
            KeyEvent keyEvent = (KeyEvent) intent.getParcelableExtra("android.intent.extra.KEY_EVENT");
            q.a("MediaBroadcastReceiver", "onReceive(): keyEvent: " + keyEvent);
            if (keyEvent != null && keyEvent.getAction() == 0) {
                int keyCode = keyEvent.getKeyCode();
                if (keyCode == 87) {
                    q.a("MediaBroadcastReceiver", "Next Pressed");
                } else if (keyCode == 88) {
                    q.a("MediaBroadcastReceiver", "Previous pressed");
                } else if (keyCode == 79) {
                    q.a("MediaBroadcastReceiver", "Head Set Hook pressed");
                    Intent intent2 = new Intent();
                    intent2.setAction("CallManager.AcceptInvitationReq");
                    context.sendBroadcast(intent2);
                } else if (keyCode == 25) {
                    q.a("MediaBroadcastReceiver", "Head Set Volume Down pressed");
                } else if (keyCode == 24) {
                    q.a("MediaBroadcastReceiver", "Head Set Volume Up pressed");
                } else {
                    q.a("MediaBroadcastReceiver", "Unhandled keycode");
                }
            }
        }
    }
}
