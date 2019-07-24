package com.dwolla.shared

import com.google.gson.TypeAdapter
import com.google.gson.annotations.JsonAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter

@JsonAdapter(DateOfBirthJsonAdapter::class)
data class DateOfBirth(
    @JvmField val year: Int,
    @JvmField val month: Int,
    @JvmField val day: Int
) {
    constructor(
        year: String,
        month: String,
        day: String
    ) : this(
        year.toInt(),
        month.toInt(),
        day.toInt()
    )
}

private class DateOfBirthJsonAdapter : TypeAdapter<DateOfBirth>() {
    override fun read(`in`: JsonReader?): DateOfBirth {
        throw NotImplementedError()
    }

    override fun write(out: JsonWriter?, value: DateOfBirth?) {
        if (out != null && value != null)
            out.value(serialize(value))
    }

    private fun serialize(dob: DateOfBirth): String {
        return "${dob.year}-${zeroPadded(dob.month)}-${zeroPadded(dob.day)}"
    }

    private fun zeroPadded(i: Int): String {
        return i.toString().padStart(2, '0')
    }
}
