package com.example.exemplos

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity



class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val exemplos = listOf<String>("Notificações", "Permissões", "Geo Localização")

        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, exemplos)
        var listView = findViewById<ListView>(R.id.list_view)

        listView.adapter = adapter
        val intentNotificacoes = Intent(this, NotificacoesActivity::class.java)
        val intentPermissoes = Intent(this, PermissoesActivity::class.java)
        val intentGeoLocalizacao = Intent(this, GeoLocalizacaoActivity::class.java)

        listView.setOnItemClickListener { adapterView, view, i, l ->
            when (i) {
                0 -> startActivity(intentNotificacoes)
                1 -> startActivity(intentPermissoes)
                2 -> startActivity(intentGeoLocalizacao)
            }

        }
    }
}