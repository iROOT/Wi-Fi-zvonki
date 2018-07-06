package net.hockeyapp.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import net.hockeyapp.android.d.g;
import net.hockeyapp.android.e.a;
import net.hockeyapp.android.views.LoginView;

public class LoginActivity extends Activity implements OnClickListener {
    private Handler loginHandler;
    private g loginTask;
    private int mode;
    private String secret;
    private String url;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(new LoginView(this));
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.url = extras.getString("url");
            this.secret = extras.getString("secret");
            this.mode = extras.getInt("mode");
        }
        configureView();
        initLoginHandler();
        Object lastNonConfigurationInstance = getLastNonConfigurationInstance();
        if (lastNonConfigurationInstance != null) {
            this.loginTask = (g) lastNonConfigurationInstance;
            this.loginTask.attach(this, this.loginHandler);
        }
    }

    private void configureView() {
        if (this.mode == 1) {
            ((EditText) findViewById(12292)).setVisibility(4);
        }
        ((Button) findViewById(LoginView.LOGIN_BUTTON_ID)).setOnClickListener(this);
    }

    private void initLoginHandler() {
        this.loginHandler = new Handler() {
            public void handleMessage(Message message) {
                if (message.getData().getBoolean("success")) {
                    LoginActivity.this.finish();
                    if (h.listener != null) {
                        h.listener.onSuccess();
                        return;
                    }
                    return;
                }
                Toast.makeText(LoginActivity.this, "Login failed. Check your credentials.", 2000).show();
            }
        };
    }

    private void performAuthentication() {
        Object obj;
        Object obj2 = 1;
        CharSequence obj3 = ((EditText) findViewById(12291)).getText().toString();
        CharSequence obj4 = ((EditText) findViewById(12292)).getText().toString();
        Map hashMap = new HashMap();
        if (this.mode == 1) {
            obj = !TextUtils.isEmpty(obj3) ? 1 : null;
            hashMap.put("email", obj3);
            hashMap.put("authcode", md5(this.secret + obj3));
        } else if (this.mode == 2) {
            if (TextUtils.isEmpty(obj3) || TextUtils.isEmpty(obj4)) {
                obj2 = null;
            }
            hashMap.put("email", obj3);
            hashMap.put("password", obj4);
            obj = obj2;
        } else {
            obj = null;
        }
        if (obj != null) {
            this.loginTask = new g(this, this.loginHandler, this.url, this.mode, hashMap);
            a.execute(this.loginTask);
            return;
        }
        Toast.makeText(this, k.get(k.LOGIN_MISSING_CREDENTIALS_TOAST_ID), 1000).show();
    }

    public String md5(String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(str.getBytes());
            byte[] digest = instance.digest();
            StringBuilder stringBuilder = new StringBuilder();
            for (byte b : digest) {
                String toHexString = Integer.toHexString(b & 255);
                while (toHexString.length() < 2) {
                    toHexString = "0" + toHexString;
                }
                stringBuilder.append(toHexString);
            }
            return stringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case LoginView.LOGIN_BUTTON_ID /*12293*/:
                performAuthentication();
                return;
            default:
                return;
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            if (h.listener != null) {
                h.listener.onBack();
            } else {
                Intent intent = new Intent(this, h.mainActivity);
                intent.setFlags(67108864);
                intent.putExtra("net.hockeyapp.android.EXIT", true);
                startActivity(intent);
                return true;
            }
        }
        return super.onKeyDown(i, keyEvent);
    }

    public Object onRetainNonConfigurationInstance() {
        if (this.loginTask != null) {
            this.loginTask.detach();
        }
        return this.loginTask;
    }
}
