package gq.catz.spamnick;

import android.annotation.TargetApi;
import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.telephony.SmsMessage;

import androidx.core.app.NotificationManagerCompat;

public class SmsReceiver extends BroadcastReceiver {
	private static final String TAG = SmsReceiver.class.getSimpleName();
	private static final String pdu_type = "pdus";
	@TargetApi(Build.VERSION_CODES.M)
	@Override
	public void onReceive(Context context, Intent intent) {
		Object[] smsExtra = (Object[]) intent.getExtras().get("pdus");
		String body = "";

		for (int i = 0; i < smsExtra.length; ++i) {
			SmsMessage sms = SmsMessage.createFromPdu((byte[]) smsExtra[i]);
			body += sms.getMessageBody();
		}

		Notification notification = new Notification.Builder(context)
				.setContentText(body)
				.setContentTitle("New Message")
				.setSmallIcon(android.R.drawable.stat_sys_warning)
				.setStyle(new Notification.BigTextStyle().bigText(body))
				.build();
		NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
		notificationManagerCompat.notify(1, notification);
	}
}
