package com.dallasgutauckis.wear.timetopee;

import android.app.Activity;
import android.app.Fragment;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preview.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.NotificationCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;

import com.dallasgutauckis.wear.timetopee.adapter.MovieResultAdapter;
import com.dallasgutauckis.wear.timetopee.api.MovieResult;
import com.dallasgutauckis.wear.timetopee.api.Trakt;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;
import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MainActivity extends Activity {

    public static int notificationId = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        private static final String EXTRA_VOICE_REPLY = "extra_voice_reply";

        private EditText mSearch;

        private GridView mGrid;

        private MovieResultAdapter mAdapter;

        private List<MovieResult> mAdapterList;

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);

            mGrid = (GridView) rootView.findViewById(R.id.results);
            mAdapterList = new ArrayList<MovieResult>();
            mAdapter = new MovieResultAdapter(rootView.getContext(), mAdapterList);

            mGrid.setAdapter(mAdapter);

            mGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    makeWatchingNotification(mAdapterList.get(position));
                }
            });

            mSearch = (EditText) rootView.findViewById(R.id.input);
            mSearch.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }

                @Override
                public void afterTextChanged(Editable s) {
                    search(String.valueOf(s));
                }
            });

            return rootView;
        }

        private void makeWatchingNotification(final MovieResult movieResult) {
            Target tmpTarget = new Target() {
                @Override
                public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom loadedFrom) {
                    int notificationId = MainActivity.notificationId++;
                    // Build intent for notification content
                    Intent viewIntent = new Intent(mSearch.getContext(), MakePeeTimeActivity.class);

                    viewIntent.putExtra("data", movieResult);

                    PendingIntent viewPendingIntent = PendingIntent.getActivity(getActivity(), 0, viewIntent, 0);

                    NotificationCompat.Builder notificationBuilder =
                            new NotificationCompat.Builder(mSearch.getContext())
                                    .setSmallIcon(R.drawable.ic_launcher)
                                    .setLargeIcon(bitmap)
                                    .setContentTitle("Now watching")
                                    .setContentText(movieResult.title)
                                    .addAction(R.drawable.ic_pee, "PEE-TIME!", viewPendingIntent);

                    // Get an instance of the NotificationManager service
                    NotificationManagerCompat notificationManager = NotificationManagerCompat.from(mSearch.getContext());

                    // Build the notification and issues it with notification manager.
                    notificationManager.notify(notificationId, notificationBuilder.build());
                }

                @Override
                public void onBitmapFailed(Drawable drawable) {

                }

                @Override
                public void onPrepareLoad(Drawable drawable) {

                }
            };

            Picasso.with(mSearch.getContext())
                    .load(movieResult.images.poster)
                    .resize(123, 232)
                    .into(tmpTarget);
        }

        private void search(String s) {
            Trakt.getInstance()
                    .searchMovies(s)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<List<MovieResult>>() {
                        @Override
                        public void call(List<MovieResult> movieResults) {
                            mAdapterList.clear();
                            mAdapterList.addAll(movieResults);
                            mAdapter.notifyDataSetChanged();
                        }
                    }, new Action1<Throwable>() {
                        @Override
                        public void call(Throwable throwable) {
                            Log.e("PEEE", "Failed…", throwable);
                        }
                    });
        }
    }
}
