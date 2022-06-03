package com.dwolla

interface Environment {
    fun tokenUrl(): String
    fun apiBaseUrl(): String
    fun authBaseUrl(): String
}
