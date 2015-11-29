package com.example.leon.rssreader2.fragment;

import android.app.Fragment;
import android.app.LoaderManager;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.leon.rssreader2.R;
import com.example.leon.rssreader2.content.News;
import com.example.leon.rssreader2.loader.PageLoader;
import com.squareup.picasso.Picasso;

/**
 * Created by Leon on 24.11.2015.
 */
public class NewsPage extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>{

    private TextView mTextView;
    private ImageView mImageView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.li_page, container, false);

        mTextView = (TextView) view.findViewById(R.id.page_text_view);
        mImageView = (ImageView) view.findViewById(R.id.page_image);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getLoaderManager().initLoader(R.id.page_loader, getArguments(), this);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        if (R.id.page_loader == id) {
            return new PageLoader(getActivity().getApplicationContext(), args.getLong(News.Columns._ID));
        }
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        String imageUrl = null;
        if (data.moveToNext()) {
            mTextView.setText(data.getString(data.getColumnIndex(News.Columns.FULL_TEXT)));
            imageUrl = data.getString(data.getColumnIndex(News.Columns.IMAGE_URL));
        }

        if (!TextUtils.isEmpty(imageUrl)) {
            Picasso.with(getActivity()).load(imageUrl).into(mImageView);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
