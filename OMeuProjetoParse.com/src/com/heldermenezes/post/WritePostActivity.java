package com.heldermenezes.post;

import java.io.ByteArrayOutputStream;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore.MediaColumns;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.heldermenezes.omeuprojeto.R;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class WritePostActivity extends Activity implements View.OnClickListener {

	private Button saveButton;
	private Button cancelButton;
	private Button pickPictureButton;
	private TextView postContent;
	private final int REQUEST_CODE = 1;
	private Bitmap photoFile;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_write_post_);

		postContent = ((EditText) findViewById(R.id.blog_post_content));

		saveButton = ((Button) findViewById(R.id.save_button));
		saveButton.setOnClickListener(this);

		cancelButton = ((Button) findViewById(R.id.cancel_button));
		cancelButton.setOnClickListener(this);

		pickPictureButton = (Button) findViewById(R.id.pick_photo_button);
		pickPictureButton.setOnClickListener(this);
	}

	private ParseFile parseBitmapToParseFile(Bitmap bitmap,String title) {
		ByteArrayOutputStream mByteArrayOutputStream = new ByteArrayOutputStream();
		// compress image to lower quality scale 1 - 100
		bitmap.compress(Bitmap.CompressFormat.PNG, 100, mByteArrayOutputStream);
		byte[] image = mByteArrayOutputStream.toByteArray();

		// Create the ParseFile
		ParseFile mParseFile = new ParseFile(title.trim().length()>0?title+".png":"noname.png", image);
		return mParseFile;
	}

	private void cancelPost() {
		setResult(RESULT_CANCELED);
		finish();
	}

	private void savePost() {
		Post post = new Post();
		String title = postContent.getText().toString();
		
		post.setTitle(title.trim().length()>0?title:"No title");
		
		post.setPhotoFile(parseBitmapToParseFile(photoFile,title));
		post.setAuthor(ParseUser.getCurrentUser());
		
		final ProgressDialog dialog = new ProgressDialog(WritePostActivity.this);
		dialog.setMessage("Posting...");
		dialog.show();
		// Save the post and return
		post.saveInBackground(new SaveCallback() {
			@Override
			public void done(ParseException e) {
				dialog.dismiss();
				if (e == null) {
					setResult(RESULT_OK);
					Toast.makeText(getApplicationContext(),
							"Successfully posting.",
							Toast.LENGTH_SHORT).show();
					finish();
				} else {
					Toast.makeText(getApplicationContext(),
							"Error saving: " + e.getMessage(),
							Toast.LENGTH_SHORT).show();
					Log.i("ERROR", e.getMessage());
				}
			}

		});

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.save_button:
			savePost();
			break;
		case R.id.cancel_button:
			cancelPost();
			break;
		case R.id.pick_photo_button:
			Intent intent = new Intent(
					Intent.ACTION_PICK,
					android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
			startActivityForResult(intent, this.REQUEST_CODE);
			break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == this.REQUEST_CODE && resultCode == RESULT_OK
				&& data != null) {
			Uri selectedImage = data.getData();
			String[] filePathColumn = { MediaColumns.DATA };

			Cursor cursor = getContentResolver().query(selectedImage,
					filePathColumn, null, null, null);
			cursor.moveToFirst();

			int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
			String picturePath = cursor.getString(columnIndex);
			cursor.close();

			Bitmap scaledBitmap = scaleBitmap(picturePath);

			photoFile = scaledBitmap;

			ImageView tmp = (ImageView) this.findViewById(R.id.imageViewResult);
			tmp.setImageBitmap(scaledBitmap);
		}
	}

	
	private Bitmap scaleBitmap(String filePath) {
		ImageView tmp = (ImageView) this.findViewById(R.id.imageViewResult);
		Toast.makeText(this, tmp.getWidth() + "", Toast.LENGTH_SHORT).show();
		int targetW = tmp.getWidth();
		int targetH = tmp.getHeight();

		// Get the dimensions of the bitmap
		BitmapFactory.Options bmOptions = new BitmapFactory.Options();
		bmOptions.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(filePath, bmOptions);
		int photoW = bmOptions.outWidth;
		int photoH = bmOptions.outHeight;

		// Determine how much to scale down the image
		int scaleFactor = Math.min(photoW / targetW, photoH / targetH);
		
		// Decode the image file into a Bitmap sized to fill the View
	    bmOptions.inJustDecodeBounds = false;
	    bmOptions.inSampleSize = scaleFactor;
	    bmOptions.inPurgeable = true;
	    
	    Bitmap bitmap = BitmapFactory.decodeFile(filePath, bmOptions);

		return bitmap;

	}

}
