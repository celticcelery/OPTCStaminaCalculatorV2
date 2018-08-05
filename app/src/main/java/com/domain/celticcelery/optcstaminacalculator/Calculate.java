package com.domain.celticcelery.optcstaminacalculator;


import android.view.View;
import android.widget.Toast;

import java.util.Calendar;

//public class Calculate {
//	public int getNeededStamina() {
//
//		int currentStam = Integer.parseInt(currentStaminaText.getText().toString());
//		int desiredStam = Integer.parseInt(desiredStaminaText.getText().toString());
//		return ((desiredStam - currentStam) * 5);
//
//
//	}
//
//	public void onClickCalculate(View view) {
//
//		try {
//			neededStam = getNeededStamina();
//		}
//		catch(IllegalStateException | NumberFormatException nfex){
//			Toast.makeText(this,error,Toast.LENGTH_SHORT).show();
//		}
//
//		if (neededStam < 0 ) {
//			Toast.makeText(this,"Invalid input",Toast.LENGTH_SHORT).show();
//			clearScreen();
//		}
//		else if(neededStam > 0){
//			time = Calendar.getInstance();
//			String currentTime = df.format(time.getTime());
//			currentTimeEditText.setText(currentTime);
//
//			time.add(Calendar.MINUTE, neededStam);
//			String desiredTime = df.format(time.getTime());
//			expectedTimeEditText.setText(desiredTime);
//		}
//
////	}
//
//}
