package com.example.jogodeadivinha.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import android.os.Bundle;

import com.example.jogodeadivinha.util.Util;

public class JogoDeAdivinha {

	private int mMin, mMax, mSorteado, mUltimoNumJogado, mTentativas;
	private ArrayList<Integer> mColJogadas;
	private final static int NENHUM = -1;

	private Date mMomentoDeInicio;
	private Date mMomentoFinal;
	
	private enum EstadoDoJogo {
		JOGO_NAO_INICIADO, JOGO_JAGADA_ACIMA, JOGO_JOGADA_ABAIXO, JOGO_JAGADA_CERTA
	}

	private EstadoDoJogo mEstadoDoJogo;
	
	public JogoDeAdivinha() {
		this.mMin = 1;
		this.mMax = 1000;
		this.mSorteado = inteiroAleatorio(this.mMin, this.mMax);
		this.mColJogadas = new ArrayList<Integer>();
		this.mUltimoNumJogado = NENHUM;
		this.mTentativas = 0;
		this.mEstadoDoJogo = EstadoDoJogo.JOGO_NAO_INICIADO;
		this.mMomentoDeInicio = new Date();
		this.mMomentoFinal = null;
	}

	public JogoDeAdivinha(int min, int max) {
		if (min > max) {
			this.mMin = max;
			this.mMax = min;
		} else {
			this.mMin = min;
			this.mMax = max;
		}

		this.mColJogadas = new ArrayList<Integer>();
		this.mSorteado = inteiroAleatorio(this.mMin, this.mMax);
		this.mColJogadas = new ArrayList<Integer>();
		this.mUltimoNumJogado = NENHUM;
		this.mTentativas = 0;
		this.mEstadoDoJogo = EstadoDoJogo.JOGO_NAO_INICIADO;
		this.mMomentoDeInicio = new Date();
		this.mMomentoFinal = null;
	}// JogoDeAdivinha

	public JogoDeAdivinha(Bundle bundle) {
		mMin = bundle.getInt("mMin");
		mMax = bundle.getInt("mMax");
		mSorteado = bundle.getInt("mSorteado");
		mUltimoNumJogado = bundle.getInt("mUltimoNumJogado");
		mTentativas = bundle.getInt("mTentativas");
		mColJogadas = bundle.getIntegerArrayList("mColJogadas");
		this.mEstadoDoJogo = (EstadoDoJogo) bundle.getSerializable("mEstado");
	}

	/*
	 * esta ferramenta nasceu para que fosse poss�vel comunicar o jogo entre
	 * Activity(s) via Intents.putExtra(s)
	 */
	public Bundle estadoDoJogoEnquantoBundle() {
		Bundle ret = new Bundle();

		ret.putInt("mMin", mMin);
		ret.putInt("mMax", mMax);
		ret.putInt("mSorteado", mSorteado);
		ret.putInt("mUltimoNumJogado", mUltimoNumJogado);
		ret.putInt("mTentativas", mTentativas);
		ret.putIntegerArrayList("mColJogadas", mColJogadas);
		ret.putSerializable("mEstado", mEstadoDoJogo);

		return ret;
	}// estadoDoJogoEnquantoBundle

	
	
	public long pontuacao(){
		long pontos = 0;
		if(mEstadoDoJogo == EstadoDoJogo.JOGO_JAGADA_CERTA){
			int amplitude = mMax - mMin;
			long inicio = mMomentoDeInicio.getTime();
			
			if(mMomentoFinal != null){
				long fim = mMomentoFinal.getTime();
				
				long tempoDeJogo = (fim - inicio)/1000;
				
				pontos = (amplitude / (mTentativas + tempoDeJogo))*100;	
			}
			
		}		
		return pontos;
	}
	
	private int inteiroAleatorio(int min, int max) {
		int ret = NENHUM;
		Random random = new Random();
		int amplitude = (max - min) + 1;
		int salto = random.nextInt(amplitude); // produz um inteiro [0, n[
		ret = min + salto;
		return ret;
	}

	public String jogar() {
		String retFeedbackDaJogada = "";
		mTentativas++;
		// O n�mero jogado estar� dispon�vel no memro de dados mUltimoNumJogado

		if (mUltimoNumJogado < mSorteado) {
			retFeedbackDaJogada = Util.MensagemDoJogo.JOGADA_ACIMA;
			this.mEstadoDoJogo = EstadoDoJogo.JOGO_JOGADA_ABAIXO;
		} else if (mUltimoNumJogado > mSorteado) {
			retFeedbackDaJogada = Util.MensagemDoJogo.JOGADA_ABAIXO;
			this.mEstadoDoJogo = EstadoDoJogo.JOGO_JAGADA_ACIMA;
		} else if (mUltimoNumJogado == mSorteado) {
			retFeedbackDaJogada = Util.MensagemDoJogo.JOGADA_CERTA + mTentativas + " tentativas!";
			this.mEstadoDoJogo = EstadoDoJogo.JOGO_JAGADA_CERTA;
		}
		return retFeedbackDaJogada;
	}// jogar

	public int setUltimoNumJogado(int num) {
		mUltimoNumJogado = num;
		return mUltimoNumJogado;
	}
	
	public EstadoDoJogo getEstadoDoJogo() {
		return mEstadoDoJogo;
	}
	
	public boolean isJogoTerminou(){
		return mEstadoDoJogo == EstadoDoJogo.JOGO_JAGADA_CERTA;
	}

	public String estadoDoJogoEnquantoFrase() {
		StringBuilder sb = new StringBuilder("");

		sb.append("mMin= " + mMin).append("\n");
		sb.append("mMax= " + mMax).append("\n");
		sb.append("mSorteado= " + mSorteado).append("\n");
		sb.append("mUltimoNumJogado= ")
				.append((mUltimoNumJogado == NENHUM) ? "Ainda nenhum\n "
						: mUltimoNumJogado)
				.append("\n")
				.append("mColJagadas")
				.append(((mColJogadas.size() == 0) ? "Nenhuma jogada ainda...\n"
						: jogadasParaFrase() + "\n"));
		sb.append("mTentativas= ").append(mTentativas).append("\n");
		sb.append("Pontua��o= ").append(pontuacao());
		return sb.toString();
	}// estadoDoJogoEnquantoFrase

	private String jogadasParaFrase() {
		// consultar mColJogadas e transforma-la numa frase
		StringBuilder sb = new StringBuilder("");
		int enderecoMin = 0;
		int enderecoMax = this.mColJogadas.size();
		int contador = enderecoMin;
		while (contador < enderecoMax) {
			int valorNoEndereco = mColJogadas.get(enderecoMin);
			sb.append("\tmColJogadas[").append(contador).append("]=")
					.append(valorNoEndereco).append("\n");
			contador++;
		}

		return sb.toString();
	}// jogadasParaFrase

}
