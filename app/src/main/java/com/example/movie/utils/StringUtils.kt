package com.example.movie.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**  input: 2020-09-05
 *   output: 05 September 2020 */
fun String.formatDate(): String {
    val parser = SimpleDateFormat("yyyy-MM-dd", Locale.US)
    val formatter = SimpleDateFormat("dd MMMM yyyy", Locale.US)

    return try {
        val date = parser.parse(this) ?: return ""
        formatter.format(date)
    } catch (parseException: ParseException) {
        throw IllegalArgumentException("Invalid datetime format")
    }
}
