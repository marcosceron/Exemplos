package com.example.exemplos

import android.app.Activity
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat

class NotificacoesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notificacoes)

        val btnNotification = findViewById<Button>(R.id.btn_notificacao)
        btnNotification.setOnClickListener {
            //notificacao1("Teste1", "Notificação da função 1")
            notificacaoSimples("Título", "Olá, você está sendo notificado")
        }
    }

    fun Activity.notificacao1(title: String, message: String) {
        val notificacao: Notification = NotificationCompat.Builder(this)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle(title)
            .setContentText(message)
            .build()
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(1, notificacao)
    }

    fun notificacaoSimples(title:String, message:String){
        val nBuilder = NotificationCompat.Builder(this, "default")
        nBuilder.setSmallIcon(R.mipmap.ic_launcher)
        nBuilder.setContentTitle(title)
        nBuilder.setContentText(message)
        val notificacao = nBuilder.build()
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel("default", "Canal de notificação teste", NotificationManager.IMPORTANCE_DEFAULT)
            notificationManager.createNotificationChannel(channel)
        }
        notificationManager.notify(1  , notificacao)
    }

}