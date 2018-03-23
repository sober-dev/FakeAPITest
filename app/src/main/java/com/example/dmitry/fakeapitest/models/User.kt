package com.example.dmitry.fakeapitest.models

import com.google.gson.annotations.SerializedName

data class User(
        @SerializedName("id") val id: Int,
        @SerializedName("name") val name: String,
        @SerializedName("username") val username: String,
        @SerializedName("email") val email: String,
        @SerializedName("address") val address: Address,
        @SerializedName("phone") val phone: String,
        @SerializedName("website") val website: String,
        @SerializedName("company") val company: Company
) {
    override fun toString() = StringBuilder()
            .append("name: $name")
            .append(", username: $username")
            .append(", email: $email")
            .append(", phone: $phone")
            .append(", website: $website")

            .append(", address: ")
            .append("(street: " + address.street)
            .append(", suite: " + address.suite)
            .append(", city: " + address.city)
            .append(", zipcode: " + address.zipcode)
            .append(", geo: (lat:" + address.geo.lat + ", lng:" + address.geo.lng + "))")

            .append(", company: ")
            .append("(name: " + company.name)
            .append(", catchPhrase: " + company.catchPhrase)
            .append(", bs: " + company.bs + ")")

            .toString()
}

data class Address(
        @SerializedName("street") val street: String,
        @SerializedName("suite") val suite: String,
        @SerializedName("city") val city: String,
        @SerializedName("zipcode") val zipcode: String,
        @SerializedName("geo") val geo: Geo
)

data class Geo(
        @SerializedName("lat") val lat: String,
        @SerializedName("lng") val lng: String
)

data class Company(
        @SerializedName("name") val name: String,
        @SerializedName("catchPhrase") val catchPhrase: String,
        @SerializedName("bs") val bs: String
)