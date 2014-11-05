package com.heldermenezes.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.heldermenezes.main.MainActivity;
import com.heldermenezes.main.R;
import com.heldermenezes.utils.AmUtil;

public class WebViewActivity extends Activity implements OnClickListener {

	private WebView mWebView;
	private String endereco;
	private WebSettings settings;
	private Button mBtnBack;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.getWindow().requestFeature(Window.FEATURE_PROGRESS);
		this.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		this.setContentView(R.layout.activity_webview);

		mWebView = (WebView) this.findViewById(R.id.id_wb_Browser);
		mBtnBack = (Button)this.findViewById(R.id.id_btn_back);
		
		mBtnBack.setOnClickListener(this);
		
		endereco = this.getIntent().getStringExtra("endereco");
		
		settingWebView();

		this.mWebView.loadUrl(endereco);

	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putString("endereco", endereco);
		AmUtil.fazerAparecerUmaBreveMensagem("onSaveInstanceState "+endereco, this);
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		endereco = savedInstanceState.getString("endereco");
		AmUtil.fazerAparecerUmaBreveMensagem("onRestoreInstanceState "+endereco, this);
	}

	private void settingWebView() {

		settings = this.mWebView.getSettings();
		settings.setJavaScriptEnabled(true);
		settings.setBuiltInZoomControls(true);

		WebViewClient mWebViewClient = new WebViewClient() {
			@Override
			public void onReceivedError(WebView view, int errorCode,
					String description, String failingUrl) {
				AmUtil.fazerAparecerUmaBreveMensagem(description,
						WebViewActivity.this);
			}
			
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				// return false to view redirection page in the same webview 
				return false;
			}
		};
		
		this.mWebView.setWebViewClient(mWebViewClient);
	}

	@Override
	public void onClick(View v) {
		if(v.getId() == R.id.id_btn_back){
			startActivity(new Intent(WebViewActivity.this,MainActivity.class));
		}
	}

}
