package com.JuanZu77.WebFavoritas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public EditText et1;
    public ListView lv1;

    public ArrayList<String> lista1;
    public ArrayAdapter<String> adaptador1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1=findViewById(R.id.et1);
        lv1=findViewById(R.id.lv1);

        agregarWeb();
    }

    public void agregarWeb(){
        lista1 = new ArrayList<String>();

        AdminSQLiteOpenHelper admin=new AdminSQLiteOpenHelper(this, "database", null, 1);
        SQLiteDatabase bd=admin.getWritableDatabase();

        Cursor registro=bd.rawQuery("select descripcion from web", null);

        while (registro.moveToNext())
        {
            lista1.add(registro.getString(0));
        }


        adaptador1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lista1);
        lv1.setAdapter(adaptador1);

        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Toast.makeText(MainActivity.this, lista1.get(i), Toast.LENGTH_LONG).show();

                Intent intento1=new Intent(Intent.ACTION_VIEW, Uri.parse("https://"+lista1.get(i)));
                startActivity(intento1);
            }
        });
    }

    public void agregar(View v)
    {

        AdminSQLiteOpenHelper admin=new AdminSQLiteOpenHelper(this, "database", null, 1);

        SQLiteDatabase bd = admin.getWritableDatabase();

        ContentValues registro= new ContentValues();
        registro.put("descripcion", et1.getText().toString());

        String sitio=et1.getText().toString();


        if (sitio.length()>5)
        {
            int cant = (int) bd.insert("web", null, registro);

            if(cant>0){
                lista1.add(sitio);
                adaptador1.notifyDataSetChanged();
                et1.setText("");
                Toast.makeText(this, "Se Agrego el sitio web", Toast.LENGTH_LONG).show();
                bd.close();
            }
            else{
            }
        }
        else
        {
            Toast.makeText(this, "Minimo 6 caracteres, ejemplo: jvz.com", Toast.LENGTH_LONG).show();
        }
    }

    public void borrar (View view)
    {
        AdminSQLiteOpenHelper admin=new AdminSQLiteOpenHelper(this, "database", null, 1);

        SQLiteDatabase bd = admin.getWritableDatabase();

        String descripcion = et1.getText().toString();

        if (descripcion.length()>0)
        {
            int cant = bd.delete( "web", "descripcion='"+descripcion+"'", null );

            String sitio=et1.getText().toString();
            lista1.remove(sitio);
            adaptador1.notifyDataSetChanged();

            if(cant>0)
            {
                Toast.makeText(this, "Se elimino el sitio web", Toast.LENGTH_LONG).show();
                et1.setText("");
            }
            else{
                Toast.makeText(this, "No existe el sitio web", Toast.LENGTH_LONG).show();
            }
        }
        else
        {
            Toast.makeText(this, "No se ingresaron datos", Toast.LENGTH_LONG).show();
        }
    }


    public void salir(View v)
    {
        finish();
    }
}

