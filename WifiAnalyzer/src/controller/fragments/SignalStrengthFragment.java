package controller.fragments;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockDialogFragment;
import com.appspot.wifianalyzerapp.R;

public class SignalStrengthFragment extends Fragment{
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
		TextView tv = (TextView)getActivity().findViewById(R.id.editText1);
		tv.setText("test");
		super.onActivityCreated(savedInstanceState);
	}
}
