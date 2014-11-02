package com.heldermenezes.activeandroidlibrary;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.activeandroid.query.Select;
import com.heldermenezes.omeuprojeto.MainActivity;
import com.heldermenezes.omeuprojeto.R;
import com.parse.ParseAnalytics;
import com.special.ResideMenu.ResideMenu;

public class Person01Fragment extends Fragment {
	
	private ResideMenu resideMenu;
	private EditText personNameEt,personAgeEt;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setHasOptionsMenu(true);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		ParseAnalytics.trackAppOpened(getActivity().getIntent());
		View view = inflater.inflate(R.layout.androidactivity_person01, container,
				false);

		personNameEt = (EditText) view.findViewById(R.id.person_name);
		personAgeEt =  (EditText) view.findViewById(R.id.person_age);
		
		Button save = (Button)view.findViewById(R.id.save);
		Button showAll = (Button)view.findViewById(R.id.show_all);
		
		save.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String name = (personNameEt.getText().toString().trim().length()>0?personNameEt.getText().toString().trim():"No Name");
				int age = Integer.parseInt(personAgeEt.getText().toString())>0?Integer.parseInt(personAgeEt.getText().toString()):0;
				
				personNameEt.setText("");
				personAgeEt.setText("");
				Person01 person = new Person01(name,age);
				person.save();
			}
		});
		showAll.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Select select = new Select();
				
				ArrayList<Person01> persons = select.all().from(Person01.class).execute();
				
				StringBuilder sb = new StringBuilder();
				for (Person01 person : persons){
					sb.append("Name: ").append(person.getPersonName()).append(" Age: ").append(person.getPersonAge()).append("\n");
				}
				Toast.makeText(getActivity(), sb.toString(), Toast.LENGTH_LONG).show();
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
