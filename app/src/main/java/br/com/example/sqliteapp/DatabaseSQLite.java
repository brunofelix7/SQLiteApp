package br.com.example.sqliteapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Classe responsável pela conexão, criação e operações do banco de dados SQLite
 */
public class DatabaseSQLite extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "sqliteapp.db";
    private static final String TABLE_NAME = "user";
    private static final String ID = "id";
    private static final String USERNAME = "username";
    private static final String EMAIL = "email";
    private static final String SENHA = "senha";
    private static final String SQL_QUERY_SELECT = "SELECT * FROM " + TABLE_NAME;
    private static final String SQL_QUERY_DROP = "DROP TABLE IF EXISTS " + TABLE_NAME;
    private static final String SQL_QUERY = "CREATE TABLE " + TABLE_NAME + "(" +
                                            ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                            USERNAME + " TEXT, " +
                                            EMAIL + " TEXT, " +
                                            SENHA + " TEXT" +
                                            ");";

    public DatabaseSQLite(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(android.database.sqlite.SQLiteDatabase db) {
        db.execSQL(SQL_QUERY);
    }

    @Override
    public void onUpgrade(android.database.sqlite.SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_QUERY_DROP);
        onCreate(db);
    }

    /**
     * Realiza a inserção de dados
     * @param username  Parâmetro informado pelo usuário no EditText
     * @param email     Parâmetro informado pelo usuário no EditText
     * @param senha     Parâmetro informado pelo usuário no EditText
     */
    public boolean insertData(String username, String email, String senha){
        SQLiteDatabase db = this.getWritableDatabase();

        //  Recebe dois parâmetros, a coluna e o valor a ser inserido
        ContentValues contentValues = new ContentValues();
        contentValues.put(USERNAME, username);
        contentValues.put(EMAIL, email);
        contentValues.put(SENHA, senha);

        //  Executa a ação
        Long result = db.insert(TABLE_NAME, null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }

    /**
     * Realiza a listagem dos dados
     * @return Retorna o um objeto do tipo Cursor, contendo as informações buscadas no meu arquivo .db
     */
    public Cursor listAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery(SQL_QUERY_SELECT, null);
        return result;
    }

    /**
     * Realiza o update de um dado específico
     * @param id        Parâmetro informado pelo usuário no EditText
     * @param username  Parâmetro informado pelo usuário no EditText
     * @param email     Parâmetro informado pelo usuário no EditText
     * @param senha     Parâmetro informado pelo usuário no EditText
     * @return
     */
    public boolean updateData(String id, String username, String email, String senha){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(ID, id);
        contentValues.put(USERNAME, username);
        contentValues.put(EMAIL, email);
        contentValues.put(SENHA, senha);

        db.update(TABLE_NAME, contentValues, ID + " = ?", new String[] { id });
        return true;
    }

    /**
     * Realiza a exclusão de um dado específico
     * @param id    Parâmetro informado pelo usuário no EditText
     * @return      Retorna o id informado
     */
    public Integer deleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, ID + " = ?", new String[] { id });
    }


}
