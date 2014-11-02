package com.heldermenezes.utils;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.wifi.WifiManager;
import android.widget.Toast;

public class Util {

	/**
	 * 
	 * @param context
	 * @param feature - PackageManager.FEATURE_WIFI/PackageManager.FEATURE_BLUETOOTH
	 * @return true if exist, otherwise return false;
	 */
	public static boolean isFeatureExistInDevice(Context context, String feature) {
		boolean ret = context.getPackageManager().hasSystemFeature(feature);;
		if (feature.equals(PackageManager.FEATURE_WIFI)) {
			WifiManager mWifiManager = (WifiManager) context
					.getSystemService(context.WIFI_SERVICE);
			ret = (mWifiManager != null);
		}else if(feature.equals(PackageManager.FEATURE_BLUETOOTH)) {
			
			BluetoothAdapter ba = BluetoothAdapter.getDefaultAdapter();
			ret = ba !=null;
		}
		// Double test for wifi feature availability
		return ret;
	}

	public static String[] featuresReport(Context context) {
		String ret[] = new String[2];
		ret[0] = isFeatureExistInDevice(context, PackageManager.FEATURE_WIFI) ? "Device has wifi"
				: "Device without wifi!";
		ret[1] = isFeatureExistInDevice(context,
				PackageManager.FEATURE_BLUETOOTH) ? "Device has bluetooth"
				: "Device without bluetooth!";
		return ret;
	}

	
	public static String featuresReportSingleLine(Context context) {
		StringBuilder ret = new StringBuilder();
		String tmp[] = featuresReport(context);
		for (int i = 0; i < tmp.length; i++) {
			ret.append(tmp[i]).append("\n");
		}
		return ret.toString();
	}

	public static void showMessage(Context context, String message) {
		Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
	}
}
