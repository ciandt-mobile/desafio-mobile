package com.ciet.leogg.filmes.model;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Keep;
import androidx.annotation.Nullable;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Keep
public class Movie implements Parcelable {
    public Movie(){}
    private Long id;
    private String posterPath;
    private String backdropPath;
    private Date releaseDate;
    private String title;
    private Integer[] genreIds;
    private String overview;

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

    public Integer[] getGenreIds() {
        return genreIds;
    }

    public String getOverview() {
        return overview;
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
        int[] boxedGenreIds = new int[genreIds.length];
        for(int j=0; j< genreIds.length;j++){
            boxedGenreIds[j] = genreIds[j];
        }
        parcel.writeInt(boxedGenreIds.length);
        parcel.writeIntArray(boxedGenreIds);
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
        int genreIdsLength = in.readInt();
        int[] boxedGenreIds = new int[genreIdsLength];
        genreIds = new Integer[genreIdsLength];
        in.readIntArray(boxedGenreIds);
        for(int i=0;i<genreIdsLength;i++){
            genreIds[i] = boxedGenreIds[i];
        }

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
