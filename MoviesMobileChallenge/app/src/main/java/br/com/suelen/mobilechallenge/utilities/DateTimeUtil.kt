package br.com.suelen.mobilechallenge.utilities

import java.text.SimpleDateFormat
import java.util.*

class DateTimeUtil {
    companion object {
        fun parseDate(dateString: String, inputFormat: String = "yyyy-MM-dd"): Date? {
            try {
                val dataFormatInput = SimpleDateFormat(inputFormat, Locale.getDefault())
                return dataFormatInput.parse(dateString)!!
            } catch(e:Exception) { }
            return null
        }

        fun reformatStringDate(
            inputStringDate: String,
            inputFormat: String = "yyyy-MM-dd",
            outputFormat: String = "dd/MM/yy"
        ): String {
            val dataFormatInput = SimpleDateFormat(inputFormat, Locale.getDefault())
            val dataFormatOutput = SimpleDateFormat(outputFormat, Locale.getDefault())
            try {
                val parsed = dataFormatInput.parse(inputStringDate)!!
                val output = dataFormatOutput.format(parsed)
                return output.toUpperCase(Locale.getDefault())
            } catch(e:Exception) { }

            return inputStringDate
        }
    }
}