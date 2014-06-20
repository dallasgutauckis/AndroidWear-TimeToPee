package com.dallasgutauckis.wear.timetopee.view;

import android.content.Context;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.dallasgutauckis.wear.timetopee.R;
import com.dallasgutauckis.wear.timetopee.api.MovieResult;
import com.squareup.picasso.Picasso;

public class MovieResultView extends FrameLayout {

    private final TextView mTitle;

    private final ImageView mImage;

    public MovieResultView(Context context) {
        super(context);
        inflate(context, R.layout.movie_result_item, this);

        mTitle = (TextView) findViewById(R.id.title);
        mImage = (ImageView) findViewById(R.id.image);
    }

    public void setData(MovieResult result) {
        mTitle.setText(result.title);
        Picasso.with(getContext())
                .load(result.images.poster)
                .into(mImage);
    }
}
