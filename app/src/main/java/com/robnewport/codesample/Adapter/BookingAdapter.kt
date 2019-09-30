package com.robnewport.codesample.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.robnewport.codesample.Activities.DetailActivity
import com.robnewport.codesample.DataClasses.VehicleBooking
import com.robnewport.codesample.R
import com.robnewport.codesample.Utils.Utils
import kotlinx.android.synthetic.main.booking_row.view.*

class BookingAdapter(private val myDataset: Array<VehicleBooking>) :

    RecyclerView.Adapter<BookingAdapter.BookingHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): BookingHolder {

        val rowView = LayoutInflater.from(parent.context)
            .inflate(R.layout.booking_row, parent, false) as LinearLayout

        return BookingHolder(rowView)
    }

    override fun onBindViewHolder(holder: BookingHolder, position: Int) {

        var vehicleBooking: VehicleBooking = myDataset[position]
        holder.bindBooking(vehicleBooking)
    }

    override fun getItemCount() = myDataset.size

    class BookingHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {

        var view: View = v
        private var vehicleBooking: VehicleBooking? = null

        fun bindBooking(vehicleBooking: VehicleBooking) {
            this.vehicleBooking = vehicleBooking

            view.date.text = Utils.bookingDateFormatter(
                Utils.stringFrom(Utils.dateFrom(vehicleBooking.startTime),
                    "E dd MMM"),
                Utils.stringFrom(Utils.dateFrom(vehicleBooking.endTime),
                    "EEE dd MMM"))

            view.time.text = Utils.bookingDateFormatter(
                Utils.stringFrom(Utils.dateFrom(vehicleBooking.startTime),
                    "h:mm a"),
                Utils.stringFrom(Utils.dateFrom(vehicleBooking.endTime),
                    "h:mm a"))
        }

        init {
            v.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            val context = itemView.context
            val showBookingDetailsIntent = Intent(context, DetailActivity::class.java)
            showBookingDetailsIntent.putExtra(BOOKING_DETAILS_KEY, this.vehicleBooking)
            context.startActivity(showBookingDetailsIntent)
        }

        companion object {
            private val BOOKING_DETAILS_KEY = "DETAILS"
        }
    }
}