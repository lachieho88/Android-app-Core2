package au.edu.swin.sdmd.core2

import android.media.Image
import android.os.Parcelable
import android.widget.ImageButton
import android.widget.ImageView
import kotlinx.parcelize.Parcelize

@Parcelize
data class Place(var imageId :Int ,var name: String, var location : String , var date : String,var rating: Float) : Parcelable {
}