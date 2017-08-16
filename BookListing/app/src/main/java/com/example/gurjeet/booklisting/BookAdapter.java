package com.example.gurjeet.booklisting;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Gurjeet on 11-Feb-17.
 */

public class BookAdapter extends ArrayAdapter<Book> {
    public static final String LOG_TAG = BookActivity.class.getName();
    public BookAdapter(Activity context, ArrayList<Book> w) {
        super(context, 0, w);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.activity_main, parent, false);
        }
        Book currentBook = getItem(position);

        TextView titView = (TextView) listItemView.findViewById(R.id.title);
        titView.setText(currentBook.getTitle());

        TextView authView = (TextView) listItemView.findViewById(R.id.author);
        authView.setText(currentBook.getAuthor());

        TextView countryView = (TextView) listItemView.findViewById(R.id.country);
        countryView.setText(currentBook.getCountry());
        return listItemView;
    }
}
