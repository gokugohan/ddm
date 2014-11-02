package com.heldermenezes.omeuprojeto;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainLoginSignUpActivity extends Activity implements OnClickListener{

	private Button btnLogin, btnSignUp;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
		this.setContentView(R.layout.main_login_signup_activity);
		
		
		this.btnLogin = (Button)findViewById(R.id.btnLoginMainWindow);
		this.btnSignUp = (Button)findViewById(R.id.btnSignUpMainWindow);
		this.btnLogin.setOnClickListener(this);
		this.btnSignUp.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		Intent intent = null;
		if(v.getId() == R.id.btnLoginMainWindow){
			intent = new Intent(MainLoginSignUpActivity.this,LoginActivity.class);
		}else if(v.getId() == R.id.btnSignUpMainWindow){
			intent = new Intent(MainLoginSignUpActivity.this,SignUpActivity.class);
		}
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
		this.startActivity(intent);
	}	
}
