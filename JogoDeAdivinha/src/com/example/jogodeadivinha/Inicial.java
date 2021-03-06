package com.example.jogodeadivinha;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.jogodeadivinha.model.JogoDeAdivinha;
import com.example.jogodeadivinha.util.Util;

public class Inicial extends Activity implements
		OnClickListener {
	private JogoDeAdivinha mJogo;
	private Button mBtnComecar;
	private EditText mEtMin, mEtMax;
	private TextView mFeedBack;
	private int mMin, mMax;

	public final static String MINIMO = "MINIMO";
	public final static String MAXIMO = "MAXIMO";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_inicial);
		init();

	}

	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt(MINIMO, mMin);
		outState.putInt(MAXIMO, mMax);
	}
	
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		mMin = savedInstanceState.getInt(MINIMO);
		mMax = savedInstanceState.getInt(MAXIMO);
	}
	
	private void init() {
		this.mBtnComecar = (Button) findViewById(R.id.id_btn_comecar);
		this.mEtMin = (EditText) findViewById(R.id.id_et_min);
		this.mEtMax = (EditText) findViewById(R.id.id_et_max);
		this.mFeedBack =(TextView)findViewById(R.id.id_tv_feedback);
		this.mBtnComecar.setOnClickListener(this);

	}

	private boolean isValuesNotEmpty() {
		try {
			String min = Util.getString(mEtMin);
			String max = Util.getString(mEtMax);
			
			if ((!min.isEmpty() && !max.isEmpty())) {
				this.mMin = Integer.parseInt(min);
				this.mMax = Integer.parseInt(max);
				mJogo = new JogoDeAdivinha(mMin,mMax);
				mFeedBack.setText(mJogo.estadoDoJogoEnquantoFrase());
				return true;
			}
		} catch (Exception e) {
			mEtMin.setText("");
			mEtMax.setText("");
			mEtMin.setHint(this.getResources().getString(R.string.str_error_number_format));
			mEtMax.setHint(this.getResources().getString(R.string.str_error_number_format));
		}

		return false;
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.id_btn_comecar) {
			comecarJogo();
		}
	}

	private void comecarJogo() {
		if (isValuesNotEmpty()) {
			Intent intent = new Intent(Inicial.this,
					Jogar.class);
			Bundle bundle = mJogo.estadoDoJogoEnquantoBundle();
			intent.putExtras(bundle);
			this.startActivity(intent);
			finish();
		}
	}
}
