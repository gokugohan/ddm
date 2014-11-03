package com.heldermenezes.fragmentos;

import android.app.Fragment;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.heldermenezes.main.R;
import com.heldermenezes.utils.Util;

public class Aula03Fragment extends Fragment implements View.OnClickListener {

	private Button mBtnToggleWifi, mBtnToggleBluetooth;
	private TextView mTextViewAboutApp, mTextViewBluetoothState;
	private WifiManager mWifiManager;
	private Boolean mWifiOn, mBlueToothOn, mAuthorizedBluetooth;
	private Context mContext;

	private boolean mWifiAvailable = false,mbDeviceHasWifi,mbdeficeHasBluetooth;
	private final int ON_CREATE = 1;

	private BluetoothAdapter mBluetoothAdapter;

	private final int REQUEST_CODE = 0;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater
				.inflate(R.layout.fragment_aula03, container, false);

		this.mBtnToggleWifi = (Button) view.findViewById(R.id.id_btn_wifi);
		this.mBtnToggleBluetooth = (Button) view
				.findViewById(R.id.id_btn_bluetooth);
		this.mTextViewAboutApp = (TextView) view
				.findViewById(R.id.id_tv_about_this_app);

		this.mTextViewBluetoothState = (TextView) view
				.findViewById(R.id.id_tv_bluetoothState);

		this.mBtnToggleWifi.setOnClickListener(this);

		this.mBtnToggleBluetooth.setOnClickListener(mBluetoothListenerClick);

		this.mContext = this.getActivity();
		
		Toast.makeText(mContext, Util.featuresReportSingleLine(mContext),Toast.LENGTH_SHORT).show();
		
		mbDeviceHasWifi = Util.isFeatureExistInDevice(mContext, PackageManager.FEATURE_WIFI);
		mbdeficeHasBluetooth = Util.isFeatureExistInDevice(mContext, PackageManager.FEATURE_BLUETOOTH);
		
		stateWifiConnectionCheckInit();

		return view;

	}

	private OnClickListener mBluetoothListenerClick = new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			if(mbdeficeHasBluetooth){
				mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
				if (!mBluetoothAdapter.isEnabled()) {
					Intent turnOnBluetooth = new Intent(
							BluetoothAdapter.ACTION_REQUEST_ENABLE);
					startActivityForResult(turnOnBluetooth, REQUEST_CODE);
					mBtnToggleBluetooth.setText(mContext.getResources()
							.getString(R.string.str_put_bluetooth_of));
					mTextViewBluetoothState.setText(mContext.getResources().getString(R.string.str_info_bluetooth_on_success));
				} else {
					mBtnToggleBluetooth.setText(mContext.getResources()
							.getString(R.string.str_put_bluetooth_on));
					mTextViewBluetoothState.setText(mContext.getResources().getString(R.string.str_info_bluetooth_already_on));
					mBluetoothAdapter.disable();
				}
			}else{
				mTextViewBluetoothState.setText(mContext.getResources().getString(R.string.str_info_device_has_no_bluetooth_feature));
			}
			
		}
	};

	private void stateWifiConnectionCheckInit() {
		
		
		if(mbDeviceHasWifi){
			try {
				this.mWifiManager = (WifiManager) this.mContext
						.getSystemService(Context.WIFI_SERVICE);
			} catch (Exception e) {
				Log.i(this.getClass().getName(),
						"Error while trying to point to the wifi service!\n"
								+ e.getMessage() + "\n");
			}

			
			
			if (this.mWifiManager != null) {
				this.mWifiAvailable = true;
				this.mWifiOn = this.mWifiManager.isWifiEnabled();
				toggleWifi();
			} else {
				this.mWifiAvailable = false;
				toggleWifi();
			}
			updateInfterface(1, mWifiOn);
		}else{
			this.mTextViewAboutApp.setText(mContext.getResources().getString(R.string.str_info_device_has_no_bluetooth_feature));
			this.mBtnToggleWifi.setEnabled(false);
		}
		
		
	}

	private void toggleWifi() {
		boolean conectionOk = this.mWifiManager.setWifiEnabled(this.mWifiOn);
		updateInfterface(0, conectionOk);
	}

	private void updateInfterface(int state, boolean conectionOk) {
		if (state == ON_CREATE) {
			if (mWifiOn) {
				mBtnToggleWifi.setText(this.mContext.getResources().getString(
						R.string.str_put_wifi_of));
			} else {
				mBtnToggleWifi.setText(this.mContext.getResources().getString(
						R.string.str_put_wifi_on));
			}
		} else {
			if (conectionOk) {
				this.mWifiOn = !this.mWifiOn;

				if (this.mWifiOn) {
					this.mBtnToggleWifi.setBackground(this.mContext
							.getResources().getDrawable(
									R.drawable.button_conected));
					mBtnToggleWifi.setText(this.mContext.getResources().getString(
							R.string.str_put_wifi_of));
					this.mTextViewAboutApp.setText(this.mContext.getResources()
							.getString(R.string.str_info_wifi_off_success));
				} else {
					this.mBtnToggleWifi.setBackground(this.mContext
							.getResources().getDrawable(
									R.drawable.button_disconected));
					mBtnToggleWifi.setText(this.mContext.getResources().getString(
							R.string.str_put_wifi_on));
					this.mTextViewAboutApp.setText(this.mContext.getResources()
							.getString(R.string.str_info_wifi_on_success));
				}
			} else {
				if (this.mWifiOn) {
					this.mTextViewAboutApp.setText(this.mContext.getResources()
							.getString(R.string.str_info_wifi_off_fails));
				} else {
					this.mTextViewAboutApp.setText(this.mContext.getResources()
							.getString(R.string.str_info_wifi_on_fails));
				}
			}
		}

	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.id_btn_wifi) {
			toggleWifi();
		}
	}
}
