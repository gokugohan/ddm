package com.example.aula20141117;

import com.example.aula20141117.util.AmUtil;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity implements OnClickListener {

	private ImageButton btnPortugal, btnReinoUnido;
	private TextView mTvInfo;
	private boolean mMusicaEmReproducao;
	private MediaPlayer mLeitorDeMusica;
	private HinoPlayer mHinoPlayer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_flags);

		init();

		addListenersToButton();
	}

	private void mostrarMensagem(String msg) {
		Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
	}

	private void init() {

		this.mLeitorDeMusica = null;
		this.mMusicaEmReproducao = false;

		this.btnPortugal = (ImageButton) this.findViewById(R.id.id_ib_flag_pt);
		this.btnReinoUnido = (ImageButton) this
				.findViewById(R.id.id_ib_flag_uk);
		this.mTvInfo = (TextView) this.findViewById(R.id.id_tv_info);

	}

	private void addListenersToButton() {
		this.btnPortugal.setOnClickListener(this);
		this.btnReinoUnido.setOnClickListener(this);
		this.btnReinoUnido.setOnLongClickListener(mOnLongClickListener);
		this.btnPortugal.setOnLongClickListener(mOnLongClickListener);
	}

	private View.OnLongClickListener mOnLongClickListener = new OnLongClickListener() {
		
		@Override
		public boolean onLongClick(View v) {
			String qualPais="";
			if(v.getId()==R.id.id_ib_flag_uk){
				qualPais = "United Kingdom";
			}
			if(v.getId() == R.id.id_ib_flag_pt){
				qualPais = "Portugal";
			}
			obterMapa(qualPais);
			return true;
		}
	};
	
	private AmUtil mAmUtil = new AmUtil();
	
	private void obterMapa(String qualPais){
		String resultado = mAmUtil.testarSuporteGooglePlayServicesNoDispositivo(MainActivity.this);
		boolean ePossivel = resultado.equals("SUCCESS");
		
		if(ePossivel){
			
			Intent intent = new Intent(MainActivity.this,MapActivity.class);
			intent.putExtra("endereco", qualPais);
			startActivity(intent);
		}
	}
	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.id_ib_flag_pt:
			playOrStopHinoDePortugal();
			break;
		case R.id.id_ib_flag_uk:
			playOrStopHinoUK();
			break;
		default:

		}
	}

	private void playOrStopHinoUK() {
		playOrStopMedia(R.raw.god_save_the_queen,
				this.getString(R.string.str_uk));
	}

	private void playOrStopHinoDePortugal() {
		playOrStopMedia(R.raw.portugal,
				this.getString(R.string.str_portugal));
	}

	private void playOrStopMedia(int media, String detalhe) {
		
		StringBuilder sb = new StringBuilder("");
	
		
		if (this.mLeitorDeMusica != null && this.mMusicaEmReproducao) {
			// o objeto para reprodu��o est� dispon�vel
			this.mLeitorDeMusica.stop();
			this.mLeitorDeMusica.release();
			this.mLeitorDeMusica = null;
			this.mMusicaEmReproducao = false;
			sb.append(this.getString(R.string.str_reproducao_stop)).append(" ")
					.append(detalhe);
			mostrarMensagem("Stop");
		} else {
			this.mLeitorDeMusica = MediaPlayer.create(MainActivity.this, media);
			// fazer play/start
			this.mLeitorDeMusica.seekTo(0);
			this.mLeitorDeMusica.start();
			this.mMusicaEmReproducao = true;
			sb.append(this.getString(R.string.str_reproducao_start))
					.append(" ").append(detalhe);
			mostrarMensagem("Reproduzido");
		}

		this.mTvInfo.setText(sb.toString());
	}
}
