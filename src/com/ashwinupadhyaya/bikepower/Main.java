package com.ashwinupadhyaya.bikepower;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.MenuInflater;
import android.view.MenuItem;

public class Main extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
    
}
