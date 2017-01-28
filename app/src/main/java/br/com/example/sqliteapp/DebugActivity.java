package br.com.example.sqliteapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class DebugActivity extends AppCompatActivity {

    protected static final String TAG = "Ciclo de Vida";

    //  Retorna o nome da classe sem o nome do pacote
    private String getClassName(){
        String classe = getClass().getName();
        return classe.substring(classe.lastIndexOf("."));
    }

    //  Ao iniciar a aplicação pela primeira vez, esse bundle sempre retornará .
    //  Em um processo de rotação de tela por exemplo, que a activity é destruída e recriada novamente,
    //  esse bundle retorna um valor, que é o estado da aplicação que foi armazenado antes pelo método
    //  onSaveInstanceState()
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Log.i(TAG, getClassName() + ".onCreate() chamado: " + bundle);
    }

    protected void onStart() {
        super.onStart();
        Log.i(TAG, getClassName() + ".onStart() chamado.");
    }

    protected void onRestart(){
        super.onRestart();
        Log.i(TAG, getClassName() + ".onRestart() chamado.");
    }

    protected void onResume(){
        super.onResume();
        Log.i(TAG, getClassName() + ".onResume() chamado.");
    }

    protected void onPause(){
        super.onPause();
        Log.i(TAG, getClassName() + ".onPause() chamado.");
    }

    //  Armazena os dados da aplicação em uma estrutura de chave e valor
    //  Salva o estado da aplicação
    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        Log.i(TAG, getClassName() + ".onSaveInstanceState() chamado.");
    }

    protected void onStop(){
        super.onStop();
        Log.i(TAG, getClassName() + ".onStop() chamado.");
    }

    protected void onDestroy(){
        super.onDestroy();
        Log.i(TAG, getClassName() + ".onDestroy() chaamdo.");
        //  Outra forma de destruir uma Activity
        //  finish();
    }

}