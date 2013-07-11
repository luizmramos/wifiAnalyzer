package controller.fragments;

import java.util.Timer;
import java.util.TimerTask;

import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ToggleButton;

import com.appspot.wifianalyzerapp.R;

import controller.threads.RadarUpdater;
import controller.threads.WifiDropdownUpdater;

public class WifiRadarFragment extends Fragment{

	Timer t;
	RadarUpdater radarUpdater;
	ToneGenerator tg;
	ToggleButton sound;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_wifi_radar,
		        container, false);
		return view;
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		sound=(ToggleButton)getActivity().findViewById(R.id.sound);
		sound.setTextOff("Mute");
		sound.setTextOn("On");
		sound.setText("Mute");
		super.onActivityCreated(savedInstanceState);
	}
	
	
	private void scheduleBeep(){
		t.schedule(new TimerTask() {
			@Override
			public void run() {
				
			    if(sound.isChecked()){
			    	tg.startTone(ToneGenerator.TONE_PROP_BEEP);
			    }
			    scheduleBeep();
			}
		}, (int)(5000/(1+10*radarUpdater.getNormalizedStrength()*radarUpdater.getNormalizedStrength())));
		Log.d("radar",radarUpdater.getNormalizedStrength()+"");
	}
	
	@Override
	public void onResume() {
		if(radarUpdater!=null){
			radarUpdater.interrupt();
		}
		if(t!=null){
			t.cancel();
		}
		if(tg!=null){
			tg.release();
		}
		radarUpdater = new RadarUpdater(getActivity(), "");
		radarUpdater.start();
		final WifiDropdownUpdater wifiDropdownUpdater = new WifiDropdownUpdater(getActivity(),radarUpdater);
		t = new Timer();
		t.schedule(new TimerTask() {
			public void run() {
				if(getActivity()!=null){
					getActivity().runOnUiThread(wifiDropdownUpdater);
				}
			}
		}, 10, 1000);
		
		tg= new ToneGenerator(AudioManager.STREAM_NOTIFICATION, 100);
		scheduleBeep();
		super.onResume();
	}
	
	@Override
	public void onPause() {
		if(t!=null){
			t.cancel();
			radarUpdater.interrupt();
		}
		if(tg!=null){
			tg.release();
		}
		super.onDestroy();
	}
	
	
	
	
}
