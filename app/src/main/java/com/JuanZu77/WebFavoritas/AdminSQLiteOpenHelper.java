package com.JuanZu77.WebFavoritas;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

/*Una vez creada la clase, no tenemos la ventana de Opciones como en Eclispe..
* Lo siguiente es escribir: extends SQliteOpenHelper
* ALT+ENTER --> implements add methods --> Ok
* Fianlmente necesitamos AGREGAr el CONSTRUCTor nuevamente ALT+ENTER sobre el nombre de la clase
* */

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {

    public AdminSQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("create table web (descripcion text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
