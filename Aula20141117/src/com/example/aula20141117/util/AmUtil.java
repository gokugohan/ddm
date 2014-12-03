package com.example.aula20141117.util;

import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBar.LayoutParams;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

public class AmUtil {
public String mUltimoErro="";
	
	public Bundle getDeviceResolutionPixels (Activity origemDoPedido){
		Bundle ret=new Bundle();
		DisplayMetrics dm = new DisplayMetrics();
		origemDoPedido.getWindowManager().getDefaultDisplay().getMetrics(dm);
		ret.putInt("widthPixels", dm.widthPixels);
		ret.putInt("heightPixels", dm.heightPixels);
		return ret;
	}//getDeviceResolutionPixels
	
	public void mostrarMensagem (String msg, Activity origem){
		Toast t=Toast.makeText (origem, msg, Toast.LENGTH_LONG);
		t.show();
	}//mostrarMensagem

	public String testarSuporteGooglePlayServicesNoDispositivo (Activity activityGeradoraDoPedido){
		//implica import com.google.android.gms.common.GooglePlayServicesUtil;
		int resultado = GooglePlayServicesUtil.isGooglePlayServicesAvailable(activityGeradoraDoPedido.getApplicationContext());
		String ret="";
		switch (resultado){
			case ConnectionResult.API_UNAVAILABLE:
				ret= "API_UNAVAILABLE";
				break;

			case ConnectionResult.CANCELED:
				ret= "CANCELED";
				break;

			case ConnectionResult.DEVELOPER_ERROR:
				ret= "DEVELOPER_ERROR";
				break;

			case ConnectionResult.DRIVE_EXTERNAL_STORAGE_REQUIRED:
				ret= "DRIVE_EXTERNAL_STORAGE_REQUIRED";
				break;

			case ConnectionResult.INTERNAL_ERROR:
				ret= "INTERNAL_ERROR";
				break;

			case ConnectionResult.INTERRUPTED:
				ret= "INTERRUPTED";
				break;

			case ConnectionResult.INVALID_ACCOUNT:
				ret= "INVALID_ACCOUNT";
				break;

			case ConnectionResult.LICENSE_CHECK_FAILED:
				ret= "LICENSE_CHECK_FAILED";
				break;

			case ConnectionResult.NETWORK_ERROR:
				ret= "NETWORK_ERROR";
				break;

			case ConnectionResult.RESOLUTION_REQUIRED:
				ret= "RESOLUTION_REQUIRED";
				break;

			case ConnectionResult.SERVICE_DISABLED:
				ret= "SERVICE_DISABLED";
				break;

			case ConnectionResult.SERVICE_INVALID:
				ret= "SERVICE_INVALID";
				break;

			case ConnectionResult.SERVICE_MISSING:
				ret= "SERVICE_MISSING";
				break;

			case ConnectionResult.SERVICE_VERSION_UPDATE_REQUIRED:
				ret= "SERVICE_VERSION_UPDATE_REQUIRED";
				break;

			case ConnectionResult.SIGN_IN_REQUIRED:
				ret= "SIGN_IN_REQUIRED";
				break;

			case ConnectionResult.SUCCESS:
				ret= "SUCCESS";
				break;

			case ConnectionResult.TIMEOUT:
				ret= "TIMEOUT";
				break;

			default:
				ret= "UNKNOWN SITUATION !!";
				break;
		}//switch
		return ret;
	}//testarSuporteGooglePlayServicesNoDispositivo
	
