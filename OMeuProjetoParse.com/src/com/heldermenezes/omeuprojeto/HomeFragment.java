package com.heldermenezes.omeuprojeto;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.special.ResideMenu.ResideMenu;

public class HomeFragment extends Fragment {
	private View parentView;
	private ResideMenu resideMenu;

	private Context mContext;
	public HomeFragment(Context contex) {
		this.mContext = contex;
	}
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		parentView = inflater.inflate(R.layout.home, container, false);
		setUpViews();
		
		return parentView;
	}

	private void setUpViews() {
		MainActivity parentActivity = (MainActivity) getActivity();
		resideMenu = parentActivity.getResideMenu();

		// add gesture operation's ignored views
		FrameLayout ignored_view = (FrameLayout) parentView
				.findViewById(R.id.ignored_view);
		resideMenu.addIgnoredView(ignored_view);
	}
}
