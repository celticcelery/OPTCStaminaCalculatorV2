package com.domain.celticcelery.optcstaminacalculator;

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
	private Button calculateBtn, desiredBtn;
	private EditText currentStaminaText, desiredStaminaText;
	private TextView currentTimeEditText, expectedTimeEditText;
	private String emptyDisplay = "";
	private SimpleDateFormat df = new SimpleDateFormat("MMMM dd, yyyy \t HH:mm:ss");
	private String error = "Please enter inputs for Current Stamina and Desired Stamina";
	private int neededStam, rechargeTime = 3;




	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_stamina_to_time);

		currentTimeEditText = (TextView) findViewById(R.id.currentTimeEditText);
		expectedTimeEditText = (TextView) findViewById(R.id.expectedTimeEditText);
		currentStaminaText = (EditText) findViewById(R.id.currentStaminaText);
		desiredStaminaText = (EditText) findViewById(R.id.desiredStaminaText);

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
	}

	public void onClickCalculate(View view) {

		try {
			neededStam = getNeededStamina();
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

	public void onClickReset(View view) {
		clearScreen();
	}
}
