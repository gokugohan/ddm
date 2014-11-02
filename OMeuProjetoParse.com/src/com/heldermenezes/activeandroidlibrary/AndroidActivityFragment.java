package com.heldermenezes.activeandroidlibrary;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.heldermenezes.omeuprojeto.MainActivity;
import com.heldermenezes.omeuprojeto.R;
import com.special.ResideMenu.ResideMenu;

public class AndroidActivityFragment extends Fragment {

	private ResideMenu resideMenu;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setHasOptionsMenu(true);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.androidactivity_main, container,
				false);

		Button button01 = (Button) view.findViewById(R.id.button01);
		Button button02 = (Button) view.findViewById(R.id.button02);
		Button button03 = (Button) view.findViewById(R.id.button03);

		button01.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				resideMenu.clearIgnoredViewList();
				Person01Fragment personFragment = new Person01Fragment();
				getActivity()
						.getSupportFragmentManager()
						.beginTransaction()
						.replace(R.id.androidactivityfragment, personFragment,
								"person01Fragment")
						.setTransitionStyle(
								FragmentTransaction.TRANSIT_FRAGMENT_FADE)
						.commit();
			}
		});

		button02.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				resideMenu.clearIgnoredViewList();
				Person02Fragment personFragment = new Person02Fragment();
				getActivity()
						.getSupportFragmentManager()
						.beginTransaction()
						.replace(R.id.androidactivityfragment, personFragment,
								"person02Fragment")
						.setTransitionStyle(
								FragmentTransaction.TRANSIT_FRAGMENT_FADE)
						.commit();
			}
		});
		setUpViews();
		resideMenu.addIgnoredView(container);
		return view;
	}

	private void setUpViews() {
		MainActivity parentActivity = (MainActivity) getActivity();
		resideMenu = parentActivity.getResideMenu();
	}
}
