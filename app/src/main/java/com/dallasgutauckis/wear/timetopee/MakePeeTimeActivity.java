package com.dallasgutauckis.wear.timetopee;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.preview.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.NotificationCompat;

import com.dallasgutauckis.wear.timetopee.api.MovieResult;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

public class MakePeeTimeActivity extends Activity {

    public static final int NOTIFICATION_DELAY_MILLIS = 3000;

    private MovieResult mData;

    Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        NotificationManagerCompat.from(this)
                .cancelAll();

        mData = getIntent().getParcelableExtra("data");

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {

                Target target = new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom loadedFrom) {
                        int notificationId = MainActivity.notificationId++;

                        NotificationCompat.Builder notificationBuilder =
                                new NotificationCompat.Builder(MakePeeTimeActivity.this)
                                        .setSmallIcon(R.drawable.ic_launcher)
                                        .setLargeIcon(bitmap)
                                        .setContentTitle("Go pee after:")
                                        .setContentText("\"Geez, Yo-Yo, did you get beat up a lot in school?\""); // This is an arbitrary quote I
                                        // copied from the internship for demo purposes

                        // Get an instance of the NotificationManager service
                        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(MakePeeTimeActivity.this);

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

                Picasso.with(MakePeeTimeActivity.this)
                        .load(mData.images.poster)
                        .resize(123, 232)
                        .into(target);
            }
        }, NOTIFICATION_DELAY_MILLIS);

        finish();
    }
}
