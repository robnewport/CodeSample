package com.robnewport.codesample.Utils

import android.content.Context
import java.io.InputStream
import java.time.Duration
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class Utils{
    companion object {

        @JvmStatic
        fun dateFrom(string: String) : LocalDateTime {
            val format = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssX")
            val date = LocalDateTime.parse(string, format)
            return date
        }

        @JvmStatic
        fun stringFrom(date: LocalDateTime, pattern: String) : String {
            var format = DateTimeFormatter.ofPattern(pattern)
            var stringFromConvertedDate = date.format(format)
            return stringFromConvertedDate
        }

        @JvmStatic
        fun bookingDateFormatter(string1: String, string2: String) : String {
            if (string1 != string2) {
                return "%s - %s".format(string1, string2)
            }
            return string1
        }

        @JvmStatic
        fun detailDateFormatter(string1: String, string2: String) : String {
            return "%s, %s".format(string1, string2)
        }

        @JvmStatic
        fun hoursBetween(date1: LocalDateTime, date2: LocalDateTime) : Long {
            val hours = Duration.between(date1, date2).toHours()
            return hours
        }

        @JvmStatic
        fun readJSONFromAsset(context: Context, filename: String): String? {
            var json: String?
            try {
                val  inputStream: InputStream = context.assets.open(filename)
                json = inputStream.bufferedReader().use{it.readText()}
            } catch (ex: Exception) {
                ex.printStackTrace()
                return null
            }
            return json
        }

    }
}