package com.example.jogodeadivinha.logic;

import java.util.ArrayList;
import java.util.Random;

public class JogoDeAdivinha {

	private int mMin, mMax, mSorteado, mUltimoNumJogado,mTentativas;	
	private ArrayList<Integer> mColJogadas;
	private final static int NENHUM = -1;
	public enum EstadoDoJogo{
		JOGO_NAO_INICIADO,JOGO_JAGADA_ACIMA,JOGO_JOGADA_ABAIXO,JOGO_JAGADA_CERTA
	}
	
	public JogoDeAdivinha() {
		this.mMin = 1;
		this.mMax = 1000;
		this.mSorteado = inteiroAleatorio(this.mMin, this.mMax);
		this.mColJogadas = new ArrayList<Integer>();
		this.mUltimoNumJogado = NENHUM;
		this.mTentativas = 0;
	}
	
	

	public JogoDeAdivinha(int min, int max) {
		if(min>max){
			this.mMin = max;
			this.mMax = min;
		}else{
			this.mMin = min;
			this.mMax = max;
		}
		
		this.mColJogadas = new ArrayList<Integer>();
	}//JogoDeAdivinha
	
	
	private int inteiroAleatorio(int min, int max) {
		int ret = NENHUM;
		Random random = new Random();
		int amplitude = (max-min)+1;
		int salto = random.nextInt(amplitude); // produz um inteiro [0, n[
		ret = min + salto;
		return ret;
	}
	
	public String jogar(int numero){
		String retFeedbackDaJogada = "";
		
		return retFeedbackDaJogada;
	}//jogar
	
	public String estadoDoJogoEnquantoFrase(){
		StringBuilder sb = new StringBuilder("");
		
		sb.append("mMin= "+mMin).append("\n");
		sb.append("mMax= "+mMax).append("\n");
		sb.append("mSorteado= "+mSorteado).append("\n");
		sb.append("mUltimoNumJogado= ").append(
				((mColJogadas.size()==0)?"Nenhuma jogada ainda...\n":jogadasParaFrase()+"\n")
				);
		sb.append("mTentativas= ").append(mTentativas);
		return sb.toString();
	}//estadoDoJogoEnquantoFrase



	private String jogadasParaFrase() {
		// consultar mColJogadas e transforma-la numa frase
		StringBuilder sb=new StringBuilder("");
		int enderecoMin = 0;
		int enderecoMax = this.mColJogadas.size();
		int contador = enderecoMin;
		while(contador<enderecoMax){
			int valorNoEndereco = mColJogadas.get(enderecoMin);
			sb.append("\nmColJogadas[").append(contador).append("]=").append(valorNoEndereco).append("\n");
			contador++;
		}
		
		return sb.toString();
	}//jogadasParaFrase
	
	
	
}