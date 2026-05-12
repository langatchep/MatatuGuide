package com.joyline.matatuguide.navigation

object Routes {

    const val SPLASH = "splash"
    const val ONBOARDING = "onboarding"
    const val HOME = "home"
    const val LOGIN = "login"
    const val REGISTER = "register"
    const val SAVED = "saved"
    const val ANALYTICS = "analytics"
    const val ADMIN_DASHBOARD = "admin_dashboard"
    const val PROFILE = "profile"

    const val RESULTS = "results/{from}/{to}"
    const val DETAILS = "details/{start}/{end}/{stages}/{fare}"
    const val MAPS = "maps/{start}/{end}"

    fun results(from: String, to: String) = "results/$from/$to"
    fun details(start: String, end: String, stages: String, fare: String) = "details/$start/$end/$stages/$fare"
    fun maps(start: String, end: String) = "maps/$start/$end"
}
