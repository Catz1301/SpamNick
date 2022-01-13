package gq.catz.spamnick;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button gotoSpammActivity = findViewById(R.id.gotoSpammActivityBtn);

		gotoSpammActivity.setOnClickListener(v -> {
			Intent spammActivityIntent = new Intent(MainActivity.this, SpammActivity.class);
			startActivity(spammActivityIntent);
		});
	}
}