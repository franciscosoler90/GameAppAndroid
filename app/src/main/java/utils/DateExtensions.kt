package utils

import entity.enums.ConverterDate
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun String.convertDateTo(
    desiredFormat: ConverterDate,
    originalFormat: ConverterDate = ConverterDate.SQL_DATE
): String? {
    return if (this.isNotEmpty()) {

        return try {
            val originalDateFormat = SimpleDateFormat(originalFormat.formatter, Locale.getDefault())
            val date = originalDateFormat.parse(this) ?: Date()

            val desiredDateFormat = SimpleDateFormat(desiredFormat.formatter, Locale("es", "ES"))
            desiredDateFormat.format(date)
        } catch (e: Exception) {
            null
        }

    } else {
        "-"
    }
}
