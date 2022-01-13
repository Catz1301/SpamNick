package gq.catz.spamnick;

import android.graphics.Color;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.klinker.android.send_message.Message;
import com.klinker.android.send_message.Settings;
import com.klinker.android.send_message.Transaction;

public class SpammActivity extends AppCompatActivity {
	public boolean threadsRunning = false;
	private final String nicksNumber = "5176671277";
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
		EditText msg = (EditText) findViewById(R.id.editTextSpamMsg), waitTime = (EditText) findViewById(R.id.editTextWaitTime);
		Button start = (Button) findViewById(R.id.startSpam), stop = (Button) findViewById(R.id.stopSpam);
		ConstraintLayout layout = (ConstraintLayout) findViewById(R.id.layout_main);
//		smsSentTV = findViewById(R.id.SMSMsgsSent);
//		smsDeliveredTV = findViewById(R.id.SMSMsgsDelivered);
//		statsSetup = true;
		settings = new Settings();
		transaction = new Transaction(this, settings);
		settings.setUseSystemSending(true);
		message = new Message("Placeholder message", nicksNumber);

		start.setOnClickListener(v -> {
			threadsRunning = true;
			layout.setBackgroundColor(Color.GREEN);
			String msgString = msg.getText().toString();
			String waitTimeMs = waitTime.getText().toString();
			StartTheSpam(msgString, Integer.valueOf(waitTimeMs));
		});

		stop.setOnClickListener(v -> {
			layout.setBackgroundColor(Color.RED);
			threadsRunning = false;
		});
	}

	private void StartTheSpam(String msgString, int waitTimeMs) {
		final String msgStr = msgString;
		final int waitTimeMilli = waitTimeMs;

		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				while (threadsRunning) {

					transaction.sendNewMessage(message, Transaction.NO_THREAD_ID);
					try {
						Thread.sleep(waitTimeMilli);
					} catch (InterruptedException e) {
						Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
						e.printStackTrace();
					}
				}
			}
			void sendSMS(String phoneNum, String msg) {
				try {
					SmsManager smsManager = SmsManager.getDefault();
					smsManager.sendTextMessage(phoneNum, null, msg, null, null);
//					putInSmsDb(msg, phoneNum);
				} catch (Exception ex) {
					Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
					ex.printStackTrace();
				}
			}
		});
		t.start();
	}
}