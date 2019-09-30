package com.robnewport.codesample.Fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.robnewport.codesample.Adapter.BookingAdapter
import com.robnewport.codesample.DataClasses.VehicleBookings
import com.robnewport.codesample.R
import com.robnewport.codesample.ViewModel.ViewModel

class MainFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    companion object {
        fun newInstance() = MainFragment()
    }

    lateinit var viewModel: ViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this)[ViewModel::class.java]
        viewManager = LinearLayoutManager(requireContext())

        val bookingsObserver = Observer<VehicleBookings> { vehicleBookings ->
            viewAdapter =
                BookingAdapter(vehicleBookings.vehicleBookings)
            recyclerView = view.findViewById<RecyclerView>(R.id.my_recycler_view).apply {
                setHasFixedSize(true)
                layoutManager = viewManager
                adapter = viewAdapter
            }
        }
        viewModel.vehicleBookings().observe(this, bookingsObserver)
    }
}
