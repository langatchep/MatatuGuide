package com.joyline.matatuguide.navigation

object Routes {

    const val SPLASH = "splash"
    const val HOME = "home"
    const val LOGIN = "login"
    const val REGISTER = "register"
    const val SAVED = "saved"

    const val RESULTS = "results/{from}/{to}"
    const val DETAILS = "details/{start}/{end}/{stages}/{fare}"
    const val MAPS = "maps/{start}/{end}"

    // ---- helpers (SAFE NAVIGATION BUILDERS) ----

    fun results(from: String, to: String) =
        "results/$from/$to"

    fun details(start: String, end: String, stages: String, fare: String) =
        "details/$start/$end/$stages/$fare"

    fun maps(start: String, end: String) =
        "maps/$start/$end"
}