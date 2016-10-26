package com.codepath.apps.restclienttemplate.models;

import com.codepath.apps.restclienttemplate.MyDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.sql.language.Select;
import com.raizlabs.android.dbflow.structure.BaseModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

@Table(database = MyDatabase.class)
public class FlickrPhoto extends BaseModel {

    @PrimaryKey
    @Column(name = "uid")
    private String uid;

    @Column(name = "name")
    private String name;

    @Column(name = "url")
    private String url;

    public FlickrPhoto() {
        super();
    }

    public FlickrPhoto(JSONObject object) {
        super();

        try {
            this.uid = object.getString("id");
            this.name = object.getString("title");
            // http://farm{farm-id}.staticflickr.com/{server-id}/{id}_{secret}.jpg
            this.url = "http://farm" + object.getInt("farm") + ".staticflickr.com/" + object
                    .getInt("server") +
                    "/" + uid + "_" + object.getString("secret") + ".jpg";
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static FlickrPhoto byPhotoUid(String uid) {
        return new Select().from(FlickrPhoto.class).where(FlickrPhoto_Table.uid.eq(uid))
                .querySingle();
    }

    public static List<FlickrPhoto> recentItems() {
        return new Select().from(FlickrPhoto.class).orderBy(FlickrPhoto_Table.uid, false)
                .limit(300).queryList();
    }

    public String getUid() {
        return uid;
    }

    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
