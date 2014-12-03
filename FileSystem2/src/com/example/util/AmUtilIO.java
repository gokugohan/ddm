package com.example.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Random;

import android.R.bool;
import android.content.Context;
import android.os.Environment;
import android.util.Log;

public class AmUtilIO {

	private static final String MUDANCA_DE_LINHA = "\n";
	private static final String MUDANCA_DE_PALAVRA = " ";
	private static final String TAG_IO = "IO";
	private int mMinLinhasPorFicheiro = 5;
	private int mMaxLinhasPorFicheiro = 10;
	private int mMinPalavrasPorLinha = 1;
	private int mMaxPalavraPorLinha = 5;
	private Context context;

	public AmUtilIO(Context context) {
		this.context = context;
	}

	public String io_aux_RandomText(String nomeFicheiro, String... conteudo) {
		StringBuilder ret = new StringBuilder();
		int quantidadeDeLinhas = aux_InteiroAleatorio(mMinLinhasPorFicheiro,
				mMaxLinhasPorFicheiro);
		int quantidadeDePalavrasNaLinha = 0;

		for (int linha = 1; linha <= quantidadeDeLinhas; linha++) {
			quantidadeDePalavrasNaLinha = aux_InteiroAleatorio(
					mMinPalavrasPorLinha, mMaxPalavraPorLinha);
			for (int palavra = 1; palavra <= quantidadeDePalavrasNaLinha; palavra++) {
				String palavraAleatorio = io_aux_RandomSstring();
				ret.append(palavraAleatorio);
				ret.append(MUDANCA_DE_PALAVRA);

			}
		}

		ret.append(MUDANCA_DE_LINHA);
		return ret.toString();
	}// io_aux_RandomText

	/**
	 * Retorna inteiro aleat�rio entre o m�nimo e o m�ximo
	 * 
	 * @param min
	 * @param max
	 * @return inteiro aleat�rio entre o min e o max
	 */
	private int aux_InteiroAleatorio(int min, int max) {
		int ret = 0;
		int amplitude = (max - min) + 1;
		Random r = new Random();
		int salto = r.nextInt(amplitude);
		int destino = min + salto;
		ret = destino;

		return ret;
	}

	private String io_aux_RandomSstring() {
		StringBuilder ret = new StringBuilder();
		int tamanhoDoString = aux_InteiroAleatorio(1, 8);
		for (int simbolo = 1; simbolo <= tamanhoDoString; simbolo++) {
			int tipoDeSimbolo = aux_InteiroAleatorio(0, 3);// tipos de simbolo
			char[] conjuntosDeSimbolosDePontucao = new char[5];// cinco sinais
																// de pontua��o
			conjuntosDeSimbolosDePontucao[0] = ',';
			conjuntosDeSimbolosDePontucao[1] = ';';
			conjuntosDeSimbolosDePontucao[2] = '!';
			conjuntosDeSimbolosDePontucao[3] = '.';
			conjuntosDeSimbolosDePontucao[4] = '_';

			char auxSimbolo = ' ';
			switch (tipoDeSimbolo) {
			case 0:// letra pequan (de entre 26 poss�veis
				auxSimbolo = (char) ('a' + aux_InteiroAleatorio(0, 25));
				break;
			case 1:
				auxSimbolo = (char) ('A' + aux_InteiroAleatorio(0, 26));
				break;
			case 2:
				auxSimbolo = (char) ('0' + aux_InteiroAleatorio(0, 9));
				break;
			case 3:
				auxSimbolo = conjuntosDeSimbolosDePontucao[aux_InteiroAleatorio(
						0, 4)];
				break;
			}// switch
			ret.append(auxSimbolo);
		}
		return ret.toString();
	}

	public String io_aux_TextoAleatorio(String nomeFicheiro) {
		return io_aux_RandomText(nomeFicheiro);
	}// io_aux_TextoAleatorio

