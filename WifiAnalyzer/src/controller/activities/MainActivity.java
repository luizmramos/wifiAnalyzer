package controller.activities;

import view.WifiNotEnabledDialog;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.appspot.wifianalyzerapp.R;

import controller.actionbar.ActionBarController;

public class MainActivity extends SherlockFragmentActivity {

	private ActionBarController  actionBarController;
	private float touchX;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity__main);
		Log.d("MyDebug","Creating MainActivity!");
		
	}
	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		if(hasFocus){
			WifiManager wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
			if(wifi.isWifiEnabled()==false){
				new WifiNotEnabledDialog().show(getSupportFragmentManager(),getString(R.string.wifiNotEnabledTag));
			}
		}
		super.onWindowFocusChanged(hasFocus);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getSupportMenuInflater().inflate(R.menu.signal_streng, menu);
		actionBarController = new ActionBarController(getSupportActionBar(),
				this);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == (MotionEvent.ACTION_DOWN)) {
			touchX=event.getX();
		}
		if (event.getAction() == (MotionEvent.ACTION_UP)) {
			if(event.getX()>touchX+10){
				actionBarController.goLeft();
			}
			else if(event.getX()<touchX-10){
				actionBarController.goRight();
			}
		}
		if (event.getAction() == (MotionEvent.ACTION_MOVE)) {
			
		}

		return false;
	}

}
