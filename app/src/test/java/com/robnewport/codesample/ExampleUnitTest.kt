package com.robnewport.codesample

import androidx.fragment.app.testing.FragmentScenario
import androidx.lifecycle.ViewModelProviders
import com.robnewport.codesample.DataClasses.*
import com.robnewport.codesample.Fragments.MainFragment
import com.robnewport.codesample.Utils.Utils
import com.robnewport.codesample.ViewModel.ViewModel
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    val localDateTime = Utils.dateFrom("2017-12-13T09:00:00+11")
    val localDateTime2 = Utils.dateFrom("2017-12-13T10:00:00+11")

    @Test fun dateUtil_CorrectDateValue_ReturnsTrue() {
        assertEquals(localDateTime.dayOfMonth, 13)
        assertEquals(localDateTime.year, 2017)
        assertEquals(localDateTime.monthValue, 12)
    }

    @Test fun dateUtil_CorrectDateFormat_ReturnsTrue() {
        assertEquals(Utils.stringFrom(localDateTime, "EEEE"), "Wednesday")
        assertEquals(Utils.stringFrom(localDateTime, "EEE"), "Wed")
        assertEquals(Utils.stringFrom(localDateTime, "dd"), "13")
        assertEquals(Utils.stringFrom(localDateTime, "MMM"), "Dec")
    }

    @Test fun dateUtil_Hours_ReturnsTrue() {
        val hoursBetweenTwoDateTimes = Utils.hoursBetween(localDateTime, localDateTime2)
        assertEquals(hoursBetweenTwoDateTimes, 1)
    }
}
