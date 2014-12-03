package com.example.aula20141117;

import android.content.Context;
import android.media.MediaPlayer;

public class HinoPlayer extends MediaPlayer {

	private boolean isHinoPlaying;
	private Context context;
	private HinoPlayer mHinoPlayer;
	
	public enum Estado{
		NOVO
	}
	
	
	public HinoPlayer(Context context) {
		this.context = context;
	}
	
	/**
	 * Preparar/Criar o hino antes de tocar
	 * @param resource
	 */
	public void preprarHino(int resource){
		this.mHinoPlayer = (HinoPlayer) MediaPlayer.create(context, resource);
	}
	
	/**
	 * Parar o hino que está a tocar
	 */
	public void pararHino(){
		if(this.mHinoPlayer != null){
			mHinoPlayer.stop();
		}
	}
	
	/**
	 * Parar o hino que está a tocar e toca o novo hino
	 * @param playNewHino
	 */
	public void tocarHino(int playNewHino, Estado estado){
		if(this.mHinoPlayer != null && estado == Estado.NOVO){
			this.mHinoPlayer.release();
			this.mHinoPlayer = null;
			this.preprarHino(playNewHino);
			this.tocarHino();
		}
	}
	
	public void tocarHino(){
		if(this.mHinoPlayer != null){
			this.mHinoPlayer.seekTo(0);
			this.mHinoPlayer.start();
			this.isHinoPlaying = true;
		}
	}
	
	public boolean isHinoIsPlaying(){
		if(this.mHinoPlayer != null){
			return isHinoPlaying;
		}
		return false;
	}
	
	

}
