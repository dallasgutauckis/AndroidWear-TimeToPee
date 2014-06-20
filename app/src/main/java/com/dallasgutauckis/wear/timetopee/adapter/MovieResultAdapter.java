package com.dallasgutauckis.wear.timetopee.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.dallasgutauckis.wear.timetopee.api.MovieResult;
import com.dallasgutauckis.wear.timetopee.view.MovieResultView;

import java.util.List;

public class MovieResultAdapter extends BaseAdapter {

    private final List<MovieResult> mList;

    public MovieResultAdapter(Context context, List<MovieResult> objects) {
        super();
        mList = objects;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public int getItemViewType(int position) {
        return 1;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return true;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MovieResultView view;

        if (convertView == null) {
            view = (MovieResultView) (convertView = new MovieResultView(parent.getContext()));
        } else {
            view = (MovieResultView) convertView;
        }

        view.setData((MovieResult) getItem(position));

        return convertView;
    }
}
