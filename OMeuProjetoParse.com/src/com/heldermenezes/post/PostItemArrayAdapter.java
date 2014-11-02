package com.heldermenezes.post;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.heldermenezes.omeuprojeto.R;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseImageView;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;

public class PostItemArrayAdapter extends ParseQueryAdapter<Post>{
	private Context context;
	public PostItemArrayAdapter(Context context) {
		super(context, new ParseQueryAdapter.QueryFactory<Post>() {

			@Override
			public ParseQuery<Post> create() {
				
				ParseQuery<Post> query = new ParseQuery<Post>("Post");
				query.orderByAscending("updatedAt");
				return query;
			}
		});
		this.context = context;
	}
	
	@Override
	public View getItemView(Post post, View v, ViewGroup parent) {
		View row = v;
		PostItemViewHolder viewHolder;
		if(row == null){
			LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			row = inflater.inflate(R.layout.post_list_item, parent, false);
			viewHolder = new PostItemViewHolder();
			viewHolder.title = (TextView)row.findViewById(R.id.post_title);
			viewHolder.image = (ParseImageView)row.findViewById(R.id.post_title_image);
			row.setTag(viewHolder);
		}else{
			viewHolder = (PostItemViewHolder) row.getTag();
		}
		
		super.getItemView(post, v, parent);
		
		ParseFile photoFile = post.getPhotoFile();
		viewHolder.title.setText(post.getTitle());
		if (photoFile != null) {
			viewHolder.image.setParseFile(photoFile);
			viewHolder.image.loadInBackground(new GetDataCallback() {
				@Override
				public void done(byte[] data, ParseException e) {
					// nothing to do
				}
			});
		}
		
		return row;
	}
	
	
	static class PostItemViewHolder {
		TextView title;
		ParseImageView image;
	}
	
	
	
	
/*
	private List<Post> posts = new ArrayList<Post>();
	private Context context;
	
	static class PostItemViewHolder {
		TextView title;
		ParseImageView image;
	}
	
	public PostItemArrayAdapter(Context context, List<Post> posts) {
		this.posts = posts;
		this.context = context;
	}
	@Override
	public int getCount() {
		return this.posts.size();
	}
	
	@Override
	public Post getItem(int position) {
		return this.posts.get(position);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		PostItemViewHolder viewHolder;
		if(row == null){
			LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			row = inflater.inflate(R.layout.post_list_item, parent, false);
			
			viewHolder = new PostItemViewHolder();
			
			viewHolder.title = (TextView) row.findViewById(R.id.post_title);
			viewHolder.image = (ParseImageView) row.findViewById(R.id.post_title_image);
			row.setTag(viewHolder);
		} else {
			viewHolder = (PostItemViewHolder) row.getTag();
		}
		
		Post post = getItem(position);
		viewHolder.title.setText(post.getTitle());
		viewHolder.image.setImageBitmap(post.getFile());
		
		return row;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}*/
}
