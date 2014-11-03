package com.heldermenezes.fragmentos;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.heldermenezes.main.R;
import com.heldermenezes.utils.AmUtil;
import com.heldermenezes.utils.Util;

public class Aula05Fragment extends Fragment {

	private Button mButtonSite1, mButtonSite2,mButtonSite3;
	private TextView mTvAboutApp;
	private EditText mEtEndereco;
	private OnClickListener mclickHandler;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater
				.inflate(R.layout.fragment_aula05, container, false);

		init(view);

		return view;
	}

	private void init(View view) {
		mTvAboutApp = (TextView) view
				.findViewById(R.id.id_tv_about_app_aula_cinco);
		mButtonSite1 = (Button) view.findViewById(R.id.id_btn_site1);
		mButtonSite2 = (Button) view.findViewById(R.id.id_btn_site2);
		mButtonSite3 = (Button) view.findViewById(R.id.id_btn_site3);
		mEtEndereco = (EditText)view.findViewById(R.id.id_et_url);

		mclickHandler = new OnClickListener() {

			@Override
			public void onClick(View v) {
				String endereco = "";
				switch (v.getId()) {
				case R.id.id_btn_site1:
					endereco = getString(R.string.str_address_site1);
					break;
				case R.id.id_btn_site2:
					endereco = getString(R.string.str_address_site2);
					break;
					
				case R.id.id_btn_site3:
					endereco = mEtEndereco.getText().toString();
					break;
				}

				abrirSite(endereco);

			}
		};
		
		mButtonSite1.setOnClickListener(mclickHandler);
		mButtonSite2.setOnClickListener(mclickHandler);
		mButtonSite3.setOnClickListener(mclickHandler);
		
	}

	protected void abrirSite(String endereco) {
		// parte 1 - tipo de mensagem
		Intent intent = new Intent(Intent.ACTION_VIEW);
		// parte 2 - detalhes da mensagem
		Uri uri = Uri.parse(endereco);
		intent.setData(uri);
		// parte 3 - invocar a mensagem
		startActivity(intent);
	}

}