package com.robnewport.codesample.DataClasses

data class Vehicles (
    val Vehicles: Array<Vehicle>
) : java.io.Serializable

data class Vehicle (
    val id: Int,
    val name: String,
    val description: String,
    val fuelType: String,
    val capacity: Int,
    val numberPlate: String,
    val image: String
) : java.io.Serializable