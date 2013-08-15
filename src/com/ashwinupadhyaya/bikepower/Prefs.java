package com.ashwinupadhyaya.bikepower;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;

public class Prefs extends PreferenceActivity implements OnSharedPreferenceChangeListener{

	EditTextPreference mPrefsUserName, mPrefsUserHeight, mPrefsUserWeight, mPrefsAirTemp,
	mPrefsAltitude, mPrefsWindSpeed, mPrefsSlope, mPrefsBikeWeight;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.user_prefs);
		mPrefsUserName = (EditTextPreference) getPreferenceScreen().findPreference("prefsUserName");
		mPrefsUserHeight = (EditTextPreference) getPreferenceScreen().findPreference("prefsUserHeight");
		mPrefsUserWeight = (EditTextPreference) getPreferenceScreen().findPreference("prefsUserWeight");
		mPrefsAirTemp = (EditTextPreference) getPreferenceScreen().findPreference("prefsAirTemp");
		mPrefsAltitude = (EditTextPreference) getPreferenceScreen().findPreference("prefsAltitude");
		mPrefsWindSpeed = (EditTextPreference) getPreferenceScreen().findPreference("prefsWindSpeed");
		mPrefsSlope = (EditTextPreference) getPreferenceScreen().findPreference("prefsSlope");
		mPrefsBikeWeight = (EditTextPreference) getPreferenceScreen().findPreference("prefsBikeWeight");
		
		getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
	}

	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
            String key) {

		if (key.equals("prefsUserName")) {
			mPrefsUserName.setSummary(sharedPreferences.getString(key, "-"));
        }
		if (key.equals("prefsUserHeight")) {
			mPrefsUserHeight.setSummary(sharedPreferences.getString(key, "-"));
        }
		if (key.equals("prefsUserWeight")) {
			mPrefsUserWeight.setSummary(sharedPreferences.getString(key, "-"));
        }
		if (key.equals("prefsAirTemp")) {
			mPrefsAirTemp.setSummary(sharedPreferences.getString(key, "-"));
        }
		if (key.equals("prefsAltitude")) {
			mPrefsAltitude.setSummary(sharedPreferences.getString(key, "-"));
        }
		if (key.equals("prefsWindSpeed")) {
			mPrefsWindSpeed.setSummary(sharedPreferences.getString(key, "-"));
        }
		if (key.equals("prefsSlope")) {
			mPrefsSlope.setSummary(sharedPreferences.getString(key, "-"));
        }
		if (key.equals("prefsBikeWeight")) {
			mPrefsBikeWeight.setSummary(sharedPreferences.getString(key, "-"));
        }
    }
}
