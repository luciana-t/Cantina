package com.luciana.cantina;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class databaseHelper extends SQLiteOpenHelper {
    //Variaveis do BD
    private static final String DB_NAME = "cantina.sqlite3";
    private static final int DB_VERSION = 1;

    //Tabela Usuario
    private static final String USER_TABLE = "Usuario";
    private static final String idUser_KEY = "idUser";
    private static final String nome_KEY = "nome";
    private static final String email_KEY = "email";
    private static final String senha_KEY = "senha";
    private static final String valorAberto_KEY = "valorAberto";
    private static final String cadastrante_KEY = "cadastrante";

    //Tabela Sugestoes
    private static final String SUGESTAO_TABLE = "Sugestao";
    private static final String idSugestao_KEY = "idSugestao";
    private static final String mensagem_KEY = "mensagem";
    private static final String anonimo_KEY = "aninimo";

    //Tabela Compras
    private static final String COMPRA_TABLE = "Compra";
    private static final String idCompra_KEY = "idCompra";
    private static final String dataEfetivada_KEY = "dataEfetivada";
    private static final String valorTotal_KEY = "valorTotal";

    //Tabela Produtos
    private static final String PRODUTO_TABLE = "Produto";
    private static final String codBarra_KEY = "codBarra";
    private static final String nomeProduto_KEY = "nome";
    private static final String estoque_KEY = "estoque";

    //Tabela Compras_Produtos
    private static final String COMPRA_PRODUTO_TABLE = "Compra_Produto";

    //Tabela Porcoes
    private static final String PORCAO_PRODUTO_TABLE = "Porcao";
    private static final String dataAquisicao_KEY = "dataAquisicao";
    private static final String validade_KEY = "validade";
    private static final String valorUnidade_KEY = "valorUnidade";
    private static final String porcaoUnidade_KEY = "porcaoUnidade";
    private static final String porcaoQuantidade_KEY = "porcaoQuantidade";

    //Create tables commands

    /*
    CREATE TABLE "Usuário" (
	"idUser"	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
	"nome"	TEXT NOT NULL,
	"email"	TEXT NOT NULL UNIQUE,
	"senha"	TEXT NOT NULL,
	"valorAberto"	REAL NOT NULL DEFAULT 0,
	"cadastrante"	NUMERIC NOT NULL DEFAULT 0
    )
    */
    private static final String CREATE_TABLE_USUARIO = "CREATE TABLE " + USER_TABLE + "("
            + idUser_KEY + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, "
            + nome_KEY + "TEXT NOT NULL, "
            + email_KEY + "TEXT NOT NULL UNIQUE, "
            + senha_KEY + "TEXT NOT NULL, "
            + valorAberto_KEY + "REAL NOT NULL DEFAULT 0, "
            + cadastrante_KEY + "NUMERIC NOT NULL DEFAULT 0 "
            + " );";

    /*
    CREATE TABLE "Sugestoes" (
	"idSugestoes"	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
	"idUser"	INTEGER NOT NULL,
	"mensagem"	TEXT NOT NULL,
	"anonimo"	NUMERIC NOT NULL DEFAULT 0,
	FOREIGN KEY("idUser") REFERENCES "Usuario"("idUser")
    )
     */
    private static final String CREATE_TABLE_SUGESTOES = "CREATE TABLE " + SUGESTAO_TABLE + "("
            + idUser_KEY + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, "
            + idSugestao_KEY + "INTEGER NOT NULL, "
            + mensagem_KEY + "TEXT NOT NULL, "
            + anonimo_KEY + "NUMERIC NOT NULL DEFAULT 0, "
            + "FOREIGN KEY(\"idUser\") REFERENCES \"Usuario\"(\"idUser\")"
            + " );";

    /*
    CREATE TABLE "Compra" (
	"idCompra"	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
	"idUser"	INTEGER NOT NULL,
	"dataEfetivada"	NUMERIC NOT NULL,
	"valorTotal"	REAL NOT NULL,
	FOREIGN KEY("idUser") REFERENCES "Usuario"("idUser")
    )
     */

    private static final String CREATE_TABLE_COMPRA = "CREATE TABLE " + COMPRA_TABLE + "("
            + idCompra_KEY + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, "
            + idUser_KEY + "INTEGER NOT NULL, "
            + dataEfetivada_KEY + "NUMERIC NOT NULL, "
            + valorTotal_KEY + "REAL NOT NULL, "
            + "FOREIGN KEY(\"idUser\") REFERENCES \"Usuario\"(\"idUser\")"
            + " );";

    /*
    CREATE TABLE "Produto" (
	"codBarra"	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
	"nome"	TEXT NOT NULL,
	"estoque"	INTEGER NOT NULL DEFAULT 0
    )
     */

    private static final String CREATE_TABLE_PRODUTO = "CREATE TABLE " + PRODUTO_TABLE + "("
            + codBarra_KEY + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, "
            + nomeProduto_KEY + "TEXT NOT NULL, "
            + estoque_KEY + "INTEGER NOT NULL DEFAULT 0 "
            + " );";

    /*
    CREATE TABLE "compra_produto" (
	"idCompra"	INTEGER NOT NULL,
	"codBarra"	INTEGER NOT NULL,
	FOREIGN KEY("codBarra") REFERENCES "Produto"("codBarra"),
	FOREIGN KEY("idCompra") REFERENCES "Usuário"("idUser"),
	PRIMARY KEY("codBarra","idCompra")
    )
     */

    private static final String CREATE_TABLE_COMPRA_PRODUTO = "CREATE TABLE " + COMPRA_PRODUTO_TABLE + "("
            + idCompra_KEY + " INTEGER NOT NULL, "
            + codBarra_KEY + "INTEGER NOT NULL, "
            + "FOREIGN KEY(\"codBarra\") REFERENCES \"Produto\"(\"codBarra\"), "
            + "FOREIGN KEY(\"idCompra\") REFERENCES \"Usuário\"(\"idUser\"), "
            + "PRIMARY KEY(\"codBarra\",\"idCompra\")"
            + " );";

    /*
    CREATE TABLE "Porção" (
	"codBarra"	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
	"dataAquisição"	NUMERIC NOT NULL,
	"validade"	NUMERIC NOT NULL,
	"valorUnidade"	REAL NOT NULL,
	"porçãoUnidade"	TEXT NOT NULL,
	"porçãoQuantidade"	INTEGER NOT NULL
    )
     */

    private static final String CREATE_TABLE_PORCAO_PRODUTO = "CREATE TABLE " + PORCAO_PRODUTO_TABLE + "("
            + codBarra_KEY + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, "
            + dataAquisicao_KEY + "TEXT NOT NULL, "
            + validade_KEY + "TEXT NOT NULL, "
            + valorUnidade_KEY + "REAL NOT NULL, "
            + porcaoUnidade_KEY + "TEXT NOT NULL, "
            + porcaoQuantidade_KEY + "INTEGER NOT NULL "
            + " );";


    public databaseHelper(Context ctx) {
        super(ctx, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USUARIO);
        db.execSQL(CREATE_TABLE_SUGESTOES);
        db.execSQL(CREATE_TABLE_COMPRA);
        db.execSQL(CREATE_TABLE_PRODUTO);
        db.execSQL(CREATE_TABLE_COMPRA_PRODUTO);
        db.execSQL(CREATE_TABLE_PORCAO_PRODUTO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS '" + USER_TABLE + "'");
        db.execSQL("DROP TABLE IF EXISTS '" + SUGESTAO_TABLE + "'");
        db.execSQL("DROP TABLE IF EXISTS '" + COMPRA_TABLE + "'");
        db.execSQL("DROP TABLE IF EXISTS '" + PRODUTO_TABLE + "'");
        db.execSQL("DROP TABLE IF EXISTS '" + COMPRA_PRODUTO_TABLE + "'");
        db.execSQL("DROP TABLE IF EXISTS '" + PORCAO_PRODUTO_TABLE + "'");
        onCreate(db);
    }

    public void cadastrarUsuario(String nome, String email, String senha, int cadastrante){
        //Abre o banco em modo escrita
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(nome_KEY, nome);
        values.put(email_KEY, email);
        values.put(senha_KEY, senha);
        values.put(cadastrante_KEY, cadastrante);
        //values.put(valorAberto_KEY, 0); default 0
        db.insert(USER_TABLE, null, values);

        //Fecha conexao com o banco
        db.close();
    }

    public void addSugestao(int idUser, String mensagem, int anonimo){
        //Abre o banco em modo escrita
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(idUser_KEY, idUser);
        values.put(mensagem_KEY, mensagem);
        values.put(anonimo_KEY, anonimo);
        db.insert(SUGESTAO_TABLE, null, values);

        //Fecha conexao com o banco
        db.close();
    }

    public void cadastrarProduto(int codBarra, String nome){
        //Abre o banco em modo escrita
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(codBarra_KEY, codBarra);
        values.put(nomeProduto_KEY, nome);
        values.put(estoque_KEY, 0);
        db.insert(PRODUTO_TABLE, null, values);

        //Fecha conexao com o banco
        db.close();
    }

    public void addEstoque(String nome, int codBarra, int quantidade, float valor, int porcaoQt, String porcaoUnidade, float valorPorcao, String validade){
        //Abre o banco em modo escrita
        SQLiteDatabase db = this.getWritableDatabase();

        //Atualiza quantidade de estoque
        ContentValues values = new ContentValues();
        values.put(codBarra_KEY, codBarra);
        values.put(nomeProduto_KEY, nome);
        values.put(estoque_KEY, quantidade);
        db.update(PRODUTO_TABLE, values, "codBarra="+codBarra, null);

        //Adquire data atual
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date data = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(data);
        Date data_atual = cal.getTime();
        String data_atual_str = dateFormat.format(data);

        //Adiciona a porcao do produto
        values.put(codBarra_KEY, codBarra);
        values.put(dataAquisicao_KEY, "16/06/2019");
        values.put(validade_KEY, validade);
        values.put(valorUnidade_KEY, valorPorcao);
        values.put(porcaoUnidade_KEY, porcaoUnidade);
        values.put(porcaoQuantidade_KEY, porcaoQt);
        db.insert(PORCAO_PRODUTO_TABLE, null, values);

        //Fecha conexao com o banco
        db.close();
    }

    public void efetuarCompra(int idUser, List<String> codBarras, float valorTotal){
        //Abre o banco em modo escrita
        SQLiteDatabase db = this.getWritableDatabase();

        //Gera uma compra
        ContentValues values = new ContentValues();
        values.put(idUser_KEY, idUser);

        //Adquire data atual
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date data = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(data);
        Date data_atual = cal.getTime();
        String data_atual_str = dateFormat.format(data);

        values.put(dataEfetivada_KEY, "18/06/2019");
        values.put(valorTotal_KEY, valorTotal);
        long oid_compra = db.insert(COMPRA_TABLE, null, values);
        //Para cada produto, adicionar oid_compra e o codBarra relativo a ele na tabela compra_produto
        for(String codBarra : codBarras) {
            values = new ContentValues();
            values.put(idCompra_KEY, oid_compra);
            values.put(codBarra_KEY, codBarra);
            db.insert(COMPRA_PRODUTO_TABLE, null, values);
        }
    }
}
