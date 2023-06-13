package com.example.poplacar

data class ConfiguracaoPartida(
    val time1: String,
    val time2: String,
    var tempo: String,
    val pontosVitoria: Int,
    var pontosTimeA: Int = 0,
    var pontosTimeB: Int = 0
)


