package com.idealista.android.challenge.core.model

import com.idealista.android.challenge.core.CoreAssembler
import com.idealista.android.challenge.core.R

sealed class Typology {
    object Studio : Typology()
    object Flat : Typology()
    object Penthouse : Typology()
    object Duplex : Typology()
    object Chalet : Typology()
    object CountryHouse : Typology()
    object Unknown : Typology()

    companion object {
        fun from(typology: String): Typology {
            return when (typology) {
                "studio" -> Studio
                "flat" -> Flat
                "penthouse" -> Penthouse
                "duplex" -> Duplex
                "chalet" -> Chalet
                "countryHouse" -> CountryHouse
                else -> Unknown
            }
        }
    }

    fun stringId(): Int {
        return when (this) {
            Studio -> R.string.typology_studio
            Flat -> R.string.typology_flat
            Penthouse -> R.string.typology_penthouse
            Duplex -> R.string.typology_duplex
            Chalet -> R.string.typology_chalet
            CountryHouse -> R.string.typology_countryHouse
            Unknown -> 0
        }
    }
}

fun Typology.string() = if (this !is Typology.Unknown) CoreAssembler.stringsProvider.string(stringId()) else ""