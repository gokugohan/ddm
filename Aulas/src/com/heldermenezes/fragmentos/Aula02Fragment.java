package com.heldermenezes.fragmentos;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.heldermenezes.main.R;
import com.heldermenezes.utils.AmUtil;

public class Aula02Fragment extends Fragment {
	
	private TextView tvMsg;
	private Button btnMsg;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.fragment_aula02, container,false);
		tvMsg = (TextView)view.findViewById(R.id.tvMsg);
		btnMsg = (Button)view.findViewById(R.id.btnMsg);
		
		btnMsg.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				AmUtil.fazerAparecerUmaBreveMensagem("Message Util from AmUtil static method", getActivity());
			}
		});
		return view;
	}
}
