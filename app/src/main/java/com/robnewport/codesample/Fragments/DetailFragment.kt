package com.robnewport.codesample.Fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.robnewport.codesample.DataClasses.*
import com.robnewport.codesample.Utils.DownloadVehicleImageTask
import com.robnewport.codesample.Utils.Utils
import com.robnewport.codesample.ViewModel.ViewModel
import kotlinx.android.synthetic.main.detail_fragment.*
import java.io.Serializable
import java.text.NumberFormat
import java.util.*

class DetailFragment: Fragment() {

    companion object {
        @JvmStatic
        fun newInstance(vehicleBooking: Serializable) = DetailFragment().apply {
            arguments = Bundle().apply {
                putSerializable("vehicleBooking", vehicleBooking)
            }
        }
    }

    private lateinit var viewModel: ViewModel
    private var vehicleBooking: VehicleBooking? = null
    private val MY_PERMISSIONS_REQUEST_INTERNET = 123

    override fun onAttach(context: Context) {
        super.onAttach(context)
        arguments?.getSerializable("vehicleBooking")?.let {
            this.vehicleBooking = it as VehicleBooking
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(com.robnewport.codesample.R.layout.detail_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val booking = this.vehicleBooking
        if(booking == null) { return }

        populateBookingFields(booking)

        val vehiclesObserver = Observer<Vehicles> { vehicles ->
            val vehicle = viewModel.findVehicle(booking.vehicleId)
            populateVehicleFields(vehicle)
        }

        val podsObserver = Observer<Pods> { pods ->
            val pod = viewModel.findPod(booking.podId)
            populatePodFields(pod)
        }

        viewModel = ViewModelProviders.of(this)[ViewModel::class.java]

        viewModel.vehicles().observe(this, vehiclesObserver)
        viewModel.pods().observe(this, podsObserver)
    }

    fun populateBookingFields(booking: VehicleBooking) {
        starts.text = Utils.detailDateFormatter(
            Utils.stringFrom(Utils.dateFrom(booking.startTime),
                "EEEE dd MMM"),
            Utils.stringFrom(Utils.dateFrom(booking.startTime),
                "h:mm a"))

        ends.text = Utils.detailDateFormatter(
            Utils.stringFrom(Utils.dateFrom(booking.endTime),
                "EEEE dd MMM"),
            Utils.stringFrom(Utils.dateFrom(booking.endTime),
                "h:mm a"))

        duration.text = "%d hours".format(Utils.hoursBetween(
            Utils.dateFrom(booking.startTime),
            Utils.dateFrom(booking.endTime))
        )

        val format = NumberFormat.getCurrencyInstance(Locale.US)
        val currency = format.format(booking.estimatedCost)

        estimatedCost.text = currency
        fuelPin.text = booking.fuelPin
    }

    fun populateVehicleFields(vehicle: Vehicle?){
        vehicleName.text = vehicle?.name
        vehicleCapacity.text = vehicle?.capacity.toString()
        vehiclePlate.text = vehicle?.numberPlate
        val vehicleUrl = vehicle?.image
        DownloadVehicleImageTask(vehicleThumbnail).execute(vehicleUrl)
    }

    fun populatePodFields(pod: Pod?) {
        podName.text = pod?.name
        podDescription.text = pod?.description
    }
}