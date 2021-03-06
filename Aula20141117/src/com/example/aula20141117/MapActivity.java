package com.example.aula20141117;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.example.aula20141117.util.AmUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends ActionBarActivity implements View.OnClickListener {

	private AmUtil mUtil = new AmUtil();
	private EditText etEndereco;
	private Button btnOk;
	private LatLng coordenadas;
	private MarkerOptions marcador;
	private GoogleMap mGoogleMap;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
		
		
		Intent intent = getIntent();
		String endereco = intent.getExtras().getString("endereco");
		mostrarMapa(endereco);
		
		init();
		
		
		
	}

	private void init() {
		
		this.etEndereco = (EditText)findViewById(R.id.id_et_endereco);
		this.btnOk = (Button)findViewById(R.id.id_bt_ok);
		this.btnOk.setOnClickListener(this);
		
	}
	
	
	
	private void mostrarMapa(String endereco){
		
		
		
		mGoogleMap = mUtil.obterGoogleMapViaResourceId(R.id.id_mapa_uk, this);
		
		mGoogleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID); //HYBRID, NONE, NORMAL, SATELLITE, TERRAIN
		
		mGoogleMap.clear();
		
		
		marcador = new MarkerOptions();
		
		//LatLng coordenadas = new LatLng (0,0);
		coordenadas = mUtil.getLatLongFromString(endereco, this);
		marcador.position(coordenadas);
		marcador.title(endereco);
		mGoogleMap.addMarker(marcador);
		mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(coordenadas, 5));
	}



	@Override
	public void onClick(View v) {
		String endereco = etEndereco.getText().toString();
		mostrarMapa(endereco);
		etEndereco.setText("");
	}

}
