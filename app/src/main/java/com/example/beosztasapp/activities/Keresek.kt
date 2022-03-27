package com.example.beosztasapp.activities

enum class StatuszK {
    TAPPENZ, SZABADSAG, EGYEB
}

enum class Allapot {
    ELINDITVA, ELFOGADVA
}

class Keresek(
    var azon: Int = -1,
    var szemely_id: Int = -1,
    var datum: String = "",
    var statusz: StatuszK = StatuszK.SZABADSAG,
    var allapot: Allapot = Allapot.ELINDITVA
)
