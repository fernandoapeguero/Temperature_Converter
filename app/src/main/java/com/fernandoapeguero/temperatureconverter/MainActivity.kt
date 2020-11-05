package com.fernandoapeguero.temperatureconverter

import android.os.Bundle
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import com.fernandoapeguero.temperatureconverter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.convertTempButton.setOnClickListener {
            convertTemperature()
        }

        binding.tempMeasurementsOption.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { group, id ->
            when(id){
                R.id.farenheit_to_celcius -> binding.convertingToTextviewDisplay.text = "Farenheit To Celcius"
                R.id.kelvin_to_celcius -> binding.convertingToTextviewDisplay.text = "Kelvin To Celcius"
                R.id.kelvin_to_farenheit -> binding.convertingToTextviewDisplay.text = "Kelvin To Farenheit"
            }

        }
        )

        binding.measurementsSwitch.setOnClickListener {

            if (binding.measurementsSwitch.isChecked ){

                when(binding.tempMeasurementsOption.checkedRadioButtonId){
                    R.id.farenheit_to_celcius -> binding.convertingToTextviewDisplay.text = "Celsius to Fahrenheit"
                    R.id.kelvin_to_celcius -> binding.convertingToTextviewDisplay.text = "Celcius To Kelvin"
                    R.id.kelvin_to_farenheit -> binding.convertingToTextviewDisplay.text = "Farenheit To Kelvin"
                }
            } else {
                when(binding.tempMeasurementsOption.checkedRadioButtonId){
                    R.id.farenheit_to_celcius -> binding.convertingToTextviewDisplay.text = "Farenheit To Celcius"
                    R.id.kelvin_to_celcius -> binding.convertingToTextviewDisplay.text = "Kelvin To Celcius"
                    R.id.kelvin_to_farenheit -> binding.convertingToTextviewDisplay.text = "Kelvin To Farenheit"
                }
            }

        }
    }




    fun convertTemperature () {
        
        val temperatureInput = binding.temperatureInput.text.toString()

        val temp = temperatureInput.toDoubleOrNull()

        if(temp == null) {
            binding.convertionResult.text = ""
            return
        }



        var convertionResult  = 0.0

        val buttonIsChecked = binding.measurementsSwitch.isChecked


        if (binding.tempMeasurementsOption.checkedRadioButtonId == R.id.farenheit_to_celcius && !buttonIsChecked){
            //converting fahrenheit to celcius
            convertionResult = (temp - 32) * 5/9
        } else if (binding.tempMeasurementsOption.checkedRadioButtonId == R.id.farenheit_to_celcius && buttonIsChecked){
            // converting celcius to fahrenheit
            convertionResult = (temp * 9/5) +32
        } else if (binding.tempMeasurementsOption.checkedRadioButtonId == R.id.kelvin_to_celcius && !buttonIsChecked){
//            converting kelvin to celcius
            convertionResult = temp  - 273.15
        } else if(binding.tempMeasurementsOption.checkedRadioButtonId == R.id.kelvin_to_celcius && buttonIsChecked){
            // converting celius to kelvin
            convertionResult = temp + 273.15
        } else if (binding.tempMeasurementsOption.checkedRadioButtonId == R.id.kelvin_to_farenheit && buttonIsChecked ) {
            //converting kelving to fahrenheit
            convertionResult =  (temp - 273.15) * 9/5 + 32
        } else if (binding.tempMeasurementsOption.checkedRadioButtonId == R.id.kelvin_to_farenheit && !buttonIsChecked ){
            //converting fahrenheit to Kelvins
            convertionResult = (temp - 32) * 5/9 + 273.15
        }

        binding.convertionResult.text = convertionResult.toString()
        



    }
}