package com.heldermenezes.omeuprojeto;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.util.Base64;
import android.util.Log;

import com.activeandroid.ActiveAndroid;
import com.heldermenezes.post.Post;
import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseObject;
import com.parse.ParseUser;

public class MyApplication extends Application{
 	
	@Override
	public void onCreate() {
		super.onCreate();
		
		ParseObject.registerSubclass(Post.class);
		Parse.initialize(this, this.getString(R.string.application_id),getString(R.string.client_key));
		
		ParseUser.enableAutomaticUser();
		ParseACL defaultACL = new ParseACL();
		// Optionally enable public read access while disabling public write access.
		// defaultACL.setPublicReadAccess(true);
		ParseACL.setDefaultACL(defaultACL, true);
		
		 
		//Initialize activeadnroid
		ActiveAndroid.initialize(this);
	}

	private void generateKeyHash() {
		try {
		        PackageInfo info = getPackageManager().getPackageInfo(
		                "com.heldermenezes.omeuprojeto", PackageManager.GET_SIGNATURES);
		        for (Signature signature : info.signatures) {
		            MessageDigest md = MessageDigest.getInstance("SHA");
		            md.update(signature.toByteArray());
		            Log.i("KeyHash:",
		                    Base64.encodeToString(md.digest(), Base64.DEFAULT));
		            System.out.println(Base64.encodeToString(md.digest(), Base64.DEFAULT));
		        }
		    } catch (NameNotFoundException e) {

		    } catch (NoSuchAlgorithmException e) {

		    }
	}
}
