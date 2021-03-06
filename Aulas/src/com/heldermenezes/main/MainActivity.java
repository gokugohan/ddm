package com.heldermenezes.main;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.heldermenezes.fragmentos.Aula01Fragment;
import com.heldermenezes.fragmentos.Aula02Fragment;
import com.heldermenezes.fragmentos.Aula03Fragment;
import com.heldermenezes.fragmentos.Aula04Fragment;
import com.heldermenezes.fragmentos.Aula05Fragment;


public class MainActivity extends Activity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    private NavigationDrawerFragment mNavigationDrawerFragment;
    private CharSequence mTitle;

    private Fragment savedFragment, activedFragment;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
    	// update the main content by replacing fragments
        FragmentManager fragmentManager = getFragmentManager();
        Fragment fragment = null;
    	switch(position){
    	case 0:
    		fragment = new Aula01Fragment();
    		break;
    	case 1:
    		fragment = new Aula02Fragment();
    		break;
    	case 2:
    		fragment = new Aula03Fragment();
    		break;
    	case 3:
    		fragment = new Aula04Fragment();
    		break;
    	case 4:
    		fragment = new Aula05Fragment();
    		break;
    	default:
    		fragment = new Aula01Fragment();
    		break;
    	}
        
        fragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }

    
    public void clickMe01(View v){
		Toast.makeText(this,"Clicked (XML OnClick)",Toast.LENGTH_SHORT).show();
	}
    
    private void saveFragmentInstance(Fragment fragmentToSave){
    	this.savedFragment = fragmentToSave;
    }
    
    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.aula01);
                break;
            case 2:
                mTitle = getString(R.string.aula02);
                break;
            case 3:
                mTitle = getString(R.string.Aula03);
                break;
            case 4:
                mTitle = getString(R.string.Aula04);
                break;
            case 5:
                mTitle = getString(R.string.Aula05);
                break;
            case 6:
                mTitle = getString(R.string.Aula06);
                break;
            case 7:
                mTitle = getString(R.string.Aula07);
                break;
            case 8:
                mTitle = getString(R.string.Aula08);
                break;
            case 9:
                mTitle = getString(R.string.Aula09);
                break;
            case 10:
                mTitle = getString(R.string.Aula10);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }

//
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
