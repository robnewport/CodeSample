package com.robnewport.codesample.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.robnewport.codesample.DataClasses.*
import com.robnewport.codesample.Utils.Utils

class ViewModel(application: Application) : AndroidViewModel(application) {

    private val context = getApplication<Application>().applicationContext

    fun vehicleBookings(): LiveData<VehicleBookings> = vehicleBookingsLiveData
    fun pods(): LiveData<Pods> = podsLiveData
    fun vehicles(): LiveData<Vehicles> = vehiclesData

    fun findVehicle(byId: Int?) : Vehicle? {
        return vehicles().value?.Vehicles?.filter { it.id == byId }?.first()
    }

    fun findPod(byId: Int?) : Pod? {
        return pods().value?.pods?.filter { it.id == byId }?.first()
    }

    private val vehicleBookingsLiveData by lazy {
        val liveData = MutableLiveData<VehicleBookings>()
        var jsonFile = Utils.readJSONFromAsset(context, "vehicleBookings.json")
        val data = Gson().fromJson(jsonFile, VehicleBookings::class.java)
        liveData.value = data
        return@lazy liveData
    }

    private val podsLiveData by lazy {
        val liveData = MutableLiveData<Pods>()
        var jsonFile = Utils.readJSONFromAsset(context, "pods.json")
        val data = Gson().fromJson(jsonFile, Pods::class.java)
        liveData.value = data
        return@lazy liveData
    }

    private val vehiclesData by lazy {
        val liveData = MutableLiveData<Vehicles>()
        var jsonFile = Utils.readJSONFromAsset(context, "vehicles.json")
        val data = Gson().fromJson(jsonFile, Vehicles::class.java)
        liveData.value = data
        return@lazy liveData
    }
}
