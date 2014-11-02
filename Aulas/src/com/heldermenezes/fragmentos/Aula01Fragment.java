package com.heldermenezes.fragmentos;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.heldermenezes.aulas.R;

public class Aula01Fragment extends Fragment implements View.OnClickListener {

	private Button btn02,btn03;
	private TextView tv;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_aula01, container,false);
		
		btn02 = (Button)view.findViewById(R.id.btnClickMe02);
		btn03 = (Button)view.findViewById(R.id.btnClickMe03);
		tv = (TextView)view.findViewById(R.id.tvHelloWorld);
		btn02.setOnClickListener(this);
		btn03.setOnClickListener(clickListener);
		return view;
	}

	@Override
	public void onClick(View v) {
		if(v.getId() == R.id.btnClickMe02){
			tv.setText("Clicked (OnClickListener implemented)");
		}
	}
	
	
	
	
	OnClickListener clickListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			tv.setText("Clicked (OnClickListener act as a variable)");
		}
	};
	
}
