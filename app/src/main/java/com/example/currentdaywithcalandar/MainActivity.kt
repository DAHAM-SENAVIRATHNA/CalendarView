package com.example.currentdaywithcalandar
import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import com.example.currentdaywithcalandar.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {

    //New Activity
    @SuppressLint("SimpleDateFormat")
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.current.setOnClickListener {
            setDate()
        }

        // The button from the layout file
        val mButton = findViewById<Button>(R.id.Activitybutton2)

        // Event start and end time with date
        val startTime = "2022-02-1T09:00:00"
        val endTime = "2022-02-1T12:00:00"

        // Parsing the date and time
        val mSimpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        val mStartTime = mSimpleDateFormat.parse(startTime)
        val mEndTime = mSimpleDateFormat.parse(endTime)


        // When Button is clicked, Intent started
        // To create an event with given time
        mButton.setOnClickListener {
            val mIntent = Intent(Intent.ACTION_EDIT)
            mIntent.type = "vnd.android.cursor.item/event"
            mIntent.putExtra("beginTime", mStartTime.time)
            mIntent.putExtra("time", true)
            mIntent.putExtra("rule", "FREQ=YEARLY")
            mIntent.putExtra("endTime", mEndTime.time)
            mIntent.putExtra("title", "My Event")
            startActivity(mIntent)
        }
    }

    //Main Activity
    private fun setDate() {
        val datePicker = Calendar.getInstance()
        val date =
            DatePickerDialog.OnDateSetListener { view: DatePicker?, year: Int, month: Int, dayOfMonth: Int ->
                datePicker[Calendar.YEAR] = year
                datePicker[Calendar.MONTH] = month
                datePicker[Calendar.DAY_OF_MONTH] = dayOfMonth
                val dateFormat = "dd-MMMM-yyyy"
                val simpleDateFormat = SimpleDateFormat(
                    dateFormat,
                    Locale.getDefault()
                )
                binding.calander.text = simpleDateFormat.format(
                    datePicker.time
                )
            }
        DatePickerDialog(
            this@MainActivity, date,
            datePicker[Calendar.YEAR],
            datePicker[Calendar.MONTH],
            datePicker[Calendar.DAY_OF_MONTH]
        ).show()
    }
}


