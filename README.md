# CodeSample-Kotlin

Explanations and Assumptions

The ViewModel implements a mediator pattern to organise access to the back-end logic around both the recycler view seen in the BookingAdapter, and the DetailFragment inside the DetailActivity. This simplifies and abstracts the state and behaviour via observers inside the fragments, which are used to see state changes in the ViewModel live data values. The live data acts as a bridge between the repository and these views through the observers, thus defining the MVVM pattern.

Data classes are used to take advantage of Kotlin’s modern data encapsulation abilities. Much like Swift’s Codable type alias for the Encodable and Decodable protocols, Kotlin data classes can provide a clean way to convert JSON into objects as is seen for Pods, Vehicle Bookings, and Vehicles with the provided JSON data. It is assumed that JSON data will be provided through a REST API in production, which the data classes can adapt to adequately. 

Testing the date field using both string and java date objects are included to demonstrate test coverage for simulated JSON date fields. 

# Summary of included source code

Activities
- DetailActivity
- MainActivity

Adapter
- BookingAdapter

DataClasses
- Pods
- VehicleBookings
- Vehicles

Fragments
- DetailFragment
- MainFragment

Utils
- DowloadVehicleImageTask
- Utils

ViewModel
- ViewModel
