package helper.jack.com.sms.helper;

import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;

/**
 * Created by jack
 * On 18-5-10:下午4:20
 * Desc:
 */
public class SmsHelper {
    public static ContentObserver registerContentObserver(Context context, SmsCallback callback) {
        ContentObserver observer = new SmsContentObserver(context, callback);
        context.getContentResolver().registerContentObserver(Uri.parse("content://sms"), true, observer);
        return observer;
    }

    public static void unRegisterContentObserver(Context context, ContentObserver observer) {
        if (context == null || observer == null) {
            return;
        }
        context.getContentResolver().unregisterContentObserver(observer);
    }
}
