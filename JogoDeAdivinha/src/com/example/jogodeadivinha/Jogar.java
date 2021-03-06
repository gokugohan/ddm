package com.example.jogodeadivinha;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.jogodeadivinha.model.JogoDeAdivinha;
import com.example.jogodeadivinha.util.Util;

public class Jogar extends Activity {

	private final static String NUMERO_JOGADA = "JOGADA";
	private JogoDeAdivinha mJogo;
	private TextView mTvDebugJogo, mTvComoJogar;
	private EditText mEtNumJogada;
	private Button mBtnJogar, mBtnRestart;
	private int numeroJogada;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.setContentView(R.layout.activity_jogar);

		init();
		addListenersToButton();

		Bundle b = getIntent().getExtras();
		mJogo = new JogoDeAdivinha(b);

		mTvDebugJogo.setText(mJogo.estadoDoJogoEnquantoFrase());

	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt(NUMERO_JOGADA, numeroJogada);
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		savedInstanceState.getInt(NUMERO_JOGADA);
	}

	private void addListenersToButton() {
		mBtnJogar.setOnClickListener(listener);
		mBtnRestart.setOnClickListener(listener);
	}

	private void init() {
		mTvDebugJogo = (TextView) findViewById(R.id.id_tv_debug_jogar);
		mTvComoJogar = (TextView) findViewById(R.id.id_tv_como_jogar);
		mEtNumJogada = (EditText) findViewById(R.id.id_et_numero_jogado);
		mBtnJogar = (Button) findViewById(R.id.id_btn_jogar);
		mBtnRestart = (Button) findViewById(R.id.id_btn_restart);
	}

	private View.OnClickListener listener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.id_btn_jogar:
				jogar();
				break;
			case R.id.id_btn_restart:
				reiniciar();
				break;
			}
		}
	};

	protected void reiniciar() {
		Intent intent = new Intent(Jogar.this, Inicial.class);
		startActivity(intent);
		finish();
	}

	protected void jogar() {

		try {
			numeroJogada = Util.getInt(mEtNumJogada);
			mEtNumJogada.setText("");
			mJogo.setUltimoNumJogado(numeroJogada);
			
			String resposta = mJogo.jogar();
			mTvComoJogar.setText(resposta);
			
			if(mJogo.isJogoTerminou()){
				AlertDialog.Builder dialog = new AlertDialog.Builder(Jogar.this);
				dialog.setTitle("Pretende voltar a jogar!");
				//dialog.setMessage(getResources().getString(R.string.str_dialog_mensagem));
				dialog.setMessage(resposta);
				dialog.setPositiveButton("SIM", positiveButton);
				
				dialog.setNegativeButton("N�O", negativeButton);
				
				dialog.show();
			}
			
			

			//Util.fazerAparecerUmaBreveMensagem("Jogando", Jogar.this);
			
		} catch (Exception e) {
			mEtNumJogada.setText("");
			mEtNumJogada.setHint(getResources().getString(R.string.str_pista_input_nao_adequado));
		}
	}

	DialogInterface.OnClickListener positiveButton = new DialogInterface.OnClickListener() {

		@Override
		public void onClick(DialogInterface dialog, int which) {
			Intent intent = new Intent(Jogar.this,Inicial.class);
			startActivity(intent);
			finish();
			
//			Intent intent = new Intent(Intent.ACTION_MAIN);
//			intent.addCategory(Intent.CATEGORY_HOME);
//			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//			startActivity(intent);
			
		}
	};
	
	DialogInterface.OnClickListener negativeButton = new DialogInterface.OnClickListener() {

		@Override
		public void onClick(DialogInterface dialog, int which) {
			dialog.dismiss();
			finish();
		}
	};
}
