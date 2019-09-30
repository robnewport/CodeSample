package com.robnewport.codesample.DataClasses

data class Pods (
    val pods: Array<Pod>
) : java.io.Serializable

data class Pod (
    val id: Int,
    val name: String,
    val description: String,
    val lat: Double,
    val lon: Double
) : java.io.Serializable