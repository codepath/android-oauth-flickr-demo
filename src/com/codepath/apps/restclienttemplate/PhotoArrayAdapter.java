package com.codepath.apps.restclienttemplate;

import java.util.List;

import com.codepath.apps.restclienttemplate.models.Photo;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class PhotoArrayAdapter extends ArrayAdapter<Photo> {
	public PhotoArrayAdapter(Context context, List<Photo> photoList) {
		super(context, R.layout.photo_item, photoList);
	}
	
	@Override
    public View getView(int position, View convertView, ViewGroup parent) {
		Photo photo = this.getItem(position);
		LinearLayout itemView;
		ImageView ivImage;
		ImageLoader imageLoader = ImageLoader.getInstance();
        if (convertView == null) {
    		LayoutInflater inflator = LayoutInflater.from(getContext());
    		itemView = (LinearLayout) inflator.inflate(R.layout.photo_item, parent, false);
        } else {
            itemView = (LinearLayout) convertView;
        }
        ivImage = (ImageView) itemView.findViewById(R.id.ivPhoto);
        ivImage.setImageResource(android.R.color.transparent); 
        imageLoader.displayImage(photo.getUrl(), ivImage);
        return itemView;
	}
}
