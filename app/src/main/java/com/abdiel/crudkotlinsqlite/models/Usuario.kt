package com.abdiel.crudkotlinsqlite.models

class Usuario{
    //intancias los valores que usaras de la base de datos
    //puedes usar el punto y coma o no, el lenguaje es mas flexible en ese aspecto
    var id: Int = 0;
    var name : String = "";
    var age : Int = 0;

    //llamamos a la propiedad constructor propia de kotlin para construir las instancias
    constructor(name:String, age:Int){
//haces lo mismo que en java
        this.name  = name;
        this.age = age;
    }




}