package com.heldermenezes.csvfilereader;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

public class CountryDetailDialogFragment extends DialogFragment {

	@Override
	@NonNull
	public Dialog onCreateDialog(Bundle savedInstanceState) {

		Bundle bundle = this.getArguments();
		int numero = bundle.getInt("number");
		String country = bundle.getString("country");
		String capital = bundle.getString("capital");
		
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Country Details");
        
        builder.setMessage("Number: "+numero + "\nCountry: "+country+"\nCapital: "+capital)
               .setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                   @Override
				public void onClick(DialogInterface dialog, int id) {
                   }
               });
        return builder.create();
	}
}
