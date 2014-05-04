package com.diverific.app;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseImageView;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;

public class CustomParseQueryAdapter extends ParseQueryAdapter<Dive> {

    public CustomParseQueryAdapter(Context context) {
        super(context, new ParseQueryAdapter.QueryFactory<Dive>() {
            public ParseQuery<Dive> create() {
                // Here we can configure a ParseQuery to display
                // only self and nearby Posts.

                ParseQuery query = new ParseQuery("Dive");
                query.setLimit(20);
                //query.whereEqualTo("createdBy", currentUser);
                //query.orderByDescending("createdBy");
                return query;
            }
        });
    }

    @Override
    public View getItemView(Dive dive, View v, ViewGroup parent) {

        if (v == null) {
            v = View.inflate(getContext(), R.layout.fragment_dive_list_item, null);
        }

        super.getItemView(dive, v, parent);

        ParseImageView image = (ParseImageView) v.findViewById(R.id.icon);
        ParseFile photoFile = dive.getPhoto();
        if (photoFile != null) {
            image.setParseFile(photoFile);
            image.loadInBackground(new GetDataCallback() {
                @Override
                public void done(byte[] data, ParseException e) {
                    // nothing to do
                }
            });
        }

        TextView titleTextView = (TextView) v.findViewById(R.id.textView);
        titleTextView.setText(dive.getDiveName());
        TextView ratingTextView = (TextView) v.findViewById(R.id.textView2);
        ratingTextView.setText(dive.getDivePrice());
        return v;
    }

}