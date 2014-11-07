package com.example.jogodeadivinha;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;


public class JogoDeAdivinhaMainActivity extends Activity implements OnClickListener {

	private Button mBtnComecar;
	private EditText mEtMin, mEtMax;
	private int mMin,mMax;
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogo_de_adivinha_main);
        init();
        
    }


	private void init() {
		this.mBtnComecar = (Button)findViewById(R.id.id_btn_comecar);
		this.mEtMin = (EditText)findViewById(R.id.id_et_min);
		this.mEtMax = (EditText)findViewById(R.id.id_et_max);
		
		this.mBtnComecar.setOnClickListener(this);
		
		
	}


	private boolean isValuesNotEmpty() {
		if(!this.mEtMin.getText().toString().trim().isEmpty() && !this.mEtMax.getText().toString().trim().isEmpty() ){
			this.mMin = Integer.parseInt(this.mEtMin.getText().toString().trim());
			this.mMax = Integer.parseInt(this.mEtMax.getText().toString().trim());
			return true;
		}
		return false;
	}


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.jogo_de_adivinha_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    
	@Override
	public void onClick(View v) {
		if(v.getId()==R.id.id_btn_comecar){
			if(isValuesNotEmpty()){
				Intent intent = new Intent(JogoDeAdivinhaMainActivity.this,JogoDeAdivinhaEmCurso.class);
				//intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				Bundle bundle = new Bundle();
				bundle.putInt("MIN", this.mMin);
				bundle.putInt("MAX", this.mMax);
				intent.putExtras(bundle);
				
				this.startActivity(intent);
			}
			
		}
	}
}
