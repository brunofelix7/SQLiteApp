package br.com.example.sqliteapp;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public DatabaseSQLite myDatabase;
    private EditText txtId, txtUsername, txtEmail, txtSenha;
    private Button buttonEnviar, buttonListar, buttonUpdate, buttonDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //  Cria o banco de dados
        myDatabase = new DatabaseSQLite(this);

        //  Recupera os recursos
        txtId = (EditText) findViewById(R.id.id);
        txtUsername = (EditText) findViewById(R.id.username);
        txtEmail = (EditText) findViewById(R.id.email);
        txtSenha = (EditText) findViewById(R.id.senha);
        buttonEnviar = (Button) findViewById(R.id.enviar);
        buttonListar = (Button) findViewById(R.id.listar);
        buttonUpdate = (Button) findViewById(R.id.update);
        buttonDelete = (Button) findViewById(R.id.delete);

        //  Chamada dos métodos
        cadastrar();
        listar();
        atualizar();
        remover();
    }

    /**
     * Realiza o cadastro no banco de dados quando o usuário preenche os campos e clica em enviar
     */
    public void cadastrar() {
        buttonEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = txtUsername.getText().toString();
                String email = txtEmail.getText().toString();
                String senha = txtSenha.getText().toString();
                boolean isInserted = myDatabase.insertData(username, email, senha);

                if(isInserted){
                    Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "Data Not Inserted", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * Realiza a listagem dos dados
     */
    public void listar() {
        buttonListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor result = myDatabase.listAllData();
                if (result.getCount() == 0) {
                    message("Desculpe...", "Nenhum dado encontrado");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (result.moveToNext()) {
                    buffer.append("Id: " + result.getString(0) + "\n");
                    buffer.append("Username: " + result.getString(1) + "\n");
                    buffer.append("E-mail: " + result.getString(2) + "\n");
                    buffer.append("Senha: " + result.getString(3) + "\n\n");
                }

                //  SHOW ALL DATA
                message("Dados", buffer.toString());
            }
        });
    }

    /**
     * Realiza o update de um dado específico
     */
    public void atualizar(){
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = txtId.getText().toString();
                String username = txtUsername.getText().toString();
                String email = txtEmail.getText().toString();
                String senha = txtSenha.getText().toString();
                boolean isUpdate = myDatabase.updateData(id, username, email, senha);

                if(isUpdate){
                    Toast.makeText(MainActivity.this, "Data Update", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "Data Not Update", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * Realiza a exclusão de um dado específico
     */
    public void remover(){
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = txtId.getText().toString();
                Integer deletedRow = myDatabase.deleteData(id);

                if(deletedRow > 0){
                    Toast.makeText(MainActivity.this, "Data Deleted", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "Data Not Deleted", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * Realiza a criação de mensagens através de AlertDialog
     * @param title     Título da mensagem
     * @param message   Mensagem
     */
    public void message(String title, String message) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setCancelable(true);
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.show();
    }

}
