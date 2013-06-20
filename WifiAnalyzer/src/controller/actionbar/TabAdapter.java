package controller.actionbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.ActionBar.TabListener;
import com.actionbarsherlock.app.SherlockFragmentActivity;

public class TabAdapter implements TabListener {
	SherlockFragmentActivity activity;
	Class<? extends Fragment> cls;
	String tag;
	public TabAdapter(String tag, SherlockFragmentActivity activity,Class<? extends Fragment> cls){
		this.activity=activity;
		this.cls=cls;
		this.tag=tag;
	}
	
	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		Fragment fragment = activity.getSupportFragmentManager().findFragmentByTag(tag);
		if(fragment == null){
			fragment = Fragment.instantiate(activity, cls.getName());
			ft.add(android.R.id.content,fragment,tag);
			
		}
		else{
			ft.attach(fragment);
		}
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		Fragment fragment = activity.getSupportFragmentManager().findFragmentByTag(tag);
		if(fragment != null){
			ft.detach(fragment);
		}
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}

}
