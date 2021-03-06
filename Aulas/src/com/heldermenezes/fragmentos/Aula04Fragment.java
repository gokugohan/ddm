package com.heldermenezes.fragmentos;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;

import com.heldermenezes.main.R;
import com.heldermenezes.utils.AmUtil;

public class Aula04Fragment extends Fragment {

	private View nw, ne, sw, se;

	private OnLongClickListener mOnLongClickListener;
	private OnClickListener mOnClickListener;
	private Context mContext;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater
				.inflate(R.layout.fragment_aula04, container, false);

		this.mContext = getActivity();
		
		initFindComponent(view);

		initListenersForViews();

		this.nw.setOnLongClickListener(mOnLongClickListener);
		this.ne.setOnLongClickListener(mOnLongClickListener);
		this.sw.setOnLongClickListener(mOnLongClickListener);
		this.se.setOnLongClickListener(mOnLongClickListener);

		this.nw.setOnClickListener(mOnClickListener);
		this.ne.setOnClickListener(mOnClickListener);
		this.sw.setOnClickListener(mOnClickListener);
		this.se.setOnClickListener(mOnClickListener);

		return view;

	}// onCreateView

	private void initListenersForViews() {

		this.mOnLongClickListener = new OnLongClickListener() {

			@Override
			public boolean onLongClick(View v) {
				fazerAparecerMenuComCoresParaEscolher(v);
				return true;
			}// onLongClick
		};// OnLongClickListener

		this.mOnClickListener = new OnClickListener() {

			@Override
			public void onClick(View v) {
				clickViewReact(v.getId(), "Click");
			}
		};
	}// initListenersForViews

	protected int fazerAparecerMenuComCoresParaEscolher(final View viewClickada) {
		int ret = 0;
		AlertDialog.Builder mAlertDialog = new AlertDialog.Builder(
				mContext);
		mAlertDialog.setTitle(getString(R.string.str_dialog_title));
		mAlertDialog.setIcon(R.drawable.ic_launcher);
		mAlertDialog.setItems(R.array.arr_color_options, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				mudarACorDeFundoNaViewClickada(viewClickada,which);
			}
		});
		
		
		mAlertDialog.setNegativeButton(getString(R.string.str_cancel_button), new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		mAlertDialog.show();
		
		return ret;
	}// fazerAparecerMenuComCoresParaEscolher

	protected void mudarACorDeFundoNaViewClickada(View view, int which) {
		int cor;
		switch(which){
		case 0:
			cor = getResources().getColor(R.color.color_black);
			break;
		case 1:
			cor = getResources().getColor(R.color.color_blue);
			break;
		case 2:
			cor = getResources().getColor(R.color.color_green);
			break;
		case 3:
			cor = getResources().getColor(R.color.color_magenta);
			break;
		case 4:
			cor = getResources().getColor(R.color.color_red);
			break;
		case 5:
			cor = getResources().getColor(R.color.color_white);
			break;
		case 6:
			cor = getResources().getColor(R.color.color_yellow);
			break;
		default:
			cor = getResources().getColor(R.color.color_unknown);
		}
		
		view.setBackgroundColor(cor);
	}

	protected void clickViewReact(int id, String clickType) {
		String msg = "";
		switch (id) {
		case R.id.id_nw:
			msg = getString(R.string.str_nw);
			break;
		case R.id.id_ne:
			msg = getString(R.string.str_ne);
			break;
		case R.id.id_sw:
			msg = getString(R.string.str_sw);
			break;
		case R.id.id_se:
			msg = getString(R.string.str_se);
			break;
		}

		AmUtil.fazerAparecerUmaBreveMensagem("Event " + clickType + ": " + msg,
				mContext);
	}

	private void initFindComponent(View view) {
		nw = (View) view.findViewById(R.id.id_nw);
		ne = (View) view.findViewById(R.id.id_ne);
		sw = (View) view.findViewById(R.id.id_sw);
		se = (View) view.findViewById(R.id.id_se);
	}

}
