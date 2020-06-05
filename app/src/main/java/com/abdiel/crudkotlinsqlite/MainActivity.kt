package com.abdiel.crudkotlinsqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.abdiel.crudkotlinsqlite.db.Basededatos
import com.abdiel.crudkotlinsqlite.models.Usuario
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val context = this

        //se agrega el setOnclickListener para poder agregar un listener al boton
        insertBtn.setOnClickListener({
            if (etNombre.text.toString().length > 0 && etedad.text.toString().length > 0){
                    var user = Usuario(etNombre.text.toString(), etedad.text.toString().toInt())
                    var db = Basededatos(context)
                    db.insertarCliente(user)
                }else {
                Toast.makeText(context, "LLena todos los campos", Toast.LENGTH_SHORT).show()
            }
        })

    }
}
