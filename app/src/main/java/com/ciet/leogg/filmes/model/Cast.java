package com.ciet.leogg.filmes.model;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Keep;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Keep
public class Cast implements Parcelable {
    public Cast(){}
    private String character;
    private String name;
    private Integer order;
    private String profilePath;

    public String getCharacter() {
        return character;
    }

    public String getName() {
        return name;
    }

    public Integer getOrder() {
        return order;
    }

    public String getProfilePath() {
        return profilePath;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(character);
        parcel.writeString(name);
        if (order == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(order);
        }
        parcel.writeString(profilePath);
    }

    protected Cast(Parcel in) {
        character = in.readString();
        name = in.readString();
        if (in.readByte() == 0) {
            order = null;
        } else {
            order = in.readInt();
        }
        profilePath = in.readString();
    }

    public static final Creator<Cast> CREATOR = new Creator<Cast>() {
        @Override
        public Cast createFromParcel(Parcel in) {
            return new Cast(in);
        }

        @Override
        public Cast[] newArray(int size) {
            return new Cast[size];
        }
    };
}
