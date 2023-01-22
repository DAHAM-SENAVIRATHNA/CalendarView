package com.example.currentdaywithcalandar
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

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val SecondActButton = findViewById<Button>(R.id.EventBtn)
        SecondActButton.setOnClickListener{

            val Intent = Intent(this,Activity2::class.java)
            startActivity(Intent)
        }
        binding.current.setOnClickListener {
            setDate()

        }

    }
    private fun setToolbar(){
        supportActionBar?.title = "Current_Day"

    }

    private fun setDate(){
        val datePicker = Calendar.getInstance()
        val date = DatePickerDialog.OnDateSetListener{
                view: DatePicker?,year: Int,month: Int,dayOfMonth: Int ->
            datePicker[Calendar.YEAR] = year
            datePicker[Calendar.MONTH]= month
            datePicker[Calendar.DAY_OF_MONTH] = dayOfMonth
            val dateFormat = "dd-MMMM-yyyy"
            val simpleDateFormat = SimpleDateFormat(dateFormat,
                Locale.getDefault()
            )
            binding.calander.text = simpleDateFormat.format(
                datePicker.time
            )
        }
        DatePickerDialog(
            this@MainActivity,date,
            datePicker[Calendar.YEAR],
            datePicker[Calendar.MONTH],
            datePicker[Calendar.DAY_OF_MONTH]
        ).show()
    }
}