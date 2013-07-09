package controller.actionbar;

import android.content.res.Resources;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.appspot.wifianalyzerapp.R;

import controller.fragments.BasicWifiInfoFragment;
import controller.fragments.SignalStrengthFragment;
import controller.fragments.WifiRadarFragment;

public class ActionBarController {
	ActionBar bar; //actionbar
	TabAdapter tabAdapter;//adapt
	Resources resources;
	ActionBar.Tab[] tabs;
	String[] tags;
	public ActionBarController(ActionBar actionBar, SherlockFragmentActivity activity){
		this.bar=actionBar;
		bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		resources = activity.getResources();
		tags = new String[3];
		tags[0]=resources.getString(R.string.strength);
		tags[1]=resources.getString(R.string.radar);
		tags[2]=resources.getString(R.string.info);
		tabs = new ActionBar.Tab[3];
		for(int i=0;i<3;i++){
			tabs[i] = bar.newTab();
			tabs[i].setText(tags[i]);
			tabs[i].setTag(tags[i]);
		}
        tabs[0].setTabListener(new TabAdapter(tags[0],activity,SignalStrengthFragment.class));  
        tabs[1].setTabListener(new TabAdapter(tags[1],activity,WifiRadarFragment.class));  
        tabs[2].setTabListener(new TabAdapter(tags[2],activity,BasicWifiInfoFragment.class)); 
        for(int i=0;i<3;i++){
        	bar.addTab(tabs[i]);
        }
	}
	public void goRight(){
		if(bar.getSelectedTab().getTag().equals(tags[0])){
			bar.selectTab(tabs[1]);
		}
		else if(bar.getSelectedTab().getTag().equals(tags[1])){
			bar.selectTab(tabs[2]);
		}
		else{
			return;
		}
	}
	public void goLeft(){
		if(bar.getSelectedTab().getTag().equals(tags[0])){
			return;
		}
		else if(bar.getSelectedTab().getTag().equals(tags[1])){
			bar.selectTab(tabs[0]);
		}
		else{
			bar.selectTab(tabs[1]);
		}
	}
}
