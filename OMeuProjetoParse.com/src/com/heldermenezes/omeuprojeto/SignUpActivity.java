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

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignUpActivity extends Activity {

	private Button btnSignUp;
	private EditText eTUserName, eTPassWord, eTPassWordRepeat;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.setContentView(R.layout.signup_activity);

		eTUserName = (EditText) findViewById(R.id.EtUserName);
		eTPassWord = (EditText) findViewById(R.id.EtPassWord);
		eTPassWordRepeat = (EditText) findViewById(R.id.EtPassword_repeat);
		btnSignUp = (Button) findViewById(R.id.BtnSignUp);

		btnSignUp.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				signup();
			}
		});

	}

	private void signup() {
		String username = eTUserName.getText().toString().trim();
		String password = eTPassWord.getText().toString().trim();
		String passewordRepeat = eTPassWordRepeat.getText().toString().trim();

		boolean validationError = false;

		StringBuilder validationErrorMessage = new StringBuilder("Error: ");

		if (username.length() == 0) {
			validationError = true;
			validationErrorMessage.append("Por favor inserir o username").append("\n");
		}
		if (password.length() == 0) {
			if (validationError) {
				validationErrorMessage.append("Erro ao login...").append("\n");
			}
			validationError = true;
			validationErrorMessage.append("Por favor inserir a palavra passe").append("\n");
		}

		if (!password.equals(passewordRepeat)) {
			if (validationError) {
				validationErrorMessage.append("Erro ao login...").append("\n");
			}
			validationError = true;
			validationErrorMessage.append("Palavras passes não são iguais").append("\n");
		}

		if (validationError) {
			Toast.makeText(this, validationErrorMessage.toString(),
					Toast.LENGTH_SHORT).show();
			return;
		}

		final ProgressDialog progressDialog = new ProgressDialog(this);
		progressDialog.setMessage("Signing up...");
		progressDialog.show();

		ParseUser newUser = new ParseUser();
		newUser.setUsername(username);
		newUser.setPassword(password);

		newUser.signUpInBackground(new SignUpCallback() {
			
			@Override
			public void done(ParseException e) {
				progressDialog.dismiss();
				if (e != null) {
					Toast.makeText(getBaseContext(), "Sign up successfuly",
							Toast.LENGTH_SHORT).show();
					Intent intent = new Intent(SignUpActivity.this,
							MainActivity.class);
					startActivity(intent);
					finish();
				} else {
					Intent intent = new Intent(SignUpActivity.this,
							MainLoginSignUpActivity.class);
					startActivity(intent);
					finish();
				}
			}
		});
	}

}
