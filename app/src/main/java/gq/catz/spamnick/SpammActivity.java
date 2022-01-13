package gq.catz.spamnick;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.klinker.android.send_message.Message;
import com.klinker.android.send_message.Settings;
import com.klinker.android.send_message.Transaction;

@SuppressWarnings("ALL")
public class SpammActivity extends AppCompatActivity {
	public boolean threadsRunning = false;
	//	final static String smsSentPrefix = "SMS Messages Sent: ", smsDeliveredPrefix = "SMS Messages Delivered: ";
//	public static TextView smsSentTV, smsDeliveredTV;
//	private static boolean statsSetup = false;
	Settings settings;
	Transaction transaction;
	Message message;
//	public static boolean isStatsReady() {
//		return statsSetup;
//	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_spamm);
		EditText	msg = (EditText) findViewById(R.id.editTextSpamMsg),
					waitTime = (EditText) findViewById(R.id.editTextWaitTime),
					phoneNo = (EditText) findViewById(R.id.phoneNumToSpamET);
		Button start = (Button) findViewById(R.id.startSpam), stop = (Button) findViewById(R.id.stopSpam);
		ConstraintLayout layout = (ConstraintLayout) findViewById(R.id.layout_main);
//		smsSentTV = findViewById(R.id.SMSMsgsSent);
//		smsDeliveredTV = findViewById(R.id.SMSMsgsDelivered);
//		statsSetup = true;
		settings = new Settings();
		transaction = new Transaction(this, settings);
		settings.setUseSystemSending(true);
		String nicksNumber = "5176671277";
		message = new Message("Placeholder message", nicksNumber);

		start.setOnClickListener(v -> {
			threadsRunning = true;
			message.setText(msg.getText().toString());
			layout.setBackgroundColor(Color.GREEN);
			String waitTimeMs = waitTime.getText().toString();
			String phoneNum = phoneNo.getText().toString();
			message.setAddress(phoneNum);
			StartTheSpam(Integer.parseInt(waitTimeMs));
		});

		stop.setOnClickListener(v -> {
			layout.setBackgroundColor(Color.RED);
			threadsRunning = false;
		});
	}

	@SuppressWarnings("BusyWait")
	private void StartTheSpam(int waitTimeMs) {
		final int waitTimeMilli = waitTimeMs;

		/*void sendSMS(String phoneNum, String msg) {
			try {
				SmsManager smsManager = SmsManager.getDefault();
				smsManager.sendTextMessage(phoneNum, null, msg, null, null);
//					putInSmsDb(msg, phoneNum);
			} catch (Exception ex) {
				Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
				ex.printStackTrace();
			}
		}*/
		Thread t = new Thread(() -> {
			while (threadsRunning) {

				transaction.sendNewMessage(message, Transaction.NO_THREAD_ID);
				try {
					Thread.sleep(waitTimeMilli);
				} catch (InterruptedException e) {
					Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
					e.printStackTrace();
				}
			}
		});
		t.start();
	}
}