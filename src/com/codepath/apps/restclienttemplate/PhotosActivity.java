package com.codepath.apps.restclienttemplate;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.GridView;

import com.codepath.apps.restclienttemplate.models.Photo;
import com.loopj.android.http.JsonHttpResponseHandler;

public class PhotosActivity extends Activity {
	
	RestClient client;
	ArrayList<Photo> photoItems;
	GridView gvPhotos;
	PhotoArrayAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_photos);
		client = RestClientApp.getRestClient();
		photoItems = new ArrayList<Photo>();
		gvPhotos = (GridView) findViewById(R.id.gvPhotos);
		adapter = new PhotoArrayAdapter(this, photoItems);
		gvPhotos.setAdapter(adapter);
		loadPhotos();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.photos, menu);
		return true;
	}
	
	public void loadPhotos() {
		client.getInterestingnessList(new JsonHttpResponseHandler() { 
    		public void onSuccess(JSONObject json) {
                Log.d("DEBUG", "result: " + json.toString());
                // Add new photos to SQLite
                try {
					JSONArray photos = json.getJSONObject("photos").getJSONArray("photo");
					for (int x = 0; x < photos.length(); x++) {
						String uid  = photos.getJSONObject(x).getString("id");
						Photo p = Photo.byPhotoUid(uid);
						if (p == null) { p = new Photo(photos.getJSONObject(x)); };
						p.save();
					}
				} catch (JSONException e) {
					e.printStackTrace();
					Log.e("debug", e.toString());
				}
                
				// Load into GridView from DB
				for (Photo p : Photo.recentItems()) {
					adapter.add(p);
				}
				Log.d("DEBUG", "Total: " + photoItems.size());
            }
    	});
	}

}
