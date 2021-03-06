package com.heldermenezes.omeuprojeto;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.heldermenezes.activeandroidlibrary.AndroidActivityFragment;
import com.heldermenezes.csvfilereader.ReadCSVFragment;
import com.heldermenezes.post.PostListFragment;
import com.parse.ParseAnonymousUtils;
import com.parse.ParseUser;
import com.special.ResideMenu.ResideMenu;
import com.special.ResideMenu.ResideMenuItem;

public class MainActivity extends FragmentActivity implements OnClickListener {

	private ResideMenu mResideMenu;
	private ResideMenuItem itemHome;
	private ResideMenuItem itemPostList;
	private ResideMenuItem itemReadCSV;
	private ResideMenuItem androidSQLLib;
	private ResideMenuItem prefScreen;

	private Button logoutButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		// getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
		// WindowManager.LayoutParams.FLAG_FULLSCREEN);

		getActionBar().setHomeButtonEnabled(true);
		if (ParseAnonymousUtils.isLinked(ParseUser.getCurrentUser())) {

			Intent intent = new Intent(this, MainLoginSignUpActivity.class);
			startActivity(intent);
			finish();
		} else {
			ParseUser currentUser = ParseUser.getCurrentUser();

			if (currentUser != null) {
				setContentView(R.layout.main);
				// logoutButton = (Button)findViewById(R.id.logout);
				// logoutButton.append(" " +currentUser.getUsername());
				// logoutButton.setOnClickListener(new OnClickListener() {
				//
				// @Override
				// public void onClick(View v) {
				// ParseUser.logOut();
				// startActivity(new
				// Intent(MainActivity.this,MainLoginSignUpActivity.class));
				// finish();
				// }
				// });

				setUpMenu();
				changeFragment(new HomeFragment(this));
			} else {
				Intent intent = new Intent(this, MainLoginSignUpActivity.class);
				startActivity(intent);
				finish();
			}
		}

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	private void setUpMenu() {
		// attach to current activity;
		mResideMenu = new ResideMenu(MainActivity.this);
		// set background of menu

		mResideMenu.setBackground(R.drawable.menu_background);
		mResideMenu.attachToActivity(MainActivity.this);
		mResideMenu.setMenuListener(menuListener);
		// valid scale factor is between 0.0f and 1.0f. leftmenu'width is
		// 150dip.
		mResideMenu.setScaleValue(0.5f);

		// create menu items;
		itemHome = new ResideMenuItem(this, R.drawable.ic_launcher, "Home");
		itemPostList = new ResideMenuItem(this, android.R.drawable.ic_menu_add,
				"PostList");
		itemReadCSV = new ResideMenuItem(this, R.drawable.ic_launcher,
				"Read CSV file");
		androidSQLLib = new ResideMenuItem(this, R.drawable.ic_launcher,
				"AndroidSQLLib");
		prefScreen = new ResideMenuItem(this, R.drawable.ic_launcher,
				"Pref-Screen");

		itemHome.setOnClickListener(this);
		itemPostList.setOnClickListener(this);
		itemReadCSV.setOnClickListener(this);
		androidSQLLib.setOnClickListener(this);
		prefScreen.setOnClickListener(this);

		mResideMenu.addMenuItem(itemHome, ResideMenu.DIRECTION_LEFT);
		mResideMenu.addMenuItem(itemPostList, ResideMenu.DIRECTION_LEFT);
		mResideMenu.addMenuItem(itemReadCSV, ResideMenu.DIRECTION_LEFT);
		mResideMenu.addMenuItem(androidSQLLib, ResideMenu.DIRECTION_LEFT);
		mResideMenu.addMenuItem(prefScreen, ResideMenu.DIRECTION_RIGHT);

		// You can disable a direction by setting ->
		// resideMenu.setSwipeDirectionDisable(ResideMenu.DIRECTION_RIGHT);

		// findViewById(R.id.title_bar_left_menu).setOnClickListener(
		// new View.OnClickListener() {
		// @Override
		// public void onClick(View view) {
		// mResideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
		// }
		// });
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		return mResideMenu.dispatchTouchEvent(ev);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.

		getMenuInflater().inflate(R.menu.main, menu);
		
		ParseUser currentUser = ParseUser.getCurrentUser();
		if (currentUser != null) {
			MenuItem item = menu.findItem(R.id.action_logout);
			item.setTitle("Logout " + currentUser.getUsername() );
		}

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		switch (id) {
		case R.id.action_logout:
			ParseUser.logOut();
			startActivity(new Intent(MainActivity.this,
					MainLoginSignUpActivity.class));
			finish();
			break;
		case android.R.id.home:
			mResideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
			break;
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View view) {
		if (view == itemHome) {
			changeFragment(new HomeFragment(this));
		} else if (view == itemPostList) {
			changeFragment(new PostListFragment());
		} else if (view == itemReadCSV) {
			changeFragment(new ReadCSVFragment(this));
		} else if (view == androidSQLLib) {
			changeFragment(new AndroidActivityFragment());
		} else if (view == prefScreen) {
			this.startActivity(new Intent(this, PrefActivity.class));
		}

		mResideMenu.closeMenu();
	}

	public void replaceFragment(Fragment fragment) {
		changeFragment(fragment);
	}

	private ResideMenu.OnMenuListener menuListener = new ResideMenu.OnMenuListener() {
		@Override
		public void openMenu() {
			// Toast.makeText(MainActivity.this, "Menu is opened!",
			// Toast.LENGTH_SHORT).show();
		}

		@Override
		public void closeMenu() {
			// Toast.makeText(MainActivity.this, "Menu is closed!",
			// Toast.LENGTH_SHORT).show();
		}
	};

	private void changeFragment(Fragment targetFragment) {
		mResideMenu.clearIgnoredViewList();
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.main_fragment, targetFragment, "fragment")
				.setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
				.commit();
	}

	// What good method is to access resideMenu？
	public ResideMenu getResideMenu() {
		return mResideMenu;
	}
}
