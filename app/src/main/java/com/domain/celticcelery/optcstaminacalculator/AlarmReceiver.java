package com.domain.celticcelery.optcstaminacalculator;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.provider.Settings;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

public class AlarmReceiver extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		MediaPlayer mediaPlayer = MediaPlayer.create(context,
				                  Settings.System.DEFAULT_ALARM_ALERT_URI);
		mediaPlayer.start();
		try {
			TimeUnit.SECONDS.sleep(5);
			mediaPlayer.stop();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Toast.makeText(context, "Desired stamina has been refilled", Toast.LENGTH_LONG).show();


	}
}
