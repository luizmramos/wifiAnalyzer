package controller.threads;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.appspot.wifianalyzerapp.R;


public class WifiDropdownUpdater implements Runnable{

	
	
	WifiManager wifi;
	Activity activity;
	Spinner  wifiDropdown;
	RadarUpdater radarUpdater;

	public WifiDropdownUpdater(Activity activity,RadarUpdater radarUpdater){
		this.activity = activity;
		wifi=(WifiManager)activity.getSystemService(Context.WIFI_SERVICE);
		wifiDropdown = (Spinner)activity.findViewById(R.id.wifiDropDown);	
		this.radarUpdater=radarUpdater;
	}
	
	@Override
	public void run() {
		Log.d("radar", "foi");
		if(wifi.isWifiEnabled()){
			wifi.startScan();
			List<ScanResult> sr = wifi.getScanResults();
			List<String> list = new ArrayList<String>();
			String selectedString = (String)wifiDropdown.getSelectedItem();
			int position=0;
			if(selectedString!=null){
				Log.d("radar",selectedString);
			}
			for(ScanResult r : sr){
				if(selectedString!=null && r.SSID.equals(selectedString)){
					position=list.size();
				}
				list.add(r.SSID);
				
			}
			ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(activity,
				android.R.layout.simple_spinner_item, list);
			
			dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			wifiDropdown.setAdapter(dataAdapter);
			if(list.size()>0){
				wifiDropdown.setSelection(position);
				radarUpdater.setWifiSSID((String)wifiDropdown.getItemAtPosition(position));
			}
		}
		
	}
	
	
}
