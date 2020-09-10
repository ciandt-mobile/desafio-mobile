package com.ciet.leogg.filmes.model;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Keep;
import androidx.annotation.Nullable;
import com.ciet.leogg.filmes.App;
import com.ciet.leogg.filmes.R;
import com.ciet.leogg.filmes.api.GenreDeserializer;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.*;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Keep
public class Movie implements Parcelable {
    public Movie(){}
    private Long id;
    private String posterPath;
    private String backdropPath;
    private Date releaseDate = new Date(1400163380494L);
    private String title;
    private String overview;
    private Integer runtime;
    @JsonDeserialize(using = GenreDeserializer.class)
    private List<String> genres;

    public Long getId() {
        return id;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public Integer getRuntime() {
        return runtime;
    }

    public List<String> getGenres() {
        return genres;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (id == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeLong(id);
        }
        parcel.writeString(posterPath);
        parcel.writeString(backdropPath);
        parcel.writeString(title);
        parcel.writeString(overview);
        Calendar convertedReleaseDate = new GregorianCalendar();
        convertedReleaseDate.setTime(releaseDate);
        parcel.writeLong(convertedReleaseDate.getTimeInMillis());
        parcel.writeInt(runtime);
        parcel.writeStringList(genres);
    }
    protected Movie(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readLong();
        }
        posterPath = in.readString();
        backdropPath = in.readString();
        title = in.readString();
        overview = in.readString();
        releaseDate = new Date(in.readLong());
        runtime = in.readInt();
        in.readStringList(genres);
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public static  Movie createDefault(){
        Movie movie = new Movie(){
            @Override
            public String getTitle() {
                return App.instance().getString(R.string.no_movie);
            }

            @Override
            public Date getReleaseDate() {
                return new Date();
            }

            @Override
            public List<String> getGenres() {
                return new ArrayList<>();
            }
        };
        return movie;
    }


    @Override
    public boolean equals(@Nullable Object obj) {
        if(this == obj){
            return true;
        }
        if(!(obj instanceof Movie)){
            return false;
        }
        return this.id.equals(((Movie) obj).id);
    }
}
