package controller.threads;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;

import utils.ColorPool;
import view.WifiStrengthXYSeries;
import android.app.Activity;
import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;

import com.androidplot.xy.BoundaryMode;
import com.androidplot.xy.XYPlot;
import com.androidplot.xy.XYStepMode;
import com.appspot.wifianalyzerapp.R;

public class GraphUpdater extends Thread{
	HashMap<String,WifiStrengthXYSeries> hash;
	XYPlot plot;
	WifiManager wifi;
	Activity activity;
	public GraphUpdater(Activity activity){
		this.activity = activity;
		wifi=(WifiManager)activity.getSystemService(Context.WIFI_SERVICE);
		plot=(XYPlot)activity.findViewById(R.id.graph);
		hash = new HashMap<String,WifiStrengthXYSeries>();
		
		plot.setTitle("");
		plot.setTicksPerRangeLabel(2);
        plot.setDomainStep(XYStepMode.INCREMENT_BY_VAL, 1);
        plot.setDomainBoundaries(-1, 15, BoundaryMode.FIXED);
        plot.setDomainLabel("Channel");
        DecimalFormat df = new DecimalFormat("#0");
        df.setParseIntegerOnly(true);
        plot.setDomainValueFormat(df);
        plot.setRangeBoundaries(-100, -20, BoundaryMode.FIXED);
        plot.setRangeLabel("Strength");
        plot.getLegendWidget().setVisible(false);
        
		
	}
	
	
	
	@Override
	public void run() {
		
	
		try {
			while(true){
				Log.d("graph", "foi");
				if(wifi.isWifiEnabled()){
					WifiInfo wifiINFO  = wifi.getConnectionInfo();
					Log.d("MyDebug",""+wifiINFO.getIpAddress());
					wifi.startScan();
					List<ScanResult> sr = wifi.getScanResults();
					
					for(WifiStrengthXYSeries s : hash.values()){
						plot.removeSeries(s);
					}
					plot.removeMarkers();
								
					for(ScanResult r : sr){
						WifiStrengthXYSeries series = hash.get(r.SSID);
						if(series == null){
							series = new WifiStrengthXYSeries(
									r.SSID, 
									(r.frequency-2407)/5, 
									r.level,
									ColorPool.getColor(hash.size()),
									ColorPool.getTransparentColor(hash.size()));
							hash.put(r.SSID, series);
							
						}
						else{
							series.setChannel((r.frequency-2407)/5);
							series.setStrength(r.level);
							series.update();
						}
						
						plot.addSeries(series, series.getFormatter());
						plot.addMarker(series.getMarker(activity.getResources().getConfiguration().orientation ));
					}
					
					plot.redraw();
				}
				Thread.sleep(1000);
			}			
			
		} catch (InterruptedException e) {
			
			for(WifiStrengthXYSeries s : hash.values()){
				plot.removeSeries(s);
			}
				
		}
		
		
	}
}
