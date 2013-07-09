package view;

import com.actionbarsherlock.app.SherlockDialogFragment;
import com.appspot.wifianalyzerapp.R;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

public class WifiNotEnabledDialog extends SherlockDialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.wifiNotEnabledMessage)
               .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                	   Intent gpsOptionsIntent = new Intent(  android.provider.Settings.ACTION_WIFI_SETTINGS);  
                       startActivityForResult(gpsOptionsIntent,0); 
                   }
               })
               /*.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                       // User cancelled the dialog
                   }
               })*/;
        // Create the AlertDialog object and return it
        return builder.create();
    }
}

