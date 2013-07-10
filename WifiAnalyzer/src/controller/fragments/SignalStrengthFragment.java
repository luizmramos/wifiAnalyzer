package controller.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.appspot.wifianalyzerapp.R;

import controller.threads.GraphUpdater;

public class SignalStrengthFragment extends Fragment{
	

	GraphUpdater graphUpdater;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_signal_strength,
		        container, false);
		Log.d("MyDebug", "teste");
		
		return view;
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}
	@Override
	public void onResume() {
		if(graphUpdater!=null){
			graphUpdater.interrupt();
			Log.d("graph", "InterruptR");
		}
		
		graphUpdater = new GraphUpdater(getActivity());
		graphUpdater.start();
		super.onResume();
	}
	@Override
	public void onPause() {
		if(graphUpdater!=null){
			graphUpdater.interrupt();
			graphUpdater=null;
			Log.d("graph", "InterruptP");
		}
		super.onPause();
	}
	@Override
	public void onDestroy() {
		if(graphUpdater!=null){
			graphUpdater.interrupt();
			graphUpdater=null;
			Log.d("graph", "InterruptD");
		}
		super.onDestroy();
	}
}
