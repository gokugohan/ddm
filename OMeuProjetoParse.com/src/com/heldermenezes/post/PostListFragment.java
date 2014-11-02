package com.heldermenezes.post;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.heldermenezes.omeuprojeto.MainActivity;
import com.heldermenezes.omeuprojeto.R;
import com.parse.ParseAnalytics;
import com.parse.ParseQueryAdapter;
import com.special.ResideMenu.ResideMenu;

public class PostListFragment extends Fragment {

	private ArrayList<Post> _posts;
	private ResideMenu resideMenu;
	private PostItemArrayAdapter mPostItemArrayAdapter;
	private ListView listView;

	private ParseQueryAdapter<Post> mainPostAdapter;
	private PostItemArrayAdapter postArrayAdapter;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setHasOptionsMenu(true);

		mainPostAdapter = new ParseQueryAdapter<Post>(getActivity(),Post.class);
		mainPostAdapter.setTextKey("textContent");
		mainPostAdapter.setImageKey("photo");
		
		postArrayAdapter = new PostItemArrayAdapter(getActivity());
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		ParseAnalytics.trackAppOpened(getActivity().getIntent());
		View view = inflater.inflate(R.layout.post_list_fragment, container,
				false);

		listView = (ListView) view.findViewById(R.id.post_list_view);
		_posts = new ArrayList<Post>();

		//updatePostList();
		setListAdapter();
		setUpViews();
		resideMenu.addIgnoredView(container);
		return view;
	}

	private void setUpViews() {
		MainActivity parentActivity = (MainActivity) getActivity();
		resideMenu = parentActivity.getResideMenu();
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(R.menu.activity_post_list, menu);
	}

	/*
	 * Creating posts and refreshing the list will be controlled from the Action
	 * Bar.
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {

		case R.id.action_refresh: {
			//updatePostList();
			break;
		}

		case R.id.action_new: {
			newPost();
			break;
		}
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onResume() {
		super.onResume();
		this.listView.setAdapter(new PostItemArrayAdapter(getActivity()));
	}
	/*private void updatePostList() {

		// Create query for objects of type "Post"
		final ParseQuery<ParseObject> query = ParseQuery.getQuery("Post");

		Toast.makeText(getActivity(), ParseUser.getCurrentUser().getUsername(),
				Toast.LENGTH_SHORT).show();
		// Restrict to cases where the author is the current user.
		// Note that you should pass in a ParseUser and not the
		// String reperesentation of that user
		query.whereEqualTo("author", ParseUser.getCurrentUser().getUsername());
		// Run the query

		query.findInBackground(new FindCallback<ParseObject>() {

			@Override
			public void done(List<ParseObject> postList, ParseException e) {
				if (e == null) {
					Post p;
					for (ParseObject post : postList) {
						p = new Post();
						String title = post.getString("textContent");
						Log.i("Post title >>> ", title);
						p.setTitle(title);
						Bitmap bitmap = BitmapFactory.decodeResource(
								getResources(), R.drawable.ic_launcher);
						p.setImage(bitmap);
						_posts.add(p);
					}
				} else {
					Log.d("Post retrieval", "Error: " + e.getMessage());
				}

				setListAdapter();
			}

		});

	}*/

	private void setListAdapter() {
		//mPostItemArrayAdapter = new PostItemArrayAdapter(getActivity(), _posts);
		//listView.setAdapter(mPostItemArrayAdapter);
		//listView.setAdapter(mainPostAdapter);
		listView.setAdapter(postArrayAdapter);
	}

	private void newPost() {
		Intent i = new Intent(getActivity(), WritePostActivity.class);
		startActivityForResult(i, 0);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == Activity.RESULT_OK) {
			// If a new post has been added, update
			// the list of posts
			//updatePostList();
		}
	}

}
