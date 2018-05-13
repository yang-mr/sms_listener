package helper.jack.com.sms.helper;

import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;


/**
 * Created by jack
 * On 18-5-10:下午4:20
 * Desc:
 */
public class SmsHelper {
    public static ContentObserver registerContentObserver(Context context, Uri uri, Handler handler, boolean notifyForDescendants) {
        ContentObserver observer = new SmsContentObserver(context, handler, uri);
        context.getContentResolver().registerContentObserver(uri, notifyForDescendants, observer);
        return observer;
    }

    public static ContentObserver registerContentObserver(Context context, Handler handler) {
        return registerContentObserver(context, Uri.parse("content://sms"), handler, true);
    }

    public static void unRegisterContentObserver(Context context, ContentObserver observer) {
        if (context == null || observer == null) {
            return;
        }
        context.getContentResolver().unregisterContentObserver(observer);
    }
}
