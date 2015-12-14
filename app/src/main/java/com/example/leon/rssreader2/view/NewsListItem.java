package com.example.leon.rssreader2.view;

import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.leon.rssreader2.R;
import com.example.leon.rssreader2.content.News;
import com.squareup.picasso.Picasso;

/**
 * Created by Leon on 14.12.2015.
 */
public class NewsListItem extends LinearLayout {

    private ImageView mIcon;
    private TextView mTitle;

    public NewsListItem(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void bindCursor(Cursor cursor) {
        mTitle.setText(cursor.getString(cursor.getColumnIndex(News.Columns.TITLE)));
        final String imageUrl = cursor.getString(cursor.getColumnIndex(News.Columns.IMAGE_URL));
        if (!TextUtils.isEmpty(imageUrl)) {
            Picasso.with(getContext()).load(imageUrl).into(mIcon);
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mIcon = (ImageView) findViewById(R.id.image);
        mTitle = (TextView) findViewById(R.id.title);
    }
}
