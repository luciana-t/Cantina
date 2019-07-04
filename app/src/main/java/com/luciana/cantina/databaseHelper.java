package com.luciana.cantina;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class databaseHelper extends SQLiteOpenHelper {
    //Variaveis do BD
    private SQLiteDatabase db;
    private static databaseHelper INSTANCE = new databaseHelper();
    private static final String DB_NAME = "cantina.sqlite3";
    private static final int DB_VERSION = 2;

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

    //Tabela Compras_Produtos
    private static final String COMPRA_PRODUTO_TABLE = "Compra_Produto";

    //Tabela Porcoes
    private static final String PORCAO_PRODUTO_TABLE = "Porcao";
    private static final String valorUnidade_KEY = "valorUnidade";
    private static final String porcaoUnidade_KEY = "porcaoUnidade";
    private static final String porcaoQuantidade_KEY = "porcaoQuantidade";

    //Tabela Porcoes
    private static final String ESTOQUE_TABLE = "Estoque";
    private static final String estoque_quantidade_KEY = "quantidadeEstoque";
    private static final String dataAquisicao_KEY = "dataAquisicao";
    private static final String validade_KEY = "validade";
    private static final String valorComprado_KEY = "valorComprado";


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
            + nome_KEY + " TEXT NOT NULL, "
            + email_KEY + " TEXT NOT NULL UNIQUE, "
            + senha_KEY + " TEXT NOT NULL, "
            + valorAberto_KEY + " REAL NOT NULL DEFAULT 0, "
            + cadastrante_KEY + " NUMERIC NOT NULL DEFAULT 0 "
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
            + idUser_KEY + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE REFERENCES \""+USER_TABLE+"\"(\""+idUser_KEY+"\"), "
            + idSugestao_KEY + " INTEGER NOT NULL, "
            + mensagem_KEY + " TEXT NOT NULL, "
            + anonimo_KEY + " NUMERIC NOT NULL DEFAULT 0 "
            //+ "FOREIGN KEY(\""+idUser_KEY+"\") REFERENCES \""+USER_TABLE+"\"(\""+idUser_KEY+"\")"
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
            + idUser_KEY + " REFERENCES \" "+USER_TABLE+" \"(\""+idUser_KEY+"\"), "
            + dataEfetivada_KEY + " TEXT NOT NULL, "
            + valorTotal_KEY + " REAL NOT NULL "
            + " );";

    /*
    CREATE TABLE "Produto" (
	"codBarra"	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
	"nome"	TEXT NOT NULL,
    )
     */

    private static final String CREATE_TABLE_PRODUTO = "CREATE TABLE " + PRODUTO_TABLE + "("
            + codBarra_KEY + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, "
            + nomeProduto_KEY + " TEXT NOT NULL "
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
            + idCompra_KEY + " REFERENCES \" "+ PRODUTO_TABLE +" \"(\" "+codBarra_KEY+" \"), "
            + codBarra_KEY + " REFERENCES \" "+ USER_TABLE +" \"(\""+idUser_KEY+"\"), "
            + "PRIMARY KEY(\""+codBarra_KEY+"\",\""+idCompra_KEY+"\")"
            + " );";

    /*
    CREATE TABLE "Porção" (
	"codBarra"	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
	"valorUnidade"	REAL NOT NULL,
	"porçãoUnidade"	TEXT NOT NULL,
	"porçãoQuantidade"	INTEGER NOT NULL
    )
     */

    private static final String CREATE_TABLE_PORCAO_PRODUTO = "CREATE TABLE " + PORCAO_PRODUTO_TABLE + "("
            + codBarra_KEY + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, "
            + valorUnidade_KEY + " REAL NOT NULL, "
            + porcaoUnidade_KEY + " TEXT NOT NULL, "
            + porcaoQuantidade_KEY + " INTEGER NOT NULL "
            + " );";

    /*
    CREATE TABLE "Estoque" (
	"codBarra"	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
	"quantidadeEstoque"  INTEGER NOT NULL DEFAULT 0,
	"dataAquisição"	TEXT NOT NULL,
	"validade"	TEXT NOT NULL,
	"valorComprado" REAL NOT NULL,
	FOREIGN KEY("codBarra") REFERENCES "Produto"("codBarra"),
	PRIMARY KEY ("codBarra" , "dataAquisicao")
    )
     */

    private static final String CREATE_TABLE_ESTOQUE = "CREATE TABLE " + ESTOQUE_TABLE + "("
            + codBarra_KEY + " REFERENCES \" "+ PRODUTO_TABLE +" \"(\""+codBarra_KEY+"\"), "
            + estoque_quantidade_KEY + " INTEGER NOT NULL DEFAULT 0, "
            + dataAquisicao_KEY + " TEXT NOT NULL, "
            //+ validade_KEY + " TEXT NOT NULL, "
            + valorComprado_KEY + " REAL NOT NULL, "
            + "PRIMARY KEY(\""+codBarra_KEY+"\",\""+dataAquisicao_KEY+"\")"
            + ");";

    public databaseHelper() {
        super(MyApp.getAppContext(), DB_NAME, null, DB_VERSION);
    }

    public static databaseHelper getInstance(){
        return INSTANCE;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("BANCO_DADOS", "Iniciando criacao das tabelas");
        db.execSQL(CREATE_TABLE_USUARIO);
        db.execSQL(CREATE_TABLE_SUGESTOES);
        db.execSQL(CREATE_TABLE_COMPRA);
        db.execSQL(CREATE_TABLE_PRODUTO);
        db.execSQL(CREATE_TABLE_COMPRA_PRODUTO);
        db.execSQL(CREATE_TABLE_PORCAO_PRODUTO);
        db.execSQL(CREATE_TABLE_ESTOQUE);
        Log.i("BANCO_DADOS", "Tabelas criadas com sucesso");

        Log.i("BANCO_DADOS", "Inserindo usuário especial");
        //Cadastra usuario administrador ja ao recriar a tabela
        ContentValues values = new ContentValues();
        values.put(nome_KEY, "Especial");
        values.put(email_KEY, "Administrador@cantina.br");
        values.put(senha_KEY, "123");
        values.put(cadastrante_KEY, 1);
        db.insert(USER_TABLE, null, values);

        Log.i("BANCO_DADOS", "Inserindo usuário comum");
        //Cadastra usuario administrador ja ao recriar a tabela
        values = new ContentValues();
        values.put(nome_KEY, "Comum");
        values.put(email_KEY, "User1@cantina.br");
        values.put(senha_KEY, "123");
        values.put(cadastrante_KEY, 0);
        db.insert(USER_TABLE, null, values);

        //Log.i("BANCO_DADOS", "Inserindo estoques fantasma");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS '" + USER_TABLE + "'");
        db.execSQL("DROP TABLE IF EXISTS '" + SUGESTAO_TABLE + "'");
        db.execSQL("DROP TABLE IF EXISTS '" + COMPRA_TABLE + "'");
        db.execSQL("DROP TABLE IF EXISTS '" + PRODUTO_TABLE + "'");
        db.execSQL("DROP TABLE IF EXISTS '" + COMPRA_PRODUTO_TABLE + "'");
        db.execSQL("DROP TABLE IF EXISTS '" + PORCAO_PRODUTO_TABLE + "'");
        db.execSQL("DROP TABLE IF EXISTS '" + ESTOQUE_TABLE + "'");
        Log.i("BANCO_DADOS", "onUpgrade called");
        onCreate(db);
    }

    //Funcoes crud de usuario

    public String cadastrarUsuario(String nome, String email, String senha, int cadastrante){
        Cursor cursor;
        //Abre o banco em modo leitura
        db = INSTANCE.getReadableDatabase();

        //Verifica se o nome de usuario ja esta cadastrado
        cursor = db.query(USER_TABLE, new String[] {nome_KEY}, nome_KEY + " = ?", new String[]{nome}, null, null, null, null);
        if(cursor.getCount() != 0) {
            db.close();
            return "Nome de usuário já cadastrado";
        }
        //Verifica se o email ja esta cadastrado
        cursor = db.query(USER_TABLE, new String[] {email_KEY}, email_KEY + " = ?", new String[]{email}, null, null, null, null);
        if(cursor.getCount() != 0) {
            db.close();
            return "Email já cadastrado";
        }

        //Se nao ha nem nome de usuario e nem email cadastrado
        db.close();
        //Abre o banco em modo escrita
        db = INSTANCE.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(nome_KEY, nome);
        values.put(email_KEY, email);
        values.put(senha_KEY, senha);
        values.put(cadastrante_KEY, cadastrante);
        db.insert(USER_TABLE, null, values);

        //Fecha conexao com o banco
        db.close();
        return "Usuário cadastrado com sucesso";
    }

    public String validarUsuario(String nome, String senha){
        Cursor cursor;
        //Abre o banco em modo leitura
        db = INSTANCE.getReadableDatabase();

        //Procura usuario no banco
        cursor = db.query(USER_TABLE, new String[] {nomeProduto_KEY, senha_KEY, cadastrante_KEY}, nomeProduto_KEY + " = ?", new String[]{nome}, null, null, null, null);
        if(cursor.getCount() == 0) {
            db.close();
            return "Usuário inválido";
        }

        cursor.moveToFirst();
        if(senha.equals(cursor.getString(1))){
            if(cursor.getInt(2) == 1){
                db.close();
                return "Usuário especial";
            }else{
                db.close();
                return "Usuário comum";
            }
        }else{
            db.close();
            return "Senha inválida";
        }
    }

    //Funcoes crud do usuario especial

    public ArrayList<String[]> getEstoque(){
        Cursor cursor, cursorNome;
        //Abre o banco em modo leitura
        db = INSTANCE.getReadableDatabase();

        ArrayList<String[]> list = new ArrayList<>();

        //Adquire todos estoques
        cursor = db.query(ESTOQUE_TABLE, new String[] {codBarra_KEY, estoque_quantidade_KEY, dataAquisicao_KEY, validade_KEY, valorComprado_KEY}, null, null, null, null, null, null);
        cursor.moveToFirst();

        //Salva num arraylist
        while (!cursor.isAfterLast()) {
            String codBarra = cursor.getString(cursor.getColumnIndex(codBarra_KEY));
            String quantidade = cursor.getString(cursor.getColumnIndex(estoque_quantidade_KEY));
            String aquisicao = cursor.getString(cursor.getColumnIndex(dataAquisicao_KEY));
            String validade = cursor.getString(cursor.getColumnIndex(validade_KEY));
            String valorComprado = cursor.getString(cursor.getColumnIndex(valorComprado_KEY));

            //Adquire o nome do produto
            cursorNome = db.query(PRODUTO_TABLE, new String[] {nomeProduto_KEY}, codBarra_KEY + " =?", new String[] {codBarra}, null, null, null, null);
            cursorNome.moveToFirst();

            list.add(new String[] {cursorNome.getString(0), codBarra, quantidade, valorComprado, aquisicao, validade});
            cursor.moveToNext();
        }

        return list;
    }

    //TODO --- Se der tempo
    public  ArrayList<String[]> getVencidos(){
        return new ArrayList<String[]>();
    }

    //Funcoes crud de sugestoes

    //TODO --- Se der tempo
    public void addSugestao(int idUser, String mensagem, int anonimo){
        //Abre o banco em modo escrita
        db = INSTANCE.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(idUser_KEY, idUser);
        values.put(mensagem_KEY, mensagem);
        values.put(anonimo_KEY, anonimo);
        db.insert(SUGESTAO_TABLE, null, values);

        //Fecha conexao com o banco
        db.close();
    }

    //Funcoes crud de produto/estoque/porcao

    public String cadastrarProduto(String codBarra, String nome){
        Cursor cursor;
        //Abre o banco em modo escrita
        db = INSTANCE.getWritableDatabase();

        //Verifica se o email ja esta cadastrado
        cursor = db.query(PRODUTO_TABLE, new String[] {codBarra_KEY}, codBarra_KEY + " = ?", new String[]{codBarra}, null, null, null, null);
        if(cursor.getCount() != 0) {
            db.close();
            return "Código de barra já cadastrado";
        }
        //Verifica se o nome de usuario ja esta cadastrado
        cursor = db.query(PRODUTO_TABLE, new String[] {nomeProduto_KEY}, nomeProduto_KEY + " = ?", new String[]{nome}, null, null, null, null);
        if(cursor.getCount() != 0) {
            db.close();
            return "Nome de produto já cadastrado";
        }

        ContentValues values = new ContentValues();
        values.put(codBarra_KEY, Integer.parseInt(codBarra));
        values.put(nomeProduto_KEY, nome);
        db.insert(PRODUTO_TABLE, null, values);

        //Fecha conexao com o banco
        db.close();
        return "Produto cadastrado";
    }

    public String addEstoque(String codBarra, String quantidade, String valor){
        Cursor cursor;
        //Abre o banco em modo escrita
        db = INSTANCE.getWritableDatabase();

        //Verifica se o produto ja esta cadastrado
        cursor = db.query(PRODUTO_TABLE, new String[] {codBarra_KEY}, codBarra_KEY + " = ?", new String[]{codBarra}, null, null, null, null);
        if(cursor.getCount() == 0) {
            db.close();
            return "Este produto ainda não foi cadastrado";
        }

        //Verifica se ja tem estoque cadastrado
        cursor = db.query(ESTOQUE_TABLE, new String[] {codBarra_KEY}, codBarra_KEY + " = ?", new String[]{codBarra}, null, null, null, null);
        if(cursor.getCount() == 0) {
            //Adquire data atual
            SimpleDateFormat dateFormat = new SimpleDateFormat("DD-MM-YYYY");
            Date data = new Date();
            Calendar cal = Calendar.getInstance();
            cal.setTime(data);
            Date data_atual = cal.getTime();
            String data_atual_str = dateFormat.format(data);

            //Cadastra o estoque
            ContentValues values = new ContentValues();
            values.put(codBarra_KEY, Integer.parseInt(codBarra));
            values.put(estoque_quantidade_KEY, Integer.parseInt(quantidade));
            values.put(dataAquisicao_KEY, data_atual_str);
            //values.put(validade_KEY, validade);
            values.put(valorComprado_KEY, Float.parseFloat(valor));
            //Insere o estoque na tabela de estoques
            db.insert(ESTOQUE_TABLE, null, values);

            //Fecha conexao com o banco
            db.close();
            return "Estoque cadastrado com sucesso";
        }else{
            //Adquire data atual
            SimpleDateFormat dateFormat = new SimpleDateFormat("DD-MM-YYYY");
            Date data = new Date();
            Calendar cal = Calendar.getInstance();
            cal.setTime(data);
            Date data_atual = cal.getTime();
            String data_atual_str = dateFormat.format(data);

            //Adquire o estoque ja existente
            cursor = db.query(ESTOQUE_TABLE, new String[] {estoque_quantidade_KEY}, codBarra_KEY + " =?", new String[] {codBarra}, null, null, null, null);
            cursor.moveToFirst();

            //Cadastra o estoque
            ContentValues values = new ContentValues();
            values.put(estoque_quantidade_KEY, cursor.getInt(0) + Integer.parseInt(quantidade)); //Atualiza a qt em estoque
            values.put(dataAquisicao_KEY, data_atual_str); //Reatualiza a data de restoque
            values.put(valorComprado_KEY, Float.parseFloat(valor)); //Atualiza o valor do produto
            //Insere o estoque na tabela de estoques
            db.update(ESTOQUE_TABLE, values, codBarra_KEY + " =?", new String[] {codBarra});

            //Fecha conexao com o banco
            db.close();
            return "Estoque atualizado com sucesso";
        }
    }

    public String addPorcao(String codBarra, String valorPorcao, String porcaoUnidade, String qtPorcao){
        Cursor cursor;
        //Abre o banco em modo escrita
        db = INSTANCE.getWritableDatabase();

        //Verifica se o produto ja esta cadastrado
        cursor = db.query(PRODUTO_TABLE, new String[] {codBarra_KEY}, codBarra_KEY + " = ?", new String[]{codBarra}, null, null, null, null);
        if(cursor.getCount() == 0) {
            db.close();
            return "Este produto ainda não foi cadastrado";
        }
        //Verifica se a unidade de porcao ja foi cadastrada para esse produto
        cursor = db.query(PORCAO_PRODUTO_TABLE, new String[] {codBarra_KEY, porcaoUnidade_KEY}, codBarra_KEY + " =? AND " + porcaoUnidade_KEY + " =? AND " + porcaoQuantidade_KEY + " =? ",  new String[]{codBarra, porcaoUnidade, qtPorcao}, null, null, null, null);
        if(cursor.getCount() != 0) {
            db.close();
            return "Essa unidade de porção já foi cadastrada para este produto";
        }

        //Cadastra a porcao
        ContentValues values = new ContentValues();
        values.put(codBarra_KEY, Integer.parseInt(codBarra));
        values.put(valorUnidade_KEY, Float.parseFloat(valorPorcao));
        values.put(porcaoUnidade_KEY, porcaoUnidade);
        values.put(porcaoQuantidade_KEY, Integer.parseInt(qtPorcao));
        //Insere o estoque na tabela de estoques
        db.insert(PORCAO_PRODUTO_TABLE, null, values);

        //Fecha conexao com o banco
        db.close();
        return "Porção cadastrada com sucesso";
    }

    public String deleteProduto(String codBarra){
        //Abre o banco em modo escrita
        db = INSTANCE.getWritableDatabase();

        //Remove estoque
        db.delete(ESTOQUE_TABLE, codBarra_KEY + " =?", new String[] {codBarra});

        //Remove porcao
        db.delete(PORCAO_PRODUTO_TABLE, codBarra_KEY + " =?", new String[] {codBarra});

        //Remove produto
        int n = db.delete(PRODUTO_TABLE, codBarra_KEY + " =?", new String[] {codBarra});

        //Fecha conexao com o banco
        db.close();

        //Retorna o resultado
        if(n == 0){
            return "Não há produto com esses dados";
        }
        return "Produto removido com sucesso";
    }

    public String deleteEstoque(String codBarra, String dataAquisicao){
        //Abre o banco em modo escrita
        db = INSTANCE.getWritableDatabase();

        int n = db.delete(ESTOQUE_TABLE, codBarra_KEY + " =? AND " + dataAquisicao_KEY + " =?", new String[] {codBarra, dataAquisicao});

        //Fecha conexcao com banco
        db.close();

        //Retorna resultado
        if(n == 0){
            return "Não há estoque com esses dados";
        }

        return "Estoque removido com sucesso";
    }

    public String deletePorcao(String codBarra, String porcaoUnidade, String qtPorcao){
        //Abre o banco em modo escrita
        db = INSTANCE.getWritableDatabase();

        int n = db.delete(PORCAO_PRODUTO_TABLE, codBarra_KEY + " =? AND " + porcaoUnidade_KEY + " =? AND " + porcaoQuantidade_KEY + " =?", new String[] {codBarra, porcaoUnidade, qtPorcao});

        //Fecha conexcao com banco
        db.close();

        //Retorna resultado
        if(n == 0){
            return "Não há porção com esses dados";
        }

        return "Porção removida com sucesso";
    }

    //Funcoes crud de compra

    public void efetuarCompra(String username, List<produto> produtos, float valorTotal){
        // Abre o banco em modo escrita
        db = INSTANCE.getWritableDatabase();

        //Adquire id do usuario
        Cursor cursor = db.query(USER_TABLE, new String[] {idUser_KEY, valorAberto_KEY}, nome_KEY + " = ?", new String[]{username}, null, null, null, null);
        int idUser = cursor.getInt(0);
        float valorAberto = cursor.getFloat(1);

        // Gera uma compra
        ContentValues values = new ContentValues();
        values.put(idUser_KEY, idUser);

        // Adquire data atual
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date data = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(data);
        Date data_atual = cal.getTime();
        String data_atual_str = dateFormat.format(data);

        values.put(dataEfetivada_KEY, data_atual_str);
        values.put(valorTotal_KEY, valorTotal);

        long oid_compra = db.insert(COMPRA_TABLE, null, values);

        // Para cada produto
        for(produto prd : produtos) {
            // Adicionar oid_compra e o codBarra relativo a ele na tabela compra_produto
            values = new ContentValues();
            values.put(idCompra_KEY, oid_compra);
            values.put(codBarra_KEY, prd.getCodBarra());
            db.insert(COMPRA_PRODUTO_TABLE, null, values);

            //Abate do estoque
            cursor = db.query(ESTOQUE_TABLE, new String[] {estoque_quantidade_KEY}, codBarra_KEY + " =? AND " + dataAquisicao_KEY + " =?", new String[] {prd.getCodBarra(), prd.getDataAquisicao()}, null, null, null, null);
            cursor.moveToFirst();
            values = new ContentValues();
            values.put(estoque_quantidade_KEY, cursor.getInt(0) - 1 );
            db.update(ESTOQUE_TABLE, values, codBarra_KEY + " =? AND " + dataAquisicao_KEY + " =?", new String[] {prd.getCodBarra(), prd.getDataAquisicao()});

            //Adiciona o valor ao estrato do usuario
            values = new ContentValues();
            values.put(valorAberto_KEY, valorAberto+prd.getPreco());
            db.update(USER_TABLE, values, nome_KEY + " =?", new String[] {username});
        }
        db.close();
    }

    public ArrayList<String[]> getProdutos(String idCompra){
        Cursor cursor, cursorProduto, cursorPorcao;
        // Abre o banco em modo escrita
        db = INSTANCE.getWritableDatabase();

        ArrayList<String[]> list = new ArrayList<>();

        cursor = db.query(COMPRA_PRODUTO_TABLE, new String[] {codBarra_KEY}, idCompra_KEY + " =?", new String[] {idCompra}, null, null, null ,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String codBarra = cursor.getString(cursor.getColumnIndex(codBarra_KEY));

            cursorProduto = db.query(PRODUTO_TABLE,new String[] {nomeProduto_KEY}, codBarra_KEY + " =?", new String[] {codBarra} , null, null, null, null);
            cursorProduto.moveToFirst();
            String nome = cursorProduto.getString(cursorProduto.getColumnIndex(nomeProduto_KEY));

            cursorPorcao = db.query(PORCAO_PRODUTO_TABLE,new String[] {valorUnidade_KEY, porcaoUnidade_KEY, porcaoQuantidade_KEY}, codBarra_KEY + " =?", new String[] {codBarra} , null, null, null, null);
            cursorPorcao.moveToFirst();

            while (!cursorPorcao.isAfterLast()){
                String valorUnidade = cursorPorcao.getString(cursorPorcao.getColumnIndex(valorUnidade_KEY));
                String porcaoUnidade = cursorPorcao.getString(cursorPorcao.getColumnIndex(porcaoUnidade_KEY));
                String qtPorcao = cursorPorcao.getString(cursorPorcao.getColumnIndex(porcaoQuantidade_KEY));

                list.add(new String[] {codBarra, nome, valorUnidade, porcaoUnidade, qtPorcao});
                cursorPorcao.moveToNext();
            }
            cursor.moveToNext();
        }

        return list;
    }

    //TODO --- Historico mensal
    public ArrayList<String[]> historico(String username, boolean mensal){
        Cursor cursor;
        // Abre o banco em modo escrita
        db = INSTANCE.getWritableDatabase();

        ArrayList<String[]> list = new ArrayList<>();

        if(mensal){
            //Retorna o historico a partir do dia 15 do ultimo mes

        }else{
            //Retorna o historico geral
            cursor = db.query(USER_TABLE, new String[] {idUser_KEY}, nome_KEY + " =?", new String[] {username}, null, null, null, null);
            cursor.moveToFirst();
            String idUser = cursor.getString(0);

            cursor = db.query(COMPRA_TABLE, new String[] {idCompra_KEY, dataEfetivada_KEY, valorTotal_KEY}, idUser_KEY + " =?", new String[] {idUser}, null, null, null ,null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                String idCompra = cursor.getString(cursor.getColumnIndex(idCompra_KEY));
                String dataEfetivada = cursor.getString(cursor.getColumnIndex(dataEfetivada_KEY));
                String valorTotal = cursor.getString(cursor.getColumnIndex(valorTotal_KEY));

                list.add(new String[] {idCompra, dataEfetivada, valorTotal});
                cursor.moveToNext();
            }
        }
        return list;
    }
}