	/**
	 * M�todo que escrever� o conte�do recebido em ficheiro de nome nomeFicheiro
	 * que ficar� na diretoria dir, desde que dentro da internal storage
	 * 
	 * @param context
	 *            o contexto do android
	 * @param dir
	 *            diretoria onde vai ser gravado o ficheiro em quest�o
	 * @param nomeFicheiro
	 *            o nome do ficheiro
	 * @param conteudo
	 *            o conteudo a ser gravado no ficheiro
	 * @return
	 */
	public String io_WriteFileToInternalStorage(String nomeFicheiro,
			String... conteudo) {
		StringBuilder ret = new StringBuilder();

		File mFilsDir = context.getFilesDir();
		if (mFilsDir != null) {
			try {
				// explora m�todos espec�ficos em Android para a internal
				// storage
				FileOutputStream fos = context.openFileOutput(nomeFicheiro,
						Context.MODE_PRIVATE);

				// File ficheiro = new
				// File(mFilsDir.getAbsolutePath(),nomeFicheiro);
				// FileOutputStream fos2 = new FileOutputStream(ficheiro);

				for (String parte : conteudo) {
					fos.write(parte.getBytes());
				}
				fos.close();
				ret.append(mFilsDir.getAbsolutePath()).append("/")
						.append(nomeFicheiro);
			} catch (FileNotFoundException e) {
				Log.i(TAG_IO, e.getMessage());
			} catch (IOException e) {
				e.printStackTrace();
				Log.i(TAG_IO, e.getMessage());
			}

		}
		return ret.toString();
	}

	/**
	 * M�todo que escrever� o conte�do recebido em ficheiro de nome nomeFicheiro
	 * que ficar� na diretoria dir, desde que dentro da internal storage
	 * 
	 * @param context
	 *            o contexto do android
	 * @param dir
	 *            diretoria onde vai ser gravado o ficheiro em quest�o
	 * @param nomeFicheiro
	 *            o nome do ficheiro
	 * @param conteudo
	 *            o conteudo a ser gravado no ficheiro
	 * @return
	 */
	public String io_WriteFileToInternalStorage(String diretorio,
			String nomeFicheiro, String... conteudo) {

		StringBuilder ret = new StringBuilder();

		File rootInternalStorage = context.getFilesDir();
		if (rootInternalStorage != null) {
			File dir = new File(diretorio);
			boolean dirJaExistia = dir.mkdir();
			boolean sucessoNaCriacao = dir.mkdirs();

			if (sucessoNaCriacao || dirJaExistia) {
				String caminho = rootInternalStorage.getAbsolutePath() + "/"
						+ dir;
				File ficheiro = new File(caminho, nomeFicheiro);
				if (ficheiro != null) {
					FileOutputStream fos;
					try {
						fos = new FileOutputStream(ficheiro);
						if (fos != null) {
							for (String parte : conteudo) {
								fos.write(parte.getBytes());
							}
							fos.close();
						}
						ret.append(caminho).append("/").append(nomeFicheiro);
					} catch (FileNotFoundException e) {
						e.printStackTrace();
						Log.i(TAG_IO, e.getMessage());
					} catch (IOException e) {
						e.printStackTrace();
						Log.i(TAG_IO, e.getMessage());
					}

				} else {
					// n�o foi poss�vel ter a dir pedida
				}
			} else {
				// n�o foi sequer poss�vel ter um objeto para pedir a dir na
				// localiza��o pedida
			}
		} else {
			// n�o temos objeto que represente a raiz da internal storage
		}

		return ret.toString();
	}

	// ///////FERAMENTAS DE IO NA EXTERNAL STORAGE ////////////////////

	public boolean is_es_SDWriteable() {
		boolean ret = false;
		String estadoAtual = io_es_GetState();
		ret = estadoAtual.equals(Environment.MEDIA_MOUNTED);
		return ret;
	}

	public boolean is_es_SDReadOnly() {
		boolean ret = false;
		String estadoAtual = io_es_GetState();
		ret = estadoAtual.equals(Environment.MEDIA_MOUNTED_READ_ONLY);
		return ret;
	}

	public boolean io_es_IsSdUsable() {
		boolean bWriteable = is_es_SDWriteable();
		boolean bReadOnly = is_es_SDReadOnly();
		boolean ret = bWriteable || bReadOnly;
		return ret;
	}

