package helper.jack.com.sms;

import android.database.ContentObserver;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import helper.jack.com.sms.helper.SmsHelper;

public class MainActivity extends AppCompatActivity {
    private ContentObserver mSmsObserver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSmsObserver = SmsHelper.registerContentObserver(this, new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                return false;
            }
        }));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SmsHelper.unRegisterContentObserver(this, mSmsObserver);
    }
}
