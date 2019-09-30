package com.robnewport.codesample.DataClasses

data class VehicleBookings (
    val vehicleBookings: Array<VehicleBooking>
)  : java.io.Serializable  {
    init {
        this.vehicleBookings
    }
}

data class VehicleBooking (
    val id: Int,
    val startTime: String,
    val endTime: String,
    val vehicleId: Int,
    val podId: Int,
    val estimatedCost: Float,
    val fuelPin: String,
    val freeKmsTotal: Int
) : java.io.Serializable