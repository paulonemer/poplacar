package com.example.poplacar

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Placar : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_placar)


        val position = intent.getIntExtra("position", -1)
        if (position != -1) {
            val sharedPreferences = getSharedPreferences("configuracoes_partida", Context.MODE_PRIVATE)
            val configuracoesPartidaJson = sharedPreferences.getString("configuracoes_partida_list", null)
            if (!configuracoesPartidaJson.isNullOrEmpty()) {
                val configuracoesPartidaList = Gson().fromJson(configuracoesPartidaJson, object : TypeToken<List<ConfiguracaoPartida>>() {}.type)
                val configuracaoPartida = configuracoesPartidaList[position]
                // Faça algo com a configuracaoPartida
            }
        }
    }
}