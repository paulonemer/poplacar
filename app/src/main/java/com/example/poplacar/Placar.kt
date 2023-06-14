package com.example.poplacar

import ConfiguracaoPartidaManager
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Chronometer
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*


class Placar : AppCompatActivity() {

    private lateinit var textoTime1: TextView
    private lateinit var textoTime2: TextView
    private lateinit var textoTempo: Chronometer
    private lateinit var pontosTimeA: TextView
    private lateinit var pontosTimeB: TextView
    private lateinit var textoPontuacao: TextView

    data class EstadoPlacar(
        val pontosTimeA: Int,
        val pontosTimeB: Int,
        val textoTempo: String = "00:00"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        var isPlaying = false

        setContentView(R.layout.activity_placar)


        val decorView = window.decorView
        decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY


        val pilhaEstados = Stack<EstadoPlacar>()
        textoTime1 = findViewById(R.id.settime1)
        textoTime2 = findViewById(R.id.settime2)
        textoTempo = findViewById(R.id.timer)
        pontosTimeA = findViewById<TextView>(R.id.PontosTimeAView)
        pontosTimeB = findViewById<TextView>(R.id.PontosTimeBView)
        var add5TimeA = findViewById<FloatingActionButton>(R.id.add5TimeA)
        var add2TimeA = findViewById<FloatingActionButton>(R.id.add2TimeA)
        var add5TimeB = findViewById<FloatingActionButton>(R.id.add5TimeB)
        var add2TimeB = findViewById<FloatingActionButton>(R.id.add2TimeB)
        var timerPlayPause = findViewById<FloatingActionButton>(R.id.timerPlayPause)
        var timer = findViewById<Chronometer>(R.id.timer)


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

        timer.setOnChronometerTickListener {
            (Chronometer.OnChronometerTickListener {
                val estadoAtual = EstadoPlacar(
                    pontosTimeA.text.toString().toInt(),
                    pontosTimeB.text.toString().toInt(),
                    timer.text.toString()
                )
                pilhaEstados.push(estadoAtual)
            })
        }

        timerPlayPause.setOnClickListener(View.OnClickListener {
            if (!timer.isEnabled)
                timer.start()
            else timer.stop()
        })

//        pontosTimeA.setOnClickListener(View.OnClickListener {
//            val estadoAtual = EstadoPlacar(
//                pontosTimeA.text.toString().toInt(),
//                pontosTimeB.text.toString().toInt()
//            )
//            pilhaEstados.push(estadoAtual)
//
//            val contadorTimeA = pontosTimeA.text.toString().toInt() + 1
//            pontosTimeA.text = contadorTimeA.toString()
//        })
        add5TimeA.setOnClickListener(View.OnClickListener {
            val estadoAtual = EstadoPlacar(
                pontosTimeA.text.toString().toInt(),
                pontosTimeB.text.toString().toInt()
            )
            pilhaEstados.push(estadoAtual)

            val contadorTimeA = pontosTimeA.text.toString().toInt() + 5
            pontosTimeA.text = contadorTimeA.toString()
//            salvarEstado()
        })
        add2TimeA.setOnClickListener(View.OnClickListener {
            val estadoAtual = EstadoPlacar(
                pontosTimeA.text.toString().toInt(),
                pontosTimeB.text.toString().toInt()
            )
            pilhaEstados.push(estadoAtual)

            val contadorTimeA = pontosTimeA.text.toString().toInt() + 2
            pontosTimeA.text = contadorTimeA.toString()
//            salvarEstado()
        })
        add5TimeB.setOnClickListener(View.OnClickListener {
            val estadoAtual = EstadoPlacar(
                pontosTimeA.text.toString().toInt(),
                pontosTimeB.text.toString().toInt()
            )
            pilhaEstados.push(estadoAtual)
            val contadorTimeB = pontosTimeB.text.toString().toInt() + 5
            pontosTimeB.text = contadorTimeB.toString()
//            salvarEstado()
        })
        add2TimeB.setOnClickListener(View.OnClickListener {
            val estadoAtual = EstadoPlacar(
                pontosTimeA.text.toString().toInt(),
                pontosTimeB.text.toString().toInt()
            )
            pilhaEstados.push(estadoAtual)
            val contadorTimeB = pontosTimeB.text.toString().toInt() + 2
            pontosTimeB.text = contadorTimeB.toString()
//            salvarEstado()
        })
//        pontosTimeB.setOnClickListener(View.OnClickListener {
//            val estadoAtual = EstadoPlacar(
//                pontosTimeA.text.toString().toInt(),
//                pontosTimeB.text.toString().toInt()
//            )
//            pilhaEstados.push(estadoAtual)
//            val contadorTimeB = pontosTimeB.text.toString().toInt() + 1
//            pontosTimeB.text = contadorTimeB.toString()
//        })
        val myButton = findViewById<LinearLayout>(R.id.myButton)
        myButton.setOnClickListener {
            if (!pilhaEstados.empty()) {
                val estadoAnterior = pilhaEstados.pop()
                pontosTimeA.text = estadoAnterior.pontosTimeA.toString()
                pontosTimeB.text = estadoAnterior.pontosTimeB.toString()
            }
//            salvarEstado()
        }

        timerPlayPause.setOnClickListener(object : View.OnClickListener {

            override fun onClick(v: View) {
                val chronoMeter = findViewById<Chronometer>(R.id.timer)
                println(chronoMeter.text)
                isPlaying = if (!isPlaying) {
                    chronoMeter.text = chronoMeter.text;
                    chronoMeter.start()
                    true
                } else {
                    chronoMeter.stop()
                    false
                }
            }
        })


    }

    private fun salvarEstado() {
        val estadoPlacar = EstadoPlacar(
            pontosTimeA = pontosTimeA.text.toString().toInt(),
            pontosTimeB = pontosTimeB.text.toString().toInt(),
                    textoTempo = textoTempo.text.toString()
        )

        val chronoMeter = findViewById<Chronometer>(R.id.timer)
        chronoMeter.stop();
        val position = intent.getIntExtra("position", -1)
        if (position != -1) {
            val configuracoesPartidaList =
                ConfiguracaoPartidaManager.getConfiguracoesPartidaList().toMutableList()
            if (position < configuracoesPartidaList.size) {
                val configuracaoPartida = configuracoesPartidaList[position]
                configuracaoPartida.pontosTimeA = estadoPlacar.pontosTimeA
                configuracaoPartida.pontosTimeB = estadoPlacar.pontosTimeB
                configuracaoPartida.tempoAtual = estadoPlacar.textoTempo

                configuracoesPartidaList[position] = configuracaoPartida
                // Atualizar a lista no SharedPreferences
                ConfiguracaoPartidaManager.setConfiguracoesPartidaList(
                    this,
                    configuracoesPartidaList
                )

                // Salvar a lista atualizada no SharedPreferences
                ConfiguracaoPartidaManager.saveConfiguracoesPartidaList(this)
            }
        }
    }

    override fun onStop() {
        salvarEstado()
        super.onStop()

    }

    override fun onPause() {
        salvarEstado()
        super.onPause()

    }

    override fun onDestroy() {
        salvarEstado()
        super.onDestroy()
    }
}



