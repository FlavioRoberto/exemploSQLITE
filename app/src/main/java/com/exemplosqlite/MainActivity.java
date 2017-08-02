package com.exemplosqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            //cria a instancia do banco de dados ou abre se ela existir
            SQLiteDatabase bancoDaddos = openOrCreateDatabase("app", MODE_PRIVATE, null);

            //cria uma tabela no banco caso ela não exista
            bancoDaddos.execSQL("CREATE TABLE IF NOT EXISTS pessoas(nome VARCHAR,idade INT(3), sexo VARCHAR(1))");

            //inserir dados na tabela
              bancoDaddos.execSQL("INSERT INTO pessoas(nome,idade,sexo) values ('Flávio Roberto',21,'M')");
              bancoDaddos.execSQL("INSERT INTO pessoas(nome,idade,sexo) values ('Gabriela Bueno',20,'F')");

            //recuperar dados do banco
            Cursor cursor = bancoDaddos.rawQuery("SELECT nome,idade,sexo FROM pessoas", null); //cursor é necessário pra percorrer a tabela e o raw Query monta um cursor baseado no banco

            //recuperar indices das colunas
            int indiceNome = cursor.getColumnIndex("nome");
            int indiceIdade = cursor.getColumnIndex("idade");
            int indiceSexo = cursor.getColumnIndex("sexo");

            //volta o cursor para o inicio das tuplas
            cursor.moveToFirst();

            //percorrer cursor enquanto ele nao for nulo
            while (cursor != null) {
                // cursor.getTipoVariavel (getString - getINT) pega uma string de acordo com o indice da coluna
                Log.i("RESULTADO - NOME - ", cursor.getString(indiceNome)); //LOG .i mostra no monitor os dados
                Log.i("RESULTADO - IDADE - ", String.valueOf(cursor.getInt(indiceIdade))); //String.Valueof converte o valor inteiro pra String
                Log.i("RESULTADO - SEXO - ", cursor.getString(indiceSexo));

                //anda uma posição no cursor
                cursor.moveToNext();
            }
        }catch (Exception e){
            String erro = "ERRO: \n"+e.getMessage();
            Log.i("RESULTADO - ERRO",erro);
        }
    }
}
