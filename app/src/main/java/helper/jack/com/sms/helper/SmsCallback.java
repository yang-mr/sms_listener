package helper.jack.com.sms.helper;

import helper.jack.com.sms.helper.domain.SmsBean;

/**
 * Created by jack
 * On 18-5-10:下午2:30
 * Desc:
 */
public interface SmsCallback {
    void call(SmsBean bean);
}
