package gq.catz.spamnick;

import android.content.Context;
import android.content.Intent;

import com.klinker.android.send_message.StatusUpdatedReceiver;

public abstract class MyDeliveredReceiver extends StatusUpdatedReceiver {

	@Override
	public void updateInInternalDatabase(Context context, Intent intent, int resultCode) {
	}
}
/*

		Log.v("delivery_receiver", "marking message as delivered");

		final Uri uri = getUri(intent);

		try {
			switch (resultCode) {
				case Activity.RESULT_OK:
					// notify user that message was delivered
					Intent delivered = new Intent(Transaction.NOTIFY_OF_DELIVERY);
					delivered.putExtra("result", true);
					delivered.putExtra("message_uri", uri == null ? "" : uri.toString());
					BroadcastUtils.sendExplicitBroadcast(
							context, delivered, Transaction.NOTIFY_OF_DELIVERY);

					if (uri != null) {
						ContentValues values = new ContentValues();
						values.put("status", "0");
						values.put("date_sent", Calendar.getInstance().getTimeInMillis());
						values.put("read", true);
						context.getContentResolver().update(uri, values, null, null);
					} else {
						Cursor query = context.getContentResolver().query(Uri.parse("content://sms/sent"), null, null, null, "date desc");

						// mark message as delivered in database
						if (query.moveToFirst()) {
							String id = query.getString(query.getColumnIndex("_id"));
							ContentValues values = new ContentValues();
							values.put("status", "0");
							values.put("date_sent", Calendar.getInstance().getTimeInMillis());
							values.put("read", true);
							context.getContentResolver().update(Uri.parse("content://sms/sent"), values, "_id=" + id, null);
						}

						query.close();
					}
					StatSingleton.SmsMessagesDelivered++;
					if (SpammActivity.isStatsReady()) {
						SpammActivity.smsDeliveredTV.setText(new StringBuilder(SpammActivity.smsDeliveredPrefix).append(StatSingleton.SmsMessagesDelivered));
					}
					break;
				case Activity.RESULT_CANCELED:
					// notify user that message failed to be delivered
					Intent notDelivered = new Intent(Transaction.NOTIFY_OF_DELIVERY);
					notDelivered.putExtra("result", false);
					notDelivered.putExtra("message_uri", uri == null ? "" : uri.toString());
					BroadcastUtils.sendExplicitBroadcast(
							context, notDelivered, Transaction.NOTIFY_OF_DELIVERY);

					if (uri != null) {
						ContentValues values = new ContentValues();
						values.put("status", "64");
						values.put("date_sent", Calendar.getInstance().getTimeInMillis());
						values.put("read", true);
						values.put("error_code", resultCode);
						context.getContentResolver().update(uri, values, null, null);
					} else {
						Cursor query2 = context.getContentResolver().query(Uri.parse("content://sms/sent"), null, null, null, "date desc");

						// mark failed in database
						if (query2.moveToFirst()) {
							String id = query2.getString(query2.getColumnIndex("_id"));
							ContentValues values = new ContentValues();
							values.put("status", "64");
							values.put("read", true);
							values.put("error_code", resultCode);
							context.getContentResolver().update(Uri.parse("content://sms/sent"), values, "_id=" + id, null);
						}

						query2.close();
					}
					break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		BroadcastUtils.sendExplicitBroadcast(context, new Intent(), Transaction.REFRESH);
	}

	private Uri getUri(Intent intent) {
		Uri uri;

		try {
			uri = Uri.parse(intent.getStringExtra("message_uri"));

			if (uri.equals("")) {
				return null;
			}
		} catch (Exception e) {
			return null;
		}

		return uri;
	}
}*/
