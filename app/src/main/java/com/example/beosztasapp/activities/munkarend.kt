package com.example.beosztasapp.activities


data class munkarend(
    var munkarend_id: Int,
    var munkakozi_szunet: Int,
    var kezdes: String,
    var befejezes: String,
    var szunet_kezd: String,
    var szunet_vege: String,
    var ledolgozott_ora: Int
)