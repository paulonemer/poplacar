package com.example.poplacar

import ConfiguracaoPartidaAdapter
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class listapartidas : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listapartidas)


        val sharedPreferences = getSharedPreferences("configuracoes", Context.MODE_PRIVATE)
        val configuracoesJson = sharedPreferences.getString("configuracoesPartidaList", null)

        val configuracoesPartidaList: MutableList<ConfiguracaoPartida> = if (configuracoesJson != null) {
            val gson = Gson()
            gson.fromJson(configuracoesJson, object : TypeToken<MutableList<ConfiguracaoPartida>>() {}.type)
        } else {
            mutableListOf() // Valor padrão, caso a string JSON esteja vazia ou nula
        }
        val recyclerView: RecyclerView = findViewById(R.id.listafinal)
        val adapter = ConfiguracaoPartidaAdapter(configuracoesPartidaList)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

    }

    }
