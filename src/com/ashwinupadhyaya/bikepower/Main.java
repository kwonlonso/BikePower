package com.ashwinupadhyaya.bikepower;

import java.io.IOException;

import android.location.GpsStatus.Listener;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class Main extends Activity implements SensorEventListener, LocationListener, Listener {

	SharedPreferences mPrefs;
	TextView tvPower, tvSpeed, tvDistance, tvTimeHrs, tvTimeMins, tvCadence, tvCalories;
	Bike mBike;
	SensorManager mSenMan;
	LocationManager mLocMan;
	BikeSensors mBikeSensors;
	BikeLocation mBikeLocation;
	float accX, accY, accZ;
	float locAcc, locBrg, locSpd, locAlt, locLat, locLon;
	long locTim, accTim;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initVars();
        initSensors();
        initLocation();
        register_listeners();
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


	private void initSensors() {
		mSenMan=(SensorManager) getSystemService(SENSOR_SERVICE);
		mBikeSensors = new BikeSensors(mSenMan.getSensorList(Sensor.TYPE_ALL));
	}


	private void initLocation() {
		mLocMan=(LocationManager) getSystemService(Context.LOCATION_SERVICE);
		mBikeLocation = new BikeLocation(mLocMan.getAllProviders());
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
		mBike = new Bike((float)10.0,
				Float.parseFloat(mPrefs.getString("prefsWindSpeed", "0.0f")),
				Float.parseFloat(mPrefs.getString("prefsAltitude", "0.0f")),
				Float.parseFloat(mPrefs.getString("prefsAirTemp", "0.0f")),
				Float.parseFloat(mPrefs.getString("prefsSlope", "0.0f")),
				Float.parseFloat(mPrefs.getString("prefsBikeWeight", "0.0f")),
				Float.parseFloat(mPrefs.getString("prefsUserHeight", "0.0f")));
		tvPower.setText("" + mBike.getPower());
		tvSpeed.setText("" + 36.0);
	}
    
	private void register_listeners() {		
		for (int i=0;i<mBikeSensors.getNum();i++) {
			mSenMan.registerListener(this, mSenMan.getDefaultSensor(mBikeSensors.getType(i)), mBikeSensors.getRate(i));
		}
		for (int i=0;i<mBikeLocation.getNum();i++) {
			if (mBikeLocation.getActive(i))
				mLocMan.requestLocationUpdates(mBikeLocation.getName(i), mBikeLocation.getMinTime(i), mBikeLocation.getMinDist(i), this);
		}
		mLocMan.addGpsStatusListener(this);
	}


	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onSensorChanged(SensorEvent event) {
		if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
			accTim = System.currentTimeMillis();
			accX = event.values[0];
			accY = event.values[1];
			accZ = event.values[2];
		}
	}


	@Override
	public void onLocationChanged(Location loc) {
		locTim = System.currentTimeMillis();
		locAcc = loc.getAccuracy();
		locAlt = (float)loc.getAltitude();
		locBrg = loc.getBearing();
		locSpd = loc.getSpeed();
		locLat = (float)loc.getLatitude();
		locLon = (float)loc.getLongitude();
	}


	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onGpsStatusChanged(int event) {
		// TODO Auto-generated method stub
		
	}
}
