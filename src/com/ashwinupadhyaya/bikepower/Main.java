package com.ashwinupadhyaya.bikepower;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class Main extends Activity {

	SharedPreferences mPrefs;
	TextView tvPower, tvSpeed, tvDistance, tvTimeHrs, tvTimeMins, tvCadence, tvCalories;
	Bike bBike;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initVars();
    }


	private void initVars() {
		tvPower 	= (TextView)findViewById(R.id.tvMainPowerRes);
		tvSpeed 	= (TextView)findViewById(R.id.tvMainSpeedRes);
		tvDistance 	= (TextView)findViewById(R.id.tvMainDistanceRes);
		tvTimeHrs 	= (TextView)findViewById(R.id.tvMainTimeHrsRes);
		tvTimeMins 	= (TextView)findViewById(R.id.tvMainTimeMinsRes);
		tvCadence 	= (TextView)findViewById(R.id.tvMainCadenceRes);
		tvCalories 	= (TextView)findViewById(R.id.tvMainCaloriesRes);
	}


	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		super.onCreateOptionsMenu(menu);
		MenuInflater mainMenu = getMenuInflater();
		mainMenu.inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_settings:
			Intent ip = new Intent("com.ashwinupadhyaya.bikepower.PREFS");
			startActivity(ip);
			break;
		case R.id.action_about:
			Intent ia = new Intent("com.ashwinupadhyaya.bikepower.ABOUTBIKEPOWER");
			startActivity(ia);
			break;
		case R.id.action_exit:
			break;
		default:
			break;
		}
		return false;
	}


	@Override
	protected void onResume() {
		super.onResume();
		mPrefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		tvCadence.setText(mPrefs.getString("prefsCadence", "0"));
		// Velocity of 10 m/s is temporary
		bBike = new Bike((float)10.0,
				Float.parseFloat(mPrefs.getString("prefsWindSpeed", "0.0f")),
				Float.parseFloat(mPrefs.getString("prefsAltitude", "0.0f")),
				Float.parseFloat(mPrefs.getString("prefsAirTemp", "0.0f")),
				Float.parseFloat(mPrefs.getString("prefsSlope", "0.0f")),
				Float.parseFloat(mPrefs.getString("prefsBikeWeight", "0.0f")),
				Float.parseFloat(mPrefs.getString("prefsUserHeight", "0.0f")));
		tvPower.setText("" + bBike.getPower());
		tvSpeed.setText("" + 36.0);
	}
    
	
}