	public GoogleMap obterGoogleMapViaResourceId (int idDoMapa, FragmentActivity geradoraDoPedido){
		//atenção não confundir com android.app.FragmentManager
		//este é android.support.v4.app.FragmentManager;
		android.support.v4.app.FragmentManager mFragmentManager; //gestor de fragmentos na library v4
		android.support.v4.app.Fragment mMapFragment; //tipo de Fragment na library v4
		com.google.android.gms.maps.SupportMapFragment mSupportMapFragment; //transformador de fragmentos em mapas
		com.google.android.gms.maps.GoogleMap mMap; //mapas na Maps API V2
		
		GoogleMap ret=null;
				
		String resultado = testarSuporteGooglePlayServicesNoDispositivo(geradoraDoPedido);
		mUltimoErro = resultado;
		//mostrarMensagem (resultado);
				
		if (resultado.equals("SUCCESS")){
			//android.support.v4.app.FragmentManager;
			mFragmentManager = geradoraDoPedido.getSupportFragmentManager();
			if (mFragmentManager == null)
				mUltimoErro = "ERRO : não foi possível obter um objeto FragmentManager";
			else
				mUltimoErro = "SUCESSO: foi possível obter um objeto FragmentManager";
			
			//GoogleMap implica import com.google.android.gms.maps.GoogleMap;
			mMapFragment = mFragmentManager.findFragmentById(idDoMapa);
			if (mMapFragment == null)
				mUltimoErro = "ERRO : não foi possível obter um MapFragment via um FragmentManager";
			else
				mUltimoErro = "SUCESSO: foi possível obter um MapFragment via um FragmentManager";
			
			//SupportMapFragment implica import com.google.android.gms.maps.SupportMapFragment;
			mSupportMapFragment = (SupportMapFragment) mMapFragment;
			if (mSupportMapFragment == null)
				mUltimoErro =  "ERRO : não foi possível fazer um cast para SupportMapFragment a partir de um MapFragment";
			else
				mUltimoErro =  "SUCESSO: foi possível fazer um cast para SupportMapFragment a partir de um MapFragment";
			
			//finalmente o mapa
			ret = mMap = mSupportMapFragment.getMap();
			if (mMap == null)
				mUltimoErro = "ERRO : não foi possível um GoogleMap a partir de um objeto SupportMapFragment via chamada a getMap()";
			else
				mUltimoErro = "SUCESSO: foi possível um GoogleMap a partir de um objeto SupportMapFragment via chamada a getMap()";
				
		}//if
		
		return ret;
	}//obterGoogleMapViaResourceId
	
	 public LatLng getLatLongFromString(String address, Activity calling)
     {
		 LatLng ret=null;
		 
         double lat= 0.0, lng= 0.0;

         Geocoder geoCoder = new Geocoder(calling, Locale.getDefault());    
         try 
         {
             List<Address> addresses = geoCoder.getFromLocationName(address , 1);
             if (addresses.size() > 0) 
             {            
                 GeoPoint p = new GeoPoint(
                         (int) (addresses.get(0).getLatitude() * 1E6), 
                         (int) (addresses.get(0).getLongitude() * 1E6));

                 lat=p.getLatitudeE6()/1E6;
                 lng=p.getLongitudeE6()/1E6;

                 Log.d("Latitude", ""+lat);
                 Log.d("Longitude", ""+lng);
                 
                 ret=new LatLng (lat, lng);
             }
         }
         catch(Exception e)
         {
           e.printStackTrace();
         }
         
         return ret;
     }//getLatLongFromString
 	

	public void setImageButtonSize(ImageButton ib, float pW, float pH){
		//FRACASSO: não consegue redimensionar o ImageButton
		
		//desnecessário
		/*
		ViewGroup.LayoutParams params = ib.getLayoutParams();
		int h=getDeviceResolutionPixels().getInt("heightPixels");
		int w=getDeviceResolutionPixels().getInt("widthPixels");
		*/
		
		//does not resize...
		//http://stackoverflow.com/questions/7866639/resize-imageview-in-run-time-using-a-button-android
		ib.setScaleType(ImageButton.ScaleType.CENTER_INSIDE); //or CENTER_CROP
		LayoutParams p = (LayoutParams) ib.getLayoutParams();
		p.height=LayoutParams.MATCH_PARENT;
		p.width=LayoutParams.MATCH_PARENT;
		ib.setLayoutParams(p);
		ib.requestLayout();
		/*
		//não produziu efeito
		params.height=LayoutParams.MATCH_PARENT;
		params.width=LayoutParams.WRAP_CONTENT;
		ib.setLayoutParams(params);
		*/
		
		/*
		params.height=Integer.parseInt(h/3+"dp");
		params.width=Integer.parseInt(w/3+"dp");
		ib.setLayoutParams(params);
		*/
		
		/*
		ib.setScaleType(ImageButton.ScaleType.CENTER_INSIDE); //or CENTER_CROP
		LinearLayout.LayoutParams newParams = new LinearLayout.LayoutParams((int)(w*pW), (int)(h*pH));
		ib.setLayoutParams(newParams);
		*/
		
		/*
		params.width=(int)Math.round(pW*params.width);
		params.height=(int)Math.round(pW*params.height);
		ib.setLayoutParams(params);
		*/
			
	}//setImageButtonSize
}
