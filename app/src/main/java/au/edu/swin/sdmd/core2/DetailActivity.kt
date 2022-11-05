package au.edu.swin.sdmd.core2

import android.annotation.SuppressLint
import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.lifecycle.ViewModel
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class DetailActivity : AppCompatActivity() {
    var place: Place? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detaill)
        //get data
         place = intent.getParcelableExtra<Place>("place")

        //Null check and set the data to be shown on screen
        place?.let {

            val vImage = findViewById<ImageView>(R.id.imageView)
            //When an image is clicked, a new screen with further details needs to be shown along with the image.
            // The data needs to be passed as a Parcelable object
            when (it.imageId) {
                1 -> {
                    vImage.setImageResource(resources.getIdentifier("university","drawable", packageName))
                }
                2 -> {
                    vImage.setImageResource(resources.getIdentifier("station","drawable", packageName))
                }
                3 -> {
                    vImage.setImageResource(resources.getIdentifier("hall","drawable", packageName))
                }
                else -> {
                    vImage.setImageResource(resources.getIdentifier("garden","drawable", packageName))
                }
            }

            val vName = findViewById<TextView>(R.id.name)
            vName.text = it.name

            val vLocation = findViewById<TextView>(R.id.location)
            vLocation.text = it.location

            val vDate = findViewById<TextView>(R.id.date)
            vDate.text = it.date

            val vRatings = findViewById<RatingBar>(R.id.ratingBar)
            vRatings.rating = it.rating

        }

    }

    override fun onBackPressed() {
        //code to make sure that the ratings bar is pressed
        //To save the result on this side we need to pass in an object
        place?.rating = findViewById<RatingBar>(R.id.ratingBar).rating
        //Turn the second activity into a form,
        // and update the details for the locations.
        // In order to make life easy for your user,
        // you need enter the existing text/values into the form for editing,
        // rather than presenting empty inputs
        val inputName =findViewById<TextInputEditText>(R.id.name)
        place?.name = inputName.text.toString()

        //Error checking should be included for required
        //fields -- this could be length of string,
        // valid dates, etc
        if (inputName.length() == 0 ){
            inputName.error = "name cannot be empty"
        }
        else
        {
            val inputLocation = findViewById<TextInputEditText>(R.id.location)
            place?.location = inputLocation.text.toString()

            val inputDate = findViewById<TextInputEditText>(R.id.date)
            place?.date = inputDate.text.toString()

            val i = intent.apply {
                putExtra("place",place)
            }
            setResult(Activity.RESULT_OK, i)
            super.onBackPressed()
        }


    }

}




