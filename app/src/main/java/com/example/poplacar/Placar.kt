package com.example.poplacar

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Placar : AppCompatActivity() {

    private lateinit var textoTime1: TextView
    private lateinit var textoTime2: TextView
    private lateinit var textoTempo: TextView
    private lateinit var textoPontuacao: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_placar)


        textoTime1 = findViewById(R.id.settime1)
        textoTime2 = findViewById(R.id.settime2)
        textoTempo = findViewById(R.id.settempo)


        val position = intent.getIntExtra("position", -1)
        if (position != -1) {
            val configuracoesPartidaList = ConfiguracaoPartidaManager.getConfiguracoesPartidaList()
            if (position < configuracoesPartidaList.size) {
                val configuracaoPartida = configuracoesPartidaList[position]
                // Use as informações do objeto configuracaoPartida para preencher os TextViews
                textoTime1.text = configuracaoPartida.time1
                textoTime2.text = configuracaoPartida.time2
                textoTempo.text = configuracaoPartida.tempo
            } else {
                // Posição inválida, faça o tratamento apropriado aqui
            }
        } else {
            // Nenhuma posição fornecida, faça o tratamento apropriado aqui
        }

    }
}