package com.abdiel.crudkotlinsqlite.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import com.abdiel.crudkotlinsqlite.models.Usuario

//es recomendable declarar antes todo lo que vas a usar que luego se hace un desmadre
val BASEDEDATOS_NOMBRE = "crudejemplo.db";
val TABLA_NOMBRE = "Usuarios";
val COL_NOMBRE = "name";
val COL_EDAD = "age";
val COL_ID = "id";


//en esta linea creas la clase con e nombre y le dan en que contexto estas manejando todos tus datos, en este caso es el contexto de la misma clase
//para crear al base de datos se una la libreria SQLiteOpenHelper, es la misma que usas en java
//los valores que tendra son el context que estas manejando, el nombre de la base de datos que declaraste anteriormente
//con el factory null, y el numero al final es la version de la base de datos, hay veces que haces cambios y no sabes que cambio
//es recomendable que cada vez que haces cambiso muy culeros, cambiar la version de la abse de datos, poniendole 2 o 3 o la version en la que ya vayas
class Basededatos(var context : Context)
    : SQLiteOpenHelper(context, BASEDEDATOS_NOMBRE, null, 1){

    override fun onCreate(db: SQLiteDatabase?) {

        //LO DE AQUI VIENE SIENDO EL QUERY QUE LE APLICARAS ALA BASE DE DATOS
        val crearTabla = "CREATE TABLE "+ TABLA_NOMBRE + " (" +
                COL_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_NOMBRE + " VARCHAR(256)," +
                COL_EDAD+ " INTEGER)";
        db?.execSQL(crearTabla); //aqui ya ejecutas el query para crearlo
        db?.close() //cierras la entrada de sqlite para evitar pedos

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        //aqui puedes actualizar la base de datos cuando haya un pedo en cache o algo asi
        //hay veces que se apendeja el sqlite y tienes que hacer esto, es lo mismo que en java
    }

    //fun es la funcion o el metodo, para llamar a al coleccion de datos se pone primero el nombre que llevara par ainstanciar
    //y despues de los dos puntos se pone al modelo en este caso Usuario
    fun insertarCliente( usuario: Usuario){
        //aqui le damos permisos a al abase de datos de escritura y lectura
        //cuando intancias esa madre, luego tienes qeu cerrar eso, que por buena practica se hace
        //que si lo dejas abierto, se puede meter cualquier wey bueno en joder
        //y te roba tus datos, por no cerrar un flujo de datos.
        val db = this.writableDatabase;

        var contentValue = ContentValues(); //instanciamos el contenedor de los datos

        //le ponemos todolo que va agregar
        contentValue.put(COL_NOMBRE, usuario.name);
        contentValue.put(COL_EDAD, usuario.age);

        //llamamos ala propiedad insert que es propia de openHelper
        //aqui mismo podemos validar si se realizo al insercion de esta forma
        var result = db.insert(TABLA_NOMBRE, null, contentValue);

        if(result == -1.toLong()){
            Toast.makeText(context, "Error al insertar", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Usuario agregado", Toast.LENGTH_SHORT).show();
        }

    }
}