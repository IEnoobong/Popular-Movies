package co.enoobong.popularmovies.data

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class ReviewKt(@SerializedName("author") var author: String = "",
                    @SerializedName("content") var content: String = "") : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(author)
        parcel.writeString(content)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ReviewKt> {
        override fun createFromParcel(parcel: Parcel): ReviewKt {
            return ReviewKt(parcel)
        }

        override fun newArray(size: Int): Array<ReviewKt?> {
            return arrayOfNulls(size)
        }
    }
}