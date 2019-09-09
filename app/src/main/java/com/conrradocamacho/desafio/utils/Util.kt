package com.conrradocamacho.desafio.utils

import android.util.Log
import com.conrradocamacho.desafio.network.bean.Item
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Conrrado Camacho on 08/09/2019.
 * con.webmaster@gmail.com
 */
object Util {

    const val formatDateServer = "yyyy-MM-dd"
    private const val formatDateYear = "yyyy"
    private const val formatBrazilianDate = "dd/MM/yy"

    fun getYearFromDateServer(dateServer: String?): String {
        try {
            var format = SimpleDateFormat(formatDateServer, Locale.US)
            val date = format.parse(dateServer ?: "")

            if (date is Date) {
                format = SimpleDateFormat(formatDateYear, Locale.US)
                return format.format(date)
            }
        }
        catch (e: Exception) {
            Log.d("getYearFromDateServer", e.message ?: "")
        }
        return ""
    }

    fun getBrazilianDateFromDateServer(dateServer: String?): String {
        try {
            var format = SimpleDateFormat(formatDateServer, Locale.US)
            val date = format.parse(dateServer ?: "")

            if (date is Date) {
                format = SimpleDateFormat(formatBrazilianDate, Locale.US)
                return format.format(date)
            }
        }
        catch (e: Exception) {
            Log.d("getYearFromDateServer", e.message ?: "")
        }
        return ""
    }

    fun getTextFromItemList(itemList: List<Item>): String {
        var text = ""
        for (item in itemList) {
            if (text == "") {
                text = item.name
            }
            else {
                text = "$text, ${item.name}"
            }
        }
        return text
    }
}