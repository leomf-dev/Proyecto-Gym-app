package com.cibertec.proygym.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class GymDB (context : Context) : SQLiteOpenHelper(context, "proygym_db.db", null, 1){

    override fun onCreate(db : SQLiteDatabase){
        db.execSQL("""
            create table usuario(
            id_usuario integer primary key autoincrement not null,
            dni text not null,
            nombres text not null,
            apellido text not null,
            sexo text not null,
            celular text not null,
            correo text not null unique,
            clave text not null,
            peso double not null,
            altura double not null
            )
        """.trimIndent())


        db.execSQL("""
            create table asesor(
                id_asesor integer primary key autoincrement not null,
                nombre_asesor text not null,
                especialidad text not null,
                correo text not null
            )
        """.trimIndent())


        db.execSQL("""
            create table plan_nutricional (
                id_plan integer primary key autoincrement not null,
                id_asesor text not null,
                nombre_plan text not null,
                calorias_diarias not null,
                descripcion text not null,
                precio text not null,
                foreign key(id_asesor) references asesor(id_asesor)
            )
        """.trimIndent())

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db. execSQL("drop table if exists usuario")
        db. execSQL("drop table if exists asesor")
        db. execSQL("drop table if exists plan_nutricional")
        onCreate(db)
    }
}