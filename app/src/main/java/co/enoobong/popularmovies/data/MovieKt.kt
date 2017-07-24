package co.enoobong.popularmovies.data

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class MovieKt(
        @SerializedName("overview")
        var overview: String = "",
        @SerializedName("release_date")
        var releaseDate: String = "",
        @SerializedName("original_title")
        var title: String = "",
        @SerializedName("vote_average")
        var voteAverage: Double = -1.0,
        @SerializedName("poster_path")
        var posterPath: String = "",
        var _posterUrl: String = "",
        @SerializedName("id")
        var movieId: Int = -1) : Parcelable {

    var posterUrl = _posterUrl
        get() = "https://image.tmdb.org/t/p/w185" + posterPath

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readDouble(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(overview)
        parcel.writeString(releaseDate)
        parcel.writeString(title)
        parcel.writeDouble(voteAverage)
        parcel.writeString(posterPath)
        parcel.writeString(posterUrl)
        parcel.writeInt(movieId)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MovieKt> {
        override fun createFromParcel(parcel: Parcel): MovieKt {
            return MovieKt(parcel)
        }

        override fun newArray(size: Int): Array<MovieKt?> {
            return arrayOfNulls(size)
        }
    }
}