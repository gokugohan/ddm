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

public class Person02Fragment extends Fragment {

	private ResideMenu resideMenu;
	private EditText name, age, score_physics, score_chemistry, score_maths,
			score_biology;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setHasOptionsMenu(true);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		ParseAnalytics.trackAppOpened(getActivity().getIntent());
		View view = inflater.inflate(R.layout.androidactivity_person02,
				container, false);

		name = (EditText) view.findViewById(R.id.person02_name);
		age = (EditText) view.findViewById(R.id.person02_age);

		score_physics = (EditText) view.findViewById(R.id.score_physics);
		score_chemistry = (EditText) view.findViewById(R.id.score_chemistry);
		score_maths = (EditText) view.findViewById(R.id.score_maths);
		score_biology = (EditText) view.findViewById(R.id.score_biology);

		Button save = (Button) view.findViewById(R.id.btnsave);
		Button showAll = (Button) view.findViewById(R.id.btnshow_all);
		
		
		
		save.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				Score score = new Score(Integer.parseInt(score_physics
						.getText().toString()), Integer
						.parseInt(score_chemistry.getText().toString()),
						Integer.parseInt(score_maths.getText().toString()),
						Integer.parseInt(score_biology.getText().toString()));

				score.save();
				
				String nName = (name.getText().toString().trim().length() > 0 ? name
						.getText().toString().trim()
						: "No Name");
				int nAge = Integer.parseInt(age.getText().toString()) > 0 ? Integer
						.parseInt(age.getText().toString()) : 0;

				clearEditText();
				
				Person02 person = new Person02(nName, nAge, score);
				person.save();
			}
		});
		
		showAll.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Select select = new Select();

				ArrayList<Person02> persons = select.all().from(Person02.class)
						.execute();

				StringBuilder sb = new StringBuilder();
				for (Person02 person : persons) {
					sb.append("Name: ").append(person.getPersonName())
							.append(" Age: ").append(person.getPersonAge()).append(" Score: ").append(person.getPersonScore())
							.append("\n");
				}
				Toast.makeText(getActivity(), sb.toString(), Toast.LENGTH_LONG)
						.show();
			}
		});

		setUpViews();
		resideMenu.addIgnoredView(container);
		return view;
	}

	private void clearEditText() {
		name.setText("");
		age.setText("");
		score_biology.setText("");
		score_chemistry.setText("");
		score_maths.setText("");
		score_physics.setText("");
	}

	private void setUpViews() {
		MainActivity parentActivity = (MainActivity) getActivity();
		resideMenu = parentActivity.getResideMenu();
	}

}
