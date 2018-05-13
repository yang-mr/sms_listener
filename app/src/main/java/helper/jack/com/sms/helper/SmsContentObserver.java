package helper.jack.com.sms.helper;

import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import helper.jack.com.sms.helper.domain.SmsBean;

/**
 * Created by jack
 * On 18-5-10:下午2:23
 * Desc:
 */
public class SmsContentObserver extends ContentObserver {
    private Context mContext;
    private Handler mHandler;
    private Uri mListenerUri;

    public SmsContentObserver(Context context, Handler handler, Uri uri) {
        super(handler);
        mContext = context.getApplicationContext();
        mHandler = handler;
        mListenerUri = uri;
    }

    @Override
    public void onChange(boolean selfChange, Uri uri) {
        Cursor c = mContext.getContentResolver().query(mListenerUri, null, null, null, null);
        if (c != null) {
            //只取最新的一条短信
            if (c.moveToNext()) {
                String number = c.getString(c.getColumnIndex("address"));//手机号
                String body = c.getString(c.getColumnIndex("body"));
                Log.d("jack", "sms listener[ number:" + number + "---body:" + body);
                if (mHandler != null) {
                    // callback
                    SmsBean bean = new SmsBean();
                    bean.setAddress(number);
                    bean.setBody(body);
                    Message message = new Message();
                    message.obj = bean;
                    mHandler.sendMessage(message);
                }
            }
            c.close();
        }
    }
}
