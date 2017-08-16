package com.example.gurjeet.booklisting;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.List;

/**
 * Created by Gurjeet on 11-Feb-17.
 */

public class BookLoader extends AsyncTaskLoader<List<Book>> {
    private static final String LOG_TAG = BookLoader.class.getName();
    private String mUrl;
    private String mImg;


    BookLoader(Context c, String url){
        super(c);
        mUrl=url;
    }
    @Override
    protected void onStartLoading() {
        Log.i(LOG_TAG,"StartLoading()");
        forceLoad();
    }
    @Override
    public List<Book> loadInBackground() {
        if (mUrl == null) {
            return null;
        }

        Log.i(LOG_TAG,"loadInBackground()");
        List<Book> books = QueryUtils.fetchBookData(mUrl);
        return books;
    }
}
