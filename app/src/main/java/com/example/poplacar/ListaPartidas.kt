package com.example.poplacar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import android.content.Intent
import ConfiguracaoPartidaAdapter

class ListaPartidas : AppCompatActivity(), ConfiguracaoPartidaAdapter.OnItemClickListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ConfiguracaoPartidaAdapter
    private lateinit var listaPartidas: List<ConfiguracaoPartida>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_partidas)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Carregar a lista de configurações de partida do SharedPreferences
        ConfiguracaoPartidaManager.loadConfiguracoesPartidaList(this)

        // Obter a lista de configurações de partida
        listaPartidas = ConfiguracaoPartidaManager.getConfiguracoesPartidaList()


        adapter = ConfiguracaoPartidaAdapter(listaPartidas)
        recyclerView.adapter = adapter

        // Configurar ouvinte de clique no próprio adapter
        adapter.setOnItemClickListener(this)
    }
    override fun onDestroy() {
        super.onDestroy()
        // Salvar a lista de configurações de partida no SharedPreferences antes de fechar o aplicativo
        ConfiguracaoPartidaManager.saveConfiguracoesPartidaList(this)
    }
    override fun onItemClick(position: Int) {
        val intent = Intent(this, Placar::class.java)
        intent.putExtra("position", position)
        startActivity(intent)
    }
}