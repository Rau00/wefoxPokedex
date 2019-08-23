package technical.test.wefoxpokedex.data.model.view

import android.os.Parcel
import android.os.Parcelable

class PokemonModelView(
    val name: String,
    val weight: String,
    val height: String,
    val sprite: String,
    val dateCatched: String,
    val baseExperience: String,
    val types: List<String>
): Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.createStringArrayList()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(weight)
        parcel.writeString(height)
        parcel.writeString(sprite)
        parcel.writeString(dateCatched)
        parcel.writeString(baseExperience)
        parcel.writeStringList(types)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PokemonModelView> {
        override fun createFromParcel(parcel: Parcel): PokemonModelView {
            return PokemonModelView(parcel)
        }

        override fun newArray(size: Int): Array<PokemonModelView?> {
            return arrayOfNulls(size)
        }
    }

}