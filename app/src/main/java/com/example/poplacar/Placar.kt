package com.example.poplacar

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.LinearLayout
import java.util.*


class Placar : AppCompatActivity() {

    private lateinit var textoTime1: TextView
    private lateinit var textoTime2: TextView
    private lateinit var textoTempo: TextView
    private lateinit var pontosTimeA: TextView
    private lateinit var pontosTimeB: TextView
    private lateinit var textoPontuacao: TextView
    data class EstadoPlacar(
        val pontosTimeA: Int,
        val pontosTimeB: Int
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        setContentView(R.layout.activity_placar)


        val decorView = window.decorView
        decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY


        val pilhaEstados = Stack<EstadoPlacar>()
        textoTime1 = findViewById(R.id.settime1)
        textoTime2 = findViewById(R.id.settime2)
        //textoTempo = findViewById(R.id.settempo)
        pontosTimeA = findViewById<TextView>(R.id.PontosTimeAView)
        pontosTimeB = findViewById<TextView>(R.id.PontosTimeBView)


        val position = intent.getIntExtra("position", -1)
        if (position != -1) {
            val configuracoesPartidaList = ConfiguracaoPartidaManager.getConfiguracoesPartidaList()
            if (position < configuracoesPartidaList.size) {
                val configuracaoPartida = configuracoesPartidaList[position]
                // Use as informações do objeto configuracaoPartida para preencher os TextViews
                textoTime1.text = configuracaoPartida.time1
                textoTime2.text = configuracaoPartida.time2
                //textoTempo.text = configuracaoPartida.tempo
                pontosTimeA.text = configuracaoPartida.pontosTimeA.toString()
                pontosTimeB.text = configuracaoPartida.pontosTimeB.toString()

            } else {
                // Posição inválida, faça o tratamento apropriado aqui
            }
        } else {
            // Nenhuma posição fornecida, faça o tratamento apropriado aqui
        }

        pontosTimeA.setOnClickListener(View.OnClickListener {
            val estadoAtual = EstadoPlacar(
                pontosTimeA.text.toString().toInt(),
                pontosTimeB.text.toString().toInt()
            )
            pilhaEstados.push(estadoAtual)

            val contadorTimeA = pontosTimeA.text.toString().toInt() + 1
            pontosTimeA.text = contadorTimeA.toString()
        })
        pontosTimeB.setOnClickListener(View.OnClickListener {
            val estadoAtual = EstadoPlacar(
                pontosTimeA.text.toString().toInt(),
                pontosTimeB.text.toString().toInt()
            )
            pilhaEstados.push(estadoAtual)
            val contadorTimeB = pontosTimeB.text.toString().toInt() + 1
            pontosTimeB.text = contadorTimeB.toString()
        })
        val myButton = findViewById<LinearLayout>(R.id.myButton)
        myButton.setOnClickListener {
            if (!pilhaEstados.empty()) {
                val estadoAnterior = pilhaEstados.pop()
                pontosTimeA.text = estadoAnterior.pontosTimeA.toString()
                pontosTimeB.text = estadoAnterior.pontosTimeB.toString()
            }
        }







    }

    fun salvarEstado() {
        val estadoPlacar = EstadoPlacar(
            pontosTimeA = pontosTimeA.text.toString().toInt(),
            pontosTimeB = pontosTimeB.text.toString().toInt()
        )

        val position = intent.getIntExtra("position", -1)
        if (position != -1) {
            val configuracoesPartidaList = ConfiguracaoPartidaManager.getConfiguracoesPartidaList().toMutableList()
            if (position < configuracoesPartidaList.size) {
                val configuracaoPartida = configuracoesPartidaList[position]
                configuracaoPartida.pontosTimeA = estadoPlacar.pontosTimeA
                configuracaoPartida.pontosTimeB = estadoPlacar.pontosTimeB


                configuracoesPartidaList[position] = configuracaoPartida
                // Atualizar a lista no SharedPreferences
                ConfiguracaoPartidaManager.setConfiguracoesPartidaList(this, configuracoesPartidaList)

                // Salvar a lista atualizada no SharedPreferences
                ConfiguracaoPartidaManager.saveConfiguracoesPartidaList(this)
            }
        }
    }
    override fun onStop() {
        super.onStop()

        salvarEstado()
    }

    override fun onPause() {
        super.onPause()

        salvarEstado()
    }
}



