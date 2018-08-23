package com.domain.celticcelery.optcstaminacalculator;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.SystemClock;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class StaminaToTime extends AppCompatActivity {

	private Calendar time;
	private AlarmManager alarmMgr;
	private PendingIntent alarmIntent;
	private Button calculateBtn, desiredBtn, alarmBtn;
	private EditText currentStaminaText, desiredStaminaText;
	private TextView currentTimeEditText, expectedTimeEditText;
	private String emptyDisplay = "";
	private SimpleDateFormat df = new SimpleDateFormat("MMMM dd, yyyy \t HH:mm:ss");
	private String error = "Please enter inputs for Current Stamina and Desired Stamina";
	private int neededStam, currentNeededStam, rechargeTime = 3;




	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_stamina_to_time);

		currentTimeEditText = (TextView) findViewById(R.id.currentTimeEditText);
		expectedTimeEditText = (TextView) findViewById(R.id.expectedTimeEditText);
		currentStaminaText = (EditText) findViewById(R.id.currentStaminaText);
		desiredStaminaText = (EditText) findViewById(R.id.desiredStaminaText);
		alarmBtn = (Button) findViewById(R.id.alarmBtn);

	}

	public int getNeededStamina() {
		int currentStam = Integer.parseInt(currentStaminaText.getText().toString());
		int desiredStam = Integer.parseInt(desiredStaminaText.getText().toString());
		return ((desiredStam - currentStam) * rechargeTime);


	}


	public void clearScreen() {
		currentStaminaText.setText(emptyDisplay);
		desiredStaminaText.setText(emptyDisplay);
		currentTimeEditText.setText(emptyDisplay);
		expectedTimeEditText.setText(emptyDisplay);
		neededStam = 0;
		time = null;
		alarmBtn.setEnabled(true);
	}

	public void onClickCalculate(View view) {

		try {
			neededStam = getNeededStamina();
			currentNeededStam = neededStam;
		}
		catch(IllegalStateException | NumberFormatException nfex){
			Toast.makeText(this,error,Toast.LENGTH_SHORT).show();
		}

		if (neededStam < 0 ) {
			Toast.makeText(this,"Invalid input",Toast.LENGTH_SHORT).show();
			clearScreen();
		}
		else if(neededStam > 0){
			time = Calendar.getInstance();
			String currentTime = df.format(time.getTime());
			currentTimeEditText.setText(currentTime);

			time.add(Calendar.MINUTE, neededStam);
			String desiredTime = df.format(time.getTime());
			expectedTimeEditText.setText(desiredTime);
		}

	}

	@RequiresApi(api = Build.VERSION_CODES.M)
	public void onClickAlarm(View view){
		try {
			Toast.makeText(this, "Alarm set for " + df.format(time.getTime()), Toast.LENGTH_SHORT).show();

				setAlarm(neededStam * 60 * 1000);
				alarmBtn.setEnabled(false);



		}
		catch(IllegalStateException | NullPointerException | NumberFormatException nfex) {
			Toast.makeText(this, "No alarm. Please press calculate for a desired time first", Toast.LENGTH_LONG).show();
		}

	}


	@RequiresApi(api = Build.VERSION_CODES.M)
	private void setAlarm(long timeInMillis) {
		alarmMgr = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
		Intent intent = new Intent(this, AlarmReceiver.class);

		alarmIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
		alarmMgr.setAndAllowWhileIdle(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime() + timeInMillis, alarmIntent);
	}

	public void onClickReset(View view) {
		clearScreen();
		if(alarmMgr != null) {
			alarmMgr.cancel(alarmIntent);
		}
	}


}
