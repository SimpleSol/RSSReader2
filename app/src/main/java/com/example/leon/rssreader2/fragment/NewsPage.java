package com.example.leon.rssreader2.fragment;

import android.app.Fragment;
import android.app.LoaderManager;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.leon.rssreader2.R;
import com.example.leon.rssreader2.content.News;
import com.example.leon.rssreader2.loader.NewsPageLoader;
import com.squareup.picasso.Picasso;

/**
 * Created by Leon on 24.11.2015.
 */
public class NewsPage extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    private ImageView mImage;
    private TextView mTextView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fmt_news_page, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mImage = (ImageView) view.findViewById(android.R.id.icon);
        mTextView = (TextView) view.findViewById(android.R.id.text1);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getLoaderManager().initLoader(R.id.news_loader, getArguments(), this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        if (R.id.news_loader == id) {
            return new NewsPageLoader(getActivity().getApplicationContext(), args.getLong(News.Columns._ID));
        }
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if (R.id.news_loader == loader.getId() && data.moveToFirst()) {
            final String imageUrl = data.getString(data.getColumnIndex(News.Columns.IMAGE_URL));
            if (imageUrl != null) {
                Picasso.with(getActivity().getApplicationContext()).load(imageUrl).into(mImage);
            }
            String fullText = data.getString(data.getColumnIndex(News.Columns.FULL_TEXT));
            if (TextUtils.isEmpty(fullText)) {
                fullText = data.getString(data.getColumnIndex(News.Columns.TITLE));
            }
            mTextView.setText(Html.fromHtml(fullText));


        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}















