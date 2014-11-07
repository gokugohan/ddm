package com.example.jogodeadivinha;

import com.example.jogodeadivinha.logic.JogoDeAdivinha;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class JogoDeAdivinhaEmCurso extends Activity {

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		this.setContentView(R.layout.activity_jogo_de_adivinha_em_curso);
	
		Bundle extras = getIntent().getExtras();
		
		TextView tv = (TextView)findViewById(R.id.id_tv_debug_jogar);
		EditText et = (EditText)findViewById(R.id.id_et_numjogar);
		extras.putInt("NUMJOGADA", Integer.parseInt(et.getText().toString()));
		
		int min = extras.getInt("MIN");
		int max = extras.getInt("MAX");
		
		JogoDeAdivinha jogo = new JogoDeAdivinha(min,max);
		
		
	}
	
}
