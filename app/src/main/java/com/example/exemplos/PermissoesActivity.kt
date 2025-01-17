package com.example.exemplos

import android.Manifest
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import android.content.pm.PackageManager

class PermissoesActivity : AppCompatActivity() {


    val ID_REQUISICAO_READ_CONTACTS = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_permissoes)

        val btnSolicitarPermissao = findViewById<Button>(R.id.btn_solicitar_permissao)
        btnSolicitarPermissao.setOnClickListener {

            if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
                == PackageManager.PERMISSION_GRANTED){
                val builder: AlertDialog.Builder = AlertDialog.Builder(this)
                builder
                    .setTitle("Permissão concedida")
                    .setPositiveButton("Ok") { dialog, which ->
                        // Do something.
                    }
                val dialog: AlertDialog = builder.create()
                dialog.show()

            }else{
                ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.READ_CONTACTS),
                    ID_REQUISICAO_READ_CONTACTS)
            }
        }



    }



    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if(requestCode == ID_REQUISICAO_READ_CONTACTS){

            if(grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                //A permissão foi concedida, o aplciativo pode utilizar o recurso

            } else{
                //A permissão não foi concedida, aqui você deverá desabilitar a funcionalidade que utiliza tal recurso
            }
        }

    }



}