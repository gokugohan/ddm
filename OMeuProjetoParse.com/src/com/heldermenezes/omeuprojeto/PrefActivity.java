package com.heldermenezes.omeuprojeto;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;

public class PrefActivity extends PreferenceActivity{

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.getFragmentManager().beginTransaction().replace(android.R.id.content, new PrefFragment()).commit();
	}
	
	
	private static class PrefFragment extends PreferenceFragment{
		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			this.addPreferencesFromResource(R.xml.pref);
			showUserSettings();
		}
		
		private void showUserSettings() {
	        SharedPreferences sharedPrefs = PreferenceManager
	                .getDefaultSharedPreferences(getActivity());
	 
	        StringBuilder builder = new StringBuilder();
	 
	        builder.append("\n Username: "
	                + sharedPrefs.getString("prefUsername", "NULL"));
	 
	        builder.append("\n Send report:"
	                + sharedPrefs.getBoolean("prefSendReport", false));
	 
	        builder.append("\n Sync Frequency: "
	                + sharedPrefs.getString("prefSyncFrequency", "NULL"));
	    }
	}
}
