package co.enoobong.popularmovies.data

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class TrailerKt(@SerializedName("key") var key: String = "",
                     @SerializedName("name") var name: String = "",
                     var _videoUrl: String = "") : Parcelable {

    var videoUrl = _videoUrl
        get() = "https://www.youtube.com/watch?v=$key"

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(key)
        parcel.writeString(name)
        parcel.writeString(videoUrl)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TrailerKt> {
        override fun createFromParcel(parcel: Parcel): TrailerKt {
            return TrailerKt(parcel)
        }

        override fun newArray(size: Int): Array<TrailerKt?> {
            return arrayOfNulls(size)
        }
    }
}