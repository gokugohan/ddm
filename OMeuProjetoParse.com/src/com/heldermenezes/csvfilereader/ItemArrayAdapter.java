package com.heldermenezes.csvfilereader;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.heldermenezes.omeuprojeto.R;

public class ItemArrayAdapter extends ArrayAdapter<String[]>{

	private List<String[]> countryList = new ArrayList<String[]>();
	
	static class ItemViewHolder {
		TextView number;
		TextView countryname;
		TextView capital;
	}
	
	public ItemArrayAdapter(Context context, int resource) {
		super(context, resource);
	}

	@Override
	public void add(String[] object) {
		countryList.add(object);
		super.add(object);
	}
	
	@Override
	public int getCount() {
		return this.countryList.size();
	}
	
	@Override
	public String[] getItem(int position) {
		return this.countryList.get(position);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		ItemViewHolder viewHolder;
		
		if(row == null){
			LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			row = inflater.inflate(R.layout.csv_single_list_item, parent, false);
			viewHolder = new ItemViewHolder();
			viewHolder.number = (TextView) row.findViewById(R.id.number);
			viewHolder.countryname = (TextView) row.findViewById(R.id.country);
			viewHolder.capital = (TextView) row.findViewById(R.id.capital);
			row.setTag(viewHolder);
		} else {
			viewHolder = (ItemViewHolder) row.getTag();
		}
		
		String[] stat = getItem(position);
		
		
		viewHolder.number.setText(removePlicas(stat[0]));
		viewHolder.countryname.setText(removePlicas(stat[1]));
		
		
		viewHolder.capital.setText((stat[3].trim().length()==0)?"Undefined":removePlicas(stat[3]));
		
		return row;
	}
	
	// Remove plicas no inícion e no fim do texto
	private String removePlicas(String text){
		if(text.isEmpty() || text.trim().length()==0){
			return "";
		}
		StringBuilder temp = new StringBuilder(text);
		text = temp.deleteCharAt(0).toString();
		temp.deleteCharAt(text.length()-1);
		return temp.toString();
	}
	
}
