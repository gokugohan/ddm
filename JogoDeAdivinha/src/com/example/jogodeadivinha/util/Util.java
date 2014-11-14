package com.example.jogodeadivinha.util;

import java.util.Random;

import android.content.Context;
import android.widget.EditText;
import android.widget.Toast;

public class Util {

	public static int inteiroAleatorio(int min, int max) {
		int ret = -1;
		Random random = new Random();
		int amplitude = (max - min) + 1;
		int salto = random.nextInt(amplitude); // produz um inteiro [0, n[
		ret = min + salto;
		return ret;
	}

	public static void fazerAparecerUmaBreveMensagem(String mensagem, Context context){
		Toast.makeText(context, mensagem, Toast.LENGTH_SHORT).show();
	}
		
	public static String getString(EditText editText){
		return editText.getText().toString().trim();
	}
	
	public static int getInt(EditText editText){
		return Integer.parseInt(editText.getText().toString().trim());
	}

}
