package helper.jack.com.sms.helper;

import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import helper.jack.com.sms.helper.domain.SmsBean;

/**
 * Created by jack
 * On 18-5-10:下午2:23
 * Desc:
 */
public class SmsContentObserver extends ContentObserver {
    private Context mContext;
    private SmsCallback mSmsCallback;

    public SmsContentObserver(Context context) {
        this(context, null);
    }

   /* public SmsContentObserver(Context context, Handler handler) {
        super(handler);
        mContext = context.getApplicationContext();
    }*/

    public SmsContentObserver(Context context, SmsCallback callback) {
        super(null);
        mContext = context.getApplicationContext();
        mSmsCallback = callback;
    }

    @Override
    public void onChange(boolean selfChange, Uri uri) {
        Cursor c = mContext.getContentResolver().query(uri, null,
                null, null, "date desc");
        if (c != null) {
            //只取最新的一条短信
            if (c.moveToNext()) {
                String number = c.getString(c.getColumnIndex("address"));//手机号
                String body = c.getString(c.getColumnIndex("body"));
                Log.d("jack:", "number:" + number + "---body:" + body);
                if (mSmsCallback != null) {
                    // callback
                    SmsBean bean = new SmsBean();
                    bean.setAddress(number);
                    bean.setBody(body);
                    mSmsCallback.call(bean);
                }
            }
            c.close();
        }
    }
}
