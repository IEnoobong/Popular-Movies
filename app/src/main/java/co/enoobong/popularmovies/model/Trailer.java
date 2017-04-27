package co.enoobong.popularmovies.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Trailer implements Parcelable {
    public static final Creator<Trailer> CREATOR = new Creator<Trailer>() {
        @Override
        public Trailer createFromParcel(Parcel in) {
            return new Trailer(in);
        }

        @Override
        public Trailer[] newArray(int size) {
            return new Trailer[size];
        }
    };
    private final String videoUrl;
    @SerializedName("key")
    private String key;
    @SerializedName("name")
    private String name;

    public Trailer() {
        videoUrl = "https://www.youtube.com/watch?v=";
    }

    protected Trailer(Parcel in) {
        key = in.readString();
        name = in.readString();
        videoUrl = in.readString();
    }

    private String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public String getVideoUrl() {
        return videoUrl + getKey();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(key);
        dest.writeString(name);
        dest.writeString(videoUrl);
    }
}