	public String io_es_GetState() {
		StringBuilder sb = new StringBuilder();

		sb.append(Environment.getExternalStorageState());

		return sb.toString();
	}

	public String io_es_GetAbsolutPath() {
		StringBuilder sb = new StringBuilder();

		File rootDaEs = Environment.getExternalStorageDirectory();
		sb.append(rootDaEs.getAbsolutePath());
		return sb.toString();
	}

	public String io_es_WriteFileToRoot(String nomeFicheiro, String... conteudo) {
		StringBuilder sb = new StringBuilder();
		boolean bPossivelEscrever = is_es_SDWriteable();
		if (bPossivelEscrever) {
			String ondeEscrever = io_es_GetAbsolutPath();
			File ficheiro = new File(ondeEscrever, nomeFicheiro);
			if (ficheiro != null) {
				try {
					FileOutputStream fos = new FileOutputStream(ficheiro);
					for (String parte : conteudo) {
						fos.write(parte.getBytes());
					}
					fos.close();
					sb.append(ondeEscrever).append("/").append(nomeFicheiro);
				} catch (FileNotFoundException e) {
					Log.i(TAG_IO, e.getMessage());
				} catch (IOException e) {
					e.printStackTrace();
					Log.i(TAG_IO, e.getMessage());
				}
			}
		}
		return sb.toString();
	}

	/**
	 * Receber o nome da diretoria onde (dentro da e.s) o ficheiro deve existir
	 * ex� "Docs/localicoes/portugal/" Recebe o nome do ficheiro ex�
	 * "santarem.txt" retorna o caminho absoluto do ficheiro eventualmente
	 * criado
	 * 
	 * @param nomeDir
	 * @param nomeFicheiro
	 * @param conteudo
	 * @return
	 */

	public String io_es_WriteFileToDir(String nomeDir, String nomeFicheiro,
			String... conteudo) {
		StringBuilder sb = new StringBuilder();
		boolean bPossivelEscrever = is_es_SDWriteable();
		if (bPossivelEscrever) {

			String rootDaES = io_es_GetAbsolutPath();
			String caminhoDesejado = rootDaES + "/" + nomeDir;
			File novaDir = new File(caminhoDesejado);
			boolean jaExisteNovaDir = novaDir.isDirectory();
			boolean sucessoNaCriacao = false;
			if (!jaExisteNovaDir) {
				sucessoNaCriacao = novaDir.mkdir();
			}
			if (jaExisteNovaDir || sucessoNaCriacao) {
				File novoFicheiro = new File(caminhoDesejado, nomeFicheiro);
				try {
					FileOutputStream fos = new FileOutputStream(novoFicheiro);
					for (String coisa : conteudo) {
						fos.write(coisa.getBytes());
					}
					fos.close();
					sb.append(caminhoDesejado).append("/").append(nomeFicheiro);
				} catch (Exception e) {
					Log.i(TAG_IO, e.getMessage());
				}
			}
		}
		return sb.toString();
	}

	/**
	 * Recebe o nome ficheiro a ler, assumido presente na root da e.s Retorna
	 * todo o conte�do do ficheiro ou uma string vazia em caso de insucesso
	 * 
	 * @param ficheiro
	 * @return
	 */
	public String io_es_ReadContentFileFromRoot(String nomeFicheiro) {
		StringBuilder sb = new StringBuilder();

		boolean bPodemosLer = io_es_IsSdUsable();
		if (bPodemosLer) {
			String rootDaEs = io_es_GetAbsolutPath();
			File ficheiro = new File(rootDaEs, nomeFicheiro);
			boolean bFicheiroExiste = ficheiro.isFile();
			if (bFicheiroExiste) {
				try {
					FileInputStream fis = new FileInputStream(ficheiro);
					int iC = 0;
					char c;
					if (fis != null) {
						while ((iC = fis.read()) != -1) {

							c = (char) iC;
							sb.append(c);
						}
						fis.close();
						return sb.toString();
					}
				} catch (Exception e) {
					Log.i(TAG_IO, e.getMessage());
				}
			}
		}

		return sb.toString();
	}
}
