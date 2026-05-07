package com.joyline.matatuguide.navigation

import android.net.Uri


const val ROUT_SPLASH = "splash"
const val ROUT_HOME = "home"
const val ROUT_LOGIN = "login"
const val ROUT_REGISTER = "register"
const val ROUT_SAVED = "saved"


const val ROUT_RESULTS = "results/{from}/{to}"
const val ROUT_DETAILS = "details/{start}/{end}/{stages}/{fare}"
const val ROUT_MAPS = "map/{start}/{end}"



fun resultsRoute(from: String, to: String): String {
    return "results/${Uri.encode(from)}/${Uri.encode(to)}"
}

fun detailsRoute(
    start: String,
    end: String,
    stages: String,
    fare: String
): String {
    return "details/${Uri.encode(start)}/${Uri.encode(end)}/${Uri.encode(stages)}/${Uri.encode(fare)}"
}

fun mapRoute(start: String, end: String): String {
    return "map/${Uri.encode(start)}/${Uri.encode(end)}"
}