package com.example.gurjeet.booklisting;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.GONE;

/**
 * Created by Gurjeet on 11-Feb-17.
 */

public class BookActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Book>> {

    private static final String GOOGLE_REQUEST_URL =
            "https://www.googleapis.com/books/v1/volumes?q=digital&maxResults=10";
    private static final int BOOK_LOADER_ID = 1;
    private TextView mEmptyStateTextView;
    private ProgressBar mprogress;
    public static final String LOG_TAG = BookActivity.class.getName();
    private BookAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_list);

        ListView BookListView = (ListView) findViewById(R.id.list);
        mprogress=(ProgressBar)findViewById(R.id.progress);
        mEmptyStateTextView = (TextView) findViewById(R.id.empty);
        BookListView.setEmptyView(mEmptyStateTextView);

        mAdapter = new BookAdapter(this, new ArrayList<Book>());

        ConnectivityManager cm =
                (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        if(isConnected) {
            BookListView.setAdapter(mAdapter);
            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(BOOK_LOADER_ID, null, this);
        }
        else {
            mEmptyStateTextView.setText(R.string.nonet);
            mprogress.setVisibility(GONE);
        }
        BookListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int pos, long l) {
                Book currentBook=mAdapter.getItem(pos);
                Intent web=new Intent(Intent.ACTION_VIEW);
                web.setData(Uri.parse(currentBook.getUrl()));
                startActivity(web);
            }

        });
    }
    @Override
    public Loader<List<Book>> onCreateLoader(int id, Bundle args) {
        Log.i(LOG_TAG,"OnCreate()");
        return new BookLoader(this, GOOGLE_REQUEST_URL);
    }
    @Override
    public void onLoadFinished(Loader<List<Book>> loader, List<Book> books) {
        mAdapter.clear();
        Log.i(LOG_TAG,"OnFinish()");
        mprogress.setVisibility(GONE);
        mEmptyStateTextView.setText(R.string.empty);

        // If there is a valid list of {@link Books}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (books != null && !books.isEmpty()) {
            mAdapter.addAll(books);
        }
    }
    @Override
    public void onLoaderReset(Loader<List<Book>> loader) {
        Log.i(LOG_TAG,"OnClear()");
        mAdapter.clear();
    }



}
