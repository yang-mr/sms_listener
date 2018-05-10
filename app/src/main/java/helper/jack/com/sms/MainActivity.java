package helper.jack.com.sms;

import android.database.ContentObserver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import helper.jack.com.sms.helper.SmsCallback;
import helper.jack.com.sms.helper.SmsHelper;
import helper.jack.com.sms.helper.domain.SmsBean;

public class MainActivity extends AppCompatActivity {
    private ContentObserver mSmsObserver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSmsObserver = SmsHelper.registerContentObserver(this, new SmsCallback() {
            @Override
            public void call(SmsBean bean) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SmsHelper.unRegisterContentObserver(this, mSmsObserver);
    }
}
