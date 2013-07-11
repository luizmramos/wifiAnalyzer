package controller.threads;

import java.util.List;

import view.RadarView;
import android.app.Activity;
import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.util.Log;
import android.widget.LinearLayout;

import com.appspot.wifianalyzerapp.R;

public class RadarUpdater extends Thread{
	
	WifiManager wifi;
	Activity activity;
	String wifiSSID;
	RadarView radarView;
	int strength;
	public void setWifiSSID(String wifiSSID) {
		this.wifiSSID = wifiSSID;
	}

	public RadarUpdater(Activity activity, String wifiSSID){
		this.activity = activity;
		wifi=(WifiManager)activity.getSystemService(Context.WIFI_SERVICE);
		this.wifiSSID = wifiSSID;
		LinearLayout linearLayout = (LinearLayout)activity.findViewById(R.id.radarLayout);
		radarView = new RadarView(activity);
		linearLayout.addView(radarView);
	}
	
	@Override
	public void run() {
		try {
			while(true){
				Log.d("radar", "foi");
				if(wifi.isWifiEnabled()){
					wifi.startScan();
					List<ScanResult> sr = wifi.getScanResults();
					for(ScanResult r : sr){
						if(r.SSID.equals(wifiSSID)){
							radarView.setStrength(r.level);
							strength = r.level;
							break;
						}
					}
				}
				if(activity!=null){
						activity.runOnUiThread(new Runnable() {
						@Override
						public void run() {
							radarView.update();
						}
					});
				}
				sleep(100);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public float getNormalizedStrength(){
		return (strength+100)/60.0f;
	}
	
}
