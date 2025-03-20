package com.sd.laborator.interfaces

interface CheckPermissionInterace {
    fun checkPermission(userCountry: String?, searchedCountryCode : String?) : Boolean
}