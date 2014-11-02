package com.heldermenezes.utils;

import android.content.Context;
import android.widget.Toast;

public class AmUtil {
	
	public static void fazerAparecerUmaBreveMensagem(String mensagem, Context context){
		Toast.makeText(context, mensagem, Toast.LENGTH_SHORT).show();
	}
	
	
	
}
