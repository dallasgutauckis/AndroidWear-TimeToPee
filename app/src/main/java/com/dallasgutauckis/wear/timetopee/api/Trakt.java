package com.dallasgutauckis.wear.timetopee.api;

import java.util.List;

import retrofit.RestAdapter;
import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

public class Trakt {

    public final static String API_KEY = "<Trakt API key>";

    private static Trakt sInstance;

    private static Object sLock = new Object();

    private final TraktMovieContract mService;

    public static final Trakt getInstance() {
        synchronized (sLock) {
            if (sInstance == null) {
                sInstance = new Trakt();
            }
        }

        return sInstance;
    }

    private Trakt() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://api.trakt.tv")
                .build();

        mService = restAdapter.create(TraktMovieContract.class);
    }

    public Observable<List<MovieResult>> searchMovies(String query) {
        return mService.searchMovies(query);
    }

    public interface TraktMovieContract {

        @GET("/search/movies.json/" + API_KEY)
        Observable<List<MovieResult>> searchMovies(@Query("query") String query);
    }
}
