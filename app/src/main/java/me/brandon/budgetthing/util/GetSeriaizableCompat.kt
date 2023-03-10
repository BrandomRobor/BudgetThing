package me.brandon.budgetthing.util

import android.os.Build
import android.os.Bundle

inline fun <reified T : java.io.Serializable> Bundle.getSerializableCompat(key: String): T? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getSerializable(key, T::class.java)
    } else {
        getSerializable(key) as T?
    }
}
