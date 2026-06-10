package com.example.practicatpi.utils

fun normalizeFlag(code: String): String {
    return when (code.lowercase().replace(".png", "")) {

        "argentina" -> "ar"
        "mexico" -> "mx"
        "uruguay" -> "uy"
        "panama" -> "pa"

        "brasil", "brazil" -> "br"
        "suiza", "switzerland" -> "ch"
        "croacia", "croatia" -> "hr"
        "japon", "japan" -> "jp"

        "francia", "france" -> "fr"
        "canada" -> "ca"
        "marruecos", "morocco" -> "ma"
        "corea del sur", "south-korea" -> "kr"

        "españa", "spain" -> "es"
        "estados unidos", "united-states" -> "us"
        "senegal" -> "sn"
        "australia" -> "au"

        "alemania", "germany" -> "de"
        "colombia" -> "co"
        "egipto", "egypt" -> "eg"
        "iran" -> "ir"

        "inglaterra", "england" -> "gb"
        "ecuador" -> "ec"
        "tunez", "tunisia" -> "tn"
        "arabia saudita", "saudi-arabia" -> "sa"

        "portugal" -> "pt"
        "paraguay" -> "py"
        "argelia", "algeria" -> "dz"
        "qatar" -> "qa"

        "paises bajos", "netherlands" -> "nl"
        "peru" -> "pe"
        "costa de marfil", "ivory-coast" -> "ci"
        "uzbekistan" -> "uz"

        else -> "ar"
    }
}