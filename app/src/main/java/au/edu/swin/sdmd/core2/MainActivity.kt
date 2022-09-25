package au.edu.swin.sdmd.core2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //create Parcelable objects
        val university = Place(1, "university", "Hawthorn", "24/09/2019", 1.0.toFloat())
        val station = Place(2, "station", "Flinders", "13/03/2017", 2.0.toFloat())
        val hall = Place(3, "hall", "london", "15/05/2018", 4.0.toFloat())
        val garden = Place(4, "garden", "Melbourne city", "28/10/2023", 5.0.toFloat())

        val vUniversityRating = findViewById<TextView>(R.id.University_Rating)
        val vStationRating = findViewById<TextView>(R.id.Station_Rating)
        val vHallRating = findViewById<TextView>(R.id.Hall_Rating)
        val vGardenRating = findViewById<TextView>(R.id.GardenRating)

        vUniversityRating.text = university.rating.toString()
        vStationRating.text = station.rating.toString()
        vHallRating.text = hall.rating.toString()
        vGardenRating.text = garden.rating.toString()

        //When an image is clicked, a new screen with further details needs to be shown along with the image.
        // The data needs to be passed as a Parcelable object
        val vUniversity = findViewById<ImageView>(R.id.UniversityImage)
        vUniversity.setOnClickListener {
            //set up intent
            val intent = Intent(this, DetailActivity::class.java)
            //put extra data into our intent, done it with a Parcelable object
            intent.putExtra("place", university)
            //start the activity based on those details
             startActivity(intent)
          //  startForResult.launch(intent)
        }

        val vStation = findViewById<ImageView>(R.id.StationIamge)
        vStation.setOnClickListener {
            //set up intent
            val intent = Intent(this, DetailActivity::class.java)
            //put extra data into our intent, done it with a Parcelable object
            intent.putExtra("place", station)
            //start the activity based on those details
              startActivity(intent)
          //  startForResult.launch(intent)

        }
        val vHall = findViewById<ImageView>(R.id.HallImage)
        vHall.setOnClickListener {
            val intent = Intent(this, DetailActivity::class.java)
            //put extra data into our intent, done it with a Parcelable object
            intent.putExtra("place", hall)
            //start the activity based on those details
             startActivity(intent)
           // startForResult.launch(intent)
        }

        val vGarden = findViewById<ImageView>(R.id.GardenImage)
        vGarden.setOnClickListener {
            val intent = Intent(this, DetailActivity::class.java)
            //put extra data into our intent, done it with a Parcelable object
            intent.putExtra("place", garden)
            //start the activity based on those details
             startActivity(intent)
           // startForResult.launch(intent)

        }
    }
}