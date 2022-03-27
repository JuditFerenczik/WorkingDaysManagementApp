package com.example.beosztasapp.activities

enum class Statusz {
    LEDOLGOZOTT, TAPPENZ, SZABADSAG, FIZ_UNNEP, PIHENO
}

data class Jelenletik(
    var szemely_id: Int = -1,
    var datum: String = "",
    var statusz: Statusz = Statusz.LEDOLGOZOTT,

    )
