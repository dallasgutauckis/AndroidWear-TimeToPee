package com.dallasgutauckis.wear.timetopee.api;

import android.os.Parcel;
import android.os.Parcelable;

/*
[
    {
        "title": "Batman",
        "year": 1989,
        "released": 614502000,
        "url": "http://trakt.tv/movie/batman-1989",
        "trailer": "http://youtube.com/watch?v=HlsM2_8u_mk",
        "runtime": 126,
        "tagline": "Have you ever danced with the devil in the pale moonlight?",
        "overview": "The Dark Knight of Gotham City begins his war on crime with his first major enemy being the clownishly homicidal Joker, who has seized control of Gotham's underworld.",
        "certification": "PG-13",
        "imdb_id": "tt0096895",
        "tmdb_id": 268,
        "images": {
            "poster": "http://slurm.trakt.us/images/posters_movies/316.2.jpg",
            "fanart": "http://slurm.trakt.us/images/fanart_movies/316.2.jpg"
        },
        "genres": [
            "Action",
            "Fantasy"
        ],
        "ratings": {
            "percentage": 78,
            "votes": 1940,
            "loved": 1782,
            "hated": 158
        }
    },…
]
 */
public class MovieResult implements Parcelable {
    public String title;
    int year;
    int released;
    public MovieImages images;

    public static class MovieImages implements Parcelable {
        public String poster;
        public String fanart;

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.poster);
            dest.writeString(this.fanart);
        }

        public MovieImages() {
        }

        private MovieImages(Parcel in) {
            this.poster = in.readString();
            this.fanart = in.readString();
        }

        public static Creator<MovieImages> CREATOR = new Creator<MovieImages>() {
            public MovieImages createFromParcel(Parcel source) {
                return new MovieImages(source);
            }

            public MovieImages[] newArray(int size) {
                return new MovieImages[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeInt(this.year);
        dest.writeInt(this.released);
        dest.writeParcelable(this.images, flags);
    }

    public MovieResult() {
    }

    private MovieResult(Parcel in) {
        this.title = in.readString();
        this.year = in.readInt();
        this.released = in.readInt();
        this.images = in.readParcelable(MovieImages.class.getClassLoader());
    }

    public static Parcelable.Creator<MovieResult> CREATOR = new Parcelable.Creator<MovieResult>() {
        public MovieResult createFromParcel(Parcel source) {
            return new MovieResult(source);
        }

        public MovieResult[] newArray(int size) {
            return new MovieResult[size];
        }
    };
}
