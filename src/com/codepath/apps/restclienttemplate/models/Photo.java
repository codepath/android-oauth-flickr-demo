package com.codepath.apps.restclienttemplate.models;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

@Table(name = "photos")
public class Photo extends Model { 
	@Column(name = "uid")
	private String uid;
	@Column(name = "name")
	private String name;
	@Column(name = "url")
	private String url;
	
	public Photo() {
		super();
	}
	
	public Photo(JSONObject object){
		super();

		try {
			this.uid = object.getString("id");
			this.name = object.getString("title");
			// http://farm{farm-id}.staticflickr.com/{server-id}/{id}_{secret}.jpg
			this.url = "http://farm" + object.getInt("farm") + ".staticflickr.com/" + object.getInt("server") + 
			  "/" + uid + "_" + object.getString("secret") + ".jpg";
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	public static Photo byPhotoUid(String uid) {
	   return new Select().from(Photo.class).where("uid = ?", uid).executeSingle();
	}
	
	public static ArrayList<Photo> recentItems() {
      return new Select().from(Photo.class).orderBy("id DESC").limit("300").execute();
	}
	

	public String getUrl() {
		return url;
	}
	
	public String getName() {
		return name;
	}
}
