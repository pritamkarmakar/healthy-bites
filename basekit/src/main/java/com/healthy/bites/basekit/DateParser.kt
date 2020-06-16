package com.healthy.bites.basekit

/**
 * Dateparser contract, implementation is in basekitimpl module
 */
interface DateParser {
    fun parseDate(startDate: String): String
}