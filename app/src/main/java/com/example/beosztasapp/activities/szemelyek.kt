package com.example.beosztasapp.activities

data class szemelyek (
    var szemely_id: Int = -1,
    var nev: String = "",
    var adoazonosito: Int = -1,
    var fonok: Int = -1,
    var munkarend: Int = -1, //munkarend
    var belepes: String = "",
    var email: String = "",
    var jelszo: String = "",
    var eves_szabadsag: Int = 0,
    var heti_munkaido: Int = 0
        )