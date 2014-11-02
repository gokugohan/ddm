package com.heldermenezes.omeuprojeto;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginActivity extends Activity {

	private Button loginbutton;
	private Button signup;
	private String usernametxt;
	private String passwordtxt;
	private EditText password;
	private EditText username;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_activity);
		
		loginbutton = (Button) findViewById(R.id.login);
		signup = (Button) findViewById(R.id.signup);
		password = (EditText) findViewById(R.id.password);
		username = (EditText) findViewById(R.id.username);

		loginbutton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				usernametxt = username.getText().toString().trim();
				passwordtxt = password.getText().toString().trim();
				
				
				if (usernametxt.length() > 0 && passwordtxt.length() > 0) {
					
					final ProgressDialog dialog = new ProgressDialog(LoginActivity.this);
					dialog.setMessage("Signing in...");
					dialog.show();
					// Send data to Parse.com for verification
					ParseUser.logInInBackground(usernametxt, passwordtxt,
							new LogInCallback() {
						
								@Override
								public void done(ParseUser user,
										ParseException e) {
									dialog.dismiss();
									// If user exist
									if (user != null) {
										// If user exist and authenticated, send
										// user to MainActivity
										Intent intent = new Intent(
												LoginActivity.this,
												MainActivity.class);
										startActivity(intent);
										
										
										Toast.makeText(getApplicationContext(),
												"Successfully Logged in",
												Toast.LENGTH_LONG).show();

										
										finish();
									} else {
										
										Toast.makeText(
												getApplicationContext(),
												"No such user exist, please signup",
												Toast.LENGTH_LONG).show();
									}
								}
								
							});
				}
			}
		});

		signup.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent intent = new Intent(getBaseContext(),SignUpActivity.class);
				startActivity(intent);
				finish();
			}
		});
	}

}
