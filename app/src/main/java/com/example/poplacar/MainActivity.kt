
package com.example.poplacar
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //essa lista deve setar as opções do spinner
        val opcoesPontosVitoria = (1..25).toList()
        //aqui a gente define os elementos da tela
        val time1 = findViewById<EditText>(R.id.settime1)
        val time2 = findViewById<EditText>(R.id.settime2)
        val tempo = findViewById<EditText>(R.id.settempo)
        val tempoAtual = findViewById<EditText>(R.id.settempo)
        val botaosalvar = findViewById<Button>(R.id.salvarset)
        val spinnerPontosVitoria: Spinner = findViewById(R.id.setpontos)

        //esse adapter funciona para ajustar o spinner
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, opcoesPontosVitoria)

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinnerPontosVitoria.adapter = adapter

        spinnerPontosVitoria.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val pontosVitoriaSelecionado = opcoesPontosVitoria[position]
                // Faça algo com o valor selecionado, se necessário
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Lógica quando nenhum item é selecionado, se necessário
            }
        }


        botaosalvar.setOnClickListener(View.OnClickListener {
            val configuracao = ConfiguracaoPartida(
                time1 = time1.text.toString(),
                time2 = time2.text.toString(),
                tempo = tempo.text.toString(),
                pontosVitoria = spinnerPontosVitoria.selectedItem as Int,
                pontosTimeA = 0,
                pontosTimeB = 0,
                tempoAtual = "0:00"
            )
            ConfiguracaoPartidaManager.loadConfiguracoesPartidaList(this)
            val novaConfiguracaoPartida = ConfiguracaoPartida(configuracao.time1, configuracao.time2, configuracao.tempo, configuracao.pontosVitoria,configuracao.pontosTimeA,configuracao.pontosTimeB, configuracao.tempoAtual)
            val configuracoesPartidaList = ConfiguracaoPartidaManager.getConfiguracoesPartidaList().toMutableList()
            configuracoesPartidaList.add(novaConfiguracaoPartida)
            ConfiguracaoPartidaManager.setConfiguracoesPartidaList(this, configuracoesPartidaList)
            ConfiguracaoPartidaManager.saveConfiguracoesPartidaList(this)
            val intent = Intent(this, ListaPartidas::class.java)
            startActivity(intent)
        })









    }
}