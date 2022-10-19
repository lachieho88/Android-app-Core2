package au.edu.swin.sdmd.core2

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {

    lateinit var university: Place
    lateinit var station: Place
    lateinit var hall: Place
    lateinit var garden: Place
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
         university = Place(1, "university", "Hawthorn", "24/09/2019", 1.0.toFloat())
         station = Place(2, "station", "Flinders", "13/03/2017", 2.0.toFloat())
         hall = Place(3, "hall", "london", "15/05/2018", 4.0.toFloat())
         garden = Place(4, "garden", "Melbourne city", "28/10/2023", 5.0.toFloat())

        initialiseVariables()
        //When an image is clicked, a new screen with further details needs to be shown along with the image.
        // The data needs to be passed as a Parcelable object
        val vUniversity = findViewById<ImageView>(R.id.UniversityImage)
        vUniversity.setOnClickListener {
            startIntent(university)
        }

        val vStation = findViewById<ImageView>(R.id.StationIamge)
        vStation.setOnClickListener {
            startIntent(station)

        }
        val vHall = findViewById<ImageView>(R.id.HallImage)
        vHall.setOnClickListener {
            startIntent(hall)
        }

        val vGarden = findViewById<ImageView>(R.id.GardenImage)
        vGarden.setOnClickListener {
            startIntent(garden)

        }
    }
    val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult())
    {
        // Log.i("RESULT","returned ${it.data.toString()}")

            result ->
        when (result.resultCode) {
            RESULT_OK -> {
                val data = result.data
                //then update your rating text view with the new value
              //  val ratings = data?.getFloatExtra("rating", (-1.0).toFloat())
                val ratings = data?.getParcelableExtra<Place>("rating")
                val vUniversityRating = findViewById<TextView>(R.id.University_Rating)
                val vStationRating = findViewById<TextView>(R.id.stationRating)
                val vHallRating = findViewById<TextView>(R.id.HallRating)
                val vGardenRating = findViewById<TextView>(R.id.GardenRating)
                ratings?.let {
                    when(it.imageId){
                        1->{
                            university = ratings
                            vUniversityRating.text = university.rating.toString()
                        }
                        2->{
                            station = ratings
                            vStationRating.text = station.rating.toString()
                        }
                        3->
                        {
                            hall = ratings
                            vHallRating.text = hall.rating.toString()
                        }
                        else ->
                        {
                            garden = ratings
                            vGardenRating.text = garden.rating.toString()
                        }
                    }
                }
            }

        }
    }



    fun startIntent(place: Place) {
        val intent = Intent(this, DetailActivity::class.java)
        //put extra data into our intent, done it with a Parcelable object
        intent.putExtra("place", place)
        //start the activity based on those details
       // startActivity(intent)
         startForResult.launch(intent)
    }
    fun initialiseVariables()
    {
        val vUniversityRating = findViewById<TextView>(R.id.University_Rating)
        val vStationRating = findViewById<TextView>(R.id.stationRating)
        val vHallRating = findViewById<TextView>(R.id.HallRating)
        val vGardenRating = findViewById<TextView>(R.id.GardenRating)

        vUniversityRating.text = university.rating.toString()
        vStationRating.text = station.rating.toString()
        vHallRating.text = hall.rating.toString()
        vGardenRating.text = garden.rating.toString()

    }
}