package com.heldermenezes.csvfilereader;

import java.io.InputStream;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.heldermenezes.omeuprojeto.MainActivity;
import com.heldermenezes.omeuprojeto.R;
import com.special.ResideMenu.ResideMenu;

public class ReadCSVFragment extends Fragment implements OnItemClickListener {
	private View parentView;
	private ResideMenu resideMenu;

	
	private ListView listView;
	private ItemArrayAdapter itemArrayAdapter;

	public ReadCSVFragment(Context contex) {
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		parentView = inflater.inflate(R.layout.csv_main, container, false);
		setUpViews();
		listView = (ListView) parentView.findViewById(R.id.list_view);
		itemArrayAdapter = new ItemArrayAdapter(getActivity(),R.layout.csv_single_list_item);

		Parcelable state = listView.onSaveInstanceState();
		listView.setAdapter(itemArrayAdapter);
		listView.onRestoreInstanceState(state);
		listView.setOnItemClickListener(this);
		
		InputStream inputStream = getResources().openRawResource(
				R.raw.countrylist);
		CSVReader csv = new CSVReader(inputStream);
		List<String[]> scoreList = csv.read();

		for (String[] scoreData : scoreList) {
			itemArrayAdapter.add(scoreData);
		}
		
		resideMenu.addIgnoredView(container);
		
		return parentView;
	}

	private void setUpViews() {
		MainActivity parentActivity = (MainActivity) getActivity();
		resideMenu = parentActivity.getResideMenu();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		
		Bundle bundle = new Bundle();
		
		TextView number = (TextView) view.findViewById(R.id.number);
		TextView country = (TextView) view.findViewById(R.id.country);
		TextView capital = (TextView) view.findViewById(R.id.capital);		
		
		bundle.putInt("number", Integer.parseInt(number.getText().toString()));
		bundle.putString("country", country.getText().toString());
		bundle.putString("capital", capital.getText().toString());
		
		
		CountryDetailDialogFragment detail = new CountryDetailDialogFragment();
		detail.setArguments(bundle);
		detail.show(getFragmentManager(), "DialogFragment");
		
	}
}
