package com.joyline.matatuguide.model

data class SavedRoutes(

    val id: String = "",

    val from: String = "",

    val to: String = "",

    val matatuName: String = "",

    val boardingStage: String = "",

    val dropOffStage: String = "",

    val fare: String = "",

    val dateSaved: Long = System.currentTimeMillis()

)